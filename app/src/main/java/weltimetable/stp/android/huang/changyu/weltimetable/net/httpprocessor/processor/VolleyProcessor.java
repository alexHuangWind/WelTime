package weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.processor;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.interfaces.ICallBack;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.interfaces.IhttpProcessor;


public class VolleyProcessor implements IhttpProcessor {

    public static final String TAG ="VolleyProcessor";

    private static RequestQueue mQueue = null;

    public VolleyProcessor(Context context){
        mQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void get(String url, Map<String, Object> params, final ICallBack callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                callback.onFailed(volleyError.toString());
            }
        });
        mQueue.add(stringRequest);
    }

    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                callback.onFailed(volleyError.toString());
            }
        });
        mQueue.add(stringRequest);
    }
}
