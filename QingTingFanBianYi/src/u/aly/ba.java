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

public class ba
  implements Serializable, Cloneable, bz<ba, e>
{
  public static final Map<e, cl> c;
  private static final dd d = new dd("Resolution");
  private static final ct e = new ct("height", (byte)8, (short)1);
  private static final ct f = new ct("width", (byte)8, (short)2);
  private static final Map<Class<? extends dg>, dh> g = new HashMap();
  private static final int h = 0;
  private static final int i = 1;
  public int a;
  public int b;
  private byte j = 0;

  static
  {
    g.put(di.class, new b(null));
    g.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("height", (byte)1, new cm((byte)8)));
    localEnumMap.put(e.b, new cl("width", (byte)1, new cm((byte)8)));
    c = Collections.unmodifiableMap(localEnumMap);
    cl.a(ba.class, c);
  }

  public ba()
  {
  }

  public ba(int paramInt1, int paramInt2)
  {
    this();
    this.a = paramInt1;
    a(true);
    this.b = paramInt2;
    b(true);
  }

  public ba(ba paramba)
  {
    this.j = paramba.j;
    this.a = paramba.a;
    this.b = paramba.b;
  }

  private void a(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    try
    {
      this.j = 0;
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

  public ba a()
  {
    return new ba(this);
  }

  public ba a(int paramInt)
  {
    this.a = paramInt;
    a(true);
    return this;
  }

  public void a(cy paramcy)
    throws cf
  {
    ((dh)g.get(paramcy.D())).b().b(paramcy, this);
  }

  public void a(boolean paramBoolean)
  {
    this.j = bw.a(this.j, 0, paramBoolean);
  }

  public void b()
  {
    a(false);
    this.a = 0;
    b(false);
    this.b = 0;
  }

  public void b(cy paramcy)
    throws cf
  {
    ((dh)g.get(paramcy.D())).b().a(paramcy, this);
  }

  public void b(boolean paramBoolean)
  {
    this.j = bw.a(this.j, 1, paramBoolean);
  }

  public int c()
  {
    return this.a;
  }

  public ba c(int paramInt)
  {
    this.b = paramInt;
    b(true);
    return this;
  }

  public e d(int paramInt)
  {
    return e.a(paramInt);
  }

  public void d()
  {
    this.j = bw.b(this.j, 0);
  }

  public boolean e()
  {
    return bw.a(this.j, 0);
  }

  public int f()
  {
    return this.b;
  }

  public void h()
  {
    this.j = bw.b(this.j, 1);
  }

  public boolean i()
  {
    return bw.a(this.j, 1);
  }

  public void j()
    throws cf
  {
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Resolution(");
    localStringBuilder.append("height:");
    localStringBuilder.append(this.a);
    localStringBuilder.append(", ");
    localStringBuilder.append("width:");
    localStringBuilder.append(this.b);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }

  private static class a extends di<ba>
  {
    public void a(cy paramcy, ba paramba)
      throws cf
    {
      paramcy.j();
      ct localct = paramcy.l();
      if (localct.b == 0)
      {
        paramcy.k();
        if (!paramba.e())
          throw new cz("Required field 'height' was not found in serialized data! Struct: " + toString());
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
          if (localct.b == 8)
          {
            paramba.a = paramcy.w();
            paramba.a(true);
          }
          else
          {
            db.a(paramcy, localct.b);
            continue;
            if (localct.b == 8)
            {
              paramba.b = paramcy.w();
              paramba.b(true);
            }
            else
            {
              db.a(paramcy, localct.b);
            }
          }
        }
      }
      if (!paramba.i())
        throw new cz("Required field 'width' was not found in serialized data! Struct: " + toString());
      paramba.j();
    }

    public void b(cy paramcy, ba paramba)
      throws cf
    {
      paramba.j();
      paramcy.a(ba.k());
      paramcy.a(ba.l());
      paramcy.a(paramba.a);
      paramcy.c();
      paramcy.a(ba.m());
      paramcy.a(paramba.b);
      paramcy.c();
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public ba.a a()
    {
      return new ba.a(null);
    }
  }

  private static class c extends dj<ba>
  {
    public void a(cy paramcy, ba paramba)
      throws cf
    {
      paramcy = (de)paramcy;
      paramcy.a(paramba.a);
      paramcy.a(paramba.b);
    }

    public void b(cy paramcy, ba paramba)
      throws cf
    {
      paramcy = (de)paramcy;
      paramba.a = paramcy.w();
      paramba.a(true);
      paramba.b = paramcy.w();
      paramba.b(true);
    }
  }

  private static class d
    implements dh
  {
    public ba.c a()
    {
      return new ba.c(null);
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
 * Qualified Name:     u.aly.ba
 * JD-Core Version:    0.6.2
 */