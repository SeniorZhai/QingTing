package com.intowow.sdk.b;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.view.Surface;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.d;
import com.intowow.sdk.model.ADProfile.q;

public class g
{
  private MediaPlayer a = null;
  private a b = null;
  private String c = null;
  private boolean d = false;
  private Context e = null;
  private boolean f = true;

  public g(Context paramContext)
  {
    this.e = paramContext;
    this.b = new a();
    this.a = new MediaPlayer();
  }

  private void a(String paramString, a parama, int paramInt1, int paramInt2)
  {
    e.a(this.e).a(paramString, parama, paramInt1, paramInt2);
  }

  public int a()
  {
    try
    {
      if (this.a != null)
      {
        i = this.a.getCurrentPosition();
        return i;
      }
      int i = 0;
    }
    finally
    {
    }
  }

  public void a(String paramString1, final String paramString2, final ADProfile paramADProfile, final int paramInt, Surface paramSurface, float paramFloat, String paramString3, boolean paramBoolean, final MediaPlayer.OnPreparedListener paramOnPreparedListener, final MediaPlayer.OnCompletionListener paramOnCompletionListener, MediaPlayer.OnErrorListener paramOnErrorListener)
  {
    try
    {
      if (this.c != null)
        b(this.c);
      this.b.a(paramADProfile, paramInt, paramString2);
      if (paramFloat > 0.0F)
        this.b.b();
      for (this.f = false; ; this.f = true)
      {
        this.c = paramString1;
        this.d = paramBoolean;
        this.a.setSurface(paramSurface);
        this.a.setVolume(paramFloat, paramFloat);
        this.a.setLooping(false);
        this.a.setAudioStreamType(3);
        this.a.setOnErrorListener(paramOnErrorListener);
        this.a.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
          public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
          {
            g.a(g.this).a();
            k.a locala = e.a(g.b(g.this)).t();
            if (g.c(g.this))
              if ((locala != null) && (locala.b() == k.a.a.c))
                if (paramOnCompletionListener != null)
                  paramOnCompletionListener.onCompletion(paramAnonymousMediaPlayer);
            while (paramOnCompletionListener == null)
            {
              return;
              g.a(g.this).a(paramADProfile, paramInt, paramString2);
              if (!g.d(g.this))
                g.a(g.this).b();
              paramAnonymousMediaPlayer.seekTo(0);
              paramAnonymousMediaPlayer.start();
              return;
            }
            paramOnCompletionListener.onCompletion(paramAnonymousMediaPlayer);
          }
        });
        this.a.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
          public void onPrepared(MediaPlayer paramAnonymousMediaPlayer)
          {
            paramAnonymousMediaPlayer.start();
            if (paramOnPreparedListener != null)
              paramOnPreparedListener.onPrepared(paramAnonymousMediaPlayer);
          }
        });
        this.a.setDataSource(paramString3);
        this.a.prepareAsync();
        label157: return;
      }
    }
    catch (Exception paramString1)
    {
      break label157;
    }
    finally
    {
    }
    throw paramString1;
  }

  public void a(boolean paramBoolean)
  {
    float f1;
    if (paramBoolean)
      f1 = 0.0F;
    try
    {
      while (true)
      {
        if (this.a != null)
        {
          this.a.setVolume(f1, f1);
          this.f = paramBoolean;
          if ((f1 > 0.0F) && (this.b != null))
            this.b.b();
        }
        return;
        f1 = 1.0F;
      }
    }
    finally
    {
    }
  }

  public boolean a(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    try
    {
      if (this.c != null)
      {
        bool1 = paramString.equals(this.c);
        if (bool1)
          break label32;
        bool1 = bool2;
      }
      while (true)
      {
        return bool1;
        label32: bool1 = bool2;
        if (this.a != null)
          bool1 = this.a.isPlaying();
      }
    }
    finally
    {
    }
    throw paramString;
  }

  public void b(String paramString)
  {
    try
    {
      if (this.c != null)
      {
        boolean bool = this.c.equals(paramString);
        if (bool)
          break label25;
      }
      while (true)
      {
        return;
        label25: this.c = null;
        this.b.a();
        this.f = true;
        this.a.stop();
        this.a.reset();
      }
    }
    finally
    {
    }
    throw paramString;
  }

  public class a
  {
    public int a = -1;
    public int b = 0;
    public int c = 0;
    public int d = 0;
    public int e = 0;
    public long f = 0L;
    public boolean g = false;
    public String h = null;

    public a()
    {
    }

    private int a(ADProfile paramADProfile)
    {
      return ((ADProfile.q)paramADProfile.a(ADProfile.d.B)).f();
    }

    private void c()
    {
      if (this.a == -1)
        return;
      int j = Math.min((int)(System.currentTimeMillis() - this.f), this.b);
      int k = (int)Math.ceil(100.0F * j / this.b);
      g.a(g.this, this.h, this, j, k);
    }

    public void a()
    {
      c();
      this.a = -1;
      this.b = 0;
      this.f = 0L;
    }

    public void a(ADProfile paramADProfile, int paramInt, String paramString)
    {
      c();
      this.a = paramADProfile.e();
      this.c = paramInt;
      this.d = paramADProfile.k();
      this.e = paramADProfile.l();
      this.b = a(paramADProfile);
      this.f = System.currentTimeMillis();
      this.h = paramString;
      this.g = false;
    }

    public void b()
    {
      this.g = true;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.b.g
 * JD-Core Version:    0.6.2
 */