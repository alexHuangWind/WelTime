package weltimetable.stp.android.huang.changyu.weltimetable.utils;

import weltimetable.stp.android.huang.changyu.weltimetable.models.TimeTableInfo;

public class CalendarController {
    private static CalendarController INSTANCE;

    private CalendarController() {
    }

    public static CalendarController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CalendarController();
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
}
