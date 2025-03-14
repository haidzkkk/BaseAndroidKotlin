package com.app.langking.data.local
import android.content.Context
import android.util.Log
import com.app.langking.data.model.Category
import com.app.langking.data.model.Lesson
import com.app.langking.data.model.UserProgress
import com.app.langking.data.model.Word
import javax.inject.Inject

class DatabaseManager @Inject constructor(val context: Context) {
    init {
        if(getUserCategory().isEmpty()){
            insertData()
        }
    }

    private fun insertData(){
        insertSampleCategories()
        insertSampleLesson()
        insertSampleWords()
        insertSampleUserProgress()
    }

    private fun insertSampleCategories() {
        val categoryDAO = CategoryDao(context)

        val categories = listOf(
            Category(id = 1, name = "Basic Vocabulary"),
            Category(id = 2, name = "Travel & Tourism"),
            Category(id = 3, name = "Business English"),
            Category(id = 4, name = "Everyday Conversations"),
            Category(id = 5, name = "English for Kids")
        )

        for (category in categories) {
            categoryDAO.insertCategory(category)
        }
    }

    private fun insertSampleLesson() {
        val lessonDao = LessonDao(context)

        val lessons = listOf(
            // Basic Vocabulary
            Lesson(id = 1, categoryId = 1, name = "Common Words"),
            Lesson(id = 2, categoryId = 1, name = "Numbers & Colors"),
            Lesson(id = 3, categoryId = 1, name = "Basic Phrases"),

            // Travel & Tourism
            Lesson(id = 4, categoryId = 2, name = "At the Airport"),
            Lesson(id = 5, categoryId = 2, name = "Hotel Conversations"),
            Lesson(id = 6, categoryId = 2, name = "Ordering Food"),

            // Business English
            Lesson(id = 7, categoryId = 3, name = "Office Communication"),
            Lesson(id = 8, categoryId = 3, name = "Email Writing"),
            Lesson(id = 9, categoryId = 3, name = "Job Interviews"),

            // Everyday Conversations
            Lesson(id = 10, categoryId = 4, name = "Shopping Dialogues"),
            Lesson(id = 11, categoryId = 4, name = "Making Appointments"),
            Lesson(id = 12, categoryId = 4, name = "Small Talk"),

            // English for Kids
            Lesson(id = 13, categoryId = 5, name = "Animal Names"),
            Lesson(id = 14, categoryId = 5, name = "Fun with Rhymes"),
            Lesson(id = 15, categoryId = 5, name = "Storytelling"),
        )

        lessons.forEach { lesson ->
            lessonDao.insertLesson(lesson)
        }
    }

