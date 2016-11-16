package com.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.xlmaterialcalendarview.R;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.xmartlabs.xlmaterialcalendarview.RxCalendarView;
import com.xmartlabs.xlmaterialcalendarview.XLMaterialCalendarView;

import org.threeten.bp.Clock;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.ZoneId;

import java.util.List;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final XLMaterialCalendarView calendarView = (XLMaterialCalendarView) findViewById(R.id.calendarView);
    final CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);

    if (calendarView.getMinimumLocalDate() != null) {
      Toast.makeText(MainActivity.this, "There shouldn't be a minimum date yet", Toast.LENGTH_SHORT).show();
    }

    if (calendarView.getMaximumLocalDate() != null) {
      Toast.makeText(MainActivity.this, "There shouldn't be a maximum date yet", Toast.LENGTH_SHORT).show();
    }

    calendarView.newState()
        .setFirstDayOfWeek(1)
        .setMinimumDate(LocalDate.of(2016, 6, 12))
        .setMaximumDate(LocalDate.of(2016, 12, 9))
        .commit();

    if (calendarView.getSelectedLocalDate() != null) {
      Toast.makeText(MainActivity.this, "There shouldn't be a selected date yet", Toast.LENGTH_SHORT).show();
    }

    if (!calendarView.getSelectedLocalDates().isEmpty()) {
      Toast.makeText(MainActivity.this, "There selected dates range should be empty", Toast.LENGTH_SHORT).show();
    }

    calendarView.setClock(mockedClock());

    calendarView.selectRange(LocalDate.of(2016, 9, 17), LocalDate.of(2016, 9, 29)); // We could select a range.

    calendarView.setSelectedDate(LocalDate.of(2016, 11, 14)); // But we will select a particular date.
    calendarView.setDateSelected(LocalDate.of(2016, 11, 14), true); // We can use this method to (un)select too.

    RxCalendarView.dateChanges(calendarView)
        .subscribe(new Action1<LocalDate>() {
          @Override
          public void call(LocalDate localDate) {
            Toast.makeText(MainActivity.this, String.format("Selected date: %s", localDate), Toast.LENGTH_SHORT).show();
          }
        });

    RxCalendarView.monthChanges(calendarView)
        .subscribe(new Action1<YearMonth>() {
          @Override
          public void call(YearMonth yearMonth) {
            Toast.makeText(MainActivity.this, String.format("Month changed: %s", yearMonth), Toast.LENGTH_SHORT).show();
          }
        });

    RxCalendarView.rangeSelected(calendarView)
        .subscribe(new Action1<List<LocalDate>>() {
          @Override
          public void call(List<LocalDate> localDates) {
            Toast.makeText(MainActivity.this, "Selected dates: " + formattedLocalDates(localDates), Toast.LENGTH_SHORT)
                .show();
          }
        });

    RxCompoundButton.checkedChanges(checkBox)
        .subscribe(new Action1<Boolean>() {
          @Override
          public void call(Boolean isChecked) {
            calendarView.setSelectionMode(isChecked
                ? MaterialCalendarView.SELECTION_MODE_RANGE
                : MaterialCalendarView.SELECTION_MODE_SINGLE
            );
          }
        });
  }

  private static String formattedLocalDates(List<LocalDate> localDates) {
    StringBuilder res = new StringBuilder(localDates.size() * 12);

    for (LocalDate localDate : localDates) {
      res.append(localDate);
      res.append(", ");
    }

    res.delete(res.length() - 2, res.length() - 1);

    return res.toString();
  }

  private static Clock mockedClock() {
    Instant instant = Instant.parse("2016-10-16T10:15:30.00Z");
    ZoneId zoneId = ZoneId.of("GMT-03:00");
    return Clock.fixed(instant, zoneId);
  }
}
