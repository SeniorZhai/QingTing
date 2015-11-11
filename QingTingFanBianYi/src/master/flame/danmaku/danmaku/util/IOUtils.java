package master.flame.danmaku.danmaku.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils
{
  public static void closeQuietly(InputStream paramInputStream)
  {
    if (paramInputStream != null);
    try
    {
      paramInputStream.close();
      return;
    }
    catch (IOException paramInputStream)
    {
    }
  }

  public static void closeQuietly(OutputStream paramOutputStream)
  {
    if (paramOutputStream != null);
    try
    {
      paramOutputStream.close();
      return;
    }
    catch (IOException paramOutputStream)
    {
    }
  }

  public static byte[] getBytes(InputStream paramInputStream)
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      byte[] arrayOfByte = new byte[8192];
      while (true)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i == -1)
          break;
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
      paramInputStream.close();
      paramInputStream = localByteArrayOutputStream.toByteArray();
      return paramInputStream;
    }
    catch (IOException paramInputStream)
    {
    }
    return null;
  }

  public static String getString(InputStream paramInputStream)
  {
    paramInputStream = getBytes(paramInputStream);
    if (paramInputStream == null)
      return null;
    return new String(paramInputStream);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.util.IOUtils
 * JD-Core Version:    0.6.2
 */