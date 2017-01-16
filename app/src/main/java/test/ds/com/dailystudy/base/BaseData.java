package test.ds.com.dailystudy.base;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.ds.com.dailystudy.manager.HttpManger;
import test.ds.com.dailystudy.utils.CommonUtils;
import test.ds.com.dailystudy.utils.LogUtils;
import test.ds.com.dailystudy.utils.MD5Encoder;
import test.ds.com.dailystudy.view.ShowingPage;

/**
 * Created by 乔智锋
 * on 2017/1/10 21:07.
 */
public abstract class BaseData {
    public static final int NOTIME = 0;
    public static final int NORMALTIME = 3 * 24 * 60 * 60 * 1000;
    private final File fileDir;

    public BaseData() {
        File cacheDir = CommonUtils.getContext().getCacheDir();
        fileDir = new File(cacheDir, "dailystudy");
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
    }

    public void getData(String path, String args, int validTime) {
        if (validTime == 0) {
            LogUtils.i("TAG****", "直接访问网络----");
            getDataFromNet(path, args, validTime);
        } else {
            //从本地获取
            String data = getDataFromLocal(path, validTime);
            if (TextUtils.isEmpty(data)) {
                getDataFromNet(path, args, validTime);
            } else {
                setResultData(data);
            }
        }
    }

    public void postData(boolean isReadCookie, boolean isSaveCookie,String path, String args, HashMap<String, String> argsMap, int validTime) {
        //拼接map
        Set<String> keySet = argsMap.keySet();
        StringBuilder stringBuilder = new StringBuilder();
        for (String key:keySet){
            stringBuilder.append(key).append(argsMap.get(key));
        }

        //判断传过来的时间
        if (validTime == 0) {
            //直接访问网络
            postDataFromNet(isReadCookie,isSaveCookie,path, args,stringBuilder.toString(), argsMap, validTime);
        } else {
            //从 本地获取
            String data = getDataFromLocal(path, validTime);
            if (TextUtils.isEmpty(data)) {
                //本地为空 请求网络
                postDataFromNet(isReadCookie,isSaveCookie,path, args,stringBuilder.toString(), argsMap, validTime);
            } else {
                //不为空 返回数据
                setResultData(data);
            }
        }
    }

    private void postDataFromNet(boolean isReadCookie, boolean isSaveCookie, final String path, final String args, final String mapArgs, HashMap<String, String> argsMap, final int validTime) {
        HttpManger.postMethod(isReadCookie, isSaveCookie, path, args, argsMap, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //写入本地
                writeDataToLoal(path,args+mapArgs,validTime,response.body());
                setResultData(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


    private String getDataFromLocal(String path, int validTime) {
        try {
            File file = new File(fileDir, MD5Encoder.encode(path));

            if (!file.exists())
                return "";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String s = bufferedReader.readLine();
            long time = Long.parseLong(s);
            LogUtils.i("TAG****", "存储的时间----" + time);
            //判断时间按是否有效
            if (System.currentTimeMillis() < time) {
                StringBuilder builder = new StringBuilder();
                String lin = null;
                while ((lin = bufferedReader.readLine()) != null) {
                    builder.append(lin);
                }
                LogUtils.i("TAG****", "本地读取的数据----" + builder.toString());
                bufferedReader.close();
                return builder.toString();
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void getDataFromNet(final String path, final String args, final int validTime) {
        HttpManger.getMethod(path, args, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                writeDataToLoal(path, args, validTime, response.body());
                setResultData(response.body());
                LogUtils.i("TAG", "BaseData数据" + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                setResulttError(ShowingPage.StateType.STATE_LOAD_ERROR);
                LogUtils.i("TAG", "失败失败真失败" );

            }
        });


    }

    private void writeDataToLoal(String path, String args, int validTime, String data) {
        try {
            LogUtils.i("TAG****", "写入本地方法----");
            File file = new File(fileDir, MD5Encoder.encode(path));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(System.currentTimeMillis() + validTime + "\r\n");
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void setResultData(String data);


    protected abstract void setResulttError(ShowingPage.StateType stateLoadError);

}
