package fm.qingting.social.api;

import android.content.Context;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.weibo.sdk.android.api.ShortUrlAPI;
import com.tencent.weibo.sdk.android.api.UserAPI;
import com.tencent.weibo.sdk.android.api.WeiboAPI;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.ModelResult;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import fm.qingting.social.ISocialEventListener;
import fm.qingting.social.SocialEventListenerWrapper;
import fm.qingting.social.auth.TencentWeiboAuth;

public class TencentWeiboApi
{
  public static void getShortLink(final Context paramContext, final String paramString, final ISocialEventListener paramISocialEventListener)
  {
    if (TencentWeiboAuth.isLoggedIn().booleanValue())
    {
      getShortLinkInternal(paramContext, paramString, paramISocialEventListener);
      return;
    }
    TencentWeiboAuth.login(paramContext, new SocialEventListenerWrapper(paramISocialEventListener)
    {
      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        TencentWeiboApi.getShortLinkInternal(paramContext, paramString, paramISocialEventListener);
      }
    });
  }

  private static void getShortLinkInternal(Context paramContext, String paramString, ISocialEventListener paramISocialEventListener)
  {
    new ShortUrlAPI(new AccountModel(Util.getSharePersistent(paramContext, "ACCESS_TOKEN"))).getShortUrl(paramContext, "json", paramString, new TencentWeiboHttpCallback(paramISocialEventListener), null, 4);
  }

  public static Boolean isSessionValid(Context paramContext)
  {
    return TencentWeiboAuth.isSessionValid(paramContext);
  }

  public static void readProfile(Context paramContext, final ISocialEventListener paramISocialEventListener)
  {
    String str = Util.getSharePersistent(paramContext, "ACCESS_TOKEN");
    if ((str == null) || (str == ""))
      return;
    new UserAPI(new AccountModel(str)).getUserInfo(paramContext, "json", new HttpCallback()
    {
      public void onResult(Object paramAnonymousObject)
      {
        paramAnonymousObject = (ModelResult)paramAnonymousObject;
        if (paramAnonymousObject == null);
        do
        {
          return;
          paramAnonymousObject = paramAnonymousObject.getObj().toString();
          JSONObject localJSONObject = (JSONObject)JSON.parse(paramAnonymousObject);
          if ((localJSONObject != null) && (localJSONObject.getInteger("ret").intValue() == 0))
          {
            localJSONObject = localJSONObject.getJSONObject("data");
            int i = localJSONObject.getInteger("sex").intValue();
            if (i == 1)
              paramAnonymousObject = "m";
            while (true)
            {
              Util.saveSharePersistent(this.val$context, "gender", paramAnonymousObject);
              Util.saveSharePersistent(this.val$context, "name", localJSONObject.getString("name"));
              Util.saveSharePersistent(this.val$context, "nick", localJSONObject.getString("nick"));
              paramAnonymousObject = localJSONObject.getString("head");
              if ((paramAnonymousObject != null) && (paramAnonymousObject.length() > 0))
                Util.saveSharePersistent(this.val$context, "avatar", paramAnonymousObject + "/120");
              Util.saveSharePersistent(this.val$context, "introduction", localJSONObject.getString("introduction"));
              Util.saveSharePersistent(this.val$context, "key", localJSONObject.getString("_id"));
              if (paramISocialEventListener == null)
                break;
              paramISocialEventListener.onComplete(null, null);
              return;
              if (i == 2)
                paramAnonymousObject = "f";
              else
                paramAnonymousObject = "n";
            }
          }
        }
        while (paramISocialEventListener == null);
        paramISocialEventListener.onException(paramAnonymousObject);
      }
    }
    , null, 4);
  }

  public static void shareImage(final Context paramContext, final String paramString1, final String paramString2, final ISocialEventListener paramISocialEventListener)
  {
    if (TencentWeiboAuth.isLoggedIn().booleanValue())
    {
      shareImageInternal(paramContext, paramString1, paramString2, paramISocialEventListener);
      return;
    }
    TencentWeiboAuth.login(paramContext, new SocialEventListenerWrapper(paramISocialEventListener)
    {
      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        if (paramISocialEventListener != null)
          TencentWeiboApi.shareImageInternal(paramContext, paramString1, paramString2, paramISocialEventListener);
      }
    });
  }

  private static void shareImageInternal(Context paramContext, String paramString1, String paramString2, ISocialEventListener paramISocialEventListener)
  {
    new WeiboAPI(new AccountModel(Util.getSharePersistent(paramContext, "ACCESS_TOKEN"))).addMulti(paramContext, "json", paramString1, 0.0D, 0.0D, paramString2, "", "", "", "", "", 0, 0, new TencentWeiboHttpCallback(paramISocialEventListener), null, 4);
  }

  public static void shareMusic(final Context paramContext, final String paramString1, final String paramString2, final String paramString3, final String paramString4, final ISocialEventListener paramISocialEventListener)
  {
    if (TencentWeiboAuth.isLoggedIn().booleanValue())
    {
      shareMusicInternal(paramContext, paramString1, paramString3, paramString2, paramString4, paramISocialEventListener);
      return;
    }
    TencentWeiboAuth.login(paramContext, new SocialEventListenerWrapper(paramISocialEventListener)
    {
      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        TencentWeiboApi.shareMusicInternal(paramContext, paramString1, paramString3, paramString2, paramString4, paramISocialEventListener);
      }
    });
  }

  private static void shareMusicInternal(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, ISocialEventListener paramISocialEventListener)
  {
    new WeiboAPI(new AccountModel(Util.getSharePersistent(paramContext, "ACCESS_TOKEN"))).addMulti(paramContext, "json", paramString1, 0.0D, 0.0D, "", "", "", paramString3, paramString2, paramString4, 0, 96, new TencentWeiboHttpCallback(paramISocialEventListener), null, 4);
  }

  public static void shareText(final Context paramContext, final String paramString, final ISocialEventListener paramISocialEventListener)
  {
    if (TencentWeiboAuth.isLoggedIn().booleanValue())
    {
      shareTextInternal(paramContext, paramString, paramISocialEventListener);
      return;
    }
    TencentWeiboAuth.login(paramContext, new SocialEventListenerWrapper(paramISocialEventListener)
    {
      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        TencentWeiboApi.shareTextInternal(paramContext, paramString, paramISocialEventListener);
      }
    });
  }

  private static void shareTextInternal(Context paramContext, String paramString, ISocialEventListener paramISocialEventListener)
  {
    new WeiboAPI(new AccountModel(Util.getSharePersistent(paramContext, "ACCESS_TOKEN"))).addWeibo(paramContext, paramString, "json", 0.0D, 0.0D, 0, 0, new HttpCallback()
    {
      public void onResult(Object paramAnonymousObject)
      {
        if (this.val$listener != null)
          this.val$listener.onComplete(paramAnonymousObject, null);
      }
    }
    , null, 4);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.social.api.TencentWeiboApi
 * JD-Core Version:    0.6.2
 */