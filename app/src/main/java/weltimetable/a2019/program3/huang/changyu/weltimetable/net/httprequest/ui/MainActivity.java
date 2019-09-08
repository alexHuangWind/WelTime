//package weltimetable.a2019.program3.huang.changyu.weltimetable.net.httprequest.ui;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//
//import weltimetable.a2019.program3.huang.changyu.weltimetable.R;
//import weltimetable.a2019.program3.huang.changyu.weltimetable.net.httprequest.interfaces.IRequestCallback;
//import weltimetable.a2019.program3.huang.changyu.weltimetable.net.httprequest.interfaces.IRequestManager;
//import weltimetable.a2019.program3.huang.changyu.weltimetable.net.httprequest.manager.RequestFactory;
//
//
///**
// * 作者：Created by lzw
// * 时间：Created on 2017/6/17 0017 22:48
// * 邮箱：lzw20099002@126.com
// */
//
//public class MainActivity extends AppCompatActivity {
//
//    private static final String TAG = "MainActivity";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        //测试请求
//        String url = "https://api.douban.com/v2/movie/top250";
//        //这里发起请求依赖的是IRequestManager接口
//        IRequestManager requestManager = RequestFactory.getRequestManager();
//        requestManager.get(url, new IRequestCallback() {
//            @Override
//            public void onSuccess(String response) {
//                Log.d(TAG, "onSuccess: " + response);
//
//            }
//
//            @Override
//            public void onFailure(Throwable throwable) {
//                throwable.printStackTrace();
//
//            }
//        });
//
//    }
//}