package com.example.chen.gank.ui.meizhi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.chen.gank.R;

public class MeiZhiActivity extends AppCompatActivity {
    private ImageView image;

    public static Intent newIntent(Context context, String url) {
        Intent intent = new Intent(context, MeiZhiActivity.class);
        intent.putExtra("url", url);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizhi);
        image = findViewById(R.id.image);

        String url = getIntent().getStringExtra("url");
        Glide.with(this).load(url).into(image);
        image.setOnClickListener(v -> finish());
    }
}
