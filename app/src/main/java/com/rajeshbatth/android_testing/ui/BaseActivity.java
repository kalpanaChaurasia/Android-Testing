package com.rajeshbatth.android_testing.ui;

import android.support.v7.app.AppCompatActivity;
import com.rajeshbatth.android_testing.utils.TaskListener;

/**
 * Author: Rajesh Batth
 * Date: 21-Jun-2015.
 */
public class BaseActivity extends AppCompatActivity {
  protected TaskListener mTaskListener;

  private boolean mIsRunning;

  public void setTaskListener(TaskListener taskListener) {
    mTaskListener = taskListener;
    if (mIsRunning && mTaskListener != null) {
      mTaskListener.onTaskStarted();
    }
  }

  public void setTaskRunning(boolean isRunning) {
    if (mTaskListener != null) {
      mIsRunning = isRunning;
      if (mIsRunning) {
        mTaskListener.onTaskStarted();
      } else {
        mTaskListener.onTaskEnded();
      }
    }
  }
}
