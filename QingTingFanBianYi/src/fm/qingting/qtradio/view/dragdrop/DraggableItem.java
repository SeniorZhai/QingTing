package fm.qingting.qtradio.view.dragdrop;

public abstract interface DraggableItem
{
  public static final int DRAG = 1;
  public static final int NORMAL = 0;

  public abstract int getState();

  public abstract void setState(int paramInt);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.dragdrop.DraggableItem
 * JD-Core Version:    0.6.2
 */