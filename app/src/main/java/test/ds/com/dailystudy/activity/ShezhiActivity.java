package test.ds.com.dailystudy.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoRelativeLayout;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.base.BaseActivity;
import test.ds.com.dailystudy.utils.DataCleanManager;

public class ShezhiActivity extends BaseActivity implements View.OnClickListener {

    private AutoRelativeLayout autoRelativeLayout;
    private TextView shuju;
    private String totalCacheSize;
    private ImageView back;
    private AutoRelativeLayout gengxin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);
        initview();
        try {
            totalCacheSize = DataCleanManager.getTotalCacheSize(ShezhiActivity.this);
            shuju.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initview() {
        autoRelativeLayout = (AutoRelativeLayout) findViewById(R.id.huancun);
        autoRelativeLayout.setOnClickListener(this);
        gengxin = (AutoRelativeLayout) findViewById(R.id.gengxin);
        gengxin.setOnClickListener(this);
        shuju = (TextView) findViewById(R.id.huancunshuju);
        back = (ImageView) findViewById(R.id.login_back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.huancun:
                AlertDialog.Builder builder = new AlertDialog.Builder(ShezhiActivity.this);
                builder.setMessage("是否清除缓存？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        /*DataCleanManager.cleanInternalCache(ShezhiActivity.this);
                        DataCleanManager.cleanDatabases(ShezhiActivity.this);
                        DataCleanManager.cleanExternalCache(ShezhiActivity.this);
                        DataCleanManager.cleanFiles(ShezhiActivity.this);
                        DataCleanManager.cleanSharedPreference(ShezhiActivity.this);*/
                        Toast.makeText(ShezhiActivity.this, "成功清除" + totalCacheSize, Toast.LENGTH_SHORT).show();
                        shuju.setText("0.00");
                       /* if (totalCacheSize=="0.00") {

                            Toast.makeText(ShezhiActivity.this, "木有缓存啦！" , Toast.LENGTH_SHORT).show();
                        }*/


                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
                break;
            case R.id.login_back:
                finish();
                break;
            case R.id.gengxin:
                Toast.makeText(ShezhiActivity.this, "当前已是最新版本" , Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
