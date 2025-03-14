package com.app.langking.ui.Lean

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.app.langking.R
import com.app.langking.TravleApplication
import com.app.langking.core.AppBaseFragment
import com.app.langking.data.model.Speak
import com.app.langking.data.model.TypeSpeak
import com.app.langking.data.model.Word
import com.app.langking.databinding.DialogTestFinishBinding
import com.app.langking.databinding.FragmentExerciseBinding
import com.app.langking.databinding.FragmentLessonBinding
import com.app.langking.databinding.ItemLearnBinding
import com.app.langking.ui.Lean.viewmodel.LearnViewModel
import com.app.langking.ui.Lean.viewmodel.LearnViewState
import com.app.langking.ui.adapter.LearnWordAdapter
import com.app.langking.ultis.popFragmentWithSlide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.LinkedList
import java.util.Queue
import javax.inject.Inject

class ExerciseFragment @Inject constructor() : AppBaseFragment<FragmentExerciseBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : LearnViewModel by lazy{
        ViewModelProvider(requireActivity(), viewModelFactory)[LearnViewModel::class.java]
    }
    lateinit var adapter: LearnWordAdapter
    private lateinit var textToSpeech: TextToSpeech

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExerciseBinding {
        return FragmentExerciseBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as TravleApplication).travleComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        init()
        handleListenData()
    }

    private var answer: String? = null
    private fun init() {
        textToSpeech = TextToSpeech(requireContext()) {}

        views.lvBack.setOnClickListener {
            popFragmentWithSlide()
        }
        views.radioAnswer.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                val selectedButton = group.findViewById<MaterialButton>(checkedId)
                answer = selectedButton.text.toString()
            }
        }
        views.btnSubmit.setOnClickListener{
            submitAnswer()
        }
        viewModel.startTest()
    }

    private fun handleListenData() {
        viewModel.liveData.currentLesson.observe(viewLifecycleOwner){
            views.tvBack.text = "${it?.name ?: ""} > Bài tập > ${it?.userProgress.toString()}"
        }
        viewModel.liveData.testAllowedMistakesCount.observe(viewLifecycleOwner){
            handleShowMistakes(it)
        }
        viewModel.liveData.testCurrentWord.observe(viewLifecycleOwner){
            views.tvQuestion.text = if(viewModel.liveData.testRandomType.isEnglish()) it.english else it.vietnamese
            val answers: Queue<String> = viewModel.getAnswers(it)
            views.btnAnswer1.text = answers.poll()
            views.btnAnswer2.text = answers.poll()
            views.btnAnswer3.text = answers.poll()
            views.btnAnswer4.text = answers.poll()

            val total: Int = viewModel.liveData.currentLesson.value?.words?.size ?: 0
            val currentPosition: Int = total - (viewModel.liveData.testShuffleWords.value?.size ?: 0)
            handleProcess(currentPosition, total)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleProcess(currentPosition: Int, total: Int){
        if(currentPosition < 0 || total < 0 || currentPosition > total) {
            views.tvProcess.text = "0 / 0"
            views.processIndicator.progress = 0
            return
        }
        views.tvProcess.text = "$currentPosition / $total"
        views.processIndicator.progress = (currentPosition.toDouble() / total.toDouble() * 100.0).toInt()

        val emojis = arrayListOf(
            R.drawable.imoji_fun1,
            R.drawable.imoji_fun2,
            R.drawable.imoji_fun3,
            R.drawable.imoji_fun4,
            R.drawable.imoji_fun5,
            R.drawable.imoji_calm,
            R.drawable.imoji_fight,
            R.drawable.imoji_fire,
            R.drawable.imoji_hi,
            R.drawable.imoji_like_2,
        )
        views.icEmoji.setImageResource(emojis.random())
    }

    private fun handleShowMistakes(count: Int ){
        views.icHeart1.apply {
            if (count >= 1){
                this.clearColorFilter()
            }else{
                this.setColorFilter(ContextCompat.getColor(requireContext(), R.color.grey), PorterDuff.Mode.SRC_IN)
            }
        }
        views.icHeart2.apply {
            if (count >= 2){
                this.clearColorFilter()
            }else{
                this.setColorFilter(ContextCompat.getColor(requireContext(), R.color.grey), PorterDuff.Mode.SRC_IN)
            }
        }
        views.icHeart3.apply {
            if (count >= 3){
                this.clearColorFilter()
            }else{
                this.setColorFilter(ContextCompat.getColor(requireContext(), R.color.grey), PorterDuff.Mode.SRC_IN)
            }
        }
        views.icVolume.setOnClickListener{
            val word = viewModel.liveData.testCurrentWord.value ?: return@setOnClickListener
            val typeSpeak = viewModel.liveData.testRandomType
            speckText(Speak(word, typeSpeak = TypeSpeak(isEnglish = typeSpeak.isEnglish(), isVietnamese = typeSpeak.isVietnamese())))
        }
    }

    private fun submitAnswer() {
        if(answer.isNullOrEmpty()){
            Toast.makeText(requireContext(), "Vui lòng chọn đáp án", Toast.LENGTH_SHORT).show()
            return
        }
        val isCorrect = viewModel.submitAnswer(answer!!)
        if(isCorrect){
            Toast.makeText(requireContext(), "Đáp án đúng", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(), "Sai đáp án", Toast.LENGTH_SHORT).show()
        }
        showBottomSheet(isCorrect, viewModel.liveData.testCurrentWord.value)
    }

    private fun showBottomSheet(isCorrect: Boolean, word: Word?){
        val dialog = BottomSheetDialog(requireContext())
        val binding = ItemLearnBinding.inflate(layoutInflater)

        binding.tvPosition.isVisible = false
        binding.icExpand.isVisible = false
        binding.iconShow.isVisible = false
        binding.iconVietnameseShow.isVisible = false
        binding.iconVietnameseCopy.isVisible = false
        binding.tvEnglish.text = word?.english
        binding.tvPronunciation.text = word?.pronunciation
        binding.tvVietnamese.text = word?.vietnamese
        binding.tvDescEnglish.text = word?.description
        binding.tvDescVietnamese.text = word?.descriptionVietnamese
        binding.icPlay.setImageResource(if(isCorrect) R.drawable.icons8_true else R.drawable.icons8_false)
        binding.icVolume.setOnClickListener{
            speckText(Speak(word!!, typeSpeak = TypeSpeak(isEnglish = true)))
        }
        binding.icVolumeSlow.setOnClickListener{
            speckText(Speak(word!!, typeSpeak = TypeSpeak(isEnglishSlow = true)))
        }
        binding.icVolumeVietnamese.setOnClickListener{
            speckText(Speak(word!!, typeSpeak = TypeSpeak(isVietnamese = true)))
        }
        Button(requireContext()).apply {
            text = "Tiếp tục"
            textSize = 16f
            setBackgroundColor(ContextCompat.getColor(context, if(isCorrect) R.color.primary else R.color.red))
            setTextColor(ContextCompat.getColor(context, android.R.color.white))
            binding.containerChild.addView(this)

            setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.setContentView(binding.root)
        dialog.show()

        dialog.setOnDismissListener {
            textToSpeech.stop()
            handleNextQuestion()
        }

    }

    private fun handleNextQuestion() {
        views.radioAnswer.clearChecked()
        answer = null

        val mistakesCount = (viewModel.liveData.testAllowedMistakesCount.value ?: 0)
        if(mistakesCount <= 0){
            showFinishTestDialog(false, LearnViewState.allowedMistakes - mistakesCount)
            Toast.makeText(requireContext(), "Bạn đã hết lượt sai", Toast.LENGTH_SHORT).show()
            return
        }

        val isContinue = viewModel.nextQuestion()
        if(!isContinue) {
            showFinishTestDialog(true, LearnViewState.allowedMistakes - mistakesCount)
            Toast.makeText(requireContext(), "Bạn đã hoàn thành câu hỏi", Toast.LENGTH_SHORT).show()
            return
        }
    }

    private fun speckText(speak: Speak){
        val content = speak.content;
        textToSpeech.setSpeechRate(speak.speed)
        textToSpeech.language = speak.language
        textToSpeech.speak(content, speak.queueMode, null,"")
    }

    private fun showFinishTestDialog(
        isFinish: Boolean,
        countIncorrect: Int,
    ){
        val binding = DialogTestFinishBinding.inflate(layoutInflater, null , false)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.tvTitle.text = if(isFinish) "Quá tuyệt vời!" else "Làm lại nào!"
        binding.tvContent.text = if(isFinish) "Tiếp tục làm tốt ở những bài tập tiếp theo nhé!" else "Làm lại nào! Bạn vẫn còn làm sai vài câu"
        binding.tvProcessCount.text = "$countIncorrect"
        binding.tvTitle.setBackgroundColor(ContextCompat.getColor(requireContext(), if(isFinish) R.color.primary else R.color.red))
        binding.icon1.apply {
            if (countIncorrect < 3){
                this.clearColorFilter()
            }else{
                this.setColorFilter(ContextCompat.getColor(requireContext(), R.color.grey), PorterDuff.Mode.SRC_IN)
            }
        }
        binding.icon2.apply {
            if (countIncorrect < 2){
                this.clearColorFilter()
            }else{
                this.setColorFilter(ContextCompat.getColor(requireContext(), R.color.grey), PorterDuff.Mode.SRC_IN)
            }
        }
        binding.icon3.apply {
            if (countIncorrect < 1){
                this.clearColorFilter()
            }else{
                this.setColorFilter(ContextCompat.getColor(requireContext(), R.color.grey), PorterDuff.Mode.SRC_IN)
            }
        }

        var isReTest = false;
        binding.btnReTest.setOnClickListener{
            dialog.dismiss()
            isReTest = true
            viewModel.startTest()
        }
        binding.btnSubmit.setOnClickListener{
            dialog.dismiss()
        }

        dialog.show()

        dialog.setOnDismissListener {
            if(!isReTest) popFragmentWithSlide()
            viewModel.submitTest()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        textToSpeech.stop()
        textToSpeech.shutdown()
    }
}