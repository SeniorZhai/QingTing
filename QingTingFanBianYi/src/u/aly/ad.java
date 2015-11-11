package u.aly;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ad extends ao
  implements p
{
  public ad(String paramString, Map<String, Object> paramMap)
  {
    a(paramString);
    b(System.currentTimeMillis());
    if (paramMap.size() > 0)
      a(b(paramMap));
    if (this.d > 0);
    for (int i = this.d; ; i = 1)
    {
      a(i);
      return;
    }
  }

  private HashMap<String, az> b(Map<String, Object> paramMap)
  {
    paramMap = paramMap.entrySet().iterator();
    HashMap localHashMap = new HashMap();
    int i = 0;
    label208: 
    while ((i < 10) && (paramMap.hasNext()))
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      az localaz = new az();
      Object localObject = localEntry.getValue();
      if ((localObject instanceof String))
        localaz.b((String)localObject);
      while (true)
      {
        if (!localaz.k())
          break label208;
        localHashMap.put(localEntry.getKey(), localaz);
        i += 1;
        break;
        if ((localObject instanceof Long))
          localaz.b(((Long)localObject).longValue());
        else if ((localObject instanceof Integer))
          localaz.b(((Integer)localObject).longValue());
        else if ((localObject instanceof Float))
          localaz.b(((Float)localObject).longValue());
        else if ((localObject instanceof Double))
          localaz.b(((Double)localObject).longValue());
      }
    }
    return localHashMap;
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
      ((av)localObject).b(this);
      return;
      localav = null;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.ad
 * JD-Core Version:    0.6.2
 */