package com.history.vietnam.feature.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.motel.data.model.PageInfo
import com.app.motel.feature.historicalEvent.HistoricalEventActivity
import com.app.motel.feature.historicalFigure.HistoricalFigureActivity
import com.app.motel.ui.adapter.SavedHorizontalAdapter
import com.app.motel.feature.territory.TerritoryActivity
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentHomeBinding
import com.history.vietnam.ultis.DateConverter
import com.history.vietnam.ultis.navigateFragmentWithSlide
import com.history.vietnam.ultis.startActivityWithSlide
import java.util.Date
import javax.inject.Inject

class HomeFragment @Inject constructor() : AppBaseFragment<FragmentHomeBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mViewModel : HomeViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(HomeViewModel::class.java)
    }

    private val savedAdapter = SavedHorizontalAdapter(object : AppBaseAdapter.AppListener<PageInfo>(){
        override fun onClickItem(item: PageInfo, action: AppBaseAdapter.ItemAction) {
            item.apply {
                this.action = PageInfo.Action.DETAIL
            }.startActivity(requireActivity())
        }
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        init()
        listenStateViewModel()
    }

    fun init(){
        views.btnDainastiTerritory.setOnClickListener {
            requireActivity().startActivityWithSlide(Intent(requireActivity(), TerritoryActivity::class.java))
        }
        views.btnTimeLineHistoryVietnam.setOnClickListener {
            requireActivity().startActivityWithSlide(Intent(requireActivity(), HistoricalEventActivity::class.java))
        }
        views.btnHistoryCharacter.setOnClickListener {
            requireActivity().startActivityWithSlide(Intent(requireActivity(), HistoricalFigureActivity::class.java))
        }
        views.searchBox.setOnClickListener {
            val extras = FragmentNavigatorExtras(
                views.searchBox to "searchTextInput"
            )
            navigateFragmentWithSlide(R.id.searchFragment, navigatorExtras = extras)
        }
        mViewModel.handle(HomeViewAction.getMotelViewAction)

        mViewModel.liveData.apply {

        }

        views.rcvSaved.adapter = savedAdapter
        views.rcvSaved.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    fun listenStateViewModel(){
        mViewModel.userController.state.currentUser.observe(viewLifecycleOwner){
            val saves = mViewModel.userController.state.currentSavePages.values.toList().let {
                it.sortedByDescending{ page -> DateConverter.stringToDate(page.time) ?: Date(0) }
            }
            savedAdapter.updateData(saves)

            views.tvSaved.isVisible = saves.isNotEmpty()
            views.rcvSaved.isVisible = saves.isNotEmpty()
        }
    }
}