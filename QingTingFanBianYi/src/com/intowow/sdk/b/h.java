package com.intowow.sdk.b;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import java.util.Iterator;
import java.util.List;

public class h
{
  private List<c> a = null;

  public void a(Bundle paramBundle)
  {
    if (paramBundle == null);
    while (true)
    {
      return;
      try
      {
        int i = paramBundle.getInt("type");
        Iterator localIterator = this.a.iterator();
        while (localIterator.hasNext())
        {
          c localc = (c)localIterator.next();
          if (localc.b.get(i))
          {
            a locala = localc.a;
            if (locala != null)
              try
              {
                localc.a.a(paramBundle);
              }
              catch (Exception localException)
              {
              }
          }
        }
      }
      catch (Exception paramBundle)
      {
      }
    }
  }

  public void a(a parama)
  {
    if (parama == null)
      return;
    try
    {
      Iterator localIterator = this.a.iterator();
      a locala;
      do
      {
        if (!localIterator.hasNext())
        {
          this.a.add(new c(parama));
          return;
        }
        locala = ((c)localIterator.next()).a;
      }
      while (locala != parama);
      return;
    }
    catch (Exception parama)
    {
    }
  }

  public static abstract interface a
  {
    public abstract List<h.b> a();

    public abstract void a(Bundle paramBundle);
  }

  public static enum b
  {
  }

  class c
  {
    public h.a a;
    public SparseBooleanArray b;

    public c(h.a arg2)
    {
      h.b localb;
      this.a = localb;
      this.b = new SparseBooleanArray();
      this$1 = this.a.a();
      if (h.this != null)
        this$1 = h.this.iterator();
      while (true)
      {
        if (!h.this.hasNext())
          return;
        localb = (h.b)h.this.next();
        this.b.put(localb.ordinal(), true);
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.b.h
 * JD-Core Version:    0.6.2
 */