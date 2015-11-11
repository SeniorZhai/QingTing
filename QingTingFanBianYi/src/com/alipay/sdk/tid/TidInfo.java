package com.alipay.sdk.tid;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.sys.GlobalContext;
import com.alipay.sdk.util.DeviceInfo;

public class TidInfo
{
  private static TidInfo a;
  private String b;
  private String c;

  public static TidInfo c()
  {
    try
    {
      if (a == null)
      {
        a = new TidInfo();
        localObject1 = GlobalContext.a().b();
        TidDbHelper localTidDbHelper = new TidDbHelper((Context)localObject1);
        String str2 = DeviceInfo.a((Context)localObject1).a();
        String str3 = DeviceInfo.a((Context)localObject1).b();
        a.b = localTidDbHelper.b(str2, str3);
        a.c = localTidDbHelper.c(str2, str3);
        if (TextUtils.isEmpty(a.c))
        {
          TidInfo localTidInfo = a;
          String str1 = Long.toHexString(System.currentTimeMillis());
          localObject1 = str1;
          if (str1.length() > 10)
            localObject1 = str1.substring(str1.length() - 10);
          localTidInfo.c = ((String)localObject1);
        }
        localTidDbHelper.a(str2, str3, a.b, a.c);
      }
      Object localObject1 = a;
      return localObject1;
    }
    finally
    {
    }
  }

  public static void d()
  {
    Object localObject = GlobalContext.a().b();
    String str1 = DeviceInfo.a((Context)localObject).a();
    String str2 = DeviceInfo.a((Context)localObject).b();
    localObject = new TidDbHelper((Context)localObject);
    ((TidDbHelper)localObject).a(str1, str2);
    ((TidDbHelper)localObject).close();
  }

  private boolean e()
  {
    return TextUtils.isEmpty(this.b);
  }

  private static String f()
  {
    String str2 = Long.toHexString(System.currentTimeMillis());
    String str1 = str2;
    if (str2.length() > 10)
      str1 = str2.substring(str2.length() - 10);
    return str1;
  }

  public final String a()
  {
    return this.b;
  }

  public final void a(Context paramContext)
  {
    TidDbHelper localTidDbHelper = new TidDbHelper(paramContext);
    try
    {
      localTidDbHelper.a(DeviceInfo.a(paramContext).a(), DeviceInfo.a(paramContext).b(), this.b, this.c);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    finally
    {
      localTidDbHelper.close();
    }
    throw paramContext;
  }

  public final void a(String paramString)
  {
    this.b = paramString;
  }

  public final String b()
  {
    return this.c;
  }

  public final void b(String paramString)
  {
    this.c = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.tid.TidInfo
 * JD-Core Version:    0.6.2
 */