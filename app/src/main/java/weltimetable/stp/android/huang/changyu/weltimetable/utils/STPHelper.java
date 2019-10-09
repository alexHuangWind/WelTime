package weltimetable.stp.android.huang.changyu.weltimetable.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import weltimetable.stp.android.huang.changyu.weltimetable.models.CourseEvent;
import weltimetable.stp.android.huang.changyu.weltimetable.models.CourseInfo;
import weltimetable.stp.android.huang.changyu.weltimetable.models.TimeTableInfo;
import weltimetable.stp.android.huang.changyu.weltimetable.models.UserInfo;

public class STPHelper {
    private static String year;
    private static String semaster;
    private static Context mContext;
    private static STPHelper INSTANCE;

    private STPHelper() {
    }

    public static STPHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new STPHelper();
        }

        return INSTANCE;
    }

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
    public static void saveAssmtInfo(Context context, CourseInfo info) {
        Gson g = new Gson();
        info.setSaveDate(Integer.parseInt(getWeekofyear()));
        SharedPrefsUtils.setStringPreference(context, SharedPrefsUtils.ASSIGNMENTS, g.toJson(info));
    }
    public static CourseInfo getAssmtInfo(Context context) {
        Gson g = new Gson();
        String str = SharedPrefsUtils.getStringPreference(context, SharedPrefsUtils.ASSIGNMENTS);
        if(str==null){
            return null;
        }
        CourseInfo info = g.fromJson(str,CourseInfo.class);
        return info;
    }
    public static void saveCourseInfo(Context context, CourseInfo info) {
        Gson g = new Gson();
        SharedPrefsUtils.setStringPreference(context, SharedPrefsUtils.COURSEINFO, g.toJson(info));
    }
    public static CourseInfo getCourseInfo(Context context) {
        Gson g = new Gson();
        String str = SharedPrefsUtils.getStringPreference(context, SharedPrefsUtils.COURSEINFO);
        if(str==null){
            return null;
        }

        CourseInfo info = g.fromJson(str,CourseInfo.class);
        return info;
    }

    public void setmContext(Context mContext) {
        STPHelper.mContext = mContext;
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
        timetableinfo.setSubject(ConstentValue.UNASSIGNED);
        timetableinfo.setTeacher("");
        timetableinfo.setRoom("");
        timetableinfo.setStatus("0");
        timetableinfo.setWeekofyear(STPHelper.getWeekofyear());
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

        for (int i = 1; i <= 5; i++) {
            c.add(Calendar.DAY_OF_MONTH, 1);
            list.add(sdf.format(c.getTime()));
        }
        return list;
    }

    public static ArrayList<String> getFragmentList() {
        ArrayList<String> list = new ArrayList();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar c = Calendar.getInstance();
//        // dayofweek
//        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
//
//        if (c.getFirstDayOfWeek() == Calendar.SUNDAY) {
//            c.add(Calendar.DAY_OF_MONTH, 1);
//        }
//        // calculate date
//        c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);
//
//        for (int i = 1; i <= 5; i++) {
//            c.add(Calendar.DAY_OF_MONTH, 1);
//            list.add(sdf.format(c.getTime()));
//        }
        list.add("Monday");
        list.add("Tuesday");
        list.add("Wednesday");
        list.add("Thursday");
        list.add("Friday");
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
        return title == null || title.equals("");
    }

    /*
    *      list.add("Monday");
            list.add("Tuesday");
            list.add("Wednesday");
            list.add("Thursday");
            list.add("Friday");*/
    public static String Fragment2String(String fragment) {
        String row = "";
        switch (fragment) {
            case ("Monday"):
                row = "1";
                break;
            case ("Tuesday"):
                row = "2";
                break;

            case ("Wednesday"):
                row = "3";
                break;
            case ("Thursday"):
                row = "4";
                break;
            case ("Friday"):
                row = "5";
                break;
            default:
                row = "6";
        }

        return row;
    }

    public static String FromTime2String(String fromTime) {
        String res = "";
        switch (fromTime) {
            case ("08:00"):
                res = "1";
                break;
            case ("09:00"):
                res = "2";
                break;


            case ("10:00"):
                res = "3";
                break;
            case ("11:00"):
                res = "4";
                break;
            case ("12:00"):
                res = "5";
                break;
            case ("13:00"):
                res = "6";
                break;
            case ("14:00"):
                res = "7";
                break;
            case ("15:00"):
                res = "8";
                break;
            case ("16:00"):
                res = "9";
                break;
            case ("17:00"):
                res = "10";
                break;
            case ("18:00"):
                res = "11";
                break;
            default:
                res = "12";
        }

        return res;
    }

    /*
    *      list.add("Monday");
            list.add("Tuesday");
            list.add("Wednesday");
            list.add("Thursday");
            list.add("Friday");*/
    public static String String2Fragment(String fragment) {
        String row = "";
        switch (fragment) {
            case ("1"):
                row = "Monday";
                break;
            case ("2"):
                row = "Tuesday";
                break;
            case ("3"):
                row = "Wednesday";
                break;
            case ("4"):
                row = "Thursday";
                break;
            case ("5"):
                row = "Friday";
                break;
            default:
                row = "6";
        }

        return row;
    }

    public static String String2FromTime(String fromTime) {
        String res = "";
        switch (fromTime) {
            case ("1"):
                res = "8:00";
                break;
            case ("2"):
                res = "9:00";
                break;

            case ("3"):
                res = "10:00";
                break;
            case ("4"):
                res = "11:00";
                break;
            case ("5"):
                res = "12:00";
                break;
            case ("6"):
                res = "13:00";
                break;
            case ("7"):
                res = "14:00";
                break;
            case ("8"):
                res = "15:00";
                break;
            case ("9"):
                res = "16:00";
                break;
            case ("10"):
                res = "17:00";
                break;
            case ("11"):
                res = "18:00";
                break;
            default:
                res = "12";
        }

        return res;
    }

    public static long getTimeMillisByTiem(String dateFormat) {
        Date date = new Date();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm yyyy-MM-dd");
            date = formatter.parse(dateFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public Context getContext() {
        return mContext;
    }

    public UserInfo getUserInfo() {
        String st = SharedPrefsUtils.getStringPreference(getContext(), SharedPrefsUtils.USERINFO);
        Gson g = new Gson();
        UserInfo info = g.fromJson(st, UserInfo.class);
        return info;
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static void parseCourseingoSaveAssinfo(Context context, CourseInfo info) {
        CourseInfo Assignmentinfo = new CourseInfo();
        ArrayList<CourseEvent> el = new ArrayList<>();
        for (CourseEvent event : info.getEvents()) {
            if (!event.getIsClass()) {
                TimeTableInfo ttinfo = new TimeTableInfo();
                ttinfo.setItemID(event.getStartTime() + STPHelper.getDateof(event.getDayOfWeek()));
                el.add(event);
            }
        }
        Assignmentinfo.setEvents(el);
        STPHelper.saveAssmtInfo(context,Assignmentinfo);

    }

}
