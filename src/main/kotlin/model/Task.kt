package model


import kotlinx.serialization.Serializable
import objects.Tasks
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import repo.Item
import repo.ItemClass
import repo.ItemTable
import java.time.LocalDate
import java.util.*

/*class Task(
    id: EntityID<Int>
) : IntEntity(id) {
    companion object : IntEntityClass<Task>(Tasks)
    var name by Tasks.name
    var shortName by Tasks.shortName
    var deadline by Tasks.deadline
    var description by Tasks.description
    var maxValue by Tasks.maxValue

    var type by Type referencedOn Tasks.type
    var course by Course referencedOn Tasks.course
}*/

@Serializable
class Task(
    val name:String,
    val shortName: String,
    //val deadline:  LocalDate = LocalDate.now(),
    val description: String,
    val maxValue: Int,
    override var id: Int = -1
    ): Item

class TaskTable: ItemTable<Task>() {
    val name = varchar("name", 50)
    val shortName = varchar("shortName", 10)
    val description = varchar("description", 100)
    val maxValue = integer ("maxValue")

    override fun fill(builder: UpdateBuilder<Int>, item: Task) {
        builder[name] = item.name
        builder[shortName] = item.shortName
        builder[description] = item.description
        builder[maxValue] = maxValue
    }

    override fun readResult(result: ResultRow) =
        Task(
            result[name],
            result[shortName],
            result[description],
            result[maxValue],
            result[id].value
        )

    val taskTable = TaskTable()

    class TaskTableClass(id: EntityID<Int>) : ItemClass<Task>(id) {
        companion object : IntEntityClass<TaskTableClass>(Tasks)
        var name by Tasks.name
        var shortName by Tasks.shortName
        //var deadline by Tasks.deadline
        var description by Tasks.description
        var maxValue by Tasks.maxValue

        var type by Type referencedOn Tasks.type //где записать тип?
        var course by Course referencedOn Tasks.course

        override val obj: Task
            get() = Task(name,shortName, description, maxValue,id.value)

        override fun fill(item: Task) {
            name = item.name
            shortName = item.shortName
            description = item.description
            maxValue = item.maxValue
        }
    }
}