package master.flame.danmaku.danmaku.model.objectpool;

public class Pools
{
  public static <T extends Poolable<T>> Pool<T> finitePool(PoolableManager<T> paramPoolableManager, int paramInt)
  {
    return new FinitePool(paramPoolableManager, paramInt);
  }

  public static <T extends Poolable<T>> Pool<T> simplePool(PoolableManager<T> paramPoolableManager)
  {
    return new FinitePool(paramPoolableManager);
  }

  public static <T extends Poolable<T>> Pool<T> synchronizedPool(Pool<T> paramPool)
  {
    return new SynchronizedPool(paramPool);
  }

  public static <T extends Poolable<T>> Pool<T> synchronizedPool(Pool<T> paramPool, Object paramObject)
  {
    return new SynchronizedPool(paramPool, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.objectpool.Pools
 * JD-Core Version:    0.6.2
 */