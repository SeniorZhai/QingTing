package com.alipay.mobilesecuritysdk.deviceID;

import HttpUtils.HttpFetcher;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;
import com.alipay.mobilesecuritysdk.util.CommonUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

public class LOG
{
  public static boolean DBG = true;
  private static String TAG = "logger";
  private static File logFileDir = null;
  private static File logFileName = null;
  private static Context mcontext = null;
  private static String model = null;
  private static String pkgName = null;

  private static long checkLogFile()
  {
    while (true)
    {
      try
      {
        logFileName = new File(logFileDir, getCurLogFileName());
        if (DBG)
          Log.d(TAG, "current logfile is:" + logFileName.getAbsolutePath());
        boolean bool = logFileName.exists();
        if (!bool)
          try
          {
            logFileName.createNewFile();
            l = 0L;
            return l;
          }
          catch (IOException localIOException)
          {
            localIOException.printStackTrace();
            continue;
          }
      }
      finally
      {
      }
      long l = logFileName.length();
    }
  }

  private static boolean doUpload(String paramString)
  {
    boolean bool = true;
    if (paramString == null)
    {
      Log.e(TAG, "logFile to JosonString is null");
      bool = false;
    }
    do
    {
      return bool;
      if (DBG)
        Log.d(TAG, paramString);
      if (mcontext == null)
        return false;
      if (!CommonUtils.isNetWorkActive(mcontext))
        return false;
      paramString = new HttpFetcher().uploadCollectedData(mcontext, "https://seccliprod.alipay.com/api/do.htm", "bugTrack", paramString, "1", true);
      if (paramString == null)
        return false;
    }
    while (paramString.getStatusLine().getStatusCode() == 200);
    return false;
  }

  private static String getCurLogFileName()
  {
    Date localDate = Calendar.getInstance().getTime();
    return new SimpleDateFormat("yyyyMMdd").format(localDate) + ".log";
  }

  private static void getInfo(Context paramContext)
  {
    model = Build.MODEL;
    pkgName = paramContext.getApplicationContext().getApplicationInfo().packageName;
    if (DBG)
      Log.d(TAG, pkgName + "," + model);
  }

  public static String getStackString(Throwable paramThrowable)
  {
    StringWriter localStringWriter = new StringWriter();
    paramThrowable.printStackTrace(new PrintWriter(localStringWriter));
    return localStringWriter.toString();
  }

  public static void init(Context paramContext)
  {
    try
    {
      mcontext = paramContext;
      if (logFileDir == null)
      {
        logFileDir = new File(paramContext.getFilesDir().getAbsolutePath() + "/log/ap");
        getInfo(paramContext);
      }
      if (logFileDir.exists())
      {
        if (logFileDir.isDirectory())
          break label109;
        throw new IllegalStateException(String.format("<%s> exists but not a Directory!", new Object[] { logFileDir.getAbsoluteFile() }));
      }
    }
    finally
    {
    }
    logFileDir.mkdirs();
    label109:
  }

