package fm.qingting.qtradio.vollo.vx96;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import fm.qingting.qtradio.fmdriver.FMDriver;
import fm.qingting.qtradio.fmdriver.IFMEventListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Vollo_Vx96_FMDriver extends FMDriver
{
  private static final int FM_RX_DWELL_PERIOD_1S = 0;
  private static final int FM_RX_DWELL_PERIOD_2S = 1;
  private static final int FM_RX_DWELL_PERIOD_3S = 2;
  private static final int FM_RX_DWELL_PERIOD_4S = 3;
  private static final int FM_RX_DWELL_PERIOD_5S = 4;
  private static final int FM_RX_DWELL_PERIOD_6S = 5;
  private static final int FM_RX_DWELL_PERIOD_7S = 6;
  private static final int FM_RX_SEARCHDIR_DOWN = 0;
  private static final int FM_RX_SEARCHDIR_UP = 1;
  private static final int FM_RX_SRCH_MODE_SCAN = 1;
  private static final int FM_RX_SRCH_MODE_SEEK = 0;
  private static final int FREQUENCY_BAND_MAX = 108001;
  private static final int MaxVolume = 15;
  private static final int MsgChannelFound = 2;
  private static final int MsgFmOff = 3;
  private static final int MsgFmOn = 4;
  private static final int MsgScanComplete = 1;
  private static final int MsgScanStarted = 5;
  private static final int MsgTune = 6;
  public static final String TAG = "FmReceiver";
  private AudioManager amObj;
  private Method asSetForceUseMethod = null;
  private Class audioSystem = null;
  private ArrayList<Integer> channels = new ArrayList();
  private Method cmAcquireMethod;
  private Method cmCancelSearchMethod;
  private Method cmConfigureMethod;
  private Method cmDisableMethod;
  private Method cmEnableMethod;
  private Method cmGetRssiLimitMethod;
  private Method cmGetRssiMethod;
  private Method cmGetTunedFrequencyMethod;
  private Method cmRegisterClientMethod;
  private Method cmSearchStationsMethod;
  private Method cmSetMuteModeMethod;
  private Method cmSetPowerModeMethod;
  private Method cmSetStationMethod;
  private Method cmSetStereoModeMethod;
  private Method cmSetVolumMethod;
  private Method cmUnRegisterClientMethod;
  private Class fmConfigClass;
  private Object fmConfigObj;
  private Class fmReceiverClass;
  private Object fmReceiverObj;
  private Class fmReceiverSuperClass;
  private Class huaweiFM = null;
  private boolean isAvailable = false;
  private Context mContext;
  private int mCurrentFreq = 1;
  private int mDefaultVolum = 1;
  private final Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (Vollo_Vx96_FMDriver.this.onFMEventListener == null)
        return;
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 1:
        Vollo_Vx96_FMDriver.this.onFMEventListener.onScanComplete(true);
        return;
      case 2:
        Vollo_Vx96_FMDriver.this.onFMEventListener.onChannelFound(paramAnonymousMessage.arg1 * 100);
        return;
      case 3:
        Vollo_Vx96_FMDriver.this.onFMEventListener.onFMOff();
        return;
      case 4:
        Vollo_Vx96_FMDriver.this.onFMEventListener.onFMOn();
        return;
      case 5:
        Vollo_Vx96_FMDriver.this.onFMEventListener.onScanStarted();
        return;
      case 6:
      }
      Vollo_Vx96_FMDriver.this.onFMEventListener.onTune(paramAnonymousMessage.arg1);
    }
  };
  private boolean mIsFMEnable = false;
  private boolean mIsPlay = false;
  private boolean mIsScan = false;
  private boolean mMute = false;
  private IFMEventListener onFMEventListener;
  private Method setChSpacingMethod;
  private Method setEmphasisMethod;
  private Method setLowerLimitMedthod;
  private Method setRadioBandMethod;
  private Method setRdsStdMethod;
  private Method setUpperLimitMethod;

  public Vollo_Vx96_FMDriver(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    initClass();
  }

  private Boolean acquireReceiver(String paramString)
  {
    Boolean localBoolean = Boolean.valueOf(false);
    try
    {
      paramString = (Boolean)this.cmAcquireMethod.invoke(this.fmReceiverObj, new Object[] { String.valueOf(paramString) });
      return paramString;
    }
    catch (IllegalArgumentException paramString)
    {
      paramString.printStackTrace();
      return localBoolean;
    }
    catch (IllegalAccessException paramString)
    {
      paramString.printStackTrace();
      return localBoolean;
    }
    catch (InvocationTargetException paramString)
    {
      paramString.printStackTrace();
    }
    return localBoolean;
  }

  private long apiGetCurrentChannel()
  {
    return getTunedFrequencyReceiver();
  }

  private long apiGetCurrentRSSI()
  {
    try
    {
      long l = ((Long)this.cmGetRssiMethod.invoke(this.fmReceiverObj, new Object[0])).longValue();
      return l;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return -2147483648L;
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
    if (this.mIsScan)
      cancelScan();
    if (this.mIsPlay)
    {
      this.mIsFMEnable = false;
      disableReceiver();
    }
    this.mIsPlay = false;
    this.mIsFMEnable = false;
    Message localMessage = Message.obtain(this.mHandler, 3, null);
    this.mHandler.sendMessage(localMessage);
  }

  private boolean apiOn()
  {
    int i;
    Message localMessage;
    if (!this.mIsFMEnable)
      if ((acquireReceiver("/dev/radio0").booleanValue()) && (enableReceiver()))
      {
        i = 1;
        if (i != 0)
        {
          this.mIsFMEnable = true;
          localMessage = Message.obtain(this.mHandler, 4, null);
          this.mHandler.sendMessage(localMessage);
        }
      }
    while (true)
    {
      return this.mIsFMEnable;
      i = 0;
      break;
      localMessage = Message.obtain(this.mHandler, 3, null);
      this.mHandler.sendMessage(localMessage);
    }
  }

  private int apiScan()
  {
    if (!searchStationsReceiver())
      return -1;
    return getTunedFrequencyReceiver();
  }

  private void apiStopScan()
  {
    if (this.mIsScan)
    {
      cancelSearchStationReceiver();
      this.mIsScan = false;
    }
  }

  private boolean buildDefaultFMConfig()
  {
    try
    {
      this.setChSpacingMethod.invoke(this.fmConfigObj, new Object[] { Integer.valueOf(1) });
      this.setEmphasisMethod.invoke(this.fmConfigObj, new Object[] { Integer.valueOf(1) });
      this.setRadioBandMethod.invoke(this.fmConfigObj, new Object[] { Integer.valueOf(2) });
      this.setRdsStdMethod.invoke(this.fmConfigObj, new Object[] { Integer.valueOf(1) });
      this.setLowerLimitMedthod.invoke(this.fmConfigObj, new Object[] { Integer.valueOf(8750) });
      this.setUpperLimitMethod.invoke(this.fmConfigObj, new Object[] { Integer.valueOf(10800) });
      return true;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return false;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return false;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
    return false;
  }

  private void cancelScan()
  {
    apiStopScan();
  }

  private boolean cancelSearchStationReceiver()
  {
    try
    {
      boolean bool = ((Boolean)this.cmCancelSearchMethod.invoke(this.fmReceiverObj, new Object[0])).booleanValue();
      return bool;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return false;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return false;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
    return false;
  }

  private boolean disableReceiver()
  {
    try
    {
      boolean bool = ((Boolean)this.cmDisableMethod.invoke(this.fmReceiverObj, new Object[0])).booleanValue();
      return bool;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return false;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return false;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
    return false;
  }

  private boolean enableReceiver()
  {
    try
    {
      boolean bool = ((Boolean)this.cmEnableMethod.invoke(this.fmReceiverObj, new Object[] { this.fmConfigObj })).booleanValue();
      return bool;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return false;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return false;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
    return false;
  }

  private void getAudioSystem()
  {
    try
    {
      this.audioSystem = Class.forName("android.media.AudioSystem");
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static int getMaxvolume()
  {
    return 15;
  }

  private int getTunedFrequencyReceiver()
  {
    try
    {
      int i = ((Integer)this.cmGetTunedFrequencyMethod.invoke(this.fmReceiverObj, new Object[0])).intValue();
      return i;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return -1;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return -1;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
    return -1;
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
      this.fmConfigClass = Class.forName("android.hardware.fmradio.FmConfig", true, this.mContext.getClassLoader());
      this.fmConfigObj = this.fmConfigClass.getDeclaredConstructor(null).newInstance(null);
      this.amObj = ((AudioManager)this.mContext.getSystemService("audio"));
      this.fmReceiverClass = Class.forName("android.hardware.fmradio.FmReceiver", true, this.mContext.getClassLoader());
      this.fmReceiverObj = this.fmReceiverClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
      if ((setUpInnerMethodFromTransceiver()) && (setUpInnerMethodFromReceiver()))
        this.isAvailable = true;
      setUpInnerMethodFromConfig();
      buildDefaultFMConfig();
      getAudioSystem();
      setUpMethodFromAudioSystem();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private boolean isAudioSystemAvailable()
  {
    return this.audioSystem != null;
  }

  private boolean searchStationsReceiver()
  {
    try
    {
      boolean bool = ((Boolean)this.cmSearchStationsMethod.invoke(this.fmReceiverObj, new Object[] { Integer.valueOf(this.mCurrentFreq), Integer.valueOf(1), Integer.valueOf(3000) })).booleanValue();
      return bool;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return false;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return false;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
    return false;
  }

  private boolean setStationReceiver(int paramInt)
  {
    try
    {
      boolean bool = ((Boolean)this.cmSetStationMethod.invoke(this.fmReceiverObj, new Object[] { Integer.valueOf(paramInt) })).booleanValue();
      return bool;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return false;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return false;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
    return false;
  }

  private void setUpInnerMethodFromConfig()
  {
    try
    {
      this.setChSpacingMethod = this.fmConfigClass.getDeclaredMethod("setChSpacing", new Class[] { Integer.TYPE });
      this.setEmphasisMethod = this.fmConfigClass.getDeclaredMethod("setEmphasis", new Class[] { Integer.TYPE });
      this.setRadioBandMethod = this.fmConfigClass.getDeclaredMethod("setRadioBand", new Class[] { Integer.TYPE });
      this.setRdsStdMethod = this.fmConfigClass.getDeclaredMethod("setRdsStd", new Class[] { Integer.TYPE });
      this.setLowerLimitMedthod = this.fmConfigClass.getDeclaredMethod("setLowerLimit", new Class[] { Integer.TYPE });
      this.setUpperLimitMethod = this.fmConfigClass.getDeclaredMethod("setUpperLimit", new Class[] { Integer.TYPE });
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

  private boolean setUpInnerMethodFromReceiver()
  {
    try
    {
      this.cmEnableMethod = this.fmReceiverClass.getDeclaredMethod("enable", new Class[] { this.fmConfigClass });
      this.cmDisableMethod = this.fmReceiverClass.getDeclaredMethod("disable", new Class[0]);
      this.cmGetTunedFrequencyMethod = this.fmReceiverClass.getDeclaredMethod("getTunedFrequency", new Class[0]);
      this.cmSearchStationsMethod = this.fmReceiverClass.getDeclaredMethod("searchStations", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      this.cmCancelSearchMethod = this.fmReceiverClass.getDeclaredMethod("cancelSearch", new Class[0]);
      this.cmGetRssiMethod = this.fmReceiverClass.getDeclaredMethod("getRssi", new Class[0]);
      this.cmUnRegisterClientMethod = this.fmReceiverClass.getDeclaredMethod("unregisterClient", new Class[0]);
      this.cmGetRssiLimitMethod = this.fmReceiverClass.getDeclaredMethod("getRssiLimit", new Class[0]);
      this.cmSetMuteModeMethod = this.fmReceiverClass.getMethod("setMuteMode", new Class[] { Integer.TYPE });
      this.cmSetStereoModeMethod = this.fmReceiverClass.getDeclaredMethod("setStereoMode", new Class[] { Boolean.TYPE });
      this.cmSetPowerModeMethod = this.fmReceiverClass.getDeclaredMethod("setPowerMode", new Class[] { Integer.TYPE });
      this.cmSetVolumMethod = this.fmReceiverClass.getDeclaredMethod("setVolume", new Class[] { Integer.TYPE });
      return true;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  private boolean setUpInnerMethodFromTransceiver()
  {
    if ((this.fmReceiverClass != null) && (this.fmReceiverClass.getGenericSuperclass() != null))
      this.fmReceiverSuperClass = this.fmReceiverClass.getSuperclass();
    try
    {
      this.cmAcquireMethod = this.fmReceiverSuperClass.getDeclaredMethod("acquire", new Class[] { String.class });
      this.cmAcquireMethod.setAccessible(true);
      this.cmSetStationMethod = this.fmReceiverSuperClass.getDeclaredMethod("setStation", new Class[] { Integer.TYPE });
      this.cmConfigureMethod = this.fmReceiverSuperClass.getDeclaredMethod("configure", new Class[] { this.fmConfigClass });
      return true;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  private void setUpMethodFromAudioSystem()
  {
    if (this.asSetForceUseMethod == null);
    try
    {
      this.asSetForceUseMethod = this.audioSystem.getMethod("setForceUse", new Class[] { Integer.TYPE, Integer.TYPE });
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

  private void setVolumViaReceiver(int paramInt)
  {
    try
    {
      boolean bool = ((Boolean)this.cmSetVolumMethod.invoke(this.fmReceiverObj, new Object[] { Integer.valueOf(paramInt) })).booleanValue();
      Log.e("FmReceiver", "set volume:" + paramInt + " " + bool);
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
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
  }

  private void showAudioStatus()
  {
    Log.e("FmReceiver", "audio mode:" + this.amObj.getMode());
    if (this.amObj.isWiredHeadsetOn())
      Log.e("FmReceiver", "wired head set on");
    if (this.amObj.isBluetoothA2dpOn())
      Log.e("FmReceiver", "bluetooth on");
    if (this.amObj.isSpeakerphoneOn())
      Log.e("FmReceiver", "speaker phone on");
    if (this.amObj.isMicrophoneMute())
      Log.e("FmReceiver", "microphone is mute");
    while (true)
    {
      if (this.amObj.isMusicActive())
        Log.e("FmReceiver", "music is active");
      return;
      Log.e("FmReceiver", "microphone is not mute");
    }
  }

  private void showChannels()
  {
    int i = 0;
    while (true)
    {
      if (i >= this.channels.size())
        return;
      Log.e("FmReceiver", "channle: " + this.channels.get(i));
      i += 1;
    }
  }

  private void showVolloLib()
  {
    int j = 0;
    Log.w("fmradio", "FmReceiver----------------");
    Method[] arrayOfMethod = this.fmReceiverClass.getDeclaredMethods();
    int k = arrayOfMethod.length;
    int i = 0;
    if (i >= k)
    {
      Log.w("fmradio", "FmTransceiver-------------");
      arrayOfMethod = this.fmReceiverClass.getSuperclass().getDeclaredMethods();
      k = arrayOfMethod.length;
      i = 0;
      label59: if (i < k)
        break label123;
      if (isAudioSystemAvailable())
      {
        Log.w("fmradio", "android.media.AudioSystem methods------");
        arrayOfMethod = this.audioSystem.getDeclaredMethods();
        k = arrayOfMethod.length;
        i = j;
      }
    }
    while (true)
    {
      if (i >= k)
      {
        return;
        Log.w("fmradio", arrayOfMethod[i].toString());
        i += 1;
        break;
        label123: Log.w("fmradio", arrayOfMethod[i].toString());
        i += 1;
        break label59;
      }
      Log.w("fmradio", arrayOfMethod[i].toString());
      i += 1;
    }
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
    return "VolloFMDriver-FmReceiver";
  }

  public int getVolume()
    throws Exception
  {
    return this.mDefaultVolum;
  }

  public boolean isAvailable()
  {
    return this.isAvailable;
  }

  public boolean isHeadsetPlugged()
  {
    return true;
  }

  public boolean isMute()
    throws Exception
  {
    return this.mMute;
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
    int i = 1;
    this.mMute = paramBoolean;
    Method localMethod = this.cmSetMuteModeMethod;
    Object localObject = this.fmReceiverObj;
    if (paramBoolean);
    while (true)
    {
      boolean bool = ((Boolean)localMethod.invoke(localObject, new Object[] { Integer.valueOf(i) })).booleanValue();
      if (bool)
        this.mMute = paramBoolean;
      Log.e("FmReceiver", "mute:" + paramBoolean + ";result:" + bool);
      return;
      i = 0;
    }
  }

  public void off()
  {
    apiOff();
  }

  public void pause()
    throws Exception
  {
    Intent localIntent = new Intent("android.intent.action.FM");
    localIntent.putExtra("state", 0);
    this.mContext.sendBroadcast(localIntent);
    this.mIsPlay = false;
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
        Message localMessage = Message.obtain(Vollo_Vx96_FMDriver.this.mHandler, 5, null);
        Vollo_Vx96_FMDriver.this.mHandler.sendMessage(localMessage);
        int i;
        if (!Vollo_Vx96_FMDriver.this.mIsFMEnable)
        {
          if ((Vollo_Vx96_FMDriver.this.acquireReceiver("/dev/radio0").booleanValue()) && (Vollo_Vx96_FMDriver.this.enableReceiver()))
          {
            i = 1;
            if (i == 0)
              break label187;
            Vollo_Vx96_FMDriver.this.mIsFMEnable = true;
            localMessage = Message.obtain(Vollo_Vx96_FMDriver.this.mHandler, 4, null);
            Vollo_Vx96_FMDriver.this.mHandler.sendMessage(localMessage);
          }
        }
        else
        {
          Vollo_Vx96_FMDriver.this.mIsPlay = false;
          Vollo_Vx96_FMDriver.this.mIsScan = true;
          Vollo_Vx96_FMDriver.this.channels.clear();
          label125: i = Vollo_Vx96_FMDriver.this.apiScan();
          if ((-1 != i) && (Vollo_Vx96_FMDriver.this.mIsScan))
            break label213;
        }
        while (true)
        {
          Vollo_Vx96_FMDriver.this.mIsScan = false;
          localMessage = Message.obtain(Vollo_Vx96_FMDriver.this.mHandler, 1, null);
          Vollo_Vx96_FMDriver.this.mHandler.sendMessage(localMessage);
          return;
          i = 0;
          break;
          label187: localMessage = Message.obtain(Vollo_Vx96_FMDriver.this.mHandler, 1, null);
          Vollo_Vx96_FMDriver.this.mHandler.sendMessage(localMessage);
          return;
          label213: Vollo_Vx96_FMDriver.this.mCurrentFreq = i;
          if (!Vollo_Vx96_FMDriver.this.inChannelList(i))
          {
            localMessage = Message.obtain(Vollo_Vx96_FMDriver.this.mHandler, 2, null);
            localMessage.arg1 = i;
            Vollo_Vx96_FMDriver.this.mHandler.sendMessage(localMessage);
            Vollo_Vx96_FMDriver.this.channels.add(Integer.valueOf(i));
            if (i < 108001)
              break label125;
          }
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
    Intent localIntent = new Intent("android.intent.action.FM");
    localIntent.putExtra("state", 1);
    if (paramBoolean)
      localIntent.putExtra("speaker", 2);
    while (true)
    {
      this.mContext.sendBroadcast(localIntent);
      return 0;
      localIntent.putExtra("speaker", 0);
    }
  }

  public void setVolume(int paramInt)
    throws Exception
  {
    int i = paramInt;
    if (paramInt < 0)
      i = 0;
    paramInt = i;
    if (i > getMaxvolume())
      paramInt = getMaxvolume();
    setVolumViaReceiver(paramInt);
    this.mDefaultVolum = paramInt;
  }

  public int tune(int paramInt)
  {
    int j = 0;
    int i = paramInt;
    if (paramInt > 10000)
      i = paramInt / 100;
    Log.e("FmReceiver", "tune, freq = " + i);
    if (!apiOn())
      paramInt = -1;
    do
    {
      return paramInt;
      this.mIsPlay = true;
      setStationReceiver(i);
      Object localObject = Message.obtain(this.mHandler, 6, null);
      ((Message)localObject).arg1 = i;
      this.mHandler.sendMessage((Message)localObject);
      localObject = new Intent("android.intent.action.FM");
      ((Intent)localObject).putExtra("state", 1);
      this.mContext.sendBroadcast((Intent)localObject);
      paramInt = j;
    }
    while (!this.mMute);
    try
    {
      mute(false);
      return 0;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }

  public void turnOff()
    throws Exception
  {
    Log.e("FmReceiver", "turn off");
    off();
  }

  public int turnOn()
    throws Exception
  {
    if (apiOn())
      return 1;
    return 0;
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
 * Qualified Name:     fm.qingting.qtradio.vollo.vx96.Vollo_Vx96_FMDriver
 * JD-Core Version:    0.6.2
 */