package fm.qingting.framework.utils;

import android.graphics.Bitmap;

class BitmapCache
{
  public Bitmap cache;
  public int id;
  public int retain = 0;

  public BitmapCache(Bitmap paramBitmap, int paramInt)
  {
    this.cache = paramBitmap;
    this.id = paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.BitmapCache
 * JD-Core Version:    0.6.2
 */