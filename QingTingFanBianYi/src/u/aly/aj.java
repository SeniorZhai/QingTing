package u.aly;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class aj
  implements Serializable, Cloneable, bz<aj, e>
{
  public static final Map<e, cl> d;
  private static final dd e = new dd("ClientStats");
  private static final ct f = new ct("successful_requests", (byte)8, (short)1);
  private static final ct g = new ct("failed_requests", (byte)8, (short)2);
  private static final ct h = new ct("last_request_spent_ms", (byte)8, (short)3);
  private static final Map<Class<? extends dg>, dh> i = new HashMap();
  private static final int j = 0;
  private static final int k = 1;
  private static final int l = 2;
  public int a;
  public int b;
  public int c;
  private byte m = 0;
  private e[] n = { e.c };

  static
  {
    i.put(di.class, new b(null));
    i.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("successful_requests", (byte)1, new cm((byte)8)));
    localEnumMap.put(e.b, new cl("failed_requests", (byte)1, new cm((byte)8)));
    localEnumMap.put(e.c, new cl("last_request_spent_ms", (byte)2, new cm((byte)8)));
    d = Collections.unmodifiableMap(localEnumMap);
    cl.a(aj.class, d);
  }

  public aj()
  {
    this.a = 0;
    this.b = 0;
  }

  public aj(int paramInt1, int paramInt2)
  {
    this();
    this.a = paramInt1;
    a(true);
    this.b = paramInt2;
    b(true);
  }

  public aj(aj paramaj)
  {
    this.m = paramaj.m;
    this.a = paramaj.a;
    this.b = paramaj.b;
    this.c = paramaj.c;
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

  public aj a()
  {
    return new aj(this);
  }

  public aj a(int paramInt)
  {
    this.a = paramInt;
    a(true);
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

  public void b()
  {
    this.a = 0;
    this.b = 0;
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
    this.m = bw.a(this.m, 1, paramBoolean);
  }

  public int c()
  {
    return this.a;
  }

  public aj c(int paramInt)
  {
    this.b = paramInt;
    b(true);
    return this;
  }

  public void c(boolean paramBoolean)
  {
    this.m = bw.a(this.m, 2, paramBoolean);
  }

  public aj d(int paramInt)
  {
    this.c = paramInt;
    c(true);
    return this;
  }

  public void d()
  {
    this.m = bw.b(this.m, 0);
  }

  public e e(int paramInt)
  {
    return e.a(paramInt);
  }

  public boolean e()
  {
    return bw.a(this.m, 0);
  }

  public int f()
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

  public int j()
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
    StringBuilder localStringBuilder = new StringBuilder("ClientStats(");
    localStringBuilder.append("successful_requests:");
    localStringBuilder.append(this.a);
    localStringBuilder.append(", ");
    localStringBuilder.append("failed_requests:");
    localStringBuilder.append(this.b);
    if (l())
    {
      localStringBuilder.append(", ");
      localStringBuilder.append("last_request_spent_ms:");
      localStringBuilder.append(this.c);
    }
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }

  private static class a extends di<aj>
  {
    public void a(cy paramcy, aj paramaj)
      throws cf
    {
      paramcy.j();
      ct localct = paramcy.l();
      if (localct.b == 0)
      {
        paramcy.k();
        if (!paramaj.e())
          throw new cz("Required field 'successful_requests' was not found in serialized data! Struct: " + toString());
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
          if (localct.b == 8)
          {
            paramaj.a = paramcy.w();
            paramaj.a(true);
          }
          else
          {
            db.a(paramcy, localct.b);
            continue;
            if (localct.b == 8)
            {
              paramaj.b = paramcy.w();
              paramaj.b(true);
            }
            else
            {
              db.a(paramcy, localct.b);
              continue;
              if (localct.b == 8)
              {
                paramaj.c = paramcy.w();
                paramaj.c(true);
              }
              else
              {
                db.a(paramcy, localct.b);
              }
            }
          }
        }
      }
      if (!paramaj.i())
        throw new cz("Required field 'failed_requests' was not found in serialized data! Struct: " + toString());
      paramaj.m();
    }

    public void b(cy paramcy, aj paramaj)
      throws cf
    {
      paramaj.m();
      paramcy.a(aj.n());
      paramcy.a(aj.o());
      paramcy.a(paramaj.a);
      paramcy.c();
      paramcy.a(aj.p());
      paramcy.a(paramaj.b);
      paramcy.c();
      if (paramaj.l())
      {
        paramcy.a(aj.q());
        paramcy.a(paramaj.c);
        paramcy.c();
      }
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public aj.a a()
    {
      return new aj.a(null);
    }
  }

  private static class c extends dj<aj>
  {
    public void a(cy paramcy, aj paramaj)
      throws cf
    {
      paramcy = (de)paramcy;
      paramcy.a(paramaj.a);
      paramcy.a(paramaj.b);
      BitSet localBitSet = new BitSet();
      if (paramaj.l())
        localBitSet.set(0);
      paramcy.a(localBitSet, 1);
      if (paramaj.l())
        paramcy.a(paramaj.c);
    }

    public void b(cy paramcy, aj paramaj)
      throws cf
    {
      paramcy = (de)paramcy;
      paramaj.a = paramcy.w();
      paramaj.a(true);
      paramaj.b = paramcy.w();
      paramaj.b(true);
      if (paramcy.b(1).get(0))
      {
        paramaj.c = paramcy.w();
        paramaj.c(true);
      }
    }
  }

  private static class d
    implements dh
  {
    public aj.c a()
    {
      return new aj.c(null);
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
 * Qualified Name:     u.aly.aj
 * JD-Core Version:    0.6.2
 */