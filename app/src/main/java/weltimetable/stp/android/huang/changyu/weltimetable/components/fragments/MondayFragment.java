package weltimetable.stp.android.huang.changyu.weltimetable.components.fragments;

import weltimetable.stp.android.huang.changyu.weltimetable.R;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.STPHelper;

import android.view.View;

import java.util.Calendar;

/**
 * Created by changyu on 20.05.stp.
 * email：alexchyandroid@gmail.com
 */

public class MondayFragment extends BaseFragment {

    @Override
    public int getLayoutID() {
        return R.layout.f_monday;
    }


    @Override
    public void initAdapter(View view) {
        super.setupAdapter(view, R.id.mondaylist, STPHelper.getDateof(Calendar.MONDAY));
    }
}