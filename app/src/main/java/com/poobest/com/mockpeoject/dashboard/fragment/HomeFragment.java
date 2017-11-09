package com.poobest.com.mockpeoject.dashboard.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.poobest.com.mockpeoject.LoginActivity;
import com.poobest.com.mockpeoject.R;
import com.poobest.com.mockpeoject.adapter.MenuListAdapter;
import com.poobest.com.mockpeoject.dashboard.DescriptionActivity;
import com.poobest.com.mockpeoject.model.ItemClickCallback;
import com.poobest.com.mockpeoject.model.dao.DerpData;

/**
 * Created by j.poobest on 9/24/2017 AD.
 */

public class HomeFragment extends Fragment
        implements ItemClickCallback, NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    MenuListAdapter adapter;

    FloatingSearchView mSearchView;
    DrawerLayout mDrawer;
    NavigationView navigationView;

    public HomeFragment() {
        super();
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_dashboard, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {

        // set floatingView
        mDrawer = rootView.findViewById(R.id.drawer_layout);
        mSearchView = rootView.findViewById(R.id.floating_search_view);
        navigationView = rootView.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        setupDrawerLayout();
        setupSearchBar();


        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState

        recyclerView = rootView.findViewById(R.id.recycler_view_dashboard);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));


        adapter = new MenuListAdapter(DerpData.getListData());

        recyclerView.setAdapter(adapter);
        adapter.setItemClickCallback(this);

    }

    private void setupSearchBar() {

        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String result) {
                if (result != null){
//                    Toast.makeText(getContext(), ""+result, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), DescriptionActivity.class);
                    intent.putExtra("result", result);
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "please", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
    }

    @Override
    public void onClick(View view, int position) {
        Toast.makeText(getContext(), "Position " + position, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), DescriptionActivity.class);
        intent.putExtra("Position", position);
        startActivity(intent);
    }

    private void attachSearchViewActivityDrawer(FloatingSearchView mSearchView) {
        mSearchView.attachNavigationDrawerToMenuButton(mDrawer);
    }

    private void setupDrawerLayout() {
        attachSearchViewActivityDrawer(mSearchView);
    }


    private void goLoginScreen() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //TODO
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        mDrawer.closeDrawer(GravityCompat.START);

        int id = item.getItemId();

        if (id == R.id.logout_facebook) {
            // Handle the camera action
        }
        return true;

    }
}
