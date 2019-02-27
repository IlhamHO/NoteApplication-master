package be.ehb.noteapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import be.ehb.noteapplication.model.Note;
import be.ehb.noteapplication.model.NoteDatabase;
import be.ehb.noteapplication.utilities.NoteAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNotes = findViewById(R.id.rv_notes);

        rvNotes = findViewById(R.id.rv_notes);
        List<Note> items = NoteDatabase.getInstance(getApplicationContext()).getNoteDAO().selectAllNote();

        NoteAdapter noteAdapter = new NoteAdapter(items,getApplicationContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());


        rvNotes.setAdapter(noteAdapter);
        rvNotes.setLayoutManager(layoutManager);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_note,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ((item.getItemId())){
            case R.id.mi_add:
                Intent intent= new Intent(getApplicationContext(),AddNoteActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
