package com.app.langking.ui.Lean

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.langking.R
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseFragment
import com.app.langking.databinding.FragmentOverviewBinding
import com.app.langking.ui.Lean.viewmodel.LearnViewModel
import com.app.langking.ui.adapter.TopAdapter
import com.bumptech.glide.Glide
import javax.inject.Inject


class OverviewFragment @Inject constructor() : AppBaseFragment<FragmentOverviewBinding>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : LearnViewModel by lazy{
        ViewModelProvider(requireActivity(), viewModelFactory)[LearnViewModel::class.java]
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOverviewBinding {
        return  FragmentOverviewBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as TravleApplication).travleComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        views.lvBack.setOnClickListener{
            requireActivity().finish()
        }
        val topAdapter = TopAdapter()
        views.rcvTop.adapter = topAdapter
        views.rcvTop.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        Glide.with(this).load(R.raw.icons8_list).into(views.iconList)
        handleListenData()
    }

    private fun handleListenData(){

        Log.e("TAG", "frag viewmodel: ${viewModel.hashCode()}", )
        viewModel.liveData.currentLesson.observe(viewLifecycleOwner){
            Log.e("TAG", "initViewmodel: ${it}", )
            views.tvLessonName.text = it?.name
        }
    }
}