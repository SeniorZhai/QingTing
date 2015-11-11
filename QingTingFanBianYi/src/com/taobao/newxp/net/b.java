package com.taobao.newxp.net;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.taobao.munion.base.f;
import com.taobao.newxp.Promoter;
import com.taobao.newxp.UFPResType;
import com.taobao.newxp.common.AlimmContext;
import com.taobao.newxp.common.ExchangeConstants;
import com.taobao.newxp.common.b.e;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class b<T extends d>
{
  AlimmContext a = AlimmContext.getAliContext();

  public static Location a(Context paramContext, com.taobao.munion.base.a parama)
  {
    paramContext = com.taobao.newxp.location.a.a(paramContext);
    parama = parama.F();
    if (paramContext == null);
    long l;
    do
    {
      return parama;
      if (parama == null)
        return paramContext;
      l = paramContext.getTime();
    }
    while (parama.getTime() - 300000L > l);
    return paramContext;
  }

  public static String a(String paramString, Map<String, Object> paramMap)
  {
    StringBuilder localStringBuilder1 = new StringBuilder(paramString);
    Iterator localIterator = paramMap.keySet().iterator();
    if (localIterator.hasNext())
      for (paramString = (String)localIterator.next(); ; paramString = paramMap.get(paramString).toString())
      {
        try
        {
          StringBuilder localStringBuilder2 = localStringBuilder1.append(URLEncoder.encode(paramString, "utf-8")).append("=");
          if (paramMap.get(paramString) != null)
            continue;
          paramString = "";
          localStringBuilder2.append(URLEncoder.encode(paramString, "utf-8")).append("&");
        }
        catch (UnsupportedEncodingException paramString)
        {
          android.util.Log.e("Alimama", "", paramString);
        }
        break;
      }
    if (localStringBuilder1.toString().endsWith("&"))
      localStringBuilder1.deleteCharAt(localStringBuilder1.length() - 1);
    return localStringBuilder1.toString().replaceAll(" ", "");
  }

  private <T extends Promoter> void a(Collection paramCollection, Class<T> paramClass, JSONObject paramJSONObject, String paramString)
  {
    com.taobao.newxp.common.Log.a(ExchangeConstants.LOG_TAG, "get promoters use class " + paramClass.toString());
    try
    {
      if (paramJSONObject.has("promoters"))
      {
        paramJSONObject = paramJSONObject.getJSONArray("promoters");
        int i = 0;
        while (i < paramJSONObject.length())
        {
          Promoter localPromoter = Promoter.getPromoterFromJson((JSONObject)paramJSONObject.get(i), paramClass);
          localPromoter.slot_act_pams = paramString;
          paramCollection.add(localPromoter);
          i += 1;
        }
      }
      com.taobao.newxp.common.Log.b(ExchangeConstants.LOG_TAG, "failed requesting");
      return;
    }
    catch (Exception paramCollection)
    {
      com.taobao.newxp.common.Log.b(ExchangeConstants.LOG_TAG, "", paramCollection);
    }
  }

  private final String b(T paramT, boolean paramBoolean)
  {
    paramT = a(paramT, paramBoolean);
    return a(n.e[0], paramT);
  }

  public int a(T paramT, Collection<? extends Promoter> paramCollection, Class<? extends Promoter> paramClass, JSONObject paramJSONObject)
  {
    int i = 0;
    if (paramJSONObject == null)
      com.taobao.newxp.common.Log.b(ExchangeConstants.LOG_TAG, "failed requesting");
    int j;
    do
    {
      return i;
      j = paramJSONObject.optInt("status", 0);
      if (1 == j)
        paramT.warp(paramJSONObject);
      i = j;
    }
    while (paramCollection == null);
    Object localObject = paramClass;
    if (paramClass == null)
      localObject = Promoter.getAdapterPromoterClz(paramT.template, paramT.resType);
    a(paramCollection, (Class)localObject, paramJSONObject, paramT.slot_act_params);
    return j;
  }

  public final o a(T paramT, f paramf)
  {
    return a(paramT, false, paramf);
  }

  public final o a(T paramT, boolean paramBoolean, f paramf)
  {
    return new o(paramT, b(paramT, paramBoolean), paramf);
  }

  public Map<String, Object> a(T paramT, boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap();
    Object localObject2 = this.a.getAppUtils();
    localHashMap.put("sdk_version", ExchangeConstants.sdk_version);
    localHashMap.put("sdk_channel", ExchangeConstants.SDK_CHANNEL);
    localHashMap.put("protocol_version", ExchangeConstants.protocol_version);
    Object localObject1;
    if (TextUtils.isEmpty(ExchangeConstants.CHANNEL))
      localObject1 = ((com.taobao.munion.base.a)localObject2).f("MUNION_CHANNEL");
    label669: int i;
    while (true)
    {
      if (!TextUtils.isEmpty((CharSequence)localObject1))
        localHashMap.put("channel", localObject1);
      localHashMap.put("utdid", ((com.taobao.munion.base.a)localObject2).C());
      localHashMap.put("device_id", ((com.taobao.munion.base.a)localObject2).r());
      localHashMap.put("idmd5", com.taobao.newxp.common.b.b.a(((com.taobao.munion.base.a)localObject2).r()));
      localHashMap.put("device_model", Build.MODEL);
      localHashMap.put("os", "android");
      localObject1 = ((com.taobao.munion.base.a)localObject2).p();
      if (!TextUtils.isEmpty((CharSequence)localObject1))
        localHashMap.put("mc", localObject1);
      localHashMap.put("os_version", Build.VERSION.RELEASE);
      localHashMap.put("locale", ((com.taobao.munion.base.a)localObject2).n());
      localHashMap.put("language", ((com.taobao.munion.base.a)localObject2).m());
      localHashMap.put("timezone", ((com.taobao.munion.base.a)localObject2).o());
      localHashMap.put("resolution", ((com.taobao.munion.base.a)localObject2).t());
      localObject1 = ((com.taobao.munion.base.a)localObject2).D();
      localHashMap.put("access", localObject1[0]);
      localHashMap.put("access_subtype", localObject1[1]);
      localHashMap.put("carrier", ((com.taobao.munion.base.a)localObject2).G());
      localObject1 = a(this.a.getAppContext(), (com.taobao.munion.base.a)localObject2);
      if (localObject1 != null)
      {
        localHashMap.put("lat", String.valueOf(((Location)localObject1).getLatitude()));
        localHashMap.put("lng", String.valueOf(((Location)localObject1).getLongitude()));
        localHashMap.put("gps_type", ((Location)localObject1).getProvider());
        localHashMap.put("gpst", String.valueOf(((Location)localObject1).getTime()));
        localHashMap.put("gps_accuracy", String.valueOf(((Location)localObject1).getAccuracy()));
      }
      localHashMap.put("cpu", ((com.taobao.munion.base.a)localObject2).H());
      Object localObject3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
      localObject1 = localObject3.split(" ")[0];
      localObject3 = localObject3.split(" ")[1];
      localHashMap.put("date", localObject1);
      localHashMap.put("time", localObject3);
      localHashMap.put("brand", Build.MANUFACTURER);
      localHashMap.put("timezone", ((com.taobao.munion.base.a)localObject2).o());
      localHashMap.put("apnm", ((com.taobao.munion.base.a)localObject2).g());
      localHashMap.put("apvn", ((com.taobao.munion.base.a)localObject2).h());
      localHashMap.put("apvc", ((com.taobao.munion.base.a)localObject2).i());
      localHashMap.put("adnm", ((com.taobao.munion.base.a)localObject2).f());
      if (!TextUtils.isEmpty(paramT.slotId))
      {
        localHashMap.put("slot_id", paramT.slotId);
        if (!TextUtils.isEmpty(paramT.filterPromoter))
          localHashMap.put("promoter", paramT.filterPromoter);
        localHashMap.put("layout_type", Integer.valueOf(paramT.layoutType));
        if (!TextUtils.isEmpty(paramT.keywords))
          localHashMap.put("keywords", e.b(paramT.keywords));
        if (!TextUtils.isEmpty(paramT.slot_act_params))
          localObject2 = paramT.slot_act_params.split("&");
      }
      else
      {
        try
        {
          localObject1 = new HashMap();
          int j = localObject2.length;
          i = 0;
          while (true)
            if (i < j)
            {
              localObject3 = localObject2[i].split("=");
              if (localObject3.length == 2)
                ((Map)localObject1).put(localObject3[0], localObject3[1]);
              i += 1;
              continue;
              localObject1 = ExchangeConstants.CHANNEL;
              break;
              if (!TextUtils.isEmpty(paramT.appkey))
              {
                localHashMap.put("app_key", paramT.appkey);
                break label669;
              }
              com.taobao.newxp.common.Log.b(ExchangeConstants.LOG_TAG, "Both APPKEY and SLOTID are empty, please specify either one. Request aborted.");
              return null;
            }
          localObject2 = ((Map)localObject1).keySet().iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localObject3 = (String)((Iterator)localObject2).next();
            localHashMap.put(localObject3, ((Map)localObject1).get(localObject3));
          }
        }
        catch (Exception localException)
        {
        }
      }
    }
    if (!TextUtils.isEmpty(paramT.urlParams))
      localHashMap.put("url_params", paramT.urlParams);
    if (!TextUtils.isEmpty(paramT.tag))
      localHashMap.put("tags", paramT.tag);
    if (paramT.autofill != 1)
      localHashMap.put("autofill", Integer.valueOf(paramT.autofill));
    if (!TextUtils.isEmpty(paramT.sid))
      localHashMap.put("sid", paramT.sid);
    if (!TextUtils.isEmpty(paramT.psid))
      localHashMap.put("psid", paramT.psid);
    if (ExchangeConstants.DETAIL_PAGE)
    {
      i = 1;
      localHashMap.put("req_imgs", Integer.valueOf(i));
      localHashMap.put("req_desc", Integer.valueOf(1));
      if (paramT.resType != null)
        break label1230;
      str = "";
      label1106: localHashMap.put("resource_type", str);
      if (paramT.template != null)
      {
        localObject2 = new StringBuilder().append(paramT.template.toString());
        if (!TextUtils.isEmpty(paramT.templateAttrs))
          break label1241;
      }
    }
    label1230: label1241: for (String str = ""; ; str = "." + paramT.templateAttrs)
    {
      localHashMap.put("template", str);
      if (paramT.landing_type > 0)
        localHashMap.put("landing_type", Integer.valueOf(paramT.landing_type));
      if (paramBoolean)
        localHashMap.put("cache", Integer.valueOf(1));
      return localHashMap;
      i = 0;
      break;
      str = paramT.resType.toString();
      break label1106;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.net.b
 * JD-Core Version:    0.6.2
 */