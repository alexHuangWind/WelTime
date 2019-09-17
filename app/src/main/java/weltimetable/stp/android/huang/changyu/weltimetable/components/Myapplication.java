package weltimetable.stp.android.huang.changyu.weltimetable.components;

import android.app.Application;

import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.http.HttpHelper;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.processor.VolleyProcessor;

public class Myapplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HttpHelper.init(new VolleyProcessor(this));
    }
}
