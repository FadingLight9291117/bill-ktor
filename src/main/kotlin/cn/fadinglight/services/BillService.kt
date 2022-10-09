package cn.fadinglight.services

import cn.fadinglight.models.Bill

interface BillService {
    fun getMonthBills(year: String, month: String): List<Bill>

}


class BillServiceImpl : BillService {
    override fun getMonthBills(year: String, month: String): List<Bill> {
        TODO("Not yet implemented")
    }
}