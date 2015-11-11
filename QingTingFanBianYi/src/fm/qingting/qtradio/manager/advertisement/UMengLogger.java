package fm.qingting.qtradio.manager.advertisement;

import android.content.Context;
import com.umeng.analytics.MobclickAgent;

public class UMengLogger
{
  public static void sendmessage(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      MobclickAgent.onEvent(paramContext, paramString1, paramString2);
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  public static void sendmessage(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    try
    {
      MobclickAgent.onEvent(paramContext, paramString1, paramString2, paramInt);
      return;
    }
    catch (Exception paramContext)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.manager.advertisement.UMengLogger
 * JD-Core Version:    0.6.2
 */