    private fun insertSampleWords() {
        val wordDao = WordDao(context)

        val words = listOf(
            // Common Words
            Word(lessonId = 1, english = "Hello", vietnamese = "Xin chào", pronunciation = "həˈloʊ", description = "A greeting used when meeting someone.", descriptionVietnamese = "Một lời chào khi gặp ai đó."),
            Word(lessonId = 1, english = "Goodbye", vietnamese = "Tạm biệt", pronunciation = "ˌɡʊdˈbaɪ", description = "A farewell when leaving.", descriptionVietnamese = "Lời chào khi rời đi."),
            Word(lessonId = 1, english = "Please", vietnamese = "Làm ơn", pronunciation = "pliːz", description = "A polite word used in requests.", descriptionVietnamese = "Từ lịch sự dùng khi yêu cầu."),
            Word(lessonId = 1, english = "Thank you", vietnamese = "Cảm ơn", pronunciation = "ˈθæŋk ju", description = "An expression of gratitude.", descriptionVietnamese = "Biểu hiện sự biết ơn."),
            Word(lessonId = 1, english = "Yes", vietnamese = "Vâng", pronunciation = "jɛs", description = "An affirmative response.", descriptionVietnamese = "Một câu trả lời khẳng định."),
            Word(lessonId = 1, english = "No", vietnamese = "Không", pronunciation = "noʊ", description = "A negative response.", descriptionVietnamese = "Một câu trả lời phủ định."),
            Word(lessonId = 1, english = "Excuse me", vietnamese = "Xin lỗi", pronunciation = "ɪkˈskjuːz mi", description = "Used to get someone's attention or to apologize.", descriptionVietnamese = "Dùng để thu hút sự chú ý hoặc xin lỗi."),
            Word(lessonId = 1, english = "Sorry", vietnamese = "Xin lỗi", pronunciation = "ˈsɑːri", description = "An expression of apology.", descriptionVietnamese = "Biểu hiện sự xin lỗi."),
            Word(lessonId = 1, english = "Help", vietnamese = "Giúp đỡ", pronunciation = "hɛlp", description = "A request for assistance.", descriptionVietnamese = "Yêu cầu sự giúp đỡ."),
            Word(lessonId = 1, english = "Friend", vietnamese = "Bạn bè", pronunciation = "frɛnd", description = "A person whom one knows and has a bond with.", descriptionVietnamese = "Một người quen biết và có mối quan hệ thân thiết."),

            // Numbers & Colors
            Word(lessonId = 2, english = "One", vietnamese = "Một", pronunciation = "wʌn", description = "The number 1.", descriptionVietnamese = "Số 1."),
            Word(lessonId = 2, english = "Two", vietnamese = "Hai", pronunciation = "tuː", description = "The number 2.", descriptionVietnamese = "Số 2."),
            Word(lessonId = 2, english = "Three", vietnamese = "Ba", pronunciation = "θriː", description = "The number 3.", descriptionVietnamese = "Số 3."),
            Word(lessonId = 2, english = "Four", vietnamese = "Bốn", pronunciation = "fɔːr", description = "The number 4.", descriptionVietnamese = "Số 4."),
            Word(lessonId = 2, english = "Five", vietnamese = "Năm", pronunciation = "faɪv", description = "The number 5.", descriptionVietnamese = "Số 5."),
            Word(lessonId = 2, english = "Red", vietnamese = "Màu đỏ", pronunciation = "rɛd", description = "A primary color often associated with passion.", descriptionVietnamese = "Màu chính thường gắn với sự đam mê."),
            Word(lessonId = 2, english = "Blue", vietnamese = "Màu xanh dương", pronunciation = "bluː", description = "A primary color often associated with calmness.", descriptionVietnamese = "Màu chính thường gắn với sự bình tĩnh."),
            Word(lessonId = 2, english = "Green", vietnamese = "Màu xanh lá", pronunciation = "ɡriːn", description = "A color often associated with nature.", descriptionVietnamese = "Màu thường liên quan đến thiên nhiên."),
            Word(lessonId = 2, english = "Yellow", vietnamese = "Màu vàng", pronunciation = "ˈjɛloʊ", description = "A bright color often associated with happiness.", descriptionVietnamese = "Màu sáng thường liên quan đến niềm vui."),
            Word(lessonId = 2, english = "Black", vietnamese = "Màu đen", pronunciation = "blæk", description = "A color often associated with mystery.", descriptionVietnamese = "Màu thường liên quan đến sự bí ẩn."),

            // Basic Phrases
            Word(lessonId = 3, english = "How are you?", vietnamese = "Bạn khỏe không?", pronunciation = "haʊ ɑr ju?", description = "A common greeting to ask about someone's well-being.", descriptionVietnamese = "Câu chào hỏi sức khỏe phổ biến."),
            Word(lessonId = 3, english = "What’s your name?", vietnamese = "Bạn tên gì?", pronunciation = "wɑːts jɔːr neɪm?", description = "A question to ask for someone's name.", descriptionVietnamese = "Câu hỏi để biết tên ai đó."),
            Word(lessonId = 3, english = "Where are you from?", vietnamese = "Bạn đến từ đâu?", pronunciation = "wɛr ɑr ju frʌm?", description = "A question to ask about someone's origin.", descriptionVietnamese = "Câu hỏi về quê quán của ai đó."),
            Word(lessonId = 3, english = "I don’t understand", vietnamese = "Tôi không hiểu", pronunciation = "aɪ doʊnt ˌʌndərˈstænd", description = "A phrase used when something is unclear.", descriptionVietnamese = "Câu nói khi không hiểu điều gì đó."),
            Word(lessonId = 3, english = "Can you help me?", vietnamese = "Bạn có thể giúp tôi không?", pronunciation = "kæn ju hɛlp mi?", description = "A request for assistance.", descriptionVietnamese = "Lời yêu cầu sự giúp đỡ."),
            Word(lessonId = 3, english = "Good morning", vietnamese = "Chào buổi sáng", pronunciation = "ɡʊd ˈmɔrnɪŋ", description = "A greeting used in the morning.", descriptionVietnamese = "Lời chào vào buổi sáng."),
            Word(lessonId = 3, english = "Good night", vietnamese = "Chúc ngủ ngon", pronunciation = "ɡʊd naɪt", description = "A phrase used before going to sleep.", descriptionVietnamese = "Câu chúc trước khi đi ngủ."),
            Word(lessonId = 3, english = "I am lost", vietnamese = "Tôi bị lạc", pronunciation = "aɪ æm lɔst", description = "A phrase used to indicate that someone doesn't know where they are.", descriptionVietnamese = "Câu nói khi ai đó không biết mình đang ở đâu."),
            Word(lessonId = 3, english = "I am sorry", vietnamese = "Tôi xin lỗi", pronunciation = "aɪ æm ˈsɑːri", description = "A phrase used to apologize for something.", descriptionVietnamese = "Câu nói để xin lỗi vì một điều gì đó."),
            Word(lessonId = 3, english = "Good morning", vietnamese = "Chào buổi sáng", pronunciation = "ɡʊd ˈmɔrnɪŋ", description = "A greeting used in the morning.", descriptionVietnamese = "Lời chào dùng vào buổi sáng."),
            Word(lessonId = 3, english = "See you later", vietnamese = "Hẹn gặp lại", pronunciation = "si ju ˈleɪtər", description = "A phrase used to say goodbye with the expectation of meeting again.", descriptionVietnamese = "Câu nói khi tạm biệt với mong đợi sẽ gặp lại."),

            // At the Airport
            Word(lessonId = 4, english = "Airport", vietnamese = "Sân bay", pronunciation = "ˈɛrˌpɔrt", description = "A place where aircraft regularly take off and land, with buildings for passengers.", descriptionVietnamese = "Nơi máy bay cất cánh và hạ cánh thường xuyên, có các tòa nhà phục vụ hành khách."),
            Word(lessonId = 4, english = "Flight", vietnamese = "Chuyến bay", pronunciation = "flaɪt", description = "A journey made by an aircraft, especially a commercial one.", descriptionVietnamese = "Một hành trình bằng máy bay, đặc biệt là chuyến bay thương mại."),
            Word(lessonId = 4, english = "Customs", vietnamese = "Hải quan", pronunciation = "ˈkʌstəmz", description = "The place at an airport where officials check incoming goods, travelers, or luggage.", descriptionVietnamese = "Nơi ở sân bay nơi nhân viên kiểm tra hàng hóa, hành khách hoặc hành lý đến."),
            Word(lessonId = 4, english = "Layover", vietnamese = "Quá cảnh", pronunciation = "ˈleɪoʊvər", description = "A short stay at a place in between parts of a journey.", descriptionVietnamese = "Khoảng thời gian dừng chân ngắn giữa các chặng của một chuyến đi."),
            Word(lessonId = 4, english = "Passport", vietnamese = "Hộ chiếu", pronunciation = "ˈpæspɔrt", description = "An official document issued by a government, certifying the holder's identity and citizenship.", descriptionVietnamese = "Giấy tờ chính thức do chính phủ cấp, xác nhận danh tính và quốc tịch của người sở hữu."),
            Word(lessonId = 4, english = "Visa", vietnamese = "Thị thực", pronunciation = "ˈviːzə", description = "An endorsement on a passport indicating that the holder is allowed to enter, leave, or stay for a specified period of time in a country.", descriptionVietnamese = "Dấu xác nhận trên hộ chiếu cho phép người sở hữu nhập cảnh, xuất cảnh hoặc lưu trú trong một khoảng thời gian nhất định."),
            Word(lessonId = 4, english = "Boarding", vietnamese = "Lên máy bay", pronunciation = "ˈbɔːrdɪŋ", description = "The process of getting onto a plane before departure.", descriptionVietnamese = "Quá trình lên máy bay trước khi khởi hành."),
            Word(lessonId = 4, english = "Check-in", vietnamese = "Làm thủ tục", pronunciation = "ʧɛk ɪn", description = "The process of confirming your presence for a flight and receiving a boarding pass.", descriptionVietnamese = "Quá trình xác nhận sự có mặt của bạn cho chuyến bay và nhận thẻ lên máy bay."),
            Word(lessonId = 4, english = "Luggage", vietnamese = "Hành lý", pronunciation = "ˈlʌɡɪʤ", description = "Bags and suitcases used for traveling.", descriptionVietnamese = "Túi xách và vali dùng cho việc đi du lịch."),
            Word(lessonId = 4, english = "Security check", vietnamese = "Kiểm tra an ninh", pronunciation = "sɪˈkjʊrəti ʧɛk", description = "A checkpoint where passengers and luggage are screened for security threats.", descriptionVietnamese = "Một điểm kiểm tra nơi hành khách và hành lý được kiểm tra an ninh."),
            Word(lessonId = 4, english = "Boarding pass", vietnamese = "Thẻ lên máy bay", pronunciation = "ˈbɔːrdɪŋ pæs", description = "A document that allows a passenger to enter the restricted area of an airport and board the aircraft.", descriptionVietnamese = "Giấy tờ cho phép hành khách vào khu vực hạn chế của sân bay và lên máy bay."),
            Word(lessonId = 4, english = "Baggage claim", vietnamese = "Khu nhận hành lý", pronunciation = "ˈbæɡɪdʒ kleɪm", description = "The area in an airport where arriving passengers collect their checked luggage.", descriptionVietnamese = "Khu vực sân bay nơi hành khách đến nhận hành lý ký gửi."),
            Word(lessonId = 4, english = "Departure gate", vietnamese = "Cửa khởi hành", pronunciation = "dɪˈpɑːrtʃər ɡeɪt", description = "The area where passengers wait before boarding the plane.", descriptionVietnamese = "Khu vực nơi hành khách chờ trước khi lên máy bay."),
            Word(lessonId = 4, english = "International terminal", vietnamese = "Nhà ga quốc tế", pronunciation = "ˌɪntərˈnæʃənəl ˈtɜrmɪnəl", description = "A part of an airport that handles flights traveling to and from other countries.", descriptionVietnamese = "Một phần của sân bay phục vụ các chuyến bay quốc tế."),
            Word(lessonId = 4, english = "Delayed flight", vietnamese = "Chuyến bay bị hoãn", pronunciation = "dɪˈleɪd flaɪt", description = "A flight that is postponed and does not leave on time.", descriptionVietnamese = "Một chuyến bay bị hoãn và không khởi hành đúng giờ."),

            // Hotel Conversations
            Word(lessonId = 5, english = "Hotel", vietnamese = "Khách sạn", pronunciation = "hoʊˈtɛl", description = "A place where people stay temporarily when traveling.", descriptionVietnamese = "Nơi mọi người ở tạm thời khi đi du lịch."),
            Word(lessonId = 5, english = "Lobby", vietnamese = "Sảnh khách sạn", pronunciation = "ˈlɑːbi", description = "The entrance hall of a hotel where guests check in.", descriptionVietnamese = "Khu vực sảnh của khách sạn nơi khách làm thủ tục nhận phòng."),
            Word(lessonId = 5, english = "Key", vietnamese = "Chìa khóa", pronunciation = "kiː", description = "A device used to open and lock a door in a hotel room.", descriptionVietnamese = "Một dụng cụ dùng để mở và khóa cửa phòng khách sạn."),
            Word(lessonId = 5, english = "Guest", vietnamese = "Khách lưu trú", pronunciation = "ɡɛst", description = "A person who stays in a hotel.", descriptionVietnamese = "Người lưu trú tại khách sạn."),
            Word(lessonId = 5, english = "Reservation", vietnamese = "Đặt chỗ trước", pronunciation = "ˌrɛzərˈveɪʃən", description = "An arrangement to have a room, table, or seat held for you at a hotel, restaurant, etc.", descriptionVietnamese = "Sự sắp xếp để có phòng, bàn hoặc ghế được giữ cho bạn tại khách sạn, nhà hàng, v.v."),
            Word(lessonId = 5, english = "Housekeeping", vietnamese = "Dọn phòng", pronunciation = "ˈhaʊskiːpɪŋ", description = "The department responsible for cleaning rooms and public areas in a hotel.", descriptionVietnamese = "Bộ phận chịu trách nhiệm dọn dẹp phòng và khu vực công cộng trong khách sạn."),
            Word(lessonId = 5, english = "Check-in", vietnamese = "Làm thủ tục nhận phòng", pronunciation = "ʧɛk ɪn", description = "The process of registering upon arrival at a hotel.", descriptionVietnamese = "Quá trình đăng ký khi đến khách sạn."),
            Word(lessonId = 5, english = "Check-out", vietnamese = "Trả phòng", pronunciation = "ʧɛk aʊt", description = "The process of leaving a hotel and paying the bill.", descriptionVietnamese = "Quá trình rời khách sạn và thanh toán hóa đơn."),
            Word(lessonId = 5, english = "Room service", vietnamese = "Dịch vụ phòng", pronunciation = "ruːm ˈsɜːrvɪs", description = "A service in a hotel where food and drinks are delivered to a guest’s room.", descriptionVietnamese = "Dịch vụ trong khách sạn cung cấp thức ăn và đồ uống đến phòng khách."),
            Word(lessonId = 5, english = "Mini-bar", vietnamese = "Tủ lạnh mini", pronunciation = "ˈmɪni bɑːr", description = "A small fridge in a hotel room stocked with drinks and snacks.", descriptionVietnamese = "Một tủ lạnh nhỏ trong phòng khách sạn chứa đồ uống và đồ ăn nhẹ."),
            Word(lessonId = 5, english = "Wake-up call", vietnamese = "Cuộc gọi báo thức", pronunciation = "ˈweɪk ʌp kɔːl", description = "A service in a hotel where staff calls guests at a requested time to wake them up.", descriptionVietnamese = "Dịch vụ trong khách sạn nơi nhân viên gọi khách vào thời gian yêu cầu để đánh thức họ."),
            Word(lessonId = 5, english = "Late check-out", vietnamese = "Trả phòng muộn", pronunciation = "leɪt ʧɛk aʊt", description = "An arrangement that allows a guest to stay beyond the usual check-out time.", descriptionVietnamese = "Sự sắp xếp cho phép khách ở lại quá giờ trả phòng thông thường."),

            // Ordering Food
            Word(lessonId = 6, english = "Menu", vietnamese = "Thực đơn", pronunciation = "ˈmɛnjuː", description = "A list of dishes available at a restaurant.", descriptionVietnamese = "Danh sách các món ăn có sẵn tại nhà hàng."),
            Word(lessonId = 6, english = "Waiter", vietnamese = "Người phục vụ nam", pronunciation = "ˈweɪtər", description = "A man who serves food and drinks in a restaurant.", descriptionVietnamese = "Người đàn ông phục vụ đồ ăn và đồ uống trong nhà hàng."),
            Word(lessonId = 6, english = "Waitress", vietnamese = "Người phục vụ nữ", pronunciation = "ˈweɪtrɪs", description = "A woman who serves food and drinks in a restaurant.", descriptionVietnamese = "Người phụ nữ phục vụ đồ ăn và đồ uống trong nhà hàng."),
            Word(lessonId = 6, english = "Chef", vietnamese = "Bếp trưởng", pronunciation = "ʃɛf", description = "A professional cook, especially the head cook in a restaurant.", descriptionVietnamese = "Đầu bếp chuyên nghiệp, đặc biệt là bếp trưởng trong một nhà hàng."),
            Word(lessonId = 6, english = "Bill", vietnamese = "Hóa đơn", pronunciation = "bɪl", description = "A statement of the money owed for goods or services.", descriptionVietnamese = "Bảng kê số tiền phải trả cho hàng hóa hoặc dịch vụ."),
            Word(lessonId = 6, english = "Tip", vietnamese = "Tiền boa", pronunciation = "tɪp", description = "A small amount of money given to service workers for good service.", descriptionVietnamese = "Một khoản tiền nhỏ được tặng cho nhân viên phục vụ để cảm ơn."),
            Word(lessonId = 6, english = "Appetizer", vietnamese = "Món khai vị", pronunciation = "ˈæpɪˌtaɪzər", description = "A small dish served before the main course.", descriptionVietnamese = "Một món ăn nhẹ được phục vụ trước món chính."),
            Word(lessonId = 6, english = "Side dish", vietnamese = "Món ăn kèm", pronunciation = "saɪd dɪʃ", description = "A small portion of food served with the main course.", descriptionVietnamese = "Một phần nhỏ của thức ăn được phục vụ kèm theo món chính."),
            Word(lessonId = 6, english = "Main course", vietnamese = "Món chính", pronunciation = "meɪn kɔːrs", description = "The main part of a meal.", descriptionVietnamese = "Phần chính của bữa ăn."),
            Word(lessonId = 6, english = "Dessert", vietnamese = "Tráng miệng", pronunciation = "dɪˈzɜːrt", description = "A sweet course typically served at the end of a meal.", descriptionVietnamese = "Một món ngọt thường được phục vụ vào cuối bữa ăn."),
            Word(lessonId = 6, english = "Takeaway", vietnamese = "Món mang đi", pronunciation = "ˈteɪkəˌweɪ", description = "Food prepared in a restaurant and eaten elsewhere.", descriptionVietnamese = "Thức ăn được chuẩn bị trong nhà hàng nhưng được mang đi ăn ở nơi khác."),
            Word(lessonId = 6, english = "Special of the day", vietnamese = "Món đặc biệt trong ngày", pronunciation = "ˈspɛʃəl ʌv ðə deɪ", description = "A unique dish prepared only for a particular day.", descriptionVietnamese = "Một món ăn đặc biệt chỉ được chế biến cho một ngày cụ thể."),

            // Office Communication
            Word(lessonId = 7, english = "Email", vietnamese = "Thư điện tử", pronunciation = "ˈiːmeɪl", description = "A method of sending messages electronically between computers.", descriptionVietnamese = "Một phương thức gửi tin nhắn điện tử giữa các máy tính."),
            Word(lessonId = 7, english = "Meeting", vietnamese = "Cuộc họp", pronunciation = "ˈmiːtɪŋ", description = "A gathering of people for discussion or decision-making.", descriptionVietnamese = "Một cuộc tụ họp của mọi người để thảo luận hoặc đưa ra quyết định."),
            Word(lessonId = 7, english = "Deadline", vietnamese = "Hạn chót", pronunciation = "ˈdɛdˌlaɪn", description = "The latest time or date by which something should be completed.", descriptionVietnamese = "Thời hạn cuối cùng mà một công việc cần phải hoàn thành."),
            Word(lessonId = 7, english = "Agenda", vietnamese = "Chương trình họp", pronunciation = "əˈdʒɛndə", description = "A list of topics to be discussed in a meeting.", descriptionVietnamese = "Danh sách các chủ đề cần thảo luận trong một cuộc họp."),
            Word(lessonId = 7, english = "Minutes", vietnamese = "Biên bản cuộc họp", pronunciation = "ˈmɪnɪts", description = "A written record of everything that was discussed in a meeting.", descriptionVietnamese = "Bản ghi chép nội dung cuộc họp."),
            Word(lessonId = 7, english = "Conference call", vietnamese = "Cuộc gọi hội nghị", pronunciation = "ˈkɒnfərəns kɔːl", description = "A phone call where multiple participants are involved.", descriptionVietnamese = "Một cuộc gọi điện thoại với nhiều người tham gia."),
            Word(lessonId = 7, english = "Brainstorming session", vietnamese = "Buổi động não", pronunciation = "ˈbreɪnstɔːrmɪŋ ˈsɛʃən", description = "A meeting where people come up with new ideas and solutions.", descriptionVietnamese = "Một cuộc họp mà mọi người đưa ra các ý tưởng và giải pháp mới."),
            Word(lessonId = 7, english = "Presentation", vietnamese = "Bài thuyết trình", pronunciation = "ˌprɛzənˈteɪʃən", description = "A talk or speech given to share information with an audience.", descriptionVietnamese = "Một bài nói hoặc bài diễn thuyết để chia sẻ thông tin với khán giả."),
            Word(lessonId = 7, english = "Follow-up", vietnamese = "Theo dõi sau cuộc họp", pronunciation = "ˈfɒloʊ ˌʌp", description = "An action or communication that continues a previous discussion.", descriptionVietnamese = "Một hành động hoặc cuộc trao đổi tiếp nối một cuộc thảo luận trước đó."),
            Word(lessonId = 7, english = "Memo", vietnamese = "Bản ghi nhớ", pronunciation = "ˈmɛmoʊ", description = "A written message sent within an organization.", descriptionVietnamese = "Một thông báo bằng văn bản được gửi trong tổ chức."),

            // Email Writing
            Word(lessonId = 8, english = "Subject line", vietnamese = "Dòng tiêu đề", pronunciation = "ˈsʌbdʒɛkt laɪn", description = "The title of an email that summarizes its content.", descriptionVietnamese = "Tiêu đề của email, tóm tắt nội dung chính."),
            Word(lessonId = 8, english = "Greeting", vietnamese = "Lời chào", pronunciation = "ˈɡriːtɪŋ", description = "A polite way to start an email, such as 'Dear Sir/Madam'.", descriptionVietnamese = "Một cách lịch sự để bắt đầu email, ví dụ: 'Kính gửi...'"),
            Word(lessonId = 8, english = "Salutation", vietnamese = "Cách mở đầu", pronunciation = "ˌsæljʊˈteɪʃən", description = "A formal way to address the recipient at the beginning of an email.", descriptionVietnamese = "Cách gọi trang trọng khi bắt đầu email."),
            Word(lessonId = 8, english = "Body", vietnamese = "Nội dung chính", pronunciation = "ˈbɒdi", description = "The main text of an email containing the message.", descriptionVietnamese = "Phần nội dung chính của email chứa thông điệp cần truyền tải."),
            Word(lessonId = 8, english = "Closing", vietnamese = "Lời kết", pronunciation = "ˈkloʊzɪŋ", description = "A polite way to end an email, such as 'Best regards'.", descriptionVietnamese = "Cách kết thúc email lịch sự, ví dụ: 'Trân trọng'."),
            Word(lessonId = 8, english = "Signature", vietnamese = "Chữ ký", pronunciation = "ˈsɪɡnətʃər", description = "A block of text at the end of an email that includes the sender’s name and contact details.", descriptionVietnamese = "Phần cuối email bao gồm tên người gửi và thông tin liên hệ."),
            Word(lessonId = 8, english = "Attachment", vietnamese = "Tệp đính kèm", pronunciation = "əˈtætʃmənt", description = "A file sent along with an email.", descriptionVietnamese = "Tệp tin được gửi kèm theo email."),
            Word(lessonId = 8, english = "CC (Carbon Copy)", vietnamese = "Gửi bản sao", pronunciation = "ˌsiː ˈsiː", description = "A feature that sends a copy of the email to additional recipients.", descriptionVietnamese = "Chức năng gửi bản sao email cho nhiều người nhận."),
            Word(lessonId = 8, english = "BCC (Blind Carbon Copy)", vietnamese = "Gửi bản sao ẩn", pronunciation = "ˌbiː ˈsiː ˈsiː", description = "A feature that sends a copy of the email without revealing the recipients.", descriptionVietnamese = "Chức năng gửi bản sao email mà không hiển thị người nhận."),
            Word(lessonId = 8, english = "Reply all", vietnamese = "Trả lời tất cả", pronunciation = "rɪˈplaɪ ɔːl", description = "A function that allows a response to be sent to all recipients of the email.", descriptionVietnamese = "Tính năng cho phép gửi phản hồi đến tất cả người nhận trong email."),

            // Job Interviews
            Word(lessonId = 9, english = "Resume", vietnamese = "Sơ yếu lý lịch", pronunciation = "ˈrɛzjʊmeɪ", description = "A document summarizing a person's work experience, skills, and education.", descriptionVietnamese = "Tài liệu tóm tắt kinh nghiệm làm việc, kỹ năng và học vấn của một người."),
            Word(lessonId = 9, english = "Cover letter", vietnamese = "Thư xin việc", pronunciation = "ˈkʌvər ˈlɛtər", description = "A letter sent with a resume to introduce the applicant and highlight qualifications.", descriptionVietnamese = "Lá thư được gửi kèm sơ yếu lý lịch để giới thiệu ứng viên và nhấn mạnh năng lực."),
            Word(lessonId = 9, english = "Job description", vietnamese = "Mô tả công việc", pronunciation = "ʤɒb dɪˈskrɪpʃən", description = "A written statement outlining the responsibilities and requirements of a job.", descriptionVietnamese = "Bản mô tả bằng văn bản về trách nhiệm và yêu cầu của một công việc."),
            Word(lessonId = 9, english = "Interview", vietnamese = "Buổi phỏng vấn", pronunciation = "ˈɪntərvjuː", description = "A formal meeting where an employer asks a candidate questions about a job.", descriptionVietnamese = "Cuộc gặp chính thức nơi nhà tuyển dụng hỏi ứng viên về công việc."),
            Word(lessonId = 9, english = "Candidate", vietnamese = "Ứng viên", pronunciation = "ˈkændɪdət", description = "A person who applies for a job position.", descriptionVietnamese = "Người nộp đơn xin việc."),
            Word(lessonId = 9, english = "Recruiter", vietnamese = "Nhà tuyển dụng", pronunciation = "rɪˈkruːtər", description = "A person responsible for finding and hiring employees.", descriptionVietnamese = "Người chịu trách nhiệm tìm kiếm và tuyển dụng nhân viên."),
            Word(lessonId = 9, english = "Salary negotiation", vietnamese = "Thương lượng lương", pronunciation = "ˈsæləri nɪˌɡoʊʃiˈeɪʃən", description = "The process of discussing and agreeing on a salary.", descriptionVietnamese = "Quá trình thảo luận và đạt được thỏa thuận về mức lương."),
            Word(lessonId = 9, english = "Strengths and weaknesses", vietnamese = "Điểm mạnh và điểm yếu", pronunciation = "strɛŋkθs ænd ˈwiːknəsɪz", description = "Personal qualities that help or hinder job performance.", descriptionVietnamese = "Những phẩm chất cá nhân giúp ích hoặc cản trở hiệu suất công việc."),
            Word(lessonId = 9, english = "Dress code", vietnamese = "Quy tắc trang phục", pronunciation = "drɛs koʊd", description = "A set of rules about what kind of clothes employees should wear.", descriptionVietnamese = "Bộ quy tắc về trang phục nhân viên nên mặc."),
            Word(lessonId = 9, english = "Follow-up email", vietnamese = "Email theo dõi", pronunciation = "ˈfɑːloʊ ʌp ˈiːmeɪl", description = "A message sent after an interview to thank the interviewer and express interest.", descriptionVietnamese = "Email được gửi sau phỏng vấn để cảm ơn người phỏng vấn và thể hiện sự quan tâm."),

            // Shopping Dialogues
            Word(lessonId = 10, english = "Price", vietnamese = "Giá cả", pronunciation = "praɪs", description = "The amount of money required to buy something.", descriptionVietnamese = "Số tiền cần trả để mua một thứ gì đó."),
            Word(lessonId = 10, english = "Discount", vietnamese = "Giảm giá", pronunciation = "ˈdɪskaʊnt", description = "A reduction in the price of an item.", descriptionVietnamese = "Sự giảm giá của một mặt hàng."),
            Word(lessonId = 10, english = "Receipt", vietnamese = "Hóa đơn", pronunciation = "rɪˈsiːt", description = "A document that proves payment has been made.", descriptionVietnamese = "Giấy tờ chứng minh đã thanh toán."),
            Word(lessonId = 10, english = "Cashier", vietnamese = "Thu ngân", pronunciation = "kæˈʃɪr", description = "A person responsible for handling payments in a store.", descriptionVietnamese = "Người chịu trách nhiệm xử lý thanh toán trong cửa hàng."),
            Word(lessonId = 10, english = "Exchange", vietnamese = "Đổi hàng", pronunciation = "ɪksˈʧeɪndʒ", description = "The process of returning an item and getting another one in return.", descriptionVietnamese = "Quá trình trả lại một món hàng và lấy món khác thay thế."),
            Word(lessonId = 10, english = "Refund", vietnamese = "Hoàn tiền", pronunciation = "ˈriːfʌnd", description = "Money returned to a customer when they return an item.", descriptionVietnamese = "Số tiền được trả lại khi khách hàng trả hàng."),
            Word(lessonId = 10, english = "Fitting room", vietnamese = "Phòng thử đồ", pronunciation = "ˈfɪtɪŋ ruːm", description = "A small room where customers try on clothes before buying.", descriptionVietnamese = "Phòng nhỏ nơi khách thử quần áo trước khi mua."),
            Word(lessonId = 10, english = "Shopping cart", vietnamese = "Giỏ hàng", pronunciation = "ˈʃɒpɪŋ kɑːrt", description = "A wheeled cart used to carry items while shopping.", descriptionVietnamese = "Xe đẩy có bánh để mang hàng hóa khi mua sắm."),
            Word(lessonId = 10, english = "Buy one get one free", vietnamese = "Mua một tặng một", pronunciation = "baɪ wʌn ɡɛt wʌn friː", description = "A special offer where customers get an extra item for free.", descriptionVietnamese = "Ưu đãi đặc biệt khi khách hàng mua một món sẽ được tặng thêm một món."),
            Word(lessonId = 10, english = "Out of stock", vietnamese = "Hết hàng", pronunciation = "aʊt ʌv stɒk", description = "When a store does not have a certain product available.", descriptionVietnamese = "Khi cửa hàng không còn sản phẩm đó để bán."),

            // Making Appointments
            Word(lessonId = 11, english = "Schedule", vietnamese = "Lịch trình", pronunciation = "ˈskɛdʒuːl", description = "A plan that lists when certain events will happen.", descriptionVietnamese = "Một kế hoạch liệt kê thời gian diễn ra các sự kiện."),
            Word(lessonId = 11, english = "Availability", vietnamese = "Tình trạng rảnh", pronunciation = "əˌveɪləˈbɪləti", description = "The time when someone is free to meet or do something.", descriptionVietnamese = "Khoảng thời gian ai đó rảnh để gặp hoặc làm gì đó."),
            Word(lessonId = 11, english = "Reschedule", vietnamese = "Dời lịch", pronunciation = "ˌriːˈskɛdʒuːl", description = "To change the time of an appointment to a later date.", descriptionVietnamese = "Thay đổi thời gian của một cuộc hẹn sang ngày khác."),
            Word(lessonId = 11, english = "Cancel", vietnamese = "Hủy lịch hẹn", pronunciation = "ˈkænsəl", description = "To call off a scheduled appointment.", descriptionVietnamese = "Hủy bỏ một cuộc hẹn đã lên lịch."),
            Word(lessonId = 11, english = "Confirmation", vietnamese = "Xác nhận", pronunciation = "ˌkɒnfɪˈmeɪʃən", description = "A statement that something has been arranged or agreed upon.", descriptionVietnamese = "Một thông báo xác nhận rằng điều gì đó đã được sắp xếp."),
            Word(lessonId = 11, english = "Reminder", vietnamese = "Lời nhắc", pronunciation = "rɪˈmaɪndər", description = "A notification or message to help someone remember an appointment.", descriptionVietnamese = "Thông báo hoặc tin nhắn nhắc nhở ai đó về cuộc hẹn."),
            Word(lessonId = 11, english = "Appointment slot", vietnamese = "Khung giờ hẹn", pronunciation = "əˈpɔɪntmənt slɒt", description = "A specific time set aside for an appointment.", descriptionVietnamese = "Một khoảng thời gian cụ thể được dành cho một cuộc hẹn."),
            Word(lessonId = 11, english = "No-show", vietnamese = "Không đến hẹn", pronunciation = "noʊ ʃoʊ", description = "A person who does not arrive for an appointment without notice.", descriptionVietnamese = "Người không xuất hiện tại cuộc hẹn mà không thông báo trước."),
            Word(lessonId = 11, english = "Walk-in", vietnamese = "Khách không hẹn trước", pronunciation = "wɔːk ɪn", description = "A person who arrives without an appointment.", descriptionVietnamese = "Người đến mà không đặt lịch trước."),
            Word(lessonId = 11, english = "Fully booked", vietnamese = "Kín lịch", pronunciation = "ˈfʊli bʊkt", description = "When all available appointment slots are taken.", descriptionVietnamese = "Khi tất cả khung giờ hẹn đã được đặt."),

            // Small Talk
            Word(lessonId = 12, english = "Greeting", vietnamese = "Lời chào", pronunciation = "ˈɡriːtɪŋ", description = "A polite way to start a conversation.", descriptionVietnamese = "Cách lịch sự để bắt đầu một cuộc trò chuyện."),
            Word(lessonId = 12, english = "Weather", vietnamese = "Thời tiết", pronunciation = "ˈwɛðər", description = "A common topic for casual conversation.", descriptionVietnamese = "Chủ đề phổ biến trong các cuộc trò chuyện ngẫu nhiên."),
            Word(lessonId = 12, english = "Compliment", vietnamese = "Lời khen", pronunciation = "ˈkɑːmplɪmənt", description = "A polite expression of praise or admiration.", descriptionVietnamese = "Cách bày tỏ sự khen ngợi hoặc ngưỡng mộ."),
            Word(lessonId = 12, english = "Icebreaker", vietnamese = "Câu mở đầu", pronunciation = "ˈaɪsˌbreɪkər", description = "A statement or question that starts a conversation easily.", descriptionVietnamese = "Câu hỏi hoặc câu nói giúp bắt đầu trò chuyện một cách tự nhiên."),
            Word(lessonId = 12, english = "Hobby", vietnamese = "Sở thích", pronunciation = "ˈhɑːbi", description = "An activity someone enjoys doing in their free time.", descriptionVietnamese = "Hoạt động mà ai đó yêu thích làm trong thời gian rảnh."),
            Word(lessonId = 12, english = "Current events", vietnamese = "Tin tức hiện tại", pronunciation = "ˈkʌrənt ɪˈvɛnts", description = "Recent news or happenings that people talk about.", descriptionVietnamese = "Tin tức mới hoặc sự kiện mà mọi người thường thảo luận."),
            Word(lessonId = 12, english = "Body language", vietnamese = "Ngôn ngữ cơ thể", pronunciation = "ˈbɒdi ˈlæŋɡwɪdʒ", description = "Non-verbal signals that convey meaning in communication.", descriptionVietnamese = "Các tín hiệu không lời truyền đạt ý nghĩa trong giao tiếp."),
            Word(lessonId = 12, english = "Chit-chat", vietnamese = "Trò chuyện phiếm", pronunciation = "ʧɪt-ʧæt", description = "Casual and light-hearted conversation.", descriptionVietnamese = "Cuộc trò chuyện nhẹ nhàng, không quá sâu sắc."),
            Word(lessonId = 12, english = "Awkward silence", vietnamese = "Khoảnh khắc im lặng gượng gạo", pronunciation = "ˈɔːkwərd ˈsaɪləns", description = "A moment of uncomfortable quiet in a conversation.", descriptionVietnamese = "Khoảnh khắc im lặng không thoải mái trong cuộc trò chuyện."),
            Word(lessonId = 12, english = "Farewell", vietnamese = "Lời tạm biệt", pronunciation = "ˌfɛrˈwɛl", description = "A polite way to end a conversation or say goodbye.", descriptionVietnamese = "Cách lịch sự để kết thúc cuộc trò chuyện hoặc nói lời tạm biệt."),

            // Animal Names
            Word(lessonId = 13, english = "Dog", vietnamese = "Chó", pronunciation = "dɔːɡ", description = "A domesticated carnivorous mammal known for loyalty.", descriptionVietnamese = "Một loài động vật ăn thịt được thuần hóa, nổi tiếng vì sự trung thành."),
            Word(lessonId = 13, english = "Cat", vietnamese = "Mèo", pronunciation = "kæt", description = "A small domesticated carnivorous mammal with soft fur.", descriptionVietnamese = "Một loài động vật ăn thịt nhỏ đã được thuần hóa với bộ lông mềm."),
            Word(lessonId = 13, english = "Elephant", vietnamese = "Voi", pronunciation = "ˈɛləfənt", description = "The largest land animal with a long trunk.", descriptionVietnamese = "Loài động vật trên cạn lớn nhất với chiếc vòi dài."),
            Word(lessonId = 13, english = "Tiger", vietnamese = "Hổ", pronunciation = "ˈtaɪɡər", description = "A large wild cat with orange fur and black stripes.", descriptionVietnamese = "Một loài mèo lớn có bộ lông màu cam và sọc đen."),
            Word(lessonId = 13, english = "Dolphin", vietnamese = "Cá heo", pronunciation = "ˈdɒlfɪn", description = "A highly intelligent marine mammal known for playfulness.", descriptionVietnamese = "Một loài động vật biển thông minh, nổi tiếng vì sự tinh nghịch."),
            Word(lessonId = 13, english = "Kangaroo", vietnamese = "Chuột túi", pronunciation = "ˌkæŋɡəˈruː", description = "A marsupial from Australia that moves by hopping.", descriptionVietnamese = "Một loài thú có túi ở Úc di chuyển bằng cách nhảy."),
            Word(lessonId = 13, english = "Giraffe", vietnamese = "Hươu cao cổ", pronunciation = "dʒɪˈræf", description = "The tallest land animal with a long neck.", descriptionVietnamese = "Loài động vật trên cạn cao nhất với chiếc cổ dài."),
            Word(lessonId = 13, english = "Penguin", vietnamese = "Chim cánh cụt", pronunciation = "ˈpɛŋɡwɪn", description = "A flightless bird that lives in cold climates.", descriptionVietnamese = "Một loài chim không bay sống ở vùng khí hậu lạnh."),
            Word(lessonId = 13, english = "Eagle", vietnamese = "Đại bàng", pronunciation = "ˈiːɡl", description = "A large bird of prey known for sharp eyesight.", descriptionVietnamese = "Một loài chim săn mồi lớn nổi tiếng với thị lực sắc bén."),
            Word(lessonId = 13, english = "Crocodile", vietnamese = "Cá sấu", pronunciation = "ˈkrɒkədaɪl", description = "A large aquatic reptile with a powerful jaw.", descriptionVietnamese = "Một loài bò sát lớn sống dưới nước với hàm răng mạnh mẽ."),

            // Fun with Rhymes
            Word(lessonId = 14, english = "Cat", vietnamese = "Mèo", pronunciation = "kæt", description = "A small pet that loves to nap.", descriptionVietnamese = "Một con vật nhỏ thích ngủ trưa."),
            Word(lessonId = 14, english = "Hat", vietnamese = "Mũ", pronunciation = "hæt", description = "Something you wear upon your head.", descriptionVietnamese = "Thứ bạn đội trên đầu."),
            Word(lessonId = 14, english = "Sun", vietnamese = "Mặt trời", pronunciation = "sʌn", description = "Shining brightly in the sky.", descriptionVietnamese = "Tỏa sáng rực rỡ trên bầu trời."),
            Word(lessonId = 14, english = "Run", vietnamese = "Chạy", pronunciation = "rʌn", description = "Moving quickly on your feet.", descriptionVietnamese = "Di chuyển nhanh bằng đôi chân."),
            Word(lessonId = 14, english = "Bee", vietnamese = "Ong", pronunciation = "biː", description = "Buzzing round from tree to tree.", descriptionVietnamese = "Bay vo ve từ cây này sang cây khác."),
            Word(lessonId = 14, english = "See", vietnamese = "Nhìn", pronunciation = "siː", description = "Using your eyes to look around.", descriptionVietnamese = "Dùng mắt để quan sát xung quanh."),
            Word(lessonId = 14, english = "Star", vietnamese = "Ngôi sao", pronunciation = "stɑːr", description = "Twinkling brightly in the night.", descriptionVietnamese = "Lấp lánh trong đêm tối."),
            Word(lessonId = 14, english = "Car", vietnamese = "Xe hơi", pronunciation = "kɑːr", description = "A vehicle that goes far.", descriptionVietnamese = "Một chiếc xe đi rất xa."),
            Word(lessonId = 14, english = "Boat", vietnamese = "Thuyền", pronunciation = "boʊt", description = "Floating on water near the coast.", descriptionVietnamese = "Trôi trên mặt nước gần bờ."),
            Word(lessonId = 14, english = "Goat", vietnamese = "Dê", pronunciation = "ɡoʊt", description = "Climbing mountains high and steep.", descriptionVietnamese = "Leo lên núi cao và dốc."),

            // Storytelling
            Word(lessonId = 15, english = "Once upon a time", vietnamese = "Ngày xửa ngày xưa", pronunciation = "wʌns əˈpɒn ə taɪm", description = "A common phrase used to begin fairy tales and stories.", descriptionVietnamese = "Cụm từ thường dùng để bắt đầu truyện cổ tích."),
            Word(lessonId = 15, english = "Hero", vietnamese = "Anh hùng", pronunciation = "ˈhɪəroʊ", description = "The main character who faces challenges and overcomes them.", descriptionVietnamese = "Nhân vật chính đối mặt với thử thách và vượt qua."),
            Word(lessonId = 15, english = "Villain", vietnamese = "Nhân vật phản diện", pronunciation = "ˈvɪlən", description = "The character who opposes the hero in a story.", descriptionVietnamese = "Nhân vật đối đầu với anh hùng trong câu chuyện."),
            Word(lessonId = 15, english = "Magic", vietnamese = "Phép thuật", pronunciation = "ˈmædʒɪk", description = "A supernatural power often seen in fantasy stories.", descriptionVietnamese = "Sức mạnh siêu nhiên thường xuất hiện trong truyện kỳ ảo."),
            Word(lessonId = 15, english = "Adventure", vietnamese = "Cuộc phiêu lưu", pronunciation = "ədˈvɛntʃər", description = "An exciting journey filled with challenges and discoveries.", descriptionVietnamese = "Một hành trình thú vị với nhiều thử thách và khám phá."),
            Word(lessonId = 15, english = "Twist", vietnamese = "Tình tiết bất ngờ", pronunciation = "twɪst", description = "An unexpected change in the story that surprises the reader.", descriptionVietnamese = "Một sự thay đổi bất ngờ trong câu chuyện khiến người đọc ngạc nhiên."),
            Word(lessonId = 15, english = "Moral", vietnamese = "Bài học đạo đức", pronunciation = "ˈmɒrəl", description = "The lesson or message a story teaches.", descriptionVietnamese = "Bài học hoặc thông điệp mà câu chuyện truyền tải."),
            Word(lessonId = 15, english = "Narrator", vietnamese = "Người kể chuyện", pronunciation = "ˈnærətər", description = "The person or voice that tells the story.", descriptionVietnamese = "Người hoặc giọng kể chuyện trong câu chuyện."),
            Word(lessonId = 15, english = "Happily ever after", vietnamese = "Hạnh phúc mãi mãi về sau", pronunciation = "ˈhæpɪli ˈɛvər ˈæftər", description = "A phrase used to end fairy tales where everything turns out well.", descriptionVietnamese = "Cụm từ kết thúc truyện cổ tích khi mọi chuyện đều tốt đẹp."),
            Word(lessonId = 15, english = "Climax", vietnamese = "Cao trào", pronunciation = "ˈklaɪmæks", description = "The most exciting or intense part of a story.", descriptionVietnamese = "Phần kịch tính nhất của câu chuyện."),

            )

        words.forEach { word ->
            wordDao.insertWord(word)
        }
    }

