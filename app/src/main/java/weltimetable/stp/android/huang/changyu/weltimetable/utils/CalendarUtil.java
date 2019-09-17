package weltimetable.stp.android.huang.changyu.weltimetable.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;

import weltimetable.stp.android.huang.changyu.weltimetable.models.CalendarInfo;

public class CalendarUtil {
    private static final int MY_PERMISSIONS_REQUEST_READ_CALENDAR = 1000;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_CALENDAR = 1001;

    public ArrayList<String> getCalendars(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_CALENDAR}, MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);
            return null;
        }

        // Projection array. Creating indices for this array instead of doing dynamic lookups improves performance.
        final String[] EVENT_PROJECTION = new String[]{
                CalendarContract.Calendars._ID,                           // 0
                CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
                CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
        };

        // The indices for the projection array above.
        final int PROJECTION_ID_INDEX = 0;
        final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
        final int PROJECTION_DISPLAY_NAME_INDEX = 2;
        final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;


        ContentResolver contentResolver = activity.getContentResolver();
        Cursor cur = contentResolver.query(CalendarContract.Calendars.CONTENT_URI, EVENT_PROJECTION, null, null, null);

        ArrayList<String> calendarInfos = new ArrayList<>();
        while (cur.moveToNext()) {
            long calID = 0;
            String displayName = null;
            String accountName = null;
            String ownerName = null;

            // Get the field values
            calID = cur.getLong(PROJECTION_ID_INDEX);
            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
            ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);

            String calendarInfo = String.format("Calendar ID: %s\nDisplay Name: %s\nAccount Name: %s\nOwner Name: %s", calID, displayName, accountName, ownerName);
            calendarInfos.add(calendarInfo);
        }

        return calendarInfos;
    }

    public ArrayList<String> getPrimaryCalendar(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_CALENDAR}, MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);
            return null;
        }

        // Projection array. Creating indices for this array instead of doing dynamic lookups improves performance.
        final String[] EVENT_PROJECTION = new String[]{
                CalendarContract.Calendars._ID,                           // 0
                CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
                CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
        };

        // The indices for the projection array above.
        final int PROJECTION_ID_INDEX = 0;
        final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
        final int PROJECTION_DISPLAY_NAME_INDEX = 2;
        final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;

        ContentResolver contentResolver = activity.getContentResolver();
        String selection = CalendarContract.Calendars.VISIBLE + " = 1 AND " + CalendarContract.Calendars.IS_PRIMARY + "=1";
        Cursor cur = contentResolver.query(CalendarContract.Calendars.CONTENT_URI, EVENT_PROJECTION, selection, null, null);

        ArrayList<String> calendarInfos = new ArrayList<>();
        while (cur.moveToNext()) {
            long calID = 0;
            String displayName = null;
            String accountName = null;
            String ownerName = null;

            // Get the field values
            calID = cur.getLong(PROJECTION_ID_INDEX);
            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
            ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);

            String calendarInfo = String.format("Calendar ID: %s\nDisplay Name: %s\nAccount Name: %s\nOwner Name: %s", calID, displayName, accountName, ownerName);
            calendarInfos.add(calendarInfo);
        }

        return calendarInfos;
    }

    public ArrayList<String> readCalendarsByAccount(View view, Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_CALENDAR}, MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);
            return null;
        }

        // Projection array. Creating indices for this array instead of doing dynamic lookups improves performance.
        final String[] EVENT_PROJECTION = new String[]{
                CalendarContract.Calendars._ID,                           // 0
                CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
                CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
        };

        // The indices for the projection array above.
        final int PROJECTION_ID_INDEX = 0;
        final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
        final int PROJECTION_DISPLAY_NAME_INDEX = 2;
        final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;

        // Run query
        Cursor cur = null;
        ContentResolver cr = activity.getContentResolver();
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[]{"test@gmail.com", "com.google", "test@gmail.com"};
        // Submit the query and get a Cursor object back.
        cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);

        // Use the cursor to step through the returned records
        ArrayList<String> calendars = new ArrayList<>();
        while (cur.moveToNext()) {
            long calID = 0;
            String displayName = null;
            String accountName = null;
            String ownerName = null;

            // Get the field values
            calID = cur.getLong(PROJECTION_ID_INDEX);
            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
            ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);

            String calendarInfo = String.format("Calendar ID: %s\nDisplay Name: %s\nAccount Name: %s\nOwner Name: %s", calID, displayName, accountName, ownerName);
            calendars.add(calendarInfo);
        }

        return calendars;
    }

    private boolean isEventAlreadyExist(Activity activity, String eventTitle) {
        final String[] INSTANCE_PROJECTION = new String[]{
                CalendarContract.Instances.EVENT_ID,      // 0
                CalendarContract.Instances.BEGIN,         // 1
                CalendarContract.Instances.TITLE          // 2
        };

        long startMillis = 0;
        long endMillis = 0;
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2017, 11, 15, 6, 00);
        startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(2017, 11, 15, 8, 00);
        endMillis = endTime.getTimeInMillis();

        // The ID of the recurring event whose instances you are searching for in the Instances table
        String selection = CalendarContract.Instances.TITLE + " = ?";
        String[] selectionArgs = new String[]{eventTitle};

        // Construct the query with the desired date range.
        Uri.Builder builder = CalendarContract.Instances.CONTENT_URI.buildUpon();
        ContentUris.appendId(builder, startMillis);
        ContentUris.appendId(builder, endMillis);

        // Submit the query
        Cursor cur = activity.getContentResolver().query(builder.build(), INSTANCE_PROJECTION, selection, selectionArgs, null);

        return cur.getCount() > 0;
    }

    /*
    * values.put(CalendarContract.Events.DTSTART, startMillis);
            values.put(CalendarContract.Events.DTEND, endMillis);
            values.put(CalendarContract.Events.TITLE, "Jazzercise");
            values.put(CalendarContract.Events.DESCRIPTION, "Group workout");
            values.put(CalendarContract.Events.CALENDAR_ID, calID);
            values.put(CalendarContract.Events.EVENT_TIMEZONE, "America/Los_Angeles");
            values.put(CalendarContract.Events.ORGANIZER, "google_calendar@gmail.com");
            * */
    public void addEvent(Activity activity, CalendarInfo calendarInfo) {

        if (isEventAlreadyExist(activity, calendarInfo.getEventTitle())) {
            STPHelper.toast(activity, "Jazzercise event already exist!");
            return;
        }
//        Calendar beginTime = Calendar.getInstance();
//        beginTime.set(2017, 11, 15, 6, 00);
//        startMillis = beginTime.getTimeInMillis();
//        Calendar endTime = Calendar.getInstance();
//        endTime.set(2017, 11, 15, 8, 00);
//        endMillis = endTime.getTimeInMillis();
        ContentResolver cr = activity.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, calendarInfo.getStartMillis());
        values.put(CalendarContract.Events.DTEND, calendarInfo.getEndMillis());
        values.put(CalendarContract.Events.TITLE, calendarInfo.getEventTitle());
        values.put(CalendarContract.Events.DESCRIPTION, calendarInfo.getDescription());
        values.put(CalendarContract.Events.CALENDAR_ID, calendarInfo.getCalID());
        values.put(CalendarContract.Events.EVENT_TIMEZONE, calendarInfo.getEventTimezone());
        values.put(CalendarContract.Events.ORGANIZER, calendarInfo.getOrganizer());

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED) {
            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
            long eventID = Long.parseLong(uri.getLastPathSegment());
            Log.i("Calendar", "Event Created, the event id is: " + eventID);
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_CALENDAR}, MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);
        }

    }

