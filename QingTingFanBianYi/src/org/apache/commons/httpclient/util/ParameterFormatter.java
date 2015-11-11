package org.apache.commons.httpclient.util;

import org.apache.commons.httpclient.NameValuePair;

public class ParameterFormatter
{
  private static final char[] SEPARATORS = { 40, 41, 60, 62, 64, 44, 59, 58, 92, 34, 47, 91, 93, 63, 61, 123, 125, 32, 9 };
  private static final char[] UNSAFE_CHARS = { 34, 92 };
  private boolean alwaysUseQuotes = true;

  public static void formatValue(StringBuffer paramStringBuffer, String paramString, boolean paramBoolean)
  {
    if (paramStringBuffer == null)
      throw new IllegalArgumentException("String buffer may not be null");
    if (paramString == null)
      throw new IllegalArgumentException("Value buffer may not be null");
    char c;
    if (paramBoolean)
    {
      paramStringBuffer.append('"');
      i = 0;
      while (true)
      {
        if (i >= paramString.length())
        {
          paramStringBuffer.append('"');
          return;
        }
        c = paramString.charAt(i);
        if (isUnsafeChar(c))
          paramStringBuffer.append('\\');
        paramStringBuffer.append(c);
        i += 1;
      }
    }
    int k = paramStringBuffer.length();
    int j = 0;
    int i = 0;
    while (true)
    {
      if (i >= paramString.length())
      {
        if (j == 0)
          break;
        paramStringBuffer.insert(k, '"');
        paramStringBuffer.append('"');
        return;
      }
      c = paramString.charAt(i);
      if (isSeparator(c))
        j = 1;
      if (isUnsafeChar(c))
        paramStringBuffer.append('\\');
      paramStringBuffer.append(c);
      i += 1;
    }
  }

  private static boolean isOneOf(char[] paramArrayOfChar, char paramChar)
  {
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfChar.length)
        return false;
      if (paramChar == paramArrayOfChar[i])
        return true;
      i += 1;
    }
  }

  private static boolean isSeparator(char paramChar)
  {
    return isOneOf(SEPARATORS, paramChar);
  }

  private static boolean isUnsafeChar(char paramChar)
  {
    return isOneOf(UNSAFE_CHARS, paramChar);
  }

  public String format(NameValuePair paramNameValuePair)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    format(localStringBuffer, paramNameValuePair);
    return localStringBuffer.toString();
  }

  public void format(StringBuffer paramStringBuffer, NameValuePair paramNameValuePair)
  {
    if (paramStringBuffer == null)
      throw new IllegalArgumentException("String buffer may not be null");
    if (paramNameValuePair == null)
      throw new IllegalArgumentException("Parameter may not be null");
    paramStringBuffer.append(paramNameValuePair.getName());
    paramNameValuePair = paramNameValuePair.getValue();
    if (paramNameValuePair != null)
    {
      paramStringBuffer.append("=");
      formatValue(paramStringBuffer, paramNameValuePair, this.alwaysUseQuotes);
    }
  }

  public boolean isAlwaysUseQuotes()
  {
    return this.alwaysUseQuotes;
  }

  public void setAlwaysUseQuotes(boolean paramBoolean)
  {
    this.alwaysUseQuotes = paramBoolean;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.ParameterFormatter
 * JD-Core Version:    0.6.2
 */