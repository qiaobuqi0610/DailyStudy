package test.ds.com.dailystudy.base;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

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
        fileDir = new File(cacheDir, "yunifang");
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
    }

    public void getData(String path, String args, int index, int validTime) {
        if (validTime == 0) {
            LogUtils.i("TAG****", "直接访问网络----");
            getDataFromNet(path, args, index, validTime);
        } else {
            //从本地获取
            String data = getDataFromLocal(path, index, validTime);
            if (TextUtils.isEmpty(data)) {
                getDataFromNet(path, args, index, validTime);
            } else {
                setResultData(data);
            }
        }
    }

    public void postData(String path, HashMap<String, String> argsMap, int index, int validTime) {
        //判断传过来的时间
        if (validTime == 0) {
            //直接访问网络
            postDataFromNet(path, argsMap, index, validTime);
        } else {
            //从 本地获取
            String data = getDataFromLocal(path, index, validTime);
            if (TextUtils.isEmpty(data)) {
                //本地为空 请求网络
                postDataFromNet(path, argsMap, index, validTime);
            } else {
                //不为空 返回数据
                setResultData(data);
            }
        }
    }

    private void postDataFromNet(String path, HashMap<String, String> argsMap, int index, int validTime) {


    }


    private String getDataFromLocal(String path, int index, int validTime) {
        try {
            File file = new File(fileDir, MD5Encoder.encode(path) + index);

            if (!file.exists())
                return "";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String s = bufferedReader.readLine();
            long time = Long.parseLong(s);
            LogUtils.i("TAG****", "存储的时间----" + time);
            //200  190
            if (System.currentTimeMillis() < time) {
                StringBuilder builder = new StringBuilder();
                String lin = null;
                while ((lin = bufferedReader.readLine()) != null) {
                    builder.append(lin);
                }
                bufferedReader.close();
                LogUtils.i("TAG****", "本地读取的数据----" + builder.toString());

                return builder.toString();
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void getDataFromNet(final String path, final String args, final int index, final int validTime) {

        //writeDataToLoal(path, args, index, validTime);

        }

private void writeDataToLoal(String path,String args,int index,int validTime,String data){
        try{
        LogUtils.i("TAG****","写入本地方法----");
        File file=new File(fileDir,MD5Encoder.encode(path)+index);
        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(System.currentTimeMillis()+validTime+"\r\n");
        bufferedWriter.write(data);
        bufferedWriter.close();
        }catch(Exception e){
        e.printStackTrace();
        }
        }

public abstract void setResultData(String data);

protected abstract void setResultError(ShowingPage.StateType stateLoadError);


        }
