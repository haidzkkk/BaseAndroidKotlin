package com.app.motel.feature.page

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.app.motel.feature.profile.UserController
import com.app.motel.feature.setting.SettingController
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.data.model.Comment
import com.history.vietnam.databinding.ItemCommentBinding
import com.history.vietnam.ultis.DateConverter

class CommentAdapter(
    private val listener: AppBaseAdapter.AppListener<Comment>,
    private val userController: UserController,
    private val settingController: SettingController? = null,
): AppBaseAdapter<Comment, ItemCommentBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, parent: ViewGroup): ItemCommentBinding {
         return ItemCommentBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemCommentBinding, item: Comment, position: Int) {
        settingController?.state?.backgroundColor?.value?.let {
            if(it == R.color.background2){
                binding.lyComment.backgroundTintList = ContextCompat.getColorStateList(binding.root.context, R.color.background1)
                binding.lyAvatar.backgroundTintList = ContextCompat.getColorStateList(binding.root.context, R.color.background1)
            }else{
                binding.lyComment.backgroundTintList = ContextCompat.getColorStateList(binding.root.context, R.color.background2)
                binding.lyAvatar.backgroundTintList = ContextCompat.getColorStateList(binding.root.context, R.color.background2)
            }
        }
        settingController?.state?.getTextColor(binding.root.context)?.apply {
            binding.btnTime.setTextColor(this)
            binding.btnReply.setTextColor(this)
            binding.tvCountLike.setTextColor(this)
        }
        settingController?.state?.getTextSize(binding.root.context)?.apply {
             binding.tvContent.textSize = this
             binding.btnTime.textSize = this
             binding.btnReply.textSize = this
             binding.btnLike.textSize = this
        }
        settingController?.state?.getTextFont(binding.root.context)?.apply {
            val boldTypeface = Typeface.create(this, Typeface.BOLD)
              binding.tvName.typeface = boldTypeface
              binding.tvContent.typeface = this
              binding.btnTime.typeface = this
              binding.btnReply.typeface = this
              binding.tvCountLike.typeface = this
        }

        binding.tvName.text = item.user?.getUserName
        binding.tvContent.text = item.content
        binding.btnTime.text = if(item.isUploading == true) "Đang đăng" else DateConverter.getTimeAgo(item.time)

        binding.imgLike.isVisible = item.likes?.isNotEmpty() == true
        binding.tvCountLike.isVisible = (item.likes?.size ?: 0) > 1
        binding.tvCountLike.text = item.likes?.size.toString()

        val adapter = CommentAdapter(listener, userController, settingController)
        binding.rcv.adapter = adapter
        adapter.updateData(item.comments?.values?.toList())

        binding.btnLike.setTextColor(if(item.likes?.containsKey(userController.state.getCurrentUserId) == true){
            ContextCompat.getColor(binding.root.context, R.color.blue )
        }else{
            settingController?.state?.getTextColor(binding.root.context) ?: ContextCompat.getColor(binding.root.context, R.color.textColor1 )
        })


        binding.btnLike.setOnClickListener {
            listener.onClickItem(item, AppBaseAdapter.ItemAction.ACTION1)
        }

        binding.btnReply.setOnClickListener {
            listener.onClickItem(item, AppBaseAdapter.ItemAction.ACTION2)
        }
    }
}