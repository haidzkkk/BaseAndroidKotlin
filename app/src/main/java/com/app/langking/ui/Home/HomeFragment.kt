package com.app.langking.ui.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.app.langking.ultis.Status
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseFragment
import com.app.langking.databinding.FragmentHomeBinding
import com.app.langking.ui.Lean.LearnActivity
import com.app.langking.ui.adapter.CategoryAdapter
import com.app.langking.ui.auth.AuthActivity
import com.app.langking.ultis.startActivityWithTransition
import javax.inject.Inject

class HomeFragment @Inject constructor() : AppBaseFragment<FragmentHomeBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val mViewModel : HomeViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[HomeViewModel::class.java]
    }

    private lateinit var categoryAdapter: CategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as TravleApplication).travleComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        init()
        handleStateViewModel()
    }

    override fun onResume() {
        super.onResume()
        mViewModel.handle(HomeViewAction.getCategoryViewAction)
    }

    private fun init(){
        categoryAdapter = CategoryAdapter{ lesson ->
            if(lesson.userProgress != null){
                LearnActivity.start(requireActivity(), lesson)
            }else{
                Toast.makeText(requireContext(), "Bạn hãy hoàn thành bài trước", Toast.LENGTH_SHORT).show()
            }
        }
        views.rcv.adapter = categoryAdapter

        views.icUser.setOnClickListener{
            requireActivity().startActivityWithTransition(Intent(requireActivity(), AuthActivity::class.java))
        }
    }

    private fun handleStateViewModel(){
        mViewModel.liveData.apply {
            categories.observe(requireActivity()) {
                when(it.status){
                    Status.SUCCESS ->{
                        categoryAdapter.populateData(it.data)
                    }
                    else -> {}
                }
            }

        }
    }
}