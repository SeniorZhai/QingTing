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

public class be
  implements Serializable, Cloneable, bz<be, e>
{
  public static final Map<e, cl> c;
  private static final dd d = new dd("Traffic");
  private static final ct e = new ct("upload_traffic", (byte)8, (short)1);
  private static final ct f = new ct("download_traffic", (byte)8, (short)2);
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
    localEnumMap.put(e.a, new cl("upload_traffic", (byte)1, new cm((byte)8)));
    localEnumMap.put(e.b, new cl("download_traffic", (byte)1, new cm((byte)8)));
    c = Collections.unmodifiableMap(localEnumMap);
    cl.a(be.class, c);
  }

  public be()
  {
  }

  public be(int paramInt1, int paramInt2)
  {
    this();
    this.a = paramInt1;
    a(true);
    this.b = paramInt2;
    b(true);
  }

  public be(be parambe)
  {
    this.j = parambe.j;
    this.a = parambe.a;
    this.b = parambe.b;
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

  public be a()
  {
    return new be(this);
  }

  public be a(int paramInt)
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

  public be c(int paramInt)
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
    StringBuilder localStringBuilder = new StringBuilder("Traffic(");
    localStringBuilder.append("upload_traffic:");
    localStringBuilder.append(this.a);
    localStringBuilder.append(", ");
    localStringBuilder.append("download_traffic:");
    localStringBuilder.append(this.b);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }

  private static class a extends di<be>
  {
    public void a(cy paramcy, be parambe)
      throws cf
    {
      paramcy.j();
      ct localct = paramcy.l();
      if (localct.b == 0)
      {
        paramcy.k();
        if (!parambe.e())
          throw new cz("Required field 'upload_traffic' was not found in serialized data! Struct: " + toString());
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
            parambe.a = paramcy.w();
            parambe.a(true);
          }
          else
          {
            db.a(paramcy, localct.b);
            continue;
            if (localct.b == 8)
            {
              parambe.b = paramcy.w();
              parambe.b(true);
            }
            else
            {
              db.a(paramcy, localct.b);
            }
          }
        }
      }
      if (!parambe.i())
        throw new cz("Required field 'download_traffic' was not found in serialized data! Struct: " + toString());
      parambe.j();
    }

    public void b(cy paramcy, be parambe)
      throws cf
    {
      parambe.j();
      paramcy.a(be.k());
      paramcy.a(be.l());
      paramcy.a(parambe.a);
      paramcy.c();
      paramcy.a(be.m());
      paramcy.a(parambe.b);
      paramcy.c();
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public be.a a()
    {
      return new be.a(null);
    }
  }

  private static class c extends dj<be>
  {
    public void a(cy paramcy, be parambe)
      throws cf
    {
      paramcy = (de)paramcy;
      paramcy.a(parambe.a);
      paramcy.a(parambe.b);
    }

    public void b(cy paramcy, be parambe)
      throws cf
    {
      paramcy = (de)paramcy;
      parambe.a = paramcy.w();
      parambe.a(true);
      parambe.b = paramcy.w();
      parambe.b(true);
    }
  }

  private static class d
    implements dh
  {
    public be.c a()
    {
      return new be.c(null);
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
 * Qualified Name:     u.aly.be
 * JD-Core Version:    0.6.2
 */