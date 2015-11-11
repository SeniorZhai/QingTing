package com.neusoft.ssp.api;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import android.util.Log;
import com.neusoft.parse.DataParser;
import com.neusoft.ssp.aidl.ITaskCallback;
import com.neusoft.ssp.aidl.MyAIDL;

public abstract class SSP_API
{
  private String a;
  private Context b;
  private MyAIDL c;
  private DataParser d = new DataParser();
  private ServiceConnection e = new a(this);
  private ITaskCallback f = new b(this);

  protected SSP_API(String paramString)
  {
    this.a = paramString;
  }

  public int compareVersion(String paramString1, String paramString2)
  {
    int i = 0;
    if ((paramString1 == null) || (paramString2 == null))
      throw new Exception();
    paramString1 = paramString1.split("\\.");
    paramString2 = paramString2.split("\\.");
    int m = Math.min(paramString1.length, paramString2.length);
    int j = 0;
    while (true)
    {
      if (j < m)
      {
        int k = paramString1[j].length() - paramString2[j].length();
        i = k;
        if (k == 0)
        {
          i = paramString1[j].compareTo(paramString2[j]);
          if (i == 0)
            break label98;
        }
      }
      if (i == 0)
        break;
      return i;
      label98: j += 1;
    }
    return paramString1.length - paramString2.length;
  }

  public Context getContext()
  {
    return this.b;
  }

  protected abstract void onRecvRequest(String paramString1, String paramString2, int paramInt, String[] paramArrayOfString);

  protected boolean replay(String paramString)
  {
    Log.v("xy", "replay start.......");
    if (this.c != null)
    {
      Log.v("xy", "myAIDL != null");
      try
      {
        this.c.send(paramString);
        return true;
      }
      catch (RemoteException paramString)
      {
        Log.v("xy", "myAIDL != null");
      }
    }
    Log.v("xy", "replay end.......");
    return false;
  }

  public void setContext(Context paramContext)
  {
    this.b = paramContext.getApplicationContext();
  }

  public boolean startWork()
  {
    Log.v("ccy", "ccy ssplib come startWork context==" + this.b);
    if (this.b != null)
    {
      Log.v("ccy", "ccy ssplib come startWork bindService");
      return this.b.bindService(new Intent("com.neusoft.ssp.aidl.service"), this.e, 1);
    }
    return false;
  }

  public void stopWork()
  {
    if (this.b != null)
      this.b.unbindService(this.e);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.neusoft.ssp.api.SSP_API
 * JD-Core Version:    0.6.2
 */