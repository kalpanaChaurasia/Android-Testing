package com.rajeshbatth.android_testing.di.components;

import android.content.Context;
import com.rajeshbatth.android_testing.account.AccountsManager;
import com.rajeshbatth.android_testing.api.HomeApi;
import com.rajeshbatth.android_testing.di.module.AccountsModule;
import com.rajeshbatth.android_testing.di.module.ApiModule;
import com.rajeshbatth.android_testing.di.scope.PerActivity;
import com.rajeshbatth.android_testing.ui.HomeActivity;
import dagger.Component;
import java.lang.ref.WeakReference;

/**
 * Created by rajesh.j on 6/19/2015.
 */
@PerActivity @Component(dependencies = { ApplicationComponent.class }, modules = {
    ApiModule.class, AccountsModule.class
}) public interface HomeComponent {

  HomeApi provideHomeApi();

  AccountsManager provideAccountsManager();

  void inject(HomeActivity homeActivity);

  class Holder {

    static WeakReference<HomeComponent> homeComponentWeakRef;

    public static HomeComponent getHomeComponent(Context context) {
      if (homeComponentWeakRef == null || homeComponentWeakRef.get() == null) {
        HomeComponent homeComponent = DaggerHomeComponent.builder()
            .applicationComponent(ApplicationComponent.Holder.getApplicationComponent(context))
            .build();
        homeComponentWeakRef = new WeakReference<>(homeComponent);
      }
      return homeComponentWeakRef.get();
    }

    public static void setHomeComponent(HomeComponent homeComponent) {
      homeComponentWeakRef = new WeakReference<>(homeComponent);
    }
  }
}
