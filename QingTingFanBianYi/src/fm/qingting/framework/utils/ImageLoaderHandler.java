package fm.qingting.framework.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

public abstract interface ImageLoaderHandler
{
  public abstract void loadImageFinish(boolean paramBoolean, String paramString, Bitmap paramBitmap, int paramInt1, int paramInt2);

  public abstract void updateImageViewFinish(boolean paramBoolean, ImageView paramImageView, String paramString, Bitmap paramBitmap);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.ImageLoaderHandler
 * JD-Core Version:    0.6.2
 */