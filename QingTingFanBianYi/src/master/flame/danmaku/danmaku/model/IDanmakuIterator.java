package master.flame.danmaku.danmaku.model;

public abstract interface IDanmakuIterator
{
  public abstract boolean hasNext();

  public abstract BaseDanmaku next();

  public abstract void remove();

  public abstract void reset();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.IDanmakuIterator
 * JD-Core Version:    0.6.2
 */