package u.aly;

import android.content.Context;
import android.content.res.Resources;

public class bk
{
  private static final String a = bk.class.getName();
  private static bk b = null;
  private Resources c;
  private final String d;
  private final String e = "drawable";
  private final String f = "id";
  private final String g = "layout";
  private final String h = "anim";
  private final String i = "style";
  private final String j = "string";
  private final String k = "array";

  private bk(Context paramContext)
  {
    this.c = paramContext.getResources();
    this.d = paramContext.getPackageName();
  }

  private int a(String paramString1, String paramString2)
  {
    int n = this.c.getIdentifier(paramString1, paramString2, this.d);
    int m = n;
    if (n == 0)
    {
      bj.b(a, "getRes(" + paramString2 + "/ " + paramString1 + ")");
      bj.b(a, "Error getting resource. Make sure you have copied all resources (res/) from SDK to your project. ");
      m = 0;
    }
    return m;
  }

  public static bk a(Context paramContext)
  {
    try
    {
      if (b == null)
        b = new bk(paramContext.getApplicationContext());
      paramContext = b;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  public int a(String paramString)
  {
    return a(paramString, "anim");
  }

  public int b(String paramString)
  {
    return a(paramString, "id");
  }

  public int c(String paramString)
  {
    return a(paramString, "drawable");
  }

  public int d(String paramString)
  {
    return a(paramString, "layout");
  }

  public int e(String paramString)
  {
    return a(paramString, "style");
  }

  public int f(String paramString)
  {
    return a(paramString, "string");
  }

  public int g(String paramString)
  {
    return a(paramString, "array");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.bk
 * JD-Core Version:    0.6.2
 */