package model

import `object`.Students
import kotlinx.serialization.Serializable
import objects.CourseStudents
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import repo.Item
import repo.ItemTable

/*class Student (
    id: EntityID<Int>
) : Person(id) {
    companion object : IntEntityClass<Student>(Students)
    var name by Students.name
    var group by Students.group

    var course by Course via CourseStudents
}*/

@Serializable
class Student(
    val name :String,
    val group: String,
    override var id: Int = -1
) : Item

class StudentTable : ItemTable<Student>() {
    val name = varchar("name", 50)
    val group = varchar("group", 50)

    override fun fill(builder: UpdateBuilder<Int>, item: Student) {
        builder[name] = item.name
        builder[group] = item.group
    }

    override fun readResult(result: ResultRow) =
        Student(
            result[name],
            result[group],
            result[id].value
        )
}

val studentTable = StudentTable()

class StudentTableClass (id: EntityID<Int>) : Person(id) {
    companion object : IntEntityClass<StudentTableClass>(Students)
    var name by Students.name
    var group by Students.group //почему не используется

    //var course by Course via CourseStudents //надо как-то заменить?
    /*override val obj: Student
        get() = Student(name, group,id.value)

    override fun fill(item: Student) {
        name = item.name
        group = item.group
    }*/
}


