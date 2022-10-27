package cn.fadinglight.services

import cn.fadinglight.dao.LabelDao
import cn.fadinglight.models.Label
import cn.fadinglight.models.LabelType

interface LabelServe {
    suspend fun getLabels(): List<LabelDao>
    suspend fun addLabel(labelType: LabelType, label: Label): Boolean
    suspend fun deleteLabel(labelId: Int): Boolean
    suspend fun addCount(labelId: Int): Boolean
}