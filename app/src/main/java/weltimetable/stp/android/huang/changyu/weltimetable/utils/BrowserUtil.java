package weltimetable.stp.android.huang.changyu.weltimetable.utils;

import weltimetable.stp.android.huang.changyu.weltimetable.components.activitys.WebViewActivity;
import weltimetable.stp.android.huang.changyu.weltimetable.R;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by changyu on 20.05.stp.
 * email：alexchyandroid@gmail.com
 */
public class BrowserUtil {
    public static void openUrlInChromeCustomTab(Context context, String url) {
        try {
            Intent intent = new Intent(context, WebViewActivity.class);
            Bundle bundle = new Bundle();
            // bundle serialization
            bundle.putSerializable(context.getString(R.string.weltime_url), url);
            // put bundle to Intent中
            intent.putExtras(bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.getApplicationContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "Err,please try again", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
