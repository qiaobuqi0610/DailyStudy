package test.ds.com.dailystudy.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.base.BaseData;
import test.ds.com.dailystudy.base.BaseFragment;
import test.ds.com.dailystudy.bean.BeanHotTitle;
import test.ds.com.dailystudy.utils.URLUtils;
import test.ds.com.dailystudy.view.ShowingPage;


public class CircleHotFragment extends BaseFragment {
    private View view;
    ArrayList<BeanHotTitle.DataBean> titleList = new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager circle_hot_vp;


    //设置顶部标题
    private void initTitle() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                circle_hot_vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        circle_hot_vp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment f1 = CircleHotVpFragment.getFragment(titleList.get(position).getName(), titleList.get(position).getTid());
                return f1;
            }

            @Override
            public int getCount() {
                return titleList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position).getName();
            }
        });
        tabLayout.setupWithViewPager(circle_hot_vp);
        circle_hot_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    @Override
    public View setSuccView() {
        view = View.inflate(getActivity(), R.layout.circle_hot_fragment, null);
        tabLayout = (TabLayout) view.findViewById(R.id.circle_hot_tablayout);
        circle_hot_vp = (ViewPager) view.findViewById(R.id.circle_hot_vp);
        new BaseData() {

            @Override
            public void setResultData(String data) {
                CircleHotFragment.this.showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                Gson gson = new Gson();
                BeanHotTitle beanHotTitle = gson.fromJson(data, BeanHotTitle.class);
                titleList = (ArrayList<BeanHotTitle.DataBean>) beanHotTitle.getData();
                initTitle();
            }

            @Override
            protected void setResulttError(ShowingPage.StateType stateLoadError) {
                CircleHotFragment.this.showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);

            }
        }.getData(URLUtils.sort_url, URLUtils.circle_hottitle, BaseData.NOTIME);
        return view;
    }

    @Override
    public View setTitleView() {
        return null;
    }
}
