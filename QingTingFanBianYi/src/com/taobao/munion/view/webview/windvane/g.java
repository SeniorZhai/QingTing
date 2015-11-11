package com.taobao.munion.view.webview.windvane;

import android.text.TextUtils;

public class g
  implements c
{
  private static g a = new g();

  public static g a()
  {
    return a;
  }

  public void a(Object paramObject, String paramString)
  {
    a locala;
    if ((paramObject instanceof a))
    {
      locala = (a)paramObject;
      if (!TextUtils.isEmpty(paramString))
        break label52;
      paramObject = String.format("javascript:window.WindVane.onSuccess(%s,'');", new Object[] { locala.g });
    }
    while (true)
    {
      if (locala.a != null);
      try
      {
        locala.a.loadUrl(paramObject);
        return;
        label52: paramObject = String.format("javascript:window.WindVane.onSuccess(%s,'%s');", new Object[] { locala.g, paramString });
      }
      catch (Exception paramObject)
      {
        paramObject.printStackTrace();
      }
    }
  }

  public void a(Object paramObject, String paramString1, String paramString2)
  {
    a locala;
    if ((paramObject instanceof a))
    {
      locala = (a)paramObject;
      if (!TextUtils.isEmpty(paramString2))
        break label52;
      paramObject = String.format("javascript:window.WindVane.fireEvent('%s', '');", new Object[] { paramString1 });
    }
    while (true)
    {
      if (locala.a != null);
      try
      {
        locala.a.loadUrl(paramObject);
        return;
        label52: paramObject = String.format("javascript:window.WindVane.fireEvent('%s','%s');", new Object[] { paramString1, paramString2 });
      }
      catch (Exception paramObject)
      {
        paramObject.printStackTrace();
      }
    }
  }

  public void b(Object paramObject, String paramString)
  {
    a locala;
    if ((paramObject instanceof a))
    {
      locala = (a)paramObject;
      if (!TextUtils.isEmpty(paramString))
        break label52;
      paramObject = String.format("javascript:window.WindVane.onFailure(%s,'');", new Object[] { locala.g });
    }
    while (true)
    {
      if (locala.a != null);
      try
      {
        locala.a.loadUrl(paramObject);
        return;
        label52: paramObject = String.format("javascript:window.WindVane.onFailure(%s,'%s');", new Object[] { locala.g, paramString });
      }
      catch (Exception paramObject)
      {
        paramObject.printStackTrace();
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.view.webview.windvane.g
 * JD-Core Version:    0.6.2
 */