package com.rajeshbatth.android_testing.ui;

import android.support.v7.app.AppCompatActivity;
import com.rajeshbatth.android_testing.utils.TaskListener;

/**
 * Author: Rajesh Batth
 * Date: 21-Jun-2015.
 */
public class BaseActivity extends AppCompatActivity {
  protected TaskListener taskListener;

  private boolean isRunning;

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
