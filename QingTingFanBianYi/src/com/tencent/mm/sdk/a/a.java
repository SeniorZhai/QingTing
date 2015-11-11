package com.tencent.mm.sdk.a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.sdk.a.a.b;
import com.tencent.mm.sdk.b.e;

public final class a
{
  public static boolean a(Context paramContext, a parama)
  {
    if ((paramContext == null) || (parama == null))
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.MMessageAct", "send fail, invalid argument");
      return false;
    }
    if (e.c(parama.b))
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.MMessageAct", "send fail, invalid targetPkgName, targetPkgName = " + parama.b);
      return false;
    }
    if (e.c(parama.c))
      parama.c = (parama.b + ".wxapi.WXEntryActivity");
    com.tencent.mm.sdk.b.a.c("MicroMsg.SDK.MMessageAct", "send, targetPkgName = " + parama.b + ", targetClassName = " + parama.c);
    Intent localIntent = new Intent();
    localIntent.setClassName(parama.b, parama.c);
    if (parama.e != null)
      localIntent.putExtras(parama.e);
    String str = paramContext.getPackageName();
    localIntent.putExtra("_mmessage_sdkVersion", 570490883);
    localIntent.putExtra("_mmessage_appPackage", str);
    localIntent.putExtra("_mmessage_content", parama.d);
    localIntent.putExtra("_mmessage_checksum", b.a(parama.d, 570490883, str));
    if (parama.flags == -1)
      localIntent.addFlags(268435456).addFlags(134217728);
    try
    {
      while (true)
      {
        paramContext.startActivity(localIntent);
        com.tencent.mm.sdk.b.a.c("MicroMsg.SDK.MMessageAct", "send mm message, intent=" + localIntent);
        return true;
        localIntent.setFlags(parama.flags);
      }
    }
    catch (Exception paramContext)
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.MMessageAct", "send fail, ex = %s", new Object[] { paramContext.getMessage() });
    }
    return false;
  }

  public static final class a
  {
    public String b;
    public String c;
    public String d;
    public Bundle e;
    public int flags = -1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.a.a
 * JD-Core Version:    0.6.2
 */