package com.example.idiom.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.idiom.R;
import com.example.idiom.adapter.SavedQuizRecyclerAdapter;
import com.example.idiom.model.SaveIdioms;
import com.example.idiom.model.SaveViewModel;
import com.example.idiom.util.MyfirebaseInstance;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private PlayQuizFragment playQuizFragment;
    private Bundle bundle;

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
        final LottieAnimationView lottieAnimationView = view.findViewById(R.id.loading_animation);
        lottieAnimationView.setAnimation("loading.json");
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();

        Button startQuizButton = view.findViewById(R.id.start_quiz_button);
        Button startStudyButton = view.findViewById(R.id.start_study_button);
        RecyclerView savedRecyclerView = view.findViewById(R.id.saved_quiz_recyclerView);
        final TextView saveQuizTitle = view.findViewById(R.id.saved_Quiz_title);
        bundle = new Bundle();
        playQuizFragment = new PlayQuizFragment();

        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //문제풀기 시작 버튼
                bundle = new Bundle();
                bundle.putBoolean("flag", false);
                playQuizFragment.setArguments(bundle);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_main, playQuizFragment).addToBackStack(null).commit();
            }
        });

        startStudyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //공부하기 시작 버튼
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new CardStudyFragment()).addToBackStack(null).commit();
            }
        });

        final SavedQuizRecyclerAdapter adapter = new SavedQuizRecyclerAdapter(new SavedQuizRecyclerAdapter.ItemOnClickListener() {
            @Override
            public void itemOnClick(SaveIdioms idiom) {
                //데이터베이스에 지우고 뷰모델 갱신
                MyfirebaseInstance.deleteIdiom(idiom.key);
                ArrayList<SaveIdioms> temp = SaveViewModel.saveIdiomsMutableLiveData.getValue();
                if (temp != null) {
                    temp.remove(idiom);
                }
                SaveViewModel.saveIdiomsMutableLiveData.setValue(temp);
            }
        });

        savedRecyclerView.setAdapter(adapter);

        adapter.setSaveItemOnClickListener(
                new SavedQuizRecyclerAdapter.SaveItemOnClickListener() {
                    @Override
                    public void saveItemOnClick(SaveIdioms saveIdioms) {

                        bundle = new Bundle();
                        bundle.putBoolean("flag", true);
                        bundle.putSerializable("idi", saveIdioms);
                        playQuizFragment.setArguments(bundle);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_main, playQuizFragment).addToBackStack(null).commit();
                    }
                }
        );


        SaveViewModel.saveIdiomsMutableLiveData.observe(this, new Observer<ArrayList<SaveIdioms>>() {
            @Override
            public void onChanged(ArrayList<SaveIdioms> saveIdioms) {
                if (saveIdioms.size() > 0) {
                    saveQuizTitle.setVisibility(View.VISIBLE);
                } else {
                    saveQuizTitle.setVisibility(View.GONE);
                }
                adapter.setSavedList(saveIdioms);
            }
        });

        SaveViewModel.loadingDataSize.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer > 0) {
                    lottieAnimationView.setVisibility(View.GONE);
                }
            }
        });
    }
}
