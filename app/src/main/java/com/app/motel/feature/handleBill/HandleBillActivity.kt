package com.app.motel.feature.handleBill

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.app.motel.AppApplication
import com.app.motel.R
import com.app.motel.common.ultis.popFragmentWithSlide
import com.app.motel.core.AppBaseActivity
import com.app.motel.data.entity.HoaDonEntity
import com.app.motel.data.model.Contract
import com.app.motel.databinding.ActivityHandleBillBinding
import com.app.motel.databinding.ActivityTenantBinding
import com.app.motel.feature.handleBill.viewmodel.HandleBillViewModel
import com.app.motel.feature.handleContract.HandleContractActivity.Companion.CONTRACT_STATE_KEY
import javax.inject.Inject

class HandleBillActivity : AppBaseActivity<ActivityHandleBillBinding>() {

    companion object{
        const val BILL_STATE_KEY = "BILL_STATE_KEY"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : HandleBillViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HandleBillViewModel::class.java)
    }

    override fun getBinding(): ActivityHandleBillBinding {
        return ActivityHandleBillBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as AppApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        init()
        setupToolBar()

        viewModel.liveData
    }
    private fun init(){
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackWithAnimation()
            }
        })

        val status: Int = intent.getIntExtra(BILL_STATE_KEY, HoaDonEntity.STATUS_PAID)
        viewModel.liveData.filterState.postValue(status)
    }

    private fun setupToolBar(){
        setSupportActionBar(views.toolbar)
        (views.toolbar.context as AppCompatActivity).setSupportActionBar(views.toolbar)
        views.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_ios_new_24)
        views.toolbar.navigationIcon?.setTint(ContextCompat.getColor(this, R.color.white))
        views.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        views.toolbar.setSubtitleTextColor(ContextCompat.getColor(this, R.color.white))
        views.toolbar.overflowIcon?.setTint(ContextCompat.getColor(this, R.color.white))
        views.toolbar.setTitleTextAppearance(this, R.style.ToolbarTitleStyle)
        views.toolbar.isTitleCentered = true
        supportActionBar?.title = "Danh sách hóa đơn"
        views.toolbar.setNavigationOnClickListener {
            handleBackWithAnimation()
        }
    }

    private fun handleBackWithAnimation() {
        val navController = findNavController(R.id.fragment_view)

        if (navController.currentDestination?.id == R.id.handleBillListragment) {
            finish()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        } else {
            popFragmentWithSlide(R.id.fragment_view)
        }
    }
}