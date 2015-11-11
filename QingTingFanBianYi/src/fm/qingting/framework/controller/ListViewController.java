package fm.qingting.framework.controller;

import android.content.Context;
import fm.qingting.framework.view.IListView;
import fm.qingting.framework.view.IView;

public abstract class ListViewController extends ViewController
  implements IListViewController
{
  protected IListView listView;

  public ListViewController(Context paramContext)
  {
    super(paramContext);
  }

  public ListViewController(Context paramContext, IListView paramIListView)
  {
    super(paramContext);
    attachView(paramIListView);
  }

  public void attachView(IListView paramIListView)
  {
    attachView(paramIListView);
  }

  public void attachView(IView paramIView)
  {
    super.attachView(paramIView);
    if ((paramIView != null) && ((paramIView instanceof IListView)))
    {
      this.listView = ((IListView)paramIView);
      return;
    }
    this.listView = null;
  }

  public void detachView()
  {
    super.detachView();
    this.listView = null;
  }

  protected <T extends Enum<T>> T getType(Class<T> paramClass, String paramString)
  {
    try
    {
      paramClass = Enum.valueOf(paramClass, paramString.toUpperCase());
      return paramClass;
    }
    catch (IllegalArgumentException paramClass)
    {
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.controller.ListViewController
 * JD-Core Version:    0.6.2
 */