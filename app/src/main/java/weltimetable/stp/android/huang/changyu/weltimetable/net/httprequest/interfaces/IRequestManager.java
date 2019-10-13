package weltimetable.stp.android.huang.changyu.weltimetable.net.httprequest.interfaces;

/**
 * this is implement by factory pattern

 */
public interface IRequestManager {
    abstract void get(String url, IRequestCallback requestCallback);
    abstract void post(String url, String requestBodyJson, IRequestCallback requestCallback);
    abstract void put(String url, String requestBodyJson, IRequestCallback requestCallback);
    abstract void delete(String url, String requestBodyJson, IRequestCallback requestCallback);
}
