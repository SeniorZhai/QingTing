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

public class au
  implements Serializable, Cloneable, bz<au, e>
{
  public static final Map<e, cl> d;
  private static final dd e = new dd("ImprintValue");
  private static final ct f = new ct("value", (byte)11, (short)1);
  private static final ct g = new ct("ts", (byte)10, (short)2);
  private static final ct h = new ct("guid", (byte)11, (short)3);
  private static final Map<Class<? extends dg>, dh> i = new HashMap();
  private static final int j = 0;
  public String a;
  public long b;
  public String c;
  private byte k = 0;
  private e[] l = { e.a };

  static
  {
    i.put(di.class, new b(null));
    i.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("value", (byte)2, new cm((byte)11)));
    localEnumMap.put(e.b, new cl("ts", (byte)1, new cm((byte)10)));
    localEnumMap.put(e.c, new cl("guid", (byte)1, new cm((byte)11)));
    d = Collections.unmodifiableMap(localEnumMap);
    cl.a(au.class, d);
  }

  public au()
  {
  }

  public au(long paramLong, String paramString)
  {
    this();
    this.b = paramLong;
    b(true);
    this.c = paramString;
  }

  public au(au paramau)
  {
    this.k = paramau.k;
    if (paramau.e())
      this.a = paramau.a;
    this.b = paramau.b;
    if (paramau.l())
      this.c = paramau.c;
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

  public au a()
  {
    return new au(this);
  }

  public au a(long paramLong)
  {
    this.b = paramLong;
    b(true);
    return this;
  }

  public au a(String paramString)
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

  public au b(String paramString)
  {
    this.c = paramString;
    return this;
  }

  public void b()
  {
    this.a = null;
    b(false);
    this.b = 0L;
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

  public String c()
  {
    return this.a;
  }

  public void c(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.c = null;
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
    this.k = bw.b(this.k, 0);
  }

  public boolean i()
  {
    return bw.a(this.k, 0);
  }

  public String j()
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
    if (this.c == null)
      throw new cz("Required field 'guid' was not present! Struct: " + toString());
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("ImprintValue(");
    int m = 1;
    if (e())
    {
      localStringBuilder.append("value:");
      if (this.a == null)
      {
        localStringBuilder.append("null");
        m = 0;
      }
    }
    else
    {
      if (m == 0)
        localStringBuilder.append(", ");
      localStringBuilder.append("ts:");
      localStringBuilder.append(this.b);
      localStringBuilder.append(", ");
      localStringBuilder.append("guid:");
      if (this.c != null)
        break label129;
      localStringBuilder.append("null");
    }
    while (true)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(this.a);
      break;
      label129: localStringBuilder.append(this.c);
    }
  }

  private static class a extends di<au>
  {
    public void a(cy paramcy, au paramau)
      throws cf
    {
      paramcy.j();
      ct localct = paramcy.l();
      if (localct.b == 0)
      {
        paramcy.k();
        if (!paramau.i())
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
            paramau.a = paramcy.z();
            paramau.a(true);
          }
          else
          {
            db.a(paramcy, localct.b);
            continue;
            if (localct.b == 10)
            {
              paramau.b = paramcy.x();
              paramau.b(true);
            }
            else
            {
              db.a(paramcy, localct.b);
              continue;
              if (localct.b == 11)
              {
                paramau.c = paramcy.z();
                paramau.c(true);
              }
              else
              {
                db.a(paramcy, localct.b);
              }
            }
          }
        }
      }
      paramau.m();
    }

    public void b(cy paramcy, au paramau)
      throws cf
    {
      paramau.m();
      paramcy.a(au.n());
      if ((paramau.a != null) && (paramau.e()))
      {
        paramcy.a(au.o());
        paramcy.a(paramau.a);
        paramcy.c();
      }
      paramcy.a(au.p());
      paramcy.a(paramau.b);
      paramcy.c();
      if (paramau.c != null)
      {
        paramcy.a(au.q());
        paramcy.a(paramau.c);
        paramcy.c();
      }
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public au.a a()
    {
      return new au.a(null);
    }
  }

  private static class c extends dj<au>
  {
    public void a(cy paramcy, au paramau)
      throws cf
    {
      paramcy = (de)paramcy;
      paramcy.a(paramau.b);
      paramcy.a(paramau.c);
      BitSet localBitSet = new BitSet();
      if (paramau.e())
        localBitSet.set(0);
      paramcy.a(localBitSet, 1);
      if (paramau.e())
        paramcy.a(paramau.a);
    }

    public void b(cy paramcy, au paramau)
      throws cf
    {
      paramcy = (de)paramcy;
      paramau.b = paramcy.x();
      paramau.b(true);
      paramau.c = paramcy.z();
      paramau.c(true);
      if (paramcy.b(1).get(0))
      {
        paramau.a = paramcy.z();
        paramau.a(true);
      }
    }
  }

  private static class d
    implements dh
  {
    public au.c a()
    {
      return new au.c(null);
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
 * Qualified Name:     u.aly.au
 * JD-Core Version:    0.6.2
 */