package fm.qingting.qtradio.tencentAgent;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.social.CloudCenter.OnLoginEventListerner;
import fm.qingting.qtradio.social.UserProfile;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class QQAgent
{
  private static String APP_ID = "100387802";
  private static final String TAG = "QQAgent";
  private static QQAgent instance;
  private static Tencent mTencent;
  private boolean hasRestoredFromDB = false;
  private Context mContext;
  private IUiListener mGetQQUserInfoListener = new IUiListener()
  {
    public void onCancel()
    {
    }

    public void onComplete(Object paramAnonymousObject)
    {
      if (paramAnonymousObject == null)
      {
        Log.e("QQAgent", "获取用户信息失败,返回为空");
        return;
      }
      Object localObject2 = (JSONObject)paramAnonymousObject;
      if (((JSONObject)localObject2).length() == 0)
      {
        Log.e("QQAgent", "获取用户信息失败,返回为空");
        return;
      }
      paramAnonymousObject = ((JSONObject)localObject2).optString("nickname");
      Object localObject1 = ((JSONObject)localObject2).optString("figureurl_qq_1");
      localObject2 = ((JSONObject)localObject2).optString("gender");
      QQAgent.access$302(QQAgent.this, new fm.qingting.qtradio.room.UserInfo());
      QQAgent.this.mUserInfo.snsInfo.sns_name = paramAnonymousObject;
      QQAgent.this.mUserInfo.snsInfo.sns_site = "qq";
      QQAgent.this.mUserInfo.snsInfo.sns_id = QQAgent.mTencent.getOpenId();
      QQAgent.this.mUserInfo.snsInfo.sns_avatar = ((String)localObject1);
      localObject1 = QQAgent.this.mUserInfo.snsInfo;
      if (((String)localObject2).equals("男"));
      for (paramAnonymousObject = "m"; ; paramAnonymousObject = "f")
      {
        ((SnsInfo)localObject1).sns_gender = paramAnonymousObject;
        QQAgent.this.saveUserInfoToDB();
        InfoManager.getInstance().setUserInfo(QQAgent.this.mUserInfo);
        return;
      }
    }

    public void onError(UiError paramAnonymousUiError)
    {
      Log.e("QQAgent", "获取用户信息失败");
    }
  };
  private CloudCenter.OnLoginEventListerner mLoginEventListerner;
  private IUiListener mLoginListener = new IUiListener()
  {
    public void onCancel()
    {
    }

    public void onComplete(Object paramAnonymousObject)
    {
      if (!QQAgent.this.parseResponse(paramAnonymousObject))
        if (QQAgent.this.mLoginEventListerner != null)
          QQAgent.this.mLoginEventListerner.onLoginFailed(5);
      do
      {
        return;
        QQAgent.this.getQQUserInfo();
      }
      while (QQAgent.this.mLoginEventListerner == null);
      QQAgent.this.mLoginEventListerner.onLoginSuccessed(5);
    }

    public void onError(UiError paramAnonymousUiError)
    {
      Log.e("QQAgent", "登录失败");
      if (QQAgent.this.mLoginEventListerner != null)
        QQAgent.this.mLoginEventListerner.onLoginFailed(5);
    }
  };
  private fm.qingting.qtradio.room.UserInfo mUserInfo;

  public static QQAgent getInstance()
  {
    if (instance == null)
      instance = new QQAgent();
    return instance;
  }

  private void getQQUserInfo()
  {
    new com.tencent.connect.UserInfo(this.mContext, mTencent.getQQToken()).getUserInfo(this.mGetQQUserInfoListener);
  }

  private boolean parseResponse(Object paramObject)
  {
    if (paramObject == null)
    {
      Log.e("QQAgent", "登录失败,返回为空");
      return false;
    }
    Object localObject = (JSONObject)paramObject;
    if (((JSONObject)localObject).length() == 0)
    {
      Log.e("QQAgent", "登录失败,返回为空");
      return false;
    }
    try
    {
      paramObject = ((JSONObject)localObject).getString("access_token");
      String str = ((JSONObject)localObject).getString("expires_in");
      localObject = ((JSONObject)localObject).getString("openid");
      if ((!TextUtils.isEmpty(paramObject)) && (!TextUtils.isEmpty(str)) && (!TextUtils.isEmpty((CharSequence)localObject)))
      {
        mTencent.setAccessToken(paramObject, str);
        mTencent.setOpenId((String)localObject);
        SharedCfg.getInstance().setQQAccessToken(paramObject);
        SharedCfg.getInstance().setQQExpiresTime(str);
        SharedCfg.getInstance().setQQOpenId((String)localObject);
      }
      Log.d("QQAgent", "登录成功");
      return true;
    }
    catch (Exception paramObject)
    {
      Log.e("QQAgent", "登录失败,返回值错误");
    }
    return false;
  }

  private void saveUserInfoToDB()
  {
    if (this.mUserInfo == null);
    while (this.mUserInfo.snsInfo.sns_id.equalsIgnoreCase(""))
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("site", "qq");
    DataManager.getInstance().getData("deletedb_user_info", null, localHashMap);
    localHashMap.put("userInfo", this.mUserInfo);
    DataManager.getInstance().getData("insertdb_user_info", null, localHashMap);
    SharedCfg.getInstance().setQQGender(this.mUserInfo.snsInfo.sns_gender);
  }

  private void tryRestoreFromDB()
  {
    if ((this.hasRestoredFromDB) || (!mTencent.isSessionValid()));
    Object localObject;
    do
    {
      return;
      localObject = new HashMap();
      ((Map)localObject).put("site", "qq");
      localObject = DataManager.getInstance().getData("getdb_user_info", null, (Map)localObject).getResult();
    }
    while (!((Result)localObject).getSuccess());
    this.mUserInfo = ((fm.qingting.qtradio.room.UserInfo)((Result)localObject).getData());
    if ((this.mUserInfo != null) && (this.mUserInfo.snsInfo != null))
    {
      this.mUserInfo.snsInfo.sns_gender = SharedCfg.getInstance().getQQGender();
      this.mUserInfo.userKey = SharedCfg.getInstance().getQQUserKey();
    }
    if ((InfoManager.getInstance().getUserProfile().getUserInfo() == null) && (this.mUserInfo != null))
      InfoManager.getInstance().setUserInfo(this.mUserInfo);
    this.hasRestoredFromDB = true;
  }

  protected Context getContext()
  {
    return this.mContext;
  }

  public void init(Context paramContext)
  {
    try
    {
      this.mContext = paramContext;
      if (mTencent == null)
      {
        mTencent = Tencent.createInstance(APP_ID, paramContext);
        paramContext = SharedCfg.getInstance().getQQAccessToken();
        String str1 = SharedCfg.getInstance().getQQExpireTime();
        String str2 = SharedCfg.getInstance().getQQOpenId();
        if ((paramContext != null) && (str1 != null) && (str2 != null))
        {
          mTencent.setAccessToken(paramContext, str1);
          mTencent.setOpenId(str2);
        }
      }
      tryRestoreFromDB();
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  public boolean isLoggedIn()
  {
    return mTencent.isSessionValid();
  }

  public void login(CloudCenter.OnLoginEventListerner paramOnLoginEventListerner)
  {
    this.mLoginEventListerner = paramOnLoginEventListerner;
    mTencent.login((Activity)this.mContext, null, this.mLoginListener);
  }

  public void logout()
  {
    mTencent.logout(this.mContext);
    HashMap localHashMap = new HashMap();
    localHashMap.put("site", "qq");
    DataManager.getInstance().getData("deletedb_user_info", null, localHashMap);
    SharedCfg.getInstance().removeQQAccessToken();
    SharedCfg.getInstance().removeQQExpiresTime();
    SharedCfg.getInstance().removeQQOpenId();
    SharedCfg.getInstance().removeQQGender();
    SharedCfg.getInstance().removeQQUserKey();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.tencentAgent.QQAgent
 * JD-Core Version:    0.6.2
 */