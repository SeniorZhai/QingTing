package com.tencent.weibo.sdk.android.component.sso;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.Uri;
import com.tencent.weibo.sdk.android.component.sso.tools.Base64;
import com.tencent.weibo.sdk.android.component.sso.tools.Cryptor;
import com.tencent.weibo.sdk.android.component.sso.tools.MD5Tools;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Random;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class AuthHelper
{
  static final String ENCRYPT_KEY = "&-*)Wb5_U,[^!9'+";
  static final int ERROR_WEIBO_INSTALL_NEEDED = -2;
  static final int ERROR_WEIBO_UPGRADE_NEEDED = -1;
  static final byte SDK_VERSION = 1;
  static final int SUPPORT_WEIBO_MIN_VERSION = 44;
  static final String WEIBO_AUTH_URL = "TencentAuth://weibo";
  static final String WEIBO_PACKAGE = "com.tencent.WBlog";
  static final int WEIBO_VALIDATE_OK = 0;
  protected static String appSecret;
  protected static long appid;
  protected static OnAuthListener listener;
  private static AuthReceiver mReceiver = new AuthReceiver();

  public static final boolean auth(Context paramContext, String paramString)
  {
    int i = validateWeiboApp(paramContext);
    long l1;
    long l2;
    String str1;
    Object localObject1;
    byte[] arrayOfByte;
    String str2;
    Object localObject2;
    if (i == 0)
    {
      l1 = System.currentTimeMillis() / 1000L;
      l2 = Math.abs(new Random().nextInt());
      str1 = getApkSignature(paramContext);
      localObject1 = generateSignature(appid, appSecret, l1, l2);
      if (localObject1 == null)
      {
        if (listener != null)
          listener.onAuthFail(-1, "");
        return false;
      }
      arrayOfByte = encypt((byte[])localObject1);
      str2 = paramContext.getPackageName();
      localObject2 = paramContext.getPackageManager();
      localObject1 = "";
    }
    try
    {
      localObject2 = ((PackageManager)localObject2).getApplicationLabel(((PackageManager)localObject2).getApplicationInfo(str2, 0)).toString();
      localObject1 = localObject2;
      label118: localObject2 = new Intent("android.intent.action.VIEW", Uri.parse("TencentAuth://weibo"));
      ((Intent)localObject2).putExtra("com.tencent.sso.APP_ID", appid);
      ((Intent)localObject2).putExtra("com.tencent.sso.TIMESTAMP", l1);
      ((Intent)localObject2).putExtra("com.tencent.sso.NONCE", l2);
      ((Intent)localObject2).putExtra("com.tencent.sso.SDK_VERSION", (byte)1);
      ((Intent)localObject2).putExtra("com.tencent.sso.PACKAGE_NAME", str2);
      ((Intent)localObject2).putExtra("com.tencent.sso.ICON_MD5", str1);
      ((Intent)localObject2).putExtra("com.tencent.sso.APP_NAME", (String)localObject1);
      ((Intent)localObject2).putExtra("com.tencent.sso.SIGNATURE", arrayOfByte);
      ((Intent)localObject2).putExtra("com.tencent.sso.RESERVER", paramString);
      paramContext.startActivity((Intent)localObject2);
      return true;
      if (i == -1)
      {
        if (listener != null)
          listener.onWeiboVersionMisMatch();
        return false;
      }
      if (i == -2)
      {
        if (listener != null)
          listener.onWeiBoNotInstalled();
        return false;
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      break label118;
    }
  }

  private static byte[] encypt(byte[] paramArrayOfByte)
  {
    return new Cryptor().encrypt(paramArrayOfByte, "&-*)Wb5_U,[^!9'+".getBytes());
  }

  private static byte[] generateSignature(long paramLong1, String paramString, long paramLong2, long paramLong3)
  {
    Object localObject = null;
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(paramLong1);
    localStringBuffer.append(paramLong2);
    localStringBuffer.append(paramLong3);
    localStringBuffer.append(1);
    try
    {
      Mac localMac = Mac.getInstance("HmacSHA1");
      localMac.init(new SecretKeySpec(paramString.getBytes("UTF-8"), localMac.getAlgorithm()));
      paramString = localMac.doFinal(localStringBuffer.toString().getBytes("UTF-8"));
      return Base64.encode(paramString).getBytes();
    }
    catch (Exception paramString)
    {
      while (true)
        paramString = localObject;
    }
  }

  private static String getApkSignature(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64).signatures[0];
      paramContext = (X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(paramContext.toByteArray()));
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(paramContext.getPublicKey().toString());
      localStringBuffer.append(paramContext.getSerialNumber().toString());
      paramContext = MD5Tools.toMD5(localStringBuffer.toString());
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
      return "";
    }
    catch (CertificateException paramContext)
    {
      while (true)
        paramContext.printStackTrace();
    }
    catch (Exception paramContext)
    {
      while (true)
        paramContext.printStackTrace();
    }
  }

  public static final void register(Context paramContext, long paramLong, String paramString, OnAuthListener paramOnAuthListener)
  {
    appid = paramLong;
    appSecret = paramString;
    listener = paramOnAuthListener;
    paramString = new IntentFilter("com.tencent.sso.AUTH");
    paramString.addCategory("android.intent.category.DEFAULT");
    paramContext.registerReceiver(mReceiver, paramString);
  }

  public static final void unregister(Context paramContext)
  {
    paramContext.unregisterReceiver(mReceiver);
  }

  private static int validateWeiboApp(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      PackageInfo localPackageInfo = paramContext.getPackageInfo("com.tencent.WBlog", 16);
      if ((localPackageInfo != null) && (localPackageInfo.versionCode >= 44))
      {
        int i = paramContext.queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("TencentAuth://weibo")), 65536).size();
        if (i > 0)
          return 0;
      }
      return -1;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return -2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.component.sso.AuthHelper
 * JD-Core Version:    0.6.2
 */