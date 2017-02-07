package test.ds.com.dailystudy.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.base.BaseActivity;
import test.ds.com.dailystudy.bean.LoginBean;
import test.ds.com.dailystudy.manager.HttpManger;
import test.ds.com.dailystudy.utils.LogUtils;
import test.ds.com.dailystudy.utils.SharedPreferencesUtils;
import test.ds.com.dailystudy.utils.URLUtils;

public class MineLoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private Button zhuce;
    private EditText login_pwd;
    private ImageView qq;
    private EditText user_phone;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_login);
        back = (ImageView) findViewById(R.id.login_back);
        zhuce = (Button) findViewById(R.id.login_zhuce);
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        //设置密码为不可见
        login_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        qq = (ImageView) findViewById(R.id.login_qq);
        user_phone = (EditText) findViewById(R.id.user_phone);
        login = (Button) findViewById(R.id.login);
        back.setOnClickListener(this);
        zhuce.setOnClickListener(this);
        login.setOnClickListener(this);
        qq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.login_zhuce:
                intentclass(MineLoginActivity.this, ZhuceActivity.class);
                break;
            case R.id.login_qq:
                UMShareAPI mShareAPI = UMShareAPI.get(MineLoginActivity.this);
                mShareAPI.getPlatformInfo(MineLoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.login:
                final String login_pwd = this.login_pwd.getText().toString().trim();
                String user_phone = this.user_phone.getText().toString().trim();
                if (!TextUtils.isEmpty(login_pwd) && !TextUtils.isEmpty(user_phone)) {
                    Map<String, String> map = new HashMap<>();
                    map.put("userName", user_phone);
                    map.put("password", login_pwd);
                    map.put("dosubmit", "1");
                    HttpManger.postMethod(false, true, URLUtils.sort_url, URLUtils.signIn, map, new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String body = response.body();
                            Gson gson = new Gson();
                            LoginBean loginBean = gson.fromJson(body, LoginBean.class);
                            if (loginBean.getStatus() == 200) {
                                String user_name = loginBean.getData().getUser_name();
                                String user_big_log = loginBean.getData().getUser_big_log();
                                LogUtils.i("TAG", "得到的姓名" + user_name);
                                LogUtils.i("TAG", "得到的头像" + user_big_log);
                                SharedPreferencesUtils.saveString(MineLoginActivity.this, "name", user_name);
                                SharedPreferencesUtils.saveString(MineLoginActivity.this, "icon", user_big_log);
                                MineLoginActivity.this.finish();
                            } else {
                                Toast.makeText(MineLoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                } else {
                    Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void intentclass(Context context, Class v) {
        Intent intent = new Intent(context, v);
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
            SharedPreferencesUtils.saveString(MineLoginActivity.this, "name", name);
            SharedPreferencesUtils.saveString(MineLoginActivity.this, "icon", icon);
            MineLoginActivity.this.finish();


        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(MineLoginActivity.this, "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MineLoginActivity.this, "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };
}
