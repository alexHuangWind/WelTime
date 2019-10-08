package weltimetable.stp.android.huang.changyu.weltimetable.components.activitys;

import weltimetable.stp.android.huang.changyu.weltimetable.components.fragments.FragmentFactory;
import weltimetable.stp.android.huang.changyu.weltimetable.components.ui.login.LoginActivity;
import weltimetable.stp.android.huang.changyu.weltimetable.R;
import weltimetable.stp.android.huang.changyu.weltimetable.models.CourseEvent;
import weltimetable.stp.android.huang.changyu.weltimetable.models.CourseInfo;
import weltimetable.stp.android.huang.changyu.weltimetable.models.STPController;
import weltimetable.stp.android.huang.changyu.weltimetable.models.TimeTableInfo;
import weltimetable.stp.android.huang.changyu.weltimetable.models.UserInfo;
import weltimetable.stp.android.huang.changyu.weltimetable.net.CallAPI;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.CourseInfoListener;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.bean.LoginBeanReq;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.bean.LoginBeanResp;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.bean.UploadInfoBean;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.bean.upLoadInfoReq;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.http.HttpCallback;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.http.HttpHelper;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.processor.OkHttpProcessor;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.processor.VolleyProcessor;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.CalendarUtil;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.ConstentValue;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.DbHelper;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.STPHelper;
import weltimetable.stp.android.huang.changyu.weltimetable.models.Adapter.FragmentsTabAdapter;
import weltimetable.stp.android.huang.changyu.weltimetable.models.Adapter.PageTransformer3D;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.AlertDialogsHelper;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.BrowserUtil;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.SharedPrefsUtils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;


/**
 * Created by changyu on 20.05.stp.
 * email：alexchyandroid@gmail.com
 */
