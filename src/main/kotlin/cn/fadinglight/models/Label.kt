package cn.fadinglight.models


enum class LabelType {
    CLASS,
    LABEL,
}

data class Label(
    var id: Int?,
    val type: LabelType,
    val name: String,
    var count: Int,
    val relativedId: Int?,
)