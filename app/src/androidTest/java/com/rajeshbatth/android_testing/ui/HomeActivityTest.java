package com.rajeshbatth.android_testing.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rajeshbatth.android_testing.MyIdlingResource;
import com.rajeshbatth.android_testing.api.HomeApi;
import com.rajeshbatth.android_testing.di.components.DaggerTestHomeComponent;
import com.rajeshbatth.android_testing.di.components.TestHomeComponent;
import com.rajeshbatth.android_testing.model.Client;
import com.rajeshbatth.android_testing.model.HomeDataModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit.Callback;

import static junit.framework.Assert.assertSame;

/**
 * Created by rajesh.j on 6/19/2015.
 */
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(
            HomeActivity.class);

    @Inject
    HomeApi mHomeApi;

    private HomeActivity mHomeActivity;

    @Before
    public void setUp() {
        TestHomeComponent testHomeComponent = DaggerTestHomeComponent
                .builder()
                .build();
        TestHomeComponent.Injector.setHomeComponent(testHomeComponent);
        testHomeComponent.inject(this);
    }

    public void testActualServer() {
        HomeActivity activity = mActivityTestRule.getActivity();
        MyIdlingResource idlingResource = new MyIdlingResource();
        activity.setTaskListener(idlingResource);
        Espresso.registerIdlingResources(idlingResource);
    }


    @Test
    public void testHitsServer() {
        mActivityTestRule.launchActivity(new Intent());
        mHomeActivity = mActivityTestRule.getActivity();
        Mockito.verify(mHomeApi).getHomeDataAsync(Matchers.<Callback<HomeDataModel>>any());
        assertSame(mHomeApi, mHomeActivity.homeApi);
        final HomeDataModel dummyData = getDummyData();
        mHomeActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mHomeActivity.mCallback.success(dummyData, null);
            }
        });
    }

    @Test
    public void testHandleError() {
        mActivityTestRule.launchActivity(new Intent());
        mHomeActivity = mActivityTestRule.getActivity();
        Mockito.verify(mHomeApi).getHomeDataAsync(Matchers.<Callback<HomeDataModel>>any());
        assertSame(mHomeApi, mHomeActivity.homeApi);
        final HomeDataModel dummyData = getDummyData();
        dummyData.getClients().clear();
        mHomeActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mHomeActivity.mCallback.success(dummyData, null);
            }
        });
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
