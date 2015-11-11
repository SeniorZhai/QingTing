package cn.wemart.sdk.app.bridge;

import android.webkit.WebView;

public class JSBridgeUtil
{
  static final String CALLBACK_ID_FORMAT = "JAVA_CB_%s";
  static final String EMPTY_STR = "";
  public static final String JAVASCRIPT_STR = "javascript:";
  static final String JS_FETCH_QUEUE_FROM_JAVA = "javascript:WemartJSBridge._fetchJSQueue();";
  static final String JS_HANDLE_MESSAGE_FROM_JAVA = "javascript:WemartJSBridge._handleMessageFromNative('%s');";
  static final String SPLIT_MARK = "/";
  static final String UNDERLINE_STR = "_";
  static final String WT_OVERRIDE_SCHEMA = "wtjs://";
  static final String WT_RETURN_DATA = "wtjs://return/";

  // ERROR //
  public static String assetFile2Str(android.content.Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore_2
    //   4: aload_0
    //   5: invokevirtual 49	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   8: aload_1
    //   9: invokevirtual 55	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   12: astore_0
    //   13: aload_0
    //   14: astore_2
    //   15: aload_0
    //   16: astore_3
    //   17: aload_0
    //   18: invokevirtual 61	java/io/InputStream:available	()I
    //   21: newarray byte
    //   23: astore_1
    //   24: aload_0
    //   25: astore_2
    //   26: aload_0
    //   27: astore_3
    //   28: aload_0
    //   29: aload_1
    //   30: invokevirtual 65	java/io/InputStream:read	([B)I
    //   33: pop
    //   34: aload_0
    //   35: astore_2
    //   36: aload_0
    //   37: astore_3
    //   38: aload_0
    //   39: invokevirtual 68	java/io/InputStream:close	()V
    //   42: aload_0
    //   43: astore_2
    //   44: aload_0
    //   45: astore_3
    //   46: new 70	java/lang/String
    //   49: dup
    //   50: aload_1
    //   51: invokespecial 73	java/lang/String:<init>	([B)V
    //   54: astore_1
    //   55: aload_0
    //   56: ifnull +7 -> 63
    //   59: aload_0
    //   60: invokevirtual 68	java/io/InputStream:close	()V
    //   63: aload_1
    //   64: areturn
    //   65: astore_0
    //   66: aload_2
    //   67: astore_3
    //   68: aload_0
    //   69: invokevirtual 76	java/lang/Exception:printStackTrace	()V
    //   72: aload_2
    //   73: ifnull +7 -> 80
    //   76: aload_2
    //   77: invokevirtual 68	java/io/InputStream:close	()V
    //   80: aconst_null
    //   81: areturn
    //   82: astore_0
    //   83: aload_3
    //   84: ifnull +7 -> 91
    //   87: aload_3
    //   88: invokevirtual 68	java/io/InputStream:close	()V
    //   91: aload_0
    //   92: athrow
    //   93: astore_0
    //   94: aload_1
    //   95: areturn
    //   96: astore_0
    //   97: goto -17 -> 80
    //   100: astore_1
    //   101: goto -10 -> 91
    //
    // Exception table:
    //   from	to	target	type
    //   4	13	65	java/lang/Exception
    //   17	24	65	java/lang/Exception
    //   28	34	65	java/lang/Exception
    //   38	42	65	java/lang/Exception
    //   46	55	65	java/lang/Exception
    //   4	13	82	finally
    //   17	24	82	finally
    //   28	34	82	finally
    //   38	42	82	finally
    //   46	55	82	finally
    //   68	72	82	finally
    //   59	63	93	java/io/IOException
    //   76	80	96	java/io/IOException
    //   87	91	100	java/io/IOException
  }

  public static String getDataFromReturnUrl(String paramString)
  {
    paramString = paramString.replace("wtjs://return/", "").split("/", 2);
    if ((paramString != null) && (paramString.length >= 2))
      return paramString[1];
    return null;
  }

  public static String getFunctionFromReturnUrl(String paramString)
  {
    paramString = paramString.replace("wtjs://return/", "").split("/");
    if ((paramString != null) && (paramString.length >= 1))
      return paramString[0];
    return null;
  }

  public static String parseFunctionName(String paramString)
  {
    return paramString.replace("javascript:WemartJSBridge.", "").replaceAll("\\(.*\\);", "");
  }

  public static void webViewLoadJs(WebView paramWebView, String paramString)
  {
    paramString = new StringBuilder(String.valueOf("var newscript = document.createElement(\"script\");")).append("newscript.src=\"").append(paramString).append("\";").toString() + "document.scripts[0].parentNode.insertBefore(newscript,document.scripts[0]);";
    paramWebView.loadUrl("javascript:" + paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.wemart.sdk.app.bridge.JSBridgeUtil
 * JD-Core Version:    0.6.2
 */