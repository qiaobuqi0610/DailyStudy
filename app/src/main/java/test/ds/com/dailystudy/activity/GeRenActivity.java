package test.ds.com.dailystudy.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.File;

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
    /**
     * 定义三种状态
     */
    private static final int REQUESTCODE_PIC = 1;//相册
    private static final int REQUESTCODE_CAM = 2;//相机
    private static final int REQUESTCODE_CUT = 3;//图片裁剪
    private Bitmap mBitmap;
    private File mFile;
    private AutoLinearLayout layout_all;
    private RelativeLayout layout_choose;
    private RelativeLayout layout_photo;
    private RelativeLayout layout_close;
    private int mScreenWidth;
    private PopupWindow avatorPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge_ren);
        imageView = (ImageView) findViewById(R.id.xiang);
        textView = (TextView) findViewById(R.id.ge_name);
        layout_all = (AutoLinearLayout) findViewById(R.id.activity_ge_ren);
        Intent intent = getIntent();
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

        SharedPreferencesUtils.clearString(GeRenActivity.this, "name", null);
        SharedPreferencesUtils.clearString(GeRenActivity.this, "icon", null);
        Toast.makeText(GeRenActivity.this, "退出登录", Toast.LENGTH_SHORT).show();
        GeRenActivity.this.finish();
    }


    public void popwindow(View view) {
        //POPWINDOW的方法
        showMyDialog();

    }

    //POPWINDOW的方法
    private void showMyDialog() {
        View view1 = LayoutInflater.from(this).inflate(R.layout.pop_show_dialog, null);
        layout_choose = (RelativeLayout) view1.findViewById(R.id.layout_choose);
        layout_photo = (RelativeLayout) view1.findViewById(R.id.layout_photo);
        layout_close = (RelativeLayout) view1.findViewById(R.id.layout_close);

        layout_choose.setBackgroundColor(getResources().getColor(
                R.color.base_color_text_white));
        layout_photo.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.pop_bg_press));
        layout_close.setBackgroundColor(getResources().getColor(
                R.color.base_color_text_white));

        layout_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout_choose.setBackgroundColor(getResources().getColor(
                        R.color.base_color_text_white));
                layout_photo.setBackgroundDrawable(getResources().getDrawable(
                        R.drawable.pop_bg_press));
                layout_close.setBackgroundColor(getResources().getColor(
                        R.color.base_color_text_white));

                openCamera();
            }
        });
        layout_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout_photo.setBackgroundColor(getResources().getColor(
                        R.color.base_color_text_white));
                layout_choose.setBackgroundDrawable(getResources().getDrawable(
                        R.drawable.pop_bg_press));
                layout_close.setBackgroundColor(getResources().getColor(
                        R.color.base_color_text_white));
                openPic();
            }
        });
        layout_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout_photo.setBackgroundColor(getResources().getColor(
                        R.color.base_color_text_white));
                layout_close.setBackgroundDrawable(getResources().getDrawable(
                        R.drawable.pop_bg_press));
                layout_choose.setBackgroundColor(getResources().getColor(
                        R.color.base_color_text_white));
                avatorPop.dismiss();
            }
        });
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        mScreenWidth = metric.widthPixels;
        avatorPop = new PopupWindow(view1, mScreenWidth, 200);
        avatorPop.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    avatorPop.dismiss();
                    return true;
                }
                return false;
            }
        });
        avatorPop.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        avatorPop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        avatorPop.setTouchable(true);
        avatorPop.setFocusable(true);
        avatorPop.setOutsideTouchable(true);
        avatorPop.setBackgroundDrawable(new BitmapDrawable());
        // 动画效果 从底部弹起
        avatorPop.setAnimationStyle(R.style.Animations_GrowFromBottom);
        avatorPop.showAtLocation(layout_all, Gravity.BOTTOM, 0, 0);

    }

    //打开相册
    private void openPic() {
        Intent picIntent = new Intent(Intent.ACTION_PICK, null);
        picIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(picIntent, REQUESTCODE_PIC);
    }

    // 打开照相机
    private void openCamera() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (!file.exists()) {
                file.mkdirs();
            }
            mFile = new File(file, System.currentTimeMillis() + ".jpg");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFile));
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(intent, REQUESTCODE_CAM);
        } else {
            Toast.makeText(this, "请确认已经插入SD卡", Toast.LENGTH_SHORT).show();
        }
    }
    //回调数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUESTCODE_CAM:
                    startPhotoZoom(Uri.fromFile(mFile));
                    break;
                case REQUESTCODE_PIC:

                    if (data == null || data.getData() == null) {
                        return;
                    }
                    startPhotoZoom(data.getData());

                    break;
                case REQUESTCODE_CUT:

                    if (data != null) {
                        setPicToView(data);
                    }
                    break;
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
    private void setPicToView(Intent data) {
        Bundle bundle =  data.getExtras();
        if (bundle != null){
            //这里也可以做文件上传
            mBitmap = bundle.getParcelable("data");
            imageView.setImageBitmap(mBitmap);
        }
    }

    /**
     * 打开系统图片裁剪功能
     * @param uri
     */
    private void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("crop",true);
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        intent.putExtra("outputX",300);
        intent.putExtra("outputY",300);
        intent.putExtra("scale",true); //黑边
        intent.putExtra("scaleUpIfNeeded",true); //黑边
        intent.putExtra("return-data",true);
        intent.putExtra("noFaceDetection",true);
        startActivityForResult(intent,REQUESTCODE_CUT);

    }
}
