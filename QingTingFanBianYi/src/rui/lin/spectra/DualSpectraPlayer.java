package rui.lin.spectra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DualSpectraPlayer
{
  protected static int sLogLevel = 3;
  protected static String sLogTag = "DualSpectraPlayer";
  protected static Logger sLogger = new Logger(sLogLevel, sLogTag);
  protected DualSpectraEventFilter mEventFilter;
  protected List<Spectra.SpectraEventListener> mEventListeners;
  protected boolean mIsInitialized;
  protected Spectra mLoadedPlayer;
  protected Spectra mMainPlayer;
  protected Spectra mSpectraA = new Spectra();
  protected Spectra mSpectraB = new Spectra();
  protected Spectra mVacantPlayer;

  public DualSpectraPlayer()
  {
    if ((this.mSpectraA.queryState() != Spectra.State.UNINITIALIZED) && (this.mSpectraB.queryState() != Spectra.State.UNINITIALIZED))
    {
      this.mVacantPlayer = this.mSpectraA;
      this.mLoadedPlayer = null;
      this.mMainPlayer = null;
      this.mEventListeners = Collections.synchronizedList(new ArrayList());
      this.mEventFilter = new DualSpectraEventFilter();
      this.mSpectraA.addEventListener(this.mEventFilter);
      this.mSpectraB.addEventListener(this.mEventFilter);
      this.mIsInitialized = true;
      sLogger.info("----- player initialized ------", new Object[0]);
      return;
    }
    this.mSpectraA = null;
    this.mSpectraB = null;
    this.mVacantPlayer = null;
    this.mLoadedPlayer = null;
    this.mMainPlayer = null;
    sLogger.error("----- player initialization failed ------", new Object[0]);
    this.mIsInitialized = false;
  }

  public boolean addEventListener(Spectra.SpectraEventListener paramSpectraEventListener)
  {
    if (this.mIsInitialized)
      return this.mEventListeners.add(paramSpectraEventListener);
    return false;
  }

  public void disableOpt()
  {
    this.mSpectraA.setShouldOpt(false);
    this.mSpectraB.setShouldOpt(false);
  }

  public void enableOpt()
  {
    this.mSpectraA.setShouldOpt(true);
    this.mSpectraB.setShouldOpt(true);
  }

  public void interrupt(boolean paramBoolean)
  {
    this.mSpectraA.interrupt(paramBoolean);
    this.mSpectraB.interrupt(paramBoolean);
  }

  public boolean interrupt()
  {
    return this.mSpectraA.interrupt();
  }

  public boolean isLiveStream()
  {
    if (this.mMainPlayer != null)
      return this.mMainPlayer.isLiveStream();
    return this.mVacantPlayer.isLiveStream();
  }

  public boolean load(List<String> paramList)
  {
    boolean bool = false;
    try
    {
      sLogger.info("#-> load", new Object[0]);
      if (this.mIsInitialized)
      {
        this.mLoadedPlayer = this.mVacantPlayer;
        bool = this.mLoadedPlayer.load(paramList);
      }
      return bool;
    }
    finally
    {
    }
    throw paramList;
  }

  public boolean pause()
  {
    boolean bool2 = false;
    try
    {
      sLogger.info("#-> pause", new Object[0]);
      boolean bool1 = bool2;
      if (this.mIsInitialized)
      {
        bool1 = bool2;
        if (this.mMainPlayer != null)
          bool1 = this.mMainPlayer.pause();
      }
      return bool1;
    }
    finally
    {
    }
  }

  public boolean play()
  {
    boolean bool2 = false;
    try
    {
      sLogger.info("#-> play", new Object[0]);
      boolean bool1 = bool2;
      if (this.mIsInitialized)
      {
        bool1 = bool2;
        if (this.mLoadedPlayer != null)
        {
          if (this.mMainPlayer != null)
            this.mMainPlayer.stop();
          this.mMainPlayer = this.mLoadedPlayer;
          if (this.mVacantPlayer == this.mMainPlayer)
            switchVacantPlayer();
          bool1 = this.mMainPlayer.play();
        }
      }
      return bool1;
    }
    finally
    {
    }
  }

  public int queryBitRate()
  {
    if (this.mMainPlayer != null)
      return this.mMainPlayer.queryBitRate();
    return this.mVacantPlayer.queryBitRate();
  }

  public int queryChannels()
  {
    if (this.mMainPlayer != null)
      return this.mMainPlayer.queryChannels();
    return this.mVacantPlayer.queryChannels();
  }

  public String queryCompressionFormat()
  {
    if (this.mMainPlayer != null)
      return this.mMainPlayer.queryCompressionFormat();
    return this.mVacantPlayer.queryCompressionFormat();
  }

  public String queryContainerFormat()
  {
    if (this.mMainPlayer != null)
      return this.mMainPlayer.queryContainerFormat();
    return this.mVacantPlayer.queryContainerFormat();
  }

  public int queryDuration()
  {
    if (this.mMainPlayer != null)
      return this.mMainPlayer.queryDuration();
    return this.mVacantPlayer.queryDuration();
  }

  public byte[] queryMetadata()
  {
    if (this.mMainPlayer != null)
      return this.mMainPlayer.queryMetadata();
    return this.mVacantPlayer.queryMetadata();
  }

  public int queryPosition()
  {
    if (this.mMainPlayer != null)
      return this.mMainPlayer.queryPosition();
    return this.mVacantPlayer.queryPosition();
  }

  public ArrayList<String> queryPreloadedUrls()
  {
    if (this.mLoadedPlayer != null)
      return this.mLoadedPlayer.queryUrls();
    return null;
  }

  public String querySampleFormat()
  {
    if (this.mMainPlayer != null)
      return this.mMainPlayer.querySampleFormat();
    return this.mVacantPlayer.querySampleFormat();
  }

  public int querySampleRate()
  {
    if (this.mMainPlayer != null)
      return this.mMainPlayer.querySampleRate();
    return this.mVacantPlayer.querySampleRate();
  }

  public String querySelectedUrl()
  {
    if (this.mMainPlayer != null)
      return this.mMainPlayer.querySelectedUrl();
    return this.mVacantPlayer.querySelectedUrl();
  }

  public Spectra.State queryState()
  {
    if (this.mMainPlayer != null);
    for (Spectra.State localState1 = this.mMainPlayer.queryState(); ; localState1 = this.mVacantPlayer.queryState())
    {
      Spectra.State localState2 = localState1;
      if (localState1 == Spectra.State.LOADED)
        localState2 = Spectra.State.STOPPED;
      return localState2;
    }
  }

  public ArrayList<String> queryUrls()
  {
    if (this.mMainPlayer != null)
      return this.mMainPlayer.queryUrls();
    return this.mVacantPlayer.queryUrls();
  }

  public boolean reconnect()
  {
    boolean bool2 = false;
    try
    {
      sLogger.info("#-> reconnect", new Object[0]);
      boolean bool1 = bool2;
      if (this.mIsInitialized)
      {
        bool1 = bool2;
        if (this.mMainPlayer != null)
        {
          if (!this.mMainPlayer.isLiveStream())
            break label67;
          this.mMainPlayer.stop();
        }
      }
      label67: int i;
      for (bool1 = this.mMainPlayer.play(); ; bool1 = this.mMainPlayer.seek(i))
      {
        return bool1;
        i = this.mMainPlayer.queryPosition();
        this.mMainPlayer.stop();
        this.mMainPlayer.play();
      }
    }
    finally
    {
    }
  }

  public boolean removeEventListener(Spectra.SpectraEventListener paramSpectraEventListener)
  {
    if (this.mIsInitialized)
      return this.mEventListeners.remove(paramSpectraEventListener);
    return false;
  }

  public boolean resume()
  {
    boolean bool2 = false;
    try
    {
      sLogger.info("#-> resume", new Object[0]);
      boolean bool1 = bool2;
      if (this.mIsInitialized)
      {
        bool1 = bool2;
        if (this.mMainPlayer != null)
          bool1 = this.mMainPlayer.resume();
      }
      return bool1;
    }
    finally
    {
    }
  }

  public boolean seek(int paramInt)
  {
    boolean bool2 = false;
    try
    {
      sLogger.info("#-> seek", new Object[0]);
      boolean bool1 = bool2;
      if (this.mIsInitialized)
      {
        bool1 = bool2;
        if (this.mMainPlayer != null)
          bool1 = this.mMainPlayer.seek(paramInt);
      }
      return bool1;
    }
    finally
    {
    }
  }

  public boolean stop()
  {
    boolean bool2 = false;
    try
    {
      sLogger.info("#-> stop", new Object[0]);
      boolean bool1 = bool2;
      if (this.mIsInitialized)
      {
        bool1 = bool2;
        if (this.mMainPlayer != null)
          bool1 = this.mMainPlayer.stop();
      }
      return bool1;
    }
    finally
    {
    }
  }

  public void switchVacantPlayer()
  {
    if (this.mVacantPlayer == this.mSpectraA);
    for (Spectra localSpectra = this.mSpectraB; ; localSpectra = this.mSpectraA)
    {
      this.mVacantPlayer = localSpectra;
      return;
    }
  }

  class DualSpectraEventFilter
    implements Spectra.SpectraEventListener
  {
    DualSpectraEventFilter()
    {
    }

    public void onSpectraEvent(Spectra paramSpectra, Spectra.SpectraEvent paramSpectraEvent)
    {
      if (paramSpectra == DualSpectraPlayer.this.mMainPlayer)
      {
        paramSpectra = DualSpectraPlayer.this.mEventListeners.iterator();
        while (paramSpectra.hasNext())
          ((Spectra.SpectraEventListener)paramSpectra.next()).onSpectraEvent(null, paramSpectraEvent);
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     rui.lin.spectra.DualSpectraPlayer
 * JD-Core Version:    0.6.2
 */