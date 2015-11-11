package fm.qingting.social.api;

import android.content.Context;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.WeiboParameters;
import fm.qingting.social.ISocialEventListener;
import fm.qingting.social.SocialEventListenerWrapper;
import fm.qingting.social.auth.SinaWeiboAuth;
import java.io.File;

public class SinaWeiboApi
{
  private static final String API_COMMENTS = "/comments";
  private static final String API_FRIENDSHIPS = "/friendships";
  private static final String API_STATUSES = "/statuses";
  private static final String API_USERS = "/users";
  private static final String JSON_CREATE = "/create.json";
  private static final String JSON_SHOW = "/show.json";
  private static final String JSON_UPDATE = "/update.json";
  private static final String JSON_UPLOAD = "/upload.json";
  private static final String JSON_UPLOAD_URL_TEXT = "/upload_url_text.json";
  private static final String PARAM_ACCESS_TOKEN = "access_token";
  private static final String PARAM_COMMENT = "comment";
  private static final String PARAM_COMMENT_ORI = "comment_ori";
  private static final String PARAM_ID = "id";
  private static final String PARAM_LAT = "lat";
  private static final String PARAM_LONG = "long";
  private static final String PARAM_PIC = "pic";
  private static final String PARAM_PIC_ID = "pic_id";
  private static final String PARAM_SCREEN_NAME = "screen_name";
  private static final String PARAM_STATUS = "status";
  private static final String PARAM_UID = "uid";
  private static final String PARAM_URL = "url";
  private static final String REVOKE_OAUTH_URL = "https://api.weibo.com/oauth2/revokeoauth2";
  private static final String SERVER_HOST = "https://api.weibo.com/2";

