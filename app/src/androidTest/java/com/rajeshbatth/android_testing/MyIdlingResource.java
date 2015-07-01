package com.rajeshbatth.android_testing;

import android.support.test.espresso.IdlingResource;
import com.rajeshbatth.android_testing.utils.TaskListener;

/**
 * Author: Rajesh Batth
 * Date: 21-Jun-2015.
 */
public class MyIdlingResource implements IdlingResource, TaskListener {

  private boolean isTaskRunning;

  private ResourceCallback resourceCallback;

  @Override
  public String getName() {
    return "My Idling Resource";
  }

  @Override
  public boolean isIdleNow() {
    return !isTaskRunning;
  }

  @Override
  public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
    this.resourceCallback = resourceCallback;
  }

  @Override
  public void onTaskStarted() {
    isTaskRunning = true;
  }

  @Override
  public void onTaskEnded() {
    isTaskRunning = false;
    if (resourceCallback != null) {
      resourceCallback.onTransitionToIdle();
    }
  }
}
