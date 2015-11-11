package com.taobao.munion.base.download;

import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class b
{
  public static int a;

  public static String a(byte[] paramArrayOfByte, String paramString)
    throws UnsupportedEncodingException, DataFormatException
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
      return null;
    Inflater localInflater = new Inflater();
    byte[] arrayOfByte = new byte[100];
    localInflater.setInput(paramArrayOfByte, 0, paramArrayOfByte.length);
    paramArrayOfByte = new StringBuilder();
    while (!localInflater.needsInput())
      paramArrayOfByte.append(new String(arrayOfByte, 0, localInflater.inflate(arrayOfByte), paramString));
    localInflater.end();
    return paramArrayOfByte.toString();
  }

  public static byte[] a(String paramString1, String paramString2)
    throws IOException
  {
    if (TextUtils.isEmpty(paramString1))
      return null;
    Deflater localDeflater = new Deflater();
    localDeflater.setInput(paramString1.getBytes(paramString2));
    localDeflater.finish();
    paramString1 = new byte[8192];
    a = 0;
    try
    {
      paramString2 = new ByteArrayOutputStream();
      try
      {
        while (!localDeflater.finished())
        {
          int i = localDeflater.deflate(paramString1);
          a += i;
          paramString2.write(paramString1, 0, i);
        }
      }
      finally
      {
      }
      if (paramString2 != null)
        paramString2.close();
      throw paramString1;
      localDeflater.end();
      if (paramString2 != null)
        paramString2.close();
      return paramString2.toByteArray();
    }
    finally
    {
      while (true)
        paramString2 = null;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.download.b
 * JD-Core Version:    0.6.2
 */