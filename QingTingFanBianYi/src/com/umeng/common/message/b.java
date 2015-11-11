package com.umeng.common.message;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import org.json.JSONObject;

public class b
{
  public static final String A = "Android";
  public static final String B = "Android";
  private static final String C = b.class.getName();
  private final String D = "appkey";
  private final String E = "channel";
  private final String F = "device_id";
  private final String G = "idmd5";
  private final String H = "mc";
  private final String I = "req_time";
  private final String J = "device_model";
  private final String K = "os";
  private final String L = "os_version";
  private final String M = "resolution";
  private final String N = "cpu";
  private final String O = "gpu_vender";
  private final String P = "gpu_renderer";
  private final String Q = "app_version";
  private final String R = "version_code";
  private final String S = "package_name";
  private final String T = "sdk_type";
  private final String U = "sdk_version";
  private final String V = "timezone";
  private final String W = "country";
  private final String X = "language";
  private final String Y = "access";
  private final String Z = "access_subtype";
  public String a;
  private final String aa = "carrier";
  private final String ab = "wrapper_type";
  private final String ac = "wrapper_version";
  public String b;
  public String c;
  public String d;
  public String e;
  public long f;
  public String g;
  public String h;
  public String i;
  public String j;
  public String k;
  public String l;
  public String m;
  public String n;
  public String o;
  public String p;
  public String q;
  public String r;
  public int s;
  public String t;
  public String u;
  public String v;
  public String w;
  public String x;
  public String y;
  public String z;

  public b()
  {
  }

  public b(String paramString1, String paramString2)
  {
    this.a = paramString1;
    this.b = paramString2;
  }

  private void c(JSONObject paramJSONObject)
    throws Exception
  {
    this.a = paramJSONObject.getString("appkey");
    this.c = paramJSONObject.getString("device_id");
    this.d = paramJSONObject.getString("idmd5");
    if (paramJSONObject.has("mc"))
      this.e = paramJSONObject.getString("mc");
    if (paramJSONObject.has("channel"))
      this.b = paramJSONObject.getString("channel");
    if (paramJSONObject.has("req_time"))
      this.f = paramJSONObject.getLong("req_time");
  }

  private void d(JSONObject paramJSONObject)
    throws Exception
  {
    Object localObject2 = null;
    if (paramJSONObject.has("device_model"))
    {
      localObject1 = paramJSONObject.getString("device_model");
      this.g = ((String)localObject1);
      if (!paramJSONObject.has("os"))
        break label157;
      localObject1 = paramJSONObject.getString("os");
      label39: this.h = ((String)localObject1);
      if (!paramJSONObject.has("os_version"))
        break label162;
      localObject1 = paramJSONObject.getString("os_version");
      label60: this.i = ((String)localObject1);
      if (!paramJSONObject.has("resolution"))
        break label167;
      localObject1 = paramJSONObject.getString("resolution");
      label81: this.j = ((String)localObject1);
      if (!paramJSONObject.has("cpu"))
        break label172;
      localObject1 = paramJSONObject.getString("cpu");
      label102: this.k = ((String)localObject1);
      if (!paramJSONObject.has("gpu_vender"))
        break label177;
    }
    label157: label162: label167: label172: label177: for (Object localObject1 = paramJSONObject.getString("gpu_vender"); ; localObject1 = null)
    {
      this.l = ((String)localObject1);
      localObject1 = localObject2;
      if (paramJSONObject.has("gpu_renderer"))
        localObject1 = paramJSONObject.getString("gpu_renderer");
      this.m = ((String)localObject1);
      return;
      localObject1 = null;
      break;
      localObject1 = null;
      break label39;
      localObject1 = null;
      break label60;
      localObject1 = null;
      break label81;
      localObject1 = null;
      break label102;
    }
  }

