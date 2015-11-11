package fm.qingting.qtradio.model;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PickDataCenterV6 extends Thread
{
  private int MAX_TIME_OUT = 10000;
  public Map<String, List<PingInfoV6>> mapMediaCenter;
  public int pickCnt = 1;

  private double getRTTByUrl(String paramString1, String paramString2)
  {
    int j = 0;
    while (true)
    {
      int i;
      try
      {
        paramString1 = (HttpURLConnection)new URL(paramString1).openConnection();
        if (paramString1 == null)
          return 2147483647.0D;
        double d2 = System.currentTimeMillis();
        paramString1.setConnectTimeout(this.MAX_TIME_OUT);
        paramString1.setReadTimeout(this.MAX_TIME_OUT);
        paramString1.setRequestMethod("GET");
        paramString1.setDoInput(true);
        paramString1.connect();
        if (paramString1.getResponseCode() == 200)
        {
          int k = paramString1.getContentLength();
          InputStream localInputStream = paramString1.getInputStream();
          if (k >= 64)
            return 2147483647.0D;
          byte[] arrayOfByte = new byte[64];
          i = 0;
          int m = localInputStream.read(arrayOfByte);
          if (m == -1)
            break label214;
          i += m;
          if (i == k);
          try
          {
            paramString2 = paramString2.getBytes();
            i = j;
            if (i < paramString2.length)
            {
              if (paramString2[i] == arrayOfByte[i])
                break label221;
              return 2147483647.0D;
            }
            d1 = System.currentTimeMillis();
            localInputStream.close();
            paramString1.disconnect();
            return (d1 - d2) / 1000.0D;
            continue;
          }
          finally
          {
          }
        }
      }
      catch (Exception paramString1)
      {
        return 2147483647.0D;
      }
      return 2147483647.0D;
      label214: double d1 = 2147483647.0D;
      continue;
      label221: i += 1;
    }
  }

  private void pkMediaCenter(List<PingInfoV6> paramList)
  {
    if (paramList == null);
    while (true)
    {
      return;
      int i = 0;
      while (i < paramList.size())
      {
        String str = ((PingInfoV6)paramList.get(i)).getDomainIP();
        double d = 2147483647.0D;
        if (str != null)
          d = getRTTByUrl(((PingInfoV6)paramList.get(i)).getPKURL(str), ((PingInfoV6)paramList.get(i)).res);
        ((PingInfoV6)paramList.get(i)).setReachTime(d);
        ((PingInfoV6)paramList.get(i)).setPinged(true);
        i += 1;
      }
    }
  }

  public void run()
  {
    while (true)
    {
      int i;
      try
      {
        if (this.mapMediaCenter != null)
        {
          i = 0;
          if (i < this.pickCnt)
          {
            Iterator localIterator = this.mapMediaCenter.entrySet().iterator();
            if (!localIterator.hasNext())
              break label82;
            List localList = (List)((Map.Entry)localIterator.next()).getValue();
            if ((localList == null) || (localList.size() <= 0))
              continue;
            pkMediaCenter(localList);
            continue;
          }
        }
      }
      catch (Exception localException)
      {
      }
      return;
      label82: i += 1;
    }
  }

  public void setDataCenterInfo(Map<String, List<PingInfoV6>> paramMap)
  {
    this.mapMediaCenter = paramMap;
  }

  class PingInfoV6Comparator
    implements Comparator<PingInfoV6>
  {
    PingInfoV6Comparator()
    {
    }

    public int compare(PingInfoV6 paramPingInfoV61, PingInfoV6 paramPingInfoV62)
    {
      if ((paramPingInfoV61 == null) || (paramPingInfoV62 == null));
      double d1;
      double d2;
      do
      {
        return -1;
        d1 = paramPingInfoV61.getReachTime();
        d2 = paramPingInfoV62.getReachTime();
      }
      while (d1 < d2);
      if (d1 > d2)
        return 1;
      return 0;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.PickDataCenterV6
 * JD-Core Version:    0.6.2
 */