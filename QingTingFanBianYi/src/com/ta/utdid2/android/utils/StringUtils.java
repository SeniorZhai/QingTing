package com.ta.utdid2.android.utils;

public class StringUtils
{
  public static String convertObjectToString(Object paramObject)
  {
    if (paramObject != null)
    {
      if ((paramObject instanceof String))
        return ((String)paramObject).toString();
      if ((paramObject instanceof Integer))
        return "" + ((Integer)paramObject).intValue();
      if ((paramObject instanceof Long))
        return "" + ((Long)paramObject).longValue();
      if ((paramObject instanceof Double))
        return "" + ((Double)paramObject).doubleValue();
      if ((paramObject instanceof Float))
        return "" + ((Float)paramObject).floatValue();
      if ((paramObject instanceof Short))
        return "" + ((Short)paramObject).shortValue();
      if ((paramObject instanceof Byte))
        return "" + ((Byte)paramObject).byteValue();
      if ((paramObject instanceof Boolean))
        return ((Boolean)paramObject).toString();
      if ((paramObject instanceof Character))
        return ((Character)paramObject).toString();
      return paramObject.toString();
    }
    return "";
  }

  public static int hashCode(String paramString)
  {
    int j = 0;
    int i = 0;
    int k = j;
    if (0 == 0)
    {
      k = j;
      if (paramString.length() > 0)
      {
        paramString = paramString.toCharArray();
        j = 0;
        while (true)
        {
          k = i;
          if (j >= paramString.length)
            break;
          i = i * 31 + paramString[j];
          j += 1;
        }
      }
    }
    return k;
  }

  public static boolean isEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() <= 0);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.android.utils.StringUtils
 * JD-Core Version:    0.6.2
 */