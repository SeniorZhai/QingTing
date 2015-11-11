package fm.qingting.qtradio.view.navigation.items;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.QtListItemView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.navigation.NaviFaceType;

public class TopButtonView extends QtListItemView
{
  private int ItemType;
  private IEventHandler eventHandler;
  private Paint mHighlightPaint = new Paint();
  boolean mInTouchMode = false;
  float mLastMotionX = 0.0F;
  float mLastMotionY = 0.0F;
  private Paint mPaint = new Paint();
  private Rect mRect = new Rect();
  boolean mSelected = false;
  private ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(106, 86, 720, 86, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CH);
  private int type = 0;

  public TopButtonView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.type = paramInt;
    setItemSelectedEnable();
    paramContext = new ColorMatrixColorFilter(new float[] { 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2F, 0.0F });
    this.mHighlightPaint.setColorFilter(paramContext);
  }

  private void drawButton(Canvas paramCanvas)
  {
    Bitmap localBitmap = BitmapResourceCache.getInstance().getResourceCache(getResources(), this, NaviFaceType.getNormalRes(this.type));
    Rect localRect = this.mRect;
    if ((this.mSelected) && (isItemPressed()));
    for (Paint localPaint = this.mHighlightPaint; ; localPaint = this.mPaint)
    {
      paramCanvas.drawBitmap(localBitmap, null, localRect, localPaint);
      return;
    }
  }

  public View getView()
  {
    return this;
  }

  public void onClick(View paramView)
  {
    if (this.eventHandler != null)
      this.eventHandler.onEvent(this, "click", Integer.valueOf(this.ItemType));
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.setDrawFilter(SkinManager.getInstance().getDrawFilter());
    paramCanvas.save();
    drawButton(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.mRect.set(this.standardLayout.leftMargin, this.standardLayout.topMargin, this.standardLayout.leftMargin + this.standardLayout.width, this.standardLayout.topMargin + this.standardLayout.height);
    setMeasuredDimension(this.standardLayout.width, paramInt2);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((!this.mInTouchMode) && (paramMotionEvent.getAction() != 0));
    do
    {
      return true;
      switch (paramMotionEvent.getAction())
      {
      default:
        return true;
      case 0:
        this.mLastMotionX = paramMotionEvent.getX();
        this.mLastMotionY = paramMotionEvent.getY();
        if ((this.mLastMotionX > 0.0F) && (this.mLastMotionX < this.standardLayout.width) && (this.mLastMotionY > 0.0F) && (this.mLastMotionY < this.standardLayout.height))
        {
          this.mSelected = true;
          this.mInTouchMode = true;
          return true;
        }
        this.mInTouchMode = false;
        return true;
      case 2:
        this.mLastMotionX = paramMotionEvent.getX();
        this.mLastMotionY = paramMotionEvent.getY();
        if ((this.mLastMotionX > 0.0F) && (this.mLastMotionX < this.standardLayout.width) && (this.mLastMotionY > 0.0F) && (this.mLastMotionY < this.standardLayout.height))
        {
          this.mSelected = true;
          return true;
        }
        this.mInTouchMode = false;
        this.mSelected = false;
        invalidate();
        return true;
      case 3:
        this.mInTouchMode = false;
        this.mSelected = false;
        return true;
      case 1:
      }
      this.mInTouchMode = false;
      this.mSelected = false;
    }
    while (this.eventHandler == null);
    this.eventHandler.onEvent(this, "click", Integer.valueOf(this.ItemType));
    return true;
  }

  public void setEventHandler(IEventHandler paramIEventHandler)
  {
    this.eventHandler = paramIEventHandler;
  }

  public void setItemType(int paramInt)
  {
    this.ItemType = paramInt;
  }

  public void setTitle(String paramString)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.navigation.items.TopButtonView
 * JD-Core Version:    0.6.2
 */