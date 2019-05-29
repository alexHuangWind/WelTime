package weltimetable.a2019.program3.huang.changyu.weltimetable.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetCheckUtil {
    public static boolean checkNet(Context context) {
        boolean mobileConnection = isMobileConnection(context);
        boolean wifiConnection = isWIFIConnection(context);
        if ( mobileConnection == false && wifiConnection == false ) {
            // no network
            return false;
        }
        return true;
    }



    /**
     * check the moblie Connection
     *
     * @param context
     * @return
     */
    public static boolean isMobileConnection(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ( networkInfo != null && networkInfo.isConnected() ) {
            return true;
        }
        return false;
    }

    /**
     * check wifi
     *
     * @param context
     * @return
     */
    public static boolean isWIFIConnection(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if ( networkInfo != null && networkInfo.isConnected() ) {
            return true;
        }
        return false;
    }

}
