package test.ds.com.dailystudy.interfac;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import test.ds.com.dailystudy.utils.CommonUtils;

/**
 * Created by 乔智锋
 * on 2017/1/13 22:23.
 */

public class ReadCookiesInterceptor implements Interceptor {

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String cookie = CommonUtils.getSp("cookie");
        //将cookie添加到请求头中
        builder.addHeader("Cookie", cookie);
        return chain.proceed(builder.build());
    }
}
