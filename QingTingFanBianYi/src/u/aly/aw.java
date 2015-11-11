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

public class aw
  implements Serializable, Cloneable, bz<aw, e>
{
  public static final Map<e, cl> d;
  private static final dd e = new dd("Location");
  private static final ct f = new ct("lat", (byte)4, (short)1);
  private static final ct g = new ct("lng", (byte)4, (short)2);
  private static final ct h = new ct("ts", (byte)10, (short)3);
  private static final Map<Class<? extends dg>, dh> i = new HashMap();
  private static final int j = 0;
  private static final int k = 1;
  private static final int l = 2;
  public double a;
  public double b;
  public long c;
  private byte m = 0;

  static
  {
    i.put(di.class, new b(null));
    i.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("lat", (byte)1, new cm((byte)4)));
    localEnumMap.put(e.b, new cl("lng", (byte)1, new cm((byte)4)));
    localEnumMap.put(e.c, new cl("ts", (byte)1, new cm((byte)10)));
    d = Collections.unmodifiableMap(localEnumMap);
    cl.a(aw.class, d);
  }

  public aw()
  {
  }

  public aw(double paramDouble1, double paramDouble2, long paramLong)
  {
    this();
    this.a = paramDouble1;
    a(true);
    this.b = paramDouble2;
    b(true);
    this.c = paramLong;
    c(true);
  }

  public aw(aw paramaw)
  {
    this.m = paramaw.m;
    this.a = paramaw.a;
    this.b = paramaw.b;
    this.c = paramaw.c;
  }

  private void a(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    try
    {
      this.m = 0;
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

  public e a(int paramInt)
  {
    return e.a(paramInt);
  }

  public aw a()
  {
    return new aw(this);
  }

  public aw a(double paramDouble)
  {
    this.a = paramDouble;
    a(true);
    return this;
  }

  public aw a(long paramLong)
  {
    this.c = paramLong;
    c(true);
    return this;
  }

  public void a(cy paramcy)
    throws cf
  {
    ((dh)i.get(paramcy.D())).b().b(paramcy, this);
  }

  public void a(boolean paramBoolean)
  {
    this.m = bw.a(this.m, 0, paramBoolean);
  }

  public aw b(double paramDouble)
  {
    this.b = paramDouble;
    b(true);
    return this;
  }

  public void b()
  {
    a(false);
    this.a = 0.0D;
    b(false);
    this.b = 0.0D;
    c(false);
    this.c = 0L;
  }

  public void b(cy paramcy)
    throws cf
  {
    ((dh)i.get(paramcy.D())).b().a(paramcy, this);
  }

  public void b(boolean paramBoolean)
  {
    this.m = bw.a(this.m, 1, paramBoolean);
  }

  public double c()
  {
    return this.a;
  }

  public void c(boolean paramBoolean)
  {
    this.m = bw.a(this.m, 2, paramBoolean);
  }

  public void d()
  {
    this.m = bw.b(this.m, 0);
  }

  public boolean e()
  {
    return bw.a(this.m, 0);
  }

  public double f()
  {
    return this.b;
  }

  public void h()
  {
    this.m = bw.b(this.m, 1);
  }

  public boolean i()
  {
    return bw.a(this.m, 1);
  }

  public long j()
  {
    return this.c;
  }

  public void k()
  {
    this.m = bw.b(this.m, 2);
  }

  public boolean l()
  {
    return bw.a(this.m, 2);
  }

  public void m()
    throws cf
  {
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Location(");
    localStringBuilder.append("lat:");
    localStringBuilder.append(this.a);
    localStringBuilder.append(", ");
    localStringBuilder.append("lng:");
    localStringBuilder.append(this.b);
    localStringBuilder.append(", ");
    localStringBuilder.append("ts:");
    localStringBuilder.append(this.c);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }

  private static class a extends di<aw>
  {
    public void a(cy paramcy, aw paramaw)
      throws cf
    {
      paramcy.j();
      ct localct = paramcy.l();
      if (localct.b == 0)
      {
        paramcy.k();
        if (!paramaw.e())
          throw new cz("Required field 'lat' was not found in serialized data! Struct: " + toString());
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
          if (localct.b == 4)
          {
            paramaw.a = paramcy.y();
            paramaw.a(true);
          }
          else
          {
            db.a(paramcy, localct.b);
            continue;
            if (localct.b == 4)
            {
              paramaw.b = paramcy.y();
              paramaw.b(true);
            }
            else
            {
              db.a(paramcy, localct.b);
              continue;
              if (localct.b == 10)
              {
                paramaw.c = paramcy.x();
                paramaw.c(true);
              }
              else
              {
                db.a(paramcy, localct.b);
              }
            }
          }
        }
      }
      if (!paramaw.i())
        throw new cz("Required field 'lng' was not found in serialized data! Struct: " + toString());
      if (!paramaw.l())
        throw new cz("Required field 'ts' was not found in serialized data! Struct: " + toString());
      paramaw.m();
    }

    public void b(cy paramcy, aw paramaw)
      throws cf
    {
      paramaw.m();
      paramcy.a(aw.n());
      paramcy.a(aw.o());
      paramcy.a(paramaw.a);
      paramcy.c();
      paramcy.a(aw.p());
      paramcy.a(paramaw.b);
      paramcy.c();
      paramcy.a(aw.q());
      paramcy.a(paramaw.c);
      paramcy.c();
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public aw.a a()
    {
      return new aw.a(null);
    }
  }

  private static class c extends dj<aw>
  {
    public void a(cy paramcy, aw paramaw)
      throws cf
    {
      paramcy = (de)paramcy;
      paramcy.a(paramaw.a);
      paramcy.a(paramaw.b);
      paramcy.a(paramaw.c);
    }

    public void b(cy paramcy, aw paramaw)
      throws cf
    {
      paramcy = (de)paramcy;
      paramaw.a = paramcy.y();
      paramaw.a(true);
      paramaw.b = paramcy.y();
      paramaw.b(true);
      paramaw.c = paramcy.x();
      paramaw.c(true);
    }
  }

  private static class d
    implements dh
  {
    public aw.c a()
    {
      return new aw.c(null);
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
 * Qualified Name:     u.aly.aw
 * JD-Core Version:    0.6.2
 */