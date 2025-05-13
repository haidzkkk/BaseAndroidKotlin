package com.app.motel.feature.territory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.motel.feature.territory.viewmodel.TerritoryViewModel
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentPageHomeBinding
import com.history.vietnam.databinding.FragmentTerritoryCommentBinding
import javax.inject.Inject

class TerritoryCommentFragment : AppBaseFragment<FragmentTerritoryCommentBinding>() {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTerritoryCommentBinding {
        return FragmentTerritoryCommentBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : TerritoryViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(TerritoryViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)


        init()
        listenStateViewModel()
    }

    private fun init() {
    }

    private fun listenStateViewModel() {
    }
}