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

public class bg
  implements Serializable, Cloneable, bz<bg, e>
{
  public static final Map<e, cl> e;
  private static final dd f = new dd("UserInfo");
  private static final ct g = new ct("gender", (byte)8, (short)1);
  private static final ct h = new ct("age", (byte)8, (short)2);
  private static final ct i = new ct("id", (byte)11, (short)3);
  private static final ct j = new ct("source", (byte)11, (short)4);
  private static final Map<Class<? extends dg>, dh> k = new HashMap();
  private static final int l = 0;
  public ap a;
  public int b;
  public String c;
  public String d;
  private byte m = 0;
  private e[] n = { e.a, e.b, e.c, e.d };

  static
  {
    k.put(di.class, new b(null));
    k.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("gender", (byte)2, new ck((byte)16, ap.class)));
    localEnumMap.put(e.b, new cl("age", (byte)2, new cm((byte)8)));
    localEnumMap.put(e.c, new cl("id", (byte)2, new cm((byte)11)));
    localEnumMap.put(e.d, new cl("source", (byte)2, new cm((byte)11)));
    e = Collections.unmodifiableMap(localEnumMap);
    cl.a(bg.class, e);
  }

  public bg()
  {
  }

  public bg(bg parambg)
  {
    this.m = parambg.m;
    if (parambg.e())
      this.a = parambg.a;
    this.b = parambg.b;
    if (parambg.l())
      this.c = parambg.c;
    if (parambg.o())
      this.d = parambg.d;
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

  public bg a()
  {
    return new bg(this);
  }

  public bg a(int paramInt)
  {
    this.b = paramInt;
    b(true);
    return this;
  }

  public bg a(String paramString)
  {
    this.c = paramString;
    return this;
  }

  public bg a(ap paramap)
  {
    this.a = paramap;
    return this;
  }

  public void a(cy paramcy)
    throws cf
  {
    ((dh)k.get(paramcy.D())).b().b(paramcy, this);
  }

  public void a(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.a = null;
  }

  public bg b(String paramString)
  {
    this.d = paramString;
    return this;
  }

  public void b()
  {
    this.a = null;
    b(false);
    this.b = 0;
    this.c = null;
    this.d = null;
  }

  public void b(cy paramcy)
    throws cf
  {
    ((dh)k.get(paramcy.D())).b().a(paramcy, this);
  }

  public void b(boolean paramBoolean)
  {
    this.m = bw.a(this.m, 0, paramBoolean);
  }

  public ap c()
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
    this.a = null;
  }

  public void d(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.d = null;
  }

  public boolean e()
  {
    return this.a != null;
  }

  public int f()
  {
    return this.b;
  }

  public void h()
  {
    this.m = bw.b(this.m, 0);
  }

  public boolean i()
  {
    return bw.a(this.m, 0);
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

  public String m()
  {
    return this.d;
  }

  public void n()
  {
    this.d = null;
  }

  public boolean o()
  {
    return this.d != null;
  }

  public void p()
    throws cf
  {
  }

  public String toString()
  {
    int i3 = 0;
    StringBuilder localStringBuilder = new StringBuilder("UserInfo(");
    int i1 = 1;
    int i2;
    if (e())
    {
      localStringBuilder.append("gender:");
      if (this.a == null)
      {
        localStringBuilder.append("null");
        i1 = 0;
      }
    }
    else
    {
      i2 = i1;
      if (i())
      {
        if (i1 == 0)
          localStringBuilder.append(", ");
        localStringBuilder.append("age:");
        localStringBuilder.append(this.b);
        i2 = 0;
      }
      if (!l())
        break label227;
      if (i2 == 0)
        localStringBuilder.append(", ");
      localStringBuilder.append("id:");
      if (this.c != null)
        break label200;
      localStringBuilder.append("null");
      i1 = i3;
    }
    while (true)
    {
      label133: if (o())
      {
        if (i1 == 0)
          localStringBuilder.append(", ");
        localStringBuilder.append("source:");
        if (this.d != null)
          break label215;
        localStringBuilder.append("null");
      }
      while (true)
      {
        localStringBuilder.append(")");
        return localStringBuilder.toString();
        localStringBuilder.append(this.a);
        break;
        label200: localStringBuilder.append(this.c);
        i1 = i3;
        break label133;
        label215: localStringBuilder.append(this.d);
      }
      label227: i1 = i2;
    }
  }

  private static class a extends di<bg>
  {
    public void a(cy paramcy, bg parambg)
      throws cf
    {
      paramcy.j();
      ct localct = paramcy.l();
      if (localct.b == 0)
      {
        paramcy.k();
        parambg.p();
        return;
      }
      switch (localct.c)
      {
      default:
        db.a(paramcy, localct.b);
      case 1:
      case 2:
      case 3:
      case 4:
      }
      while (true)
      {
        paramcy.m();
        break;
        if (localct.b == 8)
        {
          parambg.a = ap.a(paramcy.w());
          parambg.a(true);
        }
        else
        {
          db.a(paramcy, localct.b);
          continue;
          if (localct.b == 8)
          {
            parambg.b = paramcy.w();
            parambg.b(true);
          }
          else
          {
            db.a(paramcy, localct.b);
            continue;
            if (localct.b == 11)
            {
              parambg.c = paramcy.z();
              parambg.c(true);
            }
            else
            {
              db.a(paramcy, localct.b);
              continue;
              if (localct.b == 11)
              {
                parambg.d = paramcy.z();
                parambg.d(true);
              }
              else
              {
                db.a(paramcy, localct.b);
              }
            }
          }
        }
      }
    }

    public void b(cy paramcy, bg parambg)
      throws cf
    {
      parambg.p();
      paramcy.a(bg.q());
      if ((parambg.a != null) && (parambg.e()))
      {
        paramcy.a(bg.r());
        paramcy.a(parambg.a.a());
        paramcy.c();
      }
      if (parambg.i())
      {
        paramcy.a(bg.s());
        paramcy.a(parambg.b);
        paramcy.c();
      }
      if ((parambg.c != null) && (parambg.l()))
      {
        paramcy.a(bg.t());
        paramcy.a(parambg.c);
        paramcy.c();
      }
      if ((parambg.d != null) && (parambg.o()))
      {
        paramcy.a(bg.u());
        paramcy.a(parambg.d);
        paramcy.c();
      }
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public bg.a a()
    {
      return new bg.a(null);
    }
  }

  private static class c extends dj<bg>
  {
    public void a(cy paramcy, bg parambg)
      throws cf
    {
      paramcy = (de)paramcy;
      BitSet localBitSet = new BitSet();
      if (parambg.e())
        localBitSet.set(0);
      if (parambg.i())
        localBitSet.set(1);
      if (parambg.l())
        localBitSet.set(2);
      if (parambg.o())
        localBitSet.set(3);
      paramcy.a(localBitSet, 4);
      if (parambg.e())
        paramcy.a(parambg.a.a());
      if (parambg.i())
        paramcy.a(parambg.b);
      if (parambg.l())
        paramcy.a(parambg.c);
      if (parambg.o())
        paramcy.a(parambg.d);
    }

    public void b(cy paramcy, bg parambg)
      throws cf
    {
      paramcy = (de)paramcy;
      BitSet localBitSet = paramcy.b(4);
      if (localBitSet.get(0))
      {
        parambg.a = ap.a(paramcy.w());
        parambg.a(true);
      }
      if (localBitSet.get(1))
      {
        parambg.b = paramcy.w();
        parambg.b(true);
      }
      if (localBitSet.get(2))
      {
        parambg.c = paramcy.z();
        parambg.c(true);
      }
      if (localBitSet.get(3))
      {
        parambg.d = paramcy.z();
        parambg.d(true);
      }
    }
  }

  private static class d
    implements dh
  {
    public bg.c a()
    {
      return new bg.c(null);
    }
  }

  public static enum e
    implements cg
  {
    private static final Map<String, e> e;
    private final short f;
    private final String g;

    static
    {
      e = new HashMap();
      Iterator localIterator = EnumSet.allOf(e.class).iterator();
      while (localIterator.hasNext())
      {
        e locale = (e)localIterator.next();
        e.put(locale.b(), locale);
      }
    }

    private e(short paramShort, String paramString)
    {
      this.f = paramShort;
      this.g = paramString;
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
        return c;
      case 4:
      }
      return d;
    }

    public static e a(String paramString)
    {
      return (e)e.get(paramString);
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
      return this.f;
    }

    public String b()
    {
      return this.g;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.bg
 * JD-Core Version:    0.6.2
 */