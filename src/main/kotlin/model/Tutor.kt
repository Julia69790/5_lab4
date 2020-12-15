package model

import `object`.Students
import kotlinx.serialization.Serializable
import objects.CourseStudents
import objects.CourseTutors
import objects.Tutors
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import repo.Item
import repo.ItemTable

/*class Tutor(
    id: EntityID<Int>
) : Person(id) {
    companion object : IntEntityClass<Tutor>(Tutors)
    var name by Tutors.name
    var post by Tutors.post

    var course by Course via CourseTutors
}
 */

@Serializable
class Tutor(
    val name :String,
    val post: String,
    override var id: Int = -1
) : Item

class TutorTable : ItemTable<Tutor>() {
    val name = varchar("name", 50)
    val post = varchar("post", 50)

    override fun fill(builder: UpdateBuilder<Int>, item: Tutor) {
        builder[name] = item.name
        builder[post] = item.post
    }

    override fun readResult(result: ResultRow) =
        Tutor(
            result[name],
            result[post],
            result[id].value
        )
}

val tutorTable = TutorTable()

class TutorTableClass (id: EntityID<Int>) : Person(id) {
    companion object : IntEntityClass<TutorTableClass>(Tutors)
    var name by Tutors.name
    var post by Tutors.post //почему не используется

    //var course by Course via CourseTutors //надо как-то заменить?
   /* override val obj: Tutor
        get() = Tutor(name, post,id.value)

    override fun fill(item: Tutor) {
        name = item.name
        post = item.post
    }*/
}
