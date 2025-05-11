package com.app.motel.feature.historicalFigure.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.motel.feature.historicalFigure.viewmodel.HistoricalFigureViewModel
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentHistorycalFigureCommentBinding
import com.history.vietnam.databinding.FragmentHistorycalFigureHomeBinding
import javax.inject.Inject

class HistoricalFigureCommentFragment : AppBaseFragment<FragmentHistorycalFigureCommentBinding>() {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHistorycalFigureCommentBinding {
        return FragmentHistorycalFigureCommentBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : HistoricalFigureViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(HistoricalFigureViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveData

        init()
        setUpBottomNav()
    }

    private fun init() {
    }

    private fun setUpBottomNav() {

    }
}