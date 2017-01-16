package test.ds.com.dailystudy.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.adapter.RecyclerAdapter;
import test.ds.com.dailystudy.adapter.RecyclerViewHolder;
import test.ds.com.dailystudy.base.BaseData;
import test.ds.com.dailystudy.base.BaseFragment;
import test.ds.com.dailystudy.bean.HomeBean;
import test.ds.com.dailystudy.utils.UrlUtils;
import test.ds.com.dailystudy.view.GlideImageLoader;
import test.ds.com.dailystudy.view.ShowingPage;

/**
 * Created by 乔智锋
 * on 2017/1/10 21:48.
 */

public class HomeFragment extends BaseFragment {
    private View view;
    private ArrayList<String> imageUrlList = new ArrayList<>();
    private HomeBean roolData;
    private Banner banner;
    private List<HomeBean.DataBean.SliderBean> slider;

    private List<HomeBean.DataBean.HotcategoryBean> hotcategory;
    private RecyclerView recyclerView;
    private List<HomeBean.DataBean.AdlistBean> adlist;
    private ImageView img;
    private TextView name;
    private TextView name1;
    private TextView name2;
    private TextView title;
    private TextView title1;
    private TextView title2;
    private ImageView img1;
    private ImageView img2;
    private RecyclerView recyclerView1;
    private List<HomeBean.DataBean.HotcourseBean> hotcourse;
    private RecyclerView recyclerView2;
    private List<HomeBean.DataBean.IndexrecommendBean.TopBean> top;
    private List<HomeBean.DataBean.IndexrecommendBean.ListviewBean> listview;
    private RecyclerView recyclerView3;
    private RecyclerView recyclerView4;
    private List<HomeBean.DataBean.IndexothersBean> indexothers;


