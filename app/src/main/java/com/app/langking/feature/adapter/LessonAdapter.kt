package com.app.langking.feature.adapter

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.app.langking.R
import com.app.langking.data.model.Lesson
import com.app.langking.data.model.UserProgress
import com.app.langking.databinding.ItemLessonBinding
import com.app.langking.ultis.DateConverter

class LessonAdapter(private val lessons: List<Lesson>, private val onItemClick: (Lesson) -> Unit) :
    RecyclerView.Adapter<LessonAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(lesson: Lesson) {
            with(binding as ItemLessonBinding){
                handleMedalScore(binding, lesson.userProgress)
                handleProcess(binding, lesson.userProgress)
                this.tvTitle.text = lesson.name
                this.root.setOnClickListener{
                    onItemClick(lesson)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lessons[position])
    }

    override fun getItemCount() = lessons.size

    @SuppressLint("SetTextI18n")
    private fun handleProcess(binding: ItemLessonBinding, userProgress: UserProgress?){
        binding.tvProgress.text = "Bài tập: ${userProgress?.progressString ?: "Bạn hãy hoàn thành bài trước"}"
        binding.tvTime.text = if(userProgress != null && userProgress.score > 0) "Bạn đã luyện tập ${DateConverter.getDaysDifference(DateConverter.getCurrentDateTime(), userProgress.dateTest)} ngày trước" else "Bạn chưa luyện tập"
        binding.tvProgress.setTextColor(userProgress?.progressColor(binding.root.context) ?: ContextCompat.getColor(binding.root.context, R.color.grey))
    }
    private fun handleMedalScore(binding: ItemLessonBinding, userProgress: UserProgress?){
        binding.lyMedal.apply {
            if((userProgress?.score ?: 0) >= 50){
                binding.icon1.clearColorFilter()
            }else{
                binding.icon1.setColorFilter(ContextCompat.getColor(binding.root.context, R.color.grey), PorterDuff.Mode.SRC_IN)
            }
        }
        binding.lyMedal.apply {
            if((userProgress?.score ?: 0) >= 75){
                binding.icon2.clearColorFilter()
            }else{
                binding.icon2.setColorFilter(ContextCompat.getColor(binding.root.context, R.color.grey), PorterDuff.Mode.SRC_IN)
            }
        }
        binding.lyMedal.apply {
            if((userProgress?.score ?: 0) == 100){
                binding.icon3.clearColorFilter()
            }else{
                binding.icon3.setColorFilter(ContextCompat.getColor(binding.root.context, R.color.grey), PorterDuff.Mode.SRC_IN)
            }
        }

        if(userProgress != null){
            binding.icCompleted.visibility = View.VISIBLE
            binding.icCompleted.setImageResource(
                if(!userProgress.isStarted) R.drawable.icons8_new
                else if(userProgress.isComplete) R.drawable.icons8_true
                else R.drawable.icons8_false
            )
        }else{
            binding.icCompleted.visibility = View.INVISIBLE
        }
    }
}
