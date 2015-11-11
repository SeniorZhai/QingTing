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

public class ah
  implements Serializable, Cloneable, bz<ah, e>
{
  public static final Map<e, cl> b;
  private static final dd c = new dd("ActivateMsg");
  private static final ct d = new ct("ts", (byte)10, (short)1);
  private static final Map<Class<? extends dg>, dh> e = new HashMap();
  private static final int f = 0;
  public long a;
  private byte g = 0;

  static
  {
    e.put(di.class, new b(null));
    e.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("ts", (byte)1, new cm((byte)10)));
    b = Collections.unmodifiableMap(localEnumMap);
    cl.a(ah.class, b);
  }

  public ah()
  {
  }

  public ah(long paramLong)
  {
    this();
    this.a = paramLong;
    a(true);
  }

  public ah(ah paramah)
  {
    this.g = paramah.g;
    this.a = paramah.a;
  }

  private void a(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    try
    {
      this.g = 0;
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

  public ah a()
  {
    return new ah(this);
  }

  public ah a(long paramLong)
  {
    this.a = paramLong;
    a(true);
    return this;
  }

  public void a(cy paramcy)
    throws cf
  {
    ((dh)e.get(paramcy.D())).b().b(paramcy, this);
  }

  public void a(boolean paramBoolean)
  {
    this.g = bw.a(this.g, 0, paramBoolean);
  }

  public void b()
  {
    a(false);
    this.a = 0L;
  }

  public void b(cy paramcy)
    throws cf
  {
    ((dh)e.get(paramcy.D())).b().a(paramcy, this);
  }

  public long c()
  {
    return this.a;
  }

  public void d()
  {
    this.g = bw.b(this.g, 0);
  }

  public boolean e()
  {
    return bw.a(this.g, 0);
  }

  public void f()
    throws cf
  {
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("ActivateMsg(");
    localStringBuilder.append("ts:");
    localStringBuilder.append(this.a);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }

  private static class a extends di<ah>
  {
    public void a(cy paramcy, ah paramah)
      throws cf
    {
      paramcy.j();
      ct localct = paramcy.l();
      if (localct.b == 0)
      {
        paramcy.k();
        if (!paramah.e())
          throw new cz("Required field 'ts' was not found in serialized data! Struct: " + toString());
      }
      else
      {
        switch (localct.c)
        {
        default:
          db.a(paramcy, localct.b);
        case 1:
        }
        while (true)
        {
          paramcy.m();
          break;
          if (localct.b == 10)
          {
            paramah.a = paramcy.x();
            paramah.a(true);
          }
          else
          {
            db.a(paramcy, localct.b);
          }
        }
      }
      paramah.f();
    }

    public void b(cy paramcy, ah paramah)
      throws cf
    {
      paramah.f();
      paramcy.a(ah.h());
      paramcy.a(ah.i());
      paramcy.a(paramah.a);
      paramcy.c();
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public ah.a a()
    {
      return new ah.a(null);
    }
  }

  private static class c extends dj<ah>
  {
    public void a(cy paramcy, ah paramah)
      throws cf
    {
      ((de)paramcy).a(paramah.a);
    }

    public void b(cy paramcy, ah paramah)
      throws cf
    {
      paramah.a = ((de)paramcy).x();
      paramah.a(true);
    }
  }

  private static class d
    implements dh
  {
    public ah.c a()
    {
      return new ah.c(null);
    }
  }

  public static enum e
    implements cg
  {
    private static final Map<String, e> b;
    private final short c;
    private final String d;

    static
    {
      b = new HashMap();
      Iterator localIterator = EnumSet.allOf(e.class).iterator();
      while (localIterator.hasNext())
      {
        e locale = (e)localIterator.next();
        b.put(locale.b(), locale);
      }
    }

    private e(short paramShort, String paramString)
    {
      this.c = paramShort;
      this.d = paramString;
    }

    public static e a(int paramInt)
    {
      switch (paramInt)
      {
      default:
        return null;
      case 1:
      }
      return a;
    }

    public static e a(String paramString)
    {
      return (e)b.get(paramString);
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
      return this.c;
    }

    public String b()
    {
      return this.d;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.ah
 * JD-Core Version:    0.6.2
 */