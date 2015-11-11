package com.alipay.sdk.authjs;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;
import java.util.Timer;
import org.json.JSONException;
import org.json.JSONObject;

public class JsBridge
{
  private IJsCallback a;
  private Context b;

  public JsBridge(Context paramContext, IJsCallback paramIJsCallback)
  {
    this.b = paramContext;
    this.a = paramIJsCallback;
  }

  private void a(CallInfo paramCallInfo)
    throws JSONException
  {
    if (paramCallInfo == null)
      return;
    if (TextUtils.isEmpty(paramCallInfo.b()))
    {
      a(paramCallInfo.a(), CallInfo.CallError.c);
      return;
    }
    paramCallInfo = new JsBridge.1(this, paramCallInfo);
    if (Looper.getMainLooper() == Looper.myLooper());
    for (int i = 1; i != 0; i = 0)
    {
      paramCallInfo.run();
      return;
    }
    new Handler(Looper.getMainLooper()).post(paramCallInfo);
  }

  private static void a(Runnable paramRunnable)
  {
    if (paramRunnable == null)
      return;
    if (Looper.getMainLooper() == Looper.myLooper());
    for (int i = 1; i != 0; i = 0)
    {
      paramRunnable.run();
      return;
    }
    new Handler(Looper.getMainLooper()).post(paramRunnable);
  }

  private void a(String paramString, CallInfo.CallError paramCallError)
    throws JSONException
  {
    if (TextUtils.isEmpty(paramString))
      return;
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("error", paramCallError.ordinal());
    paramCallError = new CallInfo("callback");
    paramCallError.a(localJSONObject);
    paramCallError.a(paramString);
    this.a.a(paramCallError);
  }

  private CallInfo.CallError b(CallInfo paramCallInfo)
  {
    if ((paramCallInfo != null) && ("toast".equals(paramCallInfo.b())))
    {
      JSONObject localJSONObject = paramCallInfo.c();
      String str = localJSONObject.optString("content");
      int j = localJSONObject.optInt("duration");
      int i = 1;
      if (j < 2500)
        i = 0;
      Toast.makeText(this.b, str, i).show();
      new Timer().schedule(new JsBridge.2(this, paramCallInfo), i);
    }
    return CallInfo.CallError.a;
  }

  private void c(CallInfo paramCallInfo)
  {
    JSONObject localJSONObject = paramCallInfo.c();
    String str = localJSONObject.optString("content");
    int j = localJSONObject.optInt("duration");
    int i = 1;
    if (j < 2500)
      i = 0;
    Toast.makeText(this.b, str, i).show();
    new Timer().schedule(new JsBridge.2(this, paramCallInfo), i);
  }

  public final void a(String paramString)
  {
    while (true)
    {
      try
      {
        Object localObject2 = new JSONObject(paramString);
        paramString = ((JSONObject)localObject2).getString("clientId");
        CallInfo localCallInfo;
        try
        {
          if (TextUtils.isEmpty(paramString))
            return;
          localObject1 = ((JSONObject)localObject2).getJSONObject("param");
          if (!(localObject1 instanceof JSONObject))
            continue;
          localObject1 = (JSONObject)localObject1;
          String str = ((JSONObject)localObject2).getString("func");
          localObject2 = ((JSONObject)localObject2).getString("bundleName");
          localCallInfo = new CallInfo("call");
          localCallInfo.b((String)localObject2);
          localCallInfo.c(str);
          localCallInfo.a((JSONObject)localObject1);
          localCallInfo.a(paramString);
          if (!TextUtils.isEmpty(localCallInfo.b()))
            continue;
          a(localCallInfo.a(), CallInfo.CallError.c);
          return;
        }
        catch (Exception localException)
        {
          localObject1 = paramString;
          paramString = localException;
        }
        paramString.printStackTrace();
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          try
          {
            a((String)localObject1, CallInfo.CallError.d);
            return;
          }
          catch (JSONException paramString)
          {
            paramString.printStackTrace();
            return;
          }
          localObject1 = new JsBridge.1(this, localCallInfo);
          if (Looper.getMainLooper() != Looper.myLooper())
            break label212;
          i = 1;
          if (i != 0)
          {
            ((Runnable)localObject1).run();
            return;
          }
          new Handler(Looper.getMainLooper()).post((Runnable)localObject1);
          return;
        }
      }
      catch (Exception paramString)
      {
        Object localObject1 = null;
        continue;
        localObject1 = null;
        continue;
      }
      return;
      label212: int i = 0;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.authjs.JsBridge
 * JD-Core Version:    0.6.2
 */