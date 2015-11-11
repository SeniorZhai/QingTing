package com.umeng.message.proguard;

import android.content.Context;
import java.util.zip.Adler32;

public class n
{
  static String a = "d6fc3a4a06adbde89223bvefedc24fecde188aaa9161";
  static final Object b = new Object();
  static final byte c = 1;
  private static m d = null;

  static long a(m paramm)
  {
    if (paramm != null)
    {
      paramm = String.format("%s%s%s%s%s", new Object[] { paramm.f(), paramm.e(), Long.valueOf(paramm.b()), paramm.d(), paramm.c() });
      if (!f.a(paramm))
      {
        Adler32 localAdler32 = new Adler32();
        localAdler32.reset();
        localAdler32.update(paramm.getBytes());
        return localAdler32.getValue();
      }
    }
    return 0L;
  }

  public static m a(Context paramContext)
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

  private static m b(Context paramContext)
  {
    if (paramContext != null)
    {
      new m();
      label128: 
      while (true)
        synchronized (b)
        {
          String str1 = p.a(paramContext).a();
          if (!f.a(str1))
          {
            if (!str1.endsWith("\n"))
              break label128;
            str1 = str1.substring(0, str1.length() - 1);
            m localm = new m();
            long l = System.currentTimeMillis();
            String str2 = e.a(paramContext);
            paramContext = e.b(paramContext);
            localm.c(str2);
            localm.a(str2);
            localm.b(l);
            localm.b(paramContext);
            localm.d(str1);
            localm.a(a(localm));
            return localm;
          }
        }
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.n
 * JD-Core Version:    0.6.2
 */