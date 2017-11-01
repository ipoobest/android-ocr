package com.poobest.com.mockpeoject.dashboard.fragment;

import android.os.Bundle;
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
import com.facebook.login.LoginManager;
import com.poobest.com.mockpeoject.LoginActivity;
import com.poobest.com.mockpeoject.R;
import com.poobest.com.mockpeoject.adapter.MenuListAdapter;
import com.poobest.com.mockpeoject.model.ItemClickCallback;
import com.poobest.com.mockpeoject.model.dao.DerpData;
import com.poobest.com.mockpeoject.model.dao.MenuListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by j.poobest on 9/24/2017 AD.
 */

public class HomeFragment extends Fragment implements ItemClickCallback, NavigationView.OnNavigationItemSelectedListener {

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
    }

    private void attachSearchViewActivityDrawer(FloatingSearchView searchView) {
        mSearchView.attachNavigationDrawerToMenuButton(mDrawer);
    }

    private void setupDrawerLayout() {
        attachSearchViewActivityDrawer(mSearchView);
    }


    //TODO
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        mDrawer.closeDrawer(GravityCompat.START);
        switch (menuItem.getItemId()) {
            case R.id.logout_facebook:
                LoginManager.getInstance().logOut();
                goLoginScreen();
                return true;
            default:
                return true;
        }
    }

    private void goLoginScreen() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
