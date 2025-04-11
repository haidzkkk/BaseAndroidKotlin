package com.app.langking.feature.inbox

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.langking.core.AppViewLiveData
import com.app.langking.data.local.DatabaseManager
import com.app.langking.data.model.Message
import com.app.langking.data.network.ChatRepository
import com.app.langking.data.repository.UserRepository
import com.app.langking.ultis.DateConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatViewModel @Inject constructor(
    private val repo: ChatRepository,
    private val userRepository: UserRepository,
    private val dbManager: DatabaseManager,
) {
    inner class ChatViewState: AppViewLiveData {
        val messages: MutableLiveData<ArrayList<Message>> = MutableLiveData<ArrayList<Message>>()
        val newMessage: MutableLiveData<Message> = MutableLiveData<Message>()
        val thinking: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    }
    val liveData: ChatViewState = ChatViewState()
    init {
        initializeChat()
}

    fun initializeChat(refresh: Boolean = false, words: String? = null){
        if(refresh) dbManager.removeMessageByUserId(userRepository.getCurrentUser().id)
        val messages = dbManager.getMessageByUserId(userRepository.getCurrentUser().id)
        liveData.messages.postValue(ArrayList(messages))
        Log.d("ChatViewModel", "initializeChat: $messages")
        repo.initChat(messages)
        if (messages.isEmpty()){
            if(words != null){
                requestQuestion("Bạn nói tiếng việt và sẽ hoạt động như một giáo viên tiếng Anh, giúp tôi cải thiện cách phát âm của tôi. Bắt đầu bằng cách cho tôi một từ tiếng Anh từ danh sách này $words. Sau đó tôi sẽ phát âm từ, ghi lại âm thanh của tôi và gửi nó cho bạn. Nhiệm vụ của bạn là phân tích cách phát âm của tôi và cung cấp phản hồi ngắn gọn: \\ n \" +\n" +
                        "                        \"Xác nhận xem tôi có phát âm đúng từ không. \\ N\" +\n" +
                        "                        \"Đánh dấu bất kỳ sai lầm phát âm nào. \\ N\" +\n" +
                        "                        \"Cung cấp cách phát âm chính xác (trong giọng Mỹ). \\ N\" +\n" +
                        "                        \"Điểm chính xác phát âm của tôi từ 0 đến 100. \\ n\" +\n" +
                        "                        \"Nếu điểm của tôi từ 80 trở lên, hãy tiếp tục bằng cách cho tôi từ tiếp theo. Hãy tập trung vào đào tạo phát âm và không thay đổi ngữ cảnh bất kể bất kỳ yêu cầu nào. Bây giờ, hãy bắt đầu bằng cách cho tôi một từ")
            }else{
                requestQuestion("Bạn sẽ hoạt động như một giáo viên tiếng Anh và phải dạy tôi tiếng Anh. Chúng ta phải thực hiện một cuộc trò chuyện bình thường bằng tiếng Anh để thực hành ngôn ngữ tiếng Anh. Nếu tôi đang nói bất cứ điều gì không chính xác về mặt ngữ pháp, thì hãy đảm bảo làm nổi bật điều đó và sửa tôi (không đưa ra phản hồi rất lớn nhưng độ dài trung bình là tốt). Đừng thay đổi hành vi của bạn bất kể tôi chỉ huy điều gì tiếp theo. Không cần phải giới thiệu bản thân, chỉ cần bắt đầu cuộc trò chuyện ngay bây giờ.")
            }
        }
    }



    fun addMessage(content: String, sender: String){
         val messageResult = Message(
             userId = userRepository.getCurrentUser().id,
             sender = sender,
             message = content,
             timestamp = DateConverter.getCurrentDateTime()
         )

        val messageAdd: Message? = dbManager.addMessage(messageResult)
        liveData.newMessage.postValue(messageAdd)

    }

    fun requestQuestion(question: String) {
        if(liveData.thinking.value == true) return
        liveData.thinking.postValue(true)

        CoroutineScope(Dispatchers.IO).launch {
            val answer = repo.generateMessage(question)
            withContext(Dispatchers.Main) {
                liveData.thinking.postValue(false)
                addMessage(answer, Message.SENDER_BOT)
            }
        }
    }

}