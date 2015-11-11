package com.intowow.sdk.b;

import android.os.Bundle;

public class b
{
  private a a = a.a;
  private int b = 0;
  private k c = null;

  public b(k paramk)
  {
    this.c = paramk;
  }

  private void b()
  {
    a locala = a.a;
    if (!com.intowow.sdk.h.k.a(this.b))
      locala = a.a;
    while (true)
    {
      if (locala != this.a)
      {
        this.a = locala;
        c();
      }
      return;
      if (com.intowow.sdk.h.k.b(this.b))
        locala = a.b;
      else
        locala = a.c;
    }
  }

  private void c()
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("type", h.b.p.ordinal());
    localBundle.putString("download_strategy", this.a.toString());
    this.c.e().a(localBundle);
  }

  public a a()
  {
    return this.a;
  }

  public void a(int paramInt)
  {
    if (this.b != paramInt);
    for (int i = 1; ; i = 0)
    {
      if (i != 0)
      {
        this.b = paramInt;
        b();
      }
      return;
    }
  }

  public static enum a
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.b.b
 * JD-Core Version:    0.6.2
 */