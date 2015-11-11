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
import com.tencent.utils.AsynLoadImgBack;
import com.tencent.utils.SystemUtils;
import com.tencent.utils.TemporaryStorage;
import com.tencent.utils.Util;
import java.net.URLEncoder;
import java.util.ArrayList;

public class QzoneShare extends BaseApi
{
  public static final String SHARE_TO_QQ_APP_NAME = "appName";
  public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
  public static final String SHARE_TO_QQ_EXT_INT = "cflag";
  public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
  public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
  public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
  public static final String SHARE_TO_QQ_SITE = "site";
  public static final String SHARE_TO_QQ_SUMMARY = "summary";
  public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
  public static final String SHARE_TO_QQ_TITLE = "title";
  public static final String SHARE_TO_QZONE_KEY_TYPE = "req_type";
  public static final int SHARE_TO_QZONE_TYPE_IMAGE = 5;
  public static final int SHARE_TO_QZONE_TYPE_IMAGE_TEXT = 1;
  public static final int SHARE_TO_QZONE_TYPE_NO_TYPE = 0;
  private boolean a = true;
  private boolean b = false;
  private boolean c = false;
  private boolean d = false;

  public QzoneShare(Context paramContext, QQToken paramQQToken)
  {
    super(paramContext, paramQQToken);
  }

  private StringBuffer a(StringBuffer paramStringBuffer, Bundle paramBundle)
  {
    g.a("openSDK_LOG", "fillShareToQQParams() --start");
    ArrayList localArrayList = paramBundle.getStringArrayList("imageUrl");
    Object localObject = paramBundle.getString("appName");
    int j = paramBundle.getInt("req_type", 1);
    String str1 = paramBundle.getString("title");
    String str2 = paramBundle.getString("summary");
    paramBundle.putString("appId", this.mToken.getAppId());
    paramBundle.putString("sdkp", "a");
    paramBundle.putString("sdkv", "2.2.1");
    paramBundle.putString("status_os", Build.VERSION.RELEASE);
    paramBundle.putString("status_machine", Build.MODEL);
    if ((!Util.isEmpty(str1)) && (str1.length() > 40))
      paramBundle.putString("title", str1.substring(0, 40) + "...");
    if ((!Util.isEmpty(str2)) && (str2.length() > 80))
      paramBundle.putString("summary", str2.substring(0, 80) + "...");
    if (!TextUtils.isEmpty((CharSequence)localObject))
      paramBundle.putString("site", (String)localObject);
    if (localArrayList != null)
    {
      int k = localArrayList.size();
      localObject = new String[k];
      int i = 0;
      while (i < k)
      {
        localObject[i] = ((String)localArrayList.get(i));
        i += 1;
      }
      paramBundle.putStringArray("imageUrl", (String[])localObject);
    }
    paramBundle.putString("type", String.valueOf(j));
    paramBundle = Util.encodeUrl(paramBundle);
    paramStringBuffer.append("&" + paramBundle.replaceAll("\\+", "%20"));
    g.a("openSDK_LOG", "fillShareToQQParams() --end");
    return paramStringBuffer;
  }

