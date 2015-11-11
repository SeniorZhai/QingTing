package com.sina.weibo.sdk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.exception.WeiboHttpException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.WeiboParameters;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;
import org.json.JSONObject;

public class AidTask
{
  private static final String AID_FILE_NAME = "weibo_sdk_aid";
  private static final String TAG = "AidTask";
  private static final int VERSION = 1;
  public static final int WHAT_LOAD_AID_API_ERR = 1002;
  public static final int WHAT_LOAD_AID_IO_ERR = 1003;
  public static final int WHAT_LOAD_AID_SUC = 1001;
  private static AidTask sInstance;
  private String mAppKey;
  private Context mContext;
  private volatile ReentrantLock mTaskLock = new ReentrantLock(true);

  private AidTask(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
    new Thread(new Runnable()
    {
      public void run()
      {
        int i = 0;
        while (true)
        {
          if (i >= 1)
            return;
          File localFile = AidTask.this.getAidInfoFile(i);
          try
          {
            localFile.delete();
            label22: i += 1;
          }
          catch (Exception localException)
          {
            break label22;
          }
        }
      }
    }).start();
  }

  // ERROR //
  private void cacheAidInfo(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokestatic 101	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   6: istore 5
    //   8: iload 5
    //   10: ifeq +6 -> 16
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: aconst_null
    //   17: astore_3
    //   18: aconst_null
    //   19: astore 4
    //   21: new 103	java/io/FileOutputStream
    //   24: dup
    //   25: aload_0
    //   26: iconst_1
    //   27: invokespecial 74	com/sina/weibo/sdk/utils/AidTask:getAidInfoFile	(I)Ljava/io/File;
    //   30: invokespecial 106	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   33: astore_2
    //   34: aload_2
    //   35: aload_1
    //   36: invokevirtual 112	java/lang/String:getBytes	()[B
    //   39: invokevirtual 116	java/io/FileOutputStream:write	([B)V
    //   42: aload_2
    //   43: ifnull +65 -> 108
    //   46: aload_2
    //   47: invokevirtual 119	java/io/FileOutputStream:close	()V
    //   50: goto -37 -> 13
    //   53: astore_1
    //   54: aload 4
    //   56: astore_1
    //   57: aload_1
    //   58: ifnull -45 -> 13
    //   61: aload_1
    //   62: invokevirtual 119	java/io/FileOutputStream:close	()V
    //   65: goto -52 -> 13
    //   68: astore_1
    //   69: goto -56 -> 13
    //   72: astore_1
    //   73: aload_3
    //   74: astore_2
    //   75: aload_2
    //   76: ifnull +7 -> 83
    //   79: aload_2
    //   80: invokevirtual 119	java/io/FileOutputStream:close	()V
    //   83: aload_1
    //   84: athrow
    //   85: astore_1
    //   86: aload_0
    //   87: monitorexit
    //   88: aload_1
    //   89: athrow
    //   90: astore_1
    //   91: goto -78 -> 13
    //   94: astore_2
    //   95: goto -12 -> 83
    //   98: astore_1
    //   99: goto -24 -> 75
    //   102: astore_1
    //   103: aload_2
    //   104: astore_1
    //   105: goto -48 -> 57
    //   108: goto -95 -> 13
    //
    // Exception table:
    //   from	to	target	type
    //   21	34	53	java/lang/Exception
    //   61	65	68	java/io/IOException
    //   21	34	72	finally
    //   2	8	85	finally
    //   46	50	85	finally
    //   61	65	85	finally
    //   79	83	85	finally
    //   83	85	85	finally
    //   46	50	90	java/io/IOException
    //   79	83	94	java/io/IOException
    //   34	42	98	finally
    //   34	42	102	java/lang/Exception
  }