//    public void removeEvent(Activity activity) {
//        String eventTitle = "Jazzercise";
//
//        final String[] INSTANCE_PROJECTION = new String[]{
//                CalendarContract.Instances.EVENT_ID,      // 0
//                CalendarContract.Instances.BEGIN,         // 1
//                CalendarContract.Instances.TITLE          // 2
//        };
//        // The indices for the projection array above.
//        final int PROJECTION_ID_INDEX = 0;
//        final int PROJECTION_BEGIN_INDEX = 1;
//        final int PROJECTION_TITLE_INDEX = 2;
//
//        // Specify the date range you want to search for recurring event instances
//        Calendar beginTime = Calendar.getInstance();
//        beginTime.set(2017, 9, 23, 8, 0);
//        long startMillis = beginTime.getTimeInMillis();
//        Calendar endTime = Calendar.getInstance();
//        endTime.set(2018, 1, 24, 8, 0);
//        long endMillis = endTime.getTimeInMillis();
//
//
//        // The ID of the recurring event whose instances you are searching for in the Instances table
//        String selection = CalendarContract.Instances.TITLE + " = ?";
//        String[] selectionArgs = new String[]{eventTitle};
//
//        // Construct the query with the desired date range.
//        Uri.Builder builder = CalendarContract.Instances.CONTENT_URI.buildUpon();
//        ContentUris.appendId(builder, startMillis);
//        ContentUris.appendId(builder, endMillis);
//
//        // Submit the query
//        Cursor cur = activity.getContentResolver().query(builder.build(), INSTANCE_PROJECTION, selection, selectionArgs, null);
//
//        while (cur.moveToNext()) {
//            // Get the field values
//            long eventID = cur.getLong(PROJECTION_ID_INDEX);
//            long beginVal = cur.getLong(PROJECTION_BEGIN_INDEX);
//            String title = cur.getString(PROJECTION_TITLE_INDEX);
//
//            Uri deleteUri = null;
//            deleteUri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventID);
//            int rows = activity.getContentResolver().delete(deleteUri, null, null);
//            Log.i("Calendar", "Rows deleted: " + rows);
//        }
//
//    }

