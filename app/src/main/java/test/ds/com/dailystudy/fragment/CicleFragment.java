package test.ds.com.dailystudy.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.activity.SearchFriendActivity;
import test.ds.com.dailystudy.base.BaseData;
import test.ds.com.dailystudy.base.BaseFragment;
import test.ds.com.dailystudy.manager.FragmentFactory;
import test.ds.com.dailystudy.utils.LogUtils;
import test.ds.com.dailystudy.view.ShowingPage;

/**
 * Created by 乔智锋
 * on 2017/1/10 21:48.
 */

public class CicleFragment extends BaseFragment implements View.OnClickListener {
    String[] title = new String[]{"话题", "热门", "关注"};
    private View view;
    private TabLayout circle_tablayout;
    private ViewPager circle_vp;
    private ImageView toSearch;

    @Override
    public View setSuccView() {
        view = View.inflate(getActivity(), R.layout.fragment_circle, null);
        circle_tablayout = (TabLayout) view.findViewById(R.id.circle_tablayout);
        circle_vp = (ViewPager) view.findViewById(R.id.circle_vp);
        toSearch = (ImageView) view.findViewById(R.id.toSearch);
        toSearch.setOnClickListener(this);
        new BaseData() {
            @Override
            public void setResultData(String data) {
                LogUtils.i("TAG", "--------------" + data);
                CicleFragment.this.showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                initTab();
            }

            @Override
            protected void setResulttError(ShowingPage.StateType stateLoadError) {
                CicleFragment.this.showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        }.getData("http://www.meirixue.com/", "http://www.meirixue.com/api.php?c=circle&a=getCircleNamesIndexV2", BaseData.NOTIME);
        return view;
    }

    @Override
    public View setTitleView() {
        return null;
    }

    private void initTab() {
        LinearLayout linearLayout = (LinearLayout) circle_tablayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(25);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),
                R.drawable.layout_divider_vertical));
        circle_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                circle_vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        circle_vp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return FragmentFactory.getFragment(title[position]);
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });
        circle_tablayout.setupWithViewPager(circle_vp);
        circle_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                circle_tablayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toSearch:
                Intent in = new Intent(getActivity(), SearchFriendActivity.class);
                startActivity(in);
                break;
        }
    }
}
