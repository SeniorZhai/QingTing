package fm.qingting.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class ProcessDetect
{
  public static int getProcessId(String paramString, Context paramContext)
  {
    if ((paramString == null) || (paramContext == null))
      return -1;
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
      int i = 0;
      while (i < paramContext.size())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.get(i);
        if (localRunningAppProcessInfo.processName.equalsIgnoreCase(paramString))
        {
          i = localRunningAppProcessInfo.pid;
          return i;
        }
        i += 1;
      }
    }
    catch (Exception paramString)
    {
    }
    return -1;
  }

  public static boolean processExists(String paramString1, String paramString2)
  {
    if (paramString1 == null);
    while (true)
    {
      return false;
      try
      {
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("top -n 1").getInputStream()));
        boolean bool;
        do
        {
          String str;
          do
          {
            str = localBufferedReader.readLine();
            if (str == null)
              break;
          }
          while (!str.contains(paramString1));
          if (paramString2 == null)
            break label65;
          bool = str.contains(paramString2);
        }
        while (bool);
        label65: return true;
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
      }
    }
    return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.ProcessDetect
 * JD-Core Version:    0.6.2
 */