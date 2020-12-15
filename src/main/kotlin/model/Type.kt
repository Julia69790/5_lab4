package model

import kotlinx.serialization.Serializable
import objects.Types
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import repo.Item
import repo.ItemClass
import repo.ItemTable

/*class Type (
    id: EntityID<Int>
) : IntEntity(id) {
    companion object : IntEntityClass<Type>(Types)
    var name by Types.name
    var shortName by Types.shortName
}*/
@Serializable
class Type(
    val nameT:String,
    val shortNameT: String,
    override var id: Int = -1
): Item

class TypeTable: ItemTable<Type>(){
    val nameT = varchar("nameT", 50)
    val shortNameT = varchar("shortNameT", 10)

    override fun fill(builder: UpdateBuilder<Int>, item: Type) {
        builder[nameT] = item.nameT
        builder[shortNameT] = item.shortNameT
    }
    override fun readResult(result: ResultRow) =
        Type(
            result[nameT],
            result[shortNameT]
        )

    val typeTable = TypeTable()

    class TypeTableClass (id: EntityID<Int>) : ItemClass<Type>(id) {
        companion object : IntEntityClass<TypeTableClass>(Types)
        var name by Types.name
        var shortName by Types.shortName

        override val obj: Type
            get() = Type(name,shortName, id.value)

        override fun fill(item: Type) {
            name = item.nameT
            shortName = item.shortNameT
        }
    }
}