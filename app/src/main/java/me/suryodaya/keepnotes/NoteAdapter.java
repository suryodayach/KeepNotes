package me.suryodaya.keepnotes;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {

    private onItemClickListener listener;
    private GradientDrawable priorityCircle;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));
        holder.textViewDate.setText(currentNote.getDate());
        holder.textViewTime.setText(currentNote.getTime());

        int priority = currentNote.getPriority();
        priorityCircle = (GradientDrawable) holder.textViewPriority.getBackground();
        int resourceId = ContextCompat.getColor(holder.textViewPriority.getContext(), getPriorityBgColor(priority));
        priorityCircle.setColor(resourceId);

        int resourceIdText = ContextCompat.getColor(holder.textViewPriority.getContext(), getPriorityColor(priority));
        holder.textViewPriority.setTextColor(resourceIdText);
    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    public class NoteHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;
        private TextView textViewDate;
        private TextView textViewTime;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewDescription = itemView.findViewById(R.id.description);
            textViewPriority = itemView.findViewById(R.id.priority);
            textViewDate = itemView.findViewById(R.id.date);
            textViewTime = itemView.findViewById(R.id.time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface onItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    private int getPriorityColor(int priority){
        int priorityColor;
        switch (priority){
            case (1):
                priorityColor = R.color.priority1;
                break;
            case (2):
                priorityColor = R.color.priority2;
                break;
            case (3):
                priorityColor = R.color.priority3;
                break;
            case (4):
                priorityColor = R.color.priority4;
                break;
            default:
                priorityColor = R.color.priority5;
                break;
        }
        return priorityColor;
    }

    private int getPriorityBgColor(int priority){
        int priorityBgColor;
        switch (priority){
            case (1):
                priorityBgColor = R.color.priority1bg;
                break;
            case (2):
                priorityBgColor = R.color.priority2bg;
                break;
            case (3):
                priorityBgColor = R.color.priority3bg;
                break;
            case (4):
                priorityBgColor = R.color.priority4bg;
                break;
            default:
                priorityBgColor = R.color.priority5bg;
                break;
        }
        return priorityBgColor;
    }

}
