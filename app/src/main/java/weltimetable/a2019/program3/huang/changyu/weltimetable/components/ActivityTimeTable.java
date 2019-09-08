package weltimetable.a2019.program3.huang.changyu.weltimetable.components;

import weltimetable.a2019.program3.huang.changyu.weltimetable.R;
import weltimetable.a2019.program3.huang.changyu.weltimetable.components.ui.login.LoginActivity;
import weltimetable.a2019.program3.huang.changyu.weltimetable.views.FragmentsTabAdapter;
import weltimetable.a2019.program3.huang.changyu.weltimetable.views.PageTransformer3D;
import weltimetable.a2019.program3.huang.changyu.weltimetable.utils.AlertDialogsHelper;
import weltimetable.a2019.program3.huang.changyu.weltimetable.utils.BrowserUtil;

import android.content.Intent;
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
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Calendar;



/**
 * Created by changyu on 20.05.2019.
 * email：alexchyandroid@gmail.com
 */
public class ActivityTimeTable extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentsTabAdapter mAdapter;
    private ViewPager mViewPager;

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
            case R.id.LoginOrLogoff:
                Intent intent = new Intent(ActivityTimeTable.this, LoginActivity.class);
                startActivity(intent);
                return true;
            default:
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
        }
    }

    private void initView() {
        NavigationView navigationView = findViewById(R.id.nav_view1);
        BottomNavigationView navView = findViewById(R.id.nav_view2);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigationView.setNavigationItemSelectedListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("WelTimeTable");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        initFragments(toolbar);

    }

    /**
     * TODO use fireBase to storage timetableinfo
     * already success added firebase library and tested
     */
    private void initFireBase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }


    private void initCustomDialog() {
        final View alertLayout = getLayoutInflater().inflate(R.layout.dialog_add_subject, null);
        AlertDialogsHelper.getAddSubjectDialog(ActivityTimeTable.this, alertLayout, mAdapter, mViewPager);
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
                    Intent intent = new Intent(ActivityTimeTable.this, BlockActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }

    };


}
