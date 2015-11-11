package u.aly;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class bh
  implements Serializable, Cloneable, bz<bh, e>
{
  public static final Map<e, cl> j;
  private static final dd k = new dd("UMEnvelope");
  private static final ct l = new ct("version", (byte)11, (short)1);
  private static final ct m = new ct("address", (byte)11, (short)2);
  private static final ct n = new ct("signature", (byte)11, (short)3);
  private static final ct o = new ct("serial_num", (byte)8, (short)4);
  private static final ct p = new ct("ts_secs", (byte)8, (short)5);
  private static final ct q = new ct("length", (byte)8, (short)6);
  private static final ct r = new ct("entity", (byte)11, (short)7);
  private static final ct s = new ct("guid", (byte)11, (short)8);
  private static final ct t = new ct("checksum", (byte)11, (short)9);
  private static final Map<Class<? extends dg>, dh> u = new HashMap();
  private static final int v = 0;
  private static final int w = 1;
  private static final int x = 2;
  public String a;
  public String b;
  public String c;
  public int d;
  public int e;
  public int f;
  public ByteBuffer g;
  public String h;
  public String i;
  private byte y = 0;

  static
  {
    u.put(di.class, new b(null));
    u.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("version", (byte)1, new cm((byte)11)));
    localEnumMap.put(e.b, new cl("address", (byte)1, new cm((byte)11)));
    localEnumMap.put(e.c, new cl("signature", (byte)1, new cm((byte)11)));
    localEnumMap.put(e.d, new cl("serial_num", (byte)1, new cm((byte)8)));
    localEnumMap.put(e.e, new cl("ts_secs", (byte)1, new cm((byte)8)));
    localEnumMap.put(e.f, new cl("length", (byte)1, new cm((byte)8)));
    localEnumMap.put(e.g, new cl("entity", (byte)1, new cm((byte)11, true)));
    localEnumMap.put(e.h, new cl("guid", (byte)1, new cm((byte)11)));
    localEnumMap.put(e.i, new cl("checksum", (byte)1, new cm((byte)11)));
    j = Collections.unmodifiableMap(localEnumMap);
    cl.a(bh.class, j);
  }

  public bh()
  {
  }

  public bh(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, int paramInt3, ByteBuffer paramByteBuffer, String paramString4, String paramString5)
  {
    this();
    this.a = paramString1;
    this.b = paramString2;
    this.c = paramString3;
    this.d = paramInt1;
    d(true);
    this.e = paramInt2;
    e(true);
    this.f = paramInt3;
    f(true);
    this.g = paramByteBuffer;
    this.h = paramString4;
    this.i = paramString5;
  }

  public bh(bh parambh)
  {
    this.y = parambh.y;
    if (parambh.e())
      this.a = parambh.a;
    if (parambh.i())
      this.b = parambh.b;
    if (parambh.l())
      this.c = parambh.c;
    this.d = parambh.d;
    this.e = parambh.e;
    this.f = parambh.f;
    if (parambh.y())
      this.g = ca.d(parambh.g);
    if (parambh.B())
      this.h = parambh.h;
    if (parambh.E())
      this.i = parambh.i;
  }

  private void a(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    try
    {
      this.y = 0;
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

  public void A()
  {
    this.h = null;
  }

  public boolean B()
  {
    return this.h != null;
  }

  public String C()
  {
    return this.i;
  }

  public void D()
  {
    this.i = null;
  }

  public boolean E()
  {
    return this.i != null;
  }

  public void F()
    throws cf
  {
    if (this.a == null)
      throw new cz("Required field 'version' was not present! Struct: " + toString());
    if (this.b == null)
      throw new cz("Required field 'address' was not present! Struct: " + toString());
    if (this.c == null)
      throw new cz("Required field 'signature' was not present! Struct: " + toString());
    if (this.g == null)
      throw new cz("Required field 'entity' was not present! Struct: " + toString());
    if (this.h == null)
      throw new cz("Required field 'guid' was not present! Struct: " + toString());
    if (this.i == null)
      throw new cz("Required field 'checksum' was not present! Struct: " + toString());
  }

  public bh a()
  {
    return new bh(this);
  }

  public bh a(int paramInt)
  {
    this.d = paramInt;
    d(true);
    return this;
  }

  public bh a(String paramString)
  {
    this.a = paramString;
    return this;
  }

  public bh a(ByteBuffer paramByteBuffer)
  {
    this.g = paramByteBuffer;
    return this;
  }

  public bh a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null);
    for (paramArrayOfByte = (ByteBuffer)null; ; paramArrayOfByte = ByteBuffer.wrap(paramArrayOfByte))
    {
      a(paramArrayOfByte);
      return this;
    }
  }

  public void a(cy paramcy)
    throws cf
  {
    ((dh)u.get(paramcy.D())).b().b(paramcy, this);
  }

  public void a(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.a = null;
  }

  public bh b(String paramString)
  {
    this.b = paramString;
    return this;
  }

  public void b()
  {
    this.a = null;
    this.b = null;
    this.c = null;
    d(false);
    this.d = 0;
    e(false);
    this.e = 0;
    f(false);
    this.f = 0;
    this.g = null;
    this.h = null;
    this.i = null;
  }

  public void b(cy paramcy)
    throws cf
  {
    ((dh)u.get(paramcy.D())).b().a(paramcy, this);
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

  public bh c(int paramInt)
  {
    this.e = paramInt;
    e(true);
    return this;
  }

  public bh c(String paramString)
  {
    this.c = paramString;
    return this;
  }

  public void c(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.c = null;
  }

  public bh d(int paramInt)
  {
    this.f = paramInt;
    f(true);
    return this;
  }

  public bh d(String paramString)
  {
    this.h = paramString;
    return this;
  }

  public void d()
  {
    this.a = null;
  }

  public void d(boolean paramBoolean)
  {
    this.y = bw.a(this.y, 0, paramBoolean);
  }

  public e e(int paramInt)
  {
    return e.a(paramInt);
  }

  public bh e(String paramString)
  {
    this.i = paramString;
    return this;
  }

  public void e(boolean paramBoolean)
  {
    this.y = bw.a(this.y, 1, paramBoolean);
  }

  public boolean e()
  {
    return this.a != null;
  }

  public String f()
  {
    return this.b;
  }

  public void f(boolean paramBoolean)
  {
    this.y = bw.a(this.y, 2, paramBoolean);
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

  public int m()
  {
    return this.d;
  }

  public void n()
  {
    this.y = bw.b(this.y, 0);
  }

  public boolean o()
  {
    return bw.a(this.y, 0);
  }

  public int p()
  {
    return this.e;
  }

  public void q()
  {
    this.y = bw.b(this.y, 1);
  }

  public boolean r()
  {
    return bw.a(this.y, 1);
  }

  public int s()
  {
    return this.f;
  }

  public void t()
  {
    this.y = bw.b(this.y, 2);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("UMEnvelope(");
    localStringBuilder.append("version:");
    if (this.a == null)
    {
      localStringBuilder.append("null");
      localStringBuilder.append(", ");
      localStringBuilder.append("address:");
      if (this.b != null)
        break label289;
      localStringBuilder.append("null");
      label65: localStringBuilder.append(", ");
      localStringBuilder.append("signature:");
      if (this.c != null)
        break label301;
      localStringBuilder.append("null");
      label96: localStringBuilder.append(", ");
      localStringBuilder.append("serial_num:");
      localStringBuilder.append(this.d);
      localStringBuilder.append(", ");
      localStringBuilder.append("ts_secs:");
      localStringBuilder.append(this.e);
      localStringBuilder.append(", ");
      localStringBuilder.append("length:");
      localStringBuilder.append(this.f);
      localStringBuilder.append(", ");
      localStringBuilder.append("entity:");
      if (this.g != null)
        break label313;
      localStringBuilder.append("null");
      label202: localStringBuilder.append(", ");
      localStringBuilder.append("guid:");
      if (this.h != null)
        break label324;
      localStringBuilder.append("null");
      label233: localStringBuilder.append(", ");
      localStringBuilder.append("checksum:");
      if (this.i != null)
        break label336;
      localStringBuilder.append("null");
    }
    while (true)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(this.a);
      break;
      label289: localStringBuilder.append(this.b);
      break label65;
      label301: localStringBuilder.append(this.c);
      break label96;
      label313: ca.a(this.g, localStringBuilder);
      break label202;
      label324: localStringBuilder.append(this.h);
      break label233;
      label336: localStringBuilder.append(this.i);
    }
  }

  public boolean u()
  {
    return bw.a(this.y, 2);
  }

  public byte[] v()
  {
    a(ca.c(this.g));
    if (this.g == null)
      return null;
    return this.g.array();
  }

  public ByteBuffer w()
  {
    return this.g;
  }

  public void x()
  {
    this.g = null;
  }

  public boolean y()
  {
    return this.g != null;
  }

  public String z()
  {
    return this.h;
  }

  private static class a extends di<bh>
  {
    public void a(cy paramcy, bh parambh)
      throws cf
    {
      paramcy.j();
      ct localct = paramcy.l();
      if (localct.b == 0)
      {
        paramcy.k();
        if (!parambh.o())
          throw new cz("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
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
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
        }
        while (true)
        {
          paramcy.m();
          break;
          if (localct.b == 11)
          {
            parambh.a = paramcy.z();
            parambh.a(true);
          }
          else
          {
            db.a(paramcy, localct.b);
            continue;
            if (localct.b == 11)
            {
              parambh.b = paramcy.z();
              parambh.b(true);
            }
            else
            {
              db.a(paramcy, localct.b);
              continue;
              if (localct.b == 11)
              {
                parambh.c = paramcy.z();
                parambh.c(true);
              }
              else
              {
                db.a(paramcy, localct.b);
                continue;
                if (localct.b == 8)
                {
                  parambh.d = paramcy.w();
                  parambh.d(true);
                }
                else
                {
                  db.a(paramcy, localct.b);
                  continue;
                  if (localct.b == 8)
                  {
                    parambh.e = paramcy.w();
                    parambh.e(true);
                  }
                  else
                  {
                    db.a(paramcy, localct.b);
                    continue;
                    if (localct.b == 8)
                    {
                      parambh.f = paramcy.w();
                      parambh.f(true);
                    }
                    else
                    {
                      db.a(paramcy, localct.b);
                      continue;
                      if (localct.b == 11)
                      {
                        parambh.g = paramcy.A();
                        parambh.g(true);
                      }
                      else
                      {
                        db.a(paramcy, localct.b);
                        continue;
                        if (localct.b == 11)
                        {
                          parambh.h = paramcy.z();
                          parambh.h(true);
                        }
                        else
                        {
                          db.a(paramcy, localct.b);
                          continue;
                          if (localct.b == 11)
                          {
                            parambh.i = paramcy.z();
                            parambh.i(true);
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
      if (!parambh.r())
        throw new cz("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
      if (!parambh.u())
        throw new cz("Required field 'length' was not found in serialized data! Struct: " + toString());
      parambh.F();
    }

    public void b(cy paramcy, bh parambh)
      throws cf
    {
      parambh.F();
      paramcy.a(bh.G());
      if (parambh.a != null)
      {
        paramcy.a(bh.H());
        paramcy.a(parambh.a);
        paramcy.c();
      }
      if (parambh.b != null)
      {
        paramcy.a(bh.I());
        paramcy.a(parambh.b);
        paramcy.c();
      }
      if (parambh.c != null)
      {
        paramcy.a(bh.J());
        paramcy.a(parambh.c);
        paramcy.c();
      }
      paramcy.a(bh.K());
      paramcy.a(parambh.d);
      paramcy.c();
      paramcy.a(bh.L());
      paramcy.a(parambh.e);
      paramcy.c();
      paramcy.a(bh.M());
      paramcy.a(parambh.f);
      paramcy.c();
      if (parambh.g != null)
      {
        paramcy.a(bh.N());
        paramcy.a(parambh.g);
        paramcy.c();
      }
      if (parambh.h != null)
      {
        paramcy.a(bh.O());
        paramcy.a(parambh.h);
        paramcy.c();
      }
      if (parambh.i != null)
      {
        paramcy.a(bh.P());
        paramcy.a(parambh.i);
        paramcy.c();
      }
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public bh.a a()
    {
      return new bh.a(null);
    }
  }

  private static class c extends dj<bh>
  {
    public void a(cy paramcy, bh parambh)
      throws cf
    {
      paramcy = (de)paramcy;
      paramcy.a(parambh.a);
      paramcy.a(parambh.b);
      paramcy.a(parambh.c);
      paramcy.a(parambh.d);
      paramcy.a(parambh.e);
      paramcy.a(parambh.f);
      paramcy.a(parambh.g);
      paramcy.a(parambh.h);
      paramcy.a(parambh.i);
    }

    public void b(cy paramcy, bh parambh)
      throws cf
    {
      paramcy = (de)paramcy;
      parambh.a = paramcy.z();
      parambh.a(true);
      parambh.b = paramcy.z();
      parambh.b(true);
      parambh.c = paramcy.z();
      parambh.c(true);
      parambh.d = paramcy.w();
      parambh.d(true);
      parambh.e = paramcy.w();
      parambh.e(true);
      parambh.f = paramcy.w();
      parambh.f(true);
      parambh.g = paramcy.A();
      parambh.g(true);
      parambh.h = paramcy.z();
      parambh.h(true);
      parambh.i = paramcy.z();
      parambh.i(true);
    }
  }

  private static class d
    implements dh
  {
    public bh.c a()
    {
      return new bh.c(null);
    }
  }

  public static enum e
    implements cg
  {
    private static final Map<String, e> j;
    private final short k;
    private final String l;

    static
    {
      j = new HashMap();
      Iterator localIterator = EnumSet.allOf(e.class).iterator();
      while (localIterator.hasNext())
      {
        e locale = (e)localIterator.next();
        j.put(locale.b(), locale);
      }
    }

    private e(short paramShort, String paramString)
    {
      this.k = paramShort;
      this.l = paramString;
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
      }
      return i;
    }

    public static e a(String paramString)
    {
      return (e)j.get(paramString);
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
      return this.k;
    }

    public String b()
    {
      return this.l;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.bh
 * JD-Core Version:    0.6.2
 */