package demo1.panzq.com.myapp_weather_1.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import demo1.panzq.com.myapp_weather_1.bean.City;

/**
 * Created by panzq on 2017/3/13.
 */

public class CityDB {
    public static final String CITY_DB_NAME = "city.db";
    private static final String CITY_TABLE_NAME = "city";
    private SQLiteDatabase db;

    public CityDB(Context context, String path) {
        db = context.openOrCreateDatabase(path, Context.MODE_PRIVATE, null);
    }

    public boolean isOpen() {
        return db != null && db.isOpen();
    }

    public List<City> getAllCity() {
        String province;
        String city;
        String number;
        String allPY;
        String allFirstPY;
        City item;
        List<City> list = new ArrayList<City>();
        Cursor c = db.rawQuery("SELECT * from " + CITY_TABLE_NAME, null);
        while (c.moveToNext()) {
            province = c.getString(c.getColumnIndex("province"));
            city = c.getString(c.getColumnIndex("name"));
            number = c.getString(c.getColumnIndex("number"));
            allPY = c.getString(c.getColumnIndex("pinyin"));
            allFirstPY = c.getString(c.getColumnIndex("py"));
            item = new City(province, city, number, allPY, allFirstPY);
            list.add(item);
        }
        Collections.sort(list, new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return (int)getFirstName(o1.getPy())-(int)getFirstName(o2.getPy());
            }
        });
        return list;
    }
    private char getFirstName(String name)
    {
        String firstName = name.substring(0,1).toUpperCase();
        return firstName.charAt(0);
    }

    /**
     * 去掉市或县搜索
     *
     * @param city
     * @return
     */
    private String parseName(String city) {
        city = city.replaceAll("市$", "").replaceAll("县$", "").replaceAll("区$", "");
        return city;
    }

    private City getCityInfo(String city) {
        City item = null;
        Cursor c = db.rawQuery("SELECT * from " + CITY_TABLE_NAME + "where name=?", new String[]{city});
        if (c.moveToFirst()) {
            String province = c.getString(c.getColumnIndex("province"));
            String name = c.getString(c.getColumnIndex("name"));
            String number = c.getString(c.getColumnIndex("number"));
            String allPY = c.getString(c.getColumnIndex("pinyin"));
            String allFirstPY = c.getString(c.getColumnIndex("py"));
            item = new City(province, name, number, allPY, allFirstPY);
        }
        return item;
    }

    public City getCity(String city) {
        if (TextUtils.isEmpty(city))
            return null;
        City item = getCityInfo(city);//先全部搜索
        if (item == null)
            item = getCityInfo(parseName(city));
        return item;
    }
}
