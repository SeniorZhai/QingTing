package cn.com.iresearch.mapptracker.fm.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class a
{
  private static List<String> a;

  static
  {
    ArrayList localArrayList = new ArrayList();
    a = localArrayList;
    localArrayList.add("ISO-8859-1");
    a.add("GB2312");
    a.add("GBK");
    a.add("GB18030");
    a.add("US-ASCII");
    a.add("ASCII");
    a.add("ISO-2022-KR");
    a.add("ISO-8859-2");
    a.add("ISO-2022-JP");
    a.add("ISO-2022-JP-2");
    a.add("UTF-8");
  }

  public static String a(String paramString1, String paramString2, int paramInt)
  {
    Object localObject = "ISO-8859-1";
    try
    {
      Iterator localIterator = a.iterator();
      if (!localIterator.hasNext());
      while (true)
      {
        return new String(paramString1.getBytes((String)localObject), paramString2);
        String str = (String)localIterator.next();
        boolean bool = b(paramString1, str, paramInt);
        if (!bool)
          break;
        localObject = str;
      }
    }
    catch (Throwable paramString2)
    {
    }
    return paramString1;
  }

  private static boolean b(String paramString1, String paramString2, int paramInt)
  {
    String str = paramString1;
    try
    {
      if (paramString1.length() > paramInt)
        str = paramString1.substring(0, paramInt);
      boolean bool = str.equals(new String(str.getBytes(paramString2), paramString2));
      return bool;
    }
    catch (Throwable paramString1)
    {
    }
    return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.util.a
 * JD-Core Version:    0.6.2
 */