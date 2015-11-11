package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.tencent.b.a.g;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.AsynLoadImg;
import com.tencent.utils.AsynLoadImgBack;
import com.tencent.utils.SystemUtils;
import com.tencent.utils.TemporaryStorage;
import com.tencent.utils.Util;
import java.io.File;
import java.util.ArrayList;

public class QQShare extends BaseApi
{
  public static final String SHARE_TO_QQ_APP_NAME = "appName";
  public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
  public static final String SHARE_TO_QQ_EXT_INT = "cflag";
  public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
  public static final int SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN = 1;
  public static final int SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE = 2;
  public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
  public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
  public static final String SHARE_TO_QQ_KEY_TYPE = "req_type";
  public static final String SHARE_TO_QQ_SITE = "site";
  public static final String SHARE_TO_QQ_SUMMARY = "summary";
  public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
  public static final String SHARE_TO_QQ_TITLE = "title";
  public static final int SHARE_TO_QQ_TYPE_AUDIO = 2;
  public static final int SHARE_TO_QQ_TYPE_DEFAULT = 1;
  public static final int SHARE_TO_QQ_TYPE_IMAGE = 5;

  public QQShare(Context paramContext, QQToken paramQQToken)
  {
    super(paramContext, paramQQToken);
  }

  private StringBuffer a(StringBuffer paramStringBuffer, Bundle paramBundle)
  {
    g.a("openSDK_LOG", "fillShareToQQParams() --start");
    paramBundle.putString("action", "shareToQQ");
    paramBundle.putString("appId", this.mToken.getAppId());
    paramBundle.putString("sdkp", "a");
    paramBundle.putString("sdkv", "2.2.1");
    paramBundle.putString("status_os", Build.VERSION.RELEASE);
    paramBundle.putString("status_machine", Build.MODEL);
    if ((paramBundle.containsKey("content")) && (paramBundle.getString("content").length() > 40))
      paramBundle.putString("content", paramBundle.getString("content").substring(0, 40) + "...");
    if ((paramBundle.containsKey("summary")) && (paramBundle.getString("summary").length() > 80))
      paramBundle.putString("summary", paramBundle.getString("summary").substring(0, 80) + "...");
    paramBundle = Util.encodeUrl(paramBundle);
    paramStringBuffer.append("&" + paramBundle.replaceAll("\\+", "%20"));
    g.a("openSDK_LOG", "fillShareToQQParams() --end");
    return paramStringBuffer;
  }

  private void a(final Activity paramActivity, final Bundle paramBundle, final IUiListener paramIUiListener)
  {
    g.a("openSDK_LOG", "shareToMobileQQ() --start");
    String str1 = paramBundle.getString("imageUrl");
    final String str2 = paramBundle.getString("title");
    final String str3 = paramBundle.getString("summary");
    if (!TextUtils.isEmpty(str1))
      if (Util.isValidUrl(str1))
      {
        if ((TextUtils.isEmpty(str2)) && (TextUtils.isEmpty(str3)) && (!Util.hasSDCard()))
        {
          if (paramIUiListener != null)
          {
            paramIUiListener.onError(new UiError(-6, "分享图片失败，检测不到SD卡!", null));
            Log.v("shareToQQ", "分享图片失败，检测不到SD卡!");
          }
          return;
        }
        if (SystemUtils.compareQQVersion(paramActivity, "4.3.0") >= 0)
          b(paramActivity, paramBundle, paramIUiListener);
      }
    while (true)
    {
      g.a("openSDK_LOG", "shareToMobileQQ() --start");
      return;
      new AsynLoadImg(paramActivity).save(str1, new AsynLoadImgBack()
      {
        public void batchSaved(int paramAnonymousInt, ArrayList<String> paramAnonymousArrayList)
        {
        }

        public void saved(int paramAnonymousInt, String paramAnonymousString)
        {
          if (paramAnonymousInt == 0)
            paramBundle.putString("imageLocalUrl", paramAnonymousString);
          do
            do
            {
              QQShare.a(QQShare.this, paramActivity, paramBundle, paramIUiListener);
              return;
            }
            while ((!TextUtils.isEmpty(str2)) || (!TextUtils.isEmpty(str3)));
          while (paramIUiListener == null);
          paramIUiListener.onError(new UiError(-6, "获取分享图片失败!", null));
          g.a("shareToQQ", "获取分享图片失败!");
        }
      });
      continue;
      paramBundle.putString("imageUrl", null);
      if (SystemUtils.compareQQVersion(paramActivity, "4.3.0") < 0)
      {
        b(paramActivity, paramBundle, paramIUiListener);
      }
      else
      {
        a.a(paramActivity, str1, new AsynLoadImgBack()
        {
          public void batchSaved(int paramAnonymousInt, ArrayList<String> paramAnonymousArrayList)
          {
          }

          public void saved(int paramAnonymousInt, String paramAnonymousString)
          {
            if (paramAnonymousInt == 0)
              paramBundle.putString("imageLocalUrl", paramAnonymousString);
            do
              do
              {
                QQShare.a(QQShare.this, paramActivity, paramBundle, paramIUiListener);
                return;
              }
              while ((!TextUtils.isEmpty(str2)) || (!TextUtils.isEmpty(str3)));
            while (paramIUiListener == null);
            paramIUiListener.onError(new UiError(-6, "获取分享图片失败!", null));
            g.a("shareToQQ", "获取分享图片失败!");
          }
        });
        continue;
        b(paramActivity, paramBundle, paramIUiListener);
      }
    }
  }

