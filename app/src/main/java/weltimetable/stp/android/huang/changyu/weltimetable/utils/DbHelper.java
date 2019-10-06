package weltimetable.stp.android.huang.changyu.weltimetable.utils;

import weltimetable.stp.android.huang.changyu.weltimetable.components.activitys.TimeTableActivity;
import weltimetable.stp.android.huang.changyu.weltimetable.models.CourseEvent;
import weltimetable.stp.android.huang.changyu.weltimetable.models.CourseInfo;
import weltimetable.stp.android.huang.changyu.weltimetable.models.TimeTableInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by changyu on 20.05.stp.
 * email：alexchyandroid@gmail.com
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 6;
    private static final String DB_NAME = "weltimeTable";
    private static final String TIMETABLE = "timetable";
    private static final String TimeTableInfo_ID = "id";
    private static final String TimeTableInfo_SUBJECT = "subject";
    private static final String TimeTableInfo_FRAGMENT = "fragment";
    private static final String TimeTableInfo_TEACHER = "teacher";
    private static final String TimeTableInfo_ROOM = "room";
    private static final String TimeTableInfo_FROM_TIME = "fromtime";
    private static final String TimeTableInfo_DURATION = "duration";
    private static final String TimeTableInfo_COLOR = "color";
    private static final String TimeTableInfo_PROCESS = "process";
    private static final String TimeTableInfo_STATUS = "status";
    private static final String TimeTableInfo_SEMESTER = "semester";
    private static final String TimeTableInfo_WOY = "weekofyear";
    private static final String TimeTableInfo_YEAR = "year";
    private static final String TIMETABLEINFO_FROMTIMEMILLIS = "fromtimemillis";
    //date+startTime
    private static final String ITEMID = "itemid";
    private static final String TimeTableInfo_Date = "date";


    //courseInfo
    private static final String COURSEINFO = "courseinfo";
    private static final String COURSEINFO_ID = "id";
    private static final String COURSEINFO_EVENT = "event";
    private static final String COURSEINFO_COURSEID = "courseid";
    private static final String COURSEINFO_COURSE_ENDDATE = "enddate";
    private static final String COURSEINFO_QUANTITY = "quantity";


    //Block
    public static final String BLOCK = "block";
    private static final String BLOCK_INFO = "binfo";

    //dayofweek+starttime
    public static final String BLOCK_ID = "id";
    private static DbHelper INSTANCE;


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        //create table timetable
        String CREATE_TIMETABLE = "CREATE TABLE " + TIMETABLE + "("
                + TimeTableInfo_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TimeTableInfo_SUBJECT + " TEXT,"
                + TimeTableInfo_FRAGMENT + " TEXT,"
                + TimeTableInfo_TEACHER + " TEXT,"
                + ITEMID + " TEXT UNIQUE,"
                + TimeTableInfo_ROOM + " TEXT,"
                + TimeTableInfo_FROM_TIME + " TEXT,"
                + TimeTableInfo_DURATION + " TEXT,"
                + TimeTableInfo_PROCESS + " INTEGER,"
                + TimeTableInfo_STATUS + " TEXT,"
                + TimeTableInfo_SEMESTER + " TEXT,"
                + TimeTableInfo_WOY + " TEXT,"
                + TimeTableInfo_Date + " TEXT,"
                + TimeTableInfo_YEAR + " TEXT,"
                + TIMETABLEINFO_FROMTIMEMILLIS + " LONG,"
                + TimeTableInfo_COLOR + " INTEGER" + ")";
        db.execSQL(CREATE_TIMETABLE);

        //course info
        String CREATE_COURSEINFO = "CREATE TABLE " + COURSEINFO + "("
                + COURSEINFO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COURSEINFO_EVENT + " TEXT,"
                + COURSEINFO_COURSEID + " TEXT,"
                + COURSEINFO_QUANTITY + " INTEGER,"
                + COURSEINFO_COURSE_ENDDATE + " TEXT" + ")";
        db.execSQL(CREATE_COURSEINFO);

        //block
        String CREATE_BLOCK = "CREATE TABLE " + BLOCK + "("
                + BLOCK_ID + " TEXT PRIMARY KEY,"
                + BLOCK_INFO + " TEXT" + ")";
        db.execSQL(CREATE_BLOCK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TIMETABLE);
        onCreate(db);
    }

    /**
     * Methods for TimeTableInfo fragments
     **/
    public void insertTimeTableInfo(TimeTableInfo timeTableInfo) {
        String ItemId = null;
        if (getTimeTableItemInfoByItemID(timeTableInfo.getItemID()) != null) {
            ItemId = getTimeTableItemInfoByItemID(timeTableInfo.getItemID()).getItemID();
        }

        if (ItemId == null) {
            SQLiteDatabase db = this.getWritableDatabase();
            try {
                db.insert(TIMETABLE, null, getContentValues(timeTableInfo));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close();
            }
        } else {
            updateTimeTableInfo(timeTableInfo);
        }

    }


    public void deleteTTInfoById(TimeTableInfo timetableinfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TIMETABLE, ITEMID + " = ? ", new String[]{String.valueOf(timetableinfo.getItemID())});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void updateTimeTableInfo(TimeTableInfo timeTableInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.update(TIMETABLE, getContentValues(timeTableInfo), ITEMID + " = '" + timeTableInfo.getItemID() + "'", null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            db.close();
        }
    }


    public void updateTimeTableProcessById(int id, int value) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(TimeTableInfo_PROCESS, value);
            db.update(TIMETABLE, contentValues, TimeTableInfo_ID + " = " + id, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public ArrayList<TimeTableInfo> getTimeTableInfoByDate(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<TimeTableInfo> TimeTableInfoList = new ArrayList<>();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM ( SELECT * FROM " + TIMETABLE + " ORDER BY " + TimeTableInfo_FROM_TIME + " ) WHERE " + TimeTableInfo_Date + " = '" + date + "'", null);
            while (cursor.moveToNext()) {
                TimeTableInfoList.add(setTimeTableInfo(cursor));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();

        }

        return TimeTableInfoList;
    }


    public TimeTableInfo getTimeTableItemInfoByItemID(String itemid) {
        SQLiteDatabase db = this.getWritableDatabase();
        TimeTableInfo timetableinfo = null;
        Cursor cursor = db.rawQuery("SELECT * FROM ( SELECT * FROM " + TIMETABLE + " ORDER BY " + TimeTableInfo_FROM_TIME + " ) WHERE " + ITEMID + " = '" + itemid + "'", null);

        try {
            if (cursor.moveToNext()) {
                timetableinfo = setTimeTableInfo(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return timetableinfo;
    }

    private TimeTableInfo setTimeTableInfo(Cursor cursor) {

        TimeTableInfo timetableinfo = new TimeTableInfo();
        timetableinfo.setId(cursor.getInt(cursor.getColumnIndex(TimeTableInfo_ID)));
        timetableinfo.setSubject(cursor.getString(cursor.getColumnIndex(TimeTableInfo_SUBJECT)));
        timetableinfo.setTeacher(cursor.getString(cursor.getColumnIndex(TimeTableInfo_TEACHER)));
        timetableinfo.setRoom(cursor.getString(cursor.getColumnIndex(TimeTableInfo_ROOM)));
        timetableinfo.setFragment(cursor.getString(cursor.getColumnIndex(TimeTableInfo_FRAGMENT)));
        timetableinfo.setFromTime(cursor.getString(cursor.getColumnIndex(TimeTableInfo_FROM_TIME)));
        timetableinfo.setDuration(cursor.getString(cursor.getColumnIndex(TimeTableInfo_DURATION)));
        timetableinfo.setColor(cursor.getInt(cursor.getColumnIndex(TimeTableInfo_COLOR)));
        timetableinfo.setProcess(cursor.getInt(cursor.getColumnIndex(TimeTableInfo_PROCESS)));
        timetableinfo.setStatus(cursor.getString(cursor.getColumnIndex(TimeTableInfo_STATUS)));
        timetableinfo.setSemastor(cursor.getString(cursor.getColumnIndex(TimeTableInfo_SEMESTER)));
        timetableinfo.setWeekofyear(cursor.getString(cursor.getColumnIndex(TimeTableInfo_WOY)));
        timetableinfo.setYear(cursor.getString(cursor.getColumnIndex(TimeTableInfo_YEAR)));
        timetableinfo.setItemID(cursor.getString(cursor.getColumnIndex(ITEMID)));
        timetableinfo.setFromtimeMillis(cursor.getLong(cursor.getColumnIndex(TIMETABLEINFO_FROMTIMEMILLIS)));
        timetableinfo.setDate(cursor.getString(cursor.getColumnIndex(TimeTableInfo_Date)));
        return timetableinfo;
    }

    private ContentValues getContentValues(TimeTableInfo timeTableInfo) {
        ContentValues contentValues = new ContentValues();
        if (timeTableInfo.getSubject() != null) {

            contentValues.put(TimeTableInfo_SUBJECT, timeTableInfo.getSubject());

        }
        if (timeTableInfo.getFromtimeMillis() != -1) {

            contentValues.put(TIMETABLEINFO_FROMTIMEMILLIS, timeTableInfo.getFromtimeMillis());

        }
        if (timeTableInfo.getFragment() != null) {

            contentValues.put(TimeTableInfo_FRAGMENT, timeTableInfo.getFragment());

        }
        if (timeTableInfo.getTeacher() != null) {
            contentValues.put(TimeTableInfo_TEACHER, timeTableInfo.getTeacher());
        }
        if (timeTableInfo.getRoom() != null) {
            contentValues.put(TimeTableInfo_ROOM, timeTableInfo.getRoom());

        }
        if (timeTableInfo.getFromTime() != null) {
            contentValues.put(TimeTableInfo_FROM_TIME, timeTableInfo.getFromTime());


        }
        if (timeTableInfo.getDuration() != null) {
            contentValues.put(TimeTableInfo_DURATION, timeTableInfo.getDuration());

        }
        if (timeTableInfo.getColor() != -1) {
            contentValues.put(TimeTableInfo_COLOR, timeTableInfo.getColor());

        }
        if (timeTableInfo.getProcess() != -1) {
            contentValues.put(TimeTableInfo_PROCESS, timeTableInfo.getProcess());

        }
        if (timeTableInfo.getStatus() != null) {
            contentValues.put(TimeTableInfo_STATUS, timeTableInfo.getStatus());

        }
        if (timeTableInfo.getSemastor() != null) {
            contentValues.put(TimeTableInfo_SEMESTER, timeTableInfo.getSemastor());


        }
        if (timeTableInfo.getWeekofyear() != null) {
            contentValues.put(TimeTableInfo_WOY, timeTableInfo.getWeekofyear());


        }
        if (timeTableInfo.getYear() != null) {
            contentValues.put(TimeTableInfo_YEAR, timeTableInfo.getYear());

        }
        if (timeTableInfo.getItemID() != null) {
            contentValues.put(ITEMID, timeTableInfo.getItemID());

        }
        if (timeTableInfo.getDate() != null) {
            contentValues.put(TimeTableInfo_Date, timeTableInfo.getDate());
        }

        return contentValues;
    }

    public void insertCourseInfo(CourseInfo cinfo) {
        ArrayList<CourseEvent> eventlist = cinfo.getEvents();
        for (CourseEvent event : eventlist) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_WEEK, event.getDayOfWeek());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(cal.getTime());
            if (event.isClass()) {
                TimeTableInfo info = STPHelper.getUnAssignedItem();
                info.setFragment(STPHelper.String2Fragment(event.getDayOfWeek() + ""));
                info.setFromTime(event.getStartTime());
                info.setItemID(date + ":" + event.getStartTime());
                info.setDate(date);
                info.setSubject(event.getEventName());
                long time = STPHelper.getTimeMillisByTiem(event.getStartTime() + ":" + "00 " + date);
                info.setFromtimeMillis(time);
                insertTimeTableInfo(info);
            }
        }
    }

    /**
     * Methods for TimeTableInfo fragments
     **/
    public void insertBlockInfo(int x, int y) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            String qurey = x + ":" + y;
            contentValues.put(BLOCK_INFO, qurey);
            db.insert(BLOCK, null, contentValues);
        } catch (SQLiteConstraintException e) {
            e.printStackTrace();
        } finally {
            db.close();

        }
    }

    public void cleanBlockInfo() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(BLOCK, "", null);
        } catch (SQLiteConstraintException e) {
            e.printStackTrace();
        } finally {
            db.close();

        }
    }

    /**
     * Methods for TimeTableInfo fragments
     **/
    public HashMap<Integer, ArrayList<Integer>> getBlockInfo() {
        SQLiteDatabase db = null;
        ArrayList<TimeTableInfo> TimeTableInfoList = new ArrayList<>();
        Cursor cursor = null;
        Cursor cursor2 = null;
        ArrayList<String> list = new ArrayList<>();
        try {
            db = this.getWritableDatabase();
            cursor = db.rawQuery("SELECT * FROM " + BLOCK, null);
            while (cursor.moveToNext()) {
                list.add(cursor.getString(cursor.getColumnIndex(BLOCK_INFO)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        try {
            db = this.getWritableDatabase();
            cursor2 = db.rawQuery("SELECT * FROM ( SELECT * FROM " + TIMETABLE + " ORDER BY " + TimeTableInfo_FROM_TIME + " ) WHERE " + TimeTableInfo_WOY + " = '" + STPHelper.getWeekofyear() + "'", null);

            while (cursor2.moveToNext()) {
                TimeTableInfoList.add(setTimeTableInfo(cursor2));
            }
            ;
            addTTInfoToBlist(list, TimeTableInfoList);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cursor2 != null) {
                cursor2.close();
            }
            db.close();
        }
        return parseListToMap(list);
    }

    private HashMap<Integer, ArrayList<Integer>> parseListToMap(ArrayList<String> list) {
        HashMap<Integer, ArrayList<Integer>> blockMap = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 0; i < list.size(); i++) {
            setBlockMap(blockMap, list.get(i));
        }
        return blockMap;
    }

    private void setBlockMap(HashMap<Integer, ArrayList<Integer>> blockMap, String s) {
        String[] arr = s.split(":");
        if (blockMap.get(Integer.parseInt(arr[0])) != null) {
            ArrayList<Integer> list = ((ArrayList<Integer>) blockMap.get(Integer.parseInt(arr[0])));
            list.add(Integer.parseInt(arr[1]));
        } else {
            ArrayList<Integer> list = list = new ArrayList<>();
            list.add(Integer.parseInt(arr[1]));
            blockMap.put(Integer.parseInt(arr[0]), list);
        }
    }

    private void addTTInfoToBlist(ArrayList<String> list, ArrayList<TimeTableInfo> timeTableInfoList) {
        for (TimeTableInfo info : timeTableInfoList) {
            if (info.getSubject() != null && !info.getSubject().equals(ConstentValue.UNASSIGNED)) {
                String blockInfo = parseInfo(info.getFragment(), info.getFromTime());
                list.add(blockInfo);
            }
        }
    }

    private String parseInfo(String Fragement, String fromTime) {
        return STPHelper.Fragment2String(Fragement) + ":" + STPHelper.FromTime2String(fromTime);
    }

    public ArrayList<TimeTableInfo> getCalendarList() {
        SQLiteDatabase db = null;
        ArrayList<TimeTableInfo> TimeTableInfoList = new ArrayList<>();
        TimeTableInfo info = null;
        Cursor cursor = null;
        try {
            db = this.getWritableDatabase();
            cursor = db.rawQuery("SELECT * FROM ( SELECT * FROM " + TIMETABLE + " ORDER BY " + TimeTableInfo_FROM_TIME + " ) WHERE " + TimeTableInfo_WOY + " = '" + STPHelper.getWeekofyear() + "'", null);
            while (cursor.moveToNext()) {
                info = setTimeTableInfo(cursor);
                if (!info.getSubject().equals(ConstentValue.UNASSIGNED)) {
                    TimeTableInfoList.add(info);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return TimeTableInfoList;
    }
}
