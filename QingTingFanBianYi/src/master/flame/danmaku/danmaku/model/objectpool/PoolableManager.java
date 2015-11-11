package master.flame.danmaku.danmaku.model.objectpool;

public abstract interface PoolableManager<T extends Poolable<T>>
{
  public abstract T newInstance();

  public abstract void onAcquired(T paramT);

  public abstract void onReleased(T paramT);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.objectpool.PoolableManager
 * JD-Core Version:    0.6.2
 */