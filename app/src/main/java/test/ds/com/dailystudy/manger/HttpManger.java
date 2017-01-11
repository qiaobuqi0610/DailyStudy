package test.ds.com.dailystudy.manger;


import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import test.ds.com.dailystudy.interfac.ProjectAPI;

/**
 * Created by zhiyuan on 17/1/11.
 */

public class HttpManger {
    public static void getMethod(String baseUrl, String url, final Callback<String> callback) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(ScalarsConverterFactory.create()).build();

        ProjectAPI projectAPI = retrofit.create(ProjectAPI.class);

        Call<String> call = projectAPI.getMethod(url);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    public static void postMethod(String baseUrl, String url, Map<String,String> map,final Callback<String> callback) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(ScalarsConverterFactory.create()).build();

        ProjectAPI projectAPI = retrofit.create(ProjectAPI.class);

        Call<String> call = projectAPI.postMethod(url,map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callback.onResponse(call, response);
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}
