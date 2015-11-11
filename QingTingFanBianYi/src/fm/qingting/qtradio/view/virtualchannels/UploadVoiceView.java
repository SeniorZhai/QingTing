package fm.qingting.qtradio.view.virtualchannels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.View.MeasureSpec;
import android.widget.Toast;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.RecorderManager;
import fm.qingting.qtradio.manager.SkinManager;

public class UploadVoiceView extends ViewGroupViewImpl
  implements IEventHandler
{
  private final ViewLayout ctrlLayout = this.standardLayout.createChildLT(480, 390, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private UploadVoiceCtrlView ctrlView;
  private final DrawFilter filter = SkinManager.getInstance().getDrawFilter();
  private final ViewLayout inputLayout = this.standardLayout.createChildLT(480, 160, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private UploadVoiceInputFrameView inputView;
  private final Paint mPaint = new Paint();
  private float mPercentage = 0.0F;
  private int mStep = 0;
  private final Paint mThumbMaskPaint = new Paint();
  private final Paint mThumbShadowInnerPaint = new Paint();
  private final Paint mThumbShadowOuterPaint = new Paint();
  private final Paint processBgPaint = new Paint();
  private final ViewLayout processLayout = this.standardLayout.createChildLT(264, 5, 108, 329, ViewLayout.SCALE_FLAG_SLTCW);
  private final Paint processPaint = new Paint();
  private Rect processRect = new Rect();
  public final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 800, 480, 800, 0, 0, ViewLayout.FILL);
  private final ViewLayout thumbLayout = this.standardLayout.createChildLT(264, 264, 108, 70, ViewLayout.SCALE_FLAG_SLTCW);
  private Rect thumbRect = new Rect();
  private final ViewLayout volumnMicLayout = this.standardLayout.createChildLT(120, 180, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Rect volumnMicRect = new Rect();

  public UploadVoiceView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(Color.parseColor("#202020"));
    this.mThumbMaskPaint.setARGB(128, 0, 0, 0);
    this.mThumbShadowInnerPaint.setARGB(80, 255, 255, 255);
    this.mThumbShadowInnerPaint.setStyle(Paint.Style.STROKE);
    this.mThumbShadowInnerPaint.setStrokeWidth(2.0F);
    this.mThumbShadowOuterPaint.setARGB(128, 0, 0, 0);
    this.mThumbShadowOuterPaint.setStyle(Paint.Style.STROKE);
    this.mThumbShadowOuterPaint.setStrokeWidth(2.0F);
    this.processPaint.setColor(SkinManager.getUploadPageElementColor());
    this.processBgPaint.setColor(-16777216);
    this.ctrlView = new UploadVoiceCtrlView(paramContext);
    this.ctrlView.setEventHandler(this);
    addView(this.ctrlView);
    this.inputView = new UploadVoiceInputFrameView(paramContext);
    this.inputView.setEventHandler(this);
    this.inputView.setVisibility(8);
    addView(this.inputView);
  }

  private void drawThumb(Canvas paramCanvas)
  {
    Bitmap localBitmap = BitmapResourceCache.getInstance().getResourceCache(getResources(), this, 2130837907);
    paramCanvas.drawRect(new Rect(this.thumbRect.left - 2, this.thumbRect.top - 2, this.thumbRect.right + 2, this.thumbRect.bottom + 2), this.mThumbShadowOuterPaint);
    paramCanvas.drawBitmap(localBitmap, null, this.thumbRect, this.mPaint);
    paramCanvas.drawRect(this.thumbRect, this.mThumbShadowInnerPaint);
  }

  private void drawThumbMask(Canvas paramCanvas)
  {
    if (this.mStep == 1)
    {
      paramCanvas.drawRect(this.thumbRect, this.mThumbMaskPaint);
      paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCache(getResources(), this, 2130838052), null, this.volumnMicRect, this.mPaint);
      localBitmap = BitmapResourceCache.getInstance().getResourceCache(getResources(), this, 2130838053);
      f = (60.0F * this.mPercentage + 75.0F) / 180.0F;
      i = localBitmap.getWidth();
      j = localBitmap.getHeight();
      paramCanvas.drawBitmap(localBitmap, new Rect(0, 0, i, j - (int)(j * f)), new Rect(this.volumnMicRect.left, this.volumnMicRect.top, this.volumnMicRect.right, this.volumnMicRect.bottom - (int)(f * this.volumnMicRect.height())), this.mPaint);
    }
    while ((this.mStep != 2) || (this.mPercentage <= 0.0F))
    {
      Bitmap localBitmap;
      float f;
      int i;
      int j;
      return;
    }
    paramCanvas.drawRect(this.thumbRect, this.mThumbMaskPaint);
    paramCanvas.drawRect(this.processRect, this.processBgPaint);
    paramCanvas.drawRect(new Rect(this.processRect.left, this.processRect.top, this.processRect.left + (int)(this.processRect.width() * this.mPercentage), this.processRect.bottom), this.processPaint);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.setDrawFilter(this.filter);
    paramCanvas.save();
    drawThumb(paramCanvas);
    drawThumbMask(paramCanvas);
    paramCanvas.restore();
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("doSend"))
    {
      update("hideInput", null);
      dispatchActionEvent("uploadVoice", paramObject2);
    }
    if (paramString.equalsIgnoreCase("cancelSend"))
      update("hideInput", null);
    if (paramString.equalsIgnoreCase("ctrlBtnClicked"));
    switch (((Integer)paramObject2).intValue())
    {
    default:
      return;
    case 0:
      dispatchActionEvent("startRecording", null);
      return;
    case 1:
      dispatchActionEvent("stopRecording", null);
      return;
    case 2:
      dispatchActionEvent("startReplay", null);
      return;
    case 3:
      dispatchActionEvent("pauseReplay", null);
      return;
    case 4:
      dispatchActionEvent("publishRecording", null);
      return;
    case 5:
    }
    Toast.makeText(getContext(), "最短录音时长5秒", 0).show();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.ctrlView.layout(0, this.standardLayout.height - this.ctrlLayout.height, this.standardLayout.width, this.standardLayout.height);
    this.inputView.layout(0, this.standardLayout.height - this.inputLayout.height, this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.thumbLayout.scaleToBounds(this.standardLayout);
    this.ctrlLayout.scaleToBounds(this.standardLayout);
    this.volumnMicLayout.scaleToBounds(this.standardLayout);
    this.processLayout.scaleToBounds(this.standardLayout);
    this.inputLayout.scaleToBounds(this.standardLayout);
    this.ctrlLayout.measureView(this.ctrlView);
    this.inputLayout.measureView(this.inputView);
    this.thumbRect = this.thumbLayout.getRect();
    this.volumnMicRect.set(this.thumbRect.centerX() - this.volumnMicLayout.width / 2, this.thumbRect.centerY() - this.volumnMicLayout.height / 2, this.thumbRect.centerX() + this.volumnMicLayout.width / 2, this.thumbRect.centerY() + this.volumnMicLayout.height / 2);
    this.processRect.set(this.thumbRect.left, this.thumbRect.bottom - this.processLayout.height, this.thumbRect.right, this.thumbRect.bottom);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("hideInput"))
    {
      this.ctrlView.setVisibility(0);
      this.inputView.update("hide", null);
      this.inputView.setVisibility(8);
    }
    if (paramString.equalsIgnoreCase("showInput"))
    {
      this.inputView.setVisibility(0);
      this.inputView.update("show", null);
      this.ctrlView.setVisibility(8);
    }
    if (paramString.equalsIgnoreCase("setStep"))
    {
      this.mStep = ((Integer)paramObject).intValue();
      this.ctrlView.update("setStep", paramObject);
      if (this.mStep < 2)
        update("hideInput", null);
      this.mPercentage = 0.0F;
      invalidate(this.thumbRect);
    }
    if (paramString.equalsIgnoreCase("setRecordingTimeSec"))
      this.ctrlView.update("setProcessTime", paramObject);
    if (paramString.equalsIgnoreCase("setReplayTimeMSec"))
    {
      long l = ((Long)paramObject).longValue();
      this.ctrlView.update("setProcessTime", Long.valueOf(()Math.ceil(((float)l + 0.0F) / 1000.0F)));
      if (this.mStep == 2)
      {
        this.mPercentage = (((float)l + 0.0F) / (float)RecorderManager.getInstance().getCachedFileDurationMSec());
        invalidate(this.thumbRect);
      }
    }
    if (paramString.equalsIgnoreCase("onAmpChanged"))
    {
      float f2 = ((Float)paramObject).floatValue();
      float f1 = f2;
      if (this.mPercentage * 0.8F > f2)
        f1 = this.mPercentage * 0.8F;
      this.mPercentage = f1;
      invalidate(this.thumbRect);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannels.UploadVoiceView
 * JD-Core Version:    0.6.2
 */