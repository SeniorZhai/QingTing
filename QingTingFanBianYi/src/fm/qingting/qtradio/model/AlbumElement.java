package fm.qingting.qtradio.model;

import java.util.ArrayList;
import java.util.List;

public class AlbumElement
{
  public String catId;
  public String catname;
  public String desc;
  public String id;
  public String is_novel;
  public String latest_program_title;
  public String latest_program_updatetime;
  public List<AuthorItem> lstAuthors = new ArrayList();
  public List<AuthorItem> lstBroadcasters = new ArrayList();
  public String novel_status;
  public int program_count;
  public String source;
  public String thumb;
  public String title;

  public void addAuthorItem(AuthorItem paramAuthorItem)
  {
    this.lstAuthors.add(paramAuthorItem);
  }

  public void addBroadcasterItem(AuthorItem paramAuthorItem)
  {
    this.lstBroadcasters.add(paramAuthorItem);
  }

  public String getAuthors()
  {
    Object localObject;
    if ((this.lstAuthors == null) || (this.lstAuthors.size() == 0))
    {
      localObject = "无";
      return localObject;
    }
    String str = "";
    int i = 0;
    while (true)
    {
      localObject = str;
      if (i >= this.lstAuthors.size())
        break;
      str = str + ((AuthorItem)this.lstAuthors.get(i)).name;
      str = str + " ";
      i += 1;
    }
  }

  public String getBroadcasters()
  {
    Object localObject;
    if ((this.lstBroadcasters == null) || (this.lstBroadcasters.size() == 0))
    {
      localObject = "无";
      return localObject;
    }
    String str = "";
    int i = 0;
    while (true)
    {
      localObject = str;
      if (i >= this.lstBroadcasters.size())
        break;
      str = str + ((AuthorItem)this.lstBroadcasters.get(i)).name;
      str = str + " ";
      i += 1;
    }
  }

  public void setAlbumElement(AlbumElement paramAlbumElement)
  {
    this.id = paramAlbumElement.id;
    this.catId = paramAlbumElement.catId;
    this.catname = paramAlbumElement.catname;
    this.title = paramAlbumElement.title;
    this.desc = paramAlbumElement.desc;
    this.source = paramAlbumElement.source;
    this.lstBroadcasters = null;
    this.lstBroadcasters = new ArrayList(paramAlbumElement.lstBroadcasters);
    this.lstAuthors = null;
    this.lstAuthors = new ArrayList(paramAlbumElement.lstAuthors);
    this.is_novel = paramAlbumElement.is_novel;
    this.novel_status = paramAlbumElement.novel_status;
    this.program_count = paramAlbumElement.program_count;
    this.thumb = paramAlbumElement.thumb;
    this.latest_program_updatetime = paramAlbumElement.latest_program_updatetime;
    this.latest_program_title = paramAlbumElement.latest_program_title;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.AlbumElement
 * JD-Core Version:    0.6.2
 */