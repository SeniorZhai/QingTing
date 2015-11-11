package com.alibaba.fastjson.util;

import java.lang.ref.SoftReference;
import java.nio.charset.CharsetDecoder;

public class ThreadLocalCache
{
  public static final int BYTES_CACH_INIT_SIZE = 1024;
  public static final int BYTeS_CACH_MAX_SIZE = 131072;
  public static final int CHARS_CACH_INIT_SIZE = 1024;
  public static final int CHARS_CACH_MAX_SIZE = 131072;
  private static final ThreadLocal<SoftReference<byte[]>> bytesBufLocal = new ThreadLocal();
  private static final ThreadLocal<SoftReference<char[]>> charsBufLocal = new ThreadLocal();
  private static final ThreadLocal<CharsetDecoder> decoderLocal = new ThreadLocal();

  private static char[] allocate(int paramInt)
  {
    int i = getAllocateLength(1024, 131072, paramInt);
    if (i <= 131072)
    {
      char[] arrayOfChar = new char[i];
      charsBufLocal.set(new SoftReference(arrayOfChar));
      return arrayOfChar;
    }
    return new char[paramInt];
  }

  private static byte[] allocateBytes(int paramInt)
  {
    int i = getAllocateLength(1024, 131072, paramInt);
    if (i <= 131072)
    {
      byte[] arrayOfByte = new byte[i];
      bytesBufLocal.set(new SoftReference(arrayOfByte));
      return arrayOfByte;
    }
    return new byte[paramInt];
  }

  public static void clearBytes()
  {
    bytesBufLocal.set(null);
  }

  public static void clearChars()
  {
    charsBufLocal.set(null);
  }

  private static int getAllocateLength(int paramInt1, int paramInt2, int paramInt3)
  {
    int i;
    do
    {
      if (paramInt1 >= paramInt3)
        return paramInt1;
      i = paramInt1 * 2;
      paramInt1 = i;
    }
    while (i <= paramInt2);
    return paramInt3;
  }

  public static byte[] getBytes(int paramInt)
  {
    Object localObject = (SoftReference)bytesBufLocal.get();
    if (localObject == null)
      localObject = allocateBytes(paramInt);
    byte[] arrayOfByte;
    do
    {
      return localObject;
      arrayOfByte = (byte[])((SoftReference)localObject).get();
      if (arrayOfByte == null)
        return allocateBytes(paramInt);
      localObject = arrayOfByte;
    }
    while (arrayOfByte.length >= paramInt);
    return allocateBytes(paramInt);
  }

  public static char[] getChars(int paramInt)
  {
    Object localObject = (SoftReference)charsBufLocal.get();
    if (localObject == null)
      localObject = allocate(paramInt);
    char[] arrayOfChar;
    do
    {
      return localObject;
      arrayOfChar = (char[])((SoftReference)localObject).get();
      if (arrayOfChar == null)
        return allocate(paramInt);
      localObject = arrayOfChar;
    }
    while (arrayOfChar.length >= paramInt);
    return allocate(paramInt);
  }

  public static CharsetDecoder getUTF8Decoder()
  {
    CharsetDecoder localCharsetDecoder = (CharsetDecoder)decoderLocal.get();
    Object localObject = localCharsetDecoder;
    if (localCharsetDecoder == null)
    {
      localObject = new UTF8Decoder();
      decoderLocal.set(localObject);
    }
    return localObject;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.util.ThreadLocalCache
 * JD-Core Version:    0.6.2
 */