  private void a(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    g.a("openSDK_LOG", "doShareToQQ() --start");
    StringBuffer localStringBuffer1 = new StringBuffer("mqqapi://share/to_qzone?src_type=app&version=1&file_type=news");
    ArrayList localArrayList = paramBundle.getStringArrayList("imageUrl");
    String str1 = paramBundle.getString("title");
    String str2 = paramBundle.getString("summary");
    String str3 = paramBundle.getString("targetUrl");
    String str4 = paramBundle.getString("audio_url");
    int k = paramBundle.getInt("req_type", 1);
    String str5 = paramBundle.getString("appName");
    int m = paramBundle.getInt("cflag", 0);
    paramBundle = paramBundle.getString("share_qq_ext_str");
    String str6 = this.mToken.getAppId();
    String str7 = this.mToken.getOpenId();
    Log.v("shareToQQ", "openId:" + str7);
    if (localArrayList != null)
    {
      StringBuffer localStringBuffer2 = new StringBuffer();
      if (localArrayList.size() > 9);
      for (int i = 9; ; i = localArrayList.size())
      {
        int j = 0;
        while (j < i)
        {
          localStringBuffer2.append(URLEncoder.encode((String)localArrayList.get(j)));
          if (j != i - 1)
            localStringBuffer2.append(";");
          j += 1;
        }
      }
      localStringBuffer1.append("&image_url=" + Base64.encodeToString(localStringBuffer2.toString().getBytes(), 2));
    }
    if (!TextUtils.isEmpty(str1))
      localStringBuffer1.append("&title=" + Base64.encodeToString(str1.getBytes(), 2));
    if (!TextUtils.isEmpty(str2))
      localStringBuffer1.append("&description=" + Base64.encodeToString(str2.getBytes(), 2));
    if (!TextUtils.isEmpty(str6))
      localStringBuffer1.append("&share_id=" + str6);
    if (!TextUtils.isEmpty(str3))
      localStringBuffer1.append("&url=" + Base64.encodeToString(str3.getBytes(), 2));
    if (!TextUtils.isEmpty(str5))
      localStringBuffer1.append("&app_name=" + Base64.encodeToString(str5.getBytes(), 2));
    if (!Util.isEmpty(str7))
      localStringBuffer1.append("&open_id=" + Base64.encodeToString(str7.getBytes(), 2));
    if (!Util.isEmpty(str4))
      localStringBuffer1.append("&audioUrl=" + Base64.encodeToString(str4.getBytes(), 2));
    localStringBuffer1.append("&req_type=" + Base64.encodeToString(String.valueOf(k).getBytes(), 2));
    if (!Util.isEmpty(paramBundle))
      localStringBuffer1.append("&share_qq_ext_str=" + Base64.encodeToString(paramBundle.getBytes(), 2));
    localStringBuffer1.append("&cflag=" + Base64.encodeToString(String.valueOf(m).getBytes(), 2));
    Log.v("shareToQQ", localStringBuffer1.toString());
    com.tencent.connect.a.a.a(this.mContext, this.mToken, "requireApi", new String[] { "shareToNativeQQ" });
    this.mActivityIntent = new Intent("android.intent.action.VIEW");
    this.mActivityIntent.setData(Uri.parse(localStringBuffer1.toString()));
    if (SystemUtils.compareQQVersion(paramActivity, "4.6.0") < 0)
      if (hasActivityForIntent())
        startAssitActivity(paramActivity, paramIUiListener);
    while (true)
    {
      g.a("openSDK_LOG", "doShareToQQ() --end");
      return;
      paramBundle = TemporaryStorage.set("shareToQzone", paramIUiListener);
      if (paramBundle != null)
        ((IUiListener)paramBundle).onCancel();
      if (hasActivityForIntent())
        paramActivity.startActivityForResult(this.mActivityIntent, 0);
    }
  }

  private void a(Context paramContext, Bundle paramBundle, IUiListener paramIUiListener)
  {
    Object localObject = TemporaryStorage.set("shareToQzone", paramIUiListener);
    if (localObject != null)
      ((IUiListener)localObject).onCancel();
    g.a("openSDK_LOG", "shareToH5Qzone() --start");
    StringBuffer localStringBuffer = new StringBuffer("http://openmobile.qq.com/api/check2?page=qzshare.html&loginpage=loginindex.html&logintype=qzone");
    localObject = paramBundle;
    if (paramBundle == null)
      localObject = new Bundle();
    paramBundle = a(localStringBuffer, (Bundle)localObject);
    com.tencent.connect.a.a.a(this.mContext, this.mToken, "requireApi", new String[] { "shareToH5QQ" });
    if ((!Util.openBrowser(paramContext, paramBundle.toString())) && (paramIUiListener != null))
      paramIUiListener.onError(new UiError(-6, "打开浏览器失败!", null));
    g.a("openSDK_LOG", "shareToH5QQ() --end");
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
  }

