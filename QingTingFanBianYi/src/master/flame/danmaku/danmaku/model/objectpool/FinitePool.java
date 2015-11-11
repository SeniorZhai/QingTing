package master.flame.danmaku.danmaku.model.objectpool;

import java.io.PrintStream;

class FinitePool<T extends Poolable<T>>
  implements Pool<T>
{
  private final boolean mInfinite;
  private final int mLimit;
  private final PoolableManager<T> mManager;
  private int mPoolCount;
  private T mRoot;

  FinitePool(PoolableManager<T> paramPoolableManager)
  {
    this.mManager = paramPoolableManager;
    this.mLimit = 0;
    this.mInfinite = true;
  }

  FinitePool(PoolableManager<T> paramPoolableManager, int paramInt)
  {
    if (paramInt <= 0)
      throw new IllegalArgumentException("The pool limit must be > 0");
    this.mManager = paramPoolableManager;
    this.mLimit = paramInt;
    this.mInfinite = false;
  }

  public T acquire()
  {
    Poolable localPoolable;
    if (this.mRoot != null)
    {
      localPoolable = this.mRoot;
      this.mRoot = ((Poolable)localPoolable.getNextPoolable());
      this.mPoolCount -= 1;
    }
    while (true)
    {
      if (localPoolable != null)
      {
        localPoolable.setNextPoolable(null);
        localPoolable.setPooled(false);
        this.mManager.onAcquired(localPoolable);
      }
      return localPoolable;
      localPoolable = this.mManager.newInstance();
    }
  }

  public void release(T paramT)
  {
    if (!paramT.isPooled())
    {
      if ((this.mInfinite) || (this.mPoolCount < this.mLimit))
      {
        this.mPoolCount += 1;
        paramT.setNextPoolable(this.mRoot);
        paramT.setPooled(true);
        this.mRoot = paramT;
      }
      this.mManager.onReleased(paramT);
      return;
    }
    System.out.print("[FinitePool] Element is already in pool: " + paramT);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.objectpool.FinitePool
 * JD-Core Version:    0.6.2
 */