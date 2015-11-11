package com.taobao.munion.view.webview.windvane;

public class h
{
  public static c a(Object paramObject)
  {
    try
    {
      paramObject = ((a)paramObject).a.getJsBridge().b();
      if ("wv_hybird:".equals(paramObject))
        return g.a();
      if ("mraid:".equals(paramObject))
      {
        paramObject = com.taobao.munion.view.webview.windvane.mraid.c.a();
        return paramObject;
      }
    }
    catch (Exception paramObject)
    {
      paramObject.printStackTrace();
    }
    return g.a();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.view.webview.windvane.h
 * JD-Core Version:    0.6.2
 */