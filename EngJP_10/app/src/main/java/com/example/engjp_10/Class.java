package com.example.engjp_10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Class#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Class extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Class() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Class.
     */
    // TODO: Rename and change types and number of parameters
    public static Class newInstance(String param1, String param2) {
        Class fragment = new Class();
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

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private View mView; // anh xa view

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_class, container, false);

        //thuc hien anh xa view
        tabLayout = mView.findViewById(R.id.tab_layout);
        viewPager2 = mView.findViewById(R.id.class_viewpager);

        classViewPagerAdapter classViewPagerAdap = new classViewPagerAdapter(this);
        viewPager2.setAdapter(classViewPagerAdap);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            // Set tab titles based on the position
            switch (position) {
                case 0:
                    tab.setText("Course");
                    break;
                case 1:
                    tab.setText("Speaking");
                    break;
                case 2:
                    tab.setText("Listening");
                    break;
            }
        }).attach();


        return mView;
    }
}