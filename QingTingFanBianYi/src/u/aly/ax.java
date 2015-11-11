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

public class ax
  implements Serializable, Cloneable, bz<ax, e>
{
  private static final int A = 1;
  private static final int B = 2;
  private static final int C = 3;
  public static final Map<e, cl> l;
  private static final dd m = new dd("MiscInfo");
  private static final ct n = new ct("time_zone", (byte)8, (short)1);
  private static final ct o = new ct("language", (byte)11, (short)2);
  private static final ct p = new ct("country", (byte)11, (short)3);
  private static final ct q = new ct("latitude", (byte)4, (short)4);
  private static final ct r = new ct("longitude", (byte)4, (short)5);
  private static final ct s = new ct("carrier", (byte)11, (short)6);
  private static final ct t = new ct("latency", (byte)8, (short)7);
  private static final ct u = new ct("display_name", (byte)11, (short)8);
  private static final ct v = new ct("access_type", (byte)8, (short)9);
  private static final ct w = new ct("access_subtype", (byte)11, (short)10);
  private static final ct x = new ct("user_info", (byte)12, (short)11);
  private static final Map<Class<? extends dg>, dh> y = new HashMap();
  private static final int z = 0;
  private byte D = 0;
  private e[] E = { e.a, e.b, e.c, e.d, e.e, e.f, e.g, e.h, e.i, e.j, e.k };
  public int a;
  public String b;
  public String c;
  public double d;
  public double e;
  public String f;
  public int g;
  public String h;
  public ag i;
  public String j;
  public bg k;

  static
  {
    y.put(di.class, new b(null));
    y.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("time_zone", (byte)2, new cm((byte)8)));
    localEnumMap.put(e.b, new cl("language", (byte)2, new cm((byte)11)));
    localEnumMap.put(e.c, new cl("country", (byte)2, new cm((byte)11)));
    localEnumMap.put(e.d, new cl("latitude", (byte)2, new cm((byte)4)));
    localEnumMap.put(e.e, new cl("longitude", (byte)2, new cm((byte)4)));
    localEnumMap.put(e.f, new cl("carrier", (byte)2, new cm((byte)11)));
    localEnumMap.put(e.g, new cl("latency", (byte)2, new cm((byte)8)));
    localEnumMap.put(e.h, new cl("display_name", (byte)2, new cm((byte)11)));
    localEnumMap.put(e.i, new cl("access_type", (byte)2, new ck((byte)16, ag.class)));
    localEnumMap.put(e.j, new cl("access_subtype", (byte)2, new cm((byte)11)));
    localEnumMap.put(e.k, new cl("user_info", (byte)2, new cq((byte)12, bg.class)));
    l = Collections.unmodifiableMap(localEnumMap);
    cl.a(ax.class, l);
  }

  public ax()
  {
  }

  public ax(ax paramax)
  {
    this.D = paramax.D;
    this.a = paramax.a;
    if (paramax.i())
      this.b = paramax.b;
    if (paramax.l())
      this.c = paramax.c;
    this.d = paramax.d;
    this.e = paramax.e;
    if (paramax.u())
      this.f = paramax.f;
    this.g = paramax.g;
    if (paramax.A())
      this.h = paramax.h;
    if (paramax.D())
      this.i = paramax.i;
    if (paramax.G())
      this.j = paramax.j;
    if (paramax.J())
      this.k = new bg(paramax.k);
  }

  private void a(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    try
    {
      this.D = 0;
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

  public boolean A()
  {
    return this.h != null;
  }

  public ag B()
  {
    return this.i;
  }

  public void C()
  {
    this.i = null;
  }

  public boolean D()
  {
    return this.i != null;
  }

  public String E()
  {
    return this.j;
  }

  public void F()
  {
    this.j = null;
  }

  public boolean G()
  {
    return this.j != null;
  }

  public bg H()
  {
    return this.k;
  }

  public void I()
  {
    this.k = null;
  }

  public boolean J()
  {
    return this.k != null;
  }

  public void K()
    throws cf
  {
    if (this.k != null)
      this.k.p();
  }

  public ax a()
  {
    return new ax(this);
  }

  public ax a(double paramDouble)
  {
    this.d = paramDouble;
    d(true);
    return this;
  }

  public ax a(int paramInt)
  {
    this.a = paramInt;
    a(true);
    return this;
  }

  public ax a(String paramString)
  {
    this.b = paramString;
    return this;
  }

  public ax a(ag paramag)
  {
    this.i = paramag;
    return this;
  }

  public ax a(bg parambg)
  {
    this.k = parambg;
    return this;
  }

  public void a(cy paramcy)
    throws cf
  {
    ((dh)y.get(paramcy.D())).b().b(paramcy, this);
  }

  public void a(boolean paramBoolean)
  {
    this.D = bw.a(this.D, 0, paramBoolean);
  }

  public ax b(double paramDouble)
  {
    this.e = paramDouble;
    e(true);
    return this;
  }

  public ax b(String paramString)
  {
    this.c = paramString;
    return this;
  }

  public void b()
  {
    a(false);
    this.a = 0;
    this.b = null;
    this.c = null;
    d(false);
    this.d = 0.0D;
    e(false);
    this.e = 0.0D;
    this.f = null;
    g(false);
    this.g = 0;
    this.h = null;
    this.i = null;
    this.j = null;
    this.k = null;
  }

  public void b(cy paramcy)
    throws cf
  {
    ((dh)y.get(paramcy.D())).b().a(paramcy, this);
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

  public ax c(int paramInt)
  {
    this.g = paramInt;
    g(true);
    return this;
  }

  public ax c(String paramString)
  {
    this.f = paramString;
    return this;
  }

  public void c(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.c = null;
  }

  public e d(int paramInt)
  {
    return e.a(paramInt);
  }

  public ax d(String paramString)
  {
    this.h = paramString;
    return this;
  }

  public void d()
  {
    this.D = bw.b(this.D, 0);
  }

  public void d(boolean paramBoolean)
  {
    this.D = bw.a(this.D, 1, paramBoolean);
  }

  public ax e(String paramString)
  {
    this.j = paramString;
    return this;
  }

  public void e(boolean paramBoolean)
  {
    this.D = bw.a(this.D, 2, paramBoolean);
  }

  public boolean e()
  {
    return bw.a(this.D, 0);
  }

  public String f()
  {
    return this.b;
  }

  public void f(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.f = null;
  }

  public void g(boolean paramBoolean)
  {
    this.D = bw.a(this.D, 3, paramBoolean);
  }

  public void h()
  {
    this.b = null;
  }

  public void h(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.h = null;
  }

  public void i(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.i = null;
  }

  public boolean i()
  {
    return this.b != null;
  }

  public String j()
  {
    return this.c;
  }

  public void j(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.j = null;
  }

  public void k()
  {
    this.c = null;
  }

  public void k(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.k = null;
  }

  public boolean l()
  {
    return this.c != null;
  }

  public double m()
  {
    return this.d;
  }

  public void n()
  {
    this.D = bw.b(this.D, 1);
  }

  public boolean o()
  {
    return bw.a(this.D, 1);
  }

  public double p()
  {
    return this.e;
  }

  public void q()
  {
    this.D = bw.b(this.D, 2);
  }

  public boolean r()
  {
    return bw.a(this.D, 2);
  }

  public String s()
  {
    return this.f;
  }

  public void t()
  {
    this.f = null;
  }

  public String toString()
  {
    int i3 = 0;
    StringBuilder localStringBuilder = new StringBuilder("MiscInfo(");
    int i2 = 1;
    if (e())
    {
      localStringBuilder.append("time_zone:");
      localStringBuilder.append(this.a);
      i2 = 0;
    }
    int i1 = i2;
    if (i())
    {
      if (i2 == 0)
        localStringBuilder.append(", ");
      localStringBuilder.append("language:");
      if (this.b == null)
      {
        localStringBuilder.append("null");
        i1 = 0;
      }
    }
    else
    {
      i2 = i1;
      if (l())
      {
        if (i1 == 0)
          localStringBuilder.append(", ");
        localStringBuilder.append("country:");
        if (this.c != null)
          break label504;
        localStringBuilder.append("null");
        label132: i2 = 0;
      }
      i1 = i2;
      if (o())
      {
        if (i2 == 0)
          localStringBuilder.append(", ");
        localStringBuilder.append("latitude:");
        localStringBuilder.append(this.d);
        i1 = 0;
      }
      i2 = i1;
      if (r())
      {
        if (i1 == 0)
          localStringBuilder.append(", ");
        localStringBuilder.append("longitude:");
        localStringBuilder.append(this.e);
        i2 = 0;
      }
      i1 = i2;
      if (u())
      {
        if (i2 == 0)
          localStringBuilder.append(", ");
        localStringBuilder.append("carrier:");
        if (this.f != null)
          break label516;
        localStringBuilder.append("null");
        label258: i1 = 0;
      }
      i2 = i1;
      if (x())
      {
        if (i1 == 0)
          localStringBuilder.append(", ");
        localStringBuilder.append("latency:");
        localStringBuilder.append(this.g);
        i2 = 0;
      }
      i1 = i2;
      if (A())
      {
        if (i2 == 0)
          localStringBuilder.append(", ");
        localStringBuilder.append("display_name:");
        if (this.h != null)
          break label528;
        localStringBuilder.append("null");
        label344: i1 = 0;
      }
      i2 = i1;
      if (D())
      {
        if (i1 == 0)
          localStringBuilder.append(", ");
        localStringBuilder.append("access_type:");
        if (this.i != null)
          break label540;
        localStringBuilder.append("null");
        label390: i2 = 0;
      }
      if (!G())
        break label579;
      if (i2 == 0)
        localStringBuilder.append(", ");
      localStringBuilder.append("access_subtype:");
      if (this.j != null)
        break label552;
      localStringBuilder.append("null");
      i1 = i3;
    }
    while (true)
    {
      label437: if (J())
      {
        if (i1 == 0)
          localStringBuilder.append(", ");
        localStringBuilder.append("user_info:");
        if (this.k != null)
          break label567;
        localStringBuilder.append("null");
      }
      while (true)
      {
        localStringBuilder.append(")");
        return localStringBuilder.toString();
        localStringBuilder.append(this.b);
        break;
        label504: localStringBuilder.append(this.c);
        break label132;
        label516: localStringBuilder.append(this.f);
        break label258;
        label528: localStringBuilder.append(this.h);
        break label344;
        label540: localStringBuilder.append(this.i);
        break label390;
        label552: localStringBuilder.append(this.j);
        i1 = i3;
        break label437;
        label567: localStringBuilder.append(this.k);
      }
      label579: i1 = i2;
    }
  }

  public boolean u()
  {
    return this.f != null;
  }

  public int v()
  {
    return this.g;
  }

  public void w()
  {
    this.D = bw.b(this.D, 3);
  }

  public boolean x()
  {
    return bw.a(this.D, 3);
  }

  public String y()
  {
    return this.h;
  }

  public void z()
  {
    this.h = null;
  }

  private static class a extends di<ax>
  {
    public void a(cy paramcy, ax paramax)
      throws cf
    {
      paramcy.j();
      ct localct = paramcy.l();
      if (localct.b == 0)
      {
        paramcy.k();
        paramax.K();
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
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      }
      while (true)
      {
        paramcy.m();
        break;
        if (localct.b == 8)
        {
          paramax.a = paramcy.w();
          paramax.a(true);
        }
        else
        {
          db.a(paramcy, localct.b);
          continue;
          if (localct.b == 11)
          {
            paramax.b = paramcy.z();
            paramax.b(true);
          }
          else
          {
            db.a(paramcy, localct.b);
            continue;
            if (localct.b == 11)
            {
              paramax.c = paramcy.z();
              paramax.c(true);
            }
            else
            {
              db.a(paramcy, localct.b);
              continue;
              if (localct.b == 4)
              {
                paramax.d = paramcy.y();
                paramax.d(true);
              }
              else
              {
                db.a(paramcy, localct.b);
                continue;
                if (localct.b == 4)
                {
                  paramax.e = paramcy.y();
                  paramax.e(true);
                }
                else
                {
                  db.a(paramcy, localct.b);
                  continue;
                  if (localct.b == 11)
                  {
                    paramax.f = paramcy.z();
                    paramax.f(true);
                  }
                  else
                  {
                    db.a(paramcy, localct.b);
                    continue;
                    if (localct.b == 8)
                    {
                      paramax.g = paramcy.w();
                      paramax.g(true);
                    }
                    else
                    {
                      db.a(paramcy, localct.b);
                      continue;
                      if (localct.b == 11)
                      {
                        paramax.h = paramcy.z();
                        paramax.h(true);
                      }
                      else
                      {
                        db.a(paramcy, localct.b);
                        continue;
                        if (localct.b == 8)
                        {
                          paramax.i = ag.a(paramcy.w());
                          paramax.i(true);
                        }
                        else
                        {
                          db.a(paramcy, localct.b);
                          continue;
                          if (localct.b == 11)
                          {
                            paramax.j = paramcy.z();
                            paramax.j(true);
                          }
                          else
                          {
                            db.a(paramcy, localct.b);
                            continue;
                            if (localct.b == 12)
                            {
                              paramax.k = new bg();
                              paramax.k.a(paramcy);
                              paramax.k(true);
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
                }
              }
            }
          }
        }
      }
    }

    public void b(cy paramcy, ax paramax)
      throws cf
    {
      paramax.K();
      paramcy.a(ax.L());
      if (paramax.e())
      {
        paramcy.a(ax.M());
        paramcy.a(paramax.a);
        paramcy.c();
      }
      if ((paramax.b != null) && (paramax.i()))
      {
        paramcy.a(ax.N());
        paramcy.a(paramax.b);
        paramcy.c();
      }
      if ((paramax.c != null) && (paramax.l()))
      {
        paramcy.a(ax.O());
        paramcy.a(paramax.c);
        paramcy.c();
      }
      if (paramax.o())
      {
        paramcy.a(ax.P());
        paramcy.a(paramax.d);
        paramcy.c();
      }
      if (paramax.r())
      {
        paramcy.a(ax.Q());
        paramcy.a(paramax.e);
        paramcy.c();
      }
      if ((paramax.f != null) && (paramax.u()))
      {
        paramcy.a(ax.R());
        paramcy.a(paramax.f);
        paramcy.c();
      }
      if (paramax.x())
      {
        paramcy.a(ax.S());
        paramcy.a(paramax.g);
        paramcy.c();
      }
      if ((paramax.h != null) && (paramax.A()))
      {
        paramcy.a(ax.T());
        paramcy.a(paramax.h);
        paramcy.c();
      }
      if ((paramax.i != null) && (paramax.D()))
      {
        paramcy.a(ax.U());
        paramcy.a(paramax.i.a());
        paramcy.c();
      }
      if ((paramax.j != null) && (paramax.G()))
      {
        paramcy.a(ax.V());
        paramcy.a(paramax.j);
        paramcy.c();
      }
      if ((paramax.k != null) && (paramax.J()))
      {
        paramcy.a(ax.W());
        paramax.k.b(paramcy);
        paramcy.c();
      }
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public ax.a a()
    {
      return new ax.a(null);
    }
  }

  private static class c extends dj<ax>
  {
    public void a(cy paramcy, ax paramax)
      throws cf
    {
      paramcy = (de)paramcy;
      BitSet localBitSet = new BitSet();
      if (paramax.e())
        localBitSet.set(0);
      if (paramax.i())
        localBitSet.set(1);
      if (paramax.l())
        localBitSet.set(2);
      if (paramax.o())
        localBitSet.set(3);
      if (paramax.r())
        localBitSet.set(4);
      if (paramax.u())
        localBitSet.set(5);
      if (paramax.x())
        localBitSet.set(6);
      if (paramax.A())
        localBitSet.set(7);
      if (paramax.D())
        localBitSet.set(8);
      if (paramax.G())
        localBitSet.set(9);
      if (paramax.J())
        localBitSet.set(10);
      paramcy.a(localBitSet, 11);
      if (paramax.e())
        paramcy.a(paramax.a);
      if (paramax.i())
        paramcy.a(paramax.b);
      if (paramax.l())
        paramcy.a(paramax.c);
      if (paramax.o())
        paramcy.a(paramax.d);
      if (paramax.r())
        paramcy.a(paramax.e);
      if (paramax.u())
        paramcy.a(paramax.f);
      if (paramax.x())
        paramcy.a(paramax.g);
      if (paramax.A())
        paramcy.a(paramax.h);
      if (paramax.D())
        paramcy.a(paramax.i.a());
      if (paramax.G())
        paramcy.a(paramax.j);
      if (paramax.J())
        paramax.k.b(paramcy);
    }

    public void b(cy paramcy, ax paramax)
      throws cf
    {
      paramcy = (de)paramcy;
      BitSet localBitSet = paramcy.b(11);
      if (localBitSet.get(0))
      {
        paramax.a = paramcy.w();
        paramax.a(true);
      }
      if (localBitSet.get(1))
      {
        paramax.b = paramcy.z();
        paramax.b(true);
      }
      if (localBitSet.get(2))
      {
        paramax.c = paramcy.z();
        paramax.c(true);
      }
      if (localBitSet.get(3))
      {
        paramax.d = paramcy.y();
        paramax.d(true);
      }
      if (localBitSet.get(4))
      {
        paramax.e = paramcy.y();
        paramax.e(true);
      }
      if (localBitSet.get(5))
      {
        paramax.f = paramcy.z();
        paramax.f(true);
      }
      if (localBitSet.get(6))
      {
        paramax.g = paramcy.w();
        paramax.g(true);
      }
      if (localBitSet.get(7))
      {
        paramax.h = paramcy.z();
        paramax.h(true);
      }
      if (localBitSet.get(8))
      {
        paramax.i = ag.a(paramcy.w());
        paramax.i(true);
      }
      if (localBitSet.get(9))
      {
        paramax.j = paramcy.z();
        paramax.j(true);
      }
      if (localBitSet.get(10))
      {
        paramax.k = new bg();
        paramax.k.a(paramcy);
        paramax.k(true);
      }
    }
  }

  private static class d
    implements dh
  {
    public ax.c a()
    {
      return new ax.c(null);
    }
  }

  public static enum e
    implements cg
  {
    private static final Map<String, e> l;
    private final short m;
    private final String n;

    static
    {
      l = new HashMap();
      Iterator localIterator = EnumSet.allOf(e.class).iterator();
      while (localIterator.hasNext())
      {
        e locale = (e)localIterator.next();
        l.put(locale.b(), locale);
      }
    }

    private e(short paramShort, String paramString)
    {
      this.m = paramShort;
      this.n = paramString;
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
        return d;
      case 5:
        return e;
      case 6:
        return f;
      case 7:
        return g;
      case 8:
        return h;
      case 9:
        return i;
      case 10:
        return j;
      case 11:
      }
      return k;
    }

    public static e a(String paramString)
    {
      return (e)l.get(paramString);
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
      return this.m;
    }

    public String b()
    {
      return this.n;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.ax
 * JD-Core Version:    0.6.2
 */