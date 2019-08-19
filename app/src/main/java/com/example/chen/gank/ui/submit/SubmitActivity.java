package com.example.chen.gank.ui.submit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.chen.gank.R;
import com.example.chen.gank.app.Constants;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : chenshuaiyu
 */
public class SubmitActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.spinner)
    AppCompatSpinner mSpinner;
    @BindView(R.id.et_adderss)
    TextInputEditText mAddressEditText;
    @BindView(R.id.et_desc)
    TextInputEditText mDescEditText;
    @BindView(R.id.et_author)
    TextInputEditText mAuthorEditText;

    private ArrayAdapter mAdapter;

    private String address;
    private String desc;
    private String author;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("提交干货");
        actionBar.setDisplayHomeAsUpEnabled(true);

        mAdapter = new ArrayAdapter(getApplicationContext(), R.layout.item_text, Constants.FILTER_TYPE);
        mSpinner.setAdapter(mAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = Constants.FILTER_TYPE[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getData() {
        address = mAddressEditText.getText().toString();
        desc = mDescEditText.getText().toString();
        author = mAddressEditText.getText().toString();
    }

    public void submit() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_submit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.submit:
                submit();
                break;
            default:
                break;
        }
        return true;
    }
}