  private void e(JSONObject paramJSONObject)
    throws Exception
  {
    Object localObject2 = null;
    if (paramJSONObject.has("app_version"))
    {
      localObject1 = paramJSONObject.getString("app_version");
      this.n = ((String)localObject1);
      if (!paramJSONObject.has("version_code"))
        break label73;
    }
    label73: for (Object localObject1 = paramJSONObject.getString("version_code"); ; localObject1 = null)
    {
      this.o = ((String)localObject1);
      localObject1 = localObject2;
      if (paramJSONObject.has("package_name"))
        localObject1 = paramJSONObject.getString("package_name");
      this.p = ((String)localObject1);
      return;
      localObject1 = null;
      break;
    }
  }

  private void f(JSONObject paramJSONObject)
    throws Exception
  {
    this.q = paramJSONObject.getString("sdk_type");
    this.r = paramJSONObject.getString("sdk_version");
  }

  private void g(JSONObject paramJSONObject)
    throws Exception
  {
    Object localObject2 = null;
    int i1;
    if (paramJSONObject.has("timezone"))
    {
      i1 = paramJSONObject.getInt("timezone");
      this.s = i1;
      if (!paramJSONObject.has("country"))
        break label77;
    }
    label77: for (Object localObject1 = paramJSONObject.getString("country"); ; localObject1 = null)
    {
      this.t = ((String)localObject1);
      localObject1 = localObject2;
      if (paramJSONObject.has("language"))
        localObject1 = paramJSONObject.getString("language");
      this.u = ((String)localObject1);
      return;
      i1 = 8;
      break;
    }
  }

  private void h(JSONObject paramJSONObject)
    throws Exception
  {
    Object localObject2 = null;
    if (paramJSONObject.has("access"))
    {
      localObject1 = paramJSONObject.getString("access");
      this.v = ((String)localObject1);
      if (!paramJSONObject.has("access_subtype"))
        break label73;
    }
    label73: for (Object localObject1 = paramJSONObject.getString("access_subtype"); ; localObject1 = null)
    {
      this.w = ((String)localObject1);
      localObject1 = localObject2;
      if (paramJSONObject.has("carrier"))
        localObject1 = paramJSONObject.getString("carrier");
      this.x = ((String)localObject1);
      return;
      localObject1 = null;
      break;
    }
  }

  private void i(JSONObject paramJSONObject)
    throws Exception
  {
    Object localObject2 = null;
    if (paramJSONObject.has("wrapper_type"));
    for (Object localObject1 = paramJSONObject.getString("wrapper_type"); ; localObject1 = null)
    {
      this.y = ((String)localObject1);
      localObject1 = localObject2;
      if (paramJSONObject.has("wrapper_version"))
        localObject1 = paramJSONObject.getString("wrapper_version");
      this.z = ((String)localObject1);
      return;
    }
  }

  private void j(JSONObject paramJSONObject)
    throws Exception
  {
    paramJSONObject.put("appkey", this.a);
    paramJSONObject.put("device_id", this.c);
    paramJSONObject.put("idmd5", this.d);
    if (this.b != null)
      paramJSONObject.put("channel", this.b);
    if (this.e != null)
      paramJSONObject.put("mc", this.e);
    if (this.f > 0L)
      paramJSONObject.put("req_time", this.f);
  }

  private void k(JSONObject paramJSONObject)
    throws Exception
  {
    if (this.g != null)
      paramJSONObject.put("device_model", this.g);
    if (this.h != null)
      paramJSONObject.put("os", this.h);
    if (this.i != null)
      paramJSONObject.put("os_version", this.i);
    if (this.j != null)
      paramJSONObject.put("resolution", this.j);
    if (this.k != null)
      paramJSONObject.put("cpu", this.k);
    if (this.l != null)
      paramJSONObject.put("gpu_vender", this.l);
    if (this.m != null)
      paramJSONObject.put("gpu_vender", this.m);
  }

  private void l(JSONObject paramJSONObject)
    throws Exception
  {
    if (this.n != null)
      paramJSONObject.put("app_version", this.n);
    if (this.o != null)
      paramJSONObject.put("version_code", this.o);
    if (this.p != null)
      paramJSONObject.put("package_name", this.p);
  }

