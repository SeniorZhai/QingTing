package fm.qingting.utils;

public class UserRetention
{
  private static String eventname = "UserRetention";
  private static String filename = "user-retention.dat";

  // ERROR //
  public static void appStarted(android.content.Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 31	android/content/Context:fileList	()[Ljava/lang/String;
    //   4: invokestatic 37	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   7: getstatic 13	fm/qingting/utils/UserRetention:filename	Ljava/lang/String;
    //   10: invokeinterface 43 2 0
    //   15: ifne +117 -> 132
    //   18: getstatic 17	fm/qingting/utils/UserRetention:eventname	Ljava/lang/String;
    //   21: ldc 45
    //   23: invokestatic 51	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   26: pop
    //   27: invokestatic 57	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   30: iconst_5
    //   31: invokevirtual 61	java/util/Calendar:get	(I)I
    //   34: invokestatic 67	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   37: astore_1
    //   38: aload_0
    //   39: getstatic 13	fm/qingting/utils/UserRetention:filename	Ljava/lang/String;
    //   42: iconst_0
    //   43: invokevirtual 71	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   46: astore_0
    //   47: aload_0
    //   48: new 73	java/lang/StringBuilder
    //   51: dup
    //   52: invokespecial 74	java/lang/StringBuilder:<init>	()V
    //   55: aload_1
    //   56: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: bipush 10
    //   61: invokevirtual 81	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   64: aload_1
    //   65: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: bipush 10
    //   70: invokevirtual 81	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   73: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   76: invokevirtual 89	java/lang/String:getBytes	()[B
    //   79: invokevirtual 95	java/io/FileOutputStream:write	([B)V
    //   82: aload_0
    //   83: invokevirtual 98	java/io/FileOutputStream:close	()V
    //   86: invokestatic 103	fm/qingting/utils/QTMSGManage:getInstance	()Lfm/qingting/utils/QTMSGManage;
    //   89: getstatic 17	fm/qingting/utils/UserRetention:eventname	Ljava/lang/String;
    //   92: new 73	java/lang/StringBuilder
    //   95: dup
    //   96: invokespecial 74	java/lang/StringBuilder:<init>	()V
    //   99: ldc 105
    //   101: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: aload_1
    //   105: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: bipush 95
    //   110: invokevirtual 81	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   113: aload_1
    //   114: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   117: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   120: invokevirtual 109	fm/qingting/utils/QTMSGManage:sendStatistcsMessage	(Ljava/lang/String;Ljava/lang/String;)V
    //   123: return
    //   124: astore_0
    //   125: aload_0
    //   126: invokevirtual 112	java/lang/Exception:printStackTrace	()V
    //   129: goto -43 -> 86
    //   132: getstatic 17	fm/qingting/utils/UserRetention:eventname	Ljava/lang/String;
    //   135: ldc 114
    //   137: invokestatic 51	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   140: pop
    //   141: new 116	java/io/BufferedReader
    //   144: dup
    //   145: new 118	java/io/InputStreamReader
    //   148: dup
    //   149: aload_0
    //   150: getstatic 13	fm/qingting/utils/UserRetention:filename	Ljava/lang/String;
    //   153: invokevirtual 122	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   156: invokespecial 125	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   159: invokespecial 128	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   162: astore 4
    //   164: aload 4
    //   166: invokevirtual 131	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   169: invokevirtual 134	java/lang/String:trim	()Ljava/lang/String;
    //   172: astore_1
    //   173: aconst_null
    //   174: astore_2
    //   175: aload 4
    //   177: invokevirtual 131	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   180: astore_3
    //   181: aload_3
    //   182: ifnull +13 -> 195
    //   185: aload_3
    //   186: invokevirtual 134	java/lang/String:trim	()Ljava/lang/String;
    //   189: astore_3
    //   190: aload_3
    //   191: astore_2
    //   192: goto -17 -> 175
    //   195: aload 4
    //   197: invokevirtual 135	java/io/BufferedReader:close	()V
    //   200: invokestatic 57	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   203: iconst_5
    //   204: invokevirtual 61	java/util/Calendar:get	(I)I
    //   207: invokestatic 67	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   210: astore_3
    //   211: aload_3
    //   212: ifnull +122 -> 334
    //   215: aload_2
    //   216: ifnull +118 -> 334
    //   219: aload_2
    //   220: aload_3
    //   221: invokevirtual 138	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   224: ifne +110 -> 334
    //   227: getstatic 17	fm/qingting/utils/UserRetention:eventname	Ljava/lang/String;
    //   230: ldc 140
    //   232: invokestatic 51	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   235: pop
    //   236: aload_0
    //   237: getstatic 13	fm/qingting/utils/UserRetention:filename	Ljava/lang/String;
    //   240: ldc 141
    //   242: invokevirtual 71	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   245: astore_0
    //   246: aload_0
    //   247: new 73	java/lang/StringBuilder
    //   250: dup
    //   251: invokespecial 74	java/lang/StringBuilder:<init>	()V
    //   254: aload_3
    //   255: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: bipush 10
    //   260: invokevirtual 81	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   263: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   266: invokevirtual 89	java/lang/String:getBytes	()[B
    //   269: invokevirtual 95	java/io/FileOutputStream:write	([B)V
    //   272: aload_0
    //   273: invokevirtual 98	java/io/FileOutputStream:close	()V
    //   276: invokestatic 103	fm/qingting/utils/QTMSGManage:getInstance	()Lfm/qingting/utils/QTMSGManage;
    //   279: getstatic 17	fm/qingting/utils/UserRetention:eventname	Ljava/lang/String;
    //   282: new 73	java/lang/StringBuilder
    //   285: dup
    //   286: invokespecial 74	java/lang/StringBuilder:<init>	()V
    //   289: ldc 143
    //   291: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   294: aload_1
    //   295: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   298: bipush 95
    //   300: invokevirtual 81	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   303: aload_3
    //   304: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   307: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   310: invokevirtual 109	fm/qingting/utils/QTMSGManage:sendStatistcsMessage	(Ljava/lang/String;Ljava/lang/String;)V
    //   313: return
    //   314: astore_3
    //   315: aconst_null
    //   316: astore_1
    //   317: aconst_null
    //   318: astore_2
    //   319: aload_3
    //   320: invokevirtual 112	java/lang/Exception:printStackTrace	()V
    //   323: goto -123 -> 200
    //   326: astore_0
    //   327: aload_0
    //   328: invokevirtual 112	java/lang/Exception:printStackTrace	()V
    //   331: goto -55 -> 276
    //   334: getstatic 17	fm/qingting/utils/UserRetention:eventname	Ljava/lang/String;
    //   337: ldc 145
    //   339: invokestatic 51	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   342: pop
    //   343: return
    //   344: astore_3
    //   345: goto -26 -> 319
    //
    // Exception table:
    //   from	to	target	type
    //   38	86	124	java/lang/Exception
    //   141	173	314	java/lang/Exception
    //   236	276	326	java/lang/Exception
    //   175	181	344	java/lang/Exception
    //   185	190	344	java/lang/Exception
    //   195	200	344	java/lang/Exception
  }

  public static void setEventName(String paramString)
  {
    eventname = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.UserRetention
 * JD-Core Version:    0.6.2
 */