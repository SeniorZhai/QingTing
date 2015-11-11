package com.umeng.message;

import android.content.Context;
import com.umeng.message.proguard.S;
import org.agoo.ut.device.UTDevice;

public class UmengUT
  implements S
{
  private volatile boolean b = false;

  public void commitEvent(int paramInt, Object paramObject)
  {
  }

  public void commitEvent(int paramInt, Object paramObject1, Object paramObject2)
  {
  }

  public void commitEvent(int paramInt, Object paramObject1, Object paramObject2, Object paramObject3)
  {
  }

  public void commitEvent(int paramInt, Object paramObject1, Object paramObject2, Object paramObject3, String[] paramArrayOfString)
  {
  }

  public String getUtdId(Context paramContext)
  {
    try
    {
      paramContext = UTDevice.getUtdid(paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return null;
  }

  public void onCaughException(Throwable paramThrowable)
  {
  }

  public void start(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
  }

  public void stop(Context paramContext)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UmengUT
 * JD-Core Version:    0.6.2
 */