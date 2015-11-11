package fm.qingting.qtradio;

import android.content.Context;
import com.intowow.sdk.I2WAPI;
import com.intowow.sdk.SplashAD;
import com.intowow.sdk.SplashAD.SplashAdListener;
import fm.qingting.utils.QTMSGManage;

public class CrystalHelper
{
  private static boolean mHasClosed = true;
  private static onCloseListener mListener;

  public static void close()
  {
    if (!mHasClosed)
    {
      mHasClosed = true;
      if (mListener != null)
        mListener.onClose();
      QTMSGManage.getInstance().sendStatistcsMessage("ADIClick", "onClosed");
    }
  }

  public static boolean hasClosed()
  {
    return mHasClosed;
  }

  public static void init(Context paramContext)
  {
    try
    {
      I2WAPI.init(paramContext);
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  public static void onPause(Context paramContext)
  {
    try
    {
      I2WAPI.onActivityPause(paramContext);
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  public static void onResume(Context paramContext)
  {
    try
    {
      I2WAPI.onActivityResume(paramContext);
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  public static void openSplashAd(Context paramContext)
  {
    try
    {
      QTMSGManage.getInstance().sendStatistcsMessage("ADIClick", "open");
      paramContext = I2WAPI.requesSingleOfferAD(paramContext, "OPEN_SPLASH", true);
      if (paramContext != null)
        paramContext.setListener(new SplashAD.SplashAdListener()
        {
          public void onClosed()
          {
            if (CrystalHelper.mListener != null)
              CrystalHelper.mListener.onClose();
            CrystalHelper.access$002(true);
            QTMSGManage.getInstance().sendStatistcsMessage("ADIClick", "onClosed");
          }

          public void onLoadFailed()
          {
            QTMSGManage.getInstance().sendStatistcsMessage("ADIClick", "onLoadFailed");
          }

          public void onLoaded()
          {
            CrystalHelper.access$002(false);
            this.val$ad.show(2130968579, 2130968578);
            QTMSGManage.getInstance().sendStatistcsMessage("ADIClick", "onshow");
          }
        });
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  public static void setListener(onCloseListener paramonCloseListener)
  {
    mListener = paramonCloseListener;
  }

  public static abstract interface onCloseListener
  {
    public abstract void onClose();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.CrystalHelper
 * JD-Core Version:    0.6.2
 */