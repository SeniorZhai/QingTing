package com.intowow.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.intowow.sdk.b.e;
import com.intowow.sdk.model.ADProfile;

public class SplashAD
{
  private Context a = null;
  private ActivityType b = ActivityType.SINGLE_OFFER;
  private ADProfile c = null;
  private String d = null;
  private String e = null;
  private boolean f = false;
  private boolean g = false;
  private boolean h = false;
  private boolean i = false;
  private SplashAdListener j = null;
  private int k = -1;
  private int l = -1;

  public SplashAD(Context paramContext, ActivityType paramActivityType)
  {
    this.a = paramContext;
    this.b = paramActivityType;
  }

  private void a(Intent paramIntent, Bundle paramBundle)
  {
    if (this.h)
    {
      paramBundle.putInt("ENTER_ANIM_ID", this.k);
      paramIntent.putExtras(paramBundle);
      this.a.startActivity(paramIntent);
      if ((this.a instanceof Activity))
        ((Activity)this.a).overridePendingTransition(this.k, this.l);
      return;
    }
    paramIntent.putExtras(paramBundle);
    this.a.startActivity(paramIntent);
  }

  private void a(ADProfile paramADProfile, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this.a, SplashAdActivity.class);
    Bundle localBundle = new Bundle();
    localBundle.putInt("ACTIVITY_TYPE", ActivityType.SINGLE_OFFER.ordinal());
    localBundle.putParcelable("PROFILE", paramADProfile);
    localBundle.putString("PLACEMENT", paramString1);
    localBundle.putString("TOKEN", paramString2);
    if (this.i);
    for (int m = 1; ; m = 0)
    {
      localBundle.putInt("IS_AUTO_CLOSE_WHEN_ENGAGED", m);
      a(localIntent, localBundle);
      e.a(this.a).a(System.currentTimeMillis());
      return;
    }
  }

  public void close(Context paramContext)
  {
    if (paramContext != null);
    try
    {
      e.a(paramContext).h();
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  public void release()
  {
    try
    {
      e.a(this.a).b(this.j);
      this.a = null;
      this.j = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setListener(SplashAdListener paramSplashAdListener)
  {
    try
    {
      this.j = paramSplashAdListener;
      boolean bool = this.f;
      if (bool);
      try
      {
        if (this.g)
        {
          this.j.onLoadFailed();
          release();
        }
        while (true)
        {
          label36: return;
          this.j.onLoaded();
        }
      }
      catch (Exception paramSplashAdListener)
      {
        break label36;
      }
    }
    finally
    {
    }
    throw paramSplashAdListener;
  }

  // ERROR //
  public void setLoadFailed()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_1
    //   4: putfield 49	com/intowow/sdk/SplashAD:f	Z
    //   7: aload_0
    //   8: iconst_1
    //   9: putfield 51	com/intowow/sdk/SplashAD:g	Z
    //   12: aload_0
    //   13: getfield 57	com/intowow/sdk/SplashAD:j	Lcom/intowow/sdk/SplashAD$SplashAdListener;
    //   16: astore_1
    //   17: aload_1
    //   18: ifnull +16 -> 34
    //   21: aload_0
    //   22: getfield 57	com/intowow/sdk/SplashAD:j	Lcom/intowow/sdk/SplashAD$SplashAdListener;
    //   25: invokeinterface 150 1 0
    //   30: aload_0
    //   31: invokevirtual 152	com/intowow/sdk/SplashAD:release	()V
    //   34: aload_0
    //   35: monitorexit
    //   36: return
    //   37: astore_1
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: athrow
    //   42: astore_1
    //   43: goto -13 -> 30
    //
    // Exception table:
    //   from	to	target	type
    //   2	17	37	finally
    //   21	30	37	finally
    //   30	34	37	finally
    //   21	30	42	java/lang/Exception
  }

  // ERROR //
  public void setup(ADProfile paramADProfile, String paramString1, String paramString2, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_1
    //   4: putfield 49	com/intowow/sdk/SplashAD:f	Z
    //   7: aload_0
    //   8: aload_1
    //   9: putfield 43	com/intowow/sdk/SplashAD:c	Lcom/intowow/sdk/model/ADProfile;
    //   12: aload_0
    //   13: aload_2
    //   14: putfield 45	com/intowow/sdk/SplashAD:d	Ljava/lang/String;
    //   17: aload_0
    //   18: aload_3
    //   19: putfield 47	com/intowow/sdk/SplashAD:e	Ljava/lang/String;
    //   22: aload_0
    //   23: iload 4
    //   25: putfield 55	com/intowow/sdk/SplashAD:i	Z
    //   28: aload_0
    //   29: getfield 57	com/intowow/sdk/SplashAD:j	Lcom/intowow/sdk/SplashAD$SplashAdListener;
    //   32: astore_1
    //   33: aload_1
    //   34: ifnull +12 -> 46
    //   37: aload_0
    //   38: getfield 57	com/intowow/sdk/SplashAD:j	Lcom/intowow/sdk/SplashAD$SplashAdListener;
    //   41: invokeinterface 155 1 0
    //   46: aload_0
    //   47: monitorexit
    //   48: return
    //   49: astore_1
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: athrow
    //   54: astore_1
    //   55: goto -9 -> 46
    //
    // Exception table:
    //   from	to	target	type
    //   2	33	49	finally
    //   37	46	49	finally
    //   37	46	54	java/lang/Exception
  }

  public void show()
  {
    if (!this.f);
    while (true)
    {
      return;
      try
      {
        e.a(this.a).a(this.j);
        if (this.b == ActivityType.SINGLE_OFFER)
        {
          a(this.c, this.d, this.e);
          return;
        }
      }
      catch (Exception localException)
      {
      }
    }
  }

  public void show(int paramInt1, int paramInt2)
  {
    this.k = paramInt1;
    this.l = paramInt2;
    this.h = true;
    show();
  }

  public static enum ActivityType
  {
    static
    {
      SINGLE_OFFER = new ActivityType("SINGLE_OFFER", 1);
    }
  }

  public static abstract interface SplashAdListener
  {
    public abstract void onClosed();

    public abstract void onLoadFailed();

    public abstract void onLoaded();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.SplashAD
 * JD-Core Version:    0.6.2
 */