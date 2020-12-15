package objects

import `object`.Grades
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Types : IntIdTable() {
    val name = varchar("name", 50)
    val shortName = varchar("shortName", 50)

}