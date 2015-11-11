package com.mediav.ads.sdk.adcore;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import com.mediav.ads.sdk.interfaces.IBridge;
import com.mediav.ads.sdk.log.MVLog;
import com.mediav.ads.sdk.log.Utils;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.security.MessageDigest;
import org.json.JSONObject;
import org.json.JSONTokener;

public class UpdateBridge
{
  private static final String SP_SDK_VER = "ad_sdk_ver";
  private static Context context = null;
  private static String md5 = null;

  @TargetApi(14)
  public static IBridge getBridge(Context paramContext)
  {
    context = paramContext.getApplicationContext();
    MVLog.init(context);
    try
    {
      MVLog.e("path: " + context.getFilesDir().getAbsolutePath() + "/mvad/update/");
      localFile = new File(context.getFilesDir().getAbsolutePath() + "/mvad/update/");
      paramContext = new File(context.getFilesDir().getAbsolutePath() + "/mvad/opt/");
      if (!localFile.exists())
        localFile.mkdirs();
      if (!paramContext.exists())
        paramContext.mkdirs();
      Object localObject1 = new File(localFile.getAbsolutePath() + "/" + "n1100.jar");
      localFile = new File(localFile.getAbsolutePath() + "/" + "dynamic1100.jar");
      if (!((File)localObject1).exists())
        if (!localFile.exists())
        {
          localFile.createNewFile();
          Object localObject2 = context.getAssets().open("dynamic1100.jar");
          localObject1 = new byte[((InputStream)localObject2).available()];
          ((InputStream)localObject2).read((byte[])localObject1);
          ((InputStream)localObject2).close();
          localObject2 = new FileOutputStream(localFile, false);
          ((FileOutputStream)localObject2).write((byte[])localObject1);
          ((FileOutputStream)localObject2).close();
        }
      while (true)
      {
        paramContext = (IBridge)new DexClassLoader(localFile.getAbsolutePath(), paramContext.getAbsolutePath(), null, context.getClassLoader()).loadClass("com.mediav.ads.sdk.core.Bridge").newInstance();
        getNewJar();
        return paramContext;
        if (localFile.exists())
          localFile.delete();
        ((File)localObject1).renameTo(localFile);
        MVLog.d("new jar replaced old jar");
      }
    }
    catch (ClassNotFoundException paramContext)
    {
      MVLog.e(705, "getBridge ClassNotFoundException", paramContext);
      File localFile = new File(context.getFilesDir().getAbsolutePath() + "/mvad/update/");
      paramContext = new File(localFile.getAbsolutePath() + "/" + "n1100.jar");
      localFile = new File(localFile.getAbsolutePath() + "/" + "dynamic1100.jar");
      if (paramContext.exists())
        paramContext.delete();
      if (localFile.exists())
        localFile.delete();
      return null;
    }
    catch (Exception paramContext)
    {
      while (true)
        MVLog.e(705, "getBridge Exception", paramContext);
    }
  }

