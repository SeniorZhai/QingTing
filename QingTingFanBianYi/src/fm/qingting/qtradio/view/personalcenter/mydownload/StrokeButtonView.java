package fm.qingting.qtradio.view.personalcenter.mydownload;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.QtListItemView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class StrokeButtonView extends QtListItemView
{
  private ViewLayout frameLayout = this.standardLayout.createChildLT(2, 6, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Paint mBgPaint = new Paint();
  private Paint mFramePaint = new Paint();
  private RectF mFrameRectF = new RectF();
  private Paint mHighlightPaint = new Paint();
  private boolean mInTouchMode = false;
  private Paint mNormalPaint = new Paint();
  private boolean mSelected = false;
  private Rect mTextBound = new Rect();
  private String mTitle = " ";
  private ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(100, 60, 100, 60, 0, 0, ViewLayout.FILL);

  public StrokeButtonView(Context paramContext)
  {
    super(paramContext);
    this.mFramePaint.setColor(-11711155);
    this.mFramePaint.setStyle(Paint.Style.STROKE);
    this.mBgPaint.setColor(SkinManager.getTextColorHighlight());
    this.mBgPaint.setStyle(Paint.Style.FILL);
    this.mNormalPaint.setColor(-1);
    this.mHighlightPaint.setColor(-1);
    setItemSelectedEnable();
  }

  private void drawButton(Canvas paramCanvas)
  {
    Object localObject = this.mFrameRectF;
    float f1 = this.frameLayout.height;
    float f2 = this.frameLayout.height;
    if ((isItemPressed()) && (this.mSelected));
    for (Paint localPaint = this.mBgPaint; ; localPaint = this.mFramePaint)
    {
      paramCanvas.drawRoundRect((RectF)localObject, f1, f2, localPaint);
      if ((this.mTitle != null) && (!this.mTitle.equalsIgnoreCase("")))
        break;
      return;
    }
    this.mNormalPaint.getTextBounds(this.mTitle, 0, this.mTitle.length(), this.mTextBound);
    localObject = this.mTitle;
    f1 = (this.standardLayout.width - this.mTextBound.left - this.mTextBound.right) / 2;
    f2 = this.mFrameRectF.centerY();
    float f3 = (this.mTextBound.top + this.mTextBound.bottom) / 2;
    if ((isItemPressed()) && (this.mSelected));
    for (localPaint = this.mHighlightPaint; ; localPaint = this.mNormalPaint)
    {
      paramCanvas.drawText((String)localObject, f1, f2 - f3, localPaint);
      return;
    }
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
    this.mFrameRectF.set(this.frameLayout.width, this.frameLayout.width, this.standardLayout.width - this.frameLayout.width, paramInt2 - this.frameLayout.width);
    this.mFramePaint.setStrokeWidth(this.frameLayout.width);
    this.mNormalPaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mHighlightPaint.setTextSize(SkinManager.getInstance().getSubTextSize());
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
        dispatchActionEvent("onclick", null);
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

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mTitle = ((String)paramObject);
      invalidate();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mydownload.StrokeButtonView
 * JD-Core Version:    0.6.2
 */