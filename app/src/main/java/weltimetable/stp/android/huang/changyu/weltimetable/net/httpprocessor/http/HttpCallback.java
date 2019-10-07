package weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.http;


import com.google.gson.Gson;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

import weltimetable.stp.android.huang.changyu.weltimetable.net.httpprocessor.interfaces.ICallBack;

/**
 * Result 是javabean对象
 */

public abstract class HttpCallback<BasicResp> implements ICallBack {

    @Override
    public void onSuccess(String result) {
        result = result.substring(1,result.length()-1);
        result = result.replace("\\","");
//        result="{\"result\":\"200\"}";
        char [] q = result.toCharArray();
       String result2="[{\"result\":\"200\"}]";
        char [] qp = result2.toCharArray();

//        Log.d("RRRRR",result) ;
        Gson gson = new Gson();
        Class<?> cls = analysisClazzInfo(this);
        try {
            BasicResp objResult = (BasicResp) gson.fromJson(result, cls);
            onSuccess(objResult);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public abstract void onSuccess(BasicResp result);

    public static Class<?> analysisClazzInfo(Object object) {
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) params[0];
    }


}
