package fm.qingting.framework.adapter;

import java.util.List;

public class SortAdapter extends CustomizedAdapter
{
  private int mDragStartPosition = -1;
  private int mEmptyPosition = -1;

  public SortAdapter(List<Object> paramList, IAdapterIViewFactory paramIAdapterIViewFactory)
  {
    super(paramList, paramIAdapterIViewFactory);
  }

  public int getDragPosition()
  {
    return this.mDragStartPosition;
  }

  public int getEmptyPosition()
  {
    return this.mEmptyPosition;
  }

  public Object getItem(int paramInt)
  {
    if (this.data == null);
    while ((paramInt == this.mEmptyPosition) || (this.data.size() <= paramInt))
      return null;
    if ((paramInt >= this.mDragStartPosition) && (paramInt <= this.mEmptyPosition))
      return this.data.get(paramInt + 1);
    if ((paramInt >= this.mEmptyPosition) && (paramInt <= this.mDragStartPosition))
      return this.data.get(paramInt - 1);
    return this.data.get(paramInt);
  }

  public void setDragPosition(int paramInt)
  {
    this.mDragStartPosition = paramInt;
  }

  public void setEmptyPosition(int paramInt)
  {
    this.mEmptyPosition = paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.adapter.SortAdapter
 * JD-Core Version:    0.6.2
 */