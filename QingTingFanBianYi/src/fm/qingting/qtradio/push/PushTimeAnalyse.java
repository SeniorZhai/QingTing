package fm.qingting.qtradio.push;

import android.util.Log;
import fm.qingting.qtradio.stat.PlayRecord;
import fm.qingting.utils.DateUtil;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class PushTimeAnalyse
{
  public static int getMostFrequentListenHour(List<PlayRecord> paramList, int paramInt)
  {
    int j = -1;
    int m = 0;
    int k = j;
    if (paramList != null)
    {
      if (paramList.size() <= 0)
        k = j;
    }
    else
      return k;
    long[] arrayOfLong = new long[24];
    int i = 0;
    while (i < arrayOfLong.length)
    {
      arrayOfLong[i] = 0L;
      i += 1;
    }
    paramList = paramList.iterator();
    label66: i = m;
    long l2;
    long l1;
    if (paramList.hasNext())
    {
      PlayRecord localPlayRecord = (PlayRecord)paramList.next();
      l2 = localPlayRecord.duration;
      l1 = localPlayRecord.time - l2;
      i = DateUtil.getGTM8CalendarFromUtcms(l1).get(11);
      l1 = 3600L - l1 % 3600L;
    }
    while (true)
      if (l2 >= l1)
      {
        arrayOfLong[i] += l1;
        l2 -= l1;
        i += 1;
        if (i >= 24)
        {
          i = 0;
          l1 = 3600L;
        }
      }
      else
      {
        arrayOfLong[i] += l2;
        break label66;
        while (i < arrayOfLong.length)
        {
          log(i + ":" + arrayOfLong[i]);
          i += 1;
        }
        log("from hour:" + paramInt);
        l1 = -1L;
        i = j;
        while (true)
        {
          k = i;
          if (paramInt >= arrayOfLong.length)
            break;
          l2 = l1;
          if (arrayOfLong[paramInt] > l1)
          {
            l2 = arrayOfLong[paramInt];
            i = paramInt;
          }
          paramInt += 1;
          l1 = l2;
        }
        l1 = 3600L;
      }
  }

  private static void log(String paramString)
  {
    Log.i("PushTimeAnalyse", paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.push.PushTimeAnalyse
 * JD-Core Version:    0.6.2
 */