package be.ehb.noteapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import be.ehb.noteapplication.model.Note;

public class DetailNoteActivity extends AppCompatActivity {

    private TextView tvTitel, tvContent, tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_note);

        tvTitel = findViewById(R.id.tv_detail_titel);
        tvContent = findViewById(R.id.tv_detail_content);
        tvDate = findViewById(R.id.tv_detail_date);


        Note n = (Note) getIntent().getSerializableExtra("note");



        tvTitel.setText(n.getTitel());

        tvContent.setText(n.getContent());

        tvDate.setText(n.getPublishDate().toString());


    }
}