//    private void showEvents(String eventTitle) {
//        final String[] INSTANCE_PROJECTION = new String[]{
//                CalendarContract.Instances.EVENT_ID,       // 0
//                CalendarContract.Instances.BEGIN,         // 1
//                CalendarContract.Instances.TITLE,        // 2
//                CalendarContract.Instances.ORGANIZER    //3
//        };
//
//        // The indices for the projection array above.
//        final int PROJECTION_ID_INDEX = 0;
//        final int PROJECTION_BEGIN_INDEX = 1;
//        final int PROJECTION_TITLE_INDEX = 2;
//        final int PROJECTION_ORGANIZER_INDEX = 3;
//
//        // Specify the date range you want to search for recurring event instances
//        Calendar beginTime = Calendar.getInstance();
//        beginTime.set(2017, 9, 23, 8, 0);
//        long startMillis = beginTime.getTimeInMillis();
//        Calendar endTime = Calendar.getInstance();
//        endTime.set(2018, 1, 24, 8, 0);
//        long endMillis = endTime.getTimeInMillis();
//
//
//        // The ID of the recurring event whose instances you are searching for in the Instances table
//        String selection = CalendarContract.Instances.TITLE + " = ?";
//        String[] selectionArgs = new String[]{eventTitle};
//
//        // Construct the query with the desired date range.
//        Uri.Builder builder = CalendarContract.Instances.CONTENT_URI.buildUpon();
//        ContentUris.appendId(builder, startMillis);
//        ContentUris.appendId(builder, endMillis);
//
//        // Submit the query
//        Cursor cur = getContentResolver().query(builder.build(), INSTANCE_PROJECTION, selection, selectionArgs, null);
//
//
//        ArrayList<String> events = new ArrayList<>();
//        while (cur.moveToNext()) {
//            // Get the field values
//            long eventID = cur.getLong(PROJECTION_ID_INDEX);
//            long beginVal = cur.getLong(PROJECTION_BEGIN_INDEX);
//            String title = cur.getString(PROJECTION_TITLE_INDEX);
//            String organizer = cur.getString(PROJECTION_ORGANIZER_INDEX);
//
//            // Do something with the values.
//            Log.i("Calendar", "Event:  " + title);
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTimeInMillis(beginVal);
//            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//            Log.i("Calendar", "Date: " + formatter.format(calendar.getTime()));
//
//            events.add(String.format("Event: %s\nOrganizer: %s\nDate: %s", title, organizer, formatter.format(calendar.getTime())));
//        }
//
//        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, events);
//        listView.setAdapter(stringArrayAdapter);
//    }

