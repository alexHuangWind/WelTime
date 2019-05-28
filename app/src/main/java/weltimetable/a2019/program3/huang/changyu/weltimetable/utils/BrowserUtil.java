package weltimetable.a2019.program3.huang.changyu.weltimetable.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;

import weltimetable.a2019.program3.huang.changyu.weltimetable.R;
import weltimetable.a2019.program3.huang.changyu.weltimetable.activities.WebViewActivity;

/**
 * Created by Ulan on 19.10.2018.
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
