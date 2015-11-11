package u.aly;

import android.content.Context;
import com.umeng.analytics.d;
import com.umeng.analytics.e;
import com.umeng.analytics.onlineconfig.a;
import com.umeng.analytics.onlineconfig.c;

public final class k
  implements o
{
  private static k c;
  private o a;
  private Context b;

  private k(Context paramContext)
  {
    this.b = paramContext.getApplicationContext();
    this.a = new j(this.b);
  }

  public static k a(Context paramContext)
  {
    try
    {
      if ((c == null) && (paramContext != null))
        c = new k(paramContext);
      paramContext = c;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  public void a()
  {
    d.b(new e()
    {
      public void a()
      {
        k.a(k.this).a();
      }
    });
  }

  public void a(a parama)
  {
    if ((parama != null) && (this.a != null))
      parama.a((c)this.a);
  }

  public void a(o paramo)
  {
    this.a = paramo;
  }

  public void a(final p paramp)
  {
    d.b(new e()
    {
      public void a()
      {
        k.a(k.this).a(paramp);
      }
    });
  }

  public void b()
  {
    d.b(new e()
    {
      public void a()
      {
        k.a(k.this).b();
      }
    });
  }

  public void b(p paramp)
  {
    this.a.b(paramp);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.k
 * JD-Core Version:    0.6.2
 */