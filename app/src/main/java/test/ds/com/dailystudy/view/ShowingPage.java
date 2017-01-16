package test.ds.com.dailystudy.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.interfac.OnReplayLoadLinstener;
import test.ds.com.dailystudy.utils.CommonUtils;
import test.ds.com.dailystudy.utils.NetUtils;

/**
 * Created by 乔智锋
 * on 2017/1/13 21:01.
 */

public abstract class ShowingPage extends FrameLayout implements View.OnClickListener {

    public static final int STATE_LOADING = 1;
    public static final int STATE_LOAD_ERROR = 2;
    public static final int STATE_LOAD_SUCCESS = 3;
    public int currentState = STATE_LOADING;
    private final View show_error;
    private final View show_loading;
    private final AutoLinearLayout show_title;
    private final AutoRelativeLayout myRealView;
    private final Button replay_load;
    private OnReplayLoadLinstener onReplayLoadLinstener;

    public ShowingPage(Context context) {
        super(context);
        View view = CommonUtils.inflate(R.layout.showing_page);
        show_error = view.findViewById(R.id.show_error);
        replay_load = (Button) show_error.findViewById(R.id.showing_error_bt_reload);
        replay_load.setOnClickListener(this);
        show_loading = view.findViewById(R.id.show_loading);
        show_title = (AutoLinearLayout) view.findViewById(R.id.show_title);
        myRealView = (AutoRelativeLayout) view.findViewById(R.id.myRealView);

        //添加title
        View titleView = setTitleView();
        if (titleView != null) {
            show_title.addView(titleView);
        } else {
            show_title.setVisibility(GONE);
        }
        View succView = setSuccView();
        //添加成功视图
        if (succView != null) {
            myRealView.addView(succView);
        } else {
            myRealView.setVisibility(GONE);
        }

        //添加到当前视图
        this.addView(view);
        showPage();
    }

    public abstract View setSuccView();

    public abstract View setTitleView();

    private void showPage() {
        //在主线程执行
        CommonUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                showPageOnUI();
            }
        });
    }

    private void showPageOnUI() {
        /**
         * 选择当前的状态进行显示
         */
        show_loading.setVisibility(currentState == STATE_LOADING ? View.VISIBLE : View.GONE);
        show_error.setVisibility(currentState == STATE_LOAD_ERROR ? View.VISIBLE : View.GONE);
        myRealView.setVisibility(currentState == STATE_LOAD_SUCCESS ? View.VISIBLE : View.GONE);
    }

    /**
     * 对外提供方法，设置当前状态
     *
     * @param stateType
     */
    public void showCurrentPage(StateType stateType) {
        this.currentState = stateType.getCurrentState();
        showPage();
    }

    @Override
    public void onClick(View view) {
        boolean haveNet = NetUtils.isHaveNet();
        if (haveNet) {
            currentState = STATE_LOADING;
            if (onReplayLoadLinstener != null) {
                onReplayLoadLinstener.repalyData();
            }
        }
    }

    public void setReplayLoadLindener(OnReplayLoadLinstener onReplayLoadLinstener) {
        this.onReplayLoadLinstener = onReplayLoadLinstener;
    }

    /**
     * 定义枚举类，限制状态类型
     */
    public enum StateType {
        STATE_LOAD_SUCCESS(3), STATE_LOAD_ERROR(2), STATE_LOADING(1);
        private final int currentState;

        public int getCurrentState() {
            return currentState;
        }

        StateType(int currentState) {
            this.currentState = currentState;
        }
    }
}
