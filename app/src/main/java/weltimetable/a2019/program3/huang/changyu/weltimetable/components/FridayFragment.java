package weltimetable.a2019.program3.huang.changyu.weltimetable.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import weltimetable.a2019.program3.huang.changyu.weltimetable.R;
import weltimetable.a2019.program3.huang.changyu.weltimetable.models.ConstentValue;


/**
 * Created by changyu on 20.05.2019.
 * email：alexchyandroid@gmail.com
 */
public class FridayFragment extends BaseFragment {


    @Override
    public int getLayoutID() {
        return R.layout.f_friday;
    }


    @Override
    public void initAdapter(View view) {
        super.setupAdapter(view, R.id.fridaylist, ConstentValue.FRIDAY);
    }

}
