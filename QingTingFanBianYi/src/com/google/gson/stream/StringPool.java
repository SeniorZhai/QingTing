package com.google.gson.stream;

final class StringPool
{
  private final String[] pool = new String[512];

  public String get(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int j = 0;
    int i = paramInt1;
    while (i < paramInt1 + paramInt2)
    {
      j = j * 31 + paramArrayOfChar[i];
      i += 1;
    }
    i = j ^ (j >>> 20 ^ j >>> 12);
    j = (i ^ (i >>> 7 ^ i >>> 4)) & this.pool.length - 1;
    String str = this.pool[j];
    if ((str == null) || (str.length() != paramInt2))
    {
      paramArrayOfChar = new String(paramArrayOfChar, paramInt1, paramInt2);
      this.pool[j] = paramArrayOfChar;
      return paramArrayOfChar;
    }
    i = 0;
    while (i < paramInt2)
    {
      if (str.charAt(i) != paramArrayOfChar[(paramInt1 + i)])
      {
        paramArrayOfChar = new String(paramArrayOfChar, paramInt1, paramInt2);
        this.pool[j] = paramArrayOfChar;
        return paramArrayOfChar;
      }
      i += 1;
    }
    return str;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.gson.stream.StringPool
 * JD-Core Version:    0.6.2
 */