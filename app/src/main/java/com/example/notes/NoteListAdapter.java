package com.example.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class NoteListAdapter extends ListAdapter<Note, NoteViewHolder> {



    public NoteListAdapter(@NonNull DiffUtil.ItemCallback<Note> diffCallback) {
        super(diffCallback);
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // layout inflater
//        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent ,false);
//        return new viewHolder(view);
        return NoteViewHolder.create(parent);

    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note current = getItem(position);
        holder.bind(current.getNote());
    }

    static class NoteDiff extends DiffUtil.ItemCallback<Note> {

        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getNote().equals(newItem.getNote());
        }

    }
//    public class viewHolder extends RecyclerView.ViewHolder {
//
//        //define data fields here to access findviewby id
//        TextView textView;
//        Button button;
//
//        // default constructor
//        public viewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            textView = itemView.findViewById(R.id.text);
//            button = itemView.findViewById(R.id.deleteButton);
//        }
//
//    }

}
// create an interface for NewsItemClicked
//interface INotesRVAdapter {
//    void onItemClicked(Note note);
//}
