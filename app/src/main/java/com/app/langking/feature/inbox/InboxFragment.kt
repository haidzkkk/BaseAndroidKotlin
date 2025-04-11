package com.app.langking.feature.inbox

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.langking.R
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseFragment
import com.app.langking.data.model.Message
import com.app.langking.databinding.FragmentInboxBinding
import com.app.langking.feature.adapter.ChatAdapter
import com.app.langking.ultis.popFragmentWithSlide
import java.util.Locale
import javax.inject.Inject


class InboxFragment : AppBaseFragment<FragmentInboxBinding>() {
    companion object{
        const val WORDS = "words"
    }

    lateinit var adapter: ChatAdapter
    private lateinit var speechRecognizer: SpeechRecognizer
    private var isSpeaking = false

    @Inject
    lateinit var viewModel : ChatViewModel

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInboxBinding {
        return FragmentInboxBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as TravleApplication).travleComponent.inject(this)
        super.onCreate(savedInstanceState)
        val words = arguments?.getString(WORDS)
        viewModel.initializeChat(words?.isNotEmpty() == true, words)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        handleListenData()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun init() {
        speechRecognizer  = SpeechRecognizer.createSpeechRecognizer(requireContext())
        adapter = ChatAdapter(views.rcv) {

        }
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        layoutManager.stackFromEnd = true

        views.rcv.layoutManager = layoutManager
        views.rcv.adapter = adapter

        views.imgSend.setColorFilter(ContextCompat.getColor(requireContext(), R.color.grey))
        views.edtMessage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val content = views.edtMessage.text.toString()
                views.imgSend.setColorFilter(ContextCompat.getColor(requireContext(),if (content.isNotEmpty()) R.color.primary else R.color.grey))
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        views.edtMessage.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND ||
                (event?.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                val content = views.edtMessage.text.toString()
                sendQuestion(content)
                true
            } else {
                false
            }
        }

        views.imgSend.setOnClickListener{
            val content = views.edtMessage.text.toString()
            sendQuestion(content)
        }
        views.imgMic.setOnClickListener{
            if(isSpeaking){
                stopSpeaking()
            }else{
                startSpeaking()
            }
        }

        views.icGuess.setOnClickListener{
            val words = arguments?.getString(WORDS)
            viewModel.initializeChat(true, words)
        }

        views.lvBack.setOnClickListener {
            popFragmentWithSlide()
        }
    }

    private fun sendQuestion(content: String) {
        if (content.isNotEmpty()) {
            viewModel.addMessage(content, Message.SENDER_USER)
            views.rcv.scrollToPosition((viewModel.liveData.messages.value?.size ?: 0))
            viewModel.requestQuestion(content)
            views.edtMessage.text = null
        }
        stopSpeaking()
    }

    private fun handleListenData() {
        viewModel.liveData.messages.observe(viewLifecycleOwner){
            adapter.populateData(it)
        }
        viewModel.liveData.newMessage.observe(viewLifecycleOwner){
            adapter.addMessage(it)
            if(it != null){
                views.rcv.scrollToPosition((viewModel.liveData.messages.value?.size ?: 0))
                viewModel.liveData.newMessage.value = null
            }
        }
        viewModel.liveData.thinking.observe(viewLifecycleOwner){
            adapter.listenGeneration(it)
        }
    }

    private fun startSpeaking() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.RECORD_AUDIO), 1001)
            return
        }

        views.imgMic.setColorFilter(ContextCompat.getColor(requireContext(), R.color.primary))

        val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
        }

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                Log.d("Speech", "Sẵn sàng để nghe...")
                isSpeaking = true
            }

            override fun onBeginningOfSpeech() {
                Log.d("Speech", "Bắt đầu nói...")
            }

            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {
                stopSpeaking()
                Log.d("Speech", "Kết thúc nói...")
            }
            override fun onPartialResults(partialResults: Bundle?) {
                val data = partialResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (!data.isNullOrEmpty()) {
                    views.edtMessage.setText(data[0])
                    views.edtMessage.setSelection(views.edtMessage.text?.length ?: 0)
                }
            }
            override fun onError(error: Int) {
                Log.e("Speech", "Lỗi: $error")
            }

            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (!matches.isNullOrEmpty()) {
                    views.edtMessage.setText(matches[0])
                }
            }

            override fun onEvent(eventType: Int, params: Bundle?) {}
        })

        speechRecognizer.startListening(speechIntent)
    }

    private fun stopSpeaking(){
        views.imgMic.clearColorFilter()
        speechRecognizer.setRecognitionListener(null)
        speechRecognizer.stopListening()
        isSpeaking = false
    }

    override fun onPause() {
        super.onPause()
        stopSpeaking()
    }
}