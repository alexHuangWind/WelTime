package weltimetable.a2019.program3.huang.changyu.weltimetable.models;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by changyu on 20.05.2019.
 * email：alexchyandroid@gmail.com
 */
public abstract class BasePageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View view, float position) {
        if (position < -1 || position >= 1) {
            other(view, position);
        } else if (position < 1) {
            if (position > -1 && position < 0.0f) {
                // [-1,0]
                touch2Left(view, position);
            } else if (position >= 0.0f && position < 1.0f) {
                // (0,1]
                touch2Right(view, position);
            }
        }
    }

    public abstract void touch2Left(View view, float position);

    public abstract void touch2Right(View view, float position);

    public abstract void other(View view, float position);
}