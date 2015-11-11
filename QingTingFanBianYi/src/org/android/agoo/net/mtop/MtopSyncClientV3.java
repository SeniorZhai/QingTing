package org.android.agoo.net.mtop;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.message.proguard.as;
import com.umeng.message.proguard.as.a;

public class MtopSyncClientV3 extends as
  implements IMtopSynClient
{
  private volatile String a;
  private volatile String b;
  private volatile String c;

  public Result getV3(Context paramContext, MtopRequest paramMtopRequest)
  {
    try
    {
      MtopRequestHelper.checkAppKeyAndAppSecret(paramMtopRequest, this.a, this.b);
      paramMtopRequest = MtopRequestHelper.getUrlWithRequestParams(paramContext, paramMtopRequest);
      paramContext = get(paramContext, this.c, paramMtopRequest).b;
      if (TextUtils.isEmpty(paramContext))
      {
        paramContext = new Result();
        paramContext.setSuccess(false);
        paramContext.setRetDesc("request result is null");
        return paramContext;
      }
      paramContext = MtopResponseHelper.parse(paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      paramMtopRequest = new Result();
      paramMtopRequest.setSuccess(false);
      paramMtopRequest.setRetDesc(paramContext.getMessage());
    }
    return paramMtopRequest;
  }

  public void setBaseUrl(String paramString)
  {
    this.c = paramString;
  }

  public void setDefaultAppSecret(String paramString)
  {
    this.b = paramString;
  }

  public void setDefaultAppkey(String paramString)
  {
    this.a = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.mtop.MtopSyncClientV3
 * JD-Core Version:    0.6.2
 */