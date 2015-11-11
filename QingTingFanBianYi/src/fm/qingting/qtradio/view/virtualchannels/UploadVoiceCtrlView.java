package fm.qingting.qtradio.view.virtualchannels;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.widget.TextView;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.RecorderManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.utils.TimeUtil;

public class UploadVoiceCtrlView extends ViewGroupViewImpl
{
  private static final int mMinRecordingSec = 5;
  private Paint btnBgPaint = new Paint();
  private Rect cBtnRect = new Rect();
  private final ViewLayout centerBtnLayout = this.standardLayout.createChildLT(220, 220, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Rect lBtnRect = new Rect();
  private boolean mInTouchMode = false;
  private boolean mIsPlaying = false;
  private float mLastMotionX = 0.0F;
  private float mLastMotionY = 0.0F;
  private Paint mPaint = new Paint();
  private long mProcessTimeSec = 0L;
  private int mSelectedIndex = -1;
  private int mStep = 0;
  private Rect rBtnRect = new Rect();
  private final ViewLayout sideBtnLayout = this.standardLayout.createChildLT(130, 130, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 390, 480, 390, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(480, 30, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private TextView titleView;

  public UploadVoiceCtrlView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(Color.parseColor("#202020"));
    this.btnBgPaint.setColor(SkinManager.getTextColorSubInfo());
    this.titleView = new TextView(paramContext);
    this.titleView.setGravity(17);
    addView(this.titleView);
    setTitleTime();
  }

  private boolean allowRecStop()
  {
    return this.mProcessTimeSec >= 5L;
  }

  private void drawButton(Canvas paramCanvas)
  {
    int j;
    int i;
    label28: int k;
    label40: int m;
    label52: int n;
    if (this.mSelectedIndex == 0)
    {
      j = 2130837547;
      if (!allowRecStop())
        break label109;
      if (this.mSelectedIndex != 1)
        break label103;
      i = 2130837550;
      if (this.mSelectedIndex != 2)
        break label115;
      k = 2130837545;
      if (this.mSelectedIndex != 3)
        break label122;
      m = 2130837543;
      if (this.mSelectedIndex != 4)
        break label129;
      n = 2130837552;
      label64: if (this.mStep != 0)
        break label136;
      paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCache(getResources(), this, j), null, this.cBtnRect, this.mPaint);
    }
    label103: label109: label115: label122: 
    do
    {
      return;
      j = 2130837546;
      break;
      i = 2130837548;
      break label28;
      i = 2130837549;
      break label28;
      k = 2130837544;
      break label40;
      m = 2130837542;
      break label52;
      n = 2130837551;
      break label64;
      if (this.mStep == 1)
      {
        paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCache(getResources(), this, i), null, this.cBtnRect, this.mPaint);
        return;
      }
    }
    while (this.mStep != 2);
    label129: label136: paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCache(getResources(), this, n), null, this.rBtnRect, this.mPaint);
    if (this.mIsPlaying)
    {
      paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCache(getResources(), this, m), null, this.lBtnRect, this.mPaint);
      return;
    }
    paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCache(getResources(), this, k), null, this.lBtnRect, this.mPaint);
  }

  private void generateRect()
  {
    int i = this.standardLayout.width / 2;
    int j = (this.standardLayout.height + this.titleLayout.height) / 2;
    int k = i - this.sideBtnLayout.width / 2 - this.sideBtnLayout.width / 4;
    int m = this.sideBtnLayout.width / 2 + i + this.sideBtnLayout.width / 4;
    this.cBtnRect.set(i - this.centerBtnLayout.width / 2, j - this.centerBtnLayout.height / 2, i + this.centerBtnLayout.width / 2, this.centerBtnLayout.height / 2 + j);
    this.lBtnRect.set(k - this.sideBtnLayout.width / 2, j - this.sideBtnLayout.height / 2, k + this.sideBtnLayout.width / 2, this.sideBtnLayout.height / 2 + j);
    this.rBtnRect.set(m - this.sideBtnLayout.width / 2, j - this.sideBtnLayout.height / 2, m + this.sideBtnLayout.width / 2, j + this.sideBtnLayout.height / 2);
  }

  private int getSelectedIndex()
  {
    int i = 1;
    if ((this.cBtnRect.contains((int)this.mLastMotionX, (int)this.mLastMotionY)) && (this.mStep == 0))
      i = 0;
    do
    {
      return i;
      if ((!this.cBtnRect.contains((int)this.mLastMotionX, (int)this.mLastMotionY)) || (this.mStep != 1))
        break;
    }
    while (allowRecStop());
    return 5;
    if ((this.lBtnRect.contains((int)this.mLastMotionX, (int)this.mLastMotionY)) && (this.mStep == 2) && (!this.mIsPlaying))
      return 2;
    if ((this.lBtnRect.contains((int)this.mLastMotionX, (int)this.mLastMotionY)) && (this.mStep == 2) && (this.mIsPlaying))
      return 3;
    if ((this.rBtnRect.contains((int)this.mLastMotionX, (int)this.mLastMotionY)) && (this.mStep == 2))
      return 4;
    return -1;
  }

  private void setTitleTime()
  {
    switch (this.mStep)
    {
    default:
      return;
    case 0:
      this.titleView.setTextColor(SkinManager.getUploadPageElementColor());
      this.titleView.setText("最大录音时长 " + TimeUtil.mSecToTime2(RecorderManager.getInstance().getMaxDurationSec() * 1000L));
      return;
    case 1:
      this.titleView.setTextColor(SkinManager.getUploadPageElementColor());
      this.titleView.setText("当前录音时长 " + TimeUtil.mSecToTime2(this.mProcessTimeSec * 1000L));
      return;
    case 2:
    }
    this.titleView.setTextColor(SkinManager.getTextColorSubInfo());
    if (this.mIsPlaying)
    {
      this.titleView.setText("播放时长 " + TimeUtil.mSecToTime2(this.mProcessTimeSec * 1000L));
      return;
    }
    this.titleView.setText("录音时长 " + TimeUtil.mSecToTime2(RecorderManager.getInstance().getCachedFileDurationMSec()));
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.setDrawFilter(SkinManager.getInstance().getDrawFilter());
    paramCanvas.save();
    drawButton(paramCanvas);
    paramCanvas.restore();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.titleLayout.layoutView(this.titleView);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.centerBtnLayout.scaleToBounds(this.standardLayout);
    this.sideBtnLayout.scaleToBounds(this.standardLayout);
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.titleLayout.measureView(this.titleView);
    generateRect();
    this.titleView.setTextSize(0, this.titleLayout.height * 0.85F);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = false;
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
          int i;
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
            for (i = 1; ; i = 0)
            {
              this.mSelectedIndex = getSelectedIndex();
              if (this.mSelectedIndex >= 0)
                break;
              this.mInTouchMode = false;
              return true;
            }
          }
          while (i != 0);
          invalidate();
          return true;
        }
        while (!this.mInTouchMode);
        this.mSelectedIndex = -1;
      }
      while (getSelectedIndex() < 0);
      invalidate();
      return true;
    case 1:
    }
    if (this.mSelectedIndex >= 0)
    {
      if ((this.mSelectedIndex == 2) || (this.mSelectedIndex == 3))
      {
        if (!this.mIsPlaying)
          bool = true;
        this.mIsPlaying = bool;
      }
      dispatchActionEvent("ctrlBtnClicked", Integer.valueOf(this.mSelectedIndex));
    }
    this.mSelectedIndex = -1;
    invalidate();
    return true;
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setStep"))
    {
      this.mStep = ((Integer)paramObject).intValue();
      this.mProcessTimeSec = 0L;
      this.mIsPlaying = false;
      setTitleTime();
      invalidate();
    }
    if (paramString.equalsIgnoreCase("setProcessTime"))
    {
      this.mProcessTimeSec = ((Long)paramObject).longValue();
      setTitleTime();
      invalidate();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannels.UploadVoiceCtrlView
 * JD-Core Version:    0.6.2
 */