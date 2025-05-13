package com.app.motel.feature.territory

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.motel.data.model.Section
import com.app.motel.feature.page.PageIndexAdapter
import com.app.motel.feature.territory.viewmodel.TerritoryViewModel
import com.history.vietnam.AppApplication
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentTerritoryContentBinding
import javax.inject.Inject

class TerritoryContentFragment : AppBaseFragment<FragmentTerritoryContentBinding>() {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTerritoryContentBinding {
        return FragmentTerritoryContentBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : TerritoryViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(TerritoryViewModel::class.java)
    }

    private var adapter: PageIndexAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveData

        init()
        listenStateViewModel()
    }

    private fun init() {
        if(adapter == null){
            adapter = PageIndexAdapter(object : AppBaseAdapter.AppListener<Int>(){
                override fun onClickItem(item: Int, action: AppBaseAdapter.ItemAction) {
                    viewModel.liveData.selectContent.postValue(item)
                }
            })
        }
        views.rcv.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun listenStateViewModel() {
        viewModel.liveData.selectContent.observe(viewLifecycleOwner){
            adapter?.setCurrentPosition(it)
        }
        viewModel.liveData.territories.observe(viewLifecycleOwner){
            val sections = it?.flatMap { territory ->
                val list = arrayListOf<Section>()
                list.add(Section(
                    title = territory.title ?: "",
                    content = "",
                    level = 1
                ))
                territory.timelineEntries?.apply {list.addAll(this)}
                list
            }
            adapter?.updateData(sections)
        }
    }
}