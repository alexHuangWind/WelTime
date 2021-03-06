package weltimetable.stp.android.huang.changyu.weltimetable.utils;

import weltimetable.stp.android.huang.changyu.weltimetable.components.dialog.NumberPickerDialog;
import weltimetable.stp.android.huang.changyu.weltimetable.models.STPController;
import weltimetable.stp.android.huang.changyu.weltimetable.models.TimeTableInfo;
import weltimetable.stp.android.huang.changyu.weltimetable.models.Adapter.FragmentsTabAdapter;
import weltimetable.stp.android.huang.changyu.weltimetable.models.Adapter.TTInfoAdapter;
import weltimetable.stp.android.huang.changyu.weltimetable.R;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import org.xdty.preference.colorpicker.ColorPickerDialog;
import org.xdty.preference.colorpicker.ColorPickerSwatch;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by changyu on 20.05.stp.
 * email：alexchyandroid@gmail.com
 */
public class AlertDialogsHelper {

    public static void getEditSubjectDialog(Activity activity, View alertLayout, ArrayList<TimeTableInfo> adapter, ListView listView, int position) {
        final HashMap<Integer, EditText> editTextHashs = new HashMap<>();
        final EditText subject = alertLayout.findViewById(R.id.subject_dialog);
        final EditText teacher = alertLayout.findViewById(R.id.teacher_dialog);
        final EditText room = alertLayout.findViewById(R.id.room_dialog);
        final TextView from_time = alertLayout.findViewById(R.id.from_time);
        final TextView duration = alertLayout.findViewById(R.id.duration);
        final Button select_color = alertLayout.findViewById(R.id.select_color);
        final TimeTableInfo timetableinfo = adapter.get(position);
        editTextHashs.put(R.string.subject, subject);
        editTextHashs.put(R.string.teacher, teacher);
        editTextHashs.put(R.string.room, room);
        subject.setText(timetableinfo.getSubject());
        teacher.setText(timetableinfo.getTeacher());
        room.setText(timetableinfo.getRoom());
        from_time.setText(timetableinfo.getFromTime());
        duration.setText(timetableinfo.getDuration());
        select_color.setBackgroundColor(timetableinfo.getColor() != 0 ? timetableinfo.getColor() : Color.WHITE);

        from_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(activity,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                from_time.setText(String.format("%02d:%02d", hourOfDay, minute));
                                timetableinfo.setFromTime(String.format("%02d:%02d", hourOfDay, minute));
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.setTitle(R.string.choose_time);
                timePickerDialog.show();
            }
        });

        duration.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NumberPickerDialog newFragment = new NumberPickerDialog();
                newFragment.setValueChangeListener(new NumberPicker.OnValueChangeListener() {

                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        duration.setText(String.format("%02d Hours", i));
                        timetableinfo.setDuration(String.format("%02d Hours", i));
                    }
                });
                newFragment.show(activity.getFragmentManager(), "time picker");
//                final Calendar c = Calendar.getInstance();
//                int hour = c.get(Calendar.HOUR_OF_DAY);
//                int minute = c.get(Calendar.MINUTE);
//                TimePickerDialog timePickerDialog = new TimePickerDialog(activity,
//                        new TimePickerDialog.OnTimeSetListener() {
//
//                            @Override
//                            public void onTimeSet(TimePicker view, int hourOfDay,
//                                                  int minute) {
//                                duration.setText(String.format("%02d:%02d", hourOfDay, minute));
//                                timetableinfo.setDuration(String.format("%02d:%02d", hourOfDay, minute));
//                            }
//                        }, hour, minute, true);
//                timePickerDialog.setTitle(R.string.choose_time);
//                timePickerDialog.show();
            }
        });

        select_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mSelectedColor = ContextCompat.getColor(activity, R.color.white);
                select_color.setBackgroundColor(mSelectedColor);
                int[] mColors = activity.getResources().getIntArray(R.array.default_colors);
                ColorPickerDialog dialog = ColorPickerDialog.newInstance(R.string.color_picker_default_title,
                        mColors,
                        mSelectedColor,
                        5,
                        ColorPickerDialog.SIZE_SMALL,
                        true
                );

                dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int color) {
                        select_color.setBackgroundColor(color);
                    }
                });
                dialog.show(activity.getFragmentManager(), "color_dialog");
            }
        });

        final AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(R.string.edit_subject);
        alert.setCancelable(false);
        final Button cancel = alertLayout.findViewById(R.id.cancel);
        final Button save = alertLayout.findViewById(R.id.save);
        alert.setView(alertLayout);
        final AlertDialog dialog = alert.create();
        dialog.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(subject.getText()) || TextUtils.isEmpty(teacher.getText()) || TextUtils.isEmpty(room.getText())) {
                    for (Map.Entry<Integer, EditText> entry : editTextHashs.entrySet()) {
                        if (TextUtils.isEmpty(entry.getValue().getText())) {
                            entry.getValue().setError(activity.getResources().getString(entry.getKey()) + " " + activity.getResources().getString(R.string.field_error));
                            entry.getValue().requestFocus();
                        }
                    }
                } else if (!from_time.getText().toString().matches(".*\\d+.*") || !duration.getText().toString().matches(".*\\d+.*")) {
                    Snackbar.make(alertLayout, R.string.time_error, Snackbar.LENGTH_LONG).show();
                } else {
                    DbHelper db = new DbHelper(activity);
                    TTInfoAdapter TTInfoAdapter = (TTInfoAdapter) listView.getAdapter(); // In order to get notifyDataSetChanged() method.
                    ColorDrawable buttonColor = (ColorDrawable) select_color.getBackground();
                    timetableinfo.setSubject(subject.getText().toString());
                    timetableinfo.setTeacher(teacher.getText().toString());
                    timetableinfo.setRoom(room.getText().toString());
                    timetableinfo.setColor(buttonColor.getColor());
                    db.updateTimeTableInfo(timetableinfo);
                    TTInfoAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });
    }

    public static void getAddSubjectDialog(final Activity activity, final View alertLayout, final FragmentsTabAdapter adapter, final ViewPager viewPager) {
        final HashMap<Integer, EditText> editTextHashs = new HashMap<>();
        final EditText subject = alertLayout.findViewById(R.id.subject_dialog);
        editTextHashs.put(R.string.subject, subject);
        final EditText teacher = alertLayout.findViewById(R.id.teacher_dialog);
        editTextHashs.put(R.string.teacher, teacher);
        final EditText room = alertLayout.findViewById(R.id.room_dialog);
        editTextHashs.put(R.string.room, room);
        final TextView from_time = alertLayout.findViewById(R.id.from_time);
        final TextView duration = alertLayout.findViewById(R.id.duration);
        final Button select_color = alertLayout.findViewById(R.id.select_color);
        final TimeTableInfo timetableinfo = new TimeTableInfo();

        from_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(activity,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                from_time.setText(String.format("%02d:%02d", hourOfDay, minute));
                                timetableinfo.setFromTime(String.format("%02d:%02d", hourOfDay, minute));
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.setTitle(R.string.choose_time);
                timePickerDialog.show();
            }
        });

        duration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NumberPickerDialog newFragment = new NumberPickerDialog();
                newFragment.setValueChangeListener(new NumberPicker.OnValueChangeListener() {

                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        duration.setText(String.format("%02d Hours", i));
                        timetableinfo.setDuration(String.format("%02d Hours", i));
                    }
                });
                newFragment.show(activity.getFragmentManager(), "time picker");
