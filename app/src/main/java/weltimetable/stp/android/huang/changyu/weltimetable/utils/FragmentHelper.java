package weltimetable.stp.android.huang.changyu.weltimetable.utils;

import weltimetable.stp.android.huang.changyu.weltimetable.models.TimeTableInfo;
import weltimetable.stp.android.huang.changyu.weltimetable.views.TTInfoAdapter;
import weltimetable.stp.android.huang.changyu.weltimetable.R;

import android.app.Activity;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import java.util.ArrayList;


/**
 * Created by changyu on 20.05.stp.
 * email：alexchyandroid@gmail.com
 */
public class FragmentHelper {

    public static AbsListView.MultiChoiceModeListener setupListViewMultiSelect(final Activity activity, final ListView listView, final TTInfoAdapter adapter, final DbHelper db) {
        return new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                final int checkedCount = listView.getCheckedItemCount();
                mode.setTitle(checkedCount + " " + activity.getResources().getString(R.string.selected));
                if (checkedCount == 0) mode.finish();
            }

            @Override
            public boolean onActionItemClicked(final ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_delete:
                        ArrayList<TimeTableInfo> removelist = new ArrayList<>();
                        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
                        for (int i = 0; i < checkedItems.size(); i++) {
                            int key = checkedItems.keyAt(i);
                            if (checkedItems.get(key)) {
                                db.deleteTTInfoById(adapter.getItem(key));
                                removelist.add(adapter.getInfoList().get(key));
                            }
                        }
                        adapter.getInfoList().removeAll(removelist);
                        db.updateTimeTableInfo(adapter.getmTTInfo());
                        adapter.notifyDataSetChanged();
                        mode.finish();
                        return true;

                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater menuInflater = mode.getMenuInflater();
                menuInflater.inflate(R.menu.toolbar_action_mode, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }
        };
    }
}
