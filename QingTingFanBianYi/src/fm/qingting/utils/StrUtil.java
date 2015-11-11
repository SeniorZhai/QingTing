package fm.qingting.utils;

public class StrUtil
{
  public static String trimAll(String paramString)
  {
    if (paramString == null)
      return paramString;
    StringBuffer localStringBuffer = new StringBuffer();
    paramString = paramString.trim();
    int i = 0;
    while (i < paramString.length())
    {
      char c = paramString.charAt(i);
      if ((c != '\n') && (c != '\r') && (c != '\t'))
        localStringBuffer.append(c);
      i += 1;
    }
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.StrUtil
 * JD-Core Version:    0.6.2
 */