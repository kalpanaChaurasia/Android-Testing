package com.rajeshbatth.android_testing.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.rajeshbatth.android_testing.BaseApplication;
import com.rajeshbatth.android_testing.di.components.ApplicationComponent;
import com.rajeshbatth.android_testing.di.module.ActivityModule;
import com.rajeshbatth.android_testing.utils.TaskListener;

/**
 * Author: Rajesh Batth
 * Date: 21-Jun-2015.
 */
public class BaseActivity extends AppCompatActivity {
  protected TaskListener taskListener;

  private boolean isRunning;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  protected ApplicationComponent getApplicationComponent() {
    return ((BaseApplication) getApplication()).getApplicationComponent();
  }

  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }

  public void setTaskListener(TaskListener taskListener) {
    this.taskListener = taskListener;
    if (isRunning && this.taskListener != null) {
      this.taskListener.onTaskStarted();
    }
  }

  public void setTaskRunning(boolean isRunning) {
    if (taskListener != null) {
      this.isRunning = isRunning;
      if (this.isRunning) {
        taskListener.onTaskStarted();
      } else {
        taskListener.onTaskEnded();
      }
    }
  }
}
