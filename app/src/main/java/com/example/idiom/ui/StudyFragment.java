package com.example.idiom.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.idiom.R;
import com.example.idiom.adapter.StudyRecyclerViewAdapter;
import com.example.idiom.model.Idioms;
import com.example.idiom.util.MyfirebaseInstance;

public class StudyFragment extends Fragment {

    private ShowDetailFragment showDetailFragment;
    public StudyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_study, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        showDetailFragment = new ShowDetailFragment();

        RecyclerView recyclerView = view.findViewById(R.id.study_recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        StudyRecyclerViewAdapter adapter = new StudyRecyclerViewAdapter(new StudyRecyclerViewAdapter.ItemOnClickListener() {
            @Override
            public void itemOnClick(Idioms idiom) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("idi", idiom);
                showDetailFragment.setArguments(bundle);
                requireActivity().getSupportFragmentManager().beginTransaction().add(R.id.container_main, showDetailFragment).addToBackStack(null).commit();
            }
        });

        recyclerView.setAdapter(adapter);
        adapter.setItems(MyfirebaseInstance.idiomsList);
    }
}
