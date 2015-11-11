package cn.wemart.sdk.app.pay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PayUtils
{
  public static String getPayInfo(String paramString)
  {
    int i = paramString.indexOf("&sign=\"");
    String str1 = paramString.substring(i, paramString.length() - 1).replace("&sign=\"", "");
    String str2 = paramString.substring(0, i);
    try
    {
      paramString = URLEncoder.encode(str1, "UTF-8");
      return str2 + "&sign=\"" + paramString + "\"";
    }
    catch (UnsupportedEncodingException paramString)
    {
      while (true)
      {
        paramString.printStackTrace();
        paramString = str1;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.wemart.sdk.app.pay.PayUtils
 * JD-Core Version:    0.6.2
 */