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
    private String duration;
    private String time;
    private int process;
    private String semastor;
    private String weekofyear;
    private String year;
    private String status;
    private int id, color;


    public TimeTableInfo() {
    }



    public TimeTableInfo(int id,String subject, String teacher, String room, String fromtime, String duration, int color, int progress) {
        this.id = id;
        this.subject = subject;
        this.duration = duration;
        this.fromtime = fromtime;
        this.teacher = teacher;
        this.room = room;
        this.color = color;
        this.process = progress;

    }


    public void setFromTime(String fromtime) {
        this.fromtime = fromtime;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSemastor() {
        return semastor;
    }

    public void setSemastor(String semastor) {
        this.semastor = semastor;
    }

    public String getWeekofyear() {
        return weekofyear;
    }

    public void setWeekofyear(String weekofyear) {
        this.weekofyear = weekofyear;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

}
