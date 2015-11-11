package master.flame.danmaku.danmaku.model.objectpool;

public abstract interface Pool<T extends Poolable<T>>
{
  public abstract T acquire();

  public abstract void release(T paramT);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.objectpool.Pool
 * JD-Core Version:    0.6.2
 */