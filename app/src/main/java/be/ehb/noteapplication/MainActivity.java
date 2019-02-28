package be.ehb.noteapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;
import java.util.List;

import be.ehb.noteapplication.model.Note;
import be.ehb.noteapplication.model.NoteDatabase;
import be.ehb.noteapplication.utilities.NoteAdapter;

public class MainActivity extends AppCompatActivity  {

    private RecyclerView rvNotes;
    private NoteAdapter noteAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNotes = findViewById(R.id.rv_notes);

        rvNotes = findViewById(R.id.rv_notes);
        List<Note> items = NoteDatabase.getInstance(getApplicationContext()).getNoteDAO().selectAllNote();


        noteAdapter = new NoteAdapter(items, getApplicationContext(), new NoteAdapter.OnItemClickListener() {


            @Override

            public void onItemClick(Note item) {

                Intent i = new Intent(getApplicationContext(), DetailNoteActivity.class);


                i.putExtra("note", item);


                startActivity(i);

            }


        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvNotes.setAdapter(noteAdapter);
        rvNotes.setLayoutManager(layoutManager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_note, menu);

        MenuItem menuItem = menu.findItem(R.id.mi_search);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

         SearchView.OnQueryTextListener searchListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                noteAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                noteAdapter.getFilter().filter(newText);
                return true;
            }
        };
        searchView.setOnQueryTextListener(searchListener);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ((item.getItemId())) {
            case R.id.mi_add:
                Intent intent = new Intent(getApplicationContext(), AddNoteActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}







