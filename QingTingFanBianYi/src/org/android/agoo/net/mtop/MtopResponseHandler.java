package org.android.agoo.net.mtop;

import com.umeng.message.proguard.an;

public abstract class MtopResponseHandler extends an
{
  public static final String ERROR_SERVICE_NOT_AVAILABLE = "ERROR_SERVICE_NOT_AVAILABLE";
  public static final String FAIL = "FAIL";
  public static final String SUCCESS = "SUCCESS";

  protected void b(String paramString)
  {
    try
    {
      Result localResult = MtopResponseHelper.parse(paramString);
      if (localResult.isSuccess())
      {
        onSuccess(localResult.getData());
        return;
      }
      onFailure(localResult.getRetCode(), localResult.getRetDesc());
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      onFailure(localThrowable, paramString);
    }
  }

  public void onFailure(String paramString1, String paramString2)
  {
  }

  public void onFailure(Throwable paramThrowable, String paramString)
  {
    onFailure("ERROR_SERVICE_NOT_AVAILABLE", paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.mtop.MtopResponseHandler
 * JD-Core Version:    0.6.2
 */