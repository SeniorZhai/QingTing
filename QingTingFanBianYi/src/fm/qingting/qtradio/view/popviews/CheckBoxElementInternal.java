package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.AbsCheckBoxElement;

class CheckBoxElementInternal extends AbsCheckBoxElement
{
  private final Paint mPaint = new Paint();

  public CheckBoxElementInternal(Context paramContext)
  {
    super(paramContext);
  }

  protected void drawCheckState(Canvas paramCanvas)
  {
    BitmapResourceCache localBitmapResourceCache = BitmapResourceCache.getInstance();
    Resources localResources = getContext().getResources();
    int j = this.mOwnerId;
    if (isChecked());
    for (int i = 2130837716; ; i = 2130837822)
    {
      paramCanvas.drawBitmap(localBitmapResourceCache.getResourceCacheByParent(localResources, j, i), null, getBound(), this.mPaint);
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.CheckBoxElementInternal
 * JD-Core Version:    0.6.2
 */