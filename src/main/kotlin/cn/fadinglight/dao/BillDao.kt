package cn.fadinglight.dao

import cn.fadinglight.models.Bill
import cn.fadinglight.models.BillType
import kotlinx.serialization.Serializable

@Serializable
data class BillDao(
    val id: Int? = null,
    val type: String,
    val date: String,
    val money: Int,
    val cls: String,
    val label: String,
    val options: String? = null,
)

fun BillDao.bill() = Bill(
    id = id,
    type = BillType.valueOf(type),
    date = date,
    money = money,
    cls = cls,
    label = label,
    options = options ?: "",
)

fun Bill.dao() = BillDao(
    id = id,
    type = type.name,
    date = date,
    money = money,
    cls = cls,
    label = label,
    options = options,
)