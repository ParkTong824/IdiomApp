package com.example.idiom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.idiom.R;
import com.example.idiom.model.Idioms;

import java.util.ArrayList;
import java.util.List;

public class StudyRecyclerViewAdapter extends RecyclerView.Adapter<StudyRecyclerViewAdapter.ViewHolder> {
    private List<Idioms> mListItem = new ArrayList<>();

    public interface ItemOnClickListener {
        void itemOnClick(Idioms idiom);
    }
    private ItemOnClickListener mListener;

    public StudyRecyclerViewAdapter(ItemOnClickListener mListener) {
        this.mListener = mListener;
    }

    public void setItems(List<Idioms> items){
        mListItem = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.study_recyclerview, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.itemOnClick(mListItem.get(holder.getAdapterPosition()));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Idioms idiom = mListItem.get(position);
        holder.content.setText(idiom.getMean());
        holder.title.setText(idiom.getTitle());
    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
        }
    }
}
