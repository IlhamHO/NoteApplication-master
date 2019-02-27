package be.ehb.noteapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

import be.ehb.noteapplication.model.Note;
import be.ehb.noteapplication.model.NoteDatabase;

public class AddNoteActivity extends AppCompatActivity {

    private EditText etTitel;
    private EditText etContent;
    private FloatingActionButton fabSave;

    private View.OnClickListener fabSaveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Note nNote= new Note(etTitel.getText().toString(),etContent.getText().toString(),new Date());


            NoteDatabase.getInstance(getApplicationContext()).getNoteDAO().insertNote(nNote);

            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etTitel = findViewById(R.id.et_titel);
        etContent = findViewById(R.id.et_content);
        fabSave = findViewById(R.id.fab_save);
        fabSave.setOnClickListener(fabSaveListener);
    }
}
