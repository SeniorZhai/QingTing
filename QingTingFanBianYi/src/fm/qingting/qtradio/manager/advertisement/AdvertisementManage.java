package fm.qingting.qtradio.manager.advertisement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import fm.qingting.qtradio.model.advertisement.QTAdvertisementInfo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class AdvertisementManage
{
  private static QTAdvertisementInfo _qtAdvertisementInfo;
  private static AdvertisementManage instance;
  private AdvertisementMRec adReceiver;
  private List<QTAdvertisementInfo> adinfolist = new ArrayList();
  public String currentADKey = "";
  public boolean isNeedDisPlayAD = true;
  private HashSet<WeakReference<IADEventListener>> listeners = new HashSet();
  private Context mContext;

  public static AdvertisementManage getInstance()
  {
    if (instance == null)
      instance = new AdvertisementManage();
    return instance;
  }

  public static QTAdvertisementInfo get_qtAdvertisementInfo()
  {
    return _qtAdvertisementInfo;
  }

  public static void set_qtAdvertisementInfo(QTAdvertisementInfo paramQTAdvertisementInfo)
  {
    _qtAdvertisementInfo = paramQTAdvertisementInfo;
  }

  public void addInfoList(List<QTAdvertisementInfo> paramList)
  {
    try
    {
      this.adinfolist.clear();
      if ((this.adinfolist != null) && (paramList != null) && (paramList.size() > 0))
      {
        paramList = paramList.iterator();
        while (paramList.hasNext())
        {
          QTAdvertisementInfo localQTAdvertisementInfo = (QTAdvertisementInfo)paramList.next();
          this.adinfolist.add(localQTAdvertisementInfo);
        }
      }
    }
    catch (Exception paramList)
    {
    }
  }

  public void addListener(IADEventListener paramIADEventListener)
  {
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
      if (((WeakReference)localIterator.next()).get() == paramIADEventListener)
        return;
    this.listeners.add(new WeakReference(paramIADEventListener));
  }

  public void dispatchADFoundEvent(String paramString)
  {
    removeUnavailableListener();
    Iterator localIterator = new HashSet(this.listeners).iterator();
    while (localIterator.hasNext())
    {
      IADEventListener localIADEventListener = (IADEventListener)((WeakReference)localIterator.next()).get();
      if (localIADEventListener != null)
        localIADEventListener.onAdvertiseFoundEvent(paramString);
    }
  }

  public QTAdvertisementInfo getCurrentAdvertisementInfo()
  {
    if ((this.currentADKey != "") && (this.currentADKey != null) && (this.adinfolist.size() > 0))
    {
      Iterator localIterator = this.adinfolist.iterator();
      while (localIterator.hasNext())
      {
        QTAdvertisementInfo localQTAdvertisementInfo = (QTAdvertisementInfo)localIterator.next();
        if (localQTAdvertisementInfo.adID.equalsIgnoreCase(this.currentADKey))
          return localQTAdvertisementInfo;
      }
    }
    return null;
  }

  public void registerADReceiver(Context paramContext)
  {
    try
    {
      if (this.adReceiver != null)
        return;
      this.adReceiver = new AdvertisementMRec();
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("fm.qingting.radio.qt_ad_appear");
      paramContext.registerReceiver(this.adReceiver, localIntentFilter);
      this.mContext = paramContext;
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  public void removeListener(IADEventListener paramIADEventListener)
  {
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
      if (((WeakReference)localIterator.next()).get() == paramIADEventListener)
        localIterator.remove();
  }

  public void removeUnavailableListener()
  {
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
      if ((IADEventListener)((WeakReference)localIterator.next()).get() == null)
        localIterator.remove();
  }

  public void setDisplayAD(boolean paramBoolean)
  {
    this.isNeedDisPlayAD = paramBoolean;
  }

  public void unregisterADReceiver(Context paramContext)
  {
    if ((this.adReceiver != null) && (this.mContext != null))
      try
      {
        this.mContext.unregisterReceiver(this.adReceiver);
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      finally
      {
        this.adReceiver = null;
      }
    this.adReceiver = null;
  }

  class AdvertisementMRec extends BroadcastReceiver
  {
    AdvertisementMRec()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (!AdvertisementManage.this.isNeedDisPlayAD);
      do
      {
        return;
        paramContext = paramIntent.getAction();
      }
      while ((paramContext == null) || (!paramContext.equalsIgnoreCase("fm.qingting.radio.qt_ad_appear")));
      AdvertisementManage.this.currentADKey = paramIntent.getStringExtra("T_QT_ID");
      AdvertisementManage.this.dispatchADFoundEvent(AdvertisementManage.this.currentADKey);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.manager.advertisement.AdvertisementManage
 * JD-Core Version:    0.6.2
 */