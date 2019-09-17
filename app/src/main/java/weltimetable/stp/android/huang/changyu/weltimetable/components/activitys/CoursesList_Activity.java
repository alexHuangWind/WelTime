package weltimetable.stp.android.huang.changyu.weltimetable.components.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import weltimetable.stp.android.huang.changyu.weltimetable.R;

public class CoursesList_Activity extends AppCompatActivity {
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list_);
        lv = findViewById(R.id.LV_courses);

    }

}
