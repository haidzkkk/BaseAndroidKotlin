package com.app.motel.feature.tenant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.motel.AppApplication
import com.app.motel.R
import com.app.motel.core.AppBaseFragment
import com.app.motel.databinding.FragmentTenantFormBinding
import com.app.motel.databinding.FragmentTenantListBinding
import com.app.motel.feature.tenant.viewmodel.TenantViewModel
import javax.inject.Inject

class TenantListFragment : AppBaseFragment<FragmentTenantListBinding>() {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTenantListBinding {
        return FragmentTenantListBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : TenantViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(TenantViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveData
    }
}