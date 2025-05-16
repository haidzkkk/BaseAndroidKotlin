package com.app.motel.feature.territory

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.motel.data.model.PageInfo
import com.app.motel.data.model.Territory
import com.app.motel.feature.page.viewmodel.PageViewModel
import com.app.motel.feature.territory.viewmodel.TerritoryViewModel
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentPageHomeBinding
import com.history.vietnam.databinding.FragmentTerritoryHomeBinding
import javax.inject.Inject

class TerritoryHomeFragment : AppBaseFragment<FragmentTerritoryHomeBinding>() {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTerritoryHomeBinding {
        return FragmentTerritoryHomeBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : TerritoryViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(TerritoryViewModel::class.java)
    }

    var adapter: TerritoryAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)


        init()
        listenStateViewModel()
    }

    private fun init() {
        viewModel.getTerritories()

        if(adapter == null){
            adapter = TerritoryAdapter(object : AppBaseAdapter.AppListener<Territory>(){
                override fun onClickItem(item: Territory, action: AppBaseAdapter.ItemAction) {

                }
            })
        }
        views.rcv.adapter = adapter

        views.rcv.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                try {
                    val items = viewModel.liveData.territories.value ?: arrayListOf()
                    val position = (views.rcv.layoutManager as? LinearLayoutManager)?.findLastVisibleItemPosition() ?: -1
                    val item = items[position]
                    viewModel.postCurrentEvent(item)
                }catch (e: Exception){
                }
            }
        })
    }

    private fun listenStateViewModel() {
        viewModel.liveData.territories.observe(viewLifecycleOwner){
            adapter?.updateData(it)
            views.tvEmpty.isVisible = it.isNullOrEmpty()
            handleSelectInfo()
        }
        viewModel.liveData.selectContent.observe(viewLifecycleOwner){
            val position = viewModel.findTerritoryIndexFromFlatPosition(it ?: 0)
            if(position == -1) return@observe
            views.rcv.post {
                views.rcv.smoothScrollToPosition(position)
            }
        }
    }

    private fun handleSelectInfo(){
        val info = viewModel.liveData.infoSelect.value ?: return
        if(info.type == PageInfo.Type.TERRITORY || info.type == PageInfo.Type.TERRITORY_TIMELINE_ENTRY){
            val position = viewModel.liveData.getTerritoryInfoSelectIndex
            if(position != null){
                views.rcv.smoothScrollToPosition(position)
            }
        }
        if(info.type == PageInfo.Type.TERRITORY_TIMELINE_ENTRY){
            adapter?.setCurrentTerritoryId(
                viewModel.liveData.getTerritoryInfoSelectId,
                viewModel.liveData.getTimeLineInfoSelectId,
            )
        }
        viewModel.setInfoSelect(null)
    }
}