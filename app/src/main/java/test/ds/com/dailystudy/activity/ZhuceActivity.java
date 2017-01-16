package test.ds.com.dailystudy.activity;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.base.BaseActivity;

public class ZhuceActivity extends BaseActivity implements View.OnClickListener {

    private ImageView zhuce_back;
    private EditText zhuce_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        zhuce_back = (ImageView) findViewById(R.id.zhuce_back);
        zhuce_back.setOnClickListener(this);
        zhuce_pwd = (EditText) findViewById(R.id.zhuce_pwd);
        zhuce_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @Override
    public void onClick(View v) {
           switch (v.getId())
           {
               case R.id.zhuce_back:
                   finish();
                   break;
           }
    }
}
