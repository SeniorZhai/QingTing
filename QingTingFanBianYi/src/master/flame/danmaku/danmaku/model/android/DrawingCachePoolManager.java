package master.flame.danmaku.danmaku.model.android;

import master.flame.danmaku.danmaku.model.objectpool.PoolableManager;

public class DrawingCachePoolManager
  implements PoolableManager<DrawingCache>
{
  public DrawingCache newInstance()
  {
    return null;
  }

  public void onAcquired(DrawingCache paramDrawingCache)
  {
  }

  public void onReleased(DrawingCache paramDrawingCache)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.android.DrawingCachePoolManager
 * JD-Core Version:    0.6.2
 */