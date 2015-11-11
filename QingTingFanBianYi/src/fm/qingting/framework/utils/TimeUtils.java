package fm.qingting.framework.utils;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils
{
  public static String dateToString(Date paramDate)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(paramDate);
  }

  public static String dateToString(Date paramDate, int paramInt)
  {
    return new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a").format(paramDate);
  }

  public static String dateToStringI(long paramLong, DateFormat paramDateFormat)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    return paramDateFormat.format(localCalendar.getTime());
  }

  public static String getDay()
  {
    return new SimpleDateFormat("d").format(new Date());
  }

  public static String getHour()
  {
    return new SimpleDateFormat("H").format(new Date());
  }

  public static String getMonth()
  {
    return new SimpleDateFormat("M").format(new Date());
  }

  public static String getTimeLeft(long paramLong)
  {
    int i = (int)((Calendar.getInstance().getTimeInMillis() - paramLong) / 1000L);
    if (i < 0)
      return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(paramLong));
    if (i < 60)
      return String.format("%d 秒前", new Object[] { Integer.valueOf(i) });
    int j = 60 * 60;
    if (i < j)
      return String.format("%d 分钟前", new Object[] { Integer.valueOf(i / 60) });
    j *= 24;
    if (i < j)
      return String.format("%d 小时前", new Object[] { Integer.valueOf(i / 3600) });
    j *= 7;
    if (i < j)
      return String.format("%d 天前", new Object[] { Integer.valueOf(i / 86400) });
    j *= 4;
    if (i < j)
      return String.format("%d 周前", new Object[] { Integer.valueOf(i / 604800) });
    if (i < j * 13)
      return String.format("%d 个月之前", new Object[] { Integer.valueOf(i / 2419200) });
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(paramLong));
  }

  public static String getWeek()
  {
    return new SimpleDateFormat("E").format(new Date());
  }

  public static String getYYYY_MM_DD()
  {
    return dateToString(new Date()).substring(0, 10);
  }

  public static String getYYYY_MM_DD(String paramString)
  {
    return paramString.substring(0, 10);
  }

  public static String getYear()
  {
    return new SimpleDateFormat("yyyy").format(new Date());
  }

  public static String now()
  {
    return dateToString(new Date());
  }

  public static String now(int paramInt)
  {
    return dateToString(new Date(), paramInt);
  }

  public static Date stringToDate(String paramString)
  {
    int i = paramString.indexOf("AD");
    String str2 = paramString.trim();
    paramString = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
    String str1 = str2;
    if (i > -1)
    {
      str1 = str2.substring(0, i) + "公元" + str2.substring("AD".length() + i);
      paramString = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
    }
    if ((str1.indexOf("-") > -1) && (str1.indexOf(" ") < 0))
      paramString = new SimpleDateFormat("yyyyMMddHHmmssZ");
    while (true)
    {
      return paramString.parse(str1, new ParsePosition(0));
      if ((str1.indexOf("/") > -1) && (str1.indexOf(" ") > -1))
        paramString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      else if ((str1.indexOf("-") > -1) && (str1.indexOf(" ") > -1))
        paramString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      else if (((str1.indexOf("/") > -1) && (str1.indexOf("am") > -1)) || (str1.indexOf("pm") > -1))
        paramString = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
      else if (((str1.indexOf("-") > -1) && (str1.indexOf("am") > -1)) || (str1.indexOf("pm") > -1))
        paramString = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.TimeUtils
 * JD-Core Version:    0.6.2
 */