package fm.qingting.utils;

import java.util.List;

public class ListUtils
{
  public static List<Object> convertToObjectList(List<?> paramList)
  {
    try
    {
      paramList = (List)paramList;
      return paramList;
    }
    catch (Exception paramList)
    {
      paramList.printStackTrace();
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.ListUtils
 * JD-Core Version:    0.6.2
 */