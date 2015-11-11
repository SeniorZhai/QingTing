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
import java.util.Map.Entry;
import java.util.Set;

public class al
  implements Serializable, Cloneable, bz<al, e>
{
  public static final Map<e, cl> f;
  private static final dd g = new dd("Ekv");
  private static final ct h = new ct("ts", (byte)10, (short)1);
  private static final ct i = new ct("name", (byte)11, (short)2);
  private static final ct j = new ct("ckv", (byte)13, (short)3);
  private static final ct k = new ct("duration", (byte)10, (short)4);
  private static final ct l = new ct("acc", (byte)8, (short)5);
  private static final Map<Class<? extends dg>, dh> m = new HashMap();
  private static final int n = 0;
  private static final int o = 1;
  private static final int p = 2;
  public long a;
  public String b;
  public Map<String, String> c;
  public long d;
  public int e;
  private byte q = 0;
  private e[] r = { e.d, e.e };

  static
  {
    m.put(di.class, new b(null));
    m.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("ts", (byte)1, new cm((byte)10)));
    localEnumMap.put(e.b, new cl("name", (byte)1, new cm((byte)11)));
    localEnumMap.put(e.c, new cl("ckv", (byte)1, new co((byte)13, new cm((byte)11), new cm((byte)11))));
    localEnumMap.put(e.d, new cl("duration", (byte)2, new cm((byte)10)));
    localEnumMap.put(e.e, new cl("acc", (byte)2, new cm((byte)8)));
    f = Collections.unmodifiableMap(localEnumMap);
    cl.a(al.class, f);
  }

  public al()
  {
  }

  public al(long paramLong, String paramString, Map<String, String> paramMap)
  {
    this();
    this.a = paramLong;
    a(true);
    this.b = paramString;
    this.c = paramMap;
  }

  public al(al paramal)
  {
    this.q = paramal.q;
    this.a = paramal.a;
    if (paramal.i())
      this.b = paramal.b;
    if (paramal.m())
    {
      HashMap localHashMap = new HashMap();
      Iterator localIterator = paramal.c.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        localHashMap.put((String)localEntry.getKey(), (String)localEntry.getValue());
      }
      this.c = localHashMap;
    }
    this.d = paramal.d;
    this.e = paramal.e;
  }

  private void a(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    try
    {
      this.q = 0;
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

  public al a()
  {
    return new al(this);
  }

  public al a(int paramInt)
  {
    this.e = paramInt;
    e(true);
    return this;
  }

  public al a(long paramLong)
  {
    this.a = paramLong;
    a(true);
    return this;
  }

  public al a(String paramString)
  {
    this.b = paramString;
    return this;
  }

  public al a(Map<String, String> paramMap)
  {
    this.c = paramMap;
    return this;
  }

  public void a(String paramString1, String paramString2)
  {
    if (this.c == null)
      this.c = new HashMap();
    this.c.put(paramString1, paramString2);
  }

  public void a(cy paramcy)
    throws cf
  {
    ((dh)m.get(paramcy.D())).b().b(paramcy, this);
  }

  public void a(boolean paramBoolean)
  {
    this.q = bw.a(this.q, 0, paramBoolean);
  }

  public al b(long paramLong)
  {
    this.d = paramLong;
    d(true);
    return this;
  }

  public void b()
  {
    a(false);
    this.a = 0L;
    this.b = null;
    this.c = null;
    d(false);
    this.d = 0L;
    e(false);
    this.e = 0;
  }

  public void b(cy paramcy)
    throws cf
  {
    ((dh)m.get(paramcy.D())).b().a(paramcy, this);
  }

  public void b(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.b = null;
  }

  public long c()
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
    this.q = bw.b(this.q, 0);
  }

  public void d(boolean paramBoolean)
  {
    this.q = bw.a(this.q, 1, paramBoolean);
  }

  public void e(boolean paramBoolean)
  {
    this.q = bw.a(this.q, 2, paramBoolean);
  }

  public boolean e()
  {
    return bw.a(this.q, 0);
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

  public int j()
  {
    if (this.c == null)
      return 0;
    return this.c.size();
  }

  public Map<String, String> k()
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

  public long n()
  {
    return this.d;
  }

  public void o()
  {
    this.q = bw.b(this.q, 1);
  }

  public boolean p()
  {
    return bw.a(this.q, 1);
  }

  public int q()
  {
    return this.e;
  }

  public void r()
  {
    this.q = bw.b(this.q, 2);
  }

  public boolean s()
  {
    return bw.a(this.q, 2);
  }

  public void t()
    throws cf
  {
    if (this.b == null)
      throw new cz("Required field 'name' was not present! Struct: " + toString());
    if (this.c == null)
      throw new cz("Required field 'ckv' was not present! Struct: " + toString());
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Ekv(");
    localStringBuilder.append("ts:");
    localStringBuilder.append(this.a);
    localStringBuilder.append(", ");
    localStringBuilder.append("name:");
    if (this.b == null)
    {
      localStringBuilder.append("null");
      localStringBuilder.append(", ");
      localStringBuilder.append("ckv:");
      if (this.c != null)
        break label179;
      localStringBuilder.append("null");
    }
    while (true)
    {
      if (p())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("duration:");
        localStringBuilder.append(this.d);
      }
      if (s())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("acc:");
        localStringBuilder.append(this.e);
      }
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(this.b);
      break;
      label179: localStringBuilder.append(this.c);
    }
  }

  private static class a extends di<al>
  {
    public void a(cy paramcy, al paramal)
      throws cf
    {
      paramcy.j();
      Object localObject = paramcy.l();
      if (((ct)localObject).b == 0)
      {
        paramcy.k();
        if (!paramal.e())
          throw new cz("Required field 'ts' was not found in serialized data! Struct: " + toString());
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
        case 4:
        case 5:
        }
        while (true)
        {
          paramcy.m();
          break;
          if (((ct)localObject).b == 10)
          {
            paramal.a = paramcy.x();
            paramal.a(true);
          }
          else
          {
            db.a(paramcy, ((ct)localObject).b);
            continue;
            if (((ct)localObject).b == 11)
            {
              paramal.b = paramcy.z();
              paramal.b(true);
            }
            else
            {
              db.a(paramcy, ((ct)localObject).b);
              continue;
              if (((ct)localObject).b == 13)
              {
                localObject = paramcy.n();
                paramal.c = new HashMap(((cv)localObject).c * 2);
                int i = 0;
                while (i < ((cv)localObject).c)
                {
                  String str1 = paramcy.z();
                  String str2 = paramcy.z();
                  paramal.c.put(str1, str2);
                  i += 1;
                }
                paramcy.o();
                paramal.c(true);
              }
              else
              {
                db.a(paramcy, ((ct)localObject).b);
                continue;
                if (((ct)localObject).b == 10)
                {
                  paramal.d = paramcy.x();
                  paramal.d(true);
                }
                else
                {
                  db.a(paramcy, ((ct)localObject).b);
                  continue;
                  if (((ct)localObject).b == 8)
                  {
                    paramal.e = paramcy.w();
                    paramal.e(true);
                  }
                  else
                  {
                    db.a(paramcy, ((ct)localObject).b);
                  }
                }
              }
            }
          }
        }
      }
      paramal.t();
    }

    public void b(cy paramcy, al paramal)
      throws cf
    {
      paramal.t();
      paramcy.a(al.u());
      paramcy.a(al.v());
      paramcy.a(paramal.a);
      paramcy.c();
      if (paramal.b != null)
      {
        paramcy.a(al.w());
        paramcy.a(paramal.b);
        paramcy.c();
      }
      if (paramal.c != null)
      {
        paramcy.a(al.x());
        paramcy.a(new cv((byte)11, (byte)11, paramal.c.size()));
        Iterator localIterator = paramal.c.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          paramcy.a((String)localEntry.getKey());
          paramcy.a((String)localEntry.getValue());
        }
        paramcy.e();
        paramcy.c();
      }
      if (paramal.p())
      {
        paramcy.a(al.y());
        paramcy.a(paramal.d);
        paramcy.c();
      }
      if (paramal.s())
      {
        paramcy.a(al.z());
        paramcy.a(paramal.e);
        paramcy.c();
      }
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public al.a a()
    {
      return new al.a(null);
    }
  }

  private static class c extends dj<al>
  {
    public void a(cy paramcy, al paramal)
      throws cf
    {
      paramcy = (de)paramcy;
      paramcy.a(paramal.a);
      paramcy.a(paramal.b);
      paramcy.a(paramal.c.size());
      Object localObject = paramal.c.entrySet().iterator();
      while (((Iterator)localObject).hasNext())
      {
        Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
        paramcy.a((String)localEntry.getKey());
        paramcy.a((String)localEntry.getValue());
      }
      localObject = new BitSet();
      if (paramal.p())
        ((BitSet)localObject).set(0);
      if (paramal.s())
        ((BitSet)localObject).set(1);
      paramcy.a((BitSet)localObject, 2);
      if (paramal.p())
        paramcy.a(paramal.d);
      if (paramal.s())
        paramcy.a(paramal.e);
    }

    public void b(cy paramcy, al paramal)
      throws cf
    {
      paramcy = (de)paramcy;
      paramal.a = paramcy.x();
      paramal.a(true);
      paramal.b = paramcy.z();
      paramal.b(true);
      Object localObject = new cv((byte)11, (byte)11, paramcy.w());
      paramal.c = new HashMap(((cv)localObject).c * 2);
      int i = 0;
      while (i < ((cv)localObject).c)
      {
        String str1 = paramcy.z();
        String str2 = paramcy.z();
        paramal.c.put(str1, str2);
        i += 1;
      }
      paramal.c(true);
      localObject = paramcy.b(2);
      if (((BitSet)localObject).get(0))
      {
        paramal.d = paramcy.x();
        paramal.d(true);
      }
      if (((BitSet)localObject).get(1))
      {
        paramal.e = paramcy.w();
        paramal.e(true);
      }
    }
  }

  private static class d
    implements dh
  {
    public al.c a()
    {
      return new al.c(null);
    }
  }

  public static enum e
    implements cg
  {
    private static final Map<String, e> f;
    private final short g;
    private final String h;

    static
    {
      f = new HashMap();
      Iterator localIterator = EnumSet.allOf(e.class).iterator();
      while (localIterator.hasNext())
      {
        e locale = (e)localIterator.next();
        f.put(locale.b(), locale);
      }
    }

    private e(short paramShort, String paramString)
    {
      this.g = paramShort;
      this.h = paramString;
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
      }
      return e;
    }

    public static e a(String paramString)
    {
      return (e)f.get(paramString);
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
      return this.g;
    }

    public String b()
    {
      return this.h;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.al
 * JD-Core Version:    0.6.2
 */