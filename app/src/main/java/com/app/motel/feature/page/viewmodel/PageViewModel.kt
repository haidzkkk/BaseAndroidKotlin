package com.app.motel.feature.page.viewmodel

import androidx.lifecycle.viewModelScope
import com.app.motel.data.model.PageInfo
import com.app.motel.data.model.Section
import com.app.motel.data.repository.PageRepository
import com.app.motel.feature.setting.SettingController
import com.history.vietnam.core.AppBaseViewModel
import com.history.vietnam.data.model.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class PageViewModel @Inject constructor(
    private val pageRepository: PageRepository,
    val settingRepository: SettingController,
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
    }

    fun clearFigure(){
        liveData.pageInfo.postValue(null)
        liveData.figureSummary.postValue(null)
        liveData.figureDetail.postValue(null)
        liveData.figureContentSections = listOf()
        liveData.selectContent.postValue(0)
    }

}