  // ERROR //
  private String encryptRsa(String paramString1, String paramString2)
    throws Exception
  {
    // Byte code:
    //   0: ldc 123
    //   2: invokestatic 129	javax/crypto/Cipher:getInstance	(Ljava/lang/String;)Ljavax/crypto/Cipher;
    //   5: astore 4
    //   7: aload 4
    //   9: iconst_1
    //   10: aload_0
    //   11: aload_2
    //   12: invokespecial 133	com/sina/weibo/sdk/utils/AidTask:getPublicKey	(Ljava/lang/String;)Ljava/security/PublicKey;
    //   15: invokevirtual 137	javax/crypto/Cipher:init	(ILjava/security/Key;)V
    //   18: aconst_null
    //   19: astore_3
    //   20: aload_1
    //   21: ldc 139
    //   23: invokevirtual 142	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   26: astore_1
    //   27: new 144	java/io/ByteArrayOutputStream
    //   30: dup
    //   31: invokespecial 145	java/io/ByteArrayOutputStream:<init>	()V
    //   34: astore_2
    //   35: iconst_0
    //   36: istore 5
    //   38: aload_0
    //   39: aload_1
    //   40: iload 5
    //   42: bipush 117
    //   44: invokespecial 149	com/sina/weibo/sdk/utils/AidTask:splite	([BII)I
    //   47: istore 6
    //   49: iload 6
    //   51: iconst_m1
    //   52: if_icmpne +120 -> 172
    //   55: aload_2
    //   56: invokevirtual 152	java/io/ByteArrayOutputStream:flush	()V
    //   59: aload_2
    //   60: invokevirtual 155	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   63: astore_1
    //   64: ldc 20
    //   66: new 157	java/lang/StringBuilder
    //   69: dup
    //   70: ldc 159
    //   72: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   75: aload_1
    //   76: arraylength
    //   77: invokevirtual 165	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   80: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   83: invokestatic 174	com/sina/weibo/sdk/utils/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   86: aload_1
    //   87: invokestatic 180	com/sina/weibo/sdk/utils/Base64:encodebyte	([B)[B
    //   90: astore_1
    //   91: ldc 20
    //   93: new 157	java/lang/StringBuilder
    //   96: dup
    //   97: ldc 182
    //   99: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   102: aload_1
    //   103: arraylength
    //   104: invokevirtual 165	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   107: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   110: invokestatic 174	com/sina/weibo/sdk/utils/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   113: new 108	java/lang/String
    //   116: dup
    //   117: aload_1
    //   118: ldc 139
    //   120: invokespecial 185	java/lang/String:<init>	([BLjava/lang/String;)V
    //   123: astore_1
    //   124: new 157	java/lang/StringBuilder
    //   127: dup
    //   128: ldc 187
    //   130: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   133: aload_1
    //   134: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   140: astore_1
    //   141: ldc 20
    //   143: new 157	java/lang/StringBuilder
    //   146: dup
    //   147: ldc 192
    //   149: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   152: aload_1
    //   153: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   159: invokestatic 174	com/sina/weibo/sdk/utils/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   162: aload_2
    //   163: ifnull +7 -> 170
    //   166: aload_2
    //   167: invokevirtual 193	java/io/ByteArrayOutputStream:close	()V
    //   170: aload_1
    //   171: areturn
    //   172: aload 4
    //   174: aload_1
    //   175: iload 5
    //   177: iload 6
    //   179: invokevirtual 197	javax/crypto/Cipher:doFinal	([BII)[B
    //   182: astore_3
    //   183: aload_2
    //   184: aload_3
    //   185: invokevirtual 198	java/io/ByteArrayOutputStream:write	([B)V
    //   188: ldc 20
    //   190: new 157	java/lang/StringBuilder
    //   193: dup
    //   194: ldc 200
    //   196: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   199: iload 5
    //   201: invokevirtual 165	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   204: ldc 202
    //   206: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   209: iload 6
    //   211: invokevirtual 165	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   214: ldc 204
    //   216: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: aload_3
    //   220: arraylength
    //   221: invokevirtual 165	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   224: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   227: invokestatic 174	com/sina/weibo/sdk/utils/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   230: iload 5
    //   232: iload 6
    //   234: iadd
    //   235: istore 5
    //   237: goto -199 -> 38
    //   240: astore_1
    //   241: aload_3
    //   242: astore_2
    //   243: aload_2
    //   244: ifnull +7 -> 251
    //   247: aload_2
    //   248: invokevirtual 193	java/io/ByteArrayOutputStream:close	()V
    //   251: aload_1
    //   252: athrow
    //   253: astore_2
    //   254: aload_1
    //   255: areturn
    //   256: astore_2
    //   257: goto -6 -> 251
    //   260: astore_1
    //   261: goto -18 -> 243
    //
    // Exception table:
    //   from	to	target	type
    //   27	35	240	finally
    //   166	170	253	java/io/IOException
    //   247	251	256	java/io/IOException
    //   38	49	260	finally
    //   55	162	260	finally
    //   172	230	260	finally
  }

