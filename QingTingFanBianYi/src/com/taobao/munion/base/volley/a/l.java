package com.taobao.munion.base.volley.a;

import com.taobao.munion.base.Log;
import com.taobao.munion.base.volley.i;
import com.taobao.munion.base.volley.n;
import com.taobao.munion.base.volley.n.a;
import com.taobao.munion.base.volley.n.b;
import java.io.UnsupportedEncodingException;

public class l extends com.taobao.munion.base.volley.l<String>
{
  private final n.b<String> a;

  public l(int paramInt, String paramString, n.b<String> paramb, n.a parama)
  {
    super(paramInt, paramString, parama);
    this.a = paramb;
  }

  public l(String paramString, n.b<String> paramb, n.a parama)
  {
    this(0, paramString, paramb, parama);
  }

  protected n<String> a(i parami)
  {
    try
    {
      String str1 = new String(parami.b, f.a(parami.c));
      Log.i("Request:%s   ResponseCode:%s", new Object[] { f(), Integer.valueOf(parami.a) });
      return n.a(str1, f.a(parami));
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
        String str2 = new String(parami.b);
    }
  }

  protected void c(String paramString)
  {
    if (this.a != null)
      this.a.a(paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.volley.a.l
 * JD-Core Version:    0.6.2
 */