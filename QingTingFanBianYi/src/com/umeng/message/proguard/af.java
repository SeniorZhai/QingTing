package com.umeng.message.proguard;

class af
{
  private boolean a = false;
  private String b;

  af(boolean paramBoolean, String paramString)
  {
    this.a = paramBoolean;
    this.b = paramString;
  }

  long a()
  {
    if (this.a)
      return 40000L;
    return 300000L;
  }

  long a(long paramLong)
  {
    paramLong = aQ.a(paramLong, this.b);
    if (paramLong <= 2000L)
      return 2000L;
    return paramLong;
  }

  long b()
  {
    if (this.a)
      return 20000L;
    return 120000L;
  }

  long c()
  {
    if (this.a)
      return 1000L;
    return 2000L;
  }

  long d()
  {
    if (this.a)
      return a(5000L);
    return a(10000L);
  }

  long e()
  {
    if (this.a)
      return a(2000L);
    return a(5000L);
  }

  long f()
  {
    if (this.a)
      return a(2000L);
    return a(5000L);
  }

  long g()
  {
    if (this.a)
      return a(5000L);
    return a(20000L);
  }

  long h()
  {
    if (this.a)
      return a(5000L);
    return a(15000L);
  }

  public long i()
  {
    if (this.a)
      return 5000L;
    return 40000L;
  }

  long j()
  {
    if (this.a)
      return 5000L;
    return 10000L;
  }

  long k()
  {
    if (this.a)
      return 5000L;
    return 10000L;
  }

  long l()
  {
    if (this.a)
      return 600000L;
    return 1800000L;
  }

  long m()
  {
    if (this.a)
      return 10000L;
    return 60000L;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.af
 * JD-Core Version:    0.6.2
 */