  private void m(JSONObject paramJSONObject)
    throws Exception
  {
    paramJSONObject.put("sdk_type", this.q);
    paramJSONObject.put("sdk_version", this.r);
  }

  private void n(JSONObject paramJSONObject)
    throws Exception
  {
    paramJSONObject.put("timezone", this.s);
    if (this.t != null)
      paramJSONObject.put("country", this.t);
    if (this.u != null)
      paramJSONObject.put("language", this.u);
  }

  private void o(JSONObject paramJSONObject)
    throws Exception
  {
    if (this.v != null)
      paramJSONObject.put("access", this.v);
    if (this.w != null)
      paramJSONObject.put("access_subtype", this.w);
    if (this.x != null)
      paramJSONObject.put("carrier", this.x);
  }

  private void p(JSONObject paramJSONObject)
    throws Exception
  {
    if (this.y != null)
      paramJSONObject.put("wrapper_type", this.y);
    if (this.z != null)
      paramJSONObject.put("wrapper_version", this.z);
  }

  public void a(Context paramContext)
  {
    this.g = Build.MODEL;
    this.h = "Android";
    this.i = Build.VERSION.RELEASE;
    this.j = DeviceConfig.getResolution(paramContext);
    this.k = DeviceConfig.getCPU();
  }

  public void a(Context paramContext, String[] paramArrayOfString)
  {
    if ((paramArrayOfString != null) && (paramArrayOfString.length == 2))
    {
      this.a = paramArrayOfString[0];
      this.b = paramArrayOfString[1];
    }
    if (this.a == null)
      this.a = DeviceConfig.getAppkey(paramContext);
    if (this.b == null)
      this.b = DeviceConfig.getChannel(paramContext);
    this.c = DeviceConfig.getDeviceId(paramContext);
    this.d = DeviceConfig.getDeviceIdMD5(paramContext);
    this.e = DeviceConfig.getMac(paramContext);
  }

  public void a(JSONObject paramJSONObject)
    throws Exception
  {
    if (paramJSONObject == null)
      return;
    c(paramJSONObject);
    d(paramJSONObject);
    e(paramJSONObject);
    f(paramJSONObject);
    g(paramJSONObject);
    h(paramJSONObject);
    i(paramJSONObject);
  }

  public boolean a()
  {
    if (this.a == null)
    {
      Log.b(C, "missing appkey ");
      return false;
    }
    if ((this.c == null) || (this.d == null))
    {
      Log.b(C, "missing device id");
      return false;
    }
    return true;
  }

  public void b(Context paramContext)
  {
    this.n = DeviceConfig.getAppVersionName(paramContext);
    this.o = DeviceConfig.getAppVersionCode(paramContext);
    this.p = DeviceConfig.getPackageName(paramContext);
  }

  public void b(Context paramContext, String[] paramArrayOfString)
  {
    a(paramContext, paramArrayOfString);
    a(paramContext);
    b(paramContext);
    c(paramContext);
    d(paramContext);
    e(paramContext);
  }

  public void b(JSONObject paramJSONObject)
    throws Exception
  {
    j(paramJSONObject);
    k(paramJSONObject);
    l(paramJSONObject);
    m(paramJSONObject);
    n(paramJSONObject);
    o(paramJSONObject);
    p(paramJSONObject);
  }

  public boolean b()
  {
    return (this.a != null) && (this.c != null);
  }

  public void c(Context paramContext)
  {
    this.q = "Android";
    this.r = "1.3.0";
  }

  public void d(Context paramContext)
  {
    this.s = DeviceConfig.getTimeZone(paramContext);
    paramContext = DeviceConfig.getLocaleInfo(paramContext);
    this.t = paramContext[0];
    this.u = paramContext[1];
  }

  public void e(Context paramContext)
  {
    String[] arrayOfString = DeviceConfig.getNetworkAccessMode(paramContext);
    this.v = arrayOfString[0];
    this.w = arrayOfString[1];
    this.x = DeviceConfig.getOperator(paramContext);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.common.message.b
 * JD-Core Version:    0.6.2
 */