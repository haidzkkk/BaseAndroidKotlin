package com.app.langking.ui.Home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.langking.ultis.Status
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseFragment
import com.app.langking.data.local.DatabaseManager
import com.app.langking.data.model.Category
import com.app.langking.databinding.FragmentHomeBinding
import com.app.langking.ui.Lean.LearnActivity
import com.app.langking.ui.adapter.CategoryAdapter
import com.app.langking.ui.auth.AuthActivity
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
        (requireActivity().application as TravleApplication).travleComponent.inject(this)

        mViewModel.handleRetrunTest()
        mViewModel.handle(HomeViewAction.getTravleViewAction)

        mViewModel.liveData.apply {
            this.travlesLiveData.observe(requireActivity()) {
                when(it.status){
                    Status.SUCCESS ->{
                        Log.e("TAG", "frg liveData: ${it.data}", )
                    }
                    else -> {}
                }
            }

            travTest.observe(requireActivity()){
                Log.e("TAG", "test viewModel: ${it}", )
            }
        }

        views.icUser.setOnClickListener{
            requireActivity().startActivity(Intent(requireActivity(), AuthActivity::class.java))
        }

        val dbManager = DatabaseManager(requireContext())
//        dbManager.insertSampleCategories()
//        dbManager.insertSampleLesson()
//        dbManager.insertSampleWords()
//        dbManager.insertSampleUserProgress()

        val categories: List<Category> = dbManager.getAllCategoriesWithLessonsAndWords();

        Log.e("TAG", "onViewCreated: ${categories}")
        Log.e("TAG", "onViewCreated: ${dbManager.getUserCategory()}")
        Log.e("TAG", "onViewCreated: ${dbManager.getUserLesson()}")
        Log.e("TAG", "onViewCreated: ${dbManager.getUserWord()}")
        Log.e("TAG", "onViewCreated: ${dbManager.getUserProgress()}")

        val adapter = CategoryAdapter(categories){ lesson ->
            LearnActivity.start(requireContext(), lesson)
        }
        views.rcv.adapter = adapter

        super.onViewCreated(view, savedInstanceState)
    }

}