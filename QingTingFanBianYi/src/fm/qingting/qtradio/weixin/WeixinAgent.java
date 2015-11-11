package fm.qingting.qtradio.weixin;

import android.content.Context;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.sdk.modelmsg.SendAuth.Req;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.INodeEventListener;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.CloudCenter.OnLoginEventListerner;
import fm.qingting.qtradio.social.UserProfile;
import java.util.HashMap;
import java.util.Map;

public class WeixinAgent
  implements InfoManager.INodeEventListener
{
  public static final String APP_ID = "wx7726d8211afb6b05";
  public static final String APP_SECRET = "3b8a7bf420b180a37880bbd474e2950c";
  private static final String TAG = "WeixinAgent";
  private static final String WEIXIN_SCOPE = "snsapi_userinfo";
  private static WeixinAgent _instance;
  private static IWXAPI api = null;
  private String mAccessToken;
  private Context mContext;
  public CloudCenter.OnLoginEventListerner mLoginListener;
  private String mOpenId;
  private String mRefreshToken;
  private UserInfo mUserInfo;

  public static WeixinAgent getInstance()
  {
    if (_instance == null)
      _instance = new WeixinAgent();
    return _instance;
  }

  private void refreshAccessToken()
  {
    InfoManager.getInstance().refreshWeChatToken("wx7726d8211afb6b05", this.mRefreshToken, null);
  }

  private void saveUserInfoToDB(UserInfo paramUserInfo)
  {
    if (paramUserInfo == null);
    while (paramUserInfo.snsInfo.sns_id.equalsIgnoreCase(""))
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("site", "wechat");
    DataManager.getInstance().getData("deletedb_user_info", null, localHashMap);
    localHashMap.put("userInfo", paramUserInfo);
    DataManager.getInstance().getData("insertdb_user_info", null, localHashMap);
    SharedCfg.getInstance().setWeChatGender(paramUserInfo.snsInfo.sns_gender);
  }

  private void tryRestoreFromDB()
  {
    Object localObject = new HashMap();
    ((Map)localObject).put("site", "wechat");
    localObject = DataManager.getInstance().getData("getdb_user_info", null, (Map)localObject).getResult();
    if (((Result)localObject).getSuccess())
    {
      this.mUserInfo = ((UserInfo)((Result)localObject).getData());
      if ((this.mUserInfo != null) && (this.mUserInfo.snsInfo != null))
      {
        this.mUserInfo.snsInfo.sns_gender = SharedCfg.getInstance().getWeChatGender();
        this.mUserInfo.userKey = SharedCfg.getInstance().getWeChatUserKey();
      }
      if ((InfoManager.getInstance().getUserProfile().getUserInfo() == null) && (this.mUserInfo != null))
        InfoManager.getInstance().setUserInfo(this.mUserInfo);
    }
  }

  public void init(Context paramContext)
  {
    if (paramContext == null);
    do
    {
      return;
      this.mContext = paramContext;
      if (api == null)
      {
        api = WXAPIFactory.createWXAPI(paramContext, "wx7726d8211afb6b05", true);
        api.registerApp("wx7726d8211afb6b05");
      }
      InfoManager.getInstance().registerNodeEventListener(this, "WTI");
      InfoManager.getInstance().registerNodeEventListener(this, "WTR");
      InfoManager.getInstance().registerNodeEventListener(this, "WUI");
      paramContext = SharedCfg.getInstance().getWeChatOpenId();
      setValues(SharedCfg.getInstance().getWeChatRefreshToken(), paramContext, SharedCfg.getInstance().getWeChatAccessToken());
      tryRestoreFromDB();
    }
    while (this.mRefreshToken == null);
    refreshAccessToken();
  }

  public boolean isLoggedIn()
  {
    return this.mRefreshToken != null;
  }

  public void login(CloudCenter.OnLoginEventListerner paramOnLoginEventListerner)
  {
    this.mLoginListener = paramOnLoginEventListerner;
    paramOnLoginEventListerner = new SendAuth.Req();
    paramOnLoginEventListerner.scope = "snsapi_userinfo";
    paramOnLoginEventListerner.state = "Master";
    api.sendReq(paramOnLoginEventListerner);
  }

  public void logout()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("site", "wechat");
    DataManager.getInstance().getData("deletedb_user_info", null, localHashMap);
    SharedCfg.getInstance().removeWeChatOpenId();
    SharedCfg.getInstance().removeWeChatRefreshToken();
    SharedCfg.getInstance().removeWeChatAccessToken();
    SharedCfg.getInstance().removeWeChatUserKey();
    SharedCfg.getInstance().removeWeChatGender();
    this.mRefreshToken = null;
  }

  public void onNodeUpdated(Object paramObject, String paramString)
  {
  }

  public void onNodeUpdated(Object paramObject, Map<String, String> paramMap, String paramString)
  {
    if (paramString.equalsIgnoreCase("WTI"))
    {
      paramObject = JSON.parseObject((String)paramObject);
      if (!paramObject.containsKey("errcode"))
      {
        paramMap = paramObject.getString("openid");
        setValues(paramObject.getString("refresh_token"), paramMap, paramObject.getString("access_token"));
        refreshUserInfo();
        if (this.mLoginListener != null)
          this.mLoginListener.onLoginSuccessed(6);
        Log.d("WeixinAgent", "登录到微信成功");
      }
    }
    do
    {
      do
      {
        do
        {
          return;
          if (!paramString.equalsIgnoreCase("WTR"))
            break;
          paramObject = JSON.parseObject((String)paramObject);
        }
        while (paramObject.containsKey("errcode"));
        paramMap = paramObject.getString("openid");
        setValues(paramObject.getString("refresh_token"), paramMap, paramObject.getString("access_token"));
        refreshUserInfo();
        Log.d("WeixinAgent", "刷新微信登录状态成功");
        return;
      }
      while (!paramString.equalsIgnoreCase("WUI"));
      paramString = JSON.parseObject((String)paramObject);
    }
    while (paramString.containsKey("errcode"));
    paramObject = paramString.getString("openid");
    paramMap = paramString.getString("nickname");
    int i = paramString.getIntValue("sex");
    paramString = paramString.getString("headimgurl");
    this.mUserInfo = new UserInfo();
    this.mUserInfo.snsInfo.sns_name = paramMap;
    this.mUserInfo.snsInfo.sns_site = "wechat";
    this.mUserInfo.snsInfo.sns_id = paramObject;
    this.mUserInfo.snsInfo.sns_avatar = paramString;
    paramMap = this.mUserInfo.snsInfo;
    if (i == 1);
    for (paramObject = "m"; ; paramObject = "f")
    {
      paramMap.sns_gender = paramObject;
      saveUserInfoToDB(this.mUserInfo);
      InfoManager.getInstance().setUserInfo(this.mUserInfo);
      Log.d("WeixinAgent", "获取到微信用户信息成功");
      return;
    }
  }

  public void refreshUserInfo()
  {
    InfoManager.getInstance().getWeChatUserInfo(this.mAccessToken, this.mOpenId, null);
  }

  public void setValues(String paramString1, String paramString2, String paramString3)
  {
    this.mRefreshToken = paramString1;
    this.mAccessToken = paramString3;
    this.mOpenId = paramString2;
    SharedCfg.getInstance().setWeChatAccessToken(this.mAccessToken);
    SharedCfg.getInstance().setWeChatOpenId(this.mOpenId);
    SharedCfg.getInstance().setWeChatRefreshToken(this.mRefreshToken);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.weixin.WeixinAgent
 * JD-Core Version:    0.6.2
 */