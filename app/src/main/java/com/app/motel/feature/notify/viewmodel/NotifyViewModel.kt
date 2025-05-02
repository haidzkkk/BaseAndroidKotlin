package com.app.motel.feature.notify.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.app.motel.core.AppBaseViewModel
import com.app.motel.data.model.Notification
import com.app.motel.data.model.Resource
import com.app.motel.data.repository.ComplaintRepository
import com.app.motel.data.repository.NotificationRepository
import com.app.motel.data.repository.RoomRepository
import com.app.motel.feature.profile.UserController
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotifyViewModel @Inject constructor(
    private val complaintRepository: ComplaintRepository,
    val userController: UserController,
): AppBaseViewModel<NotifyViewState, NotifyViewAction, NotifyViewEvent>(NotifyViewState()) {
    override fun handle(action: NotifyViewAction) {

    }

    fun getComplaint(){
        viewModelScope.launch {
            try {
                val complaints = complaintRepository.getComplaint(userController.state.currentBoardingHouseId)
                liveData.complaints.postValue(complaints)
            }catch (e: Exception){
                Log.e("NotifyViewModel", "lỗi: complaints: ${e.message}")
            }
        }
    }


//    fun addNews(title: String, content: String, roomId: String?){
//
//        when{
//            !userController.state.isAdmin -> {
//                liveData.addNews.postValue(Resource.Error(message = "Bạn không có quyền thêm tin tức"))
//                return
//            }
//            userController.state.currentBoardingHouseId.isBlank() -> {
//                liveData.addNews.postValue(Resource.Error(message = "Bạn chưa chọn khu trọ"))
//                return
//            }
//            title.isBlank() -> {
//                liveData.addNews.postValue(Resource.Error(message = "Tiêu đề không được đẻ trống"))
//                return
//            }
//            content.isBlank() -> {
//                liveData.addNews.postValue(Resource.Error(message = "Nội dung không được đẻ trống"))
//                return
//            }
//        }
//
//        val news = Notification(
//            title = title,
//            content = content,
//            khuTroId = userController.state.currentBoardingHouseId,
//            phongId = roomId,
//        )
//
//        viewModelScope.launch {
//            val newsAdd = notificationRepository.addNews(news)
//            liveData.addNews.postValue(newsAdd)
//        }
//    }
}