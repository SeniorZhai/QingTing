package fm.qingting.qtradio.zte;

import android.content.Context;
import android.media.IFMSystemService;
import android.media.IFMSystemService.Stub;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import fm.qingting.qtradio.fmdriver.FMDriver;
import fm.qingting.qtradio.fmdriver.IFMEventListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ZteFM extends FMDriver
{
  private static final double FREQUENCY_BAND_MAX = 108001.0D;
  private static final String TAG = "ZteFM";
  public static final String ZTE_PACKAGE_NAME = "zte.com.cn.fmradio";
  private Method AcquireMethod;
  private Method SearchNextStationMethod;
  private Method SearchPrevStationMethod;
  private Class ServiceManagerClass;
  private Method SetMuteModeMethod;
  private Class amClass;
  private Object amObj;
  private Method asSetParameterMethod;
  private boolean available = false;
  private ArrayList<Integer> channels = new ArrayList();
  private int currentFreq = -1;
  private Method endSleepMethod;
  private Class fMControllerClass;
  IFMSystemService fmService;
  private Object fmServiceObject;
  private Method getFrequencyMethod;
  private Method getService;
  private Method getVolumMethod;
  private Object internalFMServiceObject;
  private Method isSleepActiveMethod;
  private Method isSleepTimerActiveMethod;
  private Context mContext;
  private int mDefaultVolum = 9;
  private final Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (ZteFM.this.onFMEventListener == null)
        return;
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 1:
        ZteFM.this.onFMEventListener.onScanComplete(true);
        return;
      case 2:
        ZteFM.this.onFMEventListener.onChannelFound(paramAnonymousMessage.arg1);
        return;
      case 3:
        ZteFM.this.onFMEventListener.onFMOff();
        return;
      case 4:
        ZteFM.this.onFMEventListener.onFMOn();
        return;
      case 5:
        ZteFM.this.onFMEventListener.onScanStarted();
        return;
      case 6:
      }
      ZteFM.this.onFMEventListener.onTune(paramAnonymousMessage.arg1);
    }
  };
  private boolean mIsFMEnable = false;
  private boolean mIsPlay = false;
  private boolean mIsScan = false;
  private IFMEventListener onFMEventListener;
  private Method radioDisableMethod;
  private Method radioEnableMethod;
  private Method releaseMethod;
  private Method setStationMethod;
  private Method setVolumMethod;
  private Method showFmNotificationMethod;
  private Method sleepMethod;

  public ZteFM(Context paramContext)
  {
    super(paramContext);
    getServiceManager();
    CheckFMRadioService();
    this.mContext = paramContext;
    initClass();
  }

  private void CheckFMRadioService()
  {
    try
    {
      this.internalFMServiceObject = this.getService.invoke(this.ServiceManagerClass, new Object[] { String.valueOf("fmradio") });
      if (this.internalFMServiceObject == null)
        return;
      this.fmService = IFMSystemService.Stub.asInterface((IBinder)this.internalFMServiceObject);
      if (this.fmService != null)
      {
        this.available = true;
        return;
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
  }

  private void apiAMsetParameter(String paramString1, String paramString2)
  {
    try
    {
      this.asSetParameterMethod.invoke(this.amObj, new Object[] { String.valueOf(paramString1), String.valueOf(paramString2) });
      return;
    }
    catch (IllegalArgumentException paramString1)
    {
      paramString1.printStackTrace();
      return;
    }
    catch (IllegalAccessException paramString1)
    {
      paramString1.printStackTrace();
      return;
    }
    catch (InvocationTargetException paramString1)
    {
      paramString1.printStackTrace();
    }
  }

  private void apiDisableAF()
  {
  }

  private void apiDisableRDS()
  {
  }

  private void apiEnableAF()
  {
  }

  private void apiEnableRDS()
  {
  }

  private long apiGetCurrentChannel()
  {
    double d1 = 0.0D;
    try
    {
      double d2 = this.fmService.getFrequency();
      d1 = d2;
      return ()d1 * 1000L;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        localRemoteException.printStackTrace();
    }
  }

  private long apiGetCurrentRSSI()
  {
    return -2147483648L;
  }

  private long[] apiGetLastScanResult()
  {
    return new long[0];
  }

  private boolean apiIsHeadsetPlugged()
  {
    return true;
  }

  private boolean apiIsOn()
  {
    return this.mIsFMEnable;
  }

  private boolean apiIsScanning()
  {
    return this.mIsScan;
  }

  private void apiOff()
  {
    Log.e("ZteFM", "in apiOff");
    try
    {
      this.fmService.radioDisable();
      apiAMsetParameter("FM", "off");
      this.mIsPlay = false;
      this.mIsFMEnable = false;
      Message localMessage = Message.obtain(this.mHandler, 3, null);
      this.mHandler.sendMessage(localMessage);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        localRemoteException.printStackTrace();
    }
  }

  private boolean apiOn()
  {
    int i = 0;
    if (!this.mIsFMEnable);
    try
    {
      boolean bool = this.fmService.Acquire("/dev/radio0");
      i = bool;
      this.fmService.radioEnable();
      i = bool;
      if (i != 0)
      {
        this.mIsFMEnable = true;
        Message localMessage = Message.obtain(this.mHandler, 4, null);
        this.mHandler.sendMessage(localMessage);
      }
      return this.mIsFMEnable;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        localRemoteException.printStackTrace();
    }
  }

  private void apiRemoveListener(Object paramObject)
  {
  }

  private double apiScanNext()
  {
    try
    {
      this.fmService.SearchNextStation();
      double d = this.fmService.getFrequency();
      return d;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
    return -1.0D;
  }

  private double apiScanPrev()
  {
    try
    {
      this.fmService.SearchPrevStation();
      double d = this.fmService.getFrequency();
      return d;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
    return -1.0D;
  }

  private long apiSeekDown()
  {
    return -9223372036854775808L;
  }

  private long apiSeekUp()
  {
    return -9223372036854775808L;
  }

  private void apiSetBand(int paramInt)
  {
  }

  private void apiSetChannelSpacing(int paramInt)
  {
  }

  private void apiSetDEConstant(long paramLong)
  {
  }

  private void apiSetListener(Object paramObject)
  {
  }

  private void apiSetSpeakerOn(boolean paramBoolean)
  {
  }

  private void apiStopScan()
  {
  }

  private void apiTune(long paramLong)
  {
  }

  private void apisetVolume(int paramInt)
  {
    try
    {
      this.fmService.setVolum(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
  }

  // ERROR //
  private void getServiceManager()
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 289
    //   4: invokestatic 295	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   7: putfield 115	fm/qingting/qtradio/zte/ZteFM:ServiceManagerClass	Ljava/lang/Class;
    //   10: aload_0
    //   11: aload_0
    //   12: getfield 115	fm/qingting/qtradio/zte/ZteFM:ServiceManagerClass	Ljava/lang/Class;
    //   15: ldc_w 296
    //   18: iconst_1
    //   19: anewarray 291	java/lang/Class
    //   22: dup
    //   23: iconst_0
    //   24: ldc 121
    //   26: aastore
    //   27: invokevirtual 300	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   30: putfield 113	fm/qingting/qtradio/zte/ZteFM:getService	Ljava/lang/reflect/Method;
    //   33: return
    //   34: astore_1
    //   35: return
    //   36: astore_1
    //   37: return
    //   38: astore_1
    //   39: aload_1
    //   40: invokevirtual 301	java/lang/NoSuchMethodException:printStackTrace	()V
    //   43: return
    //
    // Exception table:
    //   from	to	target	type
    //   0	10	34	java/lang/SecurityException
    //   0	10	36	java/lang/ClassNotFoundException
    //   10	33	38	java/lang/NoSuchMethodException
  }

  private boolean inChannelList(int paramInt)
  {
    int i = 0;
    while (true)
    {
      if (i >= this.channels.size())
        return false;
      if (((Integer)this.channels.get(i)).intValue() == paramInt)
        return true;
      i += 1;
    }
  }

  private void initClass()
  {
    try
    {
      this.fMControllerClass = Class.forName("android.media.IFMSystemService", true, this.mContext.getClassLoader());
      this.amObj = this.mContext.getSystemService("audio");
      this.amClass = this.amObj.getClass();
      setUpInnerMethods();
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      localClassNotFoundException.printStackTrace();
    }
  }

  private void setUpInnerMethods()
  {
    try
    {
      this.asSetParameterMethod = this.amClass.getMethod("setParameter", new Class[] { String.class, String.class });
      return;
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException.printStackTrace();
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
    }
  }

  private void showChannels()
  {
    int i = 0;
    while (true)
    {
      if (i >= this.channels.size())
        return;
      Log.e("ZteFM", "channels: " + this.channels.get(i));
      i += 1;
    }
  }

  public void cancelScan()
  {
    apiStopScan();
  }

  public void cancelScanning()
    throws Exception
  {
    cancelScan();
  }

  public ArrayList<Integer> getAvailableChannels()
  {
    return new ArrayList(this.channels);
  }

  public int getCurrentChannel()
  {
    return (int)apiGetCurrentChannel();
  }

  public int getCurrentRSSI()
  {
    return (int)apiGetCurrentRSSI();
  }

  public String getName()
  {
    return "ZteFM";
  }

  public int getVolume()
    throws Exception
  {
    return this.mDefaultVolum;
  }

  public boolean isAvailable()
  {
    return this.available;
  }

  public boolean isHeadsetPlugged()
  {
    return apiIsHeadsetPlugged();
  }

  public boolean isMute()
    throws Exception
  {
    return false;
  }

  public boolean isOn()
  {
    return apiIsOn();
  }

  public boolean isPaused()
    throws Exception
  {
    return !this.mIsPlay;
  }

  public boolean isScanning()
  {
    return apiIsScanning();
  }

  public boolean isSpeakerOn()
    throws Exception
  {
    return false;
  }

  public void mute(boolean paramBoolean)
    throws Exception
  {
  }

  public void off()
  {
    apiOff();
  }

  public int on()
  {
    if (apiOn())
      return 1;
    return 0;
  }

  public void pause()
    throws Exception
  {
    off();
  }

  public void registerFMEventListener(IFMEventListener paramIFMEventListener)
  {
    setListener(paramIFMEventListener);
  }

  public void scan()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        int j = 0;
        if (!ZteFM.this.mIsFMEnable);
        double d1;
        label158: Message localMessage2;
        label246: double d2;
        do
        {
          while (true)
          {
            try
            {
              boolean bool = ZteFM.this.fmService.Acquire("/dev/radio0");
              j = bool;
              if (j != 0)
              {
                ZteFM.this.mIsFMEnable = true;
                Message localMessage1 = Message.obtain(ZteFM.this.mHandler, 4, null);
                ZteFM.this.mHandler.sendMessage(localMessage1);
                ZteFM.this.mIsPlay = false;
                ZteFM.this.apiAMsetParameter("FM", "off");
                ZteFM.this.mIsScan = true;
                d1 = 0.0D;
                i = 1;
                ZteFM.this.channels.clear();
                switch (i)
                {
                default:
                  if ((-1.0D != d1) && (ZteFM.this.mIsScan))
                    break label246;
                  localMessage1 = Message.obtain(ZteFM.this.mHandler, 1, null);
                  ZteFM.this.mHandler.sendMessage(localMessage1);
                  return;
                case 1:
                case 2:
                }
              }
            }
            catch (RemoteException localRemoteException)
            {
              localRemoteException.printStackTrace();
              continue;
              localMessage2 = Message.obtain(ZteFM.this.mHandler, 1, null);
              ZteFM.this.mHandler.sendMessage(localMessage2);
              return;
            }
            d1 = ZteFM.this.apiScanNext();
            continue;
            d1 = ZteFM.this.apiScanPrev();
          }
          d2 = d1 * 1000.0D;
          if (!ZteFM.this.inChannelList((int)d2))
            break;
        }
        while (1 != i);
        int i = 2;
        while (true)
        {
          d1 = d2;
          if (d2 < 108001.0D)
            break;
          break label158;
          localMessage2 = Message.obtain(ZteFM.this.mHandler, 2, null);
          localMessage2.arg1 = ((int)d2);
          ZteFM.this.mHandler.sendMessage(localMessage2);
          ZteFM.this.channels.add(Integer.valueOf((int)d2));
        }
      }
    }).start();
  }

  public void setListener(IFMEventListener paramIFMEventListener)
  {
    this.onFMEventListener = paramIFMEventListener;
  }

  public void setLiveAudioQualityCallback(boolean paramBoolean, int paramInt)
    throws Exception
  {
  }

  public int setSpeakerOn(boolean paramBoolean)
  {
    apiSetSpeakerOn(paramBoolean);
    return 0;
  }

  public void setVolum(int paramInt)
  {
    try
    {
      this.fmService.setVolum(paramInt);
      this.mDefaultVolum = paramInt;
      return;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
  }

  public void setVolume(int paramInt)
    throws Exception
  {
    setVolum(paramInt);
  }

  // ERROR //
  public int tune(int paramInt)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: aload_0
    //   4: getfield 75	fm/qingting/qtradio/zte/ZteFM:mIsFMEnable	Z
    //   7: ifne +161 -> 168
    //   10: aload_0
    //   11: getfield 143	fm/qingting/qtradio/zte/ZteFM:fmService	Landroid/media/IFMSystemService;
    //   14: ldc 245
    //   16: invokeinterface 249 2 0
    //   21: istore 6
    //   23: iload 6
    //   25: istore 5
    //   27: aload_0
    //   28: getfield 143	fm/qingting/qtradio/zte/ZteFM:fmService	Landroid/media/IFMSystemService;
    //   31: invokeinterface 252 1 0
    //   36: iload 6
    //   38: istore 5
    //   40: iload 5
    //   42: istore 6
    //   44: iload 5
    //   46: ifeq +33 -> 79
    //   49: aload_0
    //   50: iconst_1
    //   51: putfield 75	fm/qingting/qtradio/zte/ZteFM:mIsFMEnable	Z
    //   54: aload_0
    //   55: getfield 93	fm/qingting/qtradio/zte/ZteFM:mHandler	Landroid/os/Handler;
    //   58: iconst_4
    //   59: aconst_null
    //   60: invokestatic 236	android/os/Message:obtain	(Landroid/os/Handler;ILjava/lang/Object;)Landroid/os/Message;
    //   63: astore 4
    //   65: aload_0
    //   66: getfield 93	fm/qingting/qtradio/zte/ZteFM:mHandler	Landroid/os/Handler;
    //   69: aload 4
    //   71: invokevirtual 242	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
    //   74: pop
    //   75: iload 5
    //   77: istore 6
    //   79: iload 6
    //   81: ifeq +75 -> 156
    //   84: iload_1
    //   85: i2d
    //   86: ldc2_w 433
    //   89: ddiv
    //   90: dstore_2
    //   91: aload_0
    //   92: getfield 143	fm/qingting/qtradio/zte/ZteFM:fmService	Landroid/media/IFMSystemService;
    //   95: dload_2
    //   96: invokeinterface 438 3 0
    //   101: aload_0
    //   102: getfield 143	fm/qingting/qtradio/zte/ZteFM:fmService	Landroid/media/IFMSystemService;
    //   105: aload_0
    //   106: getfield 77	fm/qingting/qtradio/zte/ZteFM:mDefaultVolum	I
    //   109: invokeinterface 281 2 0
    //   114: aload_0
    //   115: ldc 228
    //   117: ldc_w 439
    //   120: invokespecial 167	fm/qingting/qtradio/zte/ZteFM:apiAMsetParameter	(Ljava/lang/String;Ljava/lang/String;)V
    //   123: aload_0
    //   124: iconst_1
    //   125: putfield 71	fm/qingting/qtradio/zte/ZteFM:mIsPlay	Z
    //   128: aload_0
    //   129: getfield 93	fm/qingting/qtradio/zte/ZteFM:mHandler	Landroid/os/Handler;
    //   132: bipush 6
    //   134: aconst_null
    //   135: invokestatic 236	android/os/Message:obtain	(Landroid/os/Handler;ILjava/lang/Object;)Landroid/os/Message;
    //   138: astore 4
    //   140: aload 4
    //   142: iload_1
    //   143: putfield 442	android/os/Message:arg1	I
    //   146: aload_0
    //   147: getfield 93	fm/qingting/qtradio/zte/ZteFM:mHandler	Landroid/os/Handler;
    //   150: aload 4
    //   152: invokevirtual 242	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
    //   155: pop
    //   156: iconst_0
    //   157: ireturn
    //   158: astore 4
    //   160: aload 4
    //   162: invokevirtual 205	android/os/RemoteException:printStackTrace	()V
    //   165: goto -125 -> 40
    //   168: iconst_1
    //   169: istore 6
    //   171: goto -92 -> 79
    //   174: astore 4
    //   176: aload 4
    //   178: invokevirtual 205	android/os/RemoteException:printStackTrace	()V
    //   181: goto -25 -> 156
    //
    // Exception table:
    //   from	to	target	type
    //   10	23	158	android/os/RemoteException
    //   27	36	158	android/os/RemoteException
    //   91	156	174	android/os/RemoteException
  }

  public void turnOff()
    throws Exception
  {
    off();
  }

  public int turnOn()
    throws Exception
  {
    return on();
  }

  public void unregister()
  {
    setListener(null);
  }

  public void unregisterFMEventListener()
  {
    unregister();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.zte.ZteFM
 * JD-Core Version:    0.6.2
 */