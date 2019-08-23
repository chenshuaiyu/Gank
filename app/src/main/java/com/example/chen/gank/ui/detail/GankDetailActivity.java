package com.example.chen.gank.ui.detail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.chen.gank.app.Constants;
import com.example.chen.gank.R;
import com.example.chen.gank.app.Inject;
import com.example.chen.gank.data.bean.Gank;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : chenshuaiyu
 */
public class GankDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.frame_container)
    FrameLayout mFrameLayout;

    private MenuItem mCollectItem;
    private MenuItem mUnCollectItem;

    private AgentWeb mAgentWeb;
    private Gank mGank;

    private GankDetailViewModel mViewModel;

    public static Intent newIntent(Context context, Gank gank) {
        Intent intent = new Intent(context, GankDetailActivity.class);
        intent.putExtra(Constants.GANK, gank);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_detail);
        ButterKnife.bind(this);

        mGank = (Gank) getIntent().getSerializableExtra(Constants.GANK);

        mViewModel = ViewModelProviders.of(this, Inject.getModelFactory()).get(GankDetailViewModel.class);

        mToolbar.setTitle(mGank.getDesc());
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mFrameLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(ContextCompat.getColor(this, R.color.colorPrimary))
                .setMainFrameErrorView(R.layout.error_view, -1)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .createAgentWeb()
                .ready()
                .go(mGank.getUrl());

        WebView mWebView = mAgentWeb.getWebCreator().getWebView();
        WebSettings mSettings = mWebView.getSettings();

        mSettings.setBlockNetworkImage(false);

        mSettings.setAppCacheEnabled(true);
        mSettings.setDatabaseEnabled(true);
        //若有缓存，使用缓存，否则从网络获取
        mSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        //支持缩放
        mSettings.setJavaScriptEnabled(true);
        mSettings.setSupportZoom(true);
        mSettings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        mSettings.setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        mSettings.setUseWideViewPort(true);
        //缩放至屏幕大小
        mSettings.setLoadWithOverviewMode(true);
        //自适应屏幕
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        mCollectItem = menu.findItem(R.id.collect);
        mUnCollectItem = menu.findItem(R.id.uncollect);
        mViewModel.isCollected(mGank)
                .observe(this, gank -> {
                    if (gank == null) {
                        mCollectItem.setVisible(true);
                        mUnCollectItem.setVisible(false);
                    } else {
                        mCollectItem.setVisible(false);
                        mUnCollectItem.setVisible(true);
                    }
                });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.collect:
                mViewModel.collect(mGank);
                Toast.makeText(this, R.string.collected, Toast.LENGTH_SHORT).show();
                break;
            case R.id.uncollect:
                mViewModel.cancelCollect(mGank);
                Toast.makeText(this, R.string.uncollected, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
