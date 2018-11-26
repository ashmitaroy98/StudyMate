package com.example.ashmita.studymate;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Adding Fragments
        adapter.AddFragment(new FragmentFeed(),"Feed");
        adapter.AddFragment(new FragmentChat(),"Messages");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
