package weltimetable.stp.android.huang.changyu.weltimetable.models;

import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.CourseInfoListener;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.bean.BasicResp;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.bean.CourseInfoResp;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.http.HttpCallback;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.http.HttpHelper;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httprequest.interfaces.IRequestCallback;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httprequest.manager.VolleyRequestManager;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.ConstentValue;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.STPHelper;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.SharedPrefsUtils;

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

    public CourseInfo getCourseInfo(String courseCode, CourseInfoListener listener) {


//        //====
//        final HashMap getCourseCode = new HashMap<>();
//        UserInfo userInfo = STPHelper.getInstance().getUserInfo();
//        getCourseCode.put("courseCode", courseCode.toUpperCase());
//        getCourseCode.put("token", userInfo.getToken());
//        getCourseCode.put("studentID", userInfo.getStudentID());
//        HttpHelper.obtain().post(ConstentValue.BASIC_URL + "/AddCourdeCode",
//                getCourseCode, new HttpCallback<BasicResp>() {
//                    @Override
//                    public void onSuccess(BasicResp resp) {
//                        String str = resp.toString().toLowerCase();
//                        if (str.contains("err")) {
//                            onFailed("Err");
//                            return;
//                        }
//                        ParseResp(resp, listener);
//                    }
//
//                    @Override
//                    public void onFailed(String string) {
//                        Log.d("alexTimeTable: ", string);
//
//                    }
//                });
//        //====

//        if (courseCode != null && !courseCode.equals("IT001")) {
//            return null;
//        }
//        if (info != null) {
//            return info;
//        }
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
        listener.methodToCallBack(getDefaultInfo());
        return info;
    }

    private CourseInfo getDefaultInfo() {
        Gson g = new Gson();
        String v = "{\"courseID\":\"IT001\",\"finalExam\":\"Aug 8, 2019 19:09:50\",\"courseName\":\"ProjectManagement\",\"endWeekOfYear\":\"undefined\",\"events\":[{\"CourseCode\":\"undefined\",\"CourseName\":\"ProjectManagement\",\"EndWeek\":52,\"EventName\":\"ProjectManagement\",\"Location\":\"Petone\",\"Major\":\"undefined\",\"StartWeek\":1,\"TutorID\":\"undefined\",\"TutorName\":\"Robert\",\"dayOfWeek\":3,\"finalExam\":\"Aug 8, 2019 19:09:50\",\"isClass\":true,\"quantity\":2,\"startTime\":\"9:00\"},{\"CourseCode\":\"undefined\",\"CourseName\":\"ProjectManagement\",\"EndWeek\":-1,\"EventName\":\"DeadLine Reading \",\"Location\":\"undefined\",\"Major\":\"undefined\",\"StartWeek\":-1,\"TutorID\":\"undefined\",\"TutorName\":\"Robert\",\"dayOfWeek\":-1,\"finalExam\":\"Aug 8, 2019 19:09:50\",\"isClass\":false,\"quantity\":2,\"startTime\":\"undefined\"},{\"CourseCode\":\"undefined\",\"CourseName\":\"ProjectManagement\",\"EndWeek\":-1,\"EventName\":\"Reading Book\",\"Location\":\"undefined\",\"Major\":\"undefined\",\"StartWeek\":-1,\"TutorID\":\"undefined\",\"TutorName\":\"Robert\",\"dayOfWeek\":-1,\"finalExam\":\"Aug 8, 2019 19:09:50\",\"isClass\":false,\"quantity\":2,\"startTime\":\"undefined\"},{\"CourseCode\":\"undefined\",\"CourseName\":\"ProjectManagement\",\"EndWeek\":52,\"EventName\":\"ProjectManagement\",\"Location\":\"Petone\",\"Major\":\"undefined\",\"StartWeek\":1,\"TutorID\":\"undefined\",\"TutorName\":\"Robert\",\"dayOfWeek\":5,\"finalExam\":\"Aug 8, 2019 19:09:50\",\"isClass\":true,\"quantity\":2,\"startTime\":\"11:00\"}],\"quantity\":-1,\"tutor\":\"Robert\"}";
        v=v.replaceAll("8:00","08:00");
        v=v.replaceAll("9:00","09:00");
        CourseInfo info2 = g.fromJson(v,CourseInfo.class);
        return info2;
    }

    private void ParseResp(BasicResp resp, CourseInfoListener listener) {
        info = new CourseInfo();
        eventlist = new ArrayList<CourseEvent>();
        int i = 0;
        for (Object ci : resp) {

            CourseInfoResp ciresp = parsecitoCoursere(ci);
            info.setCourseID(ciresp.getCourseCode());
            info.setCourseName(ciresp.getCourseName());
            info.setTutor(ciresp.getTutorName());
            info.setEvents(eventlist);
            info.setFinalExam(ciresp.getFinalExam());
            CourseEvent classInfo = new CourseEvent(info);
            classInfo.setEventName(ciresp.getCourseName());
            classInfo.setLocation(ciresp.getLocation());
            classInfo.setStartTime(ciresp.getStartTime());
            classInfo.setDayOfWeek(ciresp.getDayInWeek());
            classInfo.setQuantity(ciresp.getDuration());
            classInfo.setCourseName(ciresp.getCourseName());
            classInfo.setTutorName(ciresp.getTutorName());
            classInfo.setClass(true);
            classInfo.setFinalExam(ciresp.getFinalExam());
            eventlist.add(classInfo);
            if (i == 0) {
                if (!ciresp.getAssignment1Name().equals(" ")) {
                    eventlist.add(ParseAssignement(ciresp, ciresp.getAssignment1Name(), ciresp.getAssignment1Hours()));
                }
                if (!ciresp.getAssignment2Name().equals(" ")) {
                    eventlist.add(ParseAssignement(ciresp, ciresp.getAssignment2Name(), ciresp.getAssignment2Hours()));
                }
                if (!ciresp.getAssignment3Name().equals(" ")) {
                    eventlist.add(ParseAssignement(ciresp, ciresp.getAssignment3Name(), ciresp.getAssignment3Hours()));
                }
                if (!ciresp.getAssignment4Name().equals(" ")) {
                    eventlist.add(ParseAssignement(ciresp, ciresp.getAssignment4Name(), ciresp.getAssignment4Hours()));
                }
                if (!ciresp.getAssignment5Name().equals(" ")) {
                    eventlist.add(ParseAssignement(ciresp, ciresp.getAssignment5Name(), ciresp.getAssignment5Hours()));
                }
            }
            i++;
        }

       listener.methodToCallBack(info);
    }

    private CourseInfoResp parsecitoCoursere(Object ci) {
        //            "CourseCode" = IT001,
//                    CourseName = ProjectManagement,
//                    Majoy = IT,
//                    Location = Petone,
//                    TutorID = 300001.0,
//                    TutorName = Robert,
//                    Assignment1Name = DeadLine Reading,
//                    Assignment1Hours = 2.0,
//                    Assignment2Name = Reading Book,
//                    Assignment2Hours = 2.0,
//                    Assignment3Name = ,
//                    Assignment3Hours = 0.0,
//                    Assignment4Name = ,
//                    Assignment4Hours = 0.0,
//                    Assignment5Name = ,
//                    Assignment5Hours = 0.0,
//                    EventName = Final Exam,
//                    EventDate = 2019 - 11 - 01 T00: 00: 00,
//                    Year_ = 2019.0,
//                    Semester = 2.0,
//                    StartWeek = 12.0,
//                    EndWeek = 36.0,
//                    BreakWeek = 17,
//                    18,
//                    ClassRoom = C305,
//                    StartTime = 9: 00,
//                    Duration = 2.0,
//                    DayInWeek = 3.0
        CourseInfoResp cr = new CourseInfoResp();
        LinkedTreeMap ltm = (LinkedTreeMap) ci;
        cr.setCourseCode(ltm.get("CourseCode") + "");
        cr.setCourseName(ltm.get("CourseName") + "");
        cr.setMajoy(ltm.get("Majoy") + "");
        cr.setClassRoom(ltm.get("ClassRoom") + "");
        cr.setLocation(ltm.get("Location") + "");
//        cr.setTutorID(Integer.parseInt(ltm.get("TutorID")+""));
        cr.setTutorName(ltm.get("TutorName") + "");
        cr.setFinalExam(ltm.get("finalExam") + "");
        cr.setStartWeek((int) Double.parseDouble((ltm.get("StartWeek") + "")));
        cr.setEndWeek((int) Double.parseDouble(ltm.get("EndWeek") + ""));
        cr.setStartTime(ltm.get("StartTime") + "");
        cr.setDayInWeek((int) Double.parseDouble(ltm.get("DayInWeek") + ""));
        cr.setDuration((int) Double.parseDouble(ltm.get("Duration") + ""));
        cr.setAssignment1Name(ltm.get("Assignment1Name") + "");
        cr.setAssignment1Hours((int) Double.parseDouble(ltm.get("Assignment1Hours") + ""));
        cr.setAssignment2Name(ltm.get("Assignment2Name") + "");
        cr.setAssignment2Hours((int) Double.parseDouble(ltm.get("Assignment2Hours") + ""));
        cr.setAssignment3Name(ltm.get("Assignment3Name") + "");
        cr.setAssignment3Hours((int) Double.parseDouble(ltm.get("Assignment3Hours") + ""));
        cr.setAssignment4Name(ltm.get("Assignment4Name") + "");
        cr.setAssignment4Hours((int) Double.parseDouble(ltm.get("Assignment4Hours") + ""));
        cr.setAssignment5Name(ltm.get("Assignment5Name") + "");
        cr.setAssignment5Hours((int) Double.parseDouble(ltm.get("Assignment5Hours") + ""));
        return cr;
    }

    public CourseInfo getCourseInfo() {

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
        classInfo1.setStartWeek(1);
        classInfo1.setEndWeek(52);
        classInfo1.setClass(true);
        CourseEvent classInfo2 = new CourseEvent(info);
        classInfo2.setEventName(info.getCourseName());
        classInfo2.setCourseName("PM");
        classInfo2.setTutorName("Robert");
        classInfo2.setLocation("B101");
        classInfo2.setStartTime("09:00");
        classInfo2.setDayOfWeek(Calendar.WEDNESDAY);
        classInfo2.setQuantity(4);
        classInfo2.setStartWeek(1);
        classInfo2.setEndWeek(52);
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

    private CourseEvent ParseAssignement(CourseInfoResp ciresp, String assignment1Name, int assignment1Hours) {
        CourseEvent event = new CourseEvent(info);
        event.setCourseName(ciresp.getCourseName());
        event.setTutorName(ciresp.getTutorName());
        event.setEventName(assignment1Name);
        event.setQuantity(assignment1Hours);
        event.setClass(false);
        return event;


    }

}
