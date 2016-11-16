package com.xmartlabs.xlmaterialcalendarview;

import android.support.annotation.NonNull;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.ZoneId;

import java.util.ArrayList;
import java.util.List;

final class DateHelper {
  static CalendarDay localDateToCalendarDay(LocalDate localDate) {
    return CalendarDay.from(DateTimeUtils.toDate(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
  }

  static LocalDate calendarDayToLocalDate(CalendarDay calendarDay) {
    return DateTimeUtils.toInstant(calendarDay.getDate()).atZone(ZoneId.systemDefault()).toLocalDate();
  }

  static YearMonth calendarDayToYearMonth(CalendarDay calendarDay) {
    return YearMonth.from(calendarDayToLocalDate(calendarDay));
  }

  @NonNull
  static List<LocalDate> calendarDaysToLocalDates(List<CalendarDay> calendarDays) {
    List<LocalDate> selectedLocalDates = new ArrayList<>(calendarDays.size());

    for (CalendarDay calendarDay : calendarDays) {
      selectedLocalDates.add(calendarDayToLocalDate(calendarDay));
    }

    return selectedLocalDates;
  }

  private DateHelper() {
    throw new AssertionError("No instances.");
  }
}
