package cn.fadinglight.dao

import cn.fadinglight.models.Label
import cn.fadinglight.models.LabelType

data class LabelDao(
    val name: String,
    val count: Int,
    var labels: List<LabelDao>?,
)


fun LabelDao.label(type: LabelType) = Label(
    id = null,
    type = type,
    name = name,
    count = 0,
    relativedId = null
)

fun Label.dao() = LabelDao(
    name = name,
    count = count,
    labels = emptyList(),
)
