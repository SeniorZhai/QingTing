package com.handmark.pulltorefresh.library.extras;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import java.util.HashMap;

public class SoundPullEventListener<V extends View>
  implements PullToRefreshBase.OnPullEventListener<V>
{
  private final Context mContext;
  private MediaPlayer mCurrentMediaPlayer;
  private final HashMap<PullToRefreshBase.State, Integer> mSoundMap;

  public SoundPullEventListener(Context paramContext)
  {
    this.mContext = paramContext;
    this.mSoundMap = new HashMap();
  }

  private void playSound(int paramInt)
  {
    if (this.mCurrentMediaPlayer != null)
    {
      this.mCurrentMediaPlayer.stop();
      this.mCurrentMediaPlayer.release();
    }
    this.mCurrentMediaPlayer = MediaPlayer.create(this.mContext, paramInt);
    if (this.mCurrentMediaPlayer != null)
      this.mCurrentMediaPlayer.start();
  }

  public void addSoundEvent(PullToRefreshBase.State paramState, int paramInt)
  {
    this.mSoundMap.put(paramState, Integer.valueOf(paramInt));
  }

  public void clearSounds()
  {
    this.mSoundMap.clear();
  }

  public MediaPlayer getCurrentMediaPlayer()
  {
    return this.mCurrentMediaPlayer;
  }

  public final void onPullEvent(PullToRefreshBase<V> paramPullToRefreshBase, PullToRefreshBase.State paramState, PullToRefreshBase.Mode paramMode)
  {
    paramPullToRefreshBase = (Integer)this.mSoundMap.get(paramState);
    if (paramPullToRefreshBase != null)
      playSound(paramPullToRefreshBase.intValue());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.extras.SoundPullEventListener
 * JD-Core Version:    0.6.2
 */