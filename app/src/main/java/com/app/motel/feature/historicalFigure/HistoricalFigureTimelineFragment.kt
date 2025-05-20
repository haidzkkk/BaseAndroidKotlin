package com.app.motel.feature.historicalFigure

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.motel.data.model.HistoricalFigure
import com.app.motel.data.model.HistoryDynasty
import com.app.motel.feature.historicalFigure.adapter.DynastyAdapter
import com.app.motel.feature.historicalFigure.viewmodel.HistoricalFigureViewModel
import com.app.motel.feature.page.PageFragment
import com.google.gson.Gson
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentHistoricalFigureTimelineBinding
import com.history.vietnam.ultis.navigateFragmentWithSlide
import javax.inject.Inject

class HistoricalFigureTimelineFragment: AppBaseFragment<FragmentHistoricalFigureTimelineBinding>() {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHistoricalFigureTimelineBinding {
        return FragmentHistoricalFigureTimelineBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : HistoricalFigureViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(HistoricalFigureViewModel::class.java)
    }

    private var adapter: DynastyAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        init()
        listenStateViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.setCurrentFigure(null)
    }


    private fun init() {
        if(adapter == null){
            adapter = DynastyAdapter(viewModel.settingRepository,
                object : AppBaseAdapter.AppListener<HistoryDynasty>(){
                    override fun onClickItem(item: HistoryDynasty, action: AppBaseAdapter.ItemAction) {
                        viewModel.setCurrentDynasty(item)
                    }
                },
                object : AppBaseAdapter.AppListener<HistoricalFigure>(){
                    override fun onClickItem(item: HistoricalFigure, action: AppBaseAdapter.ItemAction) {
                        viewModel.setInfoSelect(null)
                        viewModel.setCurrentFigure(item)
                        navigateFragmentWithSlide(R.id.pageFragmentFigure, PageFragment.getPageInfo(item, adapter?.getCurrentDynasty?.id))
                    }
                },
            )
        }

        views.rcv.adapter = adapter
        viewModel.getHistoryFigure()


        views.rcv.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                try {
                    val items = viewModel.liveData.historyDynasty.value ?: arrayListOf()
                    val position = (views.rcv.layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition() ?: -1
                    val item = items[position]
                    viewModel.postCurrentDynasty(item)
                }catch (e: Exception){
                }
            }
        })
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    private fun listenStateViewModel() {
        Log.e(" HistoricalFigureTimelineFragment", "listenStateViewModel: ")
        viewModel.liveData.historyDynasty.observe(viewLifecycleOwner){
            adapter?.updateData(it)
           views.tvEmpty.isVisible = it.isNullOrEmpty()

            adapter?.figureAdapter?.setCurrentFigureSelectId(viewModel.liveData.getSelectInfoId)
            viewModel.liveData.getSelectTimeLineId?.apply {
                it?.forEachIndexed { index, historicalEvent ->
                    if(historicalEvent.id == this){
                        adapter?.setSelectPosition(index)
                        views.rcv.smoothScrollToPosition(index)
                        return@forEachIndexed
                    }
                }
            }
        }

        viewModel.settingRepository.state.textSize.observe(viewLifecycleOwner){
            adapter?.notifyDataSetChanged()
        }
        viewModel.settingRepository.state.textFont.observe(viewLifecycleOwner){
            adapter?.notifyDataSetChanged()
        }
        viewModel.settingRepository.state.backgroundColor.observe(viewLifecycleOwner){
            adapter?.notifyDataSetChanged()
        }
    }
}