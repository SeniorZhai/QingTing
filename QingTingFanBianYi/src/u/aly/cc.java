package u.aly;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class cc
{
  private final cy a;
  private final dl b = new dl();

  public cc()
  {
    this(new cs.a());
  }

  public cc(da paramda)
  {
    this.a = paramda.a(this.b);
  }

  private Object a(byte paramByte, byte[] paramArrayOfByte, cg paramcg, cg[] paramArrayOfcg)
    throws cf
  {
    try
    {
      paramArrayOfByte = j(paramArrayOfByte, paramcg, paramArrayOfcg);
      if (paramArrayOfByte != null)
        switch (paramByte)
        {
        default:
        case 2:
        case 3:
        case 4:
        case 6:
        case 8:
        case 10:
        case 11:
        case 100:
        }
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                      return null;
                    while (paramArrayOfByte.b != 2);
                    boolean bool = this.a.t();
                    return Boolean.valueOf(bool);
                  }
                  while (paramArrayOfByte.b != 3);
                  byte b1 = this.a.u();
                  return Byte.valueOf(b1);
                }
                while (paramArrayOfByte.b != 4);
                double d = this.a.y();
                return Double.valueOf(d);
              }
              while (paramArrayOfByte.b != 6);
              short s = this.a.v();
              return Short.valueOf(s);
            }
            while (paramArrayOfByte.b != 8);
            paramByte = this.a.w();
            return Integer.valueOf(paramByte);
          }
          while (paramArrayOfByte.b != 10);
          long l = this.a.x();
          return Long.valueOf(l);
        }
        while (paramArrayOfByte.b != 11);
        paramArrayOfByte = this.a.z();
        return paramArrayOfByte;
      }
      while (paramArrayOfByte.b != 11);
      paramArrayOfByte = this.a.A();
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      throw new cf(paramArrayOfByte);
    }
    finally
    {
      this.b.e();
      this.a.B();
    }
    throw paramArrayOfByte;
  }

  private ct j(byte[] paramArrayOfByte, cg paramcg, cg[] paramArrayOfcg)
    throws cf
  {
    int j = 0;
    this.b.a(paramArrayOfByte);
    cg[] arrayOfcg = new cg[paramArrayOfcg.length + 1];
    arrayOfcg[0] = paramcg;
    int i = 0;
    while (i < paramArrayOfcg.length)
    {
      arrayOfcg[(i + 1)] = paramArrayOfcg[i];
      i += 1;
    }
    this.a.j();
    paramArrayOfByte = null;
    i = j;
    while (i < arrayOfcg.length)
    {
      paramcg = this.a.l();
      if ((paramcg.b == 0) || (paramcg.c > arrayOfcg[i].a()))
        return null;
      if (paramcg.c != arrayOfcg[i].a())
      {
        db.a(this.a, paramcg.b);
        this.a.m();
        paramArrayOfByte = paramcg;
      }
      else
      {
        j = i + 1;
        paramArrayOfByte = paramcg;
        i = j;
        if (j < arrayOfcg.length)
        {
          this.a.j();
          paramArrayOfByte = paramcg;
          i = j;
        }
      }
    }
    return paramArrayOfByte;
  }

  public Boolean a(byte[] paramArrayOfByte, cg paramcg, cg[] paramArrayOfcg)
    throws cf
  {
    return (Boolean)a((byte)2, paramArrayOfByte, paramcg, paramArrayOfcg);
  }

  public void a(bz parambz, String paramString)
    throws cf
  {
    a(parambz, paramString.getBytes());
  }

  public void a(bz parambz, String paramString1, String paramString2)
    throws cf
  {
    try
    {
      a(parambz, paramString1.getBytes(paramString2));
      return;
    }
    catch (UnsupportedEncodingException parambz)
    {
      throw new cf("JVM DOES NOT SUPPORT ENCODING: " + paramString2);
    }
    finally
    {
      this.a.B();
    }
    throw parambz;
  }

  public void a(bz parambz, byte[] paramArrayOfByte)
    throws cf
  {
    try
    {
      this.b.a(paramArrayOfByte);
      parambz.a(this.a);
      return;
    }
    finally
    {
      this.b.e();
      this.a.B();
    }
    throw parambz;
  }

  public void a(bz parambz, byte[] paramArrayOfByte, cg paramcg, cg[] paramArrayOfcg)
    throws cf
  {
    try
    {
      if (j(paramArrayOfByte, paramcg, paramArrayOfcg) != null)
        parambz.a(this.a);
      return;
    }
    catch (Exception parambz)
    {
      throw new cf(parambz);
    }
    finally
    {
      this.b.e();
      this.a.B();
    }
    throw parambz;
  }

  public Byte b(byte[] paramArrayOfByte, cg paramcg, cg[] paramArrayOfcg)
    throws cf
  {
    return (Byte)a((byte)3, paramArrayOfByte, paramcg, paramArrayOfcg);
  }

  public Double c(byte[] paramArrayOfByte, cg paramcg, cg[] paramArrayOfcg)
    throws cf
  {
    return (Double)a((byte)4, paramArrayOfByte, paramcg, paramArrayOfcg);
  }

  public Short d(byte[] paramArrayOfByte, cg paramcg, cg[] paramArrayOfcg)
    throws cf
  {
    return (Short)a((byte)6, paramArrayOfByte, paramcg, paramArrayOfcg);
  }

  public Integer e(byte[] paramArrayOfByte, cg paramcg, cg[] paramArrayOfcg)
    throws cf
  {
    return (Integer)a((byte)8, paramArrayOfByte, paramcg, paramArrayOfcg);
  }

  public Long f(byte[] paramArrayOfByte, cg paramcg, cg[] paramArrayOfcg)
    throws cf
  {
    return (Long)a((byte)10, paramArrayOfByte, paramcg, paramArrayOfcg);
  }

  public String g(byte[] paramArrayOfByte, cg paramcg, cg[] paramArrayOfcg)
    throws cf
  {
    return (String)a((byte)11, paramArrayOfByte, paramcg, paramArrayOfcg);
  }

  public ByteBuffer h(byte[] paramArrayOfByte, cg paramcg, cg[] paramArrayOfcg)
    throws cf
  {
    return (ByteBuffer)a((byte)100, paramArrayOfByte, paramcg, paramArrayOfcg);
  }

  public Short i(byte[] paramArrayOfByte, cg paramcg, cg[] paramArrayOfcg)
    throws cf
  {
    try
    {
      if (j(paramArrayOfByte, paramcg, paramArrayOfcg) != null)
      {
        this.a.j();
        short s = this.a.l().c;
        return Short.valueOf(s);
      }
      return null;
    }
    catch (Exception paramArrayOfByte)
    {
      throw new cf(paramArrayOfByte);
    }
    finally
    {
      this.b.e();
      this.a.B();
    }
    throw paramArrayOfByte;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.cc
 * JD-Core Version:    0.6.2
 */