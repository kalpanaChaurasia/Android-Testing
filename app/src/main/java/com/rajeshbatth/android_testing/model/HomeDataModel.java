package com.rajeshbatth.android_testing.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by rajesh.j on 6/19/2015.
 */
public class HomeDataModel {
  @SerializedName("results") private List<Client> clients;

  public List<Client> getClients() {
    return clients;
  }

  public void setClients(List<Client> clients) {
    this.clients = clients;
  }
}
