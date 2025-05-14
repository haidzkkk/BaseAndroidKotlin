package com.app.motel.feature.territory

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.app.motel.feature.auth.AuthActivity
import com.app.motel.feature.page.CommentAdapter
import com.app.motel.feature.territory.viewmodel.TerritoryViewModel
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseAdapter
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.data.model.Comment
import com.history.vietnam.databinding.FragmentTerritoryCommentBinding
import com.history.vietnam.ui.showDialogConfirm
import javax.inject.Inject

class TerritoryCommentFragment : AppBaseFragment<FragmentTerritoryCommentBinding>() {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTerritoryCommentBinding {
        return FragmentTerritoryCommentBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : TerritoryViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(TerritoryViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)


        init()
        listenStateViewModel()
    }

    var adapter: CommentAdapter? = null
    private fun init() {
        adapter = CommentAdapter(
            listener = object : AppBaseAdapter.AppListener<Comment>() {
                override fun onClickItem(item: Comment, action: AppBaseAdapter.ItemAction) {
                    when(action){
                        AppBaseAdapter.ItemAction.ACTION1 -> {
                            viewModel.likeComment(item)
                        }
                        AppBaseAdapter.ItemAction.ACTION2 -> {
                            viewModel.replyComment(item)
                        }
                        else -> {

                        }
                    }
                }
            },
            userController = viewModel.userController
        )
        views.rcv.adapter = adapter

        views.txtComment.addTextChangedListener {
            val enable = views.txtComment.text.toString().isNotEmpty()
            val color = if(enable) ContextCompat.getColorStateList(requireContext(), R.color.primary)
            else ContextCompat.getColorStateList(requireContext(), R.color.icon2)
            views.tilComment.setEndIconTintList(color)
        }

        views.tilComment.setEndIconOnClickListener {
            views.txtComment.text.toString().apply {
                if(this.isNotEmpty()){
                    val success = viewModel.sendComment(this)
                    if(success){
                        views.txtComment.setText("")
                    }
                }
            }
        }

        views.lyReply.setOnClickListener {
            viewModel.replyComment(null)
        }

        views.txtComment.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_SEND ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {

                views.txtComment.text.toString().apply {
                    if(this.isNotEmpty()){
                        val success = viewModel.sendComment(this)
                        if(success){
                            views.txtComment.setText("")
                        }
                    }
                }
                true
            } else {
                false
            }
        }
    }
    private fun listenStateViewModel() {
        viewModel.liveData.comments.observe(viewLifecycleOwner){
            adapter?.updateData(it.data)
            views.tvEmpty.isVisible = it.data.isNullOrEmpty()
        }
        viewModel.liveData.currentCommentReply.observe(viewLifecycleOwner){
            views.tvUserReply.text = it?.user?.getUserName
            views.lyReply.isVisible = it != null
            views.txtComment.setText(it?.user?.getUserName ?: "")
        }
        viewModel.userController.state.loginUser.observe(viewLifecycleOwner){
            if(it && viewModel.userController.state.getCurrentUser == null){
                requireContext().showDialogConfirm(
                    title = "Opps!",
                    content = "Hãy đăng nhập để thực hiện chức năng này?",
                    buttonConfirm = "Đăng nhập",
                    buttonCancel = "Đóng",
                    confirm = {
                        requireActivity().startActivity(Intent(requireActivity(), AuthActivity::class.java))
                    }
                )
            }
            viewModel.userController.state.loginUser.postValue(false)
        }
    }
}