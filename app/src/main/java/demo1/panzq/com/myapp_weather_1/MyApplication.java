package demo1.panzq.com.myapp_weather_1;

import android.app.Application;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import demo1.panzq.com.myapp_weather_1.db.CityDB;
import demo1.panzq.com.myapp_weather_1.log.MLog;
import demo1.panzq.com.myapp_weather_1.log.MToast;

/**
 * Created by panzq on 2017/3/11.
 */

public class MyApplication extends Application {

    private MyTask myTask = null;
    public static final String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + File.separator + "demo1.panzq.com.myapp_weather_1" + File.separator + CityDB.CITY_DB_NAME;
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
        if (myTask != null && !myTask.isCancelled())
        {
            myTask.cancel(true);
        }
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

    //将数据库文件拷贝到本地目录中
    private CityDB mCityDB;

    private void copyDB() {
        File dbFile = new File(DB_PATH);
        if (!dbFile.exists()) {
            MLog.d("db is not exists");
            myTask = new MyTask();
            myTask.execute(DB_PATH);
        }
    }

    private synchronized boolean copyDB(String desPath) {
        File desFile = new File(desPath);
        try {
            InputStream srcIS = getAssets().open(CityDB.CITY_DB_NAME);
            FileOutputStream desFOS = new FileOutputStream(desFile);
            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = srcIS.read(buffer)) != -1) {
                desFOS.write(buffer, 0, len);
                desFOS.flush();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            MToast.showLong(mApplication, e.getMessage());
            System.exit(0);
            return false;
        } finally {
        }
    }

    private class MyTask extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            MLog.d(params[0]);
            boolean copySuccess = copyDB(params[0]);
            return copySuccess;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean)
            {
                MLog.d("copy success ...");
            }
            else{
                MLog.d("copy failed ...");
            }
        }
    }
}
