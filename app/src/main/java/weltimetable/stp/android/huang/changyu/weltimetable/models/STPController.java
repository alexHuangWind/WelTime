package weltimetable.stp.android.huang.changyu.weltimetable.models;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Calendar;

public class STPController {
    private static STPController INSTANCE;

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

    public CourseInfo getCourseInfo(String courseCode) {
        ArrayList<CourseInfo> list = new ArrayList();
        CourseInfo info = new CourseInfo();
        info.setCourseID("It7343");
        info.setCourseName("PM");
        info.setTutor("Robert");
        ArrayList<CourseEvent> eventlist = new ArrayList<CourseEvent>();
        CourseEvent event = new CourseEvent(info);
        eventlist.add(event);
        event.setEventName("Reading");
        event.setQuantity(2);
        event.setClass(false);
        CourseEvent event2 = new CourseEvent(info);
        eventlist.add(event2);
        event2.setEventName("assignment1");
        event2.setQuantity(4);
        event2.setClass(false);
        CourseEvent event3 = new CourseEvent(info);
        eventlist.add(event3);
        event3.setEventName("assignment2");
        event3.setQuantity(4);
        event3.setClass(false);
        CourseEvent classInfo1 = new CourseEvent(info);
        classInfo1.setEventName(info.getCourseName());
        classInfo1.setClassRoom("B201");
        classInfo1.setStartTime("08:00");
        classInfo1.setDayOfWeek(Calendar.TUESDAY);
        classInfo1.setQuantity(4);
        classInfo1.setClass(true);

        CourseEvent classInfo2 = new CourseEvent(info);
        classInfo2.setEventName(info.getCourseName());
        classInfo2.setClassRoom("B101");
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
        if (courseCode != null && courseCode.equals("12345")) {
            return info;
        }
        return new CourseInfo();
    }

    public boolean syncCalendar(Activity activity, TimeTableInfo info) {
//       Long startMillis = info.getFromTime();
//        Long endMillis = endMillis;
//        String eventTitle = eventTitle;
//        String Description = description;
//        Long CalID = calID;
//        String EventTimezone = eventTimezone;
//        String  Organizer = organizer;
//        CalendarInfo calendarInfo = new CalendarInfo();
//        CalendarUtil.getInstance().addEvent(activity,);
        return true;
    }

}
