package weltimetable.stp.android.huang.changyu.weltimetable.models;

import java.util.Date;

public class CourseEvent {
    private String EventName = "undefined";
    private int quantity = -1;
    private String startTime = "undefined";
    private String TutorName = "undefined";
    private String CourseName = "undefined";
    private int dayOfWeek = -1;
    private String Location = "undefined";

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public String getTutorID() {
        return TutorID;
    }

    public void setTutorID(String tutorID) {
        TutorID = tutorID;
    }

    public int getStartWeek() {
        return StartWeek;
    }

    public void setStartWeek(int startWeek) {
        StartWeek = startWeek;
    }

    public int getEndWeek() {
        return EndWeek;
    }

    public void setEndWeek(int endWeek) {
        EndWeek = endWeek;
    }

    private boolean isClass = true;
    private String finalExam = "";
//    private CourseInfo parent;

    private String CourseCode = "undefined";
    private String Major = "undefined";
    private String TutorID = "undefined";
    private int StartWeek = -1;
    private int EndWeek = -1;


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

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        this.Location = location;
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

    public String getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(String finalExam) {
        this.finalExam = finalExam;
    }

    public String getTutorName() {
        return TutorName;
    }

    public void setTutorName(String tutorName) {
        this.TutorName = tutorName;
    }
    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        this.CourseName = courseName;
    }
}
