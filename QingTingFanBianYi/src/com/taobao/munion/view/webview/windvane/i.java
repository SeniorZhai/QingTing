package com.taobao.munion.view.webview.windvane;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.WebView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class i
  implements Handler.Callback, d
{
  protected Pattern a;
  protected String b;
  protected final int c = 1;
  protected Context d;
  protected WindVaneWebView e;
  protected Handler f;

  public i(Context paramContext)
  {
    this.d = paramContext;
    this.f = new Handler(Looper.getMainLooper(), this);
  }

  public WebView a()
  {
    return this.e;
  }

  protected void a(int paramInt, a parama)
  {
    Message localMessage = Message.obtain();
    localMessage.what = paramInt;
    localMessage.obj = parama;
    this.f.sendMessage(localMessage);
  }

  public void a(WindVaneWebView paramWindVaneWebView)
  {
    this.e = paramWindVaneWebView;
  }

  protected void a(a parama)
  {
    Object localObject;
    if (parama.a == null)
    {
      localObject = null;
      if (localObject != null)
        break label29;
    }
    while (true)
    {
      return;
      localObject = parama.a.getJsObject(parama.d);
      break;
      try
      {
        label29: b.f localf = b.a(localObject.getClass().getName()).b(parama.e, new Class[] { Object.class, String.class });
        localf.a();
        if ((localObject != null) && ((localObject instanceof j)))
        {
          parama.b = localObject;
          parama.c = localf;
          parama.b = localObject;
          a(1, parama);
          return;
        }
      }
      catch (b.b.a parama)
      {
        parama.printStackTrace();
      }
    }
  }

  public void a(Pattern paramPattern)
  {
    this.a = paramPattern;
  }

  public boolean a(String paramString)
  {
    if (k.a(paramString))
    {
      a(k.b(paramString));
      d(paramString);
      return true;
    }
    return false;
  }

  public a b(String paramString)
  {
    if (paramString == null);
    a locala;
    int i;
    do
    {
      do
      {
        return null;
        paramString = this.a.matcher(paramString);
      }
      while (!paramString.matches());
      locala = new a();
      i = paramString.groupCount();
      if (i >= 5)
        locala.f = paramString.group(5);
    }
    while (i < 3);
    locala.d = paramString.group(1);
    locala.g = paramString.group(2);
    locala.e = paramString.group(3);
    return locala;
  }

  public String b()
  {
    return this.b;
  }

  public void c(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    do
    {
      return;
      paramString = b(paramString);
    }
    while (paramString == null);
    paramString.a = this.e;
    a(paramString);
  }

  public void d(String paramString)
  {
    this.b = paramString;
  }

  public boolean handleMessage(Message paramMessage)
  {
    a locala = (a)paramMessage.obj;
    if (locala == null)
      return false;
    try
    {
      switch (paramMessage.what)
      {
      case 1:
        Object localObject = locala.b;
        b.f localf = locala.c;
        if (TextUtils.isEmpty(locala.f));
        for (paramMessage = "{}"; ; paramMessage = locala.f)
        {
          localf.a(localObject, new Object[] { locala, paramMessage });
          return true;
        }
      }
    }
    catch (Exception paramMessage)
    {
      paramMessage.printStackTrace();
    }
    return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.view.webview.windvane.i
 * JD-Core Version:    0.6.2
 */