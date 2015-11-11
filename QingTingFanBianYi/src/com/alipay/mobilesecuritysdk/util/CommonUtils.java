package com.alipay.mobilesecuritysdk.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import org.json.JSONObject;

@SuppressLint({"SimpleDateFormat"})
public class CommonUtils
{
  public static JSONObject GetJsonFromFile(String paramString)
  {
    return null;
  }

  public static boolean GetSdCardFile()
  {
    String str = Environment.getExternalStorageState();
    return (!IsEmpty(str)) && ((str.equals("mounted")) || (str.equals("mounted_ro"))) && (Environment.getExternalStorageDirectory() != null);
  }

  public static boolean IsEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() <= 0);
  }

  public static String MD5(String paramString)
  {
    try
    {
      if (isBlank(paramString))
        return null;
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes("UTF-8"));
      paramString = ((MessageDigest)localObject).digest();
      localObject = new StringBuilder();
      int i = 0;
      while (true)
      {
        if (i >= 16)
          return ((StringBuilder)localObject).toString();
        ((StringBuilder)localObject).append(String.format("%02x", new Object[] { Byte.valueOf(paramString[i]) }));
        i += 1;
      }
    }
    catch (Exception paramString)
    {
    }
    return null;
  }

  public static String ReadFile(String paramString)
  {
    if (!new File(paramString).exists())
      return null;
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      paramString = new BufferedReader(new InputStreamReader(new FileInputStream(paramString), "UTF-8"));
      while (true)
      {
        try
        {
          String str = paramString.readLine();
          if (str == null)
          {
            paramString.close();
            return localStringBuilder.toString();
          }
          localStringBuilder.append(str);
          continue;
        }
        catch (IOException paramString)
        {
        }
        label77: paramString.printStackTrace();
      }
    }
    catch (IOException paramString)
    {
      break label77;
    }
  }

  // ERROR //
  public static void WriteFile(String paramString1, String paramString2)
    throws IOException
  {
    // Byte code:
    //   0: new 99	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 102	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: astore_2
    //   9: aconst_null
    //   10: astore_0
    //   11: aconst_null
    //   12: astore_3
    //   13: aload_2
    //   14: ifnull +22 -> 36
    //   17: new 131	java/io/FileWriter
    //   20: dup
    //   21: aload_2
    //   22: iconst_0
    //   23: invokespecial 134	java/io/FileWriter:<init>	(Ljava/io/File;Z)V
    //   26: astore_2
    //   27: aload_2
    //   28: aload_1
    //   29: invokevirtual 137	java/io/FileWriter:write	(Ljava/lang/String;)V
    //   32: aload_2
    //   33: invokevirtual 138	java/io/FileWriter:close	()V
    //   36: return
    //   37: astore_2
    //   38: aload_3
    //   39: astore_1
    //   40: aload_1
    //   41: astore_0
    //   42: ldc 140
    //   44: aload_2
    //   45: invokevirtual 143	java/lang/Exception:getLocalizedMessage	()Ljava/lang/String;
    //   48: invokestatic 149	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   51: pop
    //   52: aload_1
    //   53: invokevirtual 138	java/io/FileWriter:close	()V
    //   56: return
    //   57: astore_1
    //   58: aload_0
    //   59: invokevirtual 138	java/io/FileWriter:close	()V
    //   62: aload_1
    //   63: athrow
    //   64: astore_1
    //   65: aload_2
    //   66: astore_0
    //   67: goto -9 -> 58
    //   70: astore_0
    //   71: aload_2
    //   72: astore_1
    //   73: aload_0
    //   74: astore_2
    //   75: goto -35 -> 40
    //
    // Exception table:
    //   from	to	target	type
    //   17	27	37	java/lang/Exception
    //   17	27	57	finally
    //   42	52	57	finally
    //   27	32	64	finally
    //   27	32	70	java/lang/Exception
  }

  public static String convertDate2String(Date paramDate)
  {
    return new SimpleDateFormat("yyyyMMddHHmmss").format(paramDate);
  }

  public static boolean equalsIgnoreCase(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return paramString2 == null;
    return paramString1.equalsIgnoreCase(paramString2);
  }

  public static boolean isBlank(String paramString)
  {
    int j;
    if (paramString != null)
    {
      j = paramString.length();
      if (j != 0)
        break label15;
    }
    while (true)
    {
      return true;
      label15: int i = 0;
      while (i < j)
      {
        if (!Character.isWhitespace(paramString.charAt(i)))
          return false;
        i += 1;
      }
    }
  }

  public static boolean isBlankCollection(List<String> paramList)
  {
    if (paramList == null);
    do
      while (!paramList.hasNext())
      {
        do
          return true;
        while (paramList.size() <= 0);
        paramList = paramList.iterator();
      }
    while (isBlank((String)paramList.next()));
    return false;
  }

  public static boolean isNetWorkActive(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected()) && (paramContext.getType() == 1);
  }

  public static boolean outOfDate(long paramLong1, long paramLong2, int paramInt)
  {
    if (paramLong2 <= 0L);
    while ((System.currentTimeMillis() - paramLong1) / paramLong2 >= paramInt)
      return true;
    return false;
  }

  public static int string2int(String paramString)
  {
    try
    {
      if (isBlank(paramString))
        return 0;
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (Exception paramString)
    {
    }
    return 0;
  }

  public static long string2long(String paramString)
  {
    try
    {
      if (isBlank(paramString))
        return 0L;
      long l = Long.parseLong(paramString);
      return l;
    }
    catch (Exception paramString)
    {
    }
    return 0L;
  }

  public static String textCompress(String paramString)
  {
    try
    {
      byte[] arrayOfByte = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(paramString.length()).array();
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(paramString.length());
      GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
      localGZIPOutputStream.write(paramString.getBytes("UTF-8"));
      localGZIPOutputStream.close();
      localByteArrayOutputStream.close();
      paramString = new byte[localByteArrayOutputStream.toByteArray().length + 4];
      System.arraycopy(arrayOfByte, 0, paramString, 0, 4);
      System.arraycopy(localByteArrayOutputStream.toByteArray(), 0, paramString, 4, localByteArrayOutputStream.toByteArray().length);
      paramString = Base64.encodeToString(paramString, 8);
      return paramString;
    }
    catch (Exception paramString)
    {
      Log.i("ALP", paramString.getMessage());
    }
    return "";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.mobilesecuritysdk.util.CommonUtils
 * JD-Core Version:    0.6.2
 */