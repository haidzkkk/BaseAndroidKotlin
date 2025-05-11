package com.app.motel.data.network

import com.app.motel.data.model.HistoricalEvent
import com.app.motel.data.model.HistoricalFigure
import com.app.motel.data.model.HistoryDynasty

object Data {
    fun historicalDynasties(): List<HistoryDynasty> = listOf(
        HistoryDynasty(1, "Hồng Bàng", "2879 TCN", "258 TCN", listOf(
            HistoricalFigure(0, "Hùng Vương", "", "", null, "Quốc tổ", "Hồng Bàng", "Người sáng lập nước Văn Lang...", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/T%C6%B0%E1%BB%A3ng_H%C3%B9ng_V%C6%B0%C6%A1ng_t%E1%BA%A1i_%C4%90%E1%BB%81n_H%C3%B9ng%2C_Tao_%C4%90%C3%A0n%2C_th%C3%A1ng_12_n%C4%83m_2021_%2812%29.jpg/500px-T%C6%B0%E1%BB%A3ng_H%C3%B9ng_V%C6%B0%C6%A1ng_t%E1%BA%A1i_%C4%90%E1%BB%81n_H%C3%B9ng%2C_Tao_%C4%90%C3%A0n%2C_th%C3%A1ng_12_n%C4%83m_2021_%2812%29.jpg", "Hùng_Vương")
        )),
        HistoryDynasty(2, "Âu Lạc", "257 TCN", "179 TCN", listOf(
            HistoricalFigure(1, "An Dương Vương", "", "", null, "Vua nước Âu Lạc", "Âu Lạc", "Người xây thành Cổ Loa...", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b7/An_Duong_Vuong.jpg/220px-An_Duong_Vuong.jpg", "An_Dương_Vương")
        )),
        HistoryDynasty(3, "Nhà Triệu", "207 TCN", "111 TCN", listOf(
            HistoricalFigure(14, "Triệu Đà", "230 TCN", "137 TCN", null, "Vũ Vương", "Nhà Triệu", "Người lập ra nước Nam Việt...", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/Trieu_Da.jpg/220px-Trieu_Da.jpg", "Triệu_Đà")
        )),
        HistoryDynasty(4, "Thời kỳ Bắc thuộc lần I", "111 TCN", "40", emptyList(

        )),
        HistoryDynasty(5, "Hai Bà Trưng", "40", "43", listOf(
            HistoricalFigure(15, "Trưng Trắc", "?", "43", null, "Nữ vương", "Hai Bà Trưng", "Lãnh đạo cuộc khởi nghĩa chống quân Đông Hán...", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Hai_Ba_Trung.jpg/220px-Hai_Ba_Trung.jpg", "Hai_Bà_Trưng"),
            HistoricalFigure(16, "Trưng Nhị", "?", "43", null, "Tướng quân", "Hai Bà Trưng", "Cùng chị lãnh đạo cuộc khởi nghĩa...", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Hai_Ba_Trung.jpg/220px-Hai_Ba_Trung.jpg", "Trưng_Nhị")
        )),
        HistoryDynasty(6, "Thời kỳ Bắc thuộc lần II", "43", "544", emptyList(

        )),
        HistoryDynasty(7, "Tiền Lý", "544", "602", listOf(
            HistoricalFigure(3, "Lý Nam Đế", "503", "548", null, "Hoàng đế", "Tiền Lý", "Lập nước Vạn Xuân...", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Ly_Nam_De.jpg/220px-Ly_Nam_De.jpg", "Lý_Nam_Đế"),
            HistoricalFigure(17, "Triệu Quang Phục", "?", "571", null, "Vua", "Tiền Lý", "Người kế tục Lý Nam Đế, chống quân Lương...", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Trieu_Quang_Phuc.jpg/220px-Trieu_Quang_Phuc.jpg", "Triệu_Quang_Phục")
        )),
        HistoryDynasty(8, "Thời kỳ Bắc thuộc lần III", "602", "905", emptyList(

        )),
        HistoryDynasty(9, "Họ Khúc", "905", "938", listOf(
            HistoricalFigure(18, "Khúc Thừa Dụ", "?", "907", null, "Tiết độ sứ", "Họ Khúc", "Người đặt nền móng tự chủ...", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Khuc_Thua_Du.jpg/220px-Khuc_Thua_Du.jpg", "Khúc_Thừa_Dụ"),
            HistoricalFigure(19, "Khúc Hạo", "?", "917", null, "Tiết độ sứ", "Họ Khúc", "Tiếp tục sự nghiệp của cha...", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/13/Khuc_Hao.jpg/220px-Khuc_Hao.jpg", "Khúc_Hạo"),
            HistoricalFigure(20, "Khúc Thừa Mỹ", "?", "930", null, "Tiết độ sứ", "Họ Khúc", "Chống lại sự xâm lược của nhà Nam Hán...", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Khuc_Thua_My.jpg/220px-Khuc_Thua_My.jpg", "Khúc_Thừa_Mỹ")
        )),
        HistoryDynasty(10, "Ngô", "939", "965", listOf(
            HistoricalFigure(4, "Ngô Quyền", "898", "944", null, "Vua", "Ngô", "Chiến thắng Bạch Đằng 938...", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Ngo_Quyen.jpg/220px-Ngo_Quyen.jpg", "Ngô_Quyền"),
            HistoricalFigure(21, "Ngô Xương Văn", "?", "965", null, "Đồng vương", "Ngô", "Con trai Ngô Quyền, tham gia dẹp loạn...", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f4/Ngo_Xuong_Van.jpg/220px-Ngo_Xuong_Van.jpg", "Ngô_Xương_Văn"),
            HistoricalFigure(22, "Ngô Xương Xí", "?", "965", null, "Nam Tấn Vương", "Ngô", "Con trai Ngô Quyền, tham gia dẹp loạn...", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Ngo_Xuong_Xi.jpg/220px-Ngo_Xuong_Xi.jpg", "Ngô_Xương_Xí")
        )),
        HistoryDynasty(11, "Nhà Đinh", "968", "980", listOf(
            HistoricalFigure(5, "Đinh Tiên Hoàng", "924", "979", null, "Hoàng đế", "Đinh", "Thống nhất sau loạn 12 sứ quân...", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Dinh_Tien_Hoang.jpg/220px-Dinh_Tien_Hoang.jpg", "Đinh_Tiên_Hoàng"),
            HistoricalFigure(23, "Đinh Bộ Lĩnh", "924", "979", null, "Vạn Thắng Vương (trước khi lên ngôi)", "Đinh", "Tên thật của Đinh Tiên Hoàng...", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Dinh_Tien_Hoang.jpg/220px-Dinh_Tien_Hoang.jpg", "Đinh_Bộ_Lĩnh"),
            HistoricalFigure(24, "Đinh Liễn", "?", "979", null, "Nam Việt Vương", "Đinh", "Con trai trưởng của Đinh Tiên Hoàng...", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8c/Dinh_Lien.jpg/220px-Dinh_Lien.jpg", "Đinh_Liễn")
        )),
        HistoryDynasty(12, "Nhà Tiền Lê", "980", "1009", listOf(
            HistoricalFigure(25, "Lê Hoàn", "941", "1005", null, "Hoàng đế", "Tiền Lê", "Lập nhà Tiền Lê, đánh bại quân Tống...", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/Le_Hoan.jpg/220px-Le_Hoan.jpg", "Lê_Hoàn"),
            HistoricalFigure(26, "Lê Đại Hành", "941", "1005", null, "Hoàng đế (tên thụy)", "Tiền Lê", "Tên gọi khác của Lê Hoàn...", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/Le_Hoan.jpg/220px-Le_Hoan.jpg", "Lê_Đại_Hành"),
            HistoricalFigure(27, "Lê Trung Tông", "983", "1005", null, "Hoàng đế", "Tiền Lê", "Con trai Lê Hoàn, trị vì ngắn ngủi...", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e4/Le_Trung_Tong.jpg/220px-Le_Trung_Tong.jpg", "Lê_Trung_Tông"),
            HistoricalFigure(28, "Lê Long Đĩnh", "986", "1009", null, "Hoàng đế", "Tiền Lê", "Vị vua cuối cùng của nhà Tiền Lê...", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/Le_Long_Dinh.jpg/220px-Le_Long_Dinh.jpg", "Lê_Long_Đĩnh")
        )),
        HistoryDynasty(13, "Nhà Lý", "1009", "1225", listOf(
            HistoricalFigure(6, "Lý Thường Kiệt", "1019", "1105", null, "Tướng quốc", "Lý", "Tác giả Nam quốc sơn hà...", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/Ly_Thuong_Kiet.jpg/220px-Ly_Thuong_Kiet.jpg", "Lý_Thường_Kiệt"),
            HistoricalFigure(29, "Lý Công Uẩn", "974", "1028", null, "Lý Thái Tổ", "Lý", "Người sáng lập nhà Lý, dời đô về Thăng Long...", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Ly_Thai_To.jpg/220px-Ly_Thai_To.jpg", "Lý_Thái_Tổ"),
            HistoricalFigure(30, "Lý Thánh Tông", "1023", "1072", null, "Hoàng đế", "Lý", "Mở rộng lãnh thổ, chú trọng phát triển văn hóa...", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b3/Ly_Thanh_Tong.jpg/220px-Ly_Thanh_Tong.jpg", "Lý_Thánh_Tông"),
            HistoricalFigure(31, "Lý Nhân Tông", "1066", "1127", null, "Hoàng đế", "Lý", "Trị vì lâu nhất triều Lý, có nhiều đóng góp...", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Ly_Nhan_Tong.jpg/220px-Ly_Nhan_Tong.jpg", "Lý_Nhân_Tông")
        )),
        HistoryDynasty(14, "Nhà Trần", "1225", "1400", listOf(
            HistoricalFigure(7, "Trần Hưng Đạo", "1228", "1300", "Nguyễn Từ Quốc Mẫu", "Quốc công Tiết chế", "Trần", "Đánh bại 3 lần xâm lược của Nguyên Mông...", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Tran_Hung_Dao.jpg/220px-Tran_Hung_Dao.jpg", "Trần_Hưng_Đạo"),
            HistoricalFigure(32, "Trần Thái Tông", "1218", "1277", null, "Hoàng đế", "Trần", "Vị vua đầu tiên của nhà Trần...", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Tran_Thai_Tong.jpg/220px-Tran_Thai_Tong.jpg", "Trần_Thái_Tông"),
            HistoricalFigure(33, "Trần Thánh Tông", "1240", "1290", null, "Thái thượng hoàng", "Trần", "Cùng con lãnh đạo kháng chiến chống Nguyên Mông...", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Tran_Thanh_Tong.jpg/220px-Tran_Thanh_Tong.jpg", "Trần_Thánh_Tông"),
            HistoricalFigure(34, "Trần Nhân Tông", "1258", "1308", null, "Thái thượng hoàng", "Trần", "Lãnh đạo kháng chiến, sáng lập Thiền phái Trúc Lâm...", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b3/Tran_Nhan_Tong.jpg/220px-Tran_Nhan_Tong.jpg", "Trần_Nhân_Tông")
        )),
        HistoryDynasty(15, "Nhà Hồ", "1400", "1407", listOf(
            HistoricalFigure(35, "Hồ Quý Ly", "1336", "?", null, "Thái thượng hoàng", "Hồ", "Phế truất nhà Trần, lập nhà Hồ...", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Ho_Quy_Ly.jpg/220px-Ho_Quy_Ly.jpg", "Hồ_Quý_Ly"),
            HistoricalFigure(36, "Hồ Hán Thương", "?", "1407", null, "Hoàng đế", "Hồ", "Con trai Hồ Quý Ly, vị vua thứ hai nhà Hồ...", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Ho_Han_Thuong.jpg/220px-Ho_Han_Thuong.jpg", "Hồ_Hán_Thương")
        )),
        HistoryDynasty(16, "Thời kỳ thuộc Minh", "1407", "1427", emptyList(

        )),
        HistoryDynasty(17, "Nhà Hậu Lê (Lê sơ)", "1428", "1527", listOf(
            HistoricalFigure(8, "Nguyễn Trãi", "1380", "1442", null, "Danh nhân văn hóa", "Lê sơ", "Tác giả Bình Ngô đại cáo...", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/Nguyen_Trai.jpg/220px-Nguyen_Trai.jpg", "Nguyễn_Trãi"),
            HistoricalFigure(9, "Lê Lợi", "1385", "1433", null, "Lê Thái Tổ", "Lê sơ", "Khởi nghĩa Lam Sơn, lập nhà Hậu Lê...", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fc/Le_Loi.jpg/220px-Le_Loi.jpg", "Lê_Lợi"),
            HistoricalFigure(37, "Lê Thánh Tông", "1442", "1497", null, "Hoàng đế", "Lê sơ", "Thời kỳ thịnh trị nhất của nhà Hậu Lê...", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Le_Thanh_Tong.jpg/220px-Le_Thanh_Tong.jpg", "Lê_Thánh_Tông")
        )),
        HistoryDynasty(18, "Nhà Mạc", "1527", "1592", listOf(
            HistoricalFigure(38, "Mạc Đăng Dung", "1483", "1541", null, "Thái thượng hoàng", "Mạc", "Lập ra nhà Mạc...", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4a/Mac_Dang_Dung.jpg/220px-Mac_Dang_Dung.jpg", "Mạc_Đăng_Dung")
        )),
        HistoryDynasty(19, "Nhà Hậu Lê (Lê trung hưng)", "1533", "1789", listOf(
            HistoricalFigure(39, "Lê Thế Tông", "1530", "1599", null, "Hoàng đế", "Lê trung hưng", "Khôi phục nhà Hậu Lê...", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/Le_The_Tong.jpg/220px-Le_The_Tong.jpg", "Lê_Thế_Tông"),
            HistoricalFigure(40, "Trịnh Kiểm", "1503", "1570", null, "Chúa", "Lê trung hưng (chúa Trịnh)", "Người nắm quyền lực thực tế thời Lê trung hưng...", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Trinh_Kiem.jpg/220px-Trinh_Kiem.jpg", "Trịnh_Kiểm")
        )),
        HistoryDynasty(20, "Nhà Tây Sơn", "1778", "1802", listOf(
            HistoricalFigure(10, "Quang Trung", "1753", "1792", null, "Hoàng đế", "Tây Sơn", "Chiến thắng Đống Đa 1789...", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8c/Emperor_Quang_Trung.jpg/220px-Emperor_Quang_Trung.jpg", "Quang_Trung"),
            HistoricalFigure(41, "Nguyễn Nhạc", "1743", "1793", null, "Hoàng đế (Trung ương Hoàng đế)", "Tây Sơn", "Anh cả, người khởi xướng phong trào Tây Sơn...", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/Nguyen_Nhac.jpg/220px-Nguyen_Nhac.jpg", "Nguyễn_Nhạc"),
            HistoricalFigure(42, "Nguyễn Lữ", "?", "?", null, "Đông Định Vương", "Tây Sơn", "Em út trong ba anh em Tây Sơn...", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Nguyen_Lu.jpg/220px-Nguyen_Lu.jpg", "Nguyễn_Lữ")
        )),
        HistoryDynasty(21, "Nhà Nguyễn", "1802", "1945", listOf(
            HistoricalFigure(11, "Hồ Chí Minh", "1890", "1969", null, "Chủ tịch", "Nguyễn", "Lãnh tụ vĩ đại, khai sinh nước VNDCCH...", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/Ho_Chi_Minh_1946.jpg/220px-Ho_Chi_Minh_1946.jpg", "Hồ_Chí_Minh"),
            HistoricalFigure(12, "Võ Nguyên Giáp", "1911", "2013", null, "Đại tướng", "Nguyễn", "Chỉ huy Điện Biên Phủ, kháng chiến chống Mỹ...", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/V%C3%B5_Nguy%C3%AAn_Gi%C3%A1p_2008.jpg/220px-V%C3%B5_Nguy%C3%AAn_Gi%C3%A1p_2008.jpg", "Võ_Nguyên_Giáp"),
            HistoricalFigure(13, "Nguyễn Thái Học", "1902", "1930", null, null, "Nguyễn", "Lãnh đạo Việt Nam Quốc dân Đảng...", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Nguyen_Thai_Hoc.jpg/220px-Nguyen_Thai_Hoc.jpg", "Nguyễn_Thái_Học"),
            HistoricalFigure(43, "Gia Long", "1762", "1820", null, "Hoàng đế", "Nguyễn", "Người sáng lập nhà Nguyễn...", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Emperor_Gia_Long.jpg/220px-Emperor_Gia_Long.jpg", "Gia_Long"),
            HistoricalFigure(44, "Minh Mạng", "1791", "1841", null, "Hoàng đế", "Nguyễn", "Đẩy mạnh cải cách hành chính...", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/Emperor_Minh_Mang.jpg/220px-Emperor_Minh_Mang.jpg", "Minh_Mạng")
        ))
    )

    fun historicalEvents(): List<HistoricalEvent> = listOf(
        HistoricalEvent(
            id = 0,
            name = "Nền văn hóa Phùng Nguyên",
            birthYear = "Khoảng 4000 - 1500 TCN",
            description = "Nền văn hóa khảo cổ thời đại đồ đồng, phân bố chủ yếu ở vùng Bắc Bộ Việt Nam.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Văn_hóa_Phùng_Nguyên",
            level = 3
        ),
        HistoricalEvent(
            id = 1,
            name = "Nền văn hóa Đông Sơn",
            birthYear = "Khoảng 700 TCN - thế kỷ 3 CN",
            description = "Nền văn hóa kim khí phát triển rực rỡ, đặc trưng bởi trống đồng Đông Sơn.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Văn_hóa_Đông_Sơn",
            level = 2
        ),

        HistoricalEvent(
            id = 2,
            name = "Truyền thuyết Hùng Vương dựng nước Văn Lang",
            dynasty = "Hồng Bàng",
            birthYear = "Khoảng 2879 TCN",
            description = "Truyền thuyết về các vua Hùng, những người được coi là tổ tiên của dân tộc Việt.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Hùng_Vương",
            level = 1
        ),
        HistoricalEvent(
            id = 3,
            name = "Nhà nước Văn Lang",
            dynasty = "Hồng Bàng",
            birthYear = "Khoảng 2879 - 258 TCN",
            description = "Nhà nước sơ khai đầu tiên của người Việt, tồn tại qua 18 đời Hùng Vương (theo các nghiên cứu gần đây).",
            wikiPageId = "Văn_Lang",
            level = 1
        ),

        HistoricalEvent(
            id = 4,
            name = "Thục Phán An Dương Vương lập nước Âu Lạc",
            dynasty = "Âu Lạc",
            birthYear = "257 TCN",
            description = "Thục Phán đánh bại Hùng Vương, thống nhất các bộ lạc và lập ra nước Âu Lạc, dời đô về Cổ Loa.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Âu_Lạc",
            level = 1
        ),
        HistoricalEvent(
            id = 5,
            name = "Xây dựng thành Cổ Loa",
            dynasty = "Âu Lạc",
            birthYear = "Thế kỷ 3 TCN",
            description = "Kinh đô của nước Âu Lạc, một công trình phòng thủ kiên cố với kiến trúc xoắn ốc độc đáo.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Cổ_Loa",
            level = 2
        ),
        HistoricalEvent(
            id = 6,
            name = "Mỵ Châu - Trọng Thủy",
            dynasty = "Âu Lạc",
            birthYear = "Thế kỷ 3 TCN",
            description = "Câu chuyện bi kịch về tình yêu và sự phản bội dẫn đến sự sụp đổ của nước Âu Lạc.",
            wikiPageId = "Mỵ_Châu",
            level = 3
        ),
        HistoricalEvent(
            id = 7,
            name = "Triệu Đà xâm lược Âu Lạc",
            dynasty = "Âu Lạc",
            birthYear = "179 TCN",
            description = "Quân Nam Việt của Triệu Đà đánh bại An Dương Vương, sáp nhập Âu Lạc vào lãnh thổ Nam Việt.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Triệu_Đà",
            level = 1
        ),

        HistoricalEvent(
            id = 8,
            name = "Nhà Hán xâm lược Nam Việt",
            dynasty = "Nhà Triệu",
            birthYear = "111 TCN",
            description = "Quân Hán tiêu diệt nhà Triệu, thiết lập ách đô hộ trên lãnh thổ Âu Lạc cũ, chia thành các quận Giao Chỉ, Cửu Chân, Nhật Nam.",
            wikiPageId = "Bắc_thuộc_lần_thứ_nhất",
            level = 1
        ),
        HistoricalEvent(
            id = 9,
            name = "Khởi nghĩa Hai Bà Trưng",
            birthYear = "Năm 40",
            dynasty = "Hai Bà Trưng",
            description = "Cuộc khởi nghĩa của hai chị em Trưng Trắc và Trưng Nhị chống lại ách đô hộ của nhà Đông Hán, giành quyền tự chủ trong thời gian ngắn.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Hai_Bà_Trưng",
            level = 1
        ),
        HistoricalEvent(
            id = 10,
            name = "Mã Viện đàn áp khởi nghĩa Hai Bà Trưng",
            birthYear = "Năm 43",
            dynasty = "Hai Bà Trưng",
            description = "Quân Đông Hán dưới sự chỉ huy của Mã Viện đàn áp cuộc khởi nghĩa, Hai Bà Trưng hy sinh.",
            wikiPageId = "Mã_Viện",
            level = 2
        ),

        HistoricalEvent(
            id = 11,
            name = "Ách đô hộ của nhà Ngô",
            birthYear = "248",
            description = "Nhà Ngô chiếm Giao Châu sau khi nhà Hán sụp đổ.",
            wikiPageId = "Tam_Quốc",
            level = 3
        ),
        HistoricalEvent(
            id = 12,
            name = "Khởi nghĩa Bà Triệu",
            birthYear = "Năm 248",
            description = "Cuộc khởi nghĩa của Triệu Thị Trinh chống lại ách đô hộ của nhà Ngô.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Bà_Triệu",
            level = 2
        ),

        HistoricalEvent(
            id = 13,
            name = "Ách đô hộ của nhà Tùy và nhà Đường",
            birthYear = "602 - 905",
            description = "Giao Châu tiếp tục bị các triều đại Tùy và Đường đô hộ.",
            wikiPageId = "Bắc_thuộc",
            level = 3
        ),
        HistoricalEvent(
            id = 14,
            name = "Khúc Thừa Dụ giành quyền tự chủ",
            birthYear = "Năm 905",
            dynasty = "Họ Khúc",
            description = "Khúc Thừa Dụ nổi dậy, tự xưng Tiết độ sứ, đặt nền móng cho nền độc lập tự chủ.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Khúc_Thừa_Dụ",
            level = 2
        ),
        HistoricalEvent(
            id = 15,
            name = "Khúc Hạo xây dựng nền tự chủ",
            birthYear = "907",
            dynasty = "Họ Khúc",
            description = "Khúc Hạo tiếp tục sự nghiệp của cha, xây dựng bộ máy hành chính tự chủ.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Khúc_Hạo",
            level = 3
        ),
        HistoricalEvent(
            id = 16,
            name = "Khúc Thừa Mỹ chống quân Nam Hán",
            birthYear = "930",
            dynasty = "Họ Khúc",
            description = "Khúc Thừa Mỹ thất bại trong việc chống lại quân Nam Hán, nước ta rơi vào ách đô hộ trở lại.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Khúc_Thừa_Mỹ",
            level = 3
        ),

        HistoricalEvent(
            id = 17,
            name = "Ngô Quyền chiến thắng Bạch Đằng",
            birthYear = "Năm 938",
            dynasty = "Ngô",
            description = "Ngô Quyền đánh tan quân Nam Hán trên sông Bạch Đằng, kết thúc hoàn toàn thời kỳ Bắc thuộc.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Trận_Bạch_Đằng_(938)",
            level = 1
        ),
        HistoricalEvent(
            id = 18,
            name = "Loạn 12 sứ quân",
            birthYear = "944 - 968",
            description = "Thời kỳ hỗn loạn sau khi Ngô Quyền mất, đất nước bị chia cắt bởi 12 thế lực cát cứ.",
            wikiPageId = "Loạn_mười_hai_sứ_quân",
            level = 2
        ),
        HistoricalEvent(
            id = 19,
            name = "Đinh Bộ Lĩnh dẹp loạn 12 sứ quân",
            birthYear = "Năm 968",
            dynasty = "Đinh",
            description = "Đinh Bộ Lĩnh thống nhất đất nước, lên ngôi hoàng đế, đặt quốc hiệu là Đại Cồ Việt.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Đinh_Tiên_Hoàng",
            level = 1
        ),
        HistoricalEvent(
            id = 20,
            name = "Lê Hoàn lên ngôi, lập ra nhà Tiền Lê",
            birthYear = "Năm 980",
            dynasty = "Tiền Lê",
            description = "Lê Hoàn lên ngôi sau khi nhà Đinh suy yếu, lập ra nhà Tiền Lê.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Lê_Hoàn",
            level = 2
        ),
        HistoricalEvent(
            id = 21,
            name = "Chiến thắng quân Tống xâm lược lần thứ nhất",
            birthYear = "Năm 981",
            dynasty = "Tiền Lê",
            description = "Lê Hoàn lãnh đạo quân dân đánh bại quân Tống xâm lược.",
            wikiPageId = "Chiến_dịch_Tống-Việt_981",
            level = 1
        ),

        HistoricalEvent(
            id = 22,
            name = "Lý Công Uẩn dời đô về Thăng Long",
            birthYear = "Năm 1010",
            dynasty = "Lý",
            description = "Lý Công Uẩn lên ngôi, dời đô từ Hoa Lư về Thăng Long (Hà Nội ngày nay), mở ra một kỷ nguyên phát triển mới.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Lý_Thái_Tổ",
            level = 1
        ),
        HistoricalEvent(
            id = 23,
            name = "Chiến thắng quân Tống xâm lược lần thứ hai",
            birthYear = "1075 - 1077",
            dynasty = "Lý",
            description = "Lý Thường Kiệt lãnh đạo quân dân đánh bại quân Tống xâm lược, bảo vệ vững chắc lãnh thổ.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Chiến_dịch_Tống-Việt_1075–1077",
            level = 1
        ),
        HistoricalEvent(
            id = 24,
            name = "Nhà Trần thành lập",
            birthYear = "Năm 1225",
            dynasty = "Trần",
            description = "Nhà Trần được thành lập sau khi Lý Chiêu Hoàng nhường ngôi cho Trần Cảnh.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Nhà_Trần",
            level = 1
        ),
        HistoricalEvent(
            id = 25,
            name = "Ba lần kháng chiến chống quân Nguyên Mông",
            birthYear = "Thế kỷ 13",
            dynasty = "Trần",
            description = "Quân dân nhà Trần dưới sự lãnh đạo của các vua Trần và Trần Hưng Đạo đã ba lần đánh bại quân xâm lược Nguyên Mông.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Kháng_chiến_chống_Nguyên_Mông",
            level = 1
        ),
        HistoricalEvent(
            id = 26,
            name = "Hồ Quý Ly phế truất nhà Trần",
            birthYear = "Năm 1400",
            dynasty = "Hồ",
            description = "Hồ Quý Ly lật đổ nhà Trần, lập ra nhà Hồ.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Hồ_Quý_Ly",
            level = 2
        ),

        HistoricalEvent(
            id = 27,
            name = "Nhà Minh xâm lược Đại Việt",
            birthYear = "Năm 1407",
            dynasty = "Hồ",
            description = "Quân Minh xâm lược và đô hộ Đại Việt sau khi nhà Hồ thất bại.",
            wikiPageId = "Nhà_Minh_xâm_lược_Đại_Việt",
            level = 1
        ),
        HistoricalEvent(
            id = 28,
            name = "Khởi nghĩa Lam Sơn",
            birthYear = "1418 - 1428",
            dynasty = "Hậu Lê",
            description = "Cuộc khởi nghĩa do Lê Lợi lãnh đạo chống lại ách đô hộ của nhà Minh, giành lại độc lập cho đất nước.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Khởi_nghĩa_Lam_Sơn",
            level = 1
        ),
        HistoricalEvent(
            id = 29,
            name = "Lê Lợi lên ngôi, lập ra nhà Hậu Lê",
            birthYear = "Năm 1428",
            dynasty = "Hậu Lê",
            description = "Lê Lợi lên ngôi hoàng đế, khôi phục nền độc lập, đặt quốc hiệu là Đại Việt.",
            wikiPageId = "Lê_Thái_Tổ",
            level = 1
        ),
        HistoricalEvent(
            id = 30,
            name = "Ban hành luật Hồng Đức",
            birthYear = "Năm 1483",
            dynasty = "Hậu Lê",
            description = "Vua Lê Thánh Tông ban hành bộ luật Hồng Đức, một bộ luật tiến bộ và hoàn chỉnh của Việt Nam thời phong kiến.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Luật_Hồng_Đức",
            level = 2
        ),

        HistoricalEvent(
            id = 31,
            name = "Nhà Mạc thành lập",
            birthYear = "Năm 1527",
            dynasty = "Nhà Mạc",
            description = "Mạc Đăng Dung lật đổ nhà Hậu Lê, lập ra nhà Mạc, mở đầu thời kỳ Nam - Bắc triều.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Nhà_Mạc",
            level = 2
        ),
        HistoricalEvent(
            id = 32,
            name = "Chiến tranh Lê - Mạc",
            birthYear = "1533 - 1592",
            dynasty = "Nhà Mạc / Hậu Lê (Lê trung hưng)",
            description = "Cuộc chiến kéo dài giữa nhà Mạc ở phía Bắc và nhà Hậu Lê được các tướng lĩnh trung thành khôi phục ở phía Nam.",
            wikiPageId = "Chiến_tranh_Lê-Mạc",
            level = 2
        ),
        HistoricalEvent(
            id = 33,
            name = "Trịnh Kiểm nắm quyền, mở đầu thời kỳ chúa Trịnh",
            birthYear = "Thế kỷ 16",
            dynasty = "Hậu Lê (Lê trung hưng)",
            description = "Trịnh Kiểm thâu tóm quyền lực, các vua Lê chỉ còn là hình thức, mở đầu thời kỳ các chúa Trịnh cai trị Đàng Ngoài.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Trịnh_Kiểm",
            level = 2
        ),
        HistoricalEvent(
            id = 34,
            name = "Nguyễn Hoàng vào trấn thủ Thuận Hóa, mở đầu thời kỳ chúa Nguyễn",
            birthYear = "Năm 1558",
            dynasty = "Hậu Lê (Lê trung hưng)",
            description = "Nguyễn Hoàng được cử vào trấn thủ vùng Thuận Hóa (miền Trung), dần xây dựng thế lực riêng, mở đầu thời kỳ các chúa Nguyễn cai trị Đàng Trong.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Nguyễn_Hoàng",
            level = 2
        ),
        HistoricalEvent(
            id = 35,
            name = "Chiến tranh Trịnh - Nguyễn",
            birthYear = "1627 - 1672",
            dynasty = "Hậu Lê (Lê trung hưng)",
            description = "Loạt các cuộc chiến tranh kéo dài giữa chúa Trịnh ở Đàng Ngoài và chúa Nguyễn ở Đàng Trong, gây ra sự chia cắt đất nước.",
            wikiPageId = "Chiến_tranh_Trịnh-Nguyễn",
            level = 2
        ),

        HistoricalEvent(
            id = 36,
            name = "Khởi nghĩa Tây Sơn",
            birthYear = "Năm 1771",
            dynasty = "Tây Sơn",
            description = "Ba anh em Nguyễn Nhạc, Nguyễn Huệ, Nguyễn Lữ nổi dậy ở Tây Sơn chống lại các tập đoàn phong kiến suy yếu.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Phong_trào_Tây_Sơn",
            level = 1
        ),
        HistoricalEvent(
            id = 37,
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
            id = 38,
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
            id = 39,
            name = "Pháp bắt đầu xâm lược Việt Nam",
            birthYear = "Năm 1858",
            dynasty = "Nguyễn",
            description = "Quân Pháp tấn công Đà Nẵng, mở đầu quá trình xâm lược Việt Nam.",
            wikiPageId = "Chiến_tranh_Pháp-Đại_Nam",
            level = 1
        ),
        HistoricalEvent(
            id = 40,
            name = "Phong trào Cần Vương",
            birthYear = "1885 - 1896",
            dynasty = "Nguyễn",
            description = "Phong trào kháng chiến vũ trang của nhân dân Việt Nam dưới sự lãnh đạo của các sĩ phu yêu nước chống lại thực dân Pháp.",
            wikiPageId = "Phong_trào_Cần_Vương",
            level = 2
        ),

        HistoricalEvent(
            id = 41,
            name = "Hiệp ước Patenôtre, Việt Nam trở thành thuộc địa của Pháp",
            birthYear = "Năm 1884",
            description = "Hiệp ước đánh dấu sự hoàn thành quá trình xâm lược và đặt Việt Nam dưới sự bảo hộ của Pháp.",
            wikiPageId = "Hiệp_ước_Patenôtre",
            level = 1
        ),
        HistoricalEvent(
            id = 42,
            name = "Khởi nghĩa Yên Thế",
            birthYear = "1884 - 1913",
            description = "Cuộc khởi nghĩa nông dân lớn nhất và kéo dài nhất chống lại thực dân Pháp do Hoàng Hoa Thám lãnh đạo.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Khởi_nghĩa_Yên_Thế",
            level = 2
        ),

        HistoricalEvent(
            id = 43,
            name = "Đảng Cộng sản Việt Nam thành lập",
            birthYear = "Năm 1930",
            description = "Sự kiện đánh dấu bước ngoặt quan trọng trong lịch sử cách mạng Việt Nam, lãnh đạo cuộc đấu tranh giành độc lập.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Đảng_Cộng_sản_Việt_Nam",
            level = 1
        ),
        HistoricalEvent(
            id = 44,
            name = "Cách mạng tháng Tám",
            birthYear = "Năm 1945",
            description = "Cuộc cách mạng thành công lật đổ chế độ phong kiến và thực dân, thành lập nước Việt Nam Dân chủ Cộng hòa.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Cách_mạng_tháng_Tám",
            level = 1
        ),
        HistoricalEvent(
            id = 45,
            name = "Tuyên ngôn độc lập",
            birthYear = "Ngày 2 tháng 9 năm 1945",
            description = "Hồ Chí Minh đọc Tuyên ngôn độc lập, khai sinh nước Việt Nam Dân chủ Cộng hòa.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Tuyên_ngôn_độc_lập_Việt_Nam",
            level = 1
        ),

        HistoricalEvent(
            id = 46,
            name = "Chiến tranh Đông Dương lần thứ nhất (kháng chiến chống Pháp)",
            birthYear = "1946 - 1954",
            description = "Cuộc chiến tranh giữa Việt Nam Dân chủ Cộng hòa và thực dân Pháp nhằm giành độc lập.",
            wikiPageId = "Chiến_tranh_Đông_Dương",
            level = 1
        ),
        HistoricalEvent(
            id = 47,
            name = "Chiến thắng Điện Biên Phủ",
            birthYear = "Năm 1954",
            description = "Chiến thắng quyết định của Việt Nam trước quân Pháp, dẫn đến việc ký kết Hiệp định Genève.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Trận_Điện_Biên_Phủ",
            level = 1
        ),
        HistoricalEvent(
            id = 48,
            name = "Hiệp định Genève",
            birthYear = "Năm 1954",
            description = "Hiệp định chấm dứt chiến tranh Đông Dương, Việt Nam tạm thời bị chia cắt thành hai miền Nam và Bắc.",
            wikiPageId = "Hiệp_định_Genève_(1954)",
            level = 1
        ),
        HistoricalEvent(
            id = 49,
            name = "Chiến tranh Việt Nam (kháng chiến chống Mỹ)",
            birthYear = "1955 - 1975",
            description = "Cuộc chiến tranh giữa Việt Nam Dân chủ Cộng hòa và Việt Nam Cộng hòa (được Mỹ hậu thuẫn) nhằm thống nhất đất nước.",
            wikiPageId = "Chiến_tranh_Việt_Nam",
            level = 1
        ),
        HistoricalEvent(
            id = 50,
            name = "Sự kiện Vịnh Bắc Bộ",
            birthYear = "Năm 1964",
            description = "Sự kiện được Mỹ sử dụng làm cái cớ để leo thang chiến tranh ở Việt Nam.",
            wikiPageId = "Sự_kiện_Vịnh_Bắc_Bộ",
            level = 2
        ),
        HistoricalEvent(
            id = 51,
            name = "Chiến dịch Hồ Chí Minh",
            birthYear = "Năm 1975",
            description = "Chiến dịch quân sự cuối cùng của quân đội Việt Nam Dân chủ Cộng hòa, giải phóng hoàn toàn miền Nam, thống nhất đất nước.",
            image = "https://xuongducdongnd.com/wp-content/uploads/2023/04/thoi-ky-vua-hung.jpg",
            wikiPageId = "Chiến_dịch_Hồ_Chí_Minh",
            level = 1
        ),

        HistoricalEvent(
            id = 52,
            name = "Việt Nam thống nhất",
            birthYear = "Ngày 2 tháng 7 năm 1976",
            description = "Nước Việt Nam Dân chủ Cộng hòa và Cộng hòa miền Nam Việt Nam hợp nhất thành nước Cộng hòa Xã hội Chủ nghĩa Việt Nam.",
            wikiPageId = "Cộng_hòa_Xã_hội_chủ_nghĩa_Việt_Nam",
            level = 1
        ),
        HistoricalEvent(
            id = 53,
            name = "Chiến tranh biên giới Tây Nam",
            birthYear = "1978 - 1989",
            description = "Cuộc chiến tranh giữa Việt Nam và Khmer Đỏ ở biên giới Tây Nam.",
            wikiPageId = "Chiến_tranh_biên_giới_Tây_Nam",
            level = 2
        ),
        HistoricalEvent(
            id = 54,
            name = "Chiến tranh biên giới phía Bắc",
            birthYear = "Năm 1979",
            description = "Cuộc xung đột vũ trang giữa Việt Nam và Trung Quốc ở biên giới phía Bắc.",
            wikiPageId = "Chiến_tranh_biên_giới_Việt-Trung_1979",
            level = 2
        ),
        HistoricalEvent(
            id = 55,
            name = "Đổi mới",
            birthYear = "Năm 1986",
            description = "Chính sách cải cách kinh tế và xã hội toàn diện do Đảng Cộng sản Việt Nam khởi xướng.",
            wikiPageId = "Đổi_Mới",
            level = 1
        ),
        HistoricalEvent(
            id = 56,
            name = "Việt Nam gia nhập ASEAN",
            birthYear = "Năm 1995",
            description = "Việt Nam trở thành thành viên của Hiệp hội các quốc gia Đông Nam Á (ASEAN).",
            wikiPageId = "Việt_Nam_và_ASEAN",
            level = 2
        ),
        HistoricalEvent(
            id = 57,
            name = "Việt Nam gia nhập WTO",
            birthYear = "Năm 2007",
            description = "Việt Nam trở thành thành viên của Tổ chức Thương mại Thế giới (WTO).",
            wikiPageId = "Việt_Nam_và_WTO",
            level = 2
        )
    )
}