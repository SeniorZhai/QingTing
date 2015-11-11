package com.taobao.newxp.net;

import com.taobao.munion.base.Log;
import com.taobao.munion.base.f;
import com.taobao.munion.base.volley.i;
import com.taobao.munion.base.volley.l;
import com.taobao.munion.base.volley.n;
import com.taobao.munion.base.volley.n.a;
import com.taobao.munion.base.volley.s;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class a extends l<JSONObject>
{
  private static final String a = "utf-8";
  private static final String b = String.format("application/json; charset=%s", new Object[] { "utf-8" });
  private final f c;
  private n.a d = new n.a()
  {
    public void a(s paramAnonymouss)
    {
    }
  };
  private final String e;
  private String f = b;

  public a(int paramInt, String paramString1, String paramString2, f paramf)
  {
    super(paramInt, paramString1, paramf);
    this.c = paramf;
    this.e = paramString2;
  }

  public a(String paramString, f paramf)
  {
    this(0, paramString, null, paramf);
  }

  protected n<JSONObject> a(i parami)
  {
    return null;
  }

  protected void a(JSONObject paramJSONObject)
  {
    if (this.c != null)
      this.c.a(paramJSONObject);
  }

  public void c(String paramString)
  {
    this.f = paramString;
  }

  public Map<String, String> k()
    throws com.taobao.munion.base.volley.a
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
    return localHashMap;
  }

  public String n()
  {
    return r();
  }

  public byte[] o()
  {
    return s();
  }

  public String r()
  {
    return this.f;
  }

  public byte[] s()
  {
    try
    {
      if (this.e == null)
        return null;
      byte[] arrayOfByte = this.e.getBytes("utf-8");
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      Log.w("Unsupported Encoding while trying to get the bytes of %s using %s", new Object[] { this.e, "utf-8" });
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.net.a
 * JD-Core Version:    0.6.2
 */