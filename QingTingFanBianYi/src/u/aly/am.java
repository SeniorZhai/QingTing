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

public class am
  implements Serializable, Cloneable, bz<am, e>
{
  public static final Map<e, cl> d;
  private static final dd e = new dd("Error");
  private static final ct f = new ct("ts", (byte)10, (short)1);
  private static final ct g = new ct("context", (byte)11, (short)2);
  private static final ct h = new ct("source", (byte)8, (short)3);
  private static final Map<Class<? extends dg>, dh> i = new HashMap();
  private static final int j = 0;
  public long a;
  public String b;
  public an c;
  private byte k = 0;
  private e[] l = { e.c };

  static
  {
    i.put(di.class, new b(null));
    i.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("ts", (byte)1, new cm((byte)10)));
    localEnumMap.put(e.b, new cl("context", (byte)1, new cm((byte)11)));
    localEnumMap.put(e.c, new cl("source", (byte)2, new ck((byte)16, an.class)));
    d = Collections.unmodifiableMap(localEnumMap);
    cl.a(am.class, d);
  }

  public am()
  {
  }

  public am(long paramLong, String paramString)
  {
    this();
    this.a = paramLong;
    b(true);
    this.b = paramString;
  }

  public am(am paramam)
  {
    this.k = paramam.k;
    this.a = paramam.a;
    if (paramam.i())
      this.b = paramam.b;
    if (paramam.l())
      this.c = paramam.c;
  }

  private void a(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    try
    {
      this.k = 0;
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

  public am a()
  {
    return new am(this);
  }

  public am a(long paramLong)
  {
    this.a = paramLong;
    b(true);
    return this;
  }

  public am a(String paramString)
  {
    this.b = paramString;
    return this;
  }

  public am a(an paraman)
  {
    this.c = paraman;
    return this;
  }

  public void a(cy paramcy)
    throws cf
  {
    ((dh)i.get(paramcy.D())).b().b(paramcy, this);
  }

  public void b()
  {
    b(false);
    this.a = 0L;
    this.b = null;
    this.c = null;
  }

  public void b(cy paramcy)
    throws cf
  {
    ((dh)i.get(paramcy.D())).b().a(paramcy, this);
  }

  public void b(boolean paramBoolean)
  {
    this.k = bw.a(this.k, 0, paramBoolean);
  }

  public long c()
  {
    return this.a;
  }

  public void c(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.b = null;
  }

  public void d()
  {
    this.k = bw.b(this.k, 0);
  }

  public void d(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.c = null;
  }

  public boolean e()
  {
    return bw.a(this.k, 0);
  }

  public String f()
  {
    return this.b;
  }

  public void h()
  {
    this.b = null;
  }

  public boolean i()
  {
    return this.b != null;
  }

  public an j()
  {
    return this.c;
  }

  public void k()
  {
    this.c = null;
  }

  public boolean l()
  {
    return this.c != null;
  }

  public void m()
    throws cf
  {
    if (this.b == null)
      throw new cz("Required field 'context' was not present! Struct: " + toString());
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Error(");
    localStringBuilder.append("ts:");
    localStringBuilder.append(this.a);
    localStringBuilder.append(", ");
    localStringBuilder.append("context:");
    if (this.b == null)
    {
      localStringBuilder.append("null");
      if (l())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("source:");
        if (this.c != null)
          break label122;
        localStringBuilder.append("null");
      }
    }
    while (true)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(this.b);
      break;
      label122: localStringBuilder.append(this.c);
    }
  }

  private static class a extends di<am>
  {
    public void a(cy paramcy, am paramam)
      throws cf
    {
      paramcy.j();
      ct localct = paramcy.l();
      if (localct.b == 0)
      {
        paramcy.k();
        if (!paramam.e())
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
          if (localct.b == 10)
          {
            paramam.a = paramcy.x();
            paramam.b(true);
          }
          else
          {
            db.a(paramcy, localct.b);
            continue;
            if (localct.b == 11)
            {
              paramam.b = paramcy.z();
              paramam.c(true);
            }
            else
            {
              db.a(paramcy, localct.b);
              continue;
              if (localct.b == 8)
              {
                paramam.c = an.a(paramcy.w());
                paramam.d(true);
              }
              else
              {
                db.a(paramcy, localct.b);
              }
            }
          }
        }
      }
      paramam.m();
    }

    public void b(cy paramcy, am paramam)
      throws cf
    {
      paramam.m();
      paramcy.a(am.n());
      paramcy.a(am.o());
      paramcy.a(paramam.a);
      paramcy.c();
      if (paramam.b != null)
      {
        paramcy.a(am.p());
        paramcy.a(paramam.b);
        paramcy.c();
      }
      if ((paramam.c != null) && (paramam.l()))
      {
        paramcy.a(am.q());
        paramcy.a(paramam.c.a());
        paramcy.c();
      }
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public am.a a()
    {
      return new am.a(null);
    }
  }

  private static class c extends dj<am>
  {
    public void a(cy paramcy, am paramam)
      throws cf
    {
      paramcy = (de)paramcy;
      paramcy.a(paramam.a);
      paramcy.a(paramam.b);
      BitSet localBitSet = new BitSet();
      if (paramam.l())
        localBitSet.set(0);
      paramcy.a(localBitSet, 1);
      if (paramam.l())
        paramcy.a(paramam.c.a());
    }

    public void b(cy paramcy, am paramam)
      throws cf
    {
      paramcy = (de)paramcy;
      paramam.a = paramcy.x();
      paramam.b(true);
      paramam.b = paramcy.z();
      paramam.c(true);
      if (paramcy.b(1).get(0))
      {
        paramam.c = an.a(paramcy.w());
        paramam.d(true);
      }
    }
  }

  private static class d
    implements dh
  {
    public am.c a()
    {
      return new am.c(null);
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
 * Qualified Name:     u.aly.am
 * JD-Core Version:    0.6.2
 */