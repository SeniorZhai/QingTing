package com.alipay.sdk.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
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
import com.alipay.sdk.util.AuthHelper;
import com.alipay.sdk.util.Utils;
import com.alipay.sdk.widget.Loading;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

public class H5AuthActivity extends Activity
{
  private WebView a;
  private Loading b;
  private Handler c = new Handler();
  private boolean d;
  private Runnable e = new H5AuthActivity.15(this);

  private static void a()
  {
    synchronized (AuthHelper.a)
    {
      try
      {
        ???.notify();
        return;
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }
  }

  private void a(CallInfo paramCallInfo)
  {
    if ((this.a == null) || (paramCallInfo == null))
      return;
    try
    {
      runOnUiThread(new H5AuthActivity.14(this, String.format("AlipayJSBridge._invokeJS(%s)", new Object[] { paramCallInfo.d() })));
      return;
    }
    catch (JSONException paramCallInfo)
    {
      paramCallInfo.printStackTrace();
    }
  }

  private void a(String paramString)
  {
    this.a = new WebView(this);
    WebSettings localWebSettings = this.a.getSettings();
    localWebSettings.setUserAgentString(localWebSettings.getUserAgentString() + Utils.c(this));
    localWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
    localWebSettings.setSupportMultipleWindows(true);
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setSavePassword(false);
    localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    localWebSettings.setMinimumFontSize(localWebSettings.getMinimumFontSize() + 8);
    localWebSettings.setAllowFileAccess(false);
    this.a.setVerticalScrollbarOverlay(true);
    this.a.setWebViewClient(new H5AuthActivity.7(this));
    this.a.setWebChromeClient(new H5AuthActivity.8(this));
    this.a.setDownloadListener(new H5AuthActivity.9(this));
    setContentView(this.a);
    this.a.loadUrl(paramString);
    if (Build.VERSION.SDK_INT >= 7);
    try
    {
      paramString = this.a.getSettings().getClass().getMethod("setDomStorageEnabled", new Class[] { Boolean.TYPE });
      if (paramString != null)
        paramString.invoke(this.a.getSettings(), new Object[] { Boolean.valueOf(true) });
      try
      {
        label222: paramString = this.a.getClass().getMethod("removeJavascriptInterface", new Class[0]);
        if (paramString != null)
          paramString.invoke(this.a, new Object[] { "searchBoxJavaBridge_" });
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
      runOnUiThread(new H5AuthActivity.6(this, paramJSONObject));
      return;
      label90: i += 1;
    }
  }

  // ERROR //
  private void b()
  {
    // Byte code:
    //   0: new 56	com/alipay/sdk/widget/Loading
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 59	com/alipay/sdk/widget/Loading:<init>	(Landroid/app/Activity;)V
    //   8: astore_1
    //   9: aload_1
    //   10: invokevirtual 61	com/alipay/sdk/widget/Loading:b	()V
    //   13: aload_0
    //   14: invokevirtual 65	com/alipay/sdk/app/H5AuthActivity:getIntent	()Landroid/content/Intent;
    //   17: invokevirtual 71	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   20: ldc 73
    //   22: invokevirtual 79	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   25: astore_2
    //   26: new 81	com/alipay/sdk/data/InteractionData
    //   29: dup
    //   30: invokespecial 82	com/alipay/sdk/data/InteractionData:<init>	()V
    //   33: aload_2
    //   34: new 84	org/json/JSONObject
    //   37: dup
    //   38: invokespecial 85	org/json/JSONObject:<init>	()V
    //   41: invokestatic 90	com/alipay/sdk/data/FrameUtils:a	(Lcom/alipay/sdk/data/InteractionData;Ljava/lang/String;Lorg/json/JSONObject;)Lcom/alipay/sdk/data/Request;
    //   44: astore 7
    //   46: aload 7
    //   48: invokevirtual 95	com/alipay/sdk/data/Request:d	()Lcom/alipay/sdk/data/Envelope;
    //   51: ldc 97
    //   53: invokevirtual 102	com/alipay/sdk/data/Envelope:c	(Ljava/lang/String;)V
    //   56: aload 7
    //   58: invokevirtual 95	com/alipay/sdk/data/Request:d	()Lcom/alipay/sdk/data/Envelope;
    //   61: ldc 104
    //   63: invokevirtual 106	com/alipay/sdk/data/Envelope:a	(Ljava/lang/String;)V
    //   66: aload 7
    //   68: invokevirtual 95	com/alipay/sdk/data/Request:d	()Lcom/alipay/sdk/data/Envelope;
    //   71: ldc 108
    //   73: invokevirtual 110	com/alipay/sdk/data/Envelope:e	(Ljava/lang/String;)V
    //   76: aload 7
    //   78: invokevirtual 95	com/alipay/sdk/data/Request:d	()Lcom/alipay/sdk/data/Envelope;
    //   81: ldc 112
    //   83: invokevirtual 114	com/alipay/sdk/data/Envelope:d	(Ljava/lang/String;)V
    //   86: new 116	com/alipay/sdk/net/RequestWrapper
    //   89: dup
    //   90: new 81	com/alipay/sdk/data/InteractionData
    //   93: dup
    //   94: invokespecial 82	com/alipay/sdk/data/InteractionData:<init>	()V
    //   97: invokespecial 119	com/alipay/sdk/net/RequestWrapper:<init>	(Lcom/alipay/sdk/data/InteractionData;)V
    //   100: astore 8
    //   102: aload_1
    //   103: astore_3
    //   104: aload_1
    //   105: astore 4
    //   107: aload_1
    //   108: astore 5
    //   110: aload_1
    //   111: astore 6
    //   113: aload_1
    //   114: astore_2
    //   115: aload 8
    //   117: aload_0
    //   118: aload 7
    //   120: iconst_0
    //   121: invokevirtual 122	com/alipay/sdk/net/RequestWrapper:a	(Landroid/content/Context;Lcom/alipay/sdk/data/Request;Z)Lcom/alipay/sdk/protocol/FrameData;
    //   124: invokevirtual 127	com/alipay/sdk/protocol/FrameData:c	()Lorg/json/JSONObject;
    //   127: astore 7
    //   129: aload_1
    //   130: astore_2
    //   131: aload_1
    //   132: ifnull +22 -> 154
    //   135: aload_1
    //   136: astore_3
    //   137: aload_1
    //   138: astore 4
    //   140: aload_1
    //   141: astore 5
    //   143: aload_1
    //   144: astore 6
    //   146: aload_1
    //   147: astore_2
    //   148: aload_1
    //   149: invokevirtual 129	com/alipay/sdk/widget/Loading:c	()V
    //   152: aconst_null
    //   153: astore_2
    //   154: aload_2
    //   155: astore_3
    //   156: aload_2
    //   157: astore 4
    //   159: aload_2
    //   160: astore 5
    //   162: aload_2
    //   163: astore 6
    //   165: aload_0
    //   166: aload 7
    //   168: invokespecial 132	com/alipay/sdk/app/H5AuthActivity:a	(Lorg/json/JSONObject;)V
    //   171: return
    //   172: astore_1
    //   173: aconst_null
    //   174: astore_1
    //   175: goto -162 -> 13
    //   178: astore_1
    //   179: aload_3
    //   180: astore_2
    //   181: aload_0
    //   182: new 134	com/alipay/sdk/app/H5AuthActivity$2
    //   185: dup
    //   186: aload_0
    //   187: invokespecial 135	com/alipay/sdk/app/H5AuthActivity$2:<init>	(Lcom/alipay/sdk/app/H5AuthActivity;)V
    //   190: invokevirtual 139	com/alipay/sdk/app/H5AuthActivity:runOnUiThread	(Ljava/lang/Runnable;)V
    //   193: aload_3
    //   194: ifnull -23 -> 171
    //   197: aload_3
    //   198: invokevirtual 129	com/alipay/sdk/widget/Loading:c	()V
    //   201: return
    //   202: astore_1
    //   203: aload 4
    //   205: astore_2
    //   206: aload_0
    //   207: new 141	com/alipay/sdk/app/H5AuthActivity$3
    //   210: dup
    //   211: aload_0
    //   212: invokespecial 142	com/alipay/sdk/app/H5AuthActivity$3:<init>	(Lcom/alipay/sdk/app/H5AuthActivity;)V
    //   215: invokevirtual 139	com/alipay/sdk/app/H5AuthActivity:runOnUiThread	(Ljava/lang/Runnable;)V
    //   218: aload 4
    //   220: ifnull -49 -> 171
    //   223: aload 4
    //   225: invokevirtual 129	com/alipay/sdk/widget/Loading:c	()V
    //   228: return
    //   229: astore_1
    //   230: aload 5
    //   232: astore_2
    //   233: aload_0
    //   234: new 144	com/alipay/sdk/app/H5AuthActivity$4
    //   237: dup
    //   238: aload_0
    //   239: invokespecial 145	com/alipay/sdk/app/H5AuthActivity$4:<init>	(Lcom/alipay/sdk/app/H5AuthActivity;)V
    //   242: invokevirtual 139	com/alipay/sdk/app/H5AuthActivity:runOnUiThread	(Ljava/lang/Runnable;)V
    //   245: aload 5
    //   247: ifnull -76 -> 171
    //   250: aload 5
    //   252: invokevirtual 129	com/alipay/sdk/widget/Loading:c	()V
    //   255: return
    //   256: astore_1
    //   257: aload 6
    //   259: astore_2
    //   260: aload_0
    //   261: new 147	com/alipay/sdk/app/H5AuthActivity$5
    //   264: dup
    //   265: aload_0
    //   266: invokespecial 148	com/alipay/sdk/app/H5AuthActivity$5:<init>	(Lcom/alipay/sdk/app/H5AuthActivity;)V
    //   269: invokevirtual 139	com/alipay/sdk/app/H5AuthActivity:runOnUiThread	(Ljava/lang/Runnable;)V
    //   272: aload 6
    //   274: ifnull -103 -> 171
    //   277: aload 6
    //   279: invokevirtual 129	com/alipay/sdk/widget/Loading:c	()V
    //   282: return
    //   283: astore_1
    //   284: aload_2
    //   285: ifnull +7 -> 292
    //   288: aload_2
    //   289: invokevirtual 129	com/alipay/sdk/widget/Loading:c	()V
    //   292: aload_1
    //   293: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   0	13	172	java/lang/Exception
    //   115	129	178	com/alipay/sdk/exception/NetErrorException
    //   148	152	178	com/alipay/sdk/exception/NetErrorException
    //   165	171	178	com/alipay/sdk/exception/NetErrorException
    //   115	129	202	com/alipay/sdk/exception/FailOperatingException
    //   148	152	202	com/alipay/sdk/exception/FailOperatingException
    //   165	171	202	com/alipay/sdk/exception/FailOperatingException
    //   115	129	229	com/alipay/sdk/exception/AppErrorException
    //   148	152	229	com/alipay/sdk/exception/AppErrorException
    //   165	171	229	com/alipay/sdk/exception/AppErrorException
    //   115	129	256	com/alipay/sdk/exception/UnZipException
    //   148	152	256	com/alipay/sdk/exception/UnZipException
    //   165	171	256	com/alipay/sdk/exception/UnZipException
    //   115	129	283	finally
    //   148	152	283	finally
    //   165	171	283	finally
    //   181	193	283	finally
    //   206	218	283	finally
    //   233	245	283	finally
    //   260	272	283	finally
  }

  private void b(String paramString)
  {
    new JsBridge(this, new H5AuthActivity.13(this)).a(paramString);
  }

  private void c()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage("不能连接到服务器，是否重试？");
    localBuilder.setPositiveButton("确定", new H5AuthActivity.10(this));
    localBuilder.setNeutralButton("取消", new H5AuthActivity.11(this));
    localBuilder.create().show();
  }

  private void d()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage("系统繁忙，请稍后再试");
    localBuilder.setNeutralButton("确定", new H5AuthActivity.12(this));
    localBuilder.create().show();
  }

  private void e()
  {
    if (this.b == null)
      this.b = new Loading(this);
    this.b.b();
  }

  private void f()
  {
    if ((this.b != null) && (this.b.a()))
      this.b.c();
    this.b = null;
  }

  public void finish()
  {
    synchronized (AuthHelper.a)
    {
      try
      {
        ???.notify();
        super.finish();
        return;
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }
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
    new Thread(new H5AuthActivity.1(this)).start();
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.app.H5AuthActivity
 * JD-Core Version:    0.6.2
 */