package fm.qingting.utils;

import fm.qingting.qtradio.model.Node;
import java.util.Collections;
import java.util.List;

public class FuncUtils
{
  public static void revertNodesList(Object paramObject)
  {
    if (paramObject == null);
    while (true)
    {
      return;
      List localList = (List)paramObject;
      if (localList.size() > 0)
      {
        Collections.reverse(localList);
        paramObject = (Node)localList.get(0);
        paramObject.prevSibling = null;
        int i = 1;
        while (i < localList.size())
        {
          paramObject.nextSibling = ((Node)localList.get(i));
          ((Node)localList.get(i)).prevSibling = paramObject;
          paramObject = (Node)localList.get(i);
          paramObject.nextSibling = null;
          i += 1;
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.FuncUtils
 * JD-Core Version:    0.6.2
 */