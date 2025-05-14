package com.app.motel.feature.page.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.app.motel.data.model.PageInfo
import com.app.motel.data.model.Section
import com.app.motel.data.repository.PageRepository
import com.app.motel.data.repository.UserRepository
import com.app.motel.feature.profile.UserController
import com.app.motel.feature.setting.SettingController
import com.app.motel.ultis.IDManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.history.vietnam.core.AppBaseViewModel
import com.history.vietnam.data.model.Comment
import com.history.vietnam.data.model.Resource
import com.history.vietnam.ultis.DateConverter
import kotlinx.coroutines.launch
import javax.inject.Inject

class PageViewModel @Inject constructor(
    private val pageRepository: PageRepository,
    private val userRepository: UserRepository,
    val settingController: SettingController,
    val userController: UserController,
): AppBaseViewModel<PageState, PageViewAction, PageViewEvent>(PageState()) {
    override fun handle(action: PageViewAction) {

    }

    fun initFigure(pageInfo: PageInfo?){
        liveData.pageInfo.postValue(pageInfo)

        viewModelScope.launch {
            val summary = try {
                Resource.Success(pageRepository.getFigureSummary(pageInfo?.wikiPageId ?: ""))
            }catch (e: Exception){
                Resource.Error(e.toString())
            }
            liveData.figureSummary.postValue(summary)

            val pageId = summary.data?.pageId ?: -1
            val detail = try {
                Resource.Success(pageRepository.getFigureDetail(pageId))
            }catch (e: Exception){
                Resource.Error(e.toString())
            }
            val sectionDesc = Section.parseSections(detail.data?.query?.pages?.get(pageId.toString())?.extract ?: "")

            liveData.figureDetail.postValue(detail)
            liveData.figureContentSections = sectionDesc
        }

        if(pageInfo?.firebasePath != null){
            pageRepository.addListenerComment(pageInfo.firebasePath, commentListener)
        }
    }

    fun clearFigure(){
        pageRepository.removeListenerComment(liveData.pageInfo.value?.firebasePath ?: "", commentListener)
        liveData.pageInfo.postValue(null)
        liveData.figureSummary.postValue(Resource.Initialize())
        liveData.figureDetail.postValue(Resource.Initialize())
        liveData.figureContentSections = listOf()
        liveData.selectContent.postValue(0)
        liveData.comments.postValue(Resource.Initialize())
        liveData.currentCommentReply.postValue(null)
    }

    fun likeComment(comment: Comment) {
        if(userController.state.getCurrentUser == null) {
            userController.state.loginUser.postValue(true)
            return
        }

        if(comment.likes == null) comment.likes = hashMapOf()

        if(comment.likes!![userController.state.getCurrentUserId] != null){
            comment.likes!!.remove(userController.state.getCurrentUserId)
        }else{
            comment.likes!![userController.state.getCurrentUserId] = userController.state.getCurrentUserId
        }

        liveData.comments.postValue(Resource.Success(liveData.comments.value?.data ?: listOf()))

        viewModelScope.launch {
            pageRepository.likeComment(liveData.pageInfo.value?.firebasePath ?: "", comment)
        }
    }

    fun replyComment(comment: Comment?) {
        liveData.currentCommentReply.postValue(comment)
    }

    fun sendComment(content: String):Boolean {
        if(userController.state.getCurrentUser == null) {
            userController.state.loginUser.postValue(true)
            return false
        }

        val comment = Comment(
            id = IDManager.createID(),
            content = content,
            time = DateConverter.getCurrentStringDateTime(),
            idUser = userController.state.getCurrentUserId,
            parentCommentId = liveData.currentCommentReply.value.let { it?.parentCommentId ?: it?.id },
        ).apply {
            user = userController.state.getCurrentUser
            isUploading = true
        }

        val comments = liveData.comments.value?.data as? ArrayList<Comment> ?: arrayListOf()

        if(comment.parentCommentId != null){
            val parentComment = comments.find { it.id == comment.parentCommentId }
            if(parentComment?.comments == null) parentComment?.comments = hashMapOf()
            parentComment?.comments?.apply {
                this[comment.id ?: ""] = comment
            }
        }else{
            comments.add(0, comment)
        }

        liveData.comments.postValue(Resource.Success(comments))
        replyComment(null)

        viewModelScope.launch {
            pageRepository.sendComment(liveData.pageInfo.value?.firebasePath ?: "", comment)
        }

        return true
    }

    private val commentListener = object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            try {
                val list = snapshot.children.mapNotNull { it.getValue(Comment::class.java) }
                viewModelScope.launch {
                    for (comment in list) {
                        comment.user = userRepository.getUserById(comment.idUser)
                        comment.comments?.values?.toList()?.forEach {
                            it.user = userRepository.getUserById(it.idUser)
                        }
                    }
                    liveData.comments.postValue(Resource.Success(list))
                    Log.e("FirebaseManager", "<--- 200 comment: $list}")
                }
            } catch (e: Exception) {
                Log.e("FirebaseManager", "<--- 400 comment: ${e.message}")
            }
        }

        override fun onCancelled(error: DatabaseError) {
            Log.e("FirebaseManager", "<-- ${error.code} comment: ${error.toException()}")
        }
    }
}