package com.example.chen.gank.ui;

import android.os.Bundle;

import com.example.chen.gank.Inject;
import com.example.chen.gank.R;
import com.example.chen.gank.data.bean.Gank;
import com.example.chen.gank.data.bean.GankDailyResult;
import com.example.chen.gank.utils.BNVUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView mBottomNavigationView;
    private Toolbar mToolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_latest:
                break;
            case R.id.navigation_filter:
                break;
            case R.id.navigation_meizhi:
                break;
            case R.id.navigation_collections:
                break;
            default:
                break;
        }
        return true;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mToolbar = findViewById(R.id.toolbar);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);

        BNVUtils.disableShiftMode(mBottomNavigationView);

        MainViewModel mainViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(MainViewModel.class);
        mainViewModel.getGankDailyResults()
                .observe(this, gankDailyResult -> {
                            List<Gank> welfare = gankDailyResult.getResults().getWelfare();
                            for (Gank g : welfare) {
                                Log.d("CCC", g.getUrl());
                            }
                        }
                );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                break;
            default:
                break;
        }
        return true;
    }
}
