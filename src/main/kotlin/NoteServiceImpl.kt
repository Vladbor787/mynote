object NoteServiceImpl:NoteAndCommentServInterface <Note>{
    private var notes = mutableListOf(Note())
    private var noteId: Int = 1
    override fun add(entity: Note): Note {
        notes.add(entity.copy(id = noteId))
        noteId++
        return notes.last()
    }

    override fun delete(id: Int) {
        notes.forEach { note ->
            if (id == note.id) note.deleted = true
        }
    }

    override fun edit(id: Int, entity: Note): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (id == note.id) {
                notes[index] = note.copy(
                    id = note.id, ownerId = note.ownerId, title = entity.title,
                    text = entity.text
                )
                return true
            }
        }
        throw NoteNotFoundException("Заметка не найдена")
    }

    override fun read(id: Int): List<Note> {
        notes.forEach { note ->
            if (note.ownerId == id) return notes
    }
        throw NoteNotFoundException("Заметка не найдена")
    }

    override fun getById(i: Int): Note {
        notes.forEach { note ->
            if (note.id == i) return note
        }
        throw NoteNotFoundException("Заметка не найдена")
    }

    override fun restore(i: Int) {
        notes.forEach { note ->
            if (note.id == i && note.deleted) note.deleted = false
        }
        throw NoteNotFoundException("Заметка не найдена")
    }
}