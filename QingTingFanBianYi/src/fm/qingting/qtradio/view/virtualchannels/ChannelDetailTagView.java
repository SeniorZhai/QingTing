package fm.qingting.qtradio.view.virtualchannels;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;

public class ChannelDetailTagView extends ViewImpl
{
  private final ViewLayout arrowLayout = this.itemLayout.createChildLT(38, 20, 650, 24, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout buttonLayout = this.itemLayout.createChildLT(128, 56, 562, 6, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 68, 720, 68, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout labelLayout = this.itemLayout.createChildLT(5, 22, 18, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 12, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Rect mArrowRect = new Rect();
  private View.OnClickListener mBtnClickListener;
  private boolean mButtonChecked = false;
  private boolean mButtonEnabled = true;
  private Rect mButtonRect = new Rect();
  private int[] mButtonRes = new int[0];
  private int mCnt = 0;
  private boolean mHasArrow = false;
  private boolean mHasBackground = true;
  private boolean mHasButton = false;
  private Paint mLinePaint = new Paint();
  private Paint mPaint = new Paint();
  private int mRotateDegree = 0;
  private Rect mTextBound = new Rect();
  private String mTitle;
  private Paint mTitlePaint = new Paint();
  private boolean mToggleButton = false;
  private final ViewLayout titleLayout = this.itemLayout.createChildLT(720, 45, 30, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public ChannelDetailTagView(Context paramContext)
  {
    super(paramContext);
    this.mTitlePaint.setColor(SkinManager.getTextColorSubInfo());
    this.mLinePaint.setColor(SkinManager.getDividerColor_new());
    setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ChannelDetailTagView.this.toggleRotate();
        ChannelDetailTagView.this.dispatchActionEvent("expand", null);
      }
    });
  }

  private void drawArrow(Canvas paramCanvas)
  {
    if (!this.mHasArrow)
      return;
    Bitmap localBitmap = BitmapResourceCache.getInstance().getResourceCache(getResources(), this, 2130837715);
    int i = paramCanvas.save();
    paramCanvas.rotate(this.mRotateDegree, this.mArrowRect.centerX(), this.mArrowRect.centerY());
    paramCanvas.drawBitmap(localBitmap, null, this.mArrowRect, this.mPaint);
    paramCanvas.restoreToCount(i);
  }

  private void drawBg(Canvas paramCanvas)
  {
    if (this.mHasBackground)
    {
      int i = paramCanvas.save();
      paramCanvas.clipRect(0, 0, this.itemLayout.width, this.itemLayout.height);
      paramCanvas.drawColor(SkinManager.getBackgroundColor_item());
      paramCanvas.restoreToCount(i);
    }
  }

  private void drawButton(Canvas paramCanvas)
  {
    if ((!this.mHasButton) || (this.mButtonRes.length == 0))
      return;
    int i;
    if (this.mToggleButton)
      if (!this.mButtonChecked)
        i = this.mButtonRes[0];
    while (true)
    {
      paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCache(getResources(), this, i), null, this.mButtonRect, null);
      return;
      if (this.mButtonRes.length >= 2)
      {
        i = this.mButtonRes[1];
      }
      else
      {
        i = this.mButtonRes[0];
        continue;
        i = this.mButtonRes[0];
      }
    }
  }

  private void drawLines(Canvas paramCanvas)
  {
    paramCanvas.drawLine(0.0F, 0.0F, this.itemLayout.width, 0.0F, this.mLinePaint);
    paramCanvas.drawLine(this.titleLayout.getLeft(), this.itemLayout.height - this.lineLayout.height, this.itemLayout.width, this.itemLayout.height - this.lineLayout.height, this.mLinePaint);
  }

