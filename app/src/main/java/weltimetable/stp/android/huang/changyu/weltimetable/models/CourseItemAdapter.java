package weltimetable.stp.android.huang.changyu.weltimetable.models;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import weltimetable.stp.android.huang.changyu.weltimetable.R;

public class CourseItemAdapter extends ArrayAdapter<String> {
    private final Activity activity;
    private final String[] CourseList;
    private final Integer[] imageId;
    public CourseItemAdapter(Activity activity,
                             String[] CourseList, Integer[] imageId) {
        super(activity, R.layout.courses_item_layout, CourseList);
        this.activity = activity;
        this.CourseList = CourseList;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.courses_item_layout, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.TV_course);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.IV_course);
        txtTitle.setText(CourseList[position]);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
