package com.tencent.connect.dataprovider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import java.io.File;
import java.lang.ref.WeakReference;

public final class CallbackManager
{
  private WeakReference<Context> a;
  private Uri b;
  private String c;
  private String d;
  private String e;
  private String f;
  private boolean g = false;
  private int h;

  public CallbackManager(Activity paramActivity)
  {
    this.a = new WeakReference(paramActivity.getApplicationContext());
    paramActivity = paramActivity.getIntent();
    if (paramActivity != null)
    {
      this.b = paramActivity.getData();
      this.c = paramActivity.getStringExtra("srcPackageName");
      this.d = paramActivity.getStringExtra("srcClassName");
      this.e = paramActivity.getStringExtra("srcAction");
      this.h = paramActivity.getIntExtra("requestDataTypeFlag", 0);
      this.f = paramActivity.getStringExtra("params_appid");
      if ((this.b != null) && (this.d != null))
        this.g = true;
    }
  }

  private int a(Bundle paramBundle)
  {
    if (!this.g)
      return -2;
    Intent localIntent = new Intent();
    localIntent.setClassName(this.c, this.d);
    localIntent.setAction(this.e);
    paramBundle.putString("params_appid", this.f);
    localIntent.putExtras(paramBundle);
    localIntent.setFlags(268435456);
    ((Context)this.a.get()).startActivity(localIntent);
    return 0;
  }

  private int a(String paramString)
  {
    int j = 0;
    int i;
    if (paramString == null)
      i = -7;
    long l;
    do
    {
      String str;
      do
      {
        return i;
        str = paramString.toLowerCase();
        i = j;
      }
      while (str.startsWith("http://"));
      if (Environment.getExternalStorageState().equals("mounted"))
      {
        if (!str.startsWith(Environment.getExternalStorageDirectory().toString().toLowerCase()))
          return -5;
      }
      else
        return -10;
      paramString = new File(paramString);
      if ((!paramString.exists()) || (paramString.isDirectory()))
        return -8;
      l = paramString.length();
      if (l == 0L)
        return -9;
      i = j;
    }
    while (l <= 1073741824L);
    return -6;
  }

  public int getRequestDateTypeFlag()
  {
    return this.h;
  }

  public boolean isCallFromTencentApp()
  {
    return this.g;
  }

  public boolean isSupportType(int paramInt)
  {
    return (getRequestDateTypeFlag() & paramInt) != 0;
  }

  public int sendTextAndImagePath(String paramString1, String paramString2)
  {
    int i;
    if (!isSupportType(1))
      i = -1;
    int j;
    do
    {
      return i;
      j = a(paramString2);
      i = j;
    }
    while (j != 0);
    paramString1 = new DataType.TextAndMediaPath(paramString1, paramString2);
    paramString2 = new Bundle();
    paramString2.putInt("contentDataType", 1);
    paramString2.putParcelable("contentData", paramString1);
    return a(paramString2);
  }

  public int sendTextAndVideoPath(String paramString1, String paramString2)
  {
    int i;
    if (!isSupportType(2))
      i = -1;
    int j;
    do
    {
      return i;
      j = a(paramString2);
      i = j;
    }
    while (j != 0);
    paramString1 = new DataType.TextAndMediaPath(paramString1, paramString2);
    paramString2 = new Bundle();
    paramString2.putInt("contentDataType", 2);
    paramString2.putParcelable("contentData", paramString1);
    return a(paramString2);
  }

  public int sendTextOnly(String paramString)
  {
    if (!isSupportType(4))
      return -1;
    paramString = new DataType.TextOnly(paramString);
    Bundle localBundle = new Bundle();
    localBundle.putInt("contentDataType", 4);
    localBundle.putParcelable("contentData", paramString);
    return a(localBundle);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.connect.dataprovider.CallbackManager
 * JD-Core Version:    0.6.2
 */