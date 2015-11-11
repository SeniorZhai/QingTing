package com.alipay.sdk.auth;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.alipay.sdk.authjs.CallInfo;
import com.alipay.sdk.authjs.JsBridge;
import com.alipay.sdk.data.MspConfig;
import com.alipay.sdk.exception.FailOperatingException;
import com.alipay.sdk.protocol.ActionType;
import com.alipay.sdk.protocol.ElementAction;
import com.alipay.sdk.sys.GlobalContext;
import com.alipay.sdk.util.Utils;
import com.alipay.sdk.widget.Loading;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthActivity extends Activity
{
  static final String a = "params";
  static final String b = "redirectUri";
  private WebView c;
  private String d;
  private Loading e;
  private Handler f = new Handler();
  private boolean g;
  private Runnable h = new AuthActivity.15(this);

  // ERROR //
  private void a()
  {
    // Byte code:
    //   0: new 51	com/alipay/sdk/widget/Loading
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 54	com/alipay/sdk/widget/Loading:<init>	(Landroid/app/Activity;)V
    //   8: astore_1
    //   9: aload_1
    //   10: invokevirtual 56	com/alipay/sdk/widget/Loading:b	()V
    //   13: aload_0
    //   14: invokevirtual 60	com/alipay/sdk/auth/AuthActivity:getIntent	()Landroid/content/Intent;
    //   17: invokevirtual 66	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   20: ldc 8
    //   22: invokevirtual 72	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   25: astore_2
    //   26: aload_0
    //   27: aload_0
    //   28: invokevirtual 60	com/alipay/sdk/auth/AuthActivity:getIntent	()Landroid/content/Intent;
    //   31: invokevirtual 66	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   34: ldc 11
    //   36: invokevirtual 72	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   39: putfield 74	com/alipay/sdk/auth/AuthActivity:d	Ljava/lang/String;
    //   42: new 76	com/alipay/sdk/data/InteractionData
    //   45: dup
    //   46: invokespecial 77	com/alipay/sdk/data/InteractionData:<init>	()V
    //   49: aload_2
    //   50: new 79	org/json/JSONObject
    //   53: dup
    //   54: invokespecial 80	org/json/JSONObject:<init>	()V
    //   57: invokestatic 85	com/alipay/sdk/data/FrameUtils:a	(Lcom/alipay/sdk/data/InteractionData;Ljava/lang/String;Lorg/json/JSONObject;)Lcom/alipay/sdk/data/Request;
    //   60: astore 7
    //   62: aload 7
    //   64: invokevirtual 90	com/alipay/sdk/data/Request:d	()Lcom/alipay/sdk/data/Envelope;
    //   67: ldc 92
    //   69: invokevirtual 97	com/alipay/sdk/data/Envelope:c	(Ljava/lang/String;)V
    //   72: aload 7
    //   74: invokevirtual 90	com/alipay/sdk/data/Request:d	()Lcom/alipay/sdk/data/Envelope;
    //   77: ldc 99
    //   79: invokevirtual 101	com/alipay/sdk/data/Envelope:a	(Ljava/lang/String;)V
    //   82: aload 7
    //   84: invokevirtual 90	com/alipay/sdk/data/Request:d	()Lcom/alipay/sdk/data/Envelope;
    //   87: ldc 103
    //   89: invokevirtual 105	com/alipay/sdk/data/Envelope:e	(Ljava/lang/String;)V
    //   92: aload 7
    //   94: invokevirtual 90	com/alipay/sdk/data/Request:d	()Lcom/alipay/sdk/data/Envelope;
    //   97: ldc 107
    //   99: invokevirtual 109	com/alipay/sdk/data/Envelope:d	(Ljava/lang/String;)V
    //   102: new 111	com/alipay/sdk/net/RequestWrapper
    //   105: dup
    //   106: new 76	com/alipay/sdk/data/InteractionData
    //   109: dup
    //   110: invokespecial 77	com/alipay/sdk/data/InteractionData:<init>	()V
    //   113: invokespecial 114	com/alipay/sdk/net/RequestWrapper:<init>	(Lcom/alipay/sdk/data/InteractionData;)V
    //   116: astore 8
    //   118: aload_1
    //   119: astore_3
    //   120: aload_1
    //   121: astore 4
    //   123: aload_1
    //   124: astore 5
    //   126: aload_1
    //   127: astore 6
    //   129: aload_1
    //   130: astore_2
    //   131: aload 8
    //   133: aload_0
    //   134: aload 7
    //   136: iconst_0
    //   137: invokevirtual 117	com/alipay/sdk/net/RequestWrapper:a	(Landroid/content/Context;Lcom/alipay/sdk/data/Request;Z)Lcom/alipay/sdk/protocol/FrameData;
    //   140: invokevirtual 122	com/alipay/sdk/protocol/FrameData:c	()Lorg/json/JSONObject;
    //   143: astore 7
    //   145: aload_1
    //   146: astore_2
    //   147: aload_1
    //   148: ifnull +22 -> 170
    //   151: aload_1
    //   152: astore_3
    //   153: aload_1
    //   154: astore 4
    //   156: aload_1
    //   157: astore 5
    //   159: aload_1
    //   160: astore 6
    //   162: aload_1
    //   163: astore_2
    //   164: aload_1
    //   165: invokevirtual 124	com/alipay/sdk/widget/Loading:c	()V
    //   168: aconst_null
    //   169: astore_2
    //   170: aload_2
    //   171: astore_3
    //   172: aload_2
    //   173: astore 4
    //   175: aload_2
    //   176: astore 5
    //   178: aload_2
    //   179: astore 6
    //   181: aload_0
    //   182: aload 7
    //   184: invokespecial 127	com/alipay/sdk/auth/AuthActivity:a	(Lorg/json/JSONObject;)V
    //   187: return
    //   188: astore_1
    //   189: aconst_null
    //   190: astore_1
    //   191: goto -178 -> 13
    //   194: astore_1
    //   195: aload_3
    //   196: astore_2
    //   197: aload_0
    //   198: new 129	com/alipay/sdk/auth/AuthActivity$2
    //   201: dup
    //   202: aload_0
    //   203: invokespecial 130	com/alipay/sdk/auth/AuthActivity$2:<init>	(Lcom/alipay/sdk/auth/AuthActivity;)V
    //   206: invokevirtual 134	com/alipay/sdk/auth/AuthActivity:runOnUiThread	(Ljava/lang/Runnable;)V
    //   209: aload_3
    //   210: ifnull -23 -> 187
    //   213: aload_3
    //   214: invokevirtual 124	com/alipay/sdk/widget/Loading:c	()V
    //   217: return
    //   218: astore_1
    //   219: aload 4
    //   221: astore_2
    //   222: aload_0
    //   223: new 136	com/alipay/sdk/auth/AuthActivity$3
    //   226: dup
    //   227: aload_0
    //   228: invokespecial 137	com/alipay/sdk/auth/AuthActivity$3:<init>	(Lcom/alipay/sdk/auth/AuthActivity;)V
    //   231: invokevirtual 134	com/alipay/sdk/auth/AuthActivity:runOnUiThread	(Ljava/lang/Runnable;)V
    //   234: aload 4
    //   236: ifnull -49 -> 187
    //   239: aload 4
    //   241: invokevirtual 124	com/alipay/sdk/widget/Loading:c	()V
    //   244: return
    //   245: astore_1
    //   246: aload 5
    //   248: astore_2
    //   249: aload_0
    //   250: new 139	com/alipay/sdk/auth/AuthActivity$4
    //   253: dup
    //   254: aload_0
    //   255: invokespecial 140	com/alipay/sdk/auth/AuthActivity$4:<init>	(Lcom/alipay/sdk/auth/AuthActivity;)V
    //   258: invokevirtual 134	com/alipay/sdk/auth/AuthActivity:runOnUiThread	(Ljava/lang/Runnable;)V
    //   261: aload 5
    //   263: ifnull -76 -> 187
    //   266: aload 5
    //   268: invokevirtual 124	com/alipay/sdk/widget/Loading:c	()V
    //   271: return
    //   272: astore_1
    //   273: aload 6
    //   275: astore_2
    //   276: aload_0
    //   277: new 142	com/alipay/sdk/auth/AuthActivity$5
    //   280: dup
    //   281: aload_0
    //   282: invokespecial 143	com/alipay/sdk/auth/AuthActivity$5:<init>	(Lcom/alipay/sdk/auth/AuthActivity;)V
    //   285: invokevirtual 134	com/alipay/sdk/auth/AuthActivity:runOnUiThread	(Ljava/lang/Runnable;)V
    //   288: aload 6
    //   290: ifnull -103 -> 187
    //   293: aload 6
    //   295: invokevirtual 124	com/alipay/sdk/widget/Loading:c	()V
    //   298: return
    //   299: astore_1
    //   300: aload_2
    //   301: ifnull +7 -> 308
    //   304: aload_2
    //   305: invokevirtual 124	com/alipay/sdk/widget/Loading:c	()V
    //   308: aload_1
    //   309: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   0	13	188	java/lang/Exception
    //   131	145	194	com/alipay/sdk/exception/NetErrorException
    //   164	168	194	com/alipay/sdk/exception/NetErrorException
    //   181	187	194	com/alipay/sdk/exception/NetErrorException
    //   131	145	218	com/alipay/sdk/exception/FailOperatingException
    //   164	168	218	com/alipay/sdk/exception/FailOperatingException
    //   181	187	218	com/alipay/sdk/exception/FailOperatingException
    //   131	145	245	com/alipay/sdk/exception/AppErrorException
    //   164	168	245	com/alipay/sdk/exception/AppErrorException
    //   181	187	245	com/alipay/sdk/exception/AppErrorException
    //   131	145	272	com/alipay/sdk/exception/UnZipException
    //   164	168	272	com/alipay/sdk/exception/UnZipException
    //   181	187	272	com/alipay/sdk/exception/UnZipException
    //   131	145	299	finally
    //   164	168	299	finally
    //   181	187	299	finally
    //   197	209	299	finally
    //   222	234	299	finally
    //   249	261	299	finally
    //   276	288	299	finally
  }

  private void a(CallInfo paramCallInfo)
  {
    if ((this.c == null) || (paramCallInfo == null))
      return;
    try
    {
      runOnUiThread(new AuthActivity.14(this, String.format("AlipayJSBridge._invokeJS(%s)", new Object[] { paramCallInfo.d() })));
      return;
    }
    catch (JSONException paramCallInfo)
    {
      paramCallInfo.printStackTrace();
    }
  }

  private void a(String paramString)
  {
    this.c = new WebView(this);
    WebSettings localWebSettings = this.c.getSettings();
    localWebSettings.setUserAgentString(localWebSettings.getUserAgentString() + Utils.c(this));
    localWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
    localWebSettings.setSupportMultipleWindows(true);
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setSavePassword(false);
    localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    localWebSettings.setMinimumFontSize(localWebSettings.getMinimumFontSize() + 8);
    localWebSettings.setAllowFileAccess(false);
    this.c.setVerticalScrollbarOverlay(true);
    this.c.setWebViewClient(new AuthActivity.7(this));
    this.c.setWebChromeClient(new AuthActivity.8(this));
    this.c.setDownloadListener(new AuthActivity.9(this));
    setContentView(this.c);
    this.c.loadUrl(paramString);
    if (Build.VERSION.SDK_INT >= 7);
    try
    {
      paramString = this.c.getSettings().getClass().getMethod("setDomStorageEnabled", new Class[] { Boolean.TYPE });
      if (paramString != null)
        paramString.invoke(this.c.getSettings(), new Object[] { Boolean.valueOf(true) });
      try
      {
        label222: paramString = this.c.getClass().getMethod("removeJavascriptInterface", new Class[0]);
        if (paramString != null)
          paramString.invoke(this.c, new Object[] { "searchBoxJavaBridge_" });
        return;
      }
      catch (Exception paramString)
      {
      }
    }
    catch (Exception paramString)
    {
      break label222;
    }
  }

  private void a(JSONObject paramJSONObject)
    throws FailOperatingException
  {
    paramJSONObject = ElementAction.a(paramJSONObject.optJSONObject("form"), "onload");
    if (paramJSONObject == null)
      throw new FailOperatingException();
    paramJSONObject = ActionType.a(paramJSONObject);
    int j = paramJSONObject.length;
    int i = 0;
    while (true)
    {
      if (i < j)
      {
        Object localObject = paramJSONObject[i];
        if (localObject != ActionType.a)
          break label90;
        paramJSONObject = com.alipay.sdk.util.ActionUtil.a(localObject.e())[0];
        if (!Utils.a(paramJSONObject))
          finish();
      }
      else
      {
        return;
      }
      runOnUiThread(new AuthActivity.6(this, paramJSONObject));
      return;
      label90: i += 1;
    }
  }

  private void b()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage("不能连接到服务器，是否重试？");
    localBuilder.setPositiveButton("确定", new AuthActivity.10(this));
    localBuilder.setNeutralButton("取消", new AuthActivity.11(this));
    localBuilder.create().show();
  }

  private boolean b(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    while ((paramString.startsWith("http://")) || (paramString.startsWith("https://")))
      return false;
    if (!"SDKLite://h5quit".equalsIgnoreCase(paramString))
    {
      String str = paramString;
      if (TextUtils.equals(paramString, this.d))
        str = paramString + "?resultCode=150";
      AuthHelper.a(this, str);
    }
    finish();
    return true;
  }

  private void c()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage("系统繁忙，请稍后再试");
    localBuilder.setNeutralButton("确定", new AuthActivity.12(this));
    localBuilder.create().show();
  }

  private void c(String paramString)
  {
    new JsBridge(this, new AuthActivity.13(this)).a(paramString);
  }

  private void d()
  {
    if (this.e == null)
      this.e = new Loading(this);
    this.e.b();
  }

  private void e()
  {
    if ((this.e != null) && (this.e.a()))
      this.e.c();
    this.e = null;
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getIntent().getExtras() == null)
    {
      finish();
      return;
    }
    super.requestWindowFeature(1);
    GlobalContext.a().a(this, MspConfig.a());
    paramBundle = new RelativeLayout(this);
    paramBundle.setGravity(17);
    super.setContentView(paramBundle);
    new Thread(new AuthActivity.1(this)).start();
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.auth.AuthActivity
 * JD-Core Version:    0.6.2
 */