package weltimetable.a2019.program3.huang.changyu.weltimetable.net.httpprocessor.http;

import com.google.gson.Gson;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import weltimetable.a2019.program3.huang.changyu.weltimetable.net.httpprocessor.interfaces.ICallBack;

/**
 * Result 是javabean对象
 */

public abstract class  HttpCallback<Result> implements ICallBack {

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Class<?> cls = analysisClazzInfo(this);

        Result objResult = (Result)gson.fromJson(result,cls);
        onSuccess(objResult);

    }

    public abstract void onSuccess(Result result);

    public static Class<?> analysisClazzInfo(Object object){
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
        return (Class<?>) params[0];
    }


}
