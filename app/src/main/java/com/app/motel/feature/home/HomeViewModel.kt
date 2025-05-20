package com.history.vietnam.feature.Home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.app.motel.data.model.PageInfo
import com.app.motel.data.repository.QuizRepository
import com.app.motel.feature.profile.UserController
import com.history.vietnam.core.AppBaseViewModel
import com.history.vietnam.data.model.Resource
import com.history.vietnam.data.repository.HomeRepository
import com.history.vietnam.ultis.containsRemoveAccents
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repo: HomeRepository,
    private val quizRepository: QuizRepository,
    val userController: UserController,
    ) : AppBaseViewModel<HomeViewLiveData, HomeViewAction, HomeViewEvent>(HomeViewLiveData()) {

    init {
        setupData()
    }
    override fun handle(action: HomeViewAction) {

    }

    private fun setupData() {
        viewModelScope.launch {
//            repo.pushFigures()
//            repo.pushEvent()
//            repo.pushTerritory()
//            repo.pushQuiz()
        }
    }

    fun search(keyword: String){
        liveData.searchInfo.postValue(Resource.Loading())
        viewModelScope.launch {
            val searchSection = arrayListOf<PageInfo>()

            val dynasties = repo.getDynasty().data ?: arrayListOf()
            val territories = repo.getTerritory().data ?: arrayListOf()
            val events = repo.getEvents().data ?: arrayListOf()
            val quizzes = quizRepository.getQuizzes().data ?: arrayListOf()

            quizzes.forEach {
                if(it.title?.containsRemoveAccents(keyword, true) == true
                    || it.period?.containsRemoveAccents(keyword, true) == true
                    ){
                    searchSection.add(PageInfo.fromQuiz(it))
                }
            }
            events.forEach {
                if(it.name?.containsRemoveAccents(keyword, true) == true
                    || it.birthYear?.containsRemoveAccents(keyword, true) == true
                    ){
                    searchSection.add(PageInfo.fromHistoricalEvent(it))
                }
            }
            dynasties.forEach { dynasty ->
                if(dynasty.name?.containsRemoveAccents(keyword, true) == true
                    || dynasty.fromDate?.containsRemoveAccents(keyword, true) == true
                    || dynasty.toDate?.containsRemoveAccents(keyword, true) == true
                ){
                    searchSection.add(PageInfo.fromHistoryDynasty(dynasty))
                }
                dynasty.figures?.forEach { figure ->
                    if(figure.name?.containsRemoveAccents(keyword, true) == true
                        || figure.birthYear?.containsRemoveAccents(keyword, true) == true
                        || figure.deathDate?.containsRemoveAccents(keyword, true) == true
                        || figure.spouse?.containsRemoveAccents(keyword, true) == true
                        || figure.title?.containsRemoveAccents(keyword, true) == true
                    ){
                        searchSection.add(PageInfo.fromHistoricalFigure(figure, dynasty.id))
                    }
                }
            }
            territories.forEach {
                if(it.title?.containsRemoveAccents(keyword, true) == true
                    || it.period?.containsRemoveAccents(keyword, true) == true
                ){
                    searchSection.add(PageInfo.fromTerritory(it))
                }
                it.timelineEntries?.forEach { section ->
                    if(section.title?.containsRemoveAccents(keyword, true) == true
                        || section.content?.containsRemoveAccents(keyword, true) == true
                    ) {
                        searchSection.add(PageInfo.fromSection(section, it.id ?: ""))
                    }
                }
            }

            liveData.searchInfo.postValue(Resource.Success(searchSection))
        }
    }
}