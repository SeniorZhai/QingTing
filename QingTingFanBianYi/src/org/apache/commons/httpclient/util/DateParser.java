package org.apache.commons.httpclient.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

public class DateParser
{
  private static final Collection DEFAULT_PATTERNS = Arrays.asList(new String[] { "EEE MMM d HH:mm:ss yyyy", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE, dd MMM yyyy HH:mm:ss zzz" });
  public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";
  public static final String PATTERN_RFC1036 = "EEEE, dd-MMM-yy HH:mm:ss zzz";
  public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";

  public static Date parseDate(String paramString)
    throws DateParseException
  {
    return parseDate(paramString, null);
  }

  public static Date parseDate(String paramString, Collection paramCollection)
    throws DateParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("dateValue is null");
    Object localObject1 = paramCollection;
    if (paramCollection == null)
      localObject1 = DEFAULT_PATTERNS;
    paramCollection = paramString;
    if (paramString.length() > 1)
    {
      paramCollection = paramString;
      if (paramString.startsWith("'"))
      {
        paramCollection = paramString;
        if (paramString.endsWith("'"))
          paramCollection = paramString.substring(1, paramString.length() - 1);
      }
    }
    paramString = null;
    localObject1 = ((Collection)localObject1).iterator();
    while (true)
    {
      if (!((Iterator)localObject1).hasNext())
        throw new DateParseException("Unable to parse the date " + paramCollection);
      Object localObject2 = (String)((Iterator)localObject1).next();
      if (paramString == null)
      {
        paramString = new SimpleDateFormat((String)localObject2, Locale.US);
        paramString.setTimeZone(TimeZone.getTimeZone("GMT"));
      }
      try
      {
        while (true)
        {
          localObject2 = paramString.parse(paramCollection);
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
 * Qualified Name:     org.apache.commons.httpclient.util.DateParser
 * JD-Core Version:    0.6.2
 */