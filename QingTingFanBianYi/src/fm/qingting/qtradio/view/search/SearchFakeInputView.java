package fm.qingting.qtradio.view.search;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.QtListItemView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.utils.QTMSGManage;

public class SearchFakeInputView extends QtListItemView
{
  private ViewLayout frameLayout = this.standardLayout.createChildLT(670, 74, 25, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout hotpointLayout = this.standardLayout.createChildLT(50, 60, 640, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Paint mBgPaint = new Paint();
  private RectF mFrameRectF = new RectF();
  private Rect mHotpointRect = new Rect();
  private boolean mInTouchMode = false;
  private TextPaint mNormalPaint = new TextPaint();
  private Paint mPaint = new Paint();
  private Rect mSearchRect = new Rect();
  private Rect mTextBound = new Rect();
  private String mTitle = " ";
  private Rect mVoiceRect = new Rect();
  private ViewLayout nameLayout = this.standardLayout.createChildLT(500, 60, 110, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout searchLayout = this.standardLayout.createChildLT(36, 36, 60, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout sideLayout = this.standardLayout.createChildLT(1, 10, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 114, 720, 114, 0, 0, ViewLayout.FILL);
  private ViewLayout voiceLayout = this.standardLayout.createChildLT(24, 36, 640, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public SearchFakeInputView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(-460552);
    this.mBgPaint.setColor(-2039584);
    this.mBgPaint.setStyle(Paint.Style.FILL);
    this.mNormalPaint.setColor(-7434605);
    setItemSelectedEnable();
    this.mTitle = "搜索电台，专辑，主播，节目";
  }

  private void drawButton(Canvas paramCanvas)
  {
    paramCanvas.drawRoundRect(this.mFrameRectF, this.sideLayout.height, this.sideLayout.height, this.mBgPaint);
    paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCache(getResources(), this, 2130837937), null, this.mSearchRect, this.mPaint);
    if ((this.mTitle == null) || (this.mTitle.equalsIgnoreCase("")))
      return;
    String str = TextUtils.ellipsize(this.mTitle, this.mNormalPaint, this.nameLayout.width, TextUtils.TruncateAt.END).toString();
    this.mNormalPaint.getTextBounds(str, 0, str.length(), this.mTextBound);
    paramCanvas.drawText(str, this.nameLayout.leftMargin, this.mFrameRectF.centerY() - (this.mTextBound.top + this.mTextBound.bottom) / 2, this.mNormalPaint);
    paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCache(getResources(), this, 2130838047), null, this.mVoiceRect, this.mPaint);
  }

  private boolean pointInView(float paramFloat1, float paramFloat2)
  {
    return this.mFrameRectF.contains(paramFloat1, paramFloat2);
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
    this.frameLayout.scaleToBounds(this.standardLayout);
    this.searchLayout.scaleToBounds(this.standardLayout);
    this.sideLayout.scaleToBounds(this.standardLayout);
    this.nameLayout.scaleToBounds(this.standardLayout);
    this.voiceLayout.scaleToBounds(this.standardLayout);
    this.hotpointLayout.scaleToBounds(this.standardLayout);
    this.mFrameRectF.set(this.frameLayout.leftMargin, (this.standardLayout.height - this.frameLayout.height) / 2, this.frameLayout.getRight(), (this.standardLayout.height + this.frameLayout.height) / 2);
    this.mSearchRect.set(this.searchLayout.leftMargin, (this.standardLayout.height - this.searchLayout.height) / 2, this.searchLayout.leftMargin + this.searchLayout.width, (this.standardLayout.height + this.searchLayout.height) / 2);
    this.mVoiceRect.set(this.voiceLayout.leftMargin, (this.standardLayout.height - this.voiceLayout.height) / 2, this.voiceLayout.leftMargin + this.voiceLayout.width, (this.standardLayout.height + this.voiceLayout.height) / 2);
    this.mHotpointRect.set(this.hotpointLayout.leftMargin, (this.standardLayout.height - this.hotpointLayout.height) / 2, this.hotpointLayout.leftMargin + this.hotpointLayout.width, (this.standardLayout.height + this.hotpointLayout.height) / 2);
    this.mNormalPaint.setTextSize(SkinManager.getInstance().getSubTextSize());
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
          return true;
          switch (paramMotionEvent.getAction())
          {
          default:
            return true;
          case 0:
            this.mInTouchMode = true;
            invalidate();
            return true;
          case 2:
          case 1:
          case 3:
          }
        }
        while (pointInView(paramMotionEvent.getX(), paramMotionEvent.getY()));
        this.mInTouchMode = false;
      }
      while (!isItemPressed());
      invalidate();
      return true;
      this.mInTouchMode = false;
      if ((this.mVoiceRect.contains((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())) || (this.mHotpointRect.contains((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())))
      {
        QTMSGManage.getInstance().sendStatistcsMessage("VoiceRecognition", "voice_search_start");
        EventDispacthManager.getInstance().dispatchAction("voice_view", null);
      }
      while (isItemPressed())
      {
        invalidate();
        return true;
        QTMSGManage.getInstance().sendStatistcsMessage("search_fromsearchframe");
        ControllerManager.getInstance().redirectToSearchView(true);
      }
      this.mInTouchMode = false;
    }
    while (!isItemPressed());
    invalidate();
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.search.SearchFakeInputView
 * JD-Core Version:    0.6.2
 */