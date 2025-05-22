package com.app.motel.feature.page.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.data.model.PageInfo
import com.app.motel.data.model.Section
import com.app.motel.data.model.WikiDetail
import com.app.motel.data.model.WikiSummary
import com.history.vietnam.core.AppState
import com.history.vietnam.data.model.Comment
import com.history.vietnam.data.model.Resource

class PageState: AppState{
    val pageInfo = MutableLiveData<PageInfo>()

    val figureSummary = MutableLiveData<Resource<WikiSummary>>()
    val figureDetail = MutableLiveData<Resource<WikiDetail>>()
    var figureContentSections: List<Section> = arrayListOf()
    val comments = MutableLiveData<Resource<List<Comment>>>()

    val currentCommentReply = MutableLiveData<Comment?>()
    var selectContent = MutableLiveData<Int>(0)
    var firstSelectPageInfo = false
}