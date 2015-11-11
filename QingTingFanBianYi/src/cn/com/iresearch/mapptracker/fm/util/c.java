package cn.com.iresearch.mapptracker.fm.util;

import cn.com.iresearch.mapptracker.fm.base64.org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class c
{
  private static byte[] a = { 1, 2, 3, 4, 5, 6, 7, 8 };

  public static String a(String paramString1, String paramString2)
  {
    IvParameterSpec localIvParameterSpec = new IvParameterSpec(a);
    paramString1 = new SecretKeySpec(paramString1.getBytes(), "DES");
    Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    localCipher.init(1, paramString1, localIvParameterSpec);
    return Base64.encodeBase64URLSafeString(localCipher.doFinal(paramString2.getBytes()));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.util.c
 * JD-Core Version:    0.6.2
 */