  public static String getMd5ByFile(File paramFile)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(paramFile);
      localObject1 = localObject2;
      paramFile = localFileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, paramFile.length());
      localObject1 = localObject2;
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localObject1 = localObject2;
      localMessageDigest.update(paramFile);
      localObject1 = localObject2;
      paramFile = getString(localMessageDigest.digest());
      localObject1 = paramFile;
      localFileInputStream.close();
      return paramFile;
    }
    catch (Exception paramFile)
    {
      paramFile.printStackTrace();
    }
    return localObject1;
  }

  private static void getNewJar()
  {
    String str = getUpdateUrl();
    HttpRequester.getAsynData(context, str, Boolean.valueOf(false), new HttpRequester.Listener()
    {
      public void onGetDataFailed(String paramAnonymousString)
      {
        if (!"204".equals(paramAnonymousString))
          MVLog.e(701, "Update Jar Error:" + this.val$url + ",code:" + paramAnonymousString);
      }

      public void onGetDataSucceed(byte[] paramAnonymousArrayOfByte)
      {
        UpdateBridge.parserJson(paramAnonymousArrayOfByte);
      }
    });
  }

  private static String getString(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar1 = new char[16];
    char[] tmp6_5 = arrayOfChar1;
    tmp6_5[0] = 48;
    char[] tmp11_6 = tmp6_5;
    tmp11_6[1] = 49;
    char[] tmp16_11 = tmp11_6;
    tmp16_11[2] = 50;
    char[] tmp21_16 = tmp16_11;
    tmp21_16[3] = 51;
    char[] tmp26_21 = tmp21_16;
    tmp26_21[4] = 52;
    char[] tmp32_26 = tmp26_21;
    tmp32_26[5] = 53;
    char[] tmp38_32 = tmp32_26;
    tmp38_32[6] = 54;
    char[] tmp45_38 = tmp38_32;
    tmp45_38[7] = 55;
    char[] tmp52_45 = tmp45_38;
    tmp52_45[8] = 56;
    char[] tmp59_52 = tmp52_45;
    tmp59_52[9] = 57;
    char[] tmp66_59 = tmp59_52;
    tmp66_59[10] = 97;
    char[] tmp73_66 = tmp66_59;
    tmp73_66[11] = 98;
    char[] tmp80_73 = tmp73_66;
    tmp80_73[12] = 99;
    char[] tmp87_80 = tmp80_73;
    tmp87_80[13] = 100;
    char[] tmp94_87 = tmp87_80;
    tmp94_87[14] = 101;
    char[] tmp101_94 = tmp94_87;
    tmp101_94[15] = 102;
    tmp101_94;
    int k = paramArrayOfByte.length;
    char[] arrayOfChar2 = new char[k * 2];
    int i = 0;
    int j = 0;
    while (i < k)
    {
      int m = paramArrayOfByte[i];
      int n = j + 1;
      arrayOfChar2[j] = arrayOfChar1[(m >>> 4 & 0xF)];
      j = n + 1;
      arrayOfChar2[n] = arrayOfChar1[(m & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar2);
  }

  private static String getUpdateUrl()
  {
    Object localObject = "";
    try
    {
      String str = "&nsdkv=" + getVer(context) + "&imei=" + URLEncoder.encode(Utils.getIMEI(), "utf-8") + "&model=" + URLEncoder.encode(Utils.getProductModel(), "utf-8").replace("+", "%20") + "&channelid=" + URLEncoder.encode("1", "utf-8") + "&appv=" + URLEncoder.encode(Utils.getAppVersion(), "utf-8") + "&appvc=" + URLEncoder.encode(Utils.getAppVersionCode(), "utf-8") + "&apppkg=" + URLEncoder.encode(Utils.getAppPackageName(), "utf-8") + "&brand=" + URLEncoder.encode(Utils.getBrand(), "utf-8").replace("+", "%20");
      localObject = str;
      return "http://show.m.mediav.com/update?sdkv=1100" + (String)localObject;
    }
    catch (Exception localException)
    {
      while (true)
        MVLog.d("URLEncoder Encode Error:" + localException.getMessage());
    }
  }

  private static String getVer(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("ver_info", 0);
    String str = localSharedPreferences.getString("ad_sdk_ver", null);
    paramContext = str;
    if (str == null)
    {
      localSharedPreferences.edit().putString("ad_sdk_ver", "1100").commit();
      paramContext = localSharedPreferences.getString("ad_sdk_ver", null);
    }
    return paramContext;
  }

  private static void parserJson(byte[] paramArrayOfByte)
  {
    try
    {
      Object localObject = (JSONObject)new JSONTokener(new String(paramArrayOfByte)).nextValue();
      int i = ((JSONObject)localObject).getInt("ver");
      int j = Integer.parseInt(getVer(context));
      md5 = ((JSONObject)localObject).getString("md5");
      localObject = ((JSONObject)localObject).getString("sdk_url");
      if (i > j)
      {
        MVLog.d("new version sdk found");
        HttpRequester.getAsynData(context, (String)localObject, Boolean.valueOf(false), new HttpRequester.Listener()
        {
          public void onGetDataFailed(String paramAnonymousString)
          {
            MVLog.e(703, "Download new jar error:" + paramAnonymousString);
          }

          public void onGetDataSucceed(byte[] paramAnonymousArrayOfByte)
          {
            MVLog.d("download jar");
            File localFile = new File(UpdateBridge.context.getFilesDir().getAbsolutePath() + "/mvad/update/");
            localFile = new File(localFile.getAbsolutePath() + "/" + "n1100.jar");
            if (localFile.exists())
              localFile.delete();
            try
            {
              localFile.createNewFile();
              FileOutputStream localFileOutputStream = new FileOutputStream(localFile, false);
              localFileOutputStream.write(paramAnonymousArrayOfByte);
              localFileOutputStream.close();
              if (UpdateBridge.md5 != null)
              {
                if (!UpdateBridge.getMd5ByFile(localFile).equals(UpdateBridge.md5))
                {
                  MVLog.e(704, "MD5 check error: " + UpdateBridge.md5);
                  localFile.delete();
                  return;
                }
                MVLog.d("new jar saved");
                UpdateBridge.setVer(UpdateBridge.context, this.val$newVer + "");
                return;
              }
            }
            catch (Exception paramAnonymousArrayOfByte)
            {
              MVLog.e("写入新包错误:" + paramAnonymousArrayOfByte.getMessage());
            }
          }
        });
        return;
      }
      MVLog.d("sdk update to latest");
      return;
    }
    catch (Exception localException)
    {
      MVLog.e(702, "parse update error" + new String(paramArrayOfByte), localException);
    }
  }

  private static void setVer(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("ver_info", 0).edit();
    paramContext.putString("ad_sdk_ver", paramString);
    paramContext.commit();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.adcore.UpdateBridge
 * JD-Core Version:    0.6.2
 */