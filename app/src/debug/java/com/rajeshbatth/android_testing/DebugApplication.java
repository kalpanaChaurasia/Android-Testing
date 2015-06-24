package com.rajeshbatth.android_testing;

import com.facebook.stetho.Stetho;

/**
 * This is Debug variant of Application.
 * All the implementation should go into BaseApplication.java.
 */
public class DebugApplication extends BaseApplication {

  @Override public void onCreate() {
    super.onCreate();
    Stetho.initialize(Stetho.newInitializerBuilder(this)
            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
            .build());
  }

}
