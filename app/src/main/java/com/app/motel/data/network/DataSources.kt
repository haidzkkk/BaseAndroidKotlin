package com.app.motel.data.network

import com.app.motel.data.model.Answer
import com.app.motel.data.model.HistoricalEvent
import com.app.motel.data.model.HistoricalFigure
import com.app.motel.data.model.HistoryDynasty
import com.app.motel.data.model.Question
import com.app.motel.data.model.Quiz
import com.app.motel.data.model.Territory
import com.app.motel.data.model.Section

object DataSources {
    fun historicalDynasties(): List<HistoryDynasty> = listOf(
        HistoryDynasty("1", "Hồng Bàng", "2879 TCN", "258 TCN", listOf(
            HistoricalFigure("0", "Hùng Vương", "", "", null, "Quốc tổ", "Hồng Bàng", "Người sáng lập nước Văn Lang...", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/T%C6%B0%E1%BB%A3ng_H%C3%B9ng_V%C6%B0%C6%A1ng_t%E1%BA%A1i_%C4%90%E1%BB%81n_H%C3%B9ng%2C_Tao_%C4%90%C3%A0n%2C_th%C3%A1ng_12_n%C4%83m_2021_%2812%29.jpg/500px-T%C6%B0%E1%BB%A3ng_H%C3%B9ng_V%C6%B0%C6%A1ng_t%E1%BA%A1i_%C4%90%E1%BB%81n_H%C3%B9ng%2C_Tao_%C4%90%C3%A0n%2C_th%C3%A1ng_12_n%C4%83m_2021_%2812%29.jpg", "Hùng_Vương")
        )),
        HistoryDynasty("2", "Âu Lạc", "257 TCN", "179 TCN", listOf(
            HistoricalFigure("1", "An Dương Vương", "", "", null, "Vua nước Âu Lạc", "Âu Lạc", "Người xây thành Cổ Loa...", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b7/An_Duong_Vuong.jpg/220px-An_Duong_Vuong.jpg", "An_Dương_Vương")
        )),
        HistoryDynasty("3", "Nhà Triệu", "207 TCN", "111 TCN", listOf(
            HistoricalFigure("14", "Triệu Đà", "230 TCN", "137 TCN", null, "Vũ Vương", "Nhà Triệu", "Người lập ra nước Nam Việt...", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/Trieu_Da.jpg/220px-Trieu_Da.jpg", "Triệu_Đà")
        )),
        HistoryDynasty("4", "Thời kỳ Bắc thuộc lần I", "111 TCN", "40", emptyList(

        )),
        HistoryDynasty("5", "Hai Bà Trưng", "40", "43", listOf(
            HistoricalFigure("15", "Trưng Trắc", "?", "43", null, "Nữ vương", "Hai Bà Trưng", "Lãnh đạo cuộc khởi nghĩa chống quân Đông Hán...", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Hai_Ba_Trung.jpg/220px-Hai_Ba_Trung.jpg", "Hai_Bà_Trưng"),
            HistoricalFigure("16", "Trưng Nhị", "?", "43", null, "Tướng quân", "Hai Bà Trưng", "Cùng chị lãnh đạo cuộc khởi nghĩa...", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Hai_Ba_Trung.jpg/220px-Hai_Ba_Trung.jpg", "Trưng_Nhị")
        )),
        HistoryDynasty("6", "Thời kỳ Bắc thuộc lần II", "43", "544", emptyList(

        )),
        HistoryDynasty("7", "Tiền Lý", "544", "602", listOf(
            HistoricalFigure("3", "Lý Nam Đế", "503", "548", null, "Hoàng đế", "Tiền Lý", "Lập nước Vạn Xuân...", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Ly_Nam_De.jpg/220px-Ly_Nam_De.jpg", "Lý_Nam_Đế"),
            HistoricalFigure("17", "Triệu Quang Phục", "?", "571", null, "Vua", "Tiền Lý", "Người kế tục Lý Nam Đế, chống quân Lương...", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Trieu_Quang_Phuc.jpg/220px-Trieu_Quang_Phuc.jpg", "Triệu_Quang_Phục")
        )),
        HistoryDynasty("8", "Thời kỳ Bắc thuộc lần III", "602", "905", emptyList(

        )),
        HistoryDynasty("9", "Họ Khúc", "905", "938", listOf(
            HistoricalFigure("18", "Khúc Thừa Dụ", "?", "907", null, "Tiết độ sứ", "Họ Khúc", "Người đặt nền móng tự chủ...", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Khuc_Thua_Du.jpg/220px-Khuc_Thua_Du.jpg", "Khúc_Thừa_Dụ"),
            HistoricalFigure("19", "Khúc Hạo", "?", "917", null, "Tiết độ sứ", "Họ Khúc", "Tiếp tục sự nghiệp của cha...", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/13/Khuc_Hao.jpg/220px-Khuc_Hao.jpg", "Khúc_Hạo"),
            HistoricalFigure("20", "Khúc Thừa Mỹ", "?", "930", null, "Tiết độ sứ", "Họ Khúc", "Chống lại sự xâm lược của nhà Nam Hán...", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Khuc_Thua_My.jpg/220px-Khuc_Thua_My.jpg", "Khúc_Thừa_Mỹ")
        )),
        HistoryDynasty("10", "Ngô", "939", "965", listOf(
            HistoricalFigure("4", "Ngô Quyền", "898", "944", null, "Vua", "Ngô", "Chiến thắng Bạch Đằng 938...", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Ngo_Quyen.jpg/220px-Ngo_Quyen.jpg", "Ngô_Quyền"),
            HistoricalFigure("21", "Ngô Xương Văn", "?", "965", null, "Đồng vương", "Ngô", "Con trai Ngô Quyền, tham gia dẹp loạn...", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f4/Ngo_Xuong_Van.jpg/220px-Ngo_Xuong_Van.jpg", "Ngô_Xương_Văn"),
            HistoricalFigure("22", "Ngô Xương Xí", "?", "965", null, "Nam Tấn Vương", "Ngô", "Con trai Ngô Quyền, tham gia dẹp loạn...", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Ngo_Xuong_Xi.jpg/220px-Ngo_Xuong_Xi.jpg", "Ngô_Xương_Xí")
        )),
        HistoryDynasty("11", "Nhà Đinh", "968", "980", listOf(
            HistoricalFigure("5", "Đinh Tiên Hoàng", "924", "979", null, "Hoàng đế", "Đinh", "Thống nhất sau loạn 12 sứ quân...", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Dinh_Tien_Hoang.jpg/220px-Dinh_Tien_Hoang.jpg", "Đinh_Tiên_Hoàng"),
            HistoricalFigure("23", "Đinh Bộ Lĩnh", "924", "979", null, "Vạn Thắng Vương (trước khi lên ngôi)", "Đinh", "Tên thật của Đinh Tiên Hoàng...", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Dinh_Tien_Hoang.jpg/220px-Dinh_Tien_Hoang.jpg", "Đinh_Bộ_Lĩnh"),
            HistoricalFigure("24", "Đinh Liễn", "?", "979", null, "Nam Việt Vương", "Đinh", "Con trai trưởng của Đinh Tiên Hoàng...", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8c/Dinh_Lien.jpg/220px-Dinh_Lien.jpg", "Đinh_Liễn")
        )),
        HistoryDynasty("12", "Nhà Tiền Lê", "980", "1009", listOf(
            HistoricalFigure("25", "Lê Hoàn", "941", "1005", null, "Hoàng đế", "Tiền Lê", "Lập nhà Tiền Lê, đánh bại quân Tống...", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/Le_Hoan.jpg/220px-Le_Hoan.jpg", "Lê_Hoàn"),
            HistoricalFigure("26", "Lê Đại Hành", "941", "1005", null, "Hoàng đế (tên thụy)", "Tiền Lê", "Tên gọi khác của Lê Hoàn...", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/Le_Hoan.jpg/220px-Le_Hoan.jpg", "Lê_Đại_Hành"),
            HistoricalFigure("27", "Lê Trung Tông", "983", "1005", null, "Hoàng đế", "Tiền Lê", "Con trai Lê Hoàn, trị vì ngắn ngủi...", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e4/Le_Trung_Tong.jpg/220px-Le_Trung_Tong.jpg", "Lê_Trung_Tông"),
            HistoricalFigure("28", "Lê Long Đĩnh", "986", "1009", null, "Hoàng đế", "Tiền Lê", "Vị vua cuối cùng của nhà Tiền Lê...", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/Le_Long_Dinh.jpg/220px-Le_Long_Dinh.jpg", "Lê_Long_Đĩnh")
        )),
        HistoryDynasty("13", "Nhà Lý", "1009", "1225", listOf(
            HistoricalFigure("6", "Lý Thường Kiệt", "1019", "1105", null, "Tướng quốc", "Lý", "Tác giả Nam quốc sơn hà...", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/Ly_Thuong_Kiet.jpg/220px-Ly_Thuong_Kiet.jpg", "Lý_Thường_Kiệt"),
            HistoricalFigure("29", "Lý Công Uẩn", "974", "1028", null, "Lý Thái Tổ", "Lý", "Người sáng lập nhà Lý, dời đô về Thăng Long...", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Ly_Thai_To.jpg/220px-Ly_Thai_To.jpg", "Lý_Thái_Tổ"),
            HistoricalFigure("30", "Lý Thánh Tông", "1023", "1072", null, "Hoàng đế", "Lý", "Mở rộng lãnh thổ, chú trọng phát triển văn hóa...", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b3/Ly_Thanh_Tong.jpg/220px-Ly_Thanh_Tong.jpg", "Lý_Thánh_Tông"),
            HistoricalFigure("31", "Lý Nhân Tông", "1066", "1127", null, "Hoàng đế", "Lý", "Trị vì lâu nhất triều Lý, có nhiều đóng góp...", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Ly_Nhan_Tong.jpg/220px-Ly_Nhan_Tong.jpg", "Lý_Nhân_Tông")
        )),
        HistoryDynasty("14", "Nhà Trần", "1225", "1400", listOf(
            HistoricalFigure("7", "Trần Hưng Đạo", "1228", "1300", "Nguyễn Từ Quốc Mẫu", "Quốc công Tiết chế", "Trần", "Đánh bại 3 lần xâm lược của Nguyên Mông...", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Tran_Hung_Dao.jpg/220px-Tran_Hung_Dao.jpg", "Trần_Hưng_Đạo"),
            HistoricalFigure("32", "Trần Thái Tông", "1218", "1277", null, "Hoàng đế", "Trần", "Vị vua đầu tiên của nhà Trần...", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Tran_Thai_Tong.jpg/220px-Tran_Thai_Tong.jpg", "Trần_Thái_Tông"),
            HistoricalFigure("33", "Trần Thánh Tông", "1240", "1290", null, "Thái thượng hoàng", "Trần", "Cùng con lãnh đạo kháng chiến chống Nguyên Mông...", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Tran_Thanh_Tong.jpg/220px-Tran_Thanh_Tong.jpg", "Trần_Thánh_Tông"),
            HistoricalFigure("34", "Trần Nhân Tông", "1258", "1308", null, "Thái thượng hoàng", "Trần", "Lãnh đạo kháng chiến, sáng lập Thiền phái Trúc Lâm...", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b3/Tran_Nhan_Tong.jpg/220px-Tran_Nhan_Tong.jpg", "Trần_Nhân_Tông")
        )),
        HistoryDynasty("15", "Nhà Hồ", "1400", "1407", listOf(
            HistoricalFigure("35", "Hồ Quý Ly", "1336", "?", null, "Thái thượng hoàng", "Hồ", "Phế truất nhà Trần, lập nhà Hồ...", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Ho_Quy_Ly.jpg/220px-Ho_Quy_Ly.jpg", "Hồ_Quý_Ly"),
            HistoricalFigure("36", "Hồ Hán Thương", "?", "1407", null, "Hoàng đế", "Hồ", "Con trai Hồ Quý Ly, vị vua thứ hai nhà Hồ...", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Ho_Han_Thuong.jpg/220px-Ho_Han_Thuong.jpg", "Hồ_Hán_Thương")
        )),
        HistoryDynasty("16", "Thời kỳ thuộc Minh", "1407", "1427", emptyList(

        )),
        HistoryDynasty("17", "Nhà Hậu Lê (Lê sơ)", "1428", "1527", listOf(
            HistoricalFigure("8", "Nguyễn Trãi", "1380", "1442", null, "Danh nhân văn hóa", "Lê sơ", "Tác giả Bình Ngô đại cáo...", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/Nguyen_Trai.jpg/220px-Nguyen_Trai.jpg", "Nguyễn_Trãi"),
            HistoricalFigure("9", "Lê Lợi", "1385", "1433", null, "Lê Thái Tổ", "Lê sơ", "Khởi nghĩa Lam Sơn, lập nhà Hậu Lê...", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Le_Loi.jpg/220px-Le_Loi.jpg", "Lê_Lợi"),
            HistoricalFigure("37", "Lê Thánh Tông", "1442", "1497", null, "Hoàng đế", "Lê sơ", "Thời kỳ thịnh trị nhất của nhà Hậu Lê...", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Le_Thanh_Tong.jpg/220px-Le_Thanh_Tong.jpg", "Lê_Thánh_Tông")
        )),
        HistoryDynasty("18", "Nhà Mạc", "1527", "1592", listOf(
            HistoricalFigure("38", "Mạc Đăng Dung", "1483", "1541", null, "Thái thượng hoàng", "Mạc", "Lập ra nhà Mạc...", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4a/Mac_Dang_Dung.jpg/220px-Mac_Dang_Dung.jpg", "Mạc_Đăng_Dung")
        )),
        HistoryDynasty("19", "Nhà Hậu Lê (Lê trung hưng)", "1533", "1789", listOf(
            HistoricalFigure("39", "Lê Thế Tông", "1530", "1599", null, "Hoàng đế", "Lê trung hưng", "Khôi phục nhà Hậu Lê...", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/Le_The_Tong.jpg/220px-Le_The_Tong.jpg", "Lê_Thế_Tông"),
            HistoricalFigure("40", "Trịnh Kiểm", "1503", "1570", null, "Chúa", "Lê trung hưng (chúa Trịnh)", "Người nắm quyền lực thực tế thời Lê trung hưng...", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Trinh_Kiem.jpg/220px-Trinh_Kiem.jpg", "Trịnh_Kiểm")
        )),
        HistoryDynasty("20", "Nhà Tây Sơn", "1778", "1802", listOf(
            HistoricalFigure("10", "Quang Trung", "1753", "1792", null, "Hoàng đế", "Tây Sơn", "Chiến thắng Đống Đa 1789...", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8c/Emperor_Quang_Trung.jpg/220px-Emperor_Quang_Trung.jpg", "Quang_Trung"),
            HistoricalFigure("41", "Nguyễn Nhạc", "1743", "1793", null, "Hoàng đế (Trung ương Hoàng đế)", "Tây Sơn", "Anh cả, người khởi xướng phong trào Tây Sơn...", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/Nguyen_Nhac.jpg/220px-Nguyen_Nhac.jpg", "Nguyễn_Nhạc"),
            HistoricalFigure("42", "Nguyễn Lữ", "?", "?", null, "Đông Định Vương", "Tây Sơn", "Em út trong ba anh em Tây Sơn...", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Nguyen_Lu.jpg/220px-Nguyen_Lu.jpg", "Nguyễn_Lữ")
        )),
        HistoryDynasty("21", "Nhà Nguyễn", "1802", "1945", listOf(
            HistoricalFigure("11", "Hồ Chí Minh", "1890", "1969", null, "Chủ tịch", "Nguyễn", "Lãnh tụ vĩ đại, khai sinh nước VNDCCH...", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/Ho_Chi_Minh_1946.jpg/220px-Ho_Chi_Minh_1946.jpg", "Hồ_Chí_Minh"),
            HistoricalFigure("12", "Võ Nguyên Giáp", "1911", "2013", null, "Đại tướng", "Nguyễn", "Chỉ huy Điện Biên Phủ, kháng chiến chống Mỹ...", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/V%C3%B5_Nguy%C3%AAn_Gi%C3%A1p_2008.jpg/220px-V%C3%B5_Nguy%C3%AAn_Gi%C3%A1p_2008.jpg", "Võ_Nguyên_Giáp"),
            HistoricalFigure("13", "Nguyễn Thái Học", "1902", "1930", null, null, "Nguyễn", "Lãnh đạo Việt Nam Quốc dân Đảng...", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Nguyen_Thai_Hoc.jpg/220px-Nguyen_Thai_Hoc.jpg", "Nguyễn_Thái_Học"),
            HistoricalFigure("43", "Gia Long", "1762", "1820", null, "Hoàng đế", "Nguyễn", "Người sáng lập nhà Nguyễn...", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Emperor_Gia_Long.jpg/220px-Emperor_Gia_Long.jpg", "Gia_Long"),
            HistoricalFigure("44", "Minh Mạng", "1791", "1841", null, "Hoàng đế", "Nguyễn", "Đẩy mạnh cải cách hành chính...", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/Emperor_Minh_Mang.jpg/220px-Emperor_Minh_Mang.jpg", "Minh_Mạng")
        ))
    )

    fun historicalEvents(): List<HistoricalEvent> = listOf(
        HistoricalEvent(
            id = "0",
            name = "Nền văn hóa Phùng Nguyên",
            birthYear = "4000 - 1500 TCN",
            description = "Nền văn hóa khảo cổ thời đại đồ đồng, phân bố chủ yếu ở vùng Bắc Bộ Việt Nam.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Văn_hóa_Phùng_Nguyên",
            level = 3
        ),
        HistoricalEvent(
            id = "1",
            name = "Nền văn hóa Đông Sơn",
            birthYear = "700 TCN - thế kỷ 3 CN",
            description = "Nền văn hóa kim khí phát triển rực rỡ, đặc trưng bởi trống đồng Đông Sơn.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Văn_hóa_Đông_Sơn",
            level = 2
        ),

        HistoricalEvent(
            id = "2",
            name = "Truyền thuyết Hùng Vương dựng nước Văn Lang",
            dynasty = "Hồng Bàng",
            birthYear = "2879 TCN",
            description = "Truyền thuyết về các vua Hùng, những người được coi là tổ tiên của dân tộc Việt.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Hùng_Vương",
            level = 1
        ),
        HistoricalEvent(
            id = "3",
            name = "Nhà nước Văn Lang",
            dynasty = "Hồng Bàng",
            birthYear = "2879 - 258 TCN",
            description = "Nhà nước sơ khai đầu tiên của người Việt, tồn tại qua 18 đời Hùng Vương (theo các nghiên cứu gần đây).",
            wikiPageId = "Văn_Lang",
            level = 1
        ),

        HistoricalEvent(
            id = "4",
            name = "Thục Phán An Dương Vương lập nước Âu Lạc",
            dynasty = "Âu Lạc",
            birthYear = "257 TCN",
            description = "Thục Phán đánh bại Hùng Vương, thống nhất các bộ lạc và lập ra nước Âu Lạc, dời đô về Cổ Loa.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Âu_Lạc",
            level = 1
        ),
        HistoricalEvent(
            id = "5",
            name = "Xây dựng thành Cổ Loa",
            dynasty = "Âu Lạc",
            birthYear = "Thế kỷ 3 TCN",
            description = "Kinh đô của nước Âu Lạc, một công trình phòng thủ kiên cố với kiến trúc xoắn ốc độc đáo.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Cổ_Loa",
            level = 2
        ),
        HistoricalEvent(
            id = "6",
            name = "Mỵ Châu - Trọng Thủy",
            dynasty = "Âu Lạc",
            birthYear = "Thế kỷ 3 TCN",
            description = "Câu chuyện bi kịch về tình yêu và sự phản bội dẫn đến sự sụp đổ của nước Âu Lạc.",
            wikiPageId = "Mỵ_Châu",
            level = 3
        ),
        HistoricalEvent(
            id = "7",
            name = "Triệu Đà xâm lược Âu Lạc",
            dynasty = "Âu Lạc",
            birthYear = "179 TCN",
            description = "Quân Nam Việt của Triệu Đà đánh bại An Dương Vương, sáp nhập Âu Lạc vào lãnh thổ Nam Việt.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Triệu_Đà",
            level = 1
        ),

        HistoricalEvent(
            id = "8",
            name = "Nhà Hán xâm lược Nam Việt",
            dynasty = "Nhà Triệu",
            birthYear = "111 TCN",
            description = "Quân Hán tiêu diệt nhà Triệu, thiết lập ách đô hộ trên lãnh thổ Âu Lạc cũ, chia thành các quận Giao Chỉ, Cửu Chân, Nhật Nam.",
            wikiPageId = "Bắc_thuộc_lần_thứ_nhất",
            level = 1
        ),
        HistoricalEvent(
            id = "9",
            name = "Khởi nghĩa Hai Bà Trưng",
            birthYear = "Năm 40",
            dynasty = "Hai Bà Trưng",
            description = "Cuộc khởi nghĩa của hai chị em Trưng Trắc và Trưng Nhị chống lại ách đô hộ của nhà Đông Hán, giành quyền tự chủ trong thời gian ngắn.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Hai_Bà_Trưng",
            level = 1
        ),
        HistoricalEvent(
            id = "10",
            name = "Mã Viện đàn áp khởi nghĩa Hai Bà Trưng",
            birthYear = "Năm 43",
            dynasty = "Hai Bà Trưng",
            description = "Quân Đông Hán dưới sự chỉ huy của Mã Viện đàn áp cuộc khởi nghĩa, Hai Bà Trưng hy sinh.",
            wikiPageId = "Mã_Viện",
            level = 2
        ),

        HistoricalEvent(
            id = "11",
            name = "Ách đô hộ của nhà Ngô",
            birthYear = "248",
            description = "Nhà Ngô chiếm Giao Châu sau khi nhà Hán sụp đổ.",
            wikiPageId = "Tam_Quốc",
            level = 3
        ),
        HistoricalEvent(
            id = "12",
            name = "Khởi nghĩa Bà Triệu",
            birthYear = "Năm 248",
            description = "Cuộc khởi nghĩa của Triệu Thị Trinh chống lại ách đô hộ của nhà Ngô.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Bà_Triệu",
            level = 2
        ),

        HistoricalEvent(
            id = "13",
            name = "Ách đô hộ của nhà Tùy và nhà Đường",
            birthYear = "602 - 905",
            description = "Giao Châu tiếp tục bị các triều đại Tùy và Đường đô hộ.",
            wikiPageId = "Bắc_thuộc",
            level = 3
        ),
        HistoricalEvent(
            id = "14",
            name = "Khúc Thừa Dụ giành quyền tự chủ",
            birthYear = "Năm 905",
            dynasty = "Họ Khúc",
            description = "Khúc Thừa Dụ nổi dậy, tự xưng Tiết độ sứ, đặt nền móng cho nền độc lập tự chủ.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Khúc_Thừa_Dụ",
            level = 2
        ),
        HistoricalEvent(
            id = "15",
            name = "Khúc Hạo xây dựng nền tự chủ",
            birthYear = "907",
            dynasty = "Họ Khúc",
            description = "Khúc Hạo tiếp tục sự nghiệp của cha, xây dựng bộ máy hành chính tự chủ.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Khúc_Hạo",
            level = 3
        ),
        HistoricalEvent(
            id = "16",
            name = "Khúc Thừa Mỹ chống quân Nam Hán",
            birthYear = "930",
            dynasty = "Họ Khúc",
            description = "Khúc Thừa Mỹ thất bại trong việc chống lại quân Nam Hán, nước ta rơi vào ách đô hộ trở lại.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Khúc_Thừa_Mỹ",
            level = 3
        ),

        HistoricalEvent(
            id = "17",
            name = "Ngô Quyền chiến thắng Bạch Đằng",
            birthYear = "Năm 938",
            dynasty = "Ngô",
            description = "Ngô Quyền đánh tan quân Nam Hán trên sông Bạch Đằng, kết thúc hoàn toàn thời kỳ Bắc thuộc.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Trận_Bạch_Đằng_(938)",
            level = 1
        ),
        HistoricalEvent(
            id = "18",
            name = "Loạn 12 sứ quân",
            birthYear = "944 - 968",
            description = "Thời kỳ hỗn loạn sau khi Ngô Quyền mất, đất nước bị chia cắt bởi 12 thế lực cát cứ.",
            wikiPageId = "Loạn_mười_hai_sứ_quân",
            level = 2
        ),
        HistoricalEvent(
            id = "19",
            name = "Đinh Bộ Lĩnh dẹp loạn 12 sứ quân",
            birthYear = "Năm 968",
            dynasty = "Đinh",
            description = "Đinh Bộ Lĩnh thống nhất đất nước, lên ngôi hoàng đế, đặt quốc hiệu là Đại Cồ Việt.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Đinh_Tiên_Hoàng",
            level = 1
        ),
        HistoricalEvent(
            id = "20",
            name = "Lê Hoàn lên ngôi, lập ra nhà Tiền Lê",
            birthYear = "Năm 980",
            dynasty = "Tiền Lê",
            description = "Lê Hoàn lên ngôi sau khi nhà Đinh suy yếu, lập ra nhà Tiền Lê.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Lê_Hoàn",
            level = 2
        ),
        HistoricalEvent(
            id = "21",
            name = "Chiến thắng quân Tống xâm lược lần thứ nhất",
            birthYear = "Năm 981",
            dynasty = "Tiền Lê",
            description = "Lê Hoàn lãnh đạo quân dân đánh bại quân Tống xâm lược.",
            wikiPageId = "Chiến_dịch_Tống-Việt_981",
            level = 1
        ),

        HistoricalEvent(
            id = "22",
            name = "Lý Công Uẩn dời đô về Thăng Long",
            birthYear = "Năm 1010",
            dynasty = "Lý",
            description = "Lý Công Uẩn lên ngôi, dời đô từ Hoa Lư về Thăng Long (Hà Nội ngày nay), mở ra một kỷ nguyên phát triển mới.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Lý_Thái_Tổ",
            level = 1
        ),
        HistoricalEvent(
            id = "23",
            name = "Chiến thắng quân Tống xâm lược lần thứ hai",
            birthYear = "1075 - 1077",
            dynasty = "Lý",
            description = "Lý Thường Kiệt lãnh đạo quân dân đánh bại quân Tống xâm lược, bảo vệ vững chắc lãnh thổ.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Chiến_dịch_Tống-Việt_1075–1077",
            level = 1
        ),
        HistoricalEvent(
            id = "24",
            name = "Nhà Trần thành lập",
            birthYear = "Năm 1225",
            dynasty = "Trần",
            description = "Nhà Trần được thành lập sau khi Lý Chiêu Hoàng nhường ngôi cho Trần Cảnh.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Nhà_Trần",
            level = 1
        ),
        HistoricalEvent(
            id = "25",
            name = "Ba lần kháng chiến chống quân Nguyên Mông",
            birthYear = "Thế kỷ 13",
            dynasty = "Trần",
            description = "Quân dân nhà Trần dưới sự lãnh đạo của các vua Trần và Trần Hưng Đạo đã ba lần đánh bại quân xâm lược Nguyên Mông.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Kháng_chiến_chống_Nguyên_Mông",
            level = 1
        ),
        HistoricalEvent(
            id = "26",
            name = "Hồ Quý Ly phế truất nhà Trần",
            birthYear = "Năm 1400",
            dynasty = "Hồ",
            description = "Hồ Quý Ly lật đổ nhà Trần, lập ra nhà Hồ.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Hồ_Quý_Ly",
            level = 2
        ),

        HistoricalEvent(
            id = "27",
            name = "Nhà Minh xâm lược Đại Việt",
            birthYear = "Năm 1407",
            dynasty = "Hồ",
            description = "Quân Minh xâm lược và đô hộ Đại Việt sau khi nhà Hồ thất bại.",
            wikiPageId = "Nhà_Minh_xâm_lược_Đại_Việt",
            level = 1
        ),
        HistoricalEvent(
            id = "28",
            name = "Khởi nghĩa Lam Sơn",
            birthYear = "1418 - 1428",
            dynasty = "Hậu Lê",
            description = "Cuộc khởi nghĩa do Lê Lợi lãnh đạo chống lại ách đô hộ của nhà Minh, giành lại độc lập cho đất nước.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Khởi_nghĩa_Lam_Sơn",
            level = 1
        ),
        HistoricalEvent(
            id = "29",
            name = "Lê Lợi lên ngôi, lập ra nhà Hậu Lê",
            birthYear = "Năm 1428",
            dynasty = "Hậu Lê",
            description = "Lê Lợi lên ngôi hoàng đế, khôi phục nền độc lập, đặt quốc hiệu là Đại Việt.",
            wikiPageId = "Lê_Thái_Tổ",
            level = 1
        ),
        HistoricalEvent(
            id = "30",
            name = "Ban hành luật Hồng Đức",
            birthYear = "Năm 1483",
            dynasty = "Hậu Lê",
            description = "Vua Lê Thánh Tông ban hành bộ luật Hồng Đức, một bộ luật tiến bộ và hoàn chỉnh của Việt Nam thời phong kiến.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Luật_Hồng_Đức",
            level = 2
        ),

        HistoricalEvent(
            id = "31",
            name = "Nhà Mạc thành lập",
            birthYear = "Năm 1527",
            dynasty = "Nhà Mạc",
            description = "Mạc Đăng Dung lật đổ nhà Hậu Lê, lập ra nhà Mạc, mở đầu thời kỳ Nam - Bắc triều.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Nhà_Mạc",
            level = 2
        ),
        HistoricalEvent(
            id = "32",
            name = "Chiến tranh Lê - Mạc",
            birthYear = "1533 - 1592",
            dynasty = "Nhà Mạc / Hậu Lê (Lê trung hưng)",
            description = "Cuộc chiến kéo dài giữa nhà Mạc ở phía Bắc và nhà Hậu Lê được các tướng lĩnh trung thành khôi phục ở phía Nam.",
            wikiPageId = "Chiến_tranh_Lê-Mạc",
            level = 2
        ),
        HistoricalEvent(
            id = "33",
            name = "Trịnh Kiểm nắm quyền, mở đầu thời kỳ chúa Trịnh",
            birthYear = "Thế kỷ 16",
            dynasty = "Hậu Lê (Lê trung hưng)",
            description = "Trịnh Kiểm thâu tóm quyền lực, các vua Lê chỉ còn là hình thức, mở đầu thời kỳ các chúa Trịnh cai trị Đàng Ngoài.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Trịnh_Kiểm",
            level = 2
        ),
        HistoricalEvent(
            id = "34",
            name = "Nguyễn Hoàng vào trấn thủ Thuận Hóa, mở đầu thời kỳ chúa Nguyễn",
            birthYear = "Năm 1558",
            dynasty = "Hậu Lê (Lê trung hưng)",
            description = "Nguyễn Hoàng được cử vào trấn thủ vùng Thuận Hóa (miền Trung), dần xây dựng thế lực riêng, mở đầu thời kỳ các chúa Nguyễn cai trị Đàng Trong.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Nguyễn_Hoàng",
            level = 2
        ),
        HistoricalEvent(
            id = "35",
            name = "Chiến tranh Trịnh - Nguyễn",
            birthYear = "1627 - 1672",
            dynasty = "Hậu Lê (Lê trung hưng)",
            description = "Loạt các cuộc chiến tranh kéo dài giữa chúa Trịnh ở Đàng Ngoài và chúa Nguyễn ở Đàng Trong, gây ra sự chia cắt đất nước.",
            wikiPageId = "Chiến_tranh_Trịnh-Nguyễn",
            level = 2
        ),

        HistoricalEvent(
            id = "36",
            name = "Khởi nghĩa Tây Sơn",
            birthYear = "Năm 1771",
            dynasty = "Tây Sơn",
            description = "Ba anh em Nguyễn Nhạc, Nguyễn Huệ, Nguyễn Lữ nổi dậy ở Tây Sơn chống lại các tập đoàn phong kiến suy yếu.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Phong_trào_Tây_Sơn",
            level = 1
        ),
        HistoricalEvent(
            id = "37",
            name = "Nguyễn Huệ đánh bại quân Thanh",
            birthYear = "Năm 1789",
            dynasty = "Tây Sơn",
            title = "Chiến thắng Ngọc Hồi - Đống Đa",
            description = "Nguyễn Huệ (Quang Trung) lãnh đạo quân Tây Sơn đánh tan 29 vạn quân Thanh xâm lược, bảo vệ độc lập dân tộc.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Trận_Ngọc_Hồi_–_Đống_Đa",
            level = 1
        ),

        HistoricalEvent(
            id = "38",
            name = "Nguyễn Ánh lên ngôi, lập ra nhà Nguyễn",
            birthYear = "Năm 1802",
            dynasty = "Nguyễn",
            title = "Gia Long",
            description = "Nguyễn Ánh đánh bại nhà Tây Sơn, thống nhất đất nước và lên ngôi hoàng đế, lập ra nhà Nguyễn.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Gia_Long",
            level = 1
        ),
        HistoricalEvent(
            id = "39",
            name = "Pháp bắt đầu xâm lược Việt Nam",
            birthYear = "Năm 1858",
            dynasty = "Nguyễn",
            description = "Quân Pháp tấn công Đà Nẵng, mở đầu quá trình xâm lược Việt Nam.",
            wikiPageId = "Chiến_tranh_Pháp-Đại_Nam",
            level = 1
        ),
        HistoricalEvent(
            id = "40",
            name = "Phong trào Cần Vương",
            birthYear = "1885 - 1896",
            dynasty = "Nguyễn",
            description = "Phong trào kháng chiến vũ trang của nhân dân Việt Nam dưới sự lãnh đạo của các sĩ phu yêu nước chống lại thực dân Pháp.",
            wikiPageId = "Phong_trào_Cần_Vương",
            level = 2
        ),

        HistoricalEvent(
            id = "41",
            name = "Hiệp ước Patenôtre, Việt Nam trở thành thuộc địa của Pháp",
            birthYear = "Năm 1884",
            description = "Hiệp ước đánh dấu sự hoàn thành quá trình xâm lược và đặt Việt Nam dưới sự bảo hộ của Pháp.",
            wikiPageId = "Hiệp_ước_Patenôtre",
            level = 1
        ),
        HistoricalEvent(
            id = "42",
            name = "Khởi nghĩa Yên Thế",
            birthYear = "1884 - 1913",
            description = "Cuộc khởi nghĩa nông dân lớn nhất và kéo dài nhất chống lại thực dân Pháp do Hoàng Hoa Thám lãnh đạo.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Khởi_nghĩa_Yên_Thế",
            level = 2
        ),

        HistoricalEvent(
            id = "43",
            name = "Đảng Cộng sản Việt Nam thành lập",
            birthYear = "Năm 1930",
            description = "Sự kiện đánh dấu bước ngoặt quan trọng trong lịch sử cách mạng Việt Nam, lãnh đạo cuộc đấu tranh giành độc lập.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Đảng_Cộng_sản_Việt_Nam",
            level = 1
        ),
        HistoricalEvent(
            id = "44",
            name = "Cách mạng tháng Tám",
            birthYear = "Năm 1945",
            description = "Cuộc cách mạng thành công lật đổ chế độ phong kiến và thực dân, thành lập nước Việt Nam Dân chủ Cộng hòa.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Cách_mạng_tháng_Tám",
            level = 1
        ),
        HistoricalEvent(
            id = "45",
            name = "Tuyên ngôn độc lập",
            birthYear = "Ngày 2 tháng 9 năm 1945",
            description = "Hồ Chí Minh đọc Tuyên ngôn độc lập, khai sinh nước Việt Nam Dân chủ Cộng hòa.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Tuyên_ngôn_độc_lập_Việt_Nam",
            level = 1
        ),

        HistoricalEvent(
            id = "46",
            name = "Chiến tranh Đông Dương lần thứ nhất (kháng chiến chống Pháp)",
            birthYear = "1946 - 1954",
            description = "Cuộc chiến tranh giữa Việt Nam Dân chủ Cộng hòa và thực dân Pháp nhằm giành độc lập.",
            wikiPageId = "Chiến_tranh_Đông_Dương",
            level = 1
        ),
        HistoricalEvent(
            id = "47",
            name = "Chiến thắng Điện Biên Phủ",
            birthYear = "Năm 1954",
            description = "Chiến thắng quyết định của Việt Nam trước quân Pháp, dẫn đến việc ký kết Hiệp định Genève.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Trận_Điện_Biên_Phủ",
            level = 1
        ),
        HistoricalEvent(
            id = "48",
            name = "Hiệp định Genève",
            birthYear = "Năm 1954",
            description = "Hiệp định chấm dứt chiến tranh Đông Dương, Việt Nam tạm thời bị chia cắt thành hai miền Nam và Bắc.",
            wikiPageId = "Hiệp_định_Genève_(1954)",
            level = 1
        ),
        HistoricalEvent(
            id = "49",
            name = "Chiến tranh Việt Nam (kháng chiến chống Mỹ)",
            birthYear = "1955 - 1975",
            description = "Cuộc chiến tranh giữa Việt Nam Dân chủ Cộng hòa và Việt Nam Cộng hòa (được Mỹ hậu thuẫn) nhằm thống nhất đất nước.",
            wikiPageId = "Chiến_tranh_Việt_Nam",
            level = 1
        ),
        HistoricalEvent(
            id = "50",
            name = "Sự kiện Vịnh Bắc Bộ",
            birthYear = "Năm 1964",
            description = "Sự kiện được Mỹ sử dụng làm cái cớ để leo thang chiến tranh ở Việt Nam.",
            wikiPageId = "Sự_kiện_Vịnh_Bắc_Bộ",
            level = 2
        ),
        HistoricalEvent(
            id = "51",
            name = "Chiến dịch Hồ Chí Minh",
            birthYear = "Năm 1975",
            description = "Chiến dịch quân sự cuối cùng của quân đội Việt Nam Dân chủ Cộng hòa, giải phóng hoàn toàn miền Nam, thống nhất đất nước.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Chiến_dịch_Hồ_Chí_Minh",
            level = 1
        ),

        HistoricalEvent(
            id = "52",
            name = "Việt Nam thống nhất",
            birthYear = "Ngày 2 tháng 7 năm 1976",
            description = "Nước Việt Nam Dân chủ Cộng hòa và Cộng hòa miền Nam Việt Nam hợp nhất thành nước Cộng hòa Xã hội Chủ nghĩa Việt Nam.",
            wikiPageId = "Cộng_hòa_Xã_hội_chủ_nghĩa_Việt_Nam",
            level = 1
        ),
        HistoricalEvent(
            id = "53",
            name = "Chiến tranh biên giới Tây Nam",
            birthYear = "1978 - 1989",
            description = "Cuộc chiến tranh giữa Việt Nam và Khmer Đỏ ở biên giới Tây Nam.",
            wikiPageId = "Chiến_tranh_biên_giới_Tây_Nam",
            level = 2
        ),
        HistoricalEvent(
            id = "54",
            name = "Chiến tranh biên giới phía Bắc",
            birthYear = "Năm 1979",
            description = "Cuộc xung đột vũ trang giữa Việt Nam và Trung Quốc ở biên giới phía Bắc.",
            wikiPageId = "Chiến_tranh_biên_giới_Việt-Trung_1979",
            level = 2
        ),
        HistoricalEvent(
            id = "55",
            name = "Đổi mới",
            birthYear = "Năm 1986",
            description = "Chính sách cải cách kinh tế và xã hội toàn diện do Đảng Cộng sản Việt Nam khởi xướng.",
            wikiPageId = "Đổi_Mới",
            level = 1
        ),
        HistoricalEvent(
            id = "56",
            name = "Việt Nam gia nhập ASEAN",
            birthYear = "Năm 1995",
            description = "Việt Nam trở thành thành viên của Hiệp hội các quốc gia Đông Nam Á (ASEAN).",
            wikiPageId = "Việt_Nam_và_ASEAN",
            level = 2
        ),
        HistoricalEvent(
            id = "57",
            name = "Việt Nam gia nhập WTO",
            birthYear = "Năm 2007",
            description = "Việt Nam trở thành thành viên của Tổ chức Thương mại Thế giới (WTO).",
            wikiPageId = "Việt_Nam_và_WTO",
            level = 2
        )
    )

    fun historyTerritory() = arrayListOf(
        Territory(
            id = "0",
            title = "Văn Lang",
            period = "VII - III TCN",
            description = "Nước Văn Lang của bộ tộc Lạc Việt hình thành trên vùng đồng bằng sông Hồng, sông Mã và sông Lam.",
            image = "img_territory1",
            timelineEntries = listOf(
                Section(id = "0", title = "700 TCN - 650 TCN", content = "Hình thành các cộng đồng Lạc Việt ban đầu"),
                Section(id = "1", title = "650 TCN - 550 TCN", content = "Giai đoạn củng cố các bộ lạc"),
                Section(id = "2", title = "550 TCN - 450 TCN", content = "Phát triển các trung tâm văn hóa sơ khai"),
                Section(id = "3", title = "450 TCN - 350 TCN", content = "Mở rộng ảnh hưởng ra các vùng lân cận"),
                Section(id = "4", title = "350 TCN - 300 TCN", content = "Giao thương và trao đổi văn hóa"),
                Section(id = "5", title = "300 TCN - 280 TCN", content = "Sự hình thành các liên minh bộ lạc mạnh mẽ hơn"),
                Section(id = "6", title = "280 TCN - 260 TCN", content = "Giai đoạn cuối của nhà nước Văn Lang"),
                Section(id = "0", title = "700 TCN - 650 TCN", content = "Hình thành các cộng đồng Lạc Việt ban đầu"),
                Section(id = "1", title = "650 TCN - 550 TCN", content = "Giai đoạn củng cố các bộ lạc"),
                Section(id = "2", title = "550 TCN - 450 TCN", content = "Phát triển các trung tâm văn hóa sơ khai"),
                Section(id = "3", title = "450 TCN - 350 TCN", content = "Mở rộng ảnh hưởng ra các vùng lân cận"),
                Section(id = "4", title = "350 TCN - 300 TCN", content = "Giao thương và trao đổi văn hóa"),
                Section(id = "5", title = "300 TCN - 280 TCN", content = "Sự hình thành các liên minh bộ lạc mạnh mẽ hơn"),
                Section(id = "6", title = "280 TCN - 260 TCN", content = "Giai đoạn cuối của nhà nước Văn Lang"),
                Section(id = "0", title = "700 TCN - 650 TCN", content = "Hình thành các cộng đồng Lạc Việt ban đầu"),
                Section(id = "1", title = "650 TCN - 550 TCN", content = "Giai đoạn củng cố các bộ lạc"),
                Section(id = "2", title = "550 TCN - 450 TCN", content = "Phát triển các trung tâm văn hóa sơ khai"),
                Section(id = "3", title = "450 TCN - 350 TCN", content = "Mở rộng ảnh hưởng ra các vùng lân cận"),
                Section(id = "4", title = "350 TCN - 300 TCN", content = "Giao thương và trao đổi văn hóa"),
                Section(id = "5", title = "300 TCN - 280 TCN", content = "Sự hình thành các liên minh bộ lạc mạnh mẽ hơn"),
                Section(id = "6", title = "280 TCN - 260 TCN", content = "Giai đoạn cuối của nhà nước Văn Lang"),
                Section(id = "7", title = "260 TCN - 258 TCN", content = "Suy yếu và xung đột nội bộ"),
                Section(id = "8", title = "257 TCN - 257 TCN", content = "Sự kiện Thục Phán và kết thúc Văn Lang"),
            )
        ),
        Territory(
            id = "1",
            title = "Âu Lạc",
            period = "257 - 207 TCN",
            description = "Nước Âu Lạc được Thục Phán An Dương Vương thành lập sau khi thống nhất Văn Lang, kinh đô tại Cổ Loa.",
            image = "img_territory2",
            timelineEntries = listOf(
                Section(id = "0", title = "257 TCN - 240 TCN", content = "Thành lập nhà nước và xây dựng kinh đô Cổ Loa"),
                Section(id = "1", title = "240 TCN - 230 TCN", content = "Củng cố quyền lực và mở rộng lãnh thổ"),
                Section(id = "2", title = "230 TCN - 220 TCN", content = "Phát triển nông nghiệp và thủ công nghiệp"),
                Section(id = "3", title = "220 TCN - 218 TCN", content = "Giao thương với các vùng lân cận và phòng thủ"),
                Section(id = "4", title = "218 TCN - 215 TCN", content = "Những căng thẳng đầu tiên với Nam Việt"),
                Section(id = "5", title = "215 TCN - 208 TCN", content = "Giai đoạn phòng thủ và chuẩn bị chiến tranh"),
                Section(id = "6", title = "208 TCN - 208 TCN", content = "Cuộc xâm lược đầu tiên của Triệu Đà (bất thành)"),
                Section(id = "7", title = "207 TCN - 207 TCN", content = "Cuộc xâm lược thứ hai và thất bại của An Dương Vương"),
                Section(id = "8", title = "207 TCN - 207 TCN", content = "Âu Lạc bị sáp nhập vào Nam Việt"),
                Section(id = "9", title = "207 TCN - 207 TCN", content = "Ảnh hưởng văn hóa và chính trị từ Nam Việt"),
            )
        ),
        Territory(
            id = "2",
            title = "Việt Nam\nxâm lược và đô hộ",
            period = "",
            description = "Lãnh thổ Âu Lạc cũ bị sáp nhập vào nước Nam Việt của Triệu Đà, chịu sự cai trị và đồng hóa văn hóa.",
            image = "img_territory3",
            timelineEntries = listOf(
                Section(id = "0", title = "207 TCN - 190 TCN", content = "Thiết lập bộ máy cai trị của Nam Việt"),
                Section(id = "1", title = "190 TCN - 170 TCN", content = "Giai đoạn ổn định và khai thác tài nguyên"),
                Section(id = "2", title = "170 TCN - 150 TCN", content = "Sự giao thoa văn hóa Hán - Việt"),
                Section(id = "3", title = "150 TCN - 137 TCN", content = "Thời kỳ cai trị của Triệu Hồ"),
                Section(id = "4", title = "137 TCN - 125 TCN", content = "Tiếp tục chính sách đồng hóa"),
                Section(id = "5", title = "125 TCN - 115 TCN", content = "Những dấu hiệu bất ổn và kháng cự ngầm"),
                Section(id = "6", title = "115 TCN - 112 TCN", content = "Chuẩn bị cho cuộc xâm lược của nhà Hán"),
                Section(id = "7", title = "112 TCN - 111 TCN", content = "Chiến dịch xâm lược của nhà Hán"),
                Section(id = "8", title = "111 TCN - 111 TCN", content = "Ảnh hưởng sâu rộng của văn hóa Hán"),
            )
        ),
        Territory(
            id = "3",
            title = "Nhà Hán\nxâm lược và đô hộ",
            period = "",
            description = "Nước Văn Lang thuộc bộ tộc Lạc Việt đã hình thành trên vùng bình nguyên bao gồm đồng bằng sông Hồng, đồng bằng sông Mã và đồng bằng Sông Lam.",
            image = "img_territory4",
            timelineEntries = listOf(
                Section(id = "0", title = "111 TCN - 90 TCN", content = "Thiết lập các quận và bộ máy cai trị Hán"),
                Section(id = "1", title = "90 TCN - 50 TCN", content = "Áp đặt luật pháp và thuế khóa nặng nề"),
                Section(id = "2", title = "50 TCN - 0", content = "Du nhập văn hóa và giáo dục Hán"),
                Section(id = "3", title = "0 - 20", content = "Các cuộc nổi dậy nhỏ lẻ của người Việt"),
                Section(id = "4", title = "20 - 25", content = "Ảnh hưởng của sự thay đổi triều đại nhà Hán"),
                Section(id = "5", title = "25 - 35", content = "Củng cố lại ách đô hộ dưới thời Đông Hán"),
                Section(id = "6", title = "35 - 40", content = "Tăng cường áp bức, dẫn đến mâu thuẫn sâu sắc"),
                Section(id = "7", title = "Năm 40 - 43", content = "Khởi nghĩa Hai Bà Trưng và thời kỳ tự chủ ngắn ngủi"),
                Section(id = "8", title = "Năm 43 - 43", content = "Tái thiết lập ách đô hộ của nhà Hán"),
            )
        ),
    )

    // quiz
    fun quizzes(): List<Quiz> {
        return listOf(
            Quiz(
                id = "0",
                title = "Khám phá thời kỳ Hồng Bàng - Bộ 1",
                formPeriod = -2879,
                toPeriod = -258,
                period = "2879 TCN - 258 TCN",
                questionCount = 20,
                durationMinutes = 15,
                playCount = 0,
                image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
                questions = listOf(
                    Question(id = "0", text = "Theo truyền thuyết, ai là người được coi là vị vua đầu tiên của nước Văn Lang?", answers = listOf(Answer(id = "0", text = "Hùng Vương"), Answer(id = "1", text = "An Dương Vương"), Answer(id = "2", text = "Lý Thái Tổ"), Answer(id = "3", text = "Ngô Quyền")), correctAnswerId = "0"),
                    Question(id = "1", text = "Kinh đô đầu tiên của nước Văn Lang có tên là gì?", answers = listOf(Answer(id = "0", text = "Phong Châu"), Answer(id = "1", text = "Cổ Loa"), Answer(id = "2", text = "Hoa Lư"), Answer(id = "3", text = "Thăng Long")), correctAnswerId = "0"),
                    Question(id = "2", text = "Thời kỳ Hồng Bàng còn được biết đến với tên gọi nào?", answers = listOf(Answer(id = "0", text = "Thời đại đồ đá"), Answer(id = "1", text = "Thời đại Hùng Vương"), Answer(id = "2", text = "Thời kỳ Bắc thuộc"), Answer(id = "3", text = "Thời đại đồ sắt")), correctAnswerId = "1"),
                    Question(id = "3", text = "Theo truyền thuyết, Lạc Long Quân kết duyên với ai và sinh ra bao nhiêu người con?", answers = listOf(Answer(id = "0", text = "Âu Cơ, 100"), Answer(id = "1", text = "Mỵ Châu, 50"), Answer(id = "2", text = "Thánh Gióng, 1"), Answer(id = "3", text = "Bà Triệu, 30")), correctAnswerId = "0"),
                    Question(id = "4", text = "Tên gọi 'Văn Lang' có ý nghĩa là gì (theo các nhà nghiên cứu)?", answers = listOf(Answer(id = "0", text = "Đất nước của văn chương"), Answer(id = "1", text = "Đất nước của người Lạc"), Answer(id = "2", text = "Đất nước thanh bình"), Answer(id = "3", text = "Đất nước phồn thịnh")), correctAnswerId = "1"),
                    Question(id = "5", text = "Trong xã hội Văn Lang, tầng lớp nào được coi là quý tộc, có quyền lực?", answers = listOf(Answer(id = "0", text = "Nông dân"), Answer(id = "1", text = "Lạc hầu, Lạc tướng"), Answer(id = "2", text = "Thợ thủ công"), Answer(id = "3", text = "Thương nhân")), correctAnswerId = "1"),
                    Question(id = "6", text = "Hoạt động kinh tế chính của cư dân Văn Lang là gì?", answers = listOf(Answer(id = "0", text = "Buôn bán đường biển"), Answer(id = "1", text = "Trồng lúa nước"), Answer(id = "2", text = "Chăn nuôi du mục"), Answer(id = "3", text = "Khai thác khoáng sản")), correctAnswerId = "1"),
                    Question(id = "7", text = "Tín ngưỡng thờ cúng nào phổ biến trong thời kỳ Hồng Bàng?", answers = listOf(Answer(id = "0", text = "Thờ thần Mặt Trời"), Answer(id = "1", text = "Thờ cúng tổ tiên và các lực lượng tự nhiên"), Answer(id = "2", text = "Thờ Phật"), Answer(id = "3", text = "Thờ các vị thần Hy Lạp")), correctAnswerId = "1"),
                    Question(id = "8", text = "Theo truyền thuyết, Hùng Vương thứ mấy đã gả con gái Mỵ Nương cho Sơn Tinh?", answers = listOf(Answer(id = "0", text = "Hùng Vương thứ nhất"), Answer(id = "1", text = "Hùng Vương thứ sáu"), Answer(id = "2", text = "Hùng Vương thứ mười tám"), Answer(id = "3", text = "Không có Hùng Vương nào")), correctAnswerId = "2"),
                    Question(id = "9", text = "Vũ khí tiêu biểu nào thường được sử dụng trong thời kỳ Văn Lang?", answers = listOf(Answer(id = "0", text = "Kiếm sắt"), Answer(id = "1", text = "Rìu đá, giáo tre"), Answer(id = "2", text = "Súng thần công"), Answer(id = "3", text = "Bom nguyên tử")), correctAnswerId = "1"),
                    Question(id = "10", text = "Theo truyền thuyết, ai là người đã giúp An Dương Vương xây thành Cổ Loa?", answers = listOf(Answer(id = "0", text = "Lạc Long Quân"), Answer(id = "1", text = "Thánh Gióng"), Answer(id = "2", text = "Cao Lỗ"), Answer(id = "3", text = "Lý Thường Kiệt")), correctAnswerId = "2"),
                    Question(id = "11", text = "Thời kỳ Hồng Bàng được các nhà sử học ước tính kéo dài trong khoảng bao nhiêu năm?", answers = listOf(Answer(id = "0", text = "Vài chục năm"), Answer(id = "1", text = "Hơn hai nghìn năm"), Answer(id = "2", text = "Khoảng một trăm năm"), Answer(id = "3", text = "Gần mười nghìn năm")), correctAnswerId = "1"),
                    Question(id = "12", text = "Trong truyền thuyết Sơn Tinh - Thủy Tinh, Sơn Tinh tượng trưng cho yếu tố tự nhiên nào?", answers = listOf(Answer(id = "0", text = "Nước"), Answer(id = "1", text = "Đất"), Answer(id = "2", text = "Gió"), Answer(id = "3", text = "Lửa")), correctAnswerId = "1"),
                    Question(id = "13", text = "Theo truyền thuyết, Thủy Tinh tượng trưng cho yếu tố tự nhiên nào?", answers = listOf(Answer(id = "0", text = "Đất"), Answer(id = "1", text = "Nước"), Answer(id = "2", text = "Lửa"), Answer(id = "3", text = "Trời")), correctAnswerId = "1"),
                    Question(id = "14", text = "Hình ảnh chim và các hoa văn trên trống đồng thời kỳ Văn Lang thể hiện điều gì?", answers = listOf(Answer(id = "0", text = "Sự giàu có của quý tộc"), Answer(id = "1", text = "Đời sống và tín ngưỡng của người Lạc Việt"), Answer(id = "2", text = "Kỹ thuật luyện kim tiên tiến"), Answer(id = "3", text = "Quan hệ giao thương với nước ngoài")), correctAnswerId = "1"),
                    Question(id = "15", text = "Theo truyền thuyết, ai là người đã chế tạo ra nỏ thần Liên Châu?", answers = listOf(Answer(id = "0", text = "Hùng Vương"), Answer(id = "1", text = "Lạc Long Quân"), Answer(id = "2", text = "Cao Lỗ"), Answer(id = "3", text = "Thánh Gióng")), correctAnswerId = "2"),
                    Question(id = "16", text = "Sự kết thúc của thời kỳ Hồng Bàng thường được gắn liền với sự kiện nào?", answers = listOf(Answer(id = "0", text = "Chiến thắng Bạch Đằng"), Answer(id = "1", text = "An Dương Vương dựng nước Âu Lạc"), Answer(id = "2", text = "Khởi nghĩa Hai Bà Trưng"), Answer(id = "3", text = "Đinh Bộ Lĩnh dẹp loạn 12 sứ quân")), correctAnswerId = "1"),
                    Question(id = "17", text = "Theo truyền thuyết, các Hùng Vương truyền ngôi cho nhau theo hình thức nào?", answers = listOf(Answer(id = "0", text = "Cha truyền con nối"), Answer(id = "1", text = "Tuyển chọn người tài"), Answer(id = "2", text = "Nhường ngôi cho người có công"), Answer(id = "3", text = "Không có hình thức truyền ngôi nhất định")), correctAnswerId = "0"),
                    Question(id = "18", text = "Đơn vị hành chính cơ sở của nước Văn Lang được gọi là gì?", answers = listOf(Answer(id = "0", text = "Làng, xã"), Answer(id = "1", text = "Bộ"), Answer(id = "2", text = "Châu"), Answer(id = "3", text = "Huyện")), correctAnswerId = "0"),
                    Question(id = "19", text = "Ý nghĩa quan trọng nhất của thời kỳ Hồng Bàng trong lịch sử Việt Nam là gì?", answers = listOf(Answer(id = "0", text = "Mở đầu thời kỳ văn minh lúa nước"), Answer(id = "1", text = "Đặt nền móng cho quốc gia và dân tộc Việt"), Answer(id = "2", text = "Xây dựng hệ thống chữ viết đầu tiên"), Answer(id = "3", text = "Đánh bại các cuộc xâm lược từ phương Bắc")), correctAnswerId = "1"),
                )
            ),
            Quiz(
                id = "1",
                title = "Dấu ấn thời kỳ Hồng Bàng - Bộ 2",
                formPeriod = -2879,
                toPeriod = -258,
                period = "2879 TCN - 258 TCN",
                questionCount = 20,
                durationMinutes = 15,
                playCount = 0,
                image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
                questions = listOf(
                    Question(id = "0", text = "Theo truyền thuyết, ai là người đã dạy người dân trồng lúa nước?", answers = listOf(Answer(id = "0", text = "Hùng Vương"), Answer(id = "1", text = "Lạc Long Quân"), Answer(id = "2", text = "Âu Cơ"), Answer(id = "3", text = "Thánh Gióng")), correctAnswerId = "0"),
                    Question(id = "1", text = "Địa bàn chủ yếu của nước Văn Lang ngày nay thuộc vùng nào của Việt Nam?", answers = listOf(Answer(id = "0", text = "Đồng bằng sông Cửu Long"), Answer(id = "1", text = "Đồng bằng sông Hồng và các vùng lân cận"), Answer(id = "2", text = "Tây Nguyên"), Answer(id = "3", text = "Duyên hải miền Trung")), correctAnswerId = "1"),
                    Question(id = "2", text = "Theo truyền thuyết, các Hùng Vương đã trị vì đất nước trong bao nhiêu đời?", answers = listOf(Answer(id = "0", text = "10 đời"), Answer(id = "1", text = "18 đời"), Answer(id = "2", text = "30 đời"), Answer(id = "3", text = "5 đời")), correctAnswerId = "1"),
                    Question(id = "3", text = "Trong truyền thuyết Sơn Tinh - Thủy Tinh, lễ vật mà Sơn Tinh mang đến cầu hôn Mỵ Nương có gì đặc biệt?", answers = listOf(Answer(id = "0", text = "Vàng bạc châu báu"), Answer(id = "1", text = "Voi chín ngà, gà chín cựa, ngựa chín hồng mao"), Answer(id = "2", text = "Lâu đài tráng lệ"), Answer(id = "3", text = "Quân đội hùng mạnh")), correctAnswerId = "1"),
                    Question(id = "4", text = "Các nhà khảo cổ học đã tìm thấy những dấu tích nào được cho là thuộc về thời kỳ Văn Lang?", answers = listOf(Answer(id = "0", text = "Kim tự tháp"), Answer(id = "1", text = "Trống đồng, đồ gốm"), Answer(id = "2", text = "Chữ viết cổ"), Answer(id = "3", text = "Tượng nhân sư")), correctAnswerId = "1"),
                    Question(id = "5", text = "Theo truyền thuyết, tục 'ăn trầu' của người Việt có nguồn gốc từ câu chuyện nào thời Hùng Vương?", answers = listOf(Answer(id = "0", text = "Sơn Tinh - Thủy Tinh"), Answer(id = "1", text = "Trầu Cau"), Answer(id = "2", text = "Bánh chưng bánh giầy"), Answer(id = "3", text = "Thánh Gióng")), correctAnswerId = "1"),
                    Question(id = "6", text = "Nghề thủ công nào phát triển khá mạnh trong thời kỳ Văn Lang?", answers = listOf(Answer(id = "0", text = "Dệt vải lụa"), Answer(id = "1", text = "Làm đồ gốm, luyện kim đồng"), Answer(id = "2", text = "Chế tạo vũ khí hiện đại"), Answer(id = "3", text = "Đóng tàu thuyền lớn")), correctAnswerId = "1"),
                    Question(id = "7", text = "Theo truyền thuyết, ai là người đã có công đánh đuổi giặc Ân xâm lược?", answers = listOf(Answer(id = "0", text = "An Dương Vương"), Answer(id = "1", text = "Thánh Gióng"), Answer(id = "2", text = "Lý Bí"), Answer(id = "3", text = "Trưng Trắc")), correctAnswerId = "1"),
                    Question(id = "8", text = "Hình thức tổ chức nhà nước thời Văn Lang được mô tả như thế nào?", answers = listOf(Answer(id = "0", text = "Quân chủ chuyên chế tập quyền"), Answer(id = "1", text = "Nhà nước sơ khai, mang tính bộ lạc"), Answer(id = "2", text = "Cộng hòa dân chủ"), Answer(id = "3", text = "Chịu sự cai trị của nước ngoài")), correctAnswerId = "1"),
                    Question(id = "9", text = "Trong xã hội Văn Lang, mối quan hệ giữa các bộ lạc chủ yếu dựa trên điều gì?", answers = listOf(Answer(id = "0", text = "Kinh tế thương mại"), Answer(id = "1", text = "Quan hệ huyết thống và liên minh"), Answer(id = "2", text = "Sức mạnh quân sự"), Answer(id = "3", text = "Hệ thống luật pháp chặt chẽ")), correctAnswerId = "1"),
                    Question(id = "10", text = "Theo truyền thuyết, bánh chưng và bánh giầy ra đời vào thời Hùng Vương nào?", answers = listOf(Answer(id = "0", text = "Hùng Vương thứ nhất"), Answer(id = "1", text = "Hùng Vương thứ mười tám"), Answer(id = "2", text = "Hùng Vương thứ sáu"), Answer(id = "3", text = "Hùng Vương thứ ba")), correctAnswerId = "1"),
                    Question(id = "11", text = "Các nhà nghiên cứu cho rằng nền văn hóa nào là tiền đề trực tiếp cho sự hình thành văn hóa Văn Lang?", answers = listOf(Answer(id = "0", text = "Văn hóa Óc Eo"), Answer(id = "1", text = "Văn hóa Phùng Nguyên"), Answer(id = "2", text = "Văn hóa Sa Huỳnh"), Answer(id = "3", text = "Văn hóa Đông Sơn")), correctAnswerId = "1"),
                    Question(id = "12", text = "Theo truyền thuyết, ai là người đã dạy người dân cách làm bánh giầy?", answers = listOf(Answer(id = "0", text = "Lang Liêu"), Answer(id = "1", text = "Sơn Tinh"), Answer(id = "2", text = "Thủy Tinh"), Answer(id = "3", text = "Hùng Vương")), correctAnswerId = "0"),
                    Question(id = "13", text = "Theo truyền thuyết, ai là người đã dạy người dân cách làm bánh chưng?", answers = listOf(Answer(id = "0", text = "Lang Liêu"), Answer(id = "1", text = "Lạc Long Quân"), Answer(id = "2", text = "Âu Cơ"), Answer(id = "3", text = "Hùng Vương")), correctAnswerId = "0"),
                    Question(id = "14", text = "Trong xã hội Văn Lang, hình phạt chủ yếu được thực hiện như thế nào?", answers = listOf(Answer(id = "0", text = "Bỏ tù"), Answer(id = "1", text = "Dùng roi vọt, lưu đày"), Answer(id = "2", text = "Chém đầu"), Answer(id = "3", text = "Phạt tiền")), correctAnswerId = "1"),
                    Question(id = "15", text = "Theo truyền thuyết, nghề nào sau đây không phổ biến trong thời kỳ Văn Lang?", answers = listOf(Answer(id = "0", text = "Trồng lúa"), Answer(id = "1", text = "Luyện kim sắt"), Answer(id = "2", text = "Làm đồ gốm"), Answer(id = "3", text = "Đánh cá")), correctAnswerId = "1"),
                    Question(id = "16", text = "Sự ra đời của nhà nước Âu Lạc đánh dấu điều gì so với thời kỳ Văn Lang?", answers = listOf(Answer(id = "0", text = "Sự phát triển về văn hóa"), Answer(id = "1", text = "Sự thống nhất và mở rộng lãnh thổ"), Answer(id = "2", text = "Sự suy yếu về kinh tế"), Answer(id = "3", text = "Sự du nhập của tôn giáo mới")), correctAnswerId = "1"),
                    Question(id = "17", text = "Theo truyền thuyết, tiếng trống đồng trong đời sống của người Văn Lang có ý nghĩa gì?", answers = listOf(Answer(id = "0", text = "Dùng để báo hiệu giờ giấc"), Answer(id = "1", text = "Sử dụng trong các nghi lễ, hội hè"), Answer(id = "2", text = "Dùng để tập luyện quân sự"), Answer(id = "3", text = "Chỉ là vật trang trí")), correctAnswerId = "1"),
                    Question(id = "18", text = "Theo truyền thuyết, hình thức nhà ở phổ biến của cư dân Văn Lang là gì?", answers = listOf(Answer(id = "0", text = "Nhà sàn"), Answer(id = "1", text = "Nhà mái bằng"), Answer(id = "2", text = "Nhà xây bằng gạch"), Answer(id = "3", text = "Hang động")), correctAnswerId = "0"),
                    Question(id = "19", text = "Giá trị lịch sử to lớn nhất mà thời kỳ Hồng Bàng để lại cho dân tộc Việt Nam là gì?", answers = listOf(Answer(id = "0", text = "Những công trình kiến trúc vĩ đại"), Answer(id = "1", text = "Ý thức về nguồn gốc tổ tiên và tinh thần đoàn kết"), Answer(id = "2", text = "Hệ thống chữ viết hoàn chỉnh"), Answer(id = "3", text = "Một nền kinh tế thịnh vượng")), correctAnswerId = "1"),
                )
            ),
            Quiz(
                id = "2",
                title = "Hành trình về cội nguồn - Thời kỳ Hồng Bàng - Bộ 3",
                formPeriod = -2879,
                toPeriod = -258,
                period = "2879 TCN - 258 TCN",
                questionCount = 20,
                durationMinutes = 15,
                playCount = 0,
                image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
                questions = listOf(
                    Question(id = "0", text = "Theo truyền thuyết, vị vua Hùng cuối cùng đã nhường ngôi cho ai?", answers = listOf(Answer(id = "0", text = "An Dương Vương"), Answer(id = "1", text = "Không nhường ngôi"), Answer(id = "2", text = "Con trai trưởng"), Answer(id = "3", text = "Lạc Long Quân")), correctAnswerId = "0"),
                    Question(id = "1", text = "Theo các nhà nghiên cứu, yếu tố nào sau đây đóng vai trò quan trọng trong sự hình thành và phát triển của nhà nước Văn Lang?", answers = listOf(Answer(id = "0", text = "Thương mại đường biển phát triển"), Answer(id = "1", text = "Nông nghiệp trồng lúa nước và nghề thủ công"), Answer(id = "2", text = "Sự xâm lược và cai trị của các nước lân bang"), Answer(id = "3", text = "Việc phát minh ra chữ viết sớm")), correctAnswerId = "1"),
                    Question(id = "2", text = "Trong truyền thuyết, các bộ lạc Lạc Việt thường sinh sống tập trung ở đâu?", answers = listOf(Answer(id = "0", text = "Vùng núi cao"), Answer(id = "1", text = "Ven các con sông lớn"), Answer(id = "2", text = "Các đảo ngoài khơi"), Answer(id = "3", text = "Sa mạc")), correctAnswerId = "1"),
                    Question(id = "3", text = "Theo truyền thuyết, con trai của Lạc Long Quân và Âu Cơ đã đi về đâu?", answers = listOf(Answer(id = "0", text = "Xuống biển"), Answer(id = "1", text = "Lên núi"), Answer(id = "2", text = "Đi về phương Bắc"), Answer(id = "3", text = "Ở lại đồng bằng")), correctAnswerId = "1"),
                    Question(id = "4", text = "Những chiếc trống đồng Đông Sơn được coi là biểu tượng của nền văn hóa nào?", answers = listOf(Answer(id = "0", text = "Văn hóa Chăm Pa"), Answer(id = "1", text = "Văn hóa Văn Lang - Âu Lạc"), Answer(id = "2", text = "Văn hóa Phù Nam"), Answer(id = "3", text = "Văn hóa Khmer")), correctAnswerId = "1"),
                    Question(id = "5", text = "Theo truyền thuyết, tục 'búi tóc' của người Việt có từ thời kỳ nào?", answers = listOf( Answer(id = "0", text = "Thời kỳ Bắc thuộc"), Answer(id = "1", text = "Thời kỳ Hùng Vương"), Answer(id = "2", text = "Thời nhà Lý"), Answer(id = "3", text = "Thời nhà Nguyễn")), correctAnswerId = "1"),
                    Question(id = "6", text = "Trong xã hội Văn Lang, vai trò của người phụ nữ được thể hiện như thế nào (theo các dấu tích và truyền thuyết)?", answers = listOf(Answer(id = "0", text = "Hoàn toàn lệ thuộc vào nam giới"), Answer(id = "1", text = "Có vai trò nhất định trong sản xuất và đời sống"), Answer(id = "2", text = "Nắm giữ quyền lực chính trị cao nhất"), Answer(id = "3", text = "Chỉ tham gia vào các hoạt động nghi lễ")), correctAnswerId = "1"),
                    Question(id = "7", text = "Theo truyền thuyết, con vật nào thường được người Lạc Việt thờ cúng?", answers = listOf(Answer(id = "0", text = "Sư tử"), Answer(id = "1", text = "Cá sấu, chim"), Answer(id = "2", text = "Kỳ lân"), Answer(id = "3", text = "Rồng phương Đông")), correctAnswerId = "1"),
                    Question(id = "8", text = "Theo truyền thuyết, sự tích bánh chưng bánh giầy mang ý nghĩa gì?", answers = listOf(Answer(id = "0", text = "Thể hiện sự giàu có của nhà vua"), Answer(id = "1", text = "Tượng trưng cho trời tròn đất vuông và lòng biết ơn tổ tiên"), Answer(id = "2", text = "Kỷ niệm một chiến thắng quân sự"), Answer(id = "3", text = "Mô tả cuộc sống hàng ngày của người dân")), correctAnswerId = "1"),
                    Question(id = "9", text = "Theo truyền thuyết, hình thức tổ chức quân đội thời Văn Lang như thế nào?", answers = listOf(Answer(id = "0", text = "Quân đội chính quy với trang bị hiện đại"), Answer(id = "1", text = "Quân đội sơ khai, vũ khí thô sơ, mang tính bộ lạc"), Answer(id = "2", text = "Hoàn toàn không có quân đội"), Answer(id = "3", text = "Quân đội thuê từ nước ngoài")), correctAnswerId = "1"),
                    Question(id = "10", text = "Theo truyền thuyết, nghề nào sau đây có thể đã xuất hiện trong thời kỳ Văn Lang?", answers = listOf(Answer(id = "0", text = "Khai thác dầu mỏ"), Answer(id = "1", text = "Nuôi tằm dệt lụa"), Answer(id = "2", text = "Chế tạo ô tô"), Answer(id = "3", text = "Sản xuất điện năng")), correctAnswerId = "1"),
                    Question(id = "11", text = "Các nhà nghiên cứu thường dựa vào nguồn tư liệu nào để tìm hiểu về thời kỳ Hồng Bàng?", answers = listOf(Answer(id = "0", text = "Sách sử ghi chép chi tiết đương thời"), Answer(id = "1", text = "Truyền thuyết, khảo cổ học"), Answer(id = "2", text = "Phim ảnh và các tác phẩm nghệ thuật"), Answer(id = "3", text = "Lời kể của người dân ngày nay")), correctAnswerId = "1"),
                    Question(id = "12", text = "Theo truyền thuyết, ai là người đã chỉ huy cuộc chiến chống quân Thục xâm lược trước khi An Dương Vương lên ngôi?", answers = listOf(Answer(id = "0", text = "Lạc Long Quân"), Answer(id = "1", text = "Hùng Vương"), Answer(id = "2", text = "Thánh Gióng"), Answer(id = "3", text = "Không có ghi chép về cuộc chiến này")), correctAnswerId = "1"),
                    Question(id = "13", text = "Theo truyền thuyết, tục 'xăm mình' của người Việt có nguồn gốc từ đâu?", answers = listOf(Answer(id = "0", text = "Để làm đẹp"), Answer(id = "1", text = "Để tránh thủy quái"), Answer(id = "2", text = "Để thể hiện sức mạnh"), Answer(id = "3", text = "Để phân biệt đẳng cấp")), correctAnswerId = "1"),
                    Question(id = "14", text = "Trong xã hội Văn Lang, vai trò của các 'bộ' có thể được hiểu như thế nào?", answers = listOf(Answer(id = "0", text = "Các đơn vị hành chính cấp tỉnh"), Answer(id = "1", text = "Các liên minh bộ lạc có quyền tự trị nhất định"), Answer(id = "2", text = "Các tổ chức kinh tế lớn"), Answer(id = "3", text = "Các đơn vị quân đội chủ chốt")), correctAnswerId = "1"),
                    Question(id = "15", text = "Theo truyền thuyết, loại hình nghệ thuật nào có thể đã xuất hiện trong thời kỳ Văn Lang?", answers = listOf(Answer(id = "0", text = "Opera"), Answer(id = "1", text = "Hát xoan, múa rối nước"), Answer(id = "2", text = "Nhạc giao hưởng"), Answer(id = "3", text = "Điện ảnh")), correctAnswerId = "1"),
                    Question(id = "16", text = "Theo truyền thuyết, sự kiện nào đánh dấu sự chuyển giao từ thời kỳ Hồng Bàng sang thời kỳ Âu Lạc?", answers = listOf(Answer(id = "0", text = "Cuộc kháng chiến chống quân Ân thắng lợi"), Answer(id = "1", text = "Thục Phán đánh bại Hùng Vương"), Answer(id = "2", text = "Việc dời đô về Cổ Loa"), Answer(id = "3", text = "Sự ra đời của trống đồng")), correctAnswerId = "1"),
                    Question(id = "17", text = "Theo truyền thuyết, hình thức mai táng phổ biến của người Văn Lang là gì?", answers = listOf(Answer(id = "0", text = "Hỏa táng"), Answer(id = "1", text = "Chôn cất trong thạp đồng, mộ thuyền"), Answer(id = "2", text = "Thủy táng"), Answer(id = "3", text = "Để trên cây")), correctAnswerId = "1"),
                    Question(id = "18", text = "Theo truyền thuyết, vai trò của các 'Lạc tướng' trong xã hội Văn Lang là gì?", answers = listOf(Answer(id = "0", text = "Chỉ huy quân đội"), Answer(id = "1", text = "Quản lý các vùng đất và dân cư"), Answer(id = "2", text = "Làm các công việc tế lễ"), Answer(id = "3", text = "Buôn bán và trao đổi hàng hóa")), correctAnswerId = "1"),
                    Question(id = "19", text = "Ý nghĩa sâu sắc nhất mà những truyền thuyết về thời kỳ Hồng Bàng truyền lại cho thế hệ sau là gì?", answers = listOf(Answer(id = "0", text = "Những câu chuyện giải trí hấp dẫn"), Answer(id = "1", text = "Niềm tự hào về nguồn gốc dân tộc và tinh thần dựng nước"), Answer(id = "2", text = "Những kiến thức khoa học cổ đại"), Answer(id = "3", text = "Những bài học về chiến tranh và chinh phục")), correctAnswerId = "1"),
                )
            ),
            Quiz(
                id = "3",
                title = "Khám phá nước Âu Lạc - Bộ 1",
                formPeriod = -257,
                toPeriod = -207,
                period = "-257 - -207",
                questionCount = 20,
                durationMinutes = 15,
                playCount = 0,
                image = "https://thcsvathptnguyenkhuyendanang.edu.vn/wp-content/uploads/2018/10/lich-su-viet-nam-bang-tranh-nuoc-au-lac-1063.jpg",
                questions = listOf(
                    Question(id = "0", text = "Ai là người đã thành lập nhà nước Âu Lạc?", answers = listOf(Answer(id = "0", text = "Hùng Vương"), Answer(id = "1", text = "Thục Phán"), Answer(id = "2", text = "Triệu Đà"), Answer(id = "3", text = "Ngô Quyền")), correctAnswerId = "1"),
                    Question(id = "1", text = "Kinh đô của nước Âu Lạc có tên là gì?", answers = listOf(Answer(id = "0", text = "Phong Châu"), Answer(id = "1", text = "Cổ Loa"), Answer(id = "2", text = "Hoa Lư"), Answer(id = "3", text = "Thăng Long")), correctAnswerId = "1"),
                    Question(id = "2", text = "Năm nào được coi là năm thành lập nhà nước Âu Lạc?", answers = listOf(Answer(id = "0", text = "Năm 2879 TCN"), Answer(id = "1", text = "Năm 257 TCN"), Answer(id = "2", text = "Năm 207 TCN"), Answer(id = "3", text = "Năm 938 CN")), correctAnswerId = "1"),
                    Question(id = "3", text = "Thục Phán sau khi lên ngôi đã xưng vương với danh hiệu nào?", answers = listOf(Answer(id = "0", text = "Hùng Vương"), Answer(id = "1", text = "An Dương Vương"), Answer(id = "2", text = "Triệu Vũ Vương"), Answer(id = "3", text = "Lý Thái Tổ")), correctAnswerId = "1"),
                    Question(id = "4", text = "Công trình kiến trúc nổi bật nào được xây dựng dưới thời Âu Lạc?", answers = listOf(Answer(id = "0", text = "Vạn Lý Trường Thành"), Answer(id = "1", text = "Thành Cổ Loa"), Answer(id = "2", text = "Kinh thành Huế"), Answer(id = "3", text = "Thành nhà Hồ")), correctAnswerId = "1"),
                    Question(id = "5", text = "Ai là người được cho là đã chế tạo ra nỏ thần Liên Châu giúp Âu Lạc chống lại quân xâm lược?", answers = listOf(Answer(id = "0", text = "Lạc Long Quân"), Answer(id = "1", text = "Thánh Gióng"), Answer(id = "2", text = "Cao Lỗ"), Answer(id = "3", text = "Lý Thường Kiệt")), correctAnswerId = "2"),
                    Question(id = "6", text = "Nỏ thần Liên Châu có đặc điểm gì nổi bật?", answers = listOf(Answer(id = "0", text = "Bắn được nhiều mũi tên cùng lúc"), Answer(id = "1", text = "Tự động bắn khi có địch"), Answer(id = "2", text = "Bắn xa hàng nghìn dặm"), Answer(id = "3", text = "Làm bằng vàng")), correctAnswerId = "0"),
                    Question(id = "7", text = "Theo truyền thuyết, Mỵ Châu là con gái của ai?", answers = listOf(Answer(id = "0", text = "Hùng Vương"), Answer(id = "1", text = "An Dương Vương"), Answer(id = "2", text = "Triệu Đà"), Answer(id = "3", text = "Lý Ông Trọng")), correctAnswerId = "1"),
                    Question(id = "8", text = "Ai là người đã lừa Mỵ Châu để phá hủy nỏ thần, dẫn đến sự sụp đổ của Âu Lạc?", answers = listOf(Answer(id = "0", text = "Cao Lỗ"), Answer(id = "1", text = "Triệu Đà"), Answer(id = "2", text = "Tần Thủy Hoàng"), Answer(id = "3", text = "Hán Vũ Đế")), correctAnswerId = "1"),
                    Question(id = "9", text = "Năm nào nước Âu Lạc bị sáp nhập vào nước Nam Việt của Triệu Đà?", answers = listOf(Answer(id = "0", text = "Năm 2879 TCN"), Answer(id = "1", text = "Năm 257 TCN"), Answer(id = "2", text = "Năm 207 TCN"), Answer(id = "3", text = "Năm 111 TCN")), correctAnswerId = "2"),
                    Question(id = "10", text = "Sự kiện Mỵ Châu - Trọng Thủy thể hiện điều gì trong lịch sử Âu Lạc?", answers = listOf(Answer(id = "0", text = "Tình yêu bất diệt"), Answer(id = "1", text = "Sự mất cảnh giác và hậu quả của nội gián"), Answer(id = "2", text = "Sức mạnh của vũ khí"), Answer(id = "3", text = "Quan hệ hòa hiếu giữa các quốc gia")), correctAnswerId = "1"),
                    Question(id = "11", text = "Âu Lạc được hình thành trên cơ sở hợp nhất của những nhà nước nào?", answers = listOf(Answer(id = "0", text = "Văn Lang và Xích Quỷ"), Answer(id = "1", text = "Văn Lang và Âu Việt"), Answer(id = "2", text = "Lạc Việt và Điền Việt"), Answer(id = "3", text = "Mê Linh và Lãng Bạc")), correctAnswerId = "1"),
                    Question(id = "12", text = "Theo các nhà khảo cổ học, thành Cổ Loa có cấu trúc đặc biệt như thế nào?", answers = listOf(Answer(id = "0", text = "Chỉ có một vòng thành"), Answer(id = "1", text = "Có ba vòng thành khép kín"), Answer(id = "2", text = "Làm hoàn toàn bằng đá"), Answer(id = "3", text = "Có hình vuông")), correctAnswerId = "1"),
                    Question(id = "13", text = "Vũ khí nào sau đây được tìm thấy nhiều tại Cổ Loa, chứng tỏ trình độ quân sự của Âu Lạc?", answers = listOf(Answer(id = "0", text = "Kiếm sắt"), Answer(id = "1", text = "Mũi tên đồng"), Answer(id = "2", text = "Súng thần công"), Answer(id = "3", text = "Bom ba càng")), correctAnswerId = "1"),
                    Question(id = "14", text = "Thời kỳ Âu Lạc kéo dài trong khoảng bao nhiêu năm?", answers = listOf(Answer(id = "0", text = "Hơn 2000 năm"), Answer(id = "1", text = "Khoảng 50 năm"), Answer(id = "2", text = "Gần 300 năm"), Answer(id = "3", text = "Hơn 1000 năm")), correctAnswerId = "1"),
                    Question(id = "15", text = "Theo truyền thuyết, sau khi mất nước, Mỵ Châu đã hóa thành gì?", answers = listOf(Answer(id = "0", text = "Chim phượng hoàng"), Answer(id = "1", text = "Hạt châu"), Answer(id = "2", text = "Cây hoa"), Answer(id = "3", text = "Dòng sông")), correctAnswerId = "1"),
                    Question(id = "16", text = "Sự sụp đổ của nước Âu Lạc đánh dấu sự kết thúc của thời kỳ nào trong lịch sử Việt Nam?", answers = listOf(Answer(id = "0", text = "Thời kỳ đồ đá"), Answer(id = "1", text = "Thời kỳ dựng nước Văn Lang - Âu Lạc"), Answer(id = "2", text = "Thời kỳ Bắc thuộc"), Answer(id = "3", text = "Thời kỳ độc lập tự chủ")), correctAnswerId = "1"),
                    Question(id = "17", text = "Theo truyền thuyết, Trọng Thủy là con trai của ai?", answers = listOf(Answer(id = "0", text = "Hùng Vương"), Answer(id = "1", text = "An Dương Vương"), Answer(id = "2", text = "Triệu Đà"), Answer(id = "3", text = "Lý Ông Trọng")), correctAnswerId = "2"),
                    Question(id = "18", text = "Ý nghĩa lịch sử quan trọng nhất của nhà nước Âu Lạc là gì?", answers = listOf(Answer(id = "0", text = "Mở đầu thời kỳ đồ đồng"), Answer(id = "1", text = "Thể hiện sự thống nhất và sức mạnh của người Việt cổ"), Answer(id = "2", text = "Xây dựng hệ thống chữ viết đầu tiên"), Answer(id = "3", text = "Đánh bại quân xâm lược Mông - Nguyên")), correctAnswerId = "1"),
                    Question(id = "19", text = "Theo truyền thuyết, sau khi An Dương Vương mất, ông đã hóa thành gì?", answers = listOf(Answer(id = "0", text = "Rồng"), Answer(id = "1", text = "Rùa vàng"), Answer(id = "2", text = "Cây đa"), Answer(id = "3", text = "Ngọn núi")), correctAnswerId = "1"),
                )
            ),
            Quiz(
                id = "4",
                title = "Âu Lạc - Những bí ẩn lịch sử - Bộ 2",
                formPeriod = -257,
                toPeriod = -207,
                period = "-257 - -207",
                questionCount = 20,
                durationMinutes = 15,
                playCount = 0,
                image = "https://thcsvathptnguyenkhuyendanang.edu.vn/wp-content/uploads/2018/10/lich-su-viet-nam-bang-tranh-nuoc-au-lac-1063.jpg",
                questions = listOf(
                    Question(id = "0", text = "Tên gọi 'Âu Lạc' có ý nghĩa là gì?", answers = listOf(Answer(id = "0", text = "Đất nước của sự thanh bình"), Answer(id = "1", text = "Sự kết hợp giữa Âu Việt và Lạc Việt"), Answer(id = "2", text = "Đất nước của vàng bạc"), Answer(id = "3", text = "Đất nước hùng mạnh")), correctAnswerId = "1"),
                    Question(id = "1", text = "Theo truyền thuyết, An Dương Vương đã dời đô từ đâu về Cổ Loa?", answers = listOf(Answer(id = "0", text = "Phong Châu"), Answer(id = "1", text = "Không có sự dời đô"), Answer(id = "2", text = "Hoa Lư"), Answer(id = "3", text = "Thăng Long")), correctAnswerId = "0"),
                    Question(id = "2", text = "Yếu tố nào sau đây không đóng vai trò quan trọng trong sự phát triển của Âu Lạc?", answers = listOf(Answer(id = "0", text = "Nông nghiệp trồng lúa nước"), Answer(id = "1", text = "Nghề luyện kim đồng và chế tạo vũ khí"), Answer(id = "2", text = "Thương mại đường biển quốc tế"), Answer(id = "3", text = "Xây dựng thành lũy kiên cố")), correctAnswerId = "2"),
                    Question(id = "3", text = "Theo truyền thuyết, ai đã nhiều lần dẫn quân xâm lược Âu Lạc?", answers = listOf(Answer(id = "0", text = "Tần Thủy Hoàng"), Answer(id = "1", text = "Triệu Đà"), Answer(id = "2", text = "Hán Vũ Đế"), Answer(id = "3", text = "Mông Cổ")), correctAnswerId = "1"),
                    Question(id = "4", text = "Thành Cổ Loa có hình dáng đặc biệt như thế nào?", answers = listOf(Answer(id = "0", text = "Hình chữ nhật"), Answer(id = "1", text = "Hình xoắn ốc"), Answer(id = "2", text = "Hình tròn"), Answer(id = "3", text = "Hình vuông")), correctAnswerId = "1"),
                    Question(id = "5", text = "Theo truyền thuyết, điều gì đã khiến nỏ thần Liên Châu mất đi sức mạnh?", answers = listOf(Answer(id = "0", text = "Bị gãy do va chạm"), Answer(id = "1", text = "Bị đánh tráo lẫy"), Answer(id = "2", text = "Hết thuốc nỏ"), Answer(id = "3", text = "Do thời gian sử dụng quá lâu")), correctAnswerId = "1"),
                    Question(id = "6", text = "Trong xã hội Âu Lạc, tầng lớp nào có vai trò quan trọng trong việc chỉ huy quân đội?", answers = listOf(Answer(id = "0", text = "Nông dân"), Answer(id = "1", text = "Các tướng lĩnh"), Answer(id = "2", text = "Thợ thủ công"), Answer(id = "3", text = "Thương nhân")), correctAnswerId = "1"),
                    Question(id = "7", text = "Theo truyền thuyết, ai đã khuyên An Dương Vương gả Mỵ Châu cho Trọng Thủy?", answers = listOf(Answer(id = "0", text = "Cao Lỗ"), Answer(id = "1", text = "Không ai khuyên"), Answer(id = "2", text = "Các大臣 (đại thần)"), Answer(id = "3", text = "Triệu Đà")), correctAnswerId = "1"),
                    Question(id = "8", text = "Sự kiện nào sau đây không diễn ra trong thời kỳ Âu Lạc?", answers = listOf(Answer(id = "0", text = "Xây dựng thành Cổ Loa"), Answer(id = "1", text = "Chế tạo nỏ thần Liên Châu"), Answer(id = "2", text = "Chiến thắng Bạch Đằng"), Answer(id = "3", text = "Âu Lạc bị sáp nhập vào Nam Việt")), correctAnswerId = "2"),
                    Question(id = "9", text = "Theo truyền thuyết, sau khi bị Triệu Đà đánh bại, An Dương Vương đã chạy trốn bằng cách nào?", answers = listOf(Answer(id = "0", text = "Đi thuyền ra biển"), Answer(id = "1", text = "Cưỡi ngựa lên núi"), Answer(id = "2", text = "Nhảy xuống biển và đi theo rùa vàng"), Answer(id = "3", text = "Ẩn náu trong rừng sâu")), correctAnswerId = "2"),
                    Question(id = "10", text = "Theo các nhà sử học, yếu tố nào góp phần vào sự sụp đổ nhanh chóng của Âu Lạc?", answers = listOf(Answer(id = "0", text = "Sự yếu kém về kinh tế"), Answer(id = "1", text = "Sự chia rẽ trong nội bộ"), Answer(id = "2", text = "Sự mất cảnh giác và tin người"), Answer(id = "3", text = "Quân đội quá yếu kém")), correctAnswerId = "2"),
                    Question(id = "11", text = "Âu Lạc có vị trí địa lý chiến lược như thế nào?", answers = listOf(Answer(id = "0", text = "Nằm sâu trong lục địa"), Answer(id = "1", text = "Kiểm soát vùng đồng bằng màu mỡ và các tuyến giao thông quan trọng"), Answer(id = "2", text = "Chỉ là một vùng đất nhỏ hẹp ven biển"), Answer(id = "3", text = "Nằm ở vùng núi cao hiểm trở")), correctAnswerId = "1"),
                    Question(id = "12", text = "Theo truyền thuyết, ai là người đã báo mộng cho An Dương Vương về sự nguy hiểm từ Triệu Đà?", answers = listOf(Answer(id = "0", text = "Lạc Long Quân"), Answer(id = "1", text = "Thần Rùa Vàng"), Answer(id = "2", text = "Cao Lỗ"), Answer(id = "3", text = "Mỵ Châu")), correctAnswerId = "1"),
                    Question(id = "13", text = "Theo truyền thuyết, sau khi Trọng Thủy tự vẫn, Mỵ Châu đã làm gì?", answers = listOf(Answer(id = "0", text = "Trở về với cha"), Answer(id = "1", text = "Tự vẫn theo chồng"), Answer(id = "2", text = "Đi tu"), Answer(id = "3", text = "Trốn thoát ra nước ngoài")), correctAnswerId = "1"),
                    Question(id = "14", text = "Thời kỳ Âu Lạc có những đóng góp gì cho sự phát triển của văn hóa Việt Nam?", answers = listOf(Answer(id = "0", text = "Du nhập chữ Hán"), Answer(id = "1", text = "Phát triển kỹ thuật xây dựng thành lũy và chế tạo vũ khí"), Answer(id = "2", text = "Xây dựng các đền thờ Phật giáo đầu tiên"), Answer(id = "3", text = "Mở rộng giao thương với phương Tây")), correctAnswerId = "1"),
                    Question(id = "15", text = "Theo truyền thuyết, điều gì đã xảy ra với nỏ thần Liên Châu sau khi Âu Lạc sụp đổ?", answers = listOf(Answer(id = "0", text = "Bị quân TriệuĐà cướp đi"), Answer(id = "1", text = "Bị phá hủy hoàn toàn"), Answer(id = "2", text = "Biến mất một cách bí ẩn"), Answer(id = "3", text = "Được cất giữ cẩn thận")), correctAnswerId = "2"),
                    Question(id = "16", text = "Sự tồn tại của thành Cổ Loa cho thấy điều gì về trình độ tổ chức xã hội của người Âu Lạc?", answers = listOf(Answer(id = "0", text = "Còn rất lạc hậu"), Answer(id = "1", text = "Đã có sự phân công lao động và khả năng huy động nguồn lực lớn"), Answer(id = "2", text = "Hoàn toàn phụ thuộc vào sự giúp đỡ của nước ngoài"), Answer(id = "3", text = "Chưa có nhà nước thực sự")), correctAnswerId = "1"),
                    Question(id = "17", text = "Theo truyền thuyết, sau khi hóa thành rùa vàng, An Dương Vương đã đi về đâu?", answers = listOf(Answer(id = "0", text = "Về trời"), Answer(id = "1", text = "Xuống biển sâu"), Answer(id = "2", text = "Ẩn mình trong núi"), Answer(id = "3", text = "Không rõ tung tích")), correctAnswerId = "1"),
                    Question(id = "18", text = "Theo truyền thuyết, mối quan hệ giữa Âu Lạc và Nam Việt ban đầu như thế nào?", answers = listOf(Answer(id = "0", text = "Thù địch gay gắt"), Answer(id = "1", text = "Hòa hiếu, có sự giao hôn"), Answer(id = "2", text = "Hoàn toàn không có quan hệ"), Answer(id = "3", text = "Phụ thuộc lẫn nhau về kinh tế")), correctAnswerId = "1"),
                    Question(id = "19", text = "Giá trị lịch sử tiêu biểu nào của thời kỳ Âu Lạc vẫn còn được lưu giữ và phát huy đến ngày nay?", answers = listOf(Answer(id = "0", text = "Hệ thống chữ viết"), Answer(id = "1", text = "Kỹ thuật xây dựng thành lũy độc đáo"), Answer(id = "2", text = "Các công trình kiến trúc tôn giáo lớn"), Answer(id = "3", text = "Một nền kinh tế thịnh vượng")), correctAnswerId = "1"),
                )
            ),
            Quiz(
                id = "5",
                title = "Âu Lạc - Giai đoạn lịch sử oai hùng - Bộ 3",
                formPeriod = -257,
                toPeriod = -207,
                period = "-257 - -207",
                questionCount = 20,
                durationMinutes = 15,
                playCount = 0,
                image = "https://thcsvathptnguyenkhuyendanang.edu.vn/wp-content/uploads/2018/10/lich-su-viet-nam-bang-tranh-nuoc-au-lac-1063.jpg",
                questions = listOf(
                    Question(id = "0", text = "Theo truyền thuyết, ai là người đã truyền ngôi cho Thục Phán để thành lập Âu Lạc?", answers = listOf(Answer(id = "0", text = "Hùng Vương thứ nhất"), Answer(id = "1", text = "Hùng Vương cuối cùng"), Answer(id = "2", text = "Lạc Long Quân"), Answer(id = "3", text = "Không có sự truyền ngôi trực tiếp")), correctAnswerId = "1"),
                    Question(id = "1", text = "Sự hình thành nhà nước Âu Lạc có ý nghĩa gì đối với sự phát triển của cộng đồng người Việt cổ?", answers = listOf(Answer(id = "0", text = "Đánh dấu sự suy yếu"), Answer(id = "1", text = "Thể hiện sự thống nhất và sức mạnh mới"), Answer(id = "2", text = "Không có nhiều ý nghĩa"), Answer(id = "3", text = "Mở đầu thời kỳ bị đô hộ")), correctAnswerId = "1"),
                    Question(id = "2", text = "Theo truyền thuyết, thành Cổ Loa được xây dựng trong khoảng thời gian bao lâu?", answers = listOf(Answer(id = "0", text = "Vài tháng"), Answer(id = "1", text = "Rất nhiều lần xây dựng không thành công rồi mới hoàn tất"), Answer(id = "2", text = "Khoảng một năm"), Answer(id = "3", text = "Vài chục năm")), correctAnswerId = "1"),
                    Question(id = "3", text = "Yếu tố tự nhiên nào sau đây có vai trò quan trọng trong việc bảo vệ thành Cổ Loa?", answers = listOf(Answer(id = "0", text = "Địa hình núi cao hiểm trở"), Answer(id = "1", text = "Hệ thống sông ngòi, đầm lầy bao quanh"), Answer(id = "2", text = "Khí hậu khắc nghiệt"), Answer(id = "3", text = "Vị trí xa xôi hẻo lánh")), correctAnswerId = "1"),
                    Question(id = "4", text = "Theo truyền thuyết, ai là người đã tiết lộ bí mật về nỏ thần cho Trọng Thủy?", answers = listOf(Answer(id = "0", text = "Cao Lỗ"), Answer(id = "1", text = "Mỵ Châu"), Answer(id = "2", text = "An Dương Vương"), Answer(id = "3", text = "Không ai tiết lộ")), correctAnswerId = "1"),
                    Question(id = "5", text = "Theo truyền thuyết, điều gì đã xảy ra với Trọng Thủy sau khi Âu Lạc sụp đổ?", answers = listOf(Answer(id = "0", text = "Trở về nước Triệu"), Answer(id = "1", text = "Tự vẫn vì hối hận"), Answer(id = "2", text = "Bị quân Âu Lạc giết"), Answer(id = "3", text = "Trốn thoát thành công")), correctAnswerId = "1"),
                    Question(id = "6", text = "Trong xã hội Âu Lạc, mối quan hệ giữa người dân và nhà nước có đặc điểm gì?", answers = listOf(Answer(id = "0", text = "Hoàn toàn đối lập"), Answer(id = "1", text = "Có sự gắn kết và tinh thần đoàn kết chống ngoại xâm"), Answer(id = "2", text = "Chỉ dựa trên sự phục tùng tuyệt đối"), Answer(id = "3", text = "Không có sự tổ chức chặt chẽ")), correctAnswerId = "1"),
                    Question(id = "7", text = "Theo truyền thuyết, hình ảnh Rùa Vàng có ý nghĩa gì đối với nhà nước Âu Lạc?", answers = listOf(Answer(id = "0", text = "Biểu tượng của sự giàu có"), Answer(id = "1", text = "Biểu tượng của sự linh thiêng và bảo hộ"), Answer(id = "2", text = "Chỉ là một con vật bình thường"), Answer(id = "3", text = "Biểu tượng của sức mạnh quân sự")), correctAnswerId = "1"),
                    Question(id = "8", text = "Theo truyền thuyết, sự kiện nào sau đây không liên quan trực tiếp đến sự sụp đổ của Âu Lạc?", answers = listOf(Answer(id = "0", text = "Việc Mỵ Châu tiết lộ bí mật nỏ thần"), Answer(id = "1", text = "Cuộc xâm lược của Triệu Đà"), Answer(id = "2", text = "Sự bất hòa giữa An Dương Vương và Cao Lỗ"), Answer(id = "3", text = "Sự mất cảnh giác của An Dương Vương")), correctAnswerId = "2"),
                    Question(id = "9", text = "Theo truyền thuyết, sau khi An Dương Vương bị mất nước, ông đã mang theo vật gì bên mình?", answers = listOf(Answer(id = "0", text = "Nỏ thần"), Answer(id = "1", text = "Gươm thần"), Answer(id = "2", text = "Không mang theo gì"), Answer(id = "3", text = "Ấn tín nhà vua")), correctAnswerId = "0"),
                    Question(id = "10", text = "Theo các nhà sử học, yếu tố nào sau đây cho thấy sự phát triển về kỹ thuật của người Âu Lạc?", answers = listOf(Answer(id = "0", text = "Việc sử dụng rộng rãi đồ sắt"), Answer(id = "1", text = "Kỹ thuật chế tạo nỏ liên châu và xây dựng thành Cổ Loa"), Answer(id = "2", text = "Khả năng đóng tàu thuyền vượt biển"), Answer(id = "3", text = "Việc phát minh ra thuốc súng")), correctAnswerId = "1"),
                    Question(id = "11", text = "Âu Lạc có vai trò như thế nào trong quá trình hình thành bản sắc văn hóa Việt Nam?", answers = listOf(Answer(id = "0", text = "Không có vai trò gì"), Answer(id = "1", text = "Tiếp nối và phát triển những giá trị văn hóa từ thời Văn Lang"), Answer(id = "2", text = "Du nhập hoàn toàn văn hóa từ nước ngoài"), Answer(id = "3", text = "Làm suy yếu bản sắc văn hóa Việt cổ")), correctAnswerId = "1"),
                    Question(id = "12", text = "Theo truyền thuyết, điều gì đã xảy ra tại nơi Mỵ Châu gieo mình xuống biển?", answers = listOf(Answer(id = "0", text = "Biển cạn khô"), Answer(id = "1", text = "Xuất hiện ngọc trai"), Answer(id = "2", text = "Nước biển đổi màu"), Answer(id = "3", text = "Một hòn đảo nổi lên")), correctAnswerId = "1"),
                    Question(id = "13", text = "Theo truyền thuyết, sau khi An Dương Vương gặp Rùa Vàng, ông đã làm gì?", answers = listOf(Answer(id = "0", text = "Xin Rùa Vàng ban cho sức mạnh"), Answer(id = "1", text = "Nhổ móng Rùa Vàng làm lẫy nỏ"), Answer(id = "2", text = "Cùng Rùa Vàng đi chu du thiên hạ"), Answer(id = "3", text = "Không gặp Rùa Vàng")), correctAnswerId = "1"),
                    Question(id = "14", text = "Thời kỳ Âu Lạc có ý nghĩa như thế nào trong việc bảo vệ độc lập của dân tộc?", answers = listOf(Answer(id = "0", text = "Không có ý nghĩa gì"), Answer(id = "1", text = "Thể hiện ý chí tự cường và khả năng chống ngoại xâm của người Việt cổ"), Answer(id = "2", text = "Chỉ là một giai đoạn bị đô hộ"), Answer(id = "3", text = "Mở đường cho sự xâm lược dễ dàng hơn")), correctAnswerId = "1"),
                    Question(id = "15", text = "Theo truyền thuyết, vật báu nào được coi là biểu tượng sức mạnh của nước Âu Lạc?", answers = listOf(Answer(id = "0", text = "Trống đồng"), Answer(id = "1", text = "Nỏ thần Liên Châu"), Answer(id = "2", text = "Gươm thần"), Answer(id = "3", text = "Ấn tín nhà vua")), correctAnswerId = "1"),
                    Question(id = "16", text = "Theo các nhà nghiên cứu, sự khác biệt lớn nhất giữa nhà nước Văn Lang và Âu Lạc là gì?", answers = listOf(Answer(id = "0", text = "Về quy mô lãnh thổ và trình độ tổ chức"), Answer(id = "1", text = "Về hệ thống chữ viết"), Answer(id = "2", text = "Về tín ngưỡng"), Answer(id = "3", text = "Về hoạt động kinh tế")), correctAnswerId = "0"),
                    Question(id = "17", text = "Theo truyền thuyết, sau khi mất nỏ thần, An Dương Vương đã dựa vào đâu để chống lại quân Triệu?", answers = listOf(Answer(id = "0", text = "Sức mạnh của quân đội"), Answer(id = "1", text = "Thành Cổ Loa kiên cố"), Answer(id = "2", text = "Sự giúp đỡ của các bộ lạc khác"), Answer(id = "3", text = "Không còn khả năng chống cự")), correctAnswerId = "1"),
                    Question(id = "18", text = "Theo truyền thuyết, mối tình Mỵ Châu - Trọng Thủy thường được nhìn nhận như một bài học về điều gì?", answers = listOf(Answer(id = "0", text = "Sức mạnh của tình yêu vượt qua mọi rào cản"), Answer(id = "1", text = "Sự mất cảnh giác và hậu quả của việc đặt lợi ích cá nhân lên trên lợi ích quốc gia"), Answer(id = "2", text = "Tình hữu nghị giữa các dân tộc"), Answer(id = "3", text = "Vẻ đẹp của sự hy sinh")), correctAnswerId = "1"),
                    Question(id = "19", text = "Giá trị nào của thời kỳ Âu Lạc vẫn còn có ý nghĩa trong việc xây dựng và bảo vệ đất nước ngày nay?", answers = listOf(Answer(id = "0", text = "Kỹ thuật luyện kim"), Answer(id = "1", text = "Tinh thần đoàn kết, ý chí tự cường và cảnh giác trước âm mưu ngoại bang"), Answer(id = "2", text = "Hệ thống luật pháp cổ đại"), Answer(id = "3", text = "Các phong tục tập quán")), correctAnswerId = "1"),
                )
            ),
        )
    }
}