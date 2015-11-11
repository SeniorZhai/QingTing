package fm.qingting.qtradio.view.virtualchannels;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.QtListItemView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.RecorderManager;
import fm.qingting.qtradio.manager.RecorderManager.RecorderHandler;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.utils.TimeUtil;

public class UploadVoiceItemView extends QtListItemView
  implements RecorderManager.RecorderHandler
{
  private final ViewLayout btnLayout = this.itemLayout.createChildLT(90, 90, 608, 2, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout ctrlLayout = this.itemLayout.createChildLT(90, 90, 27, 23, ViewLayout.SCALE_FLAG_SLTCW);
  private Rect ctrlRect = new Rect();
  private final DrawFilter filter = SkinManager.getInstance().getDrawFilter();
  private int hash = -24;
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 136, 720, 136, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private boolean mFileExists = false;
  private Paint mHighlightPaint = new Paint();
  private boolean mInTouchMode = false;
  private boolean mIsPlaying = false;
  private float mLastMotionX = 0.0F;
  private float mLastMotionY = 0.0F;
  private Paint mManageHighlightPaint = new Paint();
  private Paint mResumeHighlightPaint = new Paint();
  private Paint mResumePaint = new Paint();
  private Paint mResumeTextPaint = new Paint();
  private int mSelectedIndex = -1;
  private String mTagId = null;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(600, 45, 30, 20, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout subInfoLayout = this.itemLayout.createChildLT(600, 45, 30, 10, ViewLayout.SCALE_FLAG_SLTCW);
  private Rect textBound = new Rect();

  public UploadVoiceItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.hash = paramInt;
    this.mResumePaint.setColor(SkinManager.getTextColorHighlight());
    this.mResumePaint.setStyle(Paint.Style.STROKE);
    this.mResumeHighlightPaint.setColor(SkinManager.getTextColorHighlight());
    this.mResumeHighlightPaint.setStyle(Paint.Style.FILL);
    this.mResumeTextPaint.setColor(SkinManager.getTextColorHighlight());
    this.mManageHighlightPaint.setColor(SkinManager.getBackgroundColor());
    paramContext = new ColorMatrixColorFilter(new float[] { 0.0F, 0.0F, 0.0F, 0.0F, 119.0F, 0.0F, 0.0F, 0.0F, 0.0F, 119.0F, 0.0F, 0.0F, 0.0F, 0.0F, 119.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F });
    this.mHighlightPaint.setColorFilter(paramContext);
    setItemSelectedEnable();
  }

  private void drawBg(Canvas paramCanvas)
  {
    if ((isItemPressed()) && (this.mSelectedIndex == 0))
    {
      int i = paramCanvas.save();
      paramCanvas.clipRect(0, 0, this.itemLayout.width, this.itemLayout.height);
      paramCanvas.drawColor(SkinManager.getItemHighlightMaskColor());
      paramCanvas.restoreToCount(i);
    }
  }

  private void drawButton(Canvas paramCanvas)
  {
    TextPaint localTextPaint = SkinManager.getInstance().getSubTextPaint();
    float f1 = this.btnLayout.leftMargin + this.btnLayout.width / 2;
    float f2 = this.itemLayout.height / 2;
    float f3 = this.btnLayout.width / 2;
    if ((isItemPressed()) && (this.mSelectedIndex == 2))
    {
      localPaint = this.mResumeHighlightPaint;
      paramCanvas.drawCircle(f1, f2, f3, localPaint);
      localTextPaint.getTextBounds("上传", 0, "上传".length(), this.textBound);
      f1 = this.btnLayout.leftMargin + this.btnLayout.width / 2 - (this.textBound.left + this.textBound.right) / 2;
      f2 = (this.itemLayout.height - this.textBound.top - this.textBound.bottom) / 2;
      if ((!isItemPressed()) || (this.mSelectedIndex != 2))
        break label203;
    }
    label203: for (Paint localPaint = this.mManageHighlightPaint; ; localPaint = this.mResumeTextPaint)
    {
      paramCanvas.drawText("上传", f1, f2, localPaint);
      return;
      localPaint = this.mResumePaint;
      break;
    }
  }

  private void drawCtrl(Canvas paramCanvas)
  {
    int i = 2130837845;
    if ((isItemPressed()) && (this.mSelectedIndex == 1))
      if (!this.mIsPlaying);
    while (true)
    {
      paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCache(getResources(), Integer.valueOf(this.hash), i), null, this.ctrlRect, this.mHighlightPaint);
      return;
      i = 2130837847;
      continue;
      if (!this.mIsPlaying)
        i = 2130837847;
    }
  }

  private void drawTexts(Canvas paramCanvas)
  {
    int i = this.ctrlRect.right + this.nameLayout.leftMargin;
    int j = this.btnLayout.leftMargin;
    TextPaint localTextPaint = SkinManager.getInstance().getNormalTextPaint();
    String str = TextUtils.ellipsize(RecorderManager.getInstance().getCachedFileName(), localTextPaint, j - i, TextUtils.TruncateAt.END).toString();
    localTextPaint.getTextBounds(str, 0, str.length(), this.textBound);
    paramCanvas.drawText(str, i, this.nameLayout.topMargin + (this.nameLayout.height - this.textBound.top - this.textBound.bottom) / 2, localTextPaint);
    localTextPaint = SkinManager.getInstance().getSubTextPaint();
    int k = this.nameLayout.getBottom() + this.subInfoLayout.topMargin;
    str = TextUtils.ellipsize(RecorderManager.getInstance().getUserName(), localTextPaint, (j - i) / 2, TextUtils.TruncateAt.END).toString();
    localTextPaint.getTextBounds(str, 0, str.length(), this.textBound);
    paramCanvas.drawText(str, i, (this.subInfoLayout.height - this.textBound.top - this.textBound.bottom) / 2 + k, localTextPaint);
    long l = RecorderManager.getInstance().getCachedFileDurationMSec();
    str = TextUtils.ellipsize("时长:" + TimeUtil.mSecToTime1(l), localTextPaint, (j - i) / 2, TextUtils.TruncateAt.END).toString();
    localTextPaint.getTextBounds(str, 0, str.length(), this.textBound);
    paramCanvas.drawText(str, j - this.textBound.right - this.textBound.left, k + (this.subInfoLayout.height - this.textBound.top - this.textBound.bottom) / 2, localTextPaint);
  }

  private int getSelectedIndex()
  {
    if (this.ctrlRect.contains((int)this.mLastMotionX, (int)this.mLastMotionY))
      return 1;
    if ((this.mLastMotionX > this.btnLayout.leftMargin) && (this.mLastMotionX < this.btnLayout.getRight()) && (this.mLastMotionY > (this.itemLayout.height - this.btnLayout.height) / 2) && (this.mLastMotionY < (this.itemLayout.height + this.btnLayout.height) / 2))
      return 2;
    return 0;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if (!this.mFileExists)
      return;
    paramCanvas.setDrawFilter(this.filter);
    paramCanvas.save();
    drawBg(paramCanvas);
    drawTexts(paramCanvas);
    drawCtrl(paramCanvas);
    drawButton(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.ctrlLayout.scaleToBounds(this.itemLayout);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.subInfoLayout.scaleToBounds(this.itemLayout);
    this.btnLayout.scaleToBounds(this.itemLayout);
    this.ctrlRect.set(this.ctrlLayout.leftMargin, this.ctrlLayout.topMargin, this.ctrlLayout.getRight(), this.ctrlLayout.getBottom());
    this.mResumePaint.setStrokeWidth(this.btnLayout.topMargin);
    this.mResumeTextPaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mManageHighlightPaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void onMonitorAmpChanged(float paramFloat)
  {
  }

  public void onRecordingSecond(Long paramLong)
  {
  }

  public void onRecordingStart()
  {
  }

  public void onRecordingStop()
  {
  }

  public void onReplaySecond(Long paramLong)
  {
  }

  public void onReplayStop()
  {
    this.mIsPlaying = false;
    invalidate();
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    case 2:
    case 3:
      do
      {
        do
        {
          return true;
          this.mInTouchMode = true;
          this.mLastMotionX = paramMotionEvent.getX();
          this.mLastMotionY = paramMotionEvent.getY();
          this.mSelectedIndex = getSelectedIndex();
          if (this.mSelectedIndex < 0)
          {
            this.mInTouchMode = false;
            return true;
          }
          invalidate();
          return true;
        }
        while (!this.mInTouchMode);
        this.mLastMotionX = paramMotionEvent.getX();
        this.mLastMotionY = paramMotionEvent.getY();
        if (this.mSelectedIndex == getSelectedIndex());
        for (int i = 1; i == 0; i = 0)
        {
          this.mInTouchMode = false;
          this.mSelectedIndex = -1;
          invalidate();
          return true;
        }
      }
      while (!this.mInTouchMode);
      this.mSelectedIndex = -1;
      invalidate();
      return true;
    case 1:
    }
    if (this.mSelectedIndex >= 0)
      update("ctrlBtnClicked", Integer.valueOf(this.mSelectedIndex));
    this.mSelectedIndex = -1;
    invalidate();
    return true;
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setTagId"))
    {
      this.mTagId = ((String)paramObject);
      this.mFileExists = RecorderManager.getInstance().initByFile(this.mTagId);
      invalidate();
    }
    int i;
    do
    {
      do
        return;
      while (!paramString.equalsIgnoreCase("ctrlBtnClicked"));
      i = ((Integer)paramObject).intValue();
      if (i == 1)
      {
        if (this.mIsPlaying)
          RecorderManager.getInstance().pauseReplay();
        for (this.mIsPlaying = false; ; this.mIsPlaying = true)
        {
          invalidate();
          return;
          RecorderManager.getInstance().startReplay(this);
        }
      }
    }
    while (i != 2);
    RecorderManager.getInstance().uploadCachedFile(RecorderManager.getInstance().getCachedFileName());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannels.UploadVoiceItemView
 * JD-Core Version:    0.6.2
 */