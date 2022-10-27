package cn.fadinglight.mapers

import org.jetbrains.exposed.dao.id.IntIdTable

object Bills : IntIdTable() {
    val type = varchar("type", 15)
    val date = varchar("date", 15)
    val money = integer("money")
    val cls = varchar("cls", 31)
    val label = varchar("label", 31)
    val options = varchar("options", 255).nullable()
}

object Labels : IntIdTable() {
    val type = varchar("type", 15)
    val name = varchar("name", 15)
    val relativeId = integer("relative_id")
    val nums = integer("nums")
}
