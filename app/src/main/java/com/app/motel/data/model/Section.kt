package com.app.motel.data.model

data class Section(
    override val id: String? = null,
    val level: Int? = null,       // 2 = ==, 3 = ===, 4 = ====
    val title: String? = null,
    val content: String? = null
): RealTimeId{
    val isLevel1: Boolean get() = (level ?: 4) <= 2
    val isLevel2: Boolean get() = (level ?: 4) == 3
    val isLevel3: Boolean get() = (level ?: 4) >= 4

    companion object{
        fun parseSections(text: String): List<Section> {
            val regex = Regex("""(={2,4})([^=]+?)\1""")

            val result = mutableListOf<Section>()
            val matches = regex.findAll(text).toList()

            for ((index, match) in matches.withIndex()) {
                val level = match.groupValues[1].length
                val title = match.groupValues[2].trim()

                val start = match.range.last + 1
                val end = if (index + 1 < matches.size) matches[index + 1].range.first else text.length
                val content = text.substring(start, end).trim()

                result.add(Section(null, level, title, content))
            }

            return result
        }
    }
}


