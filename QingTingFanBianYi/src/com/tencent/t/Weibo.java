package com.tencent.t;

import android.content.Context;
import android.os.Bundle;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.utils.HttpUtils;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Weibo extends BaseApi
{
  public Weibo(Context paramContext, QQAuth paramQQAuth, QQToken paramQQToken)
  {
    super(paramContext, paramQQAuth, paramQQToken);
  }

  public Weibo(Context paramContext, QQToken paramQQToken)
  {
    super(paramContext, paramQQToken);
  }

  public void atFriends(int paramInt, IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    localBundle.putString("reqnum", paramInt + "");
    paramIUiListener = new BaseApi.TempRequestListener(this, paramIUiListener);
    HttpUtils.requestAsync(this.mToken, this.mContext, "friends/get_intimate_friends_weibo", localBundle, "GET", paramIUiListener);
  }

  public void deleteText(String paramString, IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    localBundle.putString("id", paramString);
    paramString = new BaseApi.TempRequestListener(this, paramIUiListener);
    HttpUtils.requestAsync(this.mToken, this.mContext, "t/del_t", localBundle, "POST", paramString);
  }

  public void getWeiboInfo(IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    paramIUiListener = new BaseApi.TempRequestListener(this, paramIUiListener);
    HttpUtils.requestAsync(this.mToken, this.mContext, "user/get_info", localBundle, "GET", paramIUiListener);
  }

  public void nickTips(String paramString, int paramInt, IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    String str = paramString;
    if (paramString == null)
      str = "";
    localBundle.putString("match", str);
    localBundle.putString("reqnum", paramInt + "");
    paramString = new BaseApi.TempRequestListener(this, paramIUiListener);
    HttpUtils.requestAsync(this.mToken, this.mContext, "friends/match_nick_tips_weibo", localBundle, "GET", paramString);
  }

  public void sendPicText(String paramString1, String paramString2, IUiListener paramIUiListener)
  {
    paramIUiListener = new BaseApi.TempRequestListener(this, paramIUiListener);
    try
    {
      paramString2 = new FileInputStream(paramString2);
      localObject1 = new ByteArrayOutputStream();
      localObject2 = new byte[1024];
      while (true)
      {
        int i = paramString2.read((byte[])localObject2);
        if (i == -1)
          break;
        ((ByteArrayOutputStream)localObject1).write((byte[])localObject2, 0, i);
      }
    }
    catch (IOException paramString1)
    {
      paramIUiListener.onIOException(paramString1);
      return;
    }
    ((ByteArrayOutputStream)localObject1).close();
    paramString2.close();
    Object localObject1 = ((ByteArrayOutputStream)localObject1).toByteArray();
    Object localObject2 = composeCGIParams();
    paramString2 = paramString1;
    if (paramString1 == null)
      paramString2 = "";
    ((Bundle)localObject2).putString("content", paramString2);
    ((Bundle)localObject2).putByteArray("pic", (byte[])localObject1);
    HttpUtils.requestAsync(this.mToken, this.mContext, "t/add_pic_t", (Bundle)localObject2, "POST", paramIUiListener);
  }

  public void sendText(String paramString, IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    String str = paramString;
    if (paramString == null)
      str = "";
    localBundle.putString("content", str);
    paramString = new BaseApi.TempRequestListener(this, paramIUiListener);
    HttpUtils.requestAsync(this.mToken, this.mContext, "t/add_t", localBundle, "POST", paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.t.Weibo
 * JD-Core Version:    0.6.2
 */