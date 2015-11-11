package com.taobao.newxp.view.audio;

import com.taobao.newxp.net.MMEntity;
import com.taobao.newxp.net.s.a;
import java.util.Map;

public class a
{
  public static class a extends s.a
  {
    private int b = -1;
    private int c = -1;
    private int d = -1;

    public a(MMEntity paramMMEntity)
    {
      super();
    }

    public Map<String, Object> b()
    {
      Map localMap = super.b();
      localMap.put("dur", Integer.valueOf(this.b));
      localMap.put("tl", Integer.valueOf(this.c));
      localMap.put("st", Integer.valueOf(this.d));
      return localMap;
    }

    public a f(int paramInt)
    {
      this.b = paramInt;
      return this;
    }

    public a g(int paramInt)
    {
      this.c = paramInt;
      return this;
    }

    public a h(int paramInt)
    {
      this.d = paramInt;
      return this;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.audio.a
 * JD-Core Version:    0.6.2
 */