package com.app.langking.feature.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.app.langking.R
import com.app.langking.data.model.Category
import com.app.langking.data.model.Lesson
import com.app.langking.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val onItemClick: (Lesson) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var category: List<Category> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun populateData(category: List<Category>?) {
        if(category == null) return;
        this.category = category
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = category.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(category[position], position)
    }

    inner class ViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        private var isExpanded: Boolean = false

        fun bind(category: Category, position: Int) {
            if(position == 0) isExpanded = true
            with(binding as ItemCategoryBinding){
                this.tvTitle.text = category.name
                val adapter = LessonAdapter(category.lessons?.toList() ?: arrayListOf(), onItemClick)
                rcvLesson.adapter = adapter

                val process = category.lessons
                    ?.map { it.userProgress?.score ?: 0 }
                    ?.average()?.toFloat() ?: 0f

                processIndicator.setValue(process)

                setupExpand()
                lyTitle.setOnClickListener {
                    isExpanded = !isExpanded
                    setupExpand()
                }
            }
        }

        private fun setupExpand() {
            with(binding as ItemCategoryBinding){
                rcvLesson.visibility = if(isExpanded) ViewGroup.VISIBLE else ViewGroup.GONE
                ivExpand.setImageResource(if(isExpanded) R.drawable.icons8_minus else R.drawable.icons8_plus)
            }
        }
    }
}
