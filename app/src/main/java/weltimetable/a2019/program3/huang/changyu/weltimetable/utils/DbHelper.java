package  weltimetable.a2019.program3.huang.changyu.weltimetable.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.ArrayList;

import weltimetable.a2019.program3.huang.changyu.weltimetable.models.TimeTableInfo;

/**
 * Created by changyu on 20.05.2019.
 * email：alexchyandroid@gmail.com
 */
public class DbHelper extends SQLiteOpenHelper{

    private static final int DB_VERSION = 6;
    private static final String DB_NAME = "timetabledb";
    private static final String TIMETABLE = "timetable";
    private static final String TimeTableInfo_ID = "id";
    private static final String TimeTableInfo_SUBJECT = "subject";
    private static final String TimeTableInfo_FRAGMENT = "fragment";
    private static final String TimeTableInfo_TEACHER = "teacher";
    private static final String TimeTableInfo_ROOM = "room";
    private static final String TimeTableInfo_FROM_TIME = "fromtime";
    private static final String TimeTableInfo_TO_TIME = "totime";
    private static final String TimeTableInfo_COLOR = "color";



    public DbHelper(Context context){
        super(context , DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_TIMETABLE = "CREATE TABLE " + TIMETABLE + "("
                + TimeTableInfo_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TimeTableInfo_SUBJECT + " TEXT,"
                + TimeTableInfo_FRAGMENT + " TEXT,"
                + TimeTableInfo_TEACHER + " TEXT,"
                + TimeTableInfo_ROOM + " TEXT,"
                + TimeTableInfo_FROM_TIME + " TEXT,"
                + TimeTableInfo_TO_TIME + " TEXT,"
                + TimeTableInfo_COLOR + " INTEGER" +  ")";



        db.execSQL(CREATE_TIMETABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TIMETABLE);
        onCreate(db);
    }

    /**
     * Methods for TimeTableInfo fragments
     **/
    public void insertTimeTableInfo(TimeTableInfo timeTableInfo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TimeTableInfo_SUBJECT, timeTableInfo.getSubject());
        contentValues.put(TimeTableInfo_FRAGMENT, timeTableInfo.getFragment());
        contentValues.put(TimeTableInfo_TEACHER, timeTableInfo.getTeacher());
        contentValues.put(TimeTableInfo_ROOM, timeTableInfo.getRoom());
        contentValues.put(TimeTableInfo_FROM_TIME, timeTableInfo.getFromTime());
        contentValues.put(TimeTableInfo_TO_TIME, timeTableInfo.getToTime());
        contentValues.put(TimeTableInfo_COLOR, timeTableInfo.getColor());
        db.insert(TIMETABLE,null, contentValues);
        db.update(TIMETABLE, contentValues, TimeTableInfo_FRAGMENT, null);
        db.close();
    }

    public void deleteTTInfoById(TimeTableInfo timetableinfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TIMETABLE, TimeTableInfo_ID + " = ? ", new String[]{String.valueOf(timetableinfo.getId())});
        db.close();
    }

    public void updateTimeTableInfo(TimeTableInfo timeTableInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TimeTableInfo_SUBJECT, timeTableInfo.getSubject());
        contentValues.put(TimeTableInfo_TEACHER, timeTableInfo.getTeacher());
        contentValues.put(TimeTableInfo_ROOM, timeTableInfo.getRoom());
        contentValues.put(TimeTableInfo_FROM_TIME,timeTableInfo.getFromTime());
        contentValues.put(TimeTableInfo_TO_TIME, timeTableInfo.getToTime());
        contentValues.put(TimeTableInfo_COLOR, timeTableInfo.getColor());
        db.update(TIMETABLE, contentValues, TimeTableInfo_ID + " = " + timeTableInfo.getId(), null);
        db.close();
    }

    public ArrayList<TimeTableInfo> getTimeTableInfo(String fragment){
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<TimeTableInfo> TimeTableInfoList = new ArrayList<>();
        TimeTableInfo timetableinfo;
        Cursor cursor = db.rawQuery("SELECT * FROM ( SELECT * FROM "+TIMETABLE+" ORDER BY " + TimeTableInfo_FROM_TIME + " ) WHERE "+ TimeTableInfo_FRAGMENT +" LIKE '"+fragment+"%'",null);
        while (cursor.moveToNext()){
            timetableinfo = new TimeTableInfo();
            timetableinfo.setId(cursor.getInt(cursor.getColumnIndex(TimeTableInfo_ID)));
            timetableinfo.setSubject(cursor.getString(cursor.getColumnIndex(TimeTableInfo_SUBJECT)));
            timetableinfo.setTeacher(cursor.getString(cursor.getColumnIndex(TimeTableInfo_TEACHER)));
            timetableinfo.setRoom(cursor.getString(cursor.getColumnIndex(TimeTableInfo_ROOM)));
            timetableinfo.setFromTime(cursor.getString(cursor.getColumnIndex(TimeTableInfo_FROM_TIME)));
            timetableinfo.setToTime(cursor.getString(cursor.getColumnIndex(TimeTableInfo_TO_TIME)));
            timetableinfo.setColor(cursor.getInt(cursor.getColumnIndex(TimeTableInfo_COLOR)));
            TimeTableInfoList.add(timetableinfo);
        }
        return  TimeTableInfoList;
    }


}
