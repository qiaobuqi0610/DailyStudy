package test.ds.com.dailystudy.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.base.BaseActivity;
import test.ds.com.dailystudy.fragment.MuLuFragment;
import test.ds.com.dailystudy.fragment.PlFragment;
import test.ds.com.dailystudy.fragment.XqFragment;

public class Activity_Xq extends BaseActivity {

    private ImageView imgaeView__play;
    private TextView imgaeView__title;
    private TextView imgaeView__price;
    private TextView tv_course_detail_totaltime;
    private TextView tv_course_detail_grade;
    private TextView tv_course_detail_person_num;
    private ViewPager viewPager;
    private String id;
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__xq);
        //找到控件
        initView();
        Intent in = getIntent();
        String image = in.getStringExtra("image");
        String name = in.getStringExtra("name");
        String price = in.getStringExtra("price");
        id = in.getStringExtra("id");
        int zaixian = in.getIntExtra("zaixian", 0);
        Glide.with(Activity_Xq.this).load(image).into(imgaeView__play);
        imgaeView__title.setText(name);
        imgaeView__price.setText(price + "");
        if (price.equals("0.00")) {
            imgaeView__price.setText("免费");
        }
        tv_course_detail_person_num.setText(zaixian + "人学");
        tv_course_detail_grade.setText("评分:" + zaixian);
        tv_course_detail_totaltime.setText("总课时:15");
        for (int y = 0; y < rg.getChildCount(); y++) {
            RadioButton childAt = (RadioButton) rg.getChildAt(y);
            //获取RaidoButton对应页面的位置
            if (y == 0) {
                childAt.setTextColor(Color.RED);
            } else {
                childAt.setTextColor(Color.BLACK);
            }
        }
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                for (int y = 0; y < rg.getChildCount(); y++) {
                    //获取RaidoButton对应页面的位置
                    if (radioGroup.getChildAt(y).getId() == i) {
                      viewPager.setCurrentItem(y);
                    }
                }
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int y = 0; y < rg.getChildCount(); y++) {
                    RadioButton childAt = (RadioButton) rg.getChildAt(y);
                    //获取RaidoButton对应页面的位置
                    if (y == position) {
                        childAt.setTextColor(Color.RED);
                    } else {
                        childAt.setTextColor(Color.BLACK);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new XqFragment().chuanzhi(id);
                        break;
                    case 1:
                        fragment = new MuLuFragment().chuanzhi(id);
                        break;
                    case 2:
                        fragment = new PlFragment();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
    }

    private void initView() {
        imgaeView__play = (ImageView) findViewById(R.id.iv_course_detail_play);
        imgaeView__title = (TextView) findViewById(R.id.tv_course_detail_title);
        imgaeView__price = (TextView) findViewById(R.id.textview_course_detail_free);
        tv_course_detail_totaltime = (TextView) findViewById(R.id.tv_course_detail_totaltime);
        tv_course_detail_grade = (TextView) findViewById(R.id.tv_course_detail_grade);
        tv_course_detail_person_num = (TextView) findViewById(R.id.tv_course_detail_person_num);
        viewPager = (ViewPager) findViewById(R.id.vp_course_detail);
        rg = (RadioGroup) findViewById(R.id.rg_course_detail);

    }
}
