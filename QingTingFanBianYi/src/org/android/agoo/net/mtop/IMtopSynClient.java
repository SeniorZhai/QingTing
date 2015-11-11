package org.android.agoo.net.mtop;

import android.content.Context;
import com.umeng.message.proguard.aq;
import com.umeng.message.proguard.as.a;

public abstract interface IMtopSynClient
{
  public abstract as.a get(Context paramContext, String paramString, aq paramaq)
    throws Throwable;

  public abstract Result getV3(Context paramContext, MtopRequest paramMtopRequest);

  public abstract void setBaseUrl(String paramString);

  public abstract void setDefaultAppSecret(String paramString);

  public abstract void setDefaultAppkey(String paramString);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.mtop.IMtopSynClient
 * JD-Core Version:    0.6.2
 */