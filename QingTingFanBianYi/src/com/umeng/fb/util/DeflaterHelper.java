package com.umeng.fb.util;

import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class DeflaterHelper
{
  public static int TOTAL_LEN;

  public static byte[] deflaterCompress(String paramString1, String paramString2)
    throws IOException
  {
    if (TextUtils.isEmpty(paramString1))
      return null;
    return deflaterCompress(paramString1.getBytes(paramString2));
  }

  public static byte[] deflaterCompress(byte[] paramArrayOfByte)
    throws IOException
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0))
      return null;
    Deflater localDeflater = new Deflater();
    localDeflater.setInput(paramArrayOfByte);
    localDeflater.finish();
    byte[] arrayOfByte = new byte[8192];
    TOTAL_LEN = 0;
    paramArrayOfByte = null;
    try
    {
      Object localObject1 = new ByteArrayOutputStream();
      try
      {
        if (localDeflater.finished())
          break label97;
        int i = localDeflater.deflate(arrayOfByte);
        TOTAL_LEN += i;
        ((ByteArrayOutputStream)localObject1).write(arrayOfByte, 0, i);
      }
      finally
      {
        paramArrayOfByte = (byte[])localObject1;
        localObject1 = localObject3;
      }
      label87: if (paramArrayOfByte != null)
        paramArrayOfByte.close();
      throw ((Throwable)localObject1);
      label97: localObject3.end();
      if (localObject1 != null)
        ((ByteArrayOutputStream)localObject1).close();
      return ((ByteArrayOutputStream)localObject1).toByteArray();
    }
    finally
    {
      break label87;
    }
  }

  public static String deflaterDecompress(byte[] paramArrayOfByte, String paramString)
    throws UnsupportedEncodingException, DataFormatException
  {
    paramArrayOfByte = deflaterDecompress(paramArrayOfByte);
    if (paramArrayOfByte != null)
      return new String(paramArrayOfByte, paramString);
    return null;
  }

  public static byte[] deflaterDecompress(byte[] paramArrayOfByte)
    throws UnsupportedEncodingException, DataFormatException
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
      return null;
    Inflater localInflater = new Inflater();
    localInflater.setInput(paramArrayOfByte, 0, paramArrayOfByte.length);
    paramArrayOfByte = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte[1024];
    int i = 0;
    while (!localInflater.needsInput())
    {
      int j = localInflater.inflate(arrayOfByte);
      paramArrayOfByte.write(arrayOfByte, i, j);
      i += j;
    }
    localInflater.end();
    return paramArrayOfByte.toByteArray();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.util.DeflaterHelper
 * JD-Core Version:    0.6.2
 */