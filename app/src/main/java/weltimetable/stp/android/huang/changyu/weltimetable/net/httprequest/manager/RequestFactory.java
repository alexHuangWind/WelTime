package weltimetable.stp.android.huang.changyu.weltimetable.net.httprequest.manager;


import weltimetable.stp.android.huang.changyu.weltimetable.net.httprequest.interfaces.IRequestManager;

/**
 * this class is used to return a IRequestManager manager object,this IRequestManager
 * this is a Volley request obuect, and can be OkHttp request object

 */
public class RequestFactory {
    public static IRequestManager getRequestManager() {
        return VolleyRequestManager.getInstance();
        //return OkHttpRequestManager.getInstance();
    }
}