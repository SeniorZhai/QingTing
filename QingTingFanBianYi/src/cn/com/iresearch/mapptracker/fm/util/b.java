package cn.com.iresearch.mapptracker.fm.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;
import cn.com.iresearch.mapptracker.fm.IRMonitor;
import cn.com.iresearch.mapptracker.fm.a.a;
import cn.com.iresearch.mapptracker.fm.dao.SessionInfo;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public final class b
{
  private a a;
  private Context b;

  public b(a parama, Context paramContext)
  {
    this.a = parama;
    this.b = paramContext;
  }

  private static String a()
  {
    return (Long.parseLong(b()) + 63529L) * 5L + (int)Math.round(Math.random() * 89999.0D + 10000.0D);
  }

  private static StringBuilder a(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Environment.getExternalStorageDirectory().getAbsolutePath());
    localStringBuilder.append(File.separator);
    localStringBuilder.append("ickeck");
    localStringBuilder.append(File.separator);
    localStringBuilder.append(paramString);
    return localStringBuilder;
  }

  private static List<String> a(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = (ActivityManager)paramContext.getSystemService("activity");
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = b(paramContext);
    try
    {
      localObject1 = ((ActivityManager)localObject1).getRecentTasks(100, 0).iterator();
      while (true)
      {
        if (!((Iterator)localObject1).hasNext())
          return localArrayList;
        Object localObject2 = localPackageManager.resolveActivity(((ActivityManager.RecentTaskInfo)((Iterator)localObject1).next()).baseIntent, 0);
        if (localObject2 != null)
        {
          localObject2 = ((ResolveInfo)localObject2).activityInfo.packageName;
          if ((!paramContext.contains(localObject2)) && (!localArrayList.contains(localObject2)))
            localArrayList.add(localObject2);
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return localArrayList;
  }

  private static List<String> a(List<String> paramList, int paramInt)
  {
    int i = paramList.size();
    Object localObject;
    if ((paramList == null) || (i == 0))
      localObject = null;
    do
    {
      return localObject;
      localObject = new ArrayList();
    }
    while (paramInt > i);
    return paramList.subList(0, paramInt);
  }

  private static void a(String paramString, List<String> paramList)
  {
    if ((paramList == null) || (!paramList.contains(paramString)))
    {
      Log.i("ACHECKTAG", "alist:" + paramString);
      a(paramString, a("alist").toString(), true);
    }
  }

  private static void a(List<String> paramList)
  {
    int i;
    if (paramList != null)
    {
      a("", a("atemp").toString(), false);
      i = 0;
      if (i < paramList.size());
    }
    else
    {
      return;
    }
    Object localObject = (String)paramList.get(i);
    String str = localObject + "\n";
    if (i == paramList.size() - 1);
    while (true)
    {
      a((String)localObject, a("atemp").toString(), true);
      i += 1;
      break;
      localObject = str;
    }
  }

  private void a(List<String> paramList1, List<String> paramList2)
  {
    SessionInfo localSessionInfo;
    int i;
    if (paramList1 != null)
    {
      localSessionInfo = new SessionInfo();
      localSessionInfo.setEnd_time(Long.valueOf(Long.parseLong(c(this.b))).longValue());
      localSessionInfo.setInapp(3L);
      i = 0;
      if (i < paramList1.size());
    }
    else
    {
      return;
    }
    Object localObject = (String)paramList1.get(i);
    String str;
    if (!paramList2.contains(localObject))
    {
      str = "inapp=3 and page_name='" + (String)localObject + "'";
      if ((this.a.a(SessionInfo.class, str) == null) || (paramList2.isEmpty()))
      {
        long l = (int)Math.round(Math.random() * 90.0D + 10.0D);
        localSessionInfo.setPage_name((String)localObject);
        localSessionInfo.setSessionid(a());
        localSessionInfo.setDuration(l);
        localSessionInfo.setStart_time(l + System.currentTimeMillis() / 1000L);
        this.a.a(localSessionInfo);
        str = localObject + "\n";
        if (i != paramList1.size() - 1)
          break label236;
      }
    }
    while (true)
    {
      a((String)localObject, paramList2);
      i += 1;
      break;
      label236: localObject = str;
    }
  }

  // ERROR //
  private static boolean a(String paramString1, String paramString2, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: iconst_1
    //   7: istore 8
    //   9: new 73	java/io/File
    //   12: dup
    //   13: aload_1
    //   14: invokespecial 274	java/io/File:<init>	(Ljava/lang/String;)V
    //   17: astore 6
    //   19: aload 6
    //   21: invokevirtual 277	java/io/File:getParentFile	()Ljava/io/File;
    //   24: invokevirtual 280	java/io/File:exists	()Z
    //   27: ifne +12 -> 39
    //   30: aload 6
    //   32: invokevirtual 277	java/io/File:getParentFile	()Ljava/io/File;
    //   35: invokevirtual 283	java/io/File:mkdirs	()Z
    //   38: pop
    //   39: aload 6
    //   41: invokevirtual 277	java/io/File:getParentFile	()Ljava/io/File;
    //   44: invokevirtual 280	java/io/File:exists	()Z
    //   47: ifne +5 -> 52
    //   50: iconst_0
    //   51: ireturn
    //   52: new 285	java/io/BufferedReader
    //   55: dup
    //   56: new 287	java/io/StringReader
    //   59: dup
    //   60: aload_0
    //   61: invokespecial 288	java/io/StringReader:<init>	(Ljava/lang/String;)V
    //   64: invokespecial 291	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   67: astore_3
    //   68: new 293	java/io/BufferedWriter
    //   71: dup
    //   72: new 295	java/io/FileWriter
    //   75: dup
    //   76: aload 6
    //   78: iload_2
    //   79: invokespecial 298	java/io/FileWriter:<init>	(Ljava/io/File;Z)V
    //   82: invokespecial 301	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   85: astore_0
    //   86: sipush 1024
    //   89: newarray char
    //   91: astore 4
    //   93: aload_3
    //   94: aload 4
    //   96: invokevirtual 305	java/io/BufferedReader:read	([C)I
    //   99: istore 7
    //   101: iload 7
    //   103: iconst_m1
    //   104: if_icmpne +45 -> 149
    //   107: aload_0
    //   108: invokevirtual 308	java/io/BufferedWriter:flush	()V
    //   111: aload_3
    //   112: invokevirtual 311	java/io/BufferedReader:close	()V
    //   115: aload_0
    //   116: invokevirtual 312	java/io/BufferedWriter:close	()V
    //   119: iload 8
    //   121: istore_2
    //   122: new 21	java/lang/StringBuilder
    //   125: dup
    //   126: iload_2
    //   127: invokestatic 315	java/lang/String:valueOf	(Z)Ljava/lang/String;
    //   130: invokespecial 42	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   133: ldc_w 317
    //   136: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: aload_1
    //   140: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: invokevirtual 63	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   146: pop
    //   147: iload_2
    //   148: ireturn
    //   149: aload_0
    //   150: aload 4
    //   152: iconst_0
    //   153: iload 7
    //   155: invokevirtual 321	java/io/BufferedWriter:write	([CII)V
    //   158: goto -65 -> 93
    //   161: astore 5
    //   163: aload_0
    //   164: astore 4
    //   166: aload_3
    //   167: astore_0
    //   168: aload 4
    //   170: astore_3
    //   171: aload 5
    //   173: astore 4
    //   175: aload 4
    //   177: invokevirtual 159	java/lang/Exception:printStackTrace	()V
    //   180: aload_0
    //   181: ifnull +7 -> 188
    //   184: aload_0
    //   185: invokevirtual 311	java/io/BufferedReader:close	()V
    //   188: aload_3
    //   189: ifnull +128 -> 317
    //   192: aload_3
    //   193: invokevirtual 312	java/io/BufferedWriter:close	()V
    //   196: iconst_0
    //   197: istore_2
    //   198: goto -76 -> 122
    //   201: astore_0
    //   202: aload_0
    //   203: invokevirtual 322	java/io/IOException:printStackTrace	()V
    //   206: iconst_0
    //   207: istore_2
    //   208: goto -86 -> 122
    //   211: astore_0
    //   212: aconst_null
    //   213: astore_3
    //   214: aload 4
    //   216: astore_1
    //   217: aload_3
    //   218: ifnull +7 -> 225
    //   221: aload_3
    //   222: invokevirtual 311	java/io/BufferedReader:close	()V
    //   225: aload_1
    //   226: ifnull +7 -> 233
    //   229: aload_1
    //   230: invokevirtual 312	java/io/BufferedWriter:close	()V
    //   233: aload_0
    //   234: athrow
    //   235: astore_1
    //   236: aload_1
    //   237: invokevirtual 322	java/io/IOException:printStackTrace	()V
    //   240: goto -7 -> 233
    //   243: astore_0
    //   244: aload_0
    //   245: invokevirtual 322	java/io/IOException:printStackTrace	()V
    //   248: iload 8
    //   250: istore_2
    //   251: goto -129 -> 122
    //   254: astore_0
    //   255: goto -67 -> 188
    //   258: astore_3
    //   259: goto -34 -> 225
    //   262: astore_3
    //   263: goto -148 -> 115
    //   266: astore_0
    //   267: aload 4
    //   269: astore_1
    //   270: goto -53 -> 217
    //   273: astore 4
    //   275: aload_0
    //   276: astore_1
    //   277: aload 4
    //   279: astore_0
    //   280: goto -63 -> 217
    //   283: astore_1
    //   284: aload_0
    //   285: astore 4
    //   287: aload_1
    //   288: astore_0
    //   289: aload_3
    //   290: astore_1
    //   291: aload 4
    //   293: astore_3
    //   294: goto -77 -> 217
    //   297: astore 4
    //   299: aconst_null
    //   300: astore_0
    //   301: aload 5
    //   303: astore_3
    //   304: goto -129 -> 175
    //   307: astore 4
    //   309: aload_3
    //   310: astore_0
    //   311: aload 5
    //   313: astore_3
    //   314: goto -139 -> 175
    //   317: iconst_0
    //   318: istore_2
    //   319: goto -197 -> 122
    //
    // Exception table:
    //   from	to	target	type
    //   86	93	161	java/lang/Exception
    //   93	101	161	java/lang/Exception
    //   107	111	161	java/lang/Exception
    //   149	158	161	java/lang/Exception
    //   192	196	201	java/io/IOException
    //   9	39	211	finally
    //   39	50	211	finally
    //   52	68	211	finally
    //   229	233	235	java/io/IOException
    //   115	119	243	java/io/IOException
    //   184	188	254	java/io/IOException
    //   221	225	258	java/io/IOException
    //   111	115	262	java/io/IOException
    //   68	86	266	finally
    //   86	93	273	finally
    //   93	101	273	finally
    //   107	111	273	finally
    //   149	158	273	finally
    //   175	180	283	finally
    //   9	39	297	java/lang/Exception
    //   39	50	297	java/lang/Exception
    //   52	68	297	java/lang/Exception
    //   68	86	307	java/lang/Exception
  }

  @SuppressLint({"SimpleDateFormat"})
  private static String b()
  {
    try
    {
      Object localObject = new Date(System.currentTimeMillis());
      localObject = new SimpleDateFormat("yyyyMMdd").format((Date)localObject);
      return localObject;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  private static List<String> b(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.queryIntentActivities(localIntent, 65536).iterator();
    while (true)
    {
      if (!paramContext.hasNext())
        return localArrayList;
      localArrayList.add(((ResolveInfo)paramContext.next()).activityInfo.packageName);
    }
  }

  private static String c(Context paramContext)
  {
    int j = 0;
    while (true)
    {
      int i;
      String str;
      try
      {
        if (paramContext.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", paramContext.getPackageName()) != 0)
          break label116;
        i = 1;
        if (i != 0)
        {
          str = "";
          if (paramContext.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", paramContext.getPackageName()) == 0)
            break label121;
          i = j;
          if (i != 0)
          {
            str = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
            break label110;
            if (IRMonitor.c)
              Log.e("MAT_SESSION", "deviceId is null");
            return "";
          }
        }
        else
        {
          if (IRMonitor.c)
            Log.e("MAT_SESSION", "lost----->android.permission.READ_PHONE_STATE");
          return "";
        }
      }
      catch (Exception paramContext)
      {
        return "";
      }
      label110: if (str != null)
      {
        return str;
        label116: i = 0;
        continue;
        label121: i = 1;
      }
    }
  }

  // ERROR //
  private static List<String> c()
  {
    // Byte code:
    //   0: new 73	java/io/File
    //   3: dup
    //   4: ldc 194
    //   6: invokestatic 185	cn/com/iresearch/mapptracker/fm/util/b:a	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   9: invokevirtual 63	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   12: invokespecial 274	java/io/File:<init>	(Ljava/lang/String;)V
    //   15: astore_0
    //   16: aconst_null
    //   17: astore_1
    //   18: new 89	java/util/ArrayList
    //   21: dup
    //   22: invokespecial 90	java/util/ArrayList:<init>	()V
    //   25: astore_2
    //   26: new 285	java/io/BufferedReader
    //   29: dup
    //   30: new 387	java/io/FileReader
    //   33: dup
    //   34: aload_0
    //   35: invokespecial 390	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   38: invokespecial 291	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   41: astore_0
    //   42: aload_0
    //   43: invokevirtual 393	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   46: astore_1
    //   47: aload_1
    //   48: ifnonnull +31 -> 79
    //   51: aload_0
    //   52: invokevirtual 311	java/io/BufferedReader:close	()V
    //   55: aload_0
    //   56: invokevirtual 311	java/io/BufferedReader:close	()V
    //   59: new 21	java/lang/StringBuilder
    //   62: dup
    //   63: ldc_w 395
    //   66: invokespecial 42	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   69: aload_2
    //   70: invokevirtual 398	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   73: invokevirtual 63	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   76: pop
    //   77: aload_2
    //   78: areturn
    //   79: aload_2
    //   80: aload_1
    //   81: invokeinterface 153 2 0
    //   86: ifne -44 -> 42
    //   89: aload_2
    //   90: aload_1
    //   91: invokeinterface 156 2 0
    //   96: pop
    //   97: goto -55 -> 42
    //   100: astore_1
    //   101: aload_0
    //   102: ifnull -43 -> 59
    //   105: aload_0
    //   106: invokevirtual 311	java/io/BufferedReader:close	()V
    //   109: goto -50 -> 59
    //   112: astore_0
    //   113: goto -54 -> 59
    //   116: astore_0
    //   117: aload_1
    //   118: ifnull +7 -> 125
    //   121: aload_1
    //   122: invokevirtual 311	java/io/BufferedReader:close	()V
    //   125: aload_0
    //   126: athrow
    //   127: astore_1
    //   128: goto -3 -> 125
    //   131: astore_0
    //   132: goto -73 -> 59
    //   135: astore_2
    //   136: aload_0
    //   137: astore_1
    //   138: aload_2
    //   139: astore_0
    //   140: goto -23 -> 117
    //   143: astore_0
    //   144: aconst_null
    //   145: astore_0
    //   146: goto -45 -> 101
    //
    // Exception table:
    //   from	to	target	type
    //   42	47	100	java/io/IOException
    //   51	55	100	java/io/IOException
    //   79	97	100	java/io/IOException
    //   105	109	112	java/io/IOException
    //   26	42	116	finally
    //   121	125	127	java/io/IOException
    //   55	59	131	java/io/IOException
    //   42	47	135	finally
    //   51	55	135	finally
    //   79	97	135	finally
    //   26	42	143	java/io/IOException
  }

  // ERROR //
  private static List<String> d()
  {
    // Byte code:
    //   0: new 73	java/io/File
    //   3: dup
    //   4: ldc 183
    //   6: invokestatic 185	cn/com/iresearch/mapptracker/fm/util/b:a	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   9: invokevirtual 63	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   12: invokespecial 274	java/io/File:<init>	(Ljava/lang/String;)V
    //   15: astore_0
    //   16: aconst_null
    //   17: astore_1
    //   18: new 89	java/util/ArrayList
    //   21: dup
    //   22: invokespecial 90	java/util/ArrayList:<init>	()V
    //   25: astore_2
    //   26: new 285	java/io/BufferedReader
    //   29: dup
    //   30: new 387	java/io/FileReader
    //   33: dup
    //   34: aload_0
    //   35: invokespecial 390	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   38: invokespecial 291	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   41: astore_0
    //   42: aload_0
    //   43: invokevirtual 393	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   46: astore_1
    //   47: aload_1
    //   48: ifnonnull +13 -> 61
    //   51: aload_0
    //   52: invokevirtual 311	java/io/BufferedReader:close	()V
    //   55: aload_0
    //   56: invokevirtual 311	java/io/BufferedReader:close	()V
    //   59: aload_2
    //   60: areturn
    //   61: aload_2
    //   62: aload_1
    //   63: invokeinterface 153 2 0
    //   68: ifne -26 -> 42
    //   71: aload_2
    //   72: aload_1
    //   73: invokeinterface 156 2 0
    //   78: pop
    //   79: goto -37 -> 42
    //   82: astore_1
    //   83: aload_0
    //   84: ifnull -25 -> 59
    //   87: aload_0
    //   88: invokevirtual 311	java/io/BufferedReader:close	()V
    //   91: aload_2
    //   92: areturn
    //   93: astore_0
    //   94: aload_2
    //   95: areturn
    //   96: astore_0
    //   97: aload_1
    //   98: ifnull +7 -> 105
    //   101: aload_1
    //   102: invokevirtual 311	java/io/BufferedReader:close	()V
    //   105: aload_0
    //   106: athrow
    //   107: astore_1
    //   108: goto -3 -> 105
    //   111: astore_0
    //   112: aload_2
    //   113: areturn
    //   114: astore_2
    //   115: aload_0
    //   116: astore_1
    //   117: aload_2
    //   118: astore_0
    //   119: goto -22 -> 97
    //   122: astore_0
    //   123: aconst_null
    //   124: astore_0
    //   125: goto -42 -> 83
    //
    // Exception table:
    //   from	to	target	type
    //   42	47	82	java/io/IOException
    //   51	55	82	java/io/IOException
    //   61	79	82	java/io/IOException
    //   87	91	93	java/io/IOException
    //   26	42	96	finally
    //   101	105	107	java/io/IOException
    //   55	59	111	java/io/IOException
    //   42	47	114	finally
    //   51	55	114	finally
    //   61	79	114	finally
    //   26	42	122	java/io/IOException
  }

  public final void a(SessionInfo paramSessionInfo)
  {
    int i;
    int j;
    try
    {
      List localList1 = c();
      List localList2 = d();
      List localList3 = a(this.b);
      paramSessionInfo.setEnd_time(Long.valueOf(Long.parseLong(c(this.b))).longValue());
      paramSessionInfo.setInapp(3L);
      int k = localList3.size();
      i = 0;
      Object localObject = new ArrayList();
      boolean bool = localList1.isEmpty();
      j = 0;
      if (j >= k)
      {
        a((List)localObject, localList2);
        a(localList3);
        return;
      }
      String str1 = (String)localList3.get(j);
      long l;
      if (bool)
      {
        l = (int)Math.round(Math.random() * 90.0D + 10.0D);
        paramSessionInfo.setPage_name(str1);
        paramSessionInfo.setSessionid(a());
        paramSessionInfo.setDuration(l);
        paramSessionInfo.setStart_time(l + System.currentTimeMillis() / 1000L);
        this.a.a(paramSessionInfo);
        a(str1 + "\n", localList2);
        new StringBuilder("no savetemp save:").append(str1).toString();
      }
      else if ((!localList1.contains(str1)) && (!localList2.contains(str1)))
      {
        new StringBuilder("find new open app:").append(str1).toString();
        l = (int)Math.round(Math.random() * 90.0D + 10.0D);
        paramSessionInfo.setPage_name(str1);
        paramSessionInfo.setSessionid(a());
        paramSessionInfo.setDuration(l);
        paramSessionInfo.setStart_time(l + System.currentTimeMillis() / 1000L);
        this.a.a(paramSessionInfo);
        a(str1 + "\n", localList2);
      }
      else
      {
        int m = localList1.indexOf(str1);
        if (m <= j)
        {
          i = 0;
        }
        else
        {
          localObject = a(localList3, j);
          if ((i != 0) && (j - 1 >= 0))
          {
            String str2 = (String)localList3.get(j - 1);
            if (localList1.indexOf(str2) > m)
            {
              new StringBuilder("n(").append(str1).append(")...n-1(").append(str2).append(") = ba").toString();
              localObject = a(localList3, j);
            }
            else
            {
              new StringBuilder("n(").append(str1).append(")...n-1(").append(str2).append(") = ab").toString();
            }
          }
        }
      }
    }
    catch (Exception paramSessionInfo)
    {
      paramSessionInfo.printStackTrace();
      return;
    }
    while (true)
    {
      j += 1;
      break;
      i = 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.util.b
 * JD-Core Version:    0.6.2
 */