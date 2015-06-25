package com.rajeshbatth.android_testing.di.components;

import android.content.Context;
import com.rajeshbatth.android_testing.account.AccountsManager;
import com.rajeshbatth.android_testing.di.module.AccountsModule;
import com.rajeshbatth.android_testing.di.scope.PerActivity;
import com.rajeshbatth.android_testing.ui.SplashActivity;
import dagger.Component;
import java.lang.ref.WeakReference;

import static com.rajeshbatth.android_testing.BaseApplication.applicationComponent;

/**
 * Created by rajesh.j on 6/25/2015.
 */
@PerActivity
@Component(dependencies = { ApplicationComponent.class }, modules = { AccountsModule.class })
public interface SplashComponent {
  AccountsManager provideAccountsManager();

  void inject(SplashActivity splashActivity);

  class Injector {
    static WeakReference<SplashComponent> componentWeakReference;

    public static void setSplashComponent(SplashComponent splashComponent) {
      componentWeakReference = new WeakReference<>(splashComponent);
    }

    public static SplashComponent getSplashComponent(Context context) {
      if (componentWeakReference == null || componentWeakReference.get() == null) {
        SplashComponent splashComponent =
            DaggerSplashComponent.builder().applicationComponent(applicationComponent(context))
            .build();
        componentWeakReference = new WeakReference<>(splashComponent);
      }
      return componentWeakReference.get();
    }
  }
}
