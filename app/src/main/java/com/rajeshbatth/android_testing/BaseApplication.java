package com.rajeshbatth.android_testing;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import com.rajeshbatth.android_testing.core.ApplicationComponent;
import com.rajeshbatth.android_testing.core.DaggerApplicationComponent;
import com.rajeshbatth.android_testing.di.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.squareup.okhttp.OkHttpClient;
import javax.inject.Inject;

/**
 * This is release variant of Application. Also check DebugApplication.java in debug flavor,
 * DebugApplication extends this class, so all the implementation in this class will be available
 * to the debug variant as well.
 */
public class BaseApplication extends Application {

  protected ApplicationComponent applicationComponent;
  @Inject
  OkHttpClient okHttpClient;
  private RefWatcher refWatcher;

  public static RefWatcher getRefWatcher(Context context) {
    BaseApplication application = (BaseApplication) context.getApplicationContext();
    return application.refWatcher;
  }

  public static ApplicationComponent applicationComponent(@NonNull Context context) {
    return ((BaseApplication) context.getApplicationContext()).getApplicationComponent();
  }

  @Override
  public void onCreate() {
    super.onCreate();
    ApplicationComponent.Holder.getApplicationComponent(this).inject(this);
    LeakCanary.install(this);
  }

  public ApplicationComponent getApplicationComponent() {
    if (applicationComponent == null) {
      applicationComponent = DaggerApplicationComponent.builder()
          .applicationModule(new ApplicationModule(this))
          .build();
    }
    return applicationComponent;
  }

  public void setApplicationComponent(ApplicationComponent applicationComponent) {
    this.applicationComponent = applicationComponent;
  }
}
