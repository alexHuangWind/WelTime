package weltimetable.stp.android.huang.changyu.weltimetable.components.fragments;

import android.view.View;

import java.util.Calendar;

import weltimetable.stp.android.huang.changyu.weltimetable.R;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.STPHelper;


/**
 * Created by changyu on 20.05.stp.
 * email：alexchyandroid@gmail.com
 */
public class SundayFragment extends BaseFragment {


    @Override
    public int getLayoutID() {
        return R.layout.f_sunday;
    }


    @Override
    public void initAdapter(View view) {
        super.setupAdapter(view, R.id.sundaylist, STPHelper.getDateof(Calendar.SUNDAY));
    }

}
