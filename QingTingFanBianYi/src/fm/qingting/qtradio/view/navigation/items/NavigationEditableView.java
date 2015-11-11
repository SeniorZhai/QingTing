package fm.qingting.qtradio.view.navigation.items;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import fm.qingting.framework.utils.ImageLoader;
import fm.qingting.framework.utils.ImageLoaderHandler;
import fm.qingting.framework.view.ViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class NavigationEditableView extends ViewImpl
  implements ImageLoaderHandler
{
  private DrawFilter filter = SkinManager.getInstance().getDrawFilter();
  private Paint mPaint = new Paint();
  private final ViewLayout middleAreaLayout = ViewLayout.createViewLayoutWithBoundsLT(400, 114, 480, 114, 40, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CH);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private String url;

  public NavigationEditableView(Context paramContext)
  {
    super(paramContext);
  }

  private void drawImage(Canvas paramCanvas)
  {
    Bitmap localBitmap = ImageLoader.getInstance(getContext()).getImage(this.url, this.middleAreaLayout.width, this.middleAreaLayout.height);
    if (localBitmap == null)
    {
      ImageLoader.getInstance(getContext()).loadImage(this.url, null, this, this.middleAreaLayout.width, this.middleAreaLayout.height, this);
      return;
    }
    paramCanvas.drawBitmap(localBitmap, null, new Rect(this.middleAreaLayout.leftMargin, (this.standardLayout.height - this.middleAreaLayout.height) / 2, this.middleAreaLayout.leftMargin + this.middleAreaLayout.width, (this.standardLayout.height + this.middleAreaLayout.height) / 2), this.mPaint);
  }

  public void loadImageFinish(boolean paramBoolean, String paramString, Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    if (paramBoolean)
      invalidate();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if (this.url == null)
      return;
    paramCanvas.save();
    paramCanvas.setDrawFilter(this.filter);
    drawImage(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.middleAreaLayout.scaleToBounds(this.standardLayout);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("set"))
    {
      this.url = ((String)paramObject);
      invalidate();
    }
  }

  public void updateImageViewFinish(boolean paramBoolean, ImageView paramImageView, String paramString, Bitmap paramBitmap)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.navigation.items.NavigationEditableView
 * JD-Core Version:    0.6.2
 */