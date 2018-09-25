package com.reizx.asf.di.component;

import android.app.Service;

import com.reizx.asf.di.module.ServiceModule;
import com.reizx.asf.di.scope.ServiceScope;
import com.reizx.asf.service.ForegroundService;

import dagger.Component;

/**
 * Created by kigkrazy on 18-5-12.
 */

@ServiceScope
@Component(dependencies = AppComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
    Service getService();

    void inject(ForegroundService foregroundService);
}
