package com.neusoft.ssp.api;

import android.util.Log;
import com.neusoft.parse.DataParser;
import com.neusoft.ssp.aidl.ITaskCallback.Stub;

final class b extends ITaskCallback.Stub
{
  b(SSP_API paramSSP_API)
  {
  }

  public final void notifyRequest(String paramString)
  {
    Log.v("ccy", "ccy come iTaskCallback msg==" + paramString);
    if (SSP_API.e(this.a).parse(paramString.getBytes()))
    {
      paramString = SSP_API.e(this.a).getAppID();
      String str = SSP_API.e(this.a).getLogicID();
      int i = SSP_API.e(this.a).getFlowID();
      String[] arrayOfString = SSP_API.e(this.a).getData();
      this.a.onRecvRequest(paramString, str, i, arrayOfString);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.neusoft.ssp.api.b
 * JD-Core Version:    0.6.2
 */