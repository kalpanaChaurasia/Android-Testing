package com.rajeshbatth.android_testing.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.adapter.ClientsAdapter;
import com.rajeshbatth.android_testing.api.HomeApi;
import com.rajeshbatth.android_testing.di.components.HomeComponent;
import com.rajeshbatth.android_testing.model.Client;
import com.rajeshbatth.android_testing.model.HomeDataModel;
import java.util.ArrayList;
import javax.inject.Inject;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HomeActivity extends BaseActivity {

  @InjectView(R.id.toolbar) Toolbar toolbar;

  @InjectView(R.id.clients_listview) ListView clientsListView;

  @Inject HomeApi homeApi;

  @InjectView(R.id.empty_text) AppCompatTextView emptyText;

  private ClientsAdapter adapter;

  private ProgressDialog progressDialog;

  private ArrayList<Client> clientList = new ArrayList<>();

  @VisibleForTesting Callback<HomeDataModel> mCallback = new Callback<HomeDataModel>() {
    @Override public void success(HomeDataModel homeDataModel, Response response) {
      clientList.clear();
      clientList.addAll(homeDataModel.getClients());
      adapter.notifyDataSetChanged();
      setTaskRunning(false);
      progressDialog.dismiss();
    }

    @Override public void failure(RetrofitError error) {
      setTaskRunning(false);
      progressDialog.dismiss();
    }
  };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    ButterKnife.inject(this);
    HomeComponent.Holder.getHomeComponent(this).inject(this);
    setSupportActionBar(toolbar);
    setTitle(getString(R.string.home));
    adapter = new ClientsAdapter(clientList);
    clientsListView.setAdapter(adapter);
    fetchClients();
  }

  @OnItemClick(R.id.clients_listview) void onClientClicked(int position) {
    Client client = clientList.get(position);
    ClientDetailActivity.startClientDetailsActivity(this, client);
  }

  private void fetchClients() {
    homeApi.getHomeDataAsync(mCallback);
    progressDialog = ProgressDialog.show(this, null, "Loading...", false, false);
    setTaskRunning(true);
  }
}
