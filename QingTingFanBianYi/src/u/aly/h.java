package u.aly;

import android.content.Context;

public class h
{
  private static d a = null;
  private static f b = null;

  public static d a(Context paramContext)
  {
    try
    {
      if (a == null)
      {
        a = new d(paramContext);
        a.a(new e(paramContext));
        a.a(new g(paramContext));
        a.a(new b(paramContext));
        a.a(new i(paramContext));
        a.e();
      }
      paramContext = a;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  public static f b(Context paramContext)
  {
    try
    {
      if (b == null)
      {
        b = new f(paramContext);
        b.b();
      }
      paramContext = b;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.h
 * JD-Core Version:    0.6.2
 */