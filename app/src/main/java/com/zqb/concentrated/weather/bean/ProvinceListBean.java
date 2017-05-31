package com.zqb.concentrated.weather.bean;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by zzqqbb on 2016/11/16.
 * <?xml version="1.0" encoding="utf-8"?>
 * <china dn="day">
 * <city quName="黑龙江" pyName="heilongjiang" cityname="哈尔滨" state1="6" state2="6" stateDetailed="雨夹雪" tem1="0" tem2="-8" windState="南风4-5级转小于3级"/>
 * <city quName="吉林" pyName="jilin" cityname="长春" state1="1" state2="6" stateDetailed="多云转雨夹雪" tem1="6" tem2="-2" windState="南风3-4级转西南风5-6级"/>
 * <city quName="辽宁" pyName="liaoning" cityname="沈阳" state1="1" state2="1" stateDetailed="多云" tem1="13" tem2="2" windState="西南风4-5级转3-4级"/>
 * </china>
 */

@Root(name = " ", strict = false)
public class ProvinceListBean {

    @ElementList(required = true, inline = true, entry = "city")
    private List<AreaItem> items;

    public List<AreaItem> getItems() {
        return items;
    }

    public void setItems(List<AreaItem> items) {
        this.items = items;
    }

    @Root(name = "city", strict = false)
    public static class AreaItem {
        //<city quName="黑龙江" pyName="heilongjiang" cityname="哈尔滨"
        // state1="6" state2="6" stateDetailed="雨夹雪" tem1="0" tem2="-8"
        // windState="南风4-5级转小于3级"/>
        @Attribute
        private String quName;
        @Attribute
        private String pyName;
        @Attribute
        private String cityname;
//        @Attribute
//        private String state1;
//        @Attribute
//        private String state2;
//        @Attribute
//        private String stateDetailed;
//        @Attribute
//        private String tem1;
//        @Attribute
//        private String tem2;
//        @Attribute
//        private String windState;


/*        <city cityX="137.7" cityY="385.95" cityname="湛江" centername="湛江" fontColor="FFFFFF"
pyName="zhanjiang" state1="1" state2="1" stateDetailed="多云" tem1="26" tem2="17"
temNow="20" windState="微风" windDir="东北风" windPower="3级" humidity="87%" time="08:55"
url="101281001"/>
*/

        public String getQuName() {
            return quName;
        }

        public void setQuName(String quName) {
            this.quName = quName;
        }

        public String getPyName() {
            return pyName;
        }

        public void setPyName(String pyName) {
            this.pyName = pyName;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        @Override
        public String toString() {
            return "AreaItem{" +
                    "quName='" + quName + '\'' +
                    ", pyName='" + pyName + '\'' +
                    ", cityname='" + cityname + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ProvinceListBean{" +
                "items=" + items +
                '}';
    }
}
