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

public class SavedQuizRecyclerAdapter extends RecyclerView.Adapter<SavedQuizRecyclerAdapter.ViewHolder> {

    private ArrayList<Idioms> mSavedList = new ArrayList<>();

    private void setmSavedList(ArrayList<Idioms> mSavedList) {
        this.mSavedList = mSavedList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SavedQuizRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_recycler_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedQuizRecyclerAdapter.ViewHolder holder, int position) {
        Idioms idioms = mSavedList.get(position);
        holder.remainQuiz.setText(idioms.getId());
        holder.solvedQuiz.setText(idioms.getMean());
        holder.savedTime.setText(idioms.getTitle());
    }

    @Override
    public int getItemCount() {
        return mSavedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView savedTime;
        TextView solvedQuiz;
        TextView remainQuiz;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            savedTime = itemView.findViewById(R.id.saved_time_textView);
            solvedQuiz = itemView.findViewById(R.id.saved_solve_quiz_textView);
            remainQuiz = itemView.findViewById(R.id.saved_remain_quiz_textView);
        }
    }
}
