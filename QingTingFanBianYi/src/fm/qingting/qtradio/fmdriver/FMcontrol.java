package fm.qingting.qtradio.fmdriver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;

public class FMcontrol
{
  public static final String Code_PHONE_STATE = "android.intent.action.PHONE_STATE";
  public static FMcontrol instance;
  public MobileRec headsetPlugReceiver;
  public HashSet<WeakReference<IFMControlEventListener>> listeners = new HashSet();
  private Context mContext;
  public boolean mHeadsetState;
  private TelephonyManager teleManager = null;

  public static FMcontrol getInstance()
  {
    if (instance == null)
      instance = new FMcontrol();
    return instance;
  }

  public void addListener(IFMControlEventListener paramIFMControlEventListener)
  {
    Iterator localIterator = this.listeners.iterator();
    do
      if (!localIterator.hasNext())
      {
        this.listeners.add(new WeakReference(paramIFMControlEventListener));
        return;
      }
    while (((WeakReference)localIterator.next()).get() != paramIFMControlEventListener);
  }

  public void dispatchPluggedEvent()
  {
    removeUnavailableListener();
    Iterator localIterator = new HashSet(this.listeners).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      IFMControlEventListener localIFMControlEventListener = (IFMControlEventListener)((WeakReference)localIterator.next()).get();
      if (localIFMControlEventListener != null)
        localIFMControlEventListener.onHeadsetPlugged();
    }
  }

  public void dispatchStateEvent(boolean paramBoolean)
  {
    removeUnavailableListener();
    Iterator localIterator = new HashSet(this.listeners).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      IFMControlEventListener localIFMControlEventListener = (IFMControlEventListener)((WeakReference)localIterator.next()).get();
      if (localIFMControlEventListener != null)
        localIFMControlEventListener.onMobilesState(paramBoolean);
    }
  }

  public void dispatchUnpluggedEvent()
  {
    removeUnavailableListener();
    Iterator localIterator = new HashSet(this.listeners).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      IFMControlEventListener localIFMControlEventListener = (IFMControlEventListener)((WeakReference)localIterator.next()).get();
      if (localIFMControlEventListener != null)
        localIFMControlEventListener.onHeadsetUnplugged();
    }
  }

  public boolean isHeadsetConnected()
  {
    return this.mHeadsetState;
  }

  public void registerHeadsetPlugReceiver(Context paramContext)
  {
    try
    {
      if (this.headsetPlugReceiver != null)
        return;
      this.headsetPlugReceiver = new MobileRec();
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.HEADSET_PLUG");
      localIntentFilter.addAction("android.intent.action.PHONE_STATE");
      paramContext.registerReceiver(this.headsetPlugReceiver, localIntentFilter);
      this.mContext = paramContext;
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  public void registerPhoneState(Context paramContext)
  {
    if (paramContext == null)
      return;
    this.mContext = paramContext;
    this.mHeadsetState = ((AudioManager)this.mContext.getSystemService("audio")).isWiredHeadsetOn();
    try
    {
      this.teleManager = ((TelephonyManager)this.mContext.getSystemService("phone"));
      this.teleManager.listen(new MyPhoneStateListener(), 32);
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  public void removeAllListener()
  {
    Iterator localIterator = this.listeners.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      localIterator.remove();
    }
  }

  public void removeListener(IFMControlEventListener paramIFMControlEventListener)
  {
    Iterator localIterator = this.listeners.iterator();
    do
      if (!localIterator.hasNext())
        return;
    while (((WeakReference)localIterator.next()).get() != paramIFMControlEventListener);
    localIterator.remove();
  }

  public void removeUnavailableListener()
  {
    Iterator localIterator = this.listeners.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      if ((IFMControlEventListener)((WeakReference)localIterator.next()).get() == null)
        localIterator.remove();
    }
  }

  public void unregisterHeadsetPlugReceiver(Context paramContext)
  {
    if (this.headsetPlugReceiver != null);
    try
    {
      paramContext.unregisterReceiver(this.headsetPlugReceiver);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    finally
    {
      this.headsetPlugReceiver = null;
    }
    throw paramContext;
  }

  public class MobileRec extends BroadcastReceiver
  {
    public MobileRec()
    {
    }

    public void doReceivePhone(Context paramContext, Intent paramIntent)
    {
      switch (((TelephonyManager)paramContext.getSystemService("phone")).getCallState())
      {
      default:
        return;
      case 1:
        FMcontrol.this.dispatchStateEvent(true);
        return;
      case 0:
      }
      FMcontrol.this.dispatchStateEvent(false);
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      boolean bool = true;
      paramContext = paramIntent.getAction();
      if (paramContext.equals("android.intent.action.HEADSET_PLUG"))
      {
        int i = paramIntent.getIntExtra("state", 0);
        paramContext = FMcontrol.this;
        if (i == 1);
        while (true)
        {
          paramContext.mHeadsetState = bool;
          if (!FMcontrol.this.isHeadsetConnected())
            break;
          FMcontrol.this.dispatchPluggedEvent();
          return;
          bool = false;
        }
        FMcontrol.this.dispatchUnpluggedEvent();
        return;
      }
      paramContext.equals("android.intent.action.PHONE_STATE");
    }
  }

  class MyPhoneStateListener extends PhoneStateListener
  {
    MyPhoneStateListener()
    {
    }

    public void onCallStateChanged(int paramInt, String paramString)
    {
      switch (paramInt)
      {
      case 2:
      default:
      case 0:
      case 1:
      }
      while (true)
      {
        super.onCallStateChanged(paramInt, paramString);
        return;
        FMcontrol.this.dispatchStateEvent(false);
        continue;
        FMcontrol.this.dispatchStateEvent(true);
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.fmdriver.FMcontrol
 * JD-Core Version:    0.6.2
 */