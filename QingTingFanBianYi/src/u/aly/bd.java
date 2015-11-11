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

public class bd
  implements Serializable, Cloneable, bz<bd, e>
{
  public static final Map<e, cl> h;
  private static final dd i = new dd("Session");
  private static final ct j = new ct("id", (byte)11, (short)1);
  private static final ct k = new ct("start_time", (byte)10, (short)2);
  private static final ct l = new ct("end_time", (byte)10, (short)3);
  private static final ct m = new ct("duration", (byte)10, (short)4);
  private static final ct n = new ct("pages", (byte)15, (short)5);
  private static final ct o = new ct("locations", (byte)15, (short)6);
  private static final ct p = new ct("traffic", (byte)12, (short)7);
  private static final Map<Class<? extends dg>, dh> q = new HashMap();
  private static final int r = 0;
  private static final int s = 1;
  private static final int t = 2;
  public String a;
  public long b;
  public long c;
  public long d;
  public List<ay> e;
  public List<aw> f;
  public be g;
  private byte u = 0;
  private e[] v = { e.e, e.f, e.g };

  static
  {
    q.put(di.class, new b(null));
    q.put(dj.class, new d(null));
    EnumMap localEnumMap = new EnumMap(e.class);
    localEnumMap.put(e.a, new cl("id", (byte)1, new cm((byte)11)));
    localEnumMap.put(e.b, new cl("start_time", (byte)1, new cm((byte)10)));
    localEnumMap.put(e.c, new cl("end_time", (byte)1, new cm((byte)10)));
    localEnumMap.put(e.d, new cl("duration", (byte)1, new cm((byte)10)));
    localEnumMap.put(e.e, new cl("pages", (byte)2, new cn((byte)15, new cq((byte)12, ay.class))));
    localEnumMap.put(e.f, new cl("locations", (byte)2, new cn((byte)15, new cq((byte)12, aw.class))));
    localEnumMap.put(e.g, new cl("traffic", (byte)2, new cq((byte)12, be.class)));
    h = Collections.unmodifiableMap(localEnumMap);
    cl.a(bd.class, h);
  }

  public bd()
  {
  }

  public bd(String paramString, long paramLong1, long paramLong2, long paramLong3)
  {
    this();
    this.a = paramString;
    this.b = paramLong1;
    b(true);
    this.c = paramLong2;
    c(true);
    this.d = paramLong3;
    d(true);
  }

  public bd(bd parambd)
  {
    this.u = parambd.u;
    if (parambd.e())
      this.a = parambd.a;
    this.b = parambd.b;
    this.c = parambd.c;
    this.d = parambd.d;
    ArrayList localArrayList;
    Iterator localIterator;
    if (parambd.t())
    {
      localArrayList = new ArrayList();
      localIterator = parambd.e.iterator();
      while (localIterator.hasNext())
        localArrayList.add(new ay((ay)localIterator.next()));
      this.e = localArrayList;
    }
    if (parambd.y())
    {
      localArrayList = new ArrayList();
      localIterator = parambd.f.iterator();
      while (localIterator.hasNext())
        localArrayList.add(new aw((aw)localIterator.next()));
      this.f = localArrayList;
    }
    if (parambd.B())
      this.g = new be(parambd.g);
  }

  private void a(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    try
    {
      this.u = 0;
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
    this.g = null;
  }

  public boolean B()
  {
    return this.g != null;
  }

  public void C()
    throws cf
  {
    if (this.a == null)
      throw new cz("Required field 'id' was not present! Struct: " + toString());
    if (this.g != null)
      this.g.j();
  }

  public e a(int paramInt)
  {
    return e.a(paramInt);
  }

  public bd a()
  {
    return new bd(this);
  }

  public bd a(long paramLong)
  {
    this.b = paramLong;
    b(true);
    return this;
  }

  public bd a(String paramString)
  {
    this.a = paramString;
    return this;
  }

  public bd a(List<ay> paramList)
  {
    this.e = paramList;
    return this;
  }

  public bd a(be parambe)
  {
    this.g = parambe;
    return this;
  }

  public void a(aw paramaw)
  {
    if (this.f == null)
      this.f = new ArrayList();
    this.f.add(paramaw);
  }

  public void a(ay paramay)
  {
    if (this.e == null)
      this.e = new ArrayList();
    this.e.add(paramay);
  }

  public void a(cy paramcy)
    throws cf
  {
    ((dh)q.get(paramcy.D())).b().b(paramcy, this);
  }

  public void a(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.a = null;
  }

  public bd b(long paramLong)
  {
    this.c = paramLong;
    c(true);
    return this;
  }

  public bd b(List<aw> paramList)
  {
    this.f = paramList;
    return this;
  }

  public void b()
  {
    this.a = null;
    b(false);
    this.b = 0L;
    c(false);
    this.c = 0L;
    d(false);
    this.d = 0L;
    this.e = null;
    this.f = null;
    this.g = null;
  }

  public void b(cy paramcy)
    throws cf
  {
    ((dh)q.get(paramcy.D())).b().a(paramcy, this);
  }

  public void b(boolean paramBoolean)
  {
    this.u = bw.a(this.u, 0, paramBoolean);
  }

  public String c()
  {
    return this.a;
  }

  public bd c(long paramLong)
  {
    this.d = paramLong;
    d(true);
    return this;
  }

  public void c(boolean paramBoolean)
  {
    this.u = bw.a(this.u, 1, paramBoolean);
  }

  public void d()
  {
    this.a = null;
  }

  public void d(boolean paramBoolean)
  {
    this.u = bw.a(this.u, 2, paramBoolean);
  }

  public void e(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.e = null;
  }

  public boolean e()
  {
    return this.a != null;
  }

  public long f()
  {
    return this.b;
  }

  public void f(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.f = null;
  }

  public void g(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.g = null;
  }

  public void h()
  {
    this.u = bw.b(this.u, 0);
  }

  public boolean i()
  {
    return bw.a(this.u, 0);
  }

  public long j()
  {
    return this.c;
  }

  public void k()
  {
    this.u = bw.b(this.u, 1);
  }

  public boolean l()
  {
    return bw.a(this.u, 1);
  }

  public long m()
  {
    return this.d;
  }

  public void n()
  {
    this.u = bw.b(this.u, 2);
  }

  public boolean o()
  {
    return bw.a(this.u, 2);
  }

  public int p()
  {
    if (this.e == null)
      return 0;
    return this.e.size();
  }

  public Iterator<ay> q()
  {
    if (this.e == null)
      return null;
    return this.e.iterator();
  }

  public List<ay> r()
  {
    return this.e;
  }

  public void s()
  {
    this.e = null;
  }

  public boolean t()
  {
    return this.e != null;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Session(");
    localStringBuilder.append("id:");
    if (this.a == null)
    {
      localStringBuilder.append("null");
      localStringBuilder.append(", ");
      localStringBuilder.append("start_time:");
      localStringBuilder.append(this.b);
      localStringBuilder.append(", ");
      localStringBuilder.append("end_time:");
      localStringBuilder.append(this.c);
      localStringBuilder.append(", ");
      localStringBuilder.append("duration:");
      localStringBuilder.append(this.d);
      if (t())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("pages:");
        if (this.e != null)
          break label248;
        localStringBuilder.append("null");
      }
      label147: if (y())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("locations:");
        if (this.f != null)
          break label260;
        localStringBuilder.append("null");
      }
      label185: if (B())
      {
        localStringBuilder.append(", ");
        localStringBuilder.append("traffic:");
        if (this.g != null)
          break label272;
        localStringBuilder.append("null");
      }
    }
    while (true)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(this.a);
      break;
      label248: localStringBuilder.append(this.e);
      break label147;
      label260: localStringBuilder.append(this.f);
      break label185;
      label272: localStringBuilder.append(this.g);
    }
  }

  public int u()
  {
    if (this.f == null)
      return 0;
    return this.f.size();
  }

  public Iterator<aw> v()
  {
    if (this.f == null)
      return null;
    return this.f.iterator();
  }

  public List<aw> w()
  {
    return this.f;
  }

  public void x()
  {
    this.f = null;
  }

  public boolean y()
  {
    return this.f != null;
  }

  public be z()
  {
    return this.g;
  }

  private static class a extends di<bd>
  {
    public void a(cy paramcy, bd parambd)
      throws cf
    {
      paramcy.j();
      Object localObject1 = paramcy.l();
      if (((ct)localObject1).b == 0)
      {
        paramcy.k();
        if (!parambd.i())
          throw new cz("Required field 'start_time' was not found in serialized data! Struct: " + toString());
      }
      else
      {
        switch (((ct)localObject1).c)
        {
        default:
          db.a(paramcy, ((ct)localObject1).b);
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        }
        while (true)
        {
          paramcy.m();
          break;
          if (((ct)localObject1).b == 11)
          {
            parambd.a = paramcy.z();
            parambd.a(true);
          }
          else
          {
            db.a(paramcy, ((ct)localObject1).b);
            continue;
            if (((ct)localObject1).b == 10)
            {
              parambd.b = paramcy.x();
              parambd.b(true);
            }
            else
            {
              db.a(paramcy, ((ct)localObject1).b);
              continue;
              if (((ct)localObject1).b == 10)
              {
                parambd.c = paramcy.x();
                parambd.c(true);
              }
              else
              {
                db.a(paramcy, ((ct)localObject1).b);
                continue;
                if (((ct)localObject1).b == 10)
                {
                  parambd.d = paramcy.x();
                  parambd.d(true);
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
                    parambd.e = new ArrayList(((cu)localObject1).b);
                    i = 0;
                    while (i < ((cu)localObject1).b)
                    {
                      localObject2 = new ay();
                      ((ay)localObject2).a(paramcy);
                      parambd.e.add(localObject2);
                      i += 1;
                    }
                    paramcy.q();
                    parambd.e(true);
                  }
                  else
                  {
                    db.a(paramcy, ((ct)localObject1).b);
                    continue;
                    if (((ct)localObject1).b == 15)
                    {
                      localObject1 = paramcy.p();
                      parambd.f = new ArrayList(((cu)localObject1).b);
                      i = 0;
                      while (i < ((cu)localObject1).b)
                      {
                        localObject2 = new aw();
                        ((aw)localObject2).a(paramcy);
                        parambd.f.add(localObject2);
                        i += 1;
                      }
                      paramcy.q();
                      parambd.f(true);
                    }
                    else
                    {
                      db.a(paramcy, ((ct)localObject1).b);
                      continue;
                      if (((ct)localObject1).b == 12)
                      {
                        parambd.g = new be();
                        parambd.g.a(paramcy);
                        parambd.g(true);
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
          }
        }
      }
      if (!parambd.l())
        throw new cz("Required field 'end_time' was not found in serialized data! Struct: " + toString());
      if (!parambd.o())
        throw new cz("Required field 'duration' was not found in serialized data! Struct: " + toString());
      parambd.C();
    }

    public void b(cy paramcy, bd parambd)
      throws cf
    {
      parambd.C();
      paramcy.a(bd.D());
      if (parambd.a != null)
      {
        paramcy.a(bd.E());
        paramcy.a(parambd.a);
        paramcy.c();
      }
      paramcy.a(bd.F());
      paramcy.a(parambd.b);
      paramcy.c();
      paramcy.a(bd.G());
      paramcy.a(parambd.c);
      paramcy.c();
      paramcy.a(bd.H());
      paramcy.a(parambd.d);
      paramcy.c();
      Iterator localIterator;
      if ((parambd.e != null) && (parambd.t()))
      {
        paramcy.a(bd.I());
        paramcy.a(new cu((byte)12, parambd.e.size()));
        localIterator = parambd.e.iterator();
        while (localIterator.hasNext())
          ((ay)localIterator.next()).b(paramcy);
        paramcy.f();
        paramcy.c();
      }
      if ((parambd.f != null) && (parambd.y()))
      {
        paramcy.a(bd.J());
        paramcy.a(new cu((byte)12, parambd.f.size()));
        localIterator = parambd.f.iterator();
        while (localIterator.hasNext())
          ((aw)localIterator.next()).b(paramcy);
        paramcy.f();
        paramcy.c();
      }
      if ((parambd.g != null) && (parambd.B()))
      {
        paramcy.a(bd.K());
        parambd.g.b(paramcy);
        paramcy.c();
      }
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public bd.a a()
    {
      return new bd.a(null);
    }
  }

  private static class c extends dj<bd>
  {
    public void a(cy paramcy, bd parambd)
      throws cf
    {
      paramcy = (de)paramcy;
      paramcy.a(parambd.a);
      paramcy.a(parambd.b);
      paramcy.a(parambd.c);
      paramcy.a(parambd.d);
      Object localObject = new BitSet();
      if (parambd.t())
        ((BitSet)localObject).set(0);
      if (parambd.y())
        ((BitSet)localObject).set(1);
      if (parambd.B())
        ((BitSet)localObject).set(2);
      paramcy.a((BitSet)localObject, 3);
      if (parambd.t())
      {
        paramcy.a(parambd.e.size());
        localObject = parambd.e.iterator();
        while (((Iterator)localObject).hasNext())
          ((ay)((Iterator)localObject).next()).b(paramcy);
      }
      if (parambd.y())
      {
        paramcy.a(parambd.f.size());
        localObject = parambd.f.iterator();
        while (((Iterator)localObject).hasNext())
          ((aw)((Iterator)localObject).next()).b(paramcy);
      }
      if (parambd.B())
        parambd.g.b(paramcy);
    }

    public void b(cy paramcy, bd parambd)
      throws cf
    {
      int j = 0;
      paramcy = (de)paramcy;
      parambd.a = paramcy.z();
      parambd.a(true);
      parambd.b = paramcy.x();
      parambd.b(true);
      parambd.c = paramcy.x();
      parambd.c(true);
      parambd.d = paramcy.x();
      parambd.d(true);
      BitSet localBitSet = paramcy.b(3);
      cu localcu;
      int i;
      Object localObject;
      if (localBitSet.get(0))
      {
        localcu = new cu((byte)12, paramcy.w());
        parambd.e = new ArrayList(localcu.b);
        i = 0;
        while (i < localcu.b)
        {
          localObject = new ay();
          ((ay)localObject).a(paramcy);
          parambd.e.add(localObject);
          i += 1;
        }
        parambd.e(true);
      }
      if (localBitSet.get(1))
      {
        localcu = new cu((byte)12, paramcy.w());
        parambd.f = new ArrayList(localcu.b);
        i = j;
        while (i < localcu.b)
        {
          localObject = new aw();
          ((aw)localObject).a(paramcy);
          parambd.f.add(localObject);
          i += 1;
        }
        parambd.f(true);
      }
      if (localBitSet.get(2))
      {
        parambd.g = new be();
        parambd.g.a(paramcy);
        parambd.g(true);
      }
    }
  }

  private static class d
    implements dh
  {
    public bd.c a()
    {
      return new bd.c(null);
    }
  }

  public static enum e
    implements cg
  {
    private static final Map<String, e> h;
    private final short i;
    private final String j;

    static
    {
      h = new HashMap();
      Iterator localIterator = EnumSet.allOf(e.class).iterator();
      while (localIterator.hasNext())
      {
        e locale = (e)localIterator.next();
        h.put(locale.b(), locale);
      }
    }

    private e(short paramShort, String paramString)
    {
      this.i = paramShort;
      this.j = paramString;
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
      }
      return g;
    }

    public static e a(String paramString)
    {
      return (e)h.get(paramString);
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
      return this.i;
    }

    public String b()
    {
      return this.j;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.bd
 * JD-Core Version:    0.6.2
 */