public class TimeTableActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentsTabAdapter mAdapter;
    private ViewPager mViewPager;
    private Button BT_SBbutton;
    private AlertDialog dialog;
    private MenuItem LoginItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        init();
    }

    private void init() {
        initView();
        initCustomDialog();
//        initFireBase();
        STPHelper.getInstance().setmContext(TimeTableActivity.this.getApplicationContext());
//        HttpHelper.init(new VolleyProcessor(TimeTableActivity.this.getApplicationContext()));
    }

    private void SendLastweekCoureseData(UploadInfoBean ulbean) {
        HashMap loginMap = new HashMap<>();
        UserInfo uinfo = STPHelper.getInstance().getUserInfo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd ",
                Locale.getDefault());
        Date d = new Date();
        d.setTime(System.currentTimeMillis());
        Random r = new Random();
        ulbean.setFinishdate(sdf.format(d)+(r.nextInt(6)+10)+":00:00");
        loginMap.put("year", ulbean.getYear());
        loginMap.put("week", ulbean.getWeek());
        loginMap.put("studentID", uinfo.getStudentID());
        loginMap.put("courseCode", ulbean.getCourseCode());
        loginMap.put("taskname", ulbean.getTaskname());
        loginMap.put("finishtime", ulbean.getFinishtime());
        loginMap.put("finishdate", ulbean.getFinishdate());
        //SendLastweekCoureseData
        HttpHelper.obtain().post(ConstentValue.BASIC_URL + "/PostUserTime",

                loginMap, new HttpCallback<LoginBeanResp>() {
                    @Override
                    public void onSuccess(LoginBeanResp loginBean) {
                        Log.d("alexTimeTable: ", loginBean.toString());
                        if (loginBean.getResult() != null && !loginBean.getResult().equals("200")) {
                            onFailed(loginBean.getResult());
                            return;
                        }
                        SharedPrefsUtils.setStringPreference(TimeTableActivity.this, SharedPrefsUtils.LOGIN, loginBean.toString());
                        SharedPrefsUtils.setBooleanPreference(TimeTableActivity.this, SharedPrefsUtils.LOGIN, true);
                        TimeTableActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(TimeTableActivity.this, "success.." + loginBean.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
//                    email:'Andrew.Liu@it.weltec.ac.nz',
//                    studentID:'1234',
//                    passWord:'5dd7ad06e369442c814aaabd2d1a792b'

                    @Override
                    public void onFailed(String string) {
                        Log.d("alexTimeTable: ", string);
                        TimeTableActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(TimeTableActivity.this, "Request Failed... Code: " + string, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }

    private void doUploadData() {
        Gson g = new Gson();
        UploadInfoBean ulbean1 = g.fromJson("{year:'2019',week:'26',studentID:'2182821',courseCode:'IT001',taskname:'DeadLine Reading',arrangetime:'4',finishtime:'3',finishdate:'2019-10-06 10:39:21'}", UploadInfoBean.class);
        UploadInfoBean ulbean2 = g.fromJson("{year:'2019',week:'26',studentID:'2182821',courseCode:'IT001',taskname:'DeadLine Reading',arrangetime:'4',finishtime:'2',finishdate:'2019-10-06 11:39:21'}", UploadInfoBean.class);
        SendLastweekCoureseData(ulbean1);
        SendLastweekCoureseData(ulbean2);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        final NavigationView navigationView = findViewById(R.id.nav_view);
        switch (item.getItemId()) {
            case R.id.moodlewebsitemenu:
                String moodleWebsite = "https://moodle.weltec.ac.nz";
                if (!TextUtils.isEmpty(moodleWebsite)) {
                    BrowserUtil.openUrlInChromeCustomTab(getApplicationContext(), moodleWebsite);
                } else {
                    Snackbar.make(navigationView, R.string.school_website_snackbar, Snackbar.LENGTH_SHORT).show();
                }
                return true;
            case R.id.schoolwebsitemenu:
                String schoolWebsite = "https://weltec.ac.nz";
                if (!TextUtils.isEmpty(schoolWebsite)) {
                    BrowserUtil.openUrlInChromeCustomTab(getApplicationContext(), schoolWebsite);
                } else {
                    Snackbar.make(navigationView, R.string.school_website_snackbar, Snackbar.LENGTH_SHORT).show();
                }
                return true;
            case R.id.report:
                String Report = "https://weltec.ac.nz";
                if (!TextUtils.isEmpty(Report)) {
                    BrowserUtil.openUrlInChromeCustomTab(getApplicationContext(), Report);
                } else {
                    Snackbar.make(navigationView, R.string.school_website_snackbar, Snackbar.LENGTH_SHORT).show();
                }
                return true;
            case R.id.sync:
                DbHelper db = new DbHelper(TimeTableActivity.this);
                ArrayList<TimeTableInfo> list = db.getCalendarList();
                Calendar cal = Calendar.getInstance();
                boolean flag = true;
                for (TimeTableInfo info : list) {
                    if (!CalendarUtil.getInstance().addCalendarEvent(TimeTableActivity.this, info.getSubject(), "room: " + info.getRoom() + " Tutor: " + info.getTeacher(), info.getFromtimeMillis())) {
                        flag = false;
                    }
                }
                if (flag) {
                    STPHelper.toast(TimeTableActivity.this, "sync calendar success!");
                } else {
                    STPHelper.toast(TimeTableActivity.this, "sync calendar fail!");
                }
                return true;

            case R.id.AddCourse:
//                STPHelper.toast(TimeTableActivity.this, " add course popwindow");
//                doUploadData();
                Test();
                return true;

            case R.id.LoginOrLogoff:
                boolean b = SharedPrefsUtils.getBooleanPreference(TimeTableActivity.this, SharedPrefsUtils.LOGIN, false);
                if (b) {
                    SharedPrefsUtils.setStringPreference(TimeTableActivity.this, SharedPrefsUtils.LOGIN, "");
                    SharedPrefsUtils.setBooleanPreference(TimeTableActivity.this, SharedPrefsUtils.LOGIN, false);
                    Toast.makeText(TimeTableActivity.this, "Logout success...", Toast.LENGTH_SHORT).show();
                    LoginItem.setTitle("Login");
                } else {
                    STPHelper.startActivity(TimeTableActivity.this, LoginActivity.class);
                }
                return true;

            default:
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
        }
    }

    private void Test() {
        Gson g = new Gson();
        String v = "{\"courseID\":\"IT001\",\"courseName\":\"ProjectManagement\",\"endWeekOfYear\":\"undefined\",\"events\":[{\"CourseCode\":\"undefined\",\"CourseName\":\"ProjectManagement\",\"EndWeek\":-1,\"EventName\":\"ProjectManagement\",\"Location\":\"Petone\",\"Major\":\"undefined\",\"StartWeek\":-1,\"TutorID\":\"undefined\",\"TutorName\":\"Robert\",\"dayOfWeek\":3,\"finalExam\":\"Aug 8, 2019 19:09:50\",\"isClass\":true,\"quantity\":2,\"startTime\":\"9:00\"},{\"CourseCode\":\"undefined\",\"CourseName\":\"ProjectManagement\",\"EndWeek\":-1,\"EventName\":\"DeadLine Reading \",\"Location\":\"undefined\",\"Major\":\"undefined\",\"StartWeek\":-1,\"TutorID\":\"undefined\",\"TutorName\":\"Robert\",\"dayOfWeek\":-1,\"finalExam\":\"Aug 8, 2019 19:09:50\",\"isClass\":false,\"quantity\":2,\"startTime\":\"undefined\"},{\"CourseCode\":\"undefined\",\"CourseName\":\"ProjectManagement\",\"EndWeek\":-1,\"EventName\":\"Reading Book\",\"Location\":\"undefined\",\"Major\":\"undefined\",\"StartWeek\":-1,\"TutorID\":\"undefined\",\"TutorName\":\"Robert\",\"dayOfWeek\":-1,\"finalExam\":\"Aug 8, 2019 19:09:50\",\"isClass\":false,\"quantity\":2,\"startTime\":\"undefined\"},{\"CourseCode\":\"undefined\",\"CourseName\":\"ProjectManagement\",\"EndWeek\":-1,\"EventName\":\"ProjectManagement\",\"Location\":\"Petone\",\"Major\":\"undefined\",\"StartWeek\":-1,\"TutorID\":\"undefined\",\"TutorName\":\"Robert\",\"dayOfWeek\":5,\"finalExam\":\"Aug 8, 2019 19:09:50\",\"isClass\":true,\"quantity\":2,\"startTime\":\"11:00\"}],\"quantity\":-1,\"tutor\":\"Robert\"}";
        CourseInfo info = g.fromJson(v,CourseInfo.class);
        insertCourseIntoTimeTable(info);
        DbHelper db = new DbHelper(TimeTableActivity.this);
        STPHelper.saveCourseInfo(TimeTableActivity.this, info);
        db.insertCourseInfo(info);
    }

    private void initView() {
        NavigationView navigationView = findViewById(R.id.nav_view1);
        BottomNavigationView navView = findViewById(R.id.nav_view2);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigationView.setNavigationItemSelectedListener(this);
        LoginItem = navigationView.getMenu().findItem(R.id.LoginOrLogoff);
        Toolbar toolbar = findViewById(R.id.toolbar);
        BT_SBbutton = findViewById(R.id.add_sub);
        toolbar.setTitle("WelTimeTable");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        initListener();
        initFragments(toolbar);
        initTimeTableInfo();
    }

    private void initListener() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TimeTableActivity.this);
        builder.setTitle("input course code");
        // Set up the input
        final EditText input;
        input = new EditText(TimeTableActivity.this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //                m_Text = input.getText().toString();
                if (input.getText().toString().equals("IT001")) {
                    SharedPreferences.Editor editor = getSharedPreferences(ConstentValue.TAG_prd, MODE_PRIVATE).edit();
                    editor.putBoolean("isCourseDownload", true);
                    editor.apply();
                }
               CourseInfo defaultInfo =  STPController.getInstance().getCourseInfo(input.getText().toString(), new CourseInfoListener() {
                    @Override
                    public void methodToCallBack(CourseInfo info) {
                        if (info != null) {
                            Gson g = new Gson();
                            String res = g.toJson(info);

                            insertCourseIntoTimeTable(info);
                            DbHelper db = new DbHelper(TimeTableActivity.this);
                            STPHelper.saveCourseInfo(TimeTableActivity.this, info);
                            db.insertCourseInfo(info);


                        } else {
                            STPHelper.toast(TimeTableActivity.this, "CODE NOT MATCT ANY COURSE");
                        }
                    }
                });
                dialog.dismiss();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        BT_SBbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }

    private void insertCourseIntoTimeTable(CourseInfo info) {
        for (CourseEvent event : info.getEvents()) {
            if (event.getIsClass()) {
                TimeTableInfo ttinfo = new TimeTableInfo();
                ttinfo.setItemID(event.getStartTime() + STPHelper.getDateof(event.getDayOfWeek()));
            }
        }

    }

    private void initTimeTableInfo() {
        DbHelper dbHelper = new DbHelper(TimeTableActivity.this);
        ArrayList<String> listD = STPHelper.getDayOfWeekList();
        ArrayList<String> listF = STPHelper.getFragmentList();
        int j = 0;
        for (String var : listF) {
            TimeTableInfo info = STPHelper.getUnAssignedItem();
            for (int i = 8; i <= 18; i++) {
                info.setFragment(var);
                info.setFromTime(String.format("%02d:%02d", i, 00));
                info.setItemID(listD.get(j) + ":" + String.format("%02d:%02d", i, 00));
                info.setDate(listD.get(j));
                long time = STPHelper.getTimeMillisByTiem(i + ":" + "00 " + listD.get(j));
                info.setFromtimeMillis(time);
                TimeTableInfo info2 = dbHelper.getTimeTableItemInfoByItemID(info.getItemID());
                if (info2 == null) {
                    dbHelper.insertTimeTableInfo(info);
                }
            }
            j++;
        }

    }

    /**
     * android use fireBase to storage timetableinfo
     * already success added firebase library and tested
     */
//    private void initFireBase() {
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello, World!");
//        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//            }
//        });
//    }
    private void initCustomDialog() {
        final View alertLayout = getLayoutInflater().inflate(R.layout.dialog_add_subject, null);
        AlertDialogsHelper.getAddSubjectDialog(TimeTableActivity.this, alertLayout, mAdapter, mViewPager);
    }

    private void initFragments(Toolbar toolbar) {
        initTopNavigation(toolbar);
        initBottomNaviagtion();

    }

    private void initBottomNaviagtion() {


    }

    private void initTopNavigation(Toolbar toolbar) {
        mAdapter = new FragmentsTabAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.viewPager);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        mAdapter.addFragment(FragmentFactory.getInstance().getFragmentByTag(getResources().getString(R.string.monday)), getResources().getString(R.string.monday));
        mAdapter.addFragment(FragmentFactory.getInstance().getFragmentByTag(getResources().getString(R.string.tuesday)), getResources().getString(R.string.tuesday));
        mAdapter.addFragment(FragmentFactory.getInstance().getFragmentByTag(getResources().getString(R.string.wednesday)), getResources().getString(R.string.wednesday));
        mAdapter.addFragment(FragmentFactory.getInstance().getFragmentByTag(getResources().getString(R.string.thursday)), getResources().getString(R.string.thursday));
        mAdapter.addFragment(FragmentFactory.getInstance().getFragmentByTag(getResources().getString(R.string.friday)), getResources().getString(R.string.friday));
        mAdapter.addFragment(FragmentFactory.getInstance().getFragmentByTag(getResources().getString(R.string.saturday)), getResources().getString(R.string.saturday));
        mAdapter.addFragment(FragmentFactory.getInstance().getFragmentByTag(getResources().getString(R.string.sunday)), getResources().getString(R.string.sunday));
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageTransformer(true, new PageTransformer3D());
        mViewPager.setCurrentItem(day == 1 ? 6 : day - 2, true);
        toolbar.setTitle(mAdapter.getPageTitle(day == 1 ? 6 : day - 2));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                toolbar.setTitle(mAdapter.getPageTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment mFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent = new Intent(TimeTableActivity.this, weltimetable.stp.android.huang.changyu.weltimetable.components.activitys.BlockActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    STPHelper.startActivity(TimeTableActivity.this, NotificationActivity.class);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onResume() {
        super.onResume();
        boolean b = SharedPrefsUtils.getBooleanPreference(TimeTableActivity.this, SharedPrefsUtils.LOGIN, false);
        if (b) {
            LoginItem.setTitle("Logout");
        } else {
            LoginItem.setTitle("Login");
        }
    }

}
