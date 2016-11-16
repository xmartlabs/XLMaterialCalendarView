package com.xmartlabs.xlmaterialcalendarview;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.threeten.bp.LocalDate;

import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

import static com.jakewharton.rxbinding.internal.Preconditions.checkUiThread;

final class CalendarViewDateChangeOnSubscribe implements Observable.OnSubscribe<LocalDate> {
  private final MaterialCalendarView calendarView;

  CalendarViewDateChangeOnSubscribe(MaterialCalendarView calendarView) {
    this.calendarView = calendarView;
  }

  @Override
  @UiThread
  public void call(final Subscriber<? super LocalDate> subscriber) {
    checkUiThread();

    calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
      @Override
      public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(DateHelper.calendarDayToLocalDate(date));
        }
      }
    });

    subscriber.add(new MainThreadSubscription() {
      @Override
      protected void onUnsubscribe() {
        calendarView.setOnDateChangedListener(null);
      }
    });
  }
}
