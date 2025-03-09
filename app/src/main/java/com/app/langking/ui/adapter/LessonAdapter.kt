package com.app.langking.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.app.langking.data.model.Lesson
import com.app.langking.databinding.ItemLessonBinding

class LessonAdapter(private val lessons: List<Lesson>, private val onItemClick: (Lesson) -> Unit) :
    RecyclerView.Adapter<LessonAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(lesson: Lesson) {
            with(binding as ItemLessonBinding){
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
}
