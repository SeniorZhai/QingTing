package com.tencent.open;

import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class a
{
  HashMap<String, b> a = new HashMap();

  public void a(b paramb, String paramString)
  {
    this.a.put(paramString, paramb);
  }

  public void a(String paramString1, String paramString2, List<String> paramList, a parama)
  {
    Log.d("TDialog", "getResult---objName = " + paramString1 + " methodName = " + paramString2);
    int j = paramList.size();
    int i = 0;
    while (true)
      if (i < j)
        try
        {
          paramList.set(i, URLDecoder.decode((String)paramList.get(i), "UTF-8"));
          i += 1;
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
          while (true)
            localUnsupportedEncodingException.printStackTrace();
        }
    paramString1 = (b)this.a.get(paramString1);
    if (paramString1 != null)
    {
      Log.d("TDialog", "call----");
      paramString1.call(paramString2, paramList, parama);
    }
    do
    {
      return;
      Log.d("TDialog", "not call----objName NOT FIND");
    }
    while (parama == null);
    parama.a();
  }

  public boolean a(WebView paramWebView, String paramString)
  {
    Log.d("Dialog", "canHandleUrl---url = " + paramString);
    if (paramString == null);
    do
    {
      do
        return false;
      while (!Uri.parse(paramString).getScheme().equals("jsbridge"));
      localObject = new ArrayList(Arrays.asList((paramString + "/#").split("/")));
    }
    while (((ArrayList)localObject).size() < 6);
    String str1 = (String)((ArrayList)localObject).get(2);
    String str2 = (String)((ArrayList)localObject).get(3);
    Object localObject = ((ArrayList)localObject).subList(4, ((ArrayList)localObject).size() - 1);
    paramString = new a(paramWebView, 4L, paramString);
    paramWebView.getUrl();
    a(str1, str2, (List)localObject, paramString);
    return true;
  }

  public static class a
  {
    WeakReference<WebView> a;
    long b;
    String c;

    public a(WebView paramWebView, long paramLong, String paramString)
    {
      this.a = new WeakReference(paramWebView);
      this.b = paramLong;
      this.c = paramString;
    }

    public void a()
    {
      WebView localWebView = (WebView)this.a.get();
      if (localWebView == null)
        return;
      localWebView.loadUrl("javascript:window.JsBridge&&JsBridge.callback(" + this.b + ",{'r':1,'result':'no such method'})");
    }

    public void a(Object paramObject)
    {
      WebView localWebView = (WebView)this.a.get();
      if (localWebView == null)
        return;
      String str = "'undefined'";
      if ((paramObject instanceof String))
      {
        paramObject = ((String)paramObject).replace("\\", "\\\\").replace("'", "\\'");
        str = "'" + paramObject + "'";
      }
      while (true)
      {
        localWebView.loadUrl("javascript:window.JsBridge&&JsBridge.callback(" + this.b + ",{'r':0,'result':" + str + "});");
        return;
        if (((paramObject instanceof Number)) || ((paramObject instanceof Long)) || ((paramObject instanceof Integer)) || ((paramObject instanceof Double)) || ((paramObject instanceof Float)))
          str = paramObject.toString();
        else if ((paramObject instanceof Boolean))
          str = paramObject.toString();
      }
    }

    public void a(String paramString)
    {
      WebView localWebView = (WebView)this.a.get();
      if (localWebView != null)
        localWebView.loadUrl("javascript:" + paramString);
    }
  }

  public static class b
  {
    public void call(String paramString, List<String> paramList, a.a parama)
    {
      Method[] arrayOfMethod = getClass().getDeclaredMethods();
      Object localObject2 = null;
      int j = arrayOfMethod.length;
      int i = 0;
      while (true)
      {
        Object localObject1 = localObject2;
        if (i < j)
        {
          localObject1 = arrayOfMethod[i];
          if ((!((Method)localObject1).getName().equals(paramString)) || (((Method)localObject1).getParameterTypes().length != paramList.size()));
        }
        else if (localObject1 == null);
        try
        {
          switch (paramList.size())
          {
          case 0:
            paramString = ((Method)localObject1).invoke(this, new Object[] { paramList.get(0), paramList.get(1), paramList.get(2), paramList.get(3), paramList.get(4), paramList.get(5) });
            if (((Method)localObject1).getReturnType() == Void.class)
            {
              if (parama != null)
                parama.a(null);
              if (parama != null)
                parama.a();
            }
          case 1:
          case 2:
          case 3:
          case 4:
          case 5:
            label188: 
            while ((parama == null) || (!customCallback()))
            {
              return;
              i += 1;
              break;
              paramString = ((Method)localObject1).invoke(this, new Object[0]);
              break label188;
              paramString = ((Method)localObject1).invoke(this, new Object[] { paramList.get(0) });
              break label188;
              paramString = ((Method)localObject1).invoke(this, new Object[] { paramList.get(0), paramList.get(1) });
              break label188;
              paramString = ((Method)localObject1).invoke(this, new Object[] { paramList.get(0), paramList.get(1), paramList.get(2) });
              break label188;
              paramString = ((Method)localObject1).invoke(this, new Object[] { paramList.get(0), paramList.get(1), paramList.get(2), paramList.get(3) });
              break label188;
              paramString = ((Method)localObject1).invoke(this, new Object[] { paramList.get(0), paramList.get(1), paramList.get(2), paramList.get(3), paramList.get(4) });
              break label188;
            }
            parama.a(paramString.toString());
            return;
          }
        }
        catch (IllegalAccessException paramString)
        {
          while (true)
            if (parama != null)
              parama.a();
        }
        catch (InvocationTargetException paramString)
        {
          while (true)
            if (parama != null)
              parama.a();
        }
        catch (Exception paramString)
        {
          while (true)
            if (parama != null)
              parama.a();
        }
      }
    }

    public boolean customCallback()
    {
      return false;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.open.a
 * JD-Core Version:    0.6.2
 */