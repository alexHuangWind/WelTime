package weltimetable.stp.android.huang.changyu.weltimetable.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import weltimetable.stp.android.huang.changyu.weltimetable.models.TimeTableInfo;

public class STPHelper {
    private static String year;
    private static String semaster;

    public static String getYear() {
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        date.setTime( System.currentTimeMillis());
        c.setTime(date);
        return c.get(Calendar.YEAR)+"";
    }

    public static String getSemaster() {

        return "1";
    }

    public static String getWeekofyear() {
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        date.setTime( System.currentTimeMillis());
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR)+"";
    }

    private static String weekofyear;
    public static void toast(Context context,String message) {
        if(context==null||message==null)return;
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);

        toast.show();
    }
    public static void startActivity(Context context,Class activityClass){
        Intent intent = new Intent(context, activityClass);
        context.startActivity(intent);
    }

    public static TimeTableInfo getUnAssignedItem() {
        TimeTableInfo timetableinfo = new TimeTableInfo();
        timetableinfo.setSubject("UNASSIGNED");
        timetableinfo.setTeacher("");
        timetableinfo.setRoom("");
        timetableinfo.setStatus("0");
        timetableinfo.setDuration(String.format("(%02d Hours)", 1));
        return timetableinfo;
    }

    public static ArrayList<String> getDayOfWeekList() {
        ArrayList<String> list =  new ArrayList();
        list.add("Monday");
        list.add("Tuesday");
        list.add("Wednesday");
        list.add("Thursday");
        list.add("Friday");
        list.add("Saturday");
        list.add("Sunday");
        return list;
    }

    public static String getPicFromPath(Activity activity, String glassbreak) {
        return activity.getFileStreamPath(glassbreak).getAbsolutePath();
    }
}
