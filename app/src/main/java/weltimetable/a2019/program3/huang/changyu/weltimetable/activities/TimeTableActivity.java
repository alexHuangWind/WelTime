package weltimetable.a2019.program3.huang.changyu.weltimetable.activities;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;


import java.util.Calendar;

import weltimetable.a2019.program3.huang.changyu.weltimetable.R;
import weltimetable.a2019.program3.huang.changyu.weltimetable.adapters.FragmentsTabAdapter;
import weltimetable.a2019.program3.huang.changyu.weltimetable.fragments.FridayFragment;
import weltimetable.a2019.program3.huang.changyu.weltimetable.fragments.MondayFragment;
import weltimetable.a2019.program3.huang.changyu.weltimetable.fragments.SaturdayFragment;
import weltimetable.a2019.program3.huang.changyu.weltimetable.fragments.SundayFragment;
import weltimetable.a2019.program3.huang.changyu.weltimetable.fragments.ThursdayFragment;
import weltimetable.a2019.program3.huang.changyu.weltimetable.fragments.TuesdayFragment;
import weltimetable.a2019.program3.huang.changyu.weltimetable.fragments.WednesdayFragment;
import weltimetable.a2019.program3.huang.changyu.weltimetable.utils.AlertDialogsHelper;
import weltimetable.a2019.program3.huang.changyu.weltimetable.utils.BrowserUtil;

public class TimeTableActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentsTabAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        init();
    }

    private void init() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        setupFragments();
        setupCustomDialog();
//        getString(R.string.weltime_url)

    }


    private void setupCustomDialog() {
        final View alertLayout = getLayoutInflater().inflate(R.layout.dialog_add_subject, null);
        AlertDialogsHelper.getAddSubjectDialog(TimeTableActivity.this, alertLayout, adapter, viewPager);
    }

    private void setupFragments() {
        adapter = new FragmentsTabAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        adapter.addFragment(new MondayFragment(), getResources().getString(R.string.monday));
        adapter.addFragment(new TuesdayFragment(), getResources().getString(R.string.tuesday));
        adapter.addFragment(new WednesdayFragment(), getResources().getString(R.string.wednesday));
        adapter.addFragment(new ThursdayFragment(), getResources().getString(R.string.thursday));
        adapter.addFragment(new FridayFragment(), getResources().getString(R.string.friday));
        adapter.addFragment(new SaturdayFragment(), getResources().getString(R.string.saturday));
        adapter.addFragment(new SundayFragment(), getResources().getString(R.string.sunday));
        viewPager.setAdapter(adapter);
//        viewPager.setPageTransformer(true, new RotateUpTransformer());
        viewPager.setCurrentItem(day == 1 ? 6 : day - 2, true);
        tabLayout.setupWithViewPager(viewPager);
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
            default:
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
        }
    }
}
