package live.adabe.notesa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import live.adabe.notesa.activities.NoteDetailsActivity
import live.adabe.notesa.databinding.ActivityMainBinding
import live.adabe.notesa.models.Note
import live.adabe.notesa.models.NoteAdapter
import live.adabe.notesa.models.NoteDatabase
import live.adabe.notesa.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: NoteDatabase
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var viewModel: MainActivityViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //instantiating database
        database = Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            "notes_database"
        ).allowMainThreadQueries().build()

        //instantiating viewMOdel
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getNotes(database)

        //observe live data from view model
        viewModel.notesLiveData.observe(this, {notes ->

            //creating adapter
            noteAdapter = NoteAdapter(notes) {
                val intent = Intent(this@MainActivity, NoteDetailsActivity::class.java)
                intent.run {
                    putExtra("id", it.id)
                }
                startActivity(intent)
            }

//            refreshing recycler view

            binding.notesRv.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = noteAdapter
            }
        })


        binding.saveButton.setOnClickListener {
            val title = binding.titleInput.text.toString()
            val content = binding.contentInput.text.toString()

            saveNote(title, content)
        }

    }

    private fun saveNote(title: String, content: String) {
        val note = Note(id = 0, title, content)
        viewModel.addNote(database, note)
    }

//    private val listener = object: NoteAdapter.OnNoteItemClickListener{
//        override fun onClick(note: Note) {
//            val intent = Intent(this@MainActivity, NoteDetailsActivity::class.java)
//            intent.run {
//                putExtra("id", note.id)
//                startActivity(this)
//            }
//        }
//
//    }
}