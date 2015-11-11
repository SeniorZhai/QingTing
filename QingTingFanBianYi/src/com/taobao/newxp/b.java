package com.taobao.newxp;

import com.taobao.newxp.common.Category.a;
import java.util.Set;

public enum b
{
  public static b a(String paramString)
  {
    try
    {
      paramString = paramString.split("\\.")[0];
      b[] arrayOfb = values();
      int j = arrayOfb.length;
      int i = 0;
      while (i < j)
      {
        b localb = arrayOfb[i];
        boolean bool = localb.toString().equals(paramString);
        if (bool)
          return localb;
        i += 1;
      }
    }
    catch (Exception paramString)
    {
    }
    return null;
  }

  public static b a(String paramString, Set<Category.a> paramSet)
  {
    int i = 1;
    try
    {
      String[] arrayOfString = paramString.split("\\.");
      paramString = arrayOfString[0];
      if ((arrayOfString.length > 1) && (paramSet != null))
        while (i < arrayOfString.length)
        {
          paramSet.add(Category.a.a(arrayOfString[i]));
          i += 1;
        }
      paramSet = values();
      int j = paramSet.length;
      i = 0;
      while (i < j)
      {
        arrayOfString = paramSet[i];
        boolean bool = arrayOfString.toString().equals(paramString);
        if (bool)
          return arrayOfString;
        i += 1;
      }
    }
    catch (Exception paramString)
    {
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.b
 * JD-Core Version:    0.6.2
 */