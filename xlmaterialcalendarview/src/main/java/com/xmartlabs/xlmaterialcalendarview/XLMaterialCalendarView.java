package com.xmartlabs.xlmaterialcalendarview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.threeten.bp.Clock;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;

import java.util.Date;
import java.util.List;

/** Calendar widget based on MaterialCalendarView powered to use a backport of the Java 8 Time API. */
public class XLMaterialCalendarView extends MaterialCalendarView {
  public XLMaterialCalendarView(Context context) {
    super(context);
  }

  public XLMaterialCalendarView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   * @return the selected day, or null if no selection. If in multiple selection mode, this
   * will return the last selected date.
   */
  public LocalDate getSelectedLocalDate() {
    return DateHelper.calendarDayToLocalDate(getSelectedDate());
  }

  /** @return all of the currently selected dates. */
  public List<LocalDate> getSelectedLocalDates() {
    return DateHelper.calendarDaysToLocalDates(getSelectedDates());
  }

  /** @param localDate a date to set as selected. Null to clear selection */
  public void setSelectedDate(@Nullable LocalDate localDate) {
    setSelectedDate(localDate == null ? null : DateHelper.localDateToCalendarDay(localDate));
  }

  /**
   * Changes the selection state of a given date.
   *
   * @param localDate the date to change it selection. Passing null does nothing
   * @param selected true if day should be selected, false to deselect
   */
  public void setDateSelected(@Nullable LocalDate localDate, boolean selected) {
    setDateSelected(localDate == null ? null : DateHelper.localDateToCalendarDay(localDate), selected);
  }

  /** Sets the {@code Clock} on which the current date is based. */
  public void setClock(@NonNull Clock clock) {
    LocalDate localDate = clock.instant().atZone(ZoneId.systemDefault()).toLocalDate();
    setCurrentDate(DateHelper.localDateToCalendarDay(localDate));
  }

  /** @return the minimum selectable date for the calendar, if any */
  public LocalDate getMinimumLocalDate() {
    return DateHelper.calendarDayToLocalDate(getMinimumDate());
  }

  /** @return the maximum selectable date for the calendar, if any */
  public LocalDate getMaximumLocalDate() {
    return DateHelper.calendarDayToLocalDate(getMaximumDate());
  }

  /**
   * Select a fresh range of date including first day and last day.
   *
   * @param firstDay first day of the range to select
   * @param lastDay  last day of the range to select
   */
  public void selectRange(LocalDate firstDay, LocalDate lastDay) {
    selectRange(DateHelper.localDateToCalendarDay(firstDay), DateHelper.localDateToCalendarDay(lastDay));
  }

  /** Initialize the parameters from scratch. */
  public StateBuilder newState() {
    return new XLStateBuilder();
  }

  private class XLStateBuilder extends MaterialCalendarView.StateBuilder {
    /** @param localDate set the minimum selectable localDate, null for no minimum */
    public StateBuilder setMinimumDate(@Nullable LocalDate localDate) {
      setMinimumDate(DateHelper.localDateToCalendarDay(localDate));
      return this;
    }

    /** @param localDate set the maximum selectable localDate, null for no maximum */
    public StateBuilder setMaximumDate(@Nullable LocalDate localDate) {
      setMaximumDate(DateHelper.localDateToCalendarDay(localDate));
      return this;
    }
  }
}
