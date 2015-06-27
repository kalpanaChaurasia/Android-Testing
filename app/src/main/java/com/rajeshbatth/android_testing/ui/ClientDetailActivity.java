package com.rajeshbatth.android_testing.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.core.BaseActivity;
import com.rajeshbatth.android_testing.model.Client;

public class ClientDetailActivity extends BaseActivity {

  @InjectView(R.id.toolbar)
  Toolbar toolbar;

  public static void startClientDetailsActivity(Context context, Client client) {
    Intent intent = new Intent(context, ClientDetailActivity.class);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_client_detail);
    ButterKnife.inject(this);
    setSupportActionBar(toolbar);
    setTitle(R.string.client_details);
  }
}
