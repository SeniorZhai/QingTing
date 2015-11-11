package fm.qingting.social.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import fm.qingting.social.ISocialEventListener;
import java.util.ArrayList;

public class QZoneApi
{
  private static String APP_NAME = "蜻蜓FM";
  private static Tencent mTencentAuth;

  private static void auth(Context paramContext)
  {
    if ((mTencentAuth == null) || (mTencentAuth.isSessionValid()))
      mTencentAuth = Tencent.createInstance(APP_NAME, paramContext);
  }

  public static void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
  }

  public static void share(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, ISocialEventListener paramISocialEventListener)
  {
    auth(paramContext);
    Bundle localBundle = new Bundle();
    localBundle.putString("appName", APP_NAME);
    localBundle.putInt("req_type", 1);
    localBundle.putString("title", paramString1);
    localBundle.putString("summary", paramString2);
    localBundle.putString("targetUrl", paramString3);
    paramString1 = new ArrayList();
    paramString1.add(paramString4);
    localBundle.putStringArrayList("imageUrl", paramString1);
    localBundle.putString("site", paramString5);
    mTencentAuth.shareToQzone((Activity)paramContext, localBundle, new IUiListener()
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

  public static void shareText(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, ISocialEventListener paramISocialEventListener)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("appName", APP_NAME);
    localBundle.putInt("req_type", 1);
    localBundle.putString("title", paramString1);
    localBundle.putString("summary", paramString2);
    localBundle.putString("targetUrl", paramString4);
    localBundle.putString("site", paramString3);
    mTencentAuth.shareToQzone((Activity)paramContext, localBundle, new IUiListener()
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
 * Qualified Name:     fm.qingting.social.api.QZoneApi
 * JD-Core Version:    0.6.2
 */