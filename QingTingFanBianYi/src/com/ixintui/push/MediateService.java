package com.ixintui.push;

import android.app.IntentService;
import android.content.Intent;
import com.ixintui.plugin.IMediateService;
import com.ixintui.pushsdk.a.a;

public class MediateService extends IntentService
{
  private IMediateService a;

  public MediateService()
  {
    super("MediateService");
  }

  protected void onHandleIntent(Intent paramIntent)
  {
    if (this.a == null)
      this.a = ((IMediateService)a.a(this, "com.ixintui.push.MediateServiceImpl"));
    if (this.a == null)
      return;
    this.a.onHandleIntent(this, paramIntent);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ixintui.push.MediateService
 * JD-Core Version:    0.6.2
 */