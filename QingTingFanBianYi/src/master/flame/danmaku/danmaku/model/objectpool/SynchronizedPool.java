package master.flame.danmaku.danmaku.model.objectpool;

class SynchronizedPool<T extends Poolable<T>>
  implements Pool<T>
{
  private final Object mLock;
  private final Pool<T> mPool;

  public SynchronizedPool(Pool<T> paramPool)
  {
    this.mPool = paramPool;
    this.mLock = this;
  }

  public SynchronizedPool(Pool<T> paramPool, Object paramObject)
  {
    this.mPool = paramPool;
    this.mLock = paramObject;
  }

  public T acquire()
  {
    synchronized (this.mLock)
    {
      Poolable localPoolable = this.mPool.acquire();
      return localPoolable;
    }
  }

  public void release(T paramT)
  {
    synchronized (this.mLock)
    {
      this.mPool.release(paramT);
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.objectpool.SynchronizedPool
 * JD-Core Version:    0.6.2
 */