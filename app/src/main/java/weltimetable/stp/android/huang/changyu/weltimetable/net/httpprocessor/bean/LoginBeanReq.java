package weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.bean;


/**
 * Login req javaean
 */
public class LoginBeanReq {
    public String userName;
    public String Password;
    public String studentID;

    public LoginBeanReq(String userName, String password) {
        this.userName = userName;
        this.Password = password;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "userName='" + userName + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
