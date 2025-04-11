package com.app.langking.feature.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.langking.core.AppBaseFragment
import com.app.langking.databinding.FragmentWishListBinding

class WishListFragment : AppBaseFragment<FragmentWishListBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWishListBinding {
        return FragmentWishListBinding.inflate(inflater, container, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return super.onCreateView(inflater, container, savedInstanceState)
    }
}