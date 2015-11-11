package fm.qingting.framework.data;

import java.util.List;

public class ListData
{
  public List data;
  public Navigation navigation;
  public String title;

  public ListData(List paramList, Navigation paramNavigation)
  {
    this.data = paramList;
    this.navigation = paramNavigation;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.ListData
 * JD-Core Version:    0.6.2
 */