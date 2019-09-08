package weltimetable.a2019.program3.huang.changyu.weltimetable.models;

public class BlockModel {
    private String mTime;
    private Boolean check = false;
    public final String UNEDITABLE = "#7A7A7A";
    public final String UNCHECKED_COLOR = "#FCFAF2";
    public final String CHECKED_COLOR = "#006DCC";
    public String defaultColor = "#FBFBFB";
    public final String mondayColor = "#0088CC";

    public final String tuesdayColor = "#DA4F49";
    public final String wednesdayColor = "#FAA732";
    public final String thursdayColor = "#5BB75B";
    public final String fridayolor = "#49afcd";
    private int row=0;
    private int column =0;
    private int mDayOfweek = 0;

    public int getDayOfweek() {
        return mDayOfweek;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setDayOfweek(int dayOfweek) {
        this.mDayOfweek = dayOfweek;
    }




    public BlockModel(String time, String defaultColor,int dayOfweek) {
        this.mTime = time;
        this.defaultColor = defaultColor;
        this.mDayOfweek = dayOfweek;
    }
    public BlockModel(String time, String defaultColor) {
        this.mTime = time;
        this.defaultColor = defaultColor;
    }
    public BlockModel(String time) {
        this.mTime = time;
    }

    public String getTime() {
        return mTime;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(String defaultColor) {
        this.defaultColor = defaultColor;
    }

}
