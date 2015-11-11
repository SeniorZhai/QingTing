package fm.qingting.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class CaptureSysLog
{
  private static CaptureSysLog instance;

  public static CaptureSysLog getInstance()
  {
    if (instance == null)
      instance = new CaptureSysLog();
    return instance;
  }

  public static void getLog()
  {
    System.out.println("--------func start--------");
    String str;
    try
    {
      Object localObject = new ArrayList();
      ((ArrayList)localObject).add("logcat");
      ((ArrayList)localObject).add("-d");
      ArrayList localArrayList = new ArrayList();
      localArrayList.add("logcat");
      localArrayList.add("-c");
      localObject = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec((String[])((ArrayList)localObject).toArray(new String[((ArrayList)localObject).size()])).getInputStream()));
      while (true)
      {
        str = ((BufferedReader)localObject).readLine();
        if (str == null)
          break;
        Runtime.getRuntime().exec((String[])localArrayList.toArray(new String[localArrayList.size()]));
        System.out.println(str);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    while (true)
    {
      System.out.println("--------func end--------");
      return;
      if (str == null)
        System.out.println("-- is null --");
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.CaptureSysLog
 * JD-Core Version:    0.6.2
 */