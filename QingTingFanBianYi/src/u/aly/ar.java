package u.aly;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ar
  implements Serializable, Cloneable, bz<ar, e>
{
  public static final Map<e, cl> d;
  private static final dd e = new dd("IdSnapshot");
  private static final ct f = new ct("identity", (byte)11, (short)1);
  private static final ct g = new ct("ts", (byte)10, (short)2);
  private static final ct h = new ct("version", (byte)8, (short)3);
  private static final Map<Class<? extends dg>, dh> i = new HashMap();
  private static final int j = 0;
  private static final int k = 1;
  public String a;
  public long b;
  public int c;
  private byte l = 0;

  static
  {
    i.put(di.class, new b(null));
    i.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("identity", (byte)1, new cm((byte)11)));
    localEnumMap.put(e.b, new cl("ts", (byte)1, new cm((byte)10)));
    localEnumMap.put(e.c, new cl("version", (byte)1, new cm((byte)8)));
    d = Collections.unmodifiableMap(localEnumMap);
    cl.a(ar.class, d);
  }

  public ar()
  {
  }

  public ar(String paramString, long paramLong, int paramInt)
  {
    this();
    this.a = paramString;
    this.b = paramLong;
    b(true);
    this.c = paramInt;
    c(true);
  }

  public ar(ar paramar)
  {
    this.l = paramar.l;
    if (paramar.e())
      this.a = paramar.a;
    this.b = paramar.b;
    this.c = paramar.c;
  }

  private void a(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    try
    {
      this.l = 0;
      a(new cs(new dk(paramObjectInputStream)));
      return;
    }
    catch (cf paramObjectInputStream)
    {
    }
    throw new IOException(paramObjectInputStream.getMessage());
  }

  private void a(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    try
    {
      b(new cs(new dk(paramObjectOutputStream)));
      return;
    }
    catch (cf paramObjectOutputStream)
    {
    }
    throw new IOException(paramObjectOutputStream.getMessage());
  }

  public ar a()
  {
    return new ar(this);
  }

  public ar a(int paramInt)
  {
    this.c = paramInt;
    c(true);
    return this;
  }

  public ar a(long paramLong)
  {
    this.b = paramLong;
    b(true);
    return this;
  }

  public ar a(String paramString)
  {
    this.a = paramString;
    return this;
  }

  public void a(cy paramcy)
    throws cf
  {
    ((dh)i.get(paramcy.D())).b().b(paramcy, this);
  }

  public void a(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.a = null;
  }

  public void b()
  {
    this.a = null;
    b(false);
    this.b = 0L;
    c(false);
    this.c = 0;
  }

  public void b(cy paramcy)
    throws cf
  {
    ((dh)i.get(paramcy.D())).b().a(paramcy, this);
  }

  public void b(boolean paramBoolean)
  {
    this.l = bw.a(this.l, 0, paramBoolean);
  }

  public String c()
  {
    return this.a;
  }

  public e c(int paramInt)
  {
    return e.a(paramInt);
  }

  public void c(boolean paramBoolean)
  {
    this.l = bw.a(this.l, 1, paramBoolean);
  }

  public void d()
  {
    this.a = null;
  }

  public boolean e()
  {
    return this.a != null;
  }

  public long f()
  {
    return this.b;
  }

  public void h()
  {
    this.l = bw.b(this.l, 0);
  }

  public boolean i()
  {
    return bw.a(this.l, 0);
  }

  public int j()
  {
    return this.c;
  }

  public void k()
  {
    this.l = bw.b(this.l, 1);
  }

  public boolean l()
  {
    return bw.a(this.l, 1);
  }

  public void m()
    throws cf
  {
    if (this.a == null)
      throw new cz("Required field 'identity' was not present! Struct: " + toString());
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("IdSnapshot(");
    localStringBuilder.append("identity:");
    if (this.a == null)
      localStringBuilder.append("null");
    while (true)
    {
      localStringBuilder.append(", ");
      localStringBuilder.append("ts:");
      localStringBuilder.append(this.b);
      localStringBuilder.append(", ");
      localStringBuilder.append("version:");
      localStringBuilder.append(this.c);
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(this.a);
    }
  }

  private static class a extends di<ar>
  {
    public void a(cy paramcy, ar paramar)
      throws cf
    {
      paramcy.j();
      ct localct = paramcy.l();
      if (localct.b == 0)
      {
        paramcy.k();
        if (!paramar.i())
          throw new cz("Required field 'ts' was not found in serialized data! Struct: " + toString());
      }
      else
      {
        switch (localct.c)
        {
        default:
          db.a(paramcy, localct.b);
        case 1:
        case 2:
        case 3:
        }
        while (true)
        {
          paramcy.m();
          break;
          if (localct.b == 11)
          {
            paramar.a = paramcy.z();
            paramar.a(true);
          }
          else
          {
            db.a(paramcy, localct.b);
            continue;
            if (localct.b == 10)
            {
              paramar.b = paramcy.x();
              paramar.b(true);
            }
            else
            {
              db.a(paramcy, localct.b);
              continue;
              if (localct.b == 8)
              {
                paramar.c = paramcy.w();
                paramar.c(true);
              }
              else
              {
                db.a(paramcy, localct.b);
              }
            }
          }
        }
      }
      if (!paramar.l())
        throw new cz("Required field 'version' was not found in serialized data! Struct: " + toString());
      paramar.m();
    }

    public void b(cy paramcy, ar paramar)
      throws cf
    {
      paramar.m();
      paramcy.a(ar.n());
      if (paramar.a != null)
      {
        paramcy.a(ar.o());
        paramcy.a(paramar.a);
        paramcy.c();
      }
      paramcy.a(ar.p());
      paramcy.a(paramar.b);
      paramcy.c();
      paramcy.a(ar.q());
      paramcy.a(paramar.c);
      paramcy.c();
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public ar.a a()
    {
      return new ar.a(null);
    }
  }

  private static class c extends dj<ar>
  {
    public void a(cy paramcy, ar paramar)
      throws cf
    {
      paramcy = (de)paramcy;
      paramcy.a(paramar.a);
      paramcy.a(paramar.b);
      paramcy.a(paramar.c);
    }

    public void b(cy paramcy, ar paramar)
      throws cf
    {
      paramcy = (de)paramcy;
      paramar.a = paramcy.z();
      paramar.a(true);
      paramar.b = paramcy.x();
      paramar.b(true);
      paramar.c = paramcy.w();
      paramar.c(true);
    }
  }

  private static class d
    implements dh
  {
    public ar.c a()
    {
      return new ar.c(null);
    }
  }

  public static enum e
    implements cg
  {
    private static final Map<String, e> d;
    private final short e;
    private final String f;

    static
    {
      d = new HashMap();
      Iterator localIterator = EnumSet.allOf(e.class).iterator();
      while (localIterator.hasNext())
      {
        e locale = (e)localIterator.next();
        d.put(locale.b(), locale);
      }
    }

    private e(short paramShort, String paramString)
    {
      this.e = paramShort;
      this.f = paramString;
    }

    public static e a(int paramInt)
    {
      switch (paramInt)
      {
      default:
        return null;
      case 1:
        return a;
      case 2:
        return b;
      case 3:
      }
      return c;
    }

    public static e a(String paramString)
    {
      return (e)d.get(paramString);
    }

    public static e b(int paramInt)
    {
      e locale = a(paramInt);
      if (locale == null)
        throw new IllegalArgumentException("Field " + paramInt + " doesn't exist!");
      return locale;
    }

    public short a()
    {
      return this.e;
    }

    public String b()
    {
      return this.f;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.ar
 * JD-Core Version:    0.6.2
 */