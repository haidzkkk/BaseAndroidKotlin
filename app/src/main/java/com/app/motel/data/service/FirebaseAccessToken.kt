package com.app.motel.data.service

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.messaging.FirebaseMessaging
import com.history.vietnam.data.model.Resource
import com.history.vietnam.ultis.AppConstants
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

object FirebaseAccessToken {
    private const val FIREBASE_MESSAGE_SCOPE: String = "https://www.googleapis.com/auth/firebase.messaging"
    private const val FIREBASE_SERVICE_ACCOUNT: String = "firebase-service-account.json"

    @SuppressLint("CommitPrefEdits")
    fun getFireBaseAccessToken(context: Context){
        try {
            val stream = context.assets.open(FIREBASE_SERVICE_ACCOUNT)

            val googleCredentials = GoogleCredentials
                .fromStream(stream)
                .createScoped(listOf(FIREBASE_MESSAGE_SCOPE))

            googleCredentials.refresh()
            val accessToken = googleCredentials.accessToken.tokenValue

            context.getSharedPreferences(AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit().apply {
                putString(AppConstants.SHARED_PREFERENCES_FIREBASE_ACCESS_TOKEN, accessToken)
            }.apply()
            Log.e("AccessToken", "getAccessToken ok: $accessToken")
        } catch (e: Exception) {
            Log.e("AccessToken", "getAccessToken err: $e")
        }
    }

    suspend fun getTokenDevice(): Resource<String> = suspendCancellableCoroutine { cont ->
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    cont.resume(Resource.Error("Fetching FCM registration token failed ${task.exception}"))
                    Log.w("FCM", "Device token err: Fetching FCM registration token failed", task.exception)
                    return@addOnCompleteListener
                }

                val token = task.result
                cont.resume(Resource.Success(token))
                Log.d("FCM", "Device token ok: $token")
            }
            .addOnFailureListener { e ->
                Log.d("FCM", "Device token err: ${e.message ?: "Unknown error"}")
                cont.resume(Resource.Error(e.message ?: "Unknown error"))
            }

    }
}
