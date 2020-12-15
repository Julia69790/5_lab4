package `object`

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Students : IntIdTable() {
    val name = varchar("name", length = 50)
    val group = varchar("group", length = 50)


}