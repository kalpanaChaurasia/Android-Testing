package com.rajeshbatth.android_testing.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.model.Client;
import java.util.List;

/**
 * Created by rajesh.j on 6/19/2015.
 */
public class ClientsAdapter extends BaseAdapter {

  private final List<Client> clientList;

  public ClientsAdapter(@NonNull List<Client> clientList) {
    this.clientList = clientList;
  }

  @Override public int getCount() {
    return clientList.size();
  }

  @Override public Object getItem(int position) {
    return clientList.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView =
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_client, parent, false);
    }
    Client client = clientList.get(position);
    ImageView profilePic = (ImageView) convertView.findViewById(R.id.profile_pic);
    Glide.with(parent.getContext()).load(client.getPicture()).into(profilePic);
    ((TextView) convertView.findViewById(R.id.client_name)).setText(client.getFullName());
    return convertView;
  }
}
