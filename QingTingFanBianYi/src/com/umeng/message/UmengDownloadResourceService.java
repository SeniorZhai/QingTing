package com.umeng.message;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.IBinder;
import com.umeng.common.message.Log;
import com.umeng.message.entity.UMessage;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.json.JSONObject;

public class UmengDownloadResourceService extends Service
{
  public static final String TAG = UmengDownloadResourceService.class.getSimpleName();
  private static final String d = ".tmp";
  private static final String e = "RETRY_TIME";
  private static final String f = "OPERATIOIN";
  private static final int g = 1;
  private static final int h = 2;
  private static final long i = 1048576L;
  private static final long j = 86400000L;
  private static final int k = 300000;
  private static final int l = 3;
  private static Thread m;
  ScheduledThreadPoolExecutor a;
  Context b;
  ArrayList<String> c;

  private static long a(File paramFile)
  {
    long l2;
    if ((paramFile == null) || (!paramFile.exists()) || (!paramFile.isDirectory()))
    {
      l2 = 0L;
      return l2;
    }
    Stack localStack = new Stack();
    localStack.clear();
    localStack.push(paramFile);
    long l1 = 0L;
    while (true)
    {
      l2 = l1;
      if (localStack.isEmpty())
        break;
      paramFile = ((File)localStack.pop()).listFiles();
      int i1 = paramFile.length;
      int n = 0;
      while (n < i1)
      {
        Object localObject = paramFile[n];
        l2 = l1;
        if (!localObject.isDirectory())
          l2 = l1 + localObject.length();
        n += 1;
        l1 = l2;
      }
    }
  }

  private PendingIntent a(UMessage paramUMessage, int paramInt)
  {
    Object localObject = paramUMessage.getRaw().toString();
    int n = paramUMessage.msg_id.hashCode();
    Intent localIntent = new Intent(this.b, UmengDownloadResourceService.class);
    localIntent.putExtra("body", (String)localObject);
    localIntent.putExtra("OPERATIOIN", 2);
    localIntent.putExtra("RETRY_TIME", paramInt);
    localObject = PendingIntent.getService(this.b, n, localIntent, 134217728);
    Log.a(TAG, "PendingIntent: msgId:" + paramUMessage.msg_id + ",requestCode:" + n + ",retryTime:" + paramInt);
    return localObject;
  }

  private static void b(File paramFile, long paramLong)
  {
    if ((paramFile == null) || (!paramFile.exists()) || (!paramFile.canWrite()) || (!paramFile.isDirectory()));
    while (true)
    {
      return;
      paramFile = paramFile.listFiles();
      int i1 = paramFile.length;
      int n = 0;
      while (n < i1)
      {
        Object localObject = paramFile[n];
        if ((!localObject.isDirectory()) && (System.currentTimeMillis() - localObject.lastModified() > paramLong))
          localObject.delete();
        n += 1;
      }
    }
  }

  public static void checkDir(File arg0, long paramLong1, long paramLong2)
    throws IOException
  {
    if ((???.exists()) && (a(???.getCanonicalFile()) > paramLong1))
    {
      if (m == null)
        m = new Thread(new UmengDownloadResourceService.1(???, paramLong2));
      synchronized (m)
      {
        m.start();
        return;
      }
    }
  }

  public static String getMessageResourceFolder(Context paramContext, UMessage paramUMessage)
  {
    String str = paramContext.getCacheDir() + "/umeng_push/";
    paramContext = str;
    if (paramUMessage != null)
    {
      paramContext = str;
      if (paramUMessage.msg_id != null)
        paramContext = str + paramUMessage.msg_id + "/";
    }
    return paramContext;
  }

