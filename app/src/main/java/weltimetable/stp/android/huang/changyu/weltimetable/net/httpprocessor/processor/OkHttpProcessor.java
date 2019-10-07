package weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.processor;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;


import com.google.gson.Gson;

import org.json.JSONObject;

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
import weltimetable.stp.android.huang.changyu.weltimetable.components.activitys.TimeTableActivity;
import weltimetable.stp.android.huang.changyu.weltimetable.models.UserInfo;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.interfaces.ICallBack;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.interfaces.IhttpProcessor;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.STPHelper;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.SharedPrefsUtils;


public class OkHttpProcessor implements IhttpProcessor {

    public static final String TAG = "OkHttpProcessor";

    private static OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public OkHttpProcessor() {
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
                postParams(callback, isSuccessful, response);
            }
        });
    }


    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callback) {
        boolean b =  Looper.myLooper() != Looper.getMainLooper();
        String token = "";
        RequestBody requestBody = appendBody(params);
        UserInfo uInfo = STPHelper.getInstance().getUserInfo();
        if (uInfo != null) {
            token = uInfo.getToken();
        }
        Request request = new Request.Builder()
                .addHeader("token", token)
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
                postParams(callback, isSuccessful, response);
            }
        });
    }

    //传入参数，返回添加头信息
    private RequestBody appendBody(Map<String, Object> params) {
        FormBody.Builder body = new FormBody.Builder();
        if (params == null || params.isEmpty()) {
            return body.build();
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            body.add(entry.getKey(), entry.getValue().toString());
        }

        return body.build();
    }


    private void postParams(ICallBack callback, final boolean isSuccess, final Response response) {
        final String[] result = {""};
        boolean b =  Looper.myLooper() != Looper.getMainLooper();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                boolean b =  Looper.myLooper() != Looper.getMainLooper();
                if (isSuccess == true) {
                    try {
//                        "[{\"result\":\"200\"}]"
                        result[0] = response.body().string();
                        Headers headers = response.networkResponse().request().headers();
                        Headers headers2 = response.headers();
                        String token = headers2.get("token");
//                        String firstName = headers2.get("firstName");
                        String studentID = headers2.get("studentID");
                        String major = headers.get("majoy");
                        if (token != null && studentID != null && !token.equals("") && !studentID.equals("")) {
                            UserInfo uinfo = new UserInfo();
                            uinfo.setStudentID(studentID);
                            uinfo.setToken(token);
                            uinfo.setMajor("IT");
                            Gson g = new Gson();
                            String userINfo = g.toJson(uinfo);
                            SharedPrefsUtils.setStringPreference(STPHelper.getInstance().getContext(), SharedPrefsUtils.USERINFO, userINfo);
                        }

                        Log.d("RRR", "" + token + "  " + token);
                        callback.onSuccess(result[0]);
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                        callback.onFailed(result[0]);
                        return;
                    }
                } else {
                    result[0] = response.message().toString();
                    callback.onFailed(result[0]);
                }
            }
        });
    }
}
