package com.intowow.sdk.j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class f
{
  // ERROR //
  public static String a(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 10	java/lang/StringBuilder
    //   5: dup
    //   6: ldc 12
    //   8: invokespecial 16	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   11: astore 4
    //   13: new 18	java/io/File
    //   16: dup
    //   17: aload_0
    //   18: invokespecial 19	java/io/File:<init>	(Ljava/lang/String;)V
    //   21: invokevirtual 23	java/io/File:exists	()Z
    //   24: istore 5
    //   26: iload 5
    //   28: ifne +29 -> 57
    //   31: iconst_0
    //   32: ifeq +11 -> 43
    //   35: new 25	java/lang/NullPointerException
    //   38: dup
    //   39: invokespecial 28	java/lang/NullPointerException:<init>	()V
    //   42: athrow
    //   43: iconst_0
    //   44: ifeq +11 -> 55
    //   47: new 25	java/lang/NullPointerException
    //   50: dup
    //   51: invokespecial 28	java/lang/NullPointerException:<init>	()V
    //   54: athrow
    //   55: aconst_null
    //   56: areturn
    //   57: new 30	java/io/InputStreamReader
    //   60: dup
    //   61: new 32	java/io/FileInputStream
    //   64: dup
    //   65: aload_0
    //   66: invokespecial 33	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   69: aload_2
    //   70: invokespecial 36	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   73: astore_0
    //   74: new 38	java/io/BufferedReader
    //   77: dup
    //   78: aload_0
    //   79: invokespecial 41	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   82: astore_2
    //   83: aload_2
    //   84: invokevirtual 45	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   87: astore_3
    //   88: aload_3
    //   89: ifnonnull +25 -> 114
    //   92: aload_0
    //   93: ifnull +7 -> 100
    //   96: aload_0
    //   97: invokevirtual 48	java/io/InputStreamReader:close	()V
    //   100: aload_2
    //   101: ifnull +7 -> 108
    //   104: aload_2
    //   105: invokevirtual 49	java/io/BufferedReader:close	()V
    //   108: aload 4
    //   110: invokevirtual 52	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   113: areturn
    //   114: aload 4
    //   116: aload_3
    //   117: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: aload_1
    //   121: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: pop
    //   125: goto -42 -> 83
    //   128: astore_1
    //   129: aload_2
    //   130: astore_1
    //   131: aload_0
    //   132: ifnull +7 -> 139
    //   135: aload_0
    //   136: invokevirtual 48	java/io/InputStreamReader:close	()V
    //   139: aload_1
    //   140: ifnull -32 -> 108
    //   143: aload_1
    //   144: invokevirtual 49	java/io/BufferedReader:close	()V
    //   147: goto -39 -> 108
    //   150: astore_0
    //   151: goto -43 -> 108
    //   154: astore_1
    //   155: aconst_null
    //   156: astore_0
    //   157: aconst_null
    //   158: astore_2
    //   159: aload_0
    //   160: ifnull +7 -> 167
    //   163: aload_0
    //   164: invokevirtual 48	java/io/InputStreamReader:close	()V
    //   167: aload_2
    //   168: ifnull +7 -> 175
    //   171: aload_2
    //   172: invokevirtual 49	java/io/BufferedReader:close	()V
    //   175: aload_1
    //   176: athrow
    //   177: astore_0
    //   178: goto -70 -> 108
    //   181: astore_0
    //   182: goto -7 -> 175
    //   185: astore_1
    //   186: aconst_null
    //   187: astore_2
    //   188: goto -29 -> 159
    //   191: astore_1
    //   192: goto -33 -> 159
    //   195: astore_0
    //   196: aconst_null
    //   197: astore_0
    //   198: aload_3
    //   199: astore_1
    //   200: goto -69 -> 131
    //   203: astore_1
    //   204: aload_3
    //   205: astore_1
    //   206: goto -75 -> 131
    //   209: astore_0
    //   210: aconst_null
    //   211: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   83	88	128	java/lang/Exception
    //   114	125	128	java/lang/Exception
    //   135	139	150	java/lang/Exception
    //   143	147	150	java/lang/Exception
    //   13	26	154	finally
    //   57	74	154	finally
    //   96	100	177	java/lang/Exception
    //   104	108	177	java/lang/Exception
    //   163	167	181	java/lang/Exception
    //   171	175	181	java/lang/Exception
    //   74	83	185	finally
    //   83	88	191	finally
    //   114	125	191	finally
    //   13	26	195	java/lang/Exception
    //   57	74	195	java/lang/Exception
    //   74	83	203	java/lang/Exception
    //   35	43	209	java/lang/Exception
    //   47	55	209	java/lang/Exception
  }

  public static boolean a(String paramString1, String paramString2)
  {
    if (paramString2 == null);
    File localFile;
    do
    {
      return false;
      localFile = new File(paramString1);
    }
    while ((localFile == null) || (!localFile.exists()) || (!paramString2.equals(k.d(paramString1))));
    return true;
  }

  public static boolean a(String paramString1, String paramString2, boolean paramBoolean)
  {
    try
    {
      paramString1 = new BufferedWriter(new FileWriter(paramString1, paramBoolean));
      paramString1.write(paramString2);
      if (paramString1 != null)
      {
        paramString1.flush();
        paramString1.close();
      }
      return true;
    }
    catch (Exception paramString1)
    {
    }
    return false;
  }

  public static boolean b(String paramString1, String paramString2)
  {
    return a(paramString1, paramString2, false);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.j.f
 * JD-Core Version:    0.6.2
 */