package com.rajeshbatth.android_testing;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp.StethoInterceptor;

/**
 * This is Debug variant of Application.
 * All the general implementations should go into BaseApplication.java.
 */
public class DebugApplication extends BaseApplication {

  @Override public void onCreate() {
    super.onCreate();
    Stetho.initialize(Stetho.newInitializerBuilder(this)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        .build());
    okHttpClient.networkInterceptors().add(new StethoInterceptor());
  }

}
