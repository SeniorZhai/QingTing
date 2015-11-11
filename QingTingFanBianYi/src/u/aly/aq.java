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

public class aq
  implements Serializable, Cloneable, bz<aq, e>
{
  public static final Map<e, cl> e;
  private static final dd f = new dd("IdJournal");
  private static final ct g = new ct("domain", (byte)11, (short)1);
  private static final ct h = new ct("old_id", (byte)11, (short)2);
  private static final ct i = new ct("new_id", (byte)11, (short)3);
  private static final ct j = new ct("ts", (byte)10, (short)4);
  private static final Map<Class<? extends dg>, dh> k = new HashMap();
  private static final int l = 0;
  public String a;
  public String b;
  public String c;
  public long d;
  private byte m = 0;
  private e[] n = { e.b };

  static
  {
    k.put(di.class, new b(null));
    k.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("domain", (byte)1, new cm((byte)11)));
    localEnumMap.put(e.b, new cl("old_id", (byte)2, new cm((byte)11)));
    localEnumMap.put(e.c, new cl("new_id", (byte)1, new cm((byte)11)));
    localEnumMap.put(e.d, new cl("ts", (byte)1, new cm((byte)10)));
    e = Collections.unmodifiableMap(localEnumMap);
    cl.a(aq.class, e);
  }

  public aq()
  {
  }

  public aq(String paramString1, String paramString2, long paramLong)
  {
    this();
    this.a = paramString1;
    this.c = paramString2;
    this.d = paramLong;
    d(true);
  }

  public aq(aq paramaq)
  {
    this.m = paramaq.m;
    if (paramaq.e())
      this.a = paramaq.a;
    if (paramaq.i())
      this.b = paramaq.b;
    if (paramaq.l())
      this.c = paramaq.c;
    this.d = paramaq.d;
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

  public aq a()
  {
    return new aq(this);
  }

  public aq a(long paramLong)
  {
    this.d = paramLong;
    d(true);
    return this;
  }

  public aq a(String paramString)
  {
    this.a = paramString;
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

  public aq b(String paramString)
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
    this.d = 0L;
  }

  public void b(cy paramcy)
    throws cf
  {
    ((dh)k.get(paramcy.D())).b().a(paramcy, this);
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

  public aq c(String paramString)
  {
    this.c = paramString;
    return this;
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
    this.m = bw.a(this.m, 0, paramBoolean);
  }

  public boolean e()
  {
    return this.a != null;
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

  public long m()
  {
    return this.d;
  }

  public void n()
  {
    this.m = bw.b(this.m, 0);
  }

  public boolean o()
  {
    return bw.a(this.m, 0);
  }

  public void p()
    throws cf
  {
    if (this.a == null)
      throw new cz("Required field 'domain' was not present! Struct: " + toString());
    if (this.c == null)
      throw new cz("Required field 'new_id' was not present! Struct: " + toString());
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("IdJournal(");
    localStringBuilder.append("domain:");
    if (this.a == null)
    {
      localStringBuilder.append("null");
      if (i())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("old_id:");
        if (this.b != null)
          break label153;
        localStringBuilder.append("null");
      }
      label72: localStringBuilder.append(", ");
      localStringBuilder.append("new_id:");
      if (this.c != null)
        break label165;
      localStringBuilder.append("null");
    }
    while (true)
    {
      localStringBuilder.append(", ");
      localStringBuilder.append("ts:");
      localStringBuilder.append(this.d);
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(this.a);
      break;
      label153: localStringBuilder.append(this.b);
      break label72;
      label165: localStringBuilder.append(this.c);
    }
  }

  private static class a extends di<aq>
  {
    public void a(cy paramcy, aq paramaq)
      throws cf
    {
      paramcy.j();
      ct localct = paramcy.l();
      if (localct.b == 0)
      {
        paramcy.k();
        if (!paramaq.o())
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
        case 4:
        }
        while (true)
        {
          paramcy.m();
          break;
          if (localct.b == 11)
          {
            paramaq.a = paramcy.z();
            paramaq.a(true);
          }
          else
          {
            db.a(paramcy, localct.b);
            continue;
            if (localct.b == 11)
            {
              paramaq.b = paramcy.z();
              paramaq.b(true);
            }
            else
            {
              db.a(paramcy, localct.b);
              continue;
              if (localct.b == 11)
              {
                paramaq.c = paramcy.z();
                paramaq.c(true);
              }
              else
              {
                db.a(paramcy, localct.b);
                continue;
                if (localct.b == 10)
                {
                  paramaq.d = paramcy.x();
                  paramaq.d(true);
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
      paramaq.p();
    }

    public void b(cy paramcy, aq paramaq)
      throws cf
    {
      paramaq.p();
      paramcy.a(aq.q());
      if (paramaq.a != null)
      {
        paramcy.a(aq.r());
        paramcy.a(paramaq.a);
        paramcy.c();
      }
      if ((paramaq.b != null) && (paramaq.i()))
      {
        paramcy.a(aq.s());
        paramcy.a(paramaq.b);
        paramcy.c();
      }
      if (paramaq.c != null)
      {
        paramcy.a(aq.t());
        paramcy.a(paramaq.c);
        paramcy.c();
      }
      paramcy.a(aq.u());
      paramcy.a(paramaq.d);
      paramcy.c();
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public aq.a a()
    {
      return new aq.a(null);
    }
  }

  private static class c extends dj<aq>
  {
    public void a(cy paramcy, aq paramaq)
      throws cf
    {
      paramcy = (de)paramcy;
      paramcy.a(paramaq.a);
      paramcy.a(paramaq.c);
      paramcy.a(paramaq.d);
      BitSet localBitSet = new BitSet();
      if (paramaq.i())
        localBitSet.set(0);
      paramcy.a(localBitSet, 1);
      if (paramaq.i())
        paramcy.a(paramaq.b);
    }

    public void b(cy paramcy, aq paramaq)
      throws cf
    {
      paramcy = (de)paramcy;
      paramaq.a = paramcy.z();
      paramaq.a(true);
      paramaq.c = paramcy.z();
      paramaq.c(true);
      paramaq.d = paramcy.x();
      paramaq.d(true);
      if (paramcy.b(1).get(0))
      {
        paramaq.b = paramcy.z();
        paramaq.b(true);
      }
    }
  }

  private static class d
    implements dh
  {
    public aq.c a()
    {
      return new aq.c(null);
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
 * Qualified Name:     u.aly.aq
 * JD-Core Version:    0.6.2
 */