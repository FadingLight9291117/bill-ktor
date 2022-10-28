package cn.fadinglight.services

import cn.fadinglight.dao.LabelDao
import cn.fadinglight.dao.dao
import cn.fadinglight.mapers.Labels
import cn.fadinglight.models.Label
import cn.fadinglight.models.LabelType
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class LabelServiceImpl : LabelService {
    private fun resultRowToLabel(row: ResultRow) = Label(
        id = row[Labels.id].value,
        type = LabelType.valueOf(row[Labels.type]),
        name = row[Labels.name],
        count = row[Labels.count],
        relativedId = row[Labels.relativeId]
    )

    override suspend fun getLabels(): List<LabelDao> {
        val labelGroups = transaction {
            Labels.selectAll().map(::resultRowToLabel).groupBy { it.type }
        }
        return labelGroups[LabelType.CLASS]?.map {
            it.dao().apply {
                labels = labelGroups[LabelType.LABEL]
                    ?.filter { it2 -> it2.relativedId == it.id }
                    ?.map(Label::dao)
                    ?: emptyList()
            }
        } ?: emptyList()
    }

    override suspend fun addLabel(labelType: LabelType, label: Label): Int = transaction {
        Labels.insertAndGetId {
            it[type] = label.type.name
            it[name] = label.name
            it[count] = label.count
            it[relativeId] = label.relativedId
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