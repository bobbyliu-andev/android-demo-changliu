package faith.changliu.androiddemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import faith.changliu.androiddemo.fragments.FragA;
import faith.changliu.androiddemo.fragments.FragB;
import faith.changliu.androiddemo.fragments.FragC;
import faith.changliu.androiddemo.helpers.SimplePageListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        SimplePageListener.PageChangeCallback {

    // member vars
    private ArrayList<Fragment> mFrags;
    private MainActivity.SectionsPagerAdapter mSectionsPagerAdapter;
    private SimplePageListener onPageChangeListener;

    // views
    private Toolbar toolbar;
    private ViewPager mPager;
    private DrawerLayout mDrawer;
    private NavigationView mNavDrawer;
    private BottomNavigationView mNavBottom;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFrags = new ArrayList<>();
        mFrags.add(FragA.newInstance());
        mFrags.add(FragB.newInstance());
        mFrags.add(FragC.newInstance());

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        onPageChangeListener = new SimplePageListener(this);

        // init views
        mPager = findViewById(R.id.mPager);
        mDrawer = findViewById(R.id.drawer_layout);
        mNavDrawer = findViewById(R.id.mNavDrawer);
        mNavBottom = findViewById(R.id.mNavBottom);

        mPager.setAdapter(mSectionsPagerAdapter);
        mPager.addOnPageChangeListener(onPageChangeListener);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavDrawer.setNavigationItemSelectedListener(this);
        mNavBottom.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_1:
                mPager.setCurrentItem(0);
                break;
            case R.id.nav_2:
                mPager.setCurrentItem(1);
                break;
            case R.id.nav_3:
                mPager.setCurrentItem(2);
                break;
                default:
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSelectPage(int position) {
        switch (position) {
            case 0:
                mNavBottom.setSelectedItemId(R.id.nav_1);
                break;
            case 1:
                mNavBottom.setSelectedItemId(R.id.nav_2);
                break;
            case 2:
                mNavBottom.setSelectedItemId(R.id.nav_3);
                break;
                default:
        }
    }

    class SectionsPagerAdapter extends FragmentPagerAdapter {

        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFrags.get(position);
        }

        @Override
        public int getCount() {
            return mFrags.size();
        }
    }
}
