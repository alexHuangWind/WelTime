package weltimetable.stp.android.huang.changyu.weltimetable.models.Adapter;

import weltimetable.stp.android.huang.changyu.weltimetable.R;
import weltimetable.stp.android.huang.changyu.weltimetable.components.dialog.TaskPickerDialog;
import weltimetable.stp.android.huang.changyu.weltimetable.models.TimeTableInfo;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.AlertDialogsHelper;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.DbHelper;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.STPHelper;


import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

import android.support.annotation.NonNull;
import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Objects;


/**
 * Created by changyu on 20.05.stp.
 * email：alexchyandroid@gmail.com
 */
public class TTInfoAdapter extends ArrayAdapter<TimeTableInfo> {

    private Activity mActivity;
    private int mResource;
    private ArrayList<TimeTableInfo> mTTInfolist;
    private ListView mListView;
    private DbHelper dbHelper;
    private MediaPlayer mMp;
    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView( int position, View cardView, @NonNull ViewGroup parent) {

        if(dbHelper == null){
        dbHelper = new DbHelper(mActivity);
        }
        TimeTableInfo mTTInfo; mTTInfo = dbHelper.getTimeTableItemInfoByID(Objects.requireNonNull(getItem(position)).getId());
//        String tc = info.getTeacher();
//        String duration = info.getDuration();
//        String time_f = info.getFromTime();
//        String room = info.getRoom();
//        String sbj = info.getSubject();
//        int progress = info.getProcess();
//        int id = info.getId();
//        int color = info.getColor();
//        mTTInfo = new TimeTableInfo(id,sbj, tc, room, time_f, duration, color, progress);
        TTinfoViewHolder holder;

//        if (cardView == null) {
            LayoutInflater inflater = LayoutInflater.from(mActivity);
            cardView = inflater.inflate(mResource, parent, false);
            holder = new TTinfoViewHolder(cardView);
            holder.subject = cardView.findViewById(R.id.subject);
            holder.popWindow = cardView.findViewById(R.id.popW);
            holder.teacher = cardView.findViewById(R.id.teacher);
            holder.time = cardView.findViewById(R.id.time);
            holder.room = cardView.findViewById(R.id.room);
            holder.finishButton = cardView.findViewById(R.id.BT_Finish);
            holder.progressSeekBar = cardView.findViewById(R.id.SB_Progress);
            holder.room = cardView.findViewById(R.id.room);
            holder.cardView = cardView.findViewById(R.id.info_cardview);
            cardView.setTag(holder);
//        } else {
//            holder = (TTinfoViewHolder) cardView.getTag();
//        }

        holder.subject.setText(mTTInfo.getSubject());
        holder.teacher.setText(mTTInfo.getTeacher());
        holder.room.setText(mTTInfo.getRoom());
        holder.time.setText(mTTInfo.getFromTime() + " - " + mTTInfo.getDuration());
        holder.cardView.setBackgroundColor(mTTInfo.getColor());
        holder.progressSeekBar.setProgress(mTTInfo.getProcess());
        if(holder.progressSeekBar.getProgress()==100){
            holder.finishButton.setText("DONE");
            holder.cardView.setBackgroundResource(R.drawable.glassbreak);
        }
        holder.popWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu ppm = new PopupMenu(mActivity, holder.popWindow);
                final DbHelper db = new DbHelper(mActivity);
                ppm.getMenuInflater().inflate(R.menu.popup_menu, ppm.getMenu());
                ppm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete_popup:
                                db.deleteTTInfoById(getItem(position));
                                db.updateTimeTableInfo(getItem(position));
                                mTTInfolist.remove(position);
                                notifyDataSetChanged();
                                return true;
                            case R.id.edit_popup:
                                final View alertLayout = mActivity.getLayoutInflater().inflate(R.layout.dialog_add_subject, null);
                                AlertDialogsHelper.getEditSubjectDialog(mActivity, alertLayout, mTTInfolist, mListView, position);
                                notifyDataSetChanged();
                                return true;
                            default:
                                return onMenuItemClick(item);
                        }
                    }
                });
                ppm.show();
            }
        });
        holder.finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = holder.progressSeekBar.getProgress();
                onTaskStatueChanged(holder, mTTInfo.getId(),mTTInfo.getColor(),true);

                //                STPHelper.toast(mActivity,"progress : "+i);
            }
        });
        holder.progressSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                onTaskStatueChanged(holder, mTTInfo.getId(),mTTInfo.getColor(),false);
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            ////TODO setListItems
            @Override
            public void onClick(View view) {
                String[] itemdata = new String[10];
                for (int i = 0; i < 10; i++) {
                    itemdata[i] = "it730" + i;
                }
                TaskPickerDialog newFragment = new TaskPickerDialog();
                newFragment.setItemdata(itemdata);
//                newFragment.setListView(mListView);
                newFragment.setValueChangeListener(new NumberPicker.OnValueChangeListener() {

                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        DbHelper db = new DbHelper(mActivity);
                        TTInfoAdapter TTInfoAdapter = (TTInfoAdapter) mListView.getAdapter(); // In order to get notifyDataSetChanged() method.
                       TimeTableInfo timeTableInfo = new TimeTableInfo();
                        timeTableInfo.setSubject(TaskPickerDialog.getSubject(i));
                        timeTableInfo.setTeacher(TaskPickerDialog.getTeacher(i));
                        timeTableInfo.setRoom(TaskPickerDialog.getRoom(i));
                        timeTableInfo.setColor(TaskPickerDialog.getColor(i));
                        timeTableInfo.setFromTime(TaskPickerDialog.getFromTime(i));
                        timeTableInfo.setDuration(TaskPickerDialog.getDuration(i));
                        timeTableInfo.setId(mTTInfo.getId());
                        db.updateTimeTableInfo(timeTableInfo);
                        TTInfoAdapter.notifyDataSetChanged();
                    }
                });
                newFragment.show(mActivity.getFragmentManager(), "Task picker");
            }
        });
        hidePopUpMenu(holder);

        return cardView;
    }

    private void onTaskStatueChanged(TTinfoViewHolder holder,int id,int color, boolean flag) {
        if (flag) {
            if (holder.progressSeekBar.getProgress()!= 100) {
                holder.finishButton.setText("DONE");
                holder.cardView.setBackgroundResource(R.drawable.glassbreak);
                holder.progressSeekBar.setProgress(100);
                mMp = MediaPlayer.create(mActivity.getApplicationContext(), R.raw.sniper_shot);
                mMp.start();
                UpdateProcessByID(id,100);
                return;
            }
            holder.finishButton.setText("UNFINISHED");
            holder.cardView.setBackgroundColor(color);
            holder.progressSeekBar.setProgress(0);
            UpdateProcessByID(id,0);
            return;
        }
        if (holder.progressSeekBar.getProgress() == 100) {
            holder.finishButton.setText("DONE");
            holder.cardView.setBackgroundResource(R.drawable.glassbreak);
            mMp = MediaPlayer.create(mActivity.getApplicationContext(), R.raw.sniper_shot);
            mMp.start();
            UpdateProcessByID(id,100);
            return;
        }
        holder.finishButton.setText("UNFINISHED");
        holder.cardView.setBackgroundColor(color);
        UpdateProcessByID(id,holder.progressSeekBar.getProgress());
        return;
    }

    private void UpdateProcessByID(int id,int process) {
        DbHelper dbHelper = new DbHelper(mActivity);
//        TimeTableInfo timetableinfo = new TimeTableInfo();
//        timetableinfo.setSubject(holder.subject.getText().toString());
        ////TODO setFragment
//        timetableinfo.setFragment(fragment.find() ? fragment.group() : null);
//        fragment.group();
//        timetableinfo.setTeacher(holder.teacher.getText().toString());
//        timetableinfo.setRoom(holder.room.getText().toString());
//        timetableinfo.setColor(holder.color);
//        timetableinfo.setId(holder.id);
//        timetableinfo.setProcess(holder.progressSeekBar.getProgress());
        dbHelper.updateTimeTableProcessById(id,process);
    }

