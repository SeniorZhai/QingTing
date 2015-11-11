package fm.qingting.qtradio.fmdriver;

import android.content.Context;
import java.util.ArrayList;

public abstract class FMDriver
{
  public static int STREAM_FM = 3;
  protected Context context;

  public FMDriver(Context paramContext)
  {
    this.context = paramContext;
  }

  public abstract void cancelScanning()
    throws Exception;

  public int getAudioStreamType()
  {
    return 3;
  }

  public abstract ArrayList<Integer> getAvailableChannels();

  public abstract int getCurrentChannel()
    throws Exception;

  public abstract int getCurrentRSSI()
    throws Exception;

  public abstract String getName();

  public abstract int getVolume()
    throws Exception;

  public abstract boolean isAvailable();

  public abstract boolean isMute()
    throws Exception;

  public abstract boolean isOn()
    throws Exception;

  public abstract boolean isPaused()
    throws Exception;

  public abstract boolean isScanning()
    throws Exception;

  public abstract boolean isSpeakerOn()
    throws Exception;

  public abstract void mute(boolean paramBoolean)
    throws Exception;

  public abstract void pause()
    throws Exception;

  public abstract void registerFMEventListener(IFMEventListener paramIFMEventListener);

  public abstract void scan()
    throws Exception;

  public abstract void setLiveAudioQualityCallback(boolean paramBoolean, int paramInt)
    throws Exception;

  public abstract int setSpeakerOn(boolean paramBoolean)
    throws Exception;

  public abstract void setVolume(int paramInt)
    throws Exception;

  public abstract int tune(int paramInt)
    throws Exception;

  public abstract void turnOff()
    throws Exception;

  public abstract int turnOn()
    throws Exception;

  public abstract void unregisterFMEventListener();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.fmdriver.FMDriver
 * JD-Core Version:    0.6.2
 */