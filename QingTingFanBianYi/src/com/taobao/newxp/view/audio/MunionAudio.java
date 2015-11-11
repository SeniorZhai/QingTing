package com.taobao.newxp.view.audio;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Environment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.taobao.munion.base.Log;
import com.taobao.newxp.Promoter;
import com.taobao.newxp.controller.ExchangeDataService;
import com.taobao.newxp.controller.XpListenersCenter.ExchangeDataRequestListener;
import com.taobao.newxp.net.s;
import com.taobao.newxp.net.s.a;
import java.util.List;

public class MunionAudio extends RelativeLayout
{
  protected Promoter a;
  protected int b;
  private String c = "";
  private boolean d = false;
  private boolean e = false;
  private MediaPlayer f;
  private OnAudioADClientCallBackListener g;
  private ExchangeDataService h;

  public MunionAudio(Context paramContext)
  {
    super(paramContext);
  }

  public MunionAudio(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public MunionAudio(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private boolean a()
  {
    if (Environment.getExternalStorageState().equals("mounted"))
      return true;
    Log.i("SD card is not exist", new Object[0]);
    return false;
  }

  public OnAudioADClientCallBackListener getOnAudioADClientCallBackListener()
  {
    return this.g;
  }

  public void pauseAD()
  {
    while (true)
    {
      try
      {
        if ((this.d == true) && (this.f != null))
        {
          this.d = false;
          this.e = true;
          this.f.pause();
          int i = this.f.getCurrentPosition();
          new a.a(this.h.getEntity()).f(this.b).g(i).h(1).a(-5).a(new Promoter[] { this.a }).a().a();
          Log.i("暂停播放", new Object[0]);
          if (this.g != null)
            this.g.onDidPause();
        }
        return;
      }
      catch (Exception localException1)
      {
        Log.i("暂停播放失败", new Object[0]);
      }
      try
      {
        this.f.reset();
        label137: if (this.g == null)
          continue;
        this.g.onPlayFailed("Pause failure");
        return;
      }
      catch (Exception localException2)
      {
        break label137;
      }
    }
  }

  public void playAD()
  {
    if ((!TextUtils.isEmpty(this.c)) && (this.f != null))
    {
      if (!this.d)
      {
        this.d = true;
        try
        {
          if (!this.e)
          {
            this.f.reset();
            this.f.setDataSource(this.c);
            Log.i("filePath：" + this.c, new Object[0]);
            Log.i("开始准备播放", new Object[0]);
            this.f.prepareAsync();
            this.f.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
            {
              public void onPrepared(MediaPlayer paramAnonymousMediaPlayer)
              {
                MunionAudio.d(MunionAudio.this).start();
                new s.a(MunionAudio.b(MunionAudio.this).getEntity()).a(0).a(new Promoter[] { MunionAudio.this.a }).a().a();
                Log.i("准备完成，开始播放", new Object[0]);
                if (MunionAudio.c(MunionAudio.this) != null)
                  MunionAudio.c(MunionAudio.this).onDidStart();
              }
            });
          }
          while (true)
          {
            this.f.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
            {
              public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
              {
                Log.i("播放完成", new Object[0]);
                MunionAudio.a(MunionAudio.this, false);
                MunionAudio.b(MunionAudio.this, false);
                int i = MunionAudio.d(MunionAudio.this).getCurrentPosition();
                new a.a(MunionAudio.b(MunionAudio.this).getEntity()).f(MunionAudio.this.b).g(i).h(0).a(-5).a(new Promoter[] { MunionAudio.this.a }).a().a();
                if (MunionAudio.c(MunionAudio.this) != null)
                  MunionAudio.c(MunionAudio.this).onPlayDidFinished();
              }
            });
            return;
            this.e = false;
            this.f.start();
            new s.a(this.h.getEntity()).a(0).a(new Promoter[] { this.a }).a().a();
            Log.i("继续播放", new Object[0]);
            if (this.g != null)
              this.g.onDidStart();
          }
        }
        catch (Exception localException1)
        {
          Log.i("播放失败", new Object[0]);
          this.d = false;
        }
      }
      try
      {
        this.f.reset();
        label230: if (this.g != null)
        {
          this.g.onPlayFailed("Play failure");
          return;
          Log.i("正在播放", new Object[0]);
          if (this.g != null)
          {
            this.g.onPlayFailed("Now playing,Please stop playing");
            return;
          }
        }
      }
      catch (Exception localException2)
      {
        break label230;
      }
    }
  }

