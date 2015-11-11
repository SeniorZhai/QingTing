package com.tencent.tauth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.tencent.connect.auth.AuthDialog;
import com.tencent.connect.auth.AuthMap;
import com.tencent.connect.auth.AuthMap.Auth;
import com.tencent.utils.TemporaryStorage;
import com.tencent.utils.Util;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthActivity extends Activity
{
  private static final String ACTION_KEY = "action";
  private static final String ACTION_SHARE_TO_QQ = "shareToQQ";
  private static final String ACTION_SHARE_TO_QZONE = "shareToQzone";

  private void execAuthCallback(Bundle paramBundle, String paramString)
  {
    AuthMap localAuthMap = AuthMap.getInstance();
    String str = paramBundle.getString("serial");
    AuthMap.Auth localAuth = localAuthMap.get(str);
    if (localAuth != null)
    {
      if (paramString.indexOf("://cancel") == -1)
        break label64;
      localAuth.listener.onCancel();
      localAuth.dialog.dismiss();
    }
    while (true)
    {
      localAuthMap.remove(str);
      finish();
      return;
      label64: paramString = paramBundle.getString("access_token");
      if (paramString != null)
        paramBundle.putString("access_token", localAuthMap.decode(paramString, localAuth.key));
      paramBundle = Util.encodeUrl(paramBundle);
      paramBundle = Util.decodeUrlToJson(new JSONObject(), paramBundle);
      paramString = paramBundle.optString("cb");
      if (!"".equals(paramString))
      {
        localAuth.dialog.callJs(paramString, paramBundle.toString());
      }
      else
      {
        localAuth.listener.onComplete(paramBundle);
        localAuth.dialog.dismiss();
      }
    }
  }

  private void execShareToQQCallback(Bundle paramBundle)
  {
    Object localObject = TemporaryStorage.get(paramBundle.getString("action"));
    if (localObject == null)
    {
      finish();
      return;
    }
    localObject = (IUiListener)localObject;
    String str = paramBundle.getString("result");
    paramBundle = paramBundle.getString("response");
    if (str.equals("cancel"))
      ((IUiListener)localObject).onCancel();
    do
      while (true)
      {
        finish();
        return;
        if (!str.equals("error"))
          break;
        ((IUiListener)localObject).onError(new UiError(-6, "unknown error", paramBundle + ""));
      }
    while (!str.equals("complete"));
    if (paramBundle == null)
      paramBundle = "{\"ret\": 0}";
    while (true)
    {
      try
      {
        ((IUiListener)localObject).onComplete(new JSONObject(paramBundle));
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        ((IUiListener)localObject).onError(new UiError(-4, "json error", paramBundle + ""));
      }
      break;
    }
  }

  private void handleActionUri(Uri paramUri)
  {
    if ((paramUri == null) || (paramUri.toString().equals("")))
    {
      finish();
      return;
    }
    paramUri = paramUri.toString();
    Bundle localBundle = Util.decodeUrl(paramUri.substring(paramUri.indexOf("#") + 1));
    String str = localBundle.getString("action");
    if (str == null)
    {
      execAuthCallback(localBundle, paramUri);
      return;
    }
    if ((str.equals("shareToQQ")) || (str.equals("shareToQzone")))
    {
      execShareToQQCallback(localBundle);
      return;
    }
    execAuthCallback(localBundle, paramUri);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    handleActionUri(getIntent().getData());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.AuthActivity
 * JD-Core Version:    0.6.2
 */