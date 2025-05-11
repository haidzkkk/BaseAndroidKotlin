package com.history.vietnam.feature.Home

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.app.motel.data.network.FirebaseManager
import com.app.motel.feature.historicalEvent.HistoricalEventActivity
import com.app.motel.feature.historicalEvent.HistoricalEventTimeLineFragment
import com.app.motel.feature.historicalFigure.HistoricalFigureActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.history.vietnam.AppApplication
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.data.model.Resource
import com.history.vietnam.databinding.FragmentHomeBinding
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

        views.btnTimeLineHistoryVietnam.setOnClickListener {
            requireActivity().startActivityWithSlide(Intent(requireActivity(), HistoricalEventActivity::class.java))
        }
        views.btnHistoryCharacter.setOnClickListener {
            requireActivity().startActivityWithSlide(Intent(requireActivity(), HistoricalFigureActivity::class.java))
        }

        mViewModel.sendEventTest()
        mViewModel.handle(HomeViewAction.getMotelViewAction)

        mViewModel.liveData.apply {

        }

        super.onViewCreated(view, savedInstanceState)
    }

}