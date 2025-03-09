package com.app.langking.ui.Lean.viewmodel
import android.util.Log
import com.app.langking.core.AppBaseViewModel
import com.app.langking.data.local.DatabaseManager
import com.app.langking.data.model.Lesson
import javax.inject.Inject

class LearnViewModel @Inject constructor(
    private val dbManager: DatabaseManager,
) : AppBaseViewModel<LearnViewState, LearnViewAction, LearnViewEvent>(LearnViewState()) {

    override fun handle(action: LearnViewAction) {

    }

    init {
        val categories = dbManager.getAllCategoriesWithLessonsAndWords();
        liveData.categories.postValue(categories)
    }

    fun initViewmodel(lesson: Lesson?){
        liveData.currentLesson.postValue(lesson
            ?: liveData.categories.value?.firstOrNull()?.lessons?.firstOrNull())

    }
}