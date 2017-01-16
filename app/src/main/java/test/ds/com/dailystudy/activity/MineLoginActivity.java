package test.ds.com.dailystudy.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.base.BaseActivity;
import test.ds.com.dailystudy.utils.SharedPreferencesUtils;

public class MineLoginActivity extends BaseActivity implements View.OnClickListener{

    private ImageView back;
    private Button zhuce;
    private EditText login_pwd;
    private ImageView qq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_login);
        back = (ImageView) findViewById(R.id.login_back);
        back.setOnClickListener(this);
        zhuce = (Button) findViewById(R.id.login_zhuce);
        zhuce.setOnClickListener(this);
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        login_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        qq = (ImageView) findViewById(R.id.login_qq);
        qq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login_back:
                finish();
                break;
            case R.id.login_zhuce:
               intentclass(MineLoginActivity.this,ZhuceActivity.class);
                break;
            case R.id.login_qq:
                UMShareAPI mShareAPI = UMShareAPI.get( MineLoginActivity.this );
                mShareAPI.getPlatformInfo(MineLoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
                break;
        }
    }
    public void intentclass(Context context, Class v)
    {
        Intent intent=new Intent(context,v);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            String name = data.get("screen_name");
            String icon = data.get("profile_image_url");
            Toast.makeText(MineLoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            //保存数据
            SharedPreferencesUtils.saveString(MineLoginActivity.this,"name",name);
            SharedPreferencesUtils.saveString(MineLoginActivity.this,"icon",icon);
            MineLoginActivity.this.finish();


        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( MineLoginActivity.this, "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( MineLoginActivity.this, "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };
}
