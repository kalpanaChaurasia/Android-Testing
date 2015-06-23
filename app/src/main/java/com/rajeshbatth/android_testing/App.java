package com.rajeshbatth.android_testing;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.rajeshbatth.android_testing.di.components.DaggerHomeComponent;
import com.rajeshbatth.android_testing.di.components.HomeComponent;
import com.rajeshbatth.android_testing.di.module.NetworkModule;

/**
 * Created by rajesh.j on 6/19/2015.
 */
public class App extends Application {

    private HomeComponent mHomeComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (getHomeComponent() == null) {
            setHomeComponent(DaggerHomeComponent.builder().networkModule(new NetworkModule())
                    .build());
        }

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                      .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                      .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                      .build());
    }

    public HomeComponent getHomeComponent() {
        return mHomeComponent;
    }

    public void setHomeComponent(HomeComponent homeComponent) {
        mHomeComponent = homeComponent;
    }
}
