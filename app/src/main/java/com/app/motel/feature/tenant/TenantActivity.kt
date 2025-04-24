package com.app.motel.feature.tenant

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.app.motel.AppApplication
import com.app.motel.R
import com.app.motel.core.AppBaseActivity
import com.app.motel.databinding.ActivityTenantBinding
import com.app.motel.feature.room.viewmodel.RoomViewModel
import com.app.motel.feature.tenant.viewmodel.TenantViewModel
import javax.inject.Inject

class TenantActivity : AppBaseActivity<ActivityTenantBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : TenantViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(TenantViewModel::class.java)
    }

    override fun getBinding(): ActivityTenantBinding {
        return ActivityTenantBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as AppApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        viewModel.liveData
    }
}