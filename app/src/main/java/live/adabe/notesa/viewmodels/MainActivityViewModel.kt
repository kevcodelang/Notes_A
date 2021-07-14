package live.adabe.notesa.viewmodels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import live.adabe.notesa.models.Note
import live.adabe.notesa.models.NoteDatabase

class MainActivityViewModel : ViewModel() {


    val notesLiveData = MutableLiveData<List<Note>>()

    fun getNotes(database: NoteDatabase) {
    //  Default dispatchers with viewModel using viewModelScope
        viewModelScope.launch {
            notesLiveData.postValue(database.noteDao().getAllNotes())
        }
    }

    fun addNote(database: NoteDatabase, note: Note) {
        // Writing dispatchers using CoroutineScope
        CoroutineScope(Dispatchers.IO).launch {
            database.noteDao().addNote(note)
            getNotes(database)
        }
    }
}