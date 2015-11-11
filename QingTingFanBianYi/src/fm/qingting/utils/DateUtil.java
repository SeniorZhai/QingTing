package fm.qingting.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil
{
  public static long dateTransformBetweenTimeZone(Date paramDate, TimeZone paramTimeZone1, TimeZone paramTimeZone2)
  {
    return Long.valueOf(paramDate.getTime() - paramTimeZone1.getRawOffset() + paramTimeZone2.getRawOffset()).longValue() / 1000L;
  }

  public static int getClockTime()
  {
    Calendar localCalendar = Calendar.getInstance();
    int i = localCalendar.get(11);
    int j = localCalendar.get(12);
    return localCalendar.get(13) + (0 + i * 60 * 60 + j * 60);
  }

  public static long getCurrentMillis()
  {
    return System.currentTimeMillis() + 28800000L;
  }

  public static long getCurrentSeconds()
  {
    return dateTransformBetweenTimeZone(new Date(), TimeZone.getDefault(), TimeZone.getTimeZone("GMT+8:00"));
  }

  public static Calendar getGTM8CalendarFromUtcms(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
    localCalendar.setTimeInMillis((28800L + paramLong) * 1000L);
    return localCalendar;
  }

  public static int getGTM8CurrentHour()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
    localCalendar.setTimeInMillis(System.currentTimeMillis() + 28800000L);
    return localCalendar.get(11);
  }

  public static String getMonthDay(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(1000L * paramLong);
    return localCalendar.get(2) + 1 + "月" + localCalendar.get(5) + "日";
  }

  public static boolean isDifferentDayMs(long paramLong1, long paramLong2)
  {
    boolean bool2 = true;
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
    localCalendar.setTimeInMillis(paramLong1);
    int i = localCalendar.get(1);
    int j = localCalendar.get(2);
    int k = localCalendar.get(5);
    localCalendar.get(11);
    localCalendar.setTimeInMillis(paramLong2);
    int m = localCalendar.get(1);
    int n = localCalendar.get(2);
    int i1 = localCalendar.get(5);
    localCalendar.get(11);
    boolean bool1 = bool2;
    if (i == m)
    {
      bool1 = bool2;
      if (j == n)
      {
        bool1 = bool2;
        if (k == i1)
          bool1 = false;
      }
    }
    return bool1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.DateUtil
 * JD-Core Version:    0.6.2
 */