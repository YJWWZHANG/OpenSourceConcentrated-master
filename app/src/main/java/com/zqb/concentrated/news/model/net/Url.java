package com.zqb.concentrated.news.model.net;

import com.zqb.concentrated.news.bean.NewsChannel;

import java.util.ArrayList;

/**
 * Created by admin on 2016/9/21.
 */
public class Url {

    public static final ArrayList<NewsChannel> newsChannels;
    static {
        newsChannels = new ArrayList<NewsChannel>();
        newsChannels.add(new NewsChannel("头条", "list", Url.TopId, 0));
        newsChannels.add(new NewsChannel("娱乐", "list", Url.YuLeId, 0));
        newsChannels.add(new NewsChannel("电影", "list", Url.DianYingId, 0));
        newsChannels.add(new NewsChannel("体育", "list", Url.TiYuId, 0));
        newsChannels.add(new NewsChannel("科技", "list", Url.KeJiId, 0));
        newsChannels.add(new NewsChannel("军事", "list", Url.JunShiId, 0));
        newsChannels.add(new NewsChannel("笑话", "list", Url.XiaoHuaId, 0));
        newsChannels.add(new NewsChannel("财经", "list", Url.CaiJingId, 0));
        newsChannels.add(new NewsChannel("汽车", "list", Url.QiChiId, 0));
        newsChannels.add(new NewsChannel("游戏", "list", Url.YouXiId, 0));
        newsChannels.add(new NewsChannel("情感", "list", Url.QingGanId, 0));
//        newsChannels.addProvince(new NewsChannel("精选", "list", Url.JingXuanId, 0));
//        newsChannels.addProvince(new NewsChannel("电台", "list", Url.DianTaiId, 0));
//        newsChannels.addProvince(new NewsChannel("数码", "list", Url.ShuMaId, 0));
//        newsChannels.addProvince(new NewsChannel("时尚", "list", Url.ShiShangId, 0));
//        newsChannels.addProvince(new NewsChannel("移动", "list", Url.YiDongId, 0));
//        newsChannels.addProvince(new NewsChannel("彩票", "list", Url.CaiPiaoId, 0));
//        newsChannels.addProvince(new NewsChannel("教育", "list", Url.JiaoYuId, 0));
//        newsChannels.addProvince(new NewsChannel("论坛", "list", Url.LunTanId, 0));
//        newsChannels.addProvince(new NewsChannel("旅游", "list", Url.LvYouId, 0));
//        newsChannels.addProvince(new NewsChannel("手机", "list", Url.ShouJiId, 0));
//        newsChannels.addProvince(new NewsChannel("博客", "list", Url.BoKeId, 0));
//        newsChannels.addProvince(new NewsChannel("社会", "list", Url.SheHuiId, 0));
//        newsChannels.addProvince(new NewsChannel("家居", "list", Url.JiaJuId, 0));
//        newsChannels.addProvince(new NewsChannel("暴雪", "list", Url.BaoXueId, 0));
//        newsChannels.addProvince(new NewsChannel("亲子", "list", Url.QinZiId, 0));
//        newsChannels.addProvince(new NewsChannel("NBA", "list", Url.NBAId, 0));
//        newsChannels.addProvince(new NewsChannel("CBA", "list", Url.CBAId, 0));
//        newsChannels.addProvince(new NewsChannel("足球", "list", Url.FootId, 0));
//        newsChannels.addProvince(new NewsChannel("消息", "list", Url.MsgId, 0));
//        newsChannels.addProvince(new NewsChannel("北京", "local", Url.BeiJingId, 0));
//        newsChannels.addProvince(new NewsChannel("房产", "house", Url.FangChanId, 0));
    }

    public static final String host = "http://c.m.163.com/";
    public static final String endUrl = "-20.html";
    public static final String endDetailUrl = "/full.html";

    // 头条 http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
    public static final String TopUrl = host + "nc/article/headline/";
    public static final String TopId = "T1348647909107";
    // 新闻详情
    public static final String NewDetail = host + "nc/article/";

