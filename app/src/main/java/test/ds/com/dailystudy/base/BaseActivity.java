package test.ds.com.dailystudy.base;

import android.os.Bundle;
import android.view.KeyEvent;

import com.zhy.autolayout.AutoLayoutActivity;

import test.ds.com.dailystudy.R;

public class BaseActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_base);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            overridePendingTransition(R.animator.xin_left, R.animator.xout_right);
        }
        return super.onKeyDown(keyCode, event);
    }
}
