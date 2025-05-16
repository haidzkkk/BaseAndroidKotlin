package com.app.motel.data.model

data class Quiz(
    override val id: String? = null,          // từ 0 .. với mỗi list
    val title: String? = null,                // Ví dụ: "2 Bà trưng - Nhà Ngô"
    val formPeriod: Int? = null,               // Ví dụ: "-329"
    val toPeriod: Int? = null,               // Ví dụ: "980"
    val period: String? = null,               // Ví dụ: "-329 - 980"
    val questionCount: Int? = null,          // Ví dụ: 20
    val durationMinutes: Int? = null,        // Ví dụ: 10
    val playCount: Int? = null,              // Ví dụ: 103025
    val image: String? = null,               // Tùy chọn: tôi tự thêm
    val questions: List<Question>? = null,    // Danh sách câu hỏi
): RealTimeId

data class Question(
    override val id: String? = null,          // từ 0 .. với mỗi list
    val text: String? = null,                // Ví dụ: "Hãy chọn vũ khí để đội quân của Ngô Quyền..."
    val image: String? = null,              // Tùy chọn: tôi tự thêm
    val answers: List<Answer>? = null,       // Danh sách đáp án
    val correctAnswerId: String? = null,     // ID đáp án đúng
    val userAnswerId: String? = null // ID đáp án mà người dùng chọn (nếu đã chọn)
): RealTimeId

data class Answer(
    override val id: String? = null,          // từ 0 .. với mỗi list
    val text: String? = null,                // Ví dụ: "Cọc gỗ"
): RealTimeId