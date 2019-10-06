package weltimetable.stp.android.huang.changyu.weltimetable.components.ui.login;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import weltimetable.stp.android.huang.changyu.weltimetable.components.activitys.RegisterActivity;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.bean.LoginBeanReq;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.bean.LoginBeanResp;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.http.HttpCallback;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.http.HttpHelper;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.processor.OkHttpProcessor;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.ConstentValue;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.STPHelper;
import weltimetable.stp.android.huang.changyu.weltimetable.R;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.SharedPrefsUtils;


public class LoginActivity extends AppCompatActivity {

    private weltimetable.stp.android.huang.changyu.weltimetable.components.ui.login.LoginViewModel loginViewModel;
    private EditText ET_UserName;
    private EditText ET_PWD;
    private TextView TV_ForgotPassword;
    private TextView TV_Register;
    private Button BT_Login;
    private ProgressBar PB_Loading;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new weltimetable.stp.android.huang.changyu.weltimetable.components.ui.login.LoginViewModelFactory())
                .get(weltimetable.stp.android.huang.changyu.weltimetable.components.ui.login.LoginViewModel.class);
        init();
        setOnclickLisner();
    }

    private void init() {

        ET_UserName = findViewById(R.id.ET_Email);
        ET_PWD = findViewById(R.id.ET_PWD);
        BT_Login = findViewById(R.id.BT_Login);
        PB_Loading = findViewById(R.id.loading);
        TV_ForgotPassword = findViewById(R.id.TV_ForgotPassword);
        TV_Register = findViewById(R.id.TV_Register);

    }

    private void setOnclickLisner() {
        loginViewModel.getLoginFormState().observe(this, new Observer<weltimetable.stp.android.huang.changyu.weltimetable.components.ui.login.LoginFormState>() {
            @Override
            public void onChanged(@Nullable weltimetable.stp.android.huang.changyu.weltimetable.components.ui.login.LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                BT_Login.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    ET_UserName.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    ET_PWD.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<weltimetable.stp.android.huang.changyu.weltimetable.components.ui.login.LoginResult>() {
            @Override
            public void onChanged(@Nullable weltimetable.stp.android.huang.changyu.weltimetable.components.ui.login.LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                PB_Loading.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(ET_UserName.getText().toString(),
                        ET_PWD.getText().toString());
            }
        };
        ET_UserName.addTextChangedListener(afterTextChangedListener);
        ET_PWD.addTextChangedListener(afterTextChangedListener);
        ET_PWD.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(ET_UserName.getText().toString(),
                            ET_PWD.getText().toString());
                }
                return false;
            }
        });

        BT_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PB_Loading.setVisibility(View.VISIBLE);
                loginViewModel.login(ET_UserName.getText().toString(),
                        ET_PWD.getText().toString());
                TestLogin(new LoginBeanReq(ET_UserName.getText().toString() , ET_PWD.getText().toString()));
//                TestLogin(new LoginBeanReq( , ET_PWD.getText().toString()));
            }
        });
        TV_ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                STPHelper.toast(LoginActivity.this, "ForgotPWD");
            }
        });
        TV_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                STPHelper.startActivity(LoginActivity.this, RegisterActivity.class);
                STPHelper.toast(LoginActivity.this, "Register");
            }
        });
    }


    private void updateUiWithUser(weltimetable.stp.android.huang.changyu.weltimetable.components.ui.login.LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    private void TestLogin(LoginBeanReq loginBeanReq) {
        final HashMap loginMap = new HashMap<>();
        loginMap.put("email", loginBeanReq.userName);
        loginMap.put("studentID", loginBeanReq.studentID);
        loginMap.put("passWord", loginBeanReq.Password);
        Log.d("alexTimeTable: ", loginMap.get("email").toString());

        //Login
        HttpHelper.obtain().post(ConstentValue.BASIC_URL + "/Login",
                loginMap, new HttpCallback<LoginBeanResp>() {
                    @Override
                    public void onSuccess(LoginBeanResp loginBean) {
                        Log.d("alexTimeTable: ", loginBean.toString());
                        LoginActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(LoginActivity.this, "success。。" + loginBean.toString(), Toast.LENGTH_SHORT).show();
                                SharedPrefsUtils.setStringPreference(LoginActivity.this,SharedPrefsUtils.LOGIN,loginBean.toString());
                                SharedPrefsUtils.setBooleanPreference(LoginActivity.this,SharedPrefsUtils.LOGIN,true);
                            }
                        });
                    }
//                    email:'Andrew.Liu@it.weltec.ac.nz',
//                    studentID:'1234',
//                    passWord:'5dd7ad06e369442c814aaabd2d1a792b'

                    @Override
                    public void onFailed(String string) {
                        Log.d("alexTimeTable: ", string);
                        LoginActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(LoginActivity.this, "请求失败了。。" + string, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

    }


}