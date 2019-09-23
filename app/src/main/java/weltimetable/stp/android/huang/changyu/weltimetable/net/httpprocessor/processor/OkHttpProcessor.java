package weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.processor;


import android.os.Handler;
import android.util.Log;


import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.interfaces.ICallBack;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.interfaces.IhttpProcessor;


public class OkHttpProcessor implements IhttpProcessor {

    public static final String TAG ="OkHttpProcessor";

    private static OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public OkHttpProcessor(){
        mOkHttpClient = new OkHttpClient();
        mHandler = new Handler();
    }


    @Override
    public void get(String url, Map<String, Object> params, final ICallBack callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailed(e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                /*if(response.isSuccessful()){
                    final String result = response.body().toString();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result);
                        }
                    });

                }else{
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFailed(response.message().toString());
                        }
                    });
                }*/
                boolean isSuccessful = response.isSuccessful();
                postParams(callback,isSuccessful,response);
            }
        });
    }


    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callback) {

        RequestBody requestBody = appendBody(params);

        Request request = new Request.Builder()
                .addHeader("token","alexhuangtoken")
                .post(requestBody)
                .url(url)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailed(e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
               /* if(response.isSuccessful()){
                    final String result = response.body().toString();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result);
                        }
                    });

                }else{
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFailed(response.message().toString());
                        }
                    });
                }*/
                boolean isSuccessful = response.isSuccessful();
                postParams(callback,isSuccessful,response);
            }
        });
    }

    //传入参数，返回添加头信息
    private RequestBody appendBody( Map<String, Object> params){
        FormBody.Builder body = new FormBody.Builder();
        if(params == null || params.isEmpty()){
            return body.build();
        }
        for(Map.Entry<String, Object> entry : params.entrySet()){
                body.add(entry.getKey(),entry.getValue().toString());
        }

        return body.build();
    }


    private void postParams(final ICallBack callback, final boolean isSuccess, final Response response){
        final String[] result = {""};
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(isSuccess == true){
                    try {
//                        "[{\"result\":\"200\"}]"
                        result[0] = response.body().string();
                        Headers headers =  response.networkResponse().request().headers();;
                        Headers headers2 = response.headers();
                        String token = headers2.get("token");
                        String token2 = headers.get("token");
//                        result[1]=token;
//                        result[2]=token2;
                        Log.d("RRR",""+token+"  "+token2);
//                        result[1] = response.header("token");
//                        result[1] = response.header("firstName");
//                        result[1] = response.header("studentID");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    callback.onSuccess(result[0]);
                }else{
                    result[0] = response.message().toString();
                    callback.onFailed(result[0]);
                }
            }
        });
    }
}