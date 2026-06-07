package com.example.nabil_king.Note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nabil_king.data.entity.NoteEntity
import com.example.nabil_king.databinding.ItemNoteBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NoteAdapter(
    private val notes: MutableList<NoteEntity>,
    private val onNoteDelete: (NoteEntity) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]

        holder.binding.tvTitle.text = note.title
        holder.binding.tvContent.text = note.content

        holder.binding.btnDelete.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Catatan")
                .setMessage("Yakin ingin menghapus catatan lapangan ini?")
                .setPositiveButton("Hapus") { _, _ -> onNoteDelete(note) }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    override fun getItemCount(): Int = notes.size

    fun updateData(newNotes: List<NoteEntity>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }
}