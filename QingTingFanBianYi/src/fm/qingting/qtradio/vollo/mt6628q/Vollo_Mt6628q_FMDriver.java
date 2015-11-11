package fm.qingting.qtradio.vollo.mt6628q;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.mediatek.FMRadio.FMRadioNative;
import fm.qingting.qtradio.fmdriver.FMDriver;
import fm.qingting.qtradio.fmdriver.IFMEventListener;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Vollo_Mt6628q_FMDriver extends FMDriver
{
  private static final int FORNUMS = 6;
  private static final String FmSource = "MEDIATEK://MEDIAPLAYER_PLAYERTYPE_FM";
  private static final float LowestFreq = 87.0F;
  private static final int MsgChannelFound = 2;
  private static final int MsgFmOff = 3;
  private static final int MsgFmOn = 4;
  private static final int MsgScanComplete = 1;
  private static final int MsgScanStarted = 5;
  private static final int MsgTune = 6;
  public static final int STREAM_FM = 10;
  public static final String Tag = "Mt6628q_FM";
  public static final String driverName = "vollo Mt6628q_FM driver";
  private AudioManager.OnAudioFocusChangeListener audioListener = new AudioManager.OnAudioFocusChangeListener()
  {
    public void onAudioFocusChange(int paramAnonymousInt)
    {
      switch (paramAnonymousInt)
      {
      case 0:
      default:
        return;
      case -2:
      case -1:
        Log.e("Mt6628q_FM", "onAudioFocusChange:Loss disable fm audio [begin]");
        Vollo_Mt6628q_FMDriver.this.enableFMAudio(false);
        Log.e("Mt6628q_FM", "onAudioFocusChange:Loss disable fm audio [end]");
        return;
      case 1:
      }
      Log.e("Mt6628q_FM", "onAudioFocusChange:gain enable fm audio [begin]");
      Vollo_Mt6628q_FMDriver.this.enableFMAudio(true);
      Log.e("Mt6628q_FM", "onAudioFocusChange:gain enable fm audio [end]");
    }
  };
  private Class audioSystemClass = null;
  private float currentFreq = 0.0F;
  private ArrayList<Integer> freqList = new ArrayList();
  private Method getForceUse = null;
  private Handler handler = null;
  private boolean isDeviceOpened = false;
  private boolean isMute = false;
  private boolean isPaused = false;
  private boolean isPoweredUp = false;
  private boolean isScanning = false;
  private boolean isSpeakerOn = false;
  private List<IFMEventListener> listenerList = new ArrayList();
  private AudioManager mAm = null;
  private MediaPlayer mPlayer = null;
  private MediaPlayer.OnErrorListener playerErrorListener = new MediaPlayer.OnErrorListener()
  {
    public boolean onError(MediaPlayer arg1, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      if (100 == paramAnonymousInt1)
      {
        synchronized (Vollo_Mt6628q_FMDriver.this.mPlayer)
        {
          Vollo_Mt6628q_FMDriver.this.mPlayer.release();
          Vollo_Mt6628q_FMDriver.this.mPlayer = new MediaPlayer();
        }
        try
        {
          Vollo_Mt6628q_FMDriver.this.mPlayer.setDataSource("MEDIATEK://MEDIAPLAYER_PLAYERTYPE_FM");
          Vollo_Mt6628q_FMDriver.this.mPlayer.setAudioStreamType(10);
          Vollo_Mt6628q_FMDriver.this.mPlayer.prepare();
          Vollo_Mt6628q_FMDriver.this.mPlayer.start();
        }
        catch (IOException localIOException)
        {
          return false;
          localObject = finally;
          throw localObject;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          return false;
        }
        catch (IllegalStateException localIllegalStateException)
        {
          return false;
        }
      }
      return true;
    }
  };
  private ScanThread scanThread = null;
  private Method setForceUseMethod = null;

  public Vollo_Mt6628q_FMDriver(Context paramContext)
  {
    super(paramContext);
    FMRadioNative.load();
    loadAudioSystem();
    this.handler = new MsgHandler();
  }

  private boolean enableFMAudio(boolean paramBoolean)
  {
    Log.e("Mt6628q_FM", "enableFMAudio [begin]:" + paramBoolean);
    setUpPlayer();
    MediaPlayer localMediaPlayer = this.mPlayer;
    if (paramBoolean);
    while (true)
    {
      try
      {
        if (1 != this.mAm.requestAudioFocus(this.audioListener, 10, 1))
          break label220;
        this.mAm.setWiredHeadsetOn(true);
        Log.e("Mt6628q_FM", "enableFMAudio gained focus[begin]:" + paramBoolean);
        if (!this.mPlayer.isPlaying())
        {
          Log.e("Mt6628q_FM", "enableFMAudio gained focus[begin] is playing:" + paramBoolean);
          try
          {
            Log.e("Mt6628q_FM", "prepare [begin]");
            this.mPlayer.prepare();
            Log.e("Mt6628q_FM", "prepare [end]");
            this.mPlayer.start();
            Log.e("Mt6628q_FM", "enableFMAudio [end]" + paramBoolean);
            return true;
          }
          catch (Exception localException)
          {
            Log.e("Mt6628q_FM", "prepare [exception]");
            return false;
          }
        }
      }
      finally
      {
      }
      Log.e("Mt6628q_FM", "enableFMAudio gained focus[begin] not playing:" + paramBoolean);
      continue;
      label220: Log.e("Mt6628q_FM", "enableFMAudio gained focus [failed]");
      return false;
      if ((this.mPlayer != null) && (this.mPlayer.isPlaying()))
      {
        Log.e("Mt6628q_FM", "enableFMAudio stop mplayer");
        this.mPlayer.stop();
      }
    }
  }

  private float getFreqFromExternalFormat(int paramInt)
  {
    float f2 = paramInt;
    float f1 = f2;
    if (f2 > 100000.0F)
      f1 = f2 / 1000.0F;
    f2 = f1;
    if (f1 > 10000.0F)
      f2 = f1 / 100.0F;
    f1 = f2;
    if (f2 > 870.0F)
      f1 = f2 / 10.0F;
    return f1;
  }

  private void informChannelFound(int paramInt)
  {
    Iterator localIterator = this.listenerList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((IFMEventListener)localIterator.next()).onChannelFound(paramInt);
    }
  }

  private void informOff()
  {
    Iterator localIterator = this.listenerList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((IFMEventListener)localIterator.next()).onFMOff();
    }
  }

  private void informOn()
  {
    Iterator localIterator = this.listenerList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((IFMEventListener)localIterator.next()).onFMOn();
    }
  }

  private void informScanComplete()
  {
    Iterator localIterator = this.listenerList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((IFMEventListener)localIterator.next()).onScanComplete(true);
    }
  }

  private void informScanStart()
  {
    Iterator localIterator = this.listenerList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((IFMEventListener)localIterator.next()).onScanStarted();
    }
  }

  private void informTuned(int paramInt)
  {
    Iterator localIterator = this.listenerList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((IFMEventListener)localIterator.next()).onTune(paramInt);
    }
  }

  private void loadAudioSystem()
  {
    try
    {
      this.audioSystemClass = Class.forName("android.media.AudioSystem");
      this.setForceUseMethod = this.audioSystemClass.getMethod("setForceUse", new Class[] { Integer.TYPE, Integer.TYPE });
      this.getForceUse = this.audioSystemClass.getMethod("getForceUse", new Class[] { Integer.TYPE });
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      localClassNotFoundException.printStackTrace();
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
    }
  }

  private boolean setUpPlayer()
  {
    if (this.mPlayer == null)
    {
      this.mPlayer = new MediaPlayer();
      synchronized (this.mPlayer)
      {
        try
        {
          this.mPlayer.setDataSource("MEDIATEK://MEDIAPLAYER_PLAYERTYPE_FM");
          this.mPlayer.setAudioStreamType(10);
          this.mPlayer.setOnErrorListener(this.playerErrorListener);
        }
        catch (Exception localException)
        {
          return false;
        }
      }
    }
    return true;
  }

  private int toHzFormat(float paramFloat)
  {
    return (int)(1000.0F * paramFloat);
  }

  public void cancelScanning()
    throws Exception
  {
    if (this.isScanning)
    {
      FMRadioNative.stopscan();
      this.isScanning = false;
    }
  }

  public int getAudioStreamType()
  {
    return 10;
  }

  public ArrayList<Integer> getAvailableChannels()
  {
    return this.freqList;
  }

  public int getCurrentChannel()
    throws Exception
  {
    return (int)(this.currentFreq * 10.0F);
  }

  public int getCurrentRSSI()
    throws Exception
  {
    return FMRadioNative.readRssi();
  }

  public String getName()
  {
    return "vollo Mt6628q_FM driver";
  }

  public int getVolume()
    throws Exception
  {
    return this.mAm.getStreamVolume(10);
  }

  public boolean isAvailable()
  {
    return FMRadioNative.isAvailable();
  }

  public boolean isMute()
    throws Exception
  {
    return this.isMute;
  }

  public boolean isOn()
    throws Exception
  {
    return this.isDeviceOpened;
  }

  public boolean isPaused()
    throws Exception
  {
    return this.isPaused;
  }

  public boolean isScanning()
    throws Exception
  {
    return this.isScanning;
  }

  public boolean isSpeakerOn()
    throws Exception
  {
    return this.isSpeakerOn;
  }

  public void mute(boolean paramBoolean)
    throws Exception
  {
    if (FMRadioNative.setmute(paramBoolean) != 0)
      this.isMute = paramBoolean;
  }

  public void pause()
    throws Exception
  {
    synchronized (this.mPlayer)
    {
      if (this.mPlayer.isPlaying())
      {
        this.mPlayer.stop();
        this.isPaused = true;
      }
      return;
    }
  }

  public void registerFMEventListener(IFMEventListener paramIFMEventListener)
  {
    if (!this.listenerList.contains(paramIFMEventListener))
      this.listenerList.add(paramIFMEventListener);
  }

  public void scan()
    throws Exception
  {
    if ((!this.isScanning) && (this.scanThread == null))
    {
      this.scanThread = new ScanThread();
      this.scanThread.start();
    }
  }

  public void setLiveAudioQualityCallback(boolean paramBoolean, int paramInt)
    throws Exception
  {
  }

  public int setSpeakerOn(boolean paramBoolean)
    throws Exception
  {
    int i = 0;
    Method localMethod = this.setForceUseMethod;
    Class localClass = this.audioSystemClass;
    if (paramBoolean)
      i = 1;
    localMethod.invoke(localClass, new Object[] { Integer.valueOf(5), Integer.valueOf(i) });
    this.isSpeakerOn = paramBoolean;
    return 1;
  }

  public void setVolume(int paramInt)
    throws Exception
  {
    this.mAm.setStreamVolume(10, paramInt, 0);
  }

  public int tune(int paramInt)
    throws Exception
  {
    float f = getFreqFromExternalFormat(paramInt);
    boolean bool;
    if (!this.isPoweredUp)
    {
      bool = FMRadioNative.powerup(f);
      this.isPoweredUp = true;
    }
    while ((!bool) || (!enableFMAudio(true)))
    {
      return 0;
      bool = FMRadioNative.tune(f);
    }
    this.isPaused = false;
    FMRadioNative.setmute(false);
    this.currentFreq = f;
    Message localMessage = new Message();
    localMessage.what = 6;
    localMessage.arg1 = paramInt;
    this.handler.sendMessage(localMessage);
    return 1;
  }

  public void turnOff()
    throws Exception
  {
    FMRadioNative.powerdown(0);
    FMRadioNative.closedev();
    synchronized (this.mPlayer)
    {
      this.mPlayer.release();
      this.mPlayer = null;
      this.isDeviceOpened = false;
      this.isPoweredUp = false;
      ??? = new Message();
      ((Message)???).what = 3;
      this.handler.sendMessage((Message)???);
      return;
    }
  }

  public int turnOn()
    throws Exception
  {
    if (this.isDeviceOpened)
      return 1;
    if (!FMRadioNative.opendev())
      return 0;
    setSpeakerOn(false);
    FMRadioNative.rdsset(false);
    setUpPlayer();
    this.mAm.setStreamVolume(10, this.mAm.getStreamMaxVolume(10) / 2, 0);
    this.isDeviceOpened = true;
    Message localMessage = new Message();
    localMessage.what = 4;
    this.handler.sendMessage(localMessage);
    return 1;
  }

  public void unregisterFMEventListener()
  {
    this.listenerList.clear();
  }

  public class MsgHandler extends Handler
  {
    public MsgHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 5:
        Vollo_Mt6628q_FMDriver.this.informScanStart();
        return;
      case 2:
        Vollo_Mt6628q_FMDriver.this.informChannelFound(paramMessage.arg1);
        return;
      case 1:
        Vollo_Mt6628q_FMDriver.this.informScanComplete();
        return;
      case 4:
        Vollo_Mt6628q_FMDriver.this.informOn();
        return;
      case 3:
        Vollo_Mt6628q_FMDriver.this.informOff();
        return;
      case 6:
      }
      Vollo_Mt6628q_FMDriver.this.informTuned(paramMessage.arg1);
    }
  }

  public class ScanThread extends Thread
  {
    public ScanThread()
    {
    }

    public void run()
    {
      float f1 = 87.0F;
      Vollo_Mt6628q_FMDriver.this.isScanning = true;
      Vollo_Mt6628q_FMDriver.this.freqList.clear();
      if (!Vollo_Mt6628q_FMDriver.this.isPoweredUp)
      {
        if (!FMRadioNative.powerup(90.0F))
        {
          Vollo_Mt6628q_FMDriver.this.informScanComplete();
          return;
        }
        Vollo_Mt6628q_FMDriver.this.isPoweredUp = true;
      }
      Message localMessage = new Message();
      localMessage.what = 5;
      Vollo_Mt6628q_FMDriver.this.handler.sendMessage(localMessage);
      while (true)
      {
        float f2 = FMRadioNative.seek(f1, true);
        if ((f2 < f1) || (f2 < 87.0F))
        {
          Vollo_Mt6628q_FMDriver.this.isScanning = false;
          localMessage = new Message();
          localMessage.what = 1;
          Vollo_Mt6628q_FMDriver.this.handler.sendMessage(localMessage);
          Vollo_Mt6628q_FMDriver.this.scanThread = null;
          return;
        }
        Vollo_Mt6628q_FMDriver.this.freqList.add(Integer.valueOf(Vollo_Mt6628q_FMDriver.this.toHzFormat(f2)));
        localMessage = new Message();
        localMessage.arg1 = Vollo_Mt6628q_FMDriver.this.toHzFormat(f2);
        localMessage.what = 2;
        Vollo_Mt6628q_FMDriver.this.handler.sendMessage(localMessage);
        f1 = f2;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.vollo.mt6628q.Vollo_Mt6628q_FMDriver
 * JD-Core Version:    0.6.2
 */