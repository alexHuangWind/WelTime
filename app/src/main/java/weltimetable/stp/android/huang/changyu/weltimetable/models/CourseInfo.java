package weltimetable.stp.android.huang.changyu.weltimetable.models;

import java.util.ArrayList;

public class CourseInfo {
    private String courseName;
    private String courseID;
    private String tutor;
    private String endWeekOfYear;
    private ArrayList<CourseEvent> events;
    private int quantity;

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
