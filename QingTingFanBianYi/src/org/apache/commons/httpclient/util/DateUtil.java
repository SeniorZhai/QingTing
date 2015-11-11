package org.apache.commons.httpclient.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil
{
  private static final Collection DEFAULT_PATTERNS = Arrays.asList(new String[] { "EEE MMM d HH:mm:ss yyyy", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE, dd MMM yyyy HH:mm:ss zzz" });
  private static final Date DEFAULT_TWO_DIGIT_YEAR_START = localCalendar.getTime();
  private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
  public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";
  public static final String PATTERN_RFC1036 = "EEEE, dd-MMM-yy HH:mm:ss zzz";
  public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";

  static
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(2000, 0, 1, 0, 0);
  }

  public static String formatDate(Date paramDate)
  {
    return formatDate(paramDate, "EEE, dd MMM yyyy HH:mm:ss zzz");
  }

  public static String formatDate(Date paramDate, String paramString)
  {
    if (paramDate == null)
      throw new IllegalArgumentException("date is null");
    if (paramString == null)
      throw new IllegalArgumentException("pattern is null");
    paramString = new SimpleDateFormat(paramString, Locale.US);
    paramString.setTimeZone(GMT);
    return paramString.format(paramDate);
  }

  public static Date parseDate(String paramString)
    throws DateParseException
  {
    return parseDate(paramString, null, null);
  }

  public static Date parseDate(String paramString, Collection paramCollection)
    throws DateParseException
  {
    return parseDate(paramString, paramCollection, null);
  }

  public static Date parseDate(String paramString, Collection paramCollection, Date paramDate)
    throws DateParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("dateValue is null");
    Object localObject1 = paramCollection;
    if (paramCollection == null)
      localObject1 = DEFAULT_PATTERNS;
    paramCollection = paramDate;
    if (paramDate == null)
      paramCollection = DEFAULT_TWO_DIGIT_YEAR_START;
    paramDate = paramString;
    if (paramString.length() > 1)
    {
      paramDate = paramString;
      if (paramString.startsWith("'"))
      {
        paramDate = paramString;
        if (paramString.endsWith("'"))
          paramDate = paramString.substring(1, paramString.length() - 1);
      }
    }
    paramString = null;
    localObject1 = ((Collection)localObject1).iterator();
    while (true)
    {
      if (!((Iterator)localObject1).hasNext())
        throw new DateParseException("Unable to parse the date " + paramDate);
      Object localObject2 = (String)((Iterator)localObject1).next();
      if (paramString == null)
      {
        paramString = new SimpleDateFormat((String)localObject2, Locale.US);
        paramString.setTimeZone(TimeZone.getTimeZone("GMT"));
        paramString.set2DigitYearStart(paramCollection);
      }
      try
      {
        while (true)
        {
          localObject2 = paramString.parse(paramDate);
          return localObject2;
          paramString.applyPattern((String)localObject2);
        }
      }
      catch (ParseException localParseException)
      {
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.DateUtil
 * JD-Core Version:    0.6.2
 */