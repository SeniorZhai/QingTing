package fm.qingting.qtradio.user;

import android.content.Context;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.utils.DeviceInfo;

public class AppUserInfo
{
  public static final int QINGTING = 1;
  private static AppUserInfo _instance;
  private Context mContext;
  private UserInfo mQingTingUser = new UserInfo();

  public static AppUserInfo getInstance()
  {
    if (_instance == null)
      _instance = new AppUserInfo();
    return _instance;
  }

  public UserInfo getUserInfo(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return null;
    case 1:
    }
    return this.mQingTingUser;
  }

  public void init(Context paramContext)
  {
    if (paramContext == null)
      return;
    this.mContext = paramContext;
    this.mQingTingUser.userId = DeviceInfo.getUniqueId(paramContext);
    this.mQingTingUser.snsInfo.sns_id = this.mQingTingUser.userId;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.user.AppUserInfo
 * JD-Core Version:    0.6.2
 */