import `object`.Grades
import `object`.Students
import model.Course
import objects.*
import org.jetbrains.annotations.TestOnly
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Test


//class Main {
  //  @Test
    fun main() {       //ne nado
        Database.connect(
            "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            driver = "org.h2.Driver"
        )

        transaction {
            SchemaUtils.create(Courses, Ratings, Students, Tasks, Tutors,Types, Grades, CourseStudents, CourseTutors)

            SchemaUtils.drop(Courses, Ratings, Students, Tasks, Tutors,Types, Grades, CourseStudents, CourseTutors)
        }

    }
//}
