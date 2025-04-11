package com.app.langking.data.network

import android.util.Log
import com.app.langking.data.model.ErrorResponse
import com.app.langking.data.model.Message
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.ServerException
import com.google.ai.client.generativeai.type.content
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val generativeModel: GenerativeModel
) {
    private lateinit var chatSession: Chat

    fun initChat(messages: List<Message>){
        val contentHistories = messages.map { message ->
            Content.Builder()
                .text(message.message)
                .apply {
                    role = message.sender
                }
                .build()
        }
        chatSession = generativeModel.startChat(contentHistories)
    }

    suspend fun generateMessage(question: String): String {
        return withContext(Dispatchers.IO) {
            try {
                val response = chatSession.sendMessage(question)
                if (response.text != null) {
                    response.text ?: ""
                } else {
                    "Null response"
                }
            } catch (e: ServerException) {
                Log.e("error", "ServerException ${e.message}")
                extractErrorMessage(e.message?:"")
            } catch (e: Exception) {
                Log.e("error", "Exception ${e.message}")
                "Unexpected Error occurred"
            }
        }
    }

    suspend fun generateResponseFromAudio(file: File) {
        return withContext(Dispatchers.IO) {
            val bytes = readAudioFromAssets(file)
            val content = content {
                bytes?.let { blob("audio/mp3", it) }
                text("Understand the audio and respond accordingly.")
            }
            try {
                val response = chatSession.sendMessage(content)
                if (response.text != null) {
                    response.text ?: ""
                } else {
                    "Null response"
                }
            } catch (e: ServerException) {
                Log.e("TAG", "getResponse: $e")
                extractErrorMessage(e.message?:"")
            } catch (e: Exception) {
                Log.e("TAG", "getResponse: $e")
                "Unexpected Error occurred"
            }
        }
    }

    private fun readAudioFromAssets(file: File): ByteArray? {
        return try {
            val inputStream = file.inputStream()
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            inputStream.close()
            buffer
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun extractErrorMessage(errorString: String): String {
        val startIndex = errorString.indexOf("{")
        val endIndex = errorString.lastIndexOf("}")

        if (startIndex != -1 && endIndex != -1) {
            val jsonPart = errorString.substring(startIndex, endIndex + 1)
            Log.e("error jsonPart", jsonPart)
            val gson = Gson()
            val errorResponse = gson.fromJson(jsonPart, ErrorResponse::class.java)

            return errorResponse.error?.message ?: "Unknown error occurred"
        } else {
            return "Unknown error occurred"
        }
    }


}
