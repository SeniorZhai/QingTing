package fm.qingting.qtradio.view.dragdrop;

import fm.qingting.framework.view.IView;

public abstract class DragDropAdapter
{
  private final int COLUMN_COUNT = 5;

  public int getColumnCount()
  {
    return 5;
  }

  public abstract int getCount();

  public abstract IView instantiateItem(int paramInt);

  public abstract boolean isFixed(int paramInt);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.dragdrop.DragDropAdapter
 * JD-Core Version:    0.6.2
 */