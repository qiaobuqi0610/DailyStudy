package test.ds.com.dailystudy.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 乔智锋
 * on 2017/1/10 20:52.
 */

public class MyApplication extends Application {

    private static Context context;
    private static Handler handler;
    private static int mainThreadId;
    private static ExecutorService threadPool;

    @Override
    public void onCreate() {
        super.onCreate();
        //鸿洋适配器初始化
        AutoLayoutConifg.getInstance().useDeviceSize();
        UMShareAPI.get(this);
        context = getApplicationContext();
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        handler = new Handler();//创建Handle
        mainThreadId = Process.myTid();//得到主线程id
        threadPool = Executors.newFixedThreadPool(5);//创建线程池
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        UMShareAPI.get(this);
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static ExecutorService getThreadPool() {
        return threadPool;
    }
}
