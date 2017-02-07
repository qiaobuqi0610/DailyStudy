package test.ds.com.dailystudy.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.base.BaseActivity;
import test.ds.com.dailystudy.bean.BeanTopic;
import test.ds.com.dailystudy.fragment.ContentFragment;
import test.ds.com.dailystudy.view.GlideUtils;

public class ContentMessageAcitivity extends BaseActivity implements View.OnClickListener {

    private ImageView topback_iv;
    private ArrayList<BeanTopic.DataBean.CircleBean> circleList;
    private String id;
    private ImageView topUser_iv;
    private TextView topTitle;
    private TextView topBrief;
    private TextView userCount;
    private TextView topPostCount;
    private TabLayout topTableLayout;
    String[] titleStr = new String[]{"最热", "最新"};
    private ViewPager topViewPager;
    private CoordinatorLayout corrdinat;
    private AppBarLayout appBar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView back_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_message_acitivity);
        //得到数据
        circleList = (ArrayList<BeanTopic.DataBean.CircleBean>) getIntent().getSerializableExtra("content");
        id = getIntent().getStringExtra("id");
        initView();
        initData();
    }

    private void initData() {
        GlideUtils.loadImageView(this, circleList.get(Integer.parseInt(id)).getN_big_img(), topback_iv);
        GlideUtils.loadImageView(this, circleList.get(Integer.parseInt(id)).getN_small_img(), topUser_iv);
        topTitle.setText(circleList.get(Integer.parseInt(id)).getN_title());
        topBrief.setText(circleList.get(Integer.parseInt(id)).getN_brief());
        userCount.setText(circleList.get(Integer.parseInt(id)).getN_user_count() + "");
        topPostCount.setText(circleList.get(Integer.parseInt(id)).getN_post_count() + "");


        topTableLayout.setupWithViewPager(topViewPager);
        topViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(topTableLayout));
        topViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new ContentFragment().getOrder(position + 1 + "");
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleStr[position];
            }
        });

    }

    private void initView() {
        topback_iv = (ImageView) findViewById(R.id.topback_iv);
        topUser_iv = (ImageView) findViewById(R.id.topUser_iv);
        topTitle = (TextView) findViewById(R.id.topTitle);
        topBrief = (TextView) findViewById(R.id.topBrief);
        userCount = (TextView) findViewById(R.id.userCount);
        topPostCount = (TextView) findViewById(R.id.topPostCount);
        topTableLayout = (TabLayout) findViewById(R.id.topTableLayout);
        topViewPager = (ViewPager) findViewById(R.id.topViewPager);
        corrdinat = (CoordinatorLayout) findViewById(R.id.corrdinat);
        appBar = (AppBarLayout) findViewById(R.id.appBar);
        back_content = (ImageView) findViewById(R.id.back_content);
        back_content.setOnClickListener(this);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        AutoUtils.auto(corrdinat);
        AutoUtils.auto(appBar);
        AutoUtils.auto(collapsingToolbarLayout);
        AutoUtils.auto(topTableLayout);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
