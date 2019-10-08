package weltimetable.stp.android.huang.changyu.weltimetable.components;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.http.HttpHelper;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.processor.OkHttpProcessor;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.processor.VolleyProcessor;

public class Myapplication extends Application {
    public static RequestQueue mQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mQueue = Volley.newRequestQueue(this);
        HttpHelper.init(new OkHttpProcessor());


    }
}
