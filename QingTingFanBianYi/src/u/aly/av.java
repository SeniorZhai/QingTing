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

public class av
  implements Serializable, Cloneable, bz<av, e>
{
  public static final Map<e, cl> e;
  private static final dd f = new dd("InstantMsg");
  private static final ct g = new ct("id", (byte)11, (short)1);
  private static final ct h = new ct("errors", (byte)15, (short)2);
  private static final ct i = new ct("events", (byte)15, (short)3);
  private static final ct j = new ct("game_events", (byte)15, (short)4);
  private static final Map<Class<? extends dg>, dh> k = new HashMap();
  public String a;
  public List<am> b;
  public List<ao> c;
  public List<ao> d;
  private e[] l = { e.b, e.c, e.d };

  static
  {
    k.put(di.class, new b(null));
    k.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("id", (byte)1, new cm((byte)11)));
    localEnumMap.put(e.b, new cl("errors", (byte)2, new cn((byte)15, new cq((byte)12, am.class))));
    localEnumMap.put(e.c, new cl("events", (byte)2, new cn((byte)15, new cq((byte)12, ao.class))));
    localEnumMap.put(e.d, new cl("game_events", (byte)2, new cn((byte)15, new cq((byte)12, ao.class))));
    e = Collections.unmodifiableMap(localEnumMap);
    cl.a(av.class, e);
  }

  public av()
  {
  }

  public av(String paramString)
  {
    this();
    this.a = paramString;
  }

  public av(av paramav)
  {
    if (paramav.e())
      this.a = paramav.a;
    ArrayList localArrayList;
    Iterator localIterator;
    if (paramav.k())
    {
      localArrayList = new ArrayList();
      localIterator = paramav.b.iterator();
      while (localIterator.hasNext())
        localArrayList.add(new am((am)localIterator.next()));
      this.b = localArrayList;
    }
    if (paramav.p())
    {
      localArrayList = new ArrayList();
      localIterator = paramav.c.iterator();
      while (localIterator.hasNext())
        localArrayList.add(new ao((ao)localIterator.next()));
      this.c = localArrayList;
    }
    if (paramav.u())
    {
      localArrayList = new ArrayList();
      paramav = paramav.d.iterator();
      while (paramav.hasNext())
        localArrayList.add(new ao((ao)paramav.next()));
      this.d = localArrayList;
    }
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

  public av a()
  {
    return new av(this);
  }

  public av a(String paramString)
  {
    this.a = paramString;
    return this;
  }

  public av a(List<am> paramList)
  {
    this.b = paramList;
    return this;
  }

  public void a(am paramam)
  {
    if (this.b == null)
      this.b = new ArrayList();
    this.b.add(paramam);
  }

  public void a(ao paramao)
  {
    if (this.c == null)
      this.c = new ArrayList();
    this.c.add(paramao);
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

  public av b(List<ao> paramList)
  {
    this.c = paramList;
    return this;
  }

  public void b()
  {
    this.a = null;
    this.b = null;
    this.c = null;
    this.d = null;
  }

  public void b(ao paramao)
  {
    if (this.d == null)
      this.d = new ArrayList();
    this.d.add(paramao);
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

  public av c(List<ao> paramList)
  {
    this.d = paramList;
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
    if (!paramBoolean)
      this.d = null;
  }

  public boolean e()
  {
    return this.a != null;
  }

  public int f()
  {
    if (this.b == null)
      return 0;
    return this.b.size();
  }

  public Iterator<am> h()
  {
    if (this.b == null)
      return null;
    return this.b.iterator();
  }

  public List<am> i()
  {
    return this.b;
  }

  public void j()
  {
    this.b = null;
  }

  public boolean k()
  {
    return this.b != null;
  }

  public int l()
  {
    if (this.c == null)
      return 0;
    return this.c.size();
  }

  public Iterator<ao> m()
  {
    if (this.c == null)
      return null;
    return this.c.iterator();
  }

  public List<ao> n()
  {
    return this.c;
  }

  public void o()
  {
    this.c = null;
  }

  public boolean p()
  {
    return this.c != null;
  }

  public int q()
  {
    if (this.d == null)
      return 0;
    return this.d.size();
  }

  public Iterator<ao> r()
  {
    if (this.d == null)
      return null;
    return this.d.iterator();
  }

  public List<ao> s()
  {
    return this.d;
  }

  public void t()
  {
    this.d = null;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("InstantMsg(");
    localStringBuilder.append("id:");
    if (this.a == null)
    {
      localStringBuilder.append("null");
      if (k())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("errors:");
        if (this.b != null)
          break label173;
        localStringBuilder.append("null");
      }
      label72: if (p())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("events:");
        if (this.c != null)
          break label185;
        localStringBuilder.append("null");
      }
      label110: if (u())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("game_events:");
        if (this.d != null)
          break label197;
        localStringBuilder.append("null");
      }
    }
    while (true)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(this.a);
      break;
      label173: localStringBuilder.append(this.b);
      break label72;
      label185: localStringBuilder.append(this.c);
      break label110;
      label197: localStringBuilder.append(this.d);
    }
  }

  public boolean u()
  {
    return this.d != null;
  }

  public void v()
    throws cf
  {
    if (this.a == null)
      throw new cz("Required field 'id' was not present! Struct: " + toString());
  }

  private static class a extends di<av>
  {
    public void a(cy paramcy, av paramav)
      throws cf
    {
      paramcy.j();
      Object localObject1 = paramcy.l();
      if (((ct)localObject1).b == 0)
      {
        paramcy.k();
        paramav.v();
        return;
      }
      switch (((ct)localObject1).c)
      {
      default:
        db.a(paramcy, ((ct)localObject1).b);
      case 1:
      case 2:
      case 3:
      case 4:
      }
      while (true)
      {
        paramcy.m();
        break;
        if (((ct)localObject1).b == 11)
        {
          paramav.a = paramcy.z();
          paramav.a(true);
        }
        else
        {
          db.a(paramcy, ((ct)localObject1).b);
          continue;
          int i;
          Object localObject2;
          if (((ct)localObject1).b == 15)
          {
            localObject1 = paramcy.p();
            paramav.b = new ArrayList(((cu)localObject1).b);
            i = 0;
            while (i < ((cu)localObject1).b)
            {
              localObject2 = new am();
              ((am)localObject2).a(paramcy);
              paramav.b.add(localObject2);
              i += 1;
            }
            paramcy.q();
            paramav.b(true);
          }
          else
          {
            db.a(paramcy, ((ct)localObject1).b);
            continue;
            if (((ct)localObject1).b == 15)
            {
              localObject1 = paramcy.p();
              paramav.c = new ArrayList(((cu)localObject1).b);
              i = 0;
              while (i < ((cu)localObject1).b)
              {
                localObject2 = new ao();
                ((ao)localObject2).a(paramcy);
                paramav.c.add(localObject2);
                i += 1;
              }
              paramcy.q();
              paramav.c(true);
            }
            else
            {
              db.a(paramcy, ((ct)localObject1).b);
              continue;
              if (((ct)localObject1).b == 15)
              {
                localObject1 = paramcy.p();
                paramav.d = new ArrayList(((cu)localObject1).b);
                i = 0;
                while (i < ((cu)localObject1).b)
                {
                  localObject2 = new ao();
                  ((ao)localObject2).a(paramcy);
                  paramav.d.add(localObject2);
                  i += 1;
                }
                paramcy.q();
                paramav.d(true);
              }
              else
              {
                db.a(paramcy, ((ct)localObject1).b);
              }
            }
          }
        }
      }
    }

    public void b(cy paramcy, av paramav)
      throws cf
    {
      paramav.v();
      paramcy.a(av.w());
      if (paramav.a != null)
      {
        paramcy.a(av.x());
        paramcy.a(paramav.a);
        paramcy.c();
      }
      Iterator localIterator;
      if ((paramav.b != null) && (paramav.k()))
      {
        paramcy.a(av.y());
        paramcy.a(new cu((byte)12, paramav.b.size()));
        localIterator = paramav.b.iterator();
        while (localIterator.hasNext())
          ((am)localIterator.next()).b(paramcy);
        paramcy.f();
        paramcy.c();
      }
      if ((paramav.c != null) && (paramav.p()))
      {
        paramcy.a(av.z());
        paramcy.a(new cu((byte)12, paramav.c.size()));
        localIterator = paramav.c.iterator();
        while (localIterator.hasNext())
          ((ao)localIterator.next()).b(paramcy);
        paramcy.f();
        paramcy.c();
      }
      if ((paramav.d != null) && (paramav.u()))
      {
        paramcy.a(av.A());
        paramcy.a(new cu((byte)12, paramav.d.size()));
        paramav = paramav.d.iterator();
        while (paramav.hasNext())
          ((ao)paramav.next()).b(paramcy);
        paramcy.f();
        paramcy.c();
      }
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public av.a a()
    {
      return new av.a(null);
    }
  }

  private static class c extends dj<av>
  {
    public void a(cy paramcy, av paramav)
      throws cf
    {
      paramcy = (de)paramcy;
      paramcy.a(paramav.a);
      Object localObject = new BitSet();
      if (paramav.k())
        ((BitSet)localObject).set(0);
      if (paramav.p())
        ((BitSet)localObject).set(1);
      if (paramav.u())
        ((BitSet)localObject).set(2);
      paramcy.a((BitSet)localObject, 3);
      if (paramav.k())
      {
        paramcy.a(paramav.b.size());
        localObject = paramav.b.iterator();
        while (((Iterator)localObject).hasNext())
          ((am)((Iterator)localObject).next()).b(paramcy);
      }
      if (paramav.p())
      {
        paramcy.a(paramav.c.size());
        localObject = paramav.c.iterator();
        while (((Iterator)localObject).hasNext())
          ((ao)((Iterator)localObject).next()).b(paramcy);
      }
      if (paramav.u())
      {
        paramcy.a(paramav.d.size());
        paramav = paramav.d.iterator();
        while (paramav.hasNext())
          ((ao)paramav.next()).b(paramcy);
      }
    }

    public void b(cy paramcy, av paramav)
      throws cf
    {
      int j = 0;
      paramcy = (de)paramcy;
      paramav.a = paramcy.z();
      paramav.a(true);
      Object localObject1 = paramcy.b(3);
      Object localObject2;
      int i;
      Object localObject3;
      if (((BitSet)localObject1).get(0))
      {
        localObject2 = new cu((byte)12, paramcy.w());
        paramav.b = new ArrayList(((cu)localObject2).b);
        i = 0;
        while (i < ((cu)localObject2).b)
        {
          localObject3 = new am();
          ((am)localObject3).a(paramcy);
          paramav.b.add(localObject3);
          i += 1;
        }
        paramav.b(true);
      }
      if (((BitSet)localObject1).get(1))
      {
        localObject2 = new cu((byte)12, paramcy.w());
        paramav.c = new ArrayList(((cu)localObject2).b);
        i = 0;
        while (i < ((cu)localObject2).b)
        {
          localObject3 = new ao();
          ((ao)localObject3).a(paramcy);
          paramav.c.add(localObject3);
          i += 1;
        }
        paramav.c(true);
      }
      if (((BitSet)localObject1).get(2))
      {
        localObject1 = new cu((byte)12, paramcy.w());
        paramav.d = new ArrayList(((cu)localObject1).b);
        i = j;
        while (i < ((cu)localObject1).b)
        {
          localObject2 = new ao();
          ((ao)localObject2).a(paramcy);
          paramav.d.add(localObject2);
          i += 1;
        }
        paramav.d(true);
      }
    }
  }

  private static class d
    implements dh
  {
    public av.c a()
    {
      return new av.c(null);
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
 * Qualified Name:     u.aly.av
 * JD-Core Version:    0.6.2
 */