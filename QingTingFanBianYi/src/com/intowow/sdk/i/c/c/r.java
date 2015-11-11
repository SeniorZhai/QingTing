package com.intowow.sdk.i.c.c;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.intowow.sdk.model.ADProfile.i;

public class r
{
  private static SparseArray<c> a = null;
  private static SparseBooleanArray b = null;

  static
  {
    a = new SparseArray();
    a.put(ADProfile.i.g.ordinal(), new i.a());
    a.put(ADProfile.i.f.ordinal(), new h.a());
    a.put(ADProfile.i.c.ordinal(), new p.a());
    a.put(ADProfile.i.b.ordinal(), new o.a());
    a.put(ADProfile.i.j.ordinal(), new g.a());
    a.put(ADProfile.i.h.ordinal(), new f.a());
    a.put(ADProfile.i.d.ordinal(), new n.a());
    a.put(ADProfile.i.e.ordinal(), new q.a());
    a.put(ADProfile.i.i.ordinal(), new j.a());
    b = new SparseBooleanArray();
    int i = 0;
    while (true)
    {
      if (i >= a.size())
        return;
      b.put(a.keyAt(i), true);
      i += 1;
    }
  }

  public static SparseBooleanArray a()
  {
    return b;
  }

  public static c a(ADProfile.i parami)
  {
    return (c)a.get(parami.ordinal());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.c.r
 * JD-Core Version:    0.6.2
 */