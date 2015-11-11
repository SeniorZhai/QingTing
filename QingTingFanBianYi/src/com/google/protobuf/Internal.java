package com.google.protobuf;

import java.io.UnsupportedEncodingException;

public class Internal
{
  public static ByteString bytesDefaultValue(String paramString)
  {
    try
    {
      paramString = ByteString.copyFrom(paramString.getBytes("ISO-8859-1"));
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
    }
    throw new IllegalStateException("Java VM does not support a standard character set.", paramString);
  }

  public static boolean isValidUtf8(ByteString paramByteString)
  {
    return paramByteString.isValidUtf8();
  }

  public static String stringDefaultValue(String paramString)
  {
    try
    {
      paramString = new String(paramString.getBytes("ISO-8859-1"), "UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
    }
    throw new IllegalStateException("Java VM does not support a standard character set.", paramString);
  }

  public static abstract interface EnumLite
  {
    public abstract int getNumber();
  }

  public static abstract interface EnumLiteMap<T extends Internal.EnumLite>
  {
    public abstract T findValueByNumber(int paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.Internal
 * JD-Core Version:    0.6.2
 */