package weltimetable.stp.android.huang.changyu.weltimetable.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import weltimetable.stp.android.huang.changyu.weltimetable.components.activitys.TimeTableActivity;
import weltimetable.stp.android.huang.changyu.weltimetable.models.CourseInfo;

import static org.junit.Assert.*;

public class STPHelperTest {
    private STPHelper simpleTest;
    @Before
    public void setUp() throws Exception {
        simpleTest = STPHelper.getInstance();
    }
    @Test
    public void getInstance() {
        Assert.assertNull(simpleTest.getInstance());

    }

    @Test
    public void getYear() {
        Assert.assertNull(simpleTest.getYear());


    }

    @Test
    public void getSemaster() {
        Assert.assertNull(simpleTest.getSemaster());

    }


    @Test
    public void getAssmtInfo() {
        Assert.assertNull(simpleTest.getAssmtInfo(simpleTest.getContext()));

    }



    @Test
    public void getCourseInfo() {
        Assert.assertNull(simpleTest.getCourseInfo(simpleTest.getContext()));

    }


    @Test
    public void getWeekofyear() {
        Assert.assertNull(simpleTest.getWeekofyear());

    }


    @Test
    public void getUnAssignedItem() {
        Assert.assertNull(simpleTest.getUnAssignedItem());

    }

    @Test
    public void getDayOfWeekList() {
        Assert.assertNull(simpleTest.getDayOfWeekList());

    }

    @Test
    public void getFragmentList() {
        Assert.assertNull(simpleTest.getFragmentList());

    }

    @Test
    public void getTodayDate() {
        Assert.assertNull(simpleTest.getTodayDate());

    }

    @Test
    public void getDateof() {
        Assert.assertNull(simpleTest.getDateof(3));

    }

    @Test
    public void isEmpty() {
        Assert.assertNull(simpleTest.isEmpty("fff"));

    }

    @Test
    public void fromTime2String() {
        Assert.assertNull(STPHelper.Fragment2String("2"));

    }


    @Test
    public void getTimeMillisByTiem() {
        Assert.assertNull(simpleTest.getTimeMillisByTiem("wednesday"));

    }

    @Test
    public void getContext() {
        Assert.assertNull(simpleTest.getContext());

    }

    @Test
    public void getUserInfo() {
        Assert.assertNull(simpleTest.getUserInfo());

    }

    @Test
    public void md5() {
        Assert.assertTrue(STPHelper.md5("123456").equals("e10adc3949ba59abbe56e057f20f883e"));

    }

}