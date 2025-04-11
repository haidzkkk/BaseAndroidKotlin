package com.app.langking.feature.Home

import com.app.langking.core.AppBaseViewModel
import com.app.langking.data.local.DatabaseManager
import com.app.langking.data.model.Category
import com.app.langking.data.model.Lesson
import com.app.langking.data.model.UserProgress
import com.app.langking.data.repository.HomeRepository
import com.app.langking.data.repository.UserRepository
import com.app.langking.ultis.DateConverter
import com.app.langking.ultis.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repo: HomeRepository,
    private val userRepository: UserRepository,
    private val dbManager: DatabaseManager,
    ) : AppBaseViewModel<HomeViewLiveData, HomeViewAction, HomeViewEvent>(HomeViewLiveData()) {

    init {
        onUpdate()
        getCategory()
    }

    override fun handle(action: HomeViewAction) {
        when(action){
            is HomeViewAction.updateViewAction -> onUpdate()
            is HomeViewAction.getCategoryViewAction -> getCategory()
        }
    }

    private fun onUpdate(){
        liveData.currentUser.postValue(userRepository.getCurrentUser())
    }

    private fun getCategory(){
        val userId = userRepository.getCurrentUser().id
        val categories: List<Category> = dbManager.getAllCategoriesWithLessonsAndWords(userId)
        updateLesson(categories)
    }

    private fun updateLesson(currentCategories: List<Category>){
        for (index in currentCategories.indices) {
            val category = currentCategories[index]
            val listLessonWithoutProcess = category.getLessonWithoutProcess() ?: arrayListOf()
            var mapLessonWithoutProcessUpdated: HashMap<Int, Lesson>? = null
            if(index == 0
                && category.checkCompletedCategoryPrevious(category)
                && listLessonWithoutProcess.isNotEmpty()){
                mapLessonWithoutProcessUpdated = createUserProcessForLesson(listLessonWithoutProcess)
            }else if(index != 0
                && category.checkCompletedCategoryPrevious(currentCategories[index - 1])
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

    private fun createUserProcessForLesson(listLessonWithoutProcess : List<Lesson>): HashMap<Int, Lesson>{
        val myMap: HashMap<Int, Lesson> = hashMapOf()
        val userId = userRepository.getCurrentUser().id
        listLessonWithoutProcess.forEach {lesson ->
            val userProcessAdd = UserProgress(
                userId = userId,
                lessonId = lesson.id,
                score = 0,
                dateStart = DateConverter.getCurrentDateTime()
            )
            dbManager.addUserProgress(userProcessAdd)
            lesson.userProgress = userProcessAdd
            myMap[lesson.id] = lesson
        }
        return myMap
    }
}