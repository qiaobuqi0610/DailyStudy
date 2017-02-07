package test.ds.com.dailystudy.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.bean.BeanHotContent;
import test.ds.com.dailystudy.view.CircleImageView;

import static test.ds.com.dailystudy.R.id.hot_first_iv;
import static test.ds.com.dailystudy.R.id.hot_iv1;
import static test.ds.com.dailystudy.R.id.hot_iv2;
import static test.ds.com.dailystudy.R.id.hot_iv3;
import static test.ds.com.dailystudy.R.id.hot_two_iv1;
import static test.ds.com.dailystudy.R.id.hot_two_iv2;

/**
 * Created by 乔智锋
 * on 2017/2/7 10:56.
 */

public class HotAdapter extends CommonAdapter<BeanHotContent.DataBean> {

    private final Context context;
    private final int layoutId;
    private final List<BeanHotContent.DataBean> datas;
    private final String title;

    public HotAdapter(Context context, int layoutId, List<BeanHotContent.DataBean> datas, String title) {
        super(context, layoutId, datas);
        this.context=context;
        this.layoutId=layoutId;
        this.datas=datas;
        this.title=title;
    }

    @Override
    protected void convert(com.zhy.adapter.recyclerview.base.ViewHolder holder, BeanHotContent.DataBean dataBean, int position) {
        CircleImageView hot_userIcon = holder.getView(R.id.hot_userIcon);
        Glide.with(context)
                .load(dataBean.getUser_small_log())
                .placeholder(R.mipmap.head)
                .error(R.mipmap.head)
                .into(hot_userIcon);
        holder.setText(R.id.hot_userName, dataBean.getUser_name());
        holder.setText(R.id.hot_pTitle, dataBean.getP_title());
        if (dataBean.getP_leaderette() != null) {
            holder.setText(R.id.hot_pLeaderette, dataBean.getP_leaderette() + "");
        } else {
            holder.setText(R.id.hot_pLeaderette, "");
        }
        AutoLinearLayout hot_llt_three = holder.getView(R.id.hot_llt_three);
        AutoLinearLayout hot_llt_two = holder.getView(R.id.hot_llt_two);
        holder.setText(R.id.hot_type, "#" + title + "#");
        holder.setText(R.id.hot_likeCount, dataBean.getP_dig());
        holder.setText(R.id.hot_messageCount, dataBean.getP_replycount());
        holder.setText(R.id.hot_shareCount, dataBean.getP_sharecount());
        ImageView three_iv1 = holder.getView(hot_iv1);
        ImageView three_iv2 = holder.getView(hot_iv2);
        ImageView three_iv3 = holder.getView(hot_iv3);
        ImageView two_iv1 = holder.getView(hot_two_iv1);
        ImageView two_iv2 = holder.getView(hot_two_iv2);
        ImageView first_iv = holder.getView(hot_first_iv);


        String source = dataBean.getSource();
        if (source != null) {
            Gson gson1 = new Gson();
            String[] strings = gson1.fromJson(source, String[].class);
            if (strings.length > 0) {
                if (strings.length == 1) {
                    first_iv.setVisibility(View.VISIBLE);
                    hot_llt_two.setVisibility(View.GONE);
                    hot_llt_three.setVisibility(View.GONE);
                    Glide.with(context)
                            .load(strings[0])
                            .placeholder(R.mipmap.default_one)
                            .error(R.mipmap.default_one)
                            .into(first_iv);
                } else if (strings.length == 2) {
                    hot_llt_two.setVisibility(View.VISIBLE);
                    first_iv.setVisibility(View.GONE);
                    hot_llt_three.setVisibility(View.GONE);
                    Glide.with(context)
                            .load(strings[0])
                            .placeholder(R.mipmap.default_two)
                            .error(R.mipmap.default_two)
                            .into(two_iv1);
                    Glide.with(context)
                            .load(strings[1])
                            .placeholder(R.mipmap.default_two)
                            .error(R.mipmap.default_two)
                            .into(two_iv2);
                } else if (strings.length == 3) {
                    hot_llt_three.setVisibility(View.VISIBLE);
                    hot_llt_two.setVisibility(View.GONE);
                    first_iv.setVisibility(View.GONE);
                    Glide.with(context)
                            .load(strings[0])
                            .placeholder(R.mipmap.default_three)
                            .error(R.mipmap.default_three)
                            .into(three_iv1);
                    Glide.with(context)
                            .load(strings[1])
                            .placeholder(R.mipmap.default_three)
                            .error(R.mipmap.default_three)
                            .into(three_iv2);
                    Glide.with(context)
                            .load(strings[2])
                            .placeholder(R.mipmap.default_three)
                            .error(R.mipmap.default_three)
                            .into(three_iv3);
                } else if (strings.length > 3) {
                    hot_llt_three.setVisibility(View.VISIBLE);
                    hot_llt_two.setVisibility(View.GONE);
                    first_iv.setVisibility(View.GONE);
                    Glide.with(context)
                            .load(strings[0])
                            .placeholder(R.mipmap.default_three)
                            .error(R.mipmap.default_three)
                            .into(three_iv1);
                    Glide.with(context)
                            .load(strings[1])
                            .placeholder(R.mipmap.default_three)
                            .error(R.mipmap.default_three)
                            .into(three_iv2);
                    Glide.with(context)
                            .load(strings[2])
                            .placeholder(R.mipmap.default_three)
                            .error(R.mipmap.default_three)
                            .into(three_iv3);
                }
            }
        }
    }

}
