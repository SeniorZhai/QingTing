package com.taobao.munion.view.webview.windvane;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.munion.view.webview.windvane.mraid.AlimamaMraid;
import java.util.HashMap;

public class f
{
  private static HashMap<String, String> a = new HashMap();
  private Context b;
  private WindVaneWebView c;

  public f(Context paramContext, WindVaneWebView paramWindVaneWebView)
  {
    this.b = paramContext;
    this.c = paramWindVaneWebView;
    a();
  }

  private Object a(String paramString, WindVaneWebView paramWindVaneWebView, Context paramContext)
  {
    paramString = (String)a.get(paramString);
    if (!TextUtils.isEmpty(paramString))
      try
      {
        paramString = Class.forName(paramString);
        if ((paramString != null) && (j.class.isAssignableFrom(paramString)))
        {
          paramString = (j)paramString.newInstance();
          paramString.initialize(paramContext, paramWindVaneWebView);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    return null;
  }

  public Object a(String paramString)
  {
    if (a == null)
      a = new HashMap();
    return a(paramString, this.c, this.b);
  }

  public void a()
  {
    try
    {
      a(Class.forName("com.taobao.newxp.view.handler.waketaobao.AlimamaWakeup"));
      a(AlimamaMraid.class);
    }
    catch (Exception localException1)
    {
      try
      {
        a(Class.forName("com.taobao.newxp.view.handler.Mmusdk"));
        return;
        localException1 = localException1;
        localException1.printStackTrace();
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
  }

  public void a(Class paramClass)
  {
    if (a == null)
      a = new HashMap();
    a.put(paramClass.getSimpleName(), paramClass.getName());
  }

  public void b(String paramString)
  {
    if (a == null)
      a = new HashMap();
    a.remove(paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.view.webview.windvane.f
 * JD-Core Version:    0.6.2
 */