  public void requestAD(String paramString)
  {
    if (!a())
    {
      Log.i("not find sd card", new Object[0]);
      if (this.g != null)
        this.g.onRequestFailed("not find sd card");
      return;
    }
    this.d = false;
    this.e = false;
    if (this.f != null);
    try
    {
      this.f.stop();
      this.f.release();
      label66: this.h = new ExchangeDataService(paramString);
      this.h.getEntity().layoutType = 18;
      this.h.requestDataAsyn(getContext(), new XpListenersCenter.ExchangeDataRequestListener()
      {
        public void dataReceived(int paramAnonymousInt, List<Promoter> paramAnonymousList)
        {
          if (!paramAnonymousList.isEmpty())
          {
            MunionAudio.this.a = ((Promoter)paramAnonymousList.get(0));
            String str = ((Promoter)paramAnonymousList.get(0)).url;
            MunionAudio.this.b = ((Promoter)paramAnonymousList.get(0)).dur;
            Log.i("dur:" + MunionAudio.this.b, new Object[0]);
            Log.i("status:" + paramAnonymousInt, new Object[0]);
            Log.i("url:" + str, new Object[0]);
            if ((1 == paramAnonymousInt) && (!TextUtils.isEmpty(str)))
            {
              paramAnonymousList = new c();
              paramAnonymousList.a(str, ".mmsy/");
              paramAnonymousList.a(new c.b()
              {
                public void a(c.c paramAnonymous2c, String paramAnonymous2String)
                {
                  switch (MunionAudio.4.a[paramAnonymous2c.ordinal()])
                  {
                  default:
                  case 1:
                  case 2:
                  }
                  do
                  {
                    do
                    {
                      return;
                      paramAnonymous2c = new b();
                      MunionAudio.a(MunionAudio.this, paramAnonymous2c.a() + ".mmsy/" + paramAnonymous2String);
                      Log.i("filePath:" + MunionAudio.a(MunionAudio.this), new Object[0]);
                      MunionAudio.a(MunionAudio.this, new MediaPlayer());
                      new s.a(MunionAudio.b(MunionAudio.this).getEntity()).a(-4).a(new Promoter[] { MunionAudio.this.a }).a().a();
                      Log.i("广告请求成功:" + paramAnonymous2String, new Object[0]);
                    }
                    while (MunionAudio.c(MunionAudio.this) == null);
                    MunionAudio.c(MunionAudio.this).onRequestFinished();
                    return;
                    Log.i("下载音频文件失败", new Object[0]);
                  }
                  while (MunionAudio.c(MunionAudio.this) == null);
                  MunionAudio.c(MunionAudio.this).onRequestFailed("Download file error");
                }
              });
            }
          }
          do
          {
            do
            {
              return;
              Log.i("请求广告数据失败", new Object[0]);
            }
            while (MunionAudio.c(MunionAudio.this) == null);
            MunionAudio.c(MunionAudio.this).onRequestFailed("Request data error");
            return;
            Log.i("请求广告数据失败", new Object[0]);
          }
          while (MunionAudio.c(MunionAudio.this) == null);
          MunionAudio.c(MunionAudio.this).onRequestFailed("Request data error");
        }
      });
      return;
    }
    catch (Exception localException)
    {
      break label66;
    }
  }

  public void setOnAudioADClientCallBackListener(OnAudioADClientCallBackListener paramOnAudioADClientCallBackListener)
  {
    this.g = paramOnAudioADClientCallBackListener;
  }

  public void stopAD()
  {
    while (true)
    {
      try
      {
        if (((this.d == true) || (this.e == true)) && (this.f != null))
        {
          this.f.stop();
          this.d = false;
          this.e = false;
          int i = this.f.getCurrentPosition();
          new a.a(this.h.getEntity()).f(this.b).g(i).h(2).a(-5).a(new Promoter[] { this.a }).a().a();
          Log.i("停止播放", new Object[0]);
          if (this.g != null)
            this.g.onDidStop();
        }
        return;
      }
      catch (Exception localException1)
      {
        Log.i("停止播放失败", new Object[0]);
      }
      try
      {
        this.f.reset();
        label146: if (this.g == null)
          continue;
        this.g.onPlayFailed("Stop failure");
        return;
      }
      catch (Exception localException2)
      {
        break label146;
      }
    }
  }

  public static abstract interface OnAudioADClientCallBackListener
  {
    public abstract void onDidPause();

    public abstract void onDidStart();

    public abstract void onDidStop();

    public abstract void onPlayDidFinished();

    public abstract void onPlayFailed(String paramString);

    public abstract void onRequestFailed(String paramString);

    public abstract void onRequestFinished();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.audio.MunionAudio
 * JD-Core Version:    0.6.2
 */