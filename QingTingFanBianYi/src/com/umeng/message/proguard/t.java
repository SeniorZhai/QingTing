package com.umeng.message.proguard;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class t
{
  private static byte[] a = "uLi4/f4+Pb39.T19".getBytes();
  private static byte[] b = "nmeug.f9/Om+L823".getBytes();

  public static String a(String paramString1, String paramString2)
    throws Exception
  {
    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    localCipher.init(1, new SecretKeySpec(a, "AES"), new IvParameterSpec(b));
    return u.d(localCipher.doFinal(paramString1.getBytes(paramString2)));
  }

  public static String b(String paramString1, String paramString2)
    throws Exception
  {
    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    localCipher.init(2, new SecretKeySpec(a, "AES"), new IvParameterSpec(b));
    return new String(localCipher.doFinal(u.b(paramString1)), paramString2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.t
 * JD-Core Version:    0.6.2
 */