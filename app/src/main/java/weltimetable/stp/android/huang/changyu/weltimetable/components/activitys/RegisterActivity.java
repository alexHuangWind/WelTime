package weltimetable.stp.android.huang.changyu.weltimetable.components.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import weltimetable.stp.android.huang.changyu.weltimetable.R;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.bean.RegisterResp;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.http.HttpCallback;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.http.HttpHelper;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.ConstentValue;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.STPHelper;

public class RegisterActivity extends AppCompatActivity {
    Button bt_register;
    private EditText ET_studentId;
    private EditText ET_Email;
    private EditText ET_pwd;
    private EditText ET_pwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        initListener();
    }

    private void initView() {
        bt_register = findViewById(R.id.BT_Register);
        ET_studentId = findViewById(R.id.ET_StduentID);
        ET_Email = findViewById(R.id.ET_Email);
        ET_pwd = findViewById(R.id.ET_PWD);
        ET_pwd2 = findViewById(R.id.ET_PWD2);
    }

    private void initListener() {
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPWD();
                TestRegster();
            }
        });
    }

    private void CheckPWD() {

//        password=editText.getText().toString().trim();//第一次输入的密码赋值给password
//        password2=editText2.getText().toString().trim();//第二次输入的密码赋值给password2
//
//        if (password.equals("")||password2.equals("")){	//判断两次密码是否为空
//            Toast.makeText(getApplicationContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
//        }else if(password.equals(password2)){
//            Toast.makeText(getApplication(),"注册成功",Toast.LENGTH_SHORT).show();
//            //把Editext里面的密码上传到数据库
//            BeanLab beanLab=BeanLab.get(getApplicationContext());
//            beanLab.addValues(phone,password);
//            //注册成功后进入提前写好的登录页面
//            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
//            //intent.putExtra(,);//可以填入用户信息，如ID等
//            startActivity(intent);
//            finish();
//
//
//        }else if (password.equals("") ！= password2.equals("")){
//            Toast.makeText(getApplication(),"密码不一致，请重新输入",Toast.LENGTH_SHORT).show();

    }

    private void TestRegster() {
        final HashMap loginMap = new HashMap<>();
        loginMap.put("email", ET_Email.getText());
        loginMap.put("studentID", ET_studentId.getText());
        String pwd1 =STPHelper.md5(ET_pwd.getText().toString()) ;
        String pwd2 =STPHelper.md5(ET_pwd2.getText().toString()) ;
        if(pwd1!=null&&pwd2!=null&&!pwd1.equals(pwd2)){
            RegisterActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(RegisterActivity.this, "passwords not same...", Toast.LENGTH_SHORT).show();
                }
            });
            return;
        }

        loginMap.put("passWord", STPHelper.md5(ET_pwd2.getText().toString()));
        loginMap.put("majoy", "IT");
        Log.d("alexTimeTable: ", loginMap.get("email").toString());

        //post request
        HttpHelper.obtain().post(ConstentValue.BASIC_URL+"/Reg",
                loginMap, new HttpCallback<RegisterResp>() {
                    @Override
                    public void onSuccess(RegisterResp RegisterBean) {
                        Log.d("alexTimeTable: ", RegisterBean.toString());
                        String res = RegisterBean.getResult();
                        if(res!=null&&!res.equals("200")){
                            onFailed(res);
                            return;
                        }
                        RegisterActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "success.." + RegisterBean.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
//                    email:'Andrew.Liu@it.weltec.ac.nz',
//                    studentID:'1234',
//                    passWord:'5dd7ad06e369442c814aaabd2d1a792b'

                    @Override
                    public void onFailed(String string) {
                        Log.d("alexTimeTable: ", string);
                        RegisterActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "request Fail... code:" + string, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });


    }
}
