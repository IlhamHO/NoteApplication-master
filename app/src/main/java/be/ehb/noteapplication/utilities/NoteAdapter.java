package be.ehb.noteapplication.utilities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import be.ehb.noteapplication.DetailNoteActivity;
import be.ehb.noteapplication.R;
import be.ehb.noteapplication.model.Convertor;
import be.ehb.noteapplication.model.Note;
import be.ehb.noteapplication.model.NoteDatabase;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> implements Filterable{





  public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvTitel;
        public TextView tvDate;
        public Button btnDelete;
        private ItemClickListener itemClickListener;

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


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitel = itemView.findViewById(R.id.tv_card_titel);
            tvDate = itemView.findViewById(R.id.tv_card_date);
            btnDelete = itemView.findViewById(R.id.btn_card_delete);

            itemView.setOnClickListener(this);


            btnDelete.setOnClickListener(deleteListener);
        }

        @Override

        public void onClick(View v) {


            int welkeRij = getAdapterPosition();

            Note n = noteItems.get(welkeRij);

            noteItemClickListener.onItemClick(n);

        }

    }

    public interface OnItemClickListener {

        void onItemClick(Note item);

    }


    private List<Note> filteredNoteItems;
    private List<Note> noteItems;
    private Context context;
    private final OnItemClickListener noteItemClickListener;

    public NoteAdapter(List<Note> noteItems, Context context, OnItemClickListener noteItemClickListener) {
        this.noteItems = noteItems;
        this.context = context;
        this.noteItemClickListener = noteItemClickListener;
        filteredNoteItems = noteItems;


    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.note_card, viewGroup, false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i) {


        Note noteVoorDeRij = filteredNoteItems.get(i);

        String dateAsString = Convertor.stringFromDate(noteVoorDeRij.getPublishDate());

        noteViewHolder.tvTitel.setText(noteVoorDeRij.getTitel());
        noteViewHolder.tvDate.setText(dateAsString);

    }

    @Override
    public int getItemCount() {

        return filteredNoteItems.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Note> filteredList = new ArrayList<>();

          if (constraint.length() == 0) {
                filteredList.addAll(noteItems);
            } else {
                String zoekterm = constraint.toString().toLowerCase().trim();
                for (Note n : filteredNoteItems) {
                    if (n.getTitel().toLowerCase().contains(zoekterm)) {
                        filteredList.add(n);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

       @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
           filteredNoteItems.clear();
           filteredNoteItems.addAll((List<Note>) filterResults.values);
            notifyDataSetChanged();

        }
    };

}