    private void initview() {
        view = View.inflate(getActivity(), R.layout.fragment_home, null);
        banner = (Banner) view.findViewById(R.id.banner);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleview);
        name = (TextView) view.findViewById(R.id.name);
        name1 = (TextView) view.findViewById(R.id.name1);
        name2 = (TextView) view.findViewById(R.id.name2);
        title = (TextView) view.findViewById(R.id.titlett);
        title1 = (TextView) view.findViewById(R.id.title1);
        title2 = (TextView) view.findViewById(R.id.title2);
        img = (ImageView) view.findViewById(R.id.list_img);
        img1 = (ImageView) view.findViewById(R.id.list_img1);
        img2 = (ImageView) view.findViewById(R.id.list_img2);
        recyclerView1 = (RecyclerView) view.findViewById(R.id.recycleview1);
        recyclerView2 = (RecyclerView) view.findViewById(R.id.recycleview2);
        recyclerView3 = (RecyclerView) view.findViewById(R.id.recycleview3);
        recyclerView4 = (RecyclerView) view.findViewById(R.id.recycleview4);
    }


    @Override
    public View setSuccView() {
        initview();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new BaseData() {

            @Override
            public void setResultData(String data) {
                HomeFragment.this.showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                Gson gson = new Gson();
                roolData = gson.fromJson(data, HomeBean.class);
                //轮播
                lunbo();
                //3列数
                bottomviewpager();
                //矩形
                square();
                //热门课程
                hot();
                //小编推荐
                xiaobian();
                //都在学
                everyStudy();

            }

            @Override
            protected void setResulttError(ShowingPage.StateType stateLoadError) {
                HomeFragment.this.showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        }.getData(UrlUtils.homeUrl, UrlUtils.homeArgs, BaseData.NOTIME);


    }

    private void everyStudy() {
        indexothers = roolData.getData().getIndexothers();
        RecyclerAdapter<HomeBean.DataBean.IndexothersBean> recyclerAdapter5 = new RecyclerAdapter<HomeBean.DataBean.IndexothersBean>(getActivity(), indexothers, R.layout.other) {
            @Override
            public void convert(RecyclerViewHolder holder, HomeBean.DataBean.IndexothersBean data, int position) {
                ImageView imageView = holder.findView(R.id.others_image);
                Glide.with(getActivity())
                        .load(indexothers.get(position).getCourse_pic())
                        .placeholder(null)
                        .error(null)
                        .into(imageView);
                holder.setText(R.id.others_course_name, indexothers.get(position).getCourse_name());
                holder.setText(R.id.others_school_name, indexothers.get(position).getSchool_name());
                TextView textView = holder.findView(R.id.others_course_price);
                textView.setTextColor(indexothers.get(position).getCourse_price().equals("0.00") ? Color.parseColor("#70b30f") : Color.parseColor("#fca59e"));
                holder.setText(R.id.others_course_price, indexothers.get(position).getCourse_price().equals("0.00") ? "免费" : "￥" + indexothers.get(position).getCourse_price());
                holder.setText(R.id.others_usercount, indexothers.get(position).getUsercount() + "人在学");
            }
        };
        recyclerView4.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerView4.setAdapter(recyclerAdapter5);
    }

    //小编推荐
    private void xiaobian() {
        top = roolData.getData().getIndexrecommend().getTop();
        RecyclerAdapter<HomeBean.DataBean.IndexrecommendBean.TopBean> recyclerAdapter3 = new RecyclerAdapter<HomeBean.DataBean.IndexrecommendBean.TopBean>(getActivity(), top
                , R.layout.top_item) {
            @Override
            public void convert(RecyclerViewHolder holder, HomeBean.DataBean.IndexrecommendBean.TopBean data, int position) {
                ImageView imageView = holder.findView(R.id.top_image);
                Glide.with(getActivity())
                        .load(top.get(position).getCourse_pic())
                        .placeholder(null)
                        .error(null)
                        .into(imageView);
            }
        };
        recyclerView2.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView2.setAdapter(recyclerAdapter3);
        listview = roolData.getData().getIndexrecommend().getListview();
        RecyclerAdapter<HomeBean.DataBean.IndexrecommendBean.ListviewBean> recyclerAdapter4 = new RecyclerAdapter<HomeBean.DataBean.IndexrecommendBean.ListviewBean>(
                getActivity(), listview, R.layout.list_item
        ) {
            @Override
            public void convert(RecyclerViewHolder holder, HomeBean.DataBean.IndexrecommendBean.ListviewBean data, int position) {
                ImageView imageView = holder.findView(R.id.listiew_image);
                Glide.with(getActivity())
                        .load(listview.get(position).getCourse_pic())
                        .placeholder(null)
                        .error(null)
                        .into(imageView);
                holder.setText(R.id.course_name, listview.get(position).getCourse_name());
                holder.setText(R.id.school_name, listview.get(position).getSchool_name());
                TextView textView = holder.findView(R.id.course_price);
                textView.setTextColor(listview.get(position).getCourse_price().equals("0.00") ? Color.parseColor("#70b30f") : Color.parseColor("#fca59e"));
                holder.setText(R.id.course_price, listview.get(position).getCourse_price().equals("0.00") ? "免费" : "￥" + listview.get(position).getCourse_price());

                holder.setText(R.id.usercount, listview.get(position).getUsercount() + "人在学");
            }
        };
        recyclerView3.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerView3.setAdapter(recyclerAdapter4);
    }

    //热门课程
    private void hot() {
        hotcourse = roolData.getData().getHotcourse();
        RecyclerAdapter<HomeBean.DataBean.HotcourseBean> recyclerAdapter1 = new RecyclerAdapter<HomeBean.DataBean.HotcourseBean>(
                getActivity(), hotcourse, R.layout.recycle1_item) {
            @Override
            public void convert(RecyclerViewHolder holder, HomeBean.DataBean.HotcourseBean data, int position) {
                ImageView imageView = holder.findView(R.id.hor_image);
                Glide.with(getActivity())
                        .load(hotcourse.get(position).getImg())
                        .placeholder(null)
                        .error(null)
                        .into(imageView);
                holder.setText(R.id.hor_name, hotcourse.get(position).getName());
                holder.setText(R.id.hor_title, hotcourse.get(position).getTitle());


            }
        };
        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView1.setAdapter(recyclerAdapter1);
    }

    //矩形
    private void square() {
        adlist = roolData.getData().getAdlist();
        name.setText(adlist.get(0).getName());
        name1.setText(adlist.get(1).getName());
        name2.setText(adlist.get(2).getName());
        title.setText(adlist.get(0).getTitle());
        title1.setText(adlist.get(1).getTitle());
        title2.setText(adlist.get(2).getTitle());
        Glide.with(getActivity())
                .load(adlist.get(0).getImg())
                .placeholder(null)
                .error(null)
                .into(img);
        Glide.with(getActivity())
                .load(adlist.get(1).getImg())
                .placeholder(null)
                .error(null)
                .into(img1);
        Glide.with(getActivity())
                .load(adlist.get(2).getImg())
                .placeholder(null)
                .error(null)
                .into(img2);
    }

    //3列数
    private void bottomviewpager() {
        hotcategory = roolData.getData().getHotcategory();
        hotcategory.remove(6);
        RecyclerAdapter<HomeBean.DataBean.HotcategoryBean> recyclerAdapter = new RecyclerAdapter<HomeBean.DataBean.HotcategoryBean>(
                getActivity(), hotcategory, R.layout.recycle_item) {
            @Override
            public void convert(RecyclerViewHolder holder, HomeBean.DataBean.HotcategoryBean data, int position) {
                ImageView imageView = holder.findView(R.id.ho_img);
                Glide.with(getActivity())
                        .load(hotcategory.get(position).getImg())
                        .placeholder(null)
                        .error(null)
                        .into(imageView);
                holder.setText(R.id.ho_text, hotcategory.get(position).getCname());
            }
        };

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(recyclerAdapter);
    }

    //轮播
    private void lunbo() {
        slider = roolData.getData().getSlider();
        imageUrlList.clear();
        for (int i = 0; i < slider.size(); i++) {
            imageUrlList.add(slider.get(i).getImg());
        }
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imageUrlList);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public View setTitleView() {
        return null;
    }
}