  private String genMfpString()
  {
    Object localObject = new JSONObject();
    try
    {
      String str = getOS();
      if (!TextUtils.isEmpty(str))
        ((JSONObject)localObject).put("1", str);
      str = getImei();
      if (!TextUtils.isEmpty(str))
        ((JSONObject)localObject).put("2", str);
      str = getMeid();
      if (!TextUtils.isEmpty(str))
        ((JSONObject)localObject).put("3", str);
      str = getImsi();
      if (!TextUtils.isEmpty(str))
        ((JSONObject)localObject).put("4", str);
      str = getMac();
      if (!TextUtils.isEmpty(str))
        ((JSONObject)localObject).put("5", str);
      str = getIccid();
      if (!TextUtils.isEmpty(str))
        ((JSONObject)localObject).put("6", str);
      str = getSerialNo();
      if (!TextUtils.isEmpty(str))
        ((JSONObject)localObject).put("7", str);
      str = getAndroidId();
      if (!TextUtils.isEmpty(str))
        ((JSONObject)localObject).put("10", str);
      str = getCpu();
      if (!TextUtils.isEmpty(str))
        ((JSONObject)localObject).put("13", str);
      str = getModel();
      if (!TextUtils.isEmpty(str))
        ((JSONObject)localObject).put("14", str);
      str = getSdSize();
      if (!TextUtils.isEmpty(str))
        ((JSONObject)localObject).put("15", str);
      str = getResolution();
      if (!TextUtils.isEmpty(str))
        ((JSONObject)localObject).put("16", str);
      str = getSsid();
      if (!TextUtils.isEmpty(str))
        ((JSONObject)localObject).put("17", str);
      str = getDeviceName();
      if (!TextUtils.isEmpty(str))
        ((JSONObject)localObject).put("18", str);
      str = getConnectType();
      if (!TextUtils.isEmpty(str))
        ((JSONObject)localObject).put("19", str);
      localObject = ((JSONObject)localObject).toString();
      return localObject;
    }
    catch (JSONException localJSONException)
    {
    }
    return "";
  }

  private File getAidInfoFile(int paramInt)
  {
    return new File(this.mContext.getFilesDir(), "weibo_sdk_aid" + paramInt);
  }

  private String getAndroidId()
  {
    try
    {
      String str = Settings.Secure.getString(this.mContext.getContentResolver(), "android_id");
      return str;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  private String getConnectType()
  {
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null)
      {
        if (localNetworkInfo.getType() == 0);
        switch (localNetworkInfo.getSubtype())
        {
        default:
          if (localNetworkInfo.getType() == 1)
            return "wifi";
          break;
        case 1:
        case 2:
        case 4:
        case 7:
        case 11:
        case 3:
        case 5:
        case 6:
        case 8:
        case 9:
        case 10:
        case 12:
        case 14:
        case 15:
        case 13:
        }
      }
    }
    catch (Exception localException)
    {
      return "none";
    }
    return "none";
    return "2G";
    return "3G";
    return "4G";
  }

