package objects


import `object`.Grades
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Courses : IntIdTable() {
    var name = varchar("name", length = 50)

}