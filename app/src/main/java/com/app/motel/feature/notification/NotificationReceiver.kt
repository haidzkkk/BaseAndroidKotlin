package com.app.motel.feature.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationReceiver : BroadcastReceiver() {
    companion object{
        var actionNotify = "com.fpoly.smartlunch.NEW_DATA_AVAILABLE"
        var actionCall = "com.fpoly.smartlunch.CALL_VIDEO"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val action: Int? = intent?.getIntExtra("notification_action_broadcast", 0)

//        when(action){
//            1 ->{
//                val broadcastIntent = Intent(actionNotify).apply {
//                    putExtra("notification_action_broadcast", action)
//                }
//                context?.sendBroadcast(broadcastIntent)
//            }
//            2 ->{
//                val broadcastIntent = Intent(actionCall)
//
//                var type = intent.getStringExtra("type")
//                var idUrl = intent.getStringExtra("idUrl")
//                broadcastIntent.apply {
//                    putExtras(Bundle().apply {
//                        putString("type", type)
//                        putString("idUrl", idUrl)
//                    })
//                }
//                context?.sendBroadcast(broadcastIntent)
//            }
//        }
    }
}
