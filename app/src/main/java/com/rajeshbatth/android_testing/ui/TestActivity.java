package com.rajeshbatth.android_testing.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.view.MotionViewPager;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class TestActivity extends AppCompatActivity {

  private static final long SLIDE_INTERVAL = 4000;
  private static final double SLIDE_DURATION_FACTOR = 3.0;

  @InjectView(R.id.pager)
  MotionViewPager viewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test);
    ButterKnife.inject(this);
    viewPager.setAdapter(new MyAdapter());
    viewPager.setScrollDurationFactor(SLIDE_DURATION_FACTOR);
    CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
    indicator.setViewPager(viewPager);
    indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {

      }

      @Override
      public void onPageScrollStateChanged(int state) {
        switch (state) {
          case ViewPager.SCROLL_STATE_DRAGGING:
            handler.removeCallbacks(slidePagerRunnable);
            break;
          case ViewPager.SCROLL_STATE_SETTLING:
            handler.postDelayed(slidePagerRunnable, SLIDE_INTERVAL);
            break;
        }
      }
    });
  }

  private Handler handler = new Handler();
  private Runnable slidePagerRunnable = new Runnable() {
    @Override
    public void run() {
      viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
    }
  };

  public static class MyAdapter extends PagerAdapter {
    public int[] colors = { Color.RED, Color.GREEN, Color.BLUE };

    @Override
    public int getCount() {
      return colors.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      View view = new View(container.getContext());
      view.setBackgroundColor(colors[position]);
      ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT);
      view.setLayoutParams(layoutParams);
      container.addView(view);
      return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View) object);
    }
  }
}
