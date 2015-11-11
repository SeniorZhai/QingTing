package com.sina.weibo.sdk;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.sina.weibo.sdk.utils.LogUtil;
import java.util.Iterator;
import java.util.List;

public class WeiboAppManager
{
  private static final String SDK_INT_FILE_NAME = "weibo_for_sdk.json";
  private static final String TAG = WeiboAppManager.class.getName();
  private static final String WEIBO_IDENTITY_ACTION = "com.sina.weibo.action.sdkidentity";
  private static final Uri WEIBO_NAME_URI = Uri.parse("content://com.sina.weibo.sdkProvider/query/package");
  private static WeiboAppManager sInstance;
  private Context mContext;

  private WeiboAppManager(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
  }

  public static WeiboAppManager getInstance(Context paramContext)
  {
    try
    {
      if (sInstance == null)
        sInstance = new WeiboAppManager(paramContext);
      paramContext = sInstance;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  private WeiboInfo queryWeiboInfoByAsset(Context paramContext)
  {
    Object localObject = new Intent("com.sina.weibo.action.sdkidentity");
    ((Intent)localObject).addCategory("android.intent.category.DEFAULT");
    localObject = paramContext.getPackageManager().queryIntentServices((Intent)localObject, 0);
    if ((localObject == null) || (((List)localObject).isEmpty()))
    {
      localObject = null;
      return localObject;
    }
    paramContext = null;
    Iterator localIterator = ((List)localObject).iterator();
    while (true)
    {
      localObject = paramContext;
      if (!localIterator.hasNext())
        break;
      localObject = (ResolveInfo)localIterator.next();
      if ((((ResolveInfo)localObject).serviceInfo != null) && (((ResolveInfo)localObject).serviceInfo.applicationInfo != null) && (!TextUtils.isEmpty(((ResolveInfo)localObject).serviceInfo.applicationInfo.packageName)))
      {
        localObject = parseWeiboInfoByAsset(((ResolveInfo)localObject).serviceInfo.applicationInfo.packageName);
        if (localObject != null)
          if (paramContext == null)
            paramContext = (Context)localObject;
          else if (paramContext.getSupportApi() < ((WeiboInfo)localObject).getSupportApi())
            paramContext = (Context)localObject;
      }
    }
  }

  private WeiboInfo queryWeiboInfoByProvider(Context paramContext)
  {
    Object localObject3 = paramContext.getContentResolver();
    Object localObject2 = null;
    Object localObject1 = null;
    while (true)
    {
      try
      {
        localObject3 = ((ContentResolver)localObject3).query(WEIBO_NAME_URI, null, null, null, null);
        if (localObject3 == null)
        {
          if (localObject3 != null)
            ((Cursor)localObject3).close();
          paramContext = null;
          return paramContext;
        }
        localObject1 = localObject3;
        localObject2 = localObject3;
        int j = ((Cursor)localObject3).getColumnIndex("support_api");
        localObject1 = localObject3;
        localObject2 = localObject3;
        int k = ((Cursor)localObject3).getColumnIndex("package");
        localObject1 = localObject3;
        localObject2 = localObject3;
        if (((Cursor)localObject3).moveToFirst())
        {
          int i = -1;
          localObject1 = localObject3;
          localObject2 = localObject3;
          Object localObject4 = ((Cursor)localObject3).getString(j);
          localObject1 = localObject3;
          localObject2 = localObject3;
          try
          {
            j = Integer.parseInt((String)localObject4);
            i = j;
            localObject1 = localObject3;
            localObject2 = localObject3;
            String str = ((Cursor)localObject3).getString(k);
            localObject1 = localObject3;
            localObject2 = localObject3;
            if (!TextUtils.isEmpty(str))
            {
              localObject1 = localObject3;
              localObject2 = localObject3;
              if (ApiUtils.validateWeiboSign(paramContext, str))
              {
                localObject1 = localObject3;
                localObject2 = localObject3;
                localObject4 = new WeiboInfo();
                localObject1 = localObject3;
                localObject2 = localObject3;
                ((WeiboInfo)localObject4).setPackageName(str);
                localObject1 = localObject3;
                localObject2 = localObject3;
                ((WeiboInfo)localObject4).setSupportApi(i);
                paramContext = (Context)localObject4;
                if (localObject3 == null)
                  continue;
                ((Cursor)localObject3).close();
                return localObject4;
              }
            }
          }
          catch (NumberFormatException localNumberFormatException)
          {
            localObject1 = localObject3;
            localObject2 = localObject3;
            localNumberFormatException.printStackTrace();
            continue;
          }
        }
      }
      catch (Exception paramContext)
      {
        localObject2 = localObject1;
        LogUtil.e(TAG, paramContext.getMessage());
        return null;
      }
      finally
      {
        if (localObject2 != null)
          localObject2.close();
      }
      if (localObject3 != null)
        ((Cursor)localObject3).close();
    }
  }

  private WeiboInfo queryWeiboInfoInternal(Context paramContext)
  {
    int j = 1;
    WeiboInfo localWeiboInfo = queryWeiboInfoByProvider(paramContext);
    paramContext = queryWeiboInfoByAsset(paramContext);
    int i;
    if (localWeiboInfo != null)
    {
      i = 1;
      if (paramContext == null)
        break label52;
      label25: if ((i == 0) || (j == 0))
        break label60;
      if (localWeiboInfo.getSupportApi() < paramContext.getSupportApi())
        break label58;
    }
    label52: label58: label60: 
    while (i != 0)
    {
      return localWeiboInfo;
      i = 0;
      break;
      j = 0;
      break label25;
      return paramContext;
    }
    if (j != 0)
      return paramContext;
    return null;
  }

  public WeiboInfo getWeiboInfo()
  {
    try
    {
      WeiboInfo localWeiboInfo = queryWeiboInfoInternal(this.mContext);
      return localWeiboInfo;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  // ERROR //
  public WeiboInfo parseWeiboInfoByAsset(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 124	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: ifeq +7 -> 11
    //   7: aconst_null
    //   8: astore_1
    //   9: aload_1
    //   10: areturn
    //   11: aconst_null
    //   12: astore 8
    //   14: aconst_null
    //   15: astore 9
    //   17: aconst_null
    //   18: astore 10
    //   20: aconst_null
    //   21: astore 11
    //   23: aconst_null
    //   24: astore_3
    //   25: aload_3
    //   26: astore 4
    //   28: aload 8
    //   30: astore 5
    //   32: aload 9
    //   34: astore 6
    //   36: aload 10
    //   38: astore 7
    //   40: aload 11
    //   42: astore_2
    //   43: aload_0
    //   44: getfield 54	com/sina/weibo/sdk/WeiboAppManager:mContext	Landroid/content/Context;
    //   47: aload_1
    //   48: iconst_2
    //   49: invokevirtual 218	android/content/Context:createPackageContext	(Ljava/lang/String;I)Landroid/content/Context;
    //   52: astore 13
    //   54: aload_3
    //   55: astore 4
    //   57: aload 8
    //   59: astore 5
    //   61: aload 9
    //   63: astore 6
    //   65: aload 10
    //   67: astore 7
    //   69: aload 11
    //   71: astore_2
    //   72: sipush 4096
    //   75: newarray byte
    //   77: astore 12
    //   79: aload_3
    //   80: astore 4
    //   82: aload 8
    //   84: astore 5
    //   86: aload 9
    //   88: astore 6
    //   90: aload 10
    //   92: astore 7
    //   94: aload 11
    //   96: astore_2
    //   97: aload 13
    //   99: invokevirtual 222	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   102: ldc 11
    //   104: invokevirtual 228	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   107: astore_3
    //   108: aload_3
    //   109: astore 4
    //   111: aload_3
    //   112: astore 5
    //   114: aload_3
    //   115: astore 6
    //   117: aload_3
    //   118: astore 7
    //   120: aload_3
    //   121: astore_2
    //   122: new 230	java/lang/StringBuilder
    //   125: dup
    //   126: invokespecial 231	java/lang/StringBuilder:<init>	()V
    //   129: astore 8
    //   131: aload_3
    //   132: astore 4
    //   134: aload_3
    //   135: astore 5
    //   137: aload_3
    //   138: astore 6
    //   140: aload_3
    //   141: astore 7
    //   143: aload_3
    //   144: astore_2
    //   145: aload_3
    //   146: aload 12
    //   148: iconst_0
    //   149: sipush 4096
    //   152: invokevirtual 237	java/io/InputStream:read	([BII)I
    //   155: istore 14
    //   157: iload 14
    //   159: iconst_m1
    //   160: if_icmpne +67 -> 227
    //   163: aload_3
    //   164: astore 4
    //   166: aload_3
    //   167: astore 5
    //   169: aload_3
    //   170: astore 6
    //   172: aload_3
    //   173: astore 7
    //   175: aload_3
    //   176: astore_2
    //   177: aload 8
    //   179: invokevirtual 240	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   182: invokestatic 124	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   185: ifne +32 -> 217
    //   188: aload_3
    //   189: astore 4
    //   191: aload_3
    //   192: astore 5
    //   194: aload_3
    //   195: astore 6
    //   197: aload_3
    //   198: astore 7
    //   200: aload_3
    //   201: astore_2
    //   202: aload_0
    //   203: getfield 54	com/sina/weibo/sdk/WeiboAppManager:mContext	Landroid/content/Context;
    //   206: aload_1
    //   207: invokestatic 178	com/sina/weibo/sdk/ApiUtils:validateWeiboSign	(Landroid/content/Context;Ljava/lang/String;)Z
    //   210: istore 15
    //   212: iload 15
    //   214: ifne +88 -> 302
    //   217: aload_3
    //   218: ifnull +7 -> 225
    //   221: aload_3
    //   222: invokevirtual 241	java/io/InputStream:close	()V
    //   225: aconst_null
    //   226: areturn
    //   227: aload_3
    //   228: astore 4
    //   230: aload_3
    //   231: astore 5
    //   233: aload_3
    //   234: astore 6
    //   236: aload_3
    //   237: astore 7
    //   239: aload_3
    //   240: astore_2
    //   241: aload 8
    //   243: new 243	java/lang/String
    //   246: dup
    //   247: aload 12
    //   249: iconst_0
    //   250: iload 14
    //   252: invokespecial 246	java/lang/String:<init>	([BII)V
    //   255: invokevirtual 250	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: pop
    //   259: goto -128 -> 131
    //   262: astore_1
    //   263: aload 4
    //   265: astore_2
    //   266: getstatic 31	com/sina/weibo/sdk/WeiboAppManager:TAG	Ljava/lang/String;
    //   269: aload_1
    //   270: invokevirtual 251	android/content/pm/PackageManager$NameNotFoundException:getMessage	()Ljava/lang/String;
    //   273: invokestatic 199	com/sina/weibo/sdk/utils/LogUtil:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   276: aload 4
    //   278: ifnull +8 -> 286
    //   281: aload 4
    //   283: invokevirtual 241	java/io/InputStream:close	()V
    //   286: aconst_null
    //   287: areturn
    //   288: astore_1
    //   289: getstatic 31	com/sina/weibo/sdk/WeiboAppManager:TAG	Ljava/lang/String;
    //   292: aload_1
    //   293: invokevirtual 252	java/io/IOException:getMessage	()Ljava/lang/String;
    //   296: invokestatic 199	com/sina/weibo/sdk/utils/LogUtil:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   299: goto -74 -> 225
    //   302: aload_3
    //   303: astore 4
    //   305: aload_3
    //   306: astore 5
    //   308: aload_3
    //   309: astore 6
    //   311: aload_3
    //   312: astore 7
    //   314: aload_3
    //   315: astore_2
    //   316: new 254	org/json/JSONObject
    //   319: dup
    //   320: aload 8
    //   322: invokevirtual 240	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   325: invokespecial 255	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   328: ldc 154
    //   330: iconst_m1
    //   331: invokevirtual 259	org/json/JSONObject:optInt	(Ljava/lang/String;I)I
    //   334: istore 14
    //   336: aload_3
    //   337: astore 4
    //   339: aload_3
    //   340: astore 5
    //   342: aload_3
    //   343: astore 6
    //   345: aload_3
    //   346: astore 7
    //   348: aload_3
    //   349: astore_2
    //   350: new 6	com/sina/weibo/sdk/WeiboAppManager$WeiboInfo
    //   353: dup
    //   354: invokespecial 179	com/sina/weibo/sdk/WeiboAppManager$WeiboInfo:<init>	()V
    //   357: astore 8
    //   359: aload_3
    //   360: astore 4
    //   362: aload_3
    //   363: astore 5
    //   365: aload_3
    //   366: astore 6
    //   368: aload_3
    //   369: astore 7
    //   371: aload_3
    //   372: astore_2
    //   373: aload 8
    //   375: aload_1
    //   376: invokestatic 183	com/sina/weibo/sdk/WeiboAppManager$WeiboInfo:access$0	(Lcom/sina/weibo/sdk/WeiboAppManager$WeiboInfo;Ljava/lang/String;)V
    //   379: aload_3
    //   380: astore 4
    //   382: aload_3
    //   383: astore 5
    //   385: aload_3
    //   386: astore 6
    //   388: aload_3
    //   389: astore 7
    //   391: aload_3
    //   392: astore_2
    //   393: aload 8
    //   395: iload 14
    //   397: invokestatic 187	com/sina/weibo/sdk/WeiboAppManager$WeiboInfo:access$1	(Lcom/sina/weibo/sdk/WeiboAppManager$WeiboInfo;I)V
    //   400: aload 8
    //   402: astore_1
    //   403: aload_3
    //   404: ifnull -395 -> 9
    //   407: aload_3
    //   408: invokevirtual 241	java/io/InputStream:close	()V
    //   411: aload 8
    //   413: areturn
    //   414: astore_1
    //   415: getstatic 31	com/sina/weibo/sdk/WeiboAppManager:TAG	Ljava/lang/String;
    //   418: aload_1
    //   419: invokevirtual 252	java/io/IOException:getMessage	()Ljava/lang/String;
    //   422: invokestatic 199	com/sina/weibo/sdk/utils/LogUtil:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   425: aload 8
    //   427: areturn
    //   428: astore_1
    //   429: getstatic 31	com/sina/weibo/sdk/WeiboAppManager:TAG	Ljava/lang/String;
    //   432: aload_1
    //   433: invokevirtual 252	java/io/IOException:getMessage	()Ljava/lang/String;
    //   436: invokestatic 199	com/sina/weibo/sdk/utils/LogUtil:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   439: goto -153 -> 286
    //   442: astore_1
    //   443: aload 5
    //   445: astore_2
    //   446: getstatic 31	com/sina/weibo/sdk/WeiboAppManager:TAG	Ljava/lang/String;
    //   449: aload_1
    //   450: invokevirtual 252	java/io/IOException:getMessage	()Ljava/lang/String;
    //   453: invokestatic 199	com/sina/weibo/sdk/utils/LogUtil:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   456: aload 5
    //   458: ifnull -172 -> 286
    //   461: aload 5
    //   463: invokevirtual 241	java/io/InputStream:close	()V
    //   466: goto -180 -> 286
    //   469: astore_1
    //   470: getstatic 31	com/sina/weibo/sdk/WeiboAppManager:TAG	Ljava/lang/String;
    //   473: aload_1
    //   474: invokevirtual 252	java/io/IOException:getMessage	()Ljava/lang/String;
    //   477: invokestatic 199	com/sina/weibo/sdk/utils/LogUtil:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   480: goto -194 -> 286
    //   483: astore_1
    //   484: aload 6
    //   486: astore_2
    //   487: getstatic 31	com/sina/weibo/sdk/WeiboAppManager:TAG	Ljava/lang/String;
    //   490: aload_1
    //   491: invokevirtual 260	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   494: invokestatic 199	com/sina/weibo/sdk/utils/LogUtil:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   497: aload 6
    //   499: ifnull -213 -> 286
    //   502: aload 6
    //   504: invokevirtual 241	java/io/InputStream:close	()V
    //   507: goto -221 -> 286
    //   510: astore_1
    //   511: getstatic 31	com/sina/weibo/sdk/WeiboAppManager:TAG	Ljava/lang/String;
    //   514: aload_1
    //   515: invokevirtual 252	java/io/IOException:getMessage	()Ljava/lang/String;
    //   518: invokestatic 199	com/sina/weibo/sdk/utils/LogUtil:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   521: goto -235 -> 286
    //   524: astore_1
    //   525: aload 7
    //   527: astore_2
    //   528: getstatic 31	com/sina/weibo/sdk/WeiboAppManager:TAG	Ljava/lang/String;
    //   531: aload_1
    //   532: invokevirtual 193	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   535: invokestatic 199	com/sina/weibo/sdk/utils/LogUtil:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   538: aload 7
    //   540: ifnull -254 -> 286
    //   543: aload 7
    //   545: invokevirtual 241	java/io/InputStream:close	()V
    //   548: goto -262 -> 286
    //   551: astore_1
    //   552: getstatic 31	com/sina/weibo/sdk/WeiboAppManager:TAG	Ljava/lang/String;
    //   555: aload_1
    //   556: invokevirtual 252	java/io/IOException:getMessage	()Ljava/lang/String;
    //   559: invokestatic 199	com/sina/weibo/sdk/utils/LogUtil:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   562: goto -276 -> 286
    //   565: astore_1
    //   566: aload_2
    //   567: ifnull +7 -> 574
    //   570: aload_2
    //   571: invokevirtual 241	java/io/InputStream:close	()V
    //   574: aload_1
    //   575: athrow
    //   576: astore_2
    //   577: getstatic 31	com/sina/weibo/sdk/WeiboAppManager:TAG	Ljava/lang/String;
    //   580: aload_2
    //   581: invokevirtual 252	java/io/IOException:getMessage	()Ljava/lang/String;
    //   584: invokestatic 199	com/sina/weibo/sdk/utils/LogUtil:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   587: goto -13 -> 574
    //
    // Exception table:
    //   from	to	target	type
    //   43	54	262	android/content/pm/PackageManager$NameNotFoundException
    //   72	79	262	android/content/pm/PackageManager$NameNotFoundException
    //   97	108	262	android/content/pm/PackageManager$NameNotFoundException
    //   122	131	262	android/content/pm/PackageManager$NameNotFoundException
    //   145	157	262	android/content/pm/PackageManager$NameNotFoundException
    //   177	188	262	android/content/pm/PackageManager$NameNotFoundException
    //   202	212	262	android/content/pm/PackageManager$NameNotFoundException
    //   241	259	262	android/content/pm/PackageManager$NameNotFoundException
    //   316	336	262	android/content/pm/PackageManager$NameNotFoundException
    //   350	359	262	android/content/pm/PackageManager$NameNotFoundException
    //   373	379	262	android/content/pm/PackageManager$NameNotFoundException
    //   393	400	262	android/content/pm/PackageManager$NameNotFoundException
    //   221	225	288	java/io/IOException
    //   407	411	414	java/io/IOException
    //   281	286	428	java/io/IOException
    //   43	54	442	java/io/IOException
    //   72	79	442	java/io/IOException
    //   97	108	442	java/io/IOException
    //   122	131	442	java/io/IOException
    //   145	157	442	java/io/IOException
    //   177	188	442	java/io/IOException
    //   202	212	442	java/io/IOException
    //   241	259	442	java/io/IOException
    //   316	336	442	java/io/IOException
    //   350	359	442	java/io/IOException
    //   373	379	442	java/io/IOException
    //   393	400	442	java/io/IOException
    //   461	466	469	java/io/IOException
    //   43	54	483	org/json/JSONException
    //   72	79	483	org/json/JSONException
    //   97	108	483	org/json/JSONException
    //   122	131	483	org/json/JSONException
    //   145	157	483	org/json/JSONException
    //   177	188	483	org/json/JSONException
    //   202	212	483	org/json/JSONException
    //   241	259	483	org/json/JSONException
    //   316	336	483	org/json/JSONException
    //   350	359	483	org/json/JSONException
    //   373	379	483	org/json/JSONException
    //   393	400	483	org/json/JSONException
    //   502	507	510	java/io/IOException
    //   43	54	524	java/lang/Exception
    //   72	79	524	java/lang/Exception
    //   97	108	524	java/lang/Exception
    //   122	131	524	java/lang/Exception
    //   145	157	524	java/lang/Exception
    //   177	188	524	java/lang/Exception
    //   202	212	524	java/lang/Exception
    //   241	259	524	java/lang/Exception
    //   316	336	524	java/lang/Exception
    //   350	359	524	java/lang/Exception
    //   373	379	524	java/lang/Exception
    //   393	400	524	java/lang/Exception
    //   543	548	551	java/io/IOException
    //   43	54	565	finally
    //   72	79	565	finally
    //   97	108	565	finally
    //   122	131	565	finally
    //   145	157	565	finally
    //   177	188	565	finally
    //   202	212	565	finally
    //   241	259	565	finally
    //   266	276	565	finally
    //   316	336	565	finally
    //   350	359	565	finally
    //   373	379	565	finally
    //   393	400	565	finally
    //   446	456	565	finally
    //   487	497	565	finally
    //   528	538	565	finally
    //   570	574	576	java/io/IOException
  }

  public static class WeiboInfo
  {
    private String mPackageName;
    private int mSupportApi;

    private void setPackageName(String paramString)
    {
      this.mPackageName = paramString;
    }

    private void setSupportApi(int paramInt)
    {
      this.mSupportApi = paramInt;
    }

    public String getPackageName()
    {
      return this.mPackageName;
    }

    public int getSupportApi()
    {
      return this.mSupportApi;
    }

    public boolean isLegal()
    {
      return (!TextUtils.isEmpty(this.mPackageName)) && (this.mSupportApi > 0);
    }

    public String toString()
    {
      return "WeiboInfo: PackageName = " + this.mPackageName + ", supportApi = " + this.mSupportApi;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.WeiboAppManager
 * JD-Core Version:    0.6.2
 */