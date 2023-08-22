package com.example.travle.ui.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.travle.R
import com.example.travle.core.TravleBaseFragment
import com.example.travle.databinding.FragmentWishListBinding

class WishListFragment : TravleBaseFragment<FragmentWishListBinding>() {
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