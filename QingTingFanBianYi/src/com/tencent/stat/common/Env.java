package com.tencent.stat.common;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.tencent.stat.StatConfig;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

public class Env
{
  private static JSONObject attr_json = null;
  static BasicEnv basicEnv;
  String conn = null;
  Integer tel_network = null;

  public Env(Context paramContext)
  {
    getBasicEnv(paramContext);
    this.tel_network = StatCommonHelper.getTelephonyNetworkType(paramContext.getApplicationContext());
    this.conn = StatCommonHelper.getLinkedWay(paramContext);
  }

  public static void appendEnvAttr(Context paramContext, Map<String, String> paramMap)
    throws JSONException
  {
    if (paramMap == null);
    while (true)
    {
      return;
      if (attr_json == null)
        attr_json = new JSONObject();
      paramContext = paramMap.entrySet().iterator();
      while (paramContext.hasNext())
      {
        paramMap = (Map.Entry)paramContext.next();
        attr_json.put((String)paramMap.getKey(), paramMap.getValue());
      }
    }
  }

  static BasicEnv getBasicEnv(Context paramContext)
  {
    if (basicEnv == null)
      basicEnv = new BasicEnv(paramContext.getApplicationContext(), null);
    return basicEnv;
  }

  public void encode(JSONObject paramJSONObject)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    if (basicEnv != null)
      basicEnv.encode(localJSONObject);
    StatCommonHelper.jsonPut(localJSONObject, "cn", this.conn);
    if (this.tel_network != null)
      localJSONObject.put("tn", this.tel_network);
    paramJSONObject.put("ev", localJSONObject);
    if ((attr_json != null) && (attr_json.length() > 0))
      paramJSONObject.put("eva", attr_json);
  }

  static class BasicEnv
  {
    String appVersion;
    String channel;
    DisplayMetrics display;
    int jb = 0;
    String language = Locale.getDefault().getLanguage();
    String manufacture = Build.MANUFACTURER;
    String model = Build.MODEL;
    String operator;
    int osVersion = Build.VERSION.SDK_INT;
    String sd;
    String sdkVersion = "1.0.0";
    String timezone;

    private BasicEnv(Context paramContext)
    {
      this.display = StatCommonHelper.getDisplayMetrics(paramContext);
      this.appVersion = StatCommonHelper.getCurAppVersion(paramContext);
      this.channel = StatConfig.getInstallChannel(paramContext);
      this.operator = StatCommonHelper.getSimOperator(paramContext);
      this.timezone = TimeZone.getDefault().getID();
      this.jb = StatCommonHelper.hasRootAccess(paramContext);
      this.sd = StatCommonHelper.getExternalStorageInfo(paramContext);
    }

    void encode(JSONObject paramJSONObject)
      throws JSONException
    {
      paramJSONObject.put("sr", this.display.widthPixels + "*" + this.display.heightPixels);
      StatCommonHelper.jsonPut(paramJSONObject, "av", this.appVersion);
      StatCommonHelper.jsonPut(paramJSONObject, "ch", this.channel);
      StatCommonHelper.jsonPut(paramJSONObject, "mf", this.manufacture);
      StatCommonHelper.jsonPut(paramJSONObject, "sv", this.sdkVersion);
      StatCommonHelper.jsonPut(paramJSONObject, "ov", Integer.toString(this.osVersion));
      paramJSONObject.put("os", 1);
      StatCommonHelper.jsonPut(paramJSONObject, "op", this.operator);
      StatCommonHelper.jsonPut(paramJSONObject, "lg", this.language);
      StatCommonHelper.jsonPut(paramJSONObject, "md", this.model);
      StatCommonHelper.jsonPut(paramJSONObject, "tz", this.timezone);
      if (this.jb != 0)
        paramJSONObject.put("jb", this.jb);
      StatCommonHelper.jsonPut(paramJSONObject, "sd", this.sd);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.stat.common.Env
 * JD-Core Version:    0.6.2
 */