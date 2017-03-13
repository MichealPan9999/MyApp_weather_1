package demo1.panzq.com.myapp_weather_1.bean;

import java.io.Serializable;

/**
 * Created by panzq on 2017/3/13.
 */

public class City implements Serializable {
    private static final long servialVersionUID = 1l;
    private String province;
    private String name;
    private String number;
    private String pinyin;
    private String py;

    public City() {
    }

    public City(String province, String name, String number, String pinyin, String py) {
        this.province = province;
        this.name = name;
        this.number = number;
        this.pinyin = pinyin;
        this.py = py;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    @Override
    public String toString() {
        return "City{" +
                "province='" + province + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", py='" + py + '\'' +
                '}';
    }
}
