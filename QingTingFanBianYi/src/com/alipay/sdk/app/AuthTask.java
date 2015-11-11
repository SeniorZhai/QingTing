package com.alipay.sdk.app;

import android.app.Activity;
import com.alipay.sdk.data.MspConfig;
import com.alipay.sdk.sys.GlobalContext;
import com.alipay.sdk.util.AuthHelper;

public class AuthTask
{
  private Activity a;

  private AuthTask(Activity paramActivity)
  {
    this.a = paramActivity;
  }

  private String a(String paramString)
  {
    try
    {
      GlobalContext.a().a(this.a, MspConfig.a());
      paramString = AuthHelper.a(this.a, paramString);
      return paramString;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.app.AuthTask
 * JD-Core Version:    0.6.2
 */