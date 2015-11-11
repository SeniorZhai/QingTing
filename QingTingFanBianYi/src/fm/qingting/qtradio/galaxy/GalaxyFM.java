package fm.qingting.qtradio.galaxy;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import fm.qingting.qtradio.fmdriver.FMDriver;
import fm.qingting.qtradio.fmdriver.IFMEventListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class GalaxyFM extends FMDriver
{
  private static final int AIRPLANE_MODE = 5;
  public static final int BAND_76000_108000_kHz = 2;
  public static final int BAND_76000_90000_kHz = 3;
  public static final int BAND_87500_108000_kHz = 1;
  private static final int BATTERY_LOW = 6;
  public static final int CHAN_SPACING_100_kHz = 10;
  public static final int CHAN_SPACING_200_kHz = 20;
  public static final int CHAN_SPACING_50_kHz = 5;
  public static final String CMDAPPWIDGETUPDATE = "appwidgetupdate";
  public static final String CMDNAME = "command";
  public static final int DE_TIME_CONSTANT_50 = 1;
  public static final int DE_TIME_CONSTANT_75 = 0;
  private static final String FM_RADIO_SERVICE_NAME = "FMPlayer";
  private static final int HEAD_SET_IS_NOT_PLUGGED = 4;
  private static final int PLAYER_IS_NOT_ON = 1;
  private static final int PLAYER_SCANNING = 3;
  private static final int RADIO_AUDIO_STREAM = 10;
  private static final int RADIO_SERVICE_DOWN = 2;
  public static final String SAMSUNG_PACKAGE_NAME = "com.sec.android.app.fm";
  private static final String TAG = "GalaxyFM";
  private static final int TV_OUT_PLUGGED = 7;
  private boolean available = false;
  private Method cancelScanMethod;
  private ArrayList<Integer> channels = new ArrayList();
  private Method disableAFMethod;
  private Method disableRDSMethod;
  private Method enableAFMethod;
  private Method enableRDSMethod;
  private Object fmListener;
  private Method getCurrentChannelMethod;
  private Method getCurrentRSSIMethod;
  private Method getLastScanResultMethod;
  private Method getMaxVolumeMethod;
  private Method getVolumeMethod;
  private Class internalServiceClass;
  private Object internalServiceObject;
  private Method isHeadsetPluggedMethod;
  private Method isOnMethod;
  private Method isScanningMethod;
  private boolean isSpeakout = false;
  private IFMEventListener listener;
  private Class listenerClass;
  private Method offMethod;
  private Method onMethod;
  private Method removeListenerMethod;
  private Handler scanHandler = new Handler()
  {
    private static final int EVENT_AF_RECEIVED = 14;
    private static final int EVENT_AF_STARTED = 13;
    private static final int EVENT_CHANNEL_FOUND = 1;
    private static final int EVENT_EAR_PHONE_CONNECT = 8;
    private static final int EVENT_EAR_PHONE_DISCONNECT = 9;
    private static final int EVENT_OFF = 6;
    private static final int EVENT_ON = 5;
    private static final int EVENT_RDS_DISABLED = 12;
    private static final int EVENT_RDS_ENABLED = 11;
    private static final int EVENT_RDS_EVENT = 10;
    private static final int EVENT_SCAN_FINISHED = 3;
    private static final int EVENT_SCAN_STARTED = 2;
    private static final int EVENT_SCAN_STOPPED = 4;
    private static final int EVENT_TUNE = 7;

    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      case 11:
      case 12:
      case 13:
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
        label148: 
        do
        {
          do
          {
            long l;
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                      return;
                    while (GalaxyFM.this.listener == null);
                    l = ((Long)paramAnonymousMessage.obj).longValue();
                    GalaxyFM.this.listener.onChannelFound((int)l);
                    return;
                  }
                  while (GalaxyFM.this.listener == null);
                  GalaxyFM.this.listener.onScanStarted();
                  return;
                  paramAnonymousMessage = (long[])paramAnonymousMessage.obj;
                  int j = paramAnonymousMessage.length;
                  int i = 0;
                  if (i >= j)
                  {
                    GalaxyFM.this.channels.clear();
                    i = 0;
                  }
                  while (true)
                  {
                    if (i >= paramAnonymousMessage.length)
                    {
                      if (GalaxyFM.this.listener == null)
                        break;
                      GalaxyFM.this.listener.onScanComplete(true);
                      return;
                      Log.d("GalaxyFMs", String.valueOf(paramAnonymousMessage[i]));
                      i += 1;
                      break label148;
                    }
                    GalaxyFM.this.channels.add(Integer.valueOf((int)paramAnonymousMessage[i]));
                    i += 1;
                  }
                  paramAnonymousMessage = (long[])paramAnonymousMessage.obj;
                  GalaxyFM.this.channels.clear();
                  i = 0;
                  while (true)
                  {
                    if (i >= paramAnonymousMessage.length)
                    {
                      if (GalaxyFM.this.listener == null)
                        break;
                      GalaxyFM.this.listener.onScanComplete(true);
                      return;
                    }
                    GalaxyFM.this.channels.add(Integer.valueOf((int)paramAnonymousMessage[i]));
                    i += 1;
                  }
                }
                while (GalaxyFM.this.listener == null);
                GalaxyFM.this.listener.onFMOn();
                return;
              }
              while (GalaxyFM.this.listener == null);
              GalaxyFM.this.listener.onFMOff();
              return;
              l = ((Long)paramAnonymousMessage.obj).longValue();
            }
            while (GalaxyFM.this.listener == null);
            GalaxyFM.this.listener.onTune((int)l);
            return;
          }
          while (GalaxyFM.this.listener != null);
          return;
        }
        while (GalaxyFM.this.listener != null);
        return;
      case 10:
      }
      paramAnonymousMessage = (Object[])paramAnonymousMessage.obj;
      ((Long)paramAnonymousMessage[0]).longValue();
      String str = (String)paramAnonymousMessage[1];
      paramAnonymousMessage = (String)paramAnonymousMessage[2];
    }
  };
  private Method scanMethod;
  private Method seekDownMethod;
  private Method seekUpMethod;
  private Method setBandMethod;
  private Method setChannelSpacingMethod;
  private Method setDEConstantMethod;
  private Method setListenerMethod;
  private Method setSpeakerOnMethod;
  private Method setStereoMethod;
  private Method setVolumeMethod;
  private Method tuneMethod;

  public GalaxyFM(Context paramContext)
  {
    super(paramContext);
    this.internalServiceObject = paramContext.getSystemService("FMPlayer");
    if (this.internalServiceObject != null)
      setUpInnerMethods();
  }

  private void apiDisableAF()
  {
    try
    {
      this.disableAFMethod.invoke(this.internalServiceObject, new Object[0]);
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
  }

  private void apiDisableRDS()
  {
    try
    {
      this.disableRDSMethod.invoke(this.internalServiceObject, new Object[0]);
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
  }

  private void apiEnableAF()
  {
    try
    {
      this.enableAFMethod.invoke(this.internalServiceObject, new Object[0]);
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
  }

  private void apiEnableRDS()
  {
    try
    {
      this.enableRDSMethod.invoke(this.internalServiceObject, new Object[0]);
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
  }

  private long apiGetCurrentChannel()
  {
    try
    {
      long l = ((Long)this.getCurrentChannelMethod.invoke(this.internalServiceObject, new Object[0])).longValue();
      return l;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      return -9223372036854775808L;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      break label25;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      label25: break label25;
    }
  }

  private long apiGetCurrentRSSI()
  {
    try
    {
      long l = ((Long)this.getCurrentRSSIMethod.invoke(this.internalServiceObject, new Object[0])).longValue();
      return l;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      return -2147483648L;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      break label25;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      label25: break label25;
    }
  }

  private long[] apiGetLastScanResult()
  {
    try
    {
      long[] arrayOfLong = (long[])this.getLastScanResultMethod.invoke(this.internalServiceObject, new Object[0]);
      return arrayOfLong;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      return new long[0];
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      break label22;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      label22: break label22;
    }
  }

  private boolean apiIsHeadsetPlugged()
  {
    try
    {
      boolean bool = ((Boolean)this.isHeadsetPluggedMethod.invoke(this.internalServiceObject, new Object[0])).booleanValue();
      return bool;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      return false;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      break label25;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      label25: break label25;
    }
  }

  private boolean apiIsOn()
  {
    try
    {
      boolean bool = ((Boolean)this.isOnMethod.invoke(this.internalServiceObject, new Object[0])).booleanValue();
      return bool;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      return false;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      break label25;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      label25: break label25;
    }
  }

  private boolean apiIsScanning()
  {
    try
    {
      boolean bool = ((Boolean)this.isScanningMethod.invoke(this.internalServiceObject, new Object[0])).booleanValue();
      return bool;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      return false;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      break label25;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      label25: break label25;
    }
  }

  private void apiOff()
  {
    try
    {
      this.offMethod.invoke(this.internalServiceObject, new Object[0]);
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
  }

  private int apiOn()
  {
    try
    {
      this.onMethod.invoke(this.internalServiceObject, new Object[0]);
      return 0;
    }
    catch (InvocationTargetException localInvocationTargetException1)
    {
      try
      {
        Class localClass = localInvocationTargetException1.getTargetException().getClass();
        if (!localClass.isAssignableFrom(Class.forName("com.samsung.media.fmradio.FMPlayerException")))
          return -2147483648;
        int i = getFMError(((Integer)localClass.getMethod("getCode", new Class[0]).invoke(localInvocationTargetException1.getTargetException(), new Object[0])).intValue());
        return i;
      }
      catch (InvocationTargetException localInvocationTargetException2)
      {
        return -2147483648;
      }
      catch (IllegalAccessException localIllegalAccessException1)
      {
        break label77;
      }
      catch (IllegalArgumentException localIllegalArgumentException1)
      {
        break label77;
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        break label77;
      }
      catch (SecurityException localSecurityException)
      {
        break label77;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        break label77;
      }
    }
    catch (IllegalAccessException localIllegalAccessException2)
    {
      break label77;
    }
    catch (IllegalArgumentException localIllegalArgumentException2)
    {
      label77: break label77;
    }
  }

  private void apiRemoveListener(Object paramObject)
  {
    try
    {
      this.removeListenerMethod.invoke(this.internalServiceObject, new Object[] { paramObject });
      return;
    }
    catch (InvocationTargetException paramObject)
    {
    }
    catch (IllegalAccessException paramObject)
    {
    }
    catch (IllegalArgumentException paramObject)
    {
    }
  }

  private void apiScan()
  {
    try
    {
      this.scanMethod.invoke(this.internalServiceObject, new Object[0]);
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
  }

  private long apiSeekDown()
  {
    try
    {
      long l = ((Long)this.seekDownMethod.invoke(this.internalServiceObject, new Object[0])).longValue();
      return l;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      return -9223372036854775808L;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      break label25;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      label25: break label25;
    }
  }

  private long apiSeekUp()
  {
    try
    {
      long l = ((Long)this.seekUpMethod.invoke(this.internalServiceObject, new Object[0])).longValue();
      return l;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      return -9223372036854775808L;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      break label25;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      label25: break label25;
    }
  }

  private void apiSetBand(int paramInt)
  {
    try
    {
      this.setBandMethod.invoke(this.internalServiceObject, new Object[] { Integer.valueOf(paramInt) });
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
  }

  private void apiSetChannelSpacing(int paramInt)
  {
    try
    {
      this.setChannelSpacingMethod.invoke(this.internalServiceObject, new Object[] { Integer.valueOf(paramInt) });
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
  }

  private void apiSetDEConstant(long paramLong)
  {
    try
    {
      this.setDEConstantMethod.invoke(this.internalServiceObject, new Object[] { Long.valueOf(paramLong) });
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
  }

  private void apiSetListener(Object paramObject)
  {
    try
    {
      this.setListenerMethod.invoke(this.internalServiceObject, new Object[] { paramObject });
      return;
    }
    catch (InvocationTargetException paramObject)
    {
    }
    catch (IllegalArgumentException paramObject)
    {
    }
    catch (SecurityException paramObject)
    {
    }
    catch (IllegalAccessException paramObject)
    {
    }
  }

  private void apiSetSpeakerOn(boolean paramBoolean)
  {
    try
    {
      this.setSpeakerOnMethod.invoke(this.internalServiceObject, new Object[] { Boolean.valueOf(paramBoolean) });
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
  }

  private void apiStopScan()
  {
    try
    {
      this.cancelScanMethod.invoke(this.internalServiceObject, new Object[0]);
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
  }

  private void apiTune(long paramLong)
  {
    try
    {
      this.tuneMethod.invoke(this.internalServiceObject, new Object[] { Long.valueOf(paramLong) });
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
  }

  private void apisetVolume(long paramLong)
  {
    try
    {
      this.setVolumeMethod.invoke(this.internalServiceObject, new Object[] { Long.valueOf(paramLong) });
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private int getFMError(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return -2147483648;
    case 1:
      return -2;
    case 2:
      return 3;
    case 3:
      return 4;
    case 4:
      return 5;
    case 5:
      return 6;
    case 6:
      return 7;
    case 7:
    }
    return 8;
  }

  private void setUpInnerMethods()
  {
    this.internalServiceClass = this.internalServiceObject.getClass();
    try
    {
      this.listenerClass = Class.forName("com.samsung.media.fmradio.FMEventListener");
      this.onMethod = this.internalServiceClass.getMethod("on", new Class[0]);
      this.offMethod = this.internalServiceClass.getMethod("off", new Class[0]);
      this.isOnMethod = this.internalServiceClass.getMethod("isOn", new Class[0]);
      this.isScanningMethod = this.internalServiceClass.getMethod("isScanning", new Class[0]);
      this.setBandMethod = this.internalServiceClass.getMethod("setBand", new Class[] { Integer.TYPE });
      this.setChannelSpacingMethod = this.internalServiceClass.getMethod("setChannelSpacing", new Class[] { Integer.TYPE });
      this.setDEConstantMethod = this.internalServiceClass.getMethod("setDEConstant", new Class[] { Long.TYPE });
      this.setSpeakerOnMethod = this.internalServiceClass.getMethod("setSpeakerOn", new Class[] { Boolean.TYPE });
      this.seekUpMethod = this.internalServiceClass.getMethod("seekUp", new Class[0]);
      this.seekDownMethod = this.internalServiceClass.getMethod("seekDown", new Class[0]);
      this.scanMethod = this.internalServiceClass.getMethod("scan", new Class[0]);
      this.cancelScanMethod = this.internalServiceClass.getMethod("cancelScan", new Class[0]);
      this.tuneMethod = this.internalServiceClass.getMethod("tune", new Class[] { Long.TYPE });
      this.getCurrentChannelMethod = this.internalServiceClass.getMethod("getCurrentChannel", new Class[0]);
      this.disableRDSMethod = this.internalServiceClass.getMethod("disableRDS", new Class[0]);
      this.enableRDSMethod = this.internalServiceClass.getMethod("enableRDS", new Class[0]);
      this.disableAFMethod = this.internalServiceClass.getMethod("disableAF", new Class[0]);
      this.enableAFMethod = this.internalServiceClass.getMethod("enableAF", new Class[0]);
      this.setVolumeMethod = this.internalServiceClass.getMethod("setVolume", new Class[] { Long.TYPE });
      this.getLastScanResultMethod = this.internalServiceClass.getMethod("getLastScanResult", new Class[0]);
      this.isHeadsetPluggedMethod = this.internalServiceClass.getMethod("isHeadsetPlugged", new Class[0]);
      this.setListenerMethod = this.internalServiceClass.getMethod("setListener", new Class[] { this.listenerClass });
      this.removeListenerMethod = this.internalServiceClass.getMethod("removeListener", new Class[] { this.listenerClass });
      this.getCurrentRSSIMethod = this.internalServiceClass.getMethod("getCurrentRSSI", new Class[0]);
      this.fmListener = this.listenerClass.newInstance();
      Field localField = this.listenerClass.getDeclaredField("mHandler");
      localField.setAccessible(true);
      localField.set(this.fmListener, this.scanHandler);
      apiSetListener(this.fmListener);
      this.available = true;
      return;
    }
    catch (Exception localException)
    {
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
    }
    catch (InstantiationException localInstantiationException)
    {
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
    }
    catch (SecurityException localSecurityException)
    {
    }
  }

  public void cancelScanning()
  {
    apiStopScan();
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
    return "GalaxyFM";
  }

  public int getVolume()
    throws Exception
  {
    return 0;
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
    return !isOn();
  }

  public boolean isOn()
  {
    return apiIsOn();
  }

  public boolean isPaused()
    throws Exception
  {
    return !isOn();
  }

  public boolean isScanning()
  {
    return apiIsScanning();
  }

  public boolean isSpeakerOn()
    throws Exception
  {
    return this.isSpeakout;
  }

  public void mute(boolean paramBoolean)
    throws Exception
  {
  }

  public void pause()
    throws Exception
  {
    turnOff();
  }

  public void registerFMEventListener(IFMEventListener paramIFMEventListener)
  {
    this.listener = paramIFMEventListener;
  }

  public void scan()
  {
    apiOn();
    apiSetBand(1);
    apiSetChannelSpacing(10);
    apiSetDEConstant(0L);
    apiScan();
  }

  public void setLiveAudioQualityCallback(boolean paramBoolean, int paramInt)
  {
  }

  public int setSpeakerOn(boolean paramBoolean)
  {
    this.isSpeakout = paramBoolean;
    apiSetSpeakerOn(paramBoolean);
    return 0;
  }

  public void setVolume(int paramInt)
    throws Exception
  {
  }

  public int tune(int paramInt)
  {
    int i;
    if (!this.available)
      i = -1;
    int j;
    do
    {
      return i;
      if (isOn())
        break;
      j = turnOn();
      i = j;
    }
    while (j != 0);
    apiTune(paramInt);
    apisetVolume(8L);
    return 0;
  }

  public void turnOff()
  {
    apiOff();
  }

  public int turnOn()
  {
    return apiOn();
  }

  public void unregisterFMEventListener()
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.galaxy.GalaxyFM
 * JD-Core Version:    0.6.2
 */