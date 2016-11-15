package com.xmartlabs.xlmaterialcalendarview;

import android.support.annotation.UiThread;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import org.threeten.bp.YearMonth;

import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

import static com.jakewharton.rxbinding.internal.Preconditions.checkUiThread;

class CalendarViewMonthChangeOnSubscribe implements Observable.OnSubscribe<YearMonth> {
  private final MaterialCalendarView calendarView;

  CalendarViewMonthChangeOnSubscribe(MaterialCalendarView calendarView) {
    this.calendarView = calendarView;
  }

  @Override
  @UiThread
  public void call(final Subscriber<? super YearMonth> subscriber) {
    checkUiThread();

    calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
      @Override
      public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(DateHelper.calendarDayToYearMonth(date));
        }
      }
    });

    subscriber.add(new MainThreadSubscription() {
      @Override
      protected void onUnsubscribe() {
        calendarView.setOnMonthChangedListener(null);
      }
    });
  }
}
