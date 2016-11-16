package com.example;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

/**
 * Created by santiago on 16/11/16.
 */

public class SampleApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    AndroidThreeTen.init(this);
  }
}
