package com.app.langking.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.app.langking.R
import com.app.langking.data.model.Category
import com.app.langking.data.model.Lesson
import com.app.langking.databinding.ItemCategoryBinding
import kotlin.random.Random

class CategoryAdapter(
    private val category: List<Category>,
    private val onItemClick: (Lesson) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = category.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(category[position], position)
    }

    inner class ViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        private var isExpanded: Boolean = false
        private var process = Random.nextInt(0, 100).toFloat();

        fun bind(category: Category, position: Int) {
            if(position == 0) isExpanded = true
            with(binding as ItemCategoryBinding){
                this.tvTitle.text = category.name
                val adapter = LessonAdapter(category.lessons, onItemClick)
                rcvLesson.adapter = adapter
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
