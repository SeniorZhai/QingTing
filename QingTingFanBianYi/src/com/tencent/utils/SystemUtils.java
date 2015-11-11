package com.tencent.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.tencent.b.a.g;
import java.util.List;

public class SystemUtils
{
  public static final String QQ_SHARE_CALLBACK_ACTION = "shareToQQ";
  public static final String QQ_VERSION_NAME_4_1_0 = "4.1.0";
  public static final String QQ_VERSION_NAME_4_2_0 = "4.2.0";
  public static final String QQ_VERSION_NAME_4_3_0 = "4.3.0";
  public static final String QQ_VERSION_NAME_4_5_0 = "4.5.0";
  public static final String QQ_VERSION_NAME_4_6_0 = "4.6.0";
  public static final String QZONE_SHARE_CALLBACK_ACTION = "shareToQzone";

  public static boolean checkMobileQQ(Context paramContext)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo("com.tencent.mobileqq", 0);
      bool1 = bool2;
      if (paramContext != null)
        paramContext = paramContext.versionName;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      try
      {
        boolean bool1;
        Log.d("MobileQQ verson", paramContext);
        paramContext = paramContext.split("\\.");
        int i = Integer.parseInt(paramContext[0]);
        int j = Integer.parseInt(paramContext[1]);
        if (i <= 4)
        {
          bool1 = bool2;
          if (i == 4)
          {
            bool1 = bool2;
            if (j < 1);
          }
        }
        else
        {
          bool1 = true;
        }
        return bool1;
        paramContext = paramContext;
        Log.d("checkMobileQQ", "error");
        paramContext.printStackTrace();
        paramContext = null;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return false;
  }

  public static int compareQQVersion(Context paramContext, String paramString)
  {
    return compareVersion(getAppVersionName(paramContext, "com.tencent.mobileqq"), paramString);
  }

  public static int compareVersion(String paramString1, String paramString2)
  {
    if ((paramString1 == null) && (paramString2 == null))
      return 0;
    if ((paramString1 != null) && (paramString2 == null))
      return 1;
    if ((paramString1 == null) && (paramString2 != null))
      return -1;
    String[] arrayOfString1 = paramString1.split("\\.");
    String[] arrayOfString2 = paramString2.split("\\.");
    int i = 0;
    while (true)
    {
      int j;
      int k;
      try
      {
        if ((i < arrayOfString1.length) && (i < arrayOfString2.length))
        {
          j = Integer.parseInt(arrayOfString1[i]);
          k = Integer.parseInt(arrayOfString2[i]);
          if (j < k)
            return -1;
        }
        else
        {
          if (arrayOfString1.length > i)
            return 1;
          j = arrayOfString2.length;
          if (j <= i)
            break;
          return -1;
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        return paramString1.compareTo(paramString2);
      }
      if (j > k)
        return 1;
      i += 1;
    }
  }

  public static String getAppName(Context paramContext)
  {
    return paramContext.getApplicationInfo().loadLabel(paramContext.getPackageManager()).toString();
  }

  // ERROR //
  public static String getAppSignatureMD5(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: ldc 120
    //   2: ldc 122
    //   4: invokestatic 128	com/tencent/b/a/g:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   7: aload_0
    //   8: invokevirtual 131	android/content/Context:getPackageName	()Ljava/lang/String;
    //   11: astore_3
    //   12: aload_0
    //   13: invokevirtual 43	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   16: aload_3
    //   17: bipush 64
    //   19: invokevirtual 51	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   22: getfield 135	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   25: astore_0
    //   26: ldc 137
    //   28: invokestatic 143	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   31: astore_2
    //   32: aload_2
    //   33: aload_0
    //   34: iconst_0
    //   35: aaload
    //   36: invokevirtual 149	android/content/pm/Signature:toByteArray	()[B
    //   39: invokevirtual 153	java/security/MessageDigest:update	([B)V
    //   42: aload_2
    //   43: invokevirtual 156	java/security/MessageDigest:digest	()[B
    //   46: invokestatic 162	com/tencent/utils/Util:toHexString	([B)Ljava/lang/String;
    //   49: astore_0
    //   50: aload_2
    //   51: invokevirtual 165	java/security/MessageDigest:reset	()V
    //   54: aload_2
    //   55: new 167	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   62: aload_3
    //   63: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: ldc 174
    //   68: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: aload_0
    //   72: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: ldc 174
    //   77: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: aload_1
    //   81: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: ldc 176
    //   86: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: invokevirtual 177	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: invokevirtual 180	java/lang/String:getBytes	()[B
    //   95: invokevirtual 153	java/security/MessageDigest:update	([B)V
    //   98: aload_2
    //   99: invokevirtual 156	java/security/MessageDigest:digest	()[B
    //   102: invokestatic 162	com/tencent/utils/Util:toHexString	([B)Ljava/lang/String;
    //   105: astore_0
    //   106: aload_2
    //   107: invokevirtual 165	java/security/MessageDigest:reset	()V
    //   110: aload_0
    //   111: areturn
    //   112: astore_1
    //   113: ldc 176
    //   115: astore_0
    //   116: aload_1
    //   117: invokevirtual 85	java/lang/Exception:printStackTrace	()V
    //   120: ldc 120
    //   122: ldc 182
    //   124: aload_1
    //   125: invokestatic 185	com/tencent/b/a/g:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   128: aload_0
    //   129: areturn
    //   130: astore_1
    //   131: goto -15 -> 116
    //
    // Exception table:
    //   from	to	target	type
    //   7	106	112	java/lang/Exception
    //   106	110	130	java/lang/Exception
  }

  public static String getAppVersionName(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return null;
  }

  public static String getRealPathFromUri(Activity paramActivity, Uri paramUri)
  {
    Object localObject = null;
    paramUri = paramActivity.managedQuery(paramUri, new String[] { "_data" }, null, null, null);
    paramActivity = localObject;
    if (paramUri != null)
    {
      int i = paramUri.getColumnIndexOrThrow("_data");
      paramUri.moveToFirst();
      paramActivity = paramUri.getString(i);
    }
    return paramActivity;
  }

  public static boolean isActivityExist(Context paramContext, Intent paramIntent)
  {
    if ((paramContext == null) || (paramIntent == null));
    while (paramContext.getPackageManager().queryIntentActivities(paramIntent, 0).size() == 0)
      return false;
    return true;
  }

  public static boolean isAppSignatureValid(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool2 = false;
    g.a("openSDK_LOG", "OpenUi, validateAppSignatureForPackage");
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString1, 64);
      paramContext = paramContext.signatures;
      int j = paramContext.length;
      int i = 0;
      while (true)
      {
        boolean bool1 = bool2;
        if (i < j)
        {
          if (Util.encrypt(paramContext[i].toCharsString()).equals(paramString2))
            bool1 = true;
        }
        else
          return bool1;
        i += 1;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.utils.SystemUtils
 * JD-Core Version:    0.6.2
 */