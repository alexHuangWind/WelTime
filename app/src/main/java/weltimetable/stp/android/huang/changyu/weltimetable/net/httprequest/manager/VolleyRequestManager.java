package weltimetable.stp.android.huang.changyu.weltimetable.net.httprequest.manager;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;

import weltimetable.stp.android.huang.changyu.weltimetable.net.httprequest.interfaces.IRequestCallback;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httprequest.interfaces.IRequestManager;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httprequest.app.HttpRequestExampleApp;


public class VolleyRequestManager implements IRequestManager {

    public static VolleyRequestManager getInstance() {
        return SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        private static final VolleyRequestManager sInstance = new VolleyRequestManager();
    }

    @Override
    public void get(String url, final IRequestCallback requestCallback) {
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        requestCallback.onSuccess(s);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        requestCallback.onFailure(volleyError);
                    }
                });
        HttpRequestExampleApp.mQueue.add(request);
    }

    @Override
    public void post(String url, String requestBodyJson, final IRequestCallback requestCallback) {
        requestWithBody(url, requestBodyJson, requestCallback, Request.Method.POST);
    }

    @Override
    public void put(String url, String requestBodyJson, final IRequestCallback requestCallback) {
        requestWithBody(url, requestBodyJson, requestCallback, Request.Method.PUT);
    }

    @Override
    public void delete(String url, String requestBodyJson, final IRequestCallback requestCallback) {
        requestWithBody(url, requestBodyJson, requestCallback, Request.Method.DELETE);
    }

    /**
     * request Method
     *
     * @param url             url
     * @param requestBodyJson Json string
     * @param requestCallback Callback
     * @param method          request method
     */
    private void requestWithBody(String url, String requestBodyJson, final IRequestCallback requestCallback, int method) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(requestBodyJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonRequest<JSONObject> request = new JsonObjectRequest(method, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        requestCallback.onSuccess(response != null ? response.toString() : null);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        requestCallback.onFailure(error);
                    }
                });
        HttpRequestExampleApp.mQueue.add(request);
    }
}
