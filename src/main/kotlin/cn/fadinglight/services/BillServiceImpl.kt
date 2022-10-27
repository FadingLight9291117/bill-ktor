package cn.fadinglight.services

import cn.fadinglight.mapers.Bills
import cn.fadinglight.models.Bill
import cn.fadinglight.models.BillType
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.transactions.transaction


class BillServiceImpl : BillService {
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
        Bills.select(Bills.date like "%${year}-${month}%").map(::resultRowToBill)
    }


    override suspend fun getManyBills(year: String, month: String, day: String): List<Bill> = transaction {
        Bills.select(Bills.date eq "${year}-${month}-${day}").map(::resultRowToBill)
    }

    override suspend fun getManyBills(): List<Bill> = transaction {
        Bills.selectAll().map(::resultRowToBill)
    }

    override suspend fun getOneBill(billId: Int): Bill = transaction {
        Bills.select(Bills.id eq billId).single().let(::resultRowToBill)
    }


    override suspend fun addOneBill(bill: Bill): Int = transaction {
        Bills.insertAndGetId {
            it[type] = bill.type.name
            it[date] = bill.date
            it[cls] = bill.cls
            it[label] = bill.label
            it[money] = bill.money
            it[options] = bill.options
        }.value
    }

    override suspend fun addManyBills(bills: List<Bill>): Int = transaction {
        Bills.batchInsert(bills) {
            this[Bills.type] = it.type.name
            this[Bills.date] = it.date
            this[Bills.cls] = it.cls
            this[Bills.label] = it.label
            this[Bills.money] = it.money
            this[Bills.options] = it.options
        }.count()
    }

    override suspend fun updateManyBills(bills: List<Bill>): Int {
        TODO("Not yet implemented")
    }

    override suspend fun updateOneBill(bill: Bill) = transaction {
        Bills.update({ Bills.id eq bill.id }) {
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
        Bills.deleteWhere { Bills.id eq billId }
    }


}
