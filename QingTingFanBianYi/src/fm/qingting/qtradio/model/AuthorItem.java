package fm.qingting.qtradio.model;

import java.util.ArrayList;
import java.util.List;

public class AuthorItem
{
  public String avatar;
  public String bgphoto;
  public int diggCount;
  public int id;
  public List<SocialInfo> lstSocialInfo = new ArrayList();
  public String name;
  public String title;
  public String type;
  public int updatetime;
  public String wid;

  public void assign(AuthorItem paramAuthorItem)
  {
    if (paramAuthorItem == null);
    while (true)
    {
      return;
      this.name = paramAuthorItem.name;
      this.wid = paramAuthorItem.wid;
      this.id = paramAuthorItem.id;
      this.type = paramAuthorItem.type;
      this.updatetime = paramAuthorItem.updatetime;
      this.title = paramAuthorItem.title;
      this.avatar = paramAuthorItem.avatar;
      this.diggCount = paramAuthorItem.diggCount;
      this.bgphoto = paramAuthorItem.bgphoto;
      if ((paramAuthorItem.lstSocialInfo != null) && (paramAuthorItem.lstSocialInfo.size() > 0))
      {
        this.lstSocialInfo.clear();
        int i = 0;
        while (i < paramAuthorItem.lstSocialInfo.size())
        {
          SocialInfo localSocialInfo = new SocialInfo();
          localSocialInfo.assign((SocialInfo)paramAuthorItem.lstSocialInfo.get(i));
          this.lstSocialInfo.add(localSocialInfo);
          i += 1;
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.AuthorItem
 * JD-Core Version:    0.6.2
 */