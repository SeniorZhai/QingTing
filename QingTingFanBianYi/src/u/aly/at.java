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
import java.util.Map.Entry;
import java.util.Set;

public class at
  implements Serializable, Cloneable, bz<at, e>
{
  public static final Map<e, cl> d;
  private static final dd e = new dd("Imprint");
  private static final ct f = new ct("property", (byte)13, (short)1);
  private static final ct g = new ct("version", (byte)8, (short)2);
  private static final ct h = new ct("checksum", (byte)11, (short)3);
  private static final Map<Class<? extends dg>, dh> i = new HashMap();
  private static final int j = 0;
  public Map<String, au> a;
  public int b;
  public String c;
  private byte k = 0;

  static
  {
    i.put(di.class, new b(null));
    i.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("property", (byte)1, new co((byte)13, new cm((byte)11), new cq((byte)12, au.class))));
    localEnumMap.put(e.b, new cl("version", (byte)1, new cm((byte)8)));
    localEnumMap.put(e.c, new cl("checksum", (byte)1, new cm((byte)11)));
    d = Collections.unmodifiableMap(localEnumMap);
    cl.a(at.class, d);
  }

  public at()
  {
  }

  public at(Map<String, au> paramMap, int paramInt, String paramString)
  {
    this();
    this.a = paramMap;
    this.b = paramInt;
    b(true);
    this.c = paramString;
  }

  public at(at paramat)
  {
    this.k = paramat.k;
    if (paramat.f())
    {
      HashMap localHashMap = new HashMap();
      Iterator localIterator = paramat.a.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        localHashMap.put((String)localEntry.getKey(), new au((au)localEntry.getValue()));
      }
      this.a = localHashMap;
    }
    this.b = paramat.b;
    if (paramat.m())
      this.c = paramat.c;
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

  public at a()
  {
    return new at(this);
  }

  public at a(int paramInt)
  {
    this.b = paramInt;
    b(true);
    return this;
  }

  public at a(String paramString)
  {
    this.c = paramString;
    return this;
  }

  public at a(Map<String, au> paramMap)
  {
    this.a = paramMap;
    return this;
  }

  public void a(String paramString, au paramau)
  {
    if (this.a == null)
      this.a = new HashMap();
    this.a.put(paramString, paramau);
  }

  public void a(cy paramcy)
    throws cf
  {
    ((dh)i.get(paramcy.D())).b().b(paramcy, this);
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
    this.b = 0;
    this.c = null;
  }

  public void b(cy paramcy)
    throws cf
  {
    ((dh)i.get(paramcy.D())).b().a(paramcy, this);
  }

  public void b(boolean paramBoolean)
  {
    this.k = bw.a(this.k, 0, paramBoolean);
  }

  public int c()
  {
    if (this.a == null)
      return 0;
    return this.a.size();
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

  public Map<String, au> d()
  {
    return this.a;
  }

  public void e()
  {
    this.a = null;
  }

  public boolean f()
  {
    return this.a != null;
  }

  public int h()
  {
    return this.b;
  }

  public void i()
  {
    this.k = bw.b(this.k, 0);
  }

  public boolean j()
  {
    return bw.a(this.k, 0);
  }

  public String k()
  {
    return this.c;
  }

  public void l()
  {
    this.c = null;
  }

  public boolean m()
  {
    return this.c != null;
  }

  public void n()
    throws cf
  {
    if (this.a == null)
      throw new cz("Required field 'property' was not present! Struct: " + toString());
    if (this.c == null)
      throw new cz("Required field 'checksum' was not present! Struct: " + toString());
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Imprint(");
    localStringBuilder.append("property:");
    if (this.a == null)
    {
      localStringBuilder.append("null");
      localStringBuilder.append(", ");
      localStringBuilder.append("version:");
      localStringBuilder.append(this.b);
      localStringBuilder.append(", ");
      localStringBuilder.append("checksum:");
      if (this.c != null)
        break label115;
      localStringBuilder.append("null");
    }
    while (true)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(this.a);
      break;
      label115: localStringBuilder.append(this.c);
    }
  }

  private static class a extends di<at>
  {
    public void a(cy paramcy, at paramat)
      throws cf
    {
      paramcy.j();
      Object localObject = paramcy.l();
      if (((ct)localObject).b == 0)
      {
        paramcy.k();
        if (!paramat.j())
          throw new cz("Required field 'version' was not found in serialized data! Struct: " + toString());
      }
      else
      {
        switch (((ct)localObject).c)
        {
        default:
          db.a(paramcy, ((ct)localObject).b);
        case 1:
        case 2:
        case 3:
        }
        while (true)
        {
          paramcy.m();
          break;
          if (((ct)localObject).b == 13)
          {
            localObject = paramcy.n();
            paramat.a = new HashMap(((cv)localObject).c * 2);
            int i = 0;
            while (i < ((cv)localObject).c)
            {
              String str = paramcy.z();
              au localau = new au();
              localau.a(paramcy);
              paramat.a.put(str, localau);
              i += 1;
            }
            paramcy.o();
            paramat.a(true);
          }
          else
          {
            db.a(paramcy, ((ct)localObject).b);
            continue;
            if (((ct)localObject).b == 8)
            {
              paramat.b = paramcy.w();
              paramat.b(true);
            }
            else
            {
              db.a(paramcy, ((ct)localObject).b);
              continue;
              if (((ct)localObject).b == 11)
              {
                paramat.c = paramcy.z();
                paramat.c(true);
              }
              else
              {
                db.a(paramcy, ((ct)localObject).b);
              }
            }
          }
        }
      }
      paramat.n();
    }

    public void b(cy paramcy, at paramat)
      throws cf
    {
      paramat.n();
      paramcy.a(at.o());
      if (paramat.a != null)
      {
        paramcy.a(at.p());
        paramcy.a(new cv((byte)11, (byte)12, paramat.a.size()));
        Iterator localIterator = paramat.a.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          paramcy.a((String)localEntry.getKey());
          ((au)localEntry.getValue()).b(paramcy);
        }
        paramcy.e();
        paramcy.c();
      }
      paramcy.a(at.q());
      paramcy.a(paramat.b);
      paramcy.c();
      if (paramat.c != null)
      {
        paramcy.a(at.r());
        paramcy.a(paramat.c);
        paramcy.c();
      }
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public at.a a()
    {
      return new at.a(null);
    }
  }

  private static class c extends dj<at>
  {
    public void a(cy paramcy, at paramat)
      throws cf
    {
      paramcy = (de)paramcy;
      paramcy.a(paramat.a.size());
      Iterator localIterator = paramat.a.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        paramcy.a((String)localEntry.getKey());
        ((au)localEntry.getValue()).b(paramcy);
      }
      paramcy.a(paramat.b);
      paramcy.a(paramat.c);
    }

    public void b(cy paramcy, at paramat)
      throws cf
    {
      paramcy = (de)paramcy;
      cv localcv = new cv((byte)11, (byte)12, paramcy.w());
      paramat.a = new HashMap(localcv.c * 2);
      int i = 0;
      while (i < localcv.c)
      {
        String str = paramcy.z();
        au localau = new au();
        localau.a(paramcy);
        paramat.a.put(str, localau);
        i += 1;
      }
      paramat.a(true);
      paramat.b = paramcy.w();
      paramat.b(true);
      paramat.c = paramcy.z();
      paramat.c(true);
    }
  }

  private static class d
    implements dh
  {
    public at.c a()
    {
      return new at.c(null);
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
 * Qualified Name:     u.aly.at
 * JD-Core Version:    0.6.2
 */