package test.ds.com.dailystudy.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.HashMap;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.base.BaseActivity;
import test.ds.com.dailystudy.base.BaseData;
import test.ds.com.dailystudy.bean.Xiangqing;
import test.ds.com.dailystudy.fragment.ItemFragemnt;
import test.ds.com.dailystudy.utils.LogUtils;
import test.ds.com.dailystudy.utils.URLUtils;
import test.ds.com.dailystudy.view.ShowingPage;

public class XiangActivity extends BaseActivity {
    String[] dataArray = new String[]{"详情", "目录", "评论 "};

    private ViewPager viewPager;
    private TabLayout tableLayout;
    private TextView price;
    private ImageView back;
    private TextView name;
    private ImageView xiang_image;
    private Intent intent;
    private String id;
    private Xiangqing xiangqing;
    private Xiangqing.DataBean data1;
    private String course_price;
    private TextView keshi;
    private TextView pingyu;
    private TextView renshu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        //获得ID数据
        intent = getIntent();
        id = intent.getStringExtra("id");
        //查找控件
        initview();
        //数据源
        initdata(id);
        //viewpager滑动
       // xiang_viewpager();
    }

    private void initview() {
        back = (ImageView) findViewById(R.id.login_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        name = (TextView) findViewById(R.id.xiangname);
        price = (TextView) findViewById(R.id.xiangprice);
        xiang_image = (ImageView) findViewById(R.id.xiangimage);
        keshi = (TextView) findViewById(R.id.keshi);
        pingyu = (TextView) findViewById(R.id.ping);
        renshu = (TextView) findViewById(R.id.cou);
    }

    //顶部数据源
    private void initdata(String tid) {
        LogUtils.i("TAG", "----" + URLUtils.homeUrl + URLUtils.Xiangqing + tid);
        HashMap<String, String> map = new HashMap<>();
        map.put("courseid", tid);
        new BaseData() {
            @Override
            public void setResultData(String data) {
                LogUtils.i("TAG", "-------------------" + data + "---------------------------------");
                Gson gson = new Gson();
                xiangqing = gson.fromJson(data, Xiangqing.class);
                data1 = xiangqing.getData();
                //顶部
                top();

            }

            @Override
            protected void setResulttError(ShowingPage.StateType stateLoadError) {
                Toast.makeText(XiangActivity.this, "请求失败", Toast.LENGTH_SHORT).show();

            }
        }.postData(false, false, URLUtils.sort_url, URLUtils.Xiangqing, map, BaseData.NOTIME);
    }

    //顶部
    private void top() {
        Glide.with(XiangActivity.this)
                .load(data1.getCourse_pic())
                .placeholder(null)
                .error(null)
                .into(xiang_image);
        name.setText(data1.getCourse_name());
        course_price = data1.getCourse_price();
        price.setText(course_price.equals("0.00") ? "免费" : "￥" + course_price);
        price.setTextColor(course_price.equals("0.00") ? Color.parseColor("#70b30f") : Color.parseColor("#fca59e"));
        keshi.setText("总课时：" + data1.getCourse_hour());
        pingyu.setText("评分：" + data1.getCourse_tb_category());
        renshu.setText(data1.getCourse_paycount() + "人学");
    }


    //viewpager滑动
    private void xiang_viewpager() {
        for (int i = 0; i < dataArray.length; i++) {
            tableLayout.addTab(tableLayout.newTab().setText(dataArray[i]), false);
        }
        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return ItemFragemnt.getInstance(dataArray[position]);
            }

            @Override
            public int getCount() {
                return dataArray.length;
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tableLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
