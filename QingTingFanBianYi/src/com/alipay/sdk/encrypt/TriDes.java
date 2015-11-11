package com.alipay.sdk.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class TriDes
{
  private static String a = "DESede/ECB/PKCS5Padding";

  public static String a(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new SecretKeySpec(paramString1.getBytes(), "DESede");
      Cipher localCipher = Cipher.getInstance(a);
      localCipher.init(1, paramString1);
      paramString1 = Base64.a(localCipher.doFinal(paramString2.getBytes()));
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }

  public static String b(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new SecretKeySpec(paramString1.getBytes(), "DESede");
      Cipher localCipher = Cipher.getInstance(a);
      localCipher.init(2, paramString1);
      paramString1 = new String(localCipher.doFinal(Base64.a(paramString2)));
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.encrypt.TriDes
 * JD-Core Version:    0.6.2
 */