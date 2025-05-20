package com.app.motel.feature.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.motel.data.model.Quiz
import com.app.motel.feature.historicalEvent.viewmodel.HistoricalEventViewModel
import com.app.motel.feature.quiz.viewmodel.QuizViewModel
import com.history.vietnam.AppApplication
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentQuizListBinding
import javax.inject.Inject

class QuizListFragment : AppBaseFragment<FragmentQuizListBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentQuizListBinding {
        return FragmentQuizListBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : QuizViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(QuizViewModel::class.java)
    }

    val adapter = QuizAdapter(object : AppBaseAdapter.AppListener<Quiz>(){
        override fun onClickItem(item: Quiz, action: AppBaseAdapter.ItemAction) {
            QuizActivity.startActivity(requireActivity(), item)
        }
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        init()
        listenStateViewModel()
    }

    fun init(){
        viewModel.getQuizzes()

        views.rcv.adapter = adapter
    }

    fun listenStateViewModel(){
        viewModel.liveData.quizzes.observe(viewLifecycleOwner){
            adapter.updateData(it)
            views.tvEmpty.isVisible = it.isNullOrEmpty()
        }
    }

}