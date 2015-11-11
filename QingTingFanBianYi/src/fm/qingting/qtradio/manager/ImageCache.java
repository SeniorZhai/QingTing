package fm.qingting.qtradio.manager;

import android.graphics.Bitmap;
import android.graphics.Rect;

class ImageCache
{
  private Rect dstRect;
  private String mId;
  private Bitmap maskBitmap;
  private int offset;
  private Bitmap originBitmap;

  public ImageCache(String paramString, Bitmap paramBitmap1, Bitmap paramBitmap2, Rect paramRect, int paramInt)
  {
    this.mId = paramString;
    this.originBitmap = paramBitmap1;
    this.maskBitmap = paramBitmap2;
    this.dstRect = paramRect;
    this.offset = paramInt;
  }

  public Bitmap getBitmap()
  {
    return this.originBitmap;
  }

  public String getId()
  {
    return this.mId;
  }

  public Bitmap getMaskBitmap()
  {
    return this.maskBitmap;
  }

  public int getOffset()
  {
    return this.offset;
  }

  public Rect getRect()
  {
    return this.dstRect;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.manager.ImageCache
 * JD-Core Version:    0.6.2
 */