  private void b(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    g.a("openSDK_LOG", "doShareToQQ() --start");
    StringBuffer localStringBuffer = new StringBuffer("mqqapi://share/to_fri?src_type=app&version=1&file_type=news");
    String str5 = paramBundle.getString("imageUrl");
    String str6 = paramBundle.getString("title");
    String str7 = paramBundle.getString("summary");
    String str8 = paramBundle.getString("targetUrl");
    String str3 = paramBundle.getString("audio_url");
    int i = paramBundle.getInt("req_type", 1);
    int j = paramBundle.getInt("cflag", 0);
    String str4 = paramBundle.getString("share_qq_ext_str");
    String str2 = Util.getApplicationLable(paramActivity);
    String str1 = str2;
    if (str2 == null)
      str1 = paramBundle.getString("appName");
    paramBundle = paramBundle.getString("imageLocalUrl");
    String str9 = this.mToken.getAppId();
    str2 = this.mToken.getOpenId();
    Log.v("shareToQQ", "openId:" + str2);
    if (!TextUtils.isEmpty(str5))
      localStringBuffer.append("&image_url=" + Base64.encodeToString(str5.getBytes(), 2));
    if (!TextUtils.isEmpty(paramBundle))
      localStringBuffer.append("&file_data=" + Base64.encodeToString(paramBundle.getBytes(), 2));
    if (!TextUtils.isEmpty(str6))
      localStringBuffer.append("&title=" + Base64.encodeToString(str6.getBytes(), 2));
    if (!TextUtils.isEmpty(str7))
      localStringBuffer.append("&description=" + Base64.encodeToString(str7.getBytes(), 2));
    if (!TextUtils.isEmpty(str9))
      localStringBuffer.append("&share_id=" + str9);
    if (!TextUtils.isEmpty(str8))
      localStringBuffer.append("&url=" + Base64.encodeToString(str8.getBytes(), 2));
    if (!TextUtils.isEmpty(str1))
    {
      paramBundle = str1;
      if (str1.length() > 20)
        paramBundle = str1.substring(0, 20) + "...";
      localStringBuffer.append("&app_name=" + Base64.encodeToString(paramBundle.getBytes(), 2));
    }
    if (!TextUtils.isEmpty(str2))
      localStringBuffer.append("&open_id=" + Base64.encodeToString(str2.getBytes(), 2));
    if (!TextUtils.isEmpty(str3))
      localStringBuffer.append("&audioUrl=" + Base64.encodeToString(str3.getBytes(), 2));
    localStringBuffer.append("&req_type=" + Base64.encodeToString(String.valueOf(i).getBytes(), 2));
    if (!TextUtils.isEmpty(str4))
      localStringBuffer.append("&share_qq_ext_str=" + Base64.encodeToString(str4.getBytes(), 2));
    localStringBuffer.append("&cflag=" + Base64.encodeToString(String.valueOf(j).getBytes(), 2));
    Log.v("shareToQQ", localStringBuffer.toString());
    com.tencent.connect.a.a.a(this.mContext, this.mToken, "requireApi", new String[] { "shareToNativeQQ" });
    this.mActivityIntent = new Intent("android.intent.action.VIEW");
    this.mActivityIntent.setData(Uri.parse(localStringBuffer.toString()));
    if (SystemUtils.compareQQVersion(paramActivity, "4.6.0") < 0)
      if (hasActivityForIntent())
        startAssitActivity(paramActivity, paramIUiListener);
    while (true)
    {
      g.a("openSDK_LOG", "doShareToQQ() --end");
      return;
      paramBundle = TemporaryStorage.set("shareToQQ", paramIUiListener);
      if (paramBundle != null)
        ((IUiListener)paramBundle).onCancel();
      if (hasActivityForIntent())
        paramActivity.startActivityForResult(this.mActivityIntent, 0);
    }
  }

