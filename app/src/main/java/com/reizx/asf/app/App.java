package com.reizx.asf.app;

import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.elvishew.xlog.LogLevel;
import com.reizx.asf.di.component.AppComponent;
import com.reizx.asf.di.component.DaggerAppComponent;
import com.reizx.asf.di.module.AppModule;
import com.reizx.asf.di.module.HttpModule;

import static com.reizx.asf.util.AsfLog.initLog;
import org.qiyi.video.svg.Andromeda;

/**
 * j
 * Created by kigkrazy on 18-5-10.
 */

public class App extends Application {
    private static App app;
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //初始化日志环境，设置全局

        Log.d("asf-tag", "asf-tag onCreate...");
        initLog("asf-tag", LogLevel.ALL, null);
        Utils.init(this);//初始化AndroidUtilCode库
        Andromeda.init(app);
    }

    public static App getInstance() {
        return app;
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }
}
