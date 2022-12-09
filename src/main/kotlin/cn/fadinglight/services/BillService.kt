package cn.fadinglight.services

import cn.fadinglight.models.Bill

interface BillService {
    suspend fun getManyBills(year: String): List<Bill>
    suspend fun getManyBills(year: String, month: String): List<Bill>
    suspend fun getManyBills(year: String, month: String, day: String): List<Bill>
    suspend fun getManyBills(): List<Bill>
    suspend fun getOneBill(billId: Int): Bill?
    suspend fun addManyBills(bills: List<Bill>): Int
    suspend fun addOneBill(bill: Bill): Int
    suspend fun updateManyBills(bills: List<Bill>): Int
    suspend fun updateOneBill(bill: Bill): Int
    suspend fun deleteManyBills(bills: List<Bill>): Int
    suspend fun deleteOneBill(billId: Int): Int

}

