package com.umeng.message.proguard;

public class f
{
  public static String a(Object paramObject)
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

  public static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.length() <= 0);
  }

  public static int b(String paramString)
  {
    int i = 0;
    int j = 0;
    if (paramString.length() > 0)
    {
      paramString = paramString.toCharArray();
      i = 0;
      while (j < paramString.length)
      {
        i = i * 31 + paramString[j];
        j += 1;
      }
    }
    return i;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.f
 * JD-Core Version:    0.6.2
 */