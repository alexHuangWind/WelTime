package weltimetable.stp.android.huang.changyu.weltimetable.models;

import java.util.ArrayList;

public class CourseInfo {
    private String courseName = "undefined";
    private String courseID = "undefined";
    private String tutor = "undefined";
    private String endWeekOfYear = "undefined";

    public String getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(String finalExam) {
        this.finalExam = finalExam;
    }

    private String finalExam = "undefined";

    public long getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(int saveDate) {
        this.saveDate = saveDate;
    }


    private int saveDate = -1;

    private ArrayList<CourseEvent> events = new ArrayList<>();
    private int quantity = -1;

    public ArrayList<CourseEvent> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<CourseEvent> events) {
        this.events = events;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }


    public String getEndWeekOfYear() {
        return endWeekOfYear;
    }

    public void setEndWeekOfYear(String endWeekOfYear) {
        this.endWeekOfYear = endWeekOfYear;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void removeEvent(CourseEvent selectedEvent) {
        events.remove(selectedEvent);
    }



}