  private void drawTitle(Canvas paramCanvas)
  {
    if (this.mTitle == null)
      return;
    if (this.mCnt > 0)
    {
      String str = this.mTitle + "(" + this.mCnt + ")";
      this.mTitlePaint.getTextBounds(str, 0, str.length(), this.mTextBound);
      paramCanvas.drawText(str, this.titleLayout.leftMargin, (this.itemLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2, this.mTitlePaint);
      return;
    }
    this.mTitlePaint.getTextBounds(this.mTitle, 0, this.mTitle.length(), this.mTextBound);
    paramCanvas.drawText(this.mTitle, this.titleLayout.leftMargin, (this.itemLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2, this.mTitlePaint);
  }

  @TargetApi(11)
  private void toggleRotate()
  {
    int j = 180;
    if (!this.mHasArrow)
      return;
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      int i;
      if ((this.mRotateDegree > 180) || (this.mRotateDegree == 0))
      {
        i = 180;
        j = 0;
      }
      while (true)
      {
        ValueAnimator localValueAnimator = ValueAnimator.ofInt(new int[] { j, i });
        localValueAnimator.setDuration(350L).addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
          public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
          {
            ChannelDetailTagView.access$202(ChannelDetailTagView.this, ((Integer)paramAnonymousValueAnimator.getAnimatedValue()).intValue());
            ChannelDetailTagView.this.invalidate();
          }
        });
        localValueAnimator.start();
        return;
        i = 360;
      }
    }
    if (this.mRotateDegree == 0);
    for (this.mRotateDegree = 180; ; this.mRotateDegree = 0)
    {
      invalidate();
      return;
    }
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public boolean getButtonChecked()
  {
    return this.mButtonChecked;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.setDrawFilter(SkinManager.getInstance().getDrawFilter());
    paramCanvas.save();
    drawBg(paramCanvas);
    drawTitle(paramCanvas);
    drawArrow(paramCanvas);
    drawButton(paramCanvas);
    drawLines(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.titleLayout.scaleToBounds(this.itemLayout);
    this.labelLayout.scaleToBounds(this.itemLayout);
    this.arrowLayout.scaleToBounds(this.itemLayout);
    this.buttonLayout.scaleToBounds(this.itemLayout);
    this.mTitlePaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mArrowRect.set(this.arrowLayout.leftMargin, this.arrowLayout.topMargin, this.arrowLayout.getRight(), this.arrowLayout.getBottom());
    this.mButtonRect.set(this.buttonLayout.getLeft(), this.buttonLayout.getTop(), this.buttonLayout.getRight(), this.buttonLayout.getBottom());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((this.mHasButton) && (this.mButtonEnabled) && (this.mButtonRes.length > 0) && (paramMotionEvent.getAction() == 0))
    {
      int i = (int)paramMotionEvent.getX();
      int j = (int)paramMotionEvent.getY();
      if (this.mButtonRect.contains(i, j))
      {
        if (this.mToggleButton)
          if (this.mButtonChecked)
            break label99;
        label99: for (boolean bool = true; ; bool = false)
        {
          this.mButtonChecked = bool;
          invalidate();
          if (this.mBtnClickListener != null)
            this.mBtnClickListener.onClick(this);
          return true;
        }
      }
    }
    return super.onTouchEvent(paramMotionEvent);
  }

  public void setArrow()
  {
    this.mHasArrow = true;
  }

  public void setBackground(boolean paramBoolean)
  {
    this.mHasBackground = paramBoolean;
  }

  public void setButton(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mHasButton = paramBoolean1;
    this.mToggleButton = paramBoolean2;
    invalidate();
  }

  public void setButtonChecked(boolean paramBoolean)
  {
    this.mButtonChecked = paramBoolean;
  }

  public void setButtonEnabled(boolean paramBoolean)
  {
    this.mButtonEnabled = paramBoolean;
  }

  public void setButtonRes(int[] paramArrayOfInt)
  {
    if (paramArrayOfInt != null)
      this.mButtonRes = paramArrayOfInt;
  }

  public void setCount(int paramInt)
  {
    this.mCnt = paramInt;
    invalidate();
  }

  public void setOnButtonClickListener(View.OnClickListener paramOnClickListener)
  {
    this.mBtnClickListener = paramOnClickListener;
  }

  public void setTagName(String paramString)
  {
    this.mTitle = paramString;
    invalidate();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannels.ChannelDetailTagView
 * JD-Core Version:    0.6.2
 */