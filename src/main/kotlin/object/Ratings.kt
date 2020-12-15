package objects

import `object`.Grades
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Ratings : IntIdTable() {
    val value = Grades.integer("value")


}