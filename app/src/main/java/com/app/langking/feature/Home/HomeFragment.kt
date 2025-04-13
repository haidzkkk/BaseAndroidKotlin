package com.app.langking.feature.Home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.app.langking.R
import com.app.langking.ultis.Status
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseDialog
import com.app.langking.core.AppBaseFragment
import com.app.langking.data.local.AccountDAO
import com.app.langking.data.local.LessonDao
import com.app.langking.data.local.UserProgressDao
import com.app.langking.data.model.Account
import com.app.langking.data.model.Category
import com.app.langking.data.model.Lesson
import com.app.langking.data.model.Question
import com.app.langking.data.repository.UserRepository
import com.app.langking.databinding.DialogLoadingBinding
import com.app.langking.databinding.FragmentExerciseBinding
import com.app.langking.databinding.FragmentHomeBinding
import com.app.langking.feature.Learn.LearnActivity
import com.app.langking.feature.adapter.CategoryAdapter
import com.app.langking.feature.notification.AppNotificationManager
import com.app.langking.ultis.AppConstants
import com.app.langking.ultis.navigateFragmentWithSlide
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.LinkedList
import java.util.Queue
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
            navigateFragmentWithSlide(R.id.nav_profile)
        }

        views.icAvatar.setOnClickListener{
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