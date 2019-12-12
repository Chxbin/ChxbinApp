package com.example.chxbinapp.View;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.chxbinapp.Controller.MainController;
import com.example.chxbinapp.Model.AllSport;
import com.example.chxbinapp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<AllSport> datafromA;


    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);
        swipeRefresh = findViewById(R.id.swiperefresh);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        doYourUpdate();
                    }
                }
        );

        controller = new MainController(this);
        controller.onStart();


    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager, List<AllSport> input) {
        Bundle data  = new Bundle();
        data.putString("key", new Gson().toJson(input));
        Adapter adapter = new Adapter(getSupportFragmentManager());
        ListContentFragHome fragHome = new ListContentFragHome();
        fragHome.setArguments(data);
        ListContentFragStats fragStats = new ListContentFragStats();
        fragStats.setArguments(data);
        ListContentFragAbout fragAbout = new ListContentFragAbout();
        fragAbout.setArguments(data);

        adapter.addFragment(fragHome,"list");
        viewPager.setAdapter(adapter);
        /*adapter.addFragment(fragStats,"Stats");
        viewPager.setAdapter(adapter);*/
        adapter.addFragment(fragAbout,"About");
        viewPager.setAdapter(adapter);

    }

    public void afficheA(){

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager, datafromA);
        // Set Tabs inside Toolbar

        tabs.setupWithViewPager(viewPager);

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void doYourUpdate() {
        // TODO implement a refresh
        swipeRefresh.setRefreshing(false);
    }

    public void showList(List<AllSport> input) {

        datafromA = input;
        afficheA();

    }
}