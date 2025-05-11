package com.app.motel.feature.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.motel.feature.historicalFigure.viewmodel.HistoricalFigureViewModel
import com.history.vietnam.AppApplication
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentCommentBinding
import javax.inject.Inject

class CommentFragment : AppBaseFragment<FragmentCommentBinding>() {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCommentBinding {
        return FragmentCommentBinding.inflate(inflater, container, false)
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