package cn.fadinglight.mapers

import org.jetbrains.exposed.dao.id.IntIdTable

object Bills : IntIdTable() {
    val type = integer("type")
    val date = varchar("date", 15)
    val money = integer("money")
    val cls = varchar("cls", 31)
    val label = varchar("label", 31)
    val options = varchar("options", 255)
}

object Labels : IntIdTable() {
    val name = varchar("name", 15)
    val type = integer("type")
    val relativeId = integer("relative_id")
    val nums = integer("nums")
}
