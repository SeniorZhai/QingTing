package com.taobao.newxp.net;

import android.text.TextUtils;
import android.util.Log;
import com.taobao.munion.base.volley.i;
import com.taobao.munion.base.volley.k;
import com.taobao.munion.base.volley.n;
import com.taobao.newxp.common.AlimmContext;
import com.taobao.newxp.common.ExchangeConstants;
import com.taobao.newxp.common.b.b;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

public class o extends a
{
  Map<String, String> a;
  String b = "";
  MMEntity c;

  public o(d paramd, String paramString, com.taobao.munion.base.f paramf)
  {
    super(paramString, paramf);
    this.b = a(paramd, paramString);
    if ((paramd instanceof MMEntity))
      this.c = ((MMEntity)paramd);
  }

  public static String a(d paramd, String paramString)
  {
    try
    {
      Object localObject = AlimmContext.getAliContext().getAppUtils();
      String str1 = b.a(paramString);
      String str2 = ((com.taobao.munion.base.a)localObject).g();
      String str3 = ((com.taobao.munion.base.a)localObject).f();
      String str4 = ((com.taobao.munion.base.a)localObject).r();
      localObject = ((com.taobao.munion.base.a)localObject).C();
      if (TextUtils.isEmpty(paramd.appkey))
      {
        paramString = "";
        if (!TextUtils.isEmpty(paramd.slotId))
          break label228;
      }
      label228: for (paramd = ""; ; paramd = paramd.slotId)
      {
        long l = System.currentTimeMillis() / 1000L;
        byte[] arrayOfByte = new byte[4];
        new Random().nextBytes(arrayOfByte);
        int i = arrayOfByte[0];
        int j = arrayOfByte[1];
        int k = arrayOfByte[2];
        return URLEncoder.encode(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%d,%d", new Object[] { str1, paramd, paramString, str2, str3, str4, "", localObject, Long.valueOf(l), Integer.valueOf(arrayOfByte[3] << 24 | (i & 0xFF | j << 8 & 0xFF00 | k << 24 >>> 8)) }), "UTF-8");
        paramString = paramd.appkey;
        break;
      }
    }
    catch (Exception paramd)
    {
      Log.w(ExchangeConstants.LOG_TAG, "", paramd);
    }
    return "";
  }

  protected n<JSONObject> a(i parami)
  {
    try
    {
      if ((this.c != null) && (this.c.timeline[1] == 0L))
        this.c.timeline[1] = System.currentTimeMillis();
      parami = n.a(new JSONObject(new String(parami.b, com.taobao.munion.base.volley.a.f.a(parami.c))), com.taobao.munion.base.volley.a.f.a(parami));
      return parami;
    }
    catch (UnsupportedEncodingException parami)
    {
      return n.a(new k(parami));
    }
    catch (JSONException parami)
    {
    }
    return n.a(new k(parami));
  }

  public Map<String, String> k()
    throws com.taobao.munion.base.volley.a
  {
    Map localMap = super.k();
    localMap.put("accept-ta", this.b);
    if ((this.c != null) && (this.c.timeline[0] == 0L))
      this.c.timeline[0] = System.currentTimeMillis();
    return localMap;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.net.o
 * JD-Core Version:    0.6.2
 */