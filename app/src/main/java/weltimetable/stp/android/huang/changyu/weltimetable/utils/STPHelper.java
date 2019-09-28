package weltimetable.stp.android.huang.changyu.weltimetable.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
        date.setTime(System.currentTimeMillis());
        c.setTime(date);
        return c.get(Calendar.YEAR) + "";
    }

    public static String getSemaster() {

        return "1";
    }

    public static String getWeekofyear() {
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR) + "";
    }

    private static String weekofyear;

    public static void toast(Context context, String message) {
        if (context == null || message == null) return;
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);

        toast.show();
    }

    public static void startActivity(Context context, Class activityClass) {
        Intent intent = new Intent(context, activityClass);
        context.startActivity(intent);
    }

    public static TimeTableInfo getUnAssignedItem() {
        TimeTableInfo timetableinfo = new TimeTableInfo();
        timetableinfo.setSubject(ConstentValue.UNASIGNED);
        timetableinfo.setTeacher("");
        timetableinfo.setRoom("");
        timetableinfo.setStatus("0");
        timetableinfo.setDuration(String.format("%02d Hour", 1));
        return timetableinfo;
    }

    public static ArrayList<String> getDayOfWeekList() {
        ArrayList<String> list = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        // dayofweek
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        if (c.getFirstDayOfWeek() == Calendar.SUNDAY) {
            c.add(Calendar.DAY_OF_MONTH, 1);
        }
        // calculate date
        c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);

        for (int i = 1; i <= 7; i++) {
            c.add(Calendar.DAY_OF_MONTH, 1);
            list.add(sdf.format(c.getTime()));
        }
//        list.add("Monday");
//        list.add("Tuesday");
//        list.add("Wednesday");
//        list.add("Thursday");
//        list.add("Friday");
        return list;
    }

    public static String getTodayDate() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return sdf.format(c.getTime());
    }



    public static String getDateof(int dow) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, dow);
        return sdf.format(c.getTime());
    }

    public static boolean isEmpty(String title) {
        return  title==null||title.equals("");
    }
}
