package com.reizx.asf.service.common;

import android.app.Service;

import com.reizx.asf.app.App;
import com.reizx.asf.di.component.DaggerServiceComponent;
import com.reizx.asf.di.component.ServiceComponent;
import com.reizx.asf.di.module.ServiceModule;
import com.reizx.asf.model.DataManager;

import javax.inject.Inject;

public abstract class BaseService extends Service {
    @Inject
    protected App app;

    @Inject
    protected DataManager dm;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    protected abstract void initInject();

    protected ServiceComponent getServiceComponent(){
        return DaggerServiceComponent.builder()
                .appComponent(App.getAppComponent())
                .serviceModule(new ServiceModule(this))
                .build();
    }
}