  public void checkCache()
  {
    try
    {
      checkDir(new File(getMessageResourceFolder(this.b, null)), 1048576L, 86400000L);
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public void close(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      return;
    }
    catch (Exception paramCloseable)
    {
      paramCloseable.printStackTrace();
    }
  }

  public void deleteAlarm(UMessage paramUMessage, int paramInt)
  {
    Log.a(TAG, "deleteAlarm");
    paramUMessage = a(paramUMessage, paramInt);
    ((AlarmManager)getSystemService("alarm")).cancel(paramUMessage);
  }

  public void downloadResource(UMessage paramUMessage, int paramInt)
  {
    paramUMessage = new DownloadResourceTask(paramUMessage, paramInt);
    if (Build.VERSION.SDK_INT >= 11)
    {
      paramUMessage.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
      return;
    }
    paramUMessage.execute(new Void[0]);
  }

  public void notification(UMessage paramUMessage)
  {
    UHandler localUHandler = PushAgent.getInstance(this).getMessageHandler();
    if (localUHandler != null)
      localUHandler.handleMessage(this, paramUMessage);
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    this.a = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 4);
    this.b = this;
    this.c = new ArrayList();
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (paramIntent == null)
      return super.onStartCommand(paramIntent, paramInt1, paramInt2);
    int n = paramIntent.getIntExtra("OPERATIOIN", 2);
    int i1 = paramIntent.getIntExtra("RETRY_TIME", 3);
    Object localObject = paramIntent.getStringExtra("body");
    try
    {
      localObject = new UMessage(new JSONObject((String)localObject));
      if (this.c.contains(((UMessage)localObject).msg_id))
        return super.onStartCommand(paramIntent, paramInt1, paramInt2);
      this.c.add(((UMessage)localObject).msg_id);
      switch (n)
      {
      default:
      case 2:
        while (true)
        {
          return super.onStartCommand(paramIntent, paramInt1, paramInt2);
          Log.a(TAG, "Start Download Resource");
          n = i1 - 1;
          setAlarm((UMessage)localObject, n);
          checkCache();
          downloadResource((UMessage)localObject, n);
        }
      case 1:
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        continue;
        deleteAlarm(localException, i1);
        Log.a(TAG, "Show Notification After Downloaded Resource");
        notification(localException);
        this.c.remove(localException.msg_id);
        if (this.c.size() == 0)
          stopSelf();
      }
    }
  }

  public void setAlarm(UMessage paramUMessage, int paramInt)
  {
    Log.a(TAG, "setAlarm");
    paramUMessage = a(paramUMessage, paramInt);
    ((AlarmManager)getSystemService("alarm")).set(1, System.currentTimeMillis() + 300000L, paramUMessage);
  }

  public class DownloadResourceTask extends AsyncTask<Void, Void, Boolean>
  {
    UMessage a;
    ArrayList<String> b;
    int c;

    public DownloadResourceTask(UMessage paramInt, int arg3)
    {
      this.a = paramInt;
      this.b = new ArrayList();
      if (paramInt.isLargeIconFromInternet())
        this.b.add(paramInt.img);
      if (paramInt.isSoundFromInternet())
        this.b.add(paramInt.sound);
      int i;
      this.c = i;
    }

    protected Boolean a(Void[] paramArrayOfVoid)
    {
      paramArrayOfVoid = this.b.iterator();
      for (boolean bool = true; paramArrayOfVoid.hasNext(); bool = download((String)paramArrayOfVoid.next()) & bool);
      return Boolean.valueOf(bool);
    }

    protected void a(Boolean paramBoolean)
    {
      super.onPostExecute(paramBoolean);
      UmengDownloadResourceService.this.c.remove(this.a.msg_id);
      if ((paramBoolean.booleanValue()) || (this.c <= 0))
      {
        MessageSharedPrefs.getInstance(UmengDownloadResourceService.this.b).c(this.a.msg_id);
        paramBoolean = this.a.getRaw().toString();
        localIntent = new Intent(UmengDownloadResourceService.this.b, UmengDownloadResourceService.class);
        localIntent.putExtra("body", paramBoolean);
        localIntent.putExtra("OPERATIOIN", 1);
        localIntent.putExtra("RETRY_TIME", this.c);
        UmengDownloadResourceService.this.startService(localIntent);
      }
      while (UmengDownloadResourceService.this.c.size() != 0)
      {
        Intent localIntent;
        return;
      }
      UmengDownloadResourceService.this.stopSelf();
    }

