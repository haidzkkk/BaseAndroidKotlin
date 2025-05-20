package com.app.motel.feature.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.app.motel.data.model.Quiz
import com.app.motel.feature.quiz.viewmodel.QuizViewModel
import com.app.motel.ui.PickDate
import com.history.vietnam.AppApplication
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.core.AppBaseDialog
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.DialogDatePickerBinding
import com.history.vietnam.databinding.FragmentQuizListBinding
import javax.inject.Inject
import kotlin.math.abs

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
            QuizActivity.startActivity(requireActivity(), item.id ?: "")
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

        views.btnYear.setOnClickListener {
            pickYear()
        }
        views.btnSort.setOnClickListener {
            viewModel.liveData.typeSort.apply {
                postValue(this.value?.next())
            }
        }
        views.txtSearch.addTextChangedListener {
            viewModel.liveData.textSearch.postValue(views.txtSearch.text.toString())
        }
    }

    fun listenStateViewModel(){
        viewModel.liveData.quizzes.observe(viewLifecycleOwner){
            updateListData()
        }
        viewModel.liveData.textSearch.observe(viewLifecycleOwner){
            updateListData()
        }
        viewModel.liveData.formYear.observe(viewLifecycleOwner){
            updateListData()

            val from = viewModel.liveData.formYear.value
            val to = viewModel.liveData.toYear.value
            views.tvYearFrom.text = from?.let { it1 ->  "${abs(it1)}${if(it1 < 0) "TCN" else ""}"  } ?: ""
            views.tvYearDefaul.text = if(from == null && to == null) "Bất kỳ" else "-"
        }
        viewModel.liveData.toYear.observe(viewLifecycleOwner){
            updateListData()

            val from = viewModel.liveData.formYear.value
            val to = viewModel.liveData.toYear.value

            views.tvYearTo.text = to?.let { it1 ->  "${abs(it1)}${if(it1 < 0) "TCN" else ""}"  } ?: ""
            views.tvYearDefaul.text = if(from == null && to == null) "Bất kỳ" else "-"
        }
        viewModel.liveData.typeSort.observe(viewLifecycleOwner){
            updateListData()

            views.tvSort.text = it?.value ?: ""
        }
    }

    private fun updateListData(){
        val data = viewModel.liveData.filterListQuizzes
        adapter.updateData(data)
        views.tvEmpty.isVisible = data.isEmpty()
    }

    private fun pickYear(){
        PickDate.pickFormToYear(
            requireContext(),
            from = viewModel.liveData.formYear.value,
            to = viewModel.liveData.toYear.value,
            onConfirm = { from, to ->
                viewModel.liveData.formYear.postValue(from)
                viewModel.liveData.toYear.postValue(to)
            },
            onRemove = { _, _ ->
                viewModel.liveData.formYear.postValue(null)
                viewModel.liveData.toYear.postValue(null)
            }
        )
    }
}