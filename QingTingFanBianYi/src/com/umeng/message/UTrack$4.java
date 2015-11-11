package com.umeng.message;

import com.umeng.common.message.Log;
import com.umeng.message.proguard.C.e;
import com.umeng.message.proguard.y;
import org.json.JSONObject;

class UTrack$4
  implements Runnable
{
  UTrack$4(UTrack paramUTrack)
  {
  }

  public void run()
  {
    try
    {
      JSONObject localJSONObject = UTrack.b(this.a);
      String str = UTrack.c(this.a);
      if (!y.d(str))
      {
        Log.c(UTrack.a(), "TestDevice sign =" + str);
        localJSONObject.put("TD", str);
      }
      if ("ok".equalsIgnoreCase(UTrack.a(this.a, localJSONObject, MsgConstant.REGISTER_ENDPOINT).optString("success")))
        UmengRegistrar.a(UTrack.a(this.a), true);
      return;
    }
    catch (C.e locale)
    {
      locale.printStackTrace();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    finally
    {
      UTrack.c(false);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UTrack.4
 * JD-Core Version:    0.6.2
 */