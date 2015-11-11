package fm.qingting.qtradio.miui2;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import fm.qingting.qtradio.fmdriver.FMDriver;
import fm.qingting.qtradio.fmdriver.IFMEventListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class MiuiFMReceiver extends FMDriver
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
  private static final String TAG = "fm";
  private Class ServiceManagerClass;
  private Class amClass;
  private Object amObj;
  private Method asRequestAudioFocusMethod = null;
  private Method asSetForceUseMethod = null;
  private Method asSetParameterMethod;
  private Class audioSystem = null;
  private boolean availableFirst = false;
  private boolean availableLast = false;
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
  private Method cmRegisterRdsGroupProcessingMethod;
  private Method cmSearchStationListMethod;
  private Method cmSearchStationsMethod;
  private Method cmSetInternalAntennaMethod;
  private Method cmSetMuteModeMethod;
  private Method cmSetPowerModeMethod;
  private Method cmSetStationMethod;
  private Method cmSetStereoModeMethod;
  private Method cmSetVolumMethod;
  private Method cmUnRegisterClientMethod;
  private int currentFreq = -1;
  private Class fmConfigClass;
  private Object fmConfigObj;
  private Class fmReceiverClass;
  private Object fmReceiverObj;
  private Class fmReceiverSuperClass;
  private Class fmRxEvCallbackAdaptorClass;
  private Object fmRxEvCallbackAdaptorObj;
  private Class fmRxEvCallbacksClass;
  private Object fmRxEvCallbacksObj;
  private Object fmServiceObject;
  private Method getService;
  private boolean hasStart = false;
  private Class huaweiFM = null;
  private Class internalServiceClass;
  private Object internalServiceObject;
  private InvocationHandler invocationHandler = new InvocationHandler()
  {
    public Object invoke(Object paramAnonymousObject, Method paramAnonymousMethod, Object[] paramAnonymousArrayOfObject)
      throws Throwable
    {
      Log.e("fm", " in invoke : " + paramAnonymousMethod.getName() + " " + paramAnonymousArrayOfObject.length);
      if ("FmRxEvEnableReceiver".equals(paramAnonymousMethod.getName()))
      {
        Thread.sleep(5000L);
        Log.e("fm", " in FmRxEvEnableReceiver ");
      }
      int i;
      do
      {
        return null;
        if ("FmRxEvDisableReceiver".equals(paramAnonymousMethod.getName()))
        {
          Log.e("fm", " in FmRxEvDisableReceiver ");
          return null;
        }
        if (!"FmRxEvRadioTuneStatus".equals(paramAnonymousMethod.getName()))
          break;
        Log.e("fm", " in FmRxEvRadioTuneStatus " + paramAnonymousArrayOfObject[0] + " " + paramAnonymousArrayOfObject.length);
        i = ((Integer)paramAnonymousArrayOfObject[0]).intValue();
      }
      while (MiuiFMReceiver.this.inChannelList(i));
      MiuiFMReceiver.this.channels.add(Integer.valueOf(i));
      paramAnonymousObject = Message.obtain(MiuiFMReceiver.this.mHandler, 2, null);
      paramAnonymousObject.arg1 = i;
      MiuiFMReceiver.this.mHandler.sendMessage(paramAnonymousObject);
      return null;
      if ("FmRxEvRdsLockStatus".equals(paramAnonymousMethod.getName()))
      {
        Log.e("fm", " in FmRxEvRdsLockStatus ");
        return null;
      }
      if ("FmRxEvStereoStatus".equals(paramAnonymousMethod.getName()))
      {
        Log.e("fm", " in FmRxEvStereoStatus ");
        return null;
      }
      if ("FmRxEvServiceAvailable".equals(paramAnonymousMethod.getName()))
      {
        Log.e("fm", " in FmRxEvServiceAvailable ");
        return null;
      }
      if ("FmRxEvSearchInProgress".equals(paramAnonymousMethod.getName()))
      {
        Log.e("fm", " in FmRxEvSearchInProgress ");
        return null;
      }
      if ("FmRxEvSearchComplete".equals(paramAnonymousMethod.getName()))
      {
        Log.e("fm", " in FmRxEvSearchComplete " + paramAnonymousArrayOfObject.length);
        paramAnonymousObject = Message.obtain(MiuiFMReceiver.this.mHandler, 1, null);
        MiuiFMReceiver.this.mHandler.sendMessage(paramAnonymousObject);
        return null;
      }
      if ("FmRxEvSearchListComplete".equals(paramAnonymousMethod.getName()))
      {
        Log.e("fm", " in FmRxEvSearchListComplete " + paramAnonymousArrayOfObject.length);
        return null;
      }
      if ("FmRxEvRdsGroupData".equals(paramAnonymousMethod.getName()))
      {
        Log.e("fm", " in FmRxEvRdsGroupData ");
        return null;
      }
      if ("FmRxEvRdsPsInfo".equals(paramAnonymousMethod.getName()))
      {
        Log.e("fm", " in FmRxEvRdsPsInfo ");
        return null;
      }
      if ("FmRxEvRdsRtInfo".equals(paramAnonymousMethod.getName()))
      {
        Log.e("fm", " in FmRxEvRdsRtInfo ");
        return null;
      }
      if ("FmRxEvRdsAfInfo".equals(paramAnonymousMethod.getName()))
      {
        Log.e("fm", " in FmRxEvRdsAfInfo ");
        return null;
      }
      Log.e("fm", "receiver what???");
      return null;
    }
  };
  private boolean isClientRegistered = false;
  private Class listenerClass;
  private Context mContext;
  private int mCurrentFreq = 0;
  private int mDefaultVolum = 9;
  private final Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (MiuiFMReceiver.this.onFMEventListener == null)
        return;
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 1:
        MiuiFMReceiver.this.onFMEventListener.onScanComplete(true);
        return;
      case 2:
        MiuiFMReceiver.this.onFMEventListener.onChannelFound(paramAnonymousMessage.arg1);
        return;
      case 3:
        MiuiFMReceiver.this.onFMEventListener.onFMOff();
        return;
      case 4:
        MiuiFMReceiver.this.onFMEventListener.onFMOn();
        return;
      case 5:
        MiuiFMReceiver.this.onFMEventListener.onScanStarted();
        return;
      case 6:
      }
      MiuiFMReceiver.this.onFMEventListener.onTune(paramAnonymousMessage.arg1);
    }
  };
  private boolean mIsFMEnable = false;
  private boolean mIsPlay = false;
  private boolean mIsScan = false;
  private boolean mMute = false;
  private boolean mOpenBlueTooth = false;
  private Class motorolaService;
  private boolean needRegisterClient = false;
  private IFMEventListener onFMEventListener;
  private Context paramContext = null;
  private Method setChSpacingMethod;
  private Method setEmphasisMethod;
  private Method setLowerLimitMedthod;
  private Method setRadioBandMethod;
  private Method setRdsStdMethod;
  private Method setUpperLimitMethod;

  public MiuiFMReceiver(Context paramContext1)
  {
    super(paramContext1);
    this.mContext = paramContext1;
    initClass();
  }

  private Boolean acquireReceiver(String paramString)
  {
    return Boolean.valueOf(true);
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
    return getTunedFrequencyReceiver() * 10;
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
    return false;
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
    if (this.needRegisterClient);
    try
    {
      mute(true);
      stopFM();
      if (this.mIsPlay)
      {
        this.mIsFMEnable = false;
        disableReceiver();
        apiAMsetParameter("FM", "off");
      }
      this.mIsPlay = false;
      this.mIsFMEnable = false;
      Message localMessage = Message.obtain(this.mHandler, 3, null);
      this.mHandler.sendMessage(localMessage);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  private boolean apiOn()
  {
    if (!this.mIsFMEnable)
    {
      startFM();
      if (acquireReceiver("/dev/radio0").booleanValue())
      {
        enableReceiver();
        this.mIsFMEnable = true;
        Message localMessage = Message.obtain(this.mHandler, 4, null);
        this.mHandler.sendMessage(localMessage);
      }
    }
    while (true)
      return this.mIsFMEnable;
  }

  private void apiRemoveListener(Object paramObject)
  {
  }

  private int apiScan()
  {
    if (!searchStationsReceiver());
    return -1;
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
    if (this.asSetForceUseMethod == null)
      return;
    if (paramBoolean);
    try
    {
      this.asSetForceUseMethod.invoke(this.audioSystem, new Object[] { Integer.valueOf(1), Integer.valueOf(1) });
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return;
      this.asSetForceUseMethod.invoke(this.audioSystem, new Object[] { Integer.valueOf(1), Integer.valueOf(0) });
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

  private void apiStopScan()
  {
    cancelSearchStationReceiver();
    this.mIsScan = false;
  }

  private void apiTune(long paramLong)
  {
  }

  private void apisetVolume(long paramLong)
  {
  }

  private boolean buildDefaultFMConfig()
  {
    try
    {
      this.setChSpacingMethod.invoke(this.fmConfigObj, new Object[] { Integer.valueOf(1) });
      this.setEmphasisMethod.invoke(this.fmConfigObj, new Object[] { Integer.valueOf(1) });
      this.setRadioBandMethod.invoke(this.fmConfigObj, new Object[] { Integer.valueOf(4) });
      this.setRdsStdMethod.invoke(this.fmConfigObj, new Object[] { Integer.valueOf(1) });
      this.setLowerLimitMedthod.invoke(this.fmConfigObj, new Object[] { Integer.valueOf(87000) });
      this.setUpperLimitMethod.invoke(this.fmConfigObj, new Object[] { Integer.valueOf(108000) });
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

  private void buildSetVolumMethod()
  {
    this.availableLast = true;
    try
    {
      this.cmSetVolumMethod = null;
      this.availableLast = true;
      return;
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException.printStackTrace();
    }
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

  private boolean configureReceiver()
  {
    try
    {
      boolean bool = ((Boolean)this.cmConfigureMethod.invoke(this.fmReceiverObj, new Object[] { this.fmConfigObj })).booleanValue();
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

  private boolean enableOtherReceiver()
  {
    boolean bool1 = false;
    try
    {
      boolean bool2 = ((Boolean)this.cmSetPowerModeMethod.invoke(this.fmReceiverObj, new Object[] { Integer.valueOf(1) })).booleanValue();
      bool1 = bool2;
      bool2 = ((Boolean)this.cmRegisterRdsGroupProcessingMethod.invoke(this.fmReceiverObj, new Object[] { Integer.valueOf(23) })).booleanValue();
      bool1 = bool2;
      bool2 = ((Boolean)this.cmSetInternalAntennaMethod.invoke(this.fmReceiverObj, new Object[] { Boolean.valueOf(true) })).booleanValue();
      return bool2;
    }
    catch (Exception localException)
    {
    }
    return bool1;
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

  private boolean getAudioSystem()
  {
    if (this.audioSystem == null);
    try
    {
      this.audioSystem = Class.forName("android.media.AudioSystem");
      return true;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      localClassNotFoundException.printStackTrace();
    }
    return false;
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
      if (!isMIUIROM())
        return;
      this.fmConfigClass = Class.forName("android.hardware.fmradio.FmConfig", true, this.mContext.getClassLoader());
      this.fmConfigObj = this.fmConfigClass.getDeclaredConstructor(null).newInstance(null);
      this.amObj = this.mContext.getSystemService("audio");
      this.amClass = this.amObj.getClass();
      this.fmRxEvCallbackAdaptorClass = Class.forName("android.hardware.fmradio.FmRxEvCallbacksAdaptor");
      this.fmRxEvCallbackAdaptorObj = this.fmRxEvCallbackAdaptorClass.getDeclaredConstructor(null).newInstance(null);
      this.fmRxEvCallbacksClass = Class.forName("android.hardware.fmradio.FmRxEvCallbacks");
      ClassLoader localClassLoader = this.fmRxEvCallbacksClass.getClassLoader();
      Class localClass = this.fmRxEvCallbacksClass;
      InvocationHandler localInvocationHandler = this.invocationHandler;
      this.fmRxEvCallbacksObj = Proxy.newProxyInstance(localClassLoader, new Class[] { localClass }, localInvocationHandler);
      this.fmReceiverClass = Class.forName("android.hardware.fmradio.FmReceiver", true, this.mContext.getClassLoader());
      this.fmReceiverObj = this.fmReceiverClass.getDeclaredConstructor(new Class[] { String.class, this.fmRxEvCallbackAdaptorClass }).newInstance(new Object[] { String.valueOf("/dev/radio0"), null });
      setUpInnerMethodsFromAM();
      setUpInnerMethodFromReceiver();
      setUpInnerMethodFromConfig();
      setUpInnerMethodFromTransceiver();
      buildDefaultFMConfig();
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      localClassNotFoundException.printStackTrace();
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
        localIllegalArgumentException.printStackTrace();
    }
    catch (InstantiationException localInstantiationException)
    {
      while (true)
        localInstantiationException.printStackTrace();
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      while (true)
        localIllegalAccessException.printStackTrace();
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      while (true)
        localInvocationTargetException.printStackTrace();
    }
    catch (SecurityException localSecurityException)
    {
      while (true)
        localSecurityException.printStackTrace();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private boolean initService()
  {
    Context localContext = this.context;
    return false;
  }

  private boolean isMIUIROM()
  {
    try
    {
      Context localContext = this.mContext.createPackageContext("com.miui.fmradio", 3);
      if (localContext != null)
        return true;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  private boolean isNeedRegisterClient()
  {
    this.needRegisterClient = true;
    return true;
  }

  private void registerClientReceiver()
  {
    if (this.isClientRegistered)
      return;
    try
    {
      boolean bool = ((Boolean)this.cmRegisterClientMethod.invoke(this.fmReceiverObj, new Object[] { this.fmRxEvCallbacksObj })).booleanValue();
      this.isClientRegistered = true;
      Log.e("test.java", "Miui register client ret = " + bool);
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

  private boolean searchStationListReceiver(int paramInt)
  {
    boolean bool1 = false;
    try
    {
      boolean bool2 = ((Boolean)this.cmSearchStationListMethod.invoke(this.fmReceiverObj, new Object[] { Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(paramInt), Integer.valueOf(0) })).booleanValue();
      bool1 = bool2;
      Log.e("fm", "searchStationListReceiver: " + bool1);
      return bool1;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
        localIllegalArgumentException.printStackTrace();
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      while (true)
        localIllegalAccessException.printStackTrace();
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      while (true)
        localInvocationTargetException.printStackTrace();
    }
  }

  private boolean searchStationsReceiver()
  {
    boolean bool1 = false;
    try
    {
      registerClientReceiver();
      boolean bool2 = ((Boolean)this.cmSearchStationsMethod.invoke(this.fmReceiverObj, new Object[] { Integer.valueOf(1), Integer.valueOf(3), Integer.valueOf(1) })).booleanValue();
      bool1 = bool2;
      Log.e("fm", "search: " + bool1);
      return bool1;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
        localIllegalArgumentException.printStackTrace();
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      while (true)
        localIllegalAccessException.printStackTrace();
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      while (true)
        localInvocationTargetException.printStackTrace();
    }
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

  private void setUpInnerMethodFromReceiver()
  {
    try
    {
      this.cmEnableMethod = this.fmReceiverClass.getDeclaredMethod("enable", new Class[] { this.fmConfigClass });
      this.cmDisableMethod = this.fmReceiverClass.getDeclaredMethod("disable", new Class[0]);
      this.cmGetTunedFrequencyMethod = this.fmReceiverClass.getDeclaredMethod("getTunedFrequency", new Class[0]);
      this.cmSearchStationsMethod = this.fmReceiverClass.getDeclaredMethod("searchStations", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      this.cmSearchStationListMethod = this.fmReceiverClass.getDeclaredMethod("searchStationList", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      this.cmCancelSearchMethod = this.fmReceiverClass.getDeclaredMethod("cancelSearch", new Class[0]);
      this.cmGetRssiMethod = this.fmReceiverClass.getDeclaredMethod("getRssi", new Class[0]);
      this.cmRegisterClientMethod = this.fmReceiverClass.getDeclaredMethod("registerClient", new Class[] { this.fmRxEvCallbacksClass });
      this.cmUnRegisterClientMethod = this.fmReceiverClass.getDeclaredMethod("unregisterClient", new Class[0]);
      this.cmSetMuteModeMethod = this.fmReceiverClass.getMethod("setMuteMode", new Class[] { Integer.TYPE });
      this.cmSetStereoModeMethod = this.fmReceiverClass.getDeclaredMethod("setStereoMode", new Class[] { Boolean.TYPE });
      this.cmSetPowerModeMethod = this.fmReceiverClass.getDeclaredMethod("setPowerMode", new Class[] { Integer.TYPE });
      this.cmRegisterRdsGroupProcessingMethod = this.fmReceiverClass.getDeclaredMethod("registerRdsGroupProcessing", new Class[] { Integer.TYPE });
      this.availableFirst = true;
      buildSetVolumMethod();
      return;
    }
    catch (SecurityException localSecurityException)
    {
      while (true)
        localSecurityException.printStackTrace();
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      while (true)
        localNoSuchMethodException.printStackTrace();
    }
  }

  private void setUpInnerMethodFromTransceiver()
  {
    if ((this.fmReceiverClass != null) && (this.fmReceiverClass.getGenericSuperclass() != null))
      this.fmReceiverSuperClass = this.fmReceiverClass.getSuperclass();
    try
    {
      this.cmAcquireMethod = this.fmReceiverSuperClass.getDeclaredMethod("acquire", new Class[] { String.class });
      this.cmAcquireMethod.setAccessible(true);
      this.cmSetStationMethod = this.fmReceiverSuperClass.getDeclaredMethod("setStation", new Class[] { Integer.TYPE });
      this.cmConfigureMethod = this.fmReceiverSuperClass.getDeclaredMethod("configure", new Class[] { this.fmConfigClass });
      this.cmSetInternalAntennaMethod = this.fmReceiverSuperClass.getMethod("setInternalAntenna", new Class[] { Boolean.TYPE });
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

  private void setUpInnerMethodsFromAM()
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

  private void setVolumReceiver(int paramInt)
  {
    if (this.cmSetVolumMethod == null)
      return;
    try
    {
      this.cmSetVolumMethod.invoke(this.fmReceiverObj, new Object[] { Integer.valueOf(paramInt) });
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

  private void showChannels()
  {
    int i = 0;
    while (true)
    {
      if (i >= this.channels.size())
        return;
      i += 1;
    }
  }

  private void startFM()
  {
    registerClientReceiver();
    Intent localIntent = new Intent("qualcomm.intent.action.FM");
    localIntent.putExtra("state", 1);
    this.mContext.getApplicationContext().sendBroadcast(localIntent);
    this.hasStart = true;
  }

  private void startMiUiService()
  {
  }

  private void stopFM()
  {
    unRegisterClientReceiver();
    Intent localIntent = new Intent("qualcomm.intent.action.FM");
    localIntent.putExtra("state", 0);
    this.mContext.getApplicationContext().sendBroadcast(localIntent);
    this.hasStart = false;
  }

  private boolean turnOffBlueTooth()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.mOpenBlueTooth)
    {
      BluetoothAdapter localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
      bool1 = bool2;
      if (localBluetoothAdapter != null)
      {
        bool1 = bool2;
        if (localBluetoothAdapter.isEnabled())
          bool1 = localBluetoothAdapter.disable();
      }
    }
    return bool1;
  }

  private boolean turnOnBlueTooth()
  {
    boolean bool2 = false;
    BluetoothAdapter localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    boolean bool1 = bool2;
    if (localBluetoothAdapter != null)
    {
      bool1 = bool2;
      if (!localBluetoothAdapter.isEnabled())
      {
        bool2 = localBluetoothAdapter.enable();
        bool1 = bool2;
        if (bool2)
        {
          this.mOpenBlueTooth = true;
          bool1 = bool2;
        }
      }
    }
    return bool1;
  }

  private void unRegisterClientReceiver()
  {
    if (!this.isClientRegistered)
      return;
    try
    {
      this.cmUnRegisterClientMethod.invoke(this.fmReceiverObj, new Object[0]);
      this.isClientRegistered = false;
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
    return "FmReceiver";
  }

  public int getVolume()
    throws Exception
  {
    return this.mDefaultVolum;
  }

  public boolean isAvailable()
  {
    return (this.availableFirst) && (this.availableLast);
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
    int i;
    if (paramBoolean)
      i = 1;
    try
    {
      while (true)
      {
        ((Boolean)this.cmSetMuteModeMethod.invoke(this.fmReceiverObj, new Object[] { Integer.valueOf(i) })).booleanValue();
        return;
        i = 0;
      }
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
    if (this.needRegisterClient)
    {
      Intent localIntent = new Intent("android.intent.action.FM");
      localIntent.putExtra("state", 0);
      this.mContext.sendBroadcast(localIntent);
      this.mIsPlay = false;
      return;
    }
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
        Message localMessage;
        if (!MiuiFMReceiver.this.mIsFMEnable)
        {
          if (MiuiFMReceiver.this.acquireReceiver("/dev/radio0").booleanValue())
          {
            MiuiFMReceiver.this.enableReceiver();
            MiuiFMReceiver.this.mIsFMEnable = true;
            localMessage = Message.obtain(MiuiFMReceiver.this.mHandler, 4, null);
            MiuiFMReceiver.this.mHandler.sendMessage(localMessage);
          }
        }
        else
        {
          MiuiFMReceiver.this.mIsPlay = false;
          MiuiFMReceiver.this.apiAMsetParameter("FM", "off");
          MiuiFMReceiver.this.mIsScan = true;
          MiuiFMReceiver.this.channels.clear();
        }
        try
        {
          MiuiFMReceiver.this.mute(true);
          if (!MiuiFMReceiver.this.searchStationsReceiver())
          {
            localMessage = Message.obtain(MiuiFMReceiver.this.mHandler, 1, null);
            MiuiFMReceiver.this.mHandler.sendMessage(localMessage);
          }
          return;
          localMessage = Message.obtain(MiuiFMReceiver.this.mHandler, 1, null);
          MiuiFMReceiver.this.mHandler.sendMessage(localMessage);
          return;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
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
    if (getAudioSystem())
    {
      setUpMethodFromAudioSystem();
      apiSetSpeakerOn(paramBoolean);
    }
    return 0;
  }

  public void setVolum(int paramInt)
  {
    if (!this.needRegisterClient)
      setVolumReceiver(paramInt);
    this.mDefaultVolum = paramInt;
  }

  public void setVolume(int paramInt)
    throws Exception
  {
    setVolum(paramInt);
  }

  public int tune(int paramInt)
  {
    boolean bool1;
    if (!this.mIsFMEnable)
      if (this.needRegisterClient)
      {
        bool1 = acquireReceiver("/dev/radio0").booleanValue();
        enableReceiver();
      }
    while (true)
    {
      boolean bool2 = bool1;
      Message localMessage;
      if (bool1)
      {
        this.mIsFMEnable = true;
        localMessage = Message.obtain(this.mHandler, 4, null);
        this.mHandler.sendMessage(localMessage);
        bool2 = bool1;
      }
      startFM();
      try
      {
        mute(false);
        if (bool2)
        {
          this.mIsPlay = true;
          setStationReceiver(paramInt);
          apiAMsetParameter("FM", "on");
          localMessage = Message.obtain(this.mHandler, 6, null);
          localMessage.arg1 = paramInt;
          this.mHandler.sendMessage(localMessage);
          return 0;
          bool1 = enableReceiver();
          continue;
          bool2 = true;
        }
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }
    return -1;
  }

  public void turnOff()
    throws Exception
  {
    if (this.needRegisterClient)
      turnOffBlueTooth();
    off();
  }

  public int turnOn()
    throws Exception
  {
    if (this.needRegisterClient)
      turnOnBlueTooth();
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
 * Qualified Name:     fm.qingting.qtradio.miui2.MiuiFMReceiver
 * JD-Core Version:    0.6.2
 */