package com.app.motel.feature.page

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.motel.data.model.Section
import com.app.motel.feature.page.viewmodel.PageViewModel
import com.history.vietnam.AppApplication
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentIndexBinding
import javax.inject.Inject

class IndexFragment : AppBaseFragment<FragmentIndexBinding>() {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentIndexBinding {
        return FragmentIndexBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : PageViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(PageViewModel::class.java)
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
            }, viewModel.settingController, )
        }
        views.rcv.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun listenStateViewModel() {
        viewModel.liveData.selectContent.observe(viewLifecycleOwner){
            adapter?.setCurrentPosition(it)
        }
        viewModel.liveData.figureDetail.observe(viewLifecycleOwner){
            val titles = ArrayList(viewModel.liveData.figureContentSections)

            viewModel.liveData.pageInfo.value?.apply {
                titles.add(0, Section(
                    title = this.name ?: "Giới thiệu",
                    content = "",
                    level = 1
                ))
            }
            adapter?.updateData(titles)
        }
        viewModel.settingController.state.textFont.observe(viewLifecycleOwner){
            adapter?.notifyDataSetChanged()
        }
        viewModel.settingController.state.backgroundColor.observe(viewLifecycleOwner){
            adapter?.notifyDataSetChanged()
        }
    }
}