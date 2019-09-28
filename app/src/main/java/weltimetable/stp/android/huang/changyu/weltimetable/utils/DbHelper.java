package weltimetable.stp.android.huang.changyu.weltimetable.utils;

import weltimetable.stp.android.huang.changyu.weltimetable.models.CourseInfo;
import weltimetable.stp.android.huang.changyu.weltimetable.models.TimeTableInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Time;
import java.util.ArrayList;

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
    //dayofweek+starttime
    public static final String BLOCK_ID = "id";
    private static DbHelper INSTANCE;


    private CourseInfo info ;


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
                + BLOCK_ID + " TEXT PRIMARY KEY)";
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
        try {
            String ItemId = getTimeTableItemInfoByItemID(timeTableInfo.getItemID()).getItemID();
            if ( ItemId== null) {
                SQLiteDatabase db = this.getWritableDatabase();
                db.insert(TIMETABLE, null, getContentValues(timeTableInfo));
                db.close();
            } else {
                updateTimeTableInfo(timeTableInfo);
            }
        } catch (SQLiteConstraintException e) {
            e.printStackTrace();
        }
    }


    public void deleteTTInfoById(TimeTableInfo timetableinfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TIMETABLE, ITEMID + " = ? ", new String[]{String.valueOf(timetableinfo.getItemID())});
        db.close();
    }

    public void updateTimeTableInfo(TimeTableInfo timeTableInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TIMETABLE, getContentValues(timeTableInfo), ITEMID + " = '" + timeTableInfo.getItemID() + "'", null);
        db.close();
    }


    public void updateTimeTableProcessById(int id, int value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TimeTableInfo_PROCESS, value);
        db.update(TIMETABLE, contentValues, TimeTableInfo_ID + " = " + id, null);
        db.close();
    }

    public ArrayList<TimeTableInfo> getTimeTableInfoByDate(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<TimeTableInfo> TimeTableInfoList = new ArrayList<>();
        TimeTableInfo timetableinfo;
        Cursor cursor = db.rawQuery("SELECT * FROM ( SELECT * FROM " + TIMETABLE + " ORDER BY " + TimeTableInfo_FROM_TIME + " ) WHERE " + TimeTableInfo_Date + " = '" + date + "'", null);
        while (cursor.moveToNext()) {
            TimeTableInfoList.add(setTimeTableInfo(cursor));
        }
        db.close();
        return TimeTableInfoList;
    }


    public TimeTableInfo getTimeTableItemInfoByItemID(String itemid) {
        SQLiteDatabase db = this.getWritableDatabase();
        TimeTableInfo timetableinfo;
        Cursor cursor = db.rawQuery("SELECT * FROM ( SELECT * FROM " + TIMETABLE + " ORDER BY " + TimeTableInfo_FROM_TIME + " ) WHERE " + ITEMID + " = '" + itemid + "'", null);

        timetableinfo = new TimeTableInfo();
        try {
            cursor.moveToNext();
            timetableinfo = setTimeTableInfo(cursor);
        } catch (Exception e) {
            return STPHelper.getUnAssignedItem();
        }finally {
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
        timetableinfo.setFromTime(cursor.getString(cursor.getColumnIndex(TimeTableInfo_FROM_TIME)));
        timetableinfo.setDuration(cursor.getString(cursor.getColumnIndex(TimeTableInfo_DURATION)));
        timetableinfo.setColor(cursor.getInt(cursor.getColumnIndex(TimeTableInfo_COLOR)));
        timetableinfo.setProcess(cursor.getInt(cursor.getColumnIndex(TimeTableInfo_PROCESS)));
        timetableinfo.setStatus(cursor.getString(cursor.getColumnIndex(TimeTableInfo_STATUS)));
        timetableinfo.setSemastor(cursor.getString(cursor.getColumnIndex(TimeTableInfo_SEMESTER)));
        timetableinfo.setWeekofyear(cursor.getString(cursor.getColumnIndex(TimeTableInfo_WOY)));
        timetableinfo.setYear(cursor.getString(cursor.getColumnIndex(TimeTableInfo_YEAR)));
        timetableinfo.setItemID(cursor.getString(cursor.getColumnIndex(ITEMID)));
        timetableinfo.setDate(cursor.getString(cursor.getColumnIndex(TimeTableInfo_Date)));
        return timetableinfo;
    }

    private ContentValues getContentValues(TimeTableInfo timeTableInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TimeTableInfo_SUBJECT, timeTableInfo.getSubject());
        contentValues.put(TimeTableInfo_FRAGMENT, timeTableInfo.getFragment());
        contentValues.put(TimeTableInfo_TEACHER, timeTableInfo.getTeacher());
        contentValues.put(TimeTableInfo_ROOM, timeTableInfo.getRoom());
        contentValues.put(TimeTableInfo_FROM_TIME, timeTableInfo.getFromTime());
        contentValues.put(TimeTableInfo_DURATION, timeTableInfo.getDuration());
        contentValues.put(TimeTableInfo_COLOR, timeTableInfo.getColor());
        contentValues.put(TimeTableInfo_PROCESS, timeTableInfo.getProcess());
        contentValues.put(TimeTableInfo_STATUS, timeTableInfo.getStatus());
        contentValues.put(TimeTableInfo_SEMESTER, timeTableInfo.getSemastor());
        contentValues.put(TimeTableInfo_WOY, timeTableInfo.getWeekofyear());
        contentValues.put(TimeTableInfo_YEAR, timeTableInfo.getYear());
        contentValues.put(ITEMID, timeTableInfo.getItemID());
        contentValues.put(TimeTableInfo_Date, timeTableInfo.getDate());
        return contentValues;
    }

    public void insertCourseInfo(CourseInfo info) {
        this.info = info;
    }

    public CourseInfo getInfo() {
        return info;
    }
}
