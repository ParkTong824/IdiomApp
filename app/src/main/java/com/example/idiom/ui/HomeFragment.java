package com.example.idiom.ui;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.idiom.R;
import com.example.idiom.adapter.SavedQuizRecyclerAdapter;
import com.example.idiom.model.SaveIdioms;
import com.example.idiom.model.SaveViewModel;
import com.example.idiom.util.MyfirebaseInstance;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button startQuizButton = view.findViewById(R.id.start_quiz_button);
        Button startStudyButton = view.findViewById(R.id.start_study_button);
        RecyclerView savedRecyclerView = view.findViewById(R.id.saved_quiz_recyclerView);

        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new PlayQuizFragment()).addToBackStack(null).commit();
            }
        });

        startStudyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new CardStudyFragment()).addToBackStack(null).commit();
            }
        });

        final SavedQuizRecyclerAdapter adapter = new SavedQuizRecyclerAdapter(new SavedQuizRecyclerAdapter.ItemOnClickListener() {
            @Override
            public void itemOnClick(SaveIdioms idiom) {
                //firebase 에 지우고 뷰모델 갱신
                MyfirebaseInstance.deleteIdiom(idiom.key);
                ArrayList<SaveIdioms> temp = SaveViewModel.saveIdiomsMutableLiveData.getValue();
                temp.remove(idiom);
                SaveViewModel.saveIdiomsMutableLiveData.setValue(temp);
            }
        });

        savedRecyclerView.setAdapter(adapter);

        SaveViewModel.saveIdiomsMutableLiveData.observe(this, new Observer<ArrayList<SaveIdioms>>() {
            @Override
            public void onChanged(ArrayList<SaveIdioms> saveIdioms) {
                adapter.setSavedList(saveIdioms);
            }
        });
    }
}
