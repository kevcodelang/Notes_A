package live.adabe.notesa.viewmodels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import live.adabe.notesa.models.Note
import live.adabe.notesa.models.NoteDatabase

class MainActivityViewModel : ViewModel() {


    val notesLiveData = MutableLiveData<List<Note>>()

    fun getNotes(database: NoteDatabase){
        notesLiveData.postValue(database.noteDao().getAllNotes())
    }

    fun addNote(database: NoteDatabase, note: Note){
        database.noteDao().addNote(note)
        getNotes(database)
    }

}