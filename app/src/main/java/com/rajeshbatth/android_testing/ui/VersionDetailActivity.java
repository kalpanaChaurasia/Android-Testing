package com.rajeshbatth.android_testing.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.rajeshbatth.android_testing.R;

public class VersionDetailActivity extends AppCompatActivity {

  @InjectView(R.id.detail_logo)
  ImageView versionDetailLogo;

  @InjectView(R.id.version_name)
  TextView versionName;

  public static Intent launchActivity(Context context, Pair<String, Integer> pair) {
    Intent intent = new Intent(context, VersionDetailActivity.class);
    intent.putExtra("image", pair.second);
    intent.putExtra("name", pair.first);
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_version_detail);
    ButterKnife.inject(this);
    Intent intent = getIntent();
    int imageRes = intent.getIntExtra("image", R.drawable.android_donut);
    versionDetailLogo.setImageResource(imageRes);
    String name = intent.getStringExtra("name");
    versionName.setText(name);
  }
}
