package model

import kotlinx.serialization.Serializable
import objects.CourseStudents
import objects.CourseTutors
import objects.Courses
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import repo.Item
import repo.ItemClass
import repo.ItemTable
import repo.Repo
import java.time.LocalDate

/*class Course(
    id: EntityID<Int>
) : IntEntity(id) {
    companion object : IntEntityClass<Course>(Courses)
    var name by Courses.name

    var students by Student via CourseStudents
    var tutors by Tutor via CourseTutors

    fun addTutorByName(name: String) {
        val tutor = Tutor.new {
            this.name = name
        }
        this.tutors.plus(tutor)
    }

      fun addStudentByName(name: String) {
        val student = Student.new {
            this.name = name
        }
        this.students.plus(student)
    }

    fun setGrade(task: Task, student: Student, value: Int, date: LocalDate = LocalDate.now()) {
        if (value !in 0..task.maxValue) return
        Grade.new {
            this.task = task
            this.date = date
            this.student = student
            this.count = count
        }
    }

    fun setTask(name: String,
                type: Type,
                description: String,
                maxValue: Int,
                deadline: LocalDate = LocalDate.now())
    {
        Task.new  {
            this.name = name
            this.maxValue = maxValue
            this.deadline = deadline
            this.description = description
            this.type = type
        }
    }

}*/
@Serializable
class Course(
    val name: String,
    override var id: Int = -1
) : Item

class CourseTable  : ItemTable<Course>() {
    val name = varchar("name", 50)

    override fun fill(builder: UpdateBuilder<Int>, item: Course) {
        builder[name] = item.name
    }
    override fun readResult(result: ResultRow) =
        Course(
            result[name],
            result[id].value
        )
}

val courseTable = CourseTable()

class CourseTableClass (id: EntityID<Int>) : ItemClass<Course>(id) {
    companion object : IntEntityClass<CourseTableClass>(courseTable)
    var name by Courses.name

    //var students by Student via CourseStudents
    //var tutors by Tutor via CourseTutors

    override val obj: Course
        get() = Course(name, id.value)

    override fun fill(item: Course) {
        name = item.name
    }

    fun addTutorByName(repo: Repo<Tutor>,name: String, post: String) {
       /* val tutor = Tutor.new {
            this.name = name
        }
        this.tutors.plus(tutor)*/
        repo.create(Tutor(name, post))
    }

    fun addStudentByName(repo: Repo<Student>,name: String,group: String) {
       /* val student = Student.new {
            this.name = name
        }
        this.students.plus(student)*/
        repo.create(Student(name, group))
    }

    fun setGrade(repo: Repo<Grade>, task: Task, student: Student, value: Int, date: LocalDate = LocalDate.now()) {
        if (value !in 0..task.maxValue) return
        /*Grade.new {
            this.task = task
            this.date = date
            this.student = student
            this.count = count
        }*/
        repo.create(Grade(value))
    }

    fun setTask(repo: Repo<Task>,
                name: String,
                shortName: String,
                type: Type,
                description: String,
                maxValue: Int,
                deadline: LocalDate = LocalDate.now())
    {
        /*Task.new  {
            this.name = name
            this.maxValue = maxValue
            this.deadline = deadline
            this.description = description
            this.type = type
        }*/
        repo.create(Task(name,shortName,description,maxValue))
    }

}