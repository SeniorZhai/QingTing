package com.tencent.mm.sdk.a.a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.sdk.b.e;

public final class a
{
  public static boolean a(Context paramContext, a parama)
  {
    if ((paramContext == null) || (parama == null))
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.MMessage", "send fail, invalid argument");
      return false;
    }
    if (e.c(parama.g))
    {
      com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.MMessage", "send fail, action is null");
      return false;
    }
    String str1 = null;
    if (!e.c(parama.f))
      str1 = parama.f + ".permission.MM_MESSAGE";
    Intent localIntent = new Intent(parama.g);
    if (parama.e != null)
      localIntent.putExtras(parama.e);
    String str2 = paramContext.getPackageName();
    localIntent.putExtra("_mmessage_sdkVersion", 570490883);
    localIntent.putExtra("_mmessage_appPackage", str2);
    localIntent.putExtra("_mmessage_content", parama.d);
    localIntent.putExtra("_mmessage_checksum", b.a(parama.d, 570490883, str2));
    paramContext.sendBroadcast(localIntent, str1);
    com.tencent.mm.sdk.b.a.c("MicroMsg.SDK.MMessage", "send mm message, intent=" + localIntent + ", perm=" + str1);
    return true;
  }

  public static final class a
  {
    public String d;
    public Bundle e;
    public String f;
    public String g;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.a.a.a
 * JD-Core Version:    0.6.2
 */