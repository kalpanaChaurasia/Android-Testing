package com.rajeshbatth.android_testing.core.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.account.AccountsManager;
import com.rajeshbatth.android_testing.adapter.ClientsAdapter;
import com.rajeshbatth.android_testing.api.HomeApi;
import com.rajeshbatth.android_testing.core.BaseActivity;
import com.rajeshbatth.android_testing.core.auth.SignInActivity;
import com.rajeshbatth.android_testing.model.Client;
import com.rajeshbatth.android_testing.model.HomeDataModel;
import com.rajeshbatth.android_testing.ui.ClientDetailActivity;
import java.util.ArrayList;
import javax.inject.Inject;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HomeActivity extends BaseActivity {

  @InjectView(R.id.toolbar)
  Toolbar toolbar;

  @InjectView(R.id.clients_listview)
  ListView clientsListView;

  @Inject
  HomeApi homeApi;

  @Inject
  AccountsManager accountsManager;

  @InjectView(R.id.empty_text)
  AppCompatTextView emptyText;

  private ClientsAdapter adapter;

  private ProgressDialog progressDialog;

  private ArrayList<Client> clientList = new ArrayList<>();

  @VisibleForTesting
  Callback<HomeDataModel> callback = new Callback<HomeDataModel>() {
    @Override
    public void success(HomeDataModel homeDataModel, Response response) {
      clientList.clear();

      clientList.addAll(homeDataModel.getClients());
      adapter.notifyDataSetChanged();
      setTaskRunning(false);
      if (progressDialog != null && progressDialog.isShowing()) {
        progressDialog.dismiss();
      }
    }

    @Override
    public void failure(RetrofitError error) {
      setTaskRunning(false);
      if (progressDialog != null && progressDialog.isShowing()) {
        progressDialog.dismiss();
      }
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
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

  @OnItemClick(R.id.clients_listview)
  void onClientClicked(int position) {
    Client client = clientList.get(position);
    ClientDetailActivity.startClientDetailsActivity(this, client);
  }

  private void fetchClients() {
    homeApi.getHomeDataAsync(callback);
    progressDialog = ProgressDialog.show(this, null, "Loading...", false, false);
    setTaskRunning(true);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_home, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.action_logout) {
      logout();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void logout() {
    accountsManager.logout();
    finish();
    startActivity(new Intent(this, SignInActivity.class));
  }
}
