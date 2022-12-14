package cn.fadinglight.vo

import cn.fadinglight.models.Label
import cn.fadinglight.models.LabelType
import kotlinx.serialization.Serializable


@Serializable
data class LabelPost(
    val type: Int,
    val name: String,
    val relativeId: Int?,
)

fun LabelPost.label() = Label(
    id = null,
    type = LabelType.of(type),
    name = name,
    count = 0,
    relativeId = relativeId
)

@Serializable
data class LabelVO(
    val name: String,
    var labels: List<String>?,
)

@Serializable
data class LabelGroup(
    val consume: List<LabelVO>,
    val income: List<LabelVO>,
)


fun Label.vo() = LabelVO(
    name = name,
    labels = null,
)