    // ERROR //
    public boolean download(String paramString)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 4
      //   3: aconst_null
      //   4: astore_3
      //   5: aload_1
      //   6: invokestatic 161	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   9: ifeq +5 -> 14
      //   12: iconst_1
      //   13: ireturn
      //   14: new 163	java/lang/StringBuilder
      //   17: dup
      //   18: invokespecial 164	java/lang/StringBuilder:<init>	()V
      //   21: aload_1
      //   22: invokevirtual 167	java/lang/String:hashCode	()I
      //   25: invokevirtual 171	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   28: ldc 173
      //   30: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   33: invokevirtual 177	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   36: astore 6
      //   38: aload_0
      //   39: getfield 21	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:d	Lcom/umeng/message/UmengDownloadResourceService;
      //   42: getfield 99	com/umeng/message/UmengDownloadResourceService:b	Landroid/content/Context;
      //   45: aload_0
      //   46: getfield 26	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:a	Lcom/umeng/message/entity/UMessage;
      //   49: invokestatic 181	com/umeng/message/UmengDownloadResourceService:getMessageResourceFolder	(Landroid/content/Context;Lcom/umeng/message/entity/UMessage;)Ljava/lang/String;
      //   52: astore_2
      //   53: new 183	java/io/File
      //   56: dup
      //   57: aload_2
      //   58: new 163	java/lang/StringBuilder
      //   61: dup
      //   62: invokespecial 164	java/lang/StringBuilder:<init>	()V
      //   65: aload 6
      //   67: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   70: ldc 185
      //   72: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   75: invokevirtual 177	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   78: invokespecial 188	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
      //   81: astore 5
      //   83: new 183	java/io/File
      //   86: dup
      //   87: aload_2
      //   88: aload 6
      //   90: invokespecial 188	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
      //   93: astore 6
      //   95: aload 6
      //   97: invokevirtual 191	java/io/File:exists	()Z
      //   100: istore 8
      //   102: iload 8
      //   104: ifeq +21 -> 125
      //   107: aload_0
      //   108: getfield 21	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:d	Lcom/umeng/message/UmengDownloadResourceService;
      //   111: aconst_null
      //   112: invokevirtual 195	com/umeng/message/UmengDownloadResourceService:close	(Ljava/io/Closeable;)V
      //   115: aload_0
      //   116: getfield 21	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:d	Lcom/umeng/message/UmengDownloadResourceService;
      //   119: aconst_null
      //   120: invokevirtual 195	com/umeng/message/UmengDownloadResourceService:close	(Ljava/io/Closeable;)V
      //   123: iconst_1
      //   124: ireturn
      //   125: new 183	java/io/File
      //   128: dup
      //   129: aload_2
      //   130: invokespecial 197	java/io/File:<init>	(Ljava/lang/String;)V
      //   133: astore_2
      //   134: aload_2
      //   135: invokevirtual 191	java/io/File:exists	()Z
      //   138: ifne +8 -> 146
      //   141: aload_2
      //   142: invokevirtual 200	java/io/File:mkdirs	()Z
      //   145: pop
      //   146: aload 5
      //   148: invokevirtual 191	java/io/File:exists	()Z
      //   151: ifeq +9 -> 160
      //   154: aload 5
      //   156: invokevirtual 203	java/io/File:delete	()Z
      //   159: pop
      //   160: new 205	java/net/URL
      //   163: dup
      //   164: new 207	java/net/URI
      //   167: dup
      //   168: aload_1
      //   169: invokespecial 208	java/net/URI:<init>	(Ljava/lang/String;)V
      //   172: invokevirtual 211	java/net/URI:toASCIIString	()Ljava/lang/String;
      //   175: invokespecial 212	java/net/URL:<init>	(Ljava/lang/String;)V
      //   178: invokevirtual 216	java/net/URL:openStream	()Ljava/io/InputStream;
      //   181: astore_1
      //   182: new 218	java/io/FileOutputStream
      //   185: dup
      //   186: aload 5
      //   188: invokespecial 221	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   191: astore_2
      //   192: sipush 10240
      //   195: newarray byte
      //   197: astore_3
      //   198: aload_1
      //   199: aload_3
      //   200: invokevirtual 227	java/io/InputStream:read	([B)I
      //   203: istore 7
      //   205: iload 7
      //   207: ifle +43 -> 250
      //   210: aload_2
      //   211: aload_3
      //   212: iconst_0
      //   213: iload 7
      //   215: invokevirtual 231	java/io/FileOutputStream:write	([BII)V
      //   218: goto -20 -> 198
      //   221: astore 4
      //   223: aload_2
      //   224: astore_3
      //   225: aload 4
      //   227: astore_2
      //   228: aload_2
      //   229: invokevirtual 234	java/lang/Exception:printStackTrace	()V
      //   232: aload_0
      //   233: getfield 21	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:d	Lcom/umeng/message/UmengDownloadResourceService;
      //   236: aload_1
      //   237: invokevirtual 195	com/umeng/message/UmengDownloadResourceService:close	(Ljava/io/Closeable;)V
      //   240: aload_0
      //   241: getfield 21	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:d	Lcom/umeng/message/UmengDownloadResourceService;
      //   244: aload_3
      //   245: invokevirtual 195	com/umeng/message/UmengDownloadResourceService:close	(Ljava/io/Closeable;)V
      //   248: iconst_0
      //   249: ireturn
      //   250: aload 5
      //   252: aload 6
      //   254: invokevirtual 238	java/io/File:renameTo	(Ljava/io/File;)Z
      //   257: pop
      //   258: aload_0
      //   259: getfield 21	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:d	Lcom/umeng/message/UmengDownloadResourceService;
      //   262: aload_1
      //   263: invokevirtual 195	com/umeng/message/UmengDownloadResourceService:close	(Ljava/io/Closeable;)V
      //   266: aload_0
      //   267: getfield 21	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:d	Lcom/umeng/message/UmengDownloadResourceService;
      //   270: aload_2
      //   271: invokevirtual 195	com/umeng/message/UmengDownloadResourceService:close	(Ljava/io/Closeable;)V
      //   274: iconst_1
      //   275: ireturn
      //   276: astore_2
      //   277: aconst_null
      //   278: astore_1
      //   279: aload 4
      //   281: astore_3
      //   282: aload_0
      //   283: getfield 21	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:d	Lcom/umeng/message/UmengDownloadResourceService;
      //   286: aload_1
      //   287: invokevirtual 195	com/umeng/message/UmengDownloadResourceService:close	(Ljava/io/Closeable;)V
      //   290: aload_0
      //   291: getfield 21	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:d	Lcom/umeng/message/UmengDownloadResourceService;
      //   294: aload_3
      //   295: invokevirtual 195	com/umeng/message/UmengDownloadResourceService:close	(Ljava/io/Closeable;)V
      //   298: aload_2
      //   299: athrow
      //   300: astore_2
      //   301: aload 4
      //   303: astore_3
      //   304: goto -22 -> 282
      //   307: astore 4
      //   309: aload_2
      //   310: astore_3
      //   311: aload 4
      //   313: astore_2
      //   314: goto -32 -> 282
      //   317: astore_2
      //   318: goto -36 -> 282
      //   321: astore_2
      //   322: aconst_null
      //   323: astore_1
      //   324: goto -96 -> 228
      //   327: astore_2
      //   328: goto -100 -> 228
      //
      // Exception table:
      //   from	to	target	type
      //   192	198	221	java/lang/Exception
      //   198	205	221	java/lang/Exception
      //   210	218	221	java/lang/Exception
      //   250	258	221	java/lang/Exception
      //   14	102	276	finally
      //   125	146	276	finally
      //   146	160	276	finally
      //   160	182	276	finally
      //   182	192	300	finally
      //   192	198	307	finally
      //   198	205	307	finally
      //   210	218	307	finally
      //   250	258	307	finally
      //   228	232	317	finally
      //   14	102	321	java/lang/Exception
      //   125	146	321	java/lang/Exception
      //   146	160	321	java/lang/Exception
      //   160	182	321	java/lang/Exception
      //   182	192	327	java/lang/Exception
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UmengDownloadResourceService
 * JD-Core Version:    0.6.2
 */