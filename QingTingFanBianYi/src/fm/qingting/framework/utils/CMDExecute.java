package fm.qingting.framework.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

class CMDExecute
{
  public String run(String[] paramArrayOfString, String paramString)
    throws IOException
  {
    String str = "";
    Object localObject = str;
    try
    {
      paramArrayOfString = new ProcessBuilder(paramArrayOfString);
      if (paramString != null)
      {
        localObject = str;
        paramArrayOfString.directory(new File(paramString));
      }
      localObject = str;
      paramArrayOfString.redirectErrorStream(true);
      localObject = str;
      paramString = paramArrayOfString.start().getInputStream();
      localObject = str;
      byte[] arrayOfByte = new byte[1024];
      for (paramArrayOfString = str; ; paramArrayOfString = paramArrayOfString + new String(arrayOfByte))
      {
        localObject = paramArrayOfString;
        if (paramString.read(arrayOfByte) == -1)
        {
          localObject = paramArrayOfString;
          paramString.close();
          return paramArrayOfString;
        }
        localObject = paramArrayOfString;
        System.out.println(new String(arrayOfByte));
        localObject = paramArrayOfString;
      }
    }
    catch (Exception paramArrayOfString)
    {
      while (true)
      {
        paramArrayOfString.printStackTrace();
        paramArrayOfString = (String[])localObject;
      }
    }
    finally
    {
    }
    throw paramArrayOfString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.CMDExecute
 * JD-Core Version:    0.6.2
 */