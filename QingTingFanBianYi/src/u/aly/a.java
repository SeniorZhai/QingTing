package u.aly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class a
{
  private final int a = 10;
  private final int b = 20;
  private final String c;
  private List<aq> d;
  private ar e;

  public a(String paramString)
  {
    this.c = paramString;
  }

  private boolean g()
  {
    boolean bool2 = false;
    Object localObject3 = this.e;
    Object localObject1;
    if (localObject3 == null)
    {
      localObject1 = null;
      if (localObject3 != null)
        break label204;
    }
    label204: for (int i = 0; ; i = ((ar)localObject3).j())
    {
      String str = a(f());
      boolean bool1 = bool2;
      if (str != null)
      {
        bool1 = bool2;
        if (!str.equals(localObject1))
        {
          Object localObject2 = localObject3;
          if (localObject3 == null)
            localObject2 = new ar();
          ((ar)localObject2).a(str);
          ((ar)localObject2).a(System.currentTimeMillis());
          ((ar)localObject2).a(i + 1);
          localObject3 = new aq();
          ((aq)localObject3).a(this.c);
          ((aq)localObject3).c(str);
          ((aq)localObject3).b((String)localObject1);
          ((aq)localObject3).a(((ar)localObject2).f());
          if (this.d == null)
            this.d = new ArrayList(2);
          this.d.add(localObject3);
          if (this.d.size() > 10)
            this.d.remove(0);
          this.e = ((ar)localObject2);
          bool1 = true;
        }
      }
      return bool1;
      localObject1 = ((ar)localObject3).c();
      break;
    }
  }

  public String a(String paramString)
  {
    if (paramString == null);
    do
    {
      return null;
      paramString = paramString.trim();
    }
    while ((paramString.length() == 0) || ("0".equals(paramString)) || ("unknown".equals(paramString.toLowerCase(Locale.US))));
    return paramString;
  }

  public void a(List<aq> paramList)
  {
    this.d = paramList;
  }

  public void a(ar paramar)
  {
    this.e = paramar;
  }

  public void a(as paramas)
  {
    this.e = ((ar)paramas.d().get("mName"));
    paramas = paramas.j();
    if ((paramas != null) && (paramas.size() > 0))
    {
      if (this.d == null)
        this.d = new ArrayList();
      paramas = paramas.iterator();
      while (paramas.hasNext())
      {
        aq localaq = (aq)paramas.next();
        if (this.c.equals(localaq.a))
          this.d.add(localaq);
      }
    }
  }

  public boolean a()
  {
    return g();
  }

  public String b()
  {
    return this.c;
  }

  public boolean c()
  {
    return (this.e == null) || (this.e.j() <= 20);
  }

  public ar d()
  {
    return this.e;
  }

  public List<aq> e()
  {
    return this.d;
  }

  public abstract String f();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.a
 * JD-Core Version:    0.6.2
 */