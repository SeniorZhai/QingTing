package fm.qingting.async.http.libcore;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;

class Streams
{
  static String readFully(Reader paramReader)
    throws IOException
  {
    try
    {
      StringWriter localStringWriter = new StringWriter();
      char[] arrayOfChar = new char[1024];
      while (true)
      {
        int i = paramReader.read(arrayOfChar);
        if (i == -1)
          break;
        localStringWriter.write(arrayOfChar, 0, i);
      }
    }
    finally
    {
      paramReader.close();
    }
    String str = localObject.toString();
    paramReader.close();
    return str;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.libcore.Streams
 * JD-Core Version:    0.6.2
 */