    private fun insertSampleUserProgress() {
        val userProgressDao = UserProgressDao(context)

        val userProgresses = listOf(
            UserProgress(userId = 1, lessonId = 1, score = 100, dateTest = "2025-02-20 10:00:00", dateStart = "2025-02-20 10:00:00"),
            UserProgress(userId = 1, lessonId = 2, score = 45, dateTest = "2025-02-22 10:00:00", dateStart = "2025-02-22 10:00:00"),
            UserProgress(userId = 1, lessonId = 3, score = 60, dateTest = "2025-02-25 10:00:00", dateStart = "2025-02-25 10:00:00")
        )

        userProgresses.forEach { userProgress ->
            userProgressDao.updateProgress(userProgress)
        }
    }

    fun addUserProgress(userProgress: UserProgress){
        val userProgressDao = UserProgressDao(context)
        userProgressDao.updateProgress(userProgress)
    }

    /// get
    fun getUserCategory(): List<Category>{
        val categoryDao = CategoryDao(context)
        val categories = categoryDao.getCategories()
        return categories;
    }
    fun getUserLesson(): List<Lesson>{
        val lessonDao = LessonDao(context)
        val lessons = lessonDao.getLessonsByCategory(0)
        return lessons
    }

    fun getLessonById(lessonId: Int): Lesson?{
        val lessonDao = LessonDao(context)
        val lesson = lessonDao.getLessonById(lessonId)
        return lesson
    }

