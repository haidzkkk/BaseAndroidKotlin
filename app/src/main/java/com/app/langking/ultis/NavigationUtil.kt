package com.app.langking.ultis

import android.app.Activity
import android.app.Activity.OVERRIDE_TRANSITION_OPEN
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.app.langking.R

fun Fragment.navigateFragmentWithSlide(fragmentId: Int, args: Bundle? = null) {
    val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left)
        .setPopExitAnim(R.anim.slide_out_right)
        .build()

    findNavController().navigate(fragmentId, args, navOptions)
}

fun Fragment.popFragmentWithSlide() {
    findNavController().popBackStack()
}

fun Activity.navigateFragmentWithSlide(viewId: Int,fragmentId: Int, args: Bundle? = null) {
    val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left)
        .setPopExitAnim(R.anim.slide_out_right)
        .build()

    ((this as AppCompatActivity).supportFragmentManager.findFragmentById(viewId) as NavHostFragment)
        .navController.navigate(fragmentId, args, navOptions)
}

fun Activity.popFragmentWithSlide(viewId: Int) {
    findNavController(viewId).popBackStack()
}

fun Activity.startActivityWithTransition(intent: Intent) {
    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
    startActivity(intent, options.toBundle())
}

fun Activity.finishActivityWithTransition() {
    finishAfterTransition()
}