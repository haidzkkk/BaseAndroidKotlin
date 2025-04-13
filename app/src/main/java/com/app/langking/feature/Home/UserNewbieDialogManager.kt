package com.app.langking.feature.Home

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isVisible
import com.app.langking.core.AppBaseDialog
import com.app.langking.data.model.Category
import com.app.langking.data.model.Question
import com.app.langking.databinding.FragmentExerciseBinding
import com.google.android.material.button.MaterialButton
import java.util.LinkedList
import java.util.Queue

class UserNewbieDialogManager (
    val context: Context,
    val homeViewModel: HomeViewModel,
    val layoutInflater: LayoutInflater
) {

    fun checkNewbie(category: List<Category>?){
        if(category?.firstOrNull()?.lessons.isNullOrEmpty()) return

        val isNewbie = category!!.all { cat ->
            if (cat.id <= 1) {
                cat.lessons?.all { lesson ->
                    val progress = lesson.userProgress
                    progress == null || progress.userId == 0 || !progress.isStarted
                } ?: true
            } else {
                cat.lessons?.all { lesson ->
                    lesson.userProgress == null
                } ?: true
            }
        }

        if(isNewbie){
            showDialogNewbie(arrayListOf(
                Question(
                    title = "Bạn đã học tiếng anh bao giờ chưa?",
                    options = arrayListOf("Chưa bao giờ", "Đã học rồi"),
                    correctAnswer = "Đã học rồi"
                ),
                Question(
                    title = "Bạn đã từng học ngữ pháp chưa?",
                    options = arrayListOf("Chưa bao giờ", "Đã từng học", "Quá quen thuộc"),
                    correctAnswer = "Quá quen thuộc"
                ),
                Question(
                    title = "Tiếng anh của bạn ở mức nào?",
                    options = arrayListOf("Cơ bản", "Bình thường", "Đã biết"),
                    correctAnswer = "Đã biết"
                ),
                Question(
                    title = "Bạn có muốn học thêm từ vựng?",
                    options = arrayListOf("Tất nhiên", "Không muốn"),
                    correctAnswer = "Tất nhiên"
                ),
            ))
        }
    }

    private fun showDialogNewbie(listQuestion: List<Question>){
        val questionQueue: Queue<Question> = LinkedList(listQuestion)

        val dialog = AppBaseDialog.Builder(context, FragmentExerciseBinding.inflate(layoutInflater))
            .build()
        dialog.show()
        dialog.setCancelable(false)

        dialog.binding.icBack.isVisible = false
        dialog.binding.lyHeart.isVisible = false
        dialog.binding.icVolume.isVisible = false

        dialog.binding.tvBack.text = "Bạn là người mới?"
        dialog.binding.tvGuess.text = "Hãy chọn câu trả lời phù hợp với bạn"

        var score = 0
        var asnser: String? = null
        var correctAnswer: String? = null

        nextQuestion(dialog, listQuestion, questionQueue).apply {
            if(this == null) {
                dialog.dismiss()
            }else{
                correctAnswer = this
            }
        }
        dialog.binding.radioAnswer.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                val selectedButton = group.findViewById<MaterialButton>(checkedId)
                asnser = selectedButton.text.toString()
            }
        }
        dialog.binding.btnSubmit.setOnClickListener {
            if(asnser == null) return@setOnClickListener;
            if(asnser == correctAnswer) score++

            nextQuestion(dialog, listQuestion, questionQueue).apply {
                if(this == null) {
                    dialog.dismiss()
                }else{
                    correctAnswer = this
                }
            }
        }

        dialog.setOnDismissListener {
            Toast.makeText(context, "Các khóa học khác đã được mở, hãy bắt đầu học ngay nào!", Toast.LENGTH_LONG).show()
            val categories = homeViewModel.liveData.categories.value?.data ?: arrayListOf()
            if(score >= 2 && categories.size > score){
                homeViewModel.initProcessWithCategoryLesson(categories.subList(0, score))
            }
        }
    }

    private fun nextQuestion(
        dialog: AppBaseDialog<FragmentExerciseBinding>,
        listQuestion: List<Question>,
        questionQueue: Queue<Question>
    ): String? {
        val question = questionQueue.poll() ?: return null
        dialog.binding.radioAnswer.clearChecked()
        dialog.binding.tvProcess.text = "${listQuestion.size - questionQueue.size}/${listQuestion.size}"
        dialog.binding.tvQuestion.text = question.title
        dialog.binding.processIndicator.progress = ((listQuestion.size - questionQueue.size) * 100) / (listQuestion.size)

        dialog.binding.btnAnswer1.isVisible = false
        dialog.binding.btnAnswer2.isVisible = false
        dialog.binding.btnAnswer3.isVisible = false
        dialog.binding.btnAnswer4.isVisible = false
        question.options.forEachIndexed { index, option ->
            when (index) {
                0 -> {
                    dialog.binding.btnAnswer1.text = option
                    dialog.binding.btnAnswer1.isVisible = true
                }
                1 -> {
                    dialog.binding.btnAnswer2.text = option
                    dialog.binding.btnAnswer2.isVisible = true
                }
                2 -> {
                    dialog.binding.btnAnswer3.text = option
                    dialog.binding.btnAnswer3.isVisible = true
                }
                3 -> {
                    dialog.binding.btnAnswer4.text = option
                    dialog.binding.btnAnswer4.isVisible = true
                }
            }
        }

        return question.correctAnswer
    }
}