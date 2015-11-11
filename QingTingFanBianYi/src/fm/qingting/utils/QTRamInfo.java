package fm.qingting.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class QTRamInfo
{
  public static int getAvailableRam(Context paramContext)
  {
    try
    {
      paramContext = (ActivityManager)paramContext.getSystemService("activity");
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      paramContext.getMemoryInfo(localMemoryInfo);
      long l = localMemoryInfo.availMem / 1024L / 1024L;
      return (int)l;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return -1;
  }

  public static int getTotalRam()
  {
    long l3 = 0L;
    long l1 = l3;
    long l2 = l3;
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
      l1 = l3;
      l2 = l3;
      l3 = Integer.valueOf(localBufferedReader.readLine().split("\\s+")[1]).intValue() * 1024;
      l1 = l3;
      l2 = l3;
      localBufferedReader.close();
      l1 = l3;
      label72: return (int)(l1 / 1024L / 1024L);
    }
    catch (Exception localException)
    {
      break label72;
    }
    catch (IOException localIOException)
    {
      while (true)
        l1 = l2;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.QTRamInfo
 * JD-Core Version:    0.6.2
 */