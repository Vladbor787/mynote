object CommentServiceImpl:NoteAndCommentServInterface<Comment> {
    private var comments = mutableListOf(Comment())
    override fun add(entity: Comment): Comment {
        comments.add(entity)
        return comments.last()
    }

    override fun delete(id: Int) {
        comments.forEach { comment ->
            if (id == comment.id) comment.deleted = true
        }
    }

    override fun edit(id: Int, entity: Comment): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (!comment.deleted) {
                if (id == comment.id) {
                    comments[index] = comment.copy(
                        idOfNote = comment.idOfNote, id = comment.id,
                        deleted = false, text = comment.text
                    )
                    return true
                }
            }
        }
        throw CommentNotFoundException("Комментарий не найден")
    }

    override fun read(id: Int): List<Comment> {
        comments.forEach { comment ->
            if (comment.idOfNote == id) return comments
        }
        throw CommentNotFoundException("Комментарий не найден")
    }

    override fun getById(i: Int): Comment {
        comments.forEach { comment ->
            if (comment.id == i) return comment
        }
        throw CommentNotFoundException("Комментарий не найден")
    }

    override fun restore(i: Int) {
        comments.forEach { comment ->
            if (comment.id == i && comment.deleted) comment.deleted = false
        }
        throw CommentNotFoundException("Комментарий не найден")
    }
}