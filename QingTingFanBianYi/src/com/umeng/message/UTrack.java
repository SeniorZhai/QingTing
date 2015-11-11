package com.umeng.message;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.common.message.DeviceConfig;
import com.umeng.common.message.Log;
import com.umeng.common.message.b;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.C;
import com.umeng.message.proguard.C.e;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class UTrack
{
  private static final String a = UTrack.class.getName();
  private static UTrack c;
  private static boolean f = false;
  private static boolean g = false;
  private static boolean h = false;
  private JSONObject b;
  private ScheduledThreadPoolExecutor d;
  private Context e;

  private UTrack(Context paramContext)
  {
    this.e = paramContext.getApplicationContext();
    b();
    this.d = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 4);
  }

  private JSONObject a(JSONObject paramJSONObject, String paramString)
    throws C.e, JSONException
  {
    String str = C.c(paramString).H().r("application/json").i(paramJSONObject.toString()).b("UTF-8");
    Log.c(a, "sendRequest() url=" + paramString + "\n request = " + paramJSONObject + "\n response = " + str);
    return new JSONObject(str);
  }

  private void a(String paramString, int paramInt, long paramLong)
  {
    if (!c())
      return;
    if (TextUtils.isEmpty(paramString))
    {
      Log.b(a, "trackMsgLog: empty msgId");
      return;
    }
    long l = System.currentTimeMillis();
    MsgLogStore.getInstance(this.e).addLog(paramString, paramInt, l);
    UTrack.1 local1 = new UTrack.1(this, paramString, paramInt, l);
    if (paramLong > 0L);
    for (l = Math.abs(new Random().nextLong() % paramLong); ; l = 0L)
    {
      Log.c(a, String.format("trackMsgLog(msgId=%s, actionType=%d, random=%d, delay=%d)", new Object[] { paramString, Integer.valueOf(paramInt), Long.valueOf(paramLong), Long.valueOf(l) }));
      this.d.schedule(local1, l, TimeUnit.MILLISECONDS);
      return;
    }
  }

  private void b()
  {
    b localb;
    if (this.b == null)
    {
      localb = new b();
      localb.b(this.e, new String[0]);
      localb.a(this.e, new String[] { PushAgent.getInstance(this.e).getMessageAppkey(), PushAgent.getInstance(this.e).getMessageChannel() });
      this.b = new JSONObject();
    }
    try
    {
      localb.b(this.b);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private void b(String paramString, int paramInt, long paramLong)
  {
    try
    {
      JSONObject localJSONObject = e();
      localJSONObject.put("msg_id", paramString);
      localJSONObject.put("action_type", paramInt);
      localJSONObject.put("ts", paramLong);
      if ("ok".equalsIgnoreCase(a(localJSONObject, MsgConstant.LOG_ENDPOINT).optString("success")))
        MsgLogStore.getInstance(this.e).removeLog(paramString, paramInt);
      return;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }

  private boolean c()
  {
    if (TextUtils.isEmpty(DeviceConfig.getUtdid(this.e)))
    {
      Log.b(a, "UTDID is empty");
      return false;
    }
    if (TextUtils.isEmpty(UmengRegistrar.getRegistrationId(this.e)))
    {
      Log.b(a, "RegistrationId is empty");
      return false;
    }
    return true;
  }

  // ERROR //
  private String d()
  {
    // Byte code:
    //   0: invokestatic 306	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   3: ldc_w 308
    //   6: invokevirtual 312	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   9: ifne +5 -> 14
    //   12: aconst_null
    //   13: areturn
    //   14: new 106	java/lang/StringBuilder
    //   17: dup
    //   18: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   21: invokestatic 316	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   24: invokevirtual 321	java/io/File:getPath	()Ljava/lang/String;
    //   27: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: ldc_w 323
    //   33: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: aload_0
    //   37: getfield 47	com/umeng/message/UTrack:e	Landroid/content/Context;
    //   40: invokevirtual 326	android/content/Context:getPackageName	()Ljava/lang/String;
    //   43: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: ldc_w 328
    //   49: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   55: astore_1
    //   56: getstatic 28	com/umeng/message/UTrack:a	Ljava/lang/String;
    //   59: new 106	java/lang/StringBuilder
    //   62: dup
    //   63: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   66: ldc_w 330
    //   69: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: aload_1
    //   73: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   79: invokestatic 126	com/umeng/common/message/Log:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   82: new 318	java/io/File
    //   85: dup
    //   86: aload_1
    //   87: ldc_w 332
    //   90: invokespecial 334	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   93: astore_1
    //   94: aload_1
    //   95: invokevirtual 337	java/io/File:exists	()Z
    //   98: istore 4
    //   100: iload 4
    //   102: ifeq +182 -> 284
    //   105: new 339	java/io/BufferedReader
    //   108: dup
    //   109: new 341	java/io/FileReader
    //   112: dup
    //   113: aload_1
    //   114: invokespecial 344	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   117: invokespecial 347	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   120: astore_2
    //   121: aload_2
    //   122: astore_1
    //   123: aload_2
    //   124: invokevirtual 350	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   127: astore_3
    //   128: aload_3
    //   129: ifnull +53 -> 182
    //   132: aload_2
    //   133: astore_1
    //   134: aload_3
    //   135: ldc_w 352
    //   138: invokevirtual 355	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   141: ifeq -20 -> 121
    //   144: aload_2
    //   145: astore_1
    //   146: aload_3
    //   147: ldc_w 352
    //   150: invokevirtual 358	java/lang/String:length	()I
    //   153: invokevirtual 362	java/lang/String:substring	(I)Ljava/lang/String;
    //   156: astore_3
    //   157: aload_2
    //   158: ifnull +7 -> 165
    //   161: aload_2
    //   162: invokevirtual 365	java/io/BufferedReader:close	()V
    //   165: aload_3
    //   166: areturn
    //   167: astore_1
    //   168: aload_1
    //   169: invokevirtual 366	java/io/IOException:printStackTrace	()V
    //   172: goto -7 -> 165
    //   175: astore_1
    //   176: aload_1
    //   177: invokevirtual 242	java/lang/Exception:printStackTrace	()V
    //   180: aconst_null
    //   181: areturn
    //   182: aload_2
    //   183: ifnull +101 -> 284
    //   186: aload_2
    //   187: invokevirtual 365	java/io/BufferedReader:close	()V
    //   190: aconst_null
    //   191: areturn
    //   192: astore_1
    //   193: aload_1
    //   194: invokevirtual 366	java/io/IOException:printStackTrace	()V
    //   197: aconst_null
    //   198: areturn
    //   199: astore_3
    //   200: aconst_null
    //   201: astore_2
    //   202: aload_2
    //   203: astore_1
    //   204: aload_3
    //   205: invokevirtual 367	java/io/FileNotFoundException:printStackTrace	()V
    //   208: aload_2
    //   209: ifnull +75 -> 284
    //   212: aload_2
    //   213: invokevirtual 365	java/io/BufferedReader:close	()V
    //   216: aconst_null
    //   217: areturn
    //   218: astore_1
    //   219: aload_1
    //   220: invokevirtual 366	java/io/IOException:printStackTrace	()V
    //   223: aconst_null
    //   224: areturn
    //   225: astore_3
    //   226: aconst_null
    //   227: astore_2
    //   228: aload_2
    //   229: astore_1
    //   230: aload_3
    //   231: invokevirtual 366	java/io/IOException:printStackTrace	()V
    //   234: aload_2
    //   235: ifnull +49 -> 284
    //   238: aload_2
    //   239: invokevirtual 365	java/io/BufferedReader:close	()V
    //   242: aconst_null
    //   243: areturn
    //   244: astore_1
    //   245: aload_1
    //   246: invokevirtual 366	java/io/IOException:printStackTrace	()V
    //   249: aconst_null
    //   250: areturn
    //   251: astore_2
    //   252: aconst_null
    //   253: astore_1
    //   254: aload_1
    //   255: ifnull +7 -> 262
    //   258: aload_1
    //   259: invokevirtual 365	java/io/BufferedReader:close	()V
    //   262: aload_2
    //   263: athrow
    //   264: astore_1
    //   265: aload_1
    //   266: invokevirtual 366	java/io/IOException:printStackTrace	()V
    //   269: goto -7 -> 262
    //   272: astore_2
    //   273: goto -19 -> 254
    //   276: astore_3
    //   277: goto -49 -> 228
    //   280: astore_3
    //   281: goto -79 -> 202
    //   284: aconst_null
    //   285: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   161	165	167	java/io/IOException
    //   0	12	175	java/lang/Exception
    //   14	100	175	java/lang/Exception
    //   161	165	175	java/lang/Exception
    //   168	172	175	java/lang/Exception
    //   186	190	175	java/lang/Exception
    //   193	197	175	java/lang/Exception
    //   212	216	175	java/lang/Exception
    //   219	223	175	java/lang/Exception
    //   238	242	175	java/lang/Exception
    //   245	249	175	java/lang/Exception
    //   258	262	175	java/lang/Exception
    //   262	264	175	java/lang/Exception
    //   265	269	175	java/lang/Exception
    //   186	190	192	java/io/IOException
    //   105	121	199	java/io/FileNotFoundException
    //   212	216	218	java/io/IOException
    //   105	121	225	java/io/IOException
    //   238	242	244	java/io/IOException
    //   105	121	251	finally
    //   258	262	264	java/io/IOException
    //   123	128	272	finally
    //   134	144	272	finally
    //   146	157	272	finally
    //   204	208	272	finally
    //   230	234	272	finally
    //   123	128	276	java/io/IOException
    //   134	144	276	java/io/IOException
    //   146	157	276	java/io/IOException
    //   123	128	280	java/io/FileNotFoundException
    //   134	144	280	java/io/FileNotFoundException
    //   146	157	280	java/io/FileNotFoundException
  }

  private JSONObject e()
    throws JSONException
  {
    String str1 = UmengRegistrar.getRegistrationId(this.e);
    String str2 = DeviceConfig.getUtdid(this.e);
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("header", this.b);
    localJSONObject.put("utdid", str2);
    localJSONObject.put("device_token", str1);
    return localJSONObject;
  }

  public static UTrack getInstance(Context paramContext)
  {
    try
    {
      if (c == null)
        c = new UTrack(paramContext);
      paramContext = c;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  void a(UMessage paramUMessage)
  {
    if ((paramUMessage == null) || (paramUMessage.msg_id == null))
      return;
    a(paramUMessage.msg_id, 0, paramUMessage.random_min * 60000L);
  }

  public boolean addAlias(String paramString1, String paramString2)
    throws C.e, JSONException
  {
    if (TextUtils.isEmpty(paramString2))
      Log.b(a, "addAlias: empty type");
    Object localObject;
    do
    {
      do
        return false;
      while (!c());
      if (MessageSharedPrefs.getInstance(this.e).isAliasSet(paramString1, paramString2))
      {
        Log.c(a, String.format("addAlias: <%s, %s> has been synced to the server before. Ingore this request.", new Object[] { paramString1, paramString2 }));
        return true;
      }
      if ((MessageSharedPrefs.getInstance(this.e).getAliasCount() >= 20) && (!MessageSharedPrefs.getInstance(this.e).isAliaseTypeSet(paramString2)))
      {
        Log.b(a, String.format("addAlias: <%s, %s>, More than 20 types of alias have been added. Ignore this request", new Object[] { paramString1, paramString2 }));
        return false;
      }
      localObject = e();
      ((JSONObject)localObject).put("alias", paramString1);
      ((JSONObject)localObject).put("type", paramString2);
      ((JSONObject)localObject).put("last_alias", MessageSharedPrefs.getInstance(this.e).getLastAlias(paramString2));
      ((JSONObject)localObject).put("ts", System.currentTimeMillis());
      localObject = a((JSONObject)localObject, MsgConstant.ALIAS_ENDPOINT).optString("success");
      Log.c(a, "addAlias: " + (String)localObject);
    }
    while (!"ok".equalsIgnoreCase((String)localObject));
    MessageSharedPrefs.getInstance(this.e).addAlias(paramString1, paramString2);
    return true;
  }

  public JSONObject getHeader()
  {
    return this.b;
  }

  public boolean removeAlias(String paramString1, String paramString2)
    throws C.e, JSONException
  {
    if (TextUtils.isEmpty(paramString2))
      Log.b(a, "removeAlias: empty type");
    Object localObject;
    do
    {
      do
        return false;
      while (!c());
      localObject = e();
      ((JSONObject)localObject).put("alias", paramString1);
      ((JSONObject)localObject).put("type", paramString2);
      ((JSONObject)localObject).put("ts", System.currentTimeMillis());
      localObject = a((JSONObject)localObject, MsgConstant.DELETE_ALIAS_ENDPOINT).optString("success");
      Log.c(a, "removeAlias: " + (String)localObject);
    }
    while (!"ok".equalsIgnoreCase((String)localObject));
    MessageSharedPrefs.getInstance(this.e).removeAlias(paramString1, paramString2);
    return true;
  }

  public void sendCachedMsgLog(long paramLong)
  {
    if (!c())
      return;
    if (f)
    {
      Log.c(a, "sendCachedMsgLog already in queue, abort this request.");
      return;
    }
    Log.c(a, "sendCachedMsgLog start, set cacheLogSending flag");
    f = true;
    UTrack.2 local2 = new UTrack.2(this);
    Log.c(a, String.format("sendCachedMsgLog(delay=%d)", new Object[] { Long.valueOf(paramLong) }));
    this.d.schedule(local2, paramLong, TimeUnit.MILLISECONDS);
  }

  public void trackAppLaunch(long paramLong)
  {
    if (!c())
      return;
    if (g)
    {
      Log.c(a, "trackAppLaunch already in queue, abort this request.");
      return;
    }
    Log.c(a, "trackAppLaunch start, set appLaunchSending flag");
    g = true;
    UTrack.3 local3 = new UTrack.3(this);
    Log.c(a, String.format("trackAppLaunch(delay=%d)", new Object[] { Long.valueOf(paramLong) }));
    this.d.schedule(local3, paramLong, TimeUnit.MILLISECONDS);
  }

  public void trackMsgClick(UMessage paramUMessage)
  {
    if ((paramUMessage == null) || (paramUMessage.msg_id == null))
      return;
    a(paramUMessage.msg_id, 1, paramUMessage.random_min * 60000L);
  }

  public void trackMsgDismissed(UMessage paramUMessage)
  {
    if ((paramUMessage == null) || (paramUMessage.msg_id == null))
      return;
    a(paramUMessage.msg_id, 2, paramUMessage.random_min * 60000L);
  }

  public void trackRegister()
  {
    if (!c())
      return;
    if (h)
    {
      Log.c(a, "sendRegisterLog already in queue, abort this request.");
      return;
    }
    Log.c(a, "trackRegisterLog start, set registerSending flag");
    h = true;
    UTrack.4 local4 = new UTrack.4(this);
    Log.c(a, String.format("trackRegister(delay=%d)", new Object[] { Integer.valueOf(0) }));
    this.d.schedule(local4, 0L, TimeUnit.MILLISECONDS);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UTrack
 * JD-Core Version:    0.6.2
 */