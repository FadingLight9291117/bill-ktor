package cn.fadinglight.vo

import cn.fadinglight.models.Bill
import cn.fadinglight.models.BillType
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class BillVO(
    val id: Int? = null,
    val type: Int,
    val date: String,
    val money: Float,
    val cls: String,
    val label: String,
    val options: String = "",
)

fun BillVO.bill() = Bill(
    id = id,
    type = BillType.of(type),
    date = date,
    money = money,
    cls = cls,
    label = label,
    options = options,
)

fun Bill.vo() = BillVO(
    id = id,
    type = type.ordinal,
    date = date,
    money = money,
    cls = cls,
    label = label,
    options = options,
)