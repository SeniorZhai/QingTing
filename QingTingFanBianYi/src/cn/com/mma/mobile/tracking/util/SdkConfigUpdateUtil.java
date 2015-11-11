package cn.com.mma.mobile.tracking.util;

import android.content.Context;
import android.content.res.AssetManager;
import cn.com.mma.mobile.tracking.bean.OfflineCache;
import cn.com.mma.mobile.tracking.bean.SDK;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class SdkConfigUpdateUtil
{
  private static SDK sdk = null;

  private static boolean JudgeUpdateAccordingDate(Context paramContext)
  {
    long l1 = 3L * 86400000L;
    long l2 = System.currentTimeMillis();
    long l3 = SharedPreferencedUtil.getLong(paramContext, "cn.com.mma.mobile.tracking.other", "updateTime");
    Logger.d("mma_config lastUpdateTimeStamp:" + l3);
    StringBuilder localStringBuilder = new StringBuilder("mma_config wifi:").append(CommonUtil.isWifiConnected(paramContext)).append(" | ");
    boolean bool;
    label129: int i;
    label162: int j;
    if (l2 - l3 >= 86400000L)
    {
      bool = true;
      Logger.d(bool);
      localStringBuilder = new StringBuilder("mma_config mobile:").append(CommonUtil.isMobileConnected(paramContext)).append(" | ");
      if (l2 - l3 < l1)
        break label232;
      bool = true;
      Logger.d(bool);
      if ((!CommonUtil.isWifiConnected(paramContext)) || (l2 - l3 < 86400000L))
        break label238;
      i = 1;
      if ((!CommonUtil.isMobileConnected(paramContext)) || (l2 - l3 < l1))
        break label243;
      j = 1;
      label182: if ((i == 0) && (j == 0))
        break label248;
      bool = true;
      SharedPreferencedUtil.putLong(paramContext, "cn.com.mma.mobile.tracking.other", "updateTime", l2);
    }
    while (true)
    {
      Logger.d("mma_config File need Update：" + bool);
      return bool;
      bool = false;
      break;
      label232: bool = false;
      break label129;
      label238: i = 0;
      break label162;
      label243: j = 0;
      break label182;
      label248: bool = false;
    }
  }

  public static SDK dealUpdateConfig(Context paramContext, String paramString)
  {
    Object localObject = null;
    SDK localSDK = null;
    String str = getConfigFromNetWork(paramString);
    paramString = localSDK;
    if (str != null);
    try
    {
      localSDK = XmlUtil.doParser(new ByteArrayInputStream(str.getBytes("UTF-8")));
      paramString = localSDK;
      if (localSDK != null)
      {
        paramString = localSDK;
        localObject = localSDK;
        if (localSDK.companies != null)
        {
          paramString = localSDK;
          localObject = localSDK;
          if (localSDK.companies.size() > 0)
          {
            localObject = localSDK;
            SharedPreferencedUtil.putString(paramContext, "cn.com.mma.mobile.tracking.sdkconfig", "trackingConfig", str);
            localObject = localSDK;
            Logger.d("mma_网络更新sdkconfig.xml成功");
            paramString = localSDK;
          }
        }
      }
      return paramString;
    }
    catch (UnsupportedEncodingException paramContext)
    {
      paramContext.printStackTrace();
    }
    return localObject;
  }

  private static String getConfigFromNetWork(String paramString)
  {
    if (paramString == null)
      return null;
    try
    {
      paramString = (HttpURLConnection)new URL(paramString).openConnection();
      paramString.setConnectTimeout(10000);
      paramString.connect();
      paramString = paramString.getInputStream();
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramString));
      StringBuffer localStringBuffer = new StringBuffer();
      while (true)
      {
        String str = localBufferedReader.readLine();
        if (str == null)
        {
          paramString.close();
          return localStringBuffer.toString();
        }
        localStringBuffer.append(str);
      }
    }
    catch (MalformedURLException paramString)
    {
      paramString.printStackTrace();
      return null;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  private static SDK getNewestSDK(Context paramContext, String paramString)
  {
    Object localObject;
    if (JudgeUpdateAccordingDate(paramContext))
    {
      paramString = dealUpdateConfig(paramContext, paramString);
      localObject = paramString;
      if (paramString == null)
        localObject = getSDKFromPreferences(paramContext);
    }
    SDK localSDK;
    do
    {
      return localObject;
      localSDK = getSDKFromPreferences(paramContext);
      localObject = localSDK;
    }
    while (localSDK != null);
    return dealUpdateConfig(paramContext, paramString);
  }

  public static SDK getSDKFromPreferences(Context paramContext)
  {
    Object localObject = null;
    try
    {
      String str = SharedPreferencedUtil.getString(paramContext, "cn.com.mma.mobile.tracking.sdkconfig", "trackingConfig");
      paramContext = localObject;
      if (str != null)
        paramContext = XmlUtil.doParser(new ByteArrayInputStream(str.getBytes()));
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }

  public static SDK getSdk(Context paramContext)
  {
    if ((sdk == null) || (sdk.companies == null));
    try
    {
      sdk = XmlUtil.doParser(paramContext.getAssets().open("sdkconfig.xml"));
      setSdk(sdk);
      return sdk;
    }
    catch (IOException paramContext)
    {
      while (true)
        paramContext.printStackTrace();
    }
  }

  public static void initSdkConfigResult(Context paramContext, String paramString)
  {
    sdk = getNewestSDK(paramContext, paramString);
  }

  private static void setSdk(SDK paramSDK)
  {
    Logger.d("mma_setSdk");
    if (paramSDK != null);
    try
    {
      if ((paramSDK.offlineCache.length != null) && (!"".equals(paramSDK.offlineCache.length)))
        cn.com.mma.mobile.tracking.api.Global.OFFLINECACHE_LENGTH = Integer.parseInt(paramSDK.offlineCache.length);
      if ((paramSDK.offlineCache.queueExpirationSecs != null) && (!"".equals(paramSDK.offlineCache.queueExpirationSecs)))
        cn.com.mma.mobile.tracking.api.Global.OFFLINECACHE_QUEUEEXPIRATIONSECS = Integer.parseInt(paramSDK.offlineCache.queueExpirationSecs);
      if ((paramSDK.offlineCache.timeout != null) && (!"".equals(paramSDK.offlineCache.timeout)))
        cn.com.mma.mobile.tracking.api.Global.OFFLINECACHE_TIMEOUT = Integer.parseInt(paramSDK.offlineCache.timeout);
      return;
    }
    catch (Exception paramSDK)
    {
      paramSDK.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.mma.mobile.tracking.util.SdkConfigUpdateUtil
 * JD-Core Version:    0.6.2
 */