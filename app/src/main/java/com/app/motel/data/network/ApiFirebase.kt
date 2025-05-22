package com.app.motel.data.network

import com.app.motel.data.model.FirebaseNotificationBody
import com.history.vietnam.ultis.AppConstants
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiFirebase {
    @POST("v1/projects/${AppConstants.FIREBASE_PROJECT_ID}/messages:send")
    suspend fun postNotification(@Body body: FirebaseNotificationBody): Response<ResponseBody>
}