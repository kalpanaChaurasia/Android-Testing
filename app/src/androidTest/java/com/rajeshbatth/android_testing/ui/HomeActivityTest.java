package com.rajeshbatth.android_testing.ui;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rajeshbatth.android_testing.App;
import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.api.HomeApi;
import com.rajeshbatth.android_testing.di.components.DaggerTestHomeComponent;
import com.rajeshbatth.android_testing.di.components.TestHomeComponent;
import com.rajeshbatth.android_testing.model.Client;
import com.rajeshbatth.android_testing.model.HomeDataModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.hasItem;

/**
 * Created by rajesh.j on 6/19/2015.
 */
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new
            ActivityTestRule<>(HomeActivity.class, true, false);

    @Inject
    HomeApi mHomeApi;

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        App app = (App) instrumentation.getTargetContext().getApplicationContext();
        TestHomeComponent testHomeComponent = DaggerTestHomeComponent.builder().build();
        app.setHomeComponent(testHomeComponent);
        testHomeComponent.inject(this);
    }

    @Test
    public void testHitsServer() {
        mActivityTestRule.launchActivity(new Intent());
        HomeActivity homeActivity = mActivityTestRule.getActivity();
        Mockito.verify(mHomeApi).getHomeDataAsync(homeActivity.mCallback);
        HomeDataModel dummyData = getDummyData();
        homeActivity.mCallback.success(dummyData, null);
        onData(hasItem(dummyData.getClients().get(0))).inAdapterView(withId(R.id.clients_listview))
                                                      .perform(click());
    }

    @Test
    public void testHandleError() {
        HomeActivity homeActivity = mActivityTestRule.getActivity();
        mActivityTestRule.launchActivity(new Intent());
        Mockito.verify(mHomeApi).getHomeDataAsync(homeActivity.mCallback);
        HomeDataModel dummyData = getDummyData();
        dummyData.getClients().clear();
        homeActivity.mCallback.success(dummyData, null);
    }

    @NonNull
    private HomeDataModel getDummyData() {
        HomeDataModel homeDataModel = new HomeDataModel();
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Client client = new Client();
            client.setFirstName("Client");
            client.setLastName("" + i);
            client.setEmail(String.format("%s %s", client.getFirstName(), client.getLastName()));
            client.setEmail(
                    String.format("%s.%s@gmail.com", client.getFirstName(), client.getLastName()));
            clients.add(client);
        }
        homeDataModel.setClients(clients);
        return homeDataModel;
    }
}
