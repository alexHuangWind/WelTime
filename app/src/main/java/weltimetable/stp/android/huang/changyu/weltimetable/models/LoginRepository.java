package weltimetable.stp.android.huang.changyu.weltimetable.models;

import android.content.Context;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;
    private static Context mContext;

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource, Context context) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        mContext = context;
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<LoggedInUser> login(String username, String password) {
        // login()
        userLogin(username, password);
        Result<LoggedInUser> result = dataSource.login(username, password);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
        }
        return result;
    }

    private void userLogin(String username, String password) {
        {

//            //访问网络
//            HttpHelper.obtain().get("",
//                    null, new HttpCallback<ExpressBean>() {
//                        @Override
//                        public void onSuccess(ExpressBean expressBean) {
//                            Log.i("onSuccess: ", expressBean.data.toString());
//                            StringBuffer sb = new StringBuffer();
//                            if (expressBean != null) {
//                                ArrayList<ExpressBean.DataBean> datas = expressBean.data;
//                                for (ExpressBean.DataBean data : datas) {
//                                    sb.append("userName：")
//                                            .append("dfdfdf" + "\r\n")
//                                            .append("password：")
//                                            .append("sdfsdfs" + "\r\n" + "\r\n");
////                                    textView.setText(sb.toString());
//
//                                }
//
//                            }
//                        }
//                        @Override
//                        public void onFailed(String string) {
//                            Toast.makeText(mContext, "请求失败了。。" + string, Toast.LENGTH_SHORT).show();
//                        }
//                    });

        }
    }
}
