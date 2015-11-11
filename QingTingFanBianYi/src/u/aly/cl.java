package u.aly;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class cl
  implements Serializable
{
  private static Map<Class<? extends bz>, Map<? extends cg, cl>> d = new HashMap();
  public final String a;
  public final byte b;
  public final cm c;

  public cl(String paramString, byte paramByte, cm paramcm)
  {
    this.a = paramString;
    this.b = paramByte;
    this.c = paramcm;
  }

  public static Map<? extends cg, cl> a(Class<? extends bz> paramClass)
  {
    if (!d.containsKey(paramClass));
    try
    {
      paramClass.newInstance();
      return (Map)d.get(paramClass);
    }
    catch (InstantiationException localInstantiationException)
    {
      throw new RuntimeException("InstantiationException for TBase class: " + paramClass.getName() + ", message: " + localInstantiationException.getMessage());
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new RuntimeException("IllegalAccessException for TBase class: " + paramClass.getName() + ", message: " + localIllegalAccessException.getMessage());
    }
  }

  public static void a(Class<? extends bz> paramClass, Map<? extends cg, cl> paramMap)
  {
    d.put(paramClass, paramMap);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.cl
 * JD-Core Version:    0.6.2
 */