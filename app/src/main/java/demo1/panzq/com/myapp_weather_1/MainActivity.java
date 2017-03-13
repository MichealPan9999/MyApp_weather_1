package demo1.panzq.com.myapp_weather_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.List;

import demo1.panzq.com.myapp_weather_1.bean.City;
import demo1.panzq.com.myapp_weather_1.db.CityDB;
import demo1.panzq.com.myapp_weather_1.log.MLog;
import demo1.panzq.com.myapp_weather_1.util.Lunar;
import demo1.panzq.com.myapp_weather_1.util.TimeUtil;

public class MainActivity extends Activity implements View.OnClickListener {
    private MyApplication mApplication;
    private Button getLunarCalendar;
    private Button listAllCityInfos;
    private Lunar lunarUtil;
    private Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MLog.d("-----onCreate--------");
        mApplication = MyApplication.getInstance();
        initViews();
        initData();
    }

    private void initViews() {
        getLunarCalendar = (Button) findViewById(R.id.get_lunar_calender);
        getLunarCalendar.setOnClickListener(this);
        listAllCityInfos = (Button) findViewById(R.id.list_cities);
        listAllCityInfos.setOnClickListener(this);
    }

    private void initData() {
        cal =  Lunar.getCalendar();
        lunarUtil = new Lunar(cal);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_lunar_calender:

                MLog.d("今年总共：" + lunarUtil.yearDays(cal.get(Calendar.YEAR)));
                MLog.d("闰 "+lunarUtil.getLunarMonth()+"月");
                MLog.d("属 "+lunarUtil.getZodiac());
                MLog.d(" "+lunarUtil.getHeavenly_Stems());
                MLog.d(" "+lunarUtil.getChineseDay(cal.get(Calendar.DAY_OF_MONTH)));
                MLog.d(" "+ TimeUtil.getZhouWeek());
                MLog.d(" "+ TimeUtil.getDay(System.currentTimeMillis()));
                break;
            case R.id.list_cities:
                CityDB cityDB = new CityDB(mApplication,MyApplication.DB_PATH);
                List<City> allCity = cityDB.getAllCity();
                for(City city:allCity)
                {
                    MLog.d(city.toString());
                }
                break;
        }
    }

}
