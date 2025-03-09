package com.app.langking.ui.Lean.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.langking.core.AppViewLiveData
import com.app.langking.data.model.Category
import com.app.langking.data.model.Lesson

class LearnViewState: AppViewLiveData {
    val currentLesson: MutableLiveData<Lesson?> = MutableLiveData<Lesson?>()
    val categories: MutableLiveData<List<Category>> = MutableLiveData<List<Category>>()
}