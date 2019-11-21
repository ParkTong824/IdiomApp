package com.example.idiom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.idiom.R;
import com.example.idiom.model.SaveIdioms;

import java.util.ArrayList;

public class SavedQuizRecyclerAdapter extends RecyclerView.Adapter<SavedQuizRecyclerAdapter.ViewHolder> {

    private ArrayList<SaveIdioms> mSavedList = new ArrayList<>();

    public void setSavedList(ArrayList<SaveIdioms> mSavedList) {
        this.mSavedList = mSavedList;
        notifyDataSetChanged();
    }

    public SavedQuizRecyclerAdapter(ItemOnClickListener mListener) {
        this.mListener = mListener;
    }

    public interface ItemOnClickListener {
        void itemOnClick(SaveIdioms idiom);
    }
    private SavedQuizRecyclerAdapter.ItemOnClickListener mListener;

    public interface SaveItemOnClickListener {
        void saveItemOnClick(SaveIdioms saveIdioms);
    }

    private SavedQuizRecyclerAdapter.SaveItemOnClickListener saveItemOnClickListener;

    public void setSaveItemOnClickListener(SaveItemOnClickListener saveItemOnClickListener) {
        this.saveItemOnClickListener = saveItemOnClickListener;
    }

    @NonNull
    @Override
    public SavedQuizRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_recycler_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SavedQuizRecyclerAdapter.ViewHolder holder, int position) {
        final SaveIdioms saveIdioms = mSavedList.get(position);
        holder.remainQuiz.setText(String.valueOf(saveIdioms.remainQuiz));
        holder.solvedQuiz.setText(String.valueOf(saveIdioms.solvedQuiz));
        holder.savedTime.setText(saveIdioms.cancelTime);
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.itemOnClick(mSavedList.get(holder.getAdapterPosition()));
            }
        });
        holder.saveFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveItemOnClickListener.saveItemOnClick(mSavedList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSavedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView savedTime;
        TextView solvedQuiz;
        TextView remainQuiz;
        ImageView deleteButton;
        LinearLayout saveFrame;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            saveFrame = itemView.findViewById(R.id.saveFrame);
            savedTime = itemView.findViewById(R.id.saved_time_textView);
            solvedQuiz = itemView.findViewById(R.id.saved_solve_quiz_textView);
            remainQuiz = itemView.findViewById(R.id.saved_remain_quiz_textView);
            deleteButton = itemView.findViewById(R.id.save_delete_button);
        }
    }
}