  private void c(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    Object localObject = TemporaryStorage.set("shareToQQ", paramIUiListener);
    if (localObject != null)
      ((IUiListener)localObject).onCancel();
    g.a("openSDK_LOG", "shareToH5QQ() --start");
    StringBuffer localStringBuffer = new StringBuffer("http://openmobile.qq.com/api/check?page=shareindex.html&style=9");
    localObject = paramBundle;
    if (paramBundle == null)
      localObject = new Bundle();
    paramBundle = a(localStringBuffer, (Bundle)localObject);
    com.tencent.connect.a.a.a(this.mContext, this.mToken, "requireApi", new String[] { "shareToH5QQ" });
    if ((!Util.openBrowser(paramActivity, paramBundle.toString())) && (paramIUiListener != null))
      paramIUiListener.onError(new UiError(-6, "打开浏览器失败!", null));
    g.a("openSDK_LOG", "shareToH5QQ() --end");
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
  }

  public void shareToQQ(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    String str1 = paramBundle.getString("imageUrl");
    String str2 = paramBundle.getString("title");
    String str3 = paramBundle.getString("summary");
    String str4 = paramBundle.getString("targetUrl");
    String str5 = paramBundle.getString("imageLocalUrl");
    int i = paramBundle.getInt("req_type", 1);
    if (!Util.hasSDCard())
    {
      paramIUiListener.onError(new UiError(-6, "分享图片失败，检测不到SD卡!", null));
      g.a("openSDK_LOG", "shareToQQ() sdcard is null--end");
      return;
    }
    if (i == 5)
    {
      if (SystemUtils.compareQQVersion(paramActivity, "4.3.0") < 0)
      {
        paramIUiListener.onError(new UiError(-6, "低版本手Q不支持该项功能!", null));
        g.a("openSDK_LOG", "shareToQQ() both null--end");
        return;
      }
      if (!Util.fileExists(str5))
      {
        paramIUiListener.onError(new UiError(-6, "非法的图片地址!", null));
        g.a("openSDK_LOG", "shareToQQ()--end非法的图片地址!");
        return;
      }
    }
    if (i != 5)
    {
      if ((TextUtils.isEmpty(str4)) || ((!str4.startsWith("http://")) && (!str4.startsWith("https://"))))
      {
        paramIUiListener.onError(new UiError(-6, "传入参数有误!", null));
        g.a("openSDK_LOG", "shareToQQ() targetUrl error--end");
        return;
      }
      if (TextUtils.isEmpty(str2))
      {
        paramIUiListener.onError(new UiError(-6, "title不能为空!", null));
        g.a("openSDK_LOG", "shareToQQ() title null--end");
        return;
      }
    }
    if ((!TextUtils.isEmpty(str1)) && (!str1.startsWith("http://")) && (!str1.startsWith("https://")) && (!new File(str1).exists()))
    {
      paramIUiListener.onError(new UiError(-6, "非法的图片地址!", null));
      g.a("openSDK_LOG", "shareToQQ() image url error--end");
      return;
    }
    if ((!TextUtils.isEmpty(str2)) && (str2.length() > 45))
      paramBundle.putString("title", Util.subString(str2, 45, null, null));
    if ((!TextUtils.isEmpty(str3)) && (str3.length() > 60))
      paramBundle.putString("summary", Util.subString(str3, 60, null, null));
    if (Util.isMobileQQSupportShare(paramActivity))
      a(paramActivity, paramBundle, paramIUiListener);
    while (true)
    {
      g.a("openSDK_LOG", "shareToQQ() --end");
      return;
      c(paramActivity, paramBundle, paramIUiListener);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.connect.share.QQShare
 * JD-Core Version:    0.6.2
 */