import org.junit.Assert.*
import org.junit.Test

class NoteAndCommentServiceImplTest {

    @Test
    fun addNote() {
        val note1 = Note(id = 5)
        val expected = 1
        val result = NoteServiceImpl.add(note1).id
        assertEquals(expected, result)
    }

    @Test
    fun addComment() {
        val comment1 = Comment(id = 3)
        val expected = 3
        val result = CommentServiceImpl.add(comment1).id

        assertEquals(expected, result)
    }

    @Test
    fun shouldEditNoteIfItExists() {
        val note1Corrected = Note(id = 56, title = "newTitle")
        NoteServiceImpl.add(note1Corrected)
        val expected = true
        val result = NoteServiceImpl.edit(1, note1Corrected)

        assertEquals(expected, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun shouldNotEditNoteWithoutNoteExists() {
        val note1Corrected = Note(id = 56, title = "newTitle")
        NoteServiceImpl.add(note1Corrected)

        NoteServiceImpl.edit(5, note1Corrected)
    }

    @Test
    fun shouldEditComment() {
        val comment3 = Comment(id = 5, idOfNote = 1)
        val commentEdit = Comment(id = 6, idOfNote = 1, text = "newText")
        CommentServiceImpl.add(comment3)
        val expected = true
        val result = CommentServiceImpl.edit(5, commentEdit)

        assertEquals(expected, result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun `shouldNotEditCommentIfCommentDoesn'tExists`() {
        val commentEdit = Comment(id = 14, idOfNote = 1, text = "newText")
        CommentServiceImpl.add(commentEdit)
        CommentServiceImpl.edit(8, commentEdit)
    }

    @Test(expected = NoteNotFoundException::class)
    fun `shouldNotRestoreNoteIfNoteDoesn'tExists`() {
        val noteEdit = Note(id = 6, text = "newText")
        NoteServiceImpl.add(noteEdit)
        NoteServiceImpl.edit(8, noteEdit)
    }

    @Test(expected = CommentNotFoundException::class)
    fun `shouldNotRestoreCommentIfCommentDoesn'tExists`() {
        val commentEdit = Comment(id = 6, idOfNote = 1, text = "newText")
        CommentServiceImpl.add(commentEdit)
        CommentServiceImpl.edit(8, commentEdit)
    }
}