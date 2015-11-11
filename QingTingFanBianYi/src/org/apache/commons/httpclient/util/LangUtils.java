package org.apache.commons.httpclient.util;

public class LangUtils
{
  public static final int HASH_OFFSET = 37;
  public static final int HASH_SEED = 17;

  public static boolean equals(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == null)
      return paramObject2 == null;
    return paramObject1.equals(paramObject2);
  }

  public static int hashCode(int paramInt1, int paramInt2)
  {
    return paramInt1 * 37 + paramInt2;
  }

  public static int hashCode(int paramInt, Object paramObject)
  {
    if (paramObject != null);
    for (int i = paramObject.hashCode(); ; i = 0)
      return hashCode(paramInt, i);
  }

  public static int hashCode(int paramInt, boolean paramBoolean)
  {
    if (paramBoolean);
    for (int i = 1; ; i = 0)
      return hashCode(paramInt, i);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.LangUtils
 * JD-Core Version:    0.6.2
 */