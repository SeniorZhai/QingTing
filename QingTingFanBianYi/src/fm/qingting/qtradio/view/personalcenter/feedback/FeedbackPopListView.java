package fm.qingting.qtradio.view.personalcenter.feedback;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.SharedCfg;
import java.util.ArrayList;
import java.util.List;

public class FeedbackPopListView extends ViewImpl
{
  private Paint bgPaint = new Paint();
  private final ViewLayout buttonLayout = this.itemLayout.createChildLT(285, 65, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout channelLayout = this.itemLayout.createChildLT(300, 40, 50, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private DrawFilter filter;
  private String from;
  private List<String> functions = new ArrayList();
  private final ViewLayout intervalLayout = ViewLayout.createViewLayoutWithBoundsLT(10, 7, 300, 68, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(400, 68, 480, 800, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CH);
  private RectF mBgRectF = new RectF();
  private RectF mButtonRectF = new RectF();
  private Paint mHighlightButtonPaint = new Paint();
  private boolean mInTouchMode = false;
  private float mLastMotionX = 0.0F;
  private float mLastMotionY = 0.0F;
  private Paint mNormalButtonPaint = new Paint();
  private int mSelectIndex = -1;
  private Rect mTextBound = new Rect();
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 800, 480, 800, 0, 0, ViewLayout.FILL);
  private Paint textPaint = new Paint();
  private Paint titlePaint = new Paint();

  public FeedbackPopListView(Context paramContext)
  {
    super(paramContext);
    this.textPaint.setColor(SkinManager.getTextColorNormal());
    this.bgPaint.setColor(SkinManager.getPopBgColor());
    this.titlePaint.setColor(SkinManager.getTextColorNormal());
    this.filter = SkinManager.getInstance().getDrawFilter();
    this.mHighlightButtonPaint.setColor(SkinManager.getPopButtonHighlightColor());
    this.mNormalButtonPaint.setColor(SkinManager.getPopButtonNormalColor());
    this.mHighlightButtonPaint.setStyle(Paint.Style.FILL);
    this.mNormalButtonPaint.setStyle(Paint.Style.FILL);
  }

  private void dispatchSelectEvent()
  {
    if ((this.functions != null) && (this.functions.size() > this.mSelectIndex) && (this.mSelectIndex >= 0))
    {
      SharedCfg.getInstance().setFeedbackCategory((String)this.functions.get(this.mSelectIndex));
      dispatchActionEvent("openFeedback", this.from);
    }
  }

  private void drawBg(Canvas paramCanvas)
  {
    int i = (this.standardLayout.height - this.functions.size() * this.itemLayout.height - this.itemLayout.height * 2 / 3) / 2;
    this.mBgRectF.set((this.standardLayout.width - this.itemLayout.width) / 2, i, (this.standardLayout.width + this.itemLayout.width) / 2, i + this.functions.size() * this.itemLayout.height + this.itemLayout.height * 2 / 3 + this.itemLayout.height - this.buttonLayout.height);
    paramCanvas.drawRoundRect(this.mBgRectF, this.intervalLayout.width, this.intervalLayout.width, this.bgPaint);
  }

  private void drawButton(Canvas paramCanvas, String paramString, int paramInt, boolean paramBoolean)
  {
    this.mButtonRectF.set((this.standardLayout.width - this.buttonLayout.width) / 2, (this.itemLayout.height - this.buttonLayout.height) / 2 + paramInt, (this.standardLayout.width + this.buttonLayout.width) / 2, (this.itemLayout.height + this.buttonLayout.height) / 2 + paramInt);
    RectF localRectF = this.mButtonRectF;
    float f1 = this.intervalLayout.width;
    float f2 = this.intervalLayout.width;
    if (paramBoolean);
    for (Paint localPaint = this.mHighlightButtonPaint; ; localPaint = this.mNormalButtonPaint)
    {
      paramCanvas.drawRoundRect(localRectF, f1, f2, localPaint);
      this.textPaint.getTextBounds(paramString, 0, paramString.length(), this.mTextBound);
      paramCanvas.drawText(paramString, (this.standardLayout.width - this.buttonLayout.width) / 2 + this.channelLayout.leftMargin, paramInt + (this.itemLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2.0F, this.textPaint);
      return;
    }
  }

  private void drawTitle(Canvas paramCanvas, int paramInt)
  {
    this.titlePaint.getTextBounds("请选择问题类型", 0, "请选择问题类型".length(), this.mTextBound);
    paramCanvas.drawText("请选择问题类型", (this.standardLayout.width - this.mTextBound.width()) / 2, paramInt + (this.itemLayout.height * 2 / 3 - this.mTextBound.top - this.mTextBound.bottom) / 2.0F, this.titlePaint);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if ((this.functions == null) || (this.functions.size() == 0))
      return;
    paramCanvas.save();
    paramCanvas.setDrawFilter(this.filter);
    drawBg(paramCanvas);
    int j = (this.standardLayout.height - this.functions.size() * this.itemLayout.height - this.itemLayout.height * 2 / 3) / 2;
    drawTitle(paramCanvas, j);
    int k = this.itemLayout.height * 2 / 3;
    int i = 0;
    j += k;
    if (i < this.functions.size())
    {
      String str = (String)this.functions.get(i);
      if (this.mSelectIndex == i);
      for (boolean bool = true; ; bool = false)
      {
        drawButton(paramCanvas, str, j, bool);
        j += this.itemLayout.height;
        i += 1;
        break;
      }
    }
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.itemLayout.scaleToBounds(this.standardLayout);
    this.intervalLayout.scaleToBounds(this.itemLayout);
    this.channelLayout.scaleToBounds(this.itemLayout);
    this.buttonLayout.scaleToBounds(this.itemLayout);
    this.textPaint.setTextSize(this.itemLayout.height * 0.32F);
    this.titlePaint.setTextSize(this.itemLayout.height * 0.32F);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() != 0) && (!this.mInTouchMode));
    int i;
    do
    {
      return true;
      i = (this.standardLayout.height - this.functions.size() * this.itemLayout.height - this.itemLayout.height * 2 / 3) / 2;
      switch (paramMotionEvent.getAction())
      {
      default:
        return true;
      case 0:
        this.mLastMotionX = paramMotionEvent.getX();
        this.mLastMotionY = paramMotionEvent.getY();
        this.mInTouchMode = true;
        if ((this.mLastMotionX < (this.standardLayout.width - this.itemLayout.width) / 2) || (this.mLastMotionX > (this.standardLayout.width + this.itemLayout.width) / 2) || (this.mLastMotionY < i) || (this.mLastMotionY > this.functions.size() * this.itemLayout.height + i + this.itemLayout.height * 2 / 3))
        {
          this.mInTouchMode = false;
          dispatchActionEvent("cancelPop", null);
          return true;
        }
        this.mSelectIndex = ((int)((this.mLastMotionY - i) / this.itemLayout.height - 1.0F));
        invalidate();
        return true;
      case 2:
        this.mLastMotionX = paramMotionEvent.getX();
        this.mLastMotionY = paramMotionEvent.getY();
        if (((this.mLastMotionX < (this.standardLayout.width - this.itemLayout.width) / 2) || (this.mLastMotionX > (this.standardLayout.width + this.itemLayout.width) / 2) || (this.mLastMotionY < i) || (this.mLastMotionY > i + this.functions.size() * this.itemLayout.height + this.itemLayout.height * 2 / 3)) && (this.mSelectIndex > -1))
        {
          this.mInTouchMode = false;
          this.mSelectIndex = -1;
          invalidate();
        }
        i = (int)((paramMotionEvent.getY() - (this.standardLayout.height - this.functions.size() * this.itemLayout.height - this.itemLayout.height * 2 / 3) / 2) / this.itemLayout.height - 1.0F);
      case 1:
      case 3:
      }
    }
    while ((this.mSelectIndex <= -1) || (this.mSelectIndex == i));
    this.mSelectIndex = -1;
    this.mInTouchMode = false;
    invalidate();
    return true;
    dispatchSelectEvent();
    this.mInTouchMode = false;
    this.mSelectIndex = -1;
    invalidate();
    return true;
    this.mInTouchMode = false;
    this.mSelectIndex = -1;
    invalidate();
    return true;
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setBubbleData"))
    {
      this.functions.clear();
      this.from = ((String)paramObject);
      this.functions.add("1.缺少电台");
      this.functions.add("2.电台无法正常收听");
      this.functions.add("3.无法免流量收听");
      this.functions.add("4.节目单错误");
      this.functions.add("5.下载问题");
      this.functions.add("6.闹钟&定时关闭");
      this.functions.add("7.新功能建议");
      this.functions.add("8.其他问题");
      invalidate();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.feedback.FeedbackPopListView
 * JD-Core Version:    0.6.2
 */