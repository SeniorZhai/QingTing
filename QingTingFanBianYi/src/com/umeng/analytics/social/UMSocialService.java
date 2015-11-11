package com.umeng.analytics.social;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONObject;

public abstract class UMSocialService
{
  private static void a(Context paramContext, b paramb, String paramString, UMPlatformData[] paramArrayOfUMPlatformData)
  {
    int i = 0;
    if (paramArrayOfUMPlatformData != null);
    try
    {
      int j = paramArrayOfUMPlatformData.length;
      if (i < j)
        if (!paramArrayOfUMPlatformData[i].isValid())
          throw new a("parameter is not valid.");
    }
    catch (a paramContext)
    {
      while (true)
      {
        Log.e("MobclickAgent", "unable send event.", paramContext);
        return;
        i += 1;
      }
      new a(f.a(paramContext, paramString, paramArrayOfUMPlatformData), paramb, paramArrayOfUMPlatformData).execute(new Void[0]);
      return;
    }
    catch (Exception paramContext)
    {
      Log.e("MobclickAgent", "", paramContext);
    }
  }

  public static void share(Context paramContext, String paramString, UMPlatformData[] paramArrayOfUMPlatformData)
  {
    a(paramContext, null, paramString, paramArrayOfUMPlatformData);
  }

  public static void share(Context paramContext, UMPlatformData[] paramArrayOfUMPlatformData)
  {
    a(paramContext, null, null, paramArrayOfUMPlatformData);
  }

  private static class a extends AsyncTask<Void, Void, d>
  {
    String a = paramArrayOfString[0];
    String b = paramArrayOfString[1];
    UMSocialService.b c;
    UMPlatformData[] d;

    public a(String[] paramArrayOfString, UMSocialService.b paramb, UMPlatformData[] paramArrayOfUMPlatformData)
    {
      this.c = paramb;
      this.d = paramArrayOfUMPlatformData;
    }

    protected d a(Void[] paramArrayOfVoid)
    {
      if (TextUtils.isEmpty(this.b))
        paramArrayOfVoid = c.a(this.a);
      while (true)
        try
        {
          Object localObject = new JSONObject(paramArrayOfVoid);
          int i = ((JSONObject)localObject).optInt("st");
          if (i == 0)
          {
            i = -404;
            paramArrayOfVoid = new d(i);
            String str = ((JSONObject)localObject).optString("msg");
            if (!TextUtils.isEmpty(str))
              paramArrayOfVoid.a(str);
            localObject = ((JSONObject)localObject).optString("data");
            if (!TextUtils.isEmpty((CharSequence)localObject))
              paramArrayOfVoid.b((String)localObject);
            return paramArrayOfVoid;
            paramArrayOfVoid = c.a(this.a, this.b);
          }
        }
        catch (Exception paramArrayOfVoid)
        {
          return new d(-99, paramArrayOfVoid);
        }
    }

    protected void a(d paramd)
    {
      if (this.c != null)
        this.c.a(paramd, this.d);
    }

    protected void onPreExecute()
    {
      if (this.c != null)
        this.c.a();
    }
  }

  public static abstract interface b
  {
    public abstract void a();

    public abstract void a(d paramd, UMPlatformData[] paramArrayOfUMPlatformData);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.analytics.social.UMSocialService
 * JD-Core Version:    0.6.2
 */