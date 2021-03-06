package ru.myitschool.nasa_bootcamp.data.model

class SubComment(
    id: Long,
    val parentComment: Comment,
    text: String,
    likes: List<UserModel>,
    author: UserModel,
    date: Long
) : Comment(id, text, likes, listOf(), author, date) {

    override fun toString(): String {
        return "ID: $id\nFatherId: ${parentComment.author.id}\nComment: $text\nLikes: $likes\nUserId: ${author.id}\nDate:$date"
    }
}