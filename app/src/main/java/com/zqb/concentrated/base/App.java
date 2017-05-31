package com.zqb.concentrated.base;

import android.app.Application;

import com.blankj.utilcode.utils.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.common.html.HtmlEscapers;
import com.jude.utils.JUtils;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.zqb.concentrated.R;
import com.zqb.concentrated.weather.bean.DaoMaster;
import com.zqb.concentrated.weather.bean.DaoSession;

import org.greenrobot.greendao.database.Database;

import cn.sharesdk.framework.ShareSDK;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by admin on 2016/9/16.
 */
public class App extends Application {

    private static final boolean ENCRYPTED = false;

    private static App app;
    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Logger.init().logLevel(LogLevel.FULL);
        Fresco.initialize(this);
        JUtils.initialize(this);
        Utils.init(this);
        ShareSDK.initSDK(this, "1aef6772c61d6");

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/huawenxinkai.TTF")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ?
                "weather-db-encrypted" : "weather-db");
        Database db = ENCRYPTED ? helper.getEncryptedReadableDb("super-secret") : helper.getReadableDb();
        mDaoSession = new DaoMaster(db).newSession();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    public static App getContext() {
        return app;
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }
}
