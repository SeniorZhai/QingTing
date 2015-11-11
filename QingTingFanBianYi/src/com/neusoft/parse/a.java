package com.neusoft.parse;

import android.graphics.Bitmap;
import android.util.Log;
import java.util.HashSet;

final class a
  implements Runnable
{
  a(AsyncImageLoader paramAsyncImageLoader, String paramString, boolean paramBoolean, AsyncImageLoader.ImageCallback paramImageCallback)
  {
  }

  public final void run()
  {
    Bitmap localBitmap = AsyncImageLoader.a().getBitmapFromUrl(this.a, this.b);
    if (this.c != null)
      Log.e("chuxl", "callback.onImageLoaded(bitmap, url);");
    this.c.onImageLoaded(localBitmap, this.a);
    AsyncImageLoader.b().remove(this.a);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.neusoft.parse.a
 * JD-Core Version:    0.6.2
 */