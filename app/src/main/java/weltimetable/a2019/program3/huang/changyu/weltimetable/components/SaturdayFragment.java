package weltimetable.a2019.program3.huang.changyu.weltimetable.components;

import weltimetable.a2019.program3.huang.changyu.weltimetable.R;
import weltimetable.a2019.program3.huang.changyu.weltimetable.utils.ConstentValue;
import android.view.View;

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
