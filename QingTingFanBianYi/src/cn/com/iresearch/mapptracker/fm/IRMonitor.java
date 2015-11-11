package cn.com.iresearch.mapptracker.fm;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import cn.com.iresearch.mapptracker.fm.dao.EventInfo;
import cn.com.iresearch.mapptracker.fm.dao.SessionInfo;
import cn.com.iresearch.mapptracker.fm.dao.b;
import cn.com.iresearch.mapptracker.fm.util.DataProvider;
import cn.com.iresearch.mapptracker.fm.util.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IRMonitor
{
  public static String a = "test_android";
  public static String b = "";
  public static boolean c = false;
  public static IRCallBack d = null;
  static int e = 0;
  private static IRMonitor h;
  private cn.com.iresearch.mapptracker.fm.a.a f;
  private Context g;
  private SharedPreferences i = null;
  private SharedPreferences.Editor j;
  private b k = null;
  private boolean l = true;
  private boolean m = false;
  private long n = 0L;
  private long o = 0L;
  private int p = 0;
  private int q = 0;
  private List<String> r = new ArrayList();
  private Map<String, EventInfo> s = new HashMap();
  private String t = null;
  private boolean u = false;

  private void a(Context paramContext)
  {
    try
    {
      if (this.k == null)
        this.k = new b();
      new a(this, paramContext).start();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  private void a(boolean paramBoolean)
  {
    try
    {
      if ((this.i == null) || (this.j == null))
      {
        this.i = h.g.getSharedPreferences("MATSharedPreferences", 0);
        this.j = this.i.edit();
      }
      if ((this.t == null) || ("".equals(this.t)))
        this.t = h.g.getPackageName();
      if (this.f == null)
        this.f = cn.com.iresearch.mapptracker.fm.a.a.a(h.g, "_ire");
      if ((this.r.isEmpty()) || (this.r == null))
        this.r = cn.com.iresearch.mapptracker.fm.util.f.b(h.g, this.t);
      if (this.k == null)
        a(h.g);
      if (!paramBoolean)
      {
        this.p = this.i.getInt("sPage_Count", 0);
        this.q = this.i.getInt("event_Count", 0);
      }
      return;
    }
    catch (Exception localException)
    {
      if (c)
        Log.e("MAT_SESSION", "内部初始化失败");
      localException.printStackTrace();
    }
  }

  private static JSONObject b(EventInfo paramEventInfo)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      String str2 = paramEventInfo.getLabel();
      String str1 = str2;
      if (str2 == null)
        str1 = "";
      localJSONObject.put("label", str1);
      localJSONObject.put("event_id", paramEventInfo.getEvent_id().replace(str1, ""));
      localJSONObject.put("duration", paramEventInfo.getDuration());
      localJSONObject.put("open_count", paramEventInfo.getOpen_count());
      localJSONObject.put("start_time", paramEventInfo.getStart_time());
      localJSONObject.put("end_time", paramEventInfo.getEnd_time());
      localJSONObject.put("event_params", paramEventInfo.getEvent_params());
      return localJSONObject;
    }
    catch (JSONException paramEventInfo)
    {
      paramEventInfo.printStackTrace();
    }
    return localJSONObject;
  }

  private static JSONObject b(SessionInfo paramSessionInfo)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("start_time", paramSessionInfo.getStart_time());
      localJSONObject.put("end_time", paramSessionInfo.getEnd_time());
      localJSONObject.put("duration", paramSessionInfo.getDuration());
      localJSONObject.put("page_name", paramSessionInfo.getPage_name());
      localJSONObject.put("inapp", paramSessionInfo.getInapp());
      localJSONObject.put("sessionid", paramSessionInfo.getSessionid());
      return localJSONObject;
    }
    catch (JSONException paramSessionInfo)
    {
      paramSessionInfo.printStackTrace();
    }
    return localJSONObject;
  }

  private void b(Context paramContext)
  {
    try
    {
      Object localObject = new JSONObject();
      JSONArray localJSONArray = new JSONArray();
      ((JSONObject)localObject).put("header", cn.com.iresearch.mapptracker.fm.util.f.e(paramContext));
      ((JSONObject)localObject).put("page_list", localJSONArray);
      ((JSONObject)localObject).put("event_list", localJSONArray);
      ((JSONObject)localObject).put("lat", "");
      ((JSONObject)localObject).put("lng", "");
      ((JSONObject)localObject).put("open_count", "0");
      ((JSONObject)localObject).put("page_count", "0");
      ((JSONObject)localObject).put("run_time", "0");
      paramContext = this.k.a();
      localObject = ((JSONObject)localObject).toString();
      if (cn.com.iresearch.mapptracker.fm.util.f.b(h.g))
        new e(this, (String)localObject, paramContext).start();
      return;
    }
    catch (JSONException paramContext)
    {
      if (c)
        Log.e("MAT_SESSION", "初始化Session失败");
      paramContext.printStackTrace();
    }
  }

  private static void b(Object paramObject)
  {
    if (paramObject != null);
    try
    {
      if (h != null)
      {
        SharedPreferences.Editor localEditor = h.j;
        if (localEditor != null)
          break label28;
      }
      while (true)
      {
        return;
        try
        {
          label28: h.f.a(paramObject);
          paramObject = h;
          paramObject.p += 1;
          h.j.putInt("sPage_Count", h.p).commit();
        }
        catch (Exception paramObject)
        {
          paramObject.printStackTrace();
        }
      }
    }
    finally
    {
    }
    throw paramObject;
  }

  private boolean b()
  {
    try
    {
      long l1 = this.i.getLong("daysend", 0L);
      String str = cn.com.iresearch.mapptracker.fm.util.f.a();
      if (!"".equals(str))
      {
        long l2 = Long.valueOf(str).longValue();
        if (l2 - l1 > 0L)
          return true;
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      localNumberFormatException.printStackTrace();
    }
    return false;
  }

  public static IRMonitor getInstance(Context paramContext)
  {
    if (h == null);
    try
    {
      if (h == null)
      {
        IRMonitor localIRMonitor = new IRMonitor();
        h = localIRMonitor;
        localIRMonitor.g = paramContext.getApplicationContext();
      }
      h.g = paramContext;
      return h;
    }
    finally
    {
    }
    throw paramContext;
  }

  public void Init(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (this.i != null)
      return;
    a = paramString1;
    paramString1 = paramString2;
    if (paramString2 == null)
      paramString1 = cn.com.iresearch.mapptracker.fm.util.f.d(h.g);
    b = paramString1;
    c = paramBoolean;
    this.i = h.g.getSharedPreferences("MATSharedPreferences", 0);
    this.j = this.i.edit();
    a(h.g);
    if (this.i.getBoolean("isFirstRun", true))
      b(h.g);
    a(true);
  }

  public void Init(String paramString1, String paramString2, boolean paramBoolean, IRCallBack paramIRCallBack)
  {
    if (this.i != null)
      return;
    a = paramString1;
    paramString1 = paramString2;
    if (paramString2 == null)
      paramString1 = cn.com.iresearch.mapptracker.fm.util.f.d(h.g);
    b = paramString1;
    c = paramBoolean;
    d = paramIRCallBack;
    this.i = h.g.getSharedPreferences("MATSharedPreferences", 0);
    this.j = this.i.edit();
    a(h.g);
    if (this.i.getBoolean("isFirstRun", true))
      b(h.g);
    a(true);
  }

  public String getMyUid()
  {
    return cn.com.iresearch.mapptracker.fm.util.f.g(this.g);
  }

  public String getSDKVer()
  {
    return "2.3.2";
  }

  public String getVVUid()
  {
    String str1 = "";
    String str3 = str1;
    String str4 = str1;
    try
    {
      String str6 = DataProvider.getVVUid();
      String str5 = str1;
      str3 = str1;
      str4 = str1;
      if (!TextUtils.isEmpty(str6))
      {
        str3 = str1;
        str4 = str1;
        str1 = c.a(str6, getMyUid());
        str3 = str1;
        str4 = str1;
        str6 = "VVUID=" + str1;
        str5 = str1;
        str3 = str1;
        str4 = str1;
        if (c)
        {
          str3 = str1;
          str4 = str1;
          Log.d("MAT_SESSION", str6);
          str5 = str1;
        }
      }
      return str5;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      localUnsatisfiedLinkError.printStackTrace();
      try
      {
        String str2 = c.a("mvcv1RTK", getMyUid());
        return str2;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        return str3;
      }
    }
    catch (Exception localException2)
    {
      localException2.printStackTrace();
    }
    return str4;
  }

  public void onEvent(String paramString)
  {
    onEvent(paramString, "");
  }

  public void onEvent(String paramString1, String paramString2)
  {
    onEvent(paramString1, paramString2, null);
  }

  public void onEvent(String paramString1, String paramString2, Map<String, String> paramMap)
  {
    try
    {
      EventInfo localEventInfo = new EventInfo();
      localEventInfo.setEvent_id(paramString1 + paramString2);
      localEventInfo.setLabel(paramString2);
      localEventInfo.setStart_time(System.currentTimeMillis() / 1000L);
      localEventInfo.setEnd_time(0L);
      localEventInfo.setDuration(0L);
      localEventInfo.setOpen_count(0L);
      localEventInfo.setEvent_params(cn.com.iresearch.mapptracker.fm.util.f.a(paramMap));
      new f(true, localEventInfo, paramString1 + paramString2).start();
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }

  public void onEventEnd(String paramString1, String paramString2)
  {
    try
    {
      EventInfo localEventInfo = (EventInfo)this.s.get(paramString1 + paramString2);
      if ((localEventInfo != null) && (localEventInfo.eventisStart))
      {
        long l1 = System.currentTimeMillis() / 1000L;
        localEventInfo.setEnd_time(l1);
        localEventInfo.setDuration(l1 - localEventInfo.getStart_time());
        if (localEventInfo.eventisStart)
          localEventInfo.open_count += 1L;
        localEventInfo.eventisStart = false;
        localEventInfo.setLabel(paramString2);
        localEventInfo.setStart_time(localEventInfo.first_start_time);
        this.s.put(paramString1 + paramString2, localEventInfo);
        String str = "event_id= " + paramString1 + paramString2 + "的事件结束! start_time:" + localEventInfo.first_start_time + " end_time:" + localEventInfo.getEnd_time() + " duration:" + localEventInfo.getDuration() + " 事件发生次数:" + localEventInfo.getOpen_count();
        if (c)
          Log.e("MAT_EVENT", str);
        new f(false, localEventInfo, paramString1 + paramString2).start();
        return;
      }
      if (c)
      {
        Log.e("MAT_EVENT", "请先调用onEventStart!");
        return;
      }
    }
    catch (Exception paramString1)
    {
      if (c)
        Log.e("MAT_EVENT", "事件结束保存失败!");
      paramString1.printStackTrace();
    }
  }

  public void onEventStart(String paramString1, String paramString2, Map<String, String> paramMap)
  {
    try
    {
      EventInfo localEventInfo = (EventInfo)this.s.get(paramString1 + paramString2);
      long l1 = System.currentTimeMillis() / 1000L;
      if (localEventInfo != null)
      {
        if (localEventInfo.event_id.equals(paramString1 + paramString2))
        {
          localEventInfo.setEvent_params(cn.com.iresearch.mapptracker.fm.util.f.a(paramMap));
          localEventInfo.setStart_time(l1);
          localEventInfo.eventisStart = true;
          paramMap = "event_id= " + paramString1 + paramString2 + "的事件开始! start_time:" + localEventInfo.first_start_time;
          if (c)
            Log.e("MAT_EVENT", paramMap);
          this.s.put(paramString1 + paramString2, localEventInfo);
        }
      }
      else
      {
        localEventInfo = new EventInfo();
        localEventInfo.event_id = (paramString1 + paramString2);
        localEventInfo.setEvent_params(cn.com.iresearch.mapptracker.fm.util.f.a(paramMap));
        localEventInfo.setStart_time(l1);
        localEventInfo.first_start_time = l1;
        localEventInfo.eventisStart = true;
        paramMap = "event_id= " + paramString1 + paramString2 + "的事件开始(第一次)! ,start_time: " + localEventInfo.first_start_time;
        if (c)
          Log.e("MAT_EVENT", paramMap);
        this.s.put(paramString1 + paramString2, localEventInfo);
        return;
      }
    }
    catch (Exception paramString1)
    {
      if (c)
        Log.e("MAT_EVENT", "事件开启失败:\n");
      paramString1.printStackTrace();
    }
  }

  public void onPause()
  {
    if (!"main".equals(Thread.currentThread().getName()))
      if (c)
        Log.e("MAT_SESSION", "非main线程return");
    do
    {
      return;
      if (Looper.myLooper() == Looper.getMainLooper())
        break;
    }
    while (!c);
    Log.e("MAT_SESSION", "非主线程return");
    return;
    while (true)
    {
      long l1;
      SessionInfo localSessionInfo;
      Object localObject3;
      try
      {
        h.m = false;
        if (this.l)
          break;
        this.l = true;
        this.o = (System.currentTimeMillis() / 1000L);
        l1 = this.o - this.n;
        if (l1 < 1L)
          break;
        localSessionInfo = new SessionInfo();
        localSessionInfo.setStart_time(this.n);
        localSessionInfo.setEnd_time(this.o);
        localSessionInfo.setDuration(l1);
        localSessionInfo.setSessionid((Long.parseLong(cn.com.iresearch.mapptracker.fm.util.f.a()) + 63529L) * 5L + (int)Math.round(Math.random() * 89999.0D + 10000.0D));
        localObject3 = h.g.getApplicationContext();
        if ("".equals(h.t))
        {
          Object localObject1 = cn.com.iresearch.mapptracker.fm.util.f.c(h.g);
          if (cn.com.iresearch.mapptracker.fm.util.f.c((Context)localObject3, (String)localObject1))
            break label445;
          localSessionInfo.setInapp(0L);
          localObject3 = cn.com.iresearch.mapptracker.fm.util.f.a(h.g.getApplicationContext(), 1);
          if (this.r.contains(localObject3))
            break label388;
          localObject3 = cn.com.iresearch.mapptracker.fm.util.f.a(h.g.getApplicationContext(), 2);
          str = "通过激活最近运行程序到后台,前一个Activity: " + (String)localObject3 + " 运行时间:" + l1 + "s";
          localObject1 = localObject3;
          if (c)
          {
            Log.e("MAT_SESSION", str);
            localObject1 = localObject3;
          }
          localSessionInfo.setPage_name((String)localObject1);
          if (this.p > this.k.g())
            break;
          new d(this, localSessionInfo).start();
          return;
        }
      }
      catch (Exception localException)
      {
        if (c)
          Log.e("MAT_SESSION", "onPause逻辑失败");
        localException.printStackTrace();
        return;
      }
      Object localObject2 = h.t;
      continue;
      label388: String str = "按HOME键去后台,前一个Activity: " + (String)localObject3 + " 运行时间:" + l1 + "s";
      localObject2 = localObject3;
      if (c)
      {
        Log.e("MAT_SESSION", str);
        localObject2 = localObject3;
        continue;
        label445: localObject2 = cn.com.iresearch.mapptracker.fm.util.f.a(h.g.getApplicationContext(), 0);
        localSessionInfo.setPage_name((String)localObject2);
        localSessionInfo.setInapp(1L);
        localObject2 = "在本app中跳转,前一个Activity: " + (String)localObject2 + " 运行时间:" + l1 + "s";
        if (c)
          Log.e("MAT_SESSION", (String)localObject2);
      }
    }
  }

  // ERROR //
  public void onResume()
  {
    // Byte code:
    //   0: ldc_w 547
    //   3: invokestatic 553	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   6: invokevirtual 556	java/lang/Thread:getName	()Ljava/lang/String;
    //   9: invokevirtual 148	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   12: ifne +4 -> 16
    //   15: return
    //   16: invokestatic 564	android/os/Looper:myLooper	()Landroid/os/Looper;
    //   19: invokestatic 567	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   22: if_acmpne -7 -> 15
    //   25: aload_0
    //   26: iconst_0
    //   27: putfield 66	cn/com/iresearch/mapptracker/fm/IRMonitor:l	Z
    //   30: aload_0
    //   31: iconst_0
    //   32: invokespecial 407	cn/com/iresearch/mapptracker/fm/IRMonitor:a	(Z)V
    //   35: aload_0
    //   36: invokestatic 470	java/lang/System:currentTimeMillis	()J
    //   39: ldc2_w 471
    //   42: ldiv
    //   43: putfield 70	cn/com/iresearch/mapptracker/fm/IRMonitor:n	J
    //   46: aload_0
    //   47: getfield 72	cn/com/iresearch/mapptracker/fm/IRMonitor:o	J
    //   50: lconst_0
    //   51: lcmp
    //   52: ifne +20 -> 72
    //   55: aload_0
    //   56: aload_0
    //   57: getfield 62	cn/com/iresearch/mapptracker/fm/IRMonitor:i	Landroid/content/SharedPreferences;
    //   60: ldc_w 644
    //   63: lconst_0
    //   64: invokeinterface 324 4 0
    //   69: putfield 72	cn/com/iresearch/mapptracker/fm/IRMonitor:o	J
    //   72: aload_0
    //   73: getfield 70	cn/com/iresearch/mapptracker/fm/IRMonitor:n	J
    //   76: aload_0
    //   77: getfield 72	cn/com/iresearch/mapptracker/fm/IRMonitor:o	J
    //   80: lsub
    //   81: invokestatic 648	java/lang/Math:abs	(J)J
    //   84: lstore 6
    //   86: aload_0
    //   87: getfield 64	cn/com/iresearch/mapptracker/fm/IRMonitor:k	Lcn/com/iresearch/mapptracker/fm/dao/b;
    //   90: invokevirtual 650	cn/com/iresearch/mapptracker/fm/dao/b:e	()I
    //   93: bipush 60
    //   95: imul
    //   96: i2l
    //   97: lstore 8
    //   99: lload 6
    //   101: ldc2_w 651
    //   104: lcmp
    //   105: ifle +15 -> 120
    //   108: aload_0
    //   109: getfield 90	cn/com/iresearch/mapptracker/fm/IRMonitor:u	Z
    //   112: ifeq +8 -> 120
    //   115: aload_0
    //   116: iconst_0
    //   117: putfield 90	cn/com/iresearch/mapptracker/fm/IRMonitor:u	Z
    //   120: aload_0
    //   121: getfield 90	cn/com/iresearch/mapptracker/fm/IRMonitor:u	Z
    //   124: ifne +163 -> 287
    //   127: aload_0
    //   128: invokespecial 654	cn/com/iresearch/mapptracker/fm/IRMonitor:b	()Z
    //   131: ifeq +156 -> 287
    //   134: aload_0
    //   135: iconst_1
    //   136: putfield 90	cn/com/iresearch/mapptracker/fm/IRMonitor:u	Z
    //   139: getstatic 94	cn/com/iresearch/mapptracker/fm/IRMonitor:h	Lcn/com/iresearch/mapptracker/fm/IRMonitor;
    //   142: getfield 128	cn/com/iresearch/mapptracker/fm/IRMonitor:g	Landroid/content/Context;
    //   145: invokevirtual 389	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   148: astore_2
    //   149: new 196	org/json/JSONObject
    //   152: dup
    //   153: invokespecial 197	org/json/JSONObject:<init>	()V
    //   156: astore_1
    //   157: new 269	org/json/JSONArray
    //   160: dup
    //   161: invokespecial 270	org/json/JSONArray:<init>	()V
    //   164: astore_3
    //   165: aload_1
    //   166: ldc_w 272
    //   169: aload_2
    //   170: invokestatic 275	cn/com/iresearch/mapptracker/fm/util/f:e	(Landroid/content/Context;)Lorg/json/JSONObject;
    //   173: invokevirtual 208	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   176: pop
    //   177: aload_1
    //   178: ldc_w 277
    //   181: aload_3
    //   182: invokevirtual 208	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   185: pop
    //   186: aload_1
    //   187: ldc_w 279
    //   190: aload_3
    //   191: invokevirtual 208	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   194: pop
    //   195: aload_1
    //   196: ldc_w 281
    //   199: ldc_w 655
    //   202: invokevirtual 208	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   205: pop
    //   206: aload_1
    //   207: ldc_w 283
    //   210: ldc_w 657
    //   213: invokevirtual 208	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   216: pop
    //   217: aload_1
    //   218: ldc 228
    //   220: ldc_w 285
    //   223: invokevirtual 208	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   226: pop
    //   227: aload_1
    //   228: ldc_w 287
    //   231: ldc_w 285
    //   234: invokevirtual 208	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   237: pop
    //   238: aload_1
    //   239: ldc_w 289
    //   242: ldc_w 285
    //   245: invokevirtual 208	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   248: pop
    //   249: aload_0
    //   250: getfield 64	cn/com/iresearch/mapptracker/fm/IRMonitor:k	Lcn/com/iresearch/mapptracker/fm/dao/b;
    //   253: invokevirtual 291	cn/com/iresearch/mapptracker/fm/dao/b:a	()Ljava/lang/String;
    //   256: astore_2
    //   257: aload_1
    //   258: invokevirtual 294	org/json/JSONObject:toString	()Ljava/lang/String;
    //   261: astore_1
    //   262: getstatic 94	cn/com/iresearch/mapptracker/fm/IRMonitor:h	Lcn/com/iresearch/mapptracker/fm/IRMonitor;
    //   265: getfield 128	cn/com/iresearch/mapptracker/fm/IRMonitor:g	Landroid/content/Context;
    //   268: invokestatic 297	cn/com/iresearch/mapptracker/fm/util/f:b	(Landroid/content/Context;)Z
    //   271: ifeq +16 -> 287
    //   274: new 659	cn/com/iresearch/mapptracker/fm/c
    //   277: dup
    //   278: aload_0
    //   279: aload_1
    //   280: aload_2
    //   281: invokespecial 660	cn/com/iresearch/mapptracker/fm/c:<init>	(Lcn/com/iresearch/mapptracker/fm/IRMonitor;Ljava/lang/String;Ljava/lang/String;)V
    //   284: invokevirtual 661	cn/com/iresearch/mapptracker/fm/c:start	()V
    //   287: lload 6
    //   289: lload 8
    //   291: lcmp
    //   292: iflt +338 -> 630
    //   295: aload_0
    //   296: getfield 72	cn/com/iresearch/mapptracker/fm/IRMonitor:o	J
    //   299: lconst_0
    //   300: lcmp
    //   301: ifeq +329 -> 630
    //   304: new 434	java/lang/StringBuilder
    //   307: dup
    //   308: ldc_w 663
    //   311: invokespecial 438	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   314: lload 6
    //   316: invokevirtual 523	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   319: ldc_w 665
    //   322: invokevirtual 442	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   325: lload 8
    //   327: invokevirtual 523	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   330: ldc_w 667
    //   333: invokevirtual 442	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   336: invokevirtual 443	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   339: astore_1
    //   340: getstatic 52	cn/com/iresearch/mapptracker/fm/IRMonitor:c	Z
    //   343: ifeq +10 -> 353
    //   346: ldc 184
    //   348: aload_1
    //   349: invokestatic 191	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   352: pop
    //   353: getstatic 94	cn/com/iresearch/mapptracker/fm/IRMonitor:h	Lcn/com/iresearch/mapptracker/fm/IRMonitor;
    //   356: getfield 154	cn/com/iresearch/mapptracker/fm/IRMonitor:f	Lcn/com/iresearch/mapptracker/fm/a/a;
    //   359: ldc 249
    //   361: invokevirtual 670	cn/com/iresearch/mapptracker/fm/a/a:b	(Ljava/lang/Class;)Ljava/util/List;
    //   364: astore_2
    //   365: new 83	java/util/HashMap
    //   368: dup
    //   369: invokespecial 84	java/util/HashMap:<init>	()V
    //   372: astore_1
    //   373: aload_2
    //   374: invokeinterface 674 1 0
    //   379: astore_2
    //   380: aload_2
    //   381: invokeinterface 679 1 0
    //   386: ifne +153 -> 539
    //   389: aload_1
    //   390: invokeinterface 683 1 0
    //   395: invokeinterface 686 1 0
    //   400: astore_1
    //   401: aload_1
    //   402: invokeinterface 679 1 0
    //   407: ifeq -392 -> 15
    //   410: aload_1
    //   411: invokeinterface 690 1 0
    //   416: checkcast 692	java/util/Map$Entry
    //   419: astore_3
    //   420: getstatic 94	cn/com/iresearch/mapptracker/fm/IRMonitor:h	Lcn/com/iresearch/mapptracker/fm/IRMonitor;
    //   423: getfield 128	cn/com/iresearch/mapptracker/fm/IRMonitor:g	Landroid/content/Context;
    //   426: invokevirtual 389	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   429: pop
    //   430: aload_3
    //   431: invokeinterface 695 1 0
    //   436: checkcast 163	java/util/List
    //   439: astore_2
    //   440: aload_3
    //   441: invokeinterface 698 1 0
    //   446: checkcast 144	java/lang/String
    //   449: astore_3
    //   450: getstatic 94	cn/com/iresearch/mapptracker/fm/IRMonitor:h	Lcn/com/iresearch/mapptracker/fm/IRMonitor;
    //   453: getfield 68	cn/com/iresearch/mapptracker/fm/IRMonitor:m	Z
    //   456: ifne -55 -> 401
    //   459: new 700	cn/com/iresearch/mapptracker/fm/b
    //   462: dup
    //   463: aload_2
    //   464: aload_3
    //   465: invokespecial 703	cn/com/iresearch/mapptracker/fm/b:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   468: invokevirtual 704	cn/com/iresearch/mapptracker/fm/b:start	()V
    //   471: goto -70 -> 401
    //   474: astore_1
    //   475: getstatic 52	cn/com/iresearch/mapptracker/fm/IRMonitor:c	Z
    //   478: ifeq +12 -> 490
    //   481: ldc 184
    //   483: ldc_w 706
    //   486: invokestatic 191	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   489: pop
    //   490: aload_1
    //   491: invokevirtual 117	java/lang/Exception:printStackTrace	()V
    //   494: return
    //   495: astore_1
    //   496: getstatic 52	cn/com/iresearch/mapptracker/fm/IRMonitor:c	Z
    //   499: ifeq +12 -> 511
    //   502: ldc 184
    //   504: ldc_w 708
    //   507: invokestatic 191	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   510: pop
    //   511: aload_1
    //   512: invokevirtual 117	java/lang/Exception:printStackTrace	()V
    //   515: return
    //   516: astore_1
    //   517: getstatic 52	cn/com/iresearch/mapptracker/fm/IRMonitor:c	Z
    //   520: ifeq +12 -> 532
    //   523: ldc 184
    //   525: ldc_w 710
    //   528: invokestatic 191	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   531: pop
    //   532: aload_1
    //   533: invokevirtual 247	org/json/JSONException:printStackTrace	()V
    //   536: goto -249 -> 287
    //   539: aload_2
    //   540: invokeinterface 690 1 0
    //   545: checkcast 249	cn/com/iresearch/mapptracker/fm/dao/SessionInfo
    //   548: astore_3
    //   549: aload_3
    //   550: invokevirtual 267	cn/com/iresearch/mapptracker/fm/dao/SessionInfo:getSessionid	()Ljava/lang/String;
    //   553: iconst_0
    //   554: aload_3
    //   555: invokevirtual 267	cn/com/iresearch/mapptracker/fm/dao/SessionInfo:getSessionid	()Ljava/lang/String;
    //   558: invokevirtual 713	java/lang/String:length	()I
    //   561: iconst_5
    //   562: isub
    //   563: invokevirtual 717	java/lang/String:substring	(II)Ljava/lang/String;
    //   566: astore 4
    //   568: aload_1
    //   569: aload 4
    //   571: invokeinterface 720 2 0
    //   576: ifeq +24 -> 600
    //   579: aload_1
    //   580: aload 4
    //   582: invokeinterface 505 2 0
    //   587: checkcast 163	java/util/List
    //   590: aload_3
    //   591: invokeinterface 723 2 0
    //   596: pop
    //   597: goto -217 -> 380
    //   600: new 78	java/util/ArrayList
    //   603: dup
    //   604: invokespecial 79	java/util/ArrayList:<init>	()V
    //   607: astore 5
    //   609: aload 5
    //   611: aload_3
    //   612: invokevirtual 724	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   615: pop
    //   616: aload_1
    //   617: aload 4
    //   619: aload 5
    //   621: invokeinterface 516 3 0
    //   626: pop
    //   627: goto -247 -> 380
    //   630: new 434	java/lang/StringBuilder
    //   633: dup
    //   634: ldc_w 663
    //   637: invokespecial 438	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   640: lload 6
    //   642: invokevirtual 523	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   645: ldc_w 726
    //   648: invokevirtual 442	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   651: lload 8
    //   653: invokevirtual 523	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   656: ldc_w 728
    //   659: invokevirtual 442	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   662: invokevirtual 443	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   665: astore_1
    //   666: getstatic 52	cn/com/iresearch/mapptracker/fm/IRMonitor:c	Z
    //   669: ifeq -654 -> 15
    //   672: ldc 184
    //   674: aload_1
    //   675: invokestatic 191	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   678: pop
    //   679: return
    //
    // Exception table:
    //   from	to	target	type
    //   353	380	474	java/lang/Exception
    //   380	401	474	java/lang/Exception
    //   401	471	474	java/lang/Exception
    //   539	597	474	java/lang/Exception
    //   600	627	474	java/lang/Exception
    //   25	72	495	java/lang/Exception
    //   72	99	495	java/lang/Exception
    //   108	120	495	java/lang/Exception
    //   120	149	495	java/lang/Exception
    //   149	287	495	java/lang/Exception
    //   295	353	495	java/lang/Exception
    //   475	490	495	java/lang/Exception
    //   490	494	495	java/lang/Exception
    //   517	532	495	java/lang/Exception
    //   532	536	495	java/lang/Exception
    //   630	679	495	java/lang/Exception
    //   149	287	516	org/json/JSONException
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.IRMonitor
 * JD-Core Version:    0.6.2
 */