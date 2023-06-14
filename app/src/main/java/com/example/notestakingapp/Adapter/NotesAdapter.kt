package com.example.notestakingapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notestakingapp.Models.Note
import com.example.notestakingapp.R
import kotlin.random.Random

class NotesAdapter(private val context: Context,val listener:NotesItemClickListener): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    private val notesList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val notesLayout = itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val note = itemView.findViewById<TextView>(R.id.tv_note)
        val date = itemView.findViewById<TextView>(R.id.tv_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false))
    }

    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {
        val currentNote = notesList[position]
        holder.title.text = currentNote.title
        holder.note.text = currentNote.note
        holder.date.text = currentNote.date
        holder.title.isSelected = true
        holder.date.isSelected = true
        holder.notesLayout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(),null))
        holder.notesLayout.setOnClickListener{
            listener.onItemClicked(notesList[holder.adapterPosition])
        }
        holder.notesLayout.setOnLongClickListener{
            listener.onItemLongClicked(notesList[holder.adapterPosition],holder.notesLayout)
            true
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
    fun randomColor():Int {
        val list = arrayListOf<Int>()
        list.add(R.color.colorAmber_200)
        list.add(R.color.colorAmber_500)
        list.add(R.color.colorBlueGrey_700)
        list.add(R.color.colorBrown_300)
        list.add(R.color.colorCyan_A200)
        list.add(R.color.colorDanger)
        list.add(R.color.colorDeepPurple_100)
        return list[Random(System.currentTimeMillis().toInt()).nextInt(list.size)]
    }
    interface NotesItemClickListener{
        fun onItemClicked(note:Note)
        fun onItemLongClicked(note:Note,cardView:CardView)
    }
    fun updateList(newList:List<Note>) {
        fullList.clear()
        fullList.addAll(newList)

        notesList.clear()
        notesList.addAll(fullList)
        notifyDataSetChanged()
    }
    fun filterList(search: String) {
        notesList.clear()
        for (item in fullList) {
            if (item.title?.lowercase()?.contains(search.lowercase()) == true || item.note?.lowercase()?.contains(search.lowercase()) == true) {
                notesList.add(item)
            }
        }
        notifyDataSetChanged()
    }
}