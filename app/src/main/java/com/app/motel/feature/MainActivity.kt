package com.history.vietnam.feature

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.history.vietnam.R
import com.history.vietnam.AppApplication
import com.history.vietnam.core.AppBaseActivity
import com.history.vietnam.core.AppBaseBottomSheet
import com.history.vietnam.core.AppBaseDialog
import com.history.vietnam.databinding.ActivityMainBinding
import com.history.vietnam.feature.Home.HomeViewEvent
import com.history.vietnam.feature.Home.HomeViewModel
import com.history.vietnam.ultis.navigateFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import java.util.LinkedList
import java.util.Queue
import javax.inject.Inject

class MainActivity : AppBaseActivity<ActivityMainBinding>() {

    override fun getBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mViewModel : HomeViewModel by lazy{
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as AppApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(views.root)

        mViewModel.observeViewEvents {
            when(it){
                is HomeViewEvent.ReturnTestViewEvent ->  Log.e("TAG", "Test View event")
                else -> {}
            }
        }

        setUpBottomNav()
    }

    private fun setUpBottomNav() {
        val navController = this.findNavController(R.id.fragment_view)
        views.navBottom.setupWithNavController(navController)

        views.navBottom.menu.clear()
        views.navBottom.inflateMenu(R.menu.menu_bottom)
        this.findNavController(R.id.fragment_view).currentDestination?.id?.apply {
            views.navBottom.selectedItemId = this
        }
//        this.findNavController(R.id.fragment_view).addOnDestinationChangedListener { controller, destination, arguments ->
//            views.navBottom.isVisible =
//        }
    }

    private fun showBottomSheet(isCorrect: Boolean) {
//        val dialog = BottomSheetDialog(baseContext)
//        val binding = ItemLearnBinding.inflate(layoutInflater)
//
//        dialog.setContentView(binding.root)
//        dialog.show()
//
//        dialog.setOnDismissListener {
//
//        }
    }

    private fun showDialogNewbie(){
//        val dialog = AppBaseDialog.Builder(baseContext, FragmentExerciseBinding.inflate(layoutInflater))
//            .build()
//        dialog.show()
//        dialog.setCancelable(false)
//
//        dialog.setOnDismissListener {
//
//        }
    }
}