//    private void UpdateProcessByID(TTinfoViewHolder holder) {
//        DbHelper dbHelper = new DbHelper(mActivity);
//        TimeTableInfo timetableinfo = new TimeTableInfo();
//        timetableinfo.setSubject(holder.subject.getText().toString());
//        ////TODO setFragment
////        timetableinfo.setFragment(fragment.find() ? fragment.group() : null);
////        fragment.group();
//        timetableinfo.setTeacher(holder.teacher.getText().toString());
//        timetableinfo.setRoom(holder.room.getText().toString());
//        timetableinfo.setColor(holder.color);
//        timetableinfo.setId(holder.id);
//        timetableinfo.setProcess(holder.progressSeekBar.getProgress());
//        dbHelper.updateTimeTableInfo(timetableinfo);
//    }

    private void hidePopUpMenu(TTinfoViewHolder holder) {
        SparseBooleanArray checkedItems = mListView.getCheckedItemPositions();
        if (checkedItems.size() > 0) {
            for (int i = 0; i < checkedItems.size(); i++) {
                int key = checkedItems.keyAt(i);
                if (checkedItems.get(key)) {
                    holder.popWindow.setVisibility(View.INVISIBLE);
                }
            }
        } else {
            holder.popWindow.setVisibility(View.VISIBLE);
        }
    }

    public ArrayList<TimeTableInfo> getInfoList() {
        return mTTInfolist;
    }

//    public TimeTableInfo getmTTInfo() {
//        return mTTInfo;
//    }

    public TTInfoAdapter(Activity activity, ListView listView, int resource, ArrayList<TimeTableInfo> objects) {
        super(activity, resource, objects);
        mTTInfolist = objects;
        mResource = resource;
        mListView = listView;
        mActivity = activity;
    }

    private static class TTinfoViewHolder extends RecyclerView.ViewHolder {
        TextView teacher;
        TextView subject;
        ImageView popWindow;
        TextView room;
        TextView time;
        Button finishButton;
        SeekBar progressSeekBar;
        CardView cardView;

        public TTinfoViewHolder(View itemView) {
            super(itemView);
        }
    }
}
