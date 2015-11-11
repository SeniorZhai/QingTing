package fm.qingting.qtradio.view.navigation.items;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.QtListItemView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class TopRecordView extends QtListItemView
{
  private IEventHandler eventHandler;
  private ViewLayout frameLayout;
  private Paint mBgPaint;
  private Paint mFramePaint;
  private RectF mFrameRectF;
  private boolean mInTouchMode;
  private int mItemType;
  private Paint mNormalPaint;
  private boolean mSelected;
  private Rect mTextBound;
  private String mTitle;
  private ViewLayout standardLayout;

  public TopRecordView(Context paramContext)
  {
    super(paramContext);
    int i = ViewLayout.LT;
    int j = ViewLayout.SLT;
    this.standardLayout = ViewLayout.createViewLayoutWithBoundsLT(106, 44, 468, 60, 0, 0, ViewLayout.CW | (i | j));
    this.frameLayout = this.standardLayout.createChildLT(2, 6, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
    this.mTitle = " ";
    this.mFrameRectF = new RectF();
    this.mFramePaint = new Paint();
    this.mBgPaint = new Paint();
    this.mTextBound = new Rect();
    this.mNormalPaint = new Paint();
    this.mInTouchMode = false;
    this.mSelected = false;
    this.mFramePaint.setColor(SkinManager.getTextColorHighlight());
    this.mFramePaint.setStyle(Paint.Style.FILL);
    this.mBgPaint.setColor(SkinManager.getSpecialButtonHighlightColor());
    this.mBgPaint.setStyle(Paint.Style.FILL);
    this.mNormalPaint.setColor(SkinManager.getBackgroundColor());
    setItemSelectedEnable();
  }

  private void drawButton(Canvas paramCanvas)
  {
    RectF localRectF = this.mFrameRectF;
    float f1 = this.frameLayout.height;
    float f2 = this.frameLayout.height;
    if ((isItemPressed()) && (this.mSelected));
    for (Paint localPaint = this.mBgPaint; ; localPaint = this.mFramePaint)
    {
      paramCanvas.drawRoundRect(localRectF, f1, f2, localPaint);
      if ((this.mTitle != null) && (!this.mTitle.equalsIgnoreCase("")))
        break;
      return;
    }
    this.mNormalPaint.getTextBounds(this.mTitle, 0, this.mTitle.length(), this.mTextBound);
    paramCanvas.drawText(this.mTitle, (this.standardLayout.width - this.mTextBound.width()) / 2, this.mFrameRectF.centerY() - (this.mTextBound.top + this.mTextBound.bottom) / 2, this.mNormalPaint);
  }

  private boolean pointInView(float paramFloat1, float paramFloat2)
  {
    return (paramFloat1 > 0.0F) && (paramFloat1 < this.standardLayout.width) && (paramFloat2 > this.mFrameRectF.top) && (paramFloat2 < this.mFrameRectF.bottom);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.save();
    paramCanvas.setDrawFilter(SkinManager.getInstance().getDrawFilter());
    drawButton(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.frameLayout.scaleToBounds(this.standardLayout);
    this.mFrameRectF.set(this.frameLayout.width, (paramInt2 - this.standardLayout.height) / 2, this.standardLayout.width - this.frameLayout.width, (this.standardLayout.height + paramInt2) / 2);
    this.mNormalPaint.setTextSize(this.standardLayout.height * 0.4F);
    setMeasuredDimension(this.standardLayout.width, paramInt2);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() != 0) && (!this.mInTouchMode));
    do
    {
      do
      {
        do
        {
          do
          {
            return true;
            switch (paramMotionEvent.getAction())
            {
            default:
              return true;
            case 0:
              this.mInTouchMode = true;
              this.mSelected = true;
              invalidate();
              return true;
            case 2:
            case 1:
            case 3:
            }
          }
          while (pointInView(paramMotionEvent.getX(), paramMotionEvent.getY()));
          this.mInTouchMode = false;
          this.mSelected = false;
        }
        while (!isItemPressed());
        invalidate();
        return true;
        this.mInTouchMode = false;
        if (this.eventHandler != null)
          this.eventHandler.onEvent(this, "click", Integer.valueOf(this.mItemType));
      }
      while (!isItemPressed());
      invalidate();
      return true;
      this.mInTouchMode = false;
      this.mSelected = false;
    }
    while (!isItemPressed());
    invalidate();
    return true;
  }

  public void setEventHandler(IEventHandler paramIEventHandler)
  {
    this.eventHandler = paramIEventHandler;
  }

  public void setItemType(int paramInt)
  {
    this.mItemType = paramInt;
  }

  public void setTitle(String paramString)
  {
    this.mTitle = paramString;
    invalidate();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.navigation.items.TopRecordView
 * JD-Core Version:    0.6.2
 */