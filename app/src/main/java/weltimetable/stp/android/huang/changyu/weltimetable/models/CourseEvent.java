package weltimetable.stp.android.huang.changyu.weltimetable.models;

import java.util.Date;

public class CourseEvent {
    private String EventName = "undefined";
    private int quantity = -1;
    private String startTime = "undefined";


    private String tutor = "undefined";


    private String courseName = "undefined";
    private int dayOfWeek = -1;
    private String classRoom = "undefined";
    private boolean isClass = true;
    private Date finalExam = new Date();
//    private CourseInfo parent;


    public CourseEvent(CourseInfo info) {
//        this.parent = info;
    }

//    public CourseInfo getParent() {
//        return parent;
//    }
//
//    public void setParent(CourseInfo parent) {
//        this.parent = parent;
//    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getIsClass() {
        return isClass;
    }

    public void setClass(boolean aClass) {
        isClass = aClass;
    }

    public boolean isClass() {
        return isClass;
    }

    public Date getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(Date finalExam) {
        this.finalExam = finalExam;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
