package com.tencent.connect.auth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.b.a.g;
import com.tencent.connect.a.a;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.ApiTask;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.HttpUtils;
import com.tencent.utils.HttpUtils.HttpStatusException;
import com.tencent.utils.HttpUtils.NetworkUnavailableException;
import com.tencent.utils.ServerSetting;
import com.tencent.utils.SystemUtils;
import com.tencent.utils.Util;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthAgent extends BaseApi
{
  private IUiListener a;
  private String b;
  private Activity c;
  private IUiListener d = new IUiListener()
  {
    public void onCancel()
    {
    }

    public void onComplete(Object paramAnonymousObject)
    {
      if (paramAnonymousObject == null)
        AuthAgent.e(AuthAgent.this);
      while (true)
      {
        AuthAgent.this.writeEncryToken(AuthAgent.f(AuthAgent.this));
        return;
        Object localObject = (JSONObject)paramAnonymousObject;
        paramAnonymousObject = null;
        try
        {
          localObject = ((JSONObject)localObject).getString("encry_token");
          paramAnonymousObject = localObject;
          if (!TextUtils.isEmpty(paramAnonymousObject))
          {
            g.b("openSDK_LOG", "OpenUi, EncrytokenListener() onComplete validToken");
            AuthAgent.a(AuthAgent.this, paramAnonymousObject);
          }
        }
        catch (JSONException localJSONException)
        {
          while (true)
          {
            localJSONException.printStackTrace();
            g.a("openSDK_LOG", "OpenUi, EncrytokenListener() onComplete error", localJSONException);
          }
          g.b("openSDK_LOG", "OpenUi, EncrytokenListener() onComplete relogin");
          AuthAgent.e(AuthAgent.this);
        }
      }
    }

    public void onError(UiError paramAnonymousUiError)
    {
      g.b("openSDK_LOG", "AuthAgent, EncrytokenListener() onError relogin");
      AuthAgent.e(AuthAgent.this);
    }
  };
  private Handler e = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      g.b("openSDK_LOG", "OpenUi, handleMessage msg.what = " + paramAnonymousMessage.what + "");
      if (paramAnonymousMessage.what == 0)
        try
        {
          i = Integer.parseInt(((JSONObject)paramAnonymousMessage.obj).getString("ret"));
          if (i == 0)
          {
            AuthAgent.g(AuthAgent.this).onComplete((JSONObject)paramAnonymousMessage.obj);
            return;
          }
        }
        catch (JSONException localJSONException)
        {
          while (true)
          {
            localJSONException.printStackTrace();
            AuthAgent.e(AuthAgent.this);
            int i = 0;
          }
          AuthAgent.e(AuthAgent.this);
          return;
        }
      AuthAgent.g(AuthAgent.this).onError(new UiError(paramAnonymousMessage.what, (String)paramAnonymousMessage.obj, null));
    }
  };

  public AuthAgent(Context paramContext, QQToken paramQQToken)
  {
    super(paramContext, paramQQToken);
  }

  private int a(boolean paramBoolean, IUiListener paramIUiListener)
  {
    g.a("openSDK_LOG", "OpenUi, showDialog --start");
    CookieSyncManager.createInstance(this.mContext);
    Object localObject1 = composeCGIParams();
    if (paramBoolean)
      ((Bundle)localObject1).putString("isadd", "1");
    ((Bundle)localObject1).putString("scope", this.b);
    ((Bundle)localObject1).putString("client_id", this.mToken.getAppId());
    if (isOEM)
      ((Bundle)localObject1).putString("pf", "desktop_m_qq-" + installChannel + "-" + "android" + "-" + registerChannel + "-" + businessId);
    while (true)
    {
      Object localObject2 = System.currentTimeMillis() / 1000L + "";
      ((Bundle)localObject1).putString("sign", SystemUtils.getAppSignatureMD5(this.mContext, (String)localObject2));
      ((Bundle)localObject1).putString("time", (String)localObject2);
      ((Bundle)localObject1).putString("display", "mobile");
      ((Bundle)localObject1).putString("response_type", "token");
      ((Bundle)localObject1).putString("redirect_uri", "auth://tauth.qq.com/");
      ((Bundle)localObject1).putString("cancel_display", "1");
      ((Bundle)localObject1).putString("switch", "1");
      ((Bundle)localObject1).putString("status_userip", Util.getUserIp());
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(ServerSetting.getInstance().getEnvUrl(this.mContext, "https://openmobile.qq.com/oauth2.0/m_authorize?"));
      ((StringBuilder)localObject2).append(Util.encodeUrl((Bundle)localObject1));
      localObject1 = ((StringBuilder)localObject2).toString();
      paramIUiListener = new TokenListener(this.mContext, paramIUiListener, true, false);
      g.b("openSDK_LOG", "OpenUi, showDialog TDialog");
      new AuthDialog(this.c, "action_login", (String)localObject1, paramIUiListener, this.mToken).show();
      return 2;
      ((Bundle)localObject1).putString("pf", "openmobile_android");
    }
  }

  private void a()
  {
    this.mToken.setAccessToken("", "0");
    this.mToken.setOpenId("");
    doLogin(this.c, this.b, this.a, true);
  }

  private void a(String paramString)
  {
    g.b("openSDK_LOG", "OpenUi, EncrytokenListener() validToken()");
    Bundle localBundle = composeCGIParams();
    localBundle.putString("encrytoken", paramString);
    HttpUtils.requestAsync(this.mToken, this.mContext, "https://openmobile.qq.com/user/user_login_statis", localBundle, "POST", new RequestListener());
  }

  private boolean a(Activity paramActivity, boolean paramBoolean)
  {
    Intent localIntent = getTargetActivityIntent("com.tencent.open.agent.AgentActivity");
    if (localIntent != null)
    {
      Bundle localBundle = composeCGIParams();
      if (paramBoolean)
        localBundle.putString("isadd", "1");
      localBundle.putString("scope", this.b);
      localBundle.putString("client_id", this.mToken.getAppId());
      if (isOEM)
        localBundle.putString("pf", "desktop_m_qq-" + installChannel + "-" + "android" + "-" + registerChannel + "-" + businessId);
      while (true)
      {
        localBundle.putString("need_pay", "1");
        localBundle.putString("oauth_app_name", SystemUtils.getAppName(this.mContext));
        String str = System.currentTimeMillis() / 1000L + "";
        localBundle.putString("sign", SystemUtils.getAppSignatureMD5(this.mContext, str));
        localBundle.putString("time", str);
        localIntent.putExtra("key_action", "action_login");
        localIntent.putExtra("key_params", localBundle);
        this.mActivityIntent = localIntent;
        if (!hasActivityForIntent())
          break;
        this.a = new FeedConfirmListener(this.a);
        startAssitActivity(paramActivity, this.a);
        return true;
        localBundle.putString("pf", "openmobile_android");
      }
    }
    return false;
  }

  public int doLogin(Activity paramActivity, String paramString, IUiListener paramIUiListener)
  {
    return doLogin(paramActivity, paramString, paramIUiListener, false, false);
  }

  public int doLogin(Activity paramActivity, String paramString, IUiListener paramIUiListener, boolean paramBoolean)
  {
    return doLogin(paramActivity, paramString, paramIUiListener, paramBoolean, false);
  }

  public int doLogin(Activity paramActivity, String paramString, IUiListener paramIUiListener, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.b = paramString;
    this.c = paramActivity;
    this.a = paramIUiListener;
    if (!paramBoolean1)
    {
      paramString = this.mToken.getAccessToken();
      paramIUiListener = this.mToken.getOpenId();
      String str = this.mToken.getAppId();
      if ((!TextUtils.isEmpty(paramString)) && (!TextUtils.isEmpty(paramIUiListener)) && (!TextUtils.isEmpty(str)))
      {
        Intent localIntent1 = getTargetActivityIntent("com.tencent.open.agent.AgentActivity");
        Intent localIntent2 = getTargetActivityIntent("com.tencent.open.agent.EncryTokenActivity");
        if ((localIntent2 != null) && (localIntent1 != null) && (localIntent1.getComponent() != null) && (localIntent2.getComponent() != null) && (localIntent1.getComponent().getPackageName().equals(localIntent2.getComponent().getPackageName())))
        {
          localIntent2.putExtra("oauth_consumer_key", str);
          localIntent2.putExtra("openid", paramIUiListener);
          localIntent2.putExtra("access_token", paramString);
          localIntent2.putExtra("key_action", "action_check_token");
          this.mActivityIntent = localIntent2;
          if (hasActivityForIntent())
            startAssitActivity(paramActivity, this.d);
        }
        while (true)
        {
          return 3;
          paramString = Util.encrypt("tencent&sdk&qazxc***14969%%" + paramString + str + paramIUiListener + "qzone3.4");
          paramActivity = new JSONObject();
          try
          {
            paramActivity.put("encry_token", paramString);
            this.d.onComplete(paramActivity);
          }
          catch (JSONException paramString)
          {
            while (true)
              paramString.printStackTrace();
          }
        }
      }
    }
    if (a(paramActivity, paramBoolean2))
    {
      if (paramBoolean1)
        Util.reportBernoulli(paramActivity, "10785", 0L, this.mToken.getAppId());
      g.a("openSDK_LOG", "OpenUi, showUi, return Constants.UI_ACTIVITY");
      return 1;
    }
    this.a = new FeedConfirmListener(this.a);
    return a(paramBoolean2, this.a);
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    JSONObject localJSONObject = null;
    Object localObject2 = this.mTaskList.iterator();
    Object localObject3;
    do
    {
      localObject1 = localJSONObject;
      if (!((Iterator)localObject2).hasNext())
        break;
      localObject3 = (BaseApi.ApiTask)((Iterator)localObject2).next();
    }
    while (((BaseApi.ApiTask)localObject3).mRequestCode != paramInt1);
    Object localObject1 = ((BaseApi.ApiTask)localObject3).mListener;
    this.mTaskList.remove(localObject3);
    if (localObject1 == null)
      return;
    if (paramInt2 == -1)
    {
      paramInt1 = paramIntent.getIntExtra("key_error_code", 0);
      if (paramInt1 == 0)
      {
        paramIntent = paramIntent.getStringExtra("key_response");
        if (paramIntent == null);
      }
    }
    while (true)
    {
      try
      {
        localJSONObject = Util.parseJson(paramIntent);
        if (localObject1 == this.a)
        {
          localObject2 = localJSONObject.getString("access_token");
          localObject3 = localJSONObject.getString("expires_in");
          String str = localJSONObject.getString("openid");
          if ((!TextUtils.isEmpty((CharSequence)localObject2)) && (!TextUtils.isEmpty((CharSequence)localObject3)) && (!TextUtils.isEmpty(str)))
          {
            this.mToken.setAccessToken((String)localObject2, (String)localObject3);
            this.mToken.setOpenId(str);
          }
        }
        ((IUiListener)localObject1).onComplete(localJSONObject);
        g.a().b();
        return;
      }
      catch (JSONException localJSONException)
      {
        ((IUiListener)localObject1).onError(new UiError(-4, "服务器返回数据格式有误!", paramIntent));
        g.a("openSDK_LOG", "OpenUi, onActivityResult, json error", localJSONException);
        continue;
      }
      g.b("openSDK_LOG", "OpenUi, onActivityResult, onComplete");
      ((IUiListener)localObject1).onComplete(new JSONObject());
      continue;
      g.d("openSDK_LOG", "OpenUi, onActivityResult, onError = " + paramInt1 + "");
      ((IUiListener)localObject1).onError(new UiError(paramInt1, paramIntent.getStringExtra("key_error_msg"), paramIntent.getStringExtra("key_error_detail")));
      continue;
      g.b("openSDK_LOG", "OpenUi, onActivityResult, Constants.ACTIVITY_CANCEL");
      ((IUiListener)localObject1).onCancel();
    }
  }

  @SuppressLint({"SetJavaScriptEnabled"})
  public void writeEncryToken(Context paramContext)
  {
    String str = this.mToken.getAccessToken();
    Object localObject1 = this.mToken.getAppId();
    Object localObject2 = this.mToken.getOpenId();
    if ((str != null) && (str.length() > 0) && (localObject1 != null) && (((String)localObject1).length() > 0) && (localObject2 != null) && (((String)localObject2).length() > 0));
    for (str = Util.encrypt("tencent&sdk&qazxc***14969%%" + str + (String)localObject1 + (String)localObject2 + "qzone3.4"); ; str = null)
    {
      localObject1 = new WebView(paramContext);
      localObject2 = ((WebView)localObject1).getSettings();
      ((WebSettings)localObject2).setDomStorageEnabled(true);
      ((WebSettings)localObject2).setJavaScriptEnabled(true);
      ((WebSettings)localObject2).setDatabaseEnabled(true);
      str = "<!DOCTYPE HTML><html lang=\"en-US\"><head><meta charset=\"UTF-8\"><title>localStorage Test</title><script type=\"text/javascript\">document.domain = 'qq.com';localStorage[\"" + this.mToken.getOpenId() + "_" + this.mToken.getAppId() + "\"]=\"" + str + "\";</script></head><body></body></html>";
      paramContext = ServerSetting.getInstance().getEnvUrl(paramContext, "http://qzs.qq.com");
      ((WebView)localObject1).loadDataWithBaseURL(paramContext, str, "text/html", "utf-8", paramContext);
      return;
    }
  }

  private class FeedConfirmListener
    implements IUiListener
  {
    IUiListener a;
    private String c = "sendinstall";
    private String d = "installwording";
    private String e = "http://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi";

    public FeedConfirmListener(IUiListener arg2)
    {
      Object localObject;
      this.a = localObject;
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

    private View a(Context paramContext, Drawable paramDrawable, String paramString, View.OnClickListener paramOnClickListener1, View.OnClickListener paramOnClickListener2)
    {
      Object localObject1 = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics((DisplayMetrics)localObject1);
      float f = ((DisplayMetrics)localObject1).density;
      localObject1 = new RelativeLayout(paramContext);
      Object localObject2 = new ImageView(paramContext);
      ((ImageView)localObject2).setImageDrawable(paramDrawable);
      ((ImageView)localObject2).setScaleType(ImageView.ScaleType.FIT_XY);
      ((ImageView)localObject2).setId(1);
      int i = (int)(60.0F * f);
      int j = (int)(60.0F * f);
      int k = (int)(14.0F * f);
      k = (int)(18.0F * f);
      int m = (int)(6.0F * f);
      int n = (int)(18.0F * f);
      paramDrawable = new RelativeLayout.LayoutParams(i, j);
      paramDrawable.addRule(9);
      paramDrawable.setMargins(0, k, m, n);
      ((RelativeLayout)localObject1).addView((View)localObject2, paramDrawable);
      paramDrawable = new TextView(paramContext);
      paramDrawable.setText(paramString);
      paramDrawable.setTextSize(14.0F);
      paramDrawable.setGravity(3);
      paramDrawable.setIncludeFontPadding(false);
      paramDrawable.setPadding(0, 0, 0, 0);
      paramDrawable.setLines(2);
      paramDrawable.setId(5);
      paramDrawable.setMinWidth((int)(185.0F * f));
      paramString = new RelativeLayout.LayoutParams(-2, -2);
      paramString.addRule(1, 1);
      paramString.addRule(6, 1);
      i = (int)(10.0F * f);
      paramString.setMargins(0, 0, (int)(5.0F * f), 0);
      ((RelativeLayout)localObject1).addView(paramDrawable, paramString);
      paramDrawable = new View(paramContext);
      paramDrawable.setBackgroundColor(Color.rgb(214, 214, 214));
      paramDrawable.setId(3);
      paramString = new RelativeLayout.LayoutParams(-2, 2);
      paramString.addRule(3, 1);
      paramString.addRule(5, 1);
      paramString.addRule(7, 5);
      paramString.setMargins(0, 0, 0, (int)(12.0F * f));
      ((RelativeLayout)localObject1).addView(paramDrawable, paramString);
      paramDrawable = new LinearLayout(paramContext);
      paramString = new RelativeLayout.LayoutParams(-2, -2);
      paramString.addRule(5, 1);
      paramString.addRule(7, 5);
      paramString.addRule(3, 3);
      localObject2 = new Button(paramContext);
      ((Button)localObject2).setText("跳过");
      ((Button)localObject2).setBackgroundDrawable(a("buttonNegt.png", paramContext));
      ((Button)localObject2).setTextColor(Color.rgb(36, 97, 131));
      ((Button)localObject2).setTextSize(20.0F);
      ((Button)localObject2).setOnClickListener(paramOnClickListener2);
      ((Button)localObject2).setId(4);
      paramOnClickListener2 = new LinearLayout.LayoutParams(0, (int)(45.0F * f));
      paramOnClickListener2.rightMargin = ((int)(14.0F * f));
      paramOnClickListener2.leftMargin = ((int)(4.0F * f));
      paramOnClickListener2.weight = 1.0F;
      paramDrawable.addView((View)localObject2, paramOnClickListener2);
      paramOnClickListener2 = new Button(paramContext);
      paramOnClickListener2.setText("确定");
      paramOnClickListener2.setTextSize(20.0F);
      paramOnClickListener2.setTextColor(Color.rgb(255, 255, 255));
      paramOnClickListener2.setBackgroundDrawable(a("buttonPost.png", paramContext));
      paramOnClickListener2.setOnClickListener(paramOnClickListener1);
      paramContext = new LinearLayout.LayoutParams(0, (int)(45.0F * f));
      paramContext.weight = 1.0F;
      paramContext.rightMargin = ((int)(4.0F * f));
      paramDrawable.addView(paramOnClickListener2, paramContext);
      ((RelativeLayout)localObject1).addView(paramDrawable, paramString);
      paramContext = new FrameLayout.LayoutParams((int)(279.0F * f), (int)(163.0F * f));
      ((RelativeLayout)localObject1).setPadding((int)(14.0F * f), 0, (int)(12.0F * f), (int)(12.0F * f));
      ((RelativeLayout)localObject1).setLayoutParams(paramContext);
      ((RelativeLayout)localObject1).setBackgroundColor(Color.rgb(247, 251, 247));
      paramContext = new PaintDrawable(Color.rgb(247, 251, 247));
      paramContext.setCornerRadius(f * 5.0F);
      ((RelativeLayout)localObject1).setBackgroundDrawable(paramContext);
      return localObject1;
    }

    private void a(String paramString, final IUiListener paramIUiListener, final Object paramObject)
    {
      Drawable localDrawable = null;
      Dialog localDialog = new Dialog(AuthAgent.i(AuthAgent.this));
      localDialog.requestWindowFeature(1);
      Object localObject3 = AuthAgent.i(AuthAgent.this).getPackageManager();
      try
      {
        Object localObject1 = ((PackageManager)localObject3).getPackageInfo(AuthAgent.i(AuthAgent.this).getPackageName(), 0);
        if (localObject1 != null)
          localDrawable = ((PackageInfo)localObject1).applicationInfo.loadIcon((PackageManager)localObject3);
        localObject1 = new ButtonListener(localDialog, paramIUiListener)
        {
          public void onClick(View paramAnonymousView)
          {
            AuthAgent.FeedConfirmListener.this.a();
            if ((this.d != null) && (this.d.isShowing()))
              this.d.dismiss();
            if (paramIUiListener != null)
              paramIUiListener.onComplete(paramObject);
          }
        };
        localObject3 = new ButtonListener(localDialog, paramIUiListener)
        {
          public void onClick(View paramAnonymousView)
          {
            if ((this.d != null) && (this.d.isShowing()))
              this.d.dismiss();
            if (paramIUiListener != null)
              paramIUiListener.onComplete(paramObject);
          }
        };
        ColorDrawable localColorDrawable = new ColorDrawable();
        localColorDrawable.setAlpha(0);
        localDialog.getWindow().setBackgroundDrawable(localColorDrawable);
        localDialog.setContentView(a(AuthAgent.i(AuthAgent.this), localDrawable, paramString, (View.OnClickListener)localObject1, (View.OnClickListener)localObject3));
        localDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
          public void onCancel(DialogInterface paramAnonymousDialogInterface)
          {
            if (paramIUiListener != null)
              paramIUiListener.onComplete(paramObject);
          }
        });
        if ((AuthAgent.i(AuthAgent.this) != null) && (!AuthAgent.i(AuthAgent.this).isFinishing()))
          localDialog.show();
        return;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        while (true)
        {
          localNameNotFoundException.printStackTrace();
          Object localObject2 = null;
        }
      }
    }

    protected void a()
    {
      Bundle localBundle = AuthAgent.j(AuthAgent.this);
      HttpUtils.requestAsync(AuthAgent.k(AuthAgent.this), AuthAgent.i(AuthAgent.this), this.e, localBundle, "POST", null);
    }

    public void onCancel()
    {
      if (this.a != null)
        this.a.onCancel();
    }

    public void onComplete(Object paramObject)
    {
      int j = 0;
      int i = 0;
      if (paramObject != null)
      {
        localObject = (JSONObject)paramObject;
        if (localObject == null);
      }
      while (this.a == null)
        try
        {
          if (((JSONObject)localObject).getInt(this.c) == 1)
            i = 1;
          j = i;
          Object localObject = ((JSONObject)localObject).getString(this.d);
          localObject = URLDecoder.decode((String)localObject);
          Log.d("TAG", " WORDING = " + (String)localObject + "xx");
          if ((i != 0) && (!TextUtils.isEmpty((CharSequence)localObject)))
          {
            a((String)localObject, this.a, paramObject);
            return;
          }
        }
        catch (JSONException localJSONException)
        {
          do
            while (true)
            {
              Toast.makeText(AuthAgent.i(AuthAgent.this), "json error", 1);
              localJSONException.printStackTrace();
              i = j;
              String str = "";
            }
          while (this.a == null);
          this.a.onComplete(paramObject);
          return;
        }
      this.a.onComplete(null);
    }

    public void onError(UiError paramUiError)
    {
      if (this.a != null)
        this.a.onError(paramUiError);
    }

    private abstract class ButtonListener
      implements View.OnClickListener
    {
      Dialog d;

      ButtonListener(Dialog arg2)
      {
        Object localObject;
        this.d = localObject;
      }
    }
  }

  private class RequestListener
    implements IRequestListener
  {
    public RequestListener()
    {
      g.b("openSDK_LOG", "OpenUi, RequestListener()");
    }

    public void onComplete(JSONObject paramJSONObject)
    {
      g.b("openSDK_LOG", "OpenUi, RequestListener() onComplete");
      Message localMessage = new Message();
      localMessage.what = 0;
      localMessage.obj = paramJSONObject;
      AuthAgent.h(AuthAgent.this).sendMessage(localMessage);
    }

    public void onConnectTimeoutException(ConnectTimeoutException paramConnectTimeoutException)
    {
      g.a("openSDK_LOG", "OpenUi, RequestListener() onConnectTimeoutException", paramConnectTimeoutException);
      Message localMessage = new Message();
      localMessage.what = -7;
      localMessage.obj = (paramConnectTimeoutException.getMessage() + "");
      AuthAgent.h(AuthAgent.this).sendMessage(localMessage);
    }

    public void onHttpStatusException(HttpUtils.HttpStatusException paramHttpStatusException)
    {
      g.a("openSDK_LOG", "OpenUi, RequestListener() onHttpStatusException", paramHttpStatusException);
      Message localMessage = new Message();
      localMessage.what = -9;
      localMessage.obj = (paramHttpStatusException.getMessage() + "");
      AuthAgent.h(AuthAgent.this).sendMessage(localMessage);
    }

    public void onIOException(IOException paramIOException)
    {
      g.a("openSDK_LOG", "OpenUi, RequestListener() onIOException", paramIOException);
      Message localMessage = new Message();
      localMessage.what = -2;
      localMessage.obj = (paramIOException.getMessage() + "");
      AuthAgent.h(AuthAgent.this).sendMessage(localMessage);
    }

    public void onJSONException(JSONException paramJSONException)
    {
      g.a("openSDK_LOG", "OpenUi, RequestListener() onJSONException", paramJSONException);
      Message localMessage = new Message();
      localMessage.what = -4;
      localMessage.obj = (paramJSONException.getMessage() + "");
      AuthAgent.h(AuthAgent.this).sendMessage(localMessage);
    }

    public void onMalformedURLException(MalformedURLException paramMalformedURLException)
    {
      Message localMessage = new Message();
      localMessage.what = -3;
      localMessage.obj = (paramMalformedURLException.getMessage() + "");
      AuthAgent.h(AuthAgent.this).sendMessage(localMessage);
    }

    public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException paramNetworkUnavailableException)
    {
      g.a("openSDK_LOG", "OpenUi, RequestListener() onNetworkUnavailableException", paramNetworkUnavailableException);
      Message localMessage = new Message();
      localMessage.what = -2;
      localMessage.obj = (paramNetworkUnavailableException.getMessage() + "");
      AuthAgent.h(AuthAgent.this).sendMessage(localMessage);
    }

    public void onSocketTimeoutException(SocketTimeoutException paramSocketTimeoutException)
    {
      g.a("openSDK_LOG", "OpenUi, RequestListener() onSocketTimeoutException", paramSocketTimeoutException);
      Message localMessage = new Message();
      localMessage.what = -8;
      localMessage.obj = (paramSocketTimeoutException.getMessage() + "");
      AuthAgent.h(AuthAgent.this).sendMessage(localMessage);
    }

    public void onUnknowException(Exception paramException)
    {
      g.a("openSDK_LOG", "OpenUi, RequestListener() onUnknowException", paramException);
      Message localMessage = new Message();
      localMessage.what = -6;
      localMessage.obj = (paramException.getMessage() + "");
      AuthAgent.h(AuthAgent.this).sendMessage(localMessage);
    }
  }

  private class TokenListener
    implements IUiListener
  {
    private IUiListener b;
    private boolean c;
    private Context d;

    public TokenListener(Context paramIUiListener, IUiListener paramBoolean1, boolean paramBoolean2, boolean arg5)
    {
      this.d = paramIUiListener;
      this.b = paramBoolean1;
      this.c = paramBoolean2;
      g.b("openSDK_LOG", "OpenUi, TokenListener()");
    }

    public void onCancel()
    {
      g.b("openSDK_LOG", "OpenUi, TokenListener() onCancel");
      this.b.onCancel();
      g.a().b();
    }

    public void onComplete(Object paramObject)
    {
      g.b("openSDK_LOG", "OpenUi, TokenListener() onComplete");
      paramObject = (JSONObject)paramObject;
      try
      {
        String str1 = paramObject.getString("access_token");
        String str2 = paramObject.getString("expires_in");
        String str3 = paramObject.getString("openid");
        if ((str1 != null) && (AuthAgent.a(AuthAgent.this) != null) && (str3 != null))
        {
          AuthAgent.b(AuthAgent.this).setAccessToken(str1, str2);
          AuthAgent.c(AuthAgent.this).setOpenId(str3);
          a.d(this.d, AuthAgent.d(AuthAgent.this));
        }
        str1 = paramObject.getString("pf");
        if (str1 != null);
        try
        {
          this.d.getSharedPreferences("pfStore", 0).edit().putString("pf", str1).commit();
          if (this.c)
            CookieSyncManager.getInstance().sync();
          this.b.onComplete(paramObject);
          g.a().b();
          return;
        }
        catch (Exception localException)
        {
          while (true)
          {
            localException.printStackTrace();
            g.a("openSDK_LOG", "OpenUi, TokenListener() onComplete error", localException);
          }
        }
      }
      catch (JSONException localJSONException)
      {
        while (true)
        {
          localJSONException.printStackTrace();
          g.a("openSDK_LOG", "OpenUi, TokenListener() onComplete error", localJSONException);
        }
      }
    }

    public void onError(UiError paramUiError)
    {
      g.b("openSDK_LOG", "OpenUi, TokenListener() onError");
      this.b.onError(paramUiError);
      g.a().b();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.connect.auth.AuthAgent
 * JD-Core Version:    0.6.2
 */