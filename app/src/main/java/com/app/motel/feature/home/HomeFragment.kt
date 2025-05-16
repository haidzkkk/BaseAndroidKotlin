package com.history.vietnam.feature.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.app.motel.feature.historicalEvent.HistoricalEventActivity
import com.app.motel.feature.historicalFigure.HistoricalFigureActivity
import com.app.motel.feature.territory.TerritoryActivity
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentHomeBinding
import com.history.vietnam.ultis.navigateFragmentWithSlide
import com.history.vietnam.ultis.startActivityWithSlide
import javax.inject.Inject

class HomeFragment @Inject constructor() : AppBaseFragment<FragmentHomeBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val mViewModel : HomeViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)

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

        super.onViewCreated(view, savedInstanceState)
    }

}