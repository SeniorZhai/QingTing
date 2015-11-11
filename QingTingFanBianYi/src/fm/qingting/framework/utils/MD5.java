package fm.qingting.framework.utils;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5
{
  public static String md5Encode(String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    int i;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localObject1 = localMessageDigest;
      localObject2 = localMessageDigest;
      localMessageDigest.reset();
      localObject1 = localMessageDigest;
      localObject2 = localMessageDigest;
      localMessageDigest.update(paramString.getBytes("UTF-8"));
      localObject1 = localMessageDigest;
      paramString = ((MessageDigest)localObject1).digest();
      localObject1 = new StringBuffer();
      i = 0;
      if (i >= paramString.length)
        return ((StringBuffer)localObject1).toString();
    }
    catch (NoSuchAlgorithmException paramString)
    {
      while (true)
      {
        System.out.println("NoSuchAlgorithmException caught!");
        System.exit(-1);
      }
    }
    catch (UnsupportedEncodingException paramString)
    {
      while (true)
      {
        paramString.printStackTrace();
        localObject1 = localObject2;
      }
    }
    if (Integer.toHexString(paramString[i] & 0xFF).length() == 1)
      ((StringBuffer)localObject1).append("0").append(Integer.toHexString(paramString[i] & 0xFF));
    while (true)
    {
      i += 1;
      break;
      ((StringBuffer)localObject1).append(Integer.toHexString(paramString[i] & 0xFF));
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.MD5
 * JD-Core Version:    0.6.2
 */