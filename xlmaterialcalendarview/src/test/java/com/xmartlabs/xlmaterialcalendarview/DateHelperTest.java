package com.xmartlabs.xlmaterialcalendarview;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.junit.Test;
import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DateHelperTest {
  @Test
  public void localDateToCalendarDay() {
    LocalDate localDate = LocalDate.of(2016, 11, 15);
    CalendarDay expectedCalendarDay = CalendarDay.from(2016, 10, 15);
    CalendarDay actualCalendarDay = DateHelper.localDateToCalendarDay(localDate);
    assertEquals(expectedCalendarDay, actualCalendarDay);
  }

  @Test
  public void calendarDayToLocalDate() {
    CalendarDay calendarDay = CalendarDay.from(2016, 10, 15);
    LocalDate expectedLocalDate = LocalDate.of(2016, 11, 15);
    LocalDate actualLocalDate = DateHelper.calendarDayToLocalDate(calendarDay);
    assertEquals(expectedLocalDate, actualLocalDate);
  }

  @Test
  public void calendarDayToYearMonth() {
    CalendarDay calendarDay = CalendarDay.from(2016, 10, 15);
    YearMonth expectedYearMonth = YearMonth.of(2016, 11);
    YearMonth actuaYearMonth = DateHelper.calendarDayToYearMonth(calendarDay);
    assertEquals(expectedYearMonth, actuaYearMonth);
  }
}
