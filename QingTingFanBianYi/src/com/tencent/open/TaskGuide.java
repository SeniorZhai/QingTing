package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.connect.a.a;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.HttpUtils;
import com.tencent.utils.HttpUtils.HttpStatusException;
import com.tencent.utils.HttpUtils.NetworkUnavailableException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TaskGuide extends BaseApi
{
  private static int K = 3000;
  static long b;
  private static Drawable k;
  private static Drawable l;
  private static Drawable m;
  private static int n = 75;
  private static int o = 284;
  private static int p = 75;
  private static int q = 30;
  private static int r = 29;
  private static int s = 5;
  private static int t = 74;
  private static int u = 0;
  private static int v = 6;
  private static int w = 153;
  private static int x = 30;
  private static int y = 6;
  private static int z = 3;
  private int A = 0;
  private int B = 0;
  private float C = 0.0F;
  private Interpolator D = new AccelerateInterpolator();
  private boolean E = false;
  private boolean F = false;
  private boolean G = false;
  private long H;
  private int I;
  private int J;
  private Runnable L = null;
  private Runnable M = null;
  boolean a = false;
  IUiListener c;
  private WindowManager.LayoutParams d = null;
  private ViewGroup e = null;
  private WindowManager f;
  private Handler g = new Handler(Looper.getMainLooper());
  private h h;
  private k i = k.a;
  private k j = k.a;

  static
  {
    b = 5000L;
  }

  public TaskGuide(Context paramContext, QQAuth paramQQAuth, QQToken paramQQToken)
  {
    super(paramContext, paramQQAuth, paramQQToken);
    this.f = ((WindowManager)paramContext.getSystemService("window"));
    c();
  }

  public TaskGuide(Context paramContext, QQToken paramQQToken)
  {
    super(paramContext, paramQQToken);
    this.f = ((WindowManager)paramContext.getSystemService("window"));
    c();
  }

  private int a(int paramInt)
  {
    return (int)(paramInt * this.C);
  }

  private Drawable a(String paramString, Context paramContext)
  {
    paramContext = paramContext.getApplicationContext().getAssets();
    try
    {
      paramContext = paramContext.open(paramString);
      if (paramContext == null)
        return null;
      if (paramString.endsWith(".9.png"))
      {
        paramString = BitmapFactory.decodeStream(paramContext);
        if (paramString != null)
        {
          paramContext = paramString.getNinePatchChunk();
          NinePatch.isNinePatchChunk(paramContext);
          paramString = new NinePatchDrawable(paramString, paramContext, new Rect(), null);
          return paramString;
        }
      }
    }
    catch (IOException paramContext)
    {
      paramString = null;
    }
    while (true)
    {
      paramContext.printStackTrace();
      return paramString;
      return null;
      paramString = Drawable.createFromStream(paramContext, paramString);
      try
      {
        paramContext.close();
        return paramString;
      }
      catch (IOException paramContext)
      {
      }
    }
  }

  private WindowManager.LayoutParams a(Context paramContext)
  {
    paramContext = new WindowManager.LayoutParams();
    paramContext.gravity = 49;
    this.f.getDefaultDisplay().getWidth();
    this.f.getDefaultDisplay().getHeight();
    paramContext.width = a(o);
    paramContext.height = a(n);
    paramContext.windowAnimations = 16973826;
    paramContext.format = 1;
    paramContext.flags |= 520;
    paramContext.type = 2;
    this.d = paramContext;
    return paramContext;
  }

  private void a(int paramInt, k paramk)
  {
    if (paramInt == 0)
    {
      this.i = paramk;
      return;
    }
    if (paramInt == 1)
    {
      this.j = paramk;
      return;
    }
    this.i = paramk;
    this.j = paramk;
  }

  private void a(final String paramString)
  {
    this.g.post(new Runnable()
    {
      public void run()
      {
        Toast.makeText(TaskGuide.z(TaskGuide.this), "失败：" + paramString, 1).show();
      }
    });
  }

  private void a(boolean paramBoolean)
  {
    this.H = SystemClock.currentThreadTimeMillis();
    if (paramBoolean)
      this.F = true;
    while (true)
    {
      this.I = this.d.height;
      this.J = this.d.y;
      WindowManager.LayoutParams localLayoutParams = this.d;
      localLayoutParams.flags |= 16;
      this.f.updateViewLayout(this.e, this.d);
      return;
      this.G = true;
    }
  }

  private ViewGroup b(Context paramContext)
  {
    e locale = new e(paramContext);
    Object localObject2 = this.h.c;
    Object localObject1;
    if (localObject2.length == 1)
    {
      paramContext = new i(paramContext, localObject2[0]);
      paramContext.setId(1);
      localObject1 = new RelativeLayout.LayoutParams(-1, -2);
      ((RelativeLayout.LayoutParams)localObject1).addRule(15);
      locale.addView(paramContext, (ViewGroup.LayoutParams)localObject1);
    }
    while (true)
    {
      locale.setBackgroundDrawable(e());
      return locale;
      localObject1 = new i(paramContext, localObject2[0]);
      ((i)localObject1).setId(1);
      paramContext = new i(paramContext, localObject2[1]);
      paramContext.setId(2);
      localObject2 = new RelativeLayout.LayoutParams(-1, -2);
      ((RelativeLayout.LayoutParams)localObject2).addRule(14);
      ((RelativeLayout.LayoutParams)localObject2).setMargins(0, a(6), 0, 0);
      RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams.addRule(14);
      localLayoutParams.setMargins(0, a(4), 0, 0);
      localLayoutParams.addRule(3, 1);
      localLayoutParams.addRule(5, 1);
      locale.addView((View)localObject1, (ViewGroup.LayoutParams)localObject2);
      locale.addView(paramContext, localLayoutParams);
    }
  }

  private void b(final int paramInt)
  {
    if (this.g != null)
      this.g.post(new Runnable()
      {
        public void run()
        {
          if (TaskGuide.a(TaskGuide.this))
          {
            if (paramInt != 0)
              break label42;
            ((TaskGuide.i)TaskGuide.b(TaskGuide.this).findViewById(1)).a(TaskGuide.c(TaskGuide.this));
          }
          label42: 
          do
          {
            do
            {
              return;
              if (paramInt == 1)
              {
                ((TaskGuide.i)TaskGuide.b(TaskGuide.this).findViewById(2)).a(TaskGuide.d(TaskGuide.this));
                return;
              }
            }
            while (paramInt != 2);
            ((TaskGuide.i)TaskGuide.b(TaskGuide.this).findViewById(1)).a(TaskGuide.c(TaskGuide.this));
          }
          while (TaskGuide.b(TaskGuide.this).getChildCount() <= 1);
          ((TaskGuide.i)TaskGuide.b(TaskGuide.this).findViewById(2)).a(TaskGuide.d(TaskGuide.this));
        }
      });
  }

  private k c(int paramInt)
  {
    if (paramInt == 0)
      return this.i;
    if (paramInt == 1)
      return this.j;
    return k.a;
  }

  private void c()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    this.f.getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.A = localDisplayMetrics.widthPixels;
    this.B = localDisplayMetrics.heightPixels;
    this.C = localDisplayMetrics.density;
  }

  private void d()
  {
    if (this.d != null)
      this.d.y = (-this.d.height);
  }

  private void d(int paramInt)
  {
    h();
    this.M = new b(null);
    this.g.postDelayed(this.M, paramInt);
  }

  private Drawable e()
  {
    if (k == null)
      k = a("background.9.png", this.mContext);
    return k;
  }

  private void e(int paramInt)
  {
    Bundle localBundle = composeCGIParams();
    localBundle.putString("action", "get_gift");
    localBundle.putString("task_id", this.h.a);
    localBundle.putString("step_no", new Integer(paramInt).toString());
    localBundle.putString("appid", this.mToken.getAppId());
    d locald = new d(paramInt);
    HttpUtils.requestAsync(this.mToken, this.mContext, "http://appact.qzone.qq.com/appstore_activity_task_pcpush_sdk", localBundle, "GET", locald);
    a(paramInt, k.c);
    a.a(this.mContext, this.mToken, "TaskApi", new String[] { "getGift" });
  }

  private Drawable f()
  {
    if (l == null)
      l = a("button_green.9.png", this.mContext);
    return l;
  }

  private Drawable g()
  {
    if (m == null)
      m = a("button_red.9.png", this.mContext);
    return m;
  }

  private void h()
  {
    this.g.removeCallbacks(this.M);
    if (!j())
      this.g.removeCallbacks(this.L);
  }

  private void i()
  {
    if (this.F)
      d(3000);
    while (true)
    {
      if (this.F)
      {
        WindowManager.LayoutParams localLayoutParams = this.d;
        localLayoutParams.flags &= -17;
        this.f.updateViewLayout(this.e, this.d);
      }
      this.F = false;
      this.G = false;
      return;
      removeWindow();
    }
  }

  private boolean j()
  {
    return (this.F) || (this.G);
  }

  private void k()
  {
    if (!j())
    {
      this.g.removeCallbacks(this.M);
      this.g.removeCallbacks(this.L);
      this.L = new c(true);
      a(true);
      this.g.post(this.L);
    }
  }

  private void l()
  {
    if (!j())
    {
      this.g.removeCallbacks(this.M);
      this.g.removeCallbacks(this.L);
      this.L = new c(false);
      a(false);
      this.g.post(this.L);
    }
  }

  public void removeWindow()
  {
    if (this.E)
    {
      this.f.removeView(this.e);
      this.E = false;
    }
  }

  public void showTaskGuideWindow(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    this.mContext = paramActivity;
    this.c = paramIUiListener;
    if ((this.i == k.b) || (this.j == k.b) || (this.E))
      return;
    this.h = null;
    if (paramBundle != null)
    {
      paramActivity = new Bundle(paramBundle);
      paramActivity.putAll(composeCGIParams());
    }
    while (true)
    {
      paramBundle = new j(null);
      paramActivity.putString("action", "task_list");
      paramActivity.putString("auth", "mobile");
      paramActivity.putString("appid", this.mToken.getAppId());
      HttpUtils.requestAsync(this.mToken, this.mContext, "http://appact.qzone.qq.com/appstore_activity_task_pcpush_sdk", paramActivity, "GET", paramBundle);
      a(2, k.b);
      return;
      paramActivity = composeCGIParams();
    }
  }

  @SuppressLint({"ResourceAsColor"})
  public void showWindow()
  {
    new Handler(Looper.getMainLooper()).post(new Runnable()
    {
      public void run()
      {
        TaskGuide.a(TaskGuide.this, TaskGuide.a(TaskGuide.this, TaskGuide.f(TaskGuide.this)));
        TaskGuide.a(TaskGuide.this, TaskGuide.b(TaskGuide.this, TaskGuide.g(TaskGuide.this)));
        TaskGuide.h(TaskGuide.this);
        WindowManager localWindowManager = (WindowManager)TaskGuide.i(TaskGuide.this).getSystemService("window");
        if (((Activity)TaskGuide.j(TaskGuide.this)).isFinishing())
          return;
        if (!TaskGuide.a(TaskGuide.this))
          localWindowManager.addView(TaskGuide.b(TaskGuide.this), TaskGuide.k(TaskGuide.this));
        TaskGuide.a(TaskGuide.this, true);
        TaskGuide.c(TaskGuide.this, 2);
        TaskGuide.l(TaskGuide.this);
      }
    });
    a.a(this.mContext, this.mToken, "TaskApi", new String[] { "showTaskWindow" });
  }

  private abstract class a
    implements IRequestListener
  {
    private a()
    {
    }

    protected abstract void a(Exception paramException);

    public void onConnectTimeoutException(ConnectTimeoutException paramConnectTimeoutException)
    {
      a(paramConnectTimeoutException);
    }

    public void onHttpStatusException(HttpUtils.HttpStatusException paramHttpStatusException)
    {
      a(paramHttpStatusException);
    }

    public void onIOException(IOException paramIOException)
    {
      a(paramIOException);
    }

    public void onJSONException(JSONException paramJSONException)
    {
      a(paramJSONException);
    }

    public void onMalformedURLException(MalformedURLException paramMalformedURLException)
    {
      a(paramMalformedURLException);
    }

    public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException paramNetworkUnavailableException)
    {
      a(paramNetworkUnavailableException);
    }

    public void onSocketTimeoutException(SocketTimeoutException paramSocketTimeoutException)
    {
      a(paramSocketTimeoutException);
    }

    public void onUnknowException(Exception paramException)
    {
      a(paramException);
    }
  }

  private class b
    implements Runnable
  {
    private b()
    {
    }

    public void run()
    {
      TaskGuide.q(TaskGuide.this);
    }
  }

  class c
    implements Runnable
  {
    boolean a = false;
    float b = 0.0F;

    public c(boolean arg2)
    {
      boolean bool;
      this.a = bool;
    }

    public void run()
    {
      int j = 1;
      SystemClock.currentThreadTimeMillis();
      this.b = ((float)(this.b + 0.1D));
      float f2 = this.b;
      float f1 = f2;
      if (f2 > 1.0F)
        f1 = 1.0F;
      int i;
      int k;
      if (f1 >= 1.0F)
      {
        i = 1;
        k = (int)(TaskGuide.r(TaskGuide.this).getInterpolation(f1) * TaskGuide.s(TaskGuide.this));
        if (!this.a)
          break label170;
        TaskGuide.k(TaskGuide.this).y = (TaskGuide.t(TaskGuide.this) + k);
        label96: Log.d("TAG", "mWinParams.y = " + TaskGuide.k(TaskGuide.this).y + "deltaDistence = " + k);
        if (TaskGuide.a(TaskGuide.this))
          break label193;
        i = j;
      }
      while (true)
      {
        if (i == 0)
          break label222;
        TaskGuide.v(TaskGuide.this);
        return;
        i = 0;
        break;
        label170: TaskGuide.k(TaskGuide.this).y = (TaskGuide.t(TaskGuide.this) - k);
        break label96;
        label193: TaskGuide.u(TaskGuide.this).updateViewLayout(TaskGuide.b(TaskGuide.this), TaskGuide.k(TaskGuide.this));
      }
      label222: TaskGuide.x(TaskGuide.this).postDelayed(TaskGuide.w(TaskGuide.this), 5L);
    }
  }

  private class d extends TaskGuide.a
  {
    int b = -1;

    public d(int arg2)
    {
      super(null);
      int i;
      this.b = i;
    }

    protected void a(final Exception paramException)
    {
      if (paramException != null)
        paramException.printStackTrace();
      TaskGuide.this.c.onError(new UiError(101, "error ", "金券领取时出现异常"));
      if (TaskGuide.x(TaskGuide.this) != null)
        TaskGuide.x(TaskGuide.this).post(new Runnable()
        {
          public void run()
          {
            TaskGuide.k localk = TaskGuide.k.a;
            if (TaskGuide.d.this.b == 0);
            for (localk = TaskGuide.c(TaskGuide.this); ; localk = TaskGuide.d(TaskGuide.this))
            {
              if (localk == TaskGuide.k.c)
              {
                TaskGuide.a(TaskGuide.this, TaskGuide.d.this.b, TaskGuide.k.d);
                TaskGuide.a(TaskGuide.this, "领取失败 :" + paramException.getClass().getName());
              }
              TaskGuide.c(TaskGuide.this, TaskGuide.d.this.b);
              TaskGuide.e(TaskGuide.this, 2000);
              return;
            }
          }
        });
    }

    public void onComplete(JSONObject paramJSONObject)
    {
      JSONObject localJSONObject2 = null;
      JSONObject localJSONObject1 = localJSONObject2;
      while (true)
      {
        try
        {
          int i = paramJSONObject.getInt("code");
          localJSONObject1 = localJSONObject2;
          paramJSONObject = paramJSONObject.getString("message");
          if (i == 0)
          {
            localJSONObject1 = paramJSONObject;
            TaskGuide.a(TaskGuide.this, this.b, TaskGuide.k.e);
            localJSONObject1 = paramJSONObject;
            localJSONObject2 = new JSONObject();
            try
            {
              localJSONObject2.put("result", "金券领取成功");
              localJSONObject1 = paramJSONObject;
              TaskGuide.this.c.onComplete(localJSONObject2);
              TaskGuide.c(TaskGuide.this, this.b);
              TaskGuide.e(TaskGuide.this, 2000);
              return;
            }
            catch (JSONException localJSONException1)
            {
              localJSONObject1 = paramJSONObject;
              localJSONException1.printStackTrace();
              continue;
            }
          }
        }
        catch (JSONException paramJSONObject)
        {
          TaskGuide.a(TaskGuide.this, this.b, TaskGuide.k.d);
          TaskGuide.a(TaskGuide.this, localJSONObject1);
          paramJSONObject.printStackTrace();
          continue;
          localJSONObject1 = paramJSONObject;
          TaskGuide.a(TaskGuide.this, this.b, TaskGuide.k.d);
          localJSONObject1 = paramJSONObject;
          TaskGuide.a(TaskGuide.this, paramJSONObject);
          localJSONObject1 = paramJSONObject;
          localJSONObject2 = new JSONObject();
        }
        try
        {
          localJSONObject2.put("result", "金券领取失败");
          localJSONObject1 = paramJSONObject;
          TaskGuide.this.c.onComplete(localJSONObject2);
        }
        catch (JSONException localJSONException2)
        {
          while (true)
          {
            localJSONObject1 = paramJSONObject;
            localJSONException2.printStackTrace();
          }
        }
      }
    }
  }

  private class e extends RelativeLayout
  {
    int a = 0;

    public e(Context arg2)
    {
      super();
    }

    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
    {
      int i = (int)paramMotionEvent.getY();
      Log.d("XXXX", "onInterceptTouchEvent-- action = " + paramMotionEvent.getAction() + "currentY = " + i);
      TaskGuide.e(TaskGuide.this, 3000);
      switch (paramMotionEvent.getAction())
      {
      default:
      case 0:
      case 1:
      }
      do
      {
        return super.onInterceptTouchEvent(paramMotionEvent);
        this.a = i;
        return false;
      }
      while (this.a - i <= ViewConfiguration.getTouchSlop() * 2);
      TaskGuide.q(TaskGuide.this);
      return true;
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      super.onTouchEvent(paramMotionEvent);
      int i = (int)paramMotionEvent.getY();
      Log.d("XXXX", " onTouchEvent-----startY = " + this.a + "currentY = " + i);
      switch (paramMotionEvent.getAction())
      {
      case 2:
      default:
      case 0:
      case 1:
      }
      while (true)
      {
        return false;
        this.a = i;
        continue;
        if (this.a - i > ViewConfiguration.getTouchSlop() * 2)
          TaskGuide.q(TaskGuide.this);
      }
    }
  }

  class f
    implements View.OnClickListener
  {
    int a;

    public f(int arg2)
    {
      int i;
      this.a = i;
    }

    public void onClick(View paramView)
    {
      paramView = (Button)paramView;
      if (TaskGuide.a(TaskGuide.this, this.a) == TaskGuide.k.d)
      {
        TaskGuide.b(TaskGuide.this, this.a);
        TaskGuide.c(TaskGuide.this, this.a);
      }
      TaskGuide.e(TaskGuide.this);
    }
  }

  private static class g
  {
    int a;
    String b;
    String c;
    long d;
    int e;

    public g(int paramInt1, String paramString1, String paramString2, long paramLong, int paramInt2)
    {
      this.a = paramInt1;
      this.b = paramString1;
      this.c = paramString2;
      this.d = paramLong;
      this.e = paramInt2;
    }
  }

  private static class h
  {
    String a;
    String b;
    TaskGuide.g[] c;

    static h a(JSONObject paramJSONObject)
      throws JSONException
    {
      if (paramJSONObject == null)
        return null;
      h localh = new h();
      paramJSONObject = paramJSONObject.getJSONObject("task_info");
      localh.a = paramJSONObject.getString("task_id");
      localh.b = paramJSONObject.getString("task_desc");
      paramJSONObject = paramJSONObject.getJSONArray("step_info");
      int j = paramJSONObject.length();
      if (j > 0)
        localh.c = new TaskGuide.g[j];
      int i = 0;
      while (i < j)
      {
        Object localObject = paramJSONObject.getJSONObject(i);
        int k = ((JSONObject)localObject).getInt("step_no");
        int m = ((JSONObject)localObject).getInt("status");
        localObject = new TaskGuide.g(k, ((JSONObject)localObject).getString("step_desc"), ((JSONObject)localObject).getString("step_gift"), ((JSONObject)localObject).getLong("end_time"), m);
        localh.c[i] = localObject;
        i += 1;
      }
      return localh;
    }

    public boolean a()
    {
      return (!TextUtils.isEmpty(this.a)) && (this.c != null) && (this.c.length > 0);
    }
  }

  private class i extends LinearLayout
  {
    private TextView b;
    private Button c;
    private TaskGuide.g d;

    public i(Context paramg, TaskGuide.g arg3)
    {
      super();
      Object localObject;
      this.d = localObject;
      setOrientation(0);
      a();
    }

    private void a()
    {
      this.b = new TextView(TaskGuide.m(TaskGuide.this));
      this.b.setTextColor(Color.rgb(255, 255, 255));
      this.b.setTextSize(15.0F);
      this.b.setShadowLayer(1.0F, 1.0F, 1.0F, Color.rgb(242, 211, 199));
      this.b.setGravity(3);
      this.b.setEllipsize(TextUtils.TruncateAt.END);
      this.b.setIncludeFontPadding(false);
      this.b.setSingleLine(true);
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(0, -2);
      localLayoutParams.weight = 1.0F;
      localLayoutParams.leftMargin = TaskGuide.d(TaskGuide.this, 4);
      addView(this.b, localLayoutParams);
      this.c = new Button(TaskGuide.n(TaskGuide.this));
      this.c.setPadding(0, 0, 0, 0);
      this.c.setTextSize(16.0F);
      this.c.setTextColor(Color.rgb(255, 255, 255));
      this.c.setShadowLayer(1.0F, 1.0F, 1.0F, Color.rgb(242, 211, 199));
      this.c.setIncludeFontPadding(false);
      this.c.setOnClickListener(new TaskGuide.f(TaskGuide.this, this.d.a));
      localLayoutParams = new LinearLayout.LayoutParams(TaskGuide.d(TaskGuide.this, TaskGuide.a()), TaskGuide.d(TaskGuide.this, TaskGuide.b()));
      localLayoutParams.leftMargin = TaskGuide.d(TaskGuide.this, 2);
      localLayoutParams.rightMargin = TaskGuide.d(TaskGuide.this, 8);
      addView(this.c, localLayoutParams);
    }

    public void a(TaskGuide.k paramk)
    {
      if (!TextUtils.isEmpty(this.d.b))
        this.b.setText(this.d.b);
      switch (TaskGuide.4.a[paramk.ordinal()])
      {
      default:
      case 1:
      case 2:
        do
        {
          return;
          this.c.setEnabled(false);
          return;
          if (this.d.e == 1)
          {
            this.c.setText(this.d.c);
            this.c.setBackgroundDrawable(null);
            this.c.setTextColor(Color.rgb(255, 246, 0));
            this.c.setEnabled(false);
            return;
          }
        }
        while (this.d.e != 2);
        this.c.setText("领取奖励");
        this.c.setTextColor(Color.rgb(255, 255, 255));
        this.c.setBackgroundDrawable(TaskGuide.o(TaskGuide.this));
        this.c.setEnabled(true);
        return;
      case 3:
        this.c.setText("领取中...");
        this.c.setEnabled(false);
        return;
      case 4:
      }
      this.c.setText("已领取");
      this.c.setBackgroundDrawable(TaskGuide.p(TaskGuide.this));
      this.c.setEnabled(false);
    }
  }

  private class j extends TaskGuide.a
  {
    private j()
    {
      super(null);
    }

    protected void a(Exception paramException)
    {
      if (paramException != null)
        paramException.printStackTrace();
      if (paramException == null)
        paramException = new JSONObject();
      while (true)
      {
        try
        {
          paramException.put("result", "暂无任务");
          TaskGuide.this.c.onComplete(paramException);
          TaskGuide.x(TaskGuide.this).post(new Runnable()
          {
            public void run()
            {
              TaskGuide.a(TaskGuide.this, 2, TaskGuide.k.a);
            }
          });
          return;
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          continue;
        }
        TaskGuide.this.c.onError(new UiError(100, "error ", "获取任务失败"));
      }
    }

    public void onComplete(JSONObject paramJSONObject)
    {
      try
      {
        TaskGuide.a(TaskGuide.this, TaskGuide.h.a(paramJSONObject));
        if ((TaskGuide.y(TaskGuide.this) != null) && (TaskGuide.y(TaskGuide.this).a()))
        {
          TaskGuide.this.showWindow();
          TaskGuide.a(TaskGuide.this, 2, TaskGuide.k.d);
          paramJSONObject = new JSONObject();
        }
      }
      catch (JSONException paramJSONObject)
      {
        try
        {
          paramJSONObject.put("result", "获取成功");
          TaskGuide.this.c.onComplete(paramJSONObject);
          return;
          paramJSONObject = paramJSONObject;
          paramJSONObject.printStackTrace();
        }
        catch (JSONException localJSONException)
        {
          while (true)
            localJSONException.printStackTrace();
        }
        a(null);
      }
    }
  }

  private static enum k
  {
    public static k[] a()
    {
      return (k[])g.clone();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.open.TaskGuide
 * JD-Core Version:    0.6.2
 */