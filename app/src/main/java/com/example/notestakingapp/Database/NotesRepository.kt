package com.example.notestakingapp.Database

import androidx.lifecycle.LiveData
import com.example.notestakingapp.Models.Note

class NotesRepository(private val noteDao:NoteDao) {
    val allNotes : LiveData<List<Note>> = noteDao.getAllNotes()

    fun insert(note:Note) {
        noteDao.insert(note)
    }
    fun delete(note:Note) {
        noteDao.delete(note)
    }
    fun update(note:Note) {
        noteDao.update(note.id,note.title,note.note)
    }
}