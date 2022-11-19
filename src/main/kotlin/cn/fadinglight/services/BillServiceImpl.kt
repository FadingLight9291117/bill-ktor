package cn.fadinglight.services

import cn.fadinglight.mapers.Bills
import cn.fadinglight.mapers.Labels
import cn.fadinglight.models.Bill
import cn.fadinglight.models.BillType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.jetbrains.exposed.sql.transactions.transaction


class BillServiceImpl : BillService {
    private val labelService = LabelServiceImpl()
    private fun formatSingletonNumber(n: String) = if (n.length == 1) {
        "0$n"
    } else {
        n
    }

    private fun resultRowToBill(row: ResultRow) = Bill(
        id = row[Bills.id].value,
        type = BillType.valueOf(row[Bills.type]),
        date = row[Bills.date],
        money = row[Bills.money],
        cls = row[Bills.cls],
        label = row[Bills.label],
        options = row[Bills.options]
    )

    override suspend fun getManyBills(year: String, month: String): List<Bill> = transaction {
        Bills
            .select(Bills.date like "%${year}-${formatSingletonNumber(month)}%")
            .map(::resultRowToBill)
    }


    override suspend fun getManyBills(year: String, month: String, day: String): List<Bill> = transaction {
        Bills
            .select(Bills.date eq "${year}-${formatSingletonNumber(month)}-${formatSingletonNumber(day)}")
            .map(::resultRowToBill)
    }

    override suspend fun getManyBills(): List<Bill> = transaction {
        Bills
            .selectAll()
            .map(::resultRowToBill)
    }

    override suspend fun getOneBill(billId: Int): Bill? = transaction {
        Bills
            .select(Bills.id eq billId)
            .map(::resultRowToBill)
            .singleOrNull()
    }

    override suspend fun addOneBill(bill: Bill): Int = transaction {
        Bills
            .insertAndGetId {
                it[type] = bill.type.name
                it[date] = bill.date
                it[cls] = bill.cls
                it[label] = bill.label
                it[money] = bill.money
                it[options] = bill.options
            }

        Labels
            .update({ Labels.name inList listOf(bill.cls, bill.label) }) {
                with(SqlExpressionBuilder) {
                    it[count] = count + 1
                }
            }
    }

    override suspend fun addManyBills(bills: List<Bill>): Int = transaction {

        val newBills = Bills
            .batchInsert(bills) {
                this[Bills.type] = it.type.name
                this[Bills.date] = it.date
                this[Bills.cls] = it.cls
                this[Bills.label] = it.label
                this[Bills.money] = it.money
                this[Bills.options] = it.options
            }
            .map(::resultRowToBill)
        newBills.forEach {
            val classId = Labels
                .select(Labels.name eq it.cls)
                .map { it2 -> it2[Labels.id].value }
                .single()
            val labelId = Labels
                .select(Labels.name eq it.label)
                .map { it2 -> it2[Labels.id].value }
                .single()
            Labels.update({ (Labels.id eq labelId) or (Labels.id eq classId) }) {
                with(SqlExpressionBuilder) {
                    it[count] = count + 1
                }
            }
        }
        newBills.count()
    }

    override suspend fun updateManyBills(bills: List<Bill>): Int {
        TODO("Not yet implemented")
    }

    override suspend fun updateOneBill(bill: Bill) = transaction {
        Bills
            .update({ Bills.id eq bill.id }) {
                it[type] = bill.type.name
                it[date] = bill.date
                it[money] = bill.money
                it[cls] = bill.cls
                it[label] = bill.label
                it[options] = bill.options
            }
    }

    override suspend fun deleteManyBills(bills: List<Bill>): Int {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOneBill(billId: Int): Int = transaction {
        Bills
            .deleteWhere { Bills.id eq billId }
    }
}
