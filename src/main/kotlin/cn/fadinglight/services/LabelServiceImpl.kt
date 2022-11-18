package cn.fadinglight.services

import cn.fadinglight.vo.vo
import cn.fadinglight.mapers.Labels
import cn.fadinglight.models.Label
import cn.fadinglight.models.LabelType
import cn.fadinglight.vo.LabelGroup
import cn.fadinglight.vo.LabelPost
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class LabelServiceImpl : LabelService {
    private fun resultRowToLabel(row: ResultRow) = Label(
        id = row[Labels.id].value,
        type = LabelType.valueOf(row[Labels.type]),
        name = row[Labels.name],
        count = row[Labels.count],
        relativeId = row[Labels.relativeId]
    )

    override suspend fun getLabels(): LabelGroup {
        val labelGroups = transaction {
            Labels
                .selectAll()
                .map(::resultRowToLabel)
                .groupBy { it.type }
        }
        val consumeLabels = labelGroups[LabelType.CLASS]
            ?.map {
                it.vo().apply {
                    this.labels = labelGroups[LabelType.LABEL]
                        ?.filter { it2 -> it2.relativeId == it.id }
                        ?.map(Label::vo)
                        ?: emptyList()
                }
            } ?: emptyList()
        val incomeLabels = labelGroups[LabelType.CLASS]
            ?.map {
                it.vo().apply {
                    this.labels = labelGroups[LabelType.INCOME_CLASS]
                        ?.filter { it2 -> it2.relativeId == it.id }
                        ?.map(Label::vo)
                        ?: emptyList()
                }
            } ?: emptyList()
        return LabelGroup(income = incomeLabels, consume = consumeLabels)
    }

    override suspend fun addLabel(label: Label): Int = transaction {
        Labels
            .insertAndGetId {
                it[type] = label.type.name
                it[name] = label.name
                it[count] = 0
                it[relativeId] = label.relativeId
            }.value
    }

    override suspend fun deleteLabel(labelId: Int): Int {
        TODO("Not yet implemented")
    }

    override suspend fun addCount(labelId: Int): Int = transaction {
        Labels.update({ Labels.id eq labelId }) {
            with(SqlExpressionBuilder) {
                it[count] = count + 1
            }
        }
    }
}