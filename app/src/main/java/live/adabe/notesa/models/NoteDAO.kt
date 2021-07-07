package live.adabe.notesa.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface NoteDAO {
    @Query("SELECT * FROM note")
    fun getAllNotes() : List<Note>

    @Query("SELECT * FROM note WHERE id = :id")
    fun getNoteById(id: Int): Note

    @Insert
    fun addNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
}