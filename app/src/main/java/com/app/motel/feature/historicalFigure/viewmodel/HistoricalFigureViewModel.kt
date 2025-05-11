package com.app.motel.feature.historicalFigure.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.motel.data.model.HistoricalFigure
import com.app.motel.data.model.HistoryDynasty
import com.app.motel.data.model.Section
import com.app.motel.data.repository.HistoricalFigureRepository
import com.app.motel.feature.setting.SettingController
import com.history.vietnam.core.AppBaseViewModel
import com.history.vietnam.data.model.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoricalFigureViewModel @Inject constructor(
    private val historicalFigureRepository: HistoricalFigureRepository,
    val settingRepository: SettingController,
): AppBaseViewModel<HistoricalFigureState, HistoricalFigureViewAction, HistoricalFigureViewEvent>(HistoricalFigureState()) {
    override fun handle(action: HistoricalFigureViewAction) {

    }

    fun getHistoryFigure() {
        viewModelScope.launch {
            val historyFigures = historicalFigureRepository.getHistoryFigures()
            liveData.historyDynasty.postValue(historyFigures.data)
        }
    }

    fun initFigure(figure: HistoricalFigure?){
        liveData.figure.postValue(figure)

        viewModelScope.launch {
            val summary = try {
                Resource.Success(historicalFigureRepository.getFigureSummary(figure?.wikiPageId ?: ""))
            }catch (e: Exception){
                Resource.Error(e.toString())
            }
            liveData.figureSummary.postValue(summary)

            val pageId = summary.data?.pageId ?: -1
            val detail = try {
                Resource.Success(historicalFigureRepository.getFigureDetail(pageId))
            }catch (e: Exception){
                Resource.Error(e.toString())
            }
            val sectionDesc = Section.parseSections(detail.data?.query?.pages?.get(pageId.toString())?.extract ?: "")

            liveData.figureDetail.postValue(detail)
            liveData.figureContentSections = sectionDesc
        }
    }

    fun clearFigure(){
        liveData.figure.postValue(null)
        liveData.figureSummary.postValue(null)
        liveData.figureDetail.postValue(null)
        liveData.figureContentSections = listOf()
        liveData.selectContent.postValue(0)
    }

    fun postCurrentDynasty(historyDynasty: HistoryDynasty){
        _viewEvents.post(HistoricalFigureViewEvent.SetCurrentDynasty(historyDynasty))
    }
}