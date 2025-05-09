package com.app.langking.feature.Learn

import android.graphics.PorterDuff
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.speech.tts.Voice
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.app.langking.R
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseFragment
import com.app.langking.data.model.Speak
import com.app.langking.data.model.TypeSpeak
import com.app.langking.data.model.Word
import com.app.langking.databinding.DialogOptionLearnBinding
import com.app.langking.databinding.FragmentLessonBinding
import com.app.langking.feature.Learn.viewmodel.LearnViewModel
import com.app.langking.feature.adapter.LearnWordAdapter
import com.app.langking.ultis.navigateFragmentWithSlide
import com.app.langking.ultis.popFragmentWithSlide
import java.util.Locale
import javax.inject.Inject


class LessonWordFragment @Inject constructor() : AppBaseFragment<FragmentLessonBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : LearnViewModel by lazy{
        ViewModelProvider(requireActivity(), viewModelFactory)[LearnViewModel::class.java]
    }
    lateinit var adapter: LearnWordAdapter

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLessonBinding {
        return FragmentLessonBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as TravleApplication).travleComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        init()
        handleListenData()
    }

    private fun init() {
        textToSpeech = TextToSpeech(requireContext(), {

        }, "com.google.android.tts")

        adapter = LearnWordAdapter(
            onItemClick = {

            },
            onSpeech = { speak ->
                isAutoSpeak = false
                startSpeakLesson(speak)
            }
        )
        views.rcv.adapter = adapter
        isAutoSpeak = false
        views.icPlay.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white), PorterDuff.Mode.SRC_IN)

        views.lvBack.setOnClickListener {
            popFragmentWithSlide()
        }

        views.lyPlay.setOnClickListener {
            isAutoSpeak = !isAutoSpeak
        }

        views.icOption.setOnClickListener{
            showMenu()
        }
        views.lyTest.setOnClickListener {
            navigateFragmentWithSlide(R.id.exerciseFragment)
        }
    }

    private fun showMenu() {
        val binding = DialogOptionLearnBinding.inflate(layoutInflater, null, false)

        val popupWindow = PopupWindow(
            binding.root,  // View từ ViewBinding
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )

        popupWindow.showAsDropDown(views.icOption)

        binding.swEnglish.isChecked = viewModel.liveData.typeSpeak.value?.isEnglish ?: false
        binding.swEnglishSlow.isChecked = viewModel.liveData.typeSpeak.value?.isEnglishSlow ?: false
        binding.swVietnamese.isChecked = viewModel.liveData.typeSpeak.value?.isVietnamese ?: false
        binding.swDescEnglish.isChecked = viewModel.liveData.typeSpeak.value?.isEnglishDesc ?: false
        binding.swDescVietnamese.isChecked = viewModel.liveData.typeSpeak.value?.isVietnameseDesc ?: false

        binding.swEnglish.setOnCheckedChangeListener { _, isChecked ->
            viewModel.liveData.typeSpeak.value?.isEnglish = isChecked
            if(viewModel.liveData.typeSpeak.value!= null) viewModel.setTypeSpeak(viewModel.liveData.typeSpeak.value!!)
        }
        binding.swEnglishSlow.setOnCheckedChangeListener { _, isChecked ->
            viewModel.liveData.typeSpeak.value?.isEnglishSlow = isChecked
            if(viewModel.liveData.typeSpeak.value!= null) viewModel.setTypeSpeak(viewModel.liveData.typeSpeak.value!!)
        }
        binding.swVietnamese.setOnCheckedChangeListener { _, isChecked ->
            viewModel.liveData.typeSpeak.value?.isVietnamese = isChecked
            if(viewModel.liveData.typeSpeak.value!= null) viewModel.setTypeSpeak(viewModel.liveData.typeSpeak.value!!)
        }
        binding.swDescEnglish.setOnCheckedChangeListener { _, isChecked ->
            viewModel.liveData.typeSpeak.value?.isEnglishDesc = isChecked
            if(viewModel.liveData.typeSpeak.value!= null) viewModel.setTypeSpeak(viewModel.liveData.typeSpeak.value!!)
        }
        binding.swDescVietnamese.setOnCheckedChangeListener { _, isChecked ->
            viewModel.liveData.typeSpeak.value?.isVietnameseDesc = isChecked
            if(viewModel.liveData.typeSpeak.value!= null) viewModel.setTypeSpeak(viewModel.liveData.typeSpeak.value!!)
        }
    }

    private fun handleListenData(){
        viewModel.liveData.currentLesson.observe(viewLifecycleOwner){
            views.tvBack.text = it?.name
            adapter.populateData(it?.words)
            Log.d("TAG", "initViewmodel: $it")
        }
    }

    private fun speckText(speak: Speak){
        val content = speak.content;
        textToSpeech.setSpeechRate(speak.speed)
        textToSpeech.voice = speak.voice
        textToSpeech.speak(content, speak.queueMode, null,"")
    }

    private lateinit var textToSpeech: TextToSpeech
    var wordPosition = 0
    private var isAutoSpeak: Boolean = false
        set(value) {
            field = value
            views.icPlay.setImageResource(if(value) R.drawable.baseline_play_24 else R.drawable.baseline_pause_arrow_24)
            if(value){
                startSpeakLesson()
            }else{
                textToSpeech.setOnUtteranceProgressListener(null)
                textToSpeech.stop()
                adapter.changeItemSelected(-1)
            }
        }

    private fun startSpeakLesson(speak: Speak? = null) {
        var currentSpeak: Speak
        if(speak != null){
            currentSpeak = speak
        }else{
            if(!checkSpeak()) return
            val wordStart: Word = viewModel.liveData.currentLesson.value!!.words!![wordPosition]
            currentSpeak = Speak(wordStart,
                queueMode = TextToSpeech.QUEUE_ADD,
                durationMillisecond = 1000,
                typeSpeak = viewModel.liveData.typeSpeak.value?.copy() ?: TypeSpeak()
            )
        }

        speckText(currentSpeak)

        textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {
                Log.e("TAG", "onStart: $utteranceId $wordPosition")
                if(speak == null) views.rcv.smoothScrollToPosition(wordPosition)
            }

            override fun onDone(utteranceId: String?) {
                if(currentSpeak.typeSpeak.isContinue){
                    speckText(currentSpeak)
                    return
                }

                if(speak != null) return

                wordPosition++
                if(!checkSpeak()) return
                val wordForward: Word = viewModel.liveData.currentLesson.value!!.words!![wordPosition]
                currentSpeak = Speak(wordForward,
                    queueMode = TextToSpeech.QUEUE_ADD,
                    durationMillisecond = 1000,
                    typeSpeak = viewModel.liveData.typeSpeak.value?.copy() ?: TypeSpeak()
                )
                speckText(currentSpeak)
            }
            override fun onError(utteranceId: String?) {
                textToSpeech.stop()
            }
        })
    }

    fun checkSpeak(): Boolean{
        if(viewModel.liveData.typeSpeak.value?.isContinue == null
            || viewModel.liveData.typeSpeak.value?.isContinue == false){
            isAutoSpeak = false
            Toast.makeText(requireContext(), "Chưa chọn chế độ nghe", Toast.LENGTH_SHORT).show()
            return false
        }
        if(viewModel.liveData.currentLesson.value?.words.isNullOrEmpty()) return false
        if(wordPosition >= viewModel.liveData.currentLesson.value!!.words!!.size){
            wordPosition = 0
        }
        requireActivity().runOnUiThread {
            adapter.changeItemSelected(wordPosition)
        }
        return true;
    }


    override fun onDestroyView() {
        super.onDestroyView()
        textToSpeech.stop()
        textToSpeech.shutdown()
    }
}