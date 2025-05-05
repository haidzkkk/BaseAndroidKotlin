package com.app.motel.feature.notify.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.app.motel.core.AppBaseViewModel
import com.app.motel.data.entity.KhieuNaiEntity
import com.app.motel.data.model.Complaint
import com.app.motel.data.model.Resource
import com.app.motel.data.repository.ComplaintRepository
import com.app.motel.feature.profile.UserController
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotifyViewModel @Inject constructor(
    private val complaintRepository: ComplaintRepository,
    val userController: UserController,
): AppBaseViewModel<NotifyViewState, NotifyViewAction, NotifyViewEvent>(NotifyViewState()) {
    override fun handle(action: NotifyViewAction) {

    }

    fun setCurrentType(position: Int){
        when(position){
            0 -> liveData.currentType.postValue(KhieuNaiEntity.Type.APPLICATION)
            1 -> liveData.currentType.postValue(KhieuNaiEntity.Type.COMPLAINT)
            2 -> liveData.currentType.postValue(KhieuNaiEntity.Type.RENT_ROOM)
            else -> liveData.currentType.postValue(KhieuNaiEntity.Type.APPLICATION)
        }
    }

    fun getComplaint(){
        viewModelScope.launch {
            try {
                val complaints = complaintRepository.getComplaintAdmin(userController.state.currentBoardingHouseId)
                liveData.complaints.postValue(complaints)
            }catch (e: Exception){
                Log.e("NotifyViewModel", "lỗi: complaints: ${e.message}")
            }
        }
    }

    fun setCurrentHandleComplaint(item: Complaint?) {
        liveData.currentHandleComplaint.postValue(item)
    }

    fun updateStateComplaint(complaint: Complaint, state: String){
        when{
            complaint.id.isBlank() -> {
                liveData.updateComplaint.postValue(Resource.Error(message = "Không tìm thấy khiếu nại yêu cầu"))
                return
            }
            state.isBlank() -> {
                liveData.updateComplaint.postValue(Resource.Error(message = "Trạng thái không được để trống"))
                return
            }
            state !in KhieuNaiEntity.Status.entries.map { it.value } -> {
                liveData.updateComplaint.postValue(Resource.Error(message = "Trạng thái không hợp lệ"))
                return
            }
            state == complaint.status -> {
                liveData.updateComplaint.postValue(Resource.Error(message = "Trạng thái không thay đổi"))
                return
            }
            (complaint.status == KhieuNaiEntity.Status.RESOLVED.value
             || complaint.status == KhieuNaiEntity.Status.REJECTED.value
                    ) && (state == KhieuNaiEntity.Status.PENDING.value
                    || state == KhieuNaiEntity.Status.NEW.value) -> {
                liveData.updateComplaint.postValue(Resource.Error(message = " Khiếu nại đã được xử lý rồi"))
                return
            }
        }
        viewModelScope.launch {
            complaintRepository.updateStateComplaint(complaint.id, state)
            getComplaint()
        }
    }

//    fun addNews(title: String, content: String, roomId: String?){
//
//        when{
//            !userController.state.isAdmin -> {
//                liveData.addNews.postValue(Resource.Error(message = "Bạn không có quyền thêm tin tức"))
//                return
//            }
//            userController.state.currentBoardingHouseId.isBlank() -> {
//                liveData.addNews.postValue(Resource.Error(message = "Bạn chưa chọn khu trọ"))
//                return
//            }
//            title.isBlank() -> {
//                liveData.addNews.postValue(Resource.Error(message = "Tiêu đề không được đẻ trống"))
//                return
//            }
//            content.isBlank() -> {
//                liveData.addNews.postValue(Resource.Error(message = "Nội dung không được đẻ trống"))
//                return
//            }
//        }
//
//        val news = Notification(
//            title = title,
//            content = content,
//            khuTroId = userController.state.currentBoardingHouseId,
//            phongId = roomId,
//        )
//
//        viewModelScope.launch {
//            val newsAdd = notificationRepository.addNews(news)
//            liveData.addNews.postValue(newsAdd)
//        }
//    }
}