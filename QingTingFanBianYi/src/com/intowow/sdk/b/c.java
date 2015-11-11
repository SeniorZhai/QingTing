package com.intowow.sdk.b;

import com.intowow.sdk.f.b;
import com.intowow.sdk.h.a;
import com.intowow.sdk.h.j;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.b;
import com.intowow.sdk.triggerresponse.TriggerResponse;
import java.util.HashMap;
import java.util.Map;

public class c
{
  private b a = null;
  private h b = null;
  private Map<String, a> c = null;
  private long d = 15000L;

  public c(b paramb, h paramh)
  {
    this.a = paramb;
    this.b = paramh;
    this.c = new HashMap();
  }

  public void a()
  {
    this.c.clear();
  }

  public void a(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String paramString4, j paramj)
  {
    if (this.c.get(paramString2) == null)
      this.c.put(paramString2, new a(this.a.b(paramInt1)));
    ((a)this.c.get(paramString2)).a(paramInt2, paramString1, paramString3, paramString4, paramj);
  }

  public void a(long paramLong)
  {
    this.d = paramLong;
  }

  class a
  {
    private ADProfile b;
    private Map<String, Map<j, Long>> c;

    public a(ADProfile arg2)
    {
      Object localObject;
      this.b = localObject;
      this.c = new HashMap();
    }

    public void a(int paramInt, String paramString1, String paramString2, String paramString3, j paramj)
    {
      if ((Map)this.c.get(paramString2) == null)
        this.c.put(paramString2, new HashMap());
      Map localMap = (Map)this.c.get(paramString2);
      long l = System.currentTimeMillis();
      if ((localMap.containsKey(paramj)) && (l - ((Long)localMap.get(paramj)).longValue() < c.a(c.this)));
      boolean bool1;
      do
      {
        return;
        boolean bool2 = false;
        bool1 = bool2;
        if (this.b != null)
        {
          paramString2 = this.b.r().a(paramString2, paramj);
          bool1 = bool2;
          if (paramString2 != null)
          {
            paramString1 = a.a(paramString3, this.b, paramj, paramInt, paramString1, paramString2);
            c.b(c.this).a(paramString1);
            bool1 = paramString2.e();
          }
        }
      }
      while (bool1);
      localMap.put(paramj, Long.valueOf(l));
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.b.c
 * JD-Core Version:    0.6.2
 */