package cn.fadinglight.vo

import cn.fadinglight.models.Label
import cn.fadinglight.models.LabelType
import kotlinx.serialization.Serializable


@Serializable
data class LabelPost(
    val type: LabelType,
    val name: String,
    val relativeId: Int?,
)

fun LabelPost.label() = Label(
    id = null,
    type = type,
    name = name,
    count = 0,
    relativeId = relativeId
)

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


fun Label.vo() = LabelVO(
    name = name,
    count = count,
    labels = emptyList(),
)
