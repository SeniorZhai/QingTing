package weibo4android.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BareBonesBrowserLaunch
{
  private static void browse(String paramString)
    throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InterruptedException, InvocationTargetException, IOException, NoSuchMethodException
  {
    Object localObject1 = null;
    Object localObject2 = System.getProperty("os.name", "");
    if (((String)localObject2).startsWith("Mac OS"))
    {
      Class.forName("com.apple.eio.FileManager").getDeclaredMethod("openURL", new Class[] { String.class }).invoke(null, new Object[] { paramString });
      return;
    }
    if (((String)localObject2).startsWith("Windows"))
    {
      Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + paramString);
      return;
    }
    localObject2 = new String[6];
    localObject2[0] = "firefox";
    localObject2[1] = "opera";
    localObject2[2] = "konqueror";
    localObject2[3] = "epiphany";
    localObject2[4] = "mozilla";
    localObject2[5] = "netscape";
    int i = 0;
    while ((i < localObject2.length) && (localObject1 == null))
    {
      if (Runtime.getRuntime().exec(new String[] { "which", localObject2[i] }).waitFor() == 0)
        localObject1 = localObject2[i];
      i += 1;
    }
    if (localObject1 == null)
      throw new NoSuchMethodException("Could not find web browser");
    Runtime.getRuntime().exec(new String[] { localObject1, paramString });
  }

  public static void openURL(String paramString)
  {
    try
    {
      browse(paramString);
      return;
    }
    catch (Exception paramString)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.util.BareBonesBrowserLaunch
 * JD-Core Version:    0.6.2
 */