  public void shareToQzone(final Activity paramActivity, final Bundle paramBundle, final IUiListener paramIUiListener)
  {
    if (paramBundle == null)
    {
      paramIUiListener.onError(new UiError(-6, "传入参数不可以为空", null));
      return;
    }
    String str1 = paramBundle.getString("title");
    String str3 = paramBundle.getString("summary");
    String str4 = paramBundle.getString("targetUrl");
    ArrayList localArrayList = paramBundle.getStringArrayList("imageUrl");
    String str2 = Util.getApplicationLable(paramActivity);
    Object localObject;
    int j;
    if (str2 == null)
    {
      localObject = paramBundle.getString("appName");
      j = paramBundle.getInt("req_type");
      switch (j)
      {
      default:
        if ((Util.isEmpty(str1)) && (Util.isEmpty(str3)))
          if ((localArrayList != null) && (localArrayList.size() != 0))
            this.a = false;
        break;
      case 1:
      case 5:
      }
    }
    while (true)
    {
      this.b = false;
      this.c = true;
      for (this.d = false; ; this.d = false)
      {
        if (Util.hasSDCard())
          break label336;
        paramIUiListener.onError(new UiError(-6, "分享图片失败，检测不到SD卡!", null));
        g.a("openSDK_LOG", "shareToQzone() sdcard is null--end");
        return;
        localObject = str2;
        if (str2.length() <= 20)
          break;
        localObject = str2.substring(0, 20) + "...";
        break;
        this.a = true;
        this.b = false;
        this.c = true;
      }
      paramIUiListener.onError(new UiError(-5, "暂不支持纯图片分享到空间，建议使用图文分享", null));
      g.a("openSDK_LOG", "shareToQzone() error--end暂不支持纯图片分享到空间，建议使用图文分享");
      return;
      str1 = "来自" + (String)localObject + "的分享";
      this.a = true;
      continue;
      this.a = true;
    }
    label336: if (this.a)
    {
      if (TextUtils.isEmpty(str4))
      {
        paramIUiListener.onError(new UiError(-5, "targetUrl为必填项，请补充后分享", null));
        g.a("openSDK_LOG", "shareToQzone() targetUrl null error--end");
        return;
      }
      if (!Util.isValidUrl(str4))
      {
        paramIUiListener.onError(new UiError(-5, "targetUrl有误", null));
        g.a("openSDK_LOG", "shareToQzone() targetUrl error--end");
        return;
      }
    }
    if (this.b)
    {
      paramBundle.putString("title", "");
      paramBundle.putString("summary", "");
    }
    while (true)
    {
      if (!TextUtils.isEmpty((CharSequence)localObject))
        paramBundle.putString("appName", (String)localObject);
      if ((localArrayList != null) && ((localArrayList == null) || (localArrayList.size() != 0)))
        break;
      if (!this.d)
        break label727;
      paramIUiListener.onError(new UiError(-6, "纯图分享，imageUrl 不能为空", null));
      g.a("openSDK_LOG", "shareToQzone() imageUrl is null--end");
      return;
      if ((this.c) && (Util.isEmpty(str1)))
      {
        paramIUiListener.onError(new UiError(-6, "title不能为空!", null));
        g.a("openSDK_LOG", "shareToQzone() title is null--end");
        return;
      }
      if ((!Util.isEmpty(str1)) && (str1.length() > 200))
        paramBundle.putString("title", Util.subString(str1, 200, null, null));
      if ((!Util.isEmpty(str3)) && (str3.length() > 600))
        paramBundle.putString("summary", Util.subString(str3, 600, null, null));
    }
    int i = 0;
    while (i < localArrayList.size())
    {
      localObject = (String)localArrayList.get(i);
      if ((!Util.isValidUrl((String)localObject)) && (!Util.isValidPath((String)localObject)))
        localArrayList.remove(i);
      i += 1;
    }
    if (localArrayList.size() == 0)
    {
      paramIUiListener.onError(new UiError(-6, "非法的图片地址!", null));
      g.a("openSDK_LOG", "shareToQzone() MSG_PARAM_IMAGE_URL_FORMAT_ERROR--end");
      return;
    }
    paramBundle.putStringArrayList("imageUrl", localArrayList);
    label727: if (SystemUtils.compareQQVersion(paramActivity, "4.6.0") >= 0)
      a.a(paramActivity, localArrayList, new AsynLoadImgBack()
      {
        public void batchSaved(int paramAnonymousInt, ArrayList<String> paramAnonymousArrayList)
        {
          if (paramAnonymousInt == 0)
            paramBundle.putStringArrayList("imageUrl", paramAnonymousArrayList);
          QzoneShare.a(QzoneShare.this, paramActivity, paramBundle, paramIUiListener);
        }

        public void saved(int paramAnonymousInt, String paramAnonymousString)
        {
        }
      });
    while (true)
    {
      g.a("openSDK_LOG", "shareToQzone() --end");
      return;
      if ((SystemUtils.compareQQVersion(paramActivity, "4.2.0") >= 0) && (SystemUtils.compareQQVersion(paramActivity, "4.6.0") < 0))
      {
        localObject = new QQShare(paramActivity, this.mToken);
        if ((localArrayList != null) && (localArrayList.size() > 0))
        {
          str1 = (String)localArrayList.get(0);
          if ((j == 5) && (!Util.fileExists(str1)))
          {
            paramIUiListener.onError(new UiError(-6, "手Q版本过低，纯图分享不支持网路图片", null));
            g.a("openSDK_LOG", "shareToQzone()手Q版本过低，纯图分享不支持网路图片");
            return;
          }
          paramBundle.putString("imageLocalUrl", str1);
        }
        if (SystemUtils.compareQQVersion(paramActivity, "4.5.0") >= 0)
          paramBundle.putInt("cflag", 1);
        ((QQShare)localObject).shareToQQ(paramActivity, paramBundle, paramIUiListener);
      }
      else
      {
        a(paramActivity, paramBundle, paramIUiListener);
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.connect.share.QzoneShare
 * JD-Core Version:    0.6.2
 */