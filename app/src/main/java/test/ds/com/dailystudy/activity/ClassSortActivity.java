package test.ds.com.dailystudy.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.base.BaseActivity;
import test.ds.com.dailystudy.base.BaseData;
import test.ds.com.dailystudy.bean.Bean_Sort_List;
import test.ds.com.dailystudy.utils.LogUtils;
import test.ds.com.dailystudy.utils.URLUtils;
import test.ds.com.dailystudy.view.ShowingPage;

public class ClassSortActivity extends BaseActivity implements View.OnClickListener {

    private TextView course_list_total_tv;
    private ImageView course_list_total_iv;
    private ImageView course_list_filter_iv;
    private ImageView course_list_sort_iv;
    private AutoLinearLayout course_list_ll;
    private AutoLinearLayout course_list_ll_Filter;
    private AutoLinearLayout course_list_ll_sort;
    private TextView course_list_total_tv1;
    private TextView course_list_total_tv11;
    private TextView course_list_filter_tv;
    private TextView course_list_sort_tv;
    private ListView listView;
    private Bean_Sort_List[] bean_sort_list;
    private Intent intent;
    private String id;
    private PopupWindow popupWindow;
    private FrameLayout fram;
    private AutoLinearLayout line;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_sort);
        //找到控件
        intView();
        intent = getIntent();
        String total_tv = intent.getStringExtra("name");
        id = intent.getStringExtra("id");
        course_list_total_tv11.setText(total_tv);
        //添加监听
        initClint();
        //请求网络添加数据
        initData();

    }

    private void initData() {
        new BaseData() {

            private ArrayList<Bean_Sort_List.DatalistBean> list;

            @Override
            public void setResultData(String data) {
                LogUtils.i("TAG", "setResultData------------" + data);
                Gson gson = new Gson();
                bean_sort_list = gson.fromJson(data, Bean_Sort_List[].class);
                for (int i = 0; i < bean_sort_list.length; i++) {
                    list = new ArrayList<>();
                    if (bean_sort_list[i] != null && bean_sort_list[i] != null && bean_sort_list[i].getDatalist() != null && bean_sort_list[i].getDatalist().size() > 0) {
                        for (int j = 0; j < bean_sort_list[i].getDatalist().size(); j++) {
                            LogUtils.i("Tag", "aaaaaaaaa" + bean_sort_list[i].getDatalist().get(j));
                            list.add(bean_sort_list[i].getDatalist().get(j));
                        }
                    }
                }
                SharedPreferences sp = getSharedPreferences("name", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("data", data);
                edit.commit();
                String username = sp.getString("data", "");
                LogUtils.i("Tag", "54321" + username);
/*
                listView.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return bean_sort_list.length;
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
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = CommonUtils.inflate(R.layout.activity_sort_listitem);
                        ImageView iv_main = (ImageView) view.findViewById(R.id.course_list_item_iv_main);
                        TextView course_list_item_tv2 = (TextView) view.findViewById(R.id.course_list_item_tv2);
                        TextView course_list_item_tv3 = (TextView) view.findViewById(R.id.course_list_item_tv3);
                        TextView course_list_item_tv4 = (TextView) view.findViewById(R.id.course_list_item_tv4);
                        TextView course_list_item_tv5 = (TextView) view.findViewById(R.id.course_list_item_tv5);
                       // course_list_item_tv2.setText(list.get(position).getCourse_name());
                        return view;
                    }
                });
*/
            }

            @Override
            protected void setResulttError(ShowingPage.StateType stateLoadError) {

            }
        }.getData(URLUtils.sort_list_url, URLUtils.sort_list_canshu + id, BaseData.NORMALTIME);
        LogUtils.i("Tag", "11111111aaaa" + URLUtils.sort_list_url + URLUtils.sort_list_canshu + id);
    }

    private void initClint() {
        course_list_ll.setOnClickListener(this);
        course_list_ll_Filter.setOnClickListener(this);
        course_list_ll_sort.setOnClickListener(this);


    }

    private void intView() {
        listView = (ListView) findViewById(R.id.course_list_xlistView);
        course_list_total_iv = (ImageView) findViewById(R.id.course_list_total_iv);
        course_list_filter_iv = (ImageView) findViewById(R.id.course_list_filter_iv);
        course_list_sort_iv = (ImageView) findViewById(R.id.course_list_sort_iv);
        course_list_total_iv.setImageResource(R.mipmap.down);
        course_list_filter_iv.setImageResource(R.mipmap.down);
        course_list_sort_iv.setImageResource(R.mipmap.down);
        course_list_total_tv11 = (TextView) findViewById(R.id.course_list_total_tv);
        course_list_filter_tv = (TextView) findViewById(R.id.course_list_filter_tv);
        course_list_sort_tv = (TextView) findViewById(R.id.course_list_sort_tv);
        course_list_ll = (AutoLinearLayout) findViewById(R.id.course_list_ll);
        course_list_ll_Filter = (AutoLinearLayout) findViewById(R.id.course_list_ll_Filter);
        course_list_ll_sort = (AutoLinearLayout) findViewById(R.id.course_list_ll_sort);
//        fram = (FrameLayout) findViewById(R.id.class_soat_fram);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.course_list_ll:
                if (course_list_total_tv11.getCurrentTextColor() == Color.RED) {
                    course_list_total_tv11.setTextColor(Color.BLACK);
                    course_list_total_iv.setImageResource(R.mipmap.down);
                } else {
                    course_list_total_tv11.setTextColor(Color.RED);
                    course_list_total_iv.setImageResource(R.mipmap.heart_s);
                }
                hide(course_list_filter_iv, course_list_sort_iv, course_list_filter_tv, course_list_sort_tv);
                break;
            case R.id.course_list_ll_Filter:
                if (course_list_filter_tv.getCurrentTextColor() == Color.RED) {
                    course_list_filter_tv.setTextColor(Color.BLACK);
                    course_list_filter_iv.setImageResource(R.mipmap.down);
                } else {
                    course_list_filter_tv.setTextColor(Color.RED);
                    course_list_filter_iv.setImageResource(R.mipmap.heart_s);
                    View view = View.inflate(ClassSortActivity.this, R.layout.course_list_filter, null);
                    popupWindow = new PopupWindow(view, AutoLinearLayout.LayoutParams.MATCH_PARENT, 300);
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    popupWindow.setFocusable(true);
                    popupWindow.showAsDropDown(v);
                    listView.setBackgroundColor(this.getResources().getColor(R.color.pop_out));
                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            listView.setBackgroundColor(ClassSortActivity.this.getResources().getColor(R.color.white));
                            course_list_filter_tv.setTextColor(Color.BLACK);
                            course_list_filter_iv.setImageResource(R.mipmap.down);
                        }
                    });
                }
                break;
            case R.id.course_list_ll_sort:
                if (course_list_sort_tv.getCurrentTextColor() == Color.RED) {
                    course_list_sort_tv.setTextColor(Color.BLACK);
                    course_list_sort_iv.setImageResource(R.mipmap.down);
                    closePopupWindow();
                } else {
                    course_list_sort_tv.setTextColor(Color.RED);
                    course_list_sort_iv.setImageResource(R.mipmap.heart_s);
                    View view = View.inflate(ClassSortActivity.this, R.layout.paixupopwindow, null);
                    popupWindow = new PopupWindow(view, AutoLinearLayout.LayoutParams.MATCH_PARENT, 300);
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    popupWindow.setFocusable(true);
                    popupWindow.showAsDropDown(v);
                    listView.setBackgroundColor(this.getResources().getColor(R.color.pop_out));
                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            listView.setBackgroundColor(ClassSortActivity.this.getResources().getColor(R.color.white));
                            course_list_sort_tv.setTextColor(Color.BLACK);
                            course_list_sort_iv.setImageResource(R.mipmap.down);
                        }
                    });

                }

                break;
        }
    }


    public void hide(ImageView imageView, ImageView imageView2, TextView textView, TextView textView2) {
        imageView.setImageResource(R.mipmap.down);
        textView.setTextColor(Color.BLACK);
        imageView2.setImageResource(R.mipmap.down);
        textView2.setTextColor(Color.BLACK);
    }

    ;

    /**
     * 关闭窗口
     */
    private void closePopupWindow() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
            WindowManager.LayoutParams params = this.getWindow().getAttributes();
            params.alpha = 1f;
            this.getWindow().setAttributes(params);
        }
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {

//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.alpha = bgAlpha; //0.0-1.0
//        fram.setAlpha(bgAlpha);
//        listView.setAlpha(bgAlpha);
//        getWindow().setAttributes(lp);
    }
}
