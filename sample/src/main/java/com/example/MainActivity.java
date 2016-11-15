package com.example;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.example.xlmaterialcalendarview.R;
import com.xmartlabs.xlmaterialcalendarview.XLMaterialCalendarView;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    XLMaterialCalendarView calendarView = (XLMaterialCalendarView) findViewById(R.id.calendarView);

    // TODO
  }
}
