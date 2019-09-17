package weltimetable.stp.android.huang.changyu.weltimetable.models;

public class CalendarInfo {

    /*
    *  values.put(CalendarContract.Events.DTSTART, startMillis);
        values.put(CalendarContract.Events.DTEND, endMillis);
        values.put(CalendarContract.Events.TITLE, eventTitle);
        values.put(CalendarContract.Events.DESCRIPTION, "Group workout");
        values.put(CalendarContract.Events.CALENDAR_ID, calID);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "America/Los_Angeles");
        values.put(CalendarContract.Events.ORGANIZER, "google_calendar@gmail.com");
    * */

    private Long startMillis;
    private Long endMillis;
    private String eventTitle;
    private String Description;
    private Long CalID;
    private String EventTimezone;
    private String Organizer;

    public CalendarInfo(Long startMillis, Long endMillis, String eventTitle, String description, Long calID, String eventTimezone, String organizer) {
        this.startMillis = startMillis;
        this.endMillis = endMillis;
        this.eventTitle = eventTitle;
        Description = description;
        CalID = calID;
        EventTimezone = eventTimezone;
        Organizer = organizer;
    }

    public Long getStartMillis() {
        return startMillis;
    }

    public void setStartMillis(Long startMillis) {
        this.startMillis = startMillis;
    }

    public Long getEndMillis() {
        return endMillis;
    }

    public void setEndMillis(Long endMillis) {
        this.endMillis = endMillis;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Long getCalID() {
        return CalID;
    }

    public void setCalID(Long calID) {
        CalID = calID;
    }

    public String getEventTimezone() {
        return EventTimezone;
    }

    public void setEventTimezone(String eventTimezone) {
        EventTimezone = eventTimezone;
    }

    public String getOrganizer() {
        return Organizer;
    }

    public void setOrganizer(String organizer) {
        Organizer = organizer;
    }
}
