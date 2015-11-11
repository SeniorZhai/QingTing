package u.aly;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class as
  implements Serializable, Cloneable, bz<as, e>
{
  public static final Map<e, cl> d;
  private static final dd e = new dd("IdTracking");
  private static final ct f = new ct("snapshots", (byte)13, (short)1);
  private static final ct g = new ct("journals", (byte)15, (short)2);
  private static final ct h = new ct("checksum", (byte)11, (short)3);
  private static final Map<Class<? extends dg>, dh> i = new HashMap();
  public Map<String, ar> a;
  public List<aq> b;
  public String c;
  private e[] j = { e.b, e.c };

  static
  {
    i.put(di.class, new b(null));
    i.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("snapshots", (byte)1, new co((byte)13, new cm((byte)11), new cq((byte)12, ar.class))));
    localEnumMap.put(e.b, new cl("journals", (byte)2, new cn((byte)15, new cq((byte)12, aq.class))));
    localEnumMap.put(e.c, new cl("checksum", (byte)2, new cm((byte)11)));
    d = Collections.unmodifiableMap(localEnumMap);
    cl.a(as.class, d);
  }

  public as()
  {
  }

  public as(Map<String, ar> paramMap)
  {
    this();
    this.a = paramMap;
  }

  public as(as paramas)
  {
    Object localObject;
    Iterator localIterator;
    if (paramas.f())
    {
      localObject = new HashMap();
      localIterator = paramas.a.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        ((Map)localObject).put((String)localEntry.getKey(), new ar((ar)localEntry.getValue()));
      }
      this.a = ((Map)localObject);
    }
    if (paramas.l())
    {
      localObject = new ArrayList();
      localIterator = paramas.b.iterator();
      while (localIterator.hasNext())
        ((List)localObject).add(new aq((aq)localIterator.next()));
      this.b = ((List)localObject);
    }
    if (paramas.o())
      this.c = paramas.c;
  }

  private void a(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    try
    {
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

  public as a()
  {
    return new as(this);
  }

  public as a(String paramString)
  {
    this.c = paramString;
    return this;
  }

  public as a(List<aq> paramList)
  {
    this.b = paramList;
    return this;
  }

  public as a(Map<String, ar> paramMap)
  {
    this.a = paramMap;
    return this;
  }

  public void a(String paramString, ar paramar)
  {
    if (this.a == null)
      this.a = new HashMap();
    this.a.put(paramString, paramar);
  }

  public void a(aq paramaq)
  {
    if (this.b == null)
      this.b = new ArrayList();
    this.b.add(paramaq);
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
    this.b = null;
    this.c = null;
  }

  public void b(cy paramcy)
    throws cf
  {
    ((dh)i.get(paramcy.D())).b().a(paramcy, this);
  }

  public void b(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.b = null;
  }

  public int c()
  {
    if (this.a == null)
      return 0;
    return this.a.size();
  }

  public void c(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.c = null;
  }

  public Map<String, ar> d()
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
    if (this.b == null)
      return 0;
    return this.b.size();
  }

  public Iterator<aq> i()
  {
    if (this.b == null)
      return null;
    return this.b.iterator();
  }

  public List<aq> j()
  {
    return this.b;
  }

  public void k()
  {
    this.b = null;
  }

  public boolean l()
  {
    return this.b != null;
  }

  public String m()
  {
    return this.c;
  }

  public void n()
  {
    this.c = null;
  }

  public boolean o()
  {
    return this.c != null;
  }

  public void p()
    throws cf
  {
    if (this.a == null)
      throw new cz("Required field 'snapshots' was not present! Struct: " + toString());
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("IdTracking(");
    localStringBuilder.append("snapshots:");
    if (this.a == null)
    {
      localStringBuilder.append("null");
      if (l())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("journals:");
        if (this.b != null)
          break label135;
        localStringBuilder.append("null");
      }
      label72: if (o())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("checksum:");
        if (this.c != null)
          break label147;
        localStringBuilder.append("null");
      }
    }
    while (true)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(this.a);
      break;
      label135: localStringBuilder.append(this.b);
      break label72;
      label147: localStringBuilder.append(this.c);
    }
  }

  private static class a extends di<as>
  {
    public void a(cy paramcy, as paramas)
      throws cf
    {
      paramcy.j();
      Object localObject1 = paramcy.l();
      if (((ct)localObject1).b == 0)
      {
        paramcy.k();
        paramas.p();
        return;
      }
      switch (((ct)localObject1).c)
      {
      default:
        db.a(paramcy, ((ct)localObject1).b);
      case 1:
      case 2:
      case 3:
      }
      while (true)
      {
        paramcy.m();
        break;
        int i;
        Object localObject2;
        if (((ct)localObject1).b == 13)
        {
          localObject1 = paramcy.n();
          paramas.a = new HashMap(((cv)localObject1).c * 2);
          i = 0;
          while (i < ((cv)localObject1).c)
          {
            localObject2 = paramcy.z();
            ar localar = new ar();
            localar.a(paramcy);
            paramas.a.put(localObject2, localar);
            i += 1;
          }
          paramcy.o();
          paramas.a(true);
        }
        else
        {
          db.a(paramcy, ((ct)localObject1).b);
          continue;
          if (((ct)localObject1).b == 15)
          {
            localObject1 = paramcy.p();
            paramas.b = new ArrayList(((cu)localObject1).b);
            i = 0;
            while (i < ((cu)localObject1).b)
            {
              localObject2 = new aq();
              ((aq)localObject2).a(paramcy);
              paramas.b.add(localObject2);
              i += 1;
            }
            paramcy.q();
            paramas.b(true);
          }
          else
          {
            db.a(paramcy, ((ct)localObject1).b);
            continue;
            if (((ct)localObject1).b == 11)
            {
              paramas.c = paramcy.z();
              paramas.c(true);
            }
            else
            {
              db.a(paramcy, ((ct)localObject1).b);
            }
          }
        }
      }
    }

    public void b(cy paramcy, as paramas)
      throws cf
    {
      paramas.p();
      paramcy.a(as.q());
      Iterator localIterator;
      if (paramas.a != null)
      {
        paramcy.a(as.r());
        paramcy.a(new cv((byte)11, (byte)12, paramas.a.size()));
        localIterator = paramas.a.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          paramcy.a((String)localEntry.getKey());
          ((ar)localEntry.getValue()).b(paramcy);
        }
        paramcy.e();
        paramcy.c();
      }
      if ((paramas.b != null) && (paramas.l()))
      {
        paramcy.a(as.s());
        paramcy.a(new cu((byte)12, paramas.b.size()));
        localIterator = paramas.b.iterator();
        while (localIterator.hasNext())
          ((aq)localIterator.next()).b(paramcy);
        paramcy.f();
        paramcy.c();
      }
      if ((paramas.c != null) && (paramas.o()))
      {
        paramcy.a(as.t());
        paramcy.a(paramas.c);
        paramcy.c();
      }
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public as.a a()
    {
      return new as.a(null);
    }
  }

  private static class c extends dj<as>
  {
    public void a(cy paramcy, as paramas)
      throws cf
    {
      paramcy = (de)paramcy;
      paramcy.a(paramas.a.size());
      Object localObject = paramas.a.entrySet().iterator();
      while (((Iterator)localObject).hasNext())
      {
        Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
        paramcy.a((String)localEntry.getKey());
        ((ar)localEntry.getValue()).b(paramcy);
      }
      localObject = new BitSet();
      if (paramas.l())
        ((BitSet)localObject).set(0);
      if (paramas.o())
        ((BitSet)localObject).set(1);
      paramcy.a((BitSet)localObject, 2);
      if (paramas.l())
      {
        paramcy.a(paramas.b.size());
        localObject = paramas.b.iterator();
        while (((Iterator)localObject).hasNext())
          ((aq)((Iterator)localObject).next()).b(paramcy);
      }
      if (paramas.o())
        paramcy.a(paramas.c);
    }

    public void b(cy paramcy, as paramas)
      throws cf
    {
      int j = 0;
      paramcy = (de)paramcy;
      Object localObject1 = new cv((byte)11, (byte)12, paramcy.w());
      paramas.a = new HashMap(((cv)localObject1).c * 2);
      int i = 0;
      Object localObject2;
      Object localObject3;
      while (i < ((cv)localObject1).c)
      {
        localObject2 = paramcy.z();
        localObject3 = new ar();
        ((ar)localObject3).a(paramcy);
        paramas.a.put(localObject2, localObject3);
        i += 1;
      }
      paramas.a(true);
      localObject1 = paramcy.b(2);
      if (((BitSet)localObject1).get(0))
      {
        localObject2 = new cu((byte)12, paramcy.w());
        paramas.b = new ArrayList(((cu)localObject2).b);
        i = j;
        while (i < ((cu)localObject2).b)
        {
          localObject3 = new aq();
          ((aq)localObject3).a(paramcy);
          paramas.b.add(localObject3);
          i += 1;
        }
        paramas.b(true);
      }
      if (((BitSet)localObject1).get(1))
      {
        paramas.c = paramcy.z();
        paramas.c(true);
      }
    }
  }

  private static class d
    implements dh
  {
    public as.c a()
    {
      return new as.c(null);
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
 * Qualified Name:     u.aly.as
 * JD-Core Version:    0.6.2
 */