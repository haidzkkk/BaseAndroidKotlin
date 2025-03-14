package com.app.langking.ultis

import android.app.Activity
import android.app.Activity.OVERRIDE_TRANSITION_OPEN
import android.content.Intent
import android.os.Build
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.app.langking.R

fun Fragment.navigateFragmentWithSlide(fragmentId: Int) {
    val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left)
        .setPopExitAnim(R.anim.slide_out_right)
        .build()

    findNavController().navigate(fragmentId, null, navOptions)
}

fun Fragment.popFragmentWithSlide() {
    findNavController().popBackStack()
}

fun Activity.startActivityWithTransition(intent: Intent) {
    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
    startActivity(intent, options.toBundle())
}

fun Activity.finishActivityWithTransition() {
    finishAfterTransition()
}