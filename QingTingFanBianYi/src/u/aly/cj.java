package u.aly;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class cj<T extends cj<?, ?>, F extends cg>
  implements bz<T, F>
{
  private static final Map<Class<? extends dg>, dh> a = new HashMap();
  protected Object b;
  protected F c;

  static
  {
    a.put(di.class, new b(null));
    a.put(dj.class, new d(null));
  }

  protected cj()
  {
    this.c = null;
    this.b = null;
  }

  protected cj(F paramF, Object paramObject)
  {
    b(paramF, paramObject);
  }

  protected cj(cj<T, F> paramcj)
  {
    if (!paramcj.getClass().equals(getClass()))
      throw new ClassCastException();
    this.c = paramcj.c;
    this.b = a(paramcj.b);
  }

  private static Object a(Object paramObject)
  {
    Object localObject;
    if ((paramObject instanceof bz))
      localObject = ((bz)paramObject).g();
    do
    {
      return localObject;
      if ((paramObject instanceof ByteBuffer))
        return ca.d((ByteBuffer)paramObject);
      if ((paramObject instanceof List))
        return a((List)paramObject);
      if ((paramObject instanceof Set))
        return a((Set)paramObject);
      localObject = paramObject;
    }
    while (!(paramObject instanceof Map));
    return a((Map)paramObject);
  }

  private static List a(List paramList)
  {
    ArrayList localArrayList = new ArrayList(paramList.size());
    paramList = paramList.iterator();
    while (paramList.hasNext())
      localArrayList.add(a(paramList.next()));
    return localArrayList;
  }

  private static Map a(Map<Object, Object> paramMap)
  {
    HashMap localHashMap = new HashMap();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      localHashMap.put(a(localEntry.getKey()), a(localEntry.getValue()));
    }
    return localHashMap;
  }

  private static Set a(Set paramSet)
  {
    HashSet localHashSet = new HashSet();
    paramSet = paramSet.iterator();
    while (paramSet.hasNext())
      localHashSet.add(a(paramSet.next()));
    return localHashSet;
  }

  protected abstract Object a(cy paramcy, ct paramct)
    throws cf;

  protected abstract Object a(cy paramcy, short paramShort)
    throws cf;

  protected abstract ct a(F paramF);

  public void a(int paramInt, Object paramObject)
  {
    b(b((short)paramInt), paramObject);
  }

  protected abstract void a(F paramF, Object paramObject)
    throws ClassCastException;

  public void a(cy paramcy)
    throws cf
  {
    ((dh)a.get(paramcy.D())).b().b(paramcy, this);
  }

  public Object b(F paramF)
  {
    if (paramF != this.c)
      throw new IllegalArgumentException("Cannot get the value of field " + paramF + " because union's set field is " + this.c);
    return j();
  }

  protected abstract F b(short paramShort);

  public final void b()
  {
    this.c = null;
    this.b = null;
  }

  public void b(F paramF, Object paramObject)
  {
    a(paramF, paramObject);
    this.c = paramF;
    this.b = paramObject;
  }

  public void b(cy paramcy)
    throws cf
  {
    ((dh)a.get(paramcy.D())).b().a(paramcy, this);
  }

  public Object c(int paramInt)
  {
    return b(b((short)paramInt));
  }

  protected abstract dd c();

  protected abstract void c(cy paramcy)
    throws cf;

  public boolean c(F paramF)
  {
    return this.c == paramF;
  }

  protected abstract void d(cy paramcy)
    throws cf;

  public boolean d(int paramInt)
  {
    return c(b((short)paramInt));
  }

  public F i()
  {
    return this.c;
  }

  public Object j()
  {
    return this.b;
  }

  public boolean k()
  {
    return this.c != null;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<");
    localStringBuilder.append(getClass().getSimpleName());
    localStringBuilder.append(" ");
    Object localObject;
    if (i() != null)
    {
      localObject = j();
      localStringBuilder.append(a(i()).a);
      localStringBuilder.append(":");
      if (!(localObject instanceof ByteBuffer))
        break label96;
      ca.a((ByteBuffer)localObject, localStringBuilder);
    }
    while (true)
    {
      localStringBuilder.append(">");
      return localStringBuilder.toString();
      label96: localStringBuilder.append(localObject.toString());
    }
  }

  private static class a extends di<cj>
  {
    public void a(cy paramcy, cj paramcj)
      throws cf
    {
      paramcj.c = null;
      paramcj.b = null;
      paramcy.j();
      ct localct = paramcy.l();
      paramcj.b = paramcj.a(paramcy, localct);
      if (paramcj.b != null)
        paramcj.c = paramcj.b(localct.c);
      paramcy.m();
      paramcy.l();
      paramcy.k();
    }

    public void b(cy paramcy, cj paramcj)
      throws cf
    {
      if ((paramcj.i() == null) || (paramcj.j() == null))
        throw new cz("Cannot write a TUnion with no set value!");
      paramcy.a(paramcj.c());
      paramcy.a(paramcj.a(paramcj.c));
      paramcj.c(paramcy);
      paramcy.c();
      paramcy.d();
      paramcy.b();
    }
  }

  private static class b
    implements dh
  {
    public cj.a a()
    {
      return new cj.a(null);
    }
  }

  private static class c extends dj<cj>
  {
    public void a(cy paramcy, cj paramcj)
      throws cf
    {
      paramcj.c = null;
      paramcj.b = null;
      short s = paramcy.v();
      paramcj.b = paramcj.a(paramcy, s);
      if (paramcj.b != null)
        paramcj.c = paramcj.b(s);
    }

    public void b(cy paramcy, cj paramcj)
      throws cf
    {
      if ((paramcj.i() == null) || (paramcj.j() == null))
        throw new cz("Cannot write a TUnion with no set value!");
      paramcy.a(paramcj.c.a());
      paramcj.d(paramcy);
    }
  }

  private static class d
    implements dh
  {
    public cj.c a()
    {
      return new cj.c(null);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.cj
 * JD-Core Version:    0.6.2
 */