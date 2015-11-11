package com.taobao.newxp;

public enum a
{
  public static a a(String paramString)
  {
    a[] arrayOfa = values();
    int j = arrayOfa.length;
    int i = 0;
    while (i < j)
    {
      a locala = arrayOfa[i];
      if (locala.toString().equals(paramString))
        return locala;
      i += 1;
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.a
 * JD-Core Version:    0.6.2
 */