package fm.qingting.framework.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import com.android.volley.toolbox.ImageLoader.ImageCache;

public class ImageCacheImpl
  implements ImageLoader.ImageCache
{
  private LruCache<String, Bitmap> mCache;

  public ImageCacheImpl(int paramInt)
  {
    this.mCache = new LruCache(paramInt)
    {
      protected void entryRemoved(boolean paramAnonymousBoolean, String paramAnonymousString, Bitmap paramAnonymousBitmap1, Bitmap paramAnonymousBitmap2)
      {
        paramAnonymousBitmap1.recycle();
      }

      protected int sizeOf(String paramAnonymousString, Bitmap paramAnonymousBitmap)
      {
        return paramAnonymousBitmap.getRowBytes() * paramAnonymousBitmap.getHeight() / 1024;
      }
    };
  }

  public Bitmap getBitmap(String paramString)
  {
    return (Bitmap)this.mCache.get(paramString);
  }

  public void putBitmap(String paramString, Bitmap paramBitmap)
  {
    this.mCache.put(paramString, paramBitmap);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.ImageCacheImpl
 * JD-Core Version:    0.6.2
 */