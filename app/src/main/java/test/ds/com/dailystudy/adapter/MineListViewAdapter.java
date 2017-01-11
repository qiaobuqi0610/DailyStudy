package test.ds.com.dailystudy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.bean.MineBean;

/**
 * Created by 吕卓钊
 * on 2017/1/11 14:25.
 */

public class MineListViewAdapter extends BaseAdapter{
   private Context context;
    private ArrayList<MineBean> list;


    public MineListViewAdapter(Context context, ArrayList<MineBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(context, R.layout.listview_item,null);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.lv_image);
            TextView textView = (TextView) convertView.findViewById(R.id.lv_text);
            imageView.setImageResource(list.get(position).getImageid());
            textView.setText(list.get(position).getName());
             return convertView;

    }

}