  // ERROR //
  public static void logMessage(List<String> paramList)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 19	com/alipay/mobilesecuritysdk/deviceID/LOG:logFileDir	Ljava/io/File;
    //   6: ifnonnull +19 -> 25
    //   9: new 206	java/lang/IllegalStateException
    //   12: dup
    //   13: ldc 224
    //   15: invokespecial 215	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   18: athrow
    //   19: astore_0
    //   20: ldc 2
    //   22: monitorexit
    //   23: aload_0
    //   24: athrow
    //   25: new 226	java/lang/StringBuffer
    //   28: dup
    //   29: new 136	java/text/SimpleDateFormat
    //   32: dup
    //   33: ldc 228
    //   35: invokespecial 139	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   38: invokestatic 130	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   41: invokevirtual 134	java/util/Calendar:getTime	()Ljava/util/Date;
    //   44: invokevirtual 143	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   47: invokespecial 229	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
    //   50: astore 4
    //   52: aload 4
    //   54: new 52	java/lang/StringBuilder
    //   57: dup
    //   58: ldc 175
    //   60: invokespecial 57	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   63: getstatic 23	com/alipay/mobilesecuritysdk/deviceID/LOG:model	Ljava/lang/String;
    //   66: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   72: invokevirtual 232	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   75: pop
    //   76: aload 4
    //   78: new 52	java/lang/StringBuilder
    //   81: dup
    //   82: ldc 175
    //   84: invokespecial 57	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   87: getstatic 25	com/alipay/mobilesecuritysdk/deviceID/LOG:pkgName	Ljava/lang/String;
    //   90: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   96: invokevirtual 232	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   99: pop
    //   100: aload_0
    //   101: invokeinterface 238 1 0
    //   106: astore_0
    //   107: aload_0
    //   108: invokeinterface 243 1 0
    //   113: istore 7
    //   115: iload 7
    //   117: ifne +170 -> 287
    //   120: aconst_null
    //   121: astore_3
    //   122: aconst_null
    //   123: astore_0
    //   124: aload_0
    //   125: astore_2
    //   126: aload_3
    //   127: astore_1
    //   128: invokestatic 245	com/alipay/mobilesecuritysdk/deviceID/LOG:checkLogFile	()J
    //   131: lstore 5
    //   133: aload_0
    //   134: astore_2
    //   135: aload_3
    //   136: astore_1
    //   137: getstatic 27	com/alipay/mobilesecuritysdk/deviceID/LOG:DBG	Z
    //   140: ifeq +31 -> 171
    //   143: aload_0
    //   144: astore_2
    //   145: aload_3
    //   146: astore_1
    //   147: getstatic 31	com/alipay/mobilesecuritysdk/deviceID/LOG:TAG	Ljava/lang/String;
    //   150: new 52	java/lang/StringBuilder
    //   153: dup
    //   154: ldc 247
    //   156: invokespecial 57	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   159: lload 5
    //   161: invokevirtual 250	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   164: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   167: invokestatic 73	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   170: pop
    //   171: aload_0
    //   172: astore_2
    //   173: aload_3
    //   174: astore_1
    //   175: aload 4
    //   177: invokevirtual 252	java/lang/StringBuffer:length	()I
    //   180: i2l
    //   181: lload 5
    //   183: ladd
    //   184: ldc2_w 253
    //   187: lcmp
    //   188: ifgt +134 -> 322
    //   191: aload_0
    //   192: astore_2
    //   193: aload_3
    //   194: astore_1
    //   195: new 256	java/io/FileWriter
    //   198: dup
    //   199: getstatic 21	com/alipay/mobilesecuritysdk/deviceID/LOG:logFileName	Ljava/io/File;
    //   202: iconst_1
    //   203: invokespecial 259	java/io/FileWriter:<init>	(Ljava/io/File;Z)V
    //   206: astore_0
    //   207: aload_0
    //   208: astore_2
    //   209: aload_0
    //   210: astore_1
    //   211: aload 4
    //   213: ldc_w 261
    //   216: invokevirtual 232	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   219: pop
    //   220: aload_0
    //   221: astore_2
    //   222: aload_0
    //   223: astore_1
    //   224: getstatic 27	com/alipay/mobilesecuritysdk/deviceID/LOG:DBG	Z
    //   227: ifeq +35 -> 262
    //   230: aload_0
    //   231: astore_2
    //   232: aload_0
    //   233: astore_1
    //   234: getstatic 31	com/alipay/mobilesecuritysdk/deviceID/LOG:TAG	Ljava/lang/String;
    //   237: new 52	java/lang/StringBuilder
    //   240: dup
    //   241: ldc_w 263
    //   244: invokespecial 57	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   247: aload 4
    //   249: invokevirtual 264	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   252: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   255: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   258: invokestatic 73	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   261: pop
    //   262: aload_0
    //   263: astore_2
    //   264: aload_0
    //   265: astore_1
    //   266: aload_0
    //   267: aload 4
    //   269: invokevirtual 264	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   272: invokevirtual 267	java/io/FileWriter:write	(Ljava/lang/String;)V
    //   275: aload_0
    //   276: ifnull +7 -> 283
    //   279: aload_0
    //   280: invokevirtual 270	java/io/FileWriter:close	()V
    //   283: ldc 2
    //   285: monitorexit
    //   286: return
    //   287: aload_0
    //   288: invokeinterface 274 1 0
    //   293: checkcast 145	java/lang/String
    //   296: astore_1
    //   297: aload 4
    //   299: new 52	java/lang/StringBuilder
    //   302: dup
    //   303: ldc 175
    //   305: invokespecial 57	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   308: aload_1
    //   309: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   312: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   315: invokevirtual 232	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   318: pop
    //   319: goto -212 -> 107
    //   322: aload_0
    //   323: astore_2
    //   324: aload_3
    //   325: astore_1
    //   326: new 256	java/io/FileWriter
    //   329: dup
    //   330: getstatic 21	com/alipay/mobilesecuritysdk/deviceID/LOG:logFileName	Ljava/io/File;
    //   333: invokespecial 277	java/io/FileWriter:<init>	(Ljava/io/File;)V
    //   336: astore_0
    //   337: goto -130 -> 207
    //   340: astore_0
    //   341: aload_2
    //   342: astore_1
    //   343: aload_0
    //   344: invokevirtual 278	java/lang/Exception:printStackTrace	()V
    //   347: aload_2
    //   348: ifnull -65 -> 283
    //   351: aload_2
    //   352: invokevirtual 270	java/io/FileWriter:close	()V
    //   355: goto -72 -> 283
    //   358: astore_0
    //   359: getstatic 31	com/alipay/mobilesecuritysdk/deviceID/LOG:TAG	Ljava/lang/String;
    //   362: ldc_w 280
    //   365: invokestatic 93	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   368: pop
    //   369: aload_0
    //   370: invokevirtual 83	java/io/IOException:printStackTrace	()V
    //   373: goto -90 -> 283
    //   376: astore_0
    //   377: aload_1
    //   378: ifnull +7 -> 385
    //   381: aload_1
    //   382: invokevirtual 270	java/io/FileWriter:close	()V
    //   385: aload_0
    //   386: athrow
    //   387: astore_1
    //   388: getstatic 31	com/alipay/mobilesecuritysdk/deviceID/LOG:TAG	Ljava/lang/String;
    //   391: ldc_w 280
    //   394: invokestatic 93	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   397: pop
    //   398: aload_1
    //   399: invokevirtual 83	java/io/IOException:printStackTrace	()V
    //   402: goto -17 -> 385
    //   405: astore_0
    //   406: getstatic 31	com/alipay/mobilesecuritysdk/deviceID/LOG:TAG	Ljava/lang/String;
    //   409: ldc_w 280
    //   412: invokestatic 93	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   415: pop
    //   416: aload_0
    //   417: invokevirtual 83	java/io/IOException:printStackTrace	()V
    //   420: goto -137 -> 283
    //
    // Exception table:
    //   from	to	target	type
    //   3	19	19	finally
    //   25	107	19	finally
    //   107	115	19	finally
    //   279	283	19	finally
    //   287	319	19	finally
    //   351	355	19	finally
    //   359	373	19	finally
    //   381	385	19	finally
    //   385	387	19	finally
    //   388	402	19	finally
    //   406	420	19	finally
    //   128	133	340	java/lang/Exception
    //   137	143	340	java/lang/Exception
    //   147	171	340	java/lang/Exception
    //   175	191	340	java/lang/Exception
    //   195	207	340	java/lang/Exception
    //   211	220	340	java/lang/Exception
    //   224	230	340	java/lang/Exception
    //   234	262	340	java/lang/Exception
    //   266	275	340	java/lang/Exception
    //   326	337	340	java/lang/Exception
    //   351	355	358	java/io/IOException
    //   128	133	376	finally
    //   137	143	376	finally
    //   147	171	376	finally
    //   175	191	376	finally
    //   195	207	376	finally
    //   211	220	376	finally
    //   224	230	376	finally
    //   234	262	376	finally
    //   266	275	376	finally
    //   326	337	376	finally
    //   343	347	376	finally
    //   381	385	387	java/io/IOException
    //   279	283	405	java/io/IOException
  }

  // ERROR //
  private static String toJsonString(String paramString)
  {
    // Byte code:
    //   0: new 290	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 291	org/json/JSONObject:<init>	()V
    //   7: astore 4
    //   9: new 43	java/io/File
    //   12: dup
    //   13: getstatic 19	com/alipay/mobilesecuritysdk/deviceID/LOG:logFileDir	Ljava/io/File;
    //   16: aload_0
    //   17: invokespecial 50	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   20: astore_1
    //   21: aload_1
    //   22: ifnull +19 -> 41
    //   25: aload_1
    //   26: invokevirtual 77	java/io/File:exists	()Z
    //   29: ifeq +12 -> 41
    //   32: aload_1
    //   33: invokevirtual 86	java/io/File:length	()J
    //   36: lconst_0
    //   37: lcmp
    //   38: ifne +5 -> 43
    //   41: aconst_null
    //   42: areturn
    //   43: aload_1
    //   44: invokevirtual 86	java/io/File:length	()J
    //   47: l2i
    //   48: newarray char
    //   50: astore 5
    //   52: aconst_null
    //   53: astore_3
    //   54: aconst_null
    //   55: astore_0
    //   56: aconst_null
    //   57: astore_2
    //   58: new 293	java/io/FileReader
    //   61: dup
    //   62: aload_1
    //   63: invokespecial 294	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   66: astore_1
    //   67: aload_1
    //   68: aload 5
    //   70: invokevirtual 298	java/io/FileReader:read	([C)I
    //   73: pop
    //   74: aload_1
    //   75: ifnull +7 -> 82
    //   78: aload_1
    //   79: invokevirtual 299	java/io/FileReader:close	()V
    //   82: aload 4
    //   84: ldc_w 301
    //   87: ldc_w 303
    //   90: invokevirtual 307	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   93: pop
    //   94: aload 4
    //   96: ldc_w 309
    //   99: aload 5
    //   101: invokestatic 312	java/lang/String:valueOf	([C)Ljava/lang/String;
    //   104: invokevirtual 307	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   107: pop
    //   108: aload 4
    //   110: invokevirtual 313	org/json/JSONObject:toString	()Ljava/lang/String;
    //   113: areturn
    //   114: astore_0
    //   115: aload_2
    //   116: astore_1
    //   117: aload_0
    //   118: astore_2
    //   119: aload_1
    //   120: astore_0
    //   121: aload_2
    //   122: invokevirtual 314	java/io/FileNotFoundException:printStackTrace	()V
    //   125: aload_1
    //   126: ifnull -85 -> 41
    //   129: aload_1
    //   130: invokevirtual 299	java/io/FileReader:close	()V
    //   133: aconst_null
    //   134: areturn
    //   135: astore_0
    //   136: aload_0
    //   137: invokevirtual 83	java/io/IOException:printStackTrace	()V
    //   140: aconst_null
    //   141: areturn
    //   142: astore_2
    //   143: aload_3
    //   144: astore_1
    //   145: aload_1
    //   146: astore_0
    //   147: aload_2
    //   148: invokevirtual 83	java/io/IOException:printStackTrace	()V
    //   151: aload_1
    //   152: ifnull -111 -> 41
    //   155: aload_1
    //   156: invokevirtual 299	java/io/FileReader:close	()V
    //   159: aconst_null
    //   160: areturn
    //   161: astore_0
    //   162: aload_0
    //   163: invokevirtual 83	java/io/IOException:printStackTrace	()V
    //   166: aconst_null
    //   167: areturn
    //   168: astore_1
    //   169: aload_0
    //   170: ifnull +7 -> 177
    //   173: aload_0
    //   174: invokevirtual 299	java/io/FileReader:close	()V
    //   177: aload_1
    //   178: athrow
    //   179: astore_0
    //   180: aload_0
    //   181: invokevirtual 83	java/io/IOException:printStackTrace	()V
    //   184: goto -7 -> 177
    //   187: astore_0
    //   188: aload_0
    //   189: invokevirtual 83	java/io/IOException:printStackTrace	()V
    //   192: goto -110 -> 82
    //   195: astore_0
    //   196: getstatic 31	com/alipay/mobilesecuritysdk/deviceID/LOG:TAG	Ljava/lang/String;
    //   199: aload_0
    //   200: invokevirtual 317	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   203: invokestatic 93	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   206: pop
    //   207: aconst_null
    //   208: areturn
    //   209: astore_2
    //   210: aload_1
    //   211: astore_0
    //   212: aload_2
    //   213: astore_1
    //   214: goto -45 -> 169
    //   217: astore_2
    //   218: goto -73 -> 145
    //   221: astore_2
    //   222: goto -103 -> 119
    //
    // Exception table:
    //   from	to	target	type
    //   58	67	114	java/io/FileNotFoundException
    //   129	133	135	java/io/IOException
    //   58	67	142	java/io/IOException
    //   155	159	161	java/io/IOException
    //   58	67	168	finally
    //   121	125	168	finally
    //   147	151	168	finally
    //   173	177	179	java/io/IOException
    //   78	82	187	java/io/IOException
    //   82	108	195	org/json/JSONException
    //   67	74	209	finally
    //   67	74	217	java/io/IOException
    //   67	74	221	java/io/FileNotFoundException
  }

  public static void uploadLogFile()
  {
    try
    {
      if (logFileDir == null)
        throw new IllegalStateException("logFileDir can not be null! call 'LOG.init' first!");
    }
    finally
    {
    }
    if ((!logFileDir.exists()) || (!logFileDir.isDirectory()) || (logFileDir.list().length == 0))
      if (DBG)
        Log.d(TAG, "log Dir not exist or no log");
    while (true)
    {
      return;
      ArrayList localArrayList = new ArrayList();
      Object localObject2 = logFileDir.list();
      int j = localObject2.length;
      int i = 0;
      while (true)
      {
        if (i >= j)
        {
          Collections.sort(localArrayList);
          String str = (String)localArrayList.get(localArrayList.size() - 1);
          localObject2 = str;
          j = localArrayList.size();
          i = j;
          if (!str.equals(getCurLogFileName()))
            break label214;
          if (localArrayList.size() >= 2)
            break label191;
          if (!DBG)
            break;
          Log.d(TAG, "only log of today");
          break;
        }
        localArrayList.add(localObject2[i]);
        i += 1;
      }
      label191: localObject2 = (String)localArrayList.get(localArrayList.size() - 2);
      i = j - 1;
      label214: if (!doUpload(toJsonString((String)localObject2)))
        j = i - 1;
      while (i < j)
      {
        localObject2 = (String)localArrayList.get(i);
        new File(logFileDir, (String)localObject2).delete();
        i += 1;
        continue;
        j = i;
        if (DBG)
        {
          Log.d(TAG, "upload success");
          j = i;
        }
        i = 0;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.mobilesecuritysdk.deviceID.LOG
 * JD-Core Version:    0.6.2
 */