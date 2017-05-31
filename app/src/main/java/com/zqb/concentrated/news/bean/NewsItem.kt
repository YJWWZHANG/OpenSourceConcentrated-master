package com.zqb.concentrated.news.bean

import java.io.Serializable

/**
 * Created by admin on 2016/9/18.
 */
class NewsItem : Serializable {


    /**
     * postid : PHOT233A4000100A
     * hasCover : false
     * hasHead : 1
     * replyCount : 797
     * hasImg : 1
     * digest :
     * hasIcon : false
     * docid : 9IG74V5H00963VRO_C1AKCJTTbjlishidaiupdateDoc
     * title : 河水水位下降 居民开垦河道变“菜园”
     * order : 1
     * priority : 345
     * lmodify : 2016-09-19 10:21:02
     * boardid : photoview_bbs
     * ads : [{"docid":"C1AKRMN805169QC9","title":"\u201c你一个小孩得什么抑郁症，矫情！\u201d","tag":"doc","imgsrc":"http://cms-bucket.nosdn.127.net/fce94f17d9fc4eb8bc89ef8cb93ced3220160919095009.jpeg","subtitle":"","url":"C1AKRMN805169QC9"},{"title":"三峡升船机试航 游轮\"乘电梯\"翻越大坝","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/23ca001962604efe8c2942fcb160280120160919072831.jpeg","subtitle":"","url":"00AP0001|2198803"},{"title":"江苏一民房因液化气泄漏爆炸 多人被埋","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/f7820e9a05644fea977d6b8c4bb05e8120160919091726.jpeg","subtitle":"","url":"00AP0001|2198856"},{"title":"叙利亚建儿童乐园 用火箭弹残骸做秋千","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/5101fb1e456a4636910fb661d2d6658d20160919084633.jpeg","subtitle":"","url":"00AO0001|2198841"},{"title":"受台风影响 上海黄浦江水位沿线超警戒","tag":"photoset","imgsrc":"http://cms-bucket.nosdn.127.net/123bf85989d54dd985d7359ab001f0c720160919073815.jpeg","subtitle":"","url":"00AP0001|2198806"}]
     * photosetID : 00AP0001|2198852
     * template : normal1
     * votecount : 730
     * skipID : 00AP0001|2198852
     * alias : Top News
     * skipType : photoset
     * cid : C1348646712614
     * hasAD : 1
     * imgextra : [{"imgsrc":"http://cms-bucket.nosdn.127.net/16badf1a6d5247959758f89068cb7ed320160919091259.jpeg"},{"imgsrc":"http://cms-bucket.nosdn.127.net/41e32379d8ba410186ccd1ecc427070120160919091300.jpeg"}]
     * source : 网易原创
     * ename : androidnews
     * imgsrc : http://cms-bucket.nosdn.127.net/3d797e6b6fc94e5cbea57f91f780e5ec20160919092648.jpeg
     * tname : 头条
     * ptime : 2016-09-19 09:12:11
     */

    var postid: String? = null
    var isHasCover: Boolean = false
    var hasHead: Int = 0
    var replyCount: Int = 0
    var hasImg: Int = 0
    var digest: String? = null
    var isHasIcon: Boolean = false
    var docid: String? = null
    var title: String? = null
    var order: Int = 0
    var priority: Int = 0
    var lmodify: String? = null
    var boardid: String? = null
    var photosetID: String? = null
    var template: String? = null
    var votecount: Int = 0
    var skipID: String? = null
    var alias: String? = null
    var skipType: String? = null
    var cid: String? = null
    var hasAD: Int = 0
    var source: String? = null
    var ename: String? = null
    var imgsrc: String? = null
    var tname: String? = null
    var ptime: String? = null
    /**
     * docid : C1AKRMN805169QC9
     * title : “你一个小孩得什么抑郁症，矫情！”
     * tag : doc
     * imgsrc : http://cms-bucket.nosdn.127.net/fce94f17d9fc4eb8bc89ef8cb93ced3220160919095009.jpeg
     * subtitle :
     * url : C1AKRMN805169QC9
     */

    var ads: List<AdsBean>? = null
    /**
     * imgsrc : http://cms-bucket.nosdn.127.net/16badf1a6d5247959758f89068cb7ed320160919091259.jpeg
     */

    var imgextra: List<ImgextraBean>? = null
    /**
     * url_3w : http://news.163.com/16/0919/09/C1ALT5AQ0001124J.html
     * ltitle : 8月64个大中城市房价环比上涨 郑州领涨
     * url : http://3g.163.com/news/16/0919/09/C1ALT5AQ0001124J.html
     * subtitle :
     */

    var url_3w: String? = null
    var ltitle: String? = null
    var url: String? = null
    var subtitle: String? = null

    class AdsBean : Serializable {
        var docid: String? = null
        var title: String? = null
        var tag: String? = null
        var imgsrc: String? = null
        var subtitle: String? = null
        var url: String? = null
    }

    class ImgextraBean : Serializable {
        var imgsrc: String? = null
    }
}
