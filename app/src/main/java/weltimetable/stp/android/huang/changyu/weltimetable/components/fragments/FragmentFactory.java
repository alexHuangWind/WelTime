package weltimetable.stp.android.huang.changyu.weltimetable.components.fragments;

import weltimetable.stp.android.huang.changyu.weltimetable.utils.ConstentValue;
import android.support.v4.app.Fragment;
import java.util.HashMap;

/**
 * Created by changyu on 20.05.stp.
 * email：alexchyandroid@gmail.com
 */
public class FragmentFactory {

    private MondayFragment mondayfragment;
    private TuesdayFragment tuesdayfragment;
    private WednesdayFragment wednesdayfragment;
    private ThursdayFragment thursdayfragment;
    private FridayFragment fridayfragment;
    private SaturdayFragment saturdayfragment;
    private SundayFragment sundayfragment;
    private static FragmentFactory mFactory;
    private HashMap<String, Fragment> mHashMap = new HashMap<>();

    private FragmentFactory() {

    }

    public static FragmentFactory getInstance() {
        if (mFactory == null) {
            synchronized (FragmentFactory.class) {
                if (mFactory == null) {
                    mFactory = new FragmentFactory();
                }
            }
        }
        return mFactory;
    }

    public Fragment getFragmentByTag(String tag) {
        if (tag.equals(ConstentValue.MONDAY) && mondayfragment == null) {
            mondayfragment = new MondayFragment();
            mHashMap.put(ConstentValue.MONDAY, mondayfragment);
        }
        if (tag.equals(ConstentValue.TUESDAY) && tuesdayfragment == null) {
            tuesdayfragment = new TuesdayFragment();
            mHashMap.put(ConstentValue.TUESDAY, tuesdayfragment);
        }

        if (tag.equals(ConstentValue.WEDNESDAY) && wednesdayfragment == null) {
            wednesdayfragment = new WednesdayFragment();
            mHashMap.put(ConstentValue.WEDNESDAY, wednesdayfragment);
        }
        if (tag.equals(ConstentValue.THURSDAY) && thursdayfragment == null) {
            thursdayfragment = new ThursdayFragment();
            mHashMap.put(ConstentValue.THURSDAY, thursdayfragment);
        }
        if (tag.equals(ConstentValue.FRIDAY) && fridayfragment == null) {
            fridayfragment = new FridayFragment();
            mHashMap.put(ConstentValue.FRIDAY, fridayfragment);
        }
        if (tag.equals(ConstentValue.SATURDAY) && saturdayfragment == null) {
            saturdayfragment = new SaturdayFragment();
            mHashMap.put(ConstentValue.SATURDAY, saturdayfragment);
        }
        if (tag.equals(ConstentValue.SUNDAY) && sundayfragment == null) {
            sundayfragment = new SundayFragment();
            mHashMap.put(ConstentValue.SUNDAY, sundayfragment);
        }
        return mHashMap.get(tag);

    }
}