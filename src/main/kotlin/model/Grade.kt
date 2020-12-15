package model

import `object`.Grades
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import repo.Item
import repo.ItemClass
import repo.ItemTable


/*class Grade (
    id: EntityID<Int>
) : IntEntity(id) {
    companion object : IntEntityClass<Grade>(Grades)
    var count by Grades.count
    var date by Grades.date
    var student by Student referencedOn Grades.student
    var task by Task referencedOn Grades.task
}*/

@Serializable
class Grade (
    val count :Int,

    override var id: Int = -1
) : Item

class GradeTable : ItemTable<Grade>() {

    val count = integer ("count")

    override fun fill(builder: UpdateBuilder<Int>, item: Grade) {
        builder[count] = item.count
    }

    override fun readResult(result: ResultRow) =
        Grade(
            result[count],
            result[id].value
        )
}

val gradeTable = GradeTable()

class GradeTableClass(id: EntityID<Int>) : ItemClass<Grade>(id) {
    companion object : IntEntityClass<GradeTableClass>(Grades)
    var count by Grades.count
    var date by Grades.date
    var student by Student referencedOn Grades.student
    var task by Task referencedOn Grades.task

    override val obj: Grade
        get() = Grade(count,id.value)

    override fun fill(item: Grade) {
        count = item.count
    }
}