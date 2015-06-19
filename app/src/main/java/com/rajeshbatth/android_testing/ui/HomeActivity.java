package com.rajeshbatth.android_testing.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.rajeshbatth.android_testing.App;
import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.adapter.ClientsAdapter;
import com.rajeshbatth.android_testing.api.HomeApi;
import com.rajeshbatth.android_testing.model.Client;
import com.rajeshbatth.android_testing.model.HomeDataModel;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HomeActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @InjectView(R.id.clients_listview)
    ListView mClientsListView;

    @Inject
    HomeApi mHomeApi;

    private ClientsAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    private ArrayList<Client> mClientList = new ArrayList<>();

    @VisibleForTesting
    Callback<HomeDataModel> mCallback = new Callback<HomeDataModel>() {
        @Override
        public void success(HomeDataModel homeDataModel, Response response) {
            mClientList.clear();
            mClientList.addAll(homeDataModel.getClients());
            mAdapter.notifyDataSetChanged();
            mProgressDialog.dismiss();
        }

        @Override
        public void failure(RetrofitError error) {
            mProgressDialog.dismiss();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
        injectDependencies();
        setSupportActionBar(mToolbar);
        setTitle(getString(R.string.home));
        mAdapter = new ClientsAdapter(mClientList);
        mClientsListView.setAdapter(mAdapter);
        fetchClients();
    }

    private void injectDependencies() {
        ((App) getApplication()).getHomeComponent().inject(this);
    }

    private void fetchClients() {
        mHomeApi.getHomeDataAsync(mCallback);
        mProgressDialog = ProgressDialog.show(this, null, "Loading..", false, false);
    }
}
