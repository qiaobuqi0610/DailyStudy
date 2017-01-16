package test.ds.com.dailystudy.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.activity.ClassSortActivity;
import test.ds.com.dailystudy.bean.Bean_Sort;
import test.ds.com.dailystudy.utils.CommonUtils;

/**
 * Created by 张天成
 * on 2017/1/12 09:04.
 */
public class MyGridView_Adapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<Bean_Sort.NodesBean> list;
    private final Bean_Sort[] bean_sort;

    public MyGridView_Adapter(Context context, ArrayList<Bean_Sort.NodesBean> list, Bean_Sort[] bean_sort) {
        this.context = context;
        this.list = list;
        this.bean_sort = bean_sort;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = CommonUtils.inflate(R.layout.grive_item);
        TextView textView = (TextView) view.findViewById(R.id.grive_item_tv);
        textView.setText(list.get(position).getCategory_name());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, ClassSortActivity.class);
                in.putExtra("name", list.get(position).getCategory_name());
                in.putExtra("id", list.get(position).getId());
                context.startActivity(in);
            }
        });
        return view;
    }
}
