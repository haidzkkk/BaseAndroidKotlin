package com.app.motel.feature.historicalFigure

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.app.motel.data.model.HistoricalFigure
import com.app.motel.data.model.HistoryDynasty
import com.app.motel.feature.historicalFigure.adapter.DynastyAdapter
import com.app.motel.feature.historicalFigure.viewmodel.HistoricalFigureViewModel
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

    private fun init() {
        if(adapter == null){
            adapter = DynastyAdapter(viewModel.settingRepository,
                object : AppBaseAdapter.AppListener<HistoryDynasty>(){
                    override fun onClickItem(item: HistoryDynasty, action: AppBaseAdapter.ItemAction) {
                        viewModel.postCurrentDynasty(item)
                    }
                },
                object : AppBaseAdapter.AppListener<HistoricalFigure>(){
                    override fun onClickItem(item: HistoricalFigure, action: AppBaseAdapter.ItemAction) {
                        navigateFragmentWithSlide(R.id.historicalFigureFragment, Bundle().apply {
                            putString(HistoricalFigureFragment.KEY_ITEM_FIGURE, Gson().toJson(item))
                        })
                    }
                },
            )
        }

        views.rcv.adapter = adapter
        viewModel.getHistoryFigure()
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    private fun listenStateViewModel() {
        Log.e(" HistoricalFigureTimelineFragment", "listenStateViewModel: ")
        viewModel.liveData.historyDynasty.observe(viewLifecycleOwner){
            adapter?.updateData(it)
           views.tvEmpty.isVisible = it.isNullOrEmpty()
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