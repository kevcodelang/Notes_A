package live.adabe.notesa.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import live.adabe.notesa.databinding.NoteItemBinding

class NoteAdapter(var notes: List<Note>, val clicker: (Note) -> Unit ) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    inner class NoteViewHolder(private val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindNote(note: Note){
            binding.apply {
                idDisplay.text = note.id.toString()
                titleDisplay.text = note.title
                root.setOnClickListener { clicker(note) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context))
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindNote(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}
