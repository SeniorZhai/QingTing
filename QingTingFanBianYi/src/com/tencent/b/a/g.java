package com.tencent.b.a;

public class g extends h
{
  public static g a = null;

  public g()
  {
    this.d = new a(b);
  }

  public static g a()
  {
    if (a == null);
    try
    {
      if (a == null)
        a = new g();
      return a;
    }
    finally
    {
    }
  }

  public static final void a(String paramString1, String paramString2)
  {
    a().a(1, paramString1, paramString2, null);
  }

  public static final void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    a().a(16, paramString1, paramString2, paramThrowable);
  }

  public static final void b(String paramString1, String paramString2)
  {
    a().a(2, paramString1, paramString2, null);
  }

  public static final void c(String paramString1, String paramString2)
  {
    a().a(4, paramString1, paramString2, null);
  }

  public static final void d(String paramString1, String paramString2)
  {
    a().a(16, paramString1, paramString2, null);
  }

  public void b()
  {
    try
    {
      if (this.d != null)
      {
        this.d.a();
        this.d.b();
        this.d = null;
        a = null;
      }
      return;
    }
    finally
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.b.a.g
 * JD-Core Version:    0.6.2
 */