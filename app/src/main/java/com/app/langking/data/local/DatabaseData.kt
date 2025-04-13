package com.app.langking.data.local

import com.app.langking.data.model.Category
import com.app.langking.data.model.Lesson
import com.app.langking.data.model.UserProgress
import com.app.langking.data.model.Word

object DatabaseData {
    fun getSampleCategories(): List<Category> {
        return listOf(
            Category(id = 1, name = "Basic grammar"),
            Category(id = 2, name = "Basic Vocabulary"),
            Category(id = 3, name = "English for Kids"),
            Category(id = 4, name = "Business English"),
            Category(id = 5, name = "Everyday Conversations"),
            Category(id = 6, name = "Travel & Tourism"),
            Category(id = 7, name = "Entertainment"),
            Category(id = 8, name = "Study"),
            Category(id = 9, name = "Environment & Nature"),
            Category(id = 10, name = "Sports"),

            )
    }

    fun getSampleLesson(): List<Lesson> {
        return listOf(
            // Basic grammar
            Lesson(id = 1, categoryId = 1, name = "Tenses (Verb Forms)", content = ContentLessonData.tensesLesson),
            Lesson(id = 2, categoryId = 1, name = "Conditional Sentences", content = ContentLessonData.conditionalSentencesLesson),
            Lesson(id = 3, categoryId = 1, name = "Passive Voice", content = ContentLessonData.passiveVoiceLesson),
            Lesson(id = 4, categoryId = 1, name = "Reported Speech", content = ContentLessonData.reportedSpeechLesson),
            Lesson(id = 5, categoryId = 1, name = "Relative Clauses", content = ContentLessonData.relativeClauseLesson),
            Lesson(id = 6, categoryId = 1, name = "Phrasal Verbs", content = ContentLessonData.phrasalVerbLesson),

// Basic Vocabulary
            Lesson(id = 7, categoryId = 2, name = "Common Words", content = "Learn essential everyday vocabulary to help you begin communicating in English."),
            Lesson(id = 8, categoryId = 2, name = "Numbers & Colors", content = "Master basic numbers and colors used in daily conversations."),
            Lesson(id = 9, categoryId = 2, name = "Basic Phrases", content = "Familiarize yourself with useful expressions and greetings."),

// English for Kids
            Lesson(id = 10, categoryId = 3, name = "Animal Names", content = "Explore fun and engaging words related to animals."),
            Lesson(id = 11, categoryId = 3, name = "Fun with Rhymes", content = "Enjoy learning English through rhymes and playful verses."),
            Lesson(id = 12, categoryId = 3, name = "Storytelling", content = "Build vocabulary and creativity by listening to and telling stories."),

// Business English
            Lesson(id = 13, categoryId = 4, name = "Office Communication", content = "Learn common phrases used in office and professional environments."),
            Lesson(id = 14, categoryId = 4, name = "Email Writing", content = "Practice writing effective and professional emails."),
            Lesson(id = 15, categoryId = 4, name = "Job Interviews", content = "Prepare for job interviews with useful tips and expressions."),

// Everyday Conversations
            Lesson(id = 16, categoryId = 5, name = "Shopping Dialogues", content = "Learn how to talk about prices, items, and transactions while shopping."),
            Lesson(id = 17, categoryId = 5, name = "Making Appointments", content = "Understand how to make and change appointments in English."),
            Lesson(id = 18, categoryId = 5, name = "Small Talk", content = "Master the art of light conversation in social settings."),

// Travel & Tourism
            Lesson(id = 19, categoryId = 6, name = "At the Airport", content = "Useful English for navigating the airport experience."),
            Lesson(id = 20, categoryId = 6, name = "Hotel Conversations", content = "Common phrases used while staying at hotels."),
            Lesson(id = 21, categoryId = 6, name = "Ordering Food", content = "Learn how to order meals and ask about dishes in English."),

// Entertainment
            Lesson(id = 22, categoryId = 7, name = "Movies", content = "Talk about films, genres, and favorite movie scenes."),
            Lesson(id = 23, categoryId = 7, name = "Music", content = "Learn vocabulary related to music, instruments, and styles."),
            Lesson(id = 24, categoryId = 7, name = "Video Games", content = "Explore gaming vocabulary and how to talk about your favorite games."),

// Work & Study
            Lesson(id = 25, categoryId = 8, name = "Subject", content = "Learn names of school subjects and how to describe them."),
            Lesson(id = 26, categoryId = 8, name = "Class", content = "Understand classroom-related vocabulary and interactions."),
            Lesson(id = 27, categoryId = 8, name = "School", content = "Explore school life vocabulary including people and places."),

// Environment & Nature
            Lesson(id = 28, categoryId = 9, name = "Weather and Seasons", content = "Discuss the weather, seasons, and how they affect our lives."),
            Lesson(id = 29, categoryId = 9, name = "Natural Disasters", content = "Learn important terms for describing natural disasters."),
            Lesson(id = 30, categoryId = 9, name = "Protecting the Environment", content = "Talk about environmental issues and how to protect nature."),

// Sports
            Lesson(id = 31, categoryId = 10, name = "Sport", content = "Get familiar with general sports vocabulary and expressions."),
            Lesson(id = 32, categoryId = 10, name = "Playing Team Sports", content = "Learn about teamwork, positions, and sports strategies."),
            Lesson(id = 33, categoryId = 10, name = "Watching Sports Events", content = "Use English to talk about live games and fan experiences."),


        )
    }

