package fm.qingting.utils;

import android.content.Context;
import com.tendcloud.tenddata.TCAgent;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.qtradio.model.SharedCfg;
import java.util.Calendar;

public class QTMSGManage
{
  private static QTMSGManage instance = null;
  private Context contextRef = null;

  private Context getContext()
  {
    return this.contextRef;
  }

  public static QTMSGManage getInstance()
  {
    if (instance == null)
      instance = new QTMSGManage();
    return instance;
  }

  public void initContext(Context paramContext)
  {
    this.contextRef = paramContext;
  }

  public boolean isSameDay(String paramString)
  {
    int i = Calendar.getInstance().get(6);
    if (i != SharedCfg.getInstance().getLastDay_sendMessage(paramString))
    {
      SharedCfg.getInstance().setLastDay_sendMessage(paramString, i);
      return false;
    }
    return true;
  }

  public void sendStatistcsMessage(String paramString)
  {
    MobclickAgent.onEvent(getContext(), paramString);
    TCAgent.onEvent(getContext(), paramString);
  }

  public void sendStatistcsMessage(String paramString, Long paramLong)
  {
    MobclickAgent.onEventDuration(getContext(), paramString, paramLong.longValue());
  }

  public void sendStatistcsMessage(String paramString1, String paramString2)
  {
    if (paramString2 == null)
      return;
    MobclickAgent.onEvent(getContext(), paramString1, paramString2);
    TCAgent.onEvent(getContext(), paramString1, paramString2);
  }

  public void sendStatistcsMessage(String paramString1, String paramString2, Long paramLong)
  {
    MobclickAgent.onEventDuration(getContext(), paramString1, paramString2, paramLong.longValue());
  }

  public void sendStatisticsMessageOnceADay(String paramString)
  {
    if (!isSameDay(paramString))
      MobclickAgent.onEvent(getContext(), paramString);
  }

  public void sendStatisticsMessageOnceADay(String paramString1, String paramString2)
  {
    if (!isSameDay(paramString1 + paramString2))
      MobclickAgent.onEvent(getContext(), paramString1, paramString2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.QTMSGManage
 * JD-Core Version:    0.6.2
 */