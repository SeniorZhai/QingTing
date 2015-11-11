package fm.qingting.qtradio.view.im.sortpinyin;

import fm.qingting.qtradio.room.UserInfo;

public class SortModel
{
  public UserInfo dtc;
  public String sortLetters;

  public SortModel(UserInfo paramUserInfo, String paramString)
  {
    this.dtc = paramUserInfo;
    this.sortLetters = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.sortpinyin.SortModel
 * JD-Core Version:    0.6.2
 */