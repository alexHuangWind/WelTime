package weltimetable.a2019.program3.huang.changyu.weltimetable.components;

import android.app.Application;

import weltimetable.a2019.program3.huang.changyu.weltimetable.net.httpprocessor.http.HttpHelper;
import weltimetable.a2019.program3.huang.changyu.weltimetable.net.httpprocessor.processor.VolleyProcessor;

public class Myapplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HttpHelper.init(new VolleyProcessor(this));

    }
}
