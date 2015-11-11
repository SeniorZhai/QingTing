package fm.qingting.qtradio.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class LoadMoreFootView extends ViewImpl
{
  private static final String ALL = "无更多内容";
  private static final int FOOT_SHOW_UPPER_LIMIT = 5;
  private static final String LOADING = "正在加载更多...";
  private final int CANCELTIMELENGTH = 3000;
  private Handler cancelHandler = new Handler();
  private Runnable cancelRunnable = new Runnable()
  {
    public void run()
    {
      LoadMoreFootView.this.hideLoad();
    }
  };
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 60, 720, 60, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private boolean mAll = false;
  private boolean mLoading = false;
  private boolean mShow = true;
  private final Rect mTextBound = new Rect();
  private final Paint mTitlePaint = new Paint();
  private final ViewLayout titleLayout = this.itemLayout.createChildLT(720, 45, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public LoadMoreFootView(Context paramContext)
  {
    super(paramContext);
    this.mTitlePaint.setColor(SkinManager.getLoadMoreFooterColor());
  }

  private void drawTitle(Canvas paramCanvas)
  {
    if (!this.mShow);
    do
    {
      return;
      if (this.mAll)
      {
        this.mTitlePaint.getTextBounds("无更多内容", 0, "无更多内容".length(), this.mTextBound);
        paramCanvas.drawText("无更多内容", (this.itemLayout.width - this.mTextBound.width()) / 2, (this.itemLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2, this.mTitlePaint);
        return;
      }
    }
    while (!this.mLoading);
    this.mTitlePaint.getTextBounds("正在加载更多...", 0, "正在加载更多...".length(), this.mTextBound);
    paramCanvas.drawText("正在加载更多...", (this.itemLayout.width - this.mTextBound.width()) / 2, (this.itemLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2, this.mTitlePaint);
  }

  public void hideFootIfItemsNotEnough(int paramInt)
  {
    if (paramInt <= 5)
    {
      this.mShow = false;
      invalidate();
    }
  }

  public void hideLoad()
  {
    if (!this.mLoading)
      return;
    this.cancelHandler.removeCallbacks(this.cancelRunnable);
    this.mLoading = false;
    invalidate();
  }

  public boolean isAll()
  {
    return this.mAll;
  }

  public final boolean isLoading()
  {
    return this.mLoading;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.save();
    drawTitle(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.titleLayout.scaleToBounds(this.itemLayout);
    this.mTitlePaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void setAll()
  {
    this.mAll = true;
    invalidate();
  }

  public void showLoad()
  {
    this.mShow = true;
    if (this.mLoading)
      return;
    this.mLoading = true;
    invalidate();
    this.cancelHandler.removeCallbacks(this.cancelRunnable);
    this.cancelHandler.postDelayed(this.cancelRunnable, 3000L);
  }

  public void unsetAll()
  {
    this.mAll = false;
    invalidate();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.LoadMoreFootView
 * JD-Core Version:    0.6.2
 */