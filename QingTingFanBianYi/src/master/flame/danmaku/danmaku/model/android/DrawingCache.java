package master.flame.danmaku.danmaku.model.android;

import android.graphics.Bitmap;
import master.flame.danmaku.danmaku.model.IDrawingCache;
import master.flame.danmaku.danmaku.model.objectpool.Poolable;

public class DrawingCache
  implements IDrawingCache<DrawingCacheHolder>, Poolable<DrawingCache>
{
  private DrawingCacheHolder mHolder = new DrawingCacheHolder();
  private boolean mIsPooled;
  private DrawingCache mNextElement;
  private int mSize = 0;
  private int referenceCount = 0;

  public void build(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    DrawingCacheHolder localDrawingCacheHolder = this.mHolder;
    if (localDrawingCacheHolder == null)
      localDrawingCacheHolder = new DrawingCacheHolder(paramInt1, paramInt2, paramInt3);
    while (true)
    {
      this.mHolder = localDrawingCacheHolder;
      this.mSize = (this.mHolder.bitmap.getRowBytes() * this.mHolder.bitmap.getHeight());
      return;
      localDrawingCacheHolder.buildCache(paramInt1, paramInt2, paramInt3, paramBoolean);
    }
  }

  public void decreaseReference()
  {
    this.referenceCount -= 1;
  }

  public void destroy()
  {
    if (this.mHolder != null)
      this.mHolder.recycle();
    this.mSize = 0;
    this.referenceCount = 0;
  }

  public void erase()
  {
    DrawingCacheHolder localDrawingCacheHolder = this.mHolder;
    if (localDrawingCacheHolder == null)
      return;
    localDrawingCacheHolder.erase();
  }

  public DrawingCacheHolder get()
  {
    if ((this.mHolder == null) || (this.mHolder.bitmap == null))
      return null;
    return this.mHolder;
  }

  public DrawingCache getNextPoolable()
  {
    return this.mNextElement;
  }

  public boolean hasReferences()
  {
    return this.referenceCount > 0;
  }

  public int height()
  {
    if (this.mHolder != null)
      return this.mHolder.height;
    return 0;
  }

  public void increaseReference()
  {
    this.referenceCount += 1;
  }

  public boolean isPooled()
  {
    return this.mIsPooled;
  }

  public void setNextPoolable(DrawingCache paramDrawingCache)
  {
    this.mNextElement = paramDrawingCache;
  }

  public void setPooled(boolean paramBoolean)
  {
    this.mIsPooled = paramBoolean;
  }

  public int size()
  {
    if (this.mHolder != null)
      return this.mSize;
    return 0;
  }

  public int width()
  {
    if (this.mHolder != null)
      return this.mHolder.width;
    return 0;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.android.DrawingCache
 * JD-Core Version:    0.6.2
 */