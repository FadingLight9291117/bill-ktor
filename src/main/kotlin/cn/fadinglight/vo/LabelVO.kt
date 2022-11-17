package cn.fadinglight.vo

import cn.fadinglight.models.Label
import cn.fadinglight.models.LabelType
import kotlinx.serialization.Serializable

@Serializable
data class LabelVO(
    val name: String,
    val count: Int,
    var labels: List<LabelVO>?,
)

@Serializable
data class LabelGroup(
    val consume: List<LabelVO>,
    val income: List<LabelVO>,
)


fun LabelVO.label(type: LabelType) = Label(
    id = null,
    type = type,
    name = name,
    count = 0,
    relativedId = null
)

fun Label.vo() = LabelVO(
    name = name,
    count = count,
    labels = emptyList(),
)