  private static WeiboParameters buildStatusParam(String paramString1, String paramString2, String paramString3)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters("790020947");
    localWeiboParameters.add("status", paramString1);
    if (!TextUtils.isEmpty(paramString2))
      localWeiboParameters.add("long", paramString2);
    if (!TextUtils.isEmpty(paramString3))
      localWeiboParameters.add("lat", paramString3);
    return localWeiboParameters;
  }

  public static void checkIn(Context paramContext, String paramString1, String paramString2, String paramString3, ISocialEventListener paramISocialEventListener)
  {
    if ((paramString3 != null) && (paramString3.startsWith("http")))
      shareImage(paramContext, paramString1, paramString3, "", "", paramISocialEventListener);
    while ((paramString2 == null) || (paramString2 == ""))
      return;
    shareLocalImage(paramContext, paramString1, paramString2, paramISocialEventListener);
  }

  public static void comment(final Context paramContext, final String paramString1, final String paramString2, final Boolean paramBoolean, final ISocialEventListener paramISocialEventListener)
  {
    Oauth2AccessToken localOauth2AccessToken = SinaWeiboAuth.getToken();
    if ((localOauth2AccessToken != null) && (localOauth2AccessToken.isSessionValid()))
    {
      commentInternal(paramContext, paramString1, paramString2, paramBoolean, paramISocialEventListener);
      return;
    }
    SinaWeiboAuth.login(paramContext, new SocialEventListenerWrapper(paramISocialEventListener)
    {
      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        SinaWeiboApi.commentInternal(paramContext, paramString1, paramString2, paramBoolean, paramISocialEventListener);
      }
    });
  }

  private static void commentInternal(Context paramContext, String paramString1, String paramString2, Boolean paramBoolean, ISocialEventListener paramISocialEventListener)
  {
    Oauth2AccessToken localOauth2AccessToken = SinaWeiboAuth.getToken();
    WeiboParameters localWeiboParameters;
    if ((localOauth2AccessToken != null) && (localOauth2AccessToken.isSessionValid()))
    {
      localWeiboParameters = new WeiboParameters("790020947");
      localWeiboParameters.add("comment", paramString2);
      localWeiboParameters.add("id", paramString1);
      if (!paramBoolean.booleanValue())
        break label103;
    }
    label103: for (int i = 1; ; i = 0)
    {
      localWeiboParameters.add("comment_ori", i);
      localWeiboParameters.add("access_token", localOauth2AccessToken.getToken());
      new AsyncWeiboRunner(paramContext).requestAsync("https://api.weibo.com/2/comments/create.json", localWeiboParameters, "POST", new SinaWeiboListener(paramISocialEventListener));
      return;
    }
  }

  public static void follow(final Context paramContext, final String paramString, final ISocialEventListener paramISocialEventListener)
  {
    Oauth2AccessToken localOauth2AccessToken = SinaWeiboAuth.getToken();
    if ((localOauth2AccessToken != null) && (localOauth2AccessToken.isSessionValid()))
    {
      followInternal(paramContext, paramString, paramISocialEventListener);
      return;
    }
    SinaWeiboAuth.login(paramContext, new SocialEventListenerWrapper(paramISocialEventListener)
    {
      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        SinaWeiboApi.followInternal(paramContext, paramString, paramISocialEventListener);
      }
    });
  }

  private static void followInternal(Context paramContext, String paramString, ISocialEventListener paramISocialEventListener)
  {
    if (SinaWeiboAuth.isSessionValid(paramContext).booleanValue())
    {
      Oauth2AccessToken localOauth2AccessToken = SinaWeiboAuth.getToken();
      WeiboParameters localWeiboParameters = new WeiboParameters("790020947");
      localWeiboParameters.add("screen_name", paramString);
      localWeiboParameters.add("access_token", localOauth2AccessToken.getToken());
      new AsyncWeiboRunner(paramContext).requestAsync("https://api.weibo.com/2/friendships/create.json", localWeiboParameters, "POST", new SinaWeiboListener(paramISocialEventListener));
    }
  }

  public static void logout(Context paramContext, ISocialEventListener paramISocialEventListener)
  {
    SinaWeiboAuth.logout(paramContext, paramISocialEventListener);
  }

  public static void readProfile(Context paramContext, final ISocialEventListener paramISocialEventListener)
  {
    Oauth2AccessToken localOauth2AccessToken = SinaWeiboAuth.getToken();
    if ((localOauth2AccessToken != null) && (localOauth2AccessToken.isSessionValid()))
    {
      str = localOauth2AccessToken.getUid();
      localWeiboParameters = new WeiboParameters("790020947");
      localWeiboParameters.add("uid", str);
      localWeiboParameters.add("access_token", localOauth2AccessToken.getToken());
      new AsyncWeiboRunner(paramContext).requestAsync("https://api.weibo.com/2/users/show.json", localWeiboParameters, "GET", new SinaWeiboListener(paramISocialEventListener)
      {
        public void onComplete(String paramAnonymousString)
        {
          if (paramISocialEventListener != null)
            paramISocialEventListener.onComplete(paramAnonymousString, null);
        }
      });
    }
    while (paramISocialEventListener == null)
    {
      String str;
      WeiboParameters localWeiboParameters;
      return;
    }
    paramISocialEventListener.onException(localOauth2AccessToken);
  }

  public static void revokeAuth(Context paramContext, ISocialEventListener paramISocialEventListener)
  {
    if (SinaWeiboAuth.isSessionValid(paramContext).booleanValue())
      revokeAuthInternal(paramContext, paramISocialEventListener);
    while (paramISocialEventListener == null)
      return;
    paramISocialEventListener.onComplete(null, null);
  }

  private static void revokeAuthInternal(Context paramContext, ISocialEventListener paramISocialEventListener)
  {
    Oauth2AccessToken localOauth2AccessToken = SinaWeiboAuth.getToken();
    if ((localOauth2AccessToken != null) && (localOauth2AccessToken.isSessionValid()))
    {
      WeiboParameters localWeiboParameters = new WeiboParameters("790020947");
      localWeiboParameters.add("access_token", localOauth2AccessToken.getToken());
      new AsyncWeiboRunner(paramContext).requestAsync("https://api.weibo.com/oauth2/revokeoauth2", localWeiboParameters, "POST", new SinaWeiboListener(paramISocialEventListener));
    }
  }

  public static void shareImage(final Context paramContext, final String paramString1, final String paramString2, final String paramString3, final String paramString4, final ISocialEventListener paramISocialEventListener)
  {
    if (SinaWeiboAuth.isLoggedIn().booleanValue())
    {
      shareImageInternal(paramContext, paramString1, paramString2, paramString3, paramString4, paramISocialEventListener);
      return;
    }
    SinaWeiboAuth.login(paramContext, new SocialEventListenerWrapper(paramISocialEventListener)
    {
      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        SinaWeiboApi.shareImageInternal(paramContext, paramString1, paramString2, paramString3, paramString4, paramISocialEventListener);
      }
    });
  }

  private static void shareImageInternal(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, ISocialEventListener paramISocialEventListener)
  {
    Oauth2AccessToken localOauth2AccessToken = SinaWeiboAuth.getToken();
    if ((localOauth2AccessToken != null) && (localOauth2AccessToken.isSessionValid()))
    {
      paramString1 = buildStatusParam(paramString1, paramString4, paramString3);
      paramString1.add("url", paramString2);
      paramString1.add("access_token", localOauth2AccessToken.getToken());
      paramString1.add("pic_id", (String)null);
      new AsyncWeiboRunner(paramContext).requestAsync("https://api.weibo.com/2/statuses/upload_url_text.json", paramString1, "POST", new SinaWeiboListener(paramISocialEventListener));
    }
  }

  public static void shareLocalImage(final Context paramContext, final String paramString1, final String paramString2, final ISocialEventListener paramISocialEventListener)
  {
    if (SinaWeiboAuth.isLoggedIn().booleanValue())
    {
      shareLocalImageInternal(paramContext, paramString1, paramString2, paramISocialEventListener);
      return;
    }
    SinaWeiboAuth.login(paramContext, new SocialEventListenerWrapper(paramISocialEventListener)
    {
      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        SinaWeiboApi.shareLocalImageInternal(paramContext, paramString1, paramString2, paramISocialEventListener);
      }
    });
  }

  private static void shareLocalImageInternal(Context paramContext, String paramString1, String paramString2, ISocialEventListener paramISocialEventListener)
  {
    Oauth2AccessToken localOauth2AccessToken = SinaWeiboAuth.getToken();
    if ((localOauth2AccessToken != null) && (localOauth2AccessToken.isSessionValid()))
    {
      paramString1 = buildStatusParam(paramString1, "", "");
      paramString2 = new File(paramString2);
      if (paramString2.exists())
      {
        paramString1.add("pic", paramString2.getAbsolutePath());
        paramString1.add("access_token", localOauth2AccessToken.getToken());
        new AsyncWeiboRunner(paramContext).requestAsync("https://api.weibo.com/2/statuses/upload.json", paramString1, "POST", new SinaWeiboListener(paramISocialEventListener));
      }
    }
  }

  public static void shareMusic(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, ISocialEventListener paramISocialEventListener)
  {
  }

  public static void shareText(final Context paramContext, final String paramString1, final String paramString2, final String paramString3, final ISocialEventListener paramISocialEventListener)
  {
    if (SinaWeiboAuth.isLoggedIn().booleanValue())
    {
      shareTextInternal(paramContext, paramString1, paramString2, paramString3, paramISocialEventListener);
      return;
    }
    SinaWeiboAuth.login(paramContext, new SocialEventListenerWrapper(paramISocialEventListener)
    {
      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        SinaWeiboApi.shareTextInternal(paramContext, paramString1, paramString2, paramString3, paramISocialEventListener);
      }
    });
  }

  private static void shareTextInternal(Context paramContext, String paramString1, String paramString2, String paramString3, ISocialEventListener paramISocialEventListener)
  {
    if (SinaWeiboAuth.isSessionValid(paramContext).booleanValue())
    {
      Oauth2AccessToken localOauth2AccessToken = SinaWeiboAuth.getToken();
      paramString1 = buildStatusParam(paramString1, paramString3, paramString2);
      paramString1.add("access_token", localOauth2AccessToken.getToken());
      new AsyncWeiboRunner(paramContext);
      new AsyncWeiboRunner(paramContext).requestAsync("https://api.weibo.com/2/statuses/update.json", paramString1, "POST", new SinaWeiboListener(paramISocialEventListener));
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.social.api.SinaWeiboApi
 * JD-Core Version:    0.6.2
 */