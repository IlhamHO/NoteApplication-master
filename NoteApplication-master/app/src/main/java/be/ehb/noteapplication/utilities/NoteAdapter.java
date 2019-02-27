package be.ehb.noteapplication.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import be.ehb.noteapplication.R;
import be.ehb.noteapplication.model.Convertor;
import be.ehb.noteapplication.model.Note;
import be.ehb.noteapplication.model.NoteDatabase;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {


    public class NoteViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitel;
        public TextView tvDate;
        public Button btnDelete;
        public Button btnDetail;

        private View.OnClickListener deleteListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = getAdapterPosition();
                Note todelete = noteItems.get(position);

                NoteDatabase.getInstance(context).getNoteDAO().deleteNote(todelete);
                noteItems.remove(todelete);
                notifyDataSetChanged();

            }
        };

        private  View.OnClickListener detailListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = getAdapterPosition();
                Note detailNote = noteItems.get(pos);



            }
        };

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitel = itemView.findViewById(R.id.tv_card_titel);
            tvDate = itemView.findViewById(R.id.tv_card_date);
            btnDelete = itemView.findViewById(R.id.btn_card_delete);
            btnDetail = itemView.findViewById(R.id.btn_card_detail);

            btnDetail.setOnClickListener(detailListener);

            btnDelete.setOnClickListener(deleteListener);
        }
    }


        private List<Note> noteItems;
        private Context context;
        public  NoteAdapter(List<Note>noteItems,Context context){
            this.noteItems = noteItems;
            this.context = context;

    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.note_card,viewGroup,false);

        return  new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i) {


        Note noteVoorDeRij = noteItems.get(i);

        String dateAsString = Convertor.stringFromDate(noteVoorDeRij.getPublishDate());

        noteViewHolder.tvTitel.setText(noteVoorDeRij.getTitel());
        noteViewHolder.tvDate.setText(dateAsString);

    }

    @Override
    public int getItemCount() {
        return noteItems.size();
    }

}
