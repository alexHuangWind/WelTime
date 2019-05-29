package weltimetable.a2019.program3.huang.changyu.weltimetable.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import weltimetable.a2019.program3.huang.changyu.weltimetable.R;
import weltimetable.a2019.program3.huang.changyu.weltimetable.components.ActivityWebView;

/**
 * Created by changyu on 20.05.2019.
 * email：alexchyandroid@gmail.com
 */
public class BrowserUtil {
    public static void openUrlInChromeCustomTab(Context context, String url) {
        try {
            Intent intent = new Intent(context, ActivityWebView.class);
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
