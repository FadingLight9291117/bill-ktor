package cn.fadinglight.models

enum class BillType {
    Consume,
    Income,
}

data class Bill(
    var id: Int?,
    var type: BillType,
    val money: Int,
    val cls: String,
    val label: String,
    val options: String?,
)
