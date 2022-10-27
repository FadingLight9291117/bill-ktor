package cn.fadinglight.dao

data class LabelDao(
    val name: String,
    val count: Int,
    val labels: List<LabelDao>?
)