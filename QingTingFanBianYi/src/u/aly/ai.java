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

public class ai
  implements Serializable, Cloneable, bz<ai, e>
{
  public static final Map<e, cl> k;
  private static final dd l = new dd("AppInfo");
  private static final ct m = new ct("key", (byte)11, (short)1);
  private static final ct n = new ct("version", (byte)11, (short)2);
  private static final ct o = new ct("version_index", (byte)8, (short)3);
  private static final ct p = new ct("package_name", (byte)11, (short)4);
  private static final ct q = new ct("sdk_type", (byte)8, (short)5);
  private static final ct r = new ct("sdk_version", (byte)11, (short)6);
  private static final ct s = new ct("channel", (byte)11, (short)7);
  private static final ct t = new ct("wrapper_type", (byte)11, (short)8);
  private static final ct u = new ct("wrapper_version", (byte)11, (short)9);
  private static final ct v = new ct("vertical_type", (byte)8, (short)10);
  private static final Map<Class<? extends dg>, dh> w = new HashMap();
  private static final int x = 0;
  private static final int y = 1;
  private e[] A = { e.b, e.c, e.d, e.h, e.i, e.j };
  public String a;
  public String b;
  public int c;
  public String d;
  public bc e;
  public String f;
  public String g;
  public String h;
  public String i;
  public int j;
  private byte z = 0;

  static
  {
    w.put(di.class, new b(null));
    w.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("key", (byte)1, new cm((byte)11)));
    localEnumMap.put(e.b, new cl("version", (byte)2, new cm((byte)11)));
    localEnumMap.put(e.c, new cl("version_index", (byte)2, new cm((byte)8)));
    localEnumMap.put(e.d, new cl("package_name", (byte)2, new cm((byte)11)));
    localEnumMap.put(e.e, new cl("sdk_type", (byte)1, new ck((byte)16, bc.class)));
    localEnumMap.put(e.f, new cl("sdk_version", (byte)1, new cm((byte)11)));
    localEnumMap.put(e.g, new cl("channel", (byte)1, new cm((byte)11)));
    localEnumMap.put(e.h, new cl("wrapper_type", (byte)2, new cm((byte)11)));
    localEnumMap.put(e.i, new cl("wrapper_version", (byte)2, new cm((byte)11)));
    localEnumMap.put(e.j, new cl("vertical_type", (byte)2, new cm((byte)8)));
    k = Collections.unmodifiableMap(localEnumMap);
    cl.a(ai.class, k);
  }

  public ai()
  {
  }

  public ai(String paramString1, bc parambc, String paramString2, String paramString3)
  {
    this();
    this.a = paramString1;
    this.e = parambc;
    this.f = paramString2;
    this.g = paramString3;
  }

  public ai(ai paramai)
  {
    this.z = paramai.z;
    if (paramai.e())
      this.a = paramai.a;
    if (paramai.i())
      this.b = paramai.b;
    this.c = paramai.c;
    if (paramai.o())
      this.d = paramai.d;
    if (paramai.r())
      this.e = paramai.e;
    if (paramai.u())
      this.f = paramai.f;
    if (paramai.x())
      this.g = paramai.g;
    if (paramai.A())
      this.h = paramai.h;
    if (paramai.D())
      this.i = paramai.i;
    this.j = paramai.j;
  }

  private void a(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    try
    {
      this.z = 0;
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

  public String B()
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

  public int E()
  {
    return this.j;
  }

  public void F()
  {
    this.z = bw.b(this.z, 1);
  }

  public boolean G()
  {
    return bw.a(this.z, 1);
  }

  public void H()
    throws cf
  {
    if (this.a == null)
      throw new cz("Required field 'key' was not present! Struct: " + toString());
    if (this.e == null)
      throw new cz("Required field 'sdk_type' was not present! Struct: " + toString());
    if (this.f == null)
      throw new cz("Required field 'sdk_version' was not present! Struct: " + toString());
    if (this.g == null)
      throw new cz("Required field 'channel' was not present! Struct: " + toString());
  }

  public ai a()
  {
    return new ai(this);
  }

  public ai a(int paramInt)
  {
    this.c = paramInt;
    c(true);
    return this;
  }

  public ai a(String paramString)
  {
    this.a = paramString;
    return this;
  }

  public ai a(bc parambc)
  {
    this.e = parambc;
    return this;
  }

  public void a(cy paramcy)
    throws cf
  {
    ((dh)w.get(paramcy.D())).b().b(paramcy, this);
  }

  public void a(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.a = null;
  }

  public ai b(String paramString)
  {
    this.b = paramString;
    return this;
  }

  public void b()
  {
    this.a = null;
    this.b = null;
    c(false);
    this.c = 0;
    this.d = null;
    this.e = null;
    this.f = null;
    this.g = null;
    this.h = null;
    this.i = null;
    j(false);
    this.j = 0;
  }

  public void b(cy paramcy)
    throws cf
  {
    ((dh)w.get(paramcy.D())).b().a(paramcy, this);
  }

  public void b(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.b = null;
  }

  public String c()
  {
    return this.a;
  }

  public ai c(int paramInt)
  {
    this.j = paramInt;
    j(true);
    return this;
  }

  public ai c(String paramString)
  {
    this.d = paramString;
    return this;
  }

  public void c(boolean paramBoolean)
  {
    this.z = bw.a(this.z, 0, paramBoolean);
  }

  public e d(int paramInt)
  {
    return e.a(paramInt);
  }

  public ai d(String paramString)
  {
    this.f = paramString;
    return this;
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

  public ai e(String paramString)
  {
    this.g = paramString;
    return this;
  }

  public void e(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.e = null;
  }

  public boolean e()
  {
    return this.a != null;
  }

  public String f()
  {
    return this.b;
  }

  public ai f(String paramString)
  {
    this.h = paramString;
    return this;
  }

  public void f(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.f = null;
  }

  public ai g(String paramString)
  {
    this.i = paramString;
    return this;
  }

  public void g(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.g = null;
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

  public int j()
  {
    return this.c;
  }

  public void j(boolean paramBoolean)
  {
    this.z = bw.a(this.z, 1, paramBoolean);
  }

  public void k()
  {
    this.z = bw.b(this.z, 0);
  }

  public boolean l()
  {
    return bw.a(this.z, 0);
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

  public bc p()
  {
    return this.e;
  }

  public void q()
  {
    this.e = null;
  }

  public boolean r()
  {
    return this.e != null;
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
    StringBuilder localStringBuilder = new StringBuilder("AppInfo(");
    localStringBuilder.append("key:");
    if (this.a == null)
    {
      localStringBuilder.append("null");
      if (i())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("version:");
        if (this.b != null)
          break label368;
        localStringBuilder.append("null");
      }
      label72: if (l())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("version_index:");
        localStringBuilder.append(this.c);
      }
      if (o())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("package_name:");
        if (this.d != null)
          break label380;
        localStringBuilder.append("null");
      }
      label142: localStringBuilder.append(", ");
      localStringBuilder.append("sdk_type:");
      if (this.e != null)
        break label392;
      localStringBuilder.append("null");
      label173: localStringBuilder.append(", ");
      localStringBuilder.append("sdk_version:");
      if (this.f != null)
        break label404;
      localStringBuilder.append("null");
      label204: localStringBuilder.append(", ");
      localStringBuilder.append("channel:");
      if (this.g != null)
        break label416;
      localStringBuilder.append("null");
      label235: if (A())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("wrapper_type:");
        if (this.h != null)
          break label428;
        localStringBuilder.append("null");
      }
      label273: if (D())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("wrapper_version:");
        if (this.i != null)
          break label440;
        localStringBuilder.append("null");
      }
    }
    while (true)
    {
      if (G())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("vertical_type:");
        localStringBuilder.append(this.j);
      }
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(this.a);
      break;
      label368: localStringBuilder.append(this.b);
      break label72;
      label380: localStringBuilder.append(this.d);
      break label142;
      label392: localStringBuilder.append(this.e);
      break label173;
      label404: localStringBuilder.append(this.f);
      break label204;
      label416: localStringBuilder.append(this.g);
      break label235;
      label428: localStringBuilder.append(this.h);
      break label273;
      label440: localStringBuilder.append(this.i);
    }
  }

  public boolean u()
  {
    return this.f != null;
  }

  public String v()
  {
    return this.g;
  }

  public void w()
  {
    this.g = null;
  }

  public boolean x()
  {
    return this.g != null;
  }

  public String y()
  {
    return this.h;
  }

  public void z()
  {
    this.h = null;
  }

  private static class a extends di<ai>
  {
    public void a(cy paramcy, ai paramai)
      throws cf
    {
      paramcy.j();
      ct localct = paramcy.l();
      if (localct.b == 0)
      {
        paramcy.k();
        paramai.H();
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
      }
      while (true)
      {
        paramcy.m();
        break;
        if (localct.b == 11)
        {
          paramai.a = paramcy.z();
          paramai.a(true);
        }
        else
        {
          db.a(paramcy, localct.b);
          continue;
          if (localct.b == 11)
          {
            paramai.b = paramcy.z();
            paramai.b(true);
          }
          else
          {
            db.a(paramcy, localct.b);
            continue;
            if (localct.b == 8)
            {
              paramai.c = paramcy.w();
              paramai.c(true);
            }
            else
            {
              db.a(paramcy, localct.b);
              continue;
              if (localct.b == 11)
              {
                paramai.d = paramcy.z();
                paramai.d(true);
              }
              else
              {
                db.a(paramcy, localct.b);
                continue;
                if (localct.b == 8)
                {
                  paramai.e = bc.a(paramcy.w());
                  paramai.e(true);
                }
                else
                {
                  db.a(paramcy, localct.b);
                  continue;
                  if (localct.b == 11)
                  {
                    paramai.f = paramcy.z();
                    paramai.f(true);
                  }
                  else
                  {
                    db.a(paramcy, localct.b);
                    continue;
                    if (localct.b == 11)
                    {
                      paramai.g = paramcy.z();
                      paramai.g(true);
                    }
                    else
                    {
                      db.a(paramcy, localct.b);
                      continue;
                      if (localct.b == 11)
                      {
                        paramai.h = paramcy.z();
                        paramai.h(true);
                      }
                      else
                      {
                        db.a(paramcy, localct.b);
                        continue;
                        if (localct.b == 11)
                        {
                          paramai.i = paramcy.z();
                          paramai.i(true);
                        }
                        else
                        {
                          db.a(paramcy, localct.b);
                          continue;
                          if (localct.b == 8)
                          {
                            paramai.j = paramcy.w();
                            paramai.j(true);
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

    public void b(cy paramcy, ai paramai)
      throws cf
    {
      paramai.H();
      paramcy.a(ai.I());
      if (paramai.a != null)
      {
        paramcy.a(ai.J());
        paramcy.a(paramai.a);
        paramcy.c();
      }
      if ((paramai.b != null) && (paramai.i()))
      {
        paramcy.a(ai.K());
        paramcy.a(paramai.b);
        paramcy.c();
      }
      if (paramai.l())
      {
        paramcy.a(ai.L());
        paramcy.a(paramai.c);
        paramcy.c();
      }
      if ((paramai.d != null) && (paramai.o()))
      {
        paramcy.a(ai.M());
        paramcy.a(paramai.d);
        paramcy.c();
      }
      if (paramai.e != null)
      {
        paramcy.a(ai.N());
        paramcy.a(paramai.e.a());
        paramcy.c();
      }
      if (paramai.f != null)
      {
        paramcy.a(ai.O());
        paramcy.a(paramai.f);
        paramcy.c();
      }
      if (paramai.g != null)
      {
        paramcy.a(ai.P());
        paramcy.a(paramai.g);
        paramcy.c();
      }
      if ((paramai.h != null) && (paramai.A()))
      {
        paramcy.a(ai.Q());
        paramcy.a(paramai.h);
        paramcy.c();
      }
      if ((paramai.i != null) && (paramai.D()))
      {
        paramcy.a(ai.R());
        paramcy.a(paramai.i);
        paramcy.c();
      }
      if (paramai.G())
      {
        paramcy.a(ai.S());
        paramcy.a(paramai.j);
        paramcy.c();
      }
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public ai.a a()
    {
      return new ai.a(null);
    }
  }

  private static class c extends dj<ai>
  {
    public void a(cy paramcy, ai paramai)
      throws cf
    {
      paramcy = (de)paramcy;
      paramcy.a(paramai.a);
      paramcy.a(paramai.e.a());
      paramcy.a(paramai.f);
      paramcy.a(paramai.g);
      BitSet localBitSet = new BitSet();
      if (paramai.i())
        localBitSet.set(0);
      if (paramai.l())
        localBitSet.set(1);
      if (paramai.o())
        localBitSet.set(2);
      if (paramai.A())
        localBitSet.set(3);
      if (paramai.D())
        localBitSet.set(4);
      if (paramai.G())
        localBitSet.set(5);
      paramcy.a(localBitSet, 6);
      if (paramai.i())
        paramcy.a(paramai.b);
      if (paramai.l())
        paramcy.a(paramai.c);
      if (paramai.o())
        paramcy.a(paramai.d);
      if (paramai.A())
        paramcy.a(paramai.h);
      if (paramai.D())
        paramcy.a(paramai.i);
      if (paramai.G())
        paramcy.a(paramai.j);
    }

    public void b(cy paramcy, ai paramai)
      throws cf
    {
      paramcy = (de)paramcy;
      paramai.a = paramcy.z();
      paramai.a(true);
      paramai.e = bc.a(paramcy.w());
      paramai.e(true);
      paramai.f = paramcy.z();
      paramai.f(true);
      paramai.g = paramcy.z();
      paramai.g(true);
      BitSet localBitSet = paramcy.b(6);
      if (localBitSet.get(0))
      {
        paramai.b = paramcy.z();
        paramai.b(true);
      }
      if (localBitSet.get(1))
      {
        paramai.c = paramcy.w();
        paramai.c(true);
      }
      if (localBitSet.get(2))
      {
        paramai.d = paramcy.z();
        paramai.d(true);
      }
      if (localBitSet.get(3))
      {
        paramai.h = paramcy.z();
        paramai.h(true);
      }
      if (localBitSet.get(4))
      {
        paramai.i = paramcy.z();
        paramai.i(true);
      }
      if (localBitSet.get(5))
      {
        paramai.j = paramcy.w();
        paramai.j(true);
      }
    }
  }

  private static class d
    implements dh
  {
    public ai.c a()
    {
      return new ai.c(null);
    }
  }

  public static enum e
    implements cg
  {
    private static final Map<String, e> k;
    private final short l;
    private final String m;

    static
    {
      k = new HashMap();
      Iterator localIterator = EnumSet.allOf(e.class).iterator();
      while (localIterator.hasNext())
      {
        e locale = (e)localIterator.next();
        k.put(locale.b(), locale);
      }
    }

    private e(short paramShort, String paramString)
    {
      this.l = paramShort;
      this.m = paramString;
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
      }
      return j;
    }

    public static e a(String paramString)
    {
      return (e)k.get(paramString);
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
      return this.l;
    }

    public String b()
    {
      return this.m;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.ai
 * JD-Core Version:    0.6.2
 */