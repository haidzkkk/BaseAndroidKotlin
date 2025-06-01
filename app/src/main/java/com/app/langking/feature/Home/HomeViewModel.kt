package com.app.langking.feature.Home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.app.langking.core.AppBaseViewModel
import com.app.langking.data.local.DatabaseManager
import com.app.langking.data.model.Category
import com.app.langking.data.model.Lesson
import com.app.langking.data.model.UserProgress
import com.app.langking.data.repository.HomeRepository
import com.app.langking.data.repository.UserRepository
import com.app.langking.ultis.DateConverter
import com.app.langking.ultis.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repo: HomeRepository,
    private val userRepository: UserRepository,
    ) : AppBaseViewModel<HomeViewLiveData, HomeViewAction, HomeViewEvent>(HomeViewLiveData()) {

    init {
        onUpdate()
        importSampleData()
    }

    override fun handle(action: HomeViewAction) {
        when(action){
            is HomeViewAction.updateViewAction -> onUpdate()
            is HomeViewAction.getCategoryViewAction -> getCategory()
        }
    }

    private fun onUpdate(){
        viewModelScope.launch {
            liveData.currentUser.postValue(userRepository.getCurrentUser())
        }
    }

    private fun importSampleData(){
        viewModelScope.launch {
//            repo.importSampleData()
        }
    }

    fun getSampleData(){
        viewModelScope.launch {
            val categories = repo.getSampleData()
        }
    }

    private fun getCategory(){
        val userId = userRepository.getCurrentUserId()
        viewModelScope.launch {
            val categories: Resource<List<Category>> = repo.getCategoriesByUser()
            categories.data?.forEach {
                it.lessons = repo.getLessonProcessById(it.id, userId)
                liveData.categories.postValue(categories)
            }
            updateLesson(categories.data ?: arrayListOf())
        }
    }

    private fun updateLesson(currentCategories: List<Category>){

        val categoriesSortPosition = currentCategories.sortedBy { it.position }
        val firstPosition = currentCategories.minByOrNull { it.position ?: 1 }?.position ?: 1

        for (index in categoriesSortPosition.indices) {
            val category = currentCategories[index]

            val listLessonWithoutProcess = category.lessonWithoutProcess() ?: arrayListOf()
            var mapLessonWithoutProcessUpdated: HashMap<String, Lesson>? = null
            if(category.position == firstPosition
                && category.checkCompletedCategoryPrevious(category)
                && listLessonWithoutProcess.isNotEmpty()){
                mapLessonWithoutProcessUpdated = createUserProcessForLesson(listLessonWithoutProcess)
            }else if(category.position != firstPosition
                && category.checkCompletedCategoryPrevious(categoriesSortPosition[index - 1])
                && listLessonWithoutProcess.isNotEmpty()){
                mapLessonWithoutProcessUpdated = createUserProcessForLesson(listLessonWithoutProcess)
            }

            if(mapLessonWithoutProcessUpdated?.isNotEmpty() == true){
                if(category.lessons != null){
                    category.lessons!!.forEach { lesson ->
                        if(mapLessonWithoutProcessUpdated.containsKey(lesson.id)){
                            lesson.userProgress = mapLessonWithoutProcessUpdated[lesson.id]!!.userProgress
                        }
                    }
                }else{
                    category.lessons = mapLessonWithoutProcessUpdated.values.toList()
                }
            }
        }
        liveData.categories.postValue(Resource.Success(currentCategories))
    }

    private fun createUserProcessForLesson(listLessonWithoutProcess : List<Lesson>): HashMap<String, Lesson>{
        val myMap: HashMap<String, Lesson> = hashMapOf()
        val userId = userRepository.getCurrentUserId()
        listLessonWithoutProcess.forEach {lesson ->
            val userProcessAdd = UserProgress(
                userId = userId,
                lessonId = lesson.id,
                score = 0,
                dateStart = DateConverter.getCurrentDateTime()
            )
            viewModelScope.launch {
                repo.pushUserProgress(userProcessAdd)
            }
            lesson.userProgress = userProcessAdd
            myMap[lesson.id] = lesson
        }
        return myMap
    }

    fun initProcessWithCategoryLesson(categories: List<Category>){
        for (index in categories.indices) {
            val category = categories[index]
            val listLessonWithoutProcess = category.lessonWithoutProcess() ?: arrayListOf()
            if(listLessonWithoutProcess.isNotEmpty()){
                createUserProcessForLesson(listLessonWithoutProcess)
            }
        }
        getCategory()
    }
}