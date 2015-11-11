package com.tencent.tauth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import com.tencent.b.a.g;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.avatar.QQAvatar;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.open.SocialApi;
import com.tencent.open.TaskGuide;
import com.tencent.utils.HttpUtils;
import com.tencent.utils.HttpUtils.HttpStatusException;
import com.tencent.utils.HttpUtils.NetworkUnavailableException;
import com.tencent.utils.SystemUtils;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class Tencent
{
  private Context mContext;
  private LocationApi mLocationApi;
  private QQAuth mQQAuth;

  private Tencent(String paramString, Context paramContext)
  {
    this.mContext = paramContext;
    this.mQQAuth = QQAuth.createInstance(paramString, paramContext);
  }

  // ERROR //
  private static boolean checkManifestConfig(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: new 32	android/content/ComponentName
    //   3: dup
    //   4: aload_0
    //   5: invokevirtual 38	android/content/Context:getPackageName	()Ljava/lang/String;
    //   8: ldc 40
    //   10: invokespecial 43	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   13: astore_2
    //   14: aload_0
    //   15: invokevirtual 47	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   18: aload_2
    //   19: iconst_0
    //   20: invokevirtual 53	android/content/pm/PackageManager:getActivityInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   23: pop
    //   24: new 32	android/content/ComponentName
    //   27: dup
    //   28: aload_0
    //   29: invokevirtual 38	android/content/Context:getPackageName	()Ljava/lang/String;
    //   32: ldc 55
    //   34: invokespecial 43	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   37: astore_1
    //   38: aload_0
    //   39: invokevirtual 47	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   42: aload_1
    //   43: iconst_0
    //   44: invokevirtual 53	android/content/pm/PackageManager:getActivityInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   47: pop
    //   48: iconst_1
    //   49: ireturn
    //   50: astore_0
    //   51: new 57	java/lang/StringBuilder
    //   54: dup
    //   55: invokespecial 58	java/lang/StringBuilder:<init>	()V
    //   58: ldc 60
    //   60: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: aload_1
    //   64: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: ldc 66
    //   69: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   75: astore_0
    //   76: new 57	java/lang/StringBuilder
    //   79: dup
    //   80: invokespecial 58	java/lang/StringBuilder:<init>	()V
    //   83: aload_0
    //   84: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: ldc 71
    //   89: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: aload_1
    //   93: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: ldc 73
    //   98: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: ldc 75
    //   103: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: ldc 77
    //   108: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   114: astore_0
    //   115: invokestatic 83	com/tencent/b/a/g:a	()Lcom/tencent/b/a/g;
    //   118: pop
    //   119: ldc 85
    //   121: aload_0
    //   122: invokestatic 88	com/tencent/b/a/g:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   125: iconst_0
    //   126: ireturn
    //   127: astore_0
    //   128: new 57	java/lang/StringBuilder
    //   131: dup
    //   132: invokespecial 58	java/lang/StringBuilder:<init>	()V
    //   135: ldc 90
    //   137: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: ldc 92
    //   142: invokevirtual 64	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   148: astore_0
    //   149: invokestatic 83	com/tencent/b/a/g:a	()Lcom/tencent/b/a/g;
    //   152: pop
    //   153: ldc 94
    //   155: aload_0
    //   156: invokestatic 88	com/tencent/b/a/g:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   159: iconst_0
    //   160: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	24	50	android/content/pm/PackageManager$NameNotFoundException
    //   24	48	127	android/content/pm/PackageManager$NameNotFoundException
  }

  public static Tencent createInstance(String paramString, Context paramContext)
  {
    Tencent localTencent = new Tencent(paramString, paramContext);
    if (!checkManifestConfig(paramContext, paramString))
      return null;
    g.a("openSDK_LOG", "createInstance()  --end");
    return localTencent;
  }

  public int ask(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    new SocialApi(paramActivity, this.mQQAuth.getQQToken()).ask(paramActivity, paramBundle, paramIUiListener);
    return 0;
  }

  public int brag(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    new SocialApi(paramActivity, this.mQQAuth.getQQToken()).brag(paramActivity, paramBundle, paramIUiListener);
    return 0;
  }

  public int challenge(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    new SocialApi(paramActivity, this.mQQAuth.getQQToken()).challenge(paramActivity, paramBundle, paramIUiListener);
    return 0;
  }

  public int deleteLocation(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    if (this.mLocationApi == null)
      this.mLocationApi = new LocationApi(paramActivity, this.mQQAuth.getQQToken());
    this.mLocationApi.deleteLocation(paramActivity, paramBundle, paramIUiListener);
    return 0;
  }

  public String getAccessToken()
  {
    return this.mQQAuth.getQQToken().getAccessToken();
  }

  public String getAppId()
  {
    return this.mQQAuth.getQQToken().getAppId();
  }

  public long getExpiresIn()
  {
    return this.mQQAuth.getQQToken().getExpireTimeInSecond();
  }

  public String getOpenId()
  {
    return this.mQQAuth.getQQToken().getOpenId();
  }

  public QQToken getQQToken()
  {
    return this.mQQAuth.getQQToken();
  }

  public int gift(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    new SocialApi(paramActivity, this.mQQAuth.getQQToken()).gift(paramActivity, paramBundle, paramIUiListener);
    return 0;
  }

  public void grade(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    new SocialApi(paramActivity, this.mQQAuth.getQQToken()).grade(paramActivity, paramBundle, paramIUiListener);
  }

  public int invite(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    new SocialApi(paramActivity, this.mQQAuth.getQQToken()).invite(paramActivity, paramBundle, paramIUiListener);
    return 0;
  }

  public boolean isReady()
  {
    return (isSessionValid()) && (getOpenId() != null);
  }

  public boolean isSessionValid()
  {
    return this.mQQAuth.isSessionValid();
  }

  public boolean isSupportSSOLogin(Activity paramActivity)
  {
    if (SystemUtils.getAppVersionName(paramActivity, "com.tencent.mobileqq") == null)
    {
      Toast.makeText(paramActivity, "没有安装手Q", 0).show();
      return false;
    }
    if (SystemUtils.checkMobileQQ(paramActivity))
    {
      Toast.makeText(paramActivity, "已安装的手Q版本支持SSO登陆", 0).show();
      return true;
    }
    Toast.makeText(paramActivity, "已安装的手Q版本不支持SSO登陆", 0).show();
    return false;
  }

  public int login(Activity paramActivity, String paramString, IUiListener paramIUiListener)
  {
    return this.mQQAuth.login(paramActivity, paramString, paramIUiListener);
  }

  public int loginWithOEM(Activity paramActivity, String paramString1, IUiListener paramIUiListener, String paramString2, String paramString3, String paramString4)
  {
    return this.mQQAuth.loginWithOEM(paramActivity, paramString1, paramIUiListener, paramString2, paramString3, paramString4);
  }

  public void logout(Context paramContext)
  {
    this.mQQAuth.getQQToken().setAccessToken(null, "0");
    this.mQQAuth.getQQToken().setOpenId(null);
  }

  public boolean onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    return false;
  }

  public int reAuth(Activity paramActivity, String paramString, IUiListener paramIUiListener)
  {
    return this.mQQAuth.reAuth(paramActivity, paramString, paramIUiListener);
  }

  public int reactive(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    new SocialApi(paramActivity, this.mQQAuth.getQQToken()).reactive(paramActivity, paramBundle, paramIUiListener);
    return 0;
  }

  public JSONObject request(String paramString1, Bundle paramBundle, String paramString2)
    throws IOException, JSONException, HttpUtils.NetworkUnavailableException, HttpUtils.HttpStatusException
  {
    return HttpUtils.request(this.mQQAuth.getQQToken(), this.mContext, paramString1, paramBundle, paramString2);
  }

  public void requestAsync(String paramString1, Bundle paramBundle, String paramString2, IRequestListener paramIRequestListener, Object paramObject)
  {
    HttpUtils.requestAsync(this.mQQAuth.getQQToken(), this.mContext, paramString1, paramBundle, paramString2, paramIRequestListener);
  }

  public int searchNearby(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    if (this.mLocationApi == null)
      this.mLocationApi = new LocationApi(paramActivity, this.mQQAuth.getQQToken());
    this.mLocationApi.searchNearby(paramActivity, paramBundle, paramIUiListener);
    return 0;
  }

  public void setAccessToken(String paramString1, String paramString2)
  {
    g.a("openSDK_LOG", "setAccessToken(), expiresIn = " + paramString2 + "");
    this.mQQAuth.setAccessToken(paramString1, paramString2);
  }

  public void setAvatar(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    String str = paramBundle.getString("picture");
    int i = paramBundle.getInt("exitAnim");
    new QQAvatar(this.mContext, this.mQQAuth.getQQToken()).setAvatar(paramActivity, Uri.parse(str), paramIUiListener, i);
  }

  public void setAvatar(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener, int paramInt1, int paramInt2)
  {
    paramBundle.putInt("exitAnim", paramInt2);
    paramActivity.overridePendingTransition(paramInt1, 0);
    setAvatar(paramActivity, paramBundle, paramIUiListener);
  }

  public void setOpenId(String paramString)
  {
    g.a("openSDK_LOG", "setOpenId() --start");
    this.mQQAuth.setOpenId(this.mContext, paramString);
    g.a("openSDK_LOG", "setOpenId() --end");
  }

  public void shareToQQ(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    new QQShare(paramActivity, this.mQQAuth.getQQToken()).shareToQQ(paramActivity, paramBundle, paramIUiListener);
  }

  public void shareToQzone(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    new QzoneShare(paramActivity, this.mQQAuth.getQQToken()).shareToQzone(paramActivity, paramBundle, paramIUiListener);
  }

  public void showTaskGuideWindow(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    new TaskGuide(paramActivity, this.mQQAuth.getQQToken()).showTaskGuideWindow(paramActivity, paramBundle, paramIUiListener);
  }

  public int story(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    new SocialApi(paramActivity, this.mQQAuth.getQQToken()).story(paramActivity, paramBundle, paramIUiListener);
    return 0;
  }

  public void voice(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    new SocialApi(paramActivity, this.mQQAuth.getQQToken()).voice(paramActivity, paramBundle, paramIUiListener);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.Tencent
 * JD-Core Version:    0.6.2
 */