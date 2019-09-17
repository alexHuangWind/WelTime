package weltimetable.stp.android.huang.changyu.weltimetable.models;

/**
 * Created by changyu on 20.05.stp.
 * email：alexchyandroid@gmail.com
 */
public class TimeTableInfo {

    private String subject;
    private String fragment;
    private String teacher;
    private String room;
    private String fromtime;
    private String totime;
    private String time;
    private String process;

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status="111";
    private int id, color;

    public TimeTableInfo() {
    }

    public TimeTableInfo(String subject, String teacher, String room, String fromtime, String totime, int color) {
        this.subject = subject;
        this.totime = totime;
        this.fromtime = fromtime;
        this.teacher = teacher;
        this.room = room;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }

    public String getFromTime() {
        return fromtime;
    }

    public void setFromTime(String fromtime) {
        this.fromtime = fromtime;
    }

    public String getToTime() {
        return totime;
    }

    public void setToTime(String totime) {
        this.totime = totime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String toString() {
        return subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
