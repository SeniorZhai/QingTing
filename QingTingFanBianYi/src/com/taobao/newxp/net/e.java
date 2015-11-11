package com.taobao.newxp.net;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.taobao.munion.base.Log;
import com.taobao.munion.base.volley.a.f;
import com.taobao.munion.base.volley.i;
import com.taobao.munion.base.volley.n;
import com.taobao.newxp.common.AlimmContext;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.json.JSONObject;

public class e extends a
{
  private static String[] b = { ".taobao.com", ".tmall.com", ".etao.com", ".mmstat.com" };
  private static e c = new e();
  protected boolean a = false;

  private e()
  {
    super("http://log.mmstat.com", null);
  }

  public static e a()
  {
    if (c.a)
      return null;
    return c;
  }

  private void a(Context paramContext, CookieStore paramCookieStore)
  {
    CookieSyncManager.createInstance(paramContext);
    CookieManager localCookieManager = CookieManager.getInstance();
    if (localCookieManager != null)
    {
      localCookieManager.setAcceptCookie(true);
      Iterator localIterator = paramCookieStore.getCookies().iterator();
      while (localIterator.hasNext())
      {
        Cookie localCookie = (Cookie)localIterator.next();
        Object localObject1 = localCookie.getDomain();
        String str1 = localCookie.getName();
        String str2 = localCookie.getPath();
        String str3 = localCookie.getValue();
        Date localDate = localCookie.getExpiryDate();
        paramContext = "";
        if (localDate != null)
          paramContext = localCookie.getExpiryDate().toString();
        int j = localCookie.getVersion();
        if (((String)localObject1).contains("mmstat.com"))
        {
          localObject1 = b;
          int k = localObject1.length;
          int i = 0;
          while (i < k)
          {
            localDate = localObject1[i];
            Object localObject2 = new BasicClientCookie(str1, localCookie.getValue());
            ((BasicClientCookie)localObject2).setVersion(localCookie.getVersion());
            ((BasicClientCookie)localObject2).setDomain(localDate);
            ((BasicClientCookie)localObject2).setPath(localCookie.getPath());
            ((BasicClientCookie)localObject2).setExpiryDate(localCookie.getExpiryDate());
            paramCookieStore.addCookie((Cookie)localObject2);
            localObject2 = new StringBuilder(str1);
            ((StringBuilder)localObject2).append("=").append(str3).append(";domain=").append(localDate).append(";path=").append(str2).append(";expiry=").append(paramContext).append(";version=").append(j);
            localCookieManager.setCookie(localDate, ((StringBuilder)localObject2).toString());
            Log.i("synchronize webview cookie:" + ((StringBuilder)localObject2).toString(), new Object[0]);
            i += 1;
          }
        }
      }
      CookieSyncManager.getInstance().sync();
    }
  }

  public static final boolean a(CookieStore paramCookieStore)
  {
    while (true)
    {
      try
      {
        CookieManager localCookieManager = CookieManager.getInstance();
        localCookieManager.setAcceptCookie(true);
        try
        {
          Object localObject1 = paramCookieStore.getCookies();
          Object localObject2 = b;
          int k = localObject2.length;
          i = 0;
          if (i >= k)
            break label232;
          Object localObject3 = localObject2[i];
          Iterator localIterator = ((List)localObject1).iterator();
          if (!localIterator.hasNext())
            break label226;
          if (!localObject3.equals(((Cookie)localIterator.next()).getDomain()))
            continue;
          j = 1;
          break label238;
          if (i != 0)
          {
            paramCookieStore = paramCookieStore.getCookies().iterator();
            if (paramCookieStore.hasNext())
            {
              localObject2 = (Cookie)paramCookieStore.next();
              if (!((Cookie)localObject2).getName().equals("cna"))
                continue;
              localObject1 = ((Cookie)localObject2).getDomain();
              localObject2 = ((Cookie)localObject2).getValue();
              bool = localCookieManager.getCookie((String)localObject1).contains((CharSequence)localObject2);
              if (bool)
                continue;
              j = 0;
              if ((i != 0) && (j != 0))
              {
                bool = true;
                return bool;
                i += 1;
                continue;
              }
              bool = false;
              continue;
            }
          }
        }
        catch (Exception paramCookieStore)
        {
          boolean bool = false;
          continue;
        }
      }
      finally
      {
      }
      int j = 1;
      continue;
      label226: j = 0;
      break label238;
      label232: int i = 1;
      continue;
      label238: if (j == 0)
        i = 0;
    }
  }

  public static final boolean z()
  {
    try
    {
      boolean bool = a(AlimmContext.getAliContext().getCookieStore());
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected n<JSONObject> a(i parami)
  {
    AlimmContext localAlimmContext = AlimmContext.getAliContext();
    CookieStore localCookieStore = localAlimmContext.getCookieStore();
    a(localAlimmContext.getAppContext(), localCookieStore);
    this.a = false;
    return n.a(new JSONObject(), f.a(parami));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.net.e
 * JD-Core Version:    0.6.2
 */