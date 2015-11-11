package fm.qingting.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

public class TimeUtil
{
  private static final String JUST = "刚刚更新";
  private static final String MODEL_HOUR = "%d小时前";
  private static final String MODEL_MINUTE = "%d分钟前";
  private static final long ONEHOUR = 3600000L;
  private static final long TENMINUTE = 600000L;
  private static final long THREEHOUR = 10800000L;
  private static final String TODAY = "今天";

  public static int absoluteTimeToRelative(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.clear();
    localCalendar.setTimeInMillis(1000L * paramLong);
    return localCalendar.get(11) * 3600 + localCalendar.get(12) * 60;
  }

  public static long dateToMS(String paramString)
  {
    if (paramString != null)
      try
      {
        if (paramString.length() < 2)
          return 0L;
        long l = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(paramString).getTime();
        return l;
      }
      catch (Exception paramString)
      {
      }
    return 0L;
  }

  public static long dateToMs(String paramString1, String paramString2)
  {
    try
    {
      long l = new SimpleDateFormat(paramString2, Locale.US).parse(paramString1).getTime();
      return l;
    }
    catch (Exception paramString1)
    {
    }
    return 0L;
  }

  public static String getDayofMonth(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(1000L * paramLong);
    return String.valueOf(localCalendar.get(5));
  }

  public static int getDayofWeek(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(1000L * paramLong);
    return localCalendar.get(7);
  }

  public static int getDayofYear(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(1000L * paramLong);
    return localCalendar.get(6);
  }

  public static String getHour(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(1000L * paramLong);
    return String.format("%02d", new Object[] { Integer.valueOf(localCalendar.get(11)) });
  }

  public static int getHourOfDay(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(1000L * paramLong);
    return localCalendar.get(11);
  }

  public static String getMinute(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(1000L * paramLong);
    return String.format("%02d", new Object[] { Integer.valueOf(localCalendar.get(12)) });
  }

  public static String getMonth(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(1000L * paramLong);
    return String.valueOf(localCalendar.get(2) + 1);
  }

  public static String getReadableTime(long paramLong)
  {
    if (paramLong == 0L)
      return "刚刚更新";
    long l = System.currentTimeMillis();
    Calendar localCalendar = Calendar.getInstance();
    int i = localCalendar.get(1);
    int j = localCalendar.get(6);
    localCalendar.setTimeInMillis(paramLong);
    int k = localCalendar.get(1);
    int m = localCalendar.get(6);
    if ((i == k) && (j == m));
    for (i = 1; ; i = 0)
    {
      if (i != 0)
      {
        paramLong = l - paramLong;
        if (paramLong < 600000L)
          return "刚刚更新";
        if (paramLong < 3600000L)
          return String.format(Locale.CHINESE, "%d分钟前", new Object[] { Integer.valueOf((int)(paramLong / 1000L / 60L)) });
        if (paramLong < 10800000L)
          return String.format(Locale.CHINESE, "%d小时前", new Object[] { Integer.valueOf((int)(paramLong / 1000L / 60L / 60L)) });
        return "今天";
      }
      return msToDate5(paramLong);
    }
  }

  public static String getYear(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(1000L * paramLong);
    return new SimpleDateFormat("yy", Locale.CHINESE).format(localCalendar.getTime());
  }

  public static boolean isSundayOrSaturday()
  {
    int i = Calendar.getInstance().get(7);
    return (i == 7) || (i == 1);
  }

  public static String mSecToTime1(long paramLong)
  {
    int k = (int)Math.ceil((float)paramLong / 1000.0F);
    int i = k / 3600;
    int j = k / 60 % 60;
    k %= 60;
    if (i == 0)
    {
      if (k == 0)
        return String.format(Locale.CHINA, "%d分", new Object[] { Integer.valueOf(j) });
      if (j == 0)
        return String.format(Locale.CHINA, "%d秒", new Object[] { Integer.valueOf(k) });
      return String.format(Locale.CHINA, "%d分%d秒", new Object[] { Integer.valueOf(j), Integer.valueOf(k) });
    }
    return String.format(Locale.CHINA, "%d小时%d分", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
  }

  public static String mSecToTime2(long paramLong)
  {
    int k = (int)Math.ceil((float)paramLong / 1000.0F);
    int i = k / 3600;
    int j = k / 60 % 60;
    k %= 60;
    if (i == 0)
      return String.format(Locale.CHINA, "%02d:%02d", new Object[] { Integer.valueOf(j), Integer.valueOf(k) });
    return String.format(Locale.CHINA, "%02d:%02d:%02d", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k) });
  }

  public static String msToDate(long paramLong)
  {
    java.sql.Date localDate = new java.sql.Date(paramLong);
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(localDate);
  }

  public static String msToDate10(long paramLong)
  {
    java.sql.Date localDate = new java.sql.Date(paramLong);
    return new SimpleDateFormat("yyMMdd_HHmmss", Locale.US).format(localDate);
  }

  public static String msToDate2(long paramLong)
  {
    java.sql.Date localDate = new java.sql.Date(paramLong);
    return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(localDate);
  }

  public static String msToDate3(long paramLong)
  {
    java.sql.Date localDate = new java.sql.Date(paramLong);
    return new SimpleDateFormat("HH:mm", Locale.US).format(localDate);
  }

  public static String msToDate4(long paramLong)
  {
    java.sql.Date localDate = new java.sql.Date(paramLong);
    return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US).format(localDate);
  }

  public static String msToDate5(long paramLong)
  {
    java.sql.Date localDate = new java.sql.Date(paramLong);
    return new SimpleDateFormat("MM月dd日", Locale.CHINA).format(localDate);
  }

  public static String msToDate6(long paramLong)
  {
    java.sql.Date localDate = new java.sql.Date(paramLong);
    return new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format(localDate);
  }

  public static String msToDate7(long paramLong)
  {
    java.sql.Date localDate = new java.sql.Date(paramLong);
    return new SimpleDateFormat("yyyyMMdd", Locale.US).format(localDate);
  }

  public static String msToDate8(long paramLong)
  {
    java.sql.Date localDate = new java.sql.Date(paramLong);
    return new SimpleDateFormat("HHmmss", Locale.US).format(localDate);
  }

  public static String msToDate9(long paramLong)
  {
    java.sql.Date localDate = new java.sql.Date(paramLong);
    return new SimpleDateFormat("yyyy.MM.dd", Locale.US).format(localDate);
  }

  public static int relativeTimeToSec(String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return 0;
      try
      {
        paramString = Pattern.compile("\\D+").split(paramString);
        if (paramString.length == 2)
        {
          int i = Integer.parseInt(paramString[0]);
          int j = Integer.parseInt(paramString[1]);
          return j * 60 + i * 3600;
        }
      }
      catch (Exception paramString)
      {
      }
    }
    return 0;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.TimeUtil
 * JD-Core Version:    0.6.2
 */