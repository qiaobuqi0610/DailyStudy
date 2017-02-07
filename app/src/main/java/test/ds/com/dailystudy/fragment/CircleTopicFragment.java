package test.ds.com.dailystudy.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.liaoinstan.springview.widget.SpringView;
import com.youth.banner.Banner;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.activity.ContentMessageAcitivity;
import test.ds.com.dailystudy.base.BaseData;
import test.ds.com.dailystudy.base.BaseFragment;
import test.ds.com.dailystudy.bean.BeanTopic;
import test.ds.com.dailystudy.utils.CommonUtils;
import test.ds.com.dailystudy.utils.URLUtils;
import test.ds.com.dailystudy.view.GlideImageLoader;
import test.ds.com.dailystudy.view.GlideUtils;
import test.ds.com.dailystudy.view.MyHeader;
import test.ds.com.dailystudy.view.ShowingPage;

/**
 * Created by PC on 2017/1/12.
 */

public class CircleTopicFragment extends BaseFragment {

    private View view;
    private Banner banner;
    private BeanTopic beanTopic;
    ArrayList<String> imageList = new ArrayList<>();
    ArrayList<BeanTopic.DataBean.CircleBean> circleList = new ArrayList<>();
    private RecyclerView hotcircle;
    private RecyclerView mycircle;
    private SpringView mySpringview;

    private void initBanner() {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imageList);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    //
    private void intiRecycler() {
        hotcircle.setLayoutManager(new LinearLayoutManager(getActivity()));
        hotcircle.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));
        CommonAdapter<BeanTopic.DataBean.CircleBean> commonAdapter = new CommonAdapter<BeanTopic.DataBean.CircleBean>(getActivity(), R.layout.topic_recycleritem_layout, circleList) {
            @Override
            protected void convert(ViewHolder holder, BeanTopic.DataBean.CircleBean circleBean, int position) {
                holder.setText(R.id.topic_Title, circleList.get(position).getN_title());
                holder.setText(R.id.topic_Brief, circleList.get(position).getN_brief());
                holder.setText(R.id.topic_userCount, circleList.get(position).getN_user_count() + "关注");
                holder.setText(R.id.topic_postCount, circleList.get(position).getN_post_count() + "帖子");
                ImageView image = holder.getView(R.id.topic_iv);
                GlideUtils.loadImageView(getActivity(), circleList.get(position).getN_small_img(), image);
            }
        };
        hotcircle.setAdapter(commonAdapter);
        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(getActivity(), ContentMessageAcitivity.class);
                intent.putExtra("content", circleList);
                intent.putExtra("id", position + "");
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    public View setSuccView() {
        view = CommonUtils.inflate(R.layout.circle_topic_fragment);
        banner = (Banner) view.findViewById(R.id.circle_topic_banner);
        hotcircle = (RecyclerView) view.findViewById(R.id.topic_hotcircle_recycler);
        mycircle = (RecyclerView) view.findViewById(R.id.topic_mycircle_recycler);
        mySpringview = (SpringView) view.findViewById(R.id.content);
        mySpringview.setHeader(new MyHeader());
        mySpringview.setType(SpringView.Type.FOLLOW);
        mySpringview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mySpringview.onFinishFreshAndLoad();
                    }
                }, 1500);
            }

            @Override
            public void onLoadmore() {
            }
        });
        new BaseData() {

            @Override
            public void setResultData(String data) {
                CircleTopicFragment.this.showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                Gson gson = new Gson();
                beanTopic = gson.fromJson(data, BeanTopic.class);
                imageList.clear();
                for (int i = 0; i < beanTopic.getData().getBanner().size(); i++) {
                    imageList.add(beanTopic.getData().getBanner().get(i).getImg());
                }
                circleList = (ArrayList<BeanTopic.DataBean.CircleBean>) beanTopic.getData().getCircle();
                initBanner();
                intiRecycler();
            }

            @Override
            protected void setResulttError(ShowingPage.StateType stateLoadError) {
                CircleTopicFragment.this.showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        }.getData(URLUtils.sort_url, URLUtils.circle_topic, BaseData.NOTIME);

        return view;
    }

    @Override
    public View setTitleView() {
        return null;
    }
}