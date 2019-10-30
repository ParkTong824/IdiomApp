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
import com.example.idiom.model.Idiom;

import java.util.ArrayList;
import java.util.List;

/*
苛斂誅求 가렴주구
 가혹하게 세금을 거두거나 백성들의 재물을 억지로 빼앗음.

肝膽相照 간담상조
 서로 간과 쓸개를 보일 정도로 상호간에 격의 없이 지내는 사이

康衢煙月 강구연월
 태평성대의 평화스러운 풍경.

居安思危 거안사위
 편안한 때에 앞으로 닥칠 위태로움을 생각함.

隔靴搔癢 격화소양
 신을 신은 채 가려운 발바닥을 긁음과 같이 일의 효과를 나타내지 못함.

牽强附會 견강부회
 이치에 맞지 않은 말을 억지로 끌어 붙여 자기 주장의 조건을 맞도록 함.

結草報恩 결초보은
 풀을 엮어서 은혜를 갚는다는 뜻으로, 죽어서도 잊지 않고 은혜를 갚는다는 뜻.

鼓腹擊壤 고복경양
 배를 두드리고 발을 구르며 흥겨워한다는 뜻으로, 태평성대를 말함.

過猶不及 과유불급
 정도를 지나침은 미치지 못한 것과 같다는 말.*/
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

        //가짜데이터 삽입 테스트용
        List<Idiom> dummyData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dummyData.add(new Idiom("苛斂誅求"+i , "가혹하게 세금을 거두거나 백성들의 재물을 억지로 빼앗음.","가렴주구"));
        }

        RecyclerView recyclerView = view.findViewById(R.id.study_recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        StudyRecyclerViewAdapter adapter = new StudyRecyclerViewAdapter(new StudyRecyclerViewAdapter.ItemOnClickListener() {
            @Override
            public void itemOnClick(Idiom idiom) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("idi", idiom);
                showDetailFragment.setArguments(bundle);
                requireActivity().getSupportFragmentManager().beginTransaction().add(R.id.container_main, showDetailFragment).addToBackStack(null).commit();
            }
        });

        recyclerView.setAdapter(adapter);
        adapter.setItems(dummyData);
    }
}
