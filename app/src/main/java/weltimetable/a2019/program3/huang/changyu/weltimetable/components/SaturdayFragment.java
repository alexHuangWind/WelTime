package weltimetable.a2019.program3.huang.changyu.weltimetable.components;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import weltimetable.a2019.program3.huang.changyu.weltimetable.R;
import weltimetable.a2019.program3.huang.changyu.weltimetable.models.ConstentValue;
import weltimetable.a2019.program3.huang.changyu.weltimetable.models.TTInfoAdapter;
import weltimetable.a2019.program3.huang.changyu.weltimetable.utils.DbHelper;
import weltimetable.a2019.program3.huang.changyu.weltimetable.utils.FragmentHelper;

/**
 * Created by changyu on 20.05.2019.
 * email：alexchyandroid@gmail.com
 */
public class SaturdayFragment extends BaseFragment {
    @Override
    public int getLayoutID() {
        return R.layout.f_saturday;
    }


    @Override
    public void initAdapter(View view) {
        super.setupAdapter(view, R.id.saturdaylist, ConstentValue.SATURDAY);
    }
}
