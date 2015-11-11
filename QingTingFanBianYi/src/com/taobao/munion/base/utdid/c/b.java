package com.taobao.munion.base.utdid.c;

import android.content.Context;
import com.taobao.munion.base.utdid.a.a.e;
import com.taobao.munion.base.utdid.a.a.f;
import java.util.zip.Adler32;

public class b
{
  static String a = "d6fc3a4a06adbde89223bvefedc24fecde188aaa9161";
  static final Object b = new Object();
  static final byte c = 1;
  private static a d = null;

  static long a(a parama)
  {
    if (parama != null)
    {
      parama = String.format("%s%s%s%s%s", new Object[] { parama.f(), parama.e(), Long.valueOf(parama.b()), parama.d(), parama.c() });
      if (!f.a(parama))
      {
        Adler32 localAdler32 = new Adler32();
        localAdler32.reset();
        localAdler32.update(parama.getBytes());
        return localAdler32.getValue();
      }
    }
    return 0L;
  }

  public static a a(Context paramContext)
  {
    while (true)
    {
      try
      {
        if (d != null)
        {
          paramContext = d;
          return paramContext;
        }
        if (paramContext != null)
        {
          paramContext = b(paramContext);
          d = paramContext;
          continue;
        }
      }
      finally
      {
      }
      paramContext = null;
    }
  }

  private static a b(Context paramContext)
  {
    if (paramContext != null)
    {
      new a();
      label128: 
      while (true)
        synchronized (b)
        {
          String str1 = d.a(paramContext).a();
          if (!f.a(str1))
          {
            if (!str1.endsWith("\n"))
              break label128;
            str1 = str1.substring(0, str1.length() - 1);
            a locala = new a();
            long l = System.currentTimeMillis();
            String str2 = e.a(paramContext);
            paramContext = e.b(paramContext);
            locala.c(str2);
            locala.a(str2);
            locala.b(l);
            locala.b(paramContext);
            locala.d(str1);
            locala.a(a(locala));
            return locala;
          }
        }
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.utdid.c.b
 * JD-Core Version:    0.6.2
 */