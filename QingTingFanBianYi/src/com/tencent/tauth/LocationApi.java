package com.tencent.tauth;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.tencent.b.a.g;
import com.tencent.connect.a.a;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.utils.HttpUtils;
import com.tencent.utils.HttpUtils.HttpStatusException;
import com.tencent.utils.HttpUtils.NetworkUnavailableException;
import com.tencent.utils.Util;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationApi extends BaseApi
  implements LbsAgent.OnGetLocationListener
{
  private static final String ACTION_DELETE = "delete_location";
  private static final String ACTION_SEARCH = "search_nearby";
  private static final String CGI_DELETE_LOCATION = "http://fusion.qq.com/cgi-bin/qzapps/mapp_lbs_delete.cgi";
  private static final String CGI_SEARCH_NEARBY = "http://fusion.qq.com/cgi-bin/qzapps/mapp_lbs_getnear.cgi";
  private static final String EVENT_ID_DELETE = "id_delete_location";
  private static final String EVENT_ID_SEARCH = "id_search_nearby";
  private static final int MSG_GET_LOCATION_TIMEOUT = 101;
  private static final int MSG_VERIFY_SOSOCODE_FAILED = 104;
  private static final int MSG_VERIFY_SOSOCODE_SUCCESS = 103;
  private static final int PAGE_DEFAULT = 1;
  private Handler mHandler;
  private HandlerThread mHandlerThread;
  private LbsAgent mLbsAgent;
  private Handler mMainHandler;
  private Bundle mParams;
  private IUiListener mSearchListener;

  public LocationApi(Context paramContext, QQAuth paramQQAuth, QQToken paramQQToken)
  {
    super(paramContext, paramQQAuth, paramQQToken);
    init();
  }

  public LocationApi(Context paramContext, QQToken paramQQToken)
  {
    super(paramContext, paramQQToken);
    init();
  }

  private boolean checkNetworkAvailable()
  {
    Object localObject = (ConnectivityManager)this.mContext.getSystemService("connectivity");
    if (localObject != null)
    {
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      return (localObject != null) && (((NetworkInfo)localObject).isAvailable());
    }
    return false;
  }

  private void doSearchNearby(Location paramLocation)
  {
    g.b("openSDK_LOG", "location: search mParams: " + this.mParams);
    Bundle localBundle;
    if (this.mParams != null)
    {
      localBundle = new Bundle(this.mParams);
      localBundle.putAll(composeCGIParams());
    }
    while (true)
    {
      double d1 = paramLocation.getLatitude();
      double d2 = paramLocation.getLongitude();
      localBundle.putString("appid", this.mToken.getAppId());
      if (!localBundle.containsKey("latitude"))
        localBundle.putString("latitude", String.valueOf(d1));
      if (!localBundle.containsKey("longitude"))
        localBundle.putString("longitude", String.valueOf(d2));
      if (!localBundle.containsKey("page"))
        localBundle.putString("page", String.valueOf(1));
      localBundle.putString("encrytoken", Util.encrypt("tencent&sdk&qazxc***14969%%" + this.mToken.getAccessToken() + this.mToken.getAppId() + this.mToken.getOpenId() + "qzone3.4"));
      g.b("openSDK_LOG", "location: search params: " + localBundle);
      paramLocation = new TaskRequestListener(this.mSearchListener);
      HttpUtils.requestAsync(this.mToken, this.mContext, "http://fusion.qq.com/cgi-bin/qzapps/mapp_lbs_getnear.cgi", localBundle, "GET", paramLocation);
      return;
      localBundle = composeCGIParams();
    }
  }

  private void init()
  {
    this.mLbsAgent = new LbsAgent();
    this.mHandlerThread = new HandlerThread("get_location");
    this.mHandlerThread.start();
    this.mHandler = new Handler(this.mHandlerThread.getLooper());
    this.mMainHandler = new Handler(this.mContext.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        switch (paramAnonymousMessage.what)
        {
        case 102:
        default:
        case 103:
        case 104:
        case 101:
        }
        while (true)
        {
          super.handleMessage(paramAnonymousMessage);
          return;
          g.b("openSDK_LOG", "location: verify sosocode success.");
          LocationApi.this.mLbsAgent.requestLocationUpdate(LocationApi.this.mContext, LocationApi.this);
          LocationApi.this.mMainHandler.sendEmptyMessageDelayed(101, 10000L);
          continue;
          g.b("openSDK_LOG", "location: verify sosocode failed.");
          LocationApi.this.locationFailed(-14, "定位失败，验证定位码错误！");
          continue;
          g.b("openSDK_LOG", "location: get location timeout.");
          LocationApi.this.locationFailed(-13, "定位超时，请稍后再试或检查网络状况！");
        }
      }
    };
  }

  private void locationFailed(int paramInt, String paramString)
  {
    this.mLbsAgent.removeUpdate();
    if (this.mSearchListener == null)
      return;
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("ret", paramInt);
      localJSONObject.put("errMsg", paramString);
      this.mSearchListener.onComplete(localJSONObject);
      return;
    }
    catch (JSONException paramString)
    {
      while (true)
        paramString.printStackTrace();
    }
  }

  private void locationSuccess()
  {
    this.mLbsAgent.removeUpdate();
  }

  private void report(final String paramString, final String[] paramArrayOfString)
  {
    this.mHandler.post(new Runnable()
    {
      public void run()
      {
        if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
          return;
        if ("search_nearby".equals(paramString));
        for (String str = "id_search_nearby"; ; str = "id_delete_location")
        {
          a.a(LocationApi.this.mContext, LocationApi.this.mToken, str, paramArrayOfString);
          return;
        }
      }
    });
  }

  private JSONObject unavailableNetworkJson()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("ret", -9);
      localJSONObject.put("errMsg", "网络连接异常，请检查后重试!");
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return localJSONObject;
  }

  public void deleteLocation(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    if (!checkNetworkAvailable())
    {
      if (paramIUiListener != null)
        paramIUiListener.onComplete(unavailableNetworkJson());
      return;
    }
    if (paramBundle != null)
    {
      paramActivity = new Bundle(paramBundle);
      paramActivity.putAll(composeCGIParams());
    }
    while (true)
    {
      paramActivity.putString("appid", this.mToken.getAppId());
      paramActivity.putString("timestamp", String.valueOf(System.currentTimeMillis()));
      paramActivity.putString("encrytoken", Util.encrypt("tencent&sdk&qazxc***14969%%" + this.mToken.getAccessToken() + this.mToken.getAppId() + this.mToken.getOpenId() + "qzone3.4"));
      g.b("openSDK_LOG", "location: delete params: " + paramActivity);
      paramBundle = new TaskRequestListener(paramIUiListener);
      HttpUtils.requestAsync(this.mToken, this.mContext, "http://fusion.qq.com/cgi-bin/qzapps/mapp_lbs_delete.cgi", paramActivity, "GET", paramBundle);
      report("delete_location", new String[] { "success" });
      return;
      paramActivity = composeCGIParams();
    }
  }

  public void onLocationUpdate(Location paramLocation)
  {
    doSearchNearby(paramLocation);
    locationSuccess();
    this.mMainHandler.removeMessages(101);
  }

  public void searchNearby(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    if (!checkNetworkAvailable())
    {
      if (paramIUiListener != null)
        paramIUiListener.onComplete(unavailableNetworkJson());
      return;
    }
    this.mParams = paramBundle;
    this.mSearchListener = paramIUiListener;
    this.mHandler.post(new Runnable()
    {
      public void run()
      {
        if (LocationApi.this.mLbsAgent.verifyRegCode())
        {
          Message.obtain(LocationApi.this.mMainHandler, 103).sendToTarget();
          return;
        }
        Message.obtain(LocationApi.this.mMainHandler, 104).sendToTarget();
      }
    });
  }

  private abstract class BaseRequestListener
    implements IRequestListener
  {
    private BaseRequestListener()
    {
    }

    protected abstract void handleException(Exception paramException);

    public void onConnectTimeoutException(ConnectTimeoutException paramConnectTimeoutException)
    {
      handleException(paramConnectTimeoutException);
    }

    public void onHttpStatusException(HttpUtils.HttpStatusException paramHttpStatusException)
    {
      handleException(paramHttpStatusException);
    }

    public void onIOException(IOException paramIOException)
    {
      handleException(paramIOException);
    }

    public void onJSONException(JSONException paramJSONException)
    {
      handleException(paramJSONException);
    }

    public void onMalformedURLException(MalformedURLException paramMalformedURLException)
    {
      handleException(paramMalformedURLException);
    }

    public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException paramNetworkUnavailableException)
    {
      handleException(paramNetworkUnavailableException);
    }

    public void onSocketTimeoutException(SocketTimeoutException paramSocketTimeoutException)
    {
      handleException(paramSocketTimeoutException);
    }

    public void onUnknowException(Exception paramException)
    {
      handleException(paramException);
    }
  }

  private class TaskRequestListener extends LocationApi.BaseRequestListener
  {
    private IUiListener lis;

    public TaskRequestListener(IUiListener arg2)
    {
      super(null);
      Object localObject;
      this.lis = localObject;
    }

    protected void handleException(Exception paramException)
    {
      if (this.lis != null)
        this.lis.onError(new UiError(100, paramException.getMessage(), null));
    }

    public void onComplete(JSONObject paramJSONObject)
    {
      if (this.lis != null)
        this.lis.onComplete(paramJSONObject);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.LocationApi
 * JD-Core Version:    0.6.2
 */