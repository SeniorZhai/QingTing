package fm.qingting.framework.model;

import android.view.View;

public class NavigationBarItem
{
  protected View customeView = null;
  private int textColor = 0;
  protected String title = null;

  public NavigationBarItem(View paramView)
  {
    this.customeView = paramView;
  }

  public NavigationBarItem(String paramString)
  {
    this.title = paramString;
  }

  public NavigationBarItem(String paramString, int paramInt)
  {
    this(paramString);
    this.textColor = paramInt;
  }

  public boolean available()
  {
    return (this.title != null) || (this.customeView != null);
  }

  public View getCustomeView()
  {
    return this.customeView;
  }

  public int getTextColor()
  {
    return this.textColor;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setCustomeView(View paramView)
  {
    this.customeView = paramView;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.model.NavigationBarItem
 * JD-Core Version:    0.6.2
 */