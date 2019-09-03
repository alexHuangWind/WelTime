package weltimetable.a2019.program3.huang.changyu.weltimetable.views;

import weltimetable.a2019.program3.huang.changyu.weltimetable.R;
import weltimetable.a2019.program3.huang.changyu.weltimetable.models.TimeTableInfo;
import weltimetable.a2019.program3.huang.changyu.weltimetable.utils.AlertDialogsHelper;
import weltimetable.a2019.program3.huang.changyu.weltimetable.utils.DbHelper;
import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.app.Activity;
import android.support.v7.widget.CardView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Objects;


/**
 * Created by changyu on 20.05.2019.
 * email：alexchyandroid@gmail.com
 */
public class TTInfoAdapter extends ArrayAdapter<TimeTableInfo> {

    private Activity mActivity;
    private int mResource;
    private ArrayList<TimeTableInfo> mTTInfolist;
    private TimeTableInfo mTTInfo;
    private ListView mListView;


    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(final int position, View cardView, @NonNull ViewGroup parent) {
        String tc = Objects.requireNonNull(getItem(position)).getTeacher();
        String time_to = Objects.requireNonNull(getItem(position)).getToTime();
        String time_f = Objects.requireNonNull(getItem(position)).getFromTime();
        String room = Objects.requireNonNull(getItem(position)).getRoom();
        String sbj = Objects.requireNonNull(getItem(position)).getSubject();
        int color = getItem(position).getColor();

        mTTInfo = new TimeTableInfo(sbj, tc, room, time_f, time_to, color);
        final TTinfoViewHolder holder;

        if (cardView == null) {
            LayoutInflater inflater = LayoutInflater.from(mActivity);
            cardView = inflater.inflate(mResource, parent, false);
            holder = new TTinfoViewHolder();
            holder.subject = cardView.findViewById(R.id.subject);
            holder.popWindow = cardView.findViewById(R.id.popW);
            holder.teacher = cardView.findViewById(R.id.teacher);
            holder.time = cardView.findViewById(R.id.time);
            holder.room = cardView.findViewById(R.id.room);
            holder.cardView = cardView.findViewById(R.id.info_cardview);
            cardView.setTag(holder);
        } else {
            holder = (TTinfoViewHolder) cardView.getTag();
        }

        holder.subject.setText(mTTInfo.getSubject());
        holder.teacher.setText(mTTInfo.getTeacher());
        holder.room.setText(mTTInfo.getRoom());
        holder.time.setText(mTTInfo.getFromTime() + " - " + mTTInfo.getToTime());
        holder.cardView.setCardBackgroundColor(mTTInfo.getColor());
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

        hidePopUpMenu(holder);

        return cardView;
    }

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

    public TimeTableInfo getmTTInfo() {
        return mTTInfo;
    }

    public TTInfoAdapter(Activity activity, ListView listView, int resource, ArrayList<TimeTableInfo> objects) {
        super(activity, resource, objects);
        mTTInfolist = objects;
        mResource = resource;
        mListView = listView;
        mActivity = activity;
    }

    private static class TTinfoViewHolder {
        TextView teacher;
        TextView subject;
        ImageView popWindow;
        TextView room;
        TextView time;
        CardView cardView;
    }
}
