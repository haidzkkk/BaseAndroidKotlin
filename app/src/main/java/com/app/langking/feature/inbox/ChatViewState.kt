package com.app.langking.feature.inbox

import androidx.lifecycle.MutableLiveData
import com.app.langking.core.AppViewLiveData
import com.app.langking.data.model.Message

class ChatViewState: AppViewLiveData {
    val messages: MutableLiveData<ArrayList<Message>> = MutableLiveData<ArrayList<Message>>()
    val newMessage: MutableLiveData<Message> = MutableLiveData<Message>()
}