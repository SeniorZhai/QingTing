package fm.qingting.framework.view;

import android.widget.ListAdapter;

public abstract interface IListView extends IView
{
  public abstract ListAdapter getAdapter();

  public abstract void setAdapter(ListAdapter paramListAdapter);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.view.IListView
 * JD-Core Version:    0.6.2
 */