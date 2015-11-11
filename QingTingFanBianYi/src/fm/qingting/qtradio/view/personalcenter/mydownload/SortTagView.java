package fm.qingting.qtradio.view.personalcenter.mydownload;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import java.util.Locale;

public class SortTagView extends ViewImpl
{
  private static final String TITLE_MODEL = "%d个文件";
  private final ViewLayout iconLayout = this.standardLayout.createChildLT(24, 30, 10, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.standardLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private boolean mAsend = true;
  private String mClearString = "排序";
  private boolean mEnabled = true;
  private Paint mHighlightTitlePaint = new Paint();
  private Rect mIconRect = new Rect();
  private boolean mInTouchMode = false;
  private Paint mPaint = new Paint();
  private int mSelectedIndex = -1;
  private Rect mSortRect = new Rect();
  private Rect mTextBound = new Rect();
  private String mTitle;
  private Paint mTitlePaint = new Paint();
  private final ViewLayout sortLayout = this.standardLayout.createChildLT(128, 56, 562, 10, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 80, 720, 80, 0, 0, ViewLayout.FILL);
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(720, 60, 40, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public SortTagView(Context paramContext)
  {
    super(paramContext);
    this.mTitlePaint.setColor(-11184811);
    this.mHighlightTitlePaint.setColor(-772816);
    this.mTitle = String.format(Locale.CHINESE, "%d个文件", new Object[] { Integer.valueOf(0) });
  }

  private void drawClear(Canvas paramCanvas)
  {
    if (!this.mAsend);
    for (int i = 2130837710; ; i = 2130837709)
    {
      paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCache(getResources(), this, i), null, this.mSortRect, null);
      return;
    }
  }

  private void drawTitle(Canvas paramCanvas)
  {
    if (this.mTitle == null)
      return;
    this.mTitlePaint.getTextBounds(this.mTitle, 0, this.mTitle.length(), this.mTextBound);
    paramCanvas.drawText(this.mTitle, this.titleLayout.leftMargin, (this.standardLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2, this.mTitlePaint);
  }

  private int getSelectIndex(float paramFloat1, float paramFloat2)
  {
    if ((paramFloat2 < 0.0F) || (paramFloat2 > this.standardLayout.height));
    while ((paramFloat1 <= this.standardLayout.width - this.titleLayout.leftMargin - this.mTextBound.width() + this.mIconRect.left) || (paramFloat1 >= this.standardLayout.width))
      return -1;
    return 0;
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.setDrawFilter(SkinManager.getInstance().getDrawFilter());
    paramCanvas.save();
    drawTitle(paramCanvas);
    drawClear(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.lineLayout.scaleToBounds(this.standardLayout);
    this.iconLayout.scaleToBounds(this.standardLayout);
    this.sortLayout.scaleToBounds(this.standardLayout);
    this.mTitlePaint.setTextSize(this.titleLayout.height * 0.5F);
    this.mHighlightTitlePaint.setTextSize(this.titleLayout.height * 0.5F);
    this.mIconRect.set(-this.iconLayout.leftMargin - this.iconLayout.width, (this.standardLayout.height - this.iconLayout.height) / 2, -this.iconLayout.leftMargin, (this.standardLayout.height + this.iconLayout.height) / 2);
    this.mSortRect.set(this.sortLayout.getLeft(), this.sortLayout.getTop(), this.sortLayout.getRight(), this.sortLayout.getBottom());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = false;
    if ((!this.mEnabled) || ((!this.mInTouchMode) && (paramMotionEvent.getAction() != 0)));
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
          this.mSelectedIndex = getSelectIndex(paramMotionEvent.getX(), paramMotionEvent.getY());
          if (this.mSelectedIndex < 0)
            this.mInTouchMode = false;
          invalidate();
          return true;
        case 2:
        case 3:
        case 1:
        }
      }
      while (getSelectIndex(paramMotionEvent.getX(), paramMotionEvent.getY()) == this.mSelectedIndex);
      this.mInTouchMode = false;
      this.mSelectedIndex = -1;
      invalidate();
      return true;
      this.mInTouchMode = false;
      this.mSelectedIndex = -1;
      invalidate();
      return true;
      this.mInTouchMode = false;
    }
    while (this.mSelectedIndex <= -1);
    if (!this.mAsend)
      bool = true;
    this.mAsend = bool;
    dispatchActionEvent("converseOrder", null);
    this.mSelectedIndex = -1;
    invalidate();
    return true;
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setNumber"))
    {
      i = ((Integer)paramObject).intValue();
      this.mTitle = String.format(Locale.CHINESE, "%d个文件", new Object[] { Integer.valueOf(i) });
    }
    while (!paramString.equalsIgnoreCase("setEnable"))
    {
      int i;
      return;
    }
    this.mEnabled = ((Boolean)paramObject).booleanValue();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mydownload.SortTagView
 * JD-Core Version:    0.6.2
 */