    fun getLessonDetailById(lessonId: Int, userId: Int): Lesson?{
        val lessonDao = LessonDao(context)
        val userProgressDao = UserProgressDao(context)
        val lesson: Lesson? = lessonDao.getLessonById(lessonId)
        lesson?.words = WordDao(context).getWordsByLesson(lessonId)
        lesson?.userProgress = userProgressDao.getUserProgress(userId, lessonId) ?: UserProgress(userId = userId, lessonId = lessonId)
        return lesson
    }

    fun getUserWord(): List<Word>{
        val wordDao = WordDao(context)
        val words = wordDao.getWordsByLesson(0)
        return words;
    }

    fun getUserProgress(userId: Int): List<UserProgress>{
        val userProgressDao = UserProgressDao(context)
        val userProgresses = userProgressDao.getUserProgress(userId)
        return userProgresses
    }

    fun getUserProgress(userId: Int, lessonId: Int): UserProgress?{
        val userProgressDao = UserProgressDao(context)
        val userProgress = userProgressDao.getUserProgress(userId, lessonId)
        return userProgress
    }

    fun getAllCategoriesWithLessonsAndWords(userId: Int): List<Category>{
        val categoryDao = CategoryDao(context)
        val categories = categoryDao.getAllCategoriesDetail()
        categories.forEach {
            it.lessons?.forEach { lesson ->
                lesson.userProgress = getUserProgress(userId, lesson.id)
            }
        }
        Log.e("getAllCategoriesWithLessonsAndWords", categories.toString())
        return categories
    }

}