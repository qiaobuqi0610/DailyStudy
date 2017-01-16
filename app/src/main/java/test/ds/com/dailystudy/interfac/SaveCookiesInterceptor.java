package test.ds.com.dailystudy.interfac;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import test.ds.com.dailystudy.utils.CommonUtils;

/**
 * Created by 乔智锋
 * on 2017/1/13 22:21.
 */

public class SaveCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        //PHPSESSID=vg6d8mpgmqgni6ct15skcjjm71;loginname=15330276178;
        StringBuilder stringBuilder=new StringBuilder();
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            for (String header : originalResponse.headers("Set-Cookie")) {
                Log.i("AAAA----","=="+header+"==");
                String cookie = header.substring(0, header.indexOf(";") + 1);
                stringBuilder.append(cookie);
            }
        }
        Log.i("AAAA","++"+stringBuilder.toString()+"++");
        CommonUtils.saveSp("cookie",stringBuilder.toString());
        return originalResponse;
    }
}
