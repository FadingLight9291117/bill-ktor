package cn.fadinglight.services

import cn.fadinglight.models.Label
import cn.fadinglight.models.LabelType
import cn.fadinglight.vo.LabelGroup

interface LabelService {
    suspend fun getLabels(): LabelGroup
    suspend fun addLabel(labelType: LabelType, label: Label): Int
    suspend fun deleteLabel(labelId: Int): Int
    suspend fun addCount(labelId: Int): Int
}