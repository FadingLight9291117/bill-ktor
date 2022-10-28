package cn.fadinglight.services

import cn.fadinglight.dao.LabelDao
import cn.fadinglight.models.Label
import cn.fadinglight.models.LabelType

interface LabelService {
    suspend fun getLabels(): List<LabelDao>
    suspend fun addLabel(labelType: LabelType, label: Label): Int
    suspend fun deleteLabel(labelId: Int): Int
    suspend fun addCount(labelId: Int): Int
}