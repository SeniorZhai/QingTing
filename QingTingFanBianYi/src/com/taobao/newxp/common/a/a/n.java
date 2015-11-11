package com.taobao.newxp.common.a.a;

import java.util.HashMap;

public class n
  implements l
{
  private HashMap<String, m> a = new HashMap(7);

  public HashMap<String, m> a()
  {
    return this.a;
  }

  public void a(a parama)
  {
    String str;
    if (parama != null)
    {
      str = parama.b;
      if ((str != null) && (str.trim().length() > 0))
      {
        localm = (m)this.a.get(str);
        if (localm == null)
          break label48;
        localm.a(parama.c);
      }
    }
    return;
    label48: m localm = new m(str);
    localm.a(parama.c);
    this.a.put(str, localm);
  }

  public void b()
  {
    this.a.clear();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.a.a.n
 * JD-Core Version:    0.6.2
 */