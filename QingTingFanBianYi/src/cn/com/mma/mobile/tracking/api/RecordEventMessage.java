package cn.com.mma.mobile.tracking.api;

import android.content.Context;
import android.content.SharedPreferences;
import cn.com.mma.mobile.tracking.bean.Argument;
import cn.com.mma.mobile.tracking.bean.Company;
import cn.com.mma.mobile.tracking.bean.Config;
import cn.com.mma.mobile.tracking.bean.Domain;
import cn.com.mma.mobile.tracking.bean.SDK;
import cn.com.mma.mobile.tracking.bean.SendEvent;
import cn.com.mma.mobile.tracking.bean.Signature;
import cn.com.mma.mobile.tracking.bean.Switch;
import cn.com.mma.mobile.tracking.util.CommonUtil;
import cn.com.mma.mobile.tracking.util.DeviceInfoUtil;
import cn.com.mma.mobile.tracking.util.LocationUtil;
import cn.com.mma.mobile.tracking.util.Logger;
import cn.com.mma.mobile.tracking.util.SdkConfigUpdateUtil;
import cn.com.mma.mobile.tracking.util.SharedPreferencedUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecordEventMessage
{
  private Context context;
  private Map<String, String> params;

  public RecordEventMessage(Context paramContext)
  {
    this.context = paramContext;
    this.params = DeviceInfoUtil.fulfillTrackingInfo(paramContext);
  }

  private long getExpirationTime(Company paramCompany, Long paramLong)
  {
    if (paramCompany.sswitch.offlineCacheExpiration != null)
      return Long.parseLong(paramCompany.sswitch.offlineCacheExpiration.trim()) * 1000L + paramLong.longValue();
    return 86400000L + paramLong.longValue();
  }

  private void recordEvent(SendEvent paramSendEvent)
  {
    this.params.put("LBS", LocationUtil.getInstance(this.context).getLocation());
    Object localObject1 = new StringBuilder();
    String str2 = CommonUtil.getHostURL(paramSendEvent.getUrl());
    Object localObject2 = SdkConfigUpdateUtil.getSdk(this.context);
    Iterator localIterator1;
    if ((localObject2 != null) && (((SDK)localObject2).companies != null))
      localIterator1 = ((SDK)localObject2).companies.iterator();
    while (true)
    {
      if (!localIterator1.hasNext())
        return;
      Company localCompany = (Company)localIterator1.next();
      label139: label561: if (str2.endsWith(localCompany.domain.url))
      {
        Object localObject3 = new ArrayList();
        String str1 = "";
        localObject2 = "";
        Iterator localIterator2 = localCompany.config.arguments.iterator();
        if (!localIterator2.hasNext())
        {
          ((StringBuilder)localObject1).append((String)CommonUtil.removeExistArgmentAndGetRedirectURL(paramSendEvent.getUrl(), (List)localObject3, str1, (String)localObject2, "").get("URL"));
          localIterator2 = localCompany.config.arguments.iterator();
          label193: if (localIterator2.hasNext())
            break label440;
          localObject3 = new ArrayList();
          localObject2 = new StringBuilder(CommonUtil.removeExistEvent(((StringBuilder)localObject1).toString(), (List)localObject3, str1, (String)localObject2));
          ((StringBuilder)localObject2).append("");
          if ((localCompany.signature != null) && (localCompany.signature.paramKey != null))
          {
            str1 = CommonUtil.getSignature(this.context, ((StringBuilder)localObject2).toString());
            localObject3 = new StringBuilder(String.valueOf(localCompany.separator)).append(localCompany.signature.paramKey);
            if (localCompany.equalizer == null)
              break label754;
          }
        }
        label440: label754: for (localObject1 = localCompany.equalizer; ; localObject1 = "")
        {
          ((StringBuilder)localObject2).append((String)localObject1 + CommonUtil.encodingUTF8(str1));
          Logger.d("mma_request_url" + ((StringBuilder)localObject2).toString());
          SharedPreferencedUtil.putLong(this.context, "cn.com.mma.mobile.tracking.normal", ((StringBuilder)localObject2).toString().trim(), getExpirationTime(localCompany, Long.valueOf(paramSendEvent.getTimestamp())));
          localObject1 = localObject2;
          break;
          Object localObject4 = (Argument)localIterator2.next();
          if (!((Argument)localObject4).isRequired)
            break label139;
          str1 = localCompany.separator;
          localObject2 = localCompany.equalizer;
          ((List)localObject3).add(((Argument)localObject4).value);
          break label139;
          localObject4 = (Argument)localIterator2.next();
          if (!((Argument)localObject4).isRequired)
            break label193;
          if ("TS".equals(((Argument)localObject4).key))
          {
            localObject4 = new StringBuilder(String.valueOf(localCompany.separator)).append(((Argument)localObject4).value);
            if (localCompany.equalizer != null)
            {
              localObject3 = localCompany.equalizer;
              localObject3 = ((StringBuilder)localObject4).append((String)localObject3);
              if (!localCompany.timeStampUseSecond)
                break label561;
            }
            for (long l = paramSendEvent.getTimestamp(); ; l = paramSendEvent.getTimestamp())
            {
              ((StringBuilder)localObject1).append(l);
              break;
              localObject3 = "";
              break label513;
            }
          }
          if ("MUDS".equals(((Argument)localObject4).key))
          {
            localStringBuilder = new StringBuilder(String.valueOf(localCompany.separator)).append(((Argument)localObject4).value);
            if (localCompany.equalizer != null);
            for (localObject3 = localCompany.equalizer; ; localObject3 = "")
            {
              ((StringBuilder)localObject1).append((String)localObject3 + CommonUtil.encodingUTF8(paramSendEvent.muds, (Argument)localObject4, localCompany));
              break;
            }
          }
          StringBuilder localStringBuilder = new StringBuilder(String.valueOf(localCompany.separator)).append(((Argument)localObject4).value);
          if (localCompany.equalizer != null);
          for (localObject3 = localCompany.equalizer; ; localObject3 = "")
          {
            ((StringBuilder)localObject1).append((String)localObject3 + CommonUtil.encodingUTF8((String)this.params.get(((Argument)localObject4).key), (Argument)localObject4, localCompany));
            break;
          }
        }
      }
      label513: Logger.d("domain不匹配" + str2 + " company.domain.url:" + localCompany.domain.url);
    }
  }

  public void recordEventWithUrl(String paramString)
  {
    if (DeviceInfoUtil.isEmulator(this.context))
      Logger.d("模拟器不记录，不发送数据");
    do
    {
      return;
      SendEvent localSendEvent = new SendEvent();
      localSendEvent.setTimestamp(System.currentTimeMillis());
      localSendEvent.setUrl(paramString);
      recordEvent(localSendEvent);
    }
    while (SharedPreferencedUtil.getSharedPreferences(this.context, "cn.com.mma.mobile.tracking.normal").getAll().keySet().size() < Global.OFFLINECACHE_LENGTH);
    SendEventMessage.getSendEventMessage(this.context).sendNromalList();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.mma.mobile.tracking.api.RecordEventMessage
 * JD-Core Version:    0.6.2
 */