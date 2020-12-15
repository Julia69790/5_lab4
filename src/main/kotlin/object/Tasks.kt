package objects

import `object`.Grades
import objects.Tasks.autoIncrement
import objects.Tasks.entityId
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.`java-time`.date

object Tasks : IntIdTable() {
    var name = varchar("name", length = 50)
    val shortName = varchar("shortName", length = 50)
    var description = varchar("description", length = 50)
    var maxValue = integer("maxValue")
    var deadline = date("deadline")

    var type = reference("task", Types)
    var course = reference("course", Courses)


}