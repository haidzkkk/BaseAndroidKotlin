package com.app.langking.ui.Lean.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.langking.core.AppViewLiveData
import com.app.langking.data.model.Category
import com.app.langking.data.model.Lesson
import com.app.langking.data.model.TypeSpeak
import com.app.langking.data.model.Word
import java.util.LinkedList
import java.util.Queue
import kotlin.random.Random

class LearnViewState: AppViewLiveData {
    val currentLesson: MutableLiveData<Lesson?> = MutableLiveData<Lesson?>()
    val categories: MutableLiveData<List<Category>> = MutableLiveData<List<Category>>()
    val typeSpeak: MutableLiveData<TypeSpeak> = MutableLiveData<TypeSpeak>()

    val testCurrentWord: MutableLiveData<Word> = MutableLiveData<Word>()
    val testShuffleWords: MutableLiveData<Queue<Word>> = MutableLiveData<Queue<Word>>()
    val testAllowedMistakesCount : MutableLiveData<Int> = MutableLiveData<Int>()
    var testRandomType: TypeTest = TypeTest.english

    companion object{
        const val allowedMistakes: Int = 3
    }
}

enum class TypeTest{
    english, vietnamese;

    fun isEnglish(): Boolean{
        return this == english
    }

    fun isVietnamese(): Boolean{
        return this == vietnamese
    }

    companion object{
        fun getRandom(): TypeTest{
            return if(Random.nextBoolean()) english else vietnamese
        }
    }
}