//    public void readEvents(View view) {
//        final String[] INSTANCE_PROJECTION = new String[]{
//                CalendarContract.Instances.EVENT_ID,      // 0
//                CalendarContract.Instances.BEGIN,         // 1
//                CalendarContract.Instances.TITLE,          // 2
//                CalendarContract.Instances.ORGANIZER
//        };
//
//        // The indices for the projection array above.
//        final int PROJECTION_ID_INDEX = 0;
//        final int PROJECTION_BEGIN_INDEX = 1;
//        final int PROJECTION_TITLE_INDEX = 2;
//        final int PROJECTION_ORGANIZER_INDEX = 3;
//
//        // Specify the date range you want to search for recurring event instances
//        Calendar beginTime = Calendar.getInstance();
//        beginTime.set(2017, 9, 23, 8, 0);
//        long startMillis = beginTime.getTimeInMillis();
//        Calendar endTime = Calendar.getInstance();
//        endTime.set(2018, 1, 24, 8, 0);
//        long endMillis = endTime.getTimeInMillis();
//
//
//        // The ID of the recurring event whose instances you are searching for in the Instances table
//        String selection = CalendarContract.Instances.EVENT_ID + " = ?";
//        String[] selectionArgs = new String[]{"207"};
//
//        // Construct the query with the desired date range.
//        Uri.Builder builder = CalendarContract.Instances.CONTENT_URI.buildUpon();
//        ContentUris.appendId(builder, startMillis);
//        ContentUris.appendId(builder, endMillis);
//
//        // Submit the query
//        Cursor cur = getContentResolver().query(builder.build(), INSTANCE_PROJECTION, null, null, null);
//
//
//        ArrayList<String> events = new ArrayList<>();
//        while (cur.moveToNext()) {
//
//            // Get the field values
//            long eventID = cur.getLong(PROJECTION_ID_INDEX);
//            long beginVal = cur.getLong(PROJECTION_BEGIN_INDEX);
//            String title = cur.getString(PROJECTION_TITLE_INDEX);
//            String organizer = cur.getString(PROJECTION_ORGANIZER_INDEX);
//
//            // Do something with the values.
//            Log.i("Calendar", "Event:  " + title);
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTimeInMillis(beginVal);
//            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//            Log.i("Calendar", "Date: " + formatter.format(calendar.getTime()));
//
//            events.add(String.format("Event: %s\nOrganizer: %s\nDate: %s", title, organizer, formatter.format(calendar.getTime())));
//        }
//
//        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, events);
//        listView.setAdapter(stringArrayAdapter);
//    }

    private void updateEvent(Activity activity, long eventID, CalendarInfo calendarInfo) {
        ContentResolver cr = activity.getContentResolver();
        ContentValues values = new ContentValues();
        setClaendarInfo(values, calendarInfo);
        Uri updateUri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventID);
        int rows = activity.getContentResolver().update(updateUri, values, null, null);
        Log.i("Calendar", "Rows updated: " + rows);
    }

    private void deleteEvent(Activity activity, long eventID) {
        Uri deleteUri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventID);
        int rows = activity.getContentResolver().delete(deleteUri, null, null);
        Log.i("Calendar", "Rows deleted: " + rows);
    }
    private ContentValues setClaendarInfo(ContentValues values, CalendarInfo calendarInfo) {
        values.put(CalendarContract.Events.DTSTART, calendarInfo.getStartMillis());
        values.put(CalendarContract.Events.DTEND, calendarInfo.getEndMillis());
        values.put(CalendarContract.Events.TITLE, calendarInfo.getEventTitle());
        values.put(CalendarContract.Events.DESCRIPTION, calendarInfo.getDescription());
        values.put(CalendarContract.Events.CALENDAR_ID, calendarInfo.getCalID());
        values.put(CalendarContract.Events.EVENT_TIMEZONE, calendarInfo.getEventTimezone());
        values.put(CalendarContract.Events.ORGANIZER, calendarInfo.getOrganizer());
        return values;
    }
}
