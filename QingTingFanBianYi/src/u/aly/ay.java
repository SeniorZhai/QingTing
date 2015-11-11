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

public class ay
  implements Serializable, Cloneable, bz<ay, e>
{
  public static final Map<e, cl> c;
  private static final dd d = new dd("Page");
  private static final ct e = new ct("page_name", (byte)11, (short)1);
  private static final ct f = new ct("duration", (byte)10, (short)2);
  private static final Map<Class<? extends dg>, dh> g = new HashMap();
  private static final int h = 0;
  public String a;
  public long b;
  private byte i = 0;

  static
  {
    g.put(di.class, new b(null));
    g.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("page_name", (byte)1, new cm((byte)11)));
    localEnumMap.put(e.b, new cl("duration", (byte)1, new cm((byte)10)));
    c = Collections.unmodifiableMap(localEnumMap);
    cl.a(ay.class, c);
  }

  public ay()
  {
  }

  public ay(String paramString, long paramLong)
  {
    this();
    this.a = paramString;
    this.b = paramLong;
    b(true);
  }

  public ay(ay paramay)
  {
    this.i = paramay.i;
    if (paramay.e())
      this.a = paramay.a;
    this.b = paramay.b;
  }

  private void a(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    try
    {
      this.i = 0;
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

  public ay a()
  {
    return new ay(this);
  }

  public ay a(long paramLong)
  {
    this.b = paramLong;
    b(true);
    return this;
  }

  public ay a(String paramString)
  {
    this.a = paramString;
    return this;
  }

  public void a(cy paramcy)
    throws cf
  {
    ((dh)g.get(paramcy.D())).b().b(paramcy, this);
  }

  public void a(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.a = null;
  }

  public void b()
  {
    this.a = null;
    b(false);
    this.b = 0L;
  }

  public void b(cy paramcy)
    throws cf
  {
    ((dh)g.get(paramcy.D())).b().a(paramcy, this);
  }

  public void b(boolean paramBoolean)
  {
    this.i = bw.a(this.i, 0, paramBoolean);
  }

  public String c()
  {
    return this.a;
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
    this.i = bw.b(this.i, 0);
  }

  public boolean i()
  {
    return bw.a(this.i, 0);
  }

  public void j()
    throws cf
  {
    if (this.a == null)
      throw new cz("Required field 'page_name' was not present! Struct: " + toString());
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Page(");
    localStringBuilder.append("page_name:");
    if (this.a == null)
      localStringBuilder.append("null");
    while (true)
    {
      localStringBuilder.append(", ");
      localStringBuilder.append("duration:");
      localStringBuilder.append(this.b);
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(this.a);
    }
  }

  private static class a extends di<ay>
  {
    public void a(cy paramcy, ay paramay)
      throws cf
    {
      paramcy.j();
      ct localct = paramcy.l();
      if (localct.b == 0)
      {
        paramcy.k();
        if (!paramay.i())
          throw new cz("Required field 'duration' was not found in serialized data! Struct: " + toString());
      }
      else
      {
        switch (localct.c)
        {
        default:
          db.a(paramcy, localct.b);
        case 1:
        case 2:
        }
        while (true)
        {
          paramcy.m();
          break;
          if (localct.b == 11)
          {
            paramay.a = paramcy.z();
            paramay.a(true);
          }
          else
          {
            db.a(paramcy, localct.b);
            continue;
            if (localct.b == 10)
            {
              paramay.b = paramcy.x();
              paramay.b(true);
            }
            else
            {
              db.a(paramcy, localct.b);
            }
          }
        }
      }
      paramay.j();
    }

    public void b(cy paramcy, ay paramay)
      throws cf
    {
      paramay.j();
      paramcy.a(ay.k());
      if (paramay.a != null)
      {
        paramcy.a(ay.l());
        paramcy.a(paramay.a);
        paramcy.c();
      }
      paramcy.a(ay.m());
      paramcy.a(paramay.b);
      paramcy.c();
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public ay.a a()
    {
      return new ay.a(null);
    }
  }

  private static class c extends dj<ay>
  {
    public void a(cy paramcy, ay paramay)
      throws cf
    {
      paramcy = (de)paramcy;
      paramcy.a(paramay.a);
      paramcy.a(paramay.b);
    }

    public void b(cy paramcy, ay paramay)
      throws cf
    {
      paramcy = (de)paramcy;
      paramay.a = paramcy.z();
      paramay.a(true);
      paramay.b = paramcy.x();
      paramay.b(true);
    }
  }

  private static class d
    implements dh
  {
    public ay.c a()
    {
      return new ay.c(null);
    }
  }

  public static enum e
    implements cg
  {
    private static final Map<String, e> c;
    private final short d;
    private final String e;

    static
    {
      c = new HashMap();
      Iterator localIterator = EnumSet.allOf(e.class).iterator();
      while (localIterator.hasNext())
      {
        e locale = (e)localIterator.next();
        c.put(locale.b(), locale);
      }
    }

    private e(short paramShort, String paramString)
    {
      this.d = paramShort;
      this.e = paramString;
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
      }
      return b;
    }

    public static e a(String paramString)
    {
      return (e)c.get(paramString);
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
      return this.d;
    }

    public String b()
    {
      return this.e;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.ay
 * JD-Core Version:    0.6.2
 */