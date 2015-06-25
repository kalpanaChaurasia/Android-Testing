package com.rajeshbatth.android_testing;

import android.support.test.espresso.ViewInteraction;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Author: Rajesh Batth
 * Date: 19-Jun-2015.
 */
public class TestUtils {

  public static void safeSleep(int time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static ViewInteraction matchToolbarTitle(CharSequence title) {
    return onView(
        allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class)))).check(
        matches(withText(title.toString())));
  }
}
