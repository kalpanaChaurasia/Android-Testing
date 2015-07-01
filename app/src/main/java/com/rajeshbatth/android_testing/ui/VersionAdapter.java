package com.rajeshbatth.android_testing.ui;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.adapter.BannerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by user on 6/30/2015.
 */
public class VersionAdapter extends RecyclerView.Adapter<VersionAdapter.VersionVH> {

  private static final int ANIMATION_DELAY_INTERVAL = 50;

  private static final int ITEM_PAGER = 1;
  private static final int ITEM_HEADER = 2;
  private static final int ITEM_CARD = 3;

  private final Handler handler = new Handler(Looper.getMainLooper());
  private int lastAnimatedPosition = -1;
  private long nextAnimationStartTime;
  private int defaultItemAnimationDuration = 0;

  @Override
  public VersionVH onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    if (defaultItemAnimationDuration == 0) {
      defaultItemAnimationDuration =
          viewGroup.getResources().getInteger(android.R.integer.config_mediumAnimTime);
    }

    View view;

    if (viewType == ITEM_PAGER) {
      view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pager, viewGroup, false);
    } else if (viewType == ITEM_HEADER) {
      view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header, viewGroup, false);
    } else {
      view = LayoutInflater.from(viewGroup.getContext())
          .inflate(R.layout.item_android_version, viewGroup, false);
    }
    return new VersionVH(view, viewType);
  }

  @Override
  public void onBindViewHolder(VersionVH versionVH, int position) {
    int itemViewType = getItemViewType(position);
    if (itemViewType == ITEM_PAGER) {
      setUpPager(versionVH);
    } else if (itemViewType == ITEM_HEADER) {
      ((TextView) versionVH.itemView).setText("Android Versions");
    } else if (itemViewType == ITEM_CARD) {
      Pair<String, Integer> pair = TestTransition.versionLogoPairs.get(position % 10);
      versionVH.setVersionName(pair.first);
      versionVH.setIcon(pair.second);
      runAnimation(versionVH, position, defaultItemAnimationDuration);
    }
  }

  private void setUpPager(VersionVH versionVH) {
    ViewPager viewPager = (ViewPager) versionVH.itemView.findViewById(R.id.pager);
    viewPager.setAdapter(new BannerAdapter());
    CirclePageIndicator mIndicator =
        (CirclePageIndicator) versionVH.itemView.findViewById(R.id.indicator);
    mIndicator.setViewPager(viewPager);
  }

  @Override
  public long getItemId(int i) {
    return 0;
  }

  @Override
  public int getItemViewType(int position) {
    if (position == 0) {
      return ITEM_PAGER;
    } else if (position == 1) {
      return ITEM_HEADER;
    } else {
      return ITEM_CARD;
    }
  }

  public int getSpanSize(int position) {
    switch (getItemViewType(position)) {
      case ITEM_CARD:
        return 1;
      case ITEM_HEADER:
      case ITEM_PAGER:
        return 2;
    }
    return 1;
  }

  @Override
  public int getItemCount() {
    return TestTransition.versionLogoPairs.size() * 10 + 2;
  }

  protected void runAnimation(final RecyclerView.ViewHolder targetViewHolder, final int position,
      final int duration) {
    final float maxAlpha = 1f;
    final View targetView = targetViewHolder.itemView;

    // Don't actually run the animation right a way. This gives a nice effect
    // when adding a large batch of items.
    if (position > lastAnimatedPosition) {
      int delay = 0;
      long currTime = System.currentTimeMillis();
      if (currTime < nextAnimationStartTime + ANIMATION_DELAY_INTERVAL) {
        delay = (int) ((nextAnimationStartTime + ANIMATION_DELAY_INTERVAL) - currTime);
      }
      nextAnimationStartTime = currTime + delay;

      targetView.setAlpha(0);
      targetView.setTranslationY(500.0f);
      handler.postDelayed(new Runnable() {
        @Override
        public void run() {
          targetView.animate().alpha(maxAlpha).translationY(0).setDuration(duration);
          targetView.animate().setInterpolator(new LinearOutSlowInInterpolator());
          targetView.animate().start();
        }
      }, delay);
      lastAnimatedPosition = position;
    }
  }

  public static class VersionVH extends RecyclerView.ViewHolder {

    private final int viewType;
    private ImageView logo;
    private TextView name;

    public VersionVH(View itemView, int viewType) {
      super(itemView);
      this.viewType = viewType;
      if (this.viewType == ITEM_CARD) {
        logo = ((ImageView) itemView.findViewById(R.id.logo));
        name = ((TextView) itemView.findViewById(R.id.version_name));
      }
    }

    public void setIcon(@DrawableRes int icon) {
      if (logo != null) {
        logo.setImageResource(icon);
      }
    }

    public void setVersionName(String versionName) {
      if (name != null) {
        name.setText(versionName);
      }
    }
  }
}
