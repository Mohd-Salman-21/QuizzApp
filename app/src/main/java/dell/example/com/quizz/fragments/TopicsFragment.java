package dell.example.com.quizz.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dell.example.com.quizz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopicsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopicsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public TopicsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopicsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopicsFragment newInstance(String param1, String param2) {
        TopicsFragment fragment = new TopicsFragment();
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
        return inflater.inflate(R.layout.fragment_topics, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Activity context=getActivity();
        TabLayout tabLayout=context.findViewById(R.id.tabLayout);
        ViewPager viewPager=context.findViewById(R.id.viewPager);

        CustomPagerAdapter customPagerAdapter=new CustomPagerAdapter(getChildFragmentManager());
        customPagerAdapter.addFragments(new PopularTopicsFragment(),"Popular Topics");
        customPagerAdapter.addFragments(new AllTopicsFragment(),"All Topics");


        viewPager.setAdapter(customPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }

    public class CustomPagerAdapter extends FragmentPagerAdapter{

        public ArrayList<Fragment> mFragments=new ArrayList<>();
        public ArrayList<String> mFragmentsTitle=new ArrayList<>();

        public void addFragments(Fragment fragment,String title)
        {
            mFragments.add(fragment);
            mFragmentsTitle.add(title);
        }

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentsTitle.get(position);

        }
    }

}
