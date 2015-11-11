package fm.qingting.qtradio.view.im;

import fm.qingting.qtradio.im.ImBlackList;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.social.UserProfile;
import java.util.Locale;

public class SpecialContactsItem
{
  public static final int BLOCK = 3;
  public static final int FOLLOWER = 2;
  public static final int FOLLOWING = 1;
  public static final int GROUP = 0;
  private static final String[] MODELS = { "共有%d个群组", "共有%d个关注", "共有%d个粉丝", "共有%d个联系人" };
  public String name;
  public int resource;
  public int type;

  public SpecialContactsItem(String paramString, int paramInt1, int paramInt2)
  {
    this.name = paramString;
    this.resource = paramInt1;
    this.type = paramInt2;
  }

  private int getContactCnt()
  {
    switch (this.type)
    {
    default:
      return 0;
    case 0:
      return InfoManager.getInstance().getUserProfile().getGroupCnt();
    case 1:
      return InfoManager.getInstance().getUserProfile().getFollowingCnt();
    case 2:
      return InfoManager.getInstance().getUserProfile().getFollowerCnt();
    case 3:
    }
    return ImBlackList.getBlackListCnt(InfoManager.getInstance().getContext());
  }

  public String getContactsDesc()
  {
    String str = MODELS[this.type];
    return String.format(Locale.CHINESE, str, new Object[] { Integer.valueOf(getContactCnt()) });
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.SpecialContactsItem
 * JD-Core Version:    0.6.2
 */