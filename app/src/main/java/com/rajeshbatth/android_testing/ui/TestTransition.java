package com.rajeshbatth.android_testing.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.rajeshbatth.android_testing.R;
import java.util.ArrayList;
import java.util.List;

public class TestTransition extends AppCompatActivity {

  public static final List<Pair<String, Integer>> versionLogoPairs = new ArrayList<>();

  static {
    versionLogoPairs.add(Pair.create("CupCake", R.drawable.android_cupcake));
    versionLogoPairs.add(Pair.create("Donut", R.drawable.android_donut));
    versionLogoPairs.add(Pair.create("Eclair", R.drawable.android_eclair));
    versionLogoPairs.add(Pair.create("Froyo", R.drawable.android_froyo));
    versionLogoPairs.add(Pair.create("Gingerbread", R.drawable.android_gingerbread));
    versionLogoPairs.add(Pair.create("Honeycomb", R.drawable.android_honeycomb));
    versionLogoPairs.add(Pair.create("Ice Cream Sandwich", R.drawable.android_icecreamsandwich));
    versionLogoPairs.add(Pair.create("JellyBean", R.drawable.android_jellybean));
    versionLogoPairs.add(Pair.create("KitKat", R.drawable.android_kitkat));
    versionLogoPairs.add(Pair.create("Lollipop", R.drawable.android_lollipop));
  }

  @InjectView(R.id.android_versions_list)
  RecyclerView androidVersionRecyclerView;
  private VersionAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_transition);
    ButterKnife.inject(this);
    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
    androidVersionRecyclerView.setLayoutManager(gridLayoutManager);
    adapter = new VersionAdapter();
    androidVersionRecyclerView.setAdapter(adapter);
    gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override
      public int getSpanSize(int position) {
        return adapter.getSpanSize(position);
      }
    });
  }

  public void onItemClick(View view, int i) {
    Intent intent = VersionDetailActivity.launchActivity(this, versionLogoPairs.get(i));
    Bundle bundle =
        ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, "image").toBundle();
    startActivity(intent, bundle);
  }
}
