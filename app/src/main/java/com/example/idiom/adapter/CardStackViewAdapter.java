package com.example.idiom.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.idiom.R;
import com.example.idiom.model.Idioms;

import java.util.ArrayList;

public class CardStackViewAdapter extends RecyclerView.Adapter<CardStackViewAdapter.CardViewHolder> {

    private ArrayList<Idioms> mIdiomList;
    private Context mContext;

    public CardStackViewAdapter(ArrayList<Idioms> mIdiomList, Context mContext) {
        this.mIdiomList = mIdiomList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardstack_item_layout, parent,false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, int position) {
        Idioms idioms = mIdiomList.get(position);
        Log.e("position",""+position);
        holder.id.setText(idioms.getId());
        holder.title.setText(idioms.getTitle());
        holder.mean.setText(idioms.getMean());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.title.setVisibility(View.VISIBLE);
            }
        });
        holder.title.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mIdiomList.size();
    }

    public ArrayList<Idioms> getIdiomList(){
        return mIdiomList;
    }

    public void setIdiomList(ArrayList<Idioms> mIdiomList) {
        this.mIdiomList = mIdiomList;
    }

    public void shuffleIdiomList(ArrayList<Idioms> mIdiomList) {
        this.mIdiomList.clear();
        this.mIdiomList.addAll(mIdiomList);
        notifyDataSetChanged();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView title;
        TextView mean;
        Button button;
        CardViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.show_title_button);
            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            mean = itemView.findViewById(R.id.mean);
        }
    }
}
