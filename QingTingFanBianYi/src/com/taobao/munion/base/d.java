package com.taobao.munion.base;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.taobao.newxp.common.AlimmContext;

public class d
{
  public static boolean a = false;

  public static void a(Context paramContext)
  {
    if (a);
    do
    {
      return;
      a = true;
      CookieSyncManager.createInstance(paramContext);
      paramContext = CookieManager.getInstance();
    }
    while (paramContext == null);
    paramContext.setAcceptCookie(true);
    try
    {
      String str = AlimmContext.getAliContext().getAppUtils().C();
      long l = System.currentTimeMillis();
      Object localObject = new StringBuilder("utsid=");
      ((StringBuilder)localObject).append(str);
      ((StringBuilder)localObject).append("_");
      ((StringBuilder)localObject).append("21570058");
      ((StringBuilder)localObject).append("_");
      ((StringBuilder)localObject).append(l);
      paramContext.setCookie("taobao.com", ((StringBuilder)localObject).toString() + ";domain=taobao.com;path=/");
      paramContext.setCookie("etao.com", ((StringBuilder)localObject).toString() + ";domain=etao.com;path=/");
      paramContext.setCookie("tmall.com", ((StringBuilder)localObject).toString() + ";domain=tmall.com;path=/");
      paramContext.setCookie("mmstat.com", ((StringBuilder)localObject).toString() + ";domain=mmstat.com;path=/");
      localObject = AlimmContext.getAliContext().getAppUtils();
      str = ((a)localObject).h();
      localObject = ((a)localObject).g();
      StringBuilder localStringBuilder = new StringBuilder("utkey=umengappkey=");
      localStringBuilder.append((String)localObject);
      localStringBuilder.append("-");
      localStringBuilder.append(str);
      paramContext.setCookie("taobao.com", localStringBuilder.toString() + ";domain=taobao.com;path=/");
      paramContext.setCookie("etao.com", localStringBuilder.toString() + ";domain=etao.com;path=/");
      paramContext.setCookie("tmall.com", localStringBuilder.toString() + ";domain=tmall.com;path=/");
      paramContext.setCookie("mmstat.com", localStringBuilder.toString() + ";domain=mmstat.com;path=/");
      CookieSyncManager.getInstance().sync();
      return;
    }
    catch (Exception paramContext)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.d
 * JD-Core Version:    0.6.2
 */