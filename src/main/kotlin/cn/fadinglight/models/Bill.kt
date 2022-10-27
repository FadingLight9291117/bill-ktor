package cn.fadinglight.models

import kotlinx.serialization.Serializable


enum class BillType {
    Consume,
    Income,
}
@Serializable
data class Bill(
    var id: Int?,
    val type: BillType,
    val date: String,
    val money: Int,
    val cls: String,
    val label: String,
    val options: String?,
)