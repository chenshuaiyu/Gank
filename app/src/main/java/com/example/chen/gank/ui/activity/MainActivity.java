package com.example.chen.gank.ui.activity;

import android.os.Bundle;

import com.example.chen.gank.R;
import com.example.chen.gank.ui.filter.FilterFragment;
import com.example.chen.gank.ui.latest.LatestFragment;
import com.example.chen.gank.ui.meizhi.MeiZhiFragment;
import com.example.chen.gank.utils.BNVUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;
    private Toolbar mToolbar;

    private Fragment mCurFragment;
    private LatestFragment mLatestFragment;
    private FilterFragment mFilterFragment;
    private MeiZhiFragment mMeiZhiFragment;
    private FragmentManager mFragmentManager;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        switch (item.getItemId()) {
            case R.id.navigation_latest:
                if (mCurFragment != mLatestFragment) {
                    transaction
                            .hide(mCurFragment)
                            .show(mLatestFragment);
                    mCurFragment = mLatestFragment;
                }
                break;
            case R.id.navigation_filter:
                if (mCurFragment != mFilterFragment) {
                    transaction
                            .hide(mCurFragment)
                            .show(mFilterFragment);
                    mCurFragment = mFilterFragment;
                }
                break;
            case R.id.navigation_meizhi:
                if (mCurFragment != mMeiZhiFragment) {
                    transaction
                            .hide(mCurFragment)
                            .show(mMeiZhiFragment);
                    mCurFragment = mMeiZhiFragment;
                }
                break;
            case R.id.navigation_collections:
                break;
            default:
                break;
        }
        transaction.commit();
        return true;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mToolbar = findViewById(R.id.toolbar);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);

        BNVUtils.disableShiftMode(mBottomNavigationView);

        mLatestFragment = new LatestFragment();
        mFilterFragment = new FilterFragment();
        mMeiZhiFragment = new MeiZhiFragment();
        mCurFragment = mLatestFragment;
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.fragment_container, mLatestFragment)
                .add(R.id.fragment_container, mFilterFragment)
                .add(R.id.fragment_container, mMeiZhiFragment)
                .hide(mLatestFragment)
                .hide(mFilterFragment)
                .hide(mMeiZhiFragment)
                .show(mCurFragment)
                .commit();


//        MainViewModel mainViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(MainViewModel.class);
//        mainViewModel.getGankDailyResults()
//                .observe(this, gankDailyResult -> {
//                            List<Gank> welfare = gankDailyResult.getResults().getWelfare();
//                            for (Gank g : welfare) {
//                                Log.d("CCC", g.getUrl());
//                            }
//                        }
//                );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                break;
            case R.id.date:
                mLatestFragment.onOptionsItemSelected(item);
                break;
            case R.id.commit:
                mFilterFragment.onOptionsItemSelected(item);
                break;
            default:
                break;
        }
        return true;
    }
}