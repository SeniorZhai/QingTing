package org.android.agoo.client;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.message.proguard.P;
import com.umeng.message.proguard.Q;
import com.umeng.message.proguard.U;
import org.android.agoo.helper.PhoneHelper;
import org.android.agoo.net.mtop.IMtopSynClient;
import org.android.agoo.net.mtop.MtopRequest;
import org.android.agoo.net.mtop.MtopSyncClientV3;
import org.android.agoo.net.mtop.Result;
import org.json.JSONObject;

public class DeviceService
{
  private static final String a = "DeviceService";

  private static String a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    try
    {
      Log.d("DeviceService", "getRemoteDeviceID==>" + paramString1);
      Q.c("DeviceService", "getRemoteDeviceID--->appkey==" + paramString1 + "|appSecret==" + paramString2 + "|ttId==" + paramString3);
      MtopRequest localMtopRequest = new MtopRequest();
      localMtopRequest.setApi("mtop.sys.newDeviceId");
      localMtopRequest.setV("4.0");
      localMtopRequest.setTtId(paramString3);
      paramString3 = P.r(paramContext);
      if (!TextUtils.isEmpty(paramString3))
        localMtopRequest.putParams("old_device_id", paramString3);
      while (true)
      {
        localMtopRequest.putParams("device_global_id", U.c(paramContext));
        localMtopRequest.putParams("c0", Build.BRAND);
        localMtopRequest.putParams("c1", Build.MODEL);
        localMtopRequest.putParams("c2", PhoneHelper.getOriginalImei(paramContext));
        localMtopRequest.putParams("c3", PhoneHelper.getOriginalImsi(paramContext));
        localMtopRequest.putParams("c4", PhoneHelper.getLocalMacAddress(paramContext));
        localMtopRequest.putParams("c5", PhoneHelper.getSerialNum());
        localMtopRequest.putParams("c6", PhoneHelper.getAndroidId(paramContext));
        paramString3 = new MtopSyncClientV3();
        paramString3.setDefaultAppkey(paramString1);
        paramString3.setDefaultAppSecret(paramString2);
        paramString3.setBaseUrl(AgooSettings.getPullUrl(paramContext));
        paramContext = paramString3.getV3(paramContext, localMtopRequest);
        Q.c("DeviceService", "data:[" + paramContext.toString() + "]");
        if (!paramContext.isSuccess())
          break;
        return new JSONObject(paramContext.getData()).getString("device_id");
        localMtopRequest.putParams("new_device", "true");
      }
    }
    catch (Throwable paramContext)
    {
    }
    return null;
  }

  public static String getRegistrationId(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    try
    {
      paramContext = a(paramContext, paramString1, paramString2, paramString3);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.DeviceService
 * JD-Core Version:    0.6.2
 */