package fm.qingting.social.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import fm.qingting.social.ISocialEventListener;

public class QQApi
{
  private static String APP_ID = "100371282";
  private static String APP_NAME = "蜻蜓FM";
  private static Tencent mTencent;

  private static void auth(Context paramContext)
  {
    if ((mTencent == null) || (!mTencent.isSessionValid()))
      mTencent = Tencent.createInstance(APP_ID, paramContext);
  }

  public static void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
  }

  public static void share(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, ISocialEventListener paramISocialEventListener)
  {
    auth(paramContext);
    paramString5 = new Bundle();
    paramString5.putInt("req_type", 1);
    paramString5.putString("targetUrl", paramString4);
    paramString5.putString("appName", APP_NAME);
    paramString5.putString("title", paramString1);
    paramString1 = paramString2;
    if (paramString2 == null)
      paramString1 = "";
    paramString5.putString("summary", paramString1);
    paramString5.putString("imageUrl", paramString3);
    paramString5.putInt("cflag", 2);
    mTencent.shareToQQ((Activity)paramContext, paramString5, new IUiListener()
    {
      public void onCancel()
      {
        if (this.val$listener != null)
          this.val$listener.onCancel(null);
      }

      public void onComplete(Object paramAnonymousObject)
      {
        if (this.val$listener != null)
          this.val$listener.onComplete(paramAnonymousObject, null);
      }

      public void onError(UiError paramAnonymousUiError)
      {
        if (this.val$listener != null)
          this.val$listener.onException(paramAnonymousUiError);
      }
    });
  }

  public static void shareMusic(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, ISocialEventListener paramISocialEventListener)
  {
    auth(paramContext);
    Bundle localBundle = new Bundle();
    localBundle.putInt("req_type", 2);
    localBundle.putString("title", paramString1);
    localBundle.putString("summary", paramString2);
    localBundle.putString("targetUrl", paramString3);
    localBundle.putString("imageUrl", paramString4);
    localBundle.putString("audio_url", paramString5);
    localBundle.putString("appName", APP_NAME);
    localBundle.putInt("cflag", 1);
    new QQShare(paramContext, mTencent.getQQToken()).shareToQQ((Activity)paramContext, localBundle, new IUiListener()
    {
      public void onCancel()
      {
        if (this.val$listener != null)
          this.val$listener.onCancel(null);
      }

      public void onComplete(Object paramAnonymousObject)
      {
        if (this.val$listener != null)
          this.val$listener.onComplete(paramAnonymousObject, null);
      }

      public void onError(UiError paramAnonymousUiError)
      {
        if (this.val$listener != null)
          this.val$listener.onException(paramAnonymousUiError);
      }
    });
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.social.api.QQApi
 * JD-Core Version:    0.6.2
 */