package org.android.agoo.net.mtop;

import android.content.Context;
import com.umeng.message.proguard.al;

public class MtopAsyncClientV3 extends al
  implements IMtopAsynClient
{
  private String b;
  private String c;
  private String d;

  public void getV3(Context paramContext, MtopRequest paramMtopRequest, MtopResponseHandler paramMtopResponseHandler)
  {
    try
    {
      MtopRequestHelper.checkAppKeyAndAppSecret(paramMtopRequest, this.b, this.c);
      paramMtopRequest = MtopRequestHelper.getUrlWithRequestParams(paramContext, paramMtopRequest);
      get(paramContext, this.d, paramMtopRequest, paramMtopResponseHandler);
      return;
    }
    catch (Throwable paramContext)
    {
      paramMtopResponseHandler.onFailure(paramContext);
    }
  }

  public void setBaseUrl(String paramString)
  {
    this.d = paramString;
  }

  public void setDefaultAppSecret(String paramString)
  {
    this.c = paramString;
  }

  public void setDefaultAppkey(String paramString)
  {
    this.b = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.mtop.MtopAsyncClientV3
 * JD-Core Version:    0.6.2
 */