package sample.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sample.adapter.CustomPagerAdapter;
import sample.dev.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.HomeListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private HomeListener mListener;

    CollapsingToolbarLayout collapsingToolbarLayout;

    private CustomPagerAdapter mCustomPagerAdapter;
    private ViewPager mViewPager;

    int[] mResources = {
            R.drawable.banner_2018,
            R.drawable.pec_241,
            R.drawable.brazil,
            R.drawable.pec_241,
            R.drawable.banner_2018,
            R.drawable.brazil
    };


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configViewPager(view);
        configAppBarLayout(view);
        configCollapsingLayout(view);
    }

    private void configViewPager(View view) {
        mCustomPagerAdapter = new CustomPagerAdapter(getContext(), mResources);
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
    }

    private void configCollapsingLayout(View view) {
        collapsingToolbarLayout = view.findViewById(R.id.toolbar_layout);
//        setCollapsingBarTitle("Home");
//        collapsingToolbarLayout.setTitle("Home");
    }

    private void configAppBarLayout(View view) {
        AppBarLayout mAppBarLayout = view.findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            private boolean isShow = false;
            private int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    onBarColapsed();

                } else if (isShow) {
                    isShow = false;
                    onBarExpanded();
                }
            }
        });
    }

    private void onBarColapsed() {
        mListener.showOption(R.id.action_refresh);
//        setCollapsingBarTitle("Views Tour");
//        collapsingToolbarLayout.setVisibility(View.GONE);
        setCollapsingBarTitle("Feed de Noticias");
    }

    private void onBarExpanded() {
        mListener.hideOption(R.id.action_refresh);
//        setCollapsingBarTitle("Home");
        setCollapsingBarTitle("");
//        collapsingToolbarLayout.setVisibility(View.VISIBLE);
    }

    private void setCollapsingBarTitle(String title) {
        collapsingToolbarLayout.setTitle(title);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeListener) {
            mListener = (HomeListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement LegalTermsFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface HomeListener {
        void onHomeButtonPressed(int button);
        void showOption(int resId);
        void hideOption(int resId);
    }
}
