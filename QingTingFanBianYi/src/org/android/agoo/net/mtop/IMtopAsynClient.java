package org.android.agoo.net.mtop;

import android.content.Context;

public abstract interface IMtopAsynClient
{
  public abstract void getV3(Context paramContext, MtopRequest paramMtopRequest, MtopResponseHandler paramMtopResponseHandler);

  public abstract void setBaseUrl(String paramString);

  public abstract void setDefaultAppSecret(String paramString);

  public abstract void setDefaultAppkey(String paramString);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.mtop.IMtopAsynClient
 * JD-Core Version:    0.6.2
 */