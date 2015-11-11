package org.android.agoo.net.mtop;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.message.proguard.aN;
import com.umeng.message.proguard.aq;
import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.android.agoo.helper.PhoneHelper;
import org.json.JSONObject;

public class MtopRequestHelper
{
  public static final String SPLIT_STR = "&";
  private static final String a = "MtopRequestHelper";

  private static long a()
  {
    return System.currentTimeMillis() / 1000L;
  }

  private static String a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, long paramLong, String paramString7, String paramString8)
    throws Throwable
  {
    try
    {
      paramString1 = aN.a(new ByteArrayInputStream(paramString1.getBytes("UTF-8")));
      StringBuffer localStringBuffer = new StringBuffer();
      if ((paramString8 != null) && (!"".equals(paramString8)))
      {
        localStringBuffer.append(paramString8);
        localStringBuffer.append("&");
      }
      localStringBuffer.append(paramString2);
      localStringBuffer.append("&");
      localStringBuffer.append(paramString1);
      localStringBuffer.append("&");
      localStringBuffer.append(paramString3);
      localStringBuffer.append("&");
      localStringBuffer.append(paramString4);
      localStringBuffer.append("&");
      localStringBuffer.append(paramString5);
      localStringBuffer.append("&");
      localStringBuffer.append(paramString6);
      localStringBuffer.append("&");
      localStringBuffer.append(aN.a(new ByteArrayInputStream(paramString7.getBytes("UTF-8"))));
      localStringBuffer.append("&");
      localStringBuffer.append(paramLong);
      paramString1 = aN.a(new ByteArrayInputStream(localStringBuffer.toString().getBytes("UTF-8")));
      return paramString1;
    }
    catch (Throwable paramString1)
    {
    }
    throw paramString1;
  }

  private static String a(Map<String, Object> paramMap)
  {
    return new JSONObject(paramMap).toString();
  }

  public static void checkAppKeyAndAppSecret(MtopRequest paramMtopRequest, String paramString1, String paramString2)
  {
    if ((!TextUtils.isEmpty(paramString1)) && (TextUtils.isEmpty(paramMtopRequest.getAppKey())))
      paramMtopRequest.setAppKey(paramString1);
    if ((!TextUtils.isEmpty(paramString2)) && (TextUtils.isEmpty(paramMtopRequest.getAppSecret())))
      paramMtopRequest.setAppSecret(paramString2);
  }

  public static aq getUrlWithRequestParams(Context paramContext, MtopRequest paramMtopRequest)
    throws Throwable
  {
    aq localaq = new aq();
    localaq.a("api", paramMtopRequest.getApi());
    localaq.a("v", paramMtopRequest.getV());
    long l2 = paramMtopRequest.getTime();
    long l1 = l2;
    if (l2 <= 0L)
      l1 = a();
    localaq.a("t", "" + l1);
    String str1 = PhoneHelper.getImei(paramContext);
    localaq.a("imei", str1);
    paramContext = PhoneHelper.getImsi(paramContext);
    localaq.a("imsi", paramContext);
    localaq.a("ttid", paramMtopRequest.getTtId());
    localaq.a("appKey", paramMtopRequest.getAppKey());
    if (!TextUtils.isEmpty(paramMtopRequest.getDeviceId()))
      localaq.a("deviceId", paramMtopRequest.getDeviceId());
    Object localObject1 = paramMtopRequest.getSysParams();
    if (localObject1 != null)
    {
      localObject1 = ((Map)localObject1).entrySet().iterator();
      if (localObject1 != null)
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Map.Entry)((Iterator)localObject1).next();
          if ((localObject2 != null) && (!TextUtils.isEmpty((CharSequence)((Map.Entry)localObject2).getKey())) && (!TextUtils.isEmpty((CharSequence)((Map.Entry)localObject2).getValue())))
            localaq.a((String)((Map.Entry)localObject2).getKey(), (String)((Map.Entry)localObject2).getValue());
        }
    }
    localObject1 = a(paramMtopRequest.getParams());
    Object localObject2 = paramMtopRequest.getAppKey();
    String str2 = paramMtopRequest.getAppSecret();
    if (TextUtils.isEmpty((CharSequence)localObject2))
      throw new NullPointerException("appKey is null");
    if (paramMtopRequest.isHasSigin())
      localaq.a("sign", a((String)localObject2, str2, paramMtopRequest.getApi(), paramMtopRequest.getV(), str1, paramContext, l1, (String)localObject1, paramMtopRequest.getEcode()));
    localaq.a("data", (String)localObject1);
    if ((paramMtopRequest.getSId() != null) || (!"".equals(paramMtopRequest.getSId())))
      localaq.a("sid", paramMtopRequest.getSId());
    return localaq;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.mtop.MtopRequestHelper
 * JD-Core Version:    0.6.2
 */