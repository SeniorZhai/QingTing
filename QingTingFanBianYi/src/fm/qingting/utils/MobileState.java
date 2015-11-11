package fm.qingting.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.FileReader;

public class MobileState
{
  private static MobileState instance;

  private MobileState(Context paramContext)
  {
  }

  public static MobileState getInstance(Context paramContext)
  {
    if (instance == null)
      instance = new MobileState(paramContext);
    return instance;
  }

  public static int getNetWorkType(Context paramContext)
  {
    int i = 5;
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    int j;
    while (true)
    {
      try
      {
        paramContext = paramContext.getActiveNetworkInfo();
        if (paramContext == null)
        {
          i = -1;
          return i;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        paramContext = null;
        continue;
        j = paramContext.getType();
        if (j != 0)
          break label150;
      }
      if (paramContext.getExtraInfo() != null)
        if (paramContext.getExtraInfo().toLowerCase().equals("cmnet"))
          i = 3;
    }
    while (true)
    {
      return i;
      if (paramContext.getExtraInfo().toLowerCase().equals("3gnet"))
      {
        i = 2;
      }
      else if (paramContext.getExtraInfo().toLowerCase().equals("ctnet"))
      {
        i = 2;
      }
      else if (paramContext.getExtraInfo().toLowerCase().equals("ctwap"))
      {
        i = 3;
      }
      else if (paramContext.getExtraInfo().toLowerCase().equals("cmwap"))
      {
        i = 3;
        continue;
        label150: if (j == 1)
          i = 1;
      }
      else
      {
        i = 5;
      }
    }
  }

  public static boolean isCMNETOR3GNET(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.getType() == 0) && ((paramContext.getExtraInfo().toLowerCase().equals("cmnet")) || (paramContext.getExtraInfo().toLowerCase().equals("3gnet")));
  }

  public static boolean isNetWorkEnable(Context paramContext)
  {
    return ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo() != null;
  }

  public int getheadsetState()
  {
    try
    {
      FileReader localFileReader = new FileReader("/sys/class/switch/h2w/state");
      if (localFileReader == null)
        return 0;
      char[] arrayOfChar = new char[1024];
      int i = Integer.valueOf(new String(arrayOfChar, 0, localFileReader.read(arrayOfChar, 0, 1024)).trim()).intValue();
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }

  public boolean isMobilEnable(Context paramContext)
  {
    return ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(0) != null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.MobileState
 * JD-Core Version:    0.6.2
 */