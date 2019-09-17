package weltimetable.stp.android.huang.changyu.weltimetable.components.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

import weltimetable.stp.android.huang.changyu.weltimetable.R;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.bean.RegisterResp;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.http.HttpCallback;
import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.http.HttpHelper;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.ConstentValue;

public class RegisterActivity extends AppCompatActivity {
    Button bt_register;

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
        loginMap.put("email", "Robert@it.weltec.ac.nz");
        loginMap.put("studentID", "123123");
        loginMap.put("passWord", "slkdjflskdjflksjdf");
        loginMap.put("majoy", "IT");
        Log.d("alexTimeTable: ", loginMap.get("email").toString());

        //post request
        HttpHelper.obtain().post(ConstentValue.BASIC_URL+"/Reg",
                loginMap, new HttpCallback<RegisterResp>() {
                    @Override
                    public void onSuccess(RegisterResp loginBean) {
                        Log.d("alexTimeTable: ", loginBean.toString());
                        RegisterActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "success。。" + loginBean.toString(), Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(RegisterActivity.this, "请求失败了。。" + string, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });


    }
}
