package cn.com.mma.mobile.tracking.api;

import android.content.Context;
import cn.com.mma.mobile.tracking.util.LocationUtil;
import cn.com.mma.mobile.tracking.util.SdkConfigUpdateUtil;
import java.util.Timer;
import java.util.TimerTask;

public class Countly
{
  private static Timer failedTimer;
  private static Countly mCountly = new Countly();
  private static Timer nromalTimer;
  private RecordEventMessage recordEventMessage;
  private SendEventMessage sendEventMessage;

  public static Countly sharedInstance()
  {
    return mCountly;
  }

  public void clearAll()
  {
    try
    {
      if (this.sendEventMessage != null)
        this.sendEventMessage.clearAll();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void clearErrorList()
  {
    try
    {
      if (this.sendEventMessage != null)
        this.sendEventMessage.clearErrorList();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void init(final Context paramContext, final String paramString)
  {
    try
    {
      this.sendEventMessage = SendEventMessage.getSendEventMessage(paramContext);
      this.recordEventMessage = new RecordEventMessage(paramContext);
      nromalTimer = new Timer();
      failedTimer = new Timer();
      try
      {
        nromalTimer.schedule(new TimerTask()
        {
          public void run()
          {
            Countly.this.sendNormalMessage();
          }
        }
        , 10000L, 3600000L);
        failedTimer.schedule(new TimerTask()
        {
          public void run()
          {
            Countly.this.sendFailedMessage();
          }
        }
        , 10000L, 3600000L);
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              SdkConfigUpdateUtil.initSdkConfigResult(paramContext, paramString);
              return;
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
            }
          }
        }).start();
        return;
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  public void onClick(String paramString)
  {
    try
    {
      if (this.recordEventMessage != null)
        this.recordEventMessage.recordEventWithUrl(paramString);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }

  public void onExpose(String paramString)
  {
    try
    {
      if (this.recordEventMessage != null)
        this.recordEventMessage.recordEventWithUrl(paramString);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }

  public void sendFailedMessage()
  {
    try
    {
      if (this.sendEventMessage != null)
        this.sendEventMessage.sendFailedList();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void sendNormalMessage()
  {
    try
    {
      if (this.sendEventMessage != null)
        this.sendEventMessage.sendNromalList();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void setLogState(boolean paramBoolean)
  {
    cn.com.mma.mobile.tracking.util.Logger.DEBUG_LOG = paramBoolean;
  }

  public void stopCurrentSendThread()
  {
    try
    {
      if (this.sendEventMessage != null)
        this.sendEventMessage.stopThread();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void stopListenerLocation(Context paramContext)
  {
    try
    {
      LocationUtil.getInstance(paramContext).stopListenLocation();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  public void stopLoopTimer()
  {
    try
    {
      if (nromalTimer != null)
      {
        nromalTimer.cancel();
        nromalTimer.purge();
        nromalTimer = null;
      }
      if (failedTimer != null)
      {
        failedTimer.cancel();
        failedTimer.purge();
        failedTimer = null;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.mma.mobile.tracking.api.Countly
 * JD-Core Version:    0.6.2
 */