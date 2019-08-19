package com.example.chen.gank.ui.main;

import android.content.Intent;
import android.os.Bundle;

import com.example.chen.gank.R;
import com.example.chen.gank.app.Inject;
import com.example.chen.gank.ui.collect.CollectFragment;
import com.example.chen.gank.ui.filter.FilterFragment;
import com.example.chen.gank.ui.latest.LatestFragment;
import com.example.chen.gank.ui.meizhi.MeiZhiFragment;
import com.example.chen.gank.ui.search.SearchActivity;
import com.example.chen.gank.utils.BNVUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : chenshuaiyu
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigationView;

    private MainViewModel mViewModel;

    private Fragment mCurFragment;
    private LatestFragment mLatestFragment = new LatestFragment();
    private FilterFragment mFilterFragment = new FilterFragment();
    private MeiZhiFragment mMeiZhiFragment = new MeiZhiFragment();
    private CollectFragment mCollectFragment = new CollectFragment();
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
                    mToolbar.setTitle(mLatestFragment.getCurrentTitle());
                }
                break;
            case R.id.navigation_filter:
                if (mCurFragment != mFilterFragment) {
                    transaction
                            .hide(mCurFragment)
                            .show(mFilterFragment);
                    mCurFragment = mFilterFragment;
                    mToolbar.setTitle(R.string.filter);
                }
                break;
            case R.id.navigation_meizhi:
                if (mCurFragment != mMeiZhiFragment) {
                    transaction
                            .hide(mCurFragment)
                            .show(mMeiZhiFragment);
                    mCurFragment = mMeiZhiFragment;
                    mToolbar.setTitle(R.string.meizhi);
                }
                break;
            case R.id.navigation_collections:
                if (mCurFragment != mCollectFragment) {
                    transaction
                            .hide(mCurFragment)
                            .show(mCollectFragment);
                    mCurFragment = mCollectFragment;
                    mToolbar.setTitle(R.string.collect);
                }
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
        ButterKnife.bind(this);

        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(MainViewModel.class);

        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);

        BNVUtils.disableShiftMode(mBottomNavigationView);

        mCurFragment = mLatestFragment;
        mFragmentManager = getSupportFragmentManager();
        Fragment fragment = mFragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            mFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, mLatestFragment)
                    .add(R.id.fragment_container, mFilterFragment)
                    .add(R.id.fragment_container, mMeiZhiFragment)
                    .add(R.id.fragment_container, mCollectFragment)
                    .hide(mLatestFragment)
                    .hide(mFilterFragment)
                    .hide(mMeiZhiFragment)
                    .hide(mCollectFragment)
                    .show(mCurFragment)
                    .commit();
        }

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
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.date:
                mLatestFragment.onOptionsItemSelected(item);
                break;
            case R.id.commit:
                mFilterFragment.onOptionsItemSelected(item);
                break;
            case R.id.change_column:
                mMeiZhiFragment.onOptionsItemSelected(item);
                break;
            default:
                break;
        }
        return true;
    }
}
