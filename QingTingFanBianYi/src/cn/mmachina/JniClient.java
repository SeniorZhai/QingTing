package cn.mmachina;

import android.content.Context;

public class JniClient
{
  public static int version = 1;

  static
  {
    System.loadLibrary("MMANDKSignature");
  }

  public static native String MDString(String paramString1, Context paramContext, String paramString2);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.mmachina.JniClient
 * JD-Core Version:    0.6.2
 */