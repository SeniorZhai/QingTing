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

public class bb
  implements Serializable, Cloneable, bz<bb, e>
{
  public static final Map<e, cl> d;
  private static final dd e = new dd("Response");
  private static final ct f = new ct("resp_code", (byte)8, (short)1);
  private static final ct g = new ct("msg", (byte)11, (short)2);
  private static final ct h = new ct("imprint", (byte)12, (short)3);
  private static final Map<Class<? extends dg>, dh> i = new HashMap();
  private static final int j = 0;
  public int a;
  public String b;
  public at c;
  private byte k = 0;
  private e[] l = { e.b, e.c };

  static
  {
    i.put(di.class, new b(null));
    i.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("resp_code", (byte)1, new cm((byte)8)));
    localEnumMap.put(e.b, new cl("msg", (byte)2, new cm((byte)11)));
    localEnumMap.put(e.c, new cl("imprint", (byte)2, new cq((byte)12, at.class)));
    d = Collections.unmodifiableMap(localEnumMap);
    cl.a(bb.class, d);
  }

  public bb()
  {
  }

  public bb(int paramInt)
  {
    this();
    this.a = paramInt;
    a(true);
  }

  public bb(bb parambb)
  {
    this.k = parambb.k;
    this.a = parambb.a;
    if (parambb.i())
      this.b = parambb.b;
    if (parambb.l())
      this.c = new at(parambb.c);
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

  public bb a()
  {
    return new bb(this);
  }

  public bb a(int paramInt)
  {
    this.a = paramInt;
    a(true);
    return this;
  }

  public bb a(String paramString)
  {
    this.b = paramString;
    return this;
  }

  public bb a(at paramat)
  {
    this.c = paramat;
    return this;
  }

  public void a(cy paramcy)
    throws cf
  {
    ((dh)i.get(paramcy.D())).b().b(paramcy, this);
  }

  public void a(boolean paramBoolean)
  {
    this.k = bw.a(this.k, 0, paramBoolean);
  }

  public void b()
  {
    a(false);
    this.a = 0;
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
    if (!paramBoolean)
      this.b = null;
  }

  public int c()
  {
    return this.a;
  }

  public e c(int paramInt)
  {
    return e.a(paramInt);
  }

  public void c(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.c = null;
  }

  public void d()
  {
    this.k = bw.b(this.k, 0);
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

  public at j()
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
    if (this.c != null)
      this.c.n();
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Response(");
    localStringBuilder.append("resp_code:");
    localStringBuilder.append(this.a);
    if (i())
    {
      localStringBuilder.append(", ");
      localStringBuilder.append("msg:");
      if (this.b == null)
        localStringBuilder.append("null");
    }
    else if (l())
    {
      localStringBuilder.append(", ");
      localStringBuilder.append("imprint:");
      if (this.c != null)
        break label129;
      localStringBuilder.append("null");
    }
    while (true)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(this.b);
      break;
      label129: localStringBuilder.append(this.c);
    }
  }

  private static class a extends di<bb>
  {
    public void a(cy paramcy, bb parambb)
      throws cf
    {
      paramcy.j();
      ct localct = paramcy.l();
      if (localct.b == 0)
      {
        paramcy.k();
        if (!parambb.e())
          throw new cz("Required field 'resp_code' was not found in serialized data! Struct: " + toString());
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
            parambb.a = paramcy.w();
            parambb.a(true);
          }
          else
          {
            db.a(paramcy, localct.b);
            continue;
            if (localct.b == 11)
            {
              parambb.b = paramcy.z();
              parambb.b(true);
            }
            else
            {
              db.a(paramcy, localct.b);
              continue;
              if (localct.b == 12)
              {
                parambb.c = new at();
                parambb.c.a(paramcy);
                parambb.c(true);
              }
              else
              {
                db.a(paramcy, localct.b);
              }
            }
          }
        }
      }
      parambb.m();
    }

    public void b(cy paramcy, bb parambb)
      throws cf
    {
      parambb.m();
      paramcy.a(bb.n());
      paramcy.a(bb.o());
      paramcy.a(parambb.a);
      paramcy.c();
      if ((parambb.b != null) && (parambb.i()))
      {
        paramcy.a(bb.p());
        paramcy.a(parambb.b);
        paramcy.c();
      }
      if ((parambb.c != null) && (parambb.l()))
      {
        paramcy.a(bb.q());
        parambb.c.b(paramcy);
        paramcy.c();
      }
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public bb.a a()
    {
      return new bb.a(null);
    }
  }

  private static class c extends dj<bb>
  {
    public void a(cy paramcy, bb parambb)
      throws cf
    {
      paramcy = (de)paramcy;
      paramcy.a(parambb.a);
      BitSet localBitSet = new BitSet();
      if (parambb.i())
        localBitSet.set(0);
      if (parambb.l())
        localBitSet.set(1);
      paramcy.a(localBitSet, 2);
      if (parambb.i())
        paramcy.a(parambb.b);
      if (parambb.l())
        parambb.c.b(paramcy);
    }

    public void b(cy paramcy, bb parambb)
      throws cf
    {
      paramcy = (de)paramcy;
      parambb.a = paramcy.w();
      parambb.a(true);
      BitSet localBitSet = paramcy.b(2);
      if (localBitSet.get(0))
      {
        parambb.b = paramcy.z();
        parambb.b(true);
      }
      if (localBitSet.get(1))
      {
        parambb.c = new at();
        parambb.c.a(paramcy);
        parambb.c(true);
      }
    }
  }

  private static class d
    implements dh
  {
    public bb.c a()
    {
      return new bb.c(null);
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
 * Qualified Name:     u.aly.bb
 * JD-Core Version:    0.6.2
 */