package objects

import `object`.Grades
import objects.Tutors.autoIncrement
import objects.Tutors.entityId
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Tutors : IntIdTable() {
    val name = varchar("name", length = 50)
    val post = varchar("post", length = 50)

}