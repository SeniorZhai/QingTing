package com.taobao.newxp.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.taobao.newxp.common.Log;
import java.util.Map;

public class g
{
  private static final String a = "EXCHANGE_UPLOAD_CACHE";
  private static final String b = "upload_page_";
  private static final String c = "last_update";
  private static final String d = "UMUpload";
  private String e;
  private Context f;

  public g(Context paramContext, String paramString)
    throws Exception
  {
    if (TextUtils.isEmpty(paramString))
      throw new RuntimeException("can`t save the data appkey is empty..");
    this.f = paramContext;
    this.e = paramString;
  }

  private int a(SharedPreferences paramSharedPreferences)
  {
    paramSharedPreferences = paramSharedPreferences.getAll();
    int i = 0;
    String str = e();
    while (paramSharedPreferences.containsKey(str + i))
      i += 1;
    Log.c("UMUpload", "max page index is" + (i - 1));
    return i - 1;
  }

  private String e()
  {
    return this.e + "_" + "upload_page_";
  }

  public long a()
  {
    return this.f.getSharedPreferences("EXCHANGE_UPLOAD_CACHE", 0).getLong(this.e + "_" + "last_update", 0L);
  }

  public void a(String paramString)
    throws Exception
  {
    try
    {
      Object localObject = this.f.getSharedPreferences("EXCHANGE_UPLOAD_CACHE", 0);
      int i = a((SharedPreferences)localObject);
      String str = e() + (i + 1);
      localObject = ((SharedPreferences)localObject).edit();
      ((SharedPreferences.Editor)localObject).putString(str, paramString);
      ((SharedPreferences.Editor)localObject).commit();
      Log.c("UMUpload", "add a buffer data " + paramString);
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public boolean b()
  {
    boolean bool = false;
    if (a(this.f.getSharedPreferences("EXCHANGE_UPLOAD_CACHE", 0)) > -1)
      bool = true;
    return bool;
  }

  public String c()
  {
    try
    {
      Object localObject2 = this.f.getSharedPreferences("EXCHANGE_UPLOAD_CACHE", 0);
      int i = a((SharedPreferences)localObject2);
      String str;
      if (i > -1)
      {
        str = ((SharedPreferences)localObject2).getString(e() + i, "");
        localObject2 = ((SharedPreferences)localObject2).edit();
        ((SharedPreferences.Editor)localObject2).remove(e() + i);
        ((SharedPreferences.Editor)localObject2).commit();
        Log.c("UMUpload", "dump a buffer data " + str);
      }
      while (true)
      {
        return str;
        str = null;
      }
    }
    finally
    {
    }
  }

  public void d()
  {
    try
    {
      SharedPreferences.Editor localEditor = this.f.getSharedPreferences("EXCHANGE_UPLOAD_CACHE", 0).edit();
      localEditor.putLong(this.e + "_" + "last_update", System.currentTimeMillis());
      localEditor.commit();
      Log.c("UMUpload", "update last upload time.");
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.controller.g
 * JD-Core Version:    0.6.2
 */