//
//                final Calendar c = Calendar.getInstance();
//                int hour = c.get(Calendar.HOUR_OF_DAY);
//                int minute = c.get(Calendar.MINUTE);
//                TimePickerDialog timePickerDialog = new TimePickerDialog(activity,
//                        new TimePickerDialog.OnTimeSetListener() {
//
//                            @Override
//                            public void onTimeSet(TimePicker view, int hourOfDay,
//                                                  int minute) {
//                                duration.setText(String.format("%02d:%02d", hourOfDay, minute));
//                                timetableinfo.setDuration(String.format("%02d:%02d", hourOfDay, minute));
//                            }
//                        }, hour, minute, true);
//                timePickerDialog.setTitle(R.string.choose_time);
//                timePickerDialog.show();
            }
        });

        select_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mSelectedColor = ContextCompat.getColor(activity, R.color.white);
                select_color.setBackgroundColor(mSelectedColor);
                int[] mColors = activity.getResources().getIntArray(R.array.default_colors);
                ColorPickerDialog dialog = ColorPickerDialog.newInstance(R.string.color_picker_default_title,
                        mColors,
                        mSelectedColor,
                        5,
                        ColorPickerDialog.SIZE_SMALL,
                        true
                );

                dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int color) {
                        select_color.setBackgroundColor(color);
                    }
                });
                dialog.show(activity.getFragmentManager(), "color_dialog");
            }
        });

        final AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(R.string.add_subject);
        alert.setCancelable(false);
        Button cancel = alertLayout.findViewById(R.id.cancel);
        Button submit = alertLayout.findViewById(R.id.save);
        alert.setView(alertLayout);
        final AlertDialog dialog = alert.create();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(subject.getText()) || TextUtils.isEmpty(teacher.getText()) || TextUtils.isEmpty(room.getText())) {
                    for (Map.Entry<Integer, EditText> entry : editTextHashs.entrySet()) {
                        if (TextUtils.isEmpty(entry.getValue().getText())) {
                            entry.getValue().setError(activity.getResources().getString(entry.getKey()) + " " + activity.getResources().getString(R.string.field_error));
                            entry.getValue().requestFocus();
                        }
                    }
                } else if (!from_time.getText().toString().matches(".*\\d+.*") || !duration.getText().toString().matches(".*\\d+.*")) {
                    Snackbar.make(alertLayout, R.string.time_error, Snackbar.LENGTH_LONG).show();
                } else {
                    DbHelper dbHelper = new DbHelper(activity);
                    Matcher fragment = Pattern.compile("(.*Fragment)").matcher(adapter.getItem(viewPager.getCurrentItem()).toString());
                    ColorDrawable buttonColor = (ColorDrawable) select_color.getBackground();
                    timetableinfo.setSubject(subject.getText().toString());
                    timetableinfo.setFragment(fragment.find() ? fragment.group() : null);
                    fragment.group();
                    timetableinfo.setTeacher(teacher.getText().toString());
                    timetableinfo.setRoom(room.getText().toString());
                    timetableinfo.setColor(buttonColor.getColor());
                    dbHelper.updateTimeTableInfo(timetableinfo);
                    adapter.notifyDataSetChanged();
                    subject.getText().clear();
                    teacher.getText().clear();
                    room.getText().clear();
                    from_time.setText(R.string.select_duration);
                    duration.setText(R.string.select_duration);
                    select_color.setBackgroundColor(Color.WHITE);
                    subject.requestFocus();
                    dialog.dismiss();
                }
            }
        });
    }


}
