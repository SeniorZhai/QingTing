package fm.qingting.social.auth;

import android.content.Context;
import android.content.Intent;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.component.Authorize;
import com.tencent.weibo.sdk.android.component.sso.AuthHelper;
import com.tencent.weibo.sdk.android.component.sso.OnAuthListener;
import com.tencent.weibo.sdk.android.component.sso.WeiboToken;
import fm.qingting.social.ISocialEventListener;

public class TencentWeiboAuth
{
  private static final long APP_ID = 801439222L;
  private static final String SECRET = "9a0504d18beda2583aa2ddfb2046d4f9";
  private static Boolean isAuthorizing = Boolean.valueOf(false);
  private static Boolean isLoggedIn = Boolean.valueOf(false);
  private static Boolean isSSO = Boolean.valueOf(false);
  private static ISocialEventListener mListener;
  private static String mName;
  private static WeiboToken mToken;

  public static Boolean isLoggedIn()
  {
    return isLoggedIn;
  }

  public static Boolean isSSO()
  {
    return isSSO;
  }

  public static Boolean isSessionValid(Context paramContext)
  {
    String str1 = Util.getSharePersistent(paramContext, "ACCESS_TOKEN");
    String str2 = Util.getSharePersistent(paramContext, "EXPIRES_IN");
    String str3 = Util.getSharePersistent(paramContext, "AUTHORIZETIME");
    long l = System.currentTimeMillis() / 1000L;
    if ((notEmpty(str1).booleanValue()) && (notEmpty(str2).booleanValue()) && (notEmpty(str3).booleanValue()))
    {
      if (l < Long.parseLong(str3) + Long.parseLong(str2))
      {
        mToken = new WeiboToken();
        mToken.accessToken = str1;
        mToken.expiresIn = Long.valueOf(str2).longValue();
        mToken.omasKey = Util.getSharePersistent(paramContext, "OPEN_KEY");
        mToken.omasToken = "";
        mToken.openID = Util.getSharePersistent(paramContext, "OPEN_ID");
        mToken.refreshToken = Util.getSharePersistent(paramContext, "REFRESH_TOKEN");
        return Boolean.valueOf(true);
      }
      return Boolean.valueOf(false);
    }
    return Boolean.valueOf(false);
  }

  public static void login(Context paramContext, final ISocialEventListener paramISocialEventListener)
  {
    if (isLoggedIn.booleanValue())
      return;
    mListener = paramISocialEventListener;
    AuthHelper.register(paramContext, 801439222L, "9a0504d18beda2583aa2ddfb2046d4f9", new OnAuthListener()
    {
      public void onAuthFail(int paramAnonymousInt, String paramAnonymousString)
      {
        AuthHelper.unregister(this.val$context);
        if (paramISocialEventListener != null)
          paramISocialEventListener.onException(Integer.valueOf(paramAnonymousInt));
      }

      public void onAuthPassed(String paramAnonymousString, WeiboToken paramAnonymousWeiboToken)
      {
        TencentWeiboAuth.access$002(Boolean.valueOf(true));
        TencentWeiboAuth.access$102(Boolean.valueOf(true));
        TencentWeiboAuth.saveAuthInfo(this.val$context, paramAnonymousWeiboToken, paramAnonymousString);
        if (paramISocialEventListener != null)
          paramISocialEventListener.onComplete(paramAnonymousWeiboToken, paramAnonymousString);
      }

      public void onWeiBoNotInstalled()
      {
        AuthHelper.unregister(this.val$context);
        Intent localIntent = new Intent(this.val$context, Authorize.class);
        TencentWeiboAuth.access$302(Boolean.valueOf(true));
        this.val$context.startActivity(localIntent);
      }

      public void onWeiboVersionMisMatch()
      {
        AuthHelper.unregister(this.val$context);
        Intent localIntent = new Intent(this.val$context, Authorize.class);
        TencentWeiboAuth.access$302(Boolean.valueOf(true));
        this.val$context.startActivity(localIntent);
      }
    });
    Util.clearSharePersistent(paramContext);
    AuthHelper.auth(paramContext, "9a0504d18beda2583aa2ddfb2046d4f9");
  }

  public static void logout(Context paramContext, ISocialEventListener paramISocialEventListener)
  {
    isLoggedIn = Boolean.valueOf(false);
    if (paramISocialEventListener != null)
      paramISocialEventListener.onComplete(null, null);
  }

  private static Boolean notEmpty(String paramString)
  {
    if ((paramString != null) && (paramString != ""));
    for (boolean bool = true; ; bool = false)
      return Boolean.valueOf(bool);
  }

  public static void onResume(Context paramContext)
  {
    if (isAuthorizing.booleanValue())
    {
      isAuthorizing = Boolean.valueOf(false);
      isSSO = Boolean.valueOf(false);
      String str = Util.getSharePersistent(paramContext, "ACCESS_TOKEN");
      if (!notEmpty(str).booleanValue())
        break label157;
      mToken = new WeiboToken();
      mToken.accessToken = str;
      mToken.expiresIn = Long.parseLong(Util.getSharePersistent(paramContext, "EXPIRES_IN"));
      mToken.omasKey = Util.getSharePersistent(paramContext, "OPEN_KEY");
      mToken.omasToken = Util.getSharePersistent(paramContext, "OMAS_TOKEN");
      mToken.openID = Util.getSharePersistent(paramContext, "OPEN_ID");
      mToken.refreshToken = Util.getSharePersistent(paramContext, "REFRESH_TOKEN");
      mName = Util.getSharePersistent(paramContext, "NAME");
      isLoggedIn = Boolean.valueOf(true);
      if (mListener != null)
        mListener.onComplete(mToken, mName);
    }
    label157: 
    do
    {
      return;
      isLoggedIn = Boolean.valueOf(false);
    }
    while (mListener == null);
    mListener.onCancel(null);
  }

  public static void restoreLogin(Context paramContext)
  {
    if (isSessionValid(paramContext).booleanValue())
      isLoggedIn = Boolean.valueOf(true);
  }

  private static void saveAuthInfo(Context paramContext, WeiboToken paramWeiboToken, String paramString)
  {
    if (paramWeiboToken != null)
    {
      Util.saveSharePersistent(paramContext, "CLIENT_ID", String.valueOf(801439222L));
      Util.saveSharePersistent(paramContext, "ACCESS_TOKEN", paramWeiboToken.accessToken);
      Util.saveSharePersistent(paramContext, "OPEN_ID", paramWeiboToken.openID);
      Util.saveSharePersistent(paramContext, "OPEN_KEY", paramWeiboToken.omasKey);
      Util.saveSharePersistent(paramContext, "OMAS_TOKEN", paramWeiboToken.omasToken);
      Util.saveSharePersistent(paramContext, "REFRESH_TOKEN", paramWeiboToken.refreshToken);
      Util.saveSharePersistent(paramContext, "EXPIRES_IN", String.valueOf(paramWeiboToken.expiresIn));
      Util.saveSharePersistent(paramContext, "NAME", paramString);
      Util.saveSharePersistent(paramContext, "NICK", paramString);
      Util.saveSharePersistent(paramContext, "AUTHORIZETIME", String.valueOf(System.currentTimeMillis() / 1000L));
    }
  }

  public static void ssoLogout(Context paramContext, ISocialEventListener paramISocialEventListener)
  {
    isLoggedIn = Boolean.valueOf(false);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.social.auth.TencentWeiboAuth
 * JD-Core Version:    0.6.2
 */