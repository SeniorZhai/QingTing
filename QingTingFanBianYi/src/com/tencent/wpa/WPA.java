package com.tencent.wpa;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.HttpUtils;
import java.util.List;

public class WPA extends BaseApi
{
  public WPA(Context paramContext, QQAuth paramQQAuth, QQToken paramQQToken)
  {
    super(paramContext, paramQQAuth, paramQQToken);
  }

  public WPA(Context paramContext, QQToken paramQQToken)
  {
    super(paramContext, paramQQToken);
  }

  public void getWPAUserOnlineState(String paramString, IUiListener paramIUiListener)
  {
    if (paramString == null)
      try
      {
        throw new Exception("uin null");
      }
      catch (Exception paramString)
      {
        if (paramIUiListener != null)
          paramIUiListener.onError(new UiError(-5, "传入参数有误!", null));
        return;
      }
    if (paramString.length() < 5)
      throw new Exception("uin length < 5");
    while (true)
    {
      int i;
      if (i < paramString.length())
      {
        if (!Character.isDigit(paramString.charAt(i)))
          throw new Exception("uin not digit");
        i += 1;
      }
      else
      {
        paramString = "http://webpresence.qq.com/getonline?Type=1&" + paramString + ":";
        paramIUiListener = new BaseApi.TempRequestListener(this, paramIUiListener);
        HttpUtils.requestAsync(this.mToken, this.mContext, paramString, null, "GET", paramIUiListener);
        return;
        i = 0;
      }
    }
  }

  public int startWPAConversation(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    if (TextUtils.isEmpty(paramString1))
      return -1;
    if (paramString1.length() < 5)
      return -3;
    int i = 0;
    while (i < paramString1.length())
    {
      if (!Character.isDigit(paramString1.charAt(i)))
        return -4;
      i += 1;
    }
    String str = "";
    if (!TextUtils.isEmpty(paramString2))
      str = Base64.encodeToString(paramString2.getBytes(), 2);
    localIntent.setData(Uri.parse(String.format("mqqwpa://im/chat?chat_type=wpa&uin=%1$s&version=1&src_type=app&attach_content=%2$s", new Object[] { paramString1, str })));
    paramString1 = this.mContext.getPackageManager();
    if (paramString1.queryIntentActivities(localIntent, 65536).size() > 0)
    {
      this.mContext.startActivity(localIntent);
      return 0;
    }
    localIntent.setData(Uri.parse("http://www.myapp.com/forward/a/45592?g_f=990935"));
    if (paramString1.queryIntentActivities(localIntent, 65536).size() > 0)
    {
      this.mContext.startActivity(localIntent);
      return 0;
    }
    return -2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.wpa.WPA
 * JD-Core Version:    0.6.2
 */