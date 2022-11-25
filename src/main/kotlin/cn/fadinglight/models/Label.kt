package cn.fadinglight.models


enum class LabelType {
    CONSUME_CLASS,
    CONSUME_LABEL,
    INCOME_CLASS;

    companion object {
        fun of(n: Int) = when (n) {
            0 -> CONSUME_CLASS
            1 -> CONSUME_LABEL
            2 -> INCOME_CLASS
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