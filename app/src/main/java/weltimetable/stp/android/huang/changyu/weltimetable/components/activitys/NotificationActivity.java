package weltimetable.stp.android.huang.changyu.weltimetable.components.activitys;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import weltimetable.stp.android.huang.changyu.weltimetable.R;
import weltimetable.stp.android.huang.changyu.weltimetable.models.CourseInfo;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.STPHelper;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tv = (TextView) findViewById(R.id.TV_notification1);
        CourseInfo info = STPHelper.getCourseInfo(NotificationActivity.this);
        if (info.getFinalExam() == null || info.getFinalExam().equals("")) {
            STPHelper.toast(NotificationActivity.this, "No notification");
            return;
        }
        tv.setText(info.getFinalExam());

    }

}
