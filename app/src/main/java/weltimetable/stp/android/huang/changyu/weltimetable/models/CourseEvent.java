package weltimetable.stp.android.huang.changyu.weltimetable.models;

public class CourseEvent {
    private String EventName;
    private int quantity = 1;
    private String startTime;
    private int dayOfWeek;
    private String classRoom = "undefined";
    private boolean isClass;

    public CourseEvent(CourseInfo info) {
        this.parent = info;
    }

    public CourseInfo getParent() {
        return parent;
    }

    public void setParent(CourseInfo parent) {
        this.parent = parent;
    }

    private CourseInfo parent;

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
}
