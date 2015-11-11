package u.aly;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class d
{
  private final String a = "umeng_it.cache";
  private File b;
  private as c = null;
  private long d;
  private long e;
  private Set<a> f = new HashSet();

  public d(Context paramContext)
  {
    this.b = new File(paramContext.getFilesDir(), "umeng_it.cache");
    this.e = 86400000L;
  }

  private void a(as paramas)
  {
    if (paramas != null);
    try
    {
      try
      {
        paramas = new ci().a(paramas);
        if (paramas != null)
          bv.a(this.b, paramas);
        return;
      }
      finally
      {
      }
    }
    catch (Exception paramas)
    {
      paramas.printStackTrace();
    }
  }

  private void g()
  {
    as localas = new as();
    HashMap localHashMap = new HashMap();
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.f.iterator();
    while (localIterator.hasNext())
    {
      a locala = (a)localIterator.next();
      if (locala.c())
      {
        if (locala.d() != null)
          localHashMap.put(locala.b(), locala.d());
        if ((locala.e() != null) && (!locala.e().isEmpty()))
          localArrayList.addAll(locala.e());
      }
    }
    localas.a(localArrayList);
    localas.a(localHashMap);
    try
    {
      this.c = localas;
      return;
    }
    finally
    {
    }
  }

  // ERROR //
  private as h()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 45	u/aly/d:b	Ljava/io/File;
    //   4: invokevirtual 132	java/io/File:exists	()Z
    //   7: ifne +5 -> 12
    //   10: aconst_null
    //   11: areturn
    //   12: new 134	java/io/FileInputStream
    //   15: dup
    //   16: aload_0
    //   17: getfield 45	u/aly/d:b	Ljava/io/File;
    //   20: invokespecial 137	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   23: astore_2
    //   24: aload_2
    //   25: astore_1
    //   26: aload_2
    //   27: invokestatic 140	u/aly/bv:b	(Ljava/io/InputStream;)[B
    //   30: astore_3
    //   31: aload_2
    //   32: astore_1
    //   33: new 70	u/aly/as
    //   36: dup
    //   37: invokespecial 71	u/aly/as:<init>	()V
    //   40: astore 4
    //   42: aload_2
    //   43: astore_1
    //   44: new 142	u/aly/cc
    //   47: dup
    //   48: invokespecial 143	u/aly/cc:<init>	()V
    //   51: aload 4
    //   53: aload_3
    //   54: invokevirtual 146	u/aly/cc:a	(Lu/aly/bz;[B)V
    //   57: aload_2
    //   58: invokestatic 149	u/aly/bv:c	(Ljava/io/InputStream;)V
    //   61: aload 4
    //   63: areturn
    //   64: astore_3
    //   65: aconst_null
    //   66: astore_2
    //   67: aload_2
    //   68: astore_1
    //   69: aload_3
    //   70: invokevirtual 67	java/lang/Exception:printStackTrace	()V
    //   73: aload_2
    //   74: invokestatic 149	u/aly/bv:c	(Ljava/io/InputStream;)V
    //   77: aconst_null
    //   78: areturn
    //   79: astore_1
    //   80: aconst_null
    //   81: astore_3
    //   82: aload_1
    //   83: astore_2
    //   84: aload_3
    //   85: invokestatic 149	u/aly/bv:c	(Ljava/io/InputStream;)V
    //   88: aload_2
    //   89: athrow
    //   90: astore_2
    //   91: aload_1
    //   92: astore_3
    //   93: goto -9 -> 84
    //   96: astore_3
    //   97: goto -30 -> 67
    //
    // Exception table:
    //   from	to	target	type
    //   12	24	64	java/lang/Exception
    //   12	24	79	finally
    //   26	31	90	finally
    //   33	42	90	finally
    //   44	57	90	finally
    //   69	73	90	finally
    //   26	31	96	java/lang/Exception
    //   33	42	96	java/lang/Exception
    //   44	57	96	java/lang/Exception
  }

  public void a()
  {
    long l = System.currentTimeMillis();
    int i;
    if (l - this.d >= this.e)
    {
      Iterator localIterator = this.f.iterator();
      i = 0;
      while (localIterator.hasNext())
      {
        a locala = (a)localIterator.next();
        if (!locala.c())
        {
          i = 1;
        }
        else
        {
          if (!locala.a())
            break label94;
          i = 1;
        }
      }
    }
    label94: 
    while (true)
    {
      break;
      if (i != 0)
      {
        g();
        f();
      }
      this.d = l;
      return;
    }
  }

  public void a(long paramLong)
  {
    this.e = paramLong;
  }

  public void a(a parama)
  {
    this.f.add(parama);
  }

  public as b()
  {
    return this.c;
  }

  public String c()
  {
    return null;
  }

  public void d()
  {
    Iterator localIterator = this.f.iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      a locala = (a)localIterator.next();
      if (locala.c())
      {
        if ((locala.e() == null) || (locala.e().isEmpty()))
          break label84;
        locala.a(null);
        i = 1;
      }
    }
    label84: 
    while (true)
    {
      break;
      if (i != 0)
      {
        this.c.b(false);
        f();
      }
      return;
    }
  }

  public void e()
  {
    Object localObject2 = h();
    if (localObject2 == null)
      return;
    ArrayList localArrayList = new ArrayList(this.f.size());
    try
    {
      this.c = ((as)localObject2);
      localObject2 = this.f.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        a locala = (a)((Iterator)localObject2).next();
        locala.a(this.c);
        if (!locala.c())
          localArrayList.add(locala);
      }
    }
    finally
    {
    }
    Iterator localIterator = localObject1.iterator();
    while (localIterator.hasNext())
    {
      localObject2 = (a)localIterator.next();
      this.f.remove(localObject2);
    }
    g();
  }

  public void f()
  {
    if (this.c != null)
      a(this.c);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.d
 * JD-Core Version:    0.6.2
 */