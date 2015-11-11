package master.flame.danmaku.danmaku.model.objectpool;

public abstract interface Poolable<T>
{
  public abstract T getNextPoolable();

  public abstract boolean isPooled();

  public abstract void setNextPoolable(T paramT);

  public abstract void setPooled(boolean paramBoolean);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.objectpool.Poolable
 * JD-Core Version:    0.6.2
 */