package com.tencent.b.c;

import java.io.File;

public class a
{
  public static boolean a(File paramFile)
  {
    int i = 0;
    if (paramFile != null)
    {
      if (!paramFile.isFile())
        break label28;
      if (paramFile.delete())
        break label26;
      paramFile.deleteOnExit();
    }
    label26: label28: 
    while (!paramFile.isDirectory())
    {
      return false;
      return true;
    }
    File[] arrayOfFile = paramFile.listFiles();
    int j = arrayOfFile.length;
    while (i < j)
    {
      a(arrayOfFile[i]);
      i += 1;
    }
    return paramFile.delete();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.b.c.a
 * JD-Core Version:    0.6.2
 */