package com.rajeshbatth.android_testing.adapter;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 7/1/2015.
 */
public class BannerAdapter extends PagerAdapter {
  List<Integer> bgs =
      Arrays.asList(Color.CYAN, Color.RED, Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.BLUE);

  @Override
  public int getCount() {
    return bgs.size();
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    View view = new View(container.getContext());
    view.setBackgroundColor(bgs.get(position));
    container.addView(view);
    return view;
  }
}
