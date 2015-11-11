package cn.com.iresearch.mapptracker.fm.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import cn.com.iresearch.mapptracker.fm.base64.org.apache.commons.codec.binary.Base64;
import java.util.Random;

public final class e
{
  private static byte[] a = "Tvb!@#RS".getBytes();

  private static String a()
  {
    int i = 0;
    char[] arrayOfChar = new char[36];
    arrayOfChar[0] = 97;
    arrayOfChar[1] = 98;
    arrayOfChar[2] = 99;
    arrayOfChar[3] = 100;
    arrayOfChar[4] = 101;
    arrayOfChar[5] = 102;
    arrayOfChar[6] = 103;
    arrayOfChar[7] = 104;
    arrayOfChar[8] = 105;
    arrayOfChar[9] = 106;
    arrayOfChar[10] = 107;
    arrayOfChar[11] = 108;
    arrayOfChar[12] = 109;
    arrayOfChar[13] = 110;
    arrayOfChar[14] = 111;
    arrayOfChar[15] = 112;
    arrayOfChar[16] = 113;
    arrayOfChar[17] = 114;
    arrayOfChar[18] = 115;
    arrayOfChar[19] = 116;
    arrayOfChar[20] = 117;
    arrayOfChar[21] = 118;
    arrayOfChar[22] = 119;
    arrayOfChar[23] = 120;
    arrayOfChar[24] = 121;
    arrayOfChar[25] = 122;
    arrayOfChar[26] = 48;
    arrayOfChar[27] = 49;
    arrayOfChar[28] = 50;
    arrayOfChar[29] = 51;
    arrayOfChar[30] = 52;
    arrayOfChar[31] = 53;
    arrayOfChar[32] = 54;
    arrayOfChar[33] = 55;
    arrayOfChar[34] = 56;
    arrayOfChar[35] = 57;
    arrayOfChar;
    StringBuffer localStringBuffer = new StringBuffer("");
    Random localRandom = new Random();
    while (true)
    {
      if (i >= 8)
        return localStringBuffer.toString();
      int j = Math.abs(localRandom.nextInt(36));
      if ((j >= 0) && (j < arrayOfChar.length))
      {
        localStringBuffer.append(arrayOfChar[j]);
        i += 1;
      }
    }
  }

  public static String a(Context paramContext, String paramString)
  {
    int i = 0;
    String str2 = "";
    String str1 = str2;
    while (true)
    {
      Object localObject;
      try
      {
        localObject = paramContext.getSharedPreferences("MATSharedPreferences", 0);
        paramContext = "yoT2EXOyrvuPcbkUpAeYpkVxrBIBKhHJRg3s_hAayePTDtJCz-MDs1NF-54_jLQds4_jvB809t4130oO2yZIeOCCyDTaKO3gi0ZxjviiWtgHz_OO6knr7e29JNi7_IYZG8Iz21UByMdkiPeGU0XeS5XAqgrsDs_yY8UQvv6wvz_VYCq50zpIvlMOlucqgLwweZlbryAz8GXR6uAzsRowCj_Ms236MbzQ";
        str1 = str2;
        try
        {
          String str3 = DataProvider.pd();
          paramContext = str3;
          str1 = str2;
          str3 = ((SharedPreferences)localObject).getString("Pd", paramContext);
          str1 = str2;
          localObject = new StringBuffer();
          str1 = str2;
          paramContext = str2;
          if (TextUtils.isEmpty(str3))
            break;
          str1 = str2;
          paramContext = a();
          str1 = str2;
          char c = (char)d.a(str3, a)[0];
          str1 = str2;
          ((StringBuffer)localObject).append(paramContext.substring(0, 3) + c + paramContext.substring(3));
          str1 = str2;
          int j = paramString.length();
          str1 = str2;
          int k = j / 400;
          if (i >= k)
          {
            str1 = str2;
            ((StringBuffer)localObject).append(Base64.encodeBase64URLSafeString(d.a(paramString.substring(j - j % 400).getBytes(), paramContext)));
            str1 = str2;
            paramString = ((StringBuffer)localObject).toString();
            str1 = paramString;
            paramContext = paramString;
            if (!paramString.endsWith("$"))
              break;
            str1 = paramString;
            return paramString.substring(0, paramString.lastIndexOf("$"));
          }
        }
        catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
        {
          str1 = str2;
          localUnsatisfiedLinkError.printStackTrace();
          continue;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return str1;
      }
      str1 = str2;
      ((StringBuffer)localObject).append(Base64.encodeBase64URLSafeString(d.a(paramString.substring(i * 400, (i + 1) * 400).getBytes(), paramContext))).append("$");
      i += 1;
    }
    return paramContext;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.util.e
 * JD-Core Version:    0.6.2
 */