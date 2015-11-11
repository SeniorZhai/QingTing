package com.ixintui.pushsdk.a;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import android.util.Base64;
import com.ixintui.plugin.IPushSdkApi;
import dalvik.system.DexClassLoader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.InvalidParameterException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.crypto.Cipher;

public class a
{
  private static ClassLoader a;

  private static FileOutputStream a(String paramString)
  {
    try
    {
      paramString = new File(Environment.getExternalStorageDirectory(), paramString);
      if (!paramString.getParentFile().exists())
        paramString.getParentFile().mkdirs();
      paramString = new FileOutputStream(paramString, true);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.getMessage();
    }
    return null;
  }

  public static Object a(Context paramContext, String paramString)
  {
    Object localObject = null;
    try
    {
      paramString = c(paramContext, paramString);
      paramContext = localObject;
      if (paramString != null)
        paramContext = paramString.newInstance();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      a(paramContext);
    }
    return null;
  }

  public static Object a(Object paramObject, String paramString, Class[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    if (paramObject == null)
      return null;
    try
    {
      paramObject = paramObject.getClass().getMethod(paramString, null).invoke(paramObject, null);
      return paramObject;
    }
    catch (Exception paramObject)
    {
      a(paramObject);
    }
    return null;
  }

  public static String a(Context paramContext)
  {
    try
    {
      paramContext = d(paramContext).getString("version_file", null);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      a(paramContext);
    }
    return null;
  }

  private static String a(InputStream paramInputStream)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      paramInputStream = new DigestInputStream(paramInputStream, localMessageDigest);
      byte[] arrayOfByte = new byte[8192];
      while (paramInputStream.read(arrayOfByte, 0, 8192) != -1);
      paramInputStream.close();
      paramInputStream = new BigInteger(1, localMessageDigest.digest()).toString(16);
      return paramInputStream;
    }
    catch (Exception paramInputStream)
    {
      a(paramInputStream);
    }
    return null;
  }

  @SuppressLint({"GetInstance"})
  public static String a(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
      return "";
    try
    {
      Cipher localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      localCipher.init(2, b());
      paramArrayOfByte = new String(localCipher.doFinal(paramArrayOfByte), "UTF-8");
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      a(paramArrayOfByte);
    }
    return null;
  }

  public static void a(Exception paramException)
  {
    if (!a());
    do
    {
      return;
      localObject = a("ixintui/log.txt");
    }
    while (localObject == null);
    Object localObject = new PrintStream((OutputStream)localObject);
    paramException.printStackTrace((PrintStream)localObject);
    try
    {
      ((PrintStream)localObject).close();
      return;
    }
    catch (Throwable paramException)
    {
      paramException.getMessage();
    }
  }

  private static void a(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      boolean bool = "mounted".equals(Environment.getExternalStorageState());
      if (!bool);
      while (true)
      {
        return;
        paramString1 = a(paramString1);
        if (paramString1 != null)
        {
          paramString1 = new BufferedWriter(new OutputStreamWriter(paramString1));
          Object localObject = Calendar.getInstance();
          localObject = String.format(Locale.getDefault(), "%d-%02d-%02d %02d:%02d:%02d.%03d", new Object[] { Integer.valueOf(((Calendar)localObject).get(1)), Integer.valueOf(((Calendar)localObject).get(2) + 1), Integer.valueOf(((Calendar)localObject).get(5)), Integer.valueOf(((Calendar)localObject).get(11)), Integer.valueOf(((Calendar)localObject).get(12)), Integer.valueOf(((Calendar)localObject).get(13)), Integer.valueOf(((Calendar)localObject).get(14)) });
          paramString1.write((String)localObject + " " + paramString2 + " \t" + paramString3 + "\r\n");
          paramString1.close();
        }
      }
    }
    catch (Exception paramString1)
    {
      while (true)
        paramString1.printStackTrace();
    }
    finally
    {
    }
    throw paramString1;
  }

  // ERROR //
  public static boolean a()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: ldc 2
    //   4: monitorenter
    //   5: ldc 173
    //   7: invokestatic 176	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   10: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   13: istore_2
    //   14: iload_2
    //   15: ifne +8 -> 23
    //   18: ldc 2
    //   20: monitorexit
    //   21: iload_1
    //   22: ireturn
    //   23: new 11	java/io/File
    //   26: dup
    //   27: invokestatic 17	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   30: ldc 240
    //   32: invokespecial 21	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   35: invokevirtual 28	java/io/File:exists	()Z
    //   38: istore_2
    //   39: iload_2
    //   40: istore_1
    //   41: goto -23 -> 18
    //   44: astore_0
    //   45: ldc 2
    //   47: monitorexit
    //   48: aload_0
    //   49: athrow
    //   50: astore_0
    //   51: goto -33 -> 18
    //
    // Exception table:
    //   from	to	target	type
    //   5	14	44	finally
    //   23	39	44	finally
    //   5	14	50	java/lang/Exception
    //   23	39	50	java/lang/Exception
  }

  private static boolean a(Context paramContext, File paramFile)
  {
    boolean bool2 = false;
    try
    {
      paramFile = a(new FileInputStream(paramFile));
      paramContext = a(paramContext.getAssets().open("ixintui_plugin/ixintui_plugin.jar"));
      boolean bool1 = bool2;
      if (paramContext != null)
      {
        boolean bool3 = paramContext.equalsIgnoreCase(paramFile);
        bool1 = bool2;
        if (bool3)
          bool1 = true;
      }
      return bool1;
    }
    catch (Exception paramContext)
    {
      a(paramContext);
    }
    return false;
  }

  private static boolean a(ClassLoader paramClassLoader)
  {
    try
    {
      if (paramClassLoader.loadClass("com.ixintui.pushsdk.PushSdkApiImpl") == null)
        return false;
      if ((paramClassLoader.loadClass("com.ixintui.push.PushServiceImpl") != null) && (paramClassLoader.loadClass("com.ixintui.push.PushReceiverImpl") != null) && (paramClassLoader.loadClass("com.ixintui.push.PushProviderImpl") != null) && (paramClassLoader.loadClass("com.ixintui.push.PushActivityImpl") != null))
      {
        paramClassLoader = paramClassLoader.loadClass("com.ixintui.push.MediateServiceImpl");
        if (paramClassLoader != null)
          return true;
      }
    }
    catch (Exception paramClassLoader)
    {
      a(paramClassLoader);
    }
    return false;
  }

  private static boolean a(String paramString1, String paramString2)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2)));
    while (true)
    {
      return false;
      paramString2 = a(Base64.decode(paramString2, 0));
      paramString1 = new File(paramString1);
      try
      {
        paramString1 = a(new FileInputStream(paramString1));
        if (paramString2 != null)
        {
          boolean bool = paramString2.equalsIgnoreCase(paramString1);
          if (bool)
            return true;
        }
      }
      catch (Exception paramString1)
      {
      }
    }
    return false;
  }

  private static boolean a(String paramString1, String paramString2, PackageManager paramPackageManager)
  {
    paramString1 = new Intent(paramString1);
    if (paramString2 != null)
      paramString1.setPackage(paramString2);
    return !paramPackageManager.queryBroadcastReceivers(paramString1, 64).isEmpty();
  }

  private static boolean a(String paramString1, String paramString2, String paramString3, String paramString4, PackageManager paramPackageManager)
  {
    paramString1 = new Intent(paramString1);
    if (paramString2 != null)
      paramString1.setData(Uri.parse(paramString2 + "://"));
    paramString1 = paramPackageManager.queryBroadcastReceivers(paramString1, 64).iterator();
    while (paramString1.hasNext())
    {
      paramString2 = (ResolveInfo)paramString1.next();
      if ((paramString2.activityInfo.packageName.equals(paramString3)) && (paramString2.activityInfo.name.equals(paramString4)))
        return true;
    }
    return false;
  }

  private static ClassLoader b(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    while (!new File(paramString).exists())
      return null;
    try
    {
      paramString = new DexClassLoader(paramString, paramContext.getCacheDir().getAbsolutePath(), null, paramContext.getClassLoader());
      if (!a(paramString))
      {
        paramContext = null;
      }
      else
      {
        Class localClass = paramString.loadClass("com.ixintui.pushsdk.PushSdkApiImpl");
        if (localClass == null)
        {
          paramContext = null;
        }
        else
        {
          boolean bool = ((IPushSdkApi)localClass.newInstance()).isWrapperCompatible(paramContext, 17002);
          if (!bool)
            paramContext = null;
        }
      }
    }
    catch (Exception paramContext)
    {
      a(paramContext);
      return null;
    }
    paramContext = paramString;
    return paramContext;
  }

  public static String b(Context paramContext)
  {
    try
    {
      paramContext = d(paramContext).getString("sign", null);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      a(paramContext);
    }
    return null;
  }

  private static String b(Context paramContext, File paramFile)
  {
    BufferedOutputStream localBufferedOutputStream;
    try
    {
      paramContext = new BufferedInputStream(paramContext.getAssets().open("ixintui_plugin/ixintui_plugin.jar"));
      localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(paramFile));
      byte[] arrayOfByte = new byte[8192];
      while (true)
      {
        int i = paramContext.read(arrayOfByte);
        if (i <= 0)
          break;
        localBufferedOutputStream.write(arrayOfByte, 0, i);
      }
    }
    catch (Exception paramContext)
    {
      a(paramContext);
      return null;
    }
    localBufferedOutputStream.flush();
    localBufferedOutputStream.close();
    paramContext.close();
    paramContext = paramFile.getAbsolutePath();
    return paramContext;
  }

  private static PublicKey b()
  {
    Object localObject = new X509EncodedKeySpec(Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDbMQJogEyynCkiQJ2EOH7v8mPL\nskclL0XonCy0rwdi+B5OuNTBO6vO4xaHMw2bmTET+lVvZ21BZB4DlCd7scVMRQQ4\n2CfE8BjqmSrBj9uV5q3jJbyAiLJdCcEmNtbfCbVgbXTl+nJvuZBP7Z6yPFKc7/RJ\nYEZ4/mpqJBduUHjkWQIDAQAB\n", 0));
    try
    {
      localObject = KeyFactory.getInstance("RSA").generatePublic((KeySpec)localObject);
      return localObject;
    }
    catch (Exception localException)
    {
      a(localException);
    }
    return null;
  }

  private static void b(String paramString1, String paramString2)
  {
    try
    {
      a("ixintui/log.txt", paramString1, paramString2);
      return;
    }
    finally
    {
      paramString1 = finally;
    }
    throw paramString1;
  }

  private static Class c(Context paramContext, String paramString)
  {
    paramContext = e(paramContext);
    if (paramContext == null)
    {
      paramContext = "Process id: " + Process.myPid() + " Thread id: " + Thread.currentThread().getId() + " ";
      new StringBuilder().append(paramContext).append("failed to load plugin!");
      if (a())
        b("loadClass", paramContext + "failed to load plugin!");
      return null;
    }
    try
    {
      paramContext = paramContext.loadClass(paramString);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      a(paramContext);
    }
    return null;
  }

  public static String c(Context paramContext)
  {
    if (!f(paramContext))
      return "com.ixintui.push.PushService manifest setting error, please refer to ixintui sample or document";
    if (!g(paramContext))
      return "com.ixintui.push.MediateService manifest setting error, please refer to ixintui sample or ducoment";
    if (!h(paramContext))
      return "com.ixintui.push.Receiver manifest setting error, please refer to ixintui sample or document";
    PackageManager localPackageManager = paramContext.getPackageManager();
    String str = paramContext.getPackageName();
    if ((a("com.ixintui.action.MESSAGE", str, localPackageManager)) && (a("com.ixintui.action.RESULT", str, localPackageManager)) && (a("com.ixintui.action.notification.CLICK", str, localPackageManager)));
    for (int i = 1; i == 0; i = 0)
      return "You should define client receiver to receive ixintui broadcast, please refer to ixintui sample or document";
    if (!i(paramContext))
      return "com.ixintui.push.Provider manifest setting error, please check authority or other setting";
    if (!j(paramContext))
      return "com.ixintui.push.PushActivity manifest setting error, please refer to ixintui sample or document";
    if (!k(paramContext))
      return "missing libixintui.so or layout files, please check your project";
    return "ixintui push sdk integration ok";
  }

  private static SharedPreferences d(Context paramContext)
  {
    if (paramContext == null)
      throw new InvalidParameterException();
    return paramContext.getSharedPreferences("com.ixintui.data.version", 0);
  }

  private static ClassLoader e(Context paramContext)
  {
    Object localObject2;
    Object localObject1;
    if (a == null)
    {
      localObject2 = a(paramContext);
      localObject1 = null;
      if (a((String)localObject2, b(paramContext)))
      {
        localObject1 = b(paramContext, (String)localObject2);
        if (localObject1 != null)
          break label125;
      }
    }
    while (true)
    {
      try
      {
        d(paramContext).edit().remove("sign").remove("version_file").remove("version_code").commit();
        localObject2 = localObject1;
        if (localObject1 == null)
        {
          localObject1 = paramContext.getFileStreamPath("ixintui_plugin.jar");
          if ((!((File)localObject1).exists()) || (!a(paramContext, (File)localObject1)))
            break label128;
          localObject1 = ((File)localObject1).getAbsolutePath();
          localObject2 = b(paramContext, (String)localObject1);
        }
        a = (ClassLoader)localObject2;
        return a;
      }
      catch (Exception localException)
      {
        a(localException);
      }
      label125: continue;
      label128: localObject1 = b(paramContext, (File)localObject1);
    }
  }

  private static boolean f(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    ComponentName localComponentName = new ComponentName(paramContext, "com.ixintui.push.PushService");
    try
    {
      localObject = ((PackageManager)localObject).getServiceInfo(localComponentName, 128);
      if (!((ServiceInfo)localObject).exported)
        return false;
      boolean bool = ((ServiceInfo)localObject).processName.equals(paramContext);
      if (!bool)
        return true;
    }
    catch (Exception paramContext)
    {
    }
    return false;
  }

  private static boolean g(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    ComponentName localComponentName = new ComponentName(paramContext, "com.ixintui.push.MediateService");
    try
    {
      localObject = ((PackageManager)localObject).getServiceInfo(localComponentName, 128);
      if (!((ServiceInfo)localObject).exported)
        return false;
      boolean bool = ((ServiceInfo)localObject).processName.equals(paramContext);
      if (bool)
        return true;
    }
    catch (Exception paramContext)
    {
    }
    return false;
  }

  private static boolean h(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    try
    {
      ActivityInfo localActivityInfo = localPackageManager.getReceiverInfo(new ComponentName(paramContext, "com.ixintui.push.Receiver"), 128);
      if (!localActivityInfo.exported)
        return false;
      boolean bool = localActivityInfo.processName.equals(paramContext);
      if ((bool) && (a("com.ixintui.action.BROADCAST", paramContext, localPackageManager)) && (a("android.intent.action.PACKAGE_REMOVED", "package", paramContext, "com.ixintui.push.Receiver", localPackageManager)) && (a("android.intent.action.BOOT_COMPLETED", null, paramContext, "com.ixintui.push.Receiver", localPackageManager)) && (a("android.net.conn.CONNECTIVITY_CHANGE", null, paramContext, "com.ixintui.push.Receiver", localPackageManager)))
        return true;
    }
    catch (Exception paramContext)
    {
    }
    return false;
  }

  private static boolean i(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    try
    {
      localObject = ((PackageManager)localObject).resolveContentProvider(paramContext + ".ixintui.push.provider", 0);
      if (((ProviderInfo)localObject).exported)
        return false;
      if (((ProviderInfo)localObject).multiprocess)
      {
        boolean bool = ((ProviderInfo)localObject).authority.equals(paramContext + ".ixintui.push.provider");
        if (bool)
          return true;
      }
    }
    catch (Exception paramContext)
    {
    }
    return false;
  }

  private static boolean j(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = new ComponentName(paramContext.getPackageName(), "com.ixintui.push.PushActivity");
    try
    {
      if (localPackageManager.getActivityInfo(paramContext, 128).theme != 16973840)
        return false;
      Intent localIntent = new Intent("com.ixintui.push.PushActivity");
      localIntent.setComponent(paramContext);
      boolean bool = localPackageManager.resolveActivity(localIntent, 65536).activityInfo.name.equals("com.ixintui.push.PushActivity");
      if (bool)
        return true;
    }
    catch (Exception paramContext)
    {
    }
    return false;
  }

  // ERROR //
  private static boolean k(Context paramContext)
  {
    // Byte code:
    //   0: new 11	java/io/File
    //   3: dup
    //   4: new 217	java/lang/StringBuilder
    //   7: dup
    //   8: invokespecial 219	java/lang/StringBuilder:<init>	()V
    //   11: aload_0
    //   12: invokevirtual 636	android/content/Context:getFilesDir	()Ljava/io/File;
    //   15: invokevirtual 24	java/io/File:getParentFile	()Ljava/io/File;
    //   18: invokevirtual 369	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   21: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: ldc_w 638
    //   27: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   33: invokespecial 302	java/io/File:<init>	(Ljava/lang/String;)V
    //   36: astore_3
    //   37: new 11	java/io/File
    //   40: dup
    //   41: new 217	java/lang/StringBuilder
    //   44: dup
    //   45: invokespecial 219	java/lang/StringBuilder:<init>	()V
    //   48: invokestatic 641	android/os/Environment:getRootDirectory	()Ljava/io/File;
    //   51: invokevirtual 369	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   54: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: ldc_w 638
    //   60: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: invokevirtual 231	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   66: invokespecial 302	java/io/File:<init>	(Ljava/lang/String;)V
    //   69: astore 4
    //   71: aconst_null
    //   72: astore_1
    //   73: aload_0
    //   74: invokevirtual 254	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   77: ldc_w 643
    //   80: invokevirtual 262	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   83: astore_2
    //   84: aload_2
    //   85: astore_1
    //   86: aload_3
    //   87: invokevirtual 28	java/io/File:exists	()Z
    //   90: ifne +17 -> 107
    //   93: aload 4
    //   95: invokevirtual 28	java/io/File:exists	()Z
    //   98: ifne +9 -> 107
    //   101: aload_1
    //   102: ifnonnull +5 -> 107
    //   105: iconst_0
    //   106: ireturn
    //   107: aload_0
    //   108: invokevirtual 647	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   111: ldc_w 649
    //   114: ldc_w 651
    //   117: aload_0
    //   118: invokevirtual 487	android/content/Context:getPackageName	()Ljava/lang/String;
    //   121: invokevirtual 657	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   124: ifeq +36 -> 160
    //   127: aload_0
    //   128: invokevirtual 647	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   131: ldc_w 659
    //   134: ldc_w 651
    //   137: aload_0
    //   138: invokevirtual 487	android/content/Context:getPackageName	()Ljava/lang/String;
    //   141: invokevirtual 657	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   144: istore 5
    //   146: iload 5
    //   148: ifeq +12 -> 160
    //   151: iconst_1
    //   152: ireturn
    //   153: astore_0
    //   154: iconst_0
    //   155: ireturn
    //   156: astore_2
    //   157: goto -71 -> 86
    //   160: iconst_0
    //   161: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	71	153	java/lang/Exception
    //   86	101	153	java/lang/Exception
    //   107	146	153	java/lang/Exception
    //   73	84	156	java/lang/Exception
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ixintui.pushsdk.a.a
 * JD-Core Version:    0.6.2
 */