package objects

import objects.Tasks.course
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table

object CourseTutors : Table("course_in_tutors") {
    val courseT = reference("courseT", Courses)
    val tutor = reference("tutor", Tutors)
   override val primaryKey = PrimaryKey(courseT, tutor, name = "PK_COURSE_TUTOR")
}