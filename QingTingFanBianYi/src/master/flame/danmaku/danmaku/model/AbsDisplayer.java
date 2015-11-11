package master.flame.danmaku.danmaku.model;

public abstract class AbsDisplayer<T>
  implements IDisplayer
{
  public abstract T getExtraData();

  public boolean isHardwareAccelerated()
  {
    return false;
  }

  public abstract void setExtraData(T paramT);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.AbsDisplayer
 * JD-Core Version:    0.6.2
 */