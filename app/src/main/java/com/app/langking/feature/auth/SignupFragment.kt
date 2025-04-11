package com.app.langking.feature.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.app.langking.R
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseFragment
import com.app.langking.databinding.FragmentSignupBinding
import com.app.langking.feature.adapter.ViewPagerAdapter
import javax.inject.Inject


class SignupFragment : AppBaseFragment<FragmentSignupBinding>() {

    val fragments = arrayListOf(
        SignupFirstFragment(),
        SignupSecondFragment(),
        SignupThirdFragment(),
    )

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mViewModel : AuthViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[AuthViewModel::class.java]
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignupBinding {
        return FragmentSignupBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (requireActivity().application as TravleApplication).travleComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapter(
            fragments,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        views.viewPager.adapter = adapter
        views.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                views.tvProcess.text = "${position + 1} / ${fragments.size}"
                views.processIndicator.progress = ((position + 1).toDouble() / fragments.size.toDouble() * 100.0).toInt()
            }
        })
        views.icBack.setOnClickListener{
            val currentPageIndex: Int = mViewModel.liveData.signupPageIndex.value ?: 0
            if(currentPageIndex > 0 && currentPageIndex < fragments.size){
                mViewModel.handle(AuthViewAction.SignupPreviousPageViewAction(currentPageIndex - 1))
            }else{
                findNavController().navigate(R.id.loginFragment)
            }
        }

        mViewModel.liveData.signupPageIndex.observe(viewLifecycleOwner){ position ->
            views.viewPager.currentItem = position
            views.tvProcess.text = "${position + 1} / ${fragments.size}"
            views.processIndicator.progress = ((position + 1).toDouble() / fragments.size.toDouble() * 100.0).toInt()
        }
    }

}