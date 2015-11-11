package com.umeng.message.proguard;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;

public class g
{
  public static File a(String paramString)
  {
    File localFile = Environment.getExternalStorageDirectory();
    if (localFile != null)
    {
      paramString = new File(String.format("%s%s%s", new Object[] { localFile.getAbsolutePath(), File.separator, paramString }));
      if ((paramString != null) && (!paramString.exists()))
        paramString.mkdirs();
      return paramString;
    }
    return null;
  }

  public static String a()
  {
    String str = null;
    Object localObject1 = null;
    try
    {
      FileReader localFileReader = new FileReader("/proc/cpuinfo");
      if (localFileReader != null)
        localObject1 = str;
      while (true)
      {
        try
        {
          localBufferedReader = new BufferedReader(localFileReader, 1024);
          localObject1 = str;
          str = localBufferedReader.readLine();
          localObject1 = str;
          localObject2 = str;
        }
        catch (IOException localIOException)
        {
          try
          {
            BufferedReader localBufferedReader;
            localBufferedReader.close();
            localObject1 = str;
            localObject2 = str;
            localFileReader.close();
            localObject1 = str;
            if (localObject1 == null)
              break;
            return ((String)localObject1).substring(((String)localObject1).indexOf(':') + 1).trim();
            localIOException = localIOException;
            localObject2 = localObject1;
            Log.e("Could not read from file /proc/cpuinfo", localIOException.toString());
            continue;
          }
          catch (FileNotFoundException localFileNotFoundException1)
          {
            Object localObject2;
            localObject1 = localObject2;
          }
        }
        Log.e("BaseParameter-Could not open file /proc/cpuinfo", localFileNotFoundException1.toString());
      }
      return "";
    }
    catch (FileNotFoundException localFileNotFoundException2)
    {
      while (true)
        localObject1 = null;
    }
  }

  public static String a(Context paramContext)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      paramContext = paramContext.getPackageName();
      if ((localPackageManager != null) && (paramContext != null))
      {
        paramContext = localPackageManager.getApplicationLabel(localPackageManager.getPackageInfo(paramContext, 1).applicationInfo).toString();
        return paramContext;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }

  public static int b()
  {
    try
    {
      i = Build.VERSION.class.getField("SDK_INT").getInt(null);
      return i;
    }
    catch (Exception localException1)
    {
      try
      {
        int i = Integer.parseInt((String)Build.VERSION.class.getField("SDK").get(null));
        return i;
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
    return 2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.g
 * JD-Core Version:    0.6.2
 */