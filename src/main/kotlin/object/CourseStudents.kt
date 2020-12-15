package objects

import `object`.Students
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table

object CourseStudents : Table("course_in_students") {
    val courseS = reference("courseS", Courses)
    val student = reference("student", Students)
    override val primaryKey =  PrimaryKey(courseS, student, name = "PK_COURSE_STUDENT")
}