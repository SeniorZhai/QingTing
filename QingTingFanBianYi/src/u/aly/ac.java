package u.aly;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

public class ac extends am
  implements p
{
  public ac()
  {
    a(System.currentTimeMillis());
    a(an.a);
  }

  public ac(String paramString)
  {
    this();
    a(paramString);
  }

  public ac(Throwable paramThrowable)
  {
    this();
    a(a(paramThrowable));
  }

  private String a(Throwable paramThrowable)
  {
    Object localObject2 = null;
    if (paramThrowable == null)
      return null;
    Object localObject1 = localObject2;
    try
    {
      StringWriter localStringWriter = new StringWriter();
      localObject1 = localObject2;
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
      localObject1 = localObject2;
      paramThrowable.printStackTrace(localPrintWriter);
      localObject1 = localObject2;
      for (paramThrowable = paramThrowable.getCause(); paramThrowable != null; paramThrowable = paramThrowable.getCause())
      {
        localObject1 = localObject2;
        paramThrowable.printStackTrace(localPrintWriter);
        localObject1 = localObject2;
      }
      localObject1 = localObject2;
      paramThrowable = localStringWriter.toString();
      localObject1 = paramThrowable;
      localPrintWriter.close();
      localObject1 = paramThrowable;
      localStringWriter.close();
      return paramThrowable;
    }
    catch (Exception paramThrowable)
    {
      paramThrowable.printStackTrace();
    }
    return localObject1;
  }

  public ac a(boolean paramBoolean)
  {
    if (paramBoolean);
    for (an localan = an.a; ; localan = an.b)
    {
      a(localan);
      return this;
    }
  }

  public void a(bf parambf, String paramString)
  {
    Object localObject;
    av localav;
    if (parambf.s() > 0)
    {
      localObject = parambf.u().iterator();
      do
      {
        if (!((Iterator)localObject).hasNext())
          break;
        localav = (av)((Iterator)localObject).next();
      }
      while (!paramString.equals(localav.c()));
    }
    while (true)
    {
      localObject = localav;
      if (localav == null)
      {
        localObject = new av();
        ((av)localObject).a(paramString);
        parambf.a((av)localObject);
      }
      ((av)localObject).a(this);
      return;
      localav = null;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.ac
 * JD-Core Version:    0.6.2
 */