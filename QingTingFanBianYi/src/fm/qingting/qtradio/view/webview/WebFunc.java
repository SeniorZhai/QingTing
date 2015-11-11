package fm.qingting.qtradio.view.webview;

import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.social.CloudCenter.OnLoginEventListerner;
import fm.qingting.qtradio.social.UserProfile;

public class WebFunc
{
  private static WebFunc _instance;
  private String _callBack;
  private String _callBackJs;
  private WebView _webview;
  private final Handler userInfoHandler = new Handler();
  private Runnable userInfoRunnable = new Runnable()
  {
    public void run()
    {
      WebFunc.this.runGetUserInfo();
    }
  };

  private void doGetUserInfo()
  {
    UserInfo localUserInfo = InfoManager.getInstance().getUserProfile().getUserInfo();
    if (localUserInfo == null)
      return;
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("id", localUserInfo.userId);
    localJSONObject.put("snsId", localUserInfo.snsInfo.sns_id);
    localJSONObject.put("snsName", localUserInfo.snsInfo.sns_name);
    localJSONObject.put("snsAvatar", localUserInfo.snsInfo.sns_avatar);
    localJSONObject.put("snsSite", localUserInfo.snsInfo.sns_site);
    invokeCallBack(JSON.toJSONString(localJSONObject));
  }

  public static WebFunc getInstance()
  {
    try
    {
      if (_instance == null)
        _instance = new WebFunc();
      WebFunc localWebFunc = _instance;
      return localWebFunc;
    }
    finally
    {
    }
  }

  private void invokeCallBack(String paramString)
  {
    if (this._callBack == null)
      return;
    this._callBackJs = "javascript:";
    this._callBackJs += this._callBack;
    if (paramString == null);
    for (this._callBackJs += "(null"; ; this._callBackJs = (this._callBackJs + "('" + paramString + "'"))
    {
      this._callBackJs += ")";
      if ((this._webview == null) || (this._callBackJs == null))
        break;
      this._webview.loadUrl(this._callBackJs);
      return;
    }
  }

  private void runGetUserInfo()
  {
    if (!CloudCenter.getInstance().isLogin(false))
    {
      CloudCenter.OnLoginEventListerner local2 = new CloudCenter.OnLoginEventListerner()
      {
        public void onLoginFailed(int paramAnonymousInt)
        {
        }

        public void onLoginSuccessed(int paramAnonymousInt)
        {
          WebFunc.this.doGetUserInfo();
        }
      };
      EventDispacthManager.getInstance().dispatchAction("showLogin", local2);
      return;
    }
    doGetUserInfo();
  }

  @JavascriptInterface
  public void getUserInfo(String paramString)
  {
    this._callBack = paramString;
    this.userInfoHandler.postDelayed(this.userInfoRunnable, 0L);
  }

  public void setWebview(WebView paramWebView)
  {
    this._webview = paramWebView;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.webview.WebFunc
 * JD-Core Version:    0.6.2
 */