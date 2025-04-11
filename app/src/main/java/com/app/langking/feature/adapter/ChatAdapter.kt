package com.app.langking.feature.adapter

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.app.langking.data.model.Message
import com.app.langking.databinding.ItemChatBotBinding
import com.app.langking.databinding.ItemChatUserBinding
import com.app.langking.ultis.DateConverter


class ChatAdapter(
    private val recyclerView: RecyclerView,
    private val onItemClick: (Message) -> Unit,
) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    private var messages: ArrayList<Message> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun populateData(category: ArrayList<Message>?) {
        if(category == null) return;
        this.messages = category
        notifyDataSetChanged()
    }

    var isWriting: Boolean = false
    fun addMessage(messageAdd: Message?) {
        if(messageAdd == null) return;
        messages.add(messageAdd)
        isWriting = messageAdd.isBot
//        notifyDataSetChanged()
        notifyItemInserted(messages.size - 1)
    }

    fun listenGeneration(thinking: Boolean) {
        if(thinking){
            if (messages.none { it.id == -999 }) {
                messages.add(Message(-999, -1, Message.SENDER_BOT, "Thinking...", DateConverter.getCurrentDateTime()))
                notifyItemInserted(messages.size - 1)
            }
        }else{
            val index = messages.indexOfFirst { it.id == -999 }
            if (index != -1) {
                messages.removeAt(index)
                notifyItemRemoved(index)
            }
        }
    }

    private val userType: Int = 0
    private val botType: Int = 1
    override fun getItemViewType(position: Int): Int {
        return if(messages[position].isUser) userType
            else if(messages[position].isBot) botType
            else throw IllegalStateException("Unknown sender: ${messages[position].sender}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if(viewType == userType) ViewHolder(ItemChatUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        else ViewHolder(ItemChatBotBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = messages.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(messages[position].isUser){
            holder.bindUser(messages[position], position)
        }else if(messages[position].isBot){

            holder.bindBot(messages[position], position)
        }
    }


    inner class ViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bindUser(message: Message, position: Int) {
            with(binding as ItemChatUserBinding){
                handleShowTime(binding.tvTime, position)
                tvMessage.text = message.message
            }

        }

        fun bindBot(message: Message, position: Int) {
            with(binding as ItemChatBotBinding){
                handleShowTime(binding.tvTime, position)
                if(position == messages.size - 1 && isWriting){
                    showTypingEffect(tvMessage, message.message, recyclerView, position)
                }else{
                    tvMessage.text = message.message
                }
            }
        }

        private fun handleShowTime(tvTime: TextView, position: Int) {
            if(position > 0){
                tvTime.isVisible = !DateConverter.areDatesClose(listOf(messages[position - 1].timestamp!!, messages[position].timestamp!!))
            }
            tvTime.text = messages[position].timestamp
        }

        private fun showTypingEffect(textView: TextView, text: String, recyclerView: RecyclerView, position: Int) {
            isWriting = false
            val animator = ValueAnimator.ofInt(0, text.length)
            animator.duration = text.length * 20L
            animator.addUpdateListener { valueAnimator ->
                val length = valueAnimator.animatedValue as Int
                textView.text = text.substring(0, length)
                recyclerView.scrollToPosition(position)
            }
            animator.start()
        }

    }
}
