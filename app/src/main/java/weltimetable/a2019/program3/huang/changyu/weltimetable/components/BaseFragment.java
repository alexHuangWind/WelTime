package weltimetable.a2019.program3.huang.changyu.weltimetable.components;

import weltimetable.a2019.program3.huang.changyu.weltimetable.R;
import weltimetable.a2019.program3.huang.changyu.weltimetable.views.TTInfoAdapter;
import weltimetable.a2019.program3.huang.changyu.weltimetable.utils.DbHelper;
import weltimetable.a2019.program3.huang.changyu.weltimetable.utils.FragmentHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by changyu on 20.05.2019.
 * email：alexchyandroid@gmail.com
 */
public abstract class BaseFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutID(), container, false);
        initAdapter(view);
        return view;
    }


    protected void setupAdapter(@Nullable View view,@Nullable int listID,@Nullable String day) {
        DbHelper db = new DbHelper(getActivity());
        ListView  listView = view.findViewById(listID);
        TTInfoAdapter adapter = new TTInfoAdapter(getActivity(), listView, R.layout.listview_info_adapter, db.getTimeTableInfo(day));
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(FragmentHelper.setupListViewMultiSelect(getActivity(), listView, adapter, db));
    }


    public abstract int getLayoutID();

    public abstract void initAdapter(View view);


}