    fun getSampleWords(): List<Word> {
        return listOf(
            Word(
                lessonId = 1,
                english = "Present Simple Tense",
                vietnamese = "S + V1 + ...",
                description = "I play football every day",
                descriptionVietnamese = "Tôi chơi bóng đá mỗi ngày"
            ),
            Word(
                lessonId = 1,
                english = "Present continuous tense",
                vietnamese = "S + am/is/are + V-ing",
                description = "She walks to school every day",
                descriptionVietnamese = "Cô ấy đi bộ đến trường mỗi ngày"
            ),
            Word(
                lessonId = 1,
                english = "Present Perfect Tense",
                vietnamese = "S + has/have + V3 + ...",
                description = "She walks to school every day",
                descriptionVietnamese = "Cô ấy đi bộ đến trường mỗi ngày"
            ),
            Word(
                lessonId = 1,
                english = "Present perfect continuous Tense",
                vietnamese = "S + has/have + been + V-ing",
                description = "She has been studying all day",
                descriptionVietnamese = "Cô ấy đã học cả ngày"
            ),
            Word(
                lessonId = 1,
                english = "Past Simple Tense",
                vietnamese = "S + V2/V-ed + ...",
                description = "She walked to school every day",
                descriptionVietnamese = "Cô ấy đi bộ đến trường mỗi ngày"
            ),
            Word(
                lessonId = 1,
                english = "Past Continuous Tense",
                vietnamese = "S + was/were + V-ing + ...",
                description = "She was walking to school every day",
                descriptionVietnamese = "Cô ấy đang đi bộ đến trường mỗi ngày"
            ),
            Word(
                lessonId = 1,
                english = "Past Perfect Tense",
                vietnamese = "S + had + V3/ed + ...",
                description = "She had walked to school every day",
                descriptionVietnamese = "Cô ấy đã đi bộ đến trường mỗi ngày"
            ),
            Word(
                lessonId = 1,
                english = "Past perfect continuous Tense",
                vietnamese = "S + had + been + V-ing + ...",
                description = "She had been walking to school every day",
                descriptionVietnamese = "Cô ấy đã đi bộ đến trường mỗi ngày"
            ),
            Word(
                lessonId = 1,
                english = "Future Simple Tense",
                vietnamese = "S + will/shall + V1 + ...",
                description = "She will walk to school every day",
                descriptionVietnamese = "Cô ấy sẽ đi bộ đến trường mỗi ngày"
            ),
            Word(
                lessonId = 1,
                english = "Future Continuous Tense",
                vietnamese = "S + will/ shall + be + V-ing + ...",
                description = "She will be walking to school every day",
                descriptionVietnamese = "Cô ấy sẽ đi bộ đến trường mỗi ngày"
            ),
            Word(
                lessonId = 1,
                english = "Future Perfect Tense",
                vietnamese = "S + will + have + V3/ed + ...",
                description = "She will have walked to school every day",
                descriptionVietnamese = "Cô ấy sẽ đã đi bộ đến trường mỗi ngày"
            ),
            Word(
                lessonId = 1,
                english = "Future Perfect Continuous Tense",
                vietnamese = "S + will + have + been + V-ing + ...",
                description = "She will have been walking to school every day",
                descriptionVietnamese = "Cô ấy sẽ đã đi bộ đến trường mỗi ngày"
            ),

            Word(
                lessonId = 2,
                english = "Zero Conditional",
                vietnamese = "If + S + V1, S + V1",
                description = "If it rains, the ground gets wet",
                descriptionVietnamese = "Nếu trời mưa, mặt đất sẽ ướt"
            ),
            Word(
                lessonId = 2,
                english = "Conditional Type 1",
                vietnamese = "If + S + V, S + will + V",
                description = "If you stay out late, get a taxi home",
                descriptionVietnamese = "Nếu bạn ra ngoài khuya, hãy bắt taxi về nhà"
            ),
            Word(
                lessonId = 2,
                english = "Conditional Type 2",
                vietnamese = "If + S + V-ed, S + would + V",
                description = "If he were buying a new computer, I would send him a promotional code.",
                descriptionVietnamese = "Nếu anh ấy định mua một chiếc máy tính mới thì tôi sẽ gửi anh ấy mã giảm giá."
            ),
            Word(
                lessonId = 2,
                english = "Conditional Type 3",
                vietnamese = "If + S + had + V-ed, S + would + have + V-ed",
                description = "If he had stayed at home last night, he wouldn’t have met her",
                descriptionVietnamese = "Nếu tối qua anh ấy ở nhà, anh ấy đã không gặp cô ấy."
            ),
            Word(
                lessonId = 2,
                english = "Mixed conditional",
                vietnamese = "If + S + had + V-ed, S + would + V",
                description = "Last night, if our son had gone to bed lately, he would feel exhausted now",
                descriptionVietnamese = "Tối qua, nếu con trai chúng tôi đã đi ngủ muộn, anh ấy sẽ cảm thấy mệt mỏi bây giờ.",
            ),
            Word(
                lessonId = 2,
                english = "Mixed conditional",
                vietnamese = "If + V-ed, S + would + have + V-ed",
                description = "If I were taller, I could have helped you paint the window yesterday",
                descriptionVietnamese = "Nếu tôi cao hơn, tôi có thể giu bạn sơn cửa sổ hôm qua.",
            ),

            Word(
                lessonId = 3,
                english = "Passive voice",
                vietnamese = "O + be + V-ed + ...",
                description = "The homework is done by my sister everyday",
                descriptionVietnamese = "Bài tập được làm bởi chị tôi mỗi ngày",
            ),
            Word(
                lessonId = 3,
                english = "Passive voice of Present Simple",
                vietnamese = "S+ is/am/are + P2 + (by + O)",
                description = "English is studied by Mary everyday",
                descriptionVietnamese = "Hàng ngày, tiếng Anh được học bởi Mary.",
            ),
            Word(
                lessonId = 3,
                english = "Passive voice of Present Continuous",
                vietnamese = "S + is/am/are + being + P2 + (by + O)",
                description = "Some trees are being planted by him now.",
                descriptionVietnamese = "Bây giờ một vài cái cây đang trồng bởi anh ấy.",
            ),
            Word(
                lessonId = 3,
                english = "Passive voice of Present Perfect",
                vietnamese = "S + will + be + P2 + (by + O)",
                description = "English exercises have been done by my friend for two hours.",
                descriptionVietnamese = "Bài tập tiếng Anh đã được bạn tôi làm trong hai giờ.",
            ),
            Word(
                lessonId = 3,
                english = "Passive voice of Present Perfect Continuous",
                vietnamese = "S + have/ has + been + being + P2 + (by + O)",
                description = "This car has been being repaired by John for 2 hours.",
                descriptionVietnamese = "Chiếc xe ô tô đã đang được sửa bởi John trong vòng hai tiếng rồi.",
            ),
            Word(
                lessonId = 3,
                english = "Passive voice of Past Simple",
                vietnamese = "S + was/were + P2 + (by + O)",
                description = "A letter was written by her yesterday.",
                descriptionVietnamese = "Ngày hôm qua, một lá thư đã được viết bởi cô ấy.",
            ),
            Word(
                lessonId = 3,
                english = "Passive voice of Past Continuous",
                vietnamese = "S + was/were + being + P2 + (by + O)",
                description = "A car was being bought at 9 am yesterday.",
                descriptionVietnamese = "Vào lúc 9 giờ sáng ngày hôm qua, một chiếc ô tô đang được mua.",
            ),
            Word(
                lessonId = 3,
                english = "Passive voice of Past Perfect",
                vietnamese = "S + had + been + P2 + (by + O)",
                description = "His report had been finished before 10 p.m yesterday.",
                descriptionVietnamese = "Bản báo cáo của anh ấy đã được hoàn thành trước 10 giờ tối ngày hôm qua.",
            ),
            Word(
                lessonId = 3,
                english = "Passive voice of Past Perfect Continuous",
                vietnamese = "S + had + been + being + P2 + (by + O)",
                description = "The essay had been being typed for 3 hours before you came yesterday.",
                descriptionVietnamese = "Bài văn đã đang được gõ trong vòng 3 tiếng trước khi bạn đến ngày hôm qua.",
            ),
            Word(
                lessonId = 3,
                english = "Passive voice of Future Simple",
                vietnamese = "S + will + be + P2 + (by + O)",
                description = "A lot of things will be done tomorrow.",
                descriptionVietnamese = "Rất nhiều việc sẽ được làm vào ngày mai.",
            ),
            Word(
                lessonId = 3,
                english = "Passive voice of Future Continuous",
                vietnamese = "S + will + be + being + P2 + (by + O)",
                description = "Her children will be being taken care of at this time tomorrow.",
                descriptionVietnamese = "Con của ấy sẽ đang được chăm sóc vào thời điểm này ngày mai.",
            ),
            Word(
                lessonId = 3,
                english = "Passive voice of Future Perfect",
                vietnamese = "S + will + have + been + P2 + (by + O)",
                description = "Her studying will have been finished by the end of this year.",
                descriptionVietnamese = "Việc học của cô ấy sẽ được hoàn thành trức cuối năm nay.",
            ),
            Word(
                lessonId = 3,
                english = "Passive voice of Future Perfect Continuous",
                vietnamese = "S + will + have +been + being + P2 + (by + O)",
                description = "English will have been being taught for 5 years by next week.",
                descriptionVietnamese = "Tiếng Anh sẽ đang dạy được năm năm vào trước tuần sau.",
            ),

            Word(
                lessonId = 4,
                english = "\"I like coffee,\" she said.",
                vietnamese = "→ She said that she liked coffee.",
                description = "Reported speech with present simple",
                descriptionVietnamese = "Câu gián tiếp với thì hiện tại đơn",
                ),
            Word(
                lessonId = 4,
                english = "\"I am working now,\" he said.",
                vietnamese = "→ He said that he was working then.",
                description = "Reported speech with present continuous",
                descriptionVietnamese = "Câu gián tiếp với thì hiện tại tiếp diễn",
                ),
            Word(
                lessonId = 4,
                english = "\"I went to the party,\" John said.",
                vietnamese = "→ John said that he had gone to the party.",
                description = "Reported speech with past simple",
                descriptionVietnamese = "Câu gián tiếp với thì quá khứ đơn",
                ),
            Word(
                lessonId = 4,
                english = "\"I will call you tomorrow,\" she said.",
                vietnamese = "→ She said that she would call me the next day.",
                description = "Reported speech with future simple",
                descriptionVietnamese = "Câu gián tiếp với thì tương lai đơn",
                ),
            Word(
                lessonId = 4,
                english = "\"Do you like music?\" he asked.",
                vietnamese = "→ He asked if I liked music.",
                description = "Reported Yes/No question",
                descriptionVietnamese = "Câu hỏi Yes/No gián tiếp",
                ),
            Word(
                lessonId = 4,
                english = "\"Where do you live?\" she asked.",
                vietnamese = "→ She asked where I lived.",
                description = "Reported Wh-question",
                descriptionVietnamese = "Câu hỏi Wh- gián tiếp",
                ),
            Word(
                lessonId = 4,
                english = "\"I can swim,\" he said.",
                vietnamese = "→ He said that he could swim.",
                description = "Reported speech with modals: can → could",
                descriptionVietnamese = "Chuyển 'can' sang 'could'",
                ),
            Word(
                lessonId = 4,
                english = "\"I may go out,\" she said.",
                vietnamese = "→ She said that she might go out.",
                description = "Reported speech with modals: may → might",
                descriptionVietnamese = "Chuyển 'may' sang 'might'",
                ),
            Word(
                lessonId = 4,
                english = "\"Close the door,\" he said to me.",
                vietnamese = "→ He told me to close the door.",
                description = "Reported command – tell + to V",
                descriptionVietnamese = "Câu mệnh lệnh gián tiếp",
                ),
            Word(
                lessonId = 4,
                english = "\"Don't shout,\" she said.",
                vietnamese = "→ She told me not to shout.",
                description = "Reported negative command – tell + not to V",
                descriptionVietnamese = "Câu mệnh lệnh phủ định gián tiếp",
                ),
            Word(
                lessonId = 4,
                english = "\"I met her yesterday,\" he said.",
                vietnamese = "→ He said he had met her the day before.",
                description = "Change of time expressions",
                descriptionVietnamese = "Thay đổi trạng từ chỉ thời gian",
                ),
            Word(
                lessonId = 4,
                english = "\"I am staying here,\" she said.",
                vietnamese = "→ She said that she was staying there.",
                description = "Change of place expressions",
                descriptionVietnamese = "Thay đổi trạng từ chỉ nơi chốn",
                ),
            Word(
                lessonId = 4,
                english = "\"Be quiet,\" the teacher told us.",
                vietnamese = "→ The teacher told us to be quiet.",
                description = "Mixed reporting verb – ask/tell/say",
                descriptionVietnamese = "Các động từ tường thuật khác nhau",
                ),

            Word(
                lessonId = 5,
                english = "The man who lives next door is very friendly.",
                vietnamese = "Người đàn ông sống bên cạnh rất thân thiện.",
                description = "Relative clause with 'who' (chủ ngữ chỉ người)",
                descriptionVietnamese = "Mệnh đề quan hệ với 'who' làm chủ ngữ chỉ người"
            ),
            Word(
                lessonId = 5,
                english = "The book which is on the table is mine.",
                vietnamese = "Cuốn sách ở trên bàn là của tôi.",
                description = "Relative clause with 'which' (chủ ngữ chỉ vật)",
                descriptionVietnamese = "Mệnh đề quan hệ với 'which' làm chủ ngữ chỉ vật"
            ),
            Word(
                lessonId = 5,
                english = "I met a girl who can speak six languages.",
                vietnamese = "Tôi gặp một cô gái có thể nói sáu thứ tiếng.",
                description = "Relative clause giving extra information about a person",
                descriptionVietnamese = "Mệnh đề quan hệ cung cấp thêm thông tin về người"
            ),
            Word(
                lessonId = 5,
                english = "The man whom I saw yesterday is my teacher.",
                vietnamese = "Người đàn ông tôi gặp hôm qua là thầy giáo của tôi.",
                description = "Relative clause with 'whom' (tân ngữ chỉ người)",
                descriptionVietnamese = "Mệnh đề quan hệ với 'whom' làm tân ngữ chỉ người"
            ),
            Word(
                lessonId = 5,
                english = "This is the laptop that I bought last week.",
                vietnamese = "Đây là chiếc laptop tôi mua tuần trước.",
                description = "Using 'that' instead of 'which' or 'who'",
                descriptionVietnamese = "Sử dụng 'that' thay cho 'which' hoặc 'who'"
            ),
            Word(
                lessonId = 5,
                english = "She’s the woman whose son is a doctor.",
                vietnamese = "Cô ấy là người phụ nữ có con trai là bác sĩ.",
                description = "Relative clause with 'whose' (sở hữu)",
                descriptionVietnamese = "Mệnh đề quan hệ với 'whose' thể hiện sở hữu"
            ),
            Word(
                lessonId = 5,
                english = "Do you know the reason why she left?",
                vietnamese = "Bạn có biết lý do tại sao cô ấy rời đi không?",
                description = "Relative clause with 'why'",
                descriptionVietnamese = "Mệnh đề quan hệ với 'why'"
            ),
            Word(
                lessonId = 5,
                english = "The hotel where we stayed was very nice.",
                vietnamese = "Khách sạn mà chúng tôi ở rất đẹp.",
                description = "Relative clause with 'where' (chỉ nơi chốn)",
                descriptionVietnamese = "Mệnh đề quan hệ với 'where' chỉ nơi chốn"
            ),
            Word(
                lessonId = 5,
                english = "That’s the day when I met her.",
                vietnamese = "Đó là ngày tôi gặp cô ấy.",
                description = "Relative clause with 'when' (chỉ thời gian)",
                descriptionVietnamese = "Mệnh đề quan hệ với 'when' chỉ thời gian"
            ),
            Word(
                lessonId = 5,
                english = "The students who study hard will pass the exam.",
                vietnamese = "Những học sinh học chăm sẽ vượt qua kỳ thi.",
                description = "Defining relative clause",
                descriptionVietnamese = "Mệnh đề quan hệ xác định"
            ),
            Word(
                lessonId = 5,
                english = "My brother, who lives in Da Nang, is an engineer.",
                vietnamese = "Anh trai tôi, người sống ở Đà Nẵng, là kỹ sư.",
                description = "Non-defining relative clause",
                descriptionVietnamese = "Mệnh đề quan hệ không xác định"
            ),
            Word(
                lessonId = 5,
                english = "The girl sitting next to me is my cousin.",
                vietnamese = "Cô gái ngồi cạnh tôi là em họ tôi.",
                description = "Reduced relative clause (rút gọn với V-ing)",
                descriptionVietnamese = "Mệnh đề quan hệ rút gọn với V-ing"
            ),
            Word(
                lessonId = 5,
                english = "The documents sent yesterday are very important.",
                vietnamese = "Các tài liệu được gửi hôm qua rất quan trọng.",
                description = "Reduced relative clause (rút gọn với V-ed)",
                descriptionVietnamese = "Mệnh đề quan hệ rút gọn với V-ed"
            ),

            Word(
                lessonId = 6,
                english = "She looks after her little brother every day.",
                vietnamese = "Cô ấy chăm sóc em trai mình mỗi ngày.",
                description = "Phrasal verb: look after",
                descriptionVietnamese = "Phrasal verb: trông nom, chăm sóc"
            ),
            Word(
                lessonId = 6,
                english = "He ran into an old friend at the supermarket.",
                vietnamese = "Anh ấy tình cờ gặp lại một người bạn cũ ở siêu thị.",
                description = "Phrasal verb: run into",
                descriptionVietnamese = "Phrasal verb: tình cờ gặp"
            ),
            Word(
                lessonId = 6,
                english = "We need to deal with this problem now.",
                vietnamese = "Chúng ta cần giải quyết vấn đề này ngay bây giờ.",
                description = "Phrasal verb: deal with",
                descriptionVietnamese = "Phrasal verb: giải quyết, xử lý"
            ),
            Word(
                lessonId = 6,
                english = "Don’t give up! You can do it!",
                vietnamese = "Đừng bỏ cuộc! Bạn có thể làm được!",
                description = "Phrasal verb: give up",
                descriptionVietnamese = "Phrasal verb: từ bỏ"
            ),
            Word(
                lessonId = 6,
                english = "He turned down the job offer.",
                vietnamese = "Anh ấy đã từ chối lời đề nghị công việc.",
                description = "Phrasal verb: turn down",
                descriptionVietnamese = "Phrasal verb: từ chối"
            ),
            Word(
                lessonId = 6,
                english = "She came up with a great idea for the project.",
                vietnamese = "Cô ấy đã nảy ra một ý tưởng tuyệt vời cho dự án.",
                description = "Phrasal verb: come up with",
                descriptionVietnamese = "Phrasal verb: nghĩ ra, nảy ra"
            ),
            Word(
                lessonId = 6,
                english = "I’m looking forward to the weekend.",
                vietnamese = "Tôi đang mong đợi đến cuối tuần.",
                description = "Phrasal verb: look forward to",
                descriptionVietnamese = "Phrasal verb: mong đợi điều gì"
            ),
            Word(
                lessonId = 6,
                english = "He got over the flu quickly.",
                vietnamese = "Anh ấy đã hồi phục sau cơn cảm cúm rất nhanh.",
                description = "Phrasal verb: get over",
                descriptionVietnamese = "Phrasal verb: hồi phục, vượt qua"
            ),
            Word(
                lessonId = 6,
                english = "Please turn off the lights before leaving.",
                vietnamese = "Làm ơn tắt đèn trước khi rời đi.",
                description = "Phrasal verb: turn off",
                descriptionVietnamese = "Phrasal verb: tắt"
            ),
            Word(
                lessonId = 6,
                english = "We should carry on with the meeting.",
                vietnamese = "Chúng ta nên tiếp tục cuộc họp.",
                description = "Phrasal verb: carry on",
                descriptionVietnamese = "Phrasal verb: tiếp tục"
            ),
            Word(
                lessonId = 6,
                english = "They set up a new company last year.",
                vietnamese = "Họ đã thành lập một công ty mới vào năm ngoái.",
                description = "Phrasal verb: set up",
                descriptionVietnamese = "Phrasal verb: thành lập, thiết lập"
            ),
            Word(
                lessonId = 6,
                english = "He broke up with his girlfriend last week.",
                vietnamese = "Anh ấy đã chia tay bạn gái vào tuần trước.",
                description = "Phrasal verb: break up with",
                descriptionVietnamese = "Phrasal verb: chia tay"
            ),
            Word(
                lessonId = 6,
                english = "Let’s find out what happened.",
                vietnamese = "Hãy tìm hiểu xem chuyện gì đã xảy ra.",
                description = "Phrasal verb: find out",
                descriptionVietnamese = "Phrasal verb: tìm hiểu, khám phá"
            ),

            // Common Words
            Word(lessonId = 7, english = "Hello", vietnamese = "Xin chào", pronunciation = "həˈloʊ", description = "A greeting used when meeting someone.", descriptionVietnamese = "Một lời chào khi gặp ai đó."),
            Word(lessonId = 7, english = "Goodbye", vietnamese = "Tạm biệt", pronunciation = "ˌɡʊdˈbaɪ", description = "A farewell when leaving.", descriptionVietnamese = "Lời chào khi rời đi."),
            Word(lessonId = 7, english = "Please", vietnamese = "Làm ơn", pronunciation = "pliːz", description = "A polite word used in requests.", descriptionVietnamese = "Từ lịch sự dùng khi yêu cầu."),
            Word(lessonId = 7, english = "Thank you", vietnamese = "Cảm ơn", pronunciation = "ˈθæŋk ju", description = "An expression of gratitude.", descriptionVietnamese = "Biểu hiện sự biết ơn."),
            Word(lessonId = 7, english = "Yes", vietnamese = "Vâng", pronunciation = "jɛs", description = "An affirmative response.", descriptionVietnamese = "Một câu trả lời khẳng định."),
            Word(lessonId = 7, english = "No", vietnamese = "Không", pronunciation = "noʊ", description = "A negative response.", descriptionVietnamese = "Một câu trả lời phủ định."),
            Word(lessonId = 7, english = "Excuse me", vietnamese = "Xin lỗi", pronunciation = "ɪkˈskjuːz mi", description = "Used to get someone's attention or to apologize.", descriptionVietnamese = "Dùng để thu hút sự chú ý hoặc xin lỗi."),
            Word(lessonId = 7, english = "Sorry", vietnamese = "Xin lỗi", pronunciation = "ˈsɑːri", description = "An expression of apology.", descriptionVietnamese = "Biểu hiện sự xin lỗi."),
            Word(lessonId = 7, english = "Help", vietnamese = "Giúp đỡ", pronunciation = "hɛlp", description = "A request for assistance.", descriptionVietnamese = "Yêu cầu sự giúp đỡ."),
            Word(lessonId = 7, english = "Friend", vietnamese = "Bạn bè", pronunciation = "frɛnd", description = "A person whom one knows and has a bond with.", descriptionVietnamese = "Một người quen biết và có mối quan hệ thân thiết."),

            // Numbers & Colors
            Word(lessonId = 8, english = "One", vietnamese = "Một", pronunciation = "wʌn", description = "The number 1.", descriptionVietnamese = "Số 1."),
            Word(lessonId = 8, english = "Two", vietnamese = "Hai", pronunciation = "tuː", description = "The number 2.", descriptionVietnamese = "Số 2."),
            Word(lessonId = 8, english = "Three", vietnamese = "Ba", pronunciation = "θriː", description = "The number 3.", descriptionVietnamese = "Số 3."),
            Word(lessonId = 8, english = "Four", vietnamese = "Bốn", pronunciation = "fɔːr", description = "The number 4.", descriptionVietnamese = "Số 4."),
            Word(lessonId = 8, english = "Five", vietnamese = "Năm", pronunciation = "faɪv", description = "The number 5.", descriptionVietnamese = "Số 5."),
            Word(lessonId = 8, english = "Red", vietnamese = "Màu đỏ", pronunciation = "rɛd", description = "A primary color often associated with passion.", descriptionVietnamese = "Màu chính thường gắn với sự đam mê."),
            Word(lessonId = 8, english = "Blue", vietnamese = "Màu xanh dương", pronunciation = "bluː", description = "A primary color often associated with calmness.", descriptionVietnamese = "Màu chính thường gắn với sự bình tĩnh."),
            Word(lessonId = 8, english = "Green", vietnamese = "Màu xanh lá", pronunciation = "ɡriːn", description = "A color often associated with nature.", descriptionVietnamese = "Màu thường liên quan đến thiên nhiên."),
            Word(lessonId = 8, english = "Yellow", vietnamese = "Màu vàng", pronunciation = "ˈjɛloʊ", description = "A bright color often associated with happiness.", descriptionVietnamese = "Màu sáng thường liên quan đến niềm vui."),
            Word(lessonId = 8, english = "Black", vietnamese = "Màu đen", pronunciation = "blæk", description = "A color often associated with mystery.", descriptionVietnamese = "Màu thường liên quan đến sự bí ẩn."),

            // Basic Phrases
            Word(lessonId = 9, english = "How are you?", vietnamese = "Bạn khỏe không?", pronunciation = "haʊ ɑr ju?", description = "A common greeting to ask about someone's well-being.", descriptionVietnamese = "Câu chào hỏi sức khỏe phổ biến."),
            Word(lessonId = 9, english = "What’s your name?", vietnamese = "Bạn tên gì?", pronunciation = "wɑːts jɔːr neɪm?", description = "A question to ask for someone's name.", descriptionVietnamese = "Câu hỏi để biết tên ai đó."),
            Word(lessonId = 9, english = "Where are you from?", vietnamese = "Bạn đến từ đâu?", pronunciation = "wɛr ɑr ju frʌm?", description = "A question to ask about someone's origin.", descriptionVietnamese = "Câu hỏi về quê quán của ai đó."),
            Word(lessonId = 9, english = "I don’t understand", vietnamese = "Tôi không hiểu", pronunciation = "aɪ doʊnt ˌʌndərˈstænd", description = "A phrase used when something is unclear.", descriptionVietnamese = "Câu nói khi không hiểu điều gì đó."),
            Word(lessonId = 9, english = "Can you help me?", vietnamese = "Bạn có thể giúp tôi không?", pronunciation = "kæn ju hɛlp mi?", description = "A request for assistance.", descriptionVietnamese = "Lời yêu cầu sự giúp đỡ."),
            Word(lessonId = 9, english = "Good morning", vietnamese = "Chào buổi sáng", pronunciation = "ɡʊd ˈmɔrnɪŋ", description = "A greeting used in the morning.", descriptionVietnamese = "Lời chào vào buổi sáng."),
            Word(lessonId = 9, english = "Good night", vietnamese = "Chúc ngủ ngon", pronunciation = "ɡʊd naɪt", description = "A phrase used before going to sleep.", descriptionVietnamese = "Câu chúc trước khi đi ngủ."),
            Word(lessonId = 9, english = "I am lost", vietnamese = "Tôi bị lạc", pronunciation = "aɪ æm lɔst", description = "A phrase used to indicate that someone doesn't know where they are.", descriptionVietnamese = "Câu nói khi ai đó không biết mình đang ở đâu."),
            Word(lessonId = 9, english = "I am sorry", vietnamese = "Tôi xin lỗi", pronunciation = "aɪ æm ˈsɑːri", description = "A phrase used to apologize for something.", descriptionVietnamese = "Câu nói để xin lỗi vì một điều gì đó."),
            Word(lessonId = 9, english = "Good morning", vietnamese = "Chào buổi sáng", pronunciation = "ɡʊd ˈmɔrnɪŋ", description = "A greeting used in the morning.", descriptionVietnamese = "Lời chào dùng vào buổi sáng."),
            Word(lessonId = 9, english = "See you later", vietnamese = "Hẹn gặp lại", pronunciation = "si ju ˈleɪtər", description = "A phrase used to say goodbye with the expectation of meeting again.", descriptionVietnamese = "Câu nói khi tạm biệt với mong đợi sẽ gặp lại."),

            // Animal Names
            Word(lessonId = 10, english = "Dog", vietnamese = "Chó", pronunciation = "dɔːɡ", description = "A domesticated carnivorous mammal known for loyalty.", descriptionVietnamese = "Một loài động vật ăn thịt được thuần hóa, nổi tiếng vì sự trung thành."),
            Word(lessonId = 10, english = "Cat", vietnamese = "Mèo", pronunciation = "kæt", description = "A small domesticated carnivorous mammal with soft fur.", descriptionVietnamese = "Một loài động vật ăn thịt nhỏ đã được thuần hóa với bộ lông mềm."),
            Word(lessonId = 10, english = "Elephant", vietnamese = "Voi", pronunciation = "ˈɛləfənt", description = "The largest land animal with a long trunk.", descriptionVietnamese = "Loài động vật trên cạn lớn nhất với chiếc vòi dài."),
            Word(lessonId = 10, english = "Tiger", vietnamese = "Hổ", pronunciation = "ˈtaɪɡər", description = "A large wild cat with orange fur and black stripes.", descriptionVietnamese = "Một loài mèo lớn có bộ lông màu cam và sọc đen."),
            Word(lessonId = 10, english = "Dolphin", vietnamese = "Cá heo", pronunciation = "ˈdɒlfɪn", description = "A highly intelligent marine mammal known for playfulness.", descriptionVietnamese = "Một loài động vật biển thông minh, nổi tiếng vì sự tinh nghịch."),
            Word(lessonId = 10, english = "Kangaroo", vietnamese = "Chuột túi", pronunciation = "ˌkæŋɡəˈruː", description = "A marsupial from Australia that moves by hopping.", descriptionVietnamese = "Một loài thú có túi ở Úc di chuyển bằng cách nhảy."),
            Word(lessonId = 10, english = "Giraffe", vietnamese = "Hươu cao cổ", pronunciation = "dʒɪˈræf", description = "The tallest land animal with a long neck.", descriptionVietnamese = "Loài động vật trên cạn cao nhất với chiếc cổ dài."),
            Word(lessonId = 10, english = "Penguin", vietnamese = "Chim cánh cụt", pronunciation = "ˈpɛŋɡwɪn", description = "A flightless bird that lives in cold climates.", descriptionVietnamese = "Một loài chim không bay sống ở vùng khí hậu lạnh."),
            Word(lessonId = 10, english = "Eagle", vietnamese = "Đại bàng", pronunciation = "ˈiːɡl", description = "A large bird of prey known for sharp eyesight.", descriptionVietnamese = "Một loài chim săn mồi lớn nổi tiếng với thị lực sắc bén."),
            Word(lessonId = 10, english = "Crocodile", vietnamese = "Cá sấu", pronunciation = "ˈkrɒkədaɪl", description = "A large aquatic reptile with a powerful jaw.", descriptionVietnamese = "Một loài bò sát lớn sống dưới nước với hàm răng mạnh mẽ."),

            // Fun with Rhymes
            Word(lessonId = 11, english = "Cat", vietnamese = "Mèo", pronunciation = "kæt", description = "A small pet that loves to nap.", descriptionVietnamese = "Một con vật nhỏ thích ngủ trưa."),
            Word(lessonId = 11, english = "Hat", vietnamese = "Mũ", pronunciation = "hæt", description = "Something you wear upon your head.", descriptionVietnamese = "Thứ bạn đội trên đầu."),
            Word(lessonId = 11, english = "Sun", vietnamese = "Mặt trời", pronunciation = "sʌn", description = "Shining brightly in the sky.", descriptionVietnamese = "Tỏa sáng rực rỡ trên bầu trời."),
            Word(lessonId = 11, english = "Run", vietnamese = "Chạy", pronunciation = "rʌn", description = "Moving quickly on your feet.", descriptionVietnamese = "Di chuyển nhanh bằng đôi chân."),
            Word(lessonId = 11, english = "Bee", vietnamese = "Ong", pronunciation = "biː", description = "Buzzing round from tree to tree.", descriptionVietnamese = "Bay vo ve từ cây này sang cây khác."),
            Word(lessonId = 11, english = "See", vietnamese = "Nhìn", pronunciation = "siː", description = "Using your eyes to look around.", descriptionVietnamese = "Dùng mắt để quan sát xung quanh."),
            Word(lessonId = 11, english = "Star", vietnamese = "Ngôi sao", pronunciation = "stɑːr", description = "Twinkling brightly in the night.", descriptionVietnamese = "Lấp lánh trong đêm tối."),
            Word(lessonId = 11, english = "Car", vietnamese = "Xe hơi", pronunciation = "kɑːr", description = "A vehicle that goes far.", descriptionVietnamese = "Một chiếc xe đi rất xa."),
            Word(lessonId = 11, english = "Boat", vietnamese = "Thuyền", pronunciation = "boʊt", description = "Floating on water near the coast.", descriptionVietnamese = "Trôi trên mặt nước gần bờ."),
            Word(lessonId = 11, english = "Goat", vietnamese = "Dê", pronunciation = "ɡoʊt", description = "Climbing mountains high and steep.", descriptionVietnamese = "Leo lên núi cao và dốc."),

            // Storytelling
            Word(lessonId = 12, english = "Once upon a time", vietnamese = "Ngày xửa ngày xưa", pronunciation = "wʌns əˈpɒn ə taɪm", description = "A common phrase used to begin fairy tales and stories.", descriptionVietnamese = "Cụm từ thường dùng để bắt đầu truyện cổ tích."),
            Word(lessonId = 12, english = "Hero", vietnamese = "Anh hùng", pronunciation = "ˈhɪəroʊ", description = "The main character who faces challenges and overcomes them.", descriptionVietnamese = "Nhân vật chính đối mặt với thử thách và vượt qua."),
            Word(lessonId = 12, english = "Villain", vietnamese = "Nhân vật phản diện", pronunciation = "ˈvɪlən", description = "The character who opposes the hero in a story.", descriptionVietnamese = "Nhân vật đối đầu với anh hùng trong câu chuyện."),
            Word(lessonId = 12, english = "Magic", vietnamese = "Phép thuật", pronunciation = "ˈmædʒɪk", description = "A supernatural power often seen in fantasy stories.", descriptionVietnamese = "Sức mạnh siêu nhiên thường xuất hiện trong truyện kỳ ảo."),
            Word(lessonId = 12, english = "Adventure", vietnamese = "Cuộc phiêu lưu", pronunciation = "ədˈvɛntʃər", description = "An exciting journey filled with challenges and discoveries.", descriptionVietnamese = "Một hành trình thú vị với nhiều thử thách và khám phá."),
            Word(lessonId = 12, english = "Twist", vietnamese = "Tình tiết bất ngờ", pronunciation = "twɪst", description = "An unexpected change in the story that surprises the reader.", descriptionVietnamese = "Một sự thay đổi bất ngờ trong câu chuyện khiến người đọc ngạc nhiên."),
            Word(lessonId = 12, english = "Moral", vietnamese = "Bài học đạo đức", pronunciation = "ˈmɒrəl", description = "The lesson or message a story teaches.", descriptionVietnamese = "Bài học hoặc thông điệp mà câu chuyện truyền tải."),
            Word(lessonId = 12, english = "Narrator", vietnamese = "Người kể chuyện", pronunciation = "ˈnærətər", description = "The person or voice that tells the story.", descriptionVietnamese = "Người hoặc giọng kể chuyện trong câu chuyện."),
            Word(lessonId = 12, english = "Happily ever after", vietnamese = "Hạnh phúc mãi mãi về sau", pronunciation = "ˈhæpɪli ˈɛvər ˈæftər", description = "A phrase used to end fairy tales where everything turns out well.", descriptionVietnamese = "Cụm từ kết thúc truyện cổ tích khi mọi chuyện đều tốt đẹp."),
            Word(lessonId = 12, english = "Climax", vietnamese = "Cao trào", pronunciation = "ˈklaɪmæks", description = "The most exciting or intense part of a story.", descriptionVietnamese = "Phần kịch tính nhất của câu chuyện."),

            // Office Communication
            Word(lessonId = 13, english = "Email", vietnamese = "Thư điện tử", pronunciation = "ˈiːmeɪl", description = "A method of sending messages electronically between computers.", descriptionVietnamese = "Một phương thức gửi tin nhắn điện tử giữa các máy tính."),
            Word(lessonId = 13, english = "Meeting", vietnamese = "Cuộc họp", pronunciation = "ˈmiːtɪŋ", description = "A gathering of people for discussion or decision-making.", descriptionVietnamese = "Một cuộc tụ họp của mọi người để thảo luận hoặc đưa ra quyết định."),
            Word(lessonId = 13, english = "Deadline", vietnamese = "Hạn chót", pronunciation = "ˈdɛdˌlaɪn", description = "The latest time or date by which something should be completed.", descriptionVietnamese = "Thời hạn cuối cùng mà một công việc cần phải hoàn thành."),
            Word(lessonId = 13, english = "Agenda", vietnamese = "Chương trình họp", pronunciation = "əˈdʒɛndə", description = "A list of topics to be discussed in a meeting.", descriptionVietnamese = "Danh sách các chủ đề cần thảo luận trong một cuộc họp."),
            Word(lessonId = 13, english = "Minutes", vietnamese = "Biên bản cuộc họp", pronunciation = "ˈmɪnɪts", description = "A written record of everything that was discussed in a meeting.", descriptionVietnamese = "Bản ghi chép nội dung cuộc họp."),
            Word(lessonId = 13, english = "Conference call", vietnamese = "Cuộc gọi hội nghị", pronunciation = "ˈkɒnfərəns kɔːl", description = "A phone call where multiple participants are involved.", descriptionVietnamese = "Một cuộc gọi điện thoại với nhiều người tham gia."),
            Word(lessonId = 13, english = "Brainstorming session", vietnamese = "Buổi động não", pronunciation = "ˈbreɪnstɔːrmɪŋ ˈsɛʃən", description = "A meeting where people come up with new ideas and solutions.", descriptionVietnamese = "Một cuộc họp mà mọi người đưa ra các ý tưởng và giải pháp mới."),
            Word(lessonId = 13, english = "Presentation", vietnamese = "Bài thuyết trình", pronunciation = "ˌprɛzənˈteɪʃən", description = "A talk or speech given to share information with an audience.", descriptionVietnamese = "Một bài nói hoặc bài diễn thuyết để chia sẻ thông tin với khán giả."),
            Word(lessonId = 13, english = "Follow-up", vietnamese = "Theo dõi sau cuộc họp", pronunciation = "ˈfɒloʊ ˌʌp", description = "An action or communication that continues a previous discussion.", descriptionVietnamese = "Một hành động hoặc cuộc trao đổi tiếp nối một cuộc thảo luận trước đó."),
            Word(lessonId = 13, english = "Memo", vietnamese = "Bản ghi nhớ", pronunciation = "ˈmɛmoʊ", description = "A written message sent within an organization.", descriptionVietnamese = "Một thông báo bằng văn bản được gửi trong tổ chức."),

            // Email Writing
            Word(lessonId = 14, english = "Subject line", vietnamese = "Dòng tiêu đề", pronunciation = "ˈsʌbdʒɛkt laɪn", description = "The title of an email that summarizes its content.", descriptionVietnamese = "Tiêu đề của email, tóm tắt nội dung chính."),
            Word(lessonId = 14, english = "Greeting", vietnamese = "Lời chào", pronunciation = "ˈɡriːtɪŋ", description = "A polite way to start an email, such as 'Dear Sir/Madam'.", descriptionVietnamese = "Một cách lịch sự để bắt đầu email, ví dụ: 'Kính gửi...'"),
            Word(lessonId = 14, english = "Salutation", vietnamese = "Cách mở đầu", pronunciation = "ˌsæljʊˈteɪʃən", description = "A formal way to address the recipient at the beginning of an email.", descriptionVietnamese = "Cách gọi trang trọng khi bắt đầu email."),
            Word(lessonId = 14, english = "Body", vietnamese = "Nội dung chính", pronunciation = "ˈbɒdi", description = "The main text of an email containing the message.", descriptionVietnamese = "Phần nội dung chính của email chứa thông điệp cần truyền tải."),
            Word(lessonId = 14, english = "Closing", vietnamese = "Lời kết", pronunciation = "ˈkloʊzɪŋ", description = "A polite way to end an email, such as 'Best regards'.", descriptionVietnamese = "Cách kết thúc email lịch sự, ví dụ: 'Trân trọng'."),
            Word(lessonId = 14, english = "Signature", vietnamese = "Chữ ký", pronunciation = "ˈsɪɡnətʃər", description = "A block of text at the end of an email that includes the sender’s name and contact details.", descriptionVietnamese = "Phần cuối email bao gồm tên người gửi và thông tin liên hệ."),
            Word(lessonId = 14, english = "Attachment", vietnamese = "Tệp đính kèm", pronunciation = "əˈtætʃmənt", description = "A file sent along with an email.", descriptionVietnamese = "Tệp tin được gửi kèm theo email."),
            Word(lessonId = 14, english = "CC (Carbon Copy)", vietnamese = "Gửi bản sao", pronunciation = "ˌsiː ˈsiː", description = "A feature that sends a copy of the email to additional recipients.", descriptionVietnamese = "Chức năng gửi bản sao email cho nhiều người nhận."),
            Word(lessonId = 14, english = "BCC (Blind Carbon Copy)", vietnamese = "Gửi bản sao ẩn", pronunciation = "ˌbiː ˈsiː ˈsiː", description = "A feature that sends a copy of the email without revealing the recipients.", descriptionVietnamese = "Chức năng gửi bản sao email mà không hiển thị người nhận."),
            Word(lessonId = 14, english = "Reply all", vietnamese = "Trả lời tất cả", pronunciation = "rɪˈplaɪ ɔːl", description = "A function that allows a response to be sent to all recipients of the email.", descriptionVietnamese = "Tính năng cho phép gửi phản hồi đến tất cả người nhận trong email."),

            // Job Interviews
            Word(lessonId = 15, english = "Resume", vietnamese = "Sơ yếu lý lịch", pronunciation = "ˈrɛzjʊmeɪ", description = "A document summarizing a person's work experience, skills, and education.", descriptionVietnamese = "Tài liệu tóm tắt kinh nghiệm làm việc, kỹ năng và học vấn của một người."),
            Word(lessonId = 15, english = "Cover letter", vietnamese = "Thư xin việc", pronunciation = "ˈkʌvər ˈlɛtər", description = "A letter sent with a resume to introduce the applicant and highlight qualifications.", descriptionVietnamese = "Lá thư được gửi kèm sơ yếu lý lịch để giới thiệu ứng viên và nhấn mạnh năng lực."),
            Word(lessonId = 15, english = "Job description", vietnamese = "Mô tả công việc", pronunciation = "ʤɒb dɪˈskrɪpʃən", description = "A written statement outlining the responsibilities and requirements of a job.", descriptionVietnamese = "Bản mô tả bằng văn bản về trách nhiệm và yêu cầu của một công việc."),
            Word(lessonId = 15, english = "Interview", vietnamese = "Buổi phỏng vấn", pronunciation = "ˈɪntərvjuː", description = "A formal meeting where an employer asks a candidate questions about a job.", descriptionVietnamese = "Cuộc gặp chính thức nơi nhà tuyển dụng hỏi ứng viên về công việc."),
            Word(lessonId = 15, english = "Candidate", vietnamese = "Ứng viên", pronunciation = "ˈkændɪdət", description = "A person who applies for a job position.", descriptionVietnamese = "Người nộp đơn xin việc."),
            Word(lessonId = 15, english = "Recruiter", vietnamese = "Nhà tuyển dụng", pronunciation = "rɪˈkruːtər", description = "A person responsible for finding and hiring employees.", descriptionVietnamese = "Người chịu trách nhiệm tìm kiếm và tuyển dụng nhân viên."),
            Word(lessonId = 15, english = "Salary negotiation", vietnamese = "Thương lượng lương", pronunciation = "ˈsæləri nɪˌɡoʊʃiˈeɪʃən", description = "The process of discussing and agreeing on a salary.", descriptionVietnamese = "Quá trình thảo luận và đạt được thỏa thuận về mức lương."),
            Word(lessonId = 15, english = "Strengths and weaknesses", vietnamese = "Điểm mạnh và điểm yếu", pronunciation = "strɛŋkθs ænd ˈwiːknəsɪz", description = "Personal qualities that help or hinder job performance.", descriptionVietnamese = "Những phẩm chất cá nhân giúp ích hoặc cản trở hiệu suất công việc."),
            Word(lessonId = 15, english = "Dress code", vietnamese = "Quy tắc trang phục", pronunciation = "drɛs koʊd", description = "A set of rules about what kind of clothes employees should wear.", descriptionVietnamese = "Bộ quy tắc về trang phục nhân viên nên mặc."),
            Word(lessonId = 15, english = "Follow-up email", vietnamese = "Email theo dõi", pronunciation = "ˈfɑːloʊ ʌp ˈiːmeɪl", description = "A message sent after an interview to thank the interviewer and express interest.", descriptionVietnamese = "Email được gửi sau phỏng vấn để cảm ơn người phỏng vấn và thể hiện sự quan tâm."),

            // Shopping Dialogues
            Word(lessonId = 16, english = "Price", vietnamese = "Giá cả", pronunciation = "praɪs", description = "The amount of money required to buy something.", descriptionVietnamese = "Số tiền cần trả để mua một thứ gì đó."),
            Word(lessonId = 16, english = "Discount", vietnamese = "Giảm giá", pronunciation = "ˈdɪskaʊnt", description = "A reduction in the price of an item.", descriptionVietnamese = "Sự giảm giá của một mặt hàng."),
            Word(lessonId = 16, english = "Receipt", vietnamese = "Hóa đơn", pronunciation = "rɪˈsiːt", description = "A document that proves payment has been made.", descriptionVietnamese = "Giấy tờ chứng minh đã thanh toán."),
            Word(lessonId = 16, english = "Cashier", vietnamese = "Thu ngân", pronunciation = "kæˈʃɪr", description = "A person responsible for handling payments in a store.", descriptionVietnamese = "Người chịu trách nhiệm xử lý thanh toán trong cửa hàng."),
            Word(lessonId = 16, english = "Exchange", vietnamese = "Đổi hàng", pronunciation = "ɪksˈʧeɪndʒ", description = "The process of returning an item and getting another one in return.", descriptionVietnamese = "Quá trình trả lại một món hàng và lấy món khác thay thế."),
            Word(lessonId = 16, english = "Refund", vietnamese = "Hoàn tiền", pronunciation = "ˈriːfʌnd", description = "Money returned to a customer when they return an item.", descriptionVietnamese = "Số tiền được trả lại khi khách hàng trả hàng."),
            Word(lessonId = 16, english = "Fitting room", vietnamese = "Phòng thử đồ", pronunciation = "ˈfɪtɪŋ ruːm", description = "A small room where customers try on clothes before buying.", descriptionVietnamese = "Phòng nhỏ nơi khách thử quần áo trước khi mua."),
            Word(lessonId = 16, english = "Shopping cart", vietnamese = "Giỏ hàng", pronunciation = "ˈʃɒpɪŋ kɑːrt", description = "A wheeled cart used to carry items while shopping.", descriptionVietnamese = "Xe đẩy có bánh để mang hàng hóa khi mua sắm."),
            Word(lessonId = 16, english = "Buy one get one free", vietnamese = "Mua một tặng một", pronunciation = "baɪ wʌn ɡɛt wʌn friː", description = "A special offer where customers get an extra item for free.", descriptionVietnamese = "Ưu đãi đặc biệt khi khách hàng mua một món sẽ được tặng thêm một món."),
            Word(lessonId = 16, english = "Out of stock", vietnamese = "Hết hàng", pronunciation = "aʊt ʌv stɒk", description = "When a store does not have a certain product available.", descriptionVietnamese = "Khi cửa hàng không còn sản phẩm đó để bán."),

            // Making Appointments
            Word(lessonId = 17, english = "Schedule", vietnamese = "Lịch trình", pronunciation = "ˈskɛdʒuːl", description = "A plan that lists when certain events will happen.", descriptionVietnamese = "Một kế hoạch liệt kê thời gian diễn ra các sự kiện."),
            Word(lessonId = 17, english = "Availability", vietnamese = "Tình trạng rảnh", pronunciation = "əˌveɪləˈbɪləti", description = "The time when someone is free to meet or do something.", descriptionVietnamese = "Khoảng thời gian ai đó rảnh để gặp hoặc làm gì đó."),
            Word(lessonId = 17, english = "Reschedule", vietnamese = "Dời lịch", pronunciation = "ˌriːˈskɛdʒuːl", description = "To change the time of an appointment to a later date.", descriptionVietnamese = "Thay đổi thời gian của một cuộc hẹn sang ngày khác."),
            Word(lessonId = 17, english = "Cancel", vietnamese = "Hủy lịch hẹn", pronunciation = "ˈkænsəl", description = "To call off a scheduled appointment.", descriptionVietnamese = "Hủy bỏ một cuộc hẹn đã lên lịch."),
            Word(lessonId = 17, english = "Confirmation", vietnamese = "Xác nhận", pronunciation = "ˌkɒnfɪˈmeɪʃən", description = "A statement that something has been arranged or agreed upon.", descriptionVietnamese = "Một thông báo xác nhận rằng điều gì đó đã được sắp xếp."),
            Word(lessonId = 17, english = "Reminder", vietnamese = "Lời nhắc", pronunciation = "rɪˈmaɪndər", description = "A notification or message to help someone remember an appointment.", descriptionVietnamese = "Thông báo hoặc tin nhắn nhắc nhở ai đó về cuộc hẹn."),
            Word(lessonId = 17, english = "Appointment slot", vietnamese = "Khung giờ hẹn", pronunciation = "əˈpɔɪntmənt slɒt", description = "A specific time set aside for an appointment.", descriptionVietnamese = "Một khoảng thời gian cụ thể được dành cho một cuộc hẹn."),
            Word(lessonId = 17, english = "No-show", vietnamese = "Không đến hẹn", pronunciation = "noʊ ʃoʊ", description = "A person who does not arrive for an appointment without notice.", descriptionVietnamese = "Người không xuất hiện tại cuộc hẹn mà không thông báo trước."),
            Word(lessonId = 17, english = "Walk-in", vietnamese = "Khách không hẹn trước", pronunciation = "wɔːk ɪn", description = "A person who arrives without an appointment.", descriptionVietnamese = "Người đến mà không đặt lịch trước."),
            Word(lessonId = 17, english = "Fully booked", vietnamese = "Kín lịch", pronunciation = "ˈfʊli bʊkt", description = "When all available appointment slots are taken.", descriptionVietnamese = "Khi tất cả khung giờ hẹn đã được đặt."),

            // Small Talk
            Word(lessonId = 18, english = "Greeting", vietnamese = "Lời chào", pronunciation = "ˈɡriːtɪŋ", description = "A polite way to start a conversation.", descriptionVietnamese = "Cách lịch sự để bắt đầu một cuộc trò chuyện."),
            Word(lessonId = 18, english = "Weather", vietnamese = "Thời tiết", pronunciation = "ˈwɛðər", description = "A common topic for casual conversation.", descriptionVietnamese = "Chủ đề phổ biến trong các cuộc trò chuyện ngẫu nhiên."),
            Word(lessonId = 18, english = "Compliment", vietnamese = "Lời khen", pronunciation = "ˈkɑːmplɪmənt", description = "A polite expression of praise or admiration.", descriptionVietnamese = "Cách bày tỏ sự khen ngợi hoặc ngưỡng mộ."),
            Word(lessonId = 18, english = "Icebreaker", vietnamese = "Câu mở đầu", pronunciation = "ˈaɪsˌbreɪkər", description = "A statement or question that starts a conversation easily.", descriptionVietnamese = "Câu hỏi hoặc câu nói giúp bắt đầu trò chuyện một cách tự nhiên."),
            Word(lessonId = 18, english = "Hobby", vietnamese = "Sở thích", pronunciation = "ˈhɑːbi", description = "An activity someone enjoys doing in their free time.", descriptionVietnamese = "Hoạt động mà ai đó yêu thích làm trong thời gian rảnh."),
            Word(lessonId = 18, english = "Current events", vietnamese = "Tin tức hiện tại", pronunciation = "ˈkʌrənt ɪˈvɛnts", description = "Recent news or happenings that people talk about.", descriptionVietnamese = "Tin tức mới hoặc sự kiện mà mọi người thường thảo luận."),
            Word(lessonId = 18, english = "Body language", vietnamese = "Ngôn ngữ cơ thể", pronunciation = "ˈbɒdi ˈlæŋɡwɪdʒ", description = "Non-verbal signals that convey meaning in communication.", descriptionVietnamese = "Các tín hiệu không lời truyền đạt ý nghĩa trong giao tiếp."),
            Word(lessonId = 18, english = "Chit-chat", vietnamese = "Trò chuyện phiếm", pronunciation = "ʧɪt-ʧæt", description = "Casual and light-hearted conversation.", descriptionVietnamese = "Cuộc trò chuyện nhẹ nhàng, không quá sâu sắc."),
            Word(lessonId = 18, english = "Awkward silence", vietnamese = "Khoảnh khắc im lặng gượng gạo", pronunciation = "ˈɔːkwərd ˈsaɪləns", description = "A moment of uncomfortable quiet in a conversation.", descriptionVietnamese = "Khoảnh khắc im lặng không thoải mái trong cuộc trò chuyện."),
            Word(lessonId = 18, english = "Farewell", vietnamese = "Lời tạm biệt", pronunciation = "ˌfɛrˈwɛl", description = "A polite way to end a conversation or say goodbye.", descriptionVietnamese = "Cách lịch sự để kết thúc cuộc trò chuyện hoặc nói lời tạm biệt."),

            // At the Airport
            Word(lessonId = 19, english = "Airport", vietnamese = "Sân bay", pronunciation = "ˈɛrˌpɔrt", description = "A place where aircraft regularly take off and land, with buildings for passengers.", descriptionVietnamese = "Nơi máy bay cất cánh và hạ cánh thường xuyên, có các tòa nhà phục vụ hành khách."),
            Word(lessonId = 19, english = "Flight", vietnamese = "Chuyến bay", pronunciation = "flaɪt", description = "A journey made by an aircraft, especially a commercial one.", descriptionVietnamese = "Một hành trình bằng máy bay, đặc biệt là chuyến bay thương mại."),
            Word(lessonId = 19, english = "Customs", vietnamese = "Hải quan", pronunciation = "ˈkʌstəmz", description = "The place at an airport where officials check incoming goods, travelers, or luggage.", descriptionVietnamese = "Nơi ở sân bay nơi nhân viên kiểm tra hàng hóa, hành khách hoặc hành lý đến."),
            Word(lessonId = 19, english = "Layover", vietnamese = "Quá cảnh", pronunciation = "ˈleɪoʊvər", description = "A short stay at a place in between parts of a journey.", descriptionVietnamese = "Khoảng thời gian dừng chân ngắn giữa các chặng của một chuyến đi."),
            Word(lessonId = 19, english = "Passport", vietnamese = "Hộ chiếu", pronunciation = "ˈpæspɔrt", description = "An official document issued by a government, certifying the holder's identity and citizenship.", descriptionVietnamese = "Giấy tờ chính thức do chính phủ cấp, xác nhận danh tính và quốc tịch của người sở hữu."),
            Word(lessonId = 19, english = "Visa", vietnamese = "Thị thực", pronunciation = "ˈviːzə", description = "An endorsement on a passport indicating that the holder is allowed to enter, leave, or stay for a specified period of time in a country.", descriptionVietnamese = "Dấu xác nhận trên hộ chiếu cho phép người sở hữu nhập cảnh, xuất cảnh hoặc lưu trú trong một khoảng thời gian nhất định."),
            Word(lessonId = 19, english = "Boarding", vietnamese = "Lên máy bay", pronunciation = "ˈbɔːrdɪŋ", description = "The process of getting onto a plane before departure.", descriptionVietnamese = "Quá trình lên máy bay trước khi khởi hành."),
            Word(lessonId = 19, english = "Check-in", vietnamese = "Làm thủ tục", pronunciation = "ʧɛk ɪn", description = "The process of confirming your presence for a flight and receiving a boarding pass.", descriptionVietnamese = "Quá trình xác nhận sự có mặt của bạn cho chuyến bay và nhận thẻ lên máy bay."),
            Word(lessonId = 19, english = "Luggage", vietnamese = "Hành lý", pronunciation = "ˈlʌɡɪʤ", description = "Bags and suitcases used for traveling.", descriptionVietnamese = "Túi xách và vali dùng cho việc đi du lịch."),
            Word(lessonId = 19, english = "Security check", vietnamese = "Kiểm tra an ninh", pronunciation = "sɪˈkjʊrəti ʧɛk", description = "A checkpoint where passengers and luggage are screened for security threats.", descriptionVietnamese = "Một điểm kiểm tra nơi hành khách và hành lý được kiểm tra an ninh."),
            Word(lessonId = 19, english = "Boarding pass", vietnamese = "Thẻ lên máy bay", pronunciation = "ˈbɔːrdɪŋ pæs", description = "A document that allows a passenger to enter the restricted area of an airport and board the aircraft.", descriptionVietnamese = "Giấy tờ cho phép hành khách vào khu vực hạn chế của sân bay và lên máy bay."),
            Word(lessonId = 19, english = "Baggage claim", vietnamese = "Khu nhận hành lý", pronunciation = "ˈbæɡɪdʒ kleɪm", description = "The area in an airport where arriving passengers collect their checked luggage.", descriptionVietnamese = "Khu vực sân bay nơi hành khách đến nhận hành lý ký gửi."),
            Word(lessonId = 19, english = "Departure gate", vietnamese = "Cửa khởi hành", pronunciation = "dɪˈpɑːrtʃər ɡeɪt", description = "The area where passengers wait before boarding the plane.", descriptionVietnamese = "Khu vực nơi hành khách chờ trước khi lên máy bay."),
            Word(lessonId = 19, english = "International terminal", vietnamese = "Nhà ga quốc tế", pronunciation = "ˌɪntərˈnæʃənəl ˈtɜrmɪnəl", description = "A part of an airport that handles flights traveling to and from other countries.", descriptionVietnamese = "Một phần của sân bay phục vụ các chuyến bay quốc tế."),
            Word(lessonId = 19, english = "Delayed flight", vietnamese = "Chuyến bay bị hoãn", pronunciation = "dɪˈleɪd flaɪt", description = "A flight that is postponed and does not leave on time.", descriptionVietnamese = "Một chuyến bay bị hoãn và không khởi hành đúng giờ."),

            // Hotel Conversations
            Word(lessonId = 20, english = "Hotel", vietnamese = "Khách sạn", pronunciation = "hoʊˈtɛl", description = "A place where people stay temporarily when traveling.", descriptionVietnamese = "Nơi mọi người ở tạm thời khi đi du lịch."),
            Word(lessonId = 20, english = "Lobby", vietnamese = "Sảnh khách sạn", pronunciation = "ˈlɑːbi", description = "The entrance hall of a hotel where guests check in.", descriptionVietnamese = "Khu vực sảnh của khách sạn nơi khách làm thủ tục nhận phòng."),
            Word(lessonId = 20, english = "Key", vietnamese = "Chìa khóa", pronunciation = "kiː", description = "A device used to open and lock a door in a hotel room.", descriptionVietnamese = "Một dụng cụ dùng để mở và khóa cửa phòng khách sạn."),
            Word(lessonId = 20, english = "Guest", vietnamese = "Khách lưu trú", pronunciation = "ɡɛst", description = "A person who stays in a hotel.", descriptionVietnamese = "Người lưu trú tại khách sạn."),
            Word(lessonId = 20, english = "Reservation", vietnamese = "Đặt chỗ trước", pronunciation = "ˌrɛzərˈveɪʃən", description = "An arrangement to have a room, table, or seat held for you at a hotel, restaurant, etc.", descriptionVietnamese = "Sự sắp xếp để có phòng, bàn hoặc ghế được giữ cho bạn tại khách sạn, nhà hàng, v.v."),
            Word(lessonId = 20, english = "Housekeeping", vietnamese = "Dọn phòng", pronunciation = "ˈhaʊskiːpɪŋ", description = "The department responsible for cleaning rooms and public areas in a hotel.", descriptionVietnamese = "Bộ phận chịu trách nhiệm dọn dẹp phòng và khu vực công cộng trong khách sạn."),
            Word(lessonId = 20, english = "Check-in", vietnamese = "Làm thủ tục nhận phòng", pronunciation = "ʧɛk ɪn", description = "The process of registering upon arrival at a hotel.", descriptionVietnamese = "Quá trình đăng ký khi đến khách sạn."),
            Word(lessonId = 20, english = "Check-out", vietnamese = "Trả phòng", pronunciation = "ʧɛk aʊt", description = "The process of leaving a hotel and paying the bill.", descriptionVietnamese = "Quá trình rời khách sạn và thanh toán hóa đơn."),
            Word(lessonId = 20, english = "Room service", vietnamese = "Dịch vụ phòng", pronunciation = "ruːm ˈsɜːrvɪs", description = "A service in a hotel where food and drinks are delivered to a guest’s room.", descriptionVietnamese = "Dịch vụ trong khách sạn cung cấp thức ăn và đồ uống đến phòng khách."),
            Word(lessonId = 20, english = "Mini-bar", vietnamese = "Tủ lạnh mini", pronunciation = "ˈmɪni bɑːr", description = "A small fridge in a hotel room stocked with drinks and snacks.", descriptionVietnamese = "Một tủ lạnh nhỏ trong phòng khách sạn chứa đồ uống và đồ ăn nhẹ."),
            Word(lessonId = 20, english = "Wake-up call", vietnamese = "Cuộc gọi báo thức", pronunciation = "ˈweɪk ʌp kɔːl", description = "A service in a hotel where staff calls guests at a requested time to wake them up.", descriptionVietnamese = "Dịch vụ trong khách sạn nơi nhân viên gọi khách vào thời gian yêu cầu để đánh thức họ."),
            Word(lessonId = 20, english = "Late check-out", vietnamese = "Trả phòng muộn", pronunciation = "leɪt ʧɛk aʊt", description = "An arrangement that allows a guest to stay beyond the usual check-out time.", descriptionVietnamese = "Sự sắp xếp cho phép khách ở lại quá giờ trả phòng thông thường."),

            // Ordering Food
            Word(lessonId = 21, english = "Menu", vietnamese = "Thực đơn", pronunciation = "ˈmɛnjuː", description = "A list of dishes available at a restaurant.", descriptionVietnamese = "Danh sách các món ăn có sẵn tại nhà hàng."),
            Word(lessonId = 21, english = "Waiter", vietnamese = "Người phục vụ nam", pronunciation = "ˈweɪtər", description = "A man who serves food and drinks in a restaurant.", descriptionVietnamese = "Người đàn ông phục vụ đồ ăn và đồ uống trong nhà hàng."),
            Word(lessonId = 21, english = "Waitress", vietnamese = "Người phục vụ nữ", pronunciation = "ˈweɪtrɪs", description = "A woman who serves food and drinks in a restaurant.", descriptionVietnamese = "Người phụ nữ phục vụ đồ ăn và đồ uống trong nhà hàng."),
            Word(lessonId = 21, english = "Chef", vietnamese = "Bếp trưởng", pronunciation = "ʃɛf", description = "A professional cook, especially the head cook in a restaurant.", descriptionVietnamese = "Đầu bếp chuyên nghiệp, đặc biệt là bếp trưởng trong một nhà hàng."),
            Word(lessonId = 21, english = "Bill", vietnamese = "Hóa đơn", pronunciation = "bɪl", description = "A statement of the money owed for goods or services.", descriptionVietnamese = "Bảng kê số tiền phải trả cho hàng hóa hoặc dịch vụ."),
            Word(lessonId = 21, english = "Tip", vietnamese = "Tiền boa", pronunciation = "tɪp", description = "A small amount of money given to service workers for good service.", descriptionVietnamese = "Một khoản tiền nhỏ được tặng cho nhân viên phục vụ để cảm ơn."),
            Word(lessonId = 21, english = "Appetizer", vietnamese = "Món khai vị", pronunciation = "ˈæpɪˌtaɪzər", description = "A small dish served before the main course.", descriptionVietnamese = "Một món ăn nhẹ được phục vụ trước món chính."),
            Word(lessonId = 21, english = "Side dish", vietnamese = "Món ăn kèm", pronunciation = "saɪd dɪʃ", description = "A small portion of food served with the main course.", descriptionVietnamese = "Một phần nhỏ của thức ăn được phục vụ kèm theo món chính."),
            Word(lessonId = 21, english = "Main course", vietnamese = "Món chính", pronunciation = "meɪn kɔːrs", description = "The main part of a meal.", descriptionVietnamese = "Phần chính của bữa ăn."),
            Word(lessonId = 21, english = "Dessert", vietnamese = "Tráng miệng", pronunciation = "dɪˈzɜːrt", description = "A sweet course typically served at the end of a meal.", descriptionVietnamese = "Một món ngọt thường được phục vụ vào cuối bữa ăn."),
            Word(lessonId = 21, english = "Takeaway", vietnamese = "Món mang đi", pronunciation = "ˈteɪkəˌweɪ", description = "Food prepared in a restaurant and eaten elsewhere.", descriptionVietnamese = "Thức ăn được chuẩn bị trong nhà hàng nhưng được mang đi ăn ở nơi khác."),
            Word(lessonId = 21, english = "Special of the day", vietnamese = "Món đặc biệt trong ngày", pronunciation = "ˈspɛʃəl ʌv ðə deɪ", description = "A unique dish prepared only for a particular day.", descriptionVietnamese = "Một món ăn đặc biệt chỉ được chế biến cho một ngày cụ thể."),

            // Movie
            Word(
                lessonId =  22,
                english =  "Blockbuster",
                vietnamese =  "Phim bom tấn",
                pronunciation =  "ˈblɑːkˌbʌstər",
                description =  "A very popular and successful movie.",
                descriptionVietnamese =  "Một bộ phim rất nổi tiếng và thành công.",
            ),

            Word(
                lessonId =  22,
                english =  "Genre",
                vietnamese =  "Thể loại",
                pronunciation =  "ˈʒɑːnrə",
                description =  "A category of movies such as comedy, horror, or drama.",
                descriptionVietnamese =  "Một thể loại phim như hài, kinh dị hoặc chính kịch.",
            ),

            Word(
                lessonId =  22,
                english =  "Plot",
                vietnamese =  "Cốt truyện",
                pronunciation =  "plɑːt",
                description =  "The main events of a movie, presented in a sequence.",
                descriptionVietnamese =  "Các sự kiện chính của bộ phim được trình bày theo trình tự.",
            ),

            Word(
                lessonId =  22,
                english =  "Character",
                vietnamese =  "Nhân vật",
                pronunciation =  "ˈkærəktər",
                description =  "A person or being in a movie.",
                descriptionVietnamese =  "Một người hoặc nhân vật trong phim.",
            ),

            Word(
                lessonId =  22,
                english =  "Director",
                vietnamese =  "Đạo diễn",
                pronunciation =  "dəˈrɛktər",
                description =  "The person who supervises the actors and the filming of a movie.",
                descriptionVietnamese =  "Người giám sát diễn viên và quá trình quay phim.",
            ),

            Word(
                lessonId =  22,
                english =  "Actor",
                vietnamese =  "Diễn viên nam",
                pronunciation =  "ˈæktər",
                description =  "A male person who plays a role in a movie.",
                descriptionVietnamese =  "Một người nam đóng vai trong phim.",
            ),

            Word(
                lessonId =  22,
                english =  "Actress",
                vietnamese =  "Diễn viên nữ",
                pronunciation =  "ˈæktrəs",
                description =  "A female person who plays a role in a movie.",
                descriptionVietnamese =  "Một người nữ đóng vai trong phim.",
            ),

            Word(
                lessonId =  22,
                english =  "Scene",
                vietnamese =  "Cảnh phim",
                pronunciation =  "siːn",
                description =  "A part of a movie set in a single location or continuous time.",
                descriptionVietnamese =  "Một phần của bộ phim diễn ra ở một địa điểm hoặc thời gian liên tục.",
            ),

            Word(
                lessonId =  22,
                english =  "Script",
                vietnamese =  "Kịch bản",
                pronunciation =  "skrɪpt",
                description =  "The written text of a movie, including dialogue and directions.",
                descriptionVietnamese =  "Văn bản viết của bộ phim, bao gồm lời thoại và hướng dẫn.",
            ),

            Word(
                lessonId =  22,
                english =  "Soundtrack",
                vietnamese =  "Nhạc phim",
                pronunciation =  "ˈsaʊndˌtræk",
                description =  "The music used in a movie.",
                descriptionVietnamese =  "Âm nhạc được sử dụng trong phim.",
            ),

            Word(
                lessonId =  22,
                english =  "Sequel",
                vietnamese =  "Phần tiếp theo",
                pronunciation =  "ˈsiːkwəl",
                description =  "A movie that continues the story of a previous one.",
                descriptionVietnamese =  "Một bộ phim tiếp tục câu chuyện của phần trước.",
            ),

            Word(
                lessonId =  22,
                english =  "Premiere",
                vietnamese =  "Buổi công chiếu",
                pronunciation =  "prɪˈmɪr",
                description =  "The first official showing of a movie.",
                descriptionVietnamese =  "Buổi chiếu chính thức đầu tiên của một bộ phim.",
            ),

            Word(
                lessonId =  22,
                english =  "Subtitles",
                vietnamese =  "Phụ đề",
                pronunciation =  "ˈsʌbˌtaɪtəlz",
                description =  "Text at the bottom of the screen translating or transcribing dialogue.",
                descriptionVietnamese =  "Chữ hiển thị dưới màn hình dịch hoặc chép lời thoại.",
            ),


            // Music
            Word(
                lessonId = 23,
                english = "Melody",
                vietnamese = "Giai điệu",
                pronunciation = "ˈmɛlədi",
                description = "A sequence of musical notes that are pleasant to hear.",
                descriptionVietnamese = "Một chuỗi các nốt nhạc dễ nghe và du dương."
            ),

            Word(
                lessonId = 23,
                english = "Lyrics",
                vietnamese = "Lời bài hát",
                pronunciation = "ˈlɪrɪks",
                description = "The words of a song.",
                descriptionVietnamese = "Lời của một bài hát."
            ),

            Word(
                lessonId = 23,
                english = "Chorus",
                vietnamese = "Điệp khúc",
                pronunciation = "ˈkɔːrəs",
                description = "The part of a song that is repeated several times.",
                descriptionVietnamese = "Phần của bài hát được lặp lại nhiều lần."
            ),

            Word(
                lessonId = 23,
                english = "Instrument",
                vietnamese = "Nhạc cụ",
                pronunciation = "ˈɪnstrəmənt",
                description = "A tool used to produce music, like a guitar or piano.",
                descriptionVietnamese = "Công cụ dùng để tạo ra âm nhạc, như đàn guitar hoặc piano."
            ),

            Word(
                lessonId = 23,
                english = "Rhythm",
                vietnamese = "Nhịp điệu",
                pronunciation = "ˈrɪðəm",
                description = "The pattern of beats or timing in music.",
                descriptionVietnamese = "Mẫu nhịp hoặc thời gian trong âm nhạc."
            ),

            Word(
                lessonId = 23,
                english = "Harmony",
                vietnamese = "Hòa âm",
                pronunciation = "ˈhɑːrməni",
                description = "The combination of different musical notes played together.",
                descriptionVietnamese = "Sự kết hợp của các nốt nhạc khác nhau được chơi cùng nhau."
            ),

            Word(
                lessonId = 23,
                english = "Album",
                vietnamese = "Album",
                pronunciation = "ˈælbəm",
                description = "A collection of songs released together.",
                descriptionVietnamese = "Một bộ sưu tập các bài hát được phát hành cùng nhau."
            ),

            Word(
                lessonId = 23,
                english = "Genre",
                vietnamese = "Thể loại",
                pronunciation = "ˈʒɑːnrə",
                description = "A category of music such as pop, rock, or jazz.",
                descriptionVietnamese = "Một thể loại âm nhạc như pop, rock, hoặc jazz."
            ),

            Word(
                lessonId = 23,
                english = "Tempo",
                vietnamese = "Tốc độ nhạc",
                pronunciation = "ˈtɛmpoʊ",
                description = "The speed at which a piece of music is played.",
                descriptionVietnamese = "Tốc độ mà một bản nhạc được chơi."
            ),

            Word(
                lessonId = 23,
                english = "Band",
                vietnamese = "Ban nhạc",
                pronunciation = "bænd",
                description = "A group of people who play music together.",
                descriptionVietnamese = "Một nhóm người chơi nhạc cùng nhau."
            ),

            Word(
                lessonId = 23,
                english = "Solo",
                vietnamese = "Độc tấu",
                pronunciation = "ˈsoʊloʊ",
                description = "A performance by a single musician or singer.",
                descriptionVietnamese = "Phần trình diễn của một nghệ sĩ hoặc ca sĩ duy nhất."
            ),

            Word(
                lessonId = 23,
                english = "Live performance",
                vietnamese = "Biểu diễn trực tiếp",
                pronunciation = "laɪv pərˈfɔːrməns",
                description = "Music performed in front of an audience.",
                descriptionVietnamese = "Âm nhạc được biểu diễn trước khán giả."
            ),

            Word(
                lessonId = 23,
                english = "Hit song",
                vietnamese = "Bản hit",
                pronunciation = "hɪt sɔːŋ",
                description = "A song that becomes very popular.",
                descriptionVietnamese = "Một bài hát trở nên rất nổi tiếng."
            ),

            // video game
            Word(
                lessonId = 24,
                english = "Game console",
                vietnamese = "Máy chơi game",
                pronunciation = "ɡeɪm ˈkɒnsəʊl",
                description = "A device used to play video games.",
                descriptionVietnamese = "Thiết bị dùng để chơi trò chơi điện tử."
            ),

            Word(
                lessonId = 24,
                english = "Controller",
                vietnamese = "Tay cầm điều khiển",
                pronunciation = "kənˈtrəʊlər",
                description = "A device used to control a video game.",
                descriptionVietnamese = "Thiết bị dùng để điều khiển trò chơi điện tử."
            ),

            Word(
                lessonId = 24,
                english = "Level up",
                vietnamese = "Lên cấp",
                pronunciation = "ˈlɛvəl ʌp",
                description = "To gain enough experience to reach the next level.",
                descriptionVietnamese = "Đạt đủ kinh nghiệm để lên cấp tiếp theo."
            ),

            Word(
                lessonId = 24,
                english = "Multiplayer",
                vietnamese = "Nhiều người chơi",
                pronunciation = "ˈmʌltipleɪər",
                description = "A game mode where multiple people can play together.",
                descriptionVietnamese = "Chế độ chơi cho phép nhiều người cùng tham gia."
            ),

            Word(
                lessonId = 24,
                english = "Boss fight",
                vietnamese = "Trận đấu trùm",
                pronunciation = "bɒs faɪt",
                description = "A challenging battle against a powerful enemy in a game.",
                descriptionVietnamese = "Trận chiến khó khăn với kẻ địch mạnh trong trò chơi."
            ),

            Word(
                lessonId = 24,
                english = "Power-up",
                vietnamese = "Vật phẩm tăng sức mạnh",
                pronunciation = "ˈpaʊər ʌp",
                description = "An item that gives temporary advantages to a player.",
                descriptionVietnamese = "Vật phẩm mang lại lợi thế tạm thời cho người chơi."
            ),

            Word(
                lessonId = 24,
                english = "Health bar",
                vietnamese = "Thanh máu",
                pronunciation = "hɛlθ bɑːr",
                description = "A visual indicator of a character’s remaining health.",
                descriptionVietnamese = "Thanh hiển thị lượng máu còn lại của nhân vật."
            ),

            Word(
                lessonId = 24,
                english = "Save point",
                vietnamese = "Điểm lưu",
                pronunciation = "seɪv pɔɪnt",
                description = "A spot in a game where players can save their progress.",
                descriptionVietnamese = "Vị trí trong trò chơi nơi người chơi có thể lưu tiến trình."
            ),

            Word(
                lessonId = 24,
                english = "Quest",
                vietnamese = "Nhiệm vụ",
                pronunciation = "kwɛst",
                description = "A task or mission a player must complete in a game.",
                descriptionVietnamese = "Một nhiệm vụ mà người chơi phải hoàn thành trong game."
            ),

            Word(
                lessonId = 24,
                english = "Inventory",
                vietnamese = "Hành trang",
                pronunciation = "ˈɪnvənˌtɔri",
                description = "A list of items a player carries in the game.",
                descriptionVietnamese = "Danh sách các vật phẩm người chơi mang theo trong game."
            ),

            Word(
                lessonId = 24,
                english = "Cutscene",
                vietnamese = "Đoạn phim cắt cảnh",
                pronunciation = "ˈkʌtsiːn",
                description = "A non-interactive movie in a game that advances the story.",
                descriptionVietnamese = "Đoạn phim trong game giúp kể tiếp cốt truyện, không thể điều khiển."
            ),

            Word(
                lessonId = 24,
                english = "Lag",
                vietnamese = "Độ trễ",
                pronunciation = "læɡ",
                description = "A delay between a player’s action and the game’s response.",
                descriptionVietnamese = "Độ trễ giữa thao tác của người chơi và phản hồi trong game."
            ),

            Word(
                lessonId = 24,
                english = "Game over",
                vietnamese = "Trò chơi kết thúc",
                pronunciation = "ɡeɪm ˈəʊvər",
                description = "A message shown when the player loses all lives or fails.",
                descriptionVietnamese = "Thông báo xuất hiện khi người chơi thua hoặc hết mạng."
            ),

//            Lesson(id = 25, categoryId = 8, name = "Subject"),
            Word(
                lessonId = 25,
                english = "Mathematics",
                vietnamese = "Toán học",
                pronunciation = "ˌmæθəˈmætɪks",
                description = "The study of numbers, quantities, and shapes.",
                descriptionVietnamese = "Môn học về các con số, số lượng và hình dạng."
            ),

            Word(
                lessonId = 25,
                english = "Biology",
                vietnamese = "Sinh học",
                pronunciation = "baɪˈɒlədʒi",
                description = "The science of life and living organisms.",
                descriptionVietnamese = "Khoa học nghiên cứu về sự sống và các sinh vật sống."
            ),

            Word(
                lessonId = 25,
                english = "Chemistry",
                vietnamese = "Hóa học",
                pronunciation = "ˈkɛmɪstri",
                description = "The study of substances and how they interact.",
                descriptionVietnamese = "Môn học nghiên cứu các chất và cách chúng tương tác."
            ),

            Word(
                lessonId = 25,
                english = "Physics",
                vietnamese = "Vật lý",
                pronunciation = "ˈfɪzɪks",
                description = "The study of matter, energy, and the laws of nature.",
                descriptionVietnamese = "Môn học về vật chất, năng lượng và các định luật tự nhiên."
            ),

            Word(
                lessonId = 25,
                english = "History",
                vietnamese = "Lịch sử",
                pronunciation = "ˈhɪstəri",
                description = "The study of past events and civilizations.",
                descriptionVietnamese = "Nghiên cứu về các sự kiện và nền văn minh trong quá khứ."
            ),

            Word(
                lessonId = 25,
                english = "Geography",
                vietnamese = "Địa lý",
                pronunciation = "dʒiˈɒɡrəfi",
                description = "The study of the Earth and its features.",
                descriptionVietnamese = "Nghiên cứu về Trái Đất và các đặc điểm của nó."
            ),

            Word(
                lessonId = 25,
                english = "Literature",
                vietnamese = "Văn học",
                pronunciation = "ˈlɪtərətʃər",
                description = "The study of written works, such as poems and stories.",
                descriptionVietnamese = "Môn học về các tác phẩm văn học như thơ và truyện."
            ),

            Word(
                lessonId = 25,
                english = "English",
                vietnamese = "Tiếng Anh",
                pronunciation = "ˈɪŋɡlɪʃ",
                description = "The study of the English language and its use.",
                descriptionVietnamese = "Môn học về ngôn ngữ tiếng Anh và cách sử dụng nó."
            ),

            Word(
                lessonId = 25,
                english = "Information Technology",
                vietnamese = "Công nghệ thông tin",
                pronunciation = "ˌɪnfəˈmeɪʃən tɛkˈnɒlədʒi",
                description = "The study and use of computers and software.",
                descriptionVietnamese = "Học và sử dụng máy tính và phần mềm."
            ),

            Word(
                lessonId = 25,
                english = "Art",
                vietnamese = "Mỹ thuật",
                pronunciation = "ɑːrt",
                description = "The expression of creativity through drawing, painting, etc.",
                descriptionVietnamese = "Sự thể hiện sáng tạo qua vẽ, hội họa, v.v."
            ),

            Word(
                lessonId = 25,
                english = "Music",
                vietnamese = "Âm nhạc",
                pronunciation = "ˈmjuːzɪk",
                description = "The art of arranging sounds to create a composition.",
                descriptionVietnamese = "Nghệ thuật sắp xếp âm thanh để tạo nên một bản nhạc."
            ),

            Word(
                lessonId = 25,
                english = "Physical Education",
                vietnamese = "Giáo dục thể chất",
                pronunciation = "ˈfɪzɪkəl ˌɛdjuˈkeɪʃən",
                description = "Classes that promote physical activity and fitness.",
                descriptionVietnamese = "Môn học khuyến khích hoạt động thể chất và rèn luyện sức khỏe."
            ),

            Word(
                lessonId = 25,
                english = "Civics",
                vietnamese = "Giáo dục công dân",
                pronunciation = "ˈsɪvɪks",
                description = "The study of the rights and duties of citizenship.",
                descriptionVietnamese = "Môn học về quyền và nghĩa vụ của công dân."
            ),

//            Lesson(id = 26, categoryId = 8, name = "Class"),
            Word(
                lessonId = 26,
                english = "Classroom",
                vietnamese = "Lớp học",
                pronunciation = "ˈklɑːsruːm",
                description = "A room where classes are held.",
                descriptionVietnamese = "Phòng nơi diễn ra các buổi học."
            ),

            Word(
                lessonId = 26,
                english = "Teacher",
                vietnamese = "Giáo viên",
                pronunciation = "ˈtiːtʃər",
                description = "A person who helps students learn.",
                descriptionVietnamese = "Người giúp học sinh học tập."
            ),

            Word(
                lessonId = 26,
                english = "Student",
                vietnamese = "Học sinh",
                pronunciation = "ˈstjuːdənt",
                description = "A person who is studying at a school or college.",
                descriptionVietnamese = "Người đang học tại trường học hoặc đại học."
            ),

            Word(
                lessonId = 26,
                english = "Whiteboard",
                vietnamese = "Bảng trắng",
                pronunciation = "ˈwaɪtbɔːrd",
                description = "A board for writing on with markers in classrooms.",
                descriptionVietnamese = "Bảng để viết bằng bút dạ trong lớp học."
            ),

            Word(
                lessonId = 26,
                english = "Desk",
                vietnamese = "Bàn học",
                pronunciation = "dɛsk",
                description = "A piece of furniture where students sit and study.",
                descriptionVietnamese = "Đồ dùng nội thất nơi học sinh ngồi học."
            ),

            Word(
                lessonId = 26,
                english = "Chair",
                vietnamese = "Ghế",
                pronunciation = "tʃɛər",
                description = "A piece of furniture for sitting.",
                descriptionVietnamese = "Đồ nội thất để ngồi."
            ),

            Word(
                lessonId = 26,
                english = "Notebook",
                vietnamese = "Vở ghi",
                pronunciation = "ˈnəʊtbʊk",
                description = "A book for writing notes.",
                descriptionVietnamese = "Sổ để ghi chép."
            ),

            Word(
                lessonId = 26,
                english = "Pencil",
                vietnamese = "Bút chì",
                pronunciation = "ˈpɛnsəl",
                description = "An instrument for writing or drawing.",
                descriptionVietnamese = "Dụng cụ để viết hoặc vẽ."
            ),

            Word(
                lessonId = 26,
                english = "Eraser",
                vietnamese = "Cục tẩy",
                pronunciation = "ɪˈreɪsər",
                description = "A tool used to remove pencil marks.",
                descriptionVietnamese = "Dụng cụ để xóa vết bút chì."
            ),

            Word(
                lessonId = 26,
                english = "Homework",
                vietnamese = "Bài tập về nhà",
                pronunciation = "ˈhoʊmwɜːrk",
                description = "Tasks assigned to students to complete outside class.",
                descriptionVietnamese = "Bài được giao để học sinh làm ở nhà."
            ),

            Word(
                lessonId = 26,
                english = "Exam",
                vietnamese = "Kỳ thi",
                pronunciation = "ɪɡˈzæm",
                description = "A test to measure knowledge or skills.",
                descriptionVietnamese = "Một bài kiểm tra để đánh giá kiến thức hoặc kỹ năng."
            ),

            Word(
                lessonId = 26,
                english = "Grade",
                vietnamese = "Điểm số",
                pronunciation = "ɡreɪd",
                description = "A mark given in an exam or assignment.",
                descriptionVietnamese = "Điểm được cho trong kỳ thi hoặc bài tập."
            ),

            Word(
                lessonId = 26,
                english = "Attendance",
                vietnamese = "Sự điểm danh",
                pronunciation = "əˈtɛndəns",
                description = "The act of being present at school.",
                descriptionVietnamese = "Việc có mặt tại trường học."
            ),

//            Lesson(id = 27, categoryId = 8, name = "School"),
            Word(
                lessonId = 27,
                english = "School",
                vietnamese = "Trường học",
                pronunciation = "skuːl",
                description = "A place where students go to learn.",
                descriptionVietnamese = "Nơi học sinh đến để học tập."
            ),

            Word(
                lessonId = 27,
                english = "Principal",
                vietnamese = "Hiệu trưởng",
                pronunciation = "ˈprɪnsəpəl",
                description = "The person in charge of a school.",
                descriptionVietnamese = "Người điều hành một trường học."
            ),

            Word(
                lessonId = 27,
                english = "Recess",
                vietnamese = "Giờ ra chơi",
                pronunciation = "ˈriːsɛs",
                description = "A short break between classes for students to relax.",
                descriptionVietnamese = "Khoảng thời gian nghỉ ngắn giữa các tiết học để học sinh thư giãn."
            ),

            Word(
                lessonId = 27,
                english = "Cafeteria",
                vietnamese = "Căng tin",
                pronunciation = "ˌkæfəˈtɪəriə",
                description = "A place in school where students eat meals.",
                descriptionVietnamese = "Nơi trong trường học nơi học sinh ăn uống."
            ),

            Word(
                lessonId = 27,
                english = "Locker",
                vietnamese = "Tủ đựng đồ",
                pronunciation = "ˈlɒkər",
                description = "A small storage compartment used by students.",
                descriptionVietnamese = "Ngăn tủ nhỏ để học sinh cất giữ đồ dùng."
            ),

            Word(
                lessonId = 27,
                english = "Hallway",
                vietnamese = "Hành lang",
                pronunciation = "ˈhɔːlweɪ",
                description = "A passage in a school where students walk.",
                descriptionVietnamese = "Lối đi trong trường nơi học sinh di chuyển."
            ),

            Word(
                lessonId = 27,
                english = "Class schedule",
                vietnamese = "Thời khóa biểu",
                pronunciation = "klɑːs ˈʃɛdjuːl",
                description = "A plan showing the times of each class.",
                descriptionVietnamese = "Kế hoạch hiển thị thời gian của từng tiết học."
            ),

            Word(
                lessonId = 27,
                english = "Backpack",
                vietnamese = "Ba lô",
                pronunciation = "ˈbækˌpæk",
                description = "A bag used by students to carry books and supplies.",
                descriptionVietnamese = "Túi mà học sinh dùng để đựng sách vở và dụng cụ học tập."
            ),

            Word(
                lessonId = 27,
                english = "Uniform",
                vietnamese = "Đồng phục",
                pronunciation = "ˈjuːnɪfɔːm",
                description = "A special set of clothes worn by students at school.",
                descriptionVietnamese = "Bộ quần áo đặc biệt học sinh mặc tại trường."
            ),

            Word(
                lessonId = 27,
                english = "Playground",
                vietnamese = "Sân chơi",
                pronunciation = "ˈpleɪɡraʊnd",
                description = "An outdoor area where children play during breaks.",
                descriptionVietnamese = "Khu vực ngoài trời nơi trẻ em vui chơi trong giờ nghỉ."
            ),

            Word(
                lessonId = 27,
                english = "Assembly",
                vietnamese = "Buổi chào cờ",
                pronunciation = "əˈsɛmbli",
                description = "A gathering of students for announcements or events.",
                descriptionVietnamese = "Buổi tập trung học sinh để thông báo hoặc tổ chức sự kiện."
            ),

            Word(
                lessonId = 27,
                english = "Library",
                vietnamese = "Thư viện",
                pronunciation = "ˈlaɪbrəri",
                description = "A place where students can read or borrow books.",
                descriptionVietnamese = "Nơi học sinh có thể đọc hoặc mượn sách."
            ),

            Word(
                lessonId = 27,
                english = "Exam paper",
                vietnamese = "Đề thi",
                pronunciation = "ɪɡˈzæm ˈpeɪpər",
                description = "A sheet containing questions for a test.",
                descriptionVietnamese = "Tờ giấy chứa câu hỏi cho một kỳ thi."
            ),

//            Lesson(id = 28, categoryId = 9, name = "Weather and Seasons"),
            Word(
                lessonId = 28,
                english = "Sunny",
                vietnamese = "Nắng",
                pronunciation = "ˈsʌni",
                description = "When the sun is shining brightly.",
                descriptionVietnamese = "Khi mặt trời chiếu sáng rực rỡ."
            ),

            Word(
                lessonId = 28,
                english = "Rainy",
                vietnamese = "Mưa",
                pronunciation = "ˈreɪni",
                description = "Characterized by frequent or heavy rain.",
                descriptionVietnamese = "Có mưa nhiều hoặc mưa to."
            ),

            Word(
                lessonId = 28,
                english = "Cloudy",
                vietnamese = "Nhiều mây",
                pronunciation = "ˈklaʊdi",
                description = "Covered with clouds; not sunny.",
                descriptionVietnamese = "Trời có nhiều mây; không có nắng."
            ),

            Word(
                lessonId = 28,
                english = "Snowy",
                vietnamese = "Có tuyết",
                pronunciation = "ˈsnəʊi",
                description = "Having falling snow or covered with snow.",
                descriptionVietnamese = "Có tuyết rơi hoặc được bao phủ bởi tuyết."
            ),

            Word(
                lessonId = 28,
                english = "Windy",
                vietnamese = "Có gió",
                pronunciation = "ˈwɪndi",
                description = "Having a lot of wind.",
                descriptionVietnamese = "Có nhiều gió."
            ),

            Word(
                lessonId = 28,
                english = "Foggy",
                vietnamese = "Có sương mù",
                pronunciation = "ˈfɒɡi",
                description = "Full of fog, making it hard to see.",
                descriptionVietnamese = "Có nhiều sương mù, làm hạn chế tầm nhìn."
            ),

            Word(
                lessonId = 28,
                english = "Thunderstorm",
                vietnamese = "Cơn giông",
                pronunciation = "ˈθʌndərstɔːm",
                description = "A storm with thunder and lightning.",
                descriptionVietnamese = "Cơn bão có sấm sét."
            ),

            Word(
                lessonId = 28,
                english = "Hot",
                vietnamese = "Nóng",
                pronunciation = "hɒt",
                description = "Having a high temperature.",
                descriptionVietnamese = "Có nhiệt độ cao."
            ),

            Word(
                lessonId = 28,
                english = "Cold",
                vietnamese = "Lạnh",
                pronunciation = "kəʊld",
                description = "Having a low temperature.",
                descriptionVietnamese = "Có nhiệt độ thấp."
            ),

            Word(
                lessonId = 28,
                english = "Spring",
                vietnamese = "Mùa xuân",
                pronunciation = "sprɪŋ",
                description = "The season after winter and before summer.",
                descriptionVietnamese = "Mùa sau mùa đông và trước mùa hè."
            ),

            Word(
                lessonId = 28,
                english = "Summer",
                vietnamese = "Mùa hè",
                pronunciation = "ˈsʌmər",
                description = "The hottest season of the year.",
                descriptionVietnamese = "Mùa nóng nhất trong năm."
            ),

            Word(
                lessonId = 28,
                english = "Autumn",
                vietnamese = "Mùa thu",
                pronunciation = "ˈɔːtəm",
                description = "The season after summer and before winter.",
                descriptionVietnamese = "Mùa sau mùa hè và trước mùa đông."
            ),

            Word(
                lessonId = 28,
                english = "Winter",
                vietnamese = "Mùa đông",
                pronunciation = "ˈwɪntər",
                description = "The coldest season of the year.",
                descriptionVietnamese = "Mùa lạnh nhất trong năm."
            ),

//            Lesson(id = 29, categoryId = 9, name = "Natural Disasters"),
            Word(
                lessonId = 29,
                english = "Earthquake",
                vietnamese = "Động đất",
                pronunciation = "ˈɜːrθ.kweɪk",
                description = "A sudden shaking of the ground caused by movements in the earth's crust.",
                descriptionVietnamese = "Một sự rung lắc đột ngột của mặt đất do chuyển động trong vỏ trái đất."
            ),

            Word(
                lessonId = 29,
                english = "Tsunami",
                vietnamese = "Sóng thần",
                pronunciation = "tsuːˈnɑːmi",
                description = "A giant wave caused by an earthquake or volcanic eruption under the sea.",
                descriptionVietnamese = "Một con sóng khổng lồ do động đất hoặc núi lửa phun trào dưới biển gây ra."
            ),

            Word(
                lessonId = 29,
                english = "Volcanic eruption",
                vietnamese = "Núi lửa phun trào",
                pronunciation = "vɒlˈkænɪk ɪˈrʌpʃən",
                description = "When lava and gases explode from a volcano.",
                descriptionVietnamese = "Khi dung nham và khí gas phun trào từ núi lửa."
            ),

            Word(
                lessonId = 29,
                english = "Flood",
                vietnamese = "Lũ lụt",
                pronunciation = "flʌd",
                description = "An overflow of a large amount of water beyond its normal limits.",
                descriptionVietnamese = "Sự tràn ngập của một lượng lớn nước vượt khỏi giới hạn thông thường."
            ),

            Word(
                lessonId = 29,
                english = "Hurricane",
                vietnamese = "Bão (mạnh)",
                pronunciation = "ˈhʌrɪkən",
                description = "A violent storm with strong winds and rain.",
                descriptionVietnamese = "Cơn bão dữ dội với gió mạnh và mưa lớn."
            ),

            Word(
                lessonId = 29,
                english = "Tornado",
                vietnamese = "Lốc xoáy",
                pronunciation = "tɔːˈneɪdəʊ",
                description = "A violently rotating column of air extending from a thunderstorm to the ground.",
                descriptionVietnamese = "Một cột không khí quay dữ dội kéo dài từ cơn giông xuống mặt đất."
            ),

            Word(
                lessonId = 29,
                english = "Landslide",
                vietnamese = "Sạt lở đất",
                pronunciation = "ˈlændslaɪd",
                description = "The sliding down of a mass of earth or rock from a mountain or cliff.",
                descriptionVietnamese = "Sự trượt xuống của một khối đất hoặc đá từ núi hoặc vách đá."
            ),

            Word(
                lessonId = 29,
                english = "Drought",
                vietnamese = "Hạn hán",
                pronunciation = "draʊt",
                description = "A long period without rain.",
                descriptionVietnamese = "Một thời gian dài không có mưa."
            ),

            Word(
                lessonId = 29,
                english = "Wildfire",
                vietnamese = "Cháy rừng",
                pronunciation = "ˈwaɪldfaɪər",
                description = "A large fire that spreads quickly through wild land or forests.",
                descriptionVietnamese = "Một đám cháy lớn lan nhanh qua rừng hoặc đất hoang."
            ),

            Word(
                lessonId = 29,
                english = "Avalanche",
                vietnamese = "Tuyết lở",
                pronunciation = "ˈævəlɑːnʃ",
                description = "A mass of snow, ice, and rocks falling rapidly down a mountainside.",
                descriptionVietnamese = "Một khối tuyết, băng và đá rơi nhanh xuống sườn núi."
            ),

            Word(
                lessonId = 29,
                english = "Heatwave",
                vietnamese = "Đợt nắng nóng",
                pronunciation = "ˈhiːtweɪv",
                description = "A prolonged period of excessively hot weather.",
                descriptionVietnamese = "Một thời gian kéo dài của thời tiết cực kỳ nóng."
            ),

            Word(
                lessonId = 29,
                english = "Blizzard",
                vietnamese = "Bão tuyết",
                pronunciation = "ˈblɪzərd",
                description = "A severe snowstorm with strong winds.",
                descriptionVietnamese = "Một cơn bão tuyết dữ dội với gió mạnh."
            ),

            Word(
                lessonId = 29,
                english = "Aftershock",
                vietnamese = "Dư chấn",
                pronunciation = "ˈæftərʃɒk",
                description = "A smaller earthquake that follows the main shock.",
                descriptionVietnamese = "Một trận động đất nhỏ hơn xảy ra sau trận động đất chính."
            ),

//            Lesson(id = 30, categoryId = 9, name = "Protecting the Environment"),
            Word(
                lessonId = 30,
                english = "Recycling",
                vietnamese = "Tái chế",
                pronunciation = "ˌriːˈsaɪklɪŋ",
                description = "The process of converting waste into reusable material.",
                descriptionVietnamese = "Quá trình chuyển đổi rác thải thành vật liệu có thể sử dụng lại."
            ),

            Word(
                lessonId = 30,
                english = "Renewable energy",
                vietnamese = "Năng lượng tái tạo",
                pronunciation = "rɪˈnjuːəbl ˈɛnədʒi",
                description = "Energy from sources that are naturally replenished.",
                descriptionVietnamese = "Năng lượng từ các nguồn được tái tạo tự nhiên."
            ),

            Word(
                lessonId = 30,
                english = "Pollution",
                vietnamese = "Ô nhiễm",
                pronunciation = "pəˈluːʃən",
                description = "The presence of harmful substances in the environment.",
                descriptionVietnamese = "Sự xuất hiện của các chất độc hại trong môi trường."
            ),

            Word(
                lessonId = 30,
                english = "Compost",
                vietnamese = "Phân hữu cơ",
                pronunciation = "ˈkɒmpɒst",
                description = "Decayed organic material used as a plant fertilizer.",
                descriptionVietnamese = "Chất hữu cơ phân hủy dùng làm phân bón cho cây trồng."
            ),

            Word(
                lessonId = 30,
                english = "Eco-friendly",
                vietnamese = "Thân thiện với môi trường",
                pronunciation = "ˌiːkəʊˈfrɛndli",
                description = "Not harmful to the environment.",
                descriptionVietnamese = "Không gây hại cho môi trường."
            ),

            Word(
                lessonId = 30,
                english = "Carbon footprint",
                vietnamese = "Dấu chân carbon",
                pronunciation = "ˈkɑːbən ˈfʊtprɪnt",
                description = "The amount of carbon dioxide emitted by a person or organization.",
                descriptionVietnamese = "Lượng khí CO2 thải ra bởi một người hoặc tổ chức."
            ),

            Word(
                lessonId = 30,
                english = "Sustainable",
                vietnamese = "Bền vững",
                pronunciation = "səˈsteɪnəbl",
                description = "Using natural resources in a way that they are not depleted.",
                descriptionVietnamese = "Sử dụng tài nguyên thiên nhiên theo cách không làm cạn kiệt chúng."
            ),

            Word(
                lessonId = 30,
                english = "Conservation",
                vietnamese = "Sự bảo tồn",
                pronunciation = "ˌkɒnsəˈveɪʃən",
                description = "The protection of natural resources.",
                descriptionVietnamese = "Sự bảo vệ các tài nguyên thiên nhiên."
            ),

            Word(
                lessonId = 30,
                english = "Deforestation",
                vietnamese = "Phá rừng",
                pronunciation = "ˌdiːˌfɒrɪˈsteɪʃən",
                description = "The clearing of trees from a large area.",
                descriptionVietnamese = "Việc chặt phá cây trên một diện tích lớn."
            ),

            Word(
                lessonId = 30,
                english = "Greenhouse gases",
                vietnamese = "Khí nhà kính",
                pronunciation = "ˈɡriːnhaʊs ˈɡæsɪz",
                description = "Gases that trap heat in the atmosphere.",
                descriptionVietnamese = "Các loại khí giữ nhiệt trong khí quyển."
            ),

            Word(
                lessonId = 30,
                english = "Biodegradable",
                vietnamese = "Phân hủy sinh học",
                pronunciation = "ˌbaɪəʊdɪˈɡreɪdəbl",
                description = "Capable of being broken down naturally by microorganisms.",
                descriptionVietnamese = "Có khả năng bị phân hủy tự nhiên bởi vi sinh vật."
            ),

            Word(
                lessonId = 30,
                english = "Solar panel",
                vietnamese = "Tấm pin mặt trời",
                pronunciation = "ˈsəʊlə ˈpænl",
                description = "A device that converts sunlight into electricity.",
                descriptionVietnamese = "Thiết bị chuyển đổi ánh sáng mặt trời thành điện năng."
            ),

            Word(
                lessonId = 30,
                english = "Reduce, Reuse, Recycle",
                vietnamese = "Giảm thiểu, tái sử dụng, tái chế",
                pronunciation = "rɪˈdjuːs riːˈjuːz riːˈsaɪkl",
                description = "The three main ways to reduce waste and protect the environment.",
                descriptionVietnamese = "Ba cách chính để giảm thiểu rác thải và bảo vệ môi trường."
            ),

//            Lesson(id = 31, categoryId = 10, name = "Sport"),
            Word(
                lessonId = 31,
                english = "Team",
                vietnamese = "Đội",
                pronunciation = "tiːm",
                description = "A group of players forming one side in a competitive game or sport.",
                descriptionVietnamese = "Một nhóm người chơi tạo thành một bên trong một trò chơi hoặc môn thể thao cạnh tranh."
            ),

            Word(
                lessonId = 31,
                english = "Coach",
                vietnamese = "Huấn luyện viên",
                pronunciation = "kəʊtʃ",
                description = "A person who trains and instructs athletes.",
                descriptionVietnamese = "Người huấn luyện và hướng dẫn các vận động viên."
            ),

            Word(
                lessonId = 31,
                english = "Match",
                vietnamese = "Trận đấu",
                pronunciation = "mætʃ",
                description = "A contest in which people or teams compete against each other.",
                descriptionVietnamese = "Cuộc thi đấu giữa các cá nhân hoặc đội với nhau."
            ),

            Word(
                lessonId = 31,
                english = "Goal",
                vietnamese = "Bàn thắng",
                pronunciation = "ɡəʊl",
                description = "A point scored in a game by getting the ball into a special area.",
                descriptionVietnamese = "Điểm được ghi trong trò chơi khi đưa bóng vào khu vực quy định."
            ),

            Word(
                lessonId = 31,
                english = "Referee",
                vietnamese = "Trọng tài",
                pronunciation = "ˌrefəˈriː",
                description = "The official who controls the game and enforces the rules.",
                descriptionVietnamese = "Người điều khiển trận đấu và thực thi luật lệ."
            ),

            Word(
                lessonId = 31,
                english = "Score",
                vietnamese = "Tỷ số / Ghi điểm",
                pronunciation = "skɔː",
                description = "The number of points achieved in a game.",
                descriptionVietnamese = "Số điểm đạt được trong một trò chơi."
            ),

            Word(
                lessonId = 31,
                english = "Competition",
                vietnamese = "Cuộc thi / Cạnh tranh",
                pronunciation = "ˌkɒmpəˈtɪʃən",
                description = "An event where people try to win by being the best.",
                descriptionVietnamese = "Một sự kiện nơi mọi người cố gắng giành chiến thắng bằng cách trở nên giỏi nhất."
            ),

            Word(
                lessonId = 31,
                english = "Practice",
                vietnamese = "Luyện tập",
                pronunciation = "ˈpræktɪs",
                description = "The repeated exercise of an activity to improve a skill.",
                descriptionVietnamese = "Việc luyện tập một hoạt động để cải thiện kỹ năng."
            ),

            Word(
                lessonId = 31,
                english = "Stadium",
                vietnamese = "Sân vận động",
                pronunciation = "ˈsteɪdiəm",
                description = "A large venue for sporting events.",
                descriptionVietnamese = "Một địa điểm lớn dành cho các sự kiện thể thao."
            ),

            Word(
                lessonId = 31,
                english = "Athlete",
                vietnamese = "Vận động viên",
                pronunciation = "ˈæθliːt",
                description = "A person who competes in sports.",
                descriptionVietnamese = "Người thi đấu trong các môn thể thao."
            ),

            Word(
                lessonId = 31,
                english = "Warm-up",
                vietnamese = "Khởi động",
                pronunciation = "ˈwɔːm ʌp",
                description = "Exercises done to prepare the body for physical activity.",
                descriptionVietnamese = "Các bài tập giúp cơ thể sẵn sàng cho hoạt động thể chất."
            ),

            Word(
                lessonId = 31,
                english = "Injury",
                vietnamese = "Chấn thương",
                pronunciation = "ˈɪndʒəri",
                description = "Harm or damage to the body caused by an accident or physical activity.",
                descriptionVietnamese = "Tổn thương cơ thể do tai nạn hoặc hoạt động thể chất gây ra."
            ),

            Word(
                lessonId = 31,
                english = "Champion",
                vietnamese = "Nhà vô địch",
                pronunciation = "ˈtʃæmpiən",
                description = "The winner of a competition or contest.",
                descriptionVietnamese = "Người chiến thắng trong một cuộc thi hoặc trận đấu."
            ),

//            Lesson(id = 32, categoryId = 10, name = "Playing Team Sports"),
            Word(
                lessonId = 32,
                english = "Pass the ball",
                vietnamese = "Chuyền bóng",
                pronunciation = "pɑːs ðə bɔːl",
                description = "To give the ball to another teammate during a game.",
                descriptionVietnamese = "Đưa bóng cho đồng đội trong khi chơi."
            ),

            Word(
                lessonId = 32,
                english = "Defense",
                vietnamese = "Phòng thủ",
                pronunciation = "dɪˈfɛns",
                description = "The action of preventing the opposing team from scoring.",
                descriptionVietnamese = "Hành động ngăn cản đội đối phương ghi bàn."
            ),

            Word(
                lessonId = 32,
                english = "Offense",
                vietnamese = "Tấn công",
                pronunciation = "ˈɒfɛns",
                description = "The action of trying to score points or goals.",
                descriptionVietnamese = "Hành động cố gắng ghi điểm hoặc bàn thắng."
            ),

            Word(
                lessonId = 32,
                english = "Teamwork",
                vietnamese = "Làm việc nhóm",
                pronunciation = "ˈtiːmwɜːk",
                description = "The combined effort of a group to achieve a common goal.",
                descriptionVietnamese = "Sự hợp tác của một nhóm để đạt mục tiêu chung."
            ),

            Word(
                lessonId = 32,
                english = "Assist",
                vietnamese = "Hỗ trợ",
                pronunciation = "əˈsɪst",
                description = "To help a teammate score by passing or creating a chance.",
                descriptionVietnamese = "Giúp đồng đội ghi bàn bằng cách chuyền bóng hoặc tạo cơ hội."
            ),

            Word(
                lessonId = 32,
                english = "Substitute",
                vietnamese = "Cầu thủ dự bị",
                pronunciation = "ˈsʌbstɪtjuːt",
                description = "A player who replaces another during the game.",
                descriptionVietnamese = "Người chơi thay thế cho người khác trong trận đấu."
            ),

            Word(
                lessonId = 32,
                english = "Captain",
                vietnamese = "Đội trưởng",
                pronunciation = "ˈkæptɪn",
                description = "The leader of a sports team on the field.",
                descriptionVietnamese = "Người lãnh đạo của đội thể thao trên sân."
            ),

            Word(
                lessonId = 32,
                english = "Position",
                vietnamese = "Vị trí",
                pronunciation = "pəˈzɪʃən",
                description = "The specific role or place a player holds during a game.",
                descriptionVietnamese = "Vai trò hoặc vị trí cụ thể của người chơi trong trận đấu."
            ),

            Word(
                lessonId = 32,
                english = "Kick-off",
                vietnamese = "Giao bóng",
                pronunciation = "ˈkɪk ɒf",
                description = "The start or restart of a match.",
                descriptionVietnamese = "Thời điểm bắt đầu hoặc tiếp tục trận đấu."
            ),

            Word(
                lessonId = 32,
                english = "Timeout",
                vietnamese = "Thời gian tạm dừng",
                pronunciation = "ˈtaɪmaʊt",
                description = "A short break in the game called by the coach.",
                descriptionVietnamese = "Thời gian nghỉ ngắn do huấn luyện viên yêu cầu."
            ),

            Word(
                lessonId = 32,
                english = "Strategy",
                vietnamese = "Chiến thuật",
                pronunciation = "ˈstrætədʒi",
                description = "A plan of action designed to achieve a goal in the game.",
                descriptionVietnamese = "Kế hoạch hành động nhằm đạt được mục tiêu trong trận đấu."
            ),

            Word(
                lessonId = 32,
                english = "Foul",
                vietnamese = "Lỗi",
                pronunciation = "faʊl",
                description = "An action against the rules of the game.",
                descriptionVietnamese = "Hành động vi phạm luật chơi."
            ),

            Word(
                lessonId = 32,
                english = "Win as a team",
                vietnamese = "Chiến thắng cùng đồng đội",
                pronunciation = "wɪn æz ə tiːm",
                description = "To achieve victory through collective effort.",
                descriptionVietnamese = "Đạt được chiến thắng nhờ nỗ lực chung."
            ),

//            Lesson(id = 33, categoryId = 10, name = "Watching Sports Events"),
            Word(
                lessonId = 33,
                english = "Cheer for the team",
                vietnamese = "Cổ vũ cho đội",
                pronunciation = "ʧɪə fɔː ðə tiːm",
                description = "To support and encourage a team by shouting or singing.",
                descriptionVietnamese = "Ủng hộ và khích lệ đội bằng cách hò reo hoặc hát."
            ),

            Word(
                lessonId = 33,
                english = "Score a goal",
                vietnamese = "Ghi bàn",
                pronunciation = "skɔːr ə ɡəʊl",
                description = "To put the ball into the net and earn a point.",
                descriptionVietnamese = "Đưa bóng vào lưới và ghi điểm."
            ),

            Word(
                lessonId = 33,
                english = "Spectator",
                vietnamese = "Khán giả",
                pronunciation = "ˈspɛkteɪtə",
                description = "A person who watches an event or performance.",
                descriptionVietnamese = "Người xem một sự kiện hoặc buổi biểu diễn."
            ),

            Word(
                lessonId = 33,
                english = "Live broadcast",
                vietnamese = "Phát sóng trực tiếp",
                pronunciation = "laɪv ˈbrɔːdkɑːst",
                description = "A real-time transmission of an event on TV or online.",
                descriptionVietnamese = "Truyền hình trực tiếp một sự kiện trên TV hoặc mạng."
            ),

            Word(
                lessonId = 33,
                english = "Final match",
                vietnamese = "Trận chung kết",
                pronunciation = "ˈfaɪnəl mæʧ",
                description = "The last and most important game in a competition.",
                descriptionVietnamese = "Trận đấu cuối cùng và quan trọng nhất trong giải đấu."
            ),

            Word(
                lessonId = 33,
                english = "Halftime show",
                vietnamese = "Chương trình giữa hiệp",
                pronunciation = "ˈhɑːftaɪm ʃəʊ",
                description = "Entertainment performed during the break in a sports event.",
                descriptionVietnamese = "Phần biểu diễn giải trí trong thời gian nghỉ giữa trận."
            ),

            Word(
                lessonId = 33,
                english = "Instant replay",
                vietnamese = "Phát lại nhanh",
                pronunciation = "ˈɪnstənt ˈriːpleɪ",
                description = "A video showing a recent moment again during a broadcast.",
                descriptionVietnamese = "Video phát lại một khoảnh khắc gần đây trong chương trình."
            ),

            Word(
                lessonId = 33,
                english = "Referee decision",
                vietnamese = "Quyết định của trọng tài",
                pronunciation = "ˌrɛfəˈriː dɪˈsɪʒən",
                description = "The official judgment made by the referee.",
                descriptionVietnamese = "Phán quyết chính thức của trọng tài."
            ),

            Word(
                lessonId = 33,
                english = "Overtime",
                vietnamese = "Hiệp phụ",
                pronunciation = "ˈəʊvətaɪm",
                description = "Extra time added when a game ends in a draw.",
                descriptionVietnamese = "Thời gian thêm vào khi trận đấu kết thúc với tỷ số hòa."
            ),

            Word(
                lessonId = 33,
                english = "Fan gear",
                vietnamese = "Đồ dùng cổ vũ",
                pronunciation = "fæn ɡɪə",
                description = "Clothing and accessories worn by fans to support a team.",
                descriptionVietnamese = "Trang phục và phụ kiện do người hâm mộ mặc để cổ vũ."
            ),

            Word(
                lessonId = 33,
                english = "Stadium atmosphere",
                vietnamese = "Không khí sân vận động",
                pronunciation = "ˈsteɪdiəm ˈætməsfɪə",
                description = "The general feeling and excitement inside the stadium.",
                descriptionVietnamese = "Cảm xúc và sự náo nhiệt bên trong sân vận động."
            ),

            Word(
                lessonId = 33,
                english = "Match highlight",
                vietnamese = "Điểm nổi bật trận đấu",
                pronunciation = "mæʧ ˈhaɪlaɪt",
                description = "The most exciting or important parts of the game.",
                descriptionVietnamese = "Những phần hấp dẫn hoặc quan trọng nhất của trận đấu."
            ),

            Word(
                lessonId = 33,
                english = "Victory celebration",
                vietnamese = "Lễ ăn mừng chiến thắng",
                pronunciation = "ˈvɪktəri ˌsɛlɪˈbreɪʃən",
                description = "Festivities after winning a game or tournament.",
                descriptionVietnamese = "Hoạt động mừng sau khi giành chiến thắng trận đấu hoặc giải đấu."
            ),

        )
    }

    fun getSampleUserProgress():  List<UserProgress> {
        return listOf(
            UserProgress(userId = 1, lessonId = 1, score = 100, dateTest = "2025-02-20 10:00:00", dateStart = "2025-02-20 10:00:00"),
            UserProgress(userId = 1, lessonId = 2, score = 45, dateTest = "2025-02-22 10:00:00", dateStart = "2025-02-22 10:00:00"),
            UserProgress(userId = 1, lessonId = 3, score = 60, dateTest = "2025-02-25 10:00:00", dateStart = "2025-02-25 10:00:00")
        )
    }

}