package com.neusoft.ssp.api;

import android.graphics.Bitmap;
import com.neusoft.parse.AsyncImageLoader.ImageCallback;

final class d
  implements AsyncImageLoader.ImageCallback
{
  d(SSP_NEW_QT_FM_API paramSSP_NEW_QT_FM_API, Object paramObject, int paramInt, String paramString)
  {
  }

  public final void onImageLoaded(Bitmap paramBitmap, String paramString)
  {
    this.a.LogChuxl("onImageLoaded");
    if (paramBitmap != null)
    {
      this.a.LogChuxl("bitmap != null");
      paramBitmap = SSP_NEW_QT_FM_API.zoomImage(paramBitmap, 280.0D, 280.0D);
      this.a.replyImageToCar(this.b, 0, this.c, this.d, paramBitmap);
      return;
    }
    this.a.LogChuxl("下载失败");
    this.a.replyImageToCar(this.b, 1, this.c, this.d, paramBitmap);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.neusoft.ssp.api.d
 * JD-Core Version:    0.6.2
 */