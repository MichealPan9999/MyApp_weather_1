package demo1.panzq.com.myapp_weather_1;

import android.app.Application;
import android.content.res.Configuration;

import demo1.panzq.com.myapp_weather_1.log.MLog;

/**
 * Created by panzq on 2017/3/11.
 *
 */

public class MyApplication extends Application {

    public static synchronized MyApplication getInstance() {
        return mApplication;
    }
    static MyApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        // 程序创建的时候执行
        MLog.d("--MyApplication---onCreate");
        mApplication = this;
        copyDB();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //// 程序终止的时候执行
        MLog.d("--MyApplication---onTerminate");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // 低内存的时候执行
        MLog.d("--MyApplication---onLowMemory");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        // 程序在内存清理的时候执行
        MLog.d("--MyApplication---onTrimMemory");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        MLog.d("--MyApplication---onConfigurationChanged");
    }
    //将数据库文件拷贝到本地
    private void copyDB()
    {

    }
}
