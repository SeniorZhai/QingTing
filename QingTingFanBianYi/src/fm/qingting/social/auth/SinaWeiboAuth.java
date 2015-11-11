package fm.qingting.social.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import fm.qingting.social.ISocialEventListener;
import fm.qingting.social.SocialEventListener;

public class SinaWeiboAuth
{
  public static final String APP_KEY = "790020947";
  private static final String REDIRECT_URL = "http://weibo.callback.qingting.fm";
  private static final String SCOPE = "email,direct_messages_read,direct_messages_write,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog,invitation_write";
  private static Boolean isLoggedIn = Boolean.valueOf(false);
  private static Oauth2AccessToken mToken;
  private static SsoHandler ssoHandler;

  public static Oauth2AccessToken getToken()
  {
    return mToken;
  }

  public static Boolean isLoggedIn()
  {
    return isLoggedIn;
  }

  public static Boolean isSessionValid(Context paramContext)
  {
    if ((mToken != null) && (mToken.isSessionValid()));
    for (boolean bool = true; ; bool = false)
      return Boolean.valueOf(bool);
  }

  public static void login(Context paramContext, ISocialEventListener paramISocialEventListener)
  {
    if ((isLoggedIn.booleanValue()) && (mToken != null) && (mToken.isSessionValid()))
    {
      if (paramISocialEventListener != null)
        paramISocialEventListener.onComplete(mToken, null);
      return;
    }
    if (ssoHandler == null)
    {
      AuthInfo localAuthInfo = new AuthInfo(paramContext, "790020947", "http://weibo.callback.qingting.fm", "email,direct_messages_read,direct_messages_write,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog,invitation_write");
      ssoHandler = new SsoHandler((Activity)paramContext, localAuthInfo);
    }
    paramContext = new SocialEventListener()
    {
      public void onCancel(Object paramAnonymousObject)
      {
        if (this.val$listener != null)
          this.val$listener.onCancel(paramAnonymousObject);
      }

      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        SinaWeiboAuth.access$002(Boolean.valueOf(true));
        if (this.val$listener != null)
          this.val$listener.onComplete(paramAnonymousObject1, paramAnonymousObject2);
      }

      public void onException(Object paramAnonymousObject)
      {
        if (this.val$listener != null)
          this.val$listener.onException(paramAnonymousObject);
      }
    };
    Log.d("ZHENLI", "ssoHandler.authorize");
    ssoHandler.authorize(new SinaWeiboAuthListener(paramContext));
  }

  public static void logout(Context paramContext, ISocialEventListener paramISocialEventListener)
  {
    isLoggedIn = Boolean.valueOf(false);
    if (paramISocialEventListener != null)
      paramISocialEventListener.onComplete(null, null);
  }

  public static void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (ssoHandler != null)
      ssoHandler.authorizeCallBack(paramInt1, paramInt2, paramIntent);
  }

  public static void restoreLogin(Context paramContext)
  {
    if (isSessionValid(paramContext).booleanValue())
      isLoggedIn = Boolean.valueOf(true);
  }

  public static void restoreToken(Oauth2AccessToken paramOauth2AccessToken)
  {
    mToken = paramOauth2AccessToken;
  }

  public static void setToken(Oauth2AccessToken paramOauth2AccessToken)
  {
    mToken = paramOauth2AccessToken;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.social.auth.SinaWeiboAuth
 * JD-Core Version:    0.6.2
 */