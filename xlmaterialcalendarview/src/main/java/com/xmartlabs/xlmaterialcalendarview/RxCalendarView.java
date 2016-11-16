package com.xmartlabs.xlmaterialcalendarview;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

import static com.jakewharton.rxbinding.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} and {@linkplain Action1
 * actions} for {@link XLMaterialCalendarView}.
 */
public class RxCalendarView {
  /**
   * Create an observable which emits when the selected date changes in the {@code calendarView}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
   * to free this reference.
   */
  @CheckResult
  @NonNull
  public static Observable<LocalDate> dateChanges(@NonNull MaterialCalendarView calendarView) {
    checkNotNull(calendarView, "calendarView == null");
    return Observable.create(new CalendarViewDateChangeOnSubscribe(calendarView));
  }

  /**
   * Create an observable which emits when the month changes in the {@code calendarView}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
   * to free this reference.
   */
  @CheckResult
  @NonNull
  public static Observable<YearMonth> monthChanges(@NonNull MaterialCalendarView calendarView) {
    checkNotNull(calendarView, "calendarView == null");
    return Observable.create(new CalendarViewMonthChangeOnSubscribe(calendarView));
  }

  /**
   * Create an observable which emits when the selected range changes in the {@code calendarView}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
   * to free this reference.
   */
  @CheckResult
  @NonNull
  public static Observable<List<LocalDate>> rangeSelected(@NonNull MaterialCalendarView calendarView) {
    checkNotNull(calendarView, "calendarView == null");
    return Observable.create(new CalendarViewRangeSelectedOnSubscribe(calendarView));
  }

  private RxCalendarView() {
    throw new AssertionError("No instances.");
  }
}
