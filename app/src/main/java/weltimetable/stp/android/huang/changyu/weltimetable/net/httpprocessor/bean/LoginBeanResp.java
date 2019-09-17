package weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.bean;


/**
 * 快递查询的bjavaean
 */
public class LoginBeanResp extends BasicResp {

    public String userName;
    public String Password;

    @Override
    public String toString() {
        return "LoginBean{" +
                "userName='" + userName + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
