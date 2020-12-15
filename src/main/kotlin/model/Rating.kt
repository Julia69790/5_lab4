package model

import kotlinx.serialization.Serializable
import objects.Ratings
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import repo.Item
import repo.ItemClass
import repo.ItemTable

/*open class Rating(
    id: EntityID<Int>
) : IntEntity(id) {
    companion object : IntEntityClass<Rating>(Ratings)
    var value by Ratings.value
}*/

@Serializable
class Rating(
    val value :Int,
    override var id: Int = -1
) : Item

class RatingTable : ItemTable<Rating>() {

    val value = integer ("value")

    override fun fill(builder: UpdateBuilder<Int>, item: Rating) {
        builder[value] = item.value
    }

    override fun readResult(result: ResultRow) =
        Rating(
            result[value],
            result[id].value
        )
}

val ratingTable = RatingTable()

class RatingTableClass(id: EntityID<Int>) : ItemClass<Rating>(id) {
    companion object : IntEntityClass<RatingTableClass>(Ratings)
    var value by Ratings.value

    override val obj: Rating
        get() = Rating(value,id.value)

    override fun fill(item: Rating) {
        value = item.value
    }
}
