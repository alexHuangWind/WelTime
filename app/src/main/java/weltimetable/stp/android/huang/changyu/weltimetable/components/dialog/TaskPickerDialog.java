package weltimetable.stp.android.huang.changyu.weltimetable.components.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.NumberPicker;

public class TaskPickerDialog extends DialogFragment {
    private NumberPicker.OnValueChangeListener valueChangeListener;

    private static String[] itemdata = null;
    private static  ListView listView = null;

    public static String getSubject(int i) {
        return ""+ itemdata[i];
    }

    public static String getTeacher(int i) {
        return "Robert";
    }

    public static String getRoom(int i) {
        return "B106";
    }

    public static int getColor(int i) {
        return Color.GREEN;
    }

    public static String getFromTime(int i) {
//        from_time.setText(String.format("%02d:%02d", hourOfDay, minute));
//        timetableinfo.setFromTime(String.format("(%02d:%02d)", hourOfDay, minute));
        return String.format("%02d:%02d", 8, 00);
    }

    public static String getDuration(int i) {
        return String.format("%02d Hours", 2);
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public String[] getItemdata() {
        return itemdata;
    }

    public void setItemdata(String[] itemdata) {
        this.itemdata = itemdata;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final NumberPicker numberPicker = new NumberPicker (getActivity());

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(itemdata.length-1);
        numberPicker.setDisplayedValues(itemdata);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Task");
        builder.setMessage("Each task is one hour :");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                valueChangeListener.onValueChange(numberPicker,
                        numberPicker.getValue(), numberPicker.getValue());
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                valueChangeListener.onValueChange(numberPicker,
                        numberPicker.getValue(), numberPicker.getValue());
            }
        });

        builder.setView(numberPicker);
        return builder.create();
    }

    public NumberPicker.OnValueChangeListener getValueChangeListener() {
        return valueChangeListener;
    }

    public void setValueChangeListener(NumberPicker.OnValueChangeListener valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }
}