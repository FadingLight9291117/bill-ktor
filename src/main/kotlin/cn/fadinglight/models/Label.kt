package cn.fadinglight.models


enum class LabelType {
    CLASS,
    LABEL,
    INCOME_CLASS;

    companion object {
        fun of(n: Int) = when (n) {
            0 -> LabelType.CLASS
            1 -> LabelType.LABEL
            2 -> LabelType.INCOME_CLASS
            else -> throw IllegalArgumentException("error code $n")
        }
    }

}

data class Label(
    var id: Int?,
    val type: LabelType,
    val name: String,
    var count: Int,
    val relativeId: Int?,
)