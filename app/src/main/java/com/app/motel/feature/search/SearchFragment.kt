package com.app.motel.feature.search

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.app.motel.data.model.PageInfo
import com.app.motel.feature.historicalEvent.HistoricalEventActivity
import com.app.motel.feature.historicalFigure.HistoricalFigureActivity
import com.app.motel.feature.historicalFigure.adapter.DynastyAdapter
import com.app.motel.feature.territory.TerritoryActivity
import com.app.motel.ultis.focus
import com.google.gson.Gson
import com.history.vietnam.AppApplication
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.data.model.Resource
import com.history.vietnam.databinding.FragmentSearchBinding
import com.history.vietnam.feature.Home.HomeViewModel
import com.history.vietnam.ultis.popFragmentWithSlide
import com.history.vietnam.ultis.startActivityWithSlide
import javax.inject.Inject

class SearchFragment : AppBaseFragment<FragmentSearchBinding>() {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : HomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)

        sharedElementReturnTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
    }

    private var adapter = SearchAdapter(object : AppBaseAdapter.AppListener<PageInfo>(){
        override fun onClickItem(item: PageInfo, action: AppBaseAdapter.ItemAction) {
            when(item.type){
                PageInfo.Type.DYNASTY, PageInfo.Type.HISTORICAL_FIGURE -> {
                    requireActivity().startActivityWithSlide(Intent(requireActivity(), HistoricalFigureActivity::class.java).apply {
                        putExtra(HistoricalFigureActivity.ITEM_INFO_KEY, Gson().toJson(item))
                    })
                }
                PageInfo.Type.HISTORICAL_EVENT -> {
                    requireActivity().startActivityWithSlide(Intent(requireActivity(), HistoricalEventActivity::class.java).apply {
                        putExtra(HistoricalEventActivity.ITEM_INFO_KEY, Gson().toJson(item))
                    })
                }
                PageInfo.Type.TERRITORY, PageInfo.Type.TERRITORY_TIMELINE_ENTRY -> {
                    requireActivity().startActivityWithSlide(Intent(requireActivity(), TerritoryActivity::class.java).apply {
                        putExtra(TerritoryActivity.ITEM_INFO_KEY, Gson().toJson(item))
                    })
                }
                else -> {}
            }
        }

    })
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        init()
        listenStateViewModel()
    }

    private fun init() {
        views.rcv.adapter = adapter
        views.btnBack.setOnClickListener {
            popFragmentWithSlide()
        }

        views.txtSearch.addTextChangedListener {
            views.btnClear.isVisible = views.txtSearch.text.toString().isNotEmpty()
        }

        views.btnClear.setOnClickListener {
            views.txtSearch.text?.clear()
        }

        views.btnSearch.setOnClickListener {
            val keyword = views.txtSearch.text.toString()
            viewModel.search(keyword)
        }

        views.txtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                val keyword = views.txtSearch.text.toString()
                viewModel.search(keyword)
                true
            } else {
                false
            }
        }

        views.txtSearch.focus()
    }

    @SuppressLint("SetTextI18n")
    private fun listenStateViewModel() {
        viewModel.liveData.searchInfo.observe(viewLifecycleOwner){
            when{
                it.isLoading() -> {
                    views.tvEmpty.text = "Đang tìm kiếm..."
                    views.tvEmpty.isVisible = true
                }
                it.isSuccess() -> {
                    val data = it.data ?: arrayListOf()
                    adapter.updateData(data)
                    views.tvEmpty.isVisible = data.isEmpty()
                    views.tvEmpty.text = if(data.isEmpty()) "Không tìm thấy kết quả" else "Có kết quả"
                }
                else ->{
                    views.tvEmpty.isVisible = false
                }
            }
        }
    }
}