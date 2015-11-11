package fm.qingting.qtradio.model;

import java.util.ArrayList;

public class AlbumInfo
{
  public ArrayList<AlbumElement> lstAlbumElements = new ArrayList();
  public String title = null;

  public void addAlbumElement(AlbumElement paramAlbumElement)
  {
    this.lstAlbumElements.add(paramAlbumElement);
  }

  public String getAlbumCatId()
  {
    if ((this.lstAlbumElements == null) || (this.lstAlbumElements.size() == 0))
      return "228";
    return ((AlbumElement)this.lstAlbumElements.get(0)).catId;
  }

  public String getAlbumCatName()
  {
    if ((this.lstAlbumElements == null) || (this.lstAlbumElements.size() == 0))
      return "点播";
    return ((AlbumElement)this.lstAlbumElements.get(0)).catname;
  }

  public ArrayList<AlbumElement> getAlbumElementsList()
  {
    return this.lstAlbumElements;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.AlbumInfo
 * JD-Core Version:    0.6.2
 */