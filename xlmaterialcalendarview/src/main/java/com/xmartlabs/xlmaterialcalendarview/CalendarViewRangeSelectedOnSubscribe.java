package com.xmartlabs.xlmaterialcalendarview;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener;

import org.threeten.bp.LocalDate;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

import static com.jakewharton.rxbinding.internal.Preconditions.checkUiThread;

final class CalendarViewRangeSelectedOnSubscribe implements Observable.OnSubscribe<List<LocalDate>> {
  private final MaterialCalendarView calendarView;

  CalendarViewRangeSelectedOnSubscribe(MaterialCalendarView calendarView) {
    this.calendarView = calendarView;
  }

  @Override
  @UiThread
  public void call(final Subscriber<? super List<LocalDate>> subscriber) {
    checkUiThread();

    calendarView.setOnRangeSelectedListener(new OnRangeSelectedListener() {
      @Override
      public void onRangeSelected(@NonNull MaterialCalendarView widget, @NonNull List<CalendarDay> dates) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(DateHelper.calendarDaysToLocalDates(dates));
        }
      }
    });

    subscriber.add(new MainThreadSubscription() {
      @Override
      protected void onUnsubscribe() {
        calendarView.setOnRangeSelectedListener(null);
      }
    });
  }
}
