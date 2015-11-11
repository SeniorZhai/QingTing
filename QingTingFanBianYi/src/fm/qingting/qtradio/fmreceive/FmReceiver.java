package fm.qingting.qtradio.fmreceive;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
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

public class FmReceiver extends FMDriver
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
  private static final String TAG = "FmReceiver";
  private Class ServiceManagerClass;
  private Class amClass;
  private Object amObj;
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
  private Method cmSearchStationListMethod;
  private Method cmSearchStationsMethod;
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
  private Class huaweiFM = null;
  private Class internalServiceClass;
  private Object internalServiceObject;
  private InvocationHandler invocationHandler = new InvocationHandler()
  {
    public Object invoke(Object paramAnonymousObject, Method paramAnonymousMethod, Object[] paramAnonymousArrayOfObject)
      throws Throwable
    {
      if ("FmRxEvEnableReceiver".equals(paramAnonymousMethod.getName()))
        Thread.sleep(5000L);
      do
      {
        do
        {
          int i;
          do
          {
            do
              return null;
            while ("FmRxEvDisableReceiver".equals(paramAnonymousMethod.getName()));
            if (!"FmRxEvRadioTuneStatus".equals(paramAnonymousMethod.getName()))
              break;
            i = ((Integer)paramAnonymousArrayOfObject[0]).intValue();
          }
          while (FmReceiver.this.inChannelList(i));
          FmReceiver.this.channels.add(Integer.valueOf(i));
          paramAnonymousObject = Message.obtain(FmReceiver.this.mHandler, 2, null);
          paramAnonymousObject.arg1 = i;
          FmReceiver.this.mHandler.sendMessage(paramAnonymousObject);
          return null;
        }
        while (("FmRxEvRdsLockStatus".equals(paramAnonymousMethod.getName())) || ("FmRxEvStereoStatus".equals(paramAnonymousMethod.getName())) || ("FmRxEvServiceAvailable".equals(paramAnonymousMethod.getName())) || ("FmRxEvSearchInProgress".equals(paramAnonymousMethod.getName())));
        if ("FmRxEvSearchComplete".equals(paramAnonymousMethod.getName()))
        {
          paramAnonymousObject = Message.obtain(FmReceiver.this.mHandler, 1, null);
          FmReceiver.this.mHandler.sendMessage(paramAnonymousObject);
          return null;
        }
      }
      while (("FmRxEvSearchListComplete".equals(paramAnonymousMethod.getName())) || ("FmRxEvRdsGroupData".equals(paramAnonymousMethod.getName())) || ("FmRxEvRdsPsInfo".equals(paramAnonymousMethod.getName())) || ("FmRxEvRdsRtInfo".equals(paramAnonymousMethod.getName())) || (!"FmRxEvRdsAfInfo".equals(paramAnonymousMethod.getName())));
      return null;
    }
  };
  private Class listenerClass;
  private Context mContext;
  private int mCurrentFreq = 0;
  private int mDefaultVolum = 9;
  private final Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (FmReceiver.this.onFMEventListener == null)
        return;
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 1:
        FmReceiver.this.onFMEventListener.onScanComplete(true);
        return;
      case 2:
        FmReceiver.this.onFMEventListener.onChannelFound(paramAnonymousMessage.arg1);
        return;
      case 3:
        FmReceiver.this.onFMEventListener.onFMOff();
        return;
      case 4:
        FmReceiver.this.onFMEventListener.onFMOn();
        return;
      case 5:
        FmReceiver.this.onFMEventListener.onScanStarted();
        return;
      case 6:
      }
      FmReceiver.this.onFMEventListener.onTune(paramAnonymousMessage.arg1);
    }
  };
  private boolean mIsFMEnable = false;
  private boolean mIsPlay = false;
  private boolean mIsScan = false;
  private boolean mMute = false;
  private boolean mOpenBlueTooth = false;
  private boolean needRegisterClient = false;
  private IFMEventListener onFMEventListener;
  private Method setChSpacingMethod;
  private Method setEmphasisMethod;
  private Method setLowerLimitMedthod;
  private Method setRadioBandMethod;
  private Method setRdsStdMethod;
  private Method setUpperLimitMethod;

  public FmReceiver(Context paramContext)
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
      if (acquireReceiver("/dev/radio0").booleanValue())
      {
        enableReceiver();
        this.mIsFMEnable = true;
        Message localMessage = Message.obtain(this.mHandler, 4, null);
        this.mHandler.sendMessage(localMessage);
      }
    while (true)
      return this.mIsFMEnable;
  }

  private void apiRemoveListener(Object paramObject)
  {
  }

  private int apiScan()
  {
    if (!searchStationsReceiver())
      return -1;
    return getTunedFrequencyReceiver();
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

  private void buildSetVolumMethod()
  {
    try
    {
      this.cmSetVolumMethod = this.fmReceiverClass.getDeclaredMethod("setVolum", new Class[] { Integer.TYPE });
      this.availableLast = true;
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
      if (isNeedRegisterClient())
        registerClientReceiver();
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      localClassNotFoundException.printStackTrace();
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return;
    }
    catch (InstantiationException localInstantiationException)
    {
      localInstantiationException.printStackTrace();
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
      return;
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException.printStackTrace();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private boolean isNeedRegisterClient()
  {
    Object localObject = null;
    try
    {
      Context localContext = this.mContext.createPackageContext("com.huawei.android.FMRadio", 3);
      localObject = localContext;
      this.availableLast = true;
      localObject = localContext;
      if (localObject == null)
        return false;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        localNameNotFoundException.printStackTrace();
      this.needRegisterClient = true;
    }
    return true;
  }

  private void registerClientReceiver()
  {
    try
    {
      this.cmRegisterClientMethod.invoke(this.fmReceiverObj, new Object[] { this.fmRxEvCallbacksObj });
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

  private boolean searchStationsReceiver()
  {
    try
    {
      boolean bool = ((Boolean)this.cmSearchStationsMethod.invoke(this.fmReceiverObj, new Object[] { Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1) })).booleanValue();
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

  private void setUpInnerMethodFromReceiver()
  {
    try
    {
      this.cmEnableMethod = this.fmReceiverClass.getDeclaredMethod("enable", new Class[] { this.fmConfigClass });
      this.cmDisableMethod = this.fmReceiverClass.getDeclaredMethod("disable", new Class[0]);
      this.cmGetTunedFrequencyMethod = this.fmReceiverClass.getDeclaredMethod("getTunedFrequency", new Class[0]);
      this.cmSearchStationsMethod = this.fmReceiverClass.getDeclaredMethod("searchStations", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      this.cmCancelSearchMethod = this.fmReceiverClass.getDeclaredMethod("cancelSearch", new Class[0]);
      this.cmGetRssiMethod = this.fmReceiverClass.getDeclaredMethod("getRssi", new Class[0]);
      this.cmRegisterClientMethod = this.fmReceiverClass.getDeclaredMethod("registerClient", new Class[] { this.fmRxEvCallbacksClass });
      this.cmUnRegisterClientMethod = this.fmReceiverClass.getDeclaredMethod("unregisterClient", new Class[0]);
      this.cmSetStereoModeMethod = this.fmReceiverClass.getDeclaredMethod("setStereoMode", new Class[] { Boolean.TYPE });
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
    try
    {
      this.cmUnRegisterClientMethod.invoke(this.fmReceiverObj, new Object[0]);
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
    if (paramBoolean)
    {
      this.mMute = true;
      if (this.needRegisterClient)
      {
        Intent localIntent = new Intent("android.intent.action.FM");
        localIntent.putExtra("state", 0);
        this.mContext.sendBroadcast(localIntent);
      }
    }
    else
    {
      return;
    }
    off();
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
        if (!FmReceiver.this.mIsFMEnable)
        {
          if (FmReceiver.this.acquireReceiver("/dev/radio0").booleanValue())
          {
            FmReceiver.this.enableReceiver();
            FmReceiver.this.mIsFMEnable = true;
            localMessage = Message.obtain(FmReceiver.this.mHandler, 4, null);
            FmReceiver.this.mHandler.sendMessage(localMessage);
          }
        }
        else
        {
          FmReceiver.this.mIsPlay = false;
          FmReceiver.this.apiAMsetParameter("FM", "off");
          FmReceiver.this.mIsScan = true;
          FmReceiver.this.channels.clear();
          if (!FmReceiver.this.needRegisterClient)
            break label175;
          if (!FmReceiver.this.searchStationsReceiver())
          {
            localMessage = Message.obtain(FmReceiver.this.mHandler, 1, null);
            FmReceiver.this.mHandler.sendMessage(localMessage);
          }
          return;
        }
        Message localMessage = Message.obtain(FmReceiver.this.mHandler, 1, null);
        FmReceiver.this.mHandler.sendMessage(localMessage);
        return;
        label175: int i = FmReceiver.this.apiScan();
        if ((-1 == i) || (!FmReceiver.this.mIsScan));
        while (true)
        {
          localMessage = Message.obtain(FmReceiver.this.mHandler, 1, null);
          FmReceiver.this.mHandler.sendMessage(localMessage);
          return;
          i *= 10;
          if (!FmReceiver.this.inChannelList(i))
          {
            localMessage = Message.obtain(FmReceiver.this.mHandler, 2, null);
            localMessage.arg1 = i;
            FmReceiver.this.mHandler.sendMessage(localMessage);
            FmReceiver.this.channels.add(Integer.valueOf(i));
            if (i < 108001)
              break;
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
    boolean bool2;
    Object localObject;
    if (!this.mIsFMEnable)
      if (this.needRegisterClient)
      {
        bool1 = acquireReceiver("/dev/radio0").booleanValue();
        enableReceiver();
        bool2 = bool1;
        if (bool1)
        {
          this.mIsFMEnable = true;
          localObject = Message.obtain(this.mHandler, 4, null);
          this.mHandler.sendMessage((Message)localObject);
          bool2 = bool1;
        }
        label64: if (!bool2)
          break label217;
        this.mIsPlay = true;
        if (this.needRegisterClient)
          break label208;
        setVolumReceiver(this.mDefaultVolum);
        setStationReceiver(paramInt / 10);
      }
    while (true)
    {
      Log.e("test.java", "tune, freq = " + paramInt);
      apiAMsetParameter("FM", "on");
      localObject = Message.obtain(this.mHandler, 6, null);
      ((Message)localObject).arg1 = paramInt;
      this.mHandler.sendMessage((Message)localObject);
      if (this.needRegisterClient)
      {
        localObject = new Intent("android.intent.action.FM");
        ((Intent)localObject).putExtra("state", 1);
        this.mContext.sendBroadcast((Intent)localObject);
      }
      return 0;
      bool1 = enableReceiver();
      break;
      bool2 = true;
      break label64;
      label208: setStationReceiver(paramInt);
    }
    label217: return -1;
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
 * Qualified Name:     fm.qingting.qtradio.fmreceive.FmReceiver
 * JD-Core Version:    0.6.2
 */