package com.taobao.munion.view.webview.windvane;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.taobao.munion.base.caches.u;
import com.taobao.munion.base.caches.w;
import com.taobao.newxp.common.a.a;
import com.taobao.newxp.common.a.a.f.a;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class m extends com.taobao.munion.view.webview.b
{
  public static final int c = 0;
  public static final int d = 1;
  public static final String e = "mmusdk_cache";
  public static final String f = "1";
  public static boolean g = true;
  private int a = 0;
  protected String b = null;

  public m()
  {
  }

  public m(int paramInt)
  {
    this.a = paramInt;
  }

  // ERROR //
  private w a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: invokestatic 41	com/taobao/munion/base/caches/b:a	()Lcom/taobao/munion/base/caches/b;
    //   3: aload_1
    //   4: invokevirtual 44	com/taobao/munion/base/caches/b:c	(Ljava/lang/String;)Lcom/taobao/munion/base/caches/w;
    //   7: astore_3
    //   8: aload_3
    //   9: ifnull +10 -> 19
    //   12: aload_3
    //   13: invokevirtual 50	com/taobao/munion/base/caches/w:j	()Z
    //   16: ifeq +97 -> 113
    //   19: aload_1
    //   20: invokestatic 56	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   23: ldc 13
    //   25: invokevirtual 60	android/net/Uri:getQueryParameter	(Ljava/lang/String;)Ljava/lang/String;
    //   28: astore 4
    //   30: aload_1
    //   31: ldc 62
    //   33: ldc 64
    //   35: invokevirtual 70	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   38: ldc 72
    //   40: ldc 64
    //   42: invokevirtual 70	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   45: ldc 74
    //   47: ldc 64
    //   49: invokevirtual 70	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   52: ldc 76
    //   54: ldc 64
    //   56: invokevirtual 70	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   59: astore_1
    //   60: aload 4
    //   62: invokestatic 82	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   65: ifne +22 -> 87
    //   68: ldc 16
    //   70: aload 4
    //   72: invokevirtual 86	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   75: ifeq +12 -> 87
    //   78: invokestatic 41	com/taobao/munion/base/caches/b:a	()Lcom/taobao/munion/base/caches/b;
    //   81: aload_1
    //   82: aload_2
    //   83: invokevirtual 88	com/taobao/munion/base/caches/b:a	(Ljava/lang/String;Ljava/lang/String;)Lcom/taobao/munion/base/caches/w;
    //   86: areturn
    //   87: aload_3
    //   88: ifnull +10 -> 98
    //   91: invokestatic 41	com/taobao/munion/base/caches/b:a	()Lcom/taobao/munion/base/caches/b;
    //   94: aload_1
    //   95: invokevirtual 91	com/taobao/munion/base/caches/b:d	(Ljava/lang/String;)V
    //   98: aconst_null
    //   99: areturn
    //   100: astore_1
    //   101: aconst_null
    //   102: astore_3
    //   103: aload_1
    //   104: invokevirtual 94	java/lang/Exception:printStackTrace	()V
    //   107: aload_3
    //   108: areturn
    //   109: astore_1
    //   110: goto -7 -> 103
    //   113: aload_3
    //   114: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	100	java/lang/Exception
    //   12	19	109	java/lang/Exception
    //   19	87	109	java/lang/Exception
    //   91	98	109	java/lang/Exception
  }

  public static String a(InputStream paramInputStream)
  {
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
    paramInputStream = new StringBuilder();
    try
    {
      while (true)
      {
        String str = localBufferedReader.readLine();
        if (str == null)
          break;
        paramInputStream.append(str + "\n");
      }
    }
    catch (IOException localIOException)
    {
      localIOException = localIOException;
      localIOException.printStackTrace();
      return paramInputStream.toString();
    }
    finally
    {
    }
    throw paramInputStream;
  }

  private w b(String paramString1, String paramString2)
  {
    w localw2 = com.taobao.munion.base.caches.b.a().c(paramString1);
    w localw1 = localw2;
    if (localw2 == null)
    {
      com.taobao.munion.base.caches.b.a().a(paramString1, localw2, paramString2);
      localw1 = null;
    }
    return localw1;
  }

  public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    super.onPageStarted(paramWebView, paramString, paramBitmap);
    this.b = paramString;
  }

  public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString)
  {
    if (!TextUtils.isEmpty(this.b))
      this.b = this.b.replace("&mmusdkwakeup=1", "").replace("mmusdkwakeup=1", "");
    Object localObject;
    if ((!TextUtils.isEmpty(this.b)) && (this.b.equals(com.taobao.munion.base.caches.b.d)) && (u.b(paramString)) && (g))
    {
      localObject = new f.a(2, System.currentTimeMillis());
      a.a().a((f.a)localObject);
      g = false;
    }
    if (1 == this.a)
    {
      if (com.taobao.munion.base.caches.b.g)
      {
        localObject = a(paramString, this.b);
        if (localObject != null)
          return new WebResourceResponse(((w)localObject).d(), ((w)localObject).c(), ((w)localObject).e);
      }
      return super.shouldInterceptRequest(paramWebView, paramString);
    }
    if (com.taobao.munion.base.caches.b.a().b(paramString))
    {
      localObject = b(paramString, this.b);
      if (localObject != null)
        return new WebResourceResponse(((w)localObject).d(), ((w)localObject).c(), ((w)localObject).e);
    }
    return super.shouldInterceptRequest(paramWebView, paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.view.webview.windvane.m
 * JD-Core Version:    0.6.2
 */