package com.app.motel.feature.historicalEvent

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.motel.data.model.HistoricalEvent
import com.app.motel.feature.historicalEvent.viewmodel.HistoricalEventViewModel
import com.app.motel.feature.page.PageFragment
import com.google.gson.Gson
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentHistoricalEventTimeLineBinding
import com.history.vietnam.ui.showToast
import com.history.vietnam.ultis.navigateFragmentWithSlide
import javax.inject.Inject

class HistoricalEventTimeLineFragment: AppBaseFragment<FragmentHistoricalEventTimeLineBinding>() {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHistoricalEventTimeLineBinding {
        return FragmentHistoricalEventTimeLineBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : HistoricalEventViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(HistoricalEventViewModel::class.java)
    }

    private var adapter: EventAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        init()
        listenStateViewModel()
    }

    private fun init() {
        if(adapter == null){
            adapter = EventAdapter(viewModel.settingRepository,
                object : AppBaseAdapter.AppListener<HistoricalEvent>(){
                    override fun onClickItem(item: HistoricalEvent, action: AppBaseAdapter.ItemAction) {
                        navigateFragmentWithSlide(R.id.pageFragmentEvent, PageFragment.getPageInfo(item))
                    }
                }
            )
        }

        views.rcv.adapter = adapter
        viewModel.getHistoryEvent()

        views.rcv.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                try {
                    val items = viewModel.liveData.historyEvents.value ?: arrayListOf()
                    val position = (views.rcv.layoutManager as? LinearLayoutManager)?.findLastVisibleItemPosition() ?: -1
                    val item = items[position]
                    viewModel.postCurrentEvent(item)
                }catch (e: Exception){
                }
            }
        })
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    private fun listenStateViewModel() {
        viewModel.liveData.historyEvents.observe(viewLifecycleOwner){
            adapter?.updateData(it)
            views.tvEmpty.isVisible = it.isNullOrEmpty()
        }
//
//        viewModel.settingRepository.state.textSize.observe(viewLifecycleOwner){
//            adapter?.notifyDataSetChanged()
//        }
//        viewModel.settingRepository.state.textFont.observe(viewLifecycleOwner){
//            adapter?.notifyDataSetChanged()
//        }
//        viewModel.settingRepository.state.backgroundColor.observe(viewLifecycleOwner){
//            adapter?.notifyDataSetChanged()
//        }
    }
}