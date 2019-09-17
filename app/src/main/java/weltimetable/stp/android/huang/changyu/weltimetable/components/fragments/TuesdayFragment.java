package weltimetable.stp.android.huang.changyu.weltimetable.components.fragments;

import weltimetable.stp.android.huang.changyu.weltimetable.R;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.ConstentValue;
import android.view.View;

/**
 * Created by changyu on 20.05.stp.
 * email：alexchyandroid@gmail.com
 */
public class TuesdayFragment extends BaseFragment {
    @Override
    public int getLayoutID() {
        return R.layout.f_tuesday;
    }


    @Override
    public void initAdapter(View view) {
        super.setupAdapter(view, R.id.tuesdaylist, ConstentValue.TUESDAY);
    }

}