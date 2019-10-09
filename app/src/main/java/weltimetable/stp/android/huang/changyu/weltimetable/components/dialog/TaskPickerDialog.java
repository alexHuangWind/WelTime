package weltimetable.stp.android.huang.changyu.weltimetable.components.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.NumberPicker;

import java.util.ArrayList;
import java.util.HashMap;

import weltimetable.stp.android.huang.changyu.weltimetable.models.CourseEvent;
import weltimetable.stp.android.huang.changyu.weltimetable.models.CourseInfo;
import weltimetable.stp.android.huang.changyu.weltimetable.models.TimeTableInfo;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.STPHelper;

public class TaskPickerDialog extends DialogFragment {
    private NumberPicker.OnValueChangeListener valueChangeListener;

    private static String[] itemdata = null;
    private static ListView listView = null;
    private CourseInfo assignmentInfo;
    private CourseInfo courseInfo;
    private TimeTableInfo mttinfo;
    private HashMap<String, CourseEvent> eventMap = new HashMap<>();

    public HashMap<String, CourseEvent> getEventMap() {
        return eventMap;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //update assignment info eachweek
        assignmentInfo = STPHelper.getAssmtInfo(STPHelper.getInstance().getContext());
        courseInfo = STPHelper.getCourseInfo(STPHelper.getInstance().getContext());
        if (assignmentInfo!=null&&Integer.parseInt(STPHelper.getWeekofyear()) != assignmentInfo.getSaveDate()) {
            STPHelper.parseCourseingoSaveAssinfo(STPHelper.getInstance().getContext(), courseInfo);
            assignmentInfo = STPHelper.getAssmtInfo(STPHelper.getInstance().getContext());
        }
        final NumberPicker numberPicker = new NumberPicker(getActivity());
        for (int i = 0; i < assignmentInfo.getEvents().size(); i++) {
            CourseEvent event = assignmentInfo.getEvents().get(i);
            if (!event.getIsClass()) {
//                String title = event.getParent().getCourseName() + "-" + event.getEventName() + " * " + event.getQuantity();
                String title = event.getCourseName() + "-" + event.getEventName() + " * " + event.getQuantity();
                eventMap.put(title, event);
            }
        }
        itemdata = new String[eventMap.size()];
        int i = 0;
        for (String key : eventMap.keySet()) {
            itemdata[i] = key;
            i++;
        }
        numberPicker.setMinValue(0);
        if (eventMap.size() <= 0) {
            return null;
        }
        numberPicker.setMaxValue(eventMap.size() - 1);
        numberPicker.setDisplayedValues(itemdata);
        numberPicker.setOnValueChangedListener(valueChangeListener);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Task");
        builder.setMessage("Each task is one hour :");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                valueChangeListener.onValueChange(numberPicker,
                        numberPicker.getValue(), numberPicker.getValue());
                ArrayList<CourseEvent> eventlist = assignmentInfo.getEvents();
                String s = "which = " + which;
                CourseEvent selectedEvent = eventMap.get(itemdata[numberPicker.getValue()]);
                updateEvent(selectedEvent);

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                valueChangeListener.onValueChange(numberPicker,
//                        numberPicker.getValue(), numberPicker.getValue());
                dialog.dismiss();
            }
        });

        builder.setView(numberPicker);
        return builder.create();
    }

    private void updateEvent(CourseEvent selectedEvent) {
        CourseInfo info = STPHelper.getAssmtInfo(STPHelper.getInstance().getContext());
        CourseEvent resEvent = null;
        for (CourseEvent event : info.getEvents()) {
            if (event.getEventName().equals(selectedEvent.getEventName())) {
                event.setQuantity(event.getQuantity() - 1);
                resEvent = event;

            }
        }
        if (resEvent.getQuantity() <= 0) {
            info.removeEvent(resEvent);
        }
        STPHelper.saveAssmtInfo(STPHelper.getInstance().getContext(), info);
    }


    public NumberPicker.OnValueChangeListener getValueChangeListener() {
        return valueChangeListener;
    }

    public void setValueChangeListener(NumberPicker.OnValueChangeListener valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }

    public void setItemInfo(TimeTableInfo ttinfo) {
        this.mttinfo = ttinfo;

    }

    public static int getColor(int i) {
        return Color.GREEN;
    }

    public static String getDuration(int i) {
        return String.format("%02d Hours", 1);
    }

    public String[] getItemdata() {
        return itemdata;
    }

    public void setItemdata(String[] itemdata) {
        this.itemdata = itemdata;
    }
}