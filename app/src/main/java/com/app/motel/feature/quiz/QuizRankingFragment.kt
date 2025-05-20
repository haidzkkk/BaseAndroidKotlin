package com.app.motel.feature.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.app.motel.data.model.Ranking
import com.app.motel.feature.quiz.viewmodel.QuizViewModel
import com.app.motel.ui.custom.CustomTabBar
import com.app.motel.ultis.formatTopPosition
import com.google.android.material.divider.MaterialDivider
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentQuizRankingBinding
import com.history.vietnam.databinding.ItemRankingBinding
import com.history.vietnam.ultis.popFragmentWithSlide
import javax.inject.Inject

class QuizRankingFragment : AppBaseFragment<FragmentQuizRankingBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentQuizRankingBinding {
        return FragmentQuizRankingBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : QuizViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(QuizViewModel::class.java)
    }

    val adapter = RankingAdapter(object : AppBaseAdapter.AppListener<Ranking>(){
        override fun onClickItem(item: Ranking, action: AppBaseAdapter.ItemAction) {

        }
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        init()
        listenStateViewModel()
    }

    fun init(){
        viewModel.getRankQuiz()
        views.rcv.adapter = adapter

        currentRankingBinding = ItemRankingBinding.inflate(LayoutInflater.from(requireContext()), null, false)
        dividerBinding = MaterialDivider(requireContext())


        views.tabBar.post {
            views.tabBar.setTabSelected(if(viewModel.liveData.isTodayFilterRanking.value == true) 0 else 1)
        }
        views.tabBar.setOnTabSelectedListener(object: CustomTabBar.OnTabSelectedListener{
            override fun onTabSelected(position: Int) {
                val isToday = position == 0
                viewModel.liveData.isTodayFilterRanking.postValue(isToday)
            }
        })

        views.btnBack.setOnClickListener {
            popFragmentWithSlide()
        }
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    fun listenStateViewModel(){
        viewModel.liveData.currentQuiz.observe(viewLifecycleOwner){
            updateRanking()
            handleShowCurrentRanking()
        }
        viewModel.liveData.isTodayFilterRanking.observe(viewLifecycleOwner){
            updateRanking()
            handleShowCurrentRanking()
        }
        viewModel.liveData.quizCompleted.observe(viewLifecycleOwner){
            handleShowCurrentRanking()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateRanking() {
        val rankings = viewModel.liveData.filterListRankings
        val topThree = rankings.subList(0, minOf(3, rankings.size))
        val topTwenty = if (rankings.size > 3) {
            rankings.subList(3, minOf(20, rankings.size))
        } else {
            emptyList()
        }

        val nameTopViews = listOf(views.tvName1, views.tvName2, views.tvName3)
        val scoreTopViews = listOf(views.tvScore1, views.tvScore2, views.tvScore3)
        topThree.forEachIndexed { index, ranking ->
            nameTopViews[index].text = ranking.user?.name ?: "Ẩn danh ${(index + 1).formatTopPosition()}"
            scoreTopViews[index].text = "${ranking.score ?: 0}pt"
        }

        adapter.updateData(topTwenty)
    }

    private lateinit var currentRankingBinding : ItemRankingBinding
    private lateinit var dividerBinding : MaterialDivider
    @SuppressLint("SetTextI18n")
    private fun handleShowCurrentRanking(){
        views.lyBottom.removeView(dividerBinding)
        views.lyBottom.removeView(currentRankingBinding.root)

        val currentRanking = viewModel.liveData.quizCompleted.value?.data?.ranking
        val rankings = ArrayList(viewModel.liveData.filterListRankings)

        if(currentRanking != null) {
            rankings.add(currentRanking)
            val position = viewModel.liveData.filterListRankings.indexOfFirst {
                it.id == currentRanking.id
            }
            rankings.remove(currentRanking)

            currentRankingBinding.tvPosition.text = if(position != -1) (position + 1).formatTopPosition() else "--"
            currentRankingBinding.tvName.text = "Bạn"
            currentRankingBinding.tvScore.text = "${currentRanking.score ?: 0}pt"
            ContextCompat.getColor(requireContext(),
                when (position) {
                    0 -> R.color.gold
                    1 -> R.color.silver
                    2 -> R.color.bronze
                    else -> R.color.textColor1
                }
            ).apply {
                currentRankingBinding.tvPosition.setTextColor(this)
                currentRankingBinding.tvName.setTextColor(this)
            }
            views.lyBottom.addView(dividerBinding)
            views.lyBottom.addView(currentRankingBinding.root)
        }
    }

}