    // 足球 http://c.m.163.com/nc/article/list/T1399700447917/0-20.html
    public static final String CommonUrl = host + "nc/article/list/";
    public static final String FootId = "T1399700447917";
    // 娱乐
    public static final String YuLeId = "T1348648517839";
    // 体育
    public static final String TiYuId = "T1348649079062";
    // 财经
    public static final String CaiJingId = "T1348648756099";
    // 科技
    public static final String KeJiId = "T1348649580692";
    // 电影
    public static final String DianYingId = "T1348648650048";
    // 汽车
    public static final String QiChiId = "T1348654060988";
    // 笑话
    public static final String XiaoHuaId = "T1350383429665";
    // 笑话
    public static final String YouXiId = "T1348654151579";
    // 时尚
    public static final String ShiShangId = "T1348650593803";
    // 情感
    public static final String QingGanId = "T1348650839000";
    // 精选
    public static final String JingXuanId = "T1370583240249";
    // 电台
    public static final String DianTaiId = "T1379038288239";
    // nba
    public static final String NBAId = "T1348649145984";
    // 数码
    public static final String ShuMaId = "T1348649776727";
    // 数码
    public static final String YiDongId = "T1351233117091";
    // 彩票
    public static final String CaiPiaoId = "T1356600029035";
    // 教育
    public static final String JiaoYuId = "T1348654225495";
    // 论坛
    public static final String LunTanId = "T1349837670307";
    // 旅游
    public static final String LvYouId = "T1348654204705";
    // 手机
    public static final String ShouJiId = "T1348649654285";
    // 博客
    public static final String BoKeId = "T1349837698345";
    // 社会
    public static final String SheHuiId = "T1348648037603";
    // 家居
    public static final String JiaJuId = "T1348654105308";
    // 暴雪游戏
    public static final String BaoXueId = "T1397016069906";
    // 亲子
    public static final String QinZiId = "T1397116135282";
    // CBA
    public static final String CBAId = "T1348649475931";
    // 消息
    public static final String MsgId = "T1371543208049";

    // 北京 http://c.m.163.com/nc/article/local/5YyX5Lqs/0-20.html
    public static final String Local = host + "nc/article/local/";

    public static final String BeiJingId = "5YyX5Lqs";

    // 军事
    public static final String JunShiId = "T1348648141035";

    // 房产 http://c.m.163.com/nc/article/house/5YyX5Lqs/0-20.html
    public static final String FangChan = host + "nc/article/house/";
    // 房产id
    public static final String FangChanId = "5YyX5Lqs";

    // 图集
    public static final String TuJi = host + "photo/api/morelist/0096/4GJ60096/";// 42358.json
    // 图集end
    public static final String TuJiEnd = ".json";
    // 热点42577
    public static final String TuPianReDian = host + "photo/api/morelist/0096/54GI0096/";
    // 独家42010
    public static final String TuPianDuJia = host + "photo/api/morelist/0096/54GJ0096/";
    // 明星 42599.json
    public static final String TuPianMingXing = host + "photo/api/morelist/0096/54GK0096/";
    // 明星 42262.json
    public static final String TuPianTiTan = host + "photo/api/morelist/0096/54GM0096/";
    // 美图 39683.json
    public static final String TuPianMeiTu = host + "photo/api/morelist/0096/54GN0096/";

    // 视频 http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/10-10.html
    public static final String Video = host + "nc/video/list/";
    public static final String VideoCenter = "/n/";
    public static final String videoEndUrl = "-10.html";
    // 热点视频
    public static final String VideoReDianId = "V9LG4B3A0";
    // 娱乐视频
    public static final String VideoYuLeId = "V9LG4CHOR";
    // 搞笑视频
    public static final String VideoGaoXiaoId = "V9LG4E6VR";
    // 精品视频
    public static final String VideoJingPinId = "00850FRB";

    // 天气预报url
    public static final String WeatherHost = "http://wthrcdn.etouch.cn/weather_mini?city=";
    // http://v.juhe.cn/weather/index?cityname=
    //

    public static final String WeatherKey = "&key=1734f933d24634331a24aaadc1cb088f";

    /**
     * 新浪图片新闻
     */
    // 精选列表
    public static final String JINGXUAN_ID = "http://api.sina.cn/sinago/list.json?channel=hdpic_toutiao&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=&imei=867064013906290&uid=802909da86d9f5fc&p=";
    // 图片详情
    public static final String JINGXUANDETAIL_ID = "http://api.sina.cn/sinago/article.json?postt=hdpic_hdpic_toutiao_4&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&id=";
    // 趣图列表
    public static final String QUTU_ID = "http://api.sina.cn/sinago/list.json?channel=hdpic_funny&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p=";
    // 美图列表
    public static final String MEITU_ID = "http://api.sina.cn/sinago/list.json?channel=hdpic_pretty&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p=";
    // 故事列表
    public static final String GUSHI_ID = "http://api.sina.cn/sinago/list.json?channel=hdpic_story&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p=";


}
