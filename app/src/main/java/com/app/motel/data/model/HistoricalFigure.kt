package com.app.motel.data.model

data class HistoricalFigure(
    override val id: Int? = null,
    val name: String? = null,
    val birthYear: String? = null,      // sinh: "1228"
    val deathDate: String? = null,      // mất: "3-10-1300"
    val spouse: String? = null,         // vợ chôồng: "Nguyễn Từ Quốc Mẫu"
    val title: String? = null,          // tước hiệu: "Hưng Đạo Đại Vương"
    val dynasty: String? = null,        // triều đại: "Nhà Trần"
    val description: String? = null,
    val image: String? = null,
    val wikiPageId: String? = null,
) : RealTimeId{
}