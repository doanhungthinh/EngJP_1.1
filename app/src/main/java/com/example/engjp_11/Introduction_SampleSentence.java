package com.example.engjp_11;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Introduction_SampleSentence#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Introduction_SampleSentence extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Introduction_SampleSentence() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Introduction_SampleSentence.
     */
    // TODO: Rename and change types and number of parameters
    public static Introduction_SampleSentence newInstance(String param1, String param2) {
        Introduction_SampleSentence fragment = new Introduction_SampleSentence();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_introduction_sample_sentence, container, false);

        // Tìm ImageView theo ID
        ImageView btnPre = view.findViewById(R.id.img_vw_intro_sentence_pre);

        // Thêm sự kiện OnClickListener cho ImageView
        btnPre.setOnClickListener(v -> {
            // Sử dụng FragmentManager để pop fragment hiện tại ra khỏi backstack
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack(); // Quay lại fragment trước đó
            } else {
                getActivity().getOnBackPressedDispatcher(); // Đóng fragment hoặc thoát nếu không còn fragment nào khác
            }
        });
        // Tìm RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Kết nối Firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Sentences");

        List<SentenceItem> sentenceList = new ArrayList<>();
        SentenceAdapter adapter = new SentenceAdapter(getContext(), sentenceList);
        recyclerView.setAdapter(adapter);

        // Lấy dữ liệu từ Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sentenceList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    SentenceItem sentenceItem = snapshot.getValue(SentenceItem.class);
                    sentenceList.add(sentenceItem);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi
            }
        });

        return view;
    }
}