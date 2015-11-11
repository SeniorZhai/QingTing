package fm.qingting.async.http.libcore;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

class IoUtils
{
  static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      return;
    }
    catch (RuntimeException paramCloseable)
    {
      throw paramCloseable;
    }
    catch (Exception paramCloseable)
    {
    }
  }

  static void deleteContents(File paramFile)
    throws IOException
  {
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile == null)
      throw new IllegalArgumentException("not a directory: " + paramFile);
    int j = arrayOfFile.length;
    int i = 0;
    while (i < j)
    {
      paramFile = arrayOfFile[i];
      if (paramFile.isDirectory())
        deleteContents(paramFile);
      if (!paramFile.delete())
        throw new IOException("failed to delete file: " + paramFile);
      i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.libcore.IoUtils
 * JD-Core Version:    0.6.2
 */