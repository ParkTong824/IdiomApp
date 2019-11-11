package com.example.idiom.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.idiom.R;
import com.example.idiom.model.Idioms;

public class ShowDetailFragment extends Fragment {

    public ShowDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView title = view.findViewById(R.id.title);
        TextView korean = view.findViewById(R.id.korean);
        TextView mean = view.findViewById(R.id.mean);

        Idioms idiom;
        if (getArguments() != null) {
            idiom = (Idioms) getArguments().getSerializable("idi");
            if (idiom != null) {
                title.setText(idiom.getTitle());
                korean.setText(idiom.getId());
                mean.setText(idiom.getMean());
            }
        }
    }
}
