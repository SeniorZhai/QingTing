package cn.com.iresearch.mapptracker.fm.util;

import cn.com.iresearch.mapptracker.fm.base64.org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class d
{
  private static IvParameterSpec a = new IvParameterSpec(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 });

  public static byte[] a(String paramString, byte[] paramArrayOfByte)
  {
    paramString = Base64.decodeBase64(paramString);
    paramArrayOfByte = new SecretKeySpec(paramArrayOfByte, "DES");
    Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    localCipher.init(2, paramArrayOfByte, a);
    return localCipher.doFinal(paramString);
  }

  public static byte[] a(byte[] paramArrayOfByte, String paramString)
  {
    paramString = new SecretKeySpec(paramString.getBytes(), "DES");
    Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    localCipher.init(1, paramString, a);
    return localCipher.doFinal(paramArrayOfByte);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.util.d
 * JD-Core Version:    0.6.2
 */