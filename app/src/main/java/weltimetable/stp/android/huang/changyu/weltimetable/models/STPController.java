package weltimetable.stp.android.huang.changyu.weltimetable.models;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.bean.LoginBeanResp;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.http.HttpCallback;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.http.HttpHelper;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.ConstentValue;

public class STPController {
    private static STPController INSTANCE;

    private ArrayList<CourseEvent> eventlist;
    private CourseInfo info;

    private STPController() {
    }

    public static STPController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new STPController();
        }

        return INSTANCE;
    }


    public boolean UpdateByID(String id) {
        return true;
    }

    public boolean insertItem(TimeTableInfo info) {
        return true;
    }

    public boolean getReportInfo() {
        return true;
    }

    public boolean UpLoadCalandar() {
        return true;
    }

    public ArrayList<TimeTableInfo> getAllItemsOfthisWeek() {

        return new ArrayList();
    }

    public CourseInfo getCourseInfo( String courseCode) {
        final HashMap getCourseCode = new HashMap<>();
        getCourseCode.put("courseCode", courseCode);
        //Login
        HttpHelper.obtain().post(ConstentValue.BASIC_URL + "/Login",
                getCourseCode, new HttpCallback<LoginBeanResp>() {
                    @Override
                    public void onSuccess(LoginBeanResp loginBean) {

                    }
                    @Override
                    public void onFailed(String string) {
                        Log.d("alexTimeTable: ", string);

                    }
                });
        if (courseCode != null && !courseCode.equals("12345")) {
            return null;
        }
        if (info != null) {
            return info;
        }
        ArrayList<CourseInfo> list = new ArrayList();
        info = new CourseInfo();
        info.setCourseID("It7343");
        info.setCourseName("PM");
        info.setTutor("Robert");
        eventlist = new ArrayList<CourseEvent>();
        CourseEvent event = new CourseEvent(info);
        eventlist.add(event);
        event.setCourseName("PM");
        event.setTutorName("Robert");
        event.setEventName("Reading");
        event.setQuantity(2);
        event.setClass(false);
        CourseEvent event2 = new CourseEvent(info);
        eventlist.add(event2);
        event2.setCourseName("PM");
        event2.setTutorName("Robert");
        event2.setEventName("assignment1");
        event2.setQuantity(4);
        event2.setClass(false);
        CourseEvent event3 = new CourseEvent(info);
        eventlist.add(event3);
        event3.setCourseName("PM");
        event3.setTutorName("Robert");
        event3.setEventName("assignment2");
        event3.setQuantity(4);
        event3.setClass(false);
        CourseEvent classInfo1 = new CourseEvent(info);
        classInfo1.setEventName(info.getCourseName());
        classInfo1.setLocation("B201");
        classInfo1.setStartTime("08:00");
        classInfo1.setDayOfWeek(Calendar.TUESDAY);
        classInfo1.setQuantity(4);
        classInfo1.setCourseName("PM");
        classInfo1.setTutorName("Robert");
        classInfo1.setClass(true);
        CourseEvent classInfo2 = new CourseEvent(info);
        classInfo2.setEventName(info.getCourseName());
        classInfo2.setCourseName("PM");
        classInfo2.setTutorName("Robert");
        classInfo2.setLocation("B101");
        classInfo2.setStartTime("09:00");
        classInfo2.setDayOfWeek(Calendar.WEDNESDAY);
        classInfo2.setQuantity(4);
        classInfo2.setClass(true);
        eventlist.add(classInfo1);
        eventlist.add(classInfo2);
        info.setEndWeekOfYear("12");
        info.setQuantity(3);
        info.setEvents(eventlist);
        list.add(info);
//        try {
//            Gson gson = new Gson();
//            String json3 = gson.toJson(list);
//            Log.d("","",null);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//
        return info;
    }

}
