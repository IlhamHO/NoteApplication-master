package be.ehb.noteapplication.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;


@Database(version = 1,entities = {Note.class},exportSchema = false)
@TypeConverters({Convertor.class})
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;

    public static NoteDatabase getInstance(Context context) {

        if(instance == null){
            instance = createDatabase(context);
        }
        return instance;
    }
    private  static NoteDatabase createDatabase(Context context){
        return Room.databaseBuilder(context,NoteDatabase.class,"notes.db").allowMainThreadQueries().build();
    }
    public abstract NoteDao getNoteDAO();
}
