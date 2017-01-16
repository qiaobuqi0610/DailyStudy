package test.ds.com.dailystudy.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.base.BaseActivity;
import test.ds.com.dailystudy.utils.SharedPreferencesUtils;

public class GeRenActivity extends BaseActivity {

    private ImageView imageView;
    private TextView textView;
    private String icon;
    private String name;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge_ren);
        imageView = (ImageView) findViewById(R.id.xiang);
        textView = (TextView) findViewById(R.id.ge_name);
        Intent intent=getIntent();
        icon = intent.getStringExtra("icon");
        name = intent.getStringExtra("name");
        textView.setText(name);
        Glide.with(GeRenActivity.this)
                .load(icon)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

    public void back(View view) {
        finish();
    }

    public void tuichu(View view) {

        SharedPreferencesUtils.clearString(GeRenActivity.this,"name",null);
        SharedPreferencesUtils.clearString(GeRenActivity.this,"icon",null);
        Toast.makeText(GeRenActivity.this, "退出登录", Toast.LENGTH_SHORT).show();
        GeRenActivity.this.finish();
    }


}
