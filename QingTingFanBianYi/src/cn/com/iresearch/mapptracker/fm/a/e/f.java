package cn.com.iresearch.mapptracker.fm.a.e;

import cn.com.iresearch.mapptracker.fm.a.b.b;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public final class f
{
  private static final HashMap<String, f> g = new HashMap();
  public final LinkedHashMap<String, e> a = new LinkedHashMap();
  public final HashMap<String, c> b = new HashMap();
  private String c;
  private a d;
  private HashMap<String, d> e = new HashMap();
  private boolean f;

  public static f a(Class<?> paramClass)
  {
    if (paramClass == null)
      throw new b("table info get error,because the clazz is null");
    f localf = (f)g.get(paramClass.getName());
    Object localObject1 = localf;
    Object localObject2;
    if (localf == null)
    {
      localf = new f();
      localObject1 = (cn.com.iresearch.mapptracker.fm.a.a.a.e)paramClass.getAnnotation(cn.com.iresearch.mapptracker.fm.a.a.a.e.class);
      if ((localObject1 != null) && (((cn.com.iresearch.mapptracker.fm.a.a.a.e)localObject1).a().trim().length() != 0))
        break label279;
      localObject1 = paramClass.getName().replace('.', '_');
      localf.c = ((String)localObject1);
      paramClass.getName();
      localObject1 = cn.com.iresearch.mapptracker.fm.dao.a.getPrimaryKeyField(paramClass);
      if (localObject1 == null)
        break label289;
      localObject2 = new a();
      ((a)localObject2).a(cn.com.iresearch.mapptracker.fm.a.c.a.a((Field)localObject1));
      ((Field)localObject1).getName();
      a.b();
      ((a)localObject2).b(cn.com.iresearch.mapptracker.fm.a.c.a.b(paramClass, (Field)localObject1));
      ((a)localObject2).a(cn.com.iresearch.mapptracker.fm.a.c.a.a(paramClass, (Field)localObject1));
      ((a)localObject2).a(((Field)localObject1).getType());
      localf.d = ((a)localObject2);
      localObject1 = cn.com.iresearch.mapptracker.fm.dao.a.getPropertyList(paramClass);
      if (localObject1 != null)
      {
        localObject1 = ((List)localObject1).iterator();
        label173: if (((Iterator)localObject1).hasNext())
          break label318;
      }
      localObject1 = cn.com.iresearch.mapptracker.fm.dao.a.getManyToOneList(paramClass);
      if (localObject1 != null)
      {
        localObject1 = ((List)localObject1).iterator();
        label198: if (((Iterator)localObject1).hasNext())
          break label348;
      }
      localObject1 = cn.com.iresearch.mapptracker.fm.dao.a.getOneToManyList(paramClass);
      if (localObject1 != null)
        localObject1 = ((List)localObject1).iterator();
    }
    while (true)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        g.put(paramClass.getName(), localf);
        localObject1 = localf;
        if (localObject1 != null)
          break label408;
        throw new b("the class[" + paramClass + "]'s table is null");
        label279: localObject1 = ((cn.com.iresearch.mapptracker.fm.a.a.a.e)localObject1).a();
        break;
        label289: throw new b("the class[" + paramClass + "]'s idField is null");
        label318: localObject2 = (e)((Iterator)localObject1).next();
        if (localObject2 == null)
          break label173;
        localf.a.put(((e)localObject2).c(), localObject2);
        break label173;
        label348: localObject2 = (c)((Iterator)localObject1).next();
        if (localObject2 == null)
          break label198;
        localf.b.put(((c)localObject2).c(), localObject2);
        break label198;
      }
      localObject2 = (d)((Iterator)localObject1).next();
      if (localObject2 != null)
        localf.e.put(((d)localObject2).c(), localObject2);
    }
    label408: return localObject1;
  }

  public final String a()
  {
    return this.c;
  }

  public final a b()
  {
    return this.d;
  }

  public final boolean c()
  {
    return this.f;
  }

  public final void d()
  {
    this.f = true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.a.e.f
 * JD-Core Version:    0.6.2
 */