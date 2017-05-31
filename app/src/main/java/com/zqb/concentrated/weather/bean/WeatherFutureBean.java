package com.zqb.concentrated.weather.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import java.util.List;


/**
 * Created by zqb on 2017/1/18.
 */

public class WeatherFutureBean{

    /**
     * desc : OK
     * status : 1000
     * data : {"wendu":"0","ganmao":"虽然温度适宜但风力较大，仍较易发生感冒，体质较弱的朋友请注意适当防护。","forecast":[{"fengxiang":"东北风","fengli":"微风级","high":"高温 2℃","type":"多云","low":"低温 -5℃","date":"18日星期三"},{"fengxiang":"无持续风向","fengli":"5-6级","high":"高温 1℃","type":"晴","low":"低温 -8℃","date":"19日星期四"},{"fengxiang":"无持续风向","fengli":"微风级","high":"高温 0℃","type":"晴","low":"低温 -8℃","date":"20日星期五"},{"fengxiang":"西北风","fengli":"3-4级","high":"高温 1℃","type":"晴","low":"低温 -8℃","date":"21日星期六"},{"fengxiang":"南风","fengli":"微风级","high":"高温 -1℃","type":"晴","low":"低温 -9℃","date":"22日星期天"}],"yesterday":{"fl":"微风","fx":"北风","high":"高温 3℃","type":"霾","low":"低温 -5℃","date":"17日星期二"},"aqi":"60","city":"北京"}
     */

    private String desc;
    private int status;
    private DataBean data;

    @Override
    public String toString() {
        return "WeatherFutureBean{" +
                "desc='" + desc + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * wendu : 0
         * ganmao : 虽然温度适宜但风力较大，仍较易发生感冒，体质较弱的朋友请注意适当防护。
         * forecast : [{"fengxiang":"东北风","fengli":"微风级","high":"高温 2℃","type":"多云","low":"低温 -5℃","date":"18日星期三"},{"fengxiang":"无持续风向","fengli":"5-6级","high":"高温 1℃","type":"晴","low":"低温 -8℃","date":"19日星期四"},{"fengxiang":"无持续风向","fengli":"微风级","high":"高温 0℃","type":"晴","low":"低温 -8℃","date":"20日星期五"},{"fengxiang":"西北风","fengli":"3-4级","high":"高温 1℃","type":"晴","low":"低温 -8℃","date":"21日星期六"},{"fengxiang":"南风","fengli":"微风级","high":"高温 -1℃","type":"晴","low":"低温 -9℃","date":"22日星期天"}]
         * yesterday : {"fl":"微风","fx":"北风","high":"高温 3℃","type":"霾","low":"低温 -5℃","date":"17日星期二"}
         * aqi : 60
         * city : 北京
         */

        private String wendu;
        private String ganmao;
        private YesterdayBean yesterday;
        private String aqi;
        private String city;
        private List<ForecastBean> forecast;

        @Override
        public String toString() {
            return "DataBean{" +
                    "wendu='" + wendu + '\'' +
                    ", ganmao='" + ganmao + '\'' +
                    ", yesterday=" + yesterday +
                    ", aqi='" + aqi + '\'' +
                    ", city='" + city + '\'' +
                    ", forecast=" + forecast +
                    '}';
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public YesterdayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday) {
            this.yesterday = yesterday;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayBean extends BaseObservable{
            /**
             * fl : 微风
             * fx : 北风
             * high : 高温 3℃
             * type : 霾
             * low : 低温 -5℃
             * date : 17日星期二
             */

            private String fl;
            private String fx;
            private String high;
            private String type;
            private String low;
            private String date;

            @Override
            public String toString() {
                return "YesterdayBean{" +
                        "fl='" + fl + '\'' +
                        ", fx='" + fx + '\'' +
                        ", high='" + high + '\'' +
                        ", type='" + type + '\'' +
                        ", low='" + low + '\'' +
                        ", date='" + date + '\'' +
                        '}';
            }

            @Bindable
            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
                notifyPropertyChanged(BR.fl);
            }

            @Bindable
            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
                notifyPropertyChanged(BR.fx);
            }

            @Bindable
            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
                notifyPropertyChanged(BR.high);
            }

            @Bindable
            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
                notifyPropertyChanged(BR.type);
            }

            @Bindable
            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
                notifyPropertyChanged(BR.low);
            }

            @Bindable
            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
                notifyPropertyChanged(BR.date);
            }
        }

        public static class ForecastBean {
            /**
             * fengxiang : 东北风
             * fengli : 微风级
             * high : 高温 2℃
             * type : 多云
             * low : 低温 -5℃
             * date : 18日星期三
             */

            private String fengxiang;
            private String fengli;
            private String high;
            private String type;
            private String low;
            private String date;

            @Override
            public String toString() {
                return "ForecastBean{" +
                        "fengxiang='" + fengxiang + '\'' +
                        ", fengli='" + fengli + '\'' +
                        ", high='" + high + '\'' +
                        ", type='" + type + '\'' +
                        ", low='" + low + '\'' +
                        ", date='" + date + '\'' +
                        '}';
            }

            public String getFengxiang() {
                return fengxiang;
            }

            public void setFengxiang(String fengxiang) {
                this.fengxiang = fengxiang;
            }

            public String getFengli() {
                return fengli;
            }

            public void setFengli(String fengli) {
                this.fengli = fengli;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
