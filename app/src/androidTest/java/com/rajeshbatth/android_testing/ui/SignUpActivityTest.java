package com.rajeshbatth.android_testing.ui;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.rajeshbatth.android_testing.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.rajeshbatth.android_testing.TestUtils.safeSleep;

/**
 * Author: Rajesh Batth
 * Date: 18-Jun-2015.
 */
@RunWith(AndroidJUnit4.class)
public class SignUpActivityTest {

    @Rule
    public ActivityTestRule<SignUpActivity> mActivityTestRule = new ActivityTestRule<>(
            SignUpActivity.class);

    /**
     * Checks whether home activity is launched after successful login
     */
    @Test
    public void testForm() {
        mActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.username)).perform(typeText("Alice"), closeSoftKeyboard());
        safeSleep(100);
        onView(withId(R.id.email)).perform(typeText("alice@gmail.com"), closeSoftKeyboard());
        safeSleep(100);
        onView(withId(R.id.password)).perform(typeText("secret_password"), closeSoftKeyboard());
        safeSleep(100);
        onView(withId(R.id.confirm_password)).perform(typeText("secret_password"),
                closeSoftKeyboard());
        safeSleep(100);
        onView(withId(R.id.submit_button)).perform(click());
        safeSleep(100);
        onView(withText(R.string.home)).check(matches(isDisplayed()));
        pressBack();
    }
}