  private String getCpu()
  {
    try
    {
      String str = Build.CPU_ABI;
      return str;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  private String getDeviceName()
  {
    try
    {
      String str = Build.BRAND;
      return str;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  private String getIccid()
  {
    try
    {
      String str = ((TelephonyManager)this.mContext.getSystemService("phone")).getSimSerialNumber();
      return str;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  private String getImei()
  {
    try
    {
      String str = ((TelephonyManager)this.mContext.getSystemService("phone")).getDeviceId();
      return str;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  private String getImsi()
  {
    try
    {
      String str = ((TelephonyManager)this.mContext.getSystemService("phone")).getSubscriberId();
      return str;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  public static AidTask getInstance(Context paramContext)
  {
    try
    {
      if (sInstance == null)
        sInstance = new AidTask(paramContext);
      paramContext = sInstance;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  private String getMac()
  {
    try
    {
      Object localObject = (WifiManager)this.mContext.getSystemService("wifi");
      if (localObject == null)
        return "";
      localObject = ((WifiManager)localObject).getConnectionInfo();
      if (localObject != null)
        return ((WifiInfo)localObject).getMacAddress();
      return "";
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  private String getMeid()
  {
    try
    {
      String str = ((TelephonyManager)this.mContext.getSystemService("phone")).getDeviceId();
      return str;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  private String getMfp()
  {
    String str2 = genMfpString();
    String str1 = "";
    try
    {
      str2 = new String(str2.getBytes(), "UTF-8");
      str1 = str2;
      label25: LogUtil.d("AidTask", "genMfpString() utf-8 string : " + str1);
      try
      {
        str1 = encryptRsa(str1, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHHM0Fi2Z6+QYKXqFUX2Cy6AaWq3cPi+GSn9oeAwQbPZR75JB7Netm0HtBVVbtPhzT7UO2p1JhFUKWqrqoYuAjkgMVPmA0sFrQohns5EE44Y86XQopD4ZO+dE5KjUZFE6vrPO3rWW3np2BqlgKpjnYZri6TJApmIpGcQg9/G/3zQIDAQAB");
        LogUtil.d("AidTask", "encryptRsa() string : " + str1);
        return str1;
      }
      catch (Exception localException)
      {
        LogUtil.e("AidTask", localException.getMessage());
        return "";
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      break label25;
    }
  }

  private String getModel()
  {
    try
    {
      String str = Build.MODEL;
      return str;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  private String getOS()
  {
    try
    {
      String str = "Android " + Build.VERSION.RELEASE;
      return str;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  private PublicKey getPublicKey(String paramString)
    throws Exception
  {
    paramString = new X509EncodedKeySpec(Base64.decode(paramString.getBytes()));
    return KeyFactory.getInstance("RSA").generatePublic(paramString);
  }

  private String getResolution()
  {
    try
    {
      Object localObject = new DisplayMetrics();
      ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
      localObject = String.valueOf(((DisplayMetrics)localObject).widthPixels) + "*" + String.valueOf(((DisplayMetrics)localObject).heightPixels);
      return localObject;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  private String getSdSize()
  {
    try
    {
      Object localObject = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = ((StatFs)localObject).getBlockSize();
      localObject = Long.toString(((StatFs)localObject).getBlockCount() * l);
      return localObject;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  private String getSerialNo()
  {
    try
    {
      Object localObject = Class.forName("android.os.SystemProperties");
      localObject = (String)((Class)localObject).getMethod("get", new Class[] { String.class, String.class }).invoke(localObject, new Object[] { "ro.serialno", "unknown" });
      return localObject;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  private String getSsid()
  {
    try
    {
      Object localObject = ((WifiManager)this.mContext.getSystemService("wifi")).getConnectionInfo();
      if (localObject != null)
      {
        localObject = ((WifiInfo)localObject).getSSID();
        return localObject;
      }
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  private String loadAidFromNet()
    throws WeiboException
  {
    String str1 = this.mContext.getPackageName();
    String str2 = Utility.getSign(this.mContext, str1);
    String str3 = getMfp();
    WeiboParameters localWeiboParameters = new WeiboParameters(this.mAppKey);
    localWeiboParameters.put("appkey", this.mAppKey);
    localWeiboParameters.put("mfp", str3);
    localWeiboParameters.put("packagename", str1);
    localWeiboParameters.put("key_hash", str2);
    try
    {
      str1 = new AsyncWeiboRunner(this.mContext).request("http://api.weibo.com/oauth2/getaid.json", localWeiboParameters, "GET");
      LogUtil.d("AidTask", "loadAidFromNet response : " + str1);
      return str1;
    }
    catch (WeiboException localWeiboException)
    {
      LogUtil.d("AidTask", "loadAidFromNet WeiboException Msg : " + localWeiboException.getMessage());
      throw localWeiboException;
    }
  }

  // ERROR //
  private AidInfo loadAidInfoFromCache()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aconst_null
    //   3: astore_2
    //   4: aconst_null
    //   5: astore_3
    //   6: new 555	java/io/FileInputStream
    //   9: dup
    //   10: aload_0
    //   11: iconst_1
    //   12: invokespecial 74	com/sina/weibo/sdk/utils/AidTask:getAidInfoFile	(I)Ljava/io/File;
    //   15: invokespecial 556	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   18: astore_1
    //   19: aload_1
    //   20: invokevirtual 559	java/io/FileInputStream:available	()I
    //   23: newarray byte
    //   25: astore_2
    //   26: aload_1
    //   27: aload_2
    //   28: invokevirtual 563	java/io/FileInputStream:read	([B)I
    //   31: pop
    //   32: new 108	java/lang/String
    //   35: dup
    //   36: aload_2
    //   37: invokespecial 564	java/lang/String:<init>	([B)V
    //   40: invokestatic 568	com/sina/weibo/sdk/utils/AidTask$AidInfo:parseJson	(Ljava/lang/String;)Lcom/sina/weibo/sdk/utils/AidTask$AidInfo;
    //   43: astore_2
    //   44: aload_1
    //   45: ifnull +7 -> 52
    //   48: aload_1
    //   49: invokevirtual 569	java/io/FileInputStream:close	()V
    //   52: aload_0
    //   53: monitorexit
    //   54: aload_2
    //   55: areturn
    //   56: astore_1
    //   57: aload_3
    //   58: astore_1
    //   59: aload_1
    //   60: ifnull +7 -> 67
    //   63: aload_1
    //   64: invokevirtual 569	java/io/FileInputStream:close	()V
    //   67: aconst_null
    //   68: astore_2
    //   69: goto -17 -> 52
    //   72: astore_1
    //   73: aload_2
    //   74: ifnull +7 -> 81
    //   77: aload_2
    //   78: invokevirtual 569	java/io/FileInputStream:close	()V
    //   81: aload_1
    //   82: athrow
    //   83: astore_1
    //   84: aload_0
    //   85: monitorexit
    //   86: aload_1
    //   87: athrow
    //   88: astore_1
    //   89: goto -37 -> 52
    //   92: astore_1
    //   93: goto -26 -> 67
    //   96: astore_2
    //   97: goto -16 -> 81
    //   100: astore_3
    //   101: aload_1
    //   102: astore_2
    //   103: aload_3
    //   104: astore_1
    //   105: goto -32 -> 73
    //   108: astore_2
    //   109: goto -50 -> 59
    //   112: astore_1
    //   113: goto -29 -> 84
    //
    // Exception table:
    //   from	to	target	type
    //   6	19	56	java/lang/Exception
    //   6	19	72	finally
    //   63	67	83	finally
    //   77	81	83	finally
    //   81	83	83	finally
    //   48	52	88	java/io/IOException
    //   63	67	92	java/io/IOException
    //   77	81	96	java/io/IOException
    //   19	44	100	finally
    //   19	44	108	java/lang/Exception
    //   48	52	112	finally
  }

  private int splite(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramInt1 >= paramArrayOfByte.length)
      return -1;
    return Math.min(paramArrayOfByte.length - paramInt1, paramInt2);
  }

  public void aidTaskInit()
  {
    aidTaskInit(this.mAppKey);
  }

  public void aidTaskInit(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return;
    this.mAppKey = paramString;
    new Thread(new Runnable()
    {
      public void run()
      {
        if (!AidTask.this.mTaskLock.tryLock())
          return;
        if (!TextUtils.isEmpty(AidTask.this.loadAidFromCache()))
        {
          AidTask.this.mTaskLock.unlock();
          return;
        }
        int i = 0;
        while (true)
        {
          if (i >= 3);
          while (true)
          {
            AidTask.this.mTaskLock.unlock();
            return;
            try
            {
              String str = AidTask.this.loadAidFromNet();
              AidTask.AidInfo.parseJson(str);
              AidTask.this.cacheAidInfo(str);
            }
            catch (WeiboException localWeiboException)
            {
              LogUtil.e("AidTask", "AidTaskInit WeiboException Msg : " + localWeiboException.getMessage());
              i += 1;
            }
          }
        }
      }
    }).start();
  }

  public void getAidAsync(final Handler paramHandler)
  {
    if (TextUtils.isEmpty(this.mAppKey))
      return;
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          String str = AidTask.this.loadAidFromNet();
          AidTask.AidInfo localAidInfo = AidTask.AidInfo.parseJson(str);
          AidTask.this.cacheAidInfo(str);
          this.val$msg.what = 1001;
          this.val$msg.obj = localAidInfo;
          if (paramHandler != null)
            paramHandler.sendMessage(this.val$msg);
          return;
        }
        catch (WeiboException localWeiboException)
        {
          do
          {
            while (((localWeiboException.getCause() instanceof IOException)) || ((localWeiboException instanceof WeiboHttpException)))
            {
              this.val$msg.what = 1003;
              if (paramHandler != null)
              {
                paramHandler.sendMessage(this.val$msg);
                return;
              }
            }
            this.val$msg.what = 1002;
          }
          while (paramHandler == null);
          paramHandler.sendMessage(this.val$msg);
        }
      }
    }).start();
  }

  public AidInfo getAidSync()
    throws WeiboException
  {
    if (TextUtils.isEmpty(this.mAppKey))
      return null;
    String str = loadAidFromNet();
    AidInfo localAidInfo = AidInfo.parseJson(str);
    cacheAidInfo(str);
    return localAidInfo;
  }

  public ReentrantLock getTaskLock()
  {
    return this.mTaskLock;
  }

  public String loadAidFromCache()
  {
    try
    {
      Object localObject1 = loadAidInfoFromCache();
      if (localObject1 != null);
      for (localObject1 = ((AidInfo)localObject1).getAid(); ; localObject1 = "")
        return localObject1;
    }
    finally
    {
    }
  }

  public String loadSubCookieFromCache()
  {
    try
    {
      Object localObject1 = loadAidInfoFromCache();
      if (localObject1 != null);
      for (localObject1 = ((AidInfo)localObject1).getSubCookie(); ; localObject1 = "")
        return localObject1;
    }
    finally
    {
    }
  }

  public void setAppkey(String paramString)
  {
    this.mAppKey = paramString;
  }

  public static final class AidInfo
  {
    private String mAid;
    private String mSubCookie;

    public static AidInfo parseJson(String paramString)
      throws WeiboException
    {
      AidInfo localAidInfo = new AidInfo();
      try
      {
        paramString = new JSONObject(paramString);
        if ((paramString.has("error")) || (paramString.has("error_code")))
        {
          LogUtil.d("AidTask", "loadAidFromNet has error !!!");
          throw new WeiboException("loadAidFromNet has error !!!");
        }
      }
      catch (JSONException paramString)
      {
        LogUtil.d("AidTask", "loadAidFromNet JSONException Msg : " + paramString.getMessage());
        throw new WeiboException("loadAidFromNet has error !!!");
      }
      localAidInfo.mAid = paramString.optString("aid", "");
      localAidInfo.mSubCookie = paramString.optString("sub", "");
      return localAidInfo;
    }

    public String getAid()
    {
      return this.mAid;
    }

    public String getSubCookie()
    {
      return this.mSubCookie;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.utils.AidTask
 * JD-Core Version:    0.6.2
 */