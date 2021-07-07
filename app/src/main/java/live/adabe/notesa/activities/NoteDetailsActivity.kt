package live.adabe.notesa.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import live.adabe.notesa.R
import live.adabe.notesa.databinding.ActivityNoteDetailsBinding

class NoteDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            idDisplayTv.text = intent.getIntExtra("id", 0).toString()
            noteContent.text = intent.getStringExtra("content")
            noteTitle.text = intent.getStringExtra("title")
        }
    }
}