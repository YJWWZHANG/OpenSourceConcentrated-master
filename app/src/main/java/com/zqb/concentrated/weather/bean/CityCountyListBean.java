package com.zqb.concentrated.weather.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

import com.zqb.concentrated.BR;
/**
 * Created by zqb on 2017/1/4.
 */

@Root(name = " ", strict = false)
public class CityCountyListBean {
    @ElementList(required = true, inline = true, entry = "city")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Root(name = "city", strict = false)
    public static class Item extends BaseObservable{
/*<city cityX="333" cityY="158" cityname="从化市" centername="从化市" fontColor="FFFFFF"
pyName="" state1="0" state2="1" stateDetailed="晴转多云" tem1="26" tem2="16" temNow="24"
windState="微风" windDir="西风" windPower="1级" humidity="68%" time="15:00" url="101280103"/>*/
        @Attribute
        private String cityX;
        @Attribute
        private String cityY;
        @Attribute
        private String cityname;
        @Attribute
        private String centername;
        @Attribute
        private String fontColor;
        @Attribute
        private String pyName;
        @Attribute
        private String state1;
        @Attribute
        private String state2;
        @Attribute
        private String stateDetailed;
        @Attribute
        private String tem1;
        @Attribute
        private String tem2;
        @Attribute
        private String temNow;
        @Attribute
        private String windState;
        @Attribute
        private String windDir;
        @Attribute
        private String windPower;
        @Attribute
        private String humidity;
        @Attribute
        private String time;
        @Attribute
        private String url;

        public String getCityX() {
            return cityX;
        }

        public void setCityX(String cityX) {
            this.cityX = cityX;
        }

        public String getCityY() {
            return cityY;
        }

        public void setCityY(String cityY) {
            this.cityY = cityY;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getCentername() {
            return centername;
        }

        public void setCentername(String centername) {
            this.centername = centername;
        }

        public String getFontColor() {
            return fontColor;
        }

        public void setFontColor(String fontColor) {
            this.fontColor = fontColor;
        }

        public String getPyName() {
            return pyName;
        }

        public void setPyName(String pyName) {
            this.pyName = pyName;
        }

        @Bindable
        public String getState1() {
            return state1;
        }

        public void setState1(String state1) {
            this.state1 = state1;
            notifyPropertyChanged(BR.state1);
        }

        public String getState2() {
            return state2;
        }

        public void setState2(String state2) {
            this.state2 = state2;
        }

        @Bindable
        public String getStateDetailed() {
            return stateDetailed;
        }

        public void setStateDetailed(String stateDetailed) {
            this.stateDetailed = stateDetailed;
            notifyPropertyChanged(BR.stateDetailed);
        }

        @Bindable
        public String getTem1() {
            return tem1;
        }

        public void setTem1(String tem1) {
            this.tem1 = tem1;
            notifyPropertyChanged(BR.tem1);
        }

        @Bindable
        public String getTem2() {
            return tem2;
        }

        public void setTem2(String tem2) {
            this.tem2 = tem2;
            notifyPropertyChanged(BR.tem2);
        }

        @Bindable
        public String getTemNow() {
            return temNow;
        }

        public void setTemNow(String temNow) {
            this.temNow = temNow;
            notifyPropertyChanged(BR.temNow);
        }

        @Bindable
        public String getWindState() {
            return windState;
        }

        public void setWindState(String windState) {
            this.windState = windState;
            notifyPropertyChanged(BR.windState);
        }

        @Bindable
        public String getWindDir() {
            return windDir;
        }

        public void setWindDir(String windDir) {
            this.windDir = windDir;
            notifyPropertyChanged(BR.windDir);
        }

        @Bindable
        public String getWindPower() {
            return windPower;
        }

        public void setWindPower(String windPower) {
            this.windPower = windPower;
            notifyPropertyChanged(BR.windPower);
        }

        @Bindable
        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
            notifyPropertyChanged(BR.humidity);
        }

        @Bindable
        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
            notifyPropertyChanged(BR.time);
        }

        @Bindable
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
            notifyPropertyChanged(BR.url);
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cityX='" + cityX + '\'' +
                    ", cityY='" + cityY + '\'' +
                    ", cityname='" + cityname + '\'' +
                    ", centername='" + centername + '\'' +
                    ", fontColor='" + fontColor + '\'' +
                    ", pyName='" + pyName + '\'' +
                    ", state1='" + state1 + '\'' +
                    ", state2='" + state2 + '\'' +
                    ", stateDetailed='" + stateDetailed + '\'' +
                    ", tem1='" + tem1 + '\'' +
                    ", tem2='" + tem2 + '\'' +
                    ", temNow='" + temNow + '\'' +
                    ", windState='" + windState + '\'' +
                    ", windDir='" + windDir + '\'' +
                    ", windPower='" + windPower + '\'' +
                    ", humidity='" + humidity + '\'' +
                    ", time='" + time + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

}
