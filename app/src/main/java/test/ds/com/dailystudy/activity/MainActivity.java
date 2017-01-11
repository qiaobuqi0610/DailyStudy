package test.ds.com.dailystudy.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.base.BaseActivity;
import test.ds.com.dailystudy.factory.FragmentFactory;
import test.ds.com.dailystudy.view.LazyViewPager;

public class MainActivity extends BaseActivity {

    private LazyViewPager main_viewPager;
    private RadioGroup main_rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initData() {
        main_viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return FragmentFactory.getFragment(position);
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
        main_viewPager.setOnPageChangeListener(new LazyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < main_rg.getChildCount(); i++) {
                    RadioButton childAt = (RadioButton) main_rg.getChildAt(i);
                    if (position == i) {
                        childAt.setChecked(true);
                    }else{
                        childAt.setChecked(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        main_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < main_rg.getChildCount(); i++) {
                    RadioButton childAt = (RadioButton) main_rg.getChildAt(i);
                    if (childAt.getId() == checkedId) {
                        childAt.setTextColor(getResources().getColor(R.color.main_rb_check));
                        main_viewPager.setCurrentItem(i);
                    } else {
                        childAt.setTextColor(getResources().getColor(R.color.colorText));
                    }
                }
            }
        });
    }

    private void initView() {
        main_rg = (RadioGroup) findViewById(R.id.main_rg);
        main_viewPager = (LazyViewPager) findViewById(R.id.main_viewPager);
    }
}
