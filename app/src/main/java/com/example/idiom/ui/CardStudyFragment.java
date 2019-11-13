package com.example.idiom.ui;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;

import com.example.idiom.R;
import com.example.idiom.adapter.CardStackViewAdapter;
import com.example.idiom.model.Idioms;
import com.example.idiom.util.IdiomDiffCallback;
import com.example.idiom.util.MyfirebaseInstance;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardStudyFragment extends Fragment implements CardStackListener {

    private CardStackLayoutManager manager;
    private CardStackViewAdapter adapter;

    private TextView cardStackTotal;
    private TextView curStackView;

    private ArrayList<Idioms> shuffledList;

    private int position = 0;

    public CardStudyFragment() {
        // Required empty public constructor
    }
    private CardStackView cardStackView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        adapter = new CardStackViewAdapter(MyfirebaseInstance.idiomsList,getActivity());
        shuffledList = new ArrayList<>(MyfirebaseInstance.idiomsList);
        return inflater.inflate(R.layout.fragment_card_study, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        manager = new CardStackLayoutManager(getActivity(),this);
        cardStackView = view.findViewById(R.id.stackView);
        cardStackTotal = view.findViewById(R.id.totalPositionTextView);
        curStackView = view.findViewById(R.id.curPositionTextView);
        cardStackTotal.setText(String.valueOf(MyfirebaseInstance.idiomsList.size()));
        curStackView.setText(String.valueOf(position));
        final ImageView rewindImage = view.findViewById(R.id.rewind_imageView);
        rewindImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardStackView.rewind();
                Animation anim = AnimationUtils.loadAnimation(
                        getContext(),
                        R.anim.rotate);
                rewindImage.startAnimation(anim);
            }
        });
        initialize();

        ImageView shuffleImage = view.findViewById(R.id.shuffle_button);
        shuffleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "카드섞기완료", Toast.LENGTH_SHORT).show();
                Collections.shuffle(shuffledList);
                adapter.shuffleIdiomList(shuffledList);
                position = 0;
                curStackView.setText(String.valueOf(position));
            }
        });
    }

    private void initialize() {
        manager.setStackFrom(StackFrom.Top);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.9f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.HORIZONTAL);
        manager.setCanScrollHorizontal(true);
        manager.setCanScrollVertical(true);
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {
//        Log.e("CardStackView","onCardDragging"+direction.name()+","+ratio);
    }

    @Override
    public void onCardSwiped(Direction direction) {
        position++;
        curStackView.setText(String.valueOf(position));
        Log.e("swipe","position->"+position);
        if (manager.getTopPosition() == adapter.getItemCount() - 5) {
            paginate();
        }
    }

    private void paginate() {
        ArrayList<Idioms> old = adapter.getIdiomList();
        ArrayList<Idioms> newList = new ArrayList<>(old);
        IdiomDiffCallback callback = new IdiomDiffCallback(old, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        adapter.setIdiomList(newList);
        result.dispatchUpdatesTo(adapter);
    }

    @Override
    public void onCardRewound() {
        position--;
        curStackView.setText(String.valueOf(position));
        Log.e("CardStackView", "onCardRewound: position->"+position);
    }

    @Override
    public void onCardCanceled() {
        Log.e("CardStackView", "onCardCanceled: ${manager.topPosition}");
    }

    @Override
    public void onCardAppeared(View view, int position) {
        TextView textView = view.findViewById(R.id.title);
        Log.d("CardStackView", "onCardAppeared: ($position) ${textView.text}");
    }

    @Override
    public void onCardDisappeared(View view, int position) {
        TextView textView = view.findViewById(R.id.id);
        Log.d("CardStackView", "onCardDisappeared: ($position) ${textView.text}");
    }
}
