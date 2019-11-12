package com.example.idiom.util;

import androidx.recyclerview.widget.DiffUtil;

import com.example.idiom.model.Idioms;

import java.util.ArrayList;

public class IdiomDiffCallback extends DiffUtil.Callback {

    private ArrayList<Idioms> oldList;
    private ArrayList<Idioms> newList;

    public IdiomDiffCallback(ArrayList<Idioms> oldList, ArrayList<Idioms> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId().equals(newList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition) == newList.get(newItemPosition);
    }
}
