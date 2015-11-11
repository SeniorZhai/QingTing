package com.intowow.sdk.j;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Toast;
import com.intowow.sdk.b.e;
import com.intowow.sdk.b.h;
import com.intowow.sdk.b.h.b;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.n;
import com.intowow.sdk.model.g;

public class j
{
  private static void a(Activity paramActivity, String paramString1, String paramString2)
  {
    n.a(paramActivity, null, paramString1, paramString2, null, false, new a()
    {
      public DialogInterface.OnCancelListener a()
      {
        return null;
      }

      public void a(DialogInterface paramAnonymousDialogInterface)
      {
        paramAnonymousDialogInterface.dismiss();
        j.this.finish();
      }

      public void b(DialogInterface paramAnonymousDialogInterface)
      {
      }
    }).show();
  }

  public static void a(Context paramContext)
  {
    a(paramContext, "Open Splash for preview is ready. Please use back button to exit and reenter", 0, 5000);
  }

  public static void a(Context paramContext, com.intowow.sdk.f.b paramb, ADProfile paramADProfile)
  {
    boolean bool = b.a(paramContext, paramADProfile.p());
    if ((paramADProfile.s() == ADProfile.n.b) && (bool) && (!a(paramb.e(paramADProfile.h()[0]))))
      b(paramContext);
  }

  public static void a(Context paramContext, ADProfile paramADProfile, String paramString)
  {
    e locale = e.a(paramContext);
    if ((locale.q()) && (paramADProfile != null) && (a(locale.b(paramString))) && (!locale.m()))
    {
      locale.n();
      c(paramContext.getApplicationContext());
    }
  }

  private static void a(Context paramContext, String paramString, int paramInt1, int paramInt2)
  {
    paramContext = Toast.makeText(paramContext, paramString, paramInt1);
    paramContext.show();
    new CountDownTimer(paramInt2, 1000L)
    {
      public void onFinish()
      {
        this.a.show();
      }

      public void onTick(long paramAnonymousLong)
      {
        this.a.show();
      }
    }
    .start();
  }

  public static void a(com.intowow.sdk.f.b paramb, h paramh, ADProfile paramADProfile)
  {
    if ((paramb == null) || (paramh == null) || (paramADProfile == null));
    while (!paramb.N())
      return;
    paramb = new Bundle();
    paramb.putInt("type", h.b.m.ordinal());
    paramb.putParcelable("ADPROFILE", paramADProfile);
    paramh.a(paramb);
  }

  public static boolean a(Bundle paramBundle)
  {
    return paramBundle.getString("INTENT_PREVIEW_FETCH_ADLIST") != null;
  }

  public static boolean a(g paramg)
  {
    if ((paramg == null) || (paramg.a() == null) || (paramg.a().length() == 0));
    while (paramg.a().toUpperCase().indexOf("OPEN_SPLASH") == -1)
      return false;
    return true;
  }

  public static void b(Context paramContext)
  {
    a(paramContext, "Ads for preview is ready. Please go to the corresponding ad space", 0, 1000);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        if (j.this != null)
          j.c(j.this);
      }
    }
    , 3000L);
  }

  public static void c(Context paramContext)
  {
    a(paramContext, "This is preview mode. Please swipe to close APP to exit preview mode", 0, 3000);
  }

  public static void d(Context paramContext)
  {
    a((Activity)paramContext, "The obsolete ads had been removed. Please reenter APP", "Close APP");
  }

  public static void e(Context paramContext)
  {
    a((Activity)paramContext, "Download failed due to week internet service. Please check your internet service then scan QR code again", "Confirm");
  }

  public static void f(Context paramContext)
  {
    a((Activity)paramContext, "Download failed due to server error. Please check your internet service then scan QR code again", "Confirm");
  }

  static abstract interface a
  {
    public abstract DialogInterface.OnCancelListener a();

    public abstract void a(DialogInterface paramDialogInterface);

    public abstract void b(DialogInterface paramDialogInterface);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.j.j
 * JD-Core Version:    0.6.2
 */