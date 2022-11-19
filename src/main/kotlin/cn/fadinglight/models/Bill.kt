package cn.fadinglight.models

import kotlinx.serialization.Serializable


enum class BillType {
    CONSUME,
    INCOME;

    companion object {
        fun of(n: Int): BillType = when (n) {
            0 -> CONSUME
            1 -> INCOME
            else -> throw IllegalArgumentException("error code $n")
        }
    }
}

@Serializable
data class Bill(
    var id: Int?,
    val type: BillType,
    val date: String,
    val money: Float,
    val cls: String,
    val label: String,
    val options: String = "",
)