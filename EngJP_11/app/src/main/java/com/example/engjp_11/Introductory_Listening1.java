package com.example.engjp_11;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Introductory_Listening1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Introductory_Listening1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Introductory_Listening1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Introductory_Listening1.
     */
    // TODO: Rename and change types and number of parameters
    public static Introductory_Listening1 newInstance(String param1, String param2) {
        Introductory_Listening1 fragment = new Introductory_Listening1();
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
        View view = inflater.inflate(R.layout.fragment_introductory_listening1, container, false);

        // Tìm ImageView theo ID
        ImageView btnPre = view.findViewById(R.id.img_vw_intro_listening_pre);

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

        return view;
    }
}