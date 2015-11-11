package com.taobao.newxp.net;

import com.taobao.munion.base.Log;
import com.taobao.munion.base.volley.s;
import com.taobao.newxp.common.AlimmContext;
import org.json.JSONObject;

public class m
{
  public void a(String paramString1, String paramString2, final q paramq)
  {
    paramq = new q()
    {
      public void a(s paramAnonymouss)
      {
        Log.i("onErrorResponse ：" + paramAnonymouss.toString(), new Object[0]);
      }

      public void a(String paramAnonymousString)
      {
        if (paramAnonymousString != "")
          paramq.a(paramAnonymousString);
      }

      public void a(JSONObject paramAnonymousJSONObject)
      {
        Log.i("onResponse ：" + paramAnonymousJSONObject.toString(), new Object[0]);
      }
    };
    paramString1 = new r(new MMEntity(), paramString1, paramq);
    paramString1.d(paramString2);
    paramString2 = AlimmContext.getAliContext().getRedirctQueue();
    paramq = (k)paramString2;
    paramq.a(Boolean.valueOf(true));
    paramq.a("simba.taobao.com");
    paramString2.a(paramString1);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.net.m
 * JD-Core Version:    0.6.2
 */