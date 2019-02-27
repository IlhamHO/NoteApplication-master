package be.ehb.noteapplication.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    public  void insertNote(Note nNote);

    @Delete
    public void deleteNote(Note nNote);

    @Query("SELECT * FROM Note")
    List<Note> selectAllNote();

    @Query("SELECT * FROM Note WHERE id = :id")
    Note selectNoteById(long id);
}
