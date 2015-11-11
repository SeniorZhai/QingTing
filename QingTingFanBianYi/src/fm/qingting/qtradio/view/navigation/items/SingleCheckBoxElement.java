package fm.qingting.qtradio.view.navigation.items;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.AbsCheckBoxElement;

class SingleCheckBoxElement extends AbsCheckBoxElement
{
  private final Paint mPaint = new Paint();

  public SingleCheckBoxElement(Context paramContext)
  {
    super(paramContext);
  }

  protected void drawCheckState(Canvas paramCanvas)
  {
    Rect localRect = getBound();
    if ((this.mTranslationX != 0) || (this.mTranslationY != 0))
      localRect.offset(this.mTranslationX, this.mTranslationY);
    BitmapResourceCache localBitmapResourceCache = BitmapResourceCache.getInstance();
    Resources localResources = getContext().getResources();
    int j = this.mOwnerId;
    if (isChecked());
    for (int i = 2130837867; ; i = 2130837866)
    {
      paramCanvas.drawBitmap(localBitmapResourceCache.getResourceCacheByParent(localResources, j, i), null, localRect, this.mPaint);
      if ((this.mTranslationX != 0) || (this.mTranslationY != 0))
        localRect.offset(-this.mTranslationX, -this.mTranslationY);
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.navigation.items.SingleCheckBoxElement
 * JD-Core Version:    0.6.2
 */