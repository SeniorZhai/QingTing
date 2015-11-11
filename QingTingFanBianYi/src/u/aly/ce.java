package u.aly;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ce
{
  public static cd a(Class<? extends cd> paramClass, int paramInt)
  {
    try
    {
      paramClass = (cd)paramClass.getMethod("findByValue", new Class[] { Integer.TYPE }).invoke(null, new Object[] { Integer.valueOf(paramInt) });
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
      return null;
    }
    catch (IllegalAccessException paramClass)
    {
      return null;
    }
    catch (InvocationTargetException paramClass)
    {
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.ce
 * JD-Core Version:    0.6.2
 */