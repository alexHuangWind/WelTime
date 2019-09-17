package weltimetable.stp.android.huang.changyu.weltimetable.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class STPHelper {

    public static void toast(Context context,String message) {
        if(context==null||message==null)return;
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);

        toast.show();
    }
    public static void startActivity(Context context,Class activityClass){
        Intent intent = new Intent(context, activityClass);
        context